package practice;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import model.Employee;
import model.Reimbursement;
import service.EmployeeService;
//import util.JdbcConnection;

public class SqlTest {

	public static void main(String[] args) {
		
		System.out.println("testing");
		System.out.println(getReimbursements(1));

	}
	
	public static List<Reimbursement> getReimbursements(int employeeId) {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		List<Reimbursement> resolvedRems = EmployeeService.resolvedReimbursements(employeeId);
		List<Reimbursement> pendingRems = EmployeeService.pendingReimbursements(employeeId);
		
		for (Reimbursement rem : resolvedRems) {
			reimbursements.add(rem);
		}
		for (Reimbursement rem: pendingRems) {
			reimbursements.add(rem);
		}
		
		return reimbursements;
	}
	
}
