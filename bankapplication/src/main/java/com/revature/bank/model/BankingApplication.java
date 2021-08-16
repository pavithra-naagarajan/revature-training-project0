package com.revature.bank.model;

import java.sql.Connection;

import java.util.List;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.bank.dao.BankDAO;
import com.revature.bank.dao.BankDAOImplementation;
import com.revature.bank.exception.InvalidAmountException;
import com.revature.bank.exception.InvalidInputException;

public class BankingApplication {

	static Scanner s = new Scanner(System.in);
	BankDAO bankDAO = new BankDAOImplementation();

	Employee employee;
	Customer customer;

	// start main Menu-Home page
	public void startMenu() {

		while (true) {

			System.out.println("Home page:");
			System.out.println("\n1.Login\n2.Create Account\n-1.Exit");
			System.out.println("Enter your choice:");

			int choice = s.nextInt();
			s.nextLine();

			switch (choice) {
			case 1:
				login();
				break;
			case 2:
				createNewAccount();
				break;

			case -1:
				System.out.println("Thanks for using Banking Application!");
				System.out.println("**********************************************************************");
				System.exit(0);
				break;
			default:
				System.out.println("Incorrect input!........");

				break;

			}

		}

	}

	// create a new account section
	public void createNewAccount() {
		while (true) {
			System.out.println("**********************************************************************");
			System.out.println("Welcome to Account Creation section:");
			System.out.println("Please Enter the details to open an account:");
			System.out.println("Enter your account type:\nE for Employee type account:\nC for customer type account: ");
			System.out.println("**********************************************************************");

			String accountType = s.nextLine();
			boolean v = true;

			switch (accountType) {
			case "E":
				createAccountForEmployee();
				break;
			case "C":
				createAccountForCustomer();

				break;
			case "Z":
				v = false;
				break;
			default:
				System.out.println("Incorrect type! Please enter the appropriate type........");
				System.out.println("**********************************************************************");
				break;
			}
		}
	}

	// create account for customer
	public void createAccountForCustomer() {

		System.out.println("Welcome to Customer Type account creation:");
		System.out.println("**********************************************************************");
		System.out.println("Enter customerId:");
		int customerId = s.nextInt();
		s.nextLine();
		System.out.println("Enter customerName:");
		String customerName = s.nextLine();
		System.out.println("Enter customerMobileNumber:");
		int customerMobileNumber = s.nextInt();
		s.nextLine();
		System.out.println("Enter customerMailId:");
		String customerMailId = s.nextLine();
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(customerMailId);
		if (matcher.matches()) {
			// System.out.println("success");
			System.out.println("Enter customerPassword:");
			String customerPassword = s.nextLine();
			System.out.println("Enter customerPassword again to confirm:");
			String customerPasswordAgain = s.nextLine();
			System.out.println("Enter customerInitialBalance to start a new account:");
			int customerBalance = s.nextInt();
			s.nextLine();
			customer = new Customer(customerId, customerName, customerMobileNumber, customerMailId, customerPassword,
					customerBalance);
			bankDAO.employeeApproval(customer);

			System.out.println("**********************************************************************");
			login();
		} else {
			System.out.println("enter appropriate emailId!");
			startMenu();
		}

	}

	// create account for employee
	private void createAccountForEmployee() {

		System.out.println("Welcome to Employee Type account creation:");
		System.out.println("**********************************************************************");
		System.out.println("Enter employeeId:");
		int employeeId = s.nextInt();
		s.nextLine();
		System.out.println("Enter employeeName:");
		String employeeName = s.nextLine();
		System.out.println("Enter employeeMobileNumber:");
		int employeeMobileNumber = s.nextInt();
		s.nextLine();
		System.out.println("Enter employeeMailId:");
		String employeeMailId = s.nextLine();
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(employeeMailId);
		if (matcher.matches()) {
			// System.out.println("success");
			System.out.println("Enter employeePassword:");
			String employeePassword = s.nextLine();
			System.out.println("Enter employeePassword again to confirm:");
			String employeePasswordAgain = s.nextLine();
			System.out.println("Enter employeeInitialBalance to start a new account:");
			int employeeBalance = s.nextInt();
			s.nextLine();
			employee = new Employee(employeeId, employeeName, employeeMobileNumber, employeeMailId, employeePassword,
					employeeBalance);
			System.out.println("Your account is created successfully!");
			System.out.println("**********************************************************************");
			bankDAO.addEmployee(employee);
			login();
		} else {
			System.out.println("enter appropriate emailId!");
			startMenu();
		}

	}

//login section
	private void login() {
		while (true) {
			System.out.println("Welcome to Login Section:");

			System.out.println("Please Enter the details to login:");
			System.out.println("Enter your account type:\nE for Employee type account:\nC for customer type account: ");
			System.out.println("**********************************************************************");
			boolean v = true;
			String accountType = s.nextLine();

			switch (accountType) {
			case "E":
				loginForEmployee();
				break;
			case "C":
				loginForCustomer();
				break;
			case "Z":
				v = false;
				break;
			default:
				System.out.println("Incorrect type! Please enter the appropriate type........");
				break;

			}
		}
	}

//login for customer
	public void loginForCustomer() {
		System.out.println("Welcome to Customer Login Section:");
		System.out.println("Please Enter the details to login:");
		System.out.println("**********************************************************************");
		System.out.println("Enter customerId:");
		int customerId = s.nextInt();
		s.nextLine();
		System.out.println("Enter customerPassword:");
		String customerPassword = s.nextLine();
		int flag = 0;
		List<Customer> customers = bankDAO.getAllCustomers();
		for (Customer customer : customers) {
			if ((customer.getCustomerId() == customerId) && (customer.getCustomerPassword().equals(customerPassword))) {
				System.out.println("your Login is finished successfully");
				flag = 1;
			}
		}
		if (flag == 1) {
			System.out.println("Here your personal page! Welcome");
			BankApp bankApp = new BankApp();
			bankApp.personalPageForCustomer();
		}
		if (flag == 0) {
			System.out.println("you login details are not matched! try again");

		}
	}
	// login for employee

	public void loginForEmployee() {
		System.out.println("Welcome to Employee Login Section:");
		System.out.println("Please Enter the details to login:");
		System.out.println("**********************************************************************");
		System.out.println("Enter employeeId:");
		int employeeId = s.nextInt();
		s.nextLine();
		System.out.println("Enter employeePassword:");
		String employeePassword = s.nextLine();
		int flag = 0;

		List<Employee> employees = bankDAO.getAllEmployees();
		for (Employee employee : employees) {
			if ((employee.getEmployeeId() == employeeId) && (employee.getEmployeePassword().equals(employeePassword))) {
				System.out.println("your Login is finished successfully");
				flag = 1;
			}
		}
		if (flag == 1) {
			System.out.println("Here your personal page");
			BankApp bankApp = new BankApp();

			bankApp.personalPageForEmployee();

		}
		if (flag == 0) {
			System.out.println("you login details are not matched! try again");
			System.out.println("**********************************************************************");

		}
	}

}
