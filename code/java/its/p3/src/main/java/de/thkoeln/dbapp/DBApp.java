/**
 * @author sereni Dongmo
 */

package de.thkoeln.dbapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBApp {
	
	public static void main(String[] args)throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please Enter your Name");
		//String name = in.readLine();
		new EmployeeDAO().getEmployeeByName("Sereni' OR '1'= '1");

	}

}


class DatabaseConnection{
	
	private Connection conn;
	
	public DatabaseConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Properties connectionProps = new Properties();
		connectionProps.put("user", "hr");
		connectionProps.put("password", "7Z6zJuiQh");
		conn = DriverManager.getConnection(
				"jdbc:"+ "mysql"+ "://"+ "localhost"+ ":" + "3306"+"/employees", connectionProps);
	}
	
	public Connection getConnection(){
		return conn;
	}
	
} // end Class DatabaseConnection

class EmployeeDAO{
	
	public void getEmployeeByName(String name) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Statement stmt = new DatabaseConnection().getConnection().createStatement();
		String sql = "SELECT emp_no, first_name, last_name"
				  + " FROM employees where last_name = '"+name+"'";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			System.out.println(rs.getString(1)+", "+rs.getString(2)+" "+rs.getString(3));
		}
					
	}
	
	
} // end EmployeeDAO
