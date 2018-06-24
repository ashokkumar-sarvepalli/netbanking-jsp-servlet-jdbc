package netbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.security.auth.login.AccountNotFoundException;

import netbanking.db.connection.DBConnection;
import netbanking.pojo.dao.Balance;
import netbanking.queries.InsertUpdateQueries;
import netbanking.queries.SelectQueries;

public class BalanceDAO {

	public double getBalance(long accountNumber) throws AccountNotFoundException{
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();

			String query = SelectQueries.SELECT_BALANCE_BY_ACCOUNTNUMBER;
			query = query.replace(":accountnumber", String.valueOf(accountNumber));

			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				return rs.getDouble("balance");
			}
			throw new AccountNotFoundException("Account Not found");

		} catch (AccountNotFoundException ex) {
			throw ex;
		}catch(Exception ex){
			return 0.0;
		}

	}

	public boolean insertBalance(Balance balance) {
		try {
			Connection con = DBConnection.getConnection();
			String query = InsertUpdateQueries.INSERT_BALANCE;
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, (int) balance.getAccountNumber());
			preparedStmt.setDouble(2, balance.getBalance());
			preparedStmt.execute();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	
	public boolean update(Balance balance) {
		try {
			Connection con = DBConnection.getConnection();
			String query = InsertUpdateQueries.UPDATE_BALANCE;
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(2, (int) balance.getAccountNumber());
			preparedStmt.setDouble(1, balance.getBalance());
			preparedStmt.execute();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
