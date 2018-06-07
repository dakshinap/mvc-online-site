/*   Pujari Dakshina 
 * Student Account:  jadrn031
 * CS645, Spring 2018
 * Project #2
*/





package proj2;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		response.setContentType("text/html");//setting the content type  
		
		String minPrice = "";
		String maxPrice = "";
		String vendor = "";
		String category = "";
		
		
		DBAction activity = new DBAction();
		
		if (request.getParameterMap().containsKey("filters")) {
			
			if(request.getParameterMap().containsKey("minPrice")) {
				 minPrice = request.getParameter("minPrice");
			}
			
			if(request.getParameterMap().containsKey("maxPrice")) {
				 maxPrice = request.getParameter("maxPrice");
			}

			if(request.getParameterMap().containsKey("vendor")) {
				 vendor = request.getParameter("vendor");
			}
			
			if(request.getParameterMap().containsKey("category")) {
				 category = request.getParameter("category");
			}
			
			session.setAttribute("products", activity.getFilteredProducts(minPrice,maxPrice,vendor,category));
           
        }else {
        	session.setAttribute("products", activity.getAllProducts());
        }
		
	}  
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
