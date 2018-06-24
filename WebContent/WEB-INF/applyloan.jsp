<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function myFunction() {
    alert("Thanks for applying based on your ssn credit score. Bank will contact you");
}
</script>

</head>
<body bgcolor = "yellow">
<p><font color="blue" size= "20"> <center> Welcome To ABCInternetBanking </center> </font></p>
<p><font color="blue" size= "20"> <center> CREATE CUSTOMER </center> </font></p>
<center>
	<div style="width: 400px; margin: 200px auto 0 auto;">

<form  onsubmit="myFunction()">
<font color="brown" size="4">
			<p>
				 <label><b>Choose Loan Type</b></label>
					

					<select name="loantype" placeholder="Select the loan you want to apply?" required >
						<option value="Personal">Personal</option>
						<option value="Housing">Housing</option>
						<option value="Car">Car</option>
					</select> 
				
			</P>

			<P>
			<label><b>Enter SSN</b></label> <input type="text"
					placeholder="Enter your SSN" name="ssn" required>
					
			</P>
			
			<P>
			<label><b>Enter Amount Required</b></label> <input type="text"
					placeholder="Enter Amount Required" name="amount" required>
					
			</P>
			
			<center>
			<P>
				<button type="submit">Apply Loan</button>
		    </P>
			</center>
			</font>
			</form>
</div>
</center>
<center>

<P>
<form action=BalanceServlet method="get">
		  <button type="submit">AccountHomeScreen</button>
		</form>
</P>
</center>

</body>
</html>