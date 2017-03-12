package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

	protected String eMail;
	protected String password; // encrypted
	protected String code; // activation code and cookie id code
	protected boolean doLogin = false;
	protected boolean activated = false;
	protected boolean admin = false;
	protected boolean exists = false;
	
	public boolean isAdmin() {
		return admin;
	}

	public boolean setAdmin(boolean admin) {
		this.admin = admin;
		return DBConnect.updateBoolean("user", "is_admin", admin, "email", eMail);
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		DBConnect.updateString("user", "email", eMail, "email", this.eMail);
		this.eMail = eMail;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean setPassword(String password) {	
		this.password = password;
		return DBConnect.updateString("user", "password", password, "email", eMail);
	}

	public String getCode() {
		return this.code;
	}
	
	public String updateSession() {
		code = Functions.randomString(32);
		DBConnect.updateString("user", "code", code, "email", eMail);
		return code;
	}

	public boolean doLogin() {
		return doLogin;
	}

	public boolean doesExist() {
		return exists;
	}

	public boolean isActivated() {
		return activated;
	}

	public boolean activateUser(boolean activate) {
		this.activated = activate;
		return DBConnect.updateBoolean("user", "is_active", activated, "email", eMail);
	}

	/**
	 * @author Florian
	 * @param eMail
	 * @param password
	 *            - already encrypted
	 */
	public User(String eMail, String password) {
		this.eMail = eMail;
		this.password = password;

		// check if User exists in DB
		exists = checkUser("exists");

		if (exists) {
			// check if User Account is activated
			activated = checkUser("activated");
			admin = checkUser("admin");

			if (activated && password!=null) {
				// checks if encrypted input equals DB password
				doLogin = checkUser("doLogin");
			}

			// if not activated get activation code else get cookie id code
			PreparedStatement preparedStmt =DBConnect.getPreparedStatement("SELECT `code` FROM `user` WHERE `email` = ?");
			try {
				// add id (eMail)
				preparedStmt.setString(1, eMail);

				// execute the preparedstatement
				ResultSet resultSet = preparedStmt.executeQuery();

				resultSet.next();
				code = resultSet.getString("code");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// Close the connection and release the resources used.
				try {
					preparedStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * Inserts new User into DB with aktivationCode
	 * 
	 * @author Florian
	 */
	public boolean createNewUser() {
		if (!exists) {
			code = Functions.randomString(32);

			// create the insert preparedstatement
			PreparedStatement preparedStmt =DBConnect.getPreparedStatement("INSERT INTO `user` (`email`, `password`, `code`, `is_admin`, `is_active`) VALUES (?, ?, ?, '0', '0');");
			try {
				preparedStmt.setString(1, eMail);
				preparedStmt.setString(2, password);
				preparedStmt.setString(3, code);

				// execute the preparedstatement
				preparedStmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// Close the connection and release the resources used.
				try {
					preparedStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// check if User exists in DB
				exists = checkUser("exists");
			}
		}
		return exists;
	}

	// selects query from DB and checks if result is 1
	private boolean checkUser(String task) {
		boolean isUser = false;
		String and="";

		if(task.equals("activated")){
			and=" AND `is_active`= ?";
		} else if(task.equals("doLogin")){
			and=" AND `password`= ?";
		}else if(task.equals("admin")){
			and=" AND `is_admin`= ?";
		}

		// create the mysql select preparedstatement
		PreparedStatement preparedStmt =DBConnect.getPreparedStatement("SELECT COUNT( * ) FROM `user` WHERE `email` = ?" + and);

		try {
			preparedStmt.setString(1, eMail);

			if(task.equals("activated")){
				preparedStmt.setBoolean(2, true);
			} else if(task.equals("doLogin")){
				preparedStmt.setString(2, password);
			} else if(task.equals("admin")){
				preparedStmt.setBoolean(2, true);
			}
			// execute the preparedstatement
			ResultSet resultSet = preparedStmt.executeQuery();

			resultSet.next();
			isUser = (resultSet.getInt("COUNT( * )") == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the connection and release the resources used.
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isUser;
	}

}