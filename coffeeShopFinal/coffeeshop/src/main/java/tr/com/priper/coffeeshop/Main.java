package tr.com.priper.coffeeshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.*;


public class Main {
	public static Tree<Category> root;
	public static Tree<Category> lastCategoryTree; // stores last category user was on. default: root
	public static CartProducts userCart; // stores items in user's cart
	public static User user; // stores logged in user, null by default.
	public static ArrayList<Product> productArchive; // for storing products user has added at any given point
	public static ArrayList<Order> orders; // holds all orders
	
	public static void main(String[] args) {
		
		
	}

	public static void populateApp() { // adding sample products
		root = new Tree<Category>(new Category("Products","https://www.poorinaprivateplane.com/wp-content/uploads/2023/01/Soho-Coffee-Aereal-shot-of-friends-enjoying-coffee-at-a-shop.jpeg"));
		Tree<Category> food = root.addChild(new Category("Food","https://chatelaine.com/wp-content/uploads/2022/02/dinner-salad-recipes-hearty-salads-filling.jpg"));
		Tree<Category> sweet = food.addChild(new Category("Sweet","https://static01.nyt.com/images/2020/07/10/well/10well-newsletter/10well-newsletter-superJumbo.jpg"));
		sweet.getData().addProduct(new Product("Muffin",3,"Yummy", 500, 300,"https://images4.alphacoders.com/108/1083332.jpg"));
		sweet.getData().addProduct(new Product("Donut",2,"Yummy", 473, 250,"https://imgrosetta.mynet.com.tr/file/16976193/16976193-1200x824.png"));
		sweet.getData().addProduct(new Product("Cookie",2,"Yummy", 367, 150,"https://www.seriouseats.com/thmb/vk7ZqMbBSK63zDjNqIhj-_peDKc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__2019__02__20190131-levain-style-chocolate-chip-cookies-vicky-wasik-19-da3b6baeddc24b01b724b557150e993a.jpg"));
		sweet.getData().addProduct(new Product("Chocolate Cake",4,"for 4 people", 652, 400,"https://insanelygoodrecipes.com/wp-content/uploads/2021/12/Homemade-Sweet-Layered-Chocolate-Cake-in-a-Plate.jpg"));
		sweet.getData().addProduct(new Product("Vanilla Cake",4,"for 4 people", 589, 400,"https://www.allrecipes.com/thmb/EhDgTO2m_MZ-gLM5jhYLcVQ6Gcg=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/277000-easy-vanilla-cake-ddmfs-1X2-0101-8ac1f0aed3294888921a87d9bce9c43c.jpg"));
		Tree<Category> salty = food.addChild(new Category("Salty","https://barefeetinthekitchen.com/wp-content/uploads/2017/12/Salads-Everyday-5-1-of-1.jpg"));
		salty.getData().addProduct(new Product("Pasta Salad",7,"Great way to fill your stomach", 540, 600,"https://www.allrecipes.com/thmb/LUZ2ZXjuWFO-6c_dVuV2y_TUhUQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/14385-pasta-salad-mfs-62-2cbb356b049740e7832e4bbb899881de.jpg"));
		salty.getData().addProduct(new Product("Mixed Nuts",3,"A great mix of nuts", 400, 100,"https://images.immediate.co.uk/production/volatile/sites/30/2020/08/the-health-benefits-of-nuts-main-image-700-350-bb95ac2.jpg?quality=90&resize=960,872"));
		Tree<Category> drinks = root.addChild(new Category("Drinks","https://www.shape.com/thmb/TzgMY2oDpaLCYLUC1Jd8qiJTdkE=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/1-slide_0_0-95d907132a7f4b209a5d0c4765605aee.jpg"));
		Tree<Category> hot = drinks.addChild(new Category("Hot","https://dmrqkbkq8el9i.cloudfront.net/Pictures/2000xAny/2/2/2/282222_hotbevsgettyimages594567397_784344.jpg"));
		Tree<Category> tea = hot.addChild(new Category("Tea","https://images.everydayhealth.com/images/the-best-teas-for-your-health-1440x810.jpg"));
		tea.getData().addProduct(new Product("Green Tea",2.5,"Fresh tea leaves", 45, 200,"https://cf-images.eu-west-1.prod.boltdns.net/v1/static/1786863135/277a27df-f76a-4acb-9d22-75d74dc1b6e8/c8f8fb5f-d468-414e-be93-836f106bfb4c/1280x720/match/image.jpg"));
		tea.getData().addProduct(new Product("Black Tea",2.5,"Fresh tea leaves", 25, 200,"https://images.ctfassets.net/e8bhhtr91vp3/3rOc67nXmWi8Gu9yyZjY9w/e599824279e28094da824f36a82b8586/worldtea_abouttea_organic_img1.jpg?w=1600&q=60"));
		tea.getData().addProduct(new Product("White Tea",2.5,"Fresh tea leaves", 37, 200,"https://tea101.teabox.com/wp-content/uploads/2017/01/White-Tea_1.jpg"));
		tea.getData().addProduct(new Product("Roobius Tea",2.5,"Fresh tea leaves", 39, 200,"https://cdn.yemek.com/uploads/2021/12/rooibos-cayi-faydalari.jpg"));
		tea.getData().addProduct(new Product("Vitamin C Lemon Tea",2.5,"Only drink if you are sick", 29, 200,"https://cdn.shopify.com/s/files/1/2675/9476/files/34006.jpg?v=1600730451"));
		tea.getData().addProduct(new Product("Chamomile Tea",2.5,"Fresh tea leaves", 19, 200,"https://muhiku.com/blog/wp-content/uploads/2019/09/Papatya-%C3%87ay%C4%B1.jpg"));
		tea.getData().addProduct(new Product("Linden Tea",2.5,"Fresh tea leaves", 54, 200,"https://draxe.com/wp-content/uploads/2020/09/shutterstock_518964184-1.jpg"));
		Tree<Category> coffee = hot.addChild(new Category("Coffee","https://static.betterretailing.com/wp-content/uploads/2022/08/11104217/Hot-drinks-coffee-tea-hot-chocolate-mugs-cups-beverages.jpg"));
		coffee.getData().addProduct(new Product("Americano",2,"Dark Roast", 19, 200,"https://cdn.yemek.com/mncrop/940/625/uploads/2015/04/americano.jpg"));
		coffee.getData().addProduct(new Product("Latte",2.5,"from the best coffee beans", 130, 200,"https://i.ytimg.com/vi/2sBnTCJShy8/maxresdefault.jpg"));
		coffee.getData().addProduct(new Product("Cappuccino",3,"from the best coffee beans", 180, 200,"https://static.bizimtarifler.com/wp-content/uploads/2022/01/dreamstime_m_50040409.jpg.webp"));
		coffee.getData().addProduct(new Product("Filter Coffee",2,"from the best coffee beans", 19, 200,"https://www.baristainstitute.com/sites/default/files/styles/some_share/public/images/how-to-brew-good-filter-coffee-pauligbaristainstitute_0.jpg?itok=Frs_NGLH"));
		coffee.getData().addProduct(new Product("Mocha",2.5,"from the best coffee beans", 167, 200,"https://food-images.files.bbci.co.uk/food/recipes/the_perfect_mocha_coffee_29100_16x9.jpg"));
		coffee.getData().addProduct(new Product("Flat White",2.5,"from the best coffee beans", 159, 200,"https://kahveblog.com/wp-content/uploads/2022/12/Flat-white-webp.webp"));
		coffee.getData().addProduct(new Product("Coffee Shot",2,"from the best coffee beans", 23, 100,"https://s3-us-west-1.amazonaws.com/contentlab.studiod/getty/492c7b5cf3dc4435a3fa27ba5c078e12"));
		coffee.getData().addProduct(new Product("Gold Premium Seeds",8,"from the best coffee beans", 45, 200,"https://images.indianexpress.com/2022/03/coffee-1200.jpg"));
		coffee.getData().addProduct(new Product("Decaf",2,"from the best coffee beans", 26, 200,"https://images.ctfassets.net/cnu0m8re1exe/6bSQPRGG91friWDRWflMLS/67b9afb74351fa48f24f5d4e0d1fb707/dreamstime_l_67989889.jpg?fm=jpg&fl=progressive&w=660&h=433&fit=fill"));
		Tree<Category> cold = drinks.addChild(new Category("Cold","https://www.teaandcoffee.net/wp-content/uploads/caribou-summer.jpg"));
		cold.getData().addProduct(new Product("Lemonade",2.5,"freshly squeezed", 220, 200,"https://www.blossmangas.com/wp-content/uploads/2020/05/Lemonade-1-1.jpg"));
		cold.getData().addProduct(new Product("Iced Tea",2.5,"cold tea", 230, 200,"https://realfood.tesco.com/media/images/RFO-1400x919-IcedTea-8e156836-69f4-4433-8bae-c42e174212c1-0-1400x919.jpg"));
		cold.getData().addProduct(new Product("Ayran",2.5,"milli drink", 175, 200,"https://i3.posta.com.tr/i/posta/75/0x0/6224f25845d2a0cdfc817ab8.jpg"));
		cold.getData().addProduct(new Product("Water",1.5,"best drink ever. Ronaldo's favourite", 0, 500,"https://clickhole.com/wp-content/uploads/2020/11/iStock-1059699762-e1605027155249.jpg"));
		cold.getData().addProduct(new Product("Apple Juice",2.5,"freshly squeezed", 250, 200,"https://i.ytimg.com/vi/bGfJILzw6Mc/maxresdefault.jpg   "));
	}
	
	public static Report generateReport() {
		Report report = new Report();
		return report;
	}
	
	public static void exportReport() {
		exportObject("report.txt", generateReport());
	}
	
	public static void sendOrder(CartProducts cart) {
		ArrayList<Order> ordersList = importOrders();
		ordersList.add(new Order(cart));
		orders = ordersList;
		exportOrders(orders);
	}

	
	public static void exportObject(String filename, Object object) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
			 ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(fos);
					oos.writeObject(object);
				} catch (IOException e) {
					e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean logIn(String username, String password) {
		ArrayList<User> users = importUsers();
		for (User u: users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				user = u;
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<User> importUsers() {
		return (ArrayList<User>)importObject("users.db");
	}

	public static ArrayList<Order> importOrders() {
		return (ArrayList<Order>)importObject("orders.db");
	}

	public static Tree<Category> importMainDatabase() {
		return (Tree<Category>)importObject("maindb.db");
	}

	public static void exportUsers(ArrayList<User> newUsers) {
		exportObject("users.db", newUsers);
	}

	public static void exportOrders(ArrayList<Order> updatedOrders) {
		exportObject("orders.db", updatedOrders);
	}

	public static void exportMainDatabase(Tree<Category> newDatabase) {
		exportObject("maindb.db", newDatabase);
	}
	
	public static void addSampleUsers() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("Customer","elif","ssp"));
		users.add(new User("Customer","mansur","ssp"));
		users.add(new User("Customer","ekrem","ssp"));
		users.add(new User("Customer","kemal","ssp"));
		users.add(new User("Worker","joe","ssp"));
		users.add(new User("Worker","jack","ssp"));
		users.add(new User("Owner","slim","ssp"));
		exportUsers(users);
	}

	public static void addUser(String role, String username, String password) {
		ArrayList<User> users = importUsers();
		users.add(new User(role,username,password));
		exportUsers(users);
	}
	
	public static Object importObject(String filename) {
		FileInputStream fis;
		Object object;
		try {
			fis = new FileInputStream(filename);
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(fis);
				try {
					object = ois.readObject();
					return object;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    object = new Object();
		return object;
	}
	
	public static void changeCurrentCategory(Tree<Category> newCategoryTree) {
		lastCategoryTree = newCategoryTree;
	}
	
	
    public static void syncRoot(Tree<Category> tree, Tree<Category> changedTree)
    {
    	int childrenCount = tree.numberOfChildren(false);
        if (tree == null || childrenCount == 0)
            return;
        for (int i = 0; i < childrenCount - 1; i++) {
        	if (tree.getData().getName().equals(changedTree.getData().getName())) {
        		tree = changedTree;
        		return;
        	}
        	else {
        		syncRoot(tree.getChildren().get(i), changedTree);
        	}
        }
        
        syncRoot(tree.getChildren().get(childrenCount - 1), changedTree);
    }
	
	
}