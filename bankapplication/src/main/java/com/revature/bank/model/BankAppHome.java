package com.revature.bank.model;

import com.revature.bank.exception.InvalidAmountException;

public class BankAppHome {
	public static void main(String[] args) {
		System.out.println("               Pavithra Banking Application");
		System.out.println("**********************************************************************");
		BankingApplication bankObject = new BankingApplication();
		bankObject.startMenu();

	}
}
