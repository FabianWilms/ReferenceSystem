package edu.hm.ba.kongo.shop.ordering.guilib.gen.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import edu.hm.ba.kongo.shop.ordering.guilib.services.Order_FallbackDataGenerator;

import de.muenchen.vaadin.guilib.services.SecurityService;
import de.muenchen.vaadin.demo.i18nservice.I18nPaths;
import de.muenchen.vaadin.demo.i18nservice.buttons.SimpleAction;
import de.muenchen.vaadin.guilib.BaseUI;
import de.muenchen.vaadin.guilib.components.GenericErrorNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static de.muenchen.vaadin.demo.i18nservice.I18nPaths.getNotificationPath;

/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
@SpringComponent @UIScope
public class Order_ServiceImpl implements Order_Service, Serializable {
    
    private Order_RestClient client;
    private SecurityService securityService;

    @Autowired
    public Order_ServiceImpl(SecurityService securityService, @Value("${ORDERING.microservice.basePath}") String basePath) {
        this.securityService = securityService;
        final URI baseUri = URI.create(basePath);
		this.client = new Order_RestClientImpl(getTemplate(), baseUri);
    }

	/**
	 * creates one Order_
	 * @param order the one who shall be created
	 * @return order
	 */
    @Override
    @HystrixCommand(fallbackMethod = "defaultCreate")
    public Order_ create(Order_ order) {
        return client.create(order);
    }
    public Order_ defaultCreate(Order_ bean) {
		showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.create);
		return Order_FallbackDataGenerator.createOrderFallback();
	}

	/**
	 * updates one Order_
	 * @param order the one who shall be updated
	 * @return order
	 */
    @Override
    @HystrixCommand(fallbackMethod = "defaultUpdate")
    public Order_ update(Order_ order) {
        return client.update(order);
    }
    public Order_ defaultUpdate(Order_ bean) {
		showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.update);
		return Order_FallbackDataGenerator.createOrderFallback();
	}

	/**
	 * Method to delete a certain link
	 * @param link the link
	 * @return successful
	 */
    @Override
    @HystrixCommand(fallbackMethod = "defaultDelete")
    public boolean delete(Link link) {
    	try {
    		client.delete(link);
    	} catch (HttpClientErrorException e) {
    		final HttpStatus statusCode = e.getStatusCode();
			if (!HttpStatus.CONFLICT.equals(statusCode))
				throw e;
			showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.delete, statusCode.toString());
			return false;
		}
		return true;
    }
	public boolean defaultDelete(Link link) {
		showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.delete);
		return false;
	}

	/**
	 * Returns all Order_
	 * @return orders the results
	 */
    @Override
    @HystrixCommand(fallbackMethod = "defaultFindAll")
    public List<Order_> findAll() {
        return client.findAll();
    }
    public List<Order_> defaultFindAll() {
		showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
		return Order_FallbackDataGenerator.createOrdersFallback();
	}

	/**
	 * Returns all Order_ of a specified relation
	 * @param relation the relation
	 * @return orders the results
	 */
    @Override
    @HystrixCommand(fallbackMethod = "defaultFindAll")
    public List<Order_> findAll(Link relation) {
        return client.findAll(relation);
    }
    public List<Order_> defaultFindAll(Link relation) {
		showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
		return Order_FallbackDataGenerator.createOrdersFallback();
	}

	/**
	 * Returns an Optional of Order_ to one Link
	 * @param link the link
	 * @return the found Order_ 
	 */
    @Override
    @HystrixCommand(fallbackMethod = "defaultFindOne")
    public Optional<Order_> findOne(Link link) {
        return client.findOne(link);
    }
    public Optional<Order_> defaultFindOne(Link link) {
		showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
		return Order_FallbackDataGenerator.createOptionalOrderFallback();
	}
    

	/**
	 * Query to given string
	 * @param query
	 * @return orders the results
	 */
    @Override
    @HystrixCommand(fallbackMethod = "defaultQueryOrder")
    public List<Order_> queryOrder(String query) {
        return client.findFullTextFuzzy(query);
    }
    public List<Order_> defaultQueryOrder(String query) {
		showErrorNotification(I18nPaths.NotificationType.error,SimpleAction.read);
		return Order_FallbackDataGenerator.createOrdersFallback();
	}

	/**
     * Sets relations to entities
     * @param link the endpoint of the relation
     * @param links collection of links that are set to be related
     * @return successful
     */
    @Override
    @HystrixCommand(fallbackMethod = "defaultSetRelations")
    public void setRelations(Link link, List<Link> links) {
        client.setRelations(link, links);
    }
    public void defaultSetRelations(Link link, List<Link> links) {
		showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.association);
	}

	/**
     * Sets a relation to an entity
     * @param link the endpoint of the relation
     * @param relation the link that is set to be related
     * @return successful
     */
    @Override
    @HystrixCommand(fallbackMethod = "defaultSetRelation")
    public void setRelation(Link link, Link relation) {
        client.setRelation(link, relation);
    }
    public void defaultSetRelation(Link link, Link relation) {
		showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.association);
	}

    /**
     * Gets the resttemplate from the security if not present
     * @return resttemplate of this session
     */
    public RestTemplate getTemplate() {
        return securityService.getRestTemplate().orElse(null);
    }

	/**
     * Shows an error notification
     * @param type   the type of the notification
     * @param action the type of action performed
     */
    private void showErrorNotification(I18nPaths.NotificationType type, SimpleAction action) {
        GenericErrorNotification succes = new GenericErrorNotification(
                BaseUI.getCurrentI18nResolver().resolveRelative(Order_.class, getNotificationPath(type, action, I18nPaths.Type.label)),
                BaseUI.getCurrentI18nResolver().resolveRelative(Order_.class, getNotificationPath(type, action, I18nPaths.Type.text)));
        succes.show(Page.getCurrent());
    }

	/**
     * Shows an error notification specified by a status code
     * @param type       the type of the notification
     * @param action     the type of action performed
     * @param statusCode the status code
     */
    private void showErrorNotification(I18nPaths.NotificationType type, SimpleAction action, String statusCode) {
        GenericErrorNotification succes = new GenericErrorNotification(
                BaseUI.getCurrentI18nResolver().resolveRelative(Order_.class, getNotificationPath(type, action, I18nPaths.Type.label, statusCode)),
                BaseUI.getCurrentI18nResolver().resolveRelative(Order_.class, getNotificationPath(type, action, I18nPaths.Type.text, statusCode)));
        succes.show(Page.getCurrent());
    }
}
