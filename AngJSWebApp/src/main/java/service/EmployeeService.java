package service;

import java.util.List;

import dao.EmployeeDaoImpl;
import model.Employee;
import model.Reimbursement;

public class EmployeeService {

	private static EmployeeService employeeService;
	
	public static EmployeeService getEmployeeService() {
		if (employeeService == null) {
			employeeService = new EmployeeService();
		}
		return employeeService;
	}
	
	public static Employee employeeLogin(String email, String password) {
		return EmployeeDaoImpl.getInstance().employeeLogin(email, password);
	}
	
	public static List<Reimbursement> getReimbursements(int employeeId) {
		return EmployeeDaoImpl.getInstance().getReimbursements(employeeId);
	}
	
	public static List<Reimbursement> resolvedReimbursements(int employeeId){
		return EmployeeDaoImpl.getInstance().resolvedReimbursements(employeeId);
	}
	
	public static List<Reimbursement> pendingReimbursements(int employeeId){
		return EmployeeDaoImpl.getInstance().pendingReimbursements(employeeId);
	}
	
	public static boolean submitReimbursement(int employeeId, double amount, String remDesc) {
		return EmployeeDaoImpl.getInstance().submitReimbursement(employeeId, amount, remDesc);
	}
}
