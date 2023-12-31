<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content = "width:device-width, initial-scale = 1.0">
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<script src = "<%=request.getContextPath() %>/scripts/servizioClienti.js"></script>
<title>Servizio clienti</title>
</head>
<body>
<%@include file = "Header.jsp" %>
<div id = "page">



	<h2>Contattaci</h2>
	
	<p class = "information">Compila il seguente form e sarai contattato dal nostro Customer Service</p>
	<aside>
	<h3>Preferisci contattare un nostro operatore?</h3>
	<p>Il nostro Customer Service è a tua disposizione per rispondere alle tue domande,<br>
	   al numero di telefono: 089.12345678 
	   <br>e all' indirizzo e-mail: helpdesk@stepup.it
	   <br>nei seguenti orari:
	   <br>
		Da Lunedì a Sabato: 9.30 - 13.00 ; 14.30 - 19.30
		Domenica: 10.00 - 13.00 ; 14.30 - 19.30
	</p>
	</aside>
		<form class = "richieste" id = "servizioClienti" name = "servizioClienti" action = "ServizioClienti" method = "post">
			<div id = "tableRow">
				<label for = "Nome">Nome: </label>
					<input id = "Nome" type = "text" name = "Nome" required value = "" placeholder = "Nome" pattern="^[A-Za-z\s]+$" onchange="validateFormElem(this, document.getElementById('errorName'), nameOrLastnameErrorMessage)"/>
					<span id = "errorName"></span>
					<br>
			</div>
			<div id = "tableRow">
				<label for = "Cognome">Cognome: </label>
					<input id = "Cognome" type = "text" name = "Cognome" required value = "" placeholder = "Cognome" pattern="^[A-Za-z\s]+$" onchange="validateFormElem(this, document.getElementById('errorSurname'), nameOrLastnameErrorMessage)"/><span id = "errorSurname"></span>
					<br>
			</div>
			<div id = "tableRow">
				<label for = "Email">Email: </label>
					<input id = "Email" type = "email" name = "Email" value = "" placeholder = "Indirizzo email" required pattern = "^\S+@\S+\.\S+$" onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)" />
					<span id="errorEmail"></span><br>
			</div>
			<div id = "tableRow">
				<label for = "Telefono">Telefono: </label>
					<input id = "Telefono" type = "tel" name = "Telefono" value = "" placeholder = "Telefono" required pattern = "^\d{3}-\d{3}-\d{4}$" onchange = "validateFormElem(this, document.getElementById('errorPhone'), phoneErrorMessage)"/>
					<span id = "errorPhone"></span>
					<br>
			</div>
			<div id = "tableRow">
				<label for = "Motivo"> Motivo del contatto</label>
					<br>
					<select name = "Motivo" required>
						<option value = "DettagliProdotto">Desidero ricevere informazioni tecniche sui prodotti</option>
						<option value = "Disponibilità">Desidero ricevere informazioni su disponibilità di prodotto e conversioni taglie</option>
						<option value = "StatoOrdine">Desidero ricevere informazioni sullo stato del mio ordine</option>
						<option value = "Pagamento">Desidero ricevere informazioni su spese di spedizione e le modalità di pagamento</option>
						<option value = "Reso">Desidero effettuare un reso</option>
						<option value = "BuoniSconto">Desidero ricevere assistenza per l’utilizzo di un codice o buono sconto</option>
					</select>
				<br>
			</div>
			<div id = "tableRow">
				<label for = "Messaggio">Messaggio: </label><br>
					<textarea id = "Messaggio" name = "Messaggio" placeholder = "Inserisci un messaggio di richiesta" required onchange = "validateFormElem(this, document.getElementById('errorQuestionMessage'), questionErrorMessage)"></textarea>
				<span id = "errorQuestionMessage"></span>
			</div>
			<button class = "pulsante" type = "button" onclick = "validateDati()">Invia Richiesta</button>
		</form>

	<div id = "InfCustomerService">
		<small>Ti ricordiamo che i dati forniti verranno trattati a mezzo di strumenti informatici <br>
		al fine di dare un seguito alla richiesta od indicazione. <br>
		Step up, in qualità di titolare del trattamento dei dati, assicura che i dati forniti <br>
		verranno utilizzati solamente per gli scopi indicati, nel pieno rispetto del <br>
		Regolamento Europeo 2016/679 sulla tutela della Privacy.</small>
	</div>
</div>	
<%@ include file="Footer.jsp" %>
</body>
</html>