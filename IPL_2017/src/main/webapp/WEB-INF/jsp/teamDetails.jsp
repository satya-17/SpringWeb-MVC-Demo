<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
h1{
text-align: center;
font-family: serif;
color: #696969
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<h1>${team.name} Team Information</h1>

	<table bordercolor="black" cellpadding="5px" bgcolor="WhiteSmoke "
		cellspacing="5px" align="center">

			<tr>
				<th>Id:</th>
				<td>${team.id}</td>
			</tr>
			<tr>
				<th>Name:</th>
				<td>${team.name}</td>
			</tr>
			<tr>
				<th>Coach:</th>
				<td>${team.coach}</td>
			</tr>
			<tr>
				<th>Owner</th>
				<td>${team.owner}</td>
			</tr>
			<tr>
				<th>Captain</th>
				<td>${team.captain}</td>
			</tr>
			<tr>
				<th>Home Venue:</th>
				<td>${team.homevenue}</td>
			</tr>
			<tr>
				<td><a
					href="<c:url value="playerList"/>?teamId=${team.id}&teamName=${team.name}">Click
						here to view all players</a></td>
			</tr>
	</table>
</body>
</html>