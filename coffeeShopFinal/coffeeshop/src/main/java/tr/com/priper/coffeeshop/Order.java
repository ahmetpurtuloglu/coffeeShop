package tr.com.priper.coffeeshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
	
	int id;
	CartProducts products;
	Date dateReceived;
	Date dateDelivered;
	boolean delivered;
	User user;
	
	
	public Order(CartProducts products) {
		this.id = Main.orders.size() + 1;
		this.products = products;
		this.dateReceived = new Date();
		this.dateDelivered = null;
		this.delivered = false;
		if (Main.user != null) {
			this.user = Main.user;
		}
		else {
			this.user = null;
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public Date getDateDelivered() {
		return dateDelivered;
	}

	public void setDateDelivered(Date dateDelivered) {
		this.dateDelivered = dateDelivered;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
		if (delivered) this.dateDelivered = new Date();
	}
	
	public double getDeliveryTimeInSeconds() {
		return (double)(dateDelivered.getTime() - dateReceived.getTime())/1000;
	}
	
	public double getDeliveryTimeInMinutes() {
		return (double)(dateDelivered.getTime() - dateReceived.getTime())/1000/60;
	}
	
	public double getDeliveryTimeInHours() {
		return (double)(dateDelivered.getTime() - dateReceived.getTime())/1000/60/60;
	}
	
	public String getDeliveryTime() {
		double seconds = getDeliveryTimeInSeconds();
		if (seconds > 3600) return getDeliveryTimeInHours() + " hours";
		if (seconds > 60) return getDeliveryTimeInMinutes() + " minutes";
		return getDeliveryTimeInSeconds() + " seconds";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void markAsComplete() {
		this.dateDelivered = new Date();
		this.delivered = true;
	}
	
	public CartProducts getCart(){
		return products;
	}
	
	public ArrayList<CartProduct> getProducts(){
		return products.getProducts();
	}
	
	public double getTotal() {
		double total = 0.00;
		for (CartProduct cartProduct: getProducts()) {
			total+= cartProduct.getTotalPrice();
		}
		return total;
	}
	
	public int totalItemsInCart() {
		int count = 0;
		for (CartProduct product: products.getProducts()) {
			count += product.getCount();
		}
		return count;
	}
	
	public int totalProductsInCart() {
		return products.getProducts().size();
	}
	
	public boolean hasProduct(Product product) {
		for( CartProduct p: products.getProducts()) {
			if (p.getProduct().getName().equals(product.getName())) return true;
		}
		return false;
	}
	
	public CartProduct getCartProduct(Product product) {
		for( CartProduct p: products.getProducts()) {
			if (p.getProduct().getName().equals(product.getName())) return p;
		}
		return null;
	}
	
	public int getCartProductQuantity(Product product) {
		for( CartProduct p: products.getProducts()) {
			if (p.getProduct().getName().equals(product.getName())) return p.getCount();
		}
		return 0;
	}
	
	
	
}
