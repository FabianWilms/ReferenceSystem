package edu.hm.ba.kongo.shop.ordering.client.local;

/*
 * This file will NOT be overwritten by GAIA.
 * This file was automatically generated by GAIA.
 */
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import de.muenchen.vaadin.demo.apilib.domain.Past;
import org.springframework.hateoas.ResourceSupport;
/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
public class OrderingItem_ extends ResourceSupport {
	
	@NotNull
	@Size(max=36)
	private String cart;
	
	@NotNull
	@Past
	private java.time.LocalDate orderedOn;
	
	public OrderingItem_(){}
	
	/**
     * Create a new OrderingItem_ with the  cart, orderedOn.
     *
     * @param cart the cart of the OrderingItem_.
     * @param orderedOn the orderedOn of the OrderingItem_.
     */
    public OrderingItem_( String cart, java.time.LocalDate orderedOn) {
        this.setCart(cart);
        this.setOrderedOn(orderedOn);
    }
	
	// Getters and Setters
	public String getCart(){
		return cart;
	}
	
	public void setCart(String cart){
		this.cart = cart;
	}
	
	public java.time.LocalDate getOrderedOn(){
		return orderedOn;
	}
	
	public void setOrderedOn(java.time.LocalDate orderedOn){
		this.orderedOn = orderedOn;
	}
	
	 /**
      * A simple Enum for all the Fields of this OrderingItem_.
      * <p>
      *     You can use {@link Field#name()} for the String.
      * </p>
      */
	public enum Field {
        cart(false), orderedOn(false);

        private final boolean field;

        Field() {
			this(true);
		}

		Field(boolean field) {
			this.field = field;
		}

		public boolean isField() {
			return field;
		}

		public static String[] getProperties() {
			return Stream.of(values()).filter(Field::isField).map(Field::name).toArray(String[]::new);
		}
	}
	
	 /**
	  * A simple Enum for all the Relations ({@link OrderingItem_#getLink(String)} of the OrderingItem_.
	  * <p>
	  *     You can use {@link Rel#name()} for the String.
	  * </p>
	  */
	 public enum Rel {
	 	;
    }
	
	
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;
		if (!(other.getClass() == OrderingItem_.class))
			return false;
		if (!super.equals(other))
			return false;
		OrderingItem_ orderingItem = (OrderingItem_) other;
		if (getCart() != null ? !getCart().equals(orderingItem.getCart()) : orderingItem.getCart() != null)
			return false;
		if (getOrderedOn() != orderingItem.getOrderedOn())
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getCart() != null ? getCart().hashCode() : 0);
		result = 31 * result + (getOrderedOn() != null ? getOrderedOn().hashCode() : 0);
		return result;
	}

	/**
	 * Returns a String representation for this orderingItem.
	 * The form is:
	 * <EntityName>
	 * <attribute1_Type> <attribute1_name>: <attribute1_value>
	 * <attribute2_Type> <attribute2_name>: <attribute2_value>
	 * ...
	 */
	@Override
	public String toString(){
		String s = "orderingItem";
		s += "\nString cart: " + getCart();
		s += "\njava.time.LocalDate orderedOn: " + getOrderedOn();
		return s;
	}
}