<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup - Step Up</title>
</head>
<body>
<h1>Creazione account</h1>
<p>Unisciti a noi! Compila i seguenti campi del form per creare il tuo account su Step Up.</p>
<form action = "CreateAccount" name = "account" method = "POST">
	Username: <input type = "text" name = "username" placeholder = "Username" maxlength="50">
    Password: <input type = "password" name = "password" placeholder = "Password">
    <input type = "submit" name = "Iscriviti">
          
</form>
</body>
</html>