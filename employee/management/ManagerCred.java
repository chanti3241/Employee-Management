package com.employee.management;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManagerCred {

	private static Connection conn;
	static PreparedStatement pstmt;
	static String name;
	static int id;
	static Scanner sc = new Scanner(System.in);
	static ResultSet res;
	private static String dept;
	private static Statement stmt;

	public static void login(Connection con) {
		try {
			conn = con;
			String sql = "SELECT * FROM Employee_dtl WHERE emp_email=? AND pwd=? and emp_desg='manager'";
			pstmt = conn.prepareStatement(sql);
			System.out.println("Enter the email:");
			String email = sc.next();
			System.out.println("Enter the password:");
			String pwd = sc.next();
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);

			res = pstmt.executeQuery();
			if (res.next()) {
				id = res.getInt("emp_id");
				name = res.getString("emp_name");
				dept = res.getString("emp_dept");
				managerOptions();
			} else {
				System.out.println("Login Failed");
				login(con);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void managerOptions() {
		System.out.println("hello " + name);
		System.out.println("Select a option:\n" 
				+ "1. View manager details\n" 
				+ "2. View all Employee in Development\n"
				+ "3. Update Phone\n"
				+ "4. Update Email\n" 
				+ "5. Update the Password\n" 
				+ "6.View all the tasks\n" 
				+ "7.Update the tasks\n" 
				+ "8.Quit");

		sc = new Scanner(System.in);
		int input = sc.nextInt();

		switch (input) {
		case 1:
			ViewManagerDetails();
			managerOptions();
		case 2:
			ViewEmployeeInDevelopment();
			managerOptions();
		case 3:
			UpdatePhoneNum();
			managerOptions();
		case 4:
			UpdateEmail();
			managerOptions();
		case 5:
			UpdatePassword();
			managerOptions();
		case 6:
			viewTasks();
			managerOptions();

		case 7:
			updateTask();
			managerOptions();
		default:
			break;
		}
	}

	private static void updateTask() {
		try {
			String sql1 = "Select * from employee_dtl where emp_dept=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, dept);
			res = pstmt.executeQuery();
			System.out.println("Dear Manager, please find the list of employees " + "in the department " + dept);
			while (res.next() == true) {
				System.out.println("id: " + res.getInt(1) + " \t name: " + res.getString(2));
			}
			System.out.println("Dear Manager, please find the list of tasks " + "allocated to the department " + dept);
			String sql = "select * from tasks where allocated_dept=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept);
			res = pstmt.executeQuery();
			while (res.next() == true) {
				System.out.println("task id = " + res.getInt(1));
				System.out.println("task title = " + res.getString(2));
				System.out.println("task description = " + res.getString(3));
				System.out.println("Allocated to = " + res.getInt(6));
			}
			System.out.println("Do you wish to allocated task to any employees?(yes/no)");
			String val = sc.next();
			if (val.equalsIgnoreCase("yes")) {
				System.out.println("Enter the task id: ");
				int tsk_id = sc.nextInt();
				System.out.println("Enter the employee id: ");
				int emp_id = sc.nextInt();
				String sql2 = "update tasks set assigned_to=? where id=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, emp_id);
				pstmt.setInt(2, tsk_id);
				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("Task is updated");
				} else {
					System.out.println("Task not updated");
				}
			} else {
				System.out.println("Thank You.");
			}
			System.out.println("\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void viewTasks() {
		try {
			String sql = "select * from tasks where id=?";
			pstmt = conn.prepareStatement(sql);
			System.out.println("Enter the Id of task: ");
			int id = sc.nextInt();
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			while (res.next() == true) {
				System.out.println("task id = " + res.getInt(1));
				System.out.println("task title = " + res.getString(2));
				System.out.println("task description = " + res.getString(3));
				System.out.println("task alloted to = " + res.getInt(6));
				
			}
			System.out.println("\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void ViewManagerDetails() {
		try {
			String sql = "Select * from employee_dtl where emp_desg='manager'";
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			while (res.next() == true) {
				System.out.println("manager id= " + res.getInt(1));
				System.out.println("manage name= " + res.getString(2));
				System.out.println("maganer dept= " + res.getString(5));
				System.out.println("manager email= " + res.getString(9));
				System.out.println("-------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void ViewEmployeeInDevelopment() {
		try {
			String sql = "select * from employee_dtl where emp_dept='Development'";
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			while (res.next() == true) {
				System.out.println("employee id=" + res.getInt(1));
				System.out.println("employee name=" + res.getString(2));
				System.out.println("employee dept=" + res.getString(5));
				System.out.println("employee email=" + res.getString(9));
				System.out.println("---------------");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void UpdatePhoneNum() {
		try {
			String sql = "Update employee_dtl set emp_phone=? where emp_desg='manager'";
			pstmt = conn.prepareStatement(sql);
			System.out.println("Enter the updated phone number");
			Long phn = sc.nextLong();
			pstmt.setLong(1, phn);
			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Manager phone number updated");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void UpdateEmail() {
		try {
			String sql = "Update employee_dtl set emp_email=? where emp_desg='manager'";
			pstmt = conn.prepareStatement(sql);
			System.out.println("Enter the updated email");
			String email = sc.next();
			pstmt.setString(1, email);
			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Manager email updated");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void UpdatePassword() {
		try {
			String sql = "Update employee_dtl set pwd=? where emp_desg='manager'";
			pstmt = conn.prepareStatement(sql);
			System.out.println("Enter the updated password");
			String pwd = sc.next();
			pstmt.setString(1, pwd);
			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Manager password is updated");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}