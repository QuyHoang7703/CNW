package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Account;
import model.bean.Detail_account;

public class Account_DAO {
	private Connection cnn;
	private Statement statement;
	
	public Account_DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnn = DriverManager.getConnection("jdbc:mysql://localhost:3308/bt_nhom_cnw_3", "root", "");
			statement = cnn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Account> getListAccount(){
		ArrayList<Account> list_Account = new ArrayList<Account>();
		String sql = "Select * from account";
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list_Account.add(new Account(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list_Account;
	}
	
	public void addAccount(Account account, Detail_account detail_account) throws SQLException {
		String sql = "Insert into account(username, password) values('" + account.getUsername() + "', '" + account.getPassword() +"')";
		statement.executeUpdate(sql);
		
		String sql2 = "Select * from account where username = '"  + account.getUsername() +"'";
		ResultSet resultSet = statement.executeQuery(sql2);
		int id = -1;
		while(resultSet.next()) {
			id = resultSet.getInt("id");
		}
		System.out.println(id+"");
		String name = detail_account.getName();
		String address = detail_account.getAddress();
		String phone = detail_account.getNumber_phone();
		String email = detail_account.getEmail();
		int role = detail_account.getRole();
		
		String sql3 = "INSERT INTO detail_account (id, name, address, phone_number, email, role) VALUES (" + id + ", '" + name + "', '" + address + "', '" + phone + "', '" + email + "', " + role + ")";
		statement.executeUpdate(sql3);
		
	}
	
	public ArrayList<String> getListUser(){
		ArrayList<String> list_username = new ArrayList<String>();
		String sql = "Select username from account";
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list_username.add(resultSet.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list_username;
	}

}
