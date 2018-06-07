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
<link rel="stylesheet" href="css/home.css">

</head>
<body>

<%@ page import = "java.io.*,java.util.*,proj2.*" %>
<%! Cart cart = null; int total_items = 0; int i; %>
<%   

cart = (Cart)session.getAttribute("cart");
total_items = cart.cartSize();

%>  
<h2>Order Summary:</h2>
<table class="inlineTable">
	<tr>
		<th colspan="2">Shipping Information:</th>
    </tr>
    <tr>
    	<td>Name</td>
    	<td> <%= request.getParameter("name")%> </td>
   </tr>  
   <tr>
    	<td>Address</td>
    	<td> <%= request.getParameter("address_1")%> , <%= request.getParameter("address_2")%>
        <%= request.getParameter("city")%>, <%= request.getParameter("state")%>, <%= request.getParameter("zip")%> </td>
   </tr>  
   <tr>
    	<td>Contact Phone</td>
    	<td> <%= request.getParameter("phone")%> </td>
   </tr>  
</table>

<table class="inlineTable">
	<tr>
		<th colspan="2">Billing Information:</th>
    </tr>
    <tr>
    	<td>Name</td>
    	<td> <%= request.getParameter("name1")%> </td>
   </tr>  
   <tr>
    	<td>Address</td>
    	<td> <%= request.getParameter("address_11")%> , <%= request.getParameter("address_21")%>
        <%= request.getParameter("city1")%>, <%= request.getParameter("state1")%>, <%= request.getParameter("zip1")%> </td>
   </tr>  
   <tr>
    	<td>Contact Phone</td>
    	<td> <%= request.getParameter("phone1")%> </td>
   </tr>  
   <tr>
    	<td>Card Number</td>
    	<td> <%= request.getParameter("cardNum")%> </td>
   </tr>  
</table>

<table class="inlineTable" >
  <tr>
      <th colspan="3">Items Ordered:</th>
  </tr>
  <tr>
    <td>Mid</td>
    <td>Quantity</td>
    <td>Cost</td>
  </tr>  
	<%for   ( i = 0; i < total_items; i++){ %>
	<tr>
	  <td><% out.println(cart.getCartItem(i).getMid()); %> </td>
	  <td><% out.println(cart.getCartItem(i).getQuantity()); %> </td>
	  <td><% out.println(cart.getCartItem(i).getTotalCost()); %> </td>
	</tr>
		<% }%>
	<tr>
	    <td colspan="2">Total cost</td>
	   
	    <td>$<% out.println(cart.getOrderTotal()); %></td>
	</tr>
	<tr>
	    <td colspan="2">Shipping Cost</td>
	    
	    <td>$5</td>
	</tr>
	<tr>
	    <td colspan="2">Sales tax</td>
	    
	    <td>7.75% </td>
	</tr>
	<tr>
	    <td colspan="2">Order Total</td>
	   
	    <td>$<% out.println((0.0775*(cart.getOrderTotal()+ 5)) + (cart.getOrderTotal()+ 5)) ; %> </td>
	</tr>
  </table>
  <button class=" button1" id="confirm">Place Order</button>
  <button class=" button1" id="cancel_order">Cancel</button>
</body>
</html>