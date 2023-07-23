<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, dao.ProfileDAODataSource, dao.OrdineDAODataSource, model.ProfileDTO, model.OrdineDTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<title>Ordini commissionati</title>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src = "<%= request.getContextPath()%>/scripts/adminscripts.js"></script>

</head>
<body>
<%@include file = "AdminHeader.jsp" %>
	<label for = "filter">Scegli l'ordine con cui visualizzare gli ordini commissionati</label>
	<select name = "filter" onchange = "filter()">
		<option value = ""></option>
		<option value = "DataOrdine">Data</option>
		<option value = "Username">Utente</option>
	</select>
	
	<div id = "divFieldDate" style = "display:none">
	<label for = "Inizio">Data di inizio</label>
		<input type = "text" name = "Inizio" id = "Inizio" value = "" required placeholder = "Data di inizio" pattern = "^\d{4}-\d{2}-\d{2}$" onchange = "controllo(this, document.getElementById('errorInizio'), errorData)">
		<span id = "errorInizio"></span>
	<br>
	<label for = "Fine">Data di fine</label>
		<input type = "text" name = "Fine" id = "Fine" value = "" required placeholder = "Data di fine" pattern = "^\d{4}-\d{2}-\d{2}$" onchange = "controllo(this, document.getElementById('errorFine'), errorData)">
		<span id = "errorFine"></span>
		<button class = "pulsante" onclick = "validDateFields(); getOrdersForDate();">Filtra</button>
	</div>
	
	<div id = "divFieldUser" style = "display:none">
			<%  ProfileDAODataSource profiledao = new ProfileDAODataSource();
				Collection<ProfileDTO> users = profiledao.doRetrieveAll("COGNOME"); 
				if(users.size()>0){%>
					<label for = "Utente"> Utente: </label>
						<select name = "Utente" id = "Utente" onchange = "getUserOrders()">
						<%for(ProfileDTO p: users){%>
							<option value = "<%= p.getUsername() %>"><%= p.toString()%></option>
						<%}%>
						</select>
				<%}else{%>
					<p>Nessun utente ancora registrato al sito</p>
				<%} %>
				<button class = "pulsante" onclick = "getUserOrders();">Esegui</button>
	</div>
	
	<div id = "OrdersTable">
	</div>
	<%@include file = "AdminFooter.jsp" %>
</body>
</html>