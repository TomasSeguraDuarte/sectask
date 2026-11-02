<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar User</title>
</head>
<body>
	<h1>Editar User</h1>
	<form
		action="${pageContext.request.contextPath}/users/update/${user.id}"
		method="post">
		Username: <input type="text" name="username" value="${user.username}" /><br />
		
		Activo:
		<select name="active">
			<option value="true"  ${user.active? 'selected' : ''}>Activo</option>
			<option value="false" ${!user.active ? 'selected' : ''}>Inactivo</option>
		</select><br />
		
		Rol:
		<select name="rol">
			<c:forEach var="r" items="${roles}">
				<option value="${r.role}"
					<c:forEach var="mio" items="${user.role}">
						<c:if test="${mio.role == r.role}">selected</c:if>
					</c:forEach>>
				${r.role}
				</option>
			</c:forEach>
		</select>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Update" />
	</form>
</body>
</html>