package tr.com.priper.coffeeshop;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginPage {
   @RequestMapping(value = "/login", method= RequestMethod.GET)
   public String login(Model model) {	
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   if (Main.user != null) {
		   if (Main.user.getRole().equals("Worker")) {
			   return "redirect:/dashboard/worker";
		   }
		   else if (Main.user.getRole().equals("Owner")) {
			   return "redirect:/dashboard/owner";
		   }
		   else {
			   return "redirect:/";
		   }
	   }
      return "login";
   }
   
   @RequestMapping(value = "/login", method= RequestMethod.POST)
   public String login(UserLoginHandler user, Model model) {
	   boolean loggedIn = Main.logIn(user.getUsername(), user.getPassword());
	   model.addAttribute("cart", Main.userCart);
	   model.addAttribute("products", Main.userCart.getProducts());
	   model.addAttribute("attempt_success", loggedIn);
	   model.addAttribute("user", Main.user);
	   if (loggedIn) {
		   return "redirect:/dashboard/worker";
	   }
      return "redirect:/login";
   }
   
}