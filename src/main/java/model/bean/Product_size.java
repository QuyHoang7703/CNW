package model.bean;

public class Product_size {
	private int id_size;
	private String id_product;
	private int quantity;
	
	public int getId_size() {
		return id_size;
	}

	public void setId_size(int id_size) {
		this.id_size = id_size;
	}

	public String getId_product() {
		return id_product;
	}

	public void setId_product(String id_product) {
		this.id_product = id_product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product_size(int id_size, String id_product, int quantity) {
		
		this.id_size = id_size;
		this.id_product = id_product;
		this.quantity = quantity;
	}
	
}
