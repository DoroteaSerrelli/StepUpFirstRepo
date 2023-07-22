<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "dao.IndirizzoDAODataSource, dao.UserAccountDAODataSource, dao.ProfileDAODataSource, model.IndirizzoDTO, 
    model.UserAccountDTO, model.ProfileDTO, model.Carrello, java.util.*, model.ItemCarrello, control.GetProductTopImage" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordine</title>
<script src="<%= request.getContextPath()%>/scripts/commonscripts.js"></script>
<script src="<%=request.getContextPath()%>/scripts/Validazione.js"></script>
</head>
<body>

	<h1>Compila il form per acquistare i tuoi prodotti</h1>
	<form name = "Ordine" method = "POST" action = "ManageOrdine">
	<%
	UserAccountDAODataSource accountDao =  new UserAccountDAODataSource();
		UserAccountDTO account = accountDao.doRetrieveByKey((String) request.getSession().getAttribute("username"));
		IndirizzoDAODataSource indirizzoDao = new IndirizzoDAODataSource();
		Collection<IndirizzoDTO> indirizzi = indirizzoDao.doRetrieveAll("VIA", account.getUsername());
		ProfileDTO profilo = new ProfileDTO();
		ProfileDAODataSource profiledao = new ProfileDAODataSource();
		profilo = profiledao.doRetrieveByUsername( (String)request.getSession().getAttribute("username"));
		if (profilo != null) {
	%>
	<div class="tableRow">
		<p>
			Nome:<%=profilo.getNome()%></p>
	</div>
	<div class="tableRow">
		<p>
			Cognome:<%=profilo.getCognome()%></p>
	</div>

	<div class="tableRow">
		<p>
			Email:<%=profilo.getEmail()%></p>
	</div>

	<div class="tableRow">
		<p>
			Telefono:
			<%=profilo.getTelefono()%></p>
	</div>

	<div class="tableRow">
		<p>Indirizzo</p>
		<label for="indirizzo">Scegli l'indirizzo:</label> <select
			name="indirizzo">
			<%
			for (IndirizzoDTO i : indirizzi) {
			%>
			<option value="<%=i.getIDIndirizzo()%>"><%=i.toString()%>
				<%
				}
				%>
			
		</select>
		<p>
			Se i dati inseriti non sono corretti oppure vuoi modificare la lista
			dei tuoi indirizzi, ti invitiamo ad aggiornare i dati presso la
			tua <a href="<%=request.getContextPath()%>/common/AreaRiservata.jsp">area
				riservata</a>.
		</p>

	</div>
	
	<div class="tableRow">
		<p>
			<button class = "pulsante" type = "submit" >Ordina</button>
		</p>
	</div>

	<%
	} else {
	%>
	<p>I dati che compilerai per effettuare l'acquisto verranno inseriti nella tua area riservata. 
	Potrai modificarli nella tua area riservata per gli acquisti successivi nel nostro sito.</p>
	<div class="tableRow">
		<label for="Nome">Nome:</label><input type="text" name="Nome"
			id="firstname" required pattern="^[A-Za-z\s]+$"
			onchange="validateFormElem(this, document.getElementById('errorName'), nameOrLastnameErrorMessage)"><span
			id="errorName"></span>
	</div>
	<div class="tableRow">
		<label for="Cognome">Cognome:</label><input type="text" name="Cognome"
			id="lastname" required pattern="^[A-Za-z\s]+$"
			onchange="validateFormElem(this, document.getElementById('errorLastname'), nameOrLastnameErrorMessage)"><span
			id="errorLastname"></span>
	</div>

	<div class = "tableRow">
		<label for="Email">Email:</label><input type="email" name="Email"
			required
			onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)"
			id="email"><span id="errorEmail"></span>
	</div>
	<div class="tableRow">
		<label for="Telefono">Telefono:</label><input type="tel"
			name="Telefono" pattern="^([0-9]{3}-[0-9]{3}-[0-9]{4})$" required
			onchange="validateFormElem(this, document.getElementById('errorPhone'), phoneErrorMessage)"
			id="phone"><span id="errorPhone"></span>
	</div>
	<hr>
	<div id="addresses" class="tableRow">
		<div id="addressRow0">
			<label for="address1">Indirizzo</label> <br> <input
				type="hidden" name="address1" value="1"> <label for="Via1">Via:</label>
			<input type="text" name="Via1" id="Via1"
				placeholder="Inserisci la via" required pattern="^[A-Za-z\s]+$"
				onchange="validateFormElem(this, document.getElementById('errorVia1'), viaErrorMessage)"><span
				id="errorVia1"></span> <br> <label for="Civico1">Civico:</label>
			<input type="text" name="Civico1" id="Civico1"
				placeholder="Inserisci il numero civico" required
				pattern="^(([0-9])|([0-9]+|\w)(\w|[0-9]+))$"
				onchange="validateFormElem(this, document.getElementById('errorCivico1'), civicoErrorMessage)"><span
				id="errorCivico1"></span> <br> <label for="Citta1">Città:
			</label> <input type="text" name="Citta1" id="Citta1"
				placeholder="Inserisci la città" required pattern="^[A-Za-z\s]+$"
				onchange="validateFormElem(this, document.getElementById('errorCitta1'), cittaErrorMessage)"><span
				id="errorCitta1"></span> <br> <label for="Provincia1">Provincia:
			</label> <input type="text" name="Provincia1" id="Provincia1"
				placeholder="Inserisci la provincia" required
				pattern="^[A-Za-z\s]+$"
				onchange="validateFormElem(this, document.getElementById('errorProvincia1'), provinciaErrorMessage)"><span
				id="errorProvincia1"></span> <br> <label for="CAP1">CAP:
			</label> <input type="text" name="CAP1" id="Cap1"
				placeholder="Inserisci il CAP" required pattern="^([0-9]{5})$"
				onchange="validateFormElem(this, document.getElementById('errorCap1'), capErrorMessage)"><span
				id="errorCap1"></span>

		</div>
	</div>
	<div class="tableRow">
		<p></p>
		<p>
			<input type="submit" value="Ordina" onclick = "return validate()">Ordina
		</p>
	</div>
	</form>
	
	<%
	}
	%>
	<aside>
		<div id="cartSidebar">
			<p>Prodotti da acquistare</p>
			<%
			double costo = 0.0;
			Carrello cart = ((Carrello) request.getSession().getAttribute("Carrello"));
			%>
			<p>
				Numero di articoli:
				<%=cart.getNumProdotti()%></p>
			<table>
				<tr>
					<th></th>
					<th>Nome</th>
					<th>Quantità</th>
					<th>Prezzo complessivo</th>
				</tr>
				<%
				List<ItemCarrello> items = cart.getProducts();

				for (ItemCarrello i : items) {
				%>
				<tr>
					<td><img class="TopProduct"
						src="../GetProductTopImage?Codice=<%=i.getIDProdotto()%>"
						onerror="this.src='<%=request.getContextPath()%>/images/NoPhotoAvailable.jpg'"
						style="width: 100px; height: 100px" alt="Product_image" /></td>
					<td><%=i.getNomeProdotto()%></td>
					<td><%=i.getQuantità()%></td>
					<td>
						<%
						costo += Math.round(i.getPrezzo() * i.getQuantità() * 100.00) / 100.00;
						%><%=Math.round(i.getPrezzo() * i.getQuantità() * 100.00) / 100.00%></td>
				</tr>
				<%
				}
				%>
			</table>
			<p>
				Costo totale:
				<%=Math.round(costo * 100.00) / 100.00%></p>
		</div>
	</aside>
</body>
</html>