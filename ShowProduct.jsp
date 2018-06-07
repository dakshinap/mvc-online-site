<!--   Pujari Dakshina 
 * Student Account:  jadrn031
 * CS645, Spring 2018
 * Project #2
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*"%>
<html>
<head>
<title>Daks Makeup Alley</title>
</head>

<body>
<%@ page import = "java.io.*,java.util.*,proj2.*" %>
<%! int ordered = 0; int available;%>
<%
  int idx = -1;
  String sku = (String)request.getParameter("sku");
  Cart cart = (Cart)session.getAttribute("cart");
  if(cart == null){
	  //do nothing
  }else if(cart.getSku_map().indexOf(sku) != -1){
	idx = cart.getSku_map().indexOf(sku);
	ordered = cart.getCartItem(idx).getQuantity();
  }
  
  pageContext.setAttribute("sku", sku);
  String db = "project";
  String user = "root"; 
  String password = "password";
  try {
    java.sql.Connection con;
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost/"+db, user, password);
  
    Statement stmt=con.createStatement();  
	
	ResultSet rs=stmt.executeQuery("select sku,category.name,vendor.name,mid,description,features,cost,retail,quantity,image  from vendor, category, product WHERE vendor.id=product.venID AND category.id=product.catID and sku = '" + sku + "';");  

		while(rs.next()) {
		
		Product p = new Product(rs);
		pageContext.setAttribute("product", p);
	}
	con.close();  
  }
  catch(SQLException e) {
    out.println("SQLException caught: " +e.getMessage());
  }
  
 
  available = ((Product)pageContext.getAttribute("product")).getQuantity() - ordered;
  if(available < 0)available = 0;
  ordered = 0;
%>



<form id="productDetails" action='http://localhost:8080/Inventory/servlet/cart' method="post" >
<input type="hidden" name="mid" value=<% out.println(((Product)pageContext.getAttribute("product")).getMid());%>>
<input type="hidden" name="image" value=<% out.println(((Product)pageContext.getAttribute("product")).getImage());%>>
<input type="hidden" name="category" value=<% out.println(((Product)pageContext.getAttribute("product")).getCategory());%>>
<input type="hidden" name="vendor" value=<% out.println(((Product)pageContext.getAttribute("product")).getVendor());%>>
<input type="hidden" name="description" value=<% out.println(((Product)pageContext.getAttribute("product")).getDescription());%>>
<input type="hidden" name="features" value=<% out.println(((Product)pageContext.getAttribute("product")).getFeatures());%>>
<input type="hidden" name="quantity" value=<%=available%>>
<input type="hidden" name="sku" value=<% out.println(((Product)pageContext.getAttribute("product")).getSku());%>>
<input type="hidden" name="cost" value=<% out.println(((Product)pageContext.getAttribute("product")).getRetail());%>>
<input type="hidden" name="action" value="add">

<h2><% out.println(((Product)pageContext.getAttribute("product")).getMid());%></h2>
<img src=<% out.println("images/" +((Product)pageContext.getAttribute("product")).getImage()); %> style="width:300px;height:300px;"><br>
Category:<% out.println(((Product)pageContext.getAttribute("product")).getCategory());%><br>
Vendor:<% out.println(((Product)pageContext.getAttribute("product")).getVendor());%><br>
Features:<% out.println(((Product)pageContext.getAttribute("product")).getFeatures());%><br>
Description:<% out.println(((Product)pageContext.getAttribute("product")).getDescription());%><br>
Status:<% if (((Product)pageContext.getAttribute("product")).getQuantity() > 0){ out.println("<font color='green'>In-Stock </font>"); } else{out.println("<font color='red'>Coming Soon</font>");}%><br>
Cost: $<% out.println(((Product)pageContext.getAttribute("product")).getRetail());%><br><br>
Enter Quantity to Buy : <input type="text" name ="quantity_toBuy" value="1">

<input type="submit" value="Add To Cart"><p id="Message" style="color:red;"></p><br><br>
</form>

</body>
</html>