package tr.com.priper.coffeeshop;

import java.util.ArrayList;

public class NewCategoryHandler {
	
	String name;
	ArrayList<Product> products;

	public NewCategoryHandler(String name) {
		this.name = name;
	}
	
	public boolean hasProducts() {
		if (products != null) {
			return true;
		}
		return false;
	}
	
	public void addProduct(Product newProduct) {
		if (products == null) {
			products = new ArrayList<Product>();
		}
		products.add(newProduct);
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public int numberOfProducts() {
		return products.size();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}