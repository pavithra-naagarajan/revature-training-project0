package com.revature.bank.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bank.exception.InvalidAmountException;
import com.revature.bank.model.Customer;
import com.revature.bank.model.Employee;

public class BankDAOImplementationTest {
	BankDAO bankDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bankDAO = new BankDAOImplementation();
	}

	@After
	public void tearDown() throws Exception {
		bankDAO = null;
	}

	@Test
	public void testAddCustomer() {
		int customerIdToTest = -999;
		List<Customer> originalCustomers1 = bankDAO.getAllCustomers();
		bankDAO.addCustomer(new Customer(customerIdToTest, "demoName", 111111111, "demoName@com", "demo123", 000));
		List<Customer> originalCustomers2 = bankDAO.getAllCustomers();

		assertEquals(originalCustomers2.size(), originalCustomers1.size() + 1);

		bankDAO.deleteCustomer(customerIdToTest);
	}

	@Test
	public void testDeleteCustomer() {
		int customerIdToTest = -111;
		List<Customer> originalCustomers1 = bankDAO.getAllCustomers();
		bankDAO.addCustomer(new Customer(customerIdToTest, "demoName", 111111111, "demoName@com", "demo123", 000));
		List<Customer> originalCustomers2 = bankDAO.getAllCustomers();

		assertEquals(originalCustomers2.size(), originalCustomers1.size() + 1);

		bankDAO.deleteCustomer(customerIdToTest);
	}

	@Test
	public void testGetCustomerById() {
		// Testing getting a single
		int customerIdToTest = -999;
		Customer addedCustomer = new Customer(customerIdToTest, "demoName", 111111111, "demoName@com", "demo123", 000);
		bankDAO.addCustomer(addedCustomer);
		bankDAO.getCustomerById(-999);
		bankDAO.deleteCustomer(customerIdToTest);
	}

	@Test
	public void testGetCustomerByName() {
		int customerNameToTest = -999;
		Customer addedCustomer = new Customer(customerNameToTest, "demoName", 111111111, "demoName@com", "demo123",
				000);
		bankDAO.addCustomer(addedCustomer);
		bankDAO.getCustomerById(-999);
		bankDAO.deleteCustomer(customerNameToTest);
	}

	@Test
	public void testGetAllCustomers() {
		int customerIdToTest = -99;
		List<Customer> originalCustomers1 = bankDAO.getAllCustomers();
		bankDAO.addCustomer(new Customer(customerIdToTest, "demoName", 111111111, "demoName@com", "demo123", 000));
		List<Customer> originalCustomers2 = bankDAO.getAllCustomers();

		assertEquals(originalCustomers2.size(), originalCustomers1.size() + 1);

		bankDAO.deleteCustomer(customerIdToTest);
	}

	@Test
	public void testAddEmployee() {
		int employeeIdToTest = -9;
		List<Employee> originalEmployee1 = bankDAO.getAllEmployees();
		bankDAO.addEmployee(new Employee(employeeIdToTest, "demoName", 111111111, "demoName@com", "demo123", -100));
		List<Employee> originalEmployee2 = bankDAO.getAllEmployees();

		assertEquals(originalEmployee2.size(), originalEmployee1.size() + 1);

		bankDAO.deleteEmployee(employeeIdToTest);
	}

	@Test
	public void testDeleteEmployee() {
		int employeeIdToTest = -111;
		List<Employee> employees1 = bankDAO.getAllEmployees();
		bankDAO.addEmployee(new Employee(employeeIdToTest, "demoName", 111111111, "demoName@com", "demo123", 000));
		List<Employee> employees2 = bankDAO.getAllEmployees();

		assertEquals(employees2.size(), employees1.size() + 1);

		bankDAO.deleteEmployee(employeeIdToTest);
	}

	@Test
	public void testGetEmployeeById() {
		int employeeIdToTest = -999;
		Employee addedEmployee = new Employee(employeeIdToTest, "demoName", 111111111, "demoName@com", "demo123", 000);
		bankDAO.addEmployee(addedEmployee);
		bankDAO.getEmployeeById(-999);
		bankDAO.deleteEmployee(employeeIdToTest);
	}

	@Test
	public void testGetEmployeeByName() {
		int employeeNameToTest = -999;
		Employee addedEmployee = new Employee(employeeNameToTest, "demoName", 111111111, "demoName@com", "demo123",
				000);
		bankDAO.addEmployee(addedEmployee);
		bankDAO.getEmployeeById(-999);
		bankDAO.deleteEmployee(employeeNameToTest);
	}

	@Test
	public void testGetAllEmployees() {
		int employeeIdToTest = -999;
		List<Employee> originalEmployee11 = bankDAO.getAllEmployees();
		bankDAO.addEmployee(new Employee(employeeIdToTest, "demoName", 111111111, "demoName@com", "demo123", -100));
		List<Employee> originalEmployee22 = bankDAO.getAllEmployees();

		assertEquals(originalEmployee22.size(), originalEmployee11.size() + 1);

		bankDAO.deleteEmployee(employeeIdToTest);
	}


	@Test
	public void testCheckBalanceOfCustomer() {
		int customerIdToTest = -999, actualValue = 500, balance = 500;
		List<Customer> originalCustomer1 = bankDAO.getAllCustomers();
		bankDAO.addCustomer(new Customer(customerIdToTest, "demoName", 111111111, "demoName@com", "demo123", balance));
		int res = bankDAO.checkBalanceOfCustomer(customerIdToTest);
		assertEquals(actualValue, res);
		bankDAO.deleteCustomer(customerIdToTest);
	}

	@Test
	public void testCheckBalanceOfEmployee() {
		int employeeIdToTest = -999, actualValue = 500, balance = 500;
		List<Employee> originalEmployee1 = bankDAO.getAllEmployees();
		bankDAO.addEmployee(new Employee(employeeIdToTest, "demoName", 111111111, "demoName@com", "demo123", balance));
		int res = bankDAO.checkBalanceOfEmployee(employeeIdToTest);
		assertEquals(actualValue, res);
		bankDAO.deleteEmployee(employeeIdToTest);
	}

	@Test
	public void testWithdrawalOfCustomer() throws InvalidAmountException {
		int customerIdToTest = -999, actualValue = 500, amount = 100;
		List<Customer> originalCustomer1 = bankDAO.getAllCustomers();
		bankDAO.addCustomer(new Customer(customerIdToTest, "demoName", 111111111, "demoName@com", "demo123", 600));
		int res = bankDAO.withdrawalOfCustomer(customerIdToTest, amount);
		assertEquals(actualValue, res);
		bankDAO.deleteCustomer(customerIdToTest);
	}

	@Test
	public void testDepositOfCustomer() throws InvalidAmountException {
		int customerIdToTest = -999, actualValue = 700, amount = 100;
		List<Customer> originalCustomer1 = bankDAO.getAllCustomers();
		bankDAO.addCustomer(new Customer(customerIdToTest, "demoName", 111111111, "demoName@com", "demo123", 600));
		int res;

		res = bankDAO.depositOfCustomer(customerIdToTest, amount);
		assertEquals(actualValue, res);
		bankDAO.deleteCustomer(customerIdToTest);

	}

	@Test
	public void testWithdrawalOfEmployee() throws InvalidAmountException {
		int employeeIdToTest = -888, actualValue = 500, amount = 100;
		List<Employee> originalEmployee1 = bankDAO.getAllEmployees();
		bankDAO.addEmployee(new Employee(employeeIdToTest, "demoName", 111111111, "demoName@com", "demo123", 600));
		int res;

		res = bankDAO.withdrawalOfEmployee(employeeIdToTest, amount);
		assertEquals(actualValue, res);
		bankDAO.deleteEmployee(employeeIdToTest);

	}

	@Test
	public void testDepositOfEmployee() throws InvalidAmountException {
		int employeeIdToTest = -999, actualValue = 700, amount = 100;
		List<Employee> originalEmployee1 = bankDAO.getAllEmployees();
		bankDAO.addEmployee(new Employee(employeeIdToTest, "demoName", 111111111, "demoName@com", "demo123", 600));
		int res = bankDAO.depositOfEmployee(employeeIdToTest, amount);
		assertEquals(actualValue, res);
		bankDAO.deleteEmployee(employeeIdToTest);
	}

}
