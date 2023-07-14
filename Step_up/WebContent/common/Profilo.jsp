<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiornamento profilo</title>
<script src = "../scripts/Validate.js"></script>
<%@include file = "../Header.jsp" %>
</head>
<body>

<form name = "datiPersonali" action = "UpdateProfile" enctype = "application/x-www-form-urlencoded" method = "POST">
		<fieldset>
		<legend>Informazioni personali</legend>
		<%! int i = 1;%>
			<div>
				<label for="Nome">Nome:</label><input type="text"
					name="Nome" id="firstname" required pattern="^[A-Za-z]+[\s\w]+$"
					onchange="validateFormElem(this, document.getElementById('errorName'), nameOrLastnameErrorMessage)"><span
					id="errorName"></span>
			</div>
			<div>
				<label for="Cognome">Cognome:</label><input type="text"
					name="Cognome" id="lastname" required pattern="^[A-Za-z]+[\s\w]+$"
					onchange="validateFormElem(this, document.getElementById('errorLastname'), nameOrLastnameErrorMessage)"><span
					id="errorLastname"></span>
			</div>
			<div>
				<label for = "sesso">Sesso</label><br>
				<input type = "radio" name = "sesso" value = "Uomo">Uomo
				<br>
				<input type = "radio" name = "sesso" value = "Donna">Donna
			</div>
			<div>
				<label for="Email">Email:</label><input type="email" name="Email"
					required
					onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)"
					id="email"><span id="errorEmail"></span>
			</div>
			<div>
				<label for="Telefono">Telefono:</label><input type="tel" name="Telefono"
					required
					onchange="validateFormElem(this, document.getElementById('errorPhone'), phoneErrorMessage)"
					id="phone"><span id="errorPhone"></span>
			</div>
			<hr>
			<div id="addresses">
				<div id="addressRow0">
					<label for="address1">Indirizzo</label>
					<input type = "hidden" name = "address1" value = "<%= i%>">
					<label for = "Via">Via:</label>
					<input type="text" name="Via" id="via1" placeholder="Inserisci la via" required pattern="^[A-Za-z]+[\s\w]+$"
						onchange="validateFormElem(this, document.getElementById('errorVia1'), viaErrorMessage)"><span id="errorVia1"></span>
					<input type="text" name="Civico" id="civico1" placeholder="Inserisci il numero civico" required pattern="^([0-9])$"
						onchange="validateFormElem(this, document.getElementById('errorCivico1'), civicoErrorMessage)"><span id="errorCivico1"></span>
					<input type="text" name="Citta" id="Citta1" placeholder="Inserisci la città" required pattern="^[A-Za-z]+[\s\w]+$"
						onchange="validateFormElem(this, document.getElementById('errorCitta1'), cittaErrorMessage)"><span id="errorCitta1"></span>
					<input type="text" name="Provincia" id="Provincia1" placeholder="Inserisci la provincia" required pattern="^[A-Za-z]+[\s\w]+$"
						onchange="validateFormElem(this, document.getElementById('errorProvincia1'), provinciaErrorMessage)"><span id="errorProvincia1"></span>
					<input type="text" name="CAP" id="Cap1" placeholder="Inserisci il CAP" required pattern="^([0-9]{5}$"
						onchange="validateFormElem(this, document.getElementById('errorCap1'), capErrorMessage)"><span id="errorCap1"></span>
						
					<input type="button" value="+" onclick="addAddress()"><span id="errorAddress2"></span>
				</div>
			</div>
			<div>
				<input type="submit" value="Register" onclick="return validate()">
				<input type="reset" value="Reset">
			</div>
						
			<input type = "text" name = "<%=i%>">
		</fieldset>
		<input type = "submit" value = "Aggiorna il profilo">
		</form>
		
		
		<%@ include file="../Footer.jsp" %>
</body>
</html>