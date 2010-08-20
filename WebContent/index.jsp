<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css" media="screen">
@import url("style.css");
</style>

<title>RISAnalyzer</title>
</head>
<body>
<h1>RISAnalyzer</h1>
<p>This is RISAnalyzer by <a href="mailto:bernd@braegelmann.de">Bernd Br�gelmann</a></p>
<h2>RISPatientDoubletServlet</h2>
<form action="/RISAnalyzer/RISPatientDoubletServlet">
	<input type="checkbox" name="onlyhisdoublet" value="true" /> only his doublets<br />
    <input type="checkbox" name="style" value="autoit" /> export for AutoIT<br />
	<input type="submit" value="Perform" />
</form>
<p>RISPatientDoubletServlet is a tool for retrieving RIS patient doublets with identic last-, firstname and date of birth.</p>
</body>
</html>