package dao;

import java.util.List;

import model.Employee;
import model.Reimbursement;

public interface EmployeeDao {
	
	public Employee employeeLogin(String email, String password);
	
	public List<Reimbursement> getReimbursements(int employeeId);
	
	public List<Reimbursement> resolvedReimbursements(int employeeId);
	
	public List<Reimbursement> pendingReimbursements(int employeeId);
	
	public boolean submitReimbursement(int employeeId, double amount, String remDesc);
	
	public Employee viewCredentials(int employeeId);
	
	public Employee updateCredentials(int employeeId);

}
