package tr.com.priper.coffeeshop;

import java.io.Serializable;

public class CartProduct implements Serializable{
	
	Product product;
	int count;
	
	CartProduct(Product product){
		this.product = product;
		this.count = 1;
	}
	
	public double getPrice() {
		return product.getPrice();
	}
	
	public double getTotalPrice() {
		return roundPrice(product.getPrice() * count);
	}

	public Product getProduct() {
		return product;
	}
	
	public String getName() {
		return product.getName();
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void tweakCount(int count) {
		if (this.count > 0) {
		this.count += count;
		}
	}
	
	public static double roundPrice(double value) {
		 value *= 100;
		 Math.round(value);
		 value /=100;
		 return value;
	}

	
}
