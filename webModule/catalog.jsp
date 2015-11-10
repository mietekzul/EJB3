<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="ejb3.*,java.util.*,javax.naming.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<meta http-equiv="Content-type"
	content="text/html; charset=windows-1252" />
<title>EJB3 Client</title>
<body>

	<%
		InitialContext context = new InitialContext();
		CatalogSessionBeanFacadeRemote beanRemote = (CatalogSessionBeanFacadeRemote) contextlookup(
				"EJB3-SessionEJB#ejb3.CatalogSessionBeanFacadeRemote");

		CatalogSessionBeanFacadeRemote beanRemote = (CatalogSessionBeanFacadeRemote) contextlookup(
				"EJB3-SessionEJB#ejb3.CatalogSessionBeanFacadeRemote");
	%>

</body>
</html>