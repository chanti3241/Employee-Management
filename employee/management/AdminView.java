package com.employee.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AdminView {

	static String admin_name;
	private static Connection conn;
	private static Scanner sc;
	private static PreparedStatement pstmt;
	private static Statement statement;
	private static ResultSet res;

	static void start(String admin, Connection con) {
		try {
			admin_name = admin;
			conn = con;
			System.out.println("\t \t \t \t Welcome "+ admin );
			System.out.println("please  Select the operations to be performed: ");
			System.out.println("1. Add Employee\n"
			+"2. Update Employee Name\n"
			+"3. Update Employee Salary\n"
			+"4. Update Employee phone\n"
			+"5. Update Employee email\n"
			+"6. Update all Employee Salary\n"
			+"7. View all Employee\n"
			+"8. Delete Employee\n"
			+"9. Exit");
			sc = new Scanner(System.in);
			int input = sc.nextInt();
			
			switch (input) {
			case 1:
				addEmployee();
			
			case 2:
				updateEmployeeName();
				
			case 3:
				updateEmpSalary();
				
			case 4:
				updateEmpPhone();
				
			case 5:
				UpdateEmpEmail();
				
			case 6:
				UpdateAllEmpSalary();
				
			case 7:
				viewAllEmp();
				
			case 8:
				DeleteEmp();
				
			case 9:
				EmployeeManagement.adminOps();
				
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
				//case:8
	private static void DeleteEmp() {
		try {
			String query = "delete from Employee_dtl WHERE emp_id = ? ";
			pstmt = conn.prepareStatement(query);
			System.out.println("Enter the Id: ");
			int id = sc.nextInt();
			pstmt.setInt(1, id);
			int x = pstmt.executeUpdate();
			if(x>=0) {
			System.out.println("Employee" + id + " is Deleted");
		    }
			start(admin_name, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
				//case:7
	private static void viewAllEmp() {
		try {
			statement = conn.createStatement();
			String sql = "Select * from employee_dtl";
			res = statement.executeQuery(sql);
			
			while(res.next()==true) {
				System.out.println("Id: "+res.getInt(1));
				System.out.println("Name: "+res.getString(2));
				System.out.println("Experience: "+res.getInt(3));
				System.out.println("Company Name: "+res.getString(4));
				System.out.println("Department: "+res.getString(5));
				System.out.println("Designation: "+res.getString(6));
				System.out.println("Salary: "+res.getInt(7));
				System.out.println("Phone number: "+res.getLong(8));
				System.out.println("Email: "+res.getString(9));
				System.out.println("-----------------------");
				
			}start(admin_name, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
				//case:6
	private static void UpdateAllEmpSalary() {
		try {
			String query = "UPDATE Employee_dtl SET emp_salary =? WHERE emp_id = ? ";
			pstmt = conn.prepareStatement(query);
			System.out.println("Enter the Id: ");
			int id = sc.nextInt();
			System.out.println("Enter the Updating salary: ");
			int salary = sc.nextInt();
			
			pstmt.setInt(2, id);
			pstmt.setInt(1, salary);
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Employee Salary is Updated");
			}start(admin_name, conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
				//case:5
	private static void UpdateEmpEmail() {
		try {
			String query = "UPDATE Employee_dtl SET emp_email =? WHERE emp_id = ? ";
			pstmt = conn.prepareStatement(query);
			System.out.println("Enter the Id: ");
			int id = sc.nextInt();
			System.out.println("Enter the Updating email: ");
			String email = sc.next();
			
			pstmt.setInt(2, id);
			pstmt.setString(1, email);
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Employee Email is Updated");
			}start(admin_name, conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
				//case:4
	private static void updateEmpPhone() {
		try {
			String query = "UPDATE Employee_dtl SET emp_phone =? WHERE emp_id = ? ";
			pstmt = conn.prepareStatement(query);
			System.out.println("Enter the Id: ");
			int id = sc.nextInt();
			System.out.println("Enter the Updating Phone: ");
			Long phn = sc.nextLong();
			
			pstmt.setInt(2, id);
			pstmt.setLong(1, phn);
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Employee Phone is Updated");
			}start(admin_name, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
				//case:3
	private static void updateEmpSalary() {
		try {
			String query = "UPDATE Employee_dtl SET emp_salary =? WHERE emp_id = ? ";
			pstmt = conn.prepareStatement(query);
			System.out.println("Enter the Id: ");
			int id = sc.nextInt();
			System.out.println("Enter the Updating salary: ");
			int salary = sc.nextInt();
			
			pstmt.setInt(2, id);
			pstmt.setInt(1, salary);
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Employee Salary is Updated");
			}
			start(admin_name, conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
				//case:2
	private static void updateEmployeeName() {
		try {
			String query = "UPDATE Employee_dtl SET emp_name =? WHERE emp_id = ? ";
			pstmt = conn.prepareStatement(query);
			System.out.println("Enter the Id: ");
			int id = sc.nextInt();
			System.out.println("Enter the Name: ");
			String name = sc.next();
			
			pstmt.setInt(2, id);
			pstmt.setString(1, name);
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Employee Name is Updated");
				start(admin_name, conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
				//case:1
	private static void addEmployee() {
		try {
			String sql = "insert into employee_dtl values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			System.out.println("Enter the Id: ");
			int id = sc.nextInt();
			System.out.println("Enter the Name: ");
			String name = sc.next();
			System.out.println("Enter Experience: ");
			int exp = sc.nextInt();
			System.out.println("Enter the Company Name: ");
			String comp_name = sc.next();
			System.out.println("Enter the Department: ");
			String dept = sc.next();
			System.out.println("Enter salary Allocated: ");
			int salary = sc.nextInt();
			System.out.println("Enter the Phone number: ");
			Long phn = sc.nextLong();
			System.out.println("Enter the email: ");
			String email = sc.next();
			System.out.println("Enter the pwd: ");
			String pwd = sc.next();
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, exp);
			pstmt.setString(4, comp_name);
			pstmt.setString(5, dept);
			pstmt.setInt(6, salary);
			pstmt.setLong(7, phn);
			pstmt.setString(8, email);
			pstmt.setString(9, pwd);
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Employee Data is inserted");
			}
			start(admin_name, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
