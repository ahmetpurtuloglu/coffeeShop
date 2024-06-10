package tr.com.priper.coffeeshop;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartPage {
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(Model model, @RequestParam(required = false, name = "add_product") String addProduct,
			@RequestParam(required = false, name = "remove_product") String removeProduct,
			@RequestParam(required = false, name = "send_order") boolean sendOrder) {

		// adding necessary attributes in page
		model.addAttribute("user", Main.user);
		model.addAttribute("cart", Main.userCart);
		model.addAttribute("products", Main.userCart.getProducts());

		
		if (addProduct != null) { // if user has added a product 
			Product addProductObj = null;
			ArrayList<Product> products = Main.productArchive;
			for (Product p : products) {
				if (p.getName().equals(addProduct)) {
					addProductObj = p;
				}
			}
			Main.userCart.addProduct(addProductObj);
		}

		if (removeProduct != null) { // if user has removed a product 
			for (int i = 0; i < Main.userCart.getProducts().size(); i++) {
				Product p = Main.userCart.getProducts().get(i).getProduct();
				if (p.getName().equals(removeProduct)) {
					Main.userCart.removeProduct(p);
				}
			}
		}

		if (sendOrder != false) { // if user has sent an order 
			Main.userCart.sendOrder();
			model.addAttribute("sent_order", true);
		}

		return "cart";
	}
}