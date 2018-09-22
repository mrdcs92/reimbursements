package service;

import java.util.List;

import dao.ManagerDaoImpl;
import model.Employee;
import model.Manager;

public class ManagerService {

	private static ManagerService managerService;
	
	public static ManagerService getManagerService() {
		if (managerService == null) {
			managerService = new ManagerService();
		}
		return managerService;
	}
	
	public static Manager managerLogin(String email, String password) {
		return ManagerDaoImpl.getInstance().managerLogin(email, password);
	}
	
	public static List<Employee> getAllEmployees(){
		return ManagerDaoImpl.getInstance().getAllEmployees();
	}
	
}
