package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Product;
import model.bean.Product_Category;
import model.bean.Product_size;

public class Product_DAO {
	private Connection cnn;
	private Statement statement;
	
	public Product_DAO() {
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
	
	public ArrayList<Product_Category> getAllCategory(){
		ArrayList<Product_Category> list = new ArrayList<Product_Category>();
		String sql ="Select * from product_category";
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list.add(new Product_Category(resultSet.getString("id_category"), resultSet.getString("category_name")));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void addProduct(Product product)  {
		
		try {
			String sql = "Insert into product values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = cnn.prepareStatement(sql);
			preparedStatement.setString(1, product.getId_category());
			preparedStatement.setString(2, product.getId());
			preparedStatement.setString(3, product.getName());
			preparedStatement.setString(4, product.getBrand());
			preparedStatement.setString(5, product.getDescription());
			preparedStatement.setBigDecimal(6, product.getPrice());
			preparedStatement.setString(7, product.getOrigin());
			preparedStatement.setBytes(8, product.getImage());
				
			preparedStatement.executeUpdate();
			
			String sql2 = "Insert into product_size values(?, ?, ?)";
			PreparedStatement addProduct_size  = cnn.prepareStatement(sql2);
			for(Product_size product_size : product.getProductSizes()) {
				addProduct_size.setString(1, product_size.getId_product());
				addProduct_size.setInt(2, product_size.getId_size());
				addProduct_size.setInt(3, product_size.getQuantity());
				addProduct_size.executeUpdate();
			}
			
			System.out.println("Da them thanh cong");
		} catch (SQLException e) {
			System.out.println("Khong the add dc prouduct ccccccccccccccccc");
			e.printStackTrace();
		}
		
		
		
	}
	
	public int getIdBySize(String name_size) {
		int result = 0;
		String sql = "Select id_size from size where name_size = '" +name_size +"'";
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				result = resultSet.getInt("id_size");
			}else {
				//Thêm size mới vào nếu chưa tồn tại
				String sql2 = "Insert into size(name_size) values('" +name_size +"')";
				statement.executeUpdate(sql2);
				resultSet = statement.executeQuery(sql);
				if(resultSet.next()) {
					result = resultSet.getInt("id_size");
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Loi cho add size!");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<String> getListId(String id) {
		
		ArrayList<String> listId = new ArrayList<String>();
		String sql = "Select id_product from product";
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				listId.add(resultSet.getString("id_product"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listId;
	}
}
