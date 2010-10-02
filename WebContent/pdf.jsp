<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.lowagie.text.Document"%>

<%@page import="java.io.OutputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.lowagie.text.pdf.PdfWriter"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDF</title>
</head>
<body>
	<% 
		Document document = (Document)session.getAttribute("pdf");
		PdfWriter.getInstance(document, 
			response.getOutputStream());
	%>
</body>
</html>