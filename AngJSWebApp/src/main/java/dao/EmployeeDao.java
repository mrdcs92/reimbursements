package dao;

import model.Employee;

public interface EmployeeDao {
	
	public Employee employeeLogin(String email, String password);

}
