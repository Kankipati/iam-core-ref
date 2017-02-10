package com.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.DAO;
import com.database.DAO_impl;

public class MainConsole {
	public static void main(String[] args) {
		DAO dao = new DAO_impl();
		Scanner key = new Scanner(System.in);
		boolean isAdmin =false;
		while(!isAdmin) {
		System.out.println("Enter username");
		String username = key.nextLine();
		System.out.println("Enter password");
		String password = key.nextLine();
		
		// getting data from database with given username
		String query = "select * from admin where username='"+username+"' and password='"+password+"'";
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {  // if given username is valid then check for password.
				
					System.out.println("Welcome Admin");
					isAdmin = true;
					break;
				
			}else {
				System.out.println("Invalid Username/password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if(isAdmin) {
			adminOptions();
		}
	}

	private static void adminOptions() {
		// TODO Auto-generated method stub
		DAO dao = new DAO_impl();
		Scanner key = new Scanner(System.in);
		while(true) {
			
			System.out.println("a. Create an Identity");
			System.out.println("b. Modify an Identity");
			System.out.println("c. Delete an Identity");
			System.out.println("d. List Identities");
			System.out.println("e. quit");
			String ch = key.nextLine().toLowerCase();
			boolean flag = false;
			switch(ch) {
			case "a":	
				while(!flag) {
					System.out.println("Enter uid");
					String uid = key.nextLine();
					System.out.println("Enter displayName");
					String displayName = key.nextLine();
					System.out.println("Enter email");
					String email = key.nextLine();
					Identity identity = new Identity(uid, displayName, email);
					System.out.println(identity.saveIdentity());
					flag = identity.saved;
				}
				break;
			case "b":
				while(!flag) {
					System.out.println("Enter uid");
					String uid = key.nextLine();
					System.out.println("Enter displayName");
					String displayName = key.nextLine();
					System.out.println("Enter email");
					String email = key.nextLine();
					Identity identity = new Identity(uid, displayName, email);
					System.out.println(identity.updateIdentity());
					flag = identity.saved;
				}
				break;
			case "c":
				while(!flag) {
					System.out.println("Enter uid");
					String uid = key.nextLine();
					Identity identity = new Identity(uid,"","");
					System.out.println(identity.deleteIdentity());
					flag = identity.saved;
				}
				break;
			case "d":
				Identity identity = new Identity("","","");
				identity.displayIdentity();
				break;
			case "e":
				System.out.println("Thanks for using our App");
				System.exit(0);
				break;
			}
		}
	}
}
