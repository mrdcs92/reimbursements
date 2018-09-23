package model;

import java.util.List;

public class Employee {
	
	private int userId;
	private String username;
	private String password;
	private String email;
	private List<Reimbursement> reimbursements;
	
	public Employee(int userId, String username, String password, String email, List<Reimbursement> reimbursements) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.reimbursements = reimbursements;
	}

	public Employee() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}
	
}
