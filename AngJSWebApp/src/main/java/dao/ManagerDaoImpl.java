package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Manager;
import util.JdbcConnection;

public class ManagerDaoImpl implements ManagerDao {
	
	private static ManagerDaoImpl managerDao;
	
	public static ManagerDaoImpl getInstance() {
		if (managerDao == null) {
			managerDao = new ManagerDaoImpl();
		}
		return managerDao;
	}
	
	public Manager managerLogin(String email, String password) {
		
		Manager manager = null;
		
		try {
			Connection conn = JdbcConnection.getConnection();
			
			String sql = "select user_id, username, password, email from managers where email = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				manager = new Manager(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("email"));
			}
			ps.close();
			
		} catch (SQLException e) {
			e.getMessage();
		}	
		return manager;
	}
	
	public List<Employee> getAllEmployees() {
		
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			Connection conn = JdbcConnection.getConnection();
			
			String sql = "select user_id, username, password, email from employees";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				employees.add(new Employee(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("email")));
			}
			ps.close();
			return employees;
			
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return null;
	}

}
