package netbanking.queries;

public interface InsertUpdateQueries {

	String INSERT_CUSTOMER = "insert into customer (accountnumber, name, ssn, customerid, email,phone,openingbalance)"
			+ " values (?, ?, ?, ?, ?,?,?)";

	String INSERT_LOGIN = "insert into login (customerid, password, role, secretquestion, secretanswer)"
			+ " values (?, ?, ?, ?, ?)";

	String INSERT_LOGINHIST = "insert into loginhist(customerid,date,time) " + " values(?,?,?)";
	
	String INSERT_BALANCE = "insert into balance(accountnumber,balance)" + "values(?,?)";
	
	String INSERT_DEPOSIT = "insert into deposit(accountnumber,srcaccountnumber,srcaccountname,bankname,ifsc,date,description,amount)"
			+"values (?,?,?,?,?,?,?,?)";
	
	String INSERT_WITHDRAWAL = "insert into withdraw(accountnumber,destaccountnumber,destaccountname,bankname,ifsc,date,description,amount)"
			+"values (?,?,?,?,?,?,?,?)";

	String UPDATE_LOGIN = "update login set password=?, role=?, secretquestion=?,secretanswer=? where customerid=?";
	
	String UPDATE_BALANCE = "update balance set balance=? where accountnumber=?";
	
	String INSERT_TXN_PASWORD = "insert into securityno(accountnumber,securitynumber)" + "values(?,?)";
}
