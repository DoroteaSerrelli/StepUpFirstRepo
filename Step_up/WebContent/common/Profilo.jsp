<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, dao.IndirizzoDAODataSource, model.IndirizzoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiornamento profilo</title>
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<script src = "<%=request.getContextPath()%>/scripts/Validazione.js"></script>
</head>
<body>
<%@include file = "../Header.jsp" %>
<form class = "richieste" id = "datiPersonali" name = "datiPersonali" action = "UpdateProfile" enctype = "application/x-www-form-urlencoded" method = "POST">
		<input type= "hidden" name = "action" value = "updateProfile">
		<h2 class = "form-header">Informazioni personali</h2>
		<%! int i = 1;%>
			<div>
				<label for="Nome">Nome:</label><input type="text"
					name="Nome" id="firstname" required pattern="^[A-Za-z\s]+$"
					onchange="validateFormElem(this, document.getElementById('errorName'), nameOrLastnameErrorMessage)"><span
					id="errorName"></span>
			</div>
			<div>
				<label for="Cognome">Cognome:</label><input type="text"
					name="Cognome" id="lastname" required pattern="^[A-Za-z\s]+$"
					onchange="validateFormElem(this, document.getElementById('errorLastname'), nameOrLastnameErrorMessage)"><span
					id="errorLastname"></span>
			</div>
			<div>
				<label for = "sesso">Sesso</label><br>
				<input type = "radio" name = "Sesso" value = "Uomo">Uomo
				<br>
				<input type = "radio" name = "Sesso" value = "Donna">Donna
				<br>
				<input type = "radio" name = "Sesso" value = "Non specificare">Non specificare
			</div>
			<div>
				<label for="Email">Email:</label><input type="email" name="Email"
					required
					onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)"
					id="email"><span id="errorEmail"></span>
			</div>
			<div>
				<label for="Telefono">Telefono:</label><input type="tel" name="Telefono" pattern = "^([0-9]{3}-[0-9]{3}-[0-9]{4})$"
					required
					onchange="validateFormElem(this, document.getElementById('errorPhone'), phoneErrorMessage)"
					id="phone"><span id="errorPhone"></span>
			</div>
			<div>
				<button class = "pulsante" type = "submit" onclick="return validateDati()">Aggiorna il profilo</button>
				<button class = "pulsante" type = "reset">Reset</button>
				
			</div>		
		</form>
		<%@ include file="../Footer.jsp" %>
</body>
</html>