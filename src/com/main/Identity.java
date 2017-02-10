/**
 * 
 */
package com.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DAO;
import com.database.DAO_impl;

/**
 * @author Kankipati
 *
 */
public class Identity {
	
	private String uid;
	private String displayName;
	private String email;
	private DAO dao;
	public boolean saved;
	/**
	 * @param uid
	 * @param displayName
	 * @param email
	 */
	public Identity(String uid, String displayName, String email) {
		this.uid = uid;
		this.displayName = displayName;
		this.email = email;
		dao = new DAO_impl();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [uid=" + uid + ", displayName=" + displayName + ", email=" + email + "]";
	}
	
	public String saveIdentity() {
		String message = "";
		String query = "select * from identities where IDENTITIES_UID='"+uid+"'";
		ResultSet resultSet = dao.getData(query);
		try {
			if(!resultSet.next()) {
				query = "insert into identities(IDENTITIES_UID,IDENTITIES_DISPLAYNAME,IDENTITIES_EMAIL) "
						+ "values('"+uid+"','"+displayName+"','"+email+"')";
				int rows = dao.putData(query);
				if(rows > 0) {
					message = "Identity Saved";
					saved = true;
				}
				else {
					message = "Identity Not Saved";
					saved = false;
				}
			}
			else
			{
				saved = false;
				message = "display name already exists";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}


	public String updateIdentity() {
		// TODO Auto-generated method stub
		
		String query ="update identities set IDENTITIES_DISPLAYNAME='"+displayName+"',IDENTITIES_EMAIL='"+email+"' where IDENTITIES_UID='"+uid+"'";
		int rows = dao.putData(query);
		String message;
		if(rows > 0) {
			message = "Identity Updated";
			saved = true;
		}
		else {
			message = "Identity Not Updated";
			saved = false;
		}
		return message;
	}
	public String deleteIdentity() {
		// TODO Auto-generated method stub
		String query ="delete from identities  where IDENTITIES_UID='"+uid+"'";
		int rows = dao.putData(query);
		String message;
		if(rows > 0) {
			message = "Identity Deleted";
			saved = true;
		}
		else {
			message = "Identity Not Deleted";
			saved = false;
		}
		return message;
	}


	public void displayIdentity() {
		// TODO Auto-generated method stub
		String query = "select * from identities";
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
				do {
					uid = resultSet.getString("IDENTITIES_UID");
					displayName = resultSet.getString("IDENTITIES_DISPLAYNAME");
					email = resultSet.getString("IDENTITIES_EMAIL");
					System.out.println(this);
				}while(resultSet.next());
			}
			else {
				System.out.println("No identities found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
