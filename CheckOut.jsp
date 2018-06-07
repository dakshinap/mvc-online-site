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
<title>Customer Details</title>
<link rel="stylesheet" href="css/home.css">
</head>
<body>
<b> Shipping Details:</b> <span id="error" style="color:red;font-size:20px;"></span><br>
<form id="shipping" >
  <p class = "design">
  <label>Name: </label>
  <input type="text" name="name" id="name"> 
  </p>
  <p class = "design">
  <label>Address Line 1:</label>
  <input type="text" name="address_1" id="address_1"> <br>
  </p>
  <p class = "design">
  <label>Address Line 2:</label>
  <input type="text" name="address_2" id="address_2"> <br>
  </p>
  <p class = "design">
  <label>City:</label>
  <input type="text" name="city" id="city"> <br>
  </p>
  <p class = "design">
  <label>State:</label>
  <input type="text" name="state" id="state"> <br>
  </p>
  <p class = "design">
  <label>Zip:</label>
  <input type="text" name="zip" id="zip" maxlength="5" size="5" placeholder="xxxxx"> <br>
  </p>
  <p class = "design">
  <label>Contact Phone:</label>
  <input type="text" name="phone" id="phone" placeholder="xxx-xxx-xxxx"> <br><br>
  </p>

<input type="checkbox" id="bill_ship"> Billing information same as shipping??<br><br>
<b> Billing Details:</b> <br>
  <p class = "design">
  <label>Name:</label>
  <input type="text" name="name1" id="name1">
  </p>
  <p class = "design">
  <label>Address Line 1:</label>
  <input type="text" name="address_11" id="address_11"> <br>
  </p>
  <p class = "design">
  <label>Address Line 2:</label>
  <input type="text" name="address_21" id="address_21"> <br>
  </p>
  <p class = "design">
  <label>City:</label>
  <input type="text" name="city1" id="city1"> <br>
  </p>
  <p class = "design">
  <label>State:</label>
  <input type="text" name="state1" id="state1"> <br>
  </p>
  <p class = "design">
  <label>Zip:</label>
  <input type="text" name="zip1" id="zip1" maxlength="5" size="5" placeholder="xxxxx"> <br>
  </p>
  <p class = "design">
  <label>Contact Phone:</label>
  <input type="text" name="phone1" id="phone1" placeholder="xxx-xxx-xxxx"> <br>
  </p>
  <p class = "design">
 <label>Credit Card Type:</label>
  <input type="text" name="cardType" id="cardType"> <br>
  </p>
  <p class = "design">
  <label>Card Number:</label>
  <input type="text" name="cardNum" id="cardNum" placeholder="16 digit number"> <br>
  </p>
  <p class = "design">
  <label>Card Expiry Date:</label>
  <input type="text" name="exp" id="exp" placeholder="mm/yy"> <br>
  </p>
  <p class = "design">
  <label>Security Code:</label>
  <input type="text" name="cvv" id="cvv" placeholder="xxx"> <br>
  </p>
  
  <button type="button" id="address_submit">Continue check out</button> 
  <button type="button" id="cancel">Cancel and continue shopping</button>
    
</form>



</body>
</html>