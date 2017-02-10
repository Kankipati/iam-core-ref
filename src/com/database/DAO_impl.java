package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Kankipati
 *
 */
public class DAO_impl implements DAO {

	Connection connection;
	Statement statement;
	
	DBConnection dbconnection;
	
	public DAO_impl(){
		dbconnection=new DBConnection();
		try {
			connection = dbconnection.getConnetion();
			statement = connection.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void closeConnection() {

		try {
			if(connection!=null){
			connection.close();
			}
			
			if(statement!=null){
				statement.close();
			}
			
		} catch (Exception e) {
			System.out.println("Exception while closing connection"+e.getMessage());
		}
	}

	@Override
	public ResultSet getData(String query) {

		try {
			return statement.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Exception in getData() ->"+e.getMessage());
			return null;
		}

	}

	@Override
	public int putData(String query) {
		try {
			return statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Exception in putData() ->"+e.getMessage());
						return -1;
		}
		
	}

	@Override
	public boolean isExists(String query) {
		// TODO Auto-generated method stub
		boolean exists = false;
		try {
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				exists = true;
			}
		} catch (Exception e) {
			System.out.println("Exception in getData() ->"+e.getMessage());
			
		}
		return exists;
	}

}
