<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shot selection</title>
</head>
<body>
	<form action="updatecount" method="post">
		<table>
			<tr>
				<th>Count</th>
				<td><input type="number" name="covaxinAdded"></td>
			</tr>
			<tr>
				<th>Date</th>
				<td><input type="Date" name="date"></td>
			</tr>
			
			<tr>
				<td colspan=2><input type="submit"></td>
			</tr>
		</table>
	</form>

</body>
</html>