package com.inventory;
import java.sql.*;


public class ProductDAO {


	public void addProduct(Product p) {
	    try (Connection con = DBConnection.getConnection()) {
	        String sql = "INSERT INTO products(name,quantity,price) VALUES(?,?,?)";
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, p.getName());
	        ps.setInt(2, p.getQuantity());
	        ps.setDouble(3, p.getPrice());

	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


public void viewProducts() {
try (Connection con = DBConnection.getConnection()) {
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("SELECT * FROM products");
while (rs.next()) {
System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getDouble(4));
}
} catch (Exception e) {
e.printStackTrace();
}
}


public void deleteProduct(int id) {
try (Connection con = DBConnection.getConnection()) {
PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id=?");
ps.setInt(1, id);
ps.executeUpdate();
System.out.println("Product Deleted");
} catch (Exception e) {
e.printStackTrace();
}
}


public void updateProduct(Product p) {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE products SET name=?, quantity=?, price=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, p.getName());
        ps.setInt(2, p.getQuantity());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getId());

        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}