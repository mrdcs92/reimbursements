package service;

import dao.EmployeeDaoImpl;
import model.Employee;

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
}
