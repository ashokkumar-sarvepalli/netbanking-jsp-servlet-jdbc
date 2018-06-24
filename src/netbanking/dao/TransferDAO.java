package netbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.login.AccountNotFoundException;

import netbanking.db.connection.DBConnection;
import netbanking.exception.InsufficientBalanceException;
import netbanking.pojo.dao.Balance;
import netbanking.pojo.dao.Transaction;
import netbanking.queries.InsertUpdateQueries;

public class TransferDAO {
	
	public boolean transfer(Transaction twd) throws InsufficientBalanceException, AccountNotFoundException{
		BalanceDAO balanceDAO = new BalanceDAO();
		// check balance of src account
		double balance= balanceDAO.getBalance(twd.getAccountNumber());
		if(balance<twd.getAmmount()){
			throw new InsufficientBalanceException("Insufficient Funds");
		}
		double destBalance= balanceDAO.getBalance(twd.getDestAccountNumber());
		
		//update balance of src account
		balance = balance - twd.getAmmount();
		Balance srcBalance = new Balance();
		srcBalance.setAccountNumber(twd.getAccountNumber());
		srcBalance.setBalance(balance);
		balanceDAO.update(srcBalance);
		
		//insert withdraw of src account
		insertWithDraw(twd);
		
		//update balance of dest account
		
		destBalance = destBalance + twd.getAmmount();
		Balance destiBalance = new Balance();
		destiBalance.setAccountNumber(twd.getDestAccountNumber());
		destiBalance.setBalance(destBalance);
		balanceDAO.update(destiBalance);
		
		//insert deposit of destination account
		insertDeposit(twd);
		
		return true;
	}

	private boolean insertDeposit(Transaction twd) {
		try {
			Connection con = DBConnection.getConnection();
			String query = InsertUpdateQueries.INSERT_DEPOSIT;
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setLong(1, twd.getDestAccountNumber());
			preparedStmt.setLong(2, twd.getAccountNumber());
			preparedStmt.setString(3, twd.getName());
			preparedStmt.setString(4, "ABC" );
			preparedStmt.setString(5, "IFSC");
			String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
			preparedStmt.setString(6, date);
			preparedStmt.setString(7, twd.getDescription());
			preparedStmt.setDouble(8, twd.getAmmount());
			preparedStmt.execute();
			return true;
		} catch (Exception ex) {
			return false;
		}

		
	}

	private boolean insertWithDraw(Transaction twd) {
		try {
			Connection con = DBConnection.getConnection();
			String query = InsertUpdateQueries.INSERT_WITHDRAWAL;
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setLong(1, twd.getAccountNumber());
			preparedStmt.setLong(2, twd.getDestAccountNumber());
			preparedStmt.setString(3, twd.getDestAccountName());
			preparedStmt.setString(4, "ABC" );
			preparedStmt.setString(5, "IFSC");
			String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
			preparedStmt.setString(6, date);
			preparedStmt.setString(7, twd.getDescription());
			preparedStmt.setDouble(8, twd.getAmmount());
			preparedStmt.execute();
			return true;
		} catch (Exception ex) {
			return false;
		}

		
	}
	
	public static void main(String []args){
		String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
		System.out.println(date);
	}

}
