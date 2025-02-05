package tr.com.priper.coffeeshop;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkerDashboardPage {
	
	   @RequestMapping(value = "/dashboard/worker", method= RequestMethod.GET)
	   public String workerDashboard(Model model) {
		   if (Main.user != null) {
			   if (Main.user.getRole().equals("Owner")) {
				   return "redirect:/dashboard/owner";
			   }
			   else if (!Main.user.getRole().equals("Worker")) {
				   return "redirect:/";
			   }
		   }
		   else {
			   return "redirect:/";
		   }
		   model.addAttribute("cart", Main.userCart);
		   model.addAttribute("products", Main.userCart.getProducts());
		   model.addAttribute("user",Main.user);
	      return "worker_dashboard";
	   }
	   
	   @RequestMapping(value = "/dashboard/worker/manage-orders", method= RequestMethod.GET)
	   public String manageOrders(Model model) {
		   if (Main.user != null) {
			   if (Main.user.getRole().equals("Owner")) {
				   return "redirect:/dashboard/owner";
			   }
			   else if (!Main.user.getRole().equals("Worker")) {
				   return "redirect:/";
			   }
		   }
		   else {
			   return "redirect:/";
		   }
		   model.addAttribute("cart", Main.userCart);
		   model.addAttribute("products", Main.userCart.getProducts());
		   model.addAttribute("user",Main.user);
		   model.addAttribute("orders",(ArrayList<Order>)Main.importOrders());
	      return "manage_orders";
	   }
	
   @RequestMapping(value = "/dashboard/worker/edit-categories", method= RequestMethod.GET)
   public String viewCategories(Model model, @RequestParam(required=false, name = "category") String categoryStr) {
	   if (Main.user != null) {
		   if (Main.user.getRole().equals("Owner")) {
			   return "redirect:/dashboard/owner";
		   }
		   else if (!Main.user.getRole().equals("Worker")) {
			   return "redirect:/";
		   }
	   }
	   else {
		   return "redirect:/";
	   }
	   if (categoryStr != null) {
		   for (Tree childTree: Main.lastCategoryTree.getChildren()) {
			   Category cat = (Category)childTree.getData();
			   if ( cat.getName().equals(categoryStr) ) {
				   Main.changeCurrentCategory(childTree);
				   break;
			   }
		   }
	   }
	   else {
		   Main.changeCurrentCategory((Tree<Category>)Main.importMainDatabase());
	   }
	   
	   if (!Main.lastCategoryTree.hasSubcategories()) {
		   return "redirect:/dashboard/worker/edit-products?category=" + categoryStr;
	   }
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("category", Main.lastCategoryTree.getData());
	   model.addAttribute("user",Main.user);
	   if (Main.lastCategoryTree.hasSubcategories()) {
		   model.addAttribute("categoryTrees", Main.lastCategoryTree.getChildren());
		   return "edit_categories";
	   }
      return "edit_categories";
   }
   
   @RequestMapping(value = "/dashboard/worker/edit-products", method= RequestMethod.GET)
   public String viewProducts(Model model, @RequestParam(name = "category") String categoryStr) {
	   if (Main.user != null) {
		   if (Main.user.getRole().equals("Owner")) {
			   return "redirect:/dashboard/owner";
		   }
		   else if (!Main.user.getRole().equals("Worker")) {
			   return "redirect:/";
		   }
	   }
	   else {
		   return "redirect:/";
	   }
	   
	   if (categoryStr != null) {
		   for (Tree childTree: Main.lastCategoryTree.getChildren()) {
			   Category cat = (Category)childTree.getData();
			   if ( cat.getName().equals(categoryStr) ) {
				   Main.changeCurrentCategory(childTree);
				   break;
			   }
		   }
	   }
	   else {
		   Main.changeCurrentCategory((Tree<Category>)Main.importMainDatabase());
	   }
	   model.addAttribute("user",Main.user);
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("category", Main.lastCategoryTree.getData());
	   model.addAttribute("products", Main.lastCategoryTree.getData().getProducts());
	   return "edit_products";
   }
   
   @RequestMapping(value = "/dashboard/worker/manage-orders", method= RequestMethod.POST)
   public String manageOrders(Model model, OrderActionHandler order) {
	   if (Main.user != null) {
		   if (Main.user.getRole().equals("Owner")) {
			   return "redirect:/dashboard/owner";
		   }
		   else if (!Main.user.getRole().equals("Worker")) {
			   return "redirect:/";
		   }
	   }
	   else {
		   return "redirect:/";
	   }
	   ArrayList<Order> orders = (ArrayList<Order>)Main.importOrders();
	   for (Order existingOrder: orders) {
		   if (existingOrder.getId() == order.getId()) {
			   existingOrder.setDelivered(!existingOrder.isDelivered());
		   }
	   }
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   Main.exportOrders(orders);
	   model.addAttribute("user",Main.user);
	   model.addAttribute("orders", orders);
      return "redirect:/dashboard/worker/manage-orders";
   }

   @RequestMapping(value = "/dashboard/worker/edit-products", method= RequestMethod.POST, params="edit_product")
   public String editProduct(Model model, ProductHandler product, @RequestParam(required=false, name = "category") String categoryStr) {
	   if (Main.user != null) {
		   if (Main.user.getRole().equals("Owner")) {
			   return "redirect:/dashboard/owner";
		   }
		   else if (!Main.user.getRole().equals("Worker")) {
			   return "redirect:/";
		   }
	   }
	   else {
		   return "redirect:/";
	   }

	   Main.lastCategoryTree.getData().editProduct(product.getName(),product.getPrice(),product.getDescription(),product.getCalories(),product.getGrams(), product.getImageLink());
	   
	   Tree<Category> newRoot = Main.lastCategoryTree;
	   while (newRoot.hasParent()) {
		   newRoot = newRoot.getParent();
	   }
	   Main.root = newRoot;
	   Main.exportMainDatabase(Main.root);
	   model.addAttribute("user",Main.user);
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("category", Main.lastCategoryTree.getData());
	   
      return "redirect:/dashboard/worker/edit-products?category=" + categoryStr;
   }
   
   @RequestMapping(value = "/dashboard/worker/edit-products", method= RequestMethod.POST, params="add_product")
   public String addProduct(Model model, ProductHandler product, @RequestParam(required=false, name = "category") String categoryStr) {
	   if (Main.user != null) {
		   if (Main.user.getRole().equals("Owner")) {
			   return "redirect:/dashboard/owner";
		   }
		   else if (!Main.user.getRole().equals("Worker")) {
			   return "redirect:/";
		   }
	   }
	   else {
		   return "redirect:/";
	   }

	   Main.lastCategoryTree.getData().addProduct(new Product(product.getName(),product.getPrice(),product.getDescription(),product.getCalories(),product.getGrams(), product.getImageLink()));
	   
	   Tree<Category> newRoot = Main.lastCategoryTree;
	   while (newRoot.hasParent()) {
		   newRoot = newRoot.getParent();
	   }
	   Main.root = newRoot;
	   Main.exportMainDatabase(Main.root);
	   model.addAttribute("user",Main.user);
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("category", Main.lastCategoryTree.getData());
	   
	   return "redirect:/dashboard/worker/edit-products?category=" + categoryStr;
   }
   
   @RequestMapping(value = "/dashboard/worker/edit-products", method= RequestMethod.POST, params="remove_product")
   public String removeProduct(Model model, ProductHandler product, @RequestParam(required=false, name = "category") String categoryStr) {
	   if (Main.user != null) {
		   if (Main.user.getRole().equals("Owner")) {
			   return "redirect:/dashboard/owner";
		   }
		   else if (!Main.user.getRole().equals("Worker")) {
			   return "redirect:/";
		   }
	   }
	   else {
		   return "redirect:/";
	   }

	   Main.lastCategoryTree.getData().removeProduct(product.getName());
	   
	   Tree<Category> newRoot = Main.lastCategoryTree;
	   while (newRoot.hasParent()) {
		   newRoot = newRoot.getParent();
	   }
	   Main.root = newRoot;
	   Main.exportMainDatabase(Main.root);
	   model.addAttribute("user",Main.user);
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("category", Main.lastCategoryTree.getData());
	   
	   return "redirect:/dashboard/worker/edit-products?category=" + categoryStr;
   }
   
   @RequestMapping(value = "/dashboard/worker/edit-categories", method= RequestMethod.POST, params="add_category")
   public String addCategory(Model model, CategoryActionHandler catAction, @RequestParam(required=false, name = "category") String categoryStr) {
	   if (Main.user != null) {
		   if (Main.user.getRole().equals("Owner")) {
			   return "redirect:/dashboard/owner";
		   }
		   else if (!Main.user.getRole().equals("Worker")) {
			   return "redirect:/";
		   }
	   }
	   else {
		   return "redirect:/";
	   }
	   	   
	   if (categoryStr != null) {
		   for (Tree childTree: Main.lastCategoryTree.getChildren()) {
			   Category cat = (Category)childTree.getData();
			   if ( cat.getName().equals(categoryStr) ) {
				   Main.changeCurrentCategory(childTree);
				   break;
			   }
		   }
	   }
	   else {
		   Main.changeCurrentCategory((Tree<Category>)Main.importMainDatabase());
	   }

	   Main.lastCategoryTree.addChild(new Category(catAction.getCategoryName(),catAction.getImageLink()));
	   Tree<Category> newRoot = Main.lastCategoryTree;
	   while (newRoot.hasParent()) {
		   newRoot = newRoot.getParent();
	   }
	   Main.root = newRoot;
	   Main.exportMainDatabase(Main.root);
	   model.addAttribute("user",Main.user);
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("category", Main.lastCategoryTree.getData());
	   if (Main.lastCategoryTree.hasSubcategories()) {
		   model.addAttribute("categoryTrees", Main.lastCategoryTree.getChildren());
	   }
	   if (categoryStr !=null) return "redirect:/dashboard/worker/edit-categories?category=" + categoryStr;
	   return "redirect:/dashboard/worker/edit-categories";
   }
   
   @RequestMapping(value = "/dashboard/worker/edit-categories", method= RequestMethod.POST, params="delete_category")
   public String removeCategory(Model model, CategoryActionHandler catAction, @RequestParam(required=false, name = "category") String categoryStr) {
	   if (Main.user != null) {
		   if (Main.user.getRole().equals("Owner")) {
			   return "redirect:/dashboard/owner";
		   }
		   else if (!Main.user.getRole().equals("Worker")) {
			   return "redirect:/";
		   }
	   }
	   else {
		   return "redirect:/";
	   }	   	   
	   if (categoryStr != null) {
		   for (Tree childTree: Main.lastCategoryTree.getChildren()) {
			   Category cat = (Category)childTree.getData();
			   if ( cat.getName().equals(categoryStr) ) {
				   Main.changeCurrentCategory(childTree);
				   break;
			   }
		   }
	   }
	   else {
		   Main.changeCurrentCategory((Tree<Category>)Main.importMainDatabase());
	   }
	   for(int i = 0; i < Main.lastCategoryTree.getChildren().size(); i++){
		Tree<Category> categoryTree = Main.lastCategoryTree.getChildren().get(i);
		if (categoryTree.getData().getName().equals(catAction.getCategoryName())) {
			Main.lastCategoryTree.deleteChild(categoryTree);
		}
	   }
	   Tree<Category> newRoot = Main.lastCategoryTree;
	   while (newRoot.hasParent()) {
		   newRoot = newRoot.getParent();
	   }
	   Main.root = newRoot;
	   Main.exportMainDatabase(Main.root);
	   model.addAttribute("user",Main.user);
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("category", Main.lastCategoryTree.getData());
	   if (Main.lastCategoryTree.hasSubcategories()) {
		   model.addAttribute("categoryTrees", Main.lastCategoryTree.getChildren());
	   }
	   if (categoryStr !=null) return "redirect:/dashboard/worker/edit-categories?category=" + categoryStr;
	   return "redirect:/dashboard/worker/edit-categories";
   }
   
   
   
}