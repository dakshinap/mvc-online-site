<!--   Pujari Dakshina 
 * Student Account:  jadrn031
 * CS645, Spring 2018
 * Project #2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Daks Makeup Alley</title>
</head>
<body>
<%@ page import = "java.io.*,java.util.*,proj2.*" %>
<%! Cart cart = null; int total_items = 0; int i; %>
<%   

cart = (Cart)session.getAttribute("cart");
if(cart != null){
	total_items = cart.cartSize();
}
%>  

<% if(total_items == 0){ %>
<h2> Cart is empty</h2>
 <%}else{%>

<h3>Cart Items: <button id = "Check_out" type="button"> Check Out Now</button>  </h3>

<%for   ( i = 0; i < total_items; i++){ %>
	<div style="padding: 25px; ">
		<h3><% out.println(cart.getCartItem(i).getMid()); %> </h3>
		<input type="hidden" id="available_<%= i %>" value="<% out.println(cart.getCartItem(i).getAvailable());%>">
		<img src=<% out.println("images/" +cart.getCartItem(i).getImage()); %> style="width:100px;height:100px;">
		<p>Total Cost: <% out.println("$" + cart.getCartItem(i).getTotalCost() ) ; %> <br>
		Quantity:<input type="text"  class="modify_item" name="<%=i%>" value="<% out.println(cart.getCartItem(i).getQuantity());%>" size="5">
		(Press enter to update)<div id="<%=i%>"><%if(cart.getCartItem(i).isQuant_err() == true)out.println("Quantity unavailable. Enter upto" + cart.getCartItem(i).getAvailable()); %></div><br><br>
		<button type="button" class="delete_item" value="<%=i%>">Delete From Cart</button>
	</div>
	<hr>
	<% }%>
<% }%>
</body>
</html>