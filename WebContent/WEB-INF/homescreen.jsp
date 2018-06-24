<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="netbanking.pojo.dao.Balance"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="yellow">
	<p>
		<font color="blue" size="20">
			<center>Welcome To ABCInternetBanking</center>
		</font>
	</p>
	<p>
		<font color="blue" size="20">
			<center>Welcome <%=request.getSession().getAttribute("name") %></center>
		</font>
	</p>
	<!-- get balance object -->
	<%
 			Balance balance = (Balance) request.getAttribute("balance");
	
 	%>
 	
	
	<center>
		<div style="width: 300px; margin: 200px auto 0 auto;">

			<font color="brown" size="4"> 
				

				<P>
					<label><b>AccountNumber : <%=balance.getAccountNumber()%></b></label>

				</P>

				<P>
					<label><b>Your Current Balance : <%=balance.getBalance()%></b></label>

				</P>

			

			</font>

		</div>

	</center>
	
	<center>
	
	<p>
	<form action ="HistoricalStatement" method="get"><input type="submit" style="height: 50px; width: 200px" value="HistoricalStatement"> </form>
	<p>
	
	<p>
	<form action ="MiniStatement" method="get"><input type="submit" style="height: 50px; width: 200px" value="MiniStatement"> </form>
	<p>
	
	<p>
	<form action ="FundTransfer" method="get"><input type="submit" style="height: 50px; width: 200px" value="FundTransfer"> </form>
	<p>
	
	<p>
	<form action ="ApplyLoan" method="get"><input type="submit" style="height: 50px; width: 200px" value="ApplyLoan"> </form>
	<p>
	
	<p>
	<form action ="LogOut" method="post"><input type="submit" style="height: 50px; width: 200px" value="LogOut"> </form>
	<p>
	</center>
	
	

</body>
</html>