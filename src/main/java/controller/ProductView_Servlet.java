package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Product;
import model.bean.ProductView;
import model.bean.Product_Category;
import model.bean.Size;
import model.bo.ProductView_BO;
import model.bo.Product_BO;

/**
 * Servlet implementation class Product_Servlet
 */
@WebServlet("/ProductView_Servlet")
public class ProductView_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String url="";
		ProductView_BO bo = new ProductView_BO();
		if(request.getParameter("function")!=null) {
			showProductView(request, response);
		}
		
		Product_BO bo_product = new Product_BO();
		if(request.getParameter("btn_add")!=null) {
			url = "./form_add_product.jsp";
			ArrayList<Product_Category> listCategory = new ArrayList<Product_Category>();
			listCategory = bo_product.getAllCategory();
			request.setAttribute("listCategory", listCategory);
			request.getRequestDispatcher(url).forward(request, response);
			
		}
		
		if(request.getParameter("btn_del")!=null) {
			String id = request.getParameter("btn_del");
			System.out.println(id);
			bo_product.deleteProduct(id);
			
			String message = "Đã xóa sản phẩm thành công";
			request.setAttribute("message", message);
			
			showProductView(request, response);
			
		}
//		String id = request.getParameter("btn_update");
//		System.out.println(id);
		if(request.getParameter("btn_update")!=null) {
			url = "./form_update_product.jsp";
			String id = request.getParameter("btn_update");
			Product product = bo_product.getProductById(id);
			request.setAttribute("product", product);
			ArrayList<Product_Category> listCategory = new ArrayList<Product_Category>();
			listCategory = bo_product.getAllCategory();
			request.setAttribute("listCategory", listCategory);
			ArrayList<Size> listSize = new ArrayList<Size>();
			listSize = bo_product.getSizes();
			request.setAttribute("listSize", listSize);
			request.getRequestDispatcher(url).forward(request, response);
			
		}
		
	}
	
	public void showProductView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		ProductView_BO bo = new ProductView_BO();
		url = "./viewProduct.jsp";
		ArrayList<ProductView> listProduct = new ArrayList<ProductView>();
		listProduct = bo.getProductView_BO();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
