package Amazon.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

import Amazon.Admin.Admin;
import Amazon.User.User;

public class Login implements Admin{
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
	
    private String username ;
    private String password ;
    
    
    public Login() {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean validateLogin(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
    public String userVerification(String un) {
        String pass = null;
        try (PreparedStatement pstmt = con.prepareStatement("SELECT password FROM demodb.userlogin WHERE username = ?")) {
            pstmt.setString(1, un);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pass = rs.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pass;
    }
    
    public void newUser(String userName, String password) {
        try {
            String query = "INSERT INTO userlogin (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("Transaction successful");
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

	@Override
	public String adminVerification(String un) {
		String pass = null;
        try (PreparedStatement pstmt = con.prepareStatement("SELECT password FROM demodb.adminlogin WHERE username = ?")) {
            pstmt.setString(1, un);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pass = rs.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pass;
	}


}

