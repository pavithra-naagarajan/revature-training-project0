package com.revature.bank.model;

import java.util.Date;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bank.dao.BankDAO;
import com.revature.bank.dao.BankDAOImplementation;
import com.revature.bank.exception.InvalidAmountException;
import com.revature.bank.exception.InvalidInputException;

public class BankApp {
	Scanner s = new Scanner(System.in);
	boolean result = false;
	// Logger to record and view all transactions
	private static Logger logger = Logger.getLogger("ProductApp");
	BankDAO bankDAO = new BankDAOImplementation();
	Customer customer = null;
	Employee employee = null;
	int finalBalance;

	// personal page for employee

	public void personalPageForEmployee()  {
		
		while (true) {

			System.out.println("\nMenu List Options:");
			System.out.println("**********************************************************************");
			System.out.println("1.Delete Customer ");
			System.out.println("2.Find Customer By Id");
			System.out.println("3.Find Customer by Name");
			System.out.println("4.view All Customers");
			System.out.println("5.View All employees");
			System.out.println("6.Find Employee by employeeId");
			System.out.println("7.Find Employee by employeeName");
			System.out.println("8.withdrawal");
			System.out.println("9.Deposit");
			System.out.println("10.check Balance");
			System.out.println("11.Transfer Amount");
			System.out.println("12.Edit your details-update");
			System.out.println("13.delete Employee");
			System.out.println("-1.E X I T ");
			System.out.println("**********************************************************************");
			System.out.println("Enter your choice : ");
			int choice = s.nextInt();
			

			switch (choice) {

			case 1:
				logger.info("User starting delete customer section" + new Date());
				System.out.println("Wecome to Delete Customer section\nEnter customerId to be deleted:");
				System.out.println("**********************************************************************");

				int customerId = s.nextInt();
				
				if(bankDAO.isCustomerExists(customerId)) {
					result = bankDAO.deleteCustomer(customerId);
					}
					else
					{
						logger.error("employee not exists");
					}
				if (result)
					logger.info("#customer withcustomer Id : " + customerId + " , deleted successfully");
				else
					logger.error("customer with customer Id : " + customerId + " , not deleted successfully");

				break;
			case 2:
				logger.info("User starting Find customer by Id section" + new Date());
				System.out.println("Wecome to Find customer by Id section\nEnter CustomerId ##");
				System.out.println("**********************************************************************");
				int customerId1 = s.nextInt();
				if(bankDAO.isCustomerExists(customerId1)) {
					customer = bankDAO.getCustomerById(customerId1);
					System.out.println(customer);
				}
				else {
					System.out.println("Customer is not exist");
				}
				if (customer != null)
					logger.info("Customer with " + customerId1 + " found successfully");
				else
					logger.error("Customer with " + customerId1 + " not found successfully");
				break;
			case 3:
				logger.info("User starting Find customer by Name section" + new Date());
				System.out.println("Wecome to Find customer by Name section\nEnter CustomerName ##");
				System.out.println("**********************************************************************");
				s.nextLine();
				String customerName1 = s.nextLine();
				customer = bankDAO.getCustomerByName(customerName1);
				System.out.println(customer);
				if (customer != null)
					logger.info("Customer with " + customerName1 + " found successfully");
				else
					logger.error("Customer with " + customerName1 + " not found successfully");
				break;
			case 4:
				logger.info("User starting view all customers section" + new Date());
				System.out.println("Wecome to view all customers section ##");
				System.out.println("**********************************************************************");
				List<Customer> customers = bankDAO.getAllCustomers();
				System.out.println("###Entire list of customers:");
				for (Customer customer : customers) {
					System.out.println(customer);
				}
				break;

			case 5:
				logger.info("User starting view all employees section" + new Date());
				System.out.println("Wecome to view all employees section ##");
				System.out.println("**********************************************************************");
				List<Employee> employees = bankDAO.getAllEmployees();
				System.out.println("###Entire list of employees:");
				for (Employee employee : employees) {
					System.out.println(employee);
				}
				break;

			case 6:
				logger.info("User starting Find employee by Id section" + new Date());
				System.out.println("Wecome to Find employee by Id section\nEnter  employee Id ##");
				System.out.println("**********************************************************************");
				int employeeId1 = s.nextInt();
				if(bankDAO.isEmployeeExists(employeeId1)) {
				employee = bankDAO.getEmployeeById(employeeId1);
				System.out.println(employee);
				}else
				{
					System.out.println("employee not exists!");
				}
				
				if (employee != null)
					logger.info("employee with " + employeeId1 + " found successfully");
				else
					logger.error("employee with " + employeeId1 + " not found successfully");
				break;
			case 7:
				logger.info("User starting Find employee by Name section" + new Date());
				System.out.println("Wecome to Find employee by Name section\nEnter  employee Name ##");
				s.nextLine();
				String employeeName1 = s.nextLine();
				employee = bankDAO.getEmployeeByName(employeeName1);
				System.out.println(employee);
				if (employee != null)
					logger.info("employee with " + employeeName1 + " found successfully");
				else
					logger.error("employee with " + employeeName1 + " not found successfully");
				break;
			case 8:
				logger.info("Employee starting withdrawal section " + new Date());
				System.out.println("Enter employee Details to withdraw");
				System.out.println("enter employeeId");
				int employeeId = s.nextInt();
				System.out.println("enter the amount to withdraw from your account");

				int balance = 0;

				try {
					int amount = s.nextInt();
					if (bankDAO.isEmployeeExists(employeeId)) {
						balance = bankDAO.withdrawalOfEmployee(employeeId, amount);
						logger.info("Withdrawn successfully!");
					} else
						logger.error("employee not exists!");
				} catch (InvalidAmountException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 9:
				logger.info("Employee starting deposit section" + new Date());
				System.out.println("Enter employee Details to deposit");
				System.out.println("enter employeeId");
				int employeeId14 = s.nextInt();
				System.out.println("enter the amount to deposit in your account");

				int balance1 = 0;
				try {
					int amount1 = s.nextInt();
					if (bankDAO.isEmployeeExists(employeeId14)) {
						balance1 = bankDAO.depositOfEmployee(employeeId14, amount1);
						logger.info("deposited succesfully!");
					} else
						logger.error("employee not exists!");
				} catch (InvalidAmountException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 10:
				System.out.println("Wecome to check Balance section ##");
				System.out.println("1.To check balance of customer-press 1");
				System.out.println("2.To check balance of employee-press 2");
				int c = s.nextInt();
				switch (c) {
				case 1:
					System.out.println("Enter Customer Id:");
					int customerId11 = s.nextInt();
					int customerFinalBalance = bankDAO.checkBalanceOfCustomer(customerId11);
					System.out.println("Balance of customer with " + customerId11 + " is " + customerFinalBalance);
					break;
				case 2:
					System.out.println("Enter employeeId:");
					int employeeId11 = s.nextInt();
					finalBalance = bankDAO.checkBalanceOfEmployee(employeeId11);
					System.out.println("Balance of customer with " + employeeId11 + " is " + finalBalance);
					break;
				default:
					System.out.println("Invalid input");
					break;
				}

				break;
			case 11:
				System.out.println("Wecome to Transfer Amount section");
				System.out.println("please enter required details to transfer amount");
				System.out.println("Enter sender Id:");
				int senderId = s.nextInt();
				System.out.println("Enter receiver Id:");
				int receiverId = s.nextInt();
				System.out.println("Enter amount to be transfered:");
				int amount11 = s.nextInt();
				if (amount11 > 0)
					bankDAO.transferAmountForEmployee(senderId, receiverId, amount11);
				else
					logger.error("Negative amount cannot be transfered");
				break;
			case 12:
				System.out.println("Wecome to Edit details section");
				System.out.println("**********************************************************************");
				System.out.println("please enter all your details to be updated ");
				System.out.println("Enter employeeId:");
				int employeeId11 = s.nextInt();
				s.nextLine();
				System.out.println("Enter employeeName:");
				String employeeName = s.nextLine();
				System.out.println("Enter employeeMobileNumber:");
				int employeeMobileNumber = s.nextInt();
				s.nextLine();
				System.out.println("Enter employeeMailId:");
				String employeeMailId = s.nextLine();
				System.out.println("Enter employeePassword:");
				String employeePassword = s.nextLine();
				System.out.println("Enter employeeInitialBalance to start a new account:");
				int employeeBalance = s.nextInt();
				s.nextLine();
				employee = new Employee(employeeId11, employeeName, employeeMobileNumber, employeeMailId,
						employeePassword, employeeBalance);
				bankDAO.updateEmployee(employee);
				System.out.println("Your details is updated successfully!");
				System.out.println("**********************************************************************");
				break;
			case 13:
				logger.info("User starting delete employee section" + new Date());
				System.out.println("Wecome to Delete Customer section\nEnter customerId to be deleted:");

				int employeeId111 = s.nextInt();
				if(bankDAO.isEmployeeExists(employeeId111)) {
				result = bankDAO.deleteEmployee(employeeId111);
				}
				else
				{
					logger.error("employee not exists");
				}
				if (result)
					logger.info("#employee with employee Id : " + employeeId111 + " , deleted successfully");
				else
					logger.error("employee with employee Id : " + employeeId111 + " , not deleted successfully");

				break;
			case -1:
				BankingApplication bank = new BankingApplication();
				bank.startMenu();
				break;
			default:
				System.out.println("Invalid input");
				break;

			}

		}
	}

	// personal page for customer
	public void personalPageForCustomer() {
		while (true) {

			System.out.println("\nM E N U");

			System.out.println("1.withdrawal");
			System.out.println("2.Deposit");
			System.out.println("3.check Balance");
			System.out.println("4.Transfer Amount");
			System.out.println("5.Edit Customer -update details");

			System.out.println("-1.E X I T ");

			System.out.println("Enter your choice : ");
			int choice = s.nextInt();
			switch (choice) {
			case 1:
				logger.info("customer starting withdrawal section");
				System.out.println("Enter customer Details to withdraw");
				System.out.println("enter customerId");
				int customerId = s.nextInt();
				System.out.println("enter the amount to withdraw from your account");
				int balance = 0;

				try {
					int amount = s.nextInt();
					if (bankDAO.isCustomerExists(customerId)) {
						balance = bankDAO.withdrawalOfCustomer(customerId, amount);
						logger.info("Withdrawn successfully!");
					} else
						logger.error("customer not exists!");

				} catch (InvalidAmountException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 2:
				logger.info("customer starting deposit section");
				System.out.println("Enter customer Details to deposit");
				System.out.println("enter customerId");
				int customerId13 = s.nextInt();
				System.out.println("enter the amount to deposit in your account");

				int balance12 = 0;
				try {
					int amount1 = s.nextInt();
					if (bankDAO.isCustomerExists(customerId13)) {
						balance12 = bankDAO.depositOfCustomer(customerId13, amount1);
						logger.info("deposited successfully!");
					} else
						logger.error("customer not exists");
				} catch (InvalidAmountException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter Customer Id:");
				int customerId11 = s.nextInt();
				int customerFinalBalance = bankDAO.checkBalanceOfCustomer(customerId11);
				System.out.println("Balance of customer with " + customerId11 + " is " + customerFinalBalance);
				break;

			case 4:
				System.out.println("Wecome to Transfer Amount section");
				System.out.println("please enter required details to transfer amount");
				System.out.println("Enter sender Id:");
				int senderId = s.nextInt();
				System.out.println("Enter receiver Id:");
				int receiverId = s.nextInt();
				System.out.println("Enter amount to be transfered:");
				int amount1 = s.nextInt();
				if (amount1 > 0)
					bankDAO.transferAmountForCustomer(senderId, receiverId, amount1);
				else
					logger.error("Negative amount cannot be transfered");

				break;

			case 5:
				System.out.println("Wecome to Edit details section");
				System.out.println("**********************************************************************");
				System.out.println("please enter all your details to be updated ");
				System.out.println("Enter customerId:");
				int customerId1 = s.nextInt();
				s.nextLine();
				System.out.println("Enter customerName:");
				String customerName = s.nextLine();
				System.out.println("Enter customerMobileNumber:");
				int customerMobileNumber = s.nextInt();
				s.nextLine();
				System.out.println("Enter customerMailId:");
				String customerMailId = s.nextLine();
				System.out.println("Enter customerPassword:");
				String customerPassword = s.nextLine();
				System.out.println("Enter customerInitialBalance to start a new account:");
				int customerBalance = s.nextInt();
				s.nextLine();
				customer = new Customer(customerId1, customerName, customerMobileNumber, customerMailId,
						customerPassword, customerBalance);
				bankDAO.updateCustomer(customer);
				System.out.println("your details get updated successfully!");
				System.out.println("**********************************************************************");
			case -1:
				BankingApplication bank = new BankingApplication();
				bank.startMenu();
				break;

			default:
				System.out.println("Invalid input");
				break;

			}

		}
	}
}