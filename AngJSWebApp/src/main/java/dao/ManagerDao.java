package dao;

import java.util.List;

import model.Employee;
import model.Manager;

public interface ManagerDao {

	public Manager managerLogin(String email, String password);
	
	public List<Employee> getAllEmployees();
	
}
