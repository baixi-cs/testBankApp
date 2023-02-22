<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align = "center">
	<p>ADD AN ACCOUNT</p>
	<form action="addpost" method="post">
			<table>
				<tr>
					<td>id:</td>
					<td><input type="number" name="id" /></td>
				</tr>
				<tr>
					<td>name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>account</td>
					<td><input type="number" name="account" /></td>
				</tr>
				<tr>
					<td>address</td>
					<td><input type="text" name="address" /></td>
				</tr>
			</table>

			<input type="submit" value="Login">
		</form>
</div>
<hr>

<div align = "center">
<p>DISPLAY ACCOUNTS</p>
<form name="loginForm" method="get" action="display">
    <input type="submit" value="get" />
</form>
</div>
<hr>


<div align = "center">
<p>DELETE AN ACCOUNT</p>
<form name="loginForm" method="post" action="delete">
	<table>
				<tr>
					<td>id:</td>
					<td><input type="number" name="id" /></td>
				</tr>
	</table>
	<input type="submit" value="delete" />
   
</form>
</div>
</body>
</html>
