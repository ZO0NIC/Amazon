package Amazon.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;
import org.apache.commons.codec.binary.Base64;

import Amazon.User.User;

public class Product {
	String Name;
	private byte[] photo;
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public class Base64Encoder {
	    public static String encode(byte[] imageData) {
	        return Base64.encodeBase64String(imageData);
	    }
	}
	public String getPhotoAsBase64() {
        return Base64Encoder.encode(photo);
    }
	
	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	static Connection con=null;
	static {
		try {
			
			Properties ps = new Properties();
			ps.put("user", "root");
			ps.put("password", "root");
			//String sql = "ALTER TABLE user MODIFY COLUMN name VARCHAR(255) AFTER id, MODIFY COLUMN gender VARCHAR(255) AFTER name, MODIFY COLUMN age INT AFTER gender, MODIFY COLUMN address VARCHAR(255) AFTER age";
			Driver d = new Driver();
			con = d.connect("jdbc:mysql://localhost:3306/demodb", ps);
			Statement stmt = con.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}
	
	public List<Product> showProduct(String tag) {
	    List<Product> pd = new ArrayList<>();
	    try (PreparedStatement pstmt = con.prepareStatement("SELECT name, photo FROM demodb.product WHERE tag=?")) {
	        pstmt.setString(1, tag);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Product prod = new Product();
	                prod.setName(rs.getString("name"));
	                // Assuming your photo is stored as a BLOB in the database
	                Blob blob = rs.getBlob("photo");
	                byte[] photoBytes = blob.getBytes(1, (int) blob.length());
	                prod.setPhoto(photoBytes); // Assuming your Product class has a setter for photo
	                pd.add(prod);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return pd;
	}

	
	public void incert() {
		try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO demodb.product (name, image, tag) VALUES (?, ?, ?)")) {
		    pstmt.setString(1, "shoe1.jpg");
		    File photoFile = new File(System.getProperty("user.home") + "/Desktop/shoe1.jpg");
		    FileInputStream fis = new FileInputStream(photoFile);
		    pstmt.setBinaryStream(2, fis, (int) photoFile.length());
		    pstmt.setString(3, "shoes");
		    pstmt.executeUpdate();
		    fis.close();
		} catch (SQLException | IOException e) {
		    e.printStackTrace();
		}


	}
}
