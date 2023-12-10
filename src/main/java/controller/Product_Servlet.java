package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import model.bean.Product;
import model.bean.ProductView;
import model.bean.Product_Category;
import model.bean.Product_size;
import model.bo.ProductView_BO;
import model.bo.Product_BO;

@MultipartConfig(maxFileSize=16*1024*1024)
@WebServlet("/Product_Servlet")
public class Product_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="";
		Product_BO bo = new Product_BO();

		if(request.getParameter("btn_addProduct")!=null) {
			String id_category = request.getParameter("id_category");
			String id_product = request.getParameter("id_product");
			if(bo.checkIdAvailable(id_product)) {
				url = "./form_add_product.jsp";
				String message = "Đã tồn tại mã sản phẩm này rồi";
				request.setAttribute("message", message);
				request.getRequestDispatcher(url).forward(request, response);
			}else {
				String name_product = request.getParameter("name_product");
				String brand = request.getParameter("brand");
				String description = request.getParameter("description");
				BigDecimal price = new BigDecimal(request.getParameter("price"));
				String origin = request.getParameter("origin");
				
				Part file_Part = request.getPart("image");
				InputStream inputStream = file_Part.getInputStream();
				ByteArrayOutputStream image_data = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					image_data.write(buffer, 0, bytesRead);
				}

				byte[] byteArray = image_data.toByteArray();

				// Đóng InputStream và ByteArrayOutputStream sau khi sử dụng
				inputStream.close();
				image_data.close();
				
				String[] list_size = request.getParameterValues("size[]");
				System.out.println("So luong size " +list_size.length);
				String[] list_quantity = request.getParameterValues("quantity[]");
				ArrayList<Product_size> list = new ArrayList<Product_size>();
				
				
				for(int i=0; i<list_size.length; i++) {
					Product_size product_size = new Product_size(bo.getIdBySize_BO(list_size[i]), id_product, Integer.parseInt(list_quantity[i]));
					list.add(product_size);
				}
				System.out.println("So luong size trong array " +list.size());
				Product product = new Product(id_category, id_product, name_product, brand, description, price, origin, byteArray, list);
				bo.addProductt_BO(product);
				
				url ="./viewProduct.jsp";
				
				ProductView_BO bo_ProductView = new ProductView_BO();
				ArrayList<ProductView> listProduct = new ArrayList<ProductView>();
				listProduct = bo_ProductView.getProductView_BO();
				request.setAttribute("listProduct", listProduct);
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}
			
			
		}else {
			System.out.println("Khong nhan duoc gia tri cua btn_add_product");
		}
	}

}
