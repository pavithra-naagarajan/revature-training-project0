package com.revature.bank.model;

public class Customer {
	private int customerId;
	private String customerName;
	private int customerMobileNumber;
	private String customerMailId;
	private String customerPassword;
	private int customerBalance;
	
	
	public Customer() {
		 
	}
	public Customer(int customerId, String customerName, int customerMobileNumber, String customerMailId,
			String customerPassword, int customerBalance) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerMobileNumber = customerMobileNumber;
		this.customerMailId = customerMailId;
		this.customerPassword = customerPassword;
		this.customerBalance = customerBalance;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerMobileNumber() {
		return customerMobileNumber;
	}
	public void setCustomerMobileNumber(int customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}
	public String getCustomerMailId() {
		return customerMailId;
	}
	public void setCustomerMailId(String customerMailId) {
		this.customerMailId = customerMailId;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public int getCustomerBalance() {
		return customerBalance;
	}
	public void setCustomerBalance(int customerBalance) {
		this.customerBalance = customerBalance;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerMobileNumber="
				+ customerMobileNumber + ", customerMailId=" + customerMailId + ", customerPassword=" + customerPassword
				+ ", customerBalance=" + customerBalance + "]";
	}

	

}
