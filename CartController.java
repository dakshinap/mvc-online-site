/*   Pujari Dakshina 
 * Student Account:  jadrn031
 * CS645, Spring 2018
 * Project #2
*/




package proj2;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class CartController extends HttpServlet {
  
 //public static final String addToCart
  
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
 
  String action = request.getParameter("action");
   
   
  if(action!=null && !action.equals("")) {
	   if(action.equals("add")) {
	    addToCart(request);
	   } else if (action.equals("Update")) {
	    updateCart(request);
	   } else if (action.equals("Delete")) {
	    deleteCart(request);
	   }else if (action.equals("Empty")) {
		    emptyCart(request);
	   }
  }
  //response.sendRedirect("../AboutUs.jsp");
 }
  
 protected void deleteCart(HttpServletRequest request) {
  HttpSession session = request.getSession();
  String itemIndex = request.getParameter("itemIndex");
  Cart cart = null;
   
  Object get_cart = session.getAttribute("cart");
  if(get_cart!=null) {
	  cart = (Cart) get_cart ;
  } else {
   cart = new Cart();
  }
  cart.deleteCartItem(itemIndex);
 }
 
 protected void emptyCart(HttpServletRequest request) {
	  HttpSession session = request.getSession();
	
	  Cart cart = null;
	  Object get_cart = session.getAttribute("cart");
	  if(get_cart!=null) {
		  cart = (Cart) get_cart ;
		  cart.clearCartItems();
	  } else {
	   cart = new Cart();
	   session.setAttribute("cart", cart);
	  }
	 
}
  
 protected void updateCart(HttpServletRequest request) {
  HttpSession session = request.getSession();
  String strQuantity = request.getParameter("quantity");
  String strItemIndex = request.getParameter("itemIndex");
  
  Cart cart = null;
   
  Object get_cart = session.getAttribute("cart");
  if(get_cart!=null) {
   cart = (Cart) get_cart ;
  } else {
   cart = new Cart();
   session.setAttribute("cart", cart);
  }
  cart.updateCartItem(strItemIndex, strQuantity);
 }
  
 protected void addToCart(HttpServletRequest request) {
  HttpSession session = request.getSession();
  String sku = request.getParameter("sku");
  String description = request.getParameter("description");
  String mid = request.getParameter("mid");
  String features = request.getParameter("features");
  double unitcost = Double.parseDouble(request.getParameter("cost"));
  int quantity = Integer.parseInt(request.getParameter("quantity_toBuy"));
  int available = Integer.parseInt(request.getParameter("quantity"));
  String image = request.getParameter("image");
   
  Cart cart = null;
   
  Object get_cart = session.getAttribute("cart");
 
  if(get_cart!=null) {
   cart = (Cart) get_cart ;
  } else {
   cart = new Cart();
   session.setAttribute("cart", cart);
  }
   
  cart.addCartItem( sku,  mid, features, description, unitcost, quantity, image,available);
 }
 
}
