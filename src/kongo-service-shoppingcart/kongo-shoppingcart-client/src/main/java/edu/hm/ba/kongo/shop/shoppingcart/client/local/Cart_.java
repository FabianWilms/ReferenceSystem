package edu.hm.ba.kongo.shop.shoppingcart.client.local;

/*
 * This file will NOT be overwritten by GAIA.
 * This file was automatically generated by GAIA.
 */
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.DecimalMax;
import java.math.BigDecimal;
import edu.hm.ba.kongo.shop.shoppingcart.client.local.CartItem_;
import org.springframework.hateoas.ResourceSupport;
/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
public class Cart_ extends ResourceSupport {
	
	@NotNull
	@Size(max=24)
	private String userID;
	
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal totalPrice;
	
	private java.util.Set<CartItem_> items = new java.util.LinkedHashSet<>();
	
	public Cart_(){}
	
	/**
     * Create a new Cart_ with the  userID, totalPrice.
     *
     * @param userID the userID of the Cart_.
     * @param totalPrice the totalPrice of the Cart_.
     */
    public Cart_( String userID, BigDecimal totalPrice) {
        this.setUserID(userID);
        this.setTotalPrice(totalPrice);
    }
	
	// Getters and Setters
	public String getUserID(){
		return userID;
	}
	
	public void setUserID(String userID){
		this.userID = userID;
	}
	
	public BigDecimal getTotalPrice(){
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice){
		this.totalPrice = totalPrice;
	}
	
	public java.util.Set<CartItem_> getItems(){
		return items;
	}
	
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(as=java.util.LinkedHashSet.class)
	public void setItems(java.util.Set<CartItem_> value){
		this.items = value;
	}
	
	 /**
      * A simple Enum for all the Fields of this Cart_.
      * <p>
      *     You can use {@link Field#name()} for the String.
      * </p>
      */
	public enum Field {
        userID(false), totalPrice(false);

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
	  * A simple Enum for all the Relations ({@link Cart_#getLink(String)} of the Cart_.
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
		if (!(other.getClass() == Cart_.class))
			return false;
		if (!super.equals(other))
			return false;
		Cart_ cart = (Cart_) other;
		if (getItems() != null ? !getItems().equals(cart.getItems()) : cart.getItems() != null)
			return false;
		if (getUserID() != null ? !getUserID().equals(cart.getUserID()) : cart.getUserID() != null)
			return false;
		if (getTotalPrice() != cart.getTotalPrice())
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getItems() != null ? getItems().hashCode() : 0);
		result = 31 * result + (getUserID() != null ? getUserID().hashCode() : 0);
		result = 31 * result + (getTotalPrice() != null ? getTotalPrice().hashCode() : 0);
		return result;
	}

	/**
	 * Returns a String representation for this Cart.
	 * The form is:
	 * <EntityName>
	 * <attribute1_Type> <attribute1_name>: <attribute1_value>
	 * <attribute2_Type> <attribute2_name>: <attribute2_value>
	 * ...
	 */
	@Override
	public String toString(){
		String s = "Cart";
		s += "\njava.util.Set<CartItem_> items: " + getItems();
		s += "\nString userID: " + getUserID();
		s += "\nBigDecimal totalPrice: " + getTotalPrice();
		return s;
	}
}