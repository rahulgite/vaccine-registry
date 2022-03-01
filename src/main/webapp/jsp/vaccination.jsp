<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vaccination</title>
</head>
<body>

	<form action="savepeople" method="post">
		<table>
			<tr>
				<th>Name</th>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr>
				<th>Aadhar</th>
				<td><input type="text" name="aadhar"></td>
			</tr>
			<tr>
				<th>Age</th>
				<td><input type="number" name="userAge"></td>
			</tr>
			<tr>
				<th>Date</th>
				<td><input type="Date" name="date"></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><input type="text" name="city"></td>
			</tr>
			<tr>
				<th>Vaccine type</th>
				<td><input type="text" name="vaccineType"></td>
			</tr>
			
			<tr>
				<td colspan=2><input type="submit"></td>
			</tr>
		</table>
	</form>

</body>
</html>