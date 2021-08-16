package com.revature.bank.model;

public class Employee {
	private int employeeId;
	private String employeeName;
	private int employeeMobileNumber;
	private String employeeMailId;
	private String employeePassword;
	private int employeeBalance;
	
	public Employee() {
		
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId; 
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getEmployeeMobileNumber() {
		return employeeMobileNumber;
	}
	public void setEmployeeMobileNumber(int employeeMobileNumber) {
		this.employeeMobileNumber = employeeMobileNumber;
	}
	public String getEmployeeMailId() {
		return employeeMailId;
	}
	public void setEmployeeMailId(String employeeMailId) {
		this.employeeMailId = employeeMailId;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public int getEmployeeBalance() {
		return employeeBalance;
	}
	public void setEmployeeBalance(int employeeBalance) {
		this.employeeBalance = employeeBalance;
	}
	public Employee(int employeeId, String employeeName, int employeeMobileNumber, String employeeMailId,
			String employeePassword, int employeeBalance) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeMobileNumber = employeeMobileNumber;
		this.employeeMailId = employeeMailId;
		this.employeePassword = employeePassword;
		this.employeeBalance = employeeBalance;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeMobileNumber="
				+ employeeMobileNumber + ", employeeMailId=" + employeeMailId + ", employeePassword=" + employeePassword
				+ ", employeeBalance=" + employeeBalance + "]";
	}

}
