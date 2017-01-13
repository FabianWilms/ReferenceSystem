package edu.hm.ba.kongo.shop.ordering.guilib.gen.ui.controller;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import de.muenchen.eventbus.EventBus;
import de.muenchen.eventbus.events.Association;
import de.muenchen.eventbus.selector.entity.RequestEntityKey;
import de.muenchen.eventbus.selector.entity.RequestEvent;
import de.muenchen.eventbus.selector.entity.ResponseEntityKey;

import edu.hm.ba.kongo.shop.ordering.guilib.gen.services.Order_Service;
import edu.hm.ba.kongo.shop.ordering.client.local.Order_;
import edu.hm.ba.kongo.shop.ordering.guilib.gen.services.model.Order_Datastore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Link;
import reactor.bus.Event;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import de.muenchen.vaadin.guilib.BaseUI;

/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
@SpringComponent
@UIScope
public class Order_ViewController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(Order_ViewController.class);
	
	/**
	 * Die Order_Service Klasse
	 */
	@Autowired
	Order_Service orderService;
	
	private final Order_Datastore model = new Order_Datastore();
	

	@PostConstruct
	private void init() {
		initEventhandlers();
	}
	
	private EventBus getEventbus() {
        return BaseUI.getCurrentEventBus();
    }
	
	public Order_Datastore getModel() {
		return model;
	}

	////////////////////////
	// Service Operations //
	////////////////////////

	/**
	 * Speichert ein {@link Order_} Objekt in der Datenbank.
	 *
	 * @param order Order_ der gespeichert werden soll
	 */
	public Order_ save(Order_ order) {
		return orderService.create(order);
	}
	/**
	 * Speichert die Änderungen an einem {@link Order_} Objekt in der Datenbank.
	 *
	 * @param entity Order_
	 * @return Order_
	 */
	public Order_ updateOrder(Order_ entity) {
		return orderService.update(entity);
	}

	/**
	 * Löscht ein {@link Order_} Objekt.
	 *
	 * @param entity Order_
	 */
	public void deleteOrder(Order_ entity) {
		orderService.delete(entity.getId());
	}

	public List<Order_> queryOrder() {
		return orderService.findAll().stream().collect(Collectors.toList());
	}

	public List<Order_> queryOrder(String query) {
		return orderService.queryOrder(query);
	}
	
	/////////////////////
	// Event Steuerung //
	/////////////////////

	/**
	 * Register all event handlers on the RequestEntityKey.
	 */
	private void initEventhandlers() {
		getEventbus().on(getRequestKey(RequestEvent.CREATE).toSelector(), this::create);
		getEventbus().on(getRequestKey(RequestEvent.DELETE).toSelector(), this::delete);	 
		getEventbus().on(getRequestKey(RequestEvent.UPDATE).toSelector(), this::update);
		getEventbus().on(getRequestKey(RequestEvent.ADD_ASSOCIATION).toSelector(), this::addAssociation);	 
		getEventbus().on(getRequestKey(RequestEvent.REMOVE_ASSOCIATION).toSelector(), this::removeAssociation);
		getEventbus().on(getRequestKey(RequestEvent.ADD_ASSOCIATIONS).toSelector(), this::addAssociations);
		getEventbus().on(getRequestKey(RequestEvent.REMOVE_ASSOCIATIONS).toSelector(), this::removeAssociations);
		getEventbus().on(getRequestKey(RequestEvent.READ_LIST).toSelector(), this::readList);
		getEventbus().on(getRequestKey(RequestEvent.READ_SELECTED).toSelector(), this::readSelected);
	}

	/**
	 * Remove the specified Association from the specified Relation and update the DataStore.
	 * Update the Model and send it on the ResponseEntityKey if necessary.
	 *
	 * @param event The event with an {@link Association} as {@link Event#getData()}.
	 */
	void removeAssociation(Event<?> event) {
		final Object data = event.getData();
		if (data == null)
			throw new NullPointerException("Event data must not be null!");
		if (data.getClass() != Association.class)
			throw new IllegalArgumentException("The event must be of " + Association.class);

		final Association<?> association = (Association<?>) event.getData();
		final Order_.Rel rel = Order_.Rel.valueOf(association.getRel());
		
		notifyComponents();
	}

	/**
	 * Add the specified Association to the specified Relation and update the DataStore.
	 * <p>	 
	 * If the {@link Association#getAssociation()} has no {@link ResourceSupport#getId()} the Resouce will be created
	 * on the DataStore first.
	 * </p>
	 * Update the Model and send it on the ResponseEntityKey if necessary.
	 *
	 * @param event The event with an {@link Association} as {@link Event#getData()}.
	 */
	private void addAssociation(Event<?> event) {
		final Object data = event.getData();
		if (data == null) 
			throw new NullPointerException("Event data must not be null!");
		if (data.getClass() != Association.class)
			throw new IllegalArgumentException("The event must be of " + Association.class);

		final Association<?> association = (Association<?>) event.getData();

		final Order_.Rel rel = Order_.Rel.valueOf(association.getRel());
		refreshModelAssociations();
		notifyComponents();
	}
	
	/**
	 * Remove the specified Associations from the specified Relation and update the DataStore.
	 * Update the Model and send it on the ResponseEntityKey if necessary.
	 *
	 * @param event The event with an {@link Association} as {@link Event#getData()}.
	 */
	@SuppressWarnings("unchecked")
	void removeAssociations(Event<?> event) {
		final Object data = event.getData();
		if (data == null)
			throw new NullPointerException("Event data must not be null!");
		if (!(data instanceof List))
			throw new IllegalArgumentException("The event must be of " + List.class);
		
		final List<?> dataList = (List<?>) event.getData();
		if (dataList.isEmpty())
			throw new IllegalArgumentException("No Data provided");
		if (!dataList.stream().map(Object::getClass).allMatch(Association.class::equals))
			throw new IllegalArgumentException("The event must be a list of " + Association.class);
		
		final List<Association<?>> associations = (List<Association<?>>) dataList;
		if(!associations.stream().map(Association::getRel).allMatch(associations.get(0).getRel()::equals))
			throw new IllegalArgumentException("Associations must be of same Relation");
		
		final Order_.Rel rel = Order_.Rel.valueOf(associations.get(0).getRel());
		
		notifyComponents();
	}
	
	/**
	 * Add the specified Associations to the specified Relation and update the DataStore.
	 * <p>
	 * If the {@link Association#getAssociation()} has no {@link ResourceSupport#getId()} the Resouce will be created
	 * on the DataStore first.
	 * </p>
	 * Update the Model and send it on the ResponseEntityKey if necessary.
	 *
	 * @param event The event with an {@link Association} as {@link Event#getData()}.
	 */
	@SuppressWarnings("unchecked")
	private void addAssociations(Event<?> event) {
		final Object data = event.getData();
		if (data == null)
			throw new NullPointerException("Event data must not be null!");
		if (! (data instanceof List))
			throw new IllegalArgumentException("The event must be of " + List.class);
		
		final List<?> dataList = (List<?>) event.getData();
		if (dataList.isEmpty())
			throw new IllegalArgumentException("No Data provided");
		if (!dataList.stream().map(Object::getClass).allMatch(Association.class::equals))
			throw new IllegalArgumentException("The event must be a list of " + Association.class);
			
		final List<Association<?>> associations = (List<Association<?>>) dataList;
		if(!associations.stream().map(Association::getRel).allMatch(associations.get(0).getRel()::equals))
			throw new IllegalArgumentException("Associations must be of same Relation");
		
		final Order_.Rel rel = Order_.Rel.valueOf(associations.get(0).getRel());
		refreshModelAssociations();
		notifyComponents();
	}

	/**	
	 * Create a new Buerger on the DataStore.
	 * Update the Model and send it on the ResponseEntityKey if necessary.
	 *
	 * @param event The event with an {@link Order_} as {@link Event#getData()}.
	 */
	private void create(Event<?> event) {
		final Object data = event.getData();
		if (data == null) 
			throw new NullPointerException("Event data must not be null!");
		if (!(data instanceof Order_))
			throw new IllegalArgumentException("The event must be of " + Order_.class);
		final Order_ order = (Order_) event.getData();
		final Order_ fromREST = orderService.create(order);
		getModel().getOrders().addBean(fromREST);
		notifyComponents();
	}


	/**
	 * Delete the Order_ on the DataStore.
	 * Update the Model and send it on the ResponseEntityKey if necessary.
	 *
	 * @param event The event with an {@link Order_} as {@link Event#getData()}.
	 */
	private void delete(Event<?> event) {
		final Object data = event.getData();
		if (data == null) 
			throw new NullPointerException("Event data must not be null!");
		if (!(data instanceof Order_))
			throw new IllegalArgumentException("The event must be of " + Order_.class);
		final Order_ order = (Order_) event.getData();
		if (order.getId() == null)
			throw new IllegalArgumentException("The Order_ must have an ID.");
		orderService.delete(order.getId());
		getModel().getSelectedOrder().ifPresent(selectedOrder -> {
			if (selectedOrder.equals(order)) {
				getModel().setSelectedOrder(null);
				// reset all selected relations
			}
		});
		getModel().getOrders().removeItem(order);
		notifyComponents();
	}

	/**
	 * Update the Order_ on the DataStore.
	 * Update the Model and send it on the ResponseEntityKey if necessary.
	 *
	 * @param event The event with an {@link Order_} as {@link Event#getData()}.
	 */
	private void update(Event<?> event) {
		final Object data = event.getData();	 
		if (data == null) 
			throw new NullPointerException("Event data must not be null!");
		if (!(data instanceof Order_))
			throw new IllegalArgumentException("The event must be of " + Order_.class);
		final Order_ order = (Order_) event.getData();
		if (order.getId() == null)
			throw new IllegalArgumentException("The Order_ must have an ID.");
		final Order_ fromREST = orderService.update(order);
		refreshModelSelected();
		getModel().getOrders().addBean(fromREST);
		notifyComponents();
	}

	/**
	 * Refresh the {@link Order_Datastore#orders} list from the DataStore.
	 * <p/>	
	 * <p>
	 * This method also filters by the query (ifPresent).
	 * </p>
	 */
	private void refreshModelList() {
		final Optional<String> query = getModel().getQuery();
		if (query.isPresent()) {
			getModel().getOrders().removeAllItems();
			getModel().getOrders().addAll(queryOrder(query.get()));
		} else {
			getModel().getOrders().removeAllItems();
			getModel().getOrders().addAll(queryOrder());
		}
	}

	/**
	 * Refresh *all* the associations of the selected Order_ in the model.
	 */
	void refreshModelAssociations() {
		getModel().getSelectedOrder().ifPresent(order -> {
		});	
	}

	/**
	 * Refresh the current selected Order_, but *not* its associations.
	 */
	private void refreshModelSelected() {
		getModel().getSelectedOrder().ifPresent(order -> getModel().setSelectedOrder(orderService.findOne(order.getId()).orElse(null)));
	}

	/**	
	 * Set the query based on the String sent in the Event.
	 * Update the Model and send it on the ResponseEntityKey if necessary.
	 *
	 * @param event The event with a {@link String} query as {@link Event#getData()}.
	 */
	private void readList(Event<?> event) {
		final Object data = event.getData();

		if (data instanceof String) {
			final String filter = (String) event.getData();
			getModel().setQuery(filter);
		} else {
			getModel().setQuery(null);
		}

		refreshModelList();
		notifyComponents();
	}

	/**
	 * Read the Order_ in the Event from the DataStore and set it as the current selected Order_.
	 * If called with null, the current selected Order_ will only be refreshed from the DataStore.
	 * Update the Model and send it on the ResponseEntityKey if necessary.
	 *
	 * @param event The event with an {@link Order_} or *null* as {@link Event#getData()}.
	 */
	private void readSelected(Event<?> event) {
		final Object data = event.getData();

		if (data instanceof Order_) {
			final Order_ order = (Order_) event.getData();
			getModel().setSelectedOrder(order);
			refreshModelSelected();
			refreshModelAssociations();
		} else if (data == null) {
			refreshModelSelected();
			refreshModelAssociations();
		} else {
			throw new IllegalArgumentException("The event cannot be of Class " + event.getData().getClass());
		}
		notifyComponents();
    }

	/**
	 * Notify all the Components.
	 */
	public void notifyComponents() {
		getEventbus().notify(getResponseKey(), Event.wrap(getModel()));
	}

	/**
	 * Get the RequestEntityKey for this Entity.
	 *
	 * @param event The disered event the Key will have.
	 * @return The RequestEntityKey with the chosen RequestEvent.
	 */
	public RequestEntityKey getRequestKey(RequestEvent event) {
		return new RequestEntityKey(event, Order_.class);
	}

	/**
	 * Get the ResponseEntityKey for this Entity.
	 *
	 * @return The ResponseEntityKey.
	 */
	public ResponseEntityKey getResponseKey() {
		return new ResponseEntityKey(Order_.class);
	}
}