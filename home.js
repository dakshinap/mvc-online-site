/*   Pujari Dakshina 
 * Student Account:  jadrn031
 * CS645, Spring 2018
 * Project #2
*/





$(document).ready(function() {
	
	var slideIndex = 0;
	showSlides();

	function showSlides() {
	    var i;
	    var slides = document.getElementsByClassName("mySlides");
	    var dots = document.getElementsByClassName("dot");
	    for (i = 0; i < slides.length; i++) {
	       slides[i].style.display = "none";  
	    }
	    slideIndex++;
	    if (slideIndex > slides.length) {slideIndex = 1}    
	    for (i = 0; i < dots.length; i++) {
	        dots[i].className = dots[i].className.replace(" active", "");
	    }
	    slides[slideIndex-1].style.display = "block";  
	    dots[slideIndex-1].className += " active";
	    setTimeout(showSlides, 2000); // Change image every 2 seconds
	}
	
	$.get('http://jadran.sdsu.edu/jadrn031/servlet/home',display_products);
	
	$('#about').click(function(){
		$('#about').css({backgroundColor: '#F08080'});
		$('#contact').css({backgroundColor: '#333'});
      	$('#home').css({backgroundColor: '#333'});
          $.get('proj2/AboutUs.jsp',display_content);
    }); // about.click
    
    $('#contact').click(function(){
    	$('#contact').css({backgroundColor: '#F08080'});
    	$('#home').css({backgroundColor: '#333'});
      	$('#about').css({backgroundColor: '#333'});
          $.get('proj2/ContactUs.jsp',display_content);
    }); //contact.click
    
    $('#home').click(function(){
    	$('#home').css({backgroundColor: '#F08080'});
      	$('#contact').css({backgroundColor: '#333'});
      	$('#about').css({backgroundColor: '#333'});
        $.get('http://jadran.sdsu.edu/jadrn031/servlet/home',display_products);
     }); //home.click
    
    $('#crt').click(function(){
    	 $.get('proj2/ShowCart.jsp',display_cart);
     }); //crt.click
    
    $('#content').on('click', '.btn', function(e){
       console.log("clicked");
       console.log(this.name);
       var param = "sku=" + (this).name;
       $.post('proj2/ShowProduct.jsp?' + param, display_content);
       
    });
    
    $('#content').on('click', '#Check_out', function(e){
       
        $.post('proj2/CheckOut.jsp?', display_info);
        
     });
    
    $('#content').on('focus', '#quantity_toBuy', function(e){
    	$("#Message").text("");
    });
    
    $('#content').on('submit', '#productDetails', function(e){
        console.log("clicked");
        console.log(this.name);
        var quantity_toBuy = document.forms["productDetails"]["quantity_toBuy"].value;
        var available      = document.forms["productDetails"]["quantity"].value;
        if(quantity_toBuy == ""){
        	$("#Message").text("Invalid Quantity Entered");
        	return false;
        }
        if(new RegExp('^([0-9]*)$').test(quantity_toBuy) == false){
        	$("#Message").text("Invalid Quantity Entered.Please enter integer");
        	return false;
        }
        if(parseInt(available) == 0){
        	$("#Message").text("Sorry we are out of stock. Please check back in some days");
        	return false;
        }
        if(parseInt(quantity_toBuy) > parseInt(available)){
        	$("#Message").text("Entered Quantity not Available. Please select quantity upto " + available);
        	return false;
        }
        
      
        
        
       
        $.ajax({
            type: "POST",
             url: 'http://jadran.sdsu.edu/jadrn031/servlet/cart',
             data: $(this).serialize(),
             success: function() {
               // callback code here
            	 $.get('proj2/ShowCart.jsp',display_cart);
              }
           });

        return false;
     });
    
   

})//document ready

function display_info(response){
	$('#content').html(response);
    
	handle_focus_out();
	
	 $("#name").focusout(function(e){
		 if($('#bill_ship').is(":checked")){
			 $('#name1').val($('#name').val());
		 }
	 });
	
	$('#bill_ship').change(function() {
        if($(this).is(":checked")) {
        	 $("#name1").removeClass("error");
        	 $('#zip1').removeClass("error");
        	 $('#address_11').removeClass("error");
        	 $('#address_21').removeClass("error");
        	 $('#city1').removeClass("error");
        	 $('#state1').removeClass("error");
        	 $('#phone1').removeClass("error");
        	 
           $('#name1').val($('#name').val());
           $('#zip1').val($('#zip').val());
	    $('#address_11').val( $('#address_1').val() );
	   $('#address_21').val($('#address_2').val());
           $('#city1').val($('#city').val());
	   $('#state1').val($('#state').val());
	   
           $('#phone1').val($('#phone').val());
        }else{
           $('#name1').val("");
           $('#zip1').val("");
           $('#address_11').val("");
           $('#address_21').val("");
           $('#city1').val("");
           $('#state2').val("");
           $('#state1').val("");
           $('#phone1').val("");      
        }
    });
	
	
			 $('#content').on('click', '#address_submit', function(e){
	 
      if(isDataValid() == true){
    	  console.log($("#shipping" ).serialize());
    	  $.ajax({
              type: "POST",
               url: 'proj2/confirmation.jsp',
               data: $("#shipping" ).serialize(),
               success: function(result) {
            	   $('#content').html(result);
            	   $( "#confirm" ).click(function() {
            		   $.post('http://jadran.sdsu.edu/jadrn031/servlet/cart', { action: "Empty"}, 
            				    function(returnedData){
            			   $.get('proj2/confirm.jsp',display_content);
            				});
            		  
            		 });
            	   $( "#cancel_order" ).click(function() {
            		   $.get('http://jadran.sdsu.edu/jadrn031/servlet/home',display_products);
            		 });
                }
             });

      }
    
    });
    $('#cancel').click(function() {
     $.get('http://jadran.sdsu.edu/jadrn031/servlet/home',display_products);
     });
    

}

var checkPrice = function(currency) {
	var regex_currency = new RegExp("^[0-9]*(\.[0-9]{0,2})?$");
	return regex_currency.test(currency);
};

function handle_focus_out(){

	 $("#name").focusout(function(e){
		 if($('#bill_ship').is(":checked")){
			 $('#name1').val($('#name').val());
		 }
	 });
	 
	 $("#address_1").focusout(function(e){
		 if($('#bill_ship').is(":checked")){
			 $('#address_11').val($('#address_1').val());
		 }
	 });
	 $("#address_2").focusout(function(e){
		 if($('#bill_ship').is(":checked")){
			 $('#address_21').val($('#address_2').val());
		 }
	 });
	 $("#city").focusout(function(e){
		 if($('#bill_ship').is(":checked")){
			 $('#city1').val($('#city').val());
		 }
	 });
	 $("#state").focusout(function(e){
		 if($('#bill_ship').is(":checked")){
			 $('#state1').val($('#state').val());
		 }
	 });
	 $("#zip").focusout(function(e){
		 if($('#bill_ship').is(":checked")){
			 $('#zip1').val($('#zip').val());
		 }
	 });
	 $("#phone").focusout(function(e){
		 if($('#bill_ship').is(":checked")){
			 $('#phone1').val($('#phone').val());
		 }
	 });
	 
}

function display_content(response){
console.log("entered");
 $('#content').html(response);

}// display_content

function display_products(response){
	console.log("entered products display");
	$.get('proj2/Home2.jsp',display_product_content);

	}// display_products

function display_product_content(response){
	var divs = document.getElementById('left_column');
	if(divs != null && divs != ""){
		divs = $('#left_column').clone();
		display_content(response);
		 $('#left_column').replaceWith(divs);
		
	}else display_content(response);
	
/////////////////////////////////////////////////// Filters /////////////////////////////////////////    
    
    $('#content').on('click',"#filter_yes",function(e){
    	 
    	var data = {};
    	data['filters'] = "1";
        
    	if(!check_min_max_price()) {
    		return false;
    	}else{
    			data["minPrice"] = $("#min_price").val();;
    			data["maxPrice"] = $("#max_price").val();;
    	}
    
    	
        
        var vendorList   = '';
        var categoryList = ''; 
        
        $.each($("input[name='vendor']:checked"), function(){
                vendorList += "'" + $(this).val() + "',";
            });
          
        $.each($("input[name='category']:checked"), function(){
                categoryList += "'" + $(this).val() + "',";
            });
        
        if(vendorList != "") data["vendor"]   =  vendorList.substring(0, vendorList.length - 1);
        if(categoryList != "")data["category"] = categoryList.substring(0, categoryList.length - 1);
        
        if(jQuery.isEmptyObject(data)){
        	//Chill no need to filter
        }else{
        	$.post('http://jadran.sdsu.edu/jadrn031/servlet/home', data, display_products);
				    
        }
         
        return false;
            
        });

    $('#content').on('click',"#filter_no",function(e){
            $("#min_price").val("");
            $("#max_price").val("");
            $('#filter_list input[type=checkbox]').attr('checked',false);
            $.get('http://jadran.sdsu.edu/jadrn031/servlet/home',display_products);
	});
        
  
        
/////////////////////////////////////////////////// Filters ////////////////////////////////////////////////////////////////    
	

}// display_product_content

function check_min_max_price(){
	is_right = true;
	
	var maxPrice = $("#max_price").val();
	var minPrice = $("#min_price").val();
	
	if(maxPrice == ""){
		//cool
	}else if(!checkPrice(maxPrice)){
    	$("#max_price").addClass("error");
    	is_right = false;
    }
    
	if(minPrice == ""){
		//cool
	}else if(!checkPrice(minPrice)){
    	$("#min_price").addClass("error");
    	is_right = false;
    }
    
    if(is_right == true){
    	if(maxPrice < minPrice){
    		is_right = false;
    		$("#min_price").addClass("error");
    		$("#max_price").addClass("error");
    	}
    }
    
    $("#min_price").focusout(function(){
    	 $("#min_price").removeClass("error");
          
      });
    
    $("#max_price").focusout(function(){
    	 $("#max_price").removeClass("error");
          
      });
    
    return is_right;
	
}

function display_cart(response){
	display_content(response);
	 $('.delete_item').click(function(e){
		 $.post('http://jadran.sdsu.edu/jadrn031/servlet/cart', { action: "Delete", itemIndex :  this.value}, 
				    function(returnedData){
			 $.get('proj2/ShowCart.jsp',display_cart);
				});
	 });
	 
	 $(".modify_item").change(function(e) {
		 if(this.value == ""){
			 $("#"+this.name).html("Please enter valid Quantity")
			 this.focus();
		 }else if(new RegExp('^([0-9]*)$').test(this.value) == false){
			 $("#"+this.name).html("Please enter valid Quantity")
			 this.focus();
		 }else if(parseInt(this.value) > parseInt($("#available_" + this.name).val())){
			 $("#"+this.name).html("Please enter quantity upto " +  $("#available_" + this.name).val() )
			 this.focus();
		 }else{
			 $.post('http://jadran.sdsu.edu/jadrn031/servlet/cart', {action : 'Update', quantity : this.value, itemIndex: this.name},cart_click);
		 }
		});
	 $(".modify_item").focusout(function(e) {
		 $("#"+this.name).html("")
	 });
	 

	}//display_cart

function cart_click(){
	$('#crt').click();
}

/////////////////////////////////////////// Check Shipping and billing Info ///////////////////////////////////////////////////////////
  function isDataValid(){
   
   var flag = true;

   if(isNameValid($('#name')) == false)
      flag = false;
   if(isAddressValid($('#address_1')) == false)
       flag = false;
    if(isCityValid($('#city')) == false)
       flag = false;
    if(isStateValid($('#state')) == false)
       flag = false;
    if(isZipValid($('#zip')) == false)
     flag = false;
    if(isPhoneValid($('#phone')) == false)
       flag = false;
    if(isNameValid($('#name1')) == false)
       flag = false;
    if(isAddressValid($('#address_11')) == false)
       flag = false;
    if(isAddressValid($('#address_21')) == false)
       flag = false;
    if(isAddressValid($('#address_2')) == false)
        flag = false;
    if(isCityValid($('#city1')) == false)
       flag = false;
    if(isStateValid($('#state1')) == false)
       flag = false;
    if(isZipValid($('#zip1')) == false)
       flag = false;
    if(isPhoneValid($('#phone1')) == false)
       flag = false;
    if(isCardValid($('#cardNum')) == false)
     flag = false;
    if(isCardTypeValid($('#cardType')) == false)
     flag = false;
    if(isExpiryValid($('#exp')) == false)
       flag = false;
    if(isCvvValid($('#cvv')) == false)
       flag = false;
  if(!flag){
	  $("#error").text("Please enter valid details for the highlighted fields")
  }
 return flag;
}


function isNameValid(input){

 $(input).focusout(function(){
    $(input).removeClass("error");
      
  });
 if(input.val() == "")
   {     input.addClass("error");
   
      
         return false;
   }
   else if(new RegExp('^([a-zA-Z]+[a-z A-Z]*)$').test(input.val()) == false)
   {      input.addClass("error");
         
        
         return false;
   }
 

 return true;
}
function isZipValid(input){
  $(input).focusout(function(){
    $(input).removeClass("error");
     
  });
  if(input.val() == "")
   {  input.addClass("error");
        
      
         return false;
   }else if(new RegExp('^([0-9]*)$').test(input.val()) == false)
   {
        input.addClass("error");
        
         
         return false;
   }
  else if(input.val().length != 5)
   {    input.addClass("error");
       
       
         
         return false;
   }

 return true;
}
function isAddressValid(input){
  $(input).focusout(function(){
    $(input).removeClass("error");
      
  });
 if(input.val() == "")
   {
         input.addClass("error");
        
        
        
         return false;
   }
 return true;
}
function isCityValid(input){
  $(input).focusout(function(){
    $(input).removeClass("error");
      
  });
 if(input.val() == "")
   {
         input.addClass("error");
         
        
       
         return false;
   }
   else if(new RegExp('^([a-zA-Z]+[a-z A-Z]*)$').test(input.val()) == false)
  {
         input.addClass("error");
         
         
         return false;
  }

 return true;
}
function isStateValid(input){
  $(input).focusout(function(){
    $(input).removeClass("error");
     
  });
 if(input.val() == "")
   {
         input.addClass("error");
        
        
         
         return false;
   }
  else if(new RegExp('^([a-zA-Z]+[a-z A-Z]*)$').test(input.val()) == false)
 {
         input.addClass("error");
        
       
         
         return false;
 }

 return true;
}

function isPhoneValid(input){
  $(input).focusout(function(){
    $(input).removeClass("error");
     
  });
 if(input.val() == "")
   {
         input.addClass("error");
   
         
         return false;
   }
 
 if(new RegExp('^[0-9]{3}-[0-9]{3}-[0-9]{4}$').test(input.val()) == false)
   {
         input.addClass("error");
       
         
         return false;
   }

 return true;
}
function isCardValid(input){
  $(input).focusout(function(){
    $(input).removeClass("error");
     
  });
 if(input.val() == "")
   {
         input.addClass("error");
        
         
         return false;
   }
   else if(new RegExp('^([0-9]*)$').test(input.val()) == false)
   {
         input.addClass("error");
        
         
         
         return false;
   }
   else if(input.val().length != 16)
   {
         input.addClass("error");
         
        
         return false;
   }

 return true;
}
function isExpiryValid(input){
  $(input).focusout(function(){
    $(input).removeClass("error");
      
  });
 if(input.val() == "")
   {
         input.addClass("error");
         
        
         
         return false;
   }
 return true;
}
function isCvvValid(input){
  $(input).focusout(function(){
    $(input).removeClass("error");
      
  });
 if(input.val() == "")
   {
         input.addClass("error");
       
         
         
         return false;
   }else if(new RegExp('^([0-9]*)$').test(input.val()) == false)
   {
         input.addClass("error");
        

         
         return false;
   }
  else if(input.val().length == "")
   {
         input.addClass("error");
        
         
         
         return false;
   }
return true;
}
function isCardTypeValid(input){
  $(input).focusout(function(){
    $(input).removeClass("error");
      
  });
 if(input.val() == "")
 {
         input.addClass("error");
         
         
         
         return false;
 }
 else if(new RegExp('^([a-zA-Z]+[a-z A-Z]*)$').test(input.val()) == false)
 {
         input.addClass("error");
         
         
         
         return false;
 }

 return true;
}