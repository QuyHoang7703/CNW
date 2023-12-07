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
import model.bo.ProductView_BO;

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
		try {
			String a = request.getParameter("function");
			System.out.println(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Khong nhan dc gi");
			e.printStackTrace();
		}
		ProductView_BO bo = new ProductView_BO();
		if(request.getParameter("function")!=null) {
			url = "./viewProduct.jsp";
			ArrayList<ProductView> listProduct = new ArrayList<ProductView>();
			listProduct = bo.getProductView_BO();
			if(listProduct.size()>0) {
				System.out.println("Co pt");
			}else {
				System.out.println("Ko pt");
			}
			request.setAttribute("listProduct", listProduct);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			
		}else {
			System.out.println("ko nhan dc");
		}
	}

}
