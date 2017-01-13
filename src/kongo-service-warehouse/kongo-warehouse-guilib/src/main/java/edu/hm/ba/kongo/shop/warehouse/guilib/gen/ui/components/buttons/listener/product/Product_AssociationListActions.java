package edu.hm.ba.kongo.shop.warehouse.guilib.gen.ui.components.buttons.listener.product;

import de.muenchen.eventbus.events.Association;
import edu.hm.ba.kongo.shop.warehouse.client.local.Product_;
import de.muenchen.vaadin.guilib.components.actions.EntityAssociationListAction;
import java.util.List;
import java.util.function.Supplier;

/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
public class Product_AssociationListActions extends EntityAssociationListAction<Product_> {
	
	/**
	 * Create new AssociationActions for the Entity with the single association.
	 *
	 * @param association The association.
	 */
	public Product_AssociationListActions(Supplier<List<Association<?>>> association) {
		super(association, Product_.class);
	}
}
