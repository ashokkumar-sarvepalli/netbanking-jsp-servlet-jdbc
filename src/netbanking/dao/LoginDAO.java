package netbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import netbanking.db.connection.DBConnection;
import netbanking.pojo.dao.Login;
import netbanking.queries.InsertUpdateQueries;
import netbanking.queries.SelectQueries;

public class LoginDAO {

	public boolean isLoginSuccess(Login login) {
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();

			String query = SelectQueries.SELECT_LOGIN_SUCCESS_ATTEMPT;
			query = query.replace(":customerid", String.valueOf(login.getCustomerId()));
			query = query.replace(":password", login.getPassword());
			query = query.replace(":role", login.getRole());
			query = query.replace(":secretanswer", login.getSecretAnswer());
			query = query.replace(":secretquestion", login.getSecretQuestion());
			System.out.println("The query that got executed is "+query);
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getInt(1)>0;
		} catch (Exception ex) {
			return false;
		}
	}
	

	public boolean isLoginSuccessAdmin(String  username, String password) {
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();

			String query = SelectQueries.SELECT_LOGIN_SUCCESS_ATTEMPT_ADMIN;
			query = query.replace(":username", username);
			query = query.replace(":password", password);
			System.out.println("the quey is "+query);
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getInt(1)>0;
		} catch (Exception ex) {
			return false;
		}
	}


	public String getSecretQuestion(Login login) {
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();

			String query = SelectQueries.SELECT_LOGIN_SECRET_QUESTION;
			query = query.replace(":customerid", String.valueOf(login.getCustomerId()));
			query = query.replace(":password", login.getPassword());
			query = query.replace(":role", login.getRole());
			ResultSet rs = st.executeQuery(query);
			boolean result = rs.next();
			if(!result){
				return "InvalidLogin";
			}
			if(rs.getString("password")!=null)
			{
				if(rs.getString("secretquestion")==null){
					return "No";
				}
			}
			return rs.getString("secretquestion");
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean insertLogin(Login login) {
		try {
			Connection con = DBConnection.getConnection();
			String query = InsertUpdateQueries.INSERT_LOGIN;
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setLong(1, login.getCustomerId());
			preparedStmt.setString(2, login.getPassword());
			preparedStmt.setString(3, login.getRole());
			preparedStmt.setString(4, login.getSecretQuestion());
			preparedStmt.setString(5, login.getSecretAnswer());

			preparedStmt.execute();
			return true;
		} catch (Exception ex) {
			return false;
		}

	}
	
	public boolean updateLogin(Login login){
		try {
			Connection con = DBConnection.getConnection();
			String query = InsertUpdateQueries.UPDATE_LOGIN;
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setLong(5, login.getCustomerId());
			preparedStmt.setString(1, login.getPassword());
			preparedStmt.setString(2, login.getRole());
			preparedStmt.setString(3, login.getSecretQuestion());
			preparedStmt.setString(4, login.getSecretAnswer());

			preparedStmt.execute();

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
