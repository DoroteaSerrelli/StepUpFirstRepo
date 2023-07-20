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
			<hr>
			<div id="addresses">
				<div id="addressRow0">
					<label for="address1">Indirizzo</label>
					<br>
					<input type = "hidden" name = "address1" value = "<%= i%>">
					<label for = "Via1">Via:</label>
					<input type="text" name="Via1" id="Via1" placeholder="Inserisci la via" required pattern="^[A-Za-z\s]+$"
						onchange="validateFormElem(this, document.getElementById('errorVia1'), viaErrorMessage)"><span id="errorVia1"></span>
					<br>
					<label for = "Civico1">Civico:</label>
					<input type="text" name="Civico1" id="Civico1" placeholder="Inserisci il numero civico" required pattern="^(([0-9])|([0-9]+|\w)(\w|[0-9]+))$"
						onchange="validateFormElem(this, document.getElementById('errorCivico1'), civicoErrorMessage)"><span id="errorCivico1"></span>
					<br>
					<label for = "Citta1">Città: </label>
					<input type="text" name="Citta1" id="Citta1" placeholder="Inserisci la città" required pattern="^[A-Za-z\s]+$"
						onchange="validateFormElem(this, document.getElementById('errorCitta1'), cittaErrorMessage)"><span id="errorCitta1"></span>
					<br>
					<label for = "Provincia1">Provincia: </label>
					<input type="text" name="Provincia1" id="Provincia1" placeholder="Inserisci la provincia" required pattern="^[A-Za-z\s]+$"
						onchange="validateFormElem(this, document.getElementById('errorProvincia1'), provinciaErrorMessage)"><span id="errorProvincia1"></span>
					<br>
					<label for = "CAP1">CAP: </label>
					<input type="text" name="CAP1" id="Cap1" placeholder="Inserisci il CAP" required pattern="^([0-9]{5})$"
						onchange="validateFormElem(this, document.getElementById('errorCap1'), capErrorMessage)"><span id="errorCap1"></span>
						
					<input type="button" value="+" onclick="addAddress()"><span id="errorAddress2"></span>
				</div>
				<%
					IndirizzoDAODataSource dao = new IndirizzoDAODataSource();
					Collection<IndirizzoDTO> addresses = dao.doRetrieveAll("idindirizzo", (String)session.getAttribute("username")); 
					if(addresses.size() > 0){%>
						<label for = "deleteAddress">Indirizzo che vuoi eliminare</label>
						<select name = "deleteAddress">
							<option value = ""></option>
						<%for(IndirizzoDTO dto : addresses){%>
								<option value = "<%= dto.getIDIndirizzo()%>"><%= dto.toString() %></option>
						<%} %>
						</select>
					
					<%}else{%>
						<input type = "hidden" name = "deleteAddress" value = "">
					<%} %>
			</div>
			<div>
				<input type = "submit" value = "Aggiorna il profilo" onclick="return validate()">
				<input type="reset" value="Reset">
			</div>		
		</fieldset>
		</form>
		<%@ include file="../Footer.jsp" %>
</body>
</html>