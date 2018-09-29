package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Manager;
import model.Reimbursement;
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
				manager = new Manager(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"),
						rs.getString("email"));
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
				// employees.add(new Employee(rs.getInt("user_id"), rs.getString("username"),
				// rs.getString("password"), rs.getString("email")));
			}
			ps.close();
			return employees;

		} catch (SQLException e) {
			e.getMessage();
		}

		return null;
	}

	public List<Reimbursement> getAllReimbursements() {

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		List<Reimbursement> resolvedRems = resolvedReimbursements();
		List<Reimbursement> pendingRems = pendingReimbursements();
		
		for (Reimbursement rem : resolvedRems) {
			reimbursements.add(rem);
		}
		for (Reimbursement rem : pendingRems) {
			reimbursements.add(rem);
		}
		
		return reimbursements;
	}

	public List<Reimbursement> pendingReimbursements() {

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try {
			Connection conn = JdbcConnection.getConnection();

			String sql = "select "
					+ "rem_id, rem_date, status, res_date, emp_id, e.username as emp_name, amount, rem_desc, receipt "
					+ "from " + "employees e join reimbursements rem " + "on e.user_id = rem.emp_id " + "where "
					+ "status = 'pending' " + "order by rem_date desc";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt("rem_id"), rs.getDate("rem_date"),
						rs.getString("status"), rs.getDate("res_date"), rs.getInt("emp_id"), rs.getString("emp_name"),
						0, null, rs.getDouble("amount"), rs.getString("rem_desc"), rs.getBlob("receipt")));
			}
			ps.close();

		} catch (SQLException e) {
			e.getMessage();
		}
		return reimbursements;
	}

	public List<Reimbursement> resolvedReimbursements() {

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try {
			Connection conn = JdbcConnection.getConnection();

			String sql = "select "
					+ "rem_id, rem_date, status, res_date, emp_id, e.username as emp_name, man_id, m.username as man_name, amount, rem_desc, receipt "
					+ "from " + "employees e join reimbursements rem " + "on e.user_id = rem.emp_id "
					+ "join managers m " + "on m.user_id = rem.man_id " + "order by rem_date desc";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt("rem_id"), rs.getDate("rem_date"),
						rs.getString("status"), rs.getDate("res_date"), rs.getInt("emp_id"), rs.getString("emp_name"),
						rs.getInt("man_id"), rs.getString("man_name"), rs.getDouble("amount"), rs.getString("rem_desc"),
						rs.getBlob("receipt")));
			}
			ps.close();

		} catch (SQLException e) {
			e.getMessage();
		}
		return reimbursements;
	}

	public Reimbursement resolveReimbursement(int remId, String resStatus, int manId) {

		Reimbursement reimbursement = new Reimbursement();
		
		try {
			Connection conn = JdbcConnection.getConnection();
			String sql = "update reimbursements " + 
					"set status = ?, res_date = (TO_TIMESTAMP(LOCALTIMESTAMP, 'DD-MON-RR HH.MI.SSXFF PM')), man_id = ? " + 
					"where rem_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, resStatus);
			ps.setInt(2, manId);
			ps.setInt(3, remId);
			ps.execute();
			ps.close();

			reimbursement = getReimbursement(remId);
			
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return reimbursement;
	}
	
	public Reimbursement getReimbursement(int remId) {
		Reimbursement reimbursement = new Reimbursement();
		
		try {
			Connection conn = JdbcConnection.getConnection();
			String sql = "select "
					+ "status, res_date, man_id, m.username as man_name "
					+ "from "
					+ "reimbursements rem join managers m "
					+ "on rem.man_id = m.user_id "
					+ "where rem_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, remId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				reimbursement.setStatus(rs.getString("status"));
				reimbursement.setResDate(rs.getDate("res_date"));
				reimbursement.setManId(rs.getInt("man_id"));
				reimbursement.setManName(rs.getString("man_name"));
			}
			ps.close();
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return reimbursement;
	}

}
