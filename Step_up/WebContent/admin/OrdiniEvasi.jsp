<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "dao.ProfileDAODataSource, model.ProfileDTO;"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordini evasi</title>
</head>
<body>
	<%	OrdineDAODataSource dao = new OrdineDAODataSource();
		Collection<OrdineDTO> ordini = dao.doRetrieveAll();
		ProfileDAODataSource daop = new ProfileDAODataSource();
	%>
	
	<h2>Ordini commissionati</h2>
	<table border = "1">
		<tr>
			<th>ID ordine</th>
			<th>Data</th>
			<th>Ora</th>
			<th>Metodo di spedizione</th>
			<th>Metodo di consegna</th>
			<th>Committente</th>
		</tr>
		<% if((ordini != null) && (ordini.size()!= 0)){
				for(OrdineDTO ordine: ordini){%>
					<tr>
						<td><%=ordine.getIDOrdine() %></td>
						<td><%=ordine.getDataOrdine() %></td>
						<td><%=ordine.getOraOrdine() %></td>
						<td><%=ordine.getMetodoSpedizione() %></td>
						<td><%=ordine.getMetodoConsegna() %></td>
						<%ProfileDTO person = daop.doRetrieveByKey(ordine.getUsernameOrdine()); %>
						<td><%= person.getNome()%> <%= person.getCognome()%></td>
					</tr>
					
				<%}
		}else{%>
			<tr>
				<td colspan = "6">Nessun ordine commissionato</td>
			</tr>
		<% }%> 
			
	</table>
	
	<label for = "filter">Ordina per </label>
	<select name = "filter">
			<option>Data</option> <!-- selezionato deve mostrare un campo di inserimento data -->
			<option>Cliente</option> <!-- selezionato deve mostrare un campo di inserimento nome e cognome -->
	</select>
	
</body>
</html>