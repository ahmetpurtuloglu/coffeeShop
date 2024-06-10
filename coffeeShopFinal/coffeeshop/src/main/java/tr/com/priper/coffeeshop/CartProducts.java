package tr.com.priper.coffeeshop;

import java.io.Serializable;
import java.util.ArrayList;

public class CartProducts implements Serializable{
	
	ArrayList<CartProduct> products;

	public CartProducts() {
		this.products = new ArrayList<CartProduct>();
	}
	
	public ArrayList<CartProduct> getProducts(){
		return products;
	}
	
	public int totalItemsInCart() {
		int count = 0;
		for (CartProduct product: products) {
			count += product.getCount();
		}
		return count;
	}
	
	public int totalProductsInCart() {
		return products.size();
	}
	
	public void emptyCart() {
		this.products = new ArrayList<CartProduct>();
	}
	
	public boolean hasProduct(Product product) {
		for( CartProduct p: products ) {
			if (p.getProduct().getName().equals(product.getName())) return true;
		}
		return false;
	}
	
	public CartProduct getCartProduct(Product product) {
		for( CartProduct p: products ) {
			if (p.getProduct().getName().equals(product.getName())) return p;
		}
		return null;
	}
	
	public int getCartProductQuantity(Product product) {
		for( CartProduct p: products ) {
			if (p.getProduct().getName().equals(product.getName())) return p.getCount();
		}
		return 0;
	}
	
	
	public void addProduct(Product newProduct) {
		
		boolean productExists = false;
		for( CartProduct product: products ) {
			if (product.getName().equals(newProduct.getName())){
				product.tweakCount(1);
				productExists = true;
			}
		}
		if (productExists == false) {
			products.add(new CartProduct(newProduct));
		}
		
		
	}
	
	
	public void removeProduct(Product newProduct) {
		
		for( int i = 0; i < products.size(); i++) {
			CartProduct product = products.get(i);
			if (product.getName().equals(newProduct.getName())){
				if (product.getCount() == 1) {
					products.remove(product);
				}
				else{product.tweakCount(-1);}
			}
		}
				
	}
	
	public void removeProduct(CartProduct newProduct) {
		
		for( int i = 0; i < products.size(); i++) {
			CartProduct product = products.get(i);
			if (product.getName().equals(newProduct.getName())){
				if (product.getCount() == 1) {
					products.remove(product);
				}
				else{product.tweakCount(-1);}
			}
		}
		
	}
	
	public void sendOrder() {
		Main.sendOrder(this);
		this.emptyCart();
	}
	
	
}
