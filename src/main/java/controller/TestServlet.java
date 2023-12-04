package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = session.getAttribute("user").toString();
		 PrintWriter out = response.getWriter();
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Sample Page</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<h1>" +username +"</h1>");
		    out.println("<script>");
		    out.println("function myFunction() {");
		    out.println("   alert('Hello from JavaScript!');");
		    out.println("}");
		    out.println("</script>");
		    out.println("<button onclick='myFunction()'>Click me</button>");
		    out.println("</body>");
		    out.println("</html>");
	}

}
