<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="netbanking.pojo.ui.AccountOpeningVO"%>
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
			<center>Please note down the details... Share account
				number, customer id and password to the customer .. Note : Username
				is customer id for netbanking login... a separate mail also sent to
				customer mail id.</center>
		</font>
	</p>

	<center>
		<div style="width: 400px; margin: 200px auto 0 auto;">

			<font color="brown" size="14"> <%
 	AccountOpeningVO accountOpeningVO = (AccountOpeningVO) request.getAttribute("accountopening");
 %>
				<P>
					<label><b>customerid : <%=accountOpeningVO.getCustId()%></b></label>

				</P>

				<P>
					<label><b>AccountNumber : <%=accountOpeningVO.getAccountNumber()%></b></label>

				</P>

				<P>
					<label><b>Balance : <%=accountOpeningVO.getOpeningBalance()%></b></label>

				</P>

				<P>

					<label><b>Password : <%=accountOpeningVO.getPassword()%></b></label>

				</P>

			</font>

		</div>

	</center>

	<p>
		<font color="green" size="4">
			<center>To Go to home page press this below</center>
		 <a href=homepage.jsp>
			<center>
				press here
			</center>
		</a>
		</font>
	</p>
</body>
</html>