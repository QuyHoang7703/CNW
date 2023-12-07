package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Account;
import model.bean.Detail_account;
import model.bo.Account_BO;

/**
 * Servlet implementation class Account_Servlet
 */
@WebServlet("/Account_Servlet")
public class Account_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action = request.getParameter("btn_login");
		response.setContentType("text/html;charset=UTF-8");
		String url="";
		Account_BO account_BO = new Account_BO();
		if( request.getParameter("btn_login")!=null && request.getParameter("btn_login").equals("Login")) {
			String username = request.getParameter("txt_username");
			String password = request.getParameter("txt_password");
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			
			ArrayList<Account> list_Account = account_BO.getListAccount_BO();
			boolean check = false;
			for (Account account : list_Account) {
				if(account.getUsername().equals(username) && account.getPassword().equals(password)) {
					check = true;
					break;
					
				}
			}
			if(check) {
				url = "./main.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}else {
				url = "./login.jsp";
				String message = "Tài khoản hoặc mật khẩu không chính xác";
				request.setAttribute("message", message);
				request.getRequestDispatcher(url).forward(request, response);
			}
		}
		
		if(request.getParameter("btn_register")!=null && request.getParameter("btn_register").equals("Register")) {
			
			url = "./register.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		if(request.getParameter("btn_create_account")!=null) {
			ArrayList<String> list_username = account_BO.getListUser_BO();
			String username = (String)request.getParameter("txt_username");
			String password = (String)request.getParameter("txt_password");
			if(!list_username.contains(username)) {
				Account account = new Account(username, password);
				String name = request.getParameter("txt_name");
				String address = request.getParameter("txt_address");
				String phone_number = request.getParameter("txt_phone");
				String email = request.getParameter("txt_email");
				int role = 1;
				
				Detail_account detail_account = new Detail_account(name, address, phone_number, email, role);
				try {
					account_BO.addAccount_BO(account, detail_account);
					url = "./main.jsp";
					System.out.println("ko lỗi");
					request.getRequestDispatcher(url).forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				String message = "Đã tồn tại tài khoản này rồi";
				request.setAttribute("message", message);
				url = "./register.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
	
		}
		
		

		
		
		
	}

}
