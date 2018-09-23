package dao;

import java.util.List;

import model.Employee;
import model.Reimbursement;

public interface EmployeeDao {
	
	public Employee employeeLogin(String email, String password);
	
	public List<Reimbursement> getReimbursements(int employeeId);
	
	public List<Reimbursement> resolvedReimbursements(int employeeId);
	
	public List<Reimbursement> pendingReimbursements(int employeeId);

}
