package model.bean;

import java.sql.Date;

public class Detail_account {
	private int id;
	private String name;
	private String address;
	private String number_phone;
	private String email;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumber_phone() {
		return number_phone;
	}
	public void setNumber_phone(String number_phone) {
		this.number_phone = number_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}


	private	int role;
	
	public Detail_account( String name, String address, String number_phone, String email, int role) {

		this.name = name;
		this.address = address;
		this.number_phone = number_phone;
		this.email = email;
		this.role = role;
	}
	
}
