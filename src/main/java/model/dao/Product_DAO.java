package model.dao;

import java.math.BigDecimal;
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
import model.bean.Size;

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
	
	public void deleteProduct(String id) {
		String sql = "Delete from product_size where id_product = '" +id +"'";
		String sql2 = "Delete from product where id_product = '" +id +"'";
		try {
			statement.executeUpdate(sql);
			statement.executeUpdate(sql2);
			System.out.println("Da xoa product thanh cong");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Ko xoa product duoc");
			e.printStackTrace();
		}
		
	}
	
	public Product getProductById(String id) {
		String sql = "Select * from product where id_product ='" +id +"'";
		String sql2 = "Select * from product_size where id_product = '" +id +"'";
		ArrayList<Product_size> list = new ArrayList<Product_size>();
		try {
			ResultSet resultSet2 = statement.executeQuery(sql2);
			while(resultSet2.next()) {
				list.add(new Product_size(resultSet2.getInt("id_size"), resultSet2.getString("id_product") , resultSet2.getInt("quantity")));
			}
			System.out.println(list.size());
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				String id_category = resultSet.getString("id_category");
				String id_product = resultSet.getString("id_product");
				String name_product = resultSet.getString("name_product");
				String brand = resultSet.getString("brand");
				String description = resultSet.getString("description");
				BigDecimal price = resultSet.getBigDecimal("price");
				String origin = resultSet.getString("origin");
				byte[] image = resultSet.getBytes("image");
				Product product = new Product(id_category, id_product, name_product, brand, description, price, origin, image, list);
				return product;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Khong tim thay product voi id nay");
			e.printStackTrace();
		
		}
		return null;
	}
	
	public ArrayList<Size> getSizes(){
		ArrayList<Size> list = new ArrayList<Size>();
		String sql = "Select * from size";
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list.add(new Size(resultSet.getInt("id_size"), resultSet.getString("name_size")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Ko the lay list Size ra duoc");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void updateProduct(Product product)  {
		String id_product = product.getId();
		System.out.println(id_product);
		String sql = "Update product set id_category = ?, name_product = ?, brand = ?, description = ?, price = ?, origin = ?, image = ?"
				+ "where id_product = ?";
		try {
			PreparedStatement preparedStatement = cnn.prepareStatement(sql);
			preparedStatement.setString(1, product.getId_category());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getBrand());
			preparedStatement.setString(4, product.getDescription());
			preparedStatement.setBigDecimal(5, product.getPrice());
			preparedStatement.setString(6, product.getOrigin());
			if(product.getImage()==null) {
				System.out.println(product.getImage());
			}
			preparedStatement.setBytes(7, product.getImage());
			preparedStatement.setString(8, product.getId());
			
			preparedStatement.executeUpdate();
			
//			String sql2 = "Update product_size set id_size = ?, quantity = ? where id_product = ? ";
//			PreparedStatement updateProduct_size  = cnn.prepareStatement(sql2);
//			for(Product_size product_size : product.getProductSizes()) {
//				updateProduct_size.setInt(1, product_size.getId_size());
//				updateProduct_size.setInt(2, product_size.getQuantity());
//				updateProduct_size.setString(3, product_size.getId_product());
//				updateProduct_size.executeUpdate();
//			}
			
			
		} catch (SQLException e) {
			System.out.println("Khong the update");
			e.printStackTrace();
		}
		
		
	}
}
