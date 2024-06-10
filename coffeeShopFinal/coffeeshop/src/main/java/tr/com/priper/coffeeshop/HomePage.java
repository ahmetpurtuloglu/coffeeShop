package tr.com.priper.coffeeshop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePage {
   @RequestMapping(value = "/", method= RequestMethod.GET)
   public String homepage(Model model) {
	   List<Tree> categoriesAsTrees = ((Tree)Main.importMainDatabase()).getChildren();
	   ArrayList<Category> categories = new ArrayList<Category>();
	   for (Tree tree: categoriesAsTrees){
		   Category cat = (Category)tree.getData();
		   categories.add(cat);
	   }
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("user",Main.user);
	   model.addAttribute("categories", categories);
	   model.addAttribute("cart", Main.userCart);
	   Main.changeCurrentCategory(Main.importMainDatabase());
      return "home";
   }
}