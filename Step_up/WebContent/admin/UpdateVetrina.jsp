<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.ProductDTO, model.VetrinaDTO, dao.VetrinaDAODataSource, dao.IBeanVetrinaDAO, dao.IBeanIntDAO, dao.ProductDAODataSource, java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiornare vetrine</title>
<meta name = "viewport " content="initial-scale=1, width=device-width">
<script src = "<%= request.getContextPath()%>/scripts/adminscripts.js"></script>
</head>
<body>
<div id = "page">
<%@include file = "AdminHeader.jsp"%>
<%	VetrinaDAODataSource dao = new VetrinaDAODataSource();
	Collection <VetrinaDTO> vetrine = dao.doRetrieveAll("idvetrina");
	%>
	
	<h2>Vetrine del sito web esposte</h2>
	<table border="1">
	<tr>
		<th>Nome vetrina</th>
		<th>Codice</th>
		<th>Numero prodotti</th>
	</tr>
		<% if((vetrine != null) && (vetrine.size() != 0)){
			for(VetrinaDTO v: vetrine){%>
			<tr>
				<td> <%= v.getNomeVetrina() %> </td>
				<td><%= v.getIDVetrina() %></td>
			<td>
				<%
				if ((dao.doRetrieveAllProducts("idprodotto", v.getIDVetrina())) == null) {
				%>
				0 <%
				} else {
				%> <%=(dao.doRetrieveAllProducts("idprodotto", v.getIDVetrina()).size())%>
				<%
				}
				%>
			</td>
		</tr>		
			<%}
			}else{%>
		<tr>
			<td colspan = "3">Non ci sono vetrine definite nel sito</td>
		</tr>
		<%}%>
	</table>
	
	
	
	<form name = "inserimento" action="ManageVetrina" method="POST" accept-charset="utf-8">
	<h4>Per aggiungere una nuova vetrina compila i dati del seguente form</h4>
		<input type = "hidden" name = "action" value = "insert">
		<div class="form-tableRow">
		<label for="Codice">Codice:</label><br> 
		<input name="Codice" type="text" required placeholder="Inserisci codice vetrina" onblur = "checkValue('inserimento', 'Codice'); showProducts('inserimento');"><br> 
		</div>
		<div class="form-tableRow">
		<label for="Nome">Nome:</label><br> 
		<input name="Nome" type="text" required placeholder="Inserisci nome vetrina"><br> 
		</div>
		<div class="form-tableRow">
		<fieldset id = "addProductFieldset" style = "display: none">
		</fieldset>
		</div>
		<div class="form-tableRow">
		<button class = "pulsante" type="submit">Aggiungi</button>
		<button class = "pulsante" type = "reset">Reset</button>
		</div>
	</form>
	
	<h2>Sei intenzionato a cancellare una vetrina? Cancellazione vetrina</h2>
	<form name = "cancellazione" action="ManageVetrina" method="POST">
		<input type = "hidden" name = "action" value = "delete">
		
		<label for="Codice">Codice:</label><br> 
		<input name="Codice" type="text" required placeholder="Inserisci codice vetrina"><br>  
	<button id = "pulsante" type="submit">Elimina</button><input type="reset" value="Reset">
	</form>
	
	<h2>Sei intenzionato a modificare i prodotti presenti in una vetrina?</h2>
	<form name = "aggiornamento" action="ManageVetrina" method="POST">
		<input type = "hidden" name = "action" value = "update">
		
		<label for="Codice">Codice:</label><br> 
		<input name="Codice" type="text" required placeholder="Inserisci codice vetrina" onblur = "checkValue('aggiornamento', 'Codice'); fetchProducts('aggiornamento'); showOtherProducts('aggiornamento');"><br> 
		
		<fieldset id = "productFieldset" style = "display: none">
		</fieldset>
		
		<fieldset id = "addOtherProductFieldset" style = "display: none">
		</fieldset>

		<button id = "pulsante" type="submit">Aggiorna la vetrina</button><input type="reset" value="Reset">
	</form>
	<%@include file = "AdminFooter.jsp" %>
	</div>
</body>
</html>