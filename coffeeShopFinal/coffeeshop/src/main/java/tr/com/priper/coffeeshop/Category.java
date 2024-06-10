package tr.com.priper.coffeeshop;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.io.Serializable;

public class Category implements Serializable {
	
	String name;
	LinkedList<Product> products;
	String imageLink;

	public Category(String name, String imageLink) {
		this.name = name;
		this.imageLink = imageLink;
	}
	
	public boolean hasProducts() {
		if (products != null) {
			return true;
		}
		return false;
	}
	
	public void editProduct(String name, double price, String description, int calories, int grams, String imageLink) {
		
		for (Product product: products) {
			if (product.getName().equals(name)){
				product.setPrice(price);
				product.setDescription(description);
				product.setCalories(calories);
				product.setGrams(grams);
				product.setImageLink(imageLink);
			}
		}
		
	}
	
	public void removeProduct(String name) {
		Product toRemove = null;
		for (Product product: products) {
			if (product.getName().equals(name)){
				toRemove = product;
			}
		}
		products.remove(toRemove);
		
	}
	
	public void addProduct(Product newProduct) {
		if (products == null) {
			products = new LinkedList<Product>();
		}
		products.add(newProduct);
		Collections.sort(products, new Comparator<Product>(){ // ensure alphabetical sorting
			public int compare(Product p1, Product p2) {
				return p1.getName().compareTo(p2.getName());
			}
		});
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getImageLink(boolean withQuotes) {
		if (withQuotes) {
			imageLink = "\"" + imageLink + "\"";
		}
		return imageLink;
	}
	
	public String getImageLink() {
		return imageLink;
	}

	public LinkedList<Product> getProducts() {
		return products;
	}
	
	public int numberOfProducts() {
		return products.size();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	
	
}