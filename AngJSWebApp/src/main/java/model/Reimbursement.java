package model;

import java.sql.Blob;
import java.util.Date;

public class Reimbursement {

	private int remId;
	private Date remDate;
	private String status;
	private Date resDate;
	private int empId;
	private String empName;
	private int manId;
	private String manName;
	private int amount;
	private String remDesc;
	private Blob receipt;
	
	public Reimbursement(int remId, Date remDate, String status, Date resDate, int empId, String empName, int manId,
			String manName, int amount, String remDesc, Blob receipt) {
		super();
		this.remId = remId;
		this.remDate = remDate;
		this.status = status;
		this.resDate = resDate;
		this.empId = empId;
		this.empName = empName;
		this.manId = manId;
		this.manName = manName;
		this.amount = amount;
		this.remDesc = remDesc;
		this.receipt = receipt;
	}
	
	public Reimbursement() {
		
	}

	public int getRemId() {
		return remId;
	}

	public void setRemId(int remId) {
		this.remId = remId;
	}

	public Date getRemDate() {
		return remDate;
	}

	public void setRemDate(Date remDate) {
		this.remDate = remDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getManId() {
		return manId;
	}

	public void setManId(int manId) {
		this.manId = manId;
	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRemDesc() {
		return remDesc;
	}

	public void setRemDesc(String remDesc) {
		this.remDesc = remDesc;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}
	
}
