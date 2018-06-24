<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<% HttpSession sessions = request.getSession();
	  sessions.invalidate();
	%>
	<p>
		<font color="blue" size="20">
			<center>Logged out succesfully!!!!!</center>
		</font>
	</p>
	
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
</html>