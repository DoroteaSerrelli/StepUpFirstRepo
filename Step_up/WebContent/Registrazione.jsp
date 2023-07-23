<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<script src = "<%=request.getContextPath() %>/scripts/registrazione.js"></script>
<title>Registrazione</title>
</head>
<body>
<%@include file="Header.jsp"%>
	<div id="page">

		<form id = "account" class = "richieste" action="CreateAccount" name="account" method="POST">
			<h2>Creazione account</h2>
			<p>Unisciti a noi!<br> Compila i seguenti campi del form per creare
				il tuo account su Step Up.</p>
			<div id="tableRow">
				<label for="username">Username: </label> <input type="text"
					name="username" id = "username" placeholder="Username" required pattern = "^[A-Za-z]{1,25}$" 
					onchange="validateFormElem(this, document.getElementById('errorUsername'), usernameErrorMessage)"><span
					id="errorUsername"></span>
			</div>
			<div id="tableRow">
				<label for="password">Password: </label> <input type="password"
					name="password" id = "password" placeholder="Password" required pattern = "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,}$"
					onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)">
					<span id="errorPassword"></span>	
			</div>
			<button class="pulsante" type="submit" onclick = "validateDati()" name="Iscriviti">Iscriviti</button>

		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>