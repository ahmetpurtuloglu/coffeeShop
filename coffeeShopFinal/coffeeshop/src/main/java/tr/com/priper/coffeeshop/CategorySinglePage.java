package tr.com.priper.coffeeshop;

import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategorySinglePage {
	@RequestMapping(value = "/category/{categoryStr}", method = RequestMethod.GET)
	public String categorySingle(Model model, @PathVariable String categoryStr,
			@RequestParam(required = false, name = "add_product") String addProduct,
			@RequestParam(required = false, name = "remove_product") String removeProduct) {
		model.addAttribute("user", Main.user);

		// changing current category to the appropriate one provided in the url
		for (Tree<Category> childTree : Main.lastCategoryTree.getChildren()) {
			Category cat = (Category) childTree.getData();
			if (cat.getName().equals(categoryStr)) {
				Main.changeCurrentCategory(childTree);
				break;
			}
		}

		model.addAttribute("cart", Main.userCart);
		model.addAttribute("category", Main.lastCategoryTree.getData().getName());
		if (Main.lastCategoryTree.hasSubcategories()) { // handling category with subcategories
			model.addAttribute("categories", Main.lastCategoryTree.getChildren());
			return "category_single";
		} else { // handling last category with products
			model.addAttribute("products", Main.lastCategoryTree.getData().getProducts());
			if (addProduct != null) {
				LinkedList<Product> products = Main.lastCategoryTree.getData().getProducts();
				for (Product p : products) {
					if (p.getName().equals(addProduct)) {
						Main.userCart.addProduct(p);
						if (!Main.productArchive.contains(p))
							Main.productArchive.add(p);
					}
				}
				return "redirect:/cart";
			}
			if (removeProduct != null) {
				LinkedList<Product> products = Main.lastCategoryTree.getData().getProducts();
				for (Product p : products) {
					if (p.getName().equals(removeProduct)) {
						Main.userCart.removeProduct(p);
					}
				}
				return "redirect:/cart";
			}
			return "products";
		}
	}
}