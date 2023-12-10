package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ProductView;
import model.bean.Product_Category;
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
			url = "./viewProduct.jsp";
			ArrayList<ProductView> listProduct = new ArrayList<ProductView>();
			listProduct = bo.getProductView_BO();
			request.setAttribute("listProduct", listProduct);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			
		}
		
		Product_BO bo_product = new Product_BO();
		if(request.getParameter("btn_add")!=null) {
			url = "./form_add_product.jsp";
			ArrayList<Product_Category> listCategory = new ArrayList<Product_Category>();
			listCategory = bo_product.getAllCategory();
			request.setAttribute("listCategory", listCategory);
			request.getRequestDispatcher(url).forward(request, response);
			
		}
		
		
	}

}
