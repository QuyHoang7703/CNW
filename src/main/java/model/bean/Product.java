package model.bean;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Product {
	private String id_category;
	private String id;
	private String name;
	private String brand;
	private String description;
	private BigDecimal price;
	private String origin;
	private byte[] image;
	private ArrayList<Product_size> productSizes;
	
	public String getId_category() {
		return id_category;
	}
	public void setId_category(String id_category) {
		this.id_category = id_category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public ArrayList<Product_size> getProductSizes() {
		return productSizes;
	}
	public void setProductSizes(ArrayList<Product_size> productSizes) {
		this.productSizes = productSizes;
	}
	
	public Product(String id_category, String id, String name, String brand, String description, BigDecimal price,
			String origin, byte[] image, ArrayList<Product_size> productSizes) {
		
		this.id_category = id_category;
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.origin = origin;
		this.image = image;
		this.productSizes = productSizes;
	}
	
	
	
	
	
}
