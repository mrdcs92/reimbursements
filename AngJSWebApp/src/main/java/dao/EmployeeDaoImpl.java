package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Reimbursement;
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
			
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			
			while (rs.next()) {
				employee = new Employee(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("email"), reimbursements);
			}
			ps.close();
			
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return employee;
	}
	
	public List<Reimbursement> getReimbursements(int employeeId) {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		List<Reimbursement> resolvedRems = resolvedReimbursements(employeeId);
		List<Reimbursement> pendingRems = pendingReimbursements(employeeId);
		
		for (Reimbursement rem : resolvedRems) {
			reimbursements.add(rem);
		}
		for (Reimbursement rem: pendingRems) {
			reimbursements.add(rem);
		}
		
		return reimbursements;
	}
	
	public List<Reimbursement> resolvedReimbursements(int employeeId){
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try {
			Connection conn = JdbcConnection.getConnection();
			
			String sql = "select " + 
					"rem_id, rem_date, status, res_date, emp_id, e.username as emp_name, man_id, m.username as man_name, amount, rem_desc, receipt " + 
					"from " + 
					"employees e join reimbursements rem " + 
					"on e.user_id = rem.emp_id " + 
					"join managers m on m.user_id = rem.man_id " + 
					"where rem.emp_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				reimbursements.add(new Reimbursement(
						rs.getInt("rem_id"),
						rs.getDate("rem_date"),
						rs.getString("status"),
						rs.getDate("res_date"),
						rs.getInt("emp_id"),
						rs.getString("emp_name"),
						rs.getInt("man_id"),
						rs.getString("man_name"),
						rs.getInt("amount"),
						rs.getString("rem_desc"),
						rs.getBlob("receipt")
						));
			}
			ps.close();
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return reimbursements;
	}
	
	public List<Reimbursement> pendingReimbursements(int employeeId) {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try {
			Connection conn = JdbcConnection.getConnection();
			
			String sql = "select " + 
					"rem_id, rem_date, status, res_date, emp_id, e.username as emp_name, amount, rem_desc, receipt " + 
					"from " + 
					"employees e join reimbursements rem " + 
					"on e.user_id = rem.emp_id " + 
					"where rem.emp_id = ? and status = 'pending'";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				reimbursements.add(new Reimbursement(
						rs.getInt("rem_id"),
						rs.getDate("rem_date"),
						rs.getString("status"),
						rs.getDate("res_date"),
						rs.getInt("emp_id"),
						rs.getString("emp_name"),
						0,
						null,
						rs.getInt("amount"),
						rs.getString("rem_desc"),
						rs.getBlob("receipt")
						));
			}
			ps.close();
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return reimbursements;
		
	}

}
