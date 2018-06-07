/*   Pujari Dakshina 
 * Student Account:  jadrn031
 * CS645, Spring 2018
 * Project #2
*/

package proj2;


import java.sql.ResultSet;

public class Product {
	
	private String sku;
	private String category;
	private String vendor;
	private String mid;
	private String features;
	private String image;
	private float cost;
	private float retail;
	private int quantity;
	private String description;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getRetail() {
		return retail;
	}
	public void setRetail(float retail) {
		this.retail = retail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product(String sku,String category,String vendor,String mid,String description,String features,float cost,float retail,String image,int quantity) {
		this.sku = sku;
		this.category = category;
		this.vendor = vendor;
		this.mid = mid;
		this.setDescription(description);
		this.features = features;
		this.cost = cost;
		this.retail = retail;
		this.image = image;
		this.quantity = quantity;
		
	}
	
	public Product(ResultSet rs) {
		
		try {
		this.sku = rs.getString(1);
		this.category = rs.getString(2);
		this.vendor = rs.getString(3);
		this.mid = rs.getString(4);
		this.setDescription(rs.getString(5));
		this.features = rs.getString(6);
		this.cost = rs.getFloat(7);
		this.retail = rs.getFloat(8);
		this.image = rs.getString(10);
		this.quantity = rs.getInt(9);
		}catch (Exception e){ System.out.println(e);}  
		
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}
