package edu.hm.ba.kongo.shop.shoppingcart.client.local;

/*
 * This file will NOT be overwritten by GAIA.
 * This file was automatically generated by GAIA.
 */
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import org.springframework.hateoas.ResourceSupport;
/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
public class CartItem_ {
	
	@NotNull
	@Size(max=24)
	private String product;
	
	@NotNull
	@Min((long)1.0)
	private long quantity;
	
	public CartItem_(){}
	
	/**
     * Create a new CartItem_ with the  product, quantity.
     *
     * @param product the product of the CartItem_.
     * @param quantity the quantity of the CartItem_.
     */
    public CartItem_( String product, long quantity) {
        this.setProduct(product);
        this.setQuantity(quantity);
    }
	
	// Getters and Setters
	public String getProduct(){
		return product;
	}
	
	public void setProduct(String product){
		this.product = product;
	}
	
	public long getQuantity(){
		return quantity;
	}
	
	public void setQuantity(long quantity){
		this.quantity = quantity;
	}
	
	 /**
      * A simple Enum for all the Fields of this CartItem_.
      * <p>
      *     You can use {@link Field#name()} for the String.
      * </p>
      */
	public enum Field {
        product, quantity;

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
	  * A simple Enum for all the Relations ({@link CartItem_#getLink(String)} of the CartItem_.
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
		if (!(other.getClass() == CartItem_.class))
			return false;
		CartItem_ cartItem = (CartItem_) other;
		if (getProduct() != null ? !getProduct().equals(cartItem.getProduct()) : cartItem.getProduct() != null)
			return false;
		if (getQuantity() != cartItem.getQuantity())
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + (getProduct() != null ? getProduct().hashCode() : 0);
		result = 31 * result + (int) (getQuantity() ^ (getQuantity() >>> 32));
		return result;
	}

	/**
	 * Returns a String representation for this CartItem.
	 * The form is:
	 * <EntityName>
	 * <attribute1_Type> <attribute1_name>: <attribute1_value>
	 * <attribute2_Type> <attribute2_name>: <attribute2_value>
	 * ...
	 */
	@Override
	public String toString(){
		String s = "CartItem";
		s += "\nString product: " + getProduct();
		s += "\nlong quantity: " + getQuantity();
		return s;
	}
}
