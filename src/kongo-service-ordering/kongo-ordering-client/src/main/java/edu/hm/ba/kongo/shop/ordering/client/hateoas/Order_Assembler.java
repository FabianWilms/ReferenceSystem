package edu.hm.ba.kongo.shop.ordering.client.hateoas;

import edu.hm.ba.kongo.shop.ordering.client.domain.Order_DTO;
import edu.hm.ba.kongo.shop.ordering.client.local.Order_;
import edu.hm.ba.kongo.shop.ordering.client.rest.Order_Resource;
import org.springframework.hateoas.Resource;
import java.util.UUID;

/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
public class Order_Assembler {


	/**
	 * Transform the Resource (from the REST Server) to the local object representation.
	 *
	 * @param resource the REST DTO Resource
	 * @return the local Object Representation
	 */
	public Order_ toBean(Resource<Order_DTO> resource) {
		Order_DTO orderDTO = resource.getContent();
		Order_ bean = new Order_();		
		bean.setCart(orderDTO.getCart());
		bean.setOrderedOn(orderDTO.getOrderedOn());
		bean.add(resource.getLinks());
		
		return bean;
	}
	
	/**
	 * Transform the local object representation to the DTO resource.
	 *
	 * @param bean the local object representation
	 * @return the REST DTO Resource
	 */
	public Order_Resource toResource(Order_ bean) {
		return new Order_Resource(toDTO(bean), bean.getLinks());
	}
	
	/**
	 * Transform the local object representation to the DTO.
	 *
	 * @param bean the local object representation
	 * @return the REST DTO
	 */
	public Order_DTO toDTO(Order_ bean) {
		Order_DTO orderDTO = new Order_DTO();
		
		try{
			String[] id = bean.getId().getHref().split("/");
			orderDTO.setOid(UUID.fromString(id[id.length-1]));
		} catch(Exception e) {/* No Link found */}
		
		orderDTO.setCart(bean.getCart());
		orderDTO.setOrderedOn(bean.getOrderedOn());
		
		return orderDTO;
	}
}
