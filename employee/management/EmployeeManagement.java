package com.employee.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class EmployeeManagement {
	private static Connection con;
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		System.out.println("\t \t \t \t Welcome to Employee Management System");
		System.out.println("Please Select The User Type: \n" 
						+ "1. Manager\n" 
						+ "2. Admin\n" 
						+ "3. Emoployee\n"
						+ "4. Quit");
		int input = sc.nextInt();
		switch (input) {
		
		case 1:
			managerOps();
			main(null);
		
		case 2:
			adminOps();
			main(null);

		case 3:
			employeeOps();
			main(null);

		case 4:
			System.out.println("Terminated");
			System.exit(0);
		default:
			System.out.println("Invalid option. Please try again.");
		}
	}

	static void managerOps() {
		try {
			System.out.println("Dear Manager Please select the Option:\n" 
					+ "1. Login\n" 
					+ "2. Quit");
			
			con = DriverManager.getConnection(Credentials.url, Credentials.user, Credentials.pwd);
			int x = sc.nextInt();

			switch (x) {

			case 1:
				ManagerCred.login(con);

			case 2:
				main(null);
				System.exit(0);

			default:
				System.out.println("Invalid option. Please try again.");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void employeeOps() {
		try {
			System.out.println("\t \t \t \t Dear Employee Please select the options:\n" 
					+ "1. Login\n"
					+ "2. Quit");

			con = DriverManager.getConnection(Credentials.url, Credentials.user, Credentials.pwd);
			int n = sc.nextInt();
			switch (n) {
			case 1:
				EmployeeOps.login(con);

			case 2:
				EmployeeManagement.main(null);

			default:
				System.out.println("Invalid option. Please try again.");
				employeeOps();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void adminOps() {
		try {
			System.out.println(
					"\t \t \t \t Dear Admin Please select the option:\n" 
							+ "1. Register\n" 
							+ "2. Login\n" 
							+ "3. Quit");

			con = DriverManager.getConnection(Credentials.url, Credentials.user, Credentials.pwd);
			int n = sc.nextInt();

			switch (n) {
			case 1:
				AdminOperations.register(con);

			case 2:
				AdminOperations.login(con);

			case 3:
				EmployeeManagement.main(null);

			default:
				System.out.println("Invalid option");
				adminOps();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
