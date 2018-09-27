package serv;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.Employee;
import model.Manager;
import model.Reimbursement;
import service.EmployeeService;
import service.ManagerService;

public class RequestHelper {

	public static void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();

		if (uri.equals("/AngJSWebApp/getall.do")) {
			List<Employee> employees = ManagerService.getAllEmployees();
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(employees, new TypeToken<List<Employee>>() {
			}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}

		if (uri.equals("/AngJSWebApp/tryLogin.do")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			boolean isManager = Boolean.valueOf(request.getParameter("isManager"));
			Gson gson = new Gson();
			String element;

			if (isManager) {
				Manager manager = ManagerService.managerLogin(email, password);
				Type manType = new TypeToken<Manager>() {
				}.getType();
				element = gson.toJson(manager, manType);
			} else {
				Employee employee = EmployeeService.employeeLogin(email, password);
				Type empType = new TypeToken<Employee>() {
				}.getType();
				element = gson.toJson(employee, empType);
			}

			response.setContentType("application/json");
			response.getWriter().print(element);
		}

		if (uri.equals("/AngJSWebApp/getReimbursements.do")) {
			int employeeId = Integer.valueOf(request.getParameter("employeeId"));

			GsonBuilder gsonBuilder = new GsonBuilder();  
			gsonBuilder.serializeNulls();  
			Gson gson = gsonBuilder.create();

			List<Reimbursement> reimbursements = EmployeeService.getReimbursements(employeeId);
			Type remType = new TypeToken<List<Reimbursement>>() {}.getType();
			JsonElement element = gson.toJsonTree(reimbursements, remType);

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
		
		if (uri.equals("/AngJSWebApp/submitReimbursement.do")) {
			int employeeId = Integer.valueOf(request.getParameter("employeeId"));
			double amount = Double.parseDouble(request.getParameter("amount"));
			String remDesc = request.getParameter("remDesc");
			
			boolean result = EmployeeService.submitReimbursement(employeeId, amount, remDesc);
			
			JsonObject json = new JsonObject();
			json.addProperty("result", result);
			
			response.setContentType("application/json");
			response.getWriter().print(json);
		}
		
		if (uri.equals("/AngJSWebApp/getCredentials.do")) {
			int employeeId = Integer.valueOf(request.getParameter("employeeId"));
			
			Gson gson = new Gson();
			Type empType = new TypeToken<Employee>() {
			}.getType();
			
			Employee employee = EmployeeService.getCredentials(employeeId);

			String element = gson.toJson(employee, empType);
			response.setContentType("application/json");
			response.getWriter().print(element);
		}
		
		if (uri.equals("/AngJSWebApp/updateCredentials.do")) {
			int employeeId = Integer.valueOf(request.getParameter("userId"));
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			boolean result = EmployeeService.updateCredentials(employeeId, username, password, email);
			
			JsonObject json = new JsonObject();
			json.addProperty("result", result);
			
			response.setContentType("application/json");
			response.getWriter().print(json);
		}

	}
}
