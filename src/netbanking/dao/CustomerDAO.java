package netbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import netbanking.db.connection.DBConnection;
import netbanking.pojo.dao.Balance;
import netbanking.pojo.dao.Customer;
import netbanking.pojo.dao.Login;
import netbanking.pojo.ui.AccountOpeningVO;
import netbanking.queries.InsertUpdateQueries;
import netbanking.queries.SelectQueries;

public class CustomerDAO {

	private int BASE_ACCOUNT_NUMBER = 12345;
	private int BASE_CUSTOMER_ID = 54321;

	public long getCustomerId() {
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(SelectQueries.SELECT_MAX_CUSTOMER_ID);
			if (rs.next()) {

				if (rs.getInt(1) != 0) {
					return rs.getInt(1) + 1;
				}
			}
			return BASE_CUSTOMER_ID;

		} catch (Exception ex) {
			return 0;
		}

	}

	public long getAccountNbr() {
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(SelectQueries.SELECT_MAX_ACCOUNT_NUMBER);
			if (rs.next()) {
				if (rs.getInt(1) != 0) {
					return rs.getInt(1) + 1;
				}
			}
			return BASE_ACCOUNT_NUMBER;

		} catch (Exception ex) {
			return 0;
		}
	}

	public long getAccountNbrByCustomerId(long customerId) {
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();
			String query = SelectQueries.SELECT_ACCOUNTID_BY_CUSTOMER_ID;
			query = query.replace(":customerid", String.valueOf(customerId));
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				return rs.getLong("accountnumber");
			}
			return 0;

		} catch (Exception ex) {
			return 0;
		}
	}
	
	public String getAccountNameByCustomerId(long customerId) {
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();
			String query = SelectQueries.SELECT_NAME_BY_CUSTOMER_ID;
			query = query.replace(":customerid", String.valueOf(customerId));
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				return rs.getString("name");
			}
			return null;

		} catch (Exception ex) {
			return null;
		}
	}

	public AccountOpeningVO insertCustomer(Customer customer) {
		try {
			int customerId = (int) getCustomerId();
			int accountNbr = (int) getAccountNbr();
			Connection con = DBConnection.getConnection();
			String query = InsertUpdateQueries.INSERT_CUSTOMER;
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, accountNbr);
			preparedStmt.setString(2, customer.getName());
			preparedStmt.setString(3, customer.getSsn());
			preparedStmt.setInt(4, customerId);
			preparedStmt.setString(5, customer.getEmail());
			preparedStmt.setString(6, customer.getPhone());
			preparedStmt.setDouble(7, customer.getOpeningBalance());
			preparedStmt.execute();
			

			BalanceDAO balanceDAO = new BalanceDAO();
			Balance balance = new Balance();
			balance.setAccountNumber(accountNbr);
			balance.setBalance(customer.getOpeningBalance());
			balanceDAO.insertBalance(balance);

			LoginDAO loginDAO = new LoginDAO();
			Login login = new Login();
			login.setCustomerId(customerId);
			String password = generatePassword();
			login.setPassword(password);
			login.setRole("Customer");

			loginDAO.insertLogin(login);

			// sendMail(login,customer.getEmail());

			AccountOpeningVO accountOpeningVO = new AccountOpeningVO();
			accountOpeningVO.setAccountNumber(accountNbr);
			accountOpeningVO.setCustId(customerId);
			accountOpeningVO.setOpeningBalance(customer.getOpeningBalance());
			accountOpeningVO.setPassword(password);
			
			return accountOpeningVO;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private void sendMail(Login login, String email) {

		String fromMail = "abcbankofamerica@gmail.com";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "localhost");
		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Login Details from abcbankofamerica@gmail.com ...Keep Safe");
			message.setText("Username :" + login.getCustomerId() + "Password :" + login.getPassword());

			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	private String generatePassword() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890@#$%^&*()";
		StringBuilder sb = new StringBuilder();
		Random rd = new Random();
		while (sb.length() < 12) {
			int index = (int) (rd.nextFloat() * chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();

	}

}
