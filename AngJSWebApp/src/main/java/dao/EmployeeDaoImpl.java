package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import util.JdbcConnection;

public class EmployeeDaoImpl implements EmployeeDao {

	private static EmployeeDaoImpl employeeDao;
	
	public static EmployeeDaoImpl getInstance() {
		if (employeeDao == null) {
			employeeDao = new EmployeeDaoImpl();
		}
		return employeeDao;
	}
	
	public Employee employeeLogin(String email, String password) {
		
		Employee employee = null;
		
		try {
			Connection conn = JdbcConnection.getConnection();
			
			String sql = "select user_id, username, password, email from employees where email = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				employee = new Employee(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("email"));
			}
			ps.close();
			
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return employee;
	}

}
