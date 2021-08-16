package com.revature.bank.dao;

import java.sql.CallableStatement;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.bank.exception.InvalidAmountException;
import com.revature.bank.model.Customer;
import com.revature.bank.model.Employee;
import com.revature.bank.util.DBConnection;

public class BankDAOImplementation implements BankDAO {
	Connection connection = DBConnection.getDBConnection();

	private final String ADD_EMPLOYEE_QUERY = "insert into hr.employee values(?,?,?,?,?,?)";
	private final String UPDATE_EMPLOYEE_QUERY = "update hr.employee set employeeName = ?, employeeMobileNumber=? , employeeMailId = ? , employeePassword = ?,employeeBalance=? where employeeId = ?";
	private final String FIND_ALL_EMPLOYEES = "select * from hr.employee";

	private final String DELETE_EMPLOYEE_QUERY = "delete from hr.employee where employeeId = ?";
	private final String FIND_EMPLOYEE_BY_ID_QUERY = "select * from hr.employee  where employeeId = ?";
	private final String FIND_EMPLOYEE_BY_NAME_QUERY = "select * from hr.employee  where employeeName = ?";

	private final String ADD_CUSTOMER_QUERY = "insert into hr.customers values(?,?,?,?,?,?)";
	private final String UPDATE_CUSTOMER_QUERY = "update hr.customers set customerName = ?, customerMobileNumber=? , customerMailId = ? , customerPassword = ?,customerBalance=? where customerId = ?";
	private final String FIND_ALL_CUSTOMERS = "select * from hr.customers";

	private final String DELETE_CUSTOMER_QUERY = "delete from hr.customers where customerId = ?";
	private final String FIND_CUSTOMER_BY_ID_QUERY = "select * from hr.customers  where customerId = ?";
	private final String FIND_CUSTOMER_BY_NAME_QUERY = "select * from hr.customers  where customerName = ?";

	private final String CHECK_BALANCE_FOR_EMPLOYEE = "select employeeBalance from hr.employee  where employeeId = ?";
	private final String CHECK_BALANCE_FOR_CUSTOMER = "select customerBalance from hr.customers  where customerId = ?";

	// withdrawal functions
	private final String CUSTOMER_WITHDRAWAL = "update hr.customers set customerBalance= customerBalance-? where customerId=?";
	private final String EMPLOYEE_WITHDRAWAL = "update hr.employee set employeeBalance= employeeBalance-? where employeeId=?";

	// deposit functions
	private final String CUSTOMER_DEPOSIT = "update hr.customers set customerBalance= customerBalance+? where customerId=?";
	private final String EMPLOYEE_DEPOSIT = "update hr.employee set employeeBalance= employeeBalance+? where employeeId=?";

	Scanner s = new Scanner(System.in);

	// Add customer section

	@Override
	public boolean addCustomer(Customer customer) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(ADD_CUSTOMER_QUERY);
			statement.setInt(1, customer.getCustomerId());
			statement.setString(2, customer.getCustomerName());
			statement.setInt(3, customer.getCustomerMobileNumber());
			statement.setString(4, customer.getCustomerMailId());
			statement.setString(5, customer.getCustomerPassword());
			statement.setInt(6, customer.getCustomerBalance());

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

	// update customer section
	@Override
	public boolean updateCustomer(Customer customer) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_QUERY);
			statement.setInt(6, customer.getCustomerId());
			statement.setString(1, customer.getCustomerName());
			statement.setInt(2, customer.getCustomerMobileNumber());
			statement.setString(3, customer.getCustomerMailId());
			statement.setString(4, customer.getCustomerPassword());
			statement.setInt(5, customer.getCustomerBalance());

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

	// delete customer section

	@Override
	public boolean deleteCustomer(int customerId) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_QUERY);
			statement.setInt(1, customerId);

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

	// find customer by customerId

	@Override
	public Customer getCustomerById(int customerId) {
		List<Customer> customers = getAllCustomers();

		Customer customer = new Customer();
		try {
			PreparedStatement statement = connection.prepareStatement(FIND_CUSTOMER_BY_ID_QUERY);
			statement.setInt(1, customer.getCustomerId());
			statement.execute();
			for (Customer c : customers) {
				if (c.getCustomerId() == customerId) {

					customer.setCustomerId(c.getCustomerId());
					customer.setCustomerName(c.getCustomerName());
					customer.setCustomerMobileNumber(c.getCustomerMobileNumber());
					customer.setCustomerMailId(c.getCustomerMailId());
					customer.setCustomerPassword(c.getCustomerPassword());
					customer.setCustomerBalance(c.getCustomerBalance());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return customer;
	}

	// find customer by customer Name

	@Override
	public Customer getCustomerByName(String customerName) {
		List<Customer> customers = getAllCustomers();

		Customer customer = new Customer();
		try {
			PreparedStatement statement = connection.prepareStatement(FIND_CUSTOMER_BY_NAME_QUERY);
			statement.setString(1, customer.getCustomerName());
			statement.execute();
			for (Customer c : customers) {
				if (c.getCustomerName().equals(customerName)) {

					customer.setCustomerId(c.getCustomerId());
					customer.setCustomerName(c.getCustomerName());
					customer.setCustomerMobileNumber(c.getCustomerMobileNumber());
					customer.setCustomerMailId(c.getCustomerMailId());
					customer.setCustomerPassword(c.getCustomerPassword());
					customer.setCustomerBalance(c.getCustomerBalance());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return customer;
	}

	// view list of all customers details
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(FIND_ALL_CUSTOMERS);
			while (res.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(res.getInt(1));
				customer.setCustomerName(res.getString(2));
				customer.setCustomerMobileNumber(res.getInt(3));
				customer.setCustomerMailId(res.getString(4));
				customer.setCustomerPassword(res.getString(5));
				customer.setCustomerBalance(res.getInt(6));

				customers.add(customer);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return customers;
	}
	// Add employee section

	@Override
	public boolean addEmployee(Employee employee) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(ADD_EMPLOYEE_QUERY);
			statement.setInt(1, employee.getEmployeeId());
			statement.setString(2, employee.getEmployeeName());
			statement.setInt(3, employee.getEmployeeMobileNumber());
			statement.setString(4, employee.getEmployeeMailId());
			statement.setString(5, employee.getEmployeePassword());
			statement.setInt(6, employee.getEmployeeBalance());

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

	// update employee section
	@Override
	public boolean updateEmployee(Employee employee) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY);
			statement.setInt(6, employee.getEmployeeId());
			statement.setString(1, employee.getEmployeeName());
			statement.setInt(2, employee.getEmployeeMobileNumber());
			statement.setString(3, employee.getEmployeeMailId());
			statement.setString(4, employee.getEmployeePassword());
			statement.setInt(5, employee.getEmployeeBalance());

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}
	// delete employee section

	@Override
	public boolean deleteEmployee(int employeeId) {
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_QUERY);
			statement.setInt(1, employeeId);

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

	// find employee using employeeId
	@Override
	public Employee getEmployeeById(int employeeId) {
		List<Employee> employees = getAllEmployees();

		Employee employee = new Employee();
		try {
			PreparedStatement statement = connection.prepareStatement(FIND_EMPLOYEE_BY_ID_QUERY);
			statement.setInt(1, employee.getEmployeeId());
			statement.execute();
			for (Employee e : employees) {
				if (e.getEmployeeId() == employeeId) {

					employee.setEmployeeId(e.getEmployeeId());
					employee.setEmployeeName(e.getEmployeeName());
					employee.setEmployeeMobileNumber(e.getEmployeeMobileNumber());
					employee.setEmployeeMailId(e.getEmployeeMailId());
					employee.setEmployeePassword(e.getEmployeePassword());
					employee.setEmployeeBalance(e.getEmployeeBalance());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return employee;
	}
	// find employee using employeeName

	@Override
	public Employee getEmployeeByName(String employeeName) {
		List<Employee> employees = getAllEmployees();

		Employee employee = new Employee();
		try {
			PreparedStatement statement = connection.prepareStatement(FIND_EMPLOYEE_BY_NAME_QUERY);
			statement.setString(1, employee.getEmployeeName());
			statement.execute();
			for (Employee e : employees) {
				if (e.getEmployeeName().equals(employeeName)) {

					employee.setEmployeeId(e.getEmployeeId());
					employee.setEmployeeName(e.getEmployeeName());
					employee.setEmployeeMobileNumber(e.getEmployeeMobileNumber());
					employee.setEmployeeMailId(e.getEmployeeMailId());
					employee.setEmployeePassword(e.getEmployeePassword());
					employee.setEmployeeBalance(e.getEmployeeBalance());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return employee;
	}

	// view all employee details
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(FIND_ALL_EMPLOYEES);
			while (res.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(res.getInt(1));
				employee.setEmployeeName(res.getString(2));
				employee.setEmployeeMobileNumber(res.getInt(3));
				employee.setEmployeeMailId(res.getString(4));
				employee.setEmployeePassword(res.getString(5));
				employee.setEmployeeBalance(res.getInt(6));

				employees.add(employee);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return employees;
	}

	// transfer amount from one employee to another employee and check creditor
	// balance and debitor balance

	@Override
	public void transferAmountForEmployee(int senderId, int receiverId, int amount) {

		int debitorBalance = 0, creditorBalance = 0;
		try {
			CallableStatement statement = connection.prepareCall("call hr.transferAmountForEmployee(?,?,?,?,?)");
			statement.setInt(1, senderId);
			statement.setInt(2, receiverId);
			statement.setInt(3, amount);
			statement.registerOutParameter(4, Types.INTEGER);
			statement.setInt(4, debitorBalance);
			statement.registerOutParameter(5, Types.INTEGER);
			statement.setInt(5, creditorBalance);
			statement.execute();
			debitorBalance = statement.getInt(4);
			creditorBalance = statement.getInt(5);
			System.out.println("Amount transferred successfully!");
			System.out.println("DebitorBalance: " + debitorBalance);
			System.out.println("CreditorBalance: " + creditorBalance);

		} catch (SQLException e) {

		}

	}

	// transfer amount from one customer to another customer and check creditor
	// balance and debitor balance
	@Override
	public void transferAmountForCustomer(int senderId, int receiverId, int amount) {

		int debitorBalance = 0, creditorBalance = 0;
		try {
			CallableStatement statement = connection.prepareCall("call hr.transferAmountForCustomer(?,?,?,?,?)");
			statement.setInt(1, senderId);
			statement.setInt(2, receiverId);
			statement.setInt(3, amount);
			statement.registerOutParameter(4, Types.INTEGER);
			statement.setInt(4, debitorBalance);
			statement.registerOutParameter(5, Types.INTEGER);
			statement.setInt(5, creditorBalance);
			statement.execute();
			debitorBalance = statement.getInt(4);
			creditorBalance = statement.getInt(5);
			System.out.println("Amount transferred successfully!");
			System.out.println("DebitorBalance: " + debitorBalance);
			System.out.println("CreditorBalance: " + creditorBalance);
		} catch (SQLException e) {

		}

	}
	// check balance of customer

	@Override
	public int checkBalanceOfCustomer(int customerId) {
		List<Customer> customers = getAllCustomers();

		int balance = 0;
		try {

			PreparedStatement statement = connection.prepareStatement(CHECK_BALANCE_FOR_CUSTOMER);
			statement.setInt(1, customerId);
			statement.execute();
			for (Customer c : customers) {
				if (c.getCustomerId() == customerId) {

					balance = c.getCustomerBalance();
					// System.out.println(balance);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return balance;
	}

	// check balance of employee
	@Override
	public int checkBalanceOfEmployee(int employeeId) {
		List<Employee> employees = getAllEmployees();

		int balance = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(CHECK_BALANCE_FOR_EMPLOYEE);
			statement.setInt(1, employeeId);
			statement.execute();
			for (Employee e : employees) {
				if (e.getEmployeeId() == employeeId) {

					balance = e.getEmployeeBalance();
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return balance;
	}

	// withdrawal of customer
	@Override
	public int withdrawalOfCustomer(int customerId, int amount) throws InvalidAmountException {
		List<Customer> customers = getAllCustomers();
		if (amount < 0)
			throw new InvalidAmountException("you cannot withdraw negative amount");
		int balance = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(CUSTOMER_WITHDRAWAL);
			statement.setInt(1, amount);
			statement.setInt(2, customerId);
			statement.execute();
			for (Customer c : customers) {
				if (c.getCustomerId() == customerId) {

					balance = c.getCustomerBalance() - amount;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return balance;
	}

	// deposit of customer
	@Override
	public int depositOfCustomer(int customerId, int amount) throws InvalidAmountException {
		if (amount < 0)
			throw new InvalidAmountException("you cannot deposit negative amount");
		List<Customer> customers = getAllCustomers();

		int balance = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(CUSTOMER_DEPOSIT);
			statement.setInt(1, amount);
			statement.setInt(2, customerId);
			statement.execute();
			for (Customer c : customers) {
				if (c.getCustomerId() == customerId) {

					balance = c.getCustomerBalance() + amount;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return balance;
	}

	// withdrawal of employee
	@Override
	public int withdrawalOfEmployee(int employeeId, int amount) throws InvalidAmountException {
		if (amount < 0)
			throw new InvalidAmountException("you cannot withdraw negative amount");
		List<Employee> employees = getAllEmployees();

		int balance = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(EMPLOYEE_WITHDRAWAL);
			statement.setInt(1, amount);
			statement.setInt(2, employeeId);
			statement.execute();
			statement.close();
			for (Employee e : employees) {
				if (e.getEmployeeId() == employeeId) {

					balance = e.getEmployeeBalance() - amount;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return balance;
	}

	// deposit of customer
	@Override
	public int depositOfEmployee(int employeeId, int amount) throws InvalidAmountException {
		if (amount < 0)
			throw new InvalidAmountException("you cannot deposit negative amount");

		List<Employee> employees = getAllEmployees();

		int balance = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(EMPLOYEE_DEPOSIT);
			statement.setInt(1, amount);
			statement.setInt(2, employeeId);
			statement.execute();
			for (Employee e : employees) {
				if (e.getEmployeeId() == employeeId) {

					balance = e.getEmployeeBalance() + amount;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return balance;
	}

	@Override

	// Employee Approval
	public void employeeApproval(Customer customer) {

		if (isCustomerExists(customer.getCustomerId())) {

			System.out.println("your account is denied by Employee!");
		} else {
			addCustomer(customer);
			System.out.println("your account is approved by Employee!");
			System.out.println("Your account is created successfully!");
		}

	}

	public boolean isEmployeeExists(int employeeId) {

		boolean result = false;

		try {
			PreparedStatement stat = connection.prepareStatement(FIND_EMPLOYEE_BY_ID_QUERY);
			stat.setInt(1, employeeId);
			ResultSet res = stat.executeQuery();

			if (res.next()) {
				result = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public boolean isCustomerExists(int customerId) {

		boolean result = false;

		try {
			PreparedStatement stat = connection.prepareStatement(FIND_CUSTOMER_BY_ID_QUERY);
			stat.setInt(1, customerId);
			ResultSet res = stat.executeQuery();

			if (res.next()) {
				result = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

}
