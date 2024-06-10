package tr.com.priper.coffeeshop;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterPage {
   @RequestMapping(value = "/register", method= RequestMethod.GET)
   public String register(Model model) {
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
      return "register";
   }
   
   @RequestMapping(value = "/register", method= RequestMethod.POST)
   public String register(User user, Model model) {
	   Main.addUser("Customer",user.getUsername(), user.getPassword());
	   Main.logIn(user.getUsername(), user.getPassword());
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("user", Main.user);
      return "redirect:/";
   }
   
}