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
	<title>Daks Makeup Alley</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/home2.css">
</head>
<body>
	<%@ page import = "java.io.*,java.util.*,proj2.Product" %>
	<%! ArrayList<Product> prod = null; int num_prod = 0;int i; %>
	<%prod = (ArrayList<Product>)session.getAttribute("products");
	 	num_prod = prod.size();%>  
	
	<div class="main_fragment" id="left_column" >
		<h2 > Filters </h2><br>
		
		<form id="filter_list" >
		  Select Vendor: <br>
		  <input type="checkbox" name="vendor" value="mac"> M.A.C<br>
		  <input type="checkbox" name="vendor" value="clinique" > Clinique<br>
		  <input type="checkbox" name="vendor" value="loreal"> Loreal<br>
		  <input type="checkbox" name="vendor" value="nyx"> Nyx<br>
		  <input type="checkbox" name="vendor" value="pixie"> Pixie<br><br><br>
		  
		  Select Category: <br>
		  <input type="checkbox" name="category" value="nose"> Nose<br>
		  <input type="checkbox" name="category" value="cheeks" > Cheeks<br>
		  <input type="checkbox" name="category" value="lips" > Lips<br>
		  <input type="checkbox" name="category" value="eyes" > Eyes<br>
		  <input type="checkbox" name="category" value="brows" > Brows<br><br><br>
		  
		  Enter Price Range: <br>
		  Min: $ <input type="text" id="min_price"  maxlength="7" size="5"> &nbsp;&nbsp; Max:$ <input type="text" id="max_price" maxlength="7" size="5"><br><br>
		  <button type="button"  id="filter_yes">Apply Filter</button>&nbsp;&nbsp; <button type="button" id="filter_no">Reset Filter</button><br><br><br><br><br><br>
		</form>
	</div>
	
	<div class = "main_fragment" id="right_column">
		<%if(num_prod == 0) out.println("<p>&nbsp;&nbsp;&nbsp; Sorry no items match your search criteria.....<p>"); %>
		<%for   ( i = 0; i < num_prod; i++){ %>
		<div style="padding: 25px; ">
			<h3><% out.println(prod.get(i).getMid()); %> </h3>
			<img src=<% out.println("images/" +prod.get(i).getImage()); %> style="width:100px;height:100px;">
			<p>Price: <% out.println("$" + prod.get(i).getRetail() ) ; %> <br>
			Status: <% if (prod.get(i).getQuantity() > 0){ out.println("<font color='green'>In-Stock </font>"); } else{out.println("<font color='red'>Coming Soon</font>");}%></p>
			<button class="btn" type="button" name = <% out.println(prod.get(i).getSku());%>> View More details</button>
		</div>
		<hr>
		<% }%>
	</div>

</body>
</html>


