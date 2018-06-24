package netbanking.pojo.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatementVO {

	private String date;
	private Date dateInDateFormat;
	private String description;
	private String debitAmount;
	private String creditAmount;
	private String accountNumber;
	private String accountName;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
		dateInDateFormat = convertStringToDate(date);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public Date getDateInDateFormat() {
		return dateInDateFormat;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	private Date convertStringToDate(String fromDate) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
		Date date;
		try {
		    date = df.parse(fromDate);
		    return date;
		    
		} catch (ParseException e) {
		   return null;
		}		
	}
}
