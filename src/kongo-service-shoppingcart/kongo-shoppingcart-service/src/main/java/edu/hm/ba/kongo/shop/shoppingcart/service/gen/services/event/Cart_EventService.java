package edu.hm.ba.kongo.shop.shoppingcart.service.gen.services.event;


import edu.hm.ba.kongo.shop.shoppingcart.service.gen.domain.Cart_;

/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
/**
 * Provides methods to implement logic before and after Events.
 */
public interface Cart_EventService {
	void onAfterCreate(Cart_ entity);
	void onBeforeCreate(Cart_ entity);
	void onBeforeSave(Cart_ entity);
	void onAfterSave(Cart_ entity);
	void onBeforeLinkSave(Cart_ parent, Object linked);
	void onAfterLinkSave(Cart_ parent, Object linked);
	void onBeforeLinkDelete(Cart_ parent, Object linked);
	void onBeforeDelete(Cart_ entity);
	void onAfterDelete(Cart_ entity);
	void onAfterLinkDelete(Cart_ parent, Object linked);
}