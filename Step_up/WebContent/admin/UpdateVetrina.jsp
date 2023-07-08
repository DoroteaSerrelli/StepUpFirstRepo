<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.ProductDTO, model.VetrinaDTO, dao.VetrinaDAODataSource, dao.IBeanProductDAO, dao.ProductDAODataSource, java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiornare vetrine</title>
</head>
<body>

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
	
	<h2>Sei intenzionato ad aggiungere una nuova vetrina?</h2>
	<h2>Sei intenzionato a cancellare una vetrina?</h2>
	<h2>Sei intenzionato a modificare una vetrina (aggiungere prodotto, eliminare un prodotto)?</h2>
	
	<h2>Inserimento vetrina</h2>
	<form name = "inserimento" action="ManageVetrina" method="POST">
		<input type = "hidden" name = "action" value = "insert">
		
		<label for="Codice">Codice:</label><br> 
		<input name="Codice" type="text" required placeholder="Inserisci codice vetrina"><br> 
		
		<label for="Nome">Nome:</label><br> 
		<input name="Nome" type="text" required placeholder="Inserisci nome vetrina"><br> 
		
		<fieldset>
			<legend>Prodotti</legend>
			<% 
				ProductDAODataSource pdao = new ProductDAODataSource();
			   Collection<ProductDTO> products = pdao.doRetrieveAll("IDPRODOTTO");
			   if(products.size() != 0){
				   for(ProductDTO p: products){ %>
				   	<label for = "product"><%= p.getNomeProdotto() %> (Codice : <%=p.getIDProdotto() %>)</label>
					   <input type = "checkbox" name = "product" value = "<%= p.getIDProdotto()%>"><br>
				   <%}
			   }else{%>
			   		<p>Nessun prodotto disponibile</p>
				   
			   <%}%>
		</fieldset>

		<button id = "pulsante" type="submit">Aggiungi</button><input type="reset" value="Reset">
	</form>
	

</body>
</html>