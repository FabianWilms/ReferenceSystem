package edu.hm.ba.kongo.shop.warehouse.client.rest;

import edu.hm.ba.kongo.shop.warehouse.client.domain.Product_DTO;
import edu.hm.ba.kongo.shop.warehouse.client.hateoas.Product_Assembler;
import edu.hm.ba.kongo.shop.warehouse.client.local.Product_;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
public class Product_RestClientImpl implements Product_RestClient {
	
	public static final String FIND_FULL_TEXT_FUZZY = "findFullTextFuzzy";
	
	public static final String SEARCH = "search";
	
	/** Used to follow HATEOAS relations. */
    private final Traverson traverson;
    
    /** The restTemplate used for the HTTP Requests. */
    private final RestTemplate restTemplate;
    
    /** Assembler to switch from Product_DTO Resource to Product_ and vice versa. */
    private final Product_Assembler productAssembler = new Product_Assembler();
	
	/**
	 * Create a new Product_RestClient by RestTemplate of the server.
     * @param restTemplate The restTemplate for the HTTP Requests.
     * @param basePath The base URI of the REST Server.
     */
    public Product_RestClientImpl(RestTemplate restTemplate, final URI basePath) {
        this.restTemplate = restTemplate;
        traverson = new Traverson(basePath, MediaTypes.HAL_JSON);
        traverson.setRestOperations(restTemplate);
    }
	
	@Override
	public List<Product_> findFullTextFuzzy(String filter) {
		return traverson.follow(PRODUCTS, SEARCH, FIND_FULL_TEXT_FUZZY)
				.withTemplateParameters(Collections.singletonMap("q", filter))
				.toObject(Product_Resource.LIST).getContent()
				.stream()
				.map(productAssembler::toBean)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Product_> findAll() {  
		return traverson
				.follow(PRODUCTS)
				.toObject(Product_Resource.LIST).getContent()
				
				.stream()
				.map(productAssembler::toBean)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Product_> findAll(Link relation) {
		URI uri = URI.create(relation.getHref());
		return restTemplate
		 		.exchange(uri, HttpMethod.GET, null, Product_Resource.LIST)
		 		.getBody()
		 		.getContent()
		 		
		 		.stream()
		 		.map(productAssembler::toBean)
		 		.collect(Collectors.toList());
	}
		
	@Override
	public Optional<Product_> findOne(Link link) {
	     URI uri = URI.create(link.getHref());
	     
		Product_Resource resource;
		
		try {
			resource = restTemplate.exchange(uri, HttpMethod.GET, null, Product_Resource.class).getBody();
		} catch (HttpClientErrorException e) {
			final HttpStatus statusCode = e.getStatusCode();
			if (!HttpStatus.NOT_FOUND.equals(statusCode))
				throw e;
			if (Link.REL_SELF.equals(link.getRel()))
				throw e;
			return Optional.empty();
		}
		
		return Optional.of(productAssembler.toBean(resource));
	}
	
	@Override
	public void setRelations(Link endpoint, Collection<Link> links) {
		String relations = links.stream().map(Link::getHref).collect(Collectors.joining("\n"));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "uri-list"));
		restTemplate.exchange(URI.create(endpoint.getHref()), HttpMethod.PUT, new HttpEntity<>(relations, headers), Void.class);
	}
	
	@Override
    public void setRelation(Link endpoint, Link relation) {
    	Optional<Link> rel = Optional.ofNullable(relation);
		if (rel.isPresent()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("text", "uri-list"));
			restTemplate.exchange(URI.create(endpoint.getHref()), HttpMethod.PUT, new HttpEntity<>(rel.map(Link::getHref).get(), headers), Void.class);
		} else {
			this.delete(endpoint);
		}
    }

		
	@Override
	public Product_ create(Product_ product) {
		URI uri = URI.create(
		traverson.follow(PRODUCTS).asLink().getHref());
			Product_DTO productDTO = productAssembler.toResource(product).getContent();
			Product_Resource resource = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(productDTO), Product_Resource.class).getBody();
			return productAssembler.toBean(resource);
	}
	
	@Override
	public Product_ update(Product_ product) {

        URI uri = URI.create(product.getId().getHref());

        Product_DTO productDTO = productAssembler.toResource(product).getContent();

        Product_Resource resource = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(productDTO), Product_Resource.class).getBody();

        return productAssembler.toBean(resource);
    }
		
	@Override
	public void delete(Link id) {
		URI uri = URI.create(id.getHref());
		restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);
	}
}
