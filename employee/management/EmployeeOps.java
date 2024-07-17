package com.employee.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeOps {
	
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	private static String name;
	static int id;
	static Scanner sc = new Scanner(System.in);
	public static void login(Connection con){
		try {
			conn = con;
			String sql = "Select * from employee_dtl where emp_email=? and pwd=?";
			pstmt = conn.prepareStatement(sql);
			System.out.println("Enter the email:");
			String email = sc.next();
			System.out.println("Enter the password:");
			String pwd = sc.next();
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			
			res = pstmt.executeQuery();
			while(res.next()==true){
				id = res.getInt("emp_id");
				name = res.getString("emp_name");
				employeeOption();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void employeeOption() {
		try {
			System.out.println("\t\tWelcome "+name);
			System.out.println("\n Select the operation you wish to perform:\n"
					+"1. View Your Data\n"
					+"2. Update Your Email\n"
					+"3. Update Your Phone\n"
					+"4. Change Password\n"
					+"5. View Your Tasks\n"
					+"6. Exit");
			
			int input = sc.nextInt();
			
			switch (input) {
			case 1:
				viewData();
				employeeOption();
				
			case 2:
				updateEmail();
				employeeOption();
				
			case 3:
				updatePhoneNum();
				employeeOption();
				
			case 4:
				changePwd();
				employeeOption();
				
			case 5:
				ViewTasks();
				employeeOption();
				
			case 6:
				EmployeeManagement.employeeOps();
				
			default:
				System.out.println("invalid option");
				employeeOption();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void viewData() {
		try {
			conn.createStatement();
			res = pstmt.executeQuery();
			while(res.next()==true) {
				System.out.println("ID: "+res.getInt("emp_id"));
				System.out.println("Name: "+res.getString("emp_name"));
				System.out.println("Experience: "+res.getInt("emp_exp")+" years");
				System.out.println("Company Name: "+res.getString("emp_comp"));
				System.out.println("Department: "+res.getString("emp_dept"));
				System.out.println("Employee Salary: "+res.getInt("emp_salary"));
				System.out.println("Employee Phone number: "+res.getLong("emp_phone"));
				System.out.println("Employee Email: "+res.getString("emp_email"));
				System.out.println("------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void updateEmail() {
		System.out.println(name);
		try {
			String sql = "update Employee_dtl set emp_email= ? where emp_id=?";
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("Enter the new mail-id of the employee: ");
			String mail=sc.next();
			pstmt.setString(1, mail);
			pstmt.setInt(2, id);
			long x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Email is updated successfully");

			}else {
				System.out.println("Email is not updated");
			}
			employeeOption();
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	private static void updatePhoneNum() {
		try {
			String sql="update employee_dtl set emp_phone=? where emp_id=?";
			pstmt = conn.prepareStatement(sql);
			System.out.println("Enter the new phone number of the employee: ");
			String newPhn=sc.next();
			pstmt.setString(1, newPhn);
			pstmt.setInt(2, id);
			long x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Phone number is updated successfully");

			}else {
				System.out.println("Phone number is not updated");
			}
			employeeOption();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void changePwd() {
		try {
			String sql="update employee_dtl set pwd=? where emp_id=?";
			pstmt = conn.prepareStatement(sql);
			System.out.println("Enter the new password: ");
			String newPwd=sc.next();
			pstmt.setString(1, newPwd);
			pstmt.setInt(2, id);
			long x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Password is updated successfully");

			}else {
				System.out.println("Password is not updated");
			}
			employeeOption();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void ViewTasks() {
		try {
			System.out.println("Hello from View Tasks");
		} catch (Exception e) {
			// TODO: handle exception
		}
		employeeOption();
	}
	
}
