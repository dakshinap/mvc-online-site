/*   Pujari Dakshina 
 * Student Account:  jadrn031
 * CS645, Spring 2018
 * Project #2
*/



package proj2;

import java.util.ArrayList;
import java.util.List;


public class Cart{
 private ArrayList<CartItem>  totalCartItems = new ArrayList<CartItem>();
 private double orderTotal;
 private List<String> sku_map = new ArrayList<String>();
 //private HashMap<String, Integer> sku_map = new HashMap<>();
  
 public List<String> getSku_map() {
	return sku_map;
}

public void setSku_map(List<String> sku_map) {
	this.sku_map = sku_map;
}

public int cartSize() {
  return totalCartItems.size();
 }
  
 public void deleteCartItem(String stritemIndex) {
  int intItemIndex = 0;
  try {
	  intItemIndex = Integer.parseInt(stritemIndex);
	  totalCartItems.remove(intItemIndex);
	  sku_map.remove(intItemIndex);
	  calculateOrderTotal();
  } catch(NumberFormatException nfe) {
   System.out.println("Error while deleting cart item ");
  }
 }
  
 public void updateCartItem(String strItemIndex, String strQuantity) {
  double dblTotalCost = 0.0;
  double dblUnitCost = 0.0;
  int iQuantity = 0;
  int iItemIndex = 0;
  CartItem cartItem = null;
  try {
   iItemIndex = Integer.parseInt(strItemIndex);
   iQuantity = Integer.parseInt(strQuantity);
   if(iQuantity>0) {
    cartItem = (CartItem)totalCartItems.get(iItemIndex);
    dblUnitCost = cartItem.getUnitcost();
    dblTotalCost = dblUnitCost*iQuantity;
    cartItem.setQuantity(iQuantity);
    cartItem.setTotalCost(dblTotalCost);
    calculateOrderTotal();
   }
  } catch (NumberFormatException nfe) {
   System.out.println("Error while updating cart: ");
  }
   
 }
 
 public void updateCartItem(int iItemIndex, int iQuantity, int available) {
	  double dblTotalCost = 0.0;
	  double dblUnitCost = 0.0;
	  
	  CartItem cartItem = null;
	   if(iQuantity>0) {
	    cartItem = (CartItem)totalCartItems.get(iItemIndex);
	    dblUnitCost = cartItem.getUnitcost();
	    dblTotalCost = cartItem.getTotalCost() + dblUnitCost*iQuantity;
	    cartItem.setQuantity(cartItem.getQuantity() + iQuantity);
	    cartItem.setTotalCost(dblTotalCost);
	    calculateOrderTotal();
	   }
	 
	   
	 }

 public void addCartItem(String sku, String mid,String features,String description,double unitcost,int quantity,String image,int available) {
 
 if(sku_map.indexOf(sku) == -1) {
  try {
  if(quantity>0) {
	   CartItem cartItem = new CartItem(sku,mid,features,description,unitcost,quantity,image,available);
	   sku_map.add(sku);
	   totalCartItems.add(cartItem);
	   
    calculateOrderTotal();
   }
    
  } catch (NumberFormatException nfe) {
   System.out.println("Error while parsing from String to primitive types: "+nfe.getMessage());
   nfe.printStackTrace();
  }
 }else {
	 updateCartItem( sku_map.indexOf(sku) ,  quantity, available);
 }
 }
  
  
 public CartItem getCartItem(int itemIndex) {
  CartItem cartItem = null;
  if(totalCartItems.size()>itemIndex) {
   cartItem = (CartItem) totalCartItems.get(itemIndex);
  }
  return cartItem;
 }
  
 public ArrayList<CartItem> getCartItems() {
  return totalCartItems;
 }
 public void setCartItems(ArrayList<CartItem> alCartItems) {
  this.totalCartItems = alCartItems;
 }
 public double getOrderTotal() {
  return orderTotal;
 }
 public void setOrderTotal(double total) {
  this.orderTotal = total;
 }
  
 protected void calculateOrderTotal() {
  double totalCost = 0;
  for(int counter=0;counter<totalCartItems.size();counter++) {
   CartItem cartItem = (CartItem)totalCartItems.get(counter);
   totalCost+=cartItem.getTotalCost();
    
  }
  setOrderTotal(totalCost);
 }

public void clearCartItems() {
	updateDbTable();
	this.totalCartItems.clear();
	this.orderTotal = 0;
	this.sku_map.clear();
}

public void updateDbTable() {
	
	
    for(int i=0;i<totalCartItems.size();i++)
    {
        String sku = totalCartItems.get(i).getSku();
        int quantity = totalCartItems.get(i).getQuantity();
                               
        String res = new DBAction().selectQuery("SELECT quantity from product where sku ='" + sku + "'");
        int onhand = Integer.parseInt(res);
        onhand = onhand - quantity;
                               

        res = new DBAction().updateQuery("UPDATE product SET quantity = " + onhand + " WHERE sku ='" + sku + "'");
       
	
}}}
 
