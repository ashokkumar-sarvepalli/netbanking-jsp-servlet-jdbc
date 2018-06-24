package netbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import netbanking.db.connection.DBConnection;
import netbanking.exception.InvalidSecurityException;
import netbanking.pojo.dao.Balance;
import netbanking.queries.InsertUpdateQueries;
import netbanking.queries.SelectQueries;

public class SecurityDAO {

	
	public Long isSecuritySuccess(long accountNumber) throws InvalidSecurityException{
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();

			String query = SelectQueries.SELECT_TRANSACTION_PASSWORD;
			query = query.replace(":accountnumber", String.valueOf(accountNumber));
			
			ResultSet rs = st.executeQuery(query);
			if(rs.next())
			return rs.getLong("securitynumber");
			
			throw new InvalidSecurityException("Transaction password not set");
		} catch (Exception ex) {
			return null;
		}
	}
	
	
	public boolean insertSecurityNumber(Long accountNumber, Long securityNumber) {
		try {
			Connection con = DBConnection.getConnection();
			String query = InsertUpdateQueries.INSERT_TXN_PASWORD;
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setLong(1, accountNumber);
			preparedStmt.setLong(2, securityNumber);
			preparedStmt.execute();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
