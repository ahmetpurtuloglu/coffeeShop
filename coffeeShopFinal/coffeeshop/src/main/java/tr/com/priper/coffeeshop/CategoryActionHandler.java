package tr.com.priper.coffeeshop;

public class CategoryActionHandler {
	String categoryName;
	String parentCategoryName;
	String imageLink;
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
	
	public String getParentCategoryName() {
		return this.parentCategoryName;
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}
}
