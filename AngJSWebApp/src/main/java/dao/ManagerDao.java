package dao;

import java.util.List;

import model.Employee;
import model.Manager;
import model.Reimbursement;

public interface ManagerDao {

	public Manager managerLogin(String email, String password);
	
	public List<Employee> getAllEmployees();
	
	public List<Reimbursement> getAllReimbursements();
	
	public List<Reimbursement> pendingReimbursements();
	
	public List<Reimbursement> resolvedReimbursements();
	
	public Reimbursement resolveReimbursement(int remId);
	
}
