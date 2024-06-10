package tr.com.priper.coffeeshop;

import java.io.File;
import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeshopApplication {

	public static void main(String[] args) {
		boolean exists = false;
		try{
			exists = (new File("maindb.db").isFile());
			System.out.println("Existing category/product data found. adding existing data.");
		}
		catch(Exception e){
			System.out.println("Existing category/product data not found. adding sample data.");
		}
		if(!exists) { // if existing db is not found, populating app with sample data
			Main.populateApp();
			Main.exportMainDatabase(Main.root);
		}
		else {
			Main.root = Main.importMainDatabase();
		}
		
		exists = false;
		try{
			exists = (new File("users.db").isFile());
			System.out.println("Users already exist, they have been imported.");
		}
		catch(Exception e){
			System.out.println("No users were found. Sample users have been added: joe, jack slim, elif, mansur, kemal, ekrem. Their passwords are: ssp");
		}

		if(!exists) { // if existing users are not found, populating app with sample users: joe, jack slim, elif, mansur, kemal, ekrem (each with password "ssp")
			Main.addSampleUsers();
		}

		exists = false;
		try{
			exists = (new File("orders.db").isFile());
			System.out.println("Existing order data found, and imported.");
		}
		catch(Exception e){
			System.out.println("Existing order data not found. adding sample data.");
		}

		if(!exists) { // if existing orders records are not found, making orders record
			Main.orders = new ArrayList<Order>();
			Main.exportOrders(Main.orders);
		}
		else {
			Main.orders = Main.importOrders();
		}
		
		Main.userCart = new CartProducts();
		Main.productArchive = new ArrayList<Product>();
		Main.lastCategoryTree = Main.root;
		
		SpringApplication.run(CoffeeshopApplication.class, args);
	}

}
