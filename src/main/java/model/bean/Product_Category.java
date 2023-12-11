package model.bean;

public class Product_Category {
	private String id;
	private String name_category;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName_category() {
		return name_category;
	}
	public void setName_category(String name_category) {
		this.name_category = name_category;
	}
	
	public Product_Category(String id, String name_category) {
		this.id = id;
		this.name_category = name_category;
	}
}
