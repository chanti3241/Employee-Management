package com.employee.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AdminOperations {

	static Scanner sc = new Scanner(System.in);
	private static PreparedStatement pstmt;
	private static ResultSet res;
	private static Connection conn;
	static String uname;
	static String pwd;

	public static void register(Connection con) {
		try {
			String sql = "insert into AdminData values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			System.out.print("Enter the Id: ");
			int id = sc.nextInt();
			System.out.print("Enter the Name: ");
			String name = sc.next();
			System.out.print("Enter the Email: ");
			String mail = sc.next();
			System.out.print("Enter the UserName: ");
			String uname = sc.next();
			System.out.print("Enter the Password: ");
			String pwd = sc.next();

			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, mail);
			pstmt.setString(4, uname);
			pstmt.setString(5, pwd);

			int x = pstmt.executeUpdate();

			if (x > 0) {
				System.out.println("user registration sucess");
				EmployeeManagement.adminOps();
			} else {
				System.out.println("user registration not sucess");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void login(Connection con) {
		try {
			String sql = "select * from AdminData where uname=? and pwd=?";
			System.out.print("Enter the User name: ");
			String user = sc.next();
			System.out.print("Enter the Password: ");
			String pwd = sc.next();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, pwd);
			
			res = pstmt.executeQuery();
			
			if(res.next()==true) {
				System.out.println("login Sucess");
				uname = res.getString(2);
				AdminView.start(uname, con);
				
			}
			else {
				System.out.println(" login fail");
				AdminOperations.login(con);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void Exit(Connection con) {
		System.out.println("Exit");
	}
}
