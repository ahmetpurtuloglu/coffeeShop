package tr.com.priper.coffeeshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Logout {
	
	   @RequestMapping(value = "/logout", method= RequestMethod.GET)
	   public String logout() {
		   
		   Main.user = null;
		   return "redirect:/";

	   }
	
	
}
