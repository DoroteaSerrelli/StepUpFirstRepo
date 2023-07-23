<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, model.PagamentoDTO, model.ItemCarrello, model.ProductDTO, model.OrdineDTO, model.ProfileDTO, dao.ProductDAODataSource, dao.PagamentoDAODataSource, dao.OrdineDAODataSource, dao.ProfileDAODataSource, dao.IndirizzoDAODataSource, model.IndirizzoDTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<title>Area riservata</title>
</head>
<body>
	<%@include file = "../Header.jsp" %>

	<h1>Area riservata</h1>

	<p>
		Benvenuto nella tua area riservata.<br> Qui puoi visionare il tuo
		profilo: i tuoi dati personali e gli ordini effettuati
	</p>
	<fieldset>

		<legend>Dati personali</legend>
			<%
			ProfileDAODataSource dao = new ProfileDAODataSource();
			ProfileDTO bean = dao.doRetrieveByUsername((String) request.getSession().getAttribute("username"));
			IndirizzoDAODataSource address = new IndirizzoDAODataSource();
			Collection<IndirizzoDTO> addressesBean = address.doRetrieveAll("IDIndirizzo", bean.getUsername());
			%>

			<div class = "profileTable">
				<div class = "profileRow">Nome:
				<%= bean.getNome() %></div>
				<div class = "profileRow">Cognome:
				<%= bean.getCognome() %></div>
				<div class = "profileRow">
				Email: <%= bean.getEmail() %>
				</div>
				<div class = "profileRow">
				Telefono:
				<%= bean.getTelefono() %>
				</div>
				<div class = "profileRow">
				Sesso:
				<%= bean.getSesso() %>
				</div>
			</div>
				<fieldset>

		<legend>Indirizzi</legend>
				<br>
				<%	if(addressesBean != null){
					for(IndirizzoDTO dt : addressesBean){%>
					<p>Indirizzo n. <%= dt.getIDIndirizzo()%>: <%= dt.toString() %></p>
			<% }}else{ %>
				<p>Non hai un indirizzo inserito</p>
				<%} %>
	</fieldset>
		<a href="<%= request.getContextPath() %>/common/Profilo.jsp"><button class = "pulsante" >Aggiorna i dati personali</button></a>
		<a href="<%= request.getContextPath() %>/common/Indirizzi.jsp"><button class = "pulsante" >Aggiorna gli indirizzi</button></a>
		

	</fieldset>
	<fieldset>
		<legend>Ordini effettuati</legend>
		<%
		OrdineDAODataSource ordineDao = new OrdineDAODataSource();
		Collection<OrdineDTO> ordini = ordineDao.doRetrieveForUser(bean.getUsername());
		PagamentoDAODataSource pagamentoDao = new PagamentoDAODataSource();
		if(ordini != null && ordini.size() > 0){%>
			<table>
				<tr>
				<th>Numero ordine</th>
				<th>Importo</th>
				<th>Prodotti</th>
				<th>Data Ordine</th>
				<th>Ora ordine</th>
				<th>Metodo di spedizione</th>
				</tr>
				<% for(OrdineDTO o: ordini){
					Collection <ItemCarrello> items = ordineDao.doRetrieveAllProducts(o.getIDOrdine());
					ProductDAODataSource daoProduct = new ProductDAODataSource();
					String products = "";
					for(ItemCarrello i: items){
						ProductDTO product = daoProduct.doRetrieveByKey(i.getIDProdotto());
						products = products + " \n" + product.ordertoString() + "[prezzo di acquisto = "+
								i.getPrezzo()+"], [quantità = "+ i.getQuantità()+"]";
					}
				%>
				<tr>
					<td><%= o.getIDOrdine() %></td>
					<td><%= (pagamentoDao.doRetrieveByOrderID(o.getIDOrdine())).getImporto() %></td>
					<td><%= products.toString() %></td>
					<td><%= o.getDataOrdine() %></td>
					<td><%= o.getOraOrdine() %></td>
					<td><%= o.getMetSpedizione() %></td>
				</tr>
					
				<% }%>
			</table>
		<%}else{%>
			<p>Non hai acquistato prodotti nel nostro sito. Guarda le nostre vetrine nella nostra <a href= "<%=request.getContextPath() %>/Homepage.jsp">homepage</a>.</p>
		<%}
		%>
	</fieldset>

	<a href="<%= request.getContextPath() %>/common/Logout"><button class = "pulsante">Logout</button></a>
</body>
</html>