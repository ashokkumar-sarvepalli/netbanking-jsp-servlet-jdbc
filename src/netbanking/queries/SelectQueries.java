package netbanking.queries;

public interface SelectQueries {

	String SELECT_CUSTOMER_BY_ID = "select * from customer where customerid = ':customerid'";

	String SELECT_CUSTOMER_BY_ACCOUNTNUMBER = "select * from customer where accountnumber = ':accountnumber'";

	String SELECT_CUSTOMER_BY_ACCOUNTNUMBER_DEPOSITS = "select * from deposit where accountnumber = ':accountnumber'";

	String SELECT_CUSTOMER_BY_ACCOUNTNUMBER_WITHDRAWAL = "select * from withdraw where accountnumber = ':accountnumber'";

	String SELECT_BALANCE_BY_ACCOUNTNUMBER = "select balance from balance where accountnumber = ':accountnumber'";

	String SELECT_ACCOUNTID_BY_CUSTOMER_ID = "select accountnumber from customer where customerid = ':customerid'";
	
	String SELECT_NAME_BY_CUSTOMER_ID = "select name from customer where customerid = ':customerid'";

	String SELECT_LOGIN_SUCCESS_ATTEMPT = "select count(*) as total from login where customerid = ':customerid' and password = ':password' and role = ':role' and secretanswer = ':secretanswer' and secretquestion = ':secretquestion'";

	String SELECT_LOGIN_SECRET_QUESTION = "select secretquestion,password from login where customerid = ':customerid' and password = ':password' and role = ':role'";

	String SELECT_MAX_CUSTOMER_ID = "select max(customerid) from customer";

	String SELECT_MAX_ACCOUNT_NUMBER = "select max(accountnumber) from customer";

	String SELECT_LOGIN_SUCCESS_ATTEMPT_ADMIN = "select count(*) as total from loginadmin where username = ':username' and password = ':password'";

	String SELECT_TRANSACTION_PASSWORD = "select securitynumber from securityno where accountnumber = ':accountnumber'";
}
