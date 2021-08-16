package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.exception.InvalidAmountException;
import com.revature.bank.model.Customer;
import com.revature.bank.model.Employee;

//all the methods which are implemented in banking application
public interface BankDAO {
	public boolean addCustomer(Customer customer);

	public boolean updateCustomer(Customer customer);

	public boolean deleteCustomer(int customerId);

	public Customer getCustomerById(int customerId);

	public Customer getCustomerByName(String customerName);

	public List<Customer> getAllCustomers();

	public boolean addEmployee(Employee employee);

	public boolean updateEmployee(Employee employee);

	public boolean deleteEmployee(int employeeId);

	public Employee getEmployeeById(int employeeId);

	public Employee getEmployeeByName(String employeeName);

	public List<Employee> getAllEmployees();

	public void transferAmountForEmployee(int senderId, int receiverId, int amount);

	public void transferAmountForCustomer(int senderId, int receiverId, int amount);

	public int checkBalanceOfCustomer(int customerId);

	public int checkBalanceOfEmployee(int employeeId);

	public int withdrawalOfCustomer(int customerId, int amount) throws InvalidAmountException;

	public int depositOfCustomer(int customerId, int amount) throws InvalidAmountException;

	public int withdrawalOfEmployee(int employeeId, int amount) throws InvalidAmountException;

	public int depositOfEmployee(int employeeId, int amount) throws InvalidAmountException;

	public void employeeApproval(Customer customer);

	public boolean isEmployeeExists(int employeeId);

	public boolean isCustomerExists(int customerId);

}
