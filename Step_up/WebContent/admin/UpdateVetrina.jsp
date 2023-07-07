<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiornare vetrine</title>
</head>
<body>

<%	VetrinaDAODataSource dao = new VetrinaDAODataSource();
	Collection <VetrinaDTO> vetrine = dao.doRetrieveAll("nomevetrina");  
	%>
	
	<h2>Vetrine del sito web esposte</h2>
	<table border="1">
	<tr>
		<th>Nome vetrina</th>
		<th>Numero prodotti</th>
	</tr>
		<% if((vetrine != null) && (vetrine.size() != 0)){
			for(VetrinaDTO v: vetrine){%>
			<tr>
				<td> <%= v.getNomeVetrina() %> </td>
				<td> <%= v.getNumProdotti() %></td>
			</tr>		
			<%}
			}else{%>
		<tr>
			<td colspan = "2">Non ci sono vetrine definite nel sito</td>
		</tr>
		<%}%>
	</table>
	
	<h2>Sei intenzionato ad aggiungere una nuova vetrina?</h2>
	<h2>Sei intenzionato a cancellare una vetrina?</h2>
	<h2>Sei intenzionato a modificare una vetrina?</h2>


</body>
</html>