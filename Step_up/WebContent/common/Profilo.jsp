<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, dao.IndirizzoDAODataSource, model.IndirizzoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiornamento profilo</title>
<script src = "<%=request.getContextPath()%>/scripts/Validazione.js"></script>
</head>
<body>
<%@include file = "../Header.jsp" %>
<form name = "datiPersonali" action = "UpdateProfile" enctype = "application/x-www-form-urlencoded" method = "POST">
		<input type= "hidden" name = "action" value = "updateProfile">
		<fieldset>
		<legend>Informazioni personali</legend>
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
				<input type = "submit" value = "Aggiorna il profilo" onclick="return validateDati()">
				<input type="reset" value="Reset">
			</div>		
		</fieldset>
		</form>
		<%@ include file="../Footer.jsp" %>
</body>
</html>