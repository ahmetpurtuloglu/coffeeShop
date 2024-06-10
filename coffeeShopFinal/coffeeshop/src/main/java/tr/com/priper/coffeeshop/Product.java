package tr.com.priper.coffeeshop;

import java.io.Serializable;

public class Product implements Serializable {
	
	private String name;
	private String description;
	private double price;
	private int grams;
	private int calories;
	private String imageLink;
	
	
	public int getGrams() {
		return grams;
	}

	public void setGrams(int grams) {
		this.grams = grams;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}	
	
	public Product(String name, double price, String description, int calories, int grams, String imageLink) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.calories = calories;
		this.grams = grams;
		this.imageLink = imageLink;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageLink() {
		return imageLink;
	}
	public String getImageLink(boolean withQuotes) {
		if (withQuotes) {
			imageLink = "\"" + imageLink + "\"";
		}
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}