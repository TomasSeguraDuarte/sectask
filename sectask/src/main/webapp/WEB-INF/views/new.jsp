<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear usuario</title>
</head>
<body>
	<h1> Agrega Nuevo Usuario</h1>
	<form action="/users/create" method="POST">
		Usuario: <input type="text" name="username"/><br/>
		Contraseña: <input type="text" name="password"/><br/>
		<label for="roles"> seleccione los roles a asignar:</label>
		<select name="rolesSeleccionados" id="roles" multiple>
  			<option value="CREATOR">CREATOR</option>
  			<option value="EDITOR">EDITOR</option>
  			<option value="USER">USER</option>
		</select>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Guardar"/>
	</form>

</body>
</html>