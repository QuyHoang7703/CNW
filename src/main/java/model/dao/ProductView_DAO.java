package model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import model.bean.ProductView;

import java.util.Base64;
import java.util.Locale;
public class ProductView_DAO {
	private Connection cnn;
	private Statement statement;
	
	public ProductView_DAO() {
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
	
	public ArrayList<ProductView> getProductView(){
		ArrayList<ProductView> list = new ArrayList<ProductView>();
		String sql = "select category_name ,brand, id_product, image, price from product_category , product "
				+ "where product_category.id_category = product.id_category ";
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				String category_name = resultSet.getString("category_name");
				String brand = resultSet.getString("brand");
				String id_product = resultSet.getString("id_product");
				byte[] imageByte = resultSet.getBytes("image");
				BigDecimal price = resultSet.getBigDecimal("price");
				String price_show = PriceFormatter.formatCurrency(price);
				System.out.println(price_show);
				String image = ImageUtils.encodeImageToBase64(imageByte);
				ProductView productView = new ProductView(id_product, category_name, brand, image, price_show);
				list.add(productView);
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("loi ne");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	public class ImageUtils {
	    public static String encodeImageToBase64(byte[] imageData) {
	        return Base64.getEncoder().encodeToString(imageData);
	    }
	}
	
	public class PriceFormatter {
	    public static String formatCurrency(BigDecimal amount) {
	        Locale vietnameseLocale = new Locale("vi", "VN");
	        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnameseLocale);

	        return currencyFormatter.format(amount);
	    }
	
	}
}

