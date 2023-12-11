package model.bean;

public class Size {
	private int id;
	private String name_size;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName_size() {
		return name_size;
	}
	public void setName_size(String name_size) {
		this.name_size = name_size;
	}
	
	public Size(int id, String name_size) {
		this.id = id;
		this.name_size = name_size;
	}
	
	
}
