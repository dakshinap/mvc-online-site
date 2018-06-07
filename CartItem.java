/*   Pujari Dakshina 
 * Student Account:  jadrn031
 * CS645, Spring 2018
 * Project #2
*/






package proj2;

public class CartItem {
	
	 	private String sku;
	    private String mid;
	    private String features;
	    private String description;
	    private double unitcost;
	    private int quantity;
	    private double totalCost;
	    private String image;
	    private int available;
	    private boolean quant_err = false;
	    
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getUnitcost() {
			return unitcost;
		}
		public void setUnitcost(double unitcost) {
			this.unitcost = unitcost;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getTotalCost() {
			return totalCost;
		}
		public void setTotalCost(double totalCost) {
			this.totalCost = totalCost;
		}
	     
	   public CartItem(String sku, String mid,String features,String description,double unitcost,int quantity,String image,int available) {
		   this.sku = sku;
		   this.mid = mid;
		   this.features = features;
		   this.description = description;
		   this.unitcost = unitcost;
		   this.quantity = quantity;
		   this.totalCost = unitcost*quantity;
		   this.image = image;
		   this.available = available;
		   
	   }
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public boolean isQuant_err() {
		return quant_err;
	}
	public void setQuant_err(boolean quant_err) {
		this.quant_err = quant_err;
	}
}
