<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de usuarios</title>
</head>
<body>
<h1>Lista de usuarios</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Username</th>
			<th>Activo</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.active}
				<td><a href="users/edit/${user.id}">Editar</a> | <a
					href="users/delete/${user.id}">Eliminar</a></td>
			</tr>
		</c:forEach>
	</table>
	<p>Total: ${users.size()}</p>
	<a href="/users/new">Agregar un usuario</a><br/>
	<a href="/logout">logout</a>
</body>
</html>