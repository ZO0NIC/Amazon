package Amazon.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

import Amazon.User.User;

public class UserData {
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
	
	public void insert(User user) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format("insert into user(id ,name, gender, age, address) values(%d,\"%s\",\"%s\",\"%d\",\"%s\") ",user.getId(), user.getName(), user.getGender(), user.getAge(), user.getAddress()));
			System.out.println("transaction successfull");
			

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	public void update(User user, int id)
	{
		try {
			Statement stmt = con.createStatement();
			String name = user.getName();
			int age = user.getAge();
			String gender = user.getGender();
			String address = user.getAddress();
			stmt.executeUpdate(String.format("update user set name='"+name+"', gender='"+gender+"', age='"+age+"', address='"+address+"' WHERE id=' "+id+" '"));
			//String updateQuery = "UPDATE user SET name='"+name+"', gender='"+gender+"', age='"+age+"', address='"+address+"' WHERE id=' "+id+" '";
            
            //PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setInt(1, user.getAge());
//            preparedStatement.setString(2, user.getGender());
//            preparedStatement.setString(3, user.getAddress());
//            preparedStatement.setInt(4, id);
            
            //stmt.executeUpdate(updateQuery);
			System.out.println("update successfull");

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	public  List<User> showUser() {
		List<User> userList = new ArrayList<User>();
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("select * from demodb.user");
			while(rs.next())
			{
				 User user = new User();
		         user.setId(rs.getInt(1));
		         user.setName(rs.getString(2).trim());
		         user.setGender(rs.getString(3).trim());
		         user.setAge(rs.getInt(4));
		         user.setAddress(rs.getString(5).trim());
		         System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getInt(4) + " " + rs.getString(5));
		         userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
		
	}
	
	
	public User showUser(int id) {
		User user = new User();
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("select * from demodb.user where id='"+id+" '");
			while (rs.next()) {
				user.setId(rs.getInt(1));
	            user.setName(rs.getString(2).trim());
	            user.setGender(rs.getString(3).trim());
	            user.setAge(rs.getInt(4));
	            user.setAddress(rs.getString(5).trim());  
	            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4)+" "+rs.getString(5));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}