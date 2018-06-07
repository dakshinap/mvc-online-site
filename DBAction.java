/*   Pujari Dakshina 
 * Student Account:  jadrn031
 * CS645, Spring 2018
 * Project #2
*/





package proj2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBAction {
	
	private String host_val = "opatija.sdsu.edu/jadrn031";
	private String user = "jadrn031";
	private String password = "samovar";
	private  Connection connection = null;
    private  Statement statement = null;
    private ResultSet resultSet = null;
	
	public DBAction()
	{
		
		
	}
	
	public Connection connectToDB(){
		
		Connection con = null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(host_val,user,password);  
			}catch(Exception e){ System.out.println(e);}  
		
		return con;
	}
	
	public ArrayList<Product> getAllProducts(){
		
		ArrayList<Product> productList = new ArrayList<Product>();
		try{  
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
			Connection con=DriverManager.getConnection(host_val,user,password);  
			Statement stmt=con.createStatement();  
			
			ResultSet rs=stmt.executeQuery("select sku,category.name,vendor.name,mid,description,features,cost,retail,quantity,image  from vendor, category, product WHERE vendor.id=product.venID AND category.id=product.catID;");  
			while(rs.next()) {
				
				Product p = new Product(rs);
				productList.add(p);
				
			}
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
		
		
		return productList;
		
		
	}
	
	public String selectQuery(String query) {
        String answer = "";
        try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
         connection = DriverManager.getConnection(host_val,user,password);
         statement = connection.createStatement();
         resultSet = statement.executeQuery(query);
         ResultSetMetaData rsmd = resultSet.getMetaData();
        while(resultSet.next()) 
        {
            for(int i=1; i <= rsmd.getColumnCount(); i++)  
               answer += resultSet.getString(rsmd.getColumnName(i)) + "|";
            answer = answer.substring(0, answer.length()-1);                                                                
        }
        }
        catch(Exception e) 
        {  e.printStackTrace();
        }
    finally {
	    try {
        if(resultSet != null)
            resultSet.close();
        if(statement != null)
            statement.close();
        if(connection != null)
            connection.close();
        }
        catch(SQLException e) {
        answer += e;
        }
        } 
return answer;
}

	public String updateQuery(String query) {
        
        int result = -1;
        try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(host_val,user,password);
        statement = connection.createStatement();
        result = statement.executeUpdate(query);
        }
        catch(Exception e) 
        {
                e.printStackTrace();
        }
    finally {
	    try {
        if(resultSet != null)
            resultSet.close();
        if(statement != null)
            statement.close();
        if(connection != null)
            connection.close();
        }
          catch(SQLException e) {
                 //do nothing
        }
}
return (Integer.toString(result));
}

	public ArrayList<Product> getFilteredProducts(String minPrice, String maxPrice, String vendor, String category) {
		ArrayList<Product> productList = new ArrayList<Product>();
		double dminPrice = 0;
		double dmaxPrice = 0;
		String svendor   = "";
		String scategory = "";
		
		if(minPrice == null || minPrice == "") {
			dminPrice = 0;
		}else if(Double.parseDouble(minPrice) > 0) {
			dminPrice = Double.parseDouble(minPrice);
		}
		
		if(maxPrice == null || maxPrice == "") {
			dmaxPrice = 1000;
		}else if(Double.parseDouble(maxPrice) > 0) {
			dmaxPrice = Double.parseDouble(maxPrice);
		}
		
		if(vendor == null || vendor == "") {
			svendor = "('mac','clinique','loreal','pixie','nyx')";
		}else {
			svendor = "(" + vendor + ")";
		}
		
		if(category == null || category == "") {
			scategory = "('nose','cheeks','lips','eyes','brows')";
		}else {
			scategory = "(" + category + ")";;
		}
		
		try{  
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
			Connection con=DriverManager.getConnection(host_val,user,password);  
			Statement stmt=con.createStatement();  
			
			ResultSet rs=stmt.executeQuery("select sku,category.name,vendor.name,mid,description,features,cost,retail,quantity,image  from vendor, category, product WHERE vendor.id=product.venID AND category.id=product.catID AND (retail BETWEEN " + dminPrice + " AND  " + dmaxPrice + ") AND category.name IN " + scategory + " AND vendor.name IN " +svendor + " ;");  
			while(rs.next()) {
				
				Product p = new Product(rs);
				productList.add(p);
				
			}
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
		
		
		return productList;
	}
	

}
