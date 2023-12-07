package model.bean;

public class ProductView {
	private String id_product;
	private String category_name;
	private String brand;

	private String image_product;
	private	String price;
	public String getId_product() {
		return id_product;
	}
	
	public void setId_product(String id_product) {
		this.id_product = id_product;
	}
	public String getcategory_name() {
		return category_name;
	}
	public void setcategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage_product() {
		return image_product;
	}
	public void setImage_product(String image_product) {
		this.image_product = image_product;
	}
	
	
	public ProductView(String id_product, String category_name, String brand, String image_product, String price) {
		this.id_product = id_product;
		this.category_name = category_name;
		this.brand = brand;
		this.image_product = image_product;
		this.price = price;
	}
	
}
