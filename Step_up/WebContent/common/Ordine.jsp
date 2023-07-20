<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "dao.ProfileDAODataSource, model.ProfileDTO, model.Carrello, java.util.*, model.ItemCarrello, control.GetProductTopImage" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordine</title>
<script src = "<%= request.getContextPath()%>/scripts/commonscripts.js"></script>
</head>
<body>
	
	<h1>Compila il form per acquistare i tuoi prodotti</h1>
	<% ProfileDTO profilo = new ProfileDTO();
	ProfileDAODataSource profiledao = new ProfileDAODataSource();
	profilo = profiledao.doRetrieveByKey( (String)request.getSession().getAttribute("username"));
	if(profilo != null){%>
		<div class="tableRow">
			<p>Name:</p>
			<p>
				<input type="text" name="name">
			</p>
		</div>

		<div class="tableRow">
			<p>Address:</p>
			<p>
				<input type="text" name="address">
			</p>
		</div>

		<div class="tableRow">
			<p>City:</p>
			<p>
				<input type="text" name="city">
			</p>
		</div>

		<div class="tableRow">
			<p>State:</p>
			<p>
				<input type="text" name="state">
			</p>
		</div>

		<div class="tableRow">
			<p>Zip:</p>
			<p>
				<input type="text" name="zip">
			</p>
		</div>

		<div class="tableRow">
			<p>Phone:</p>
			<p>
				<input type="tel" name="phone">
			</p>
		</div>
		
	<%}
	%>
	<div class="tableRow">
			<p>Name:</p>
			<p>
				<input type="text" name="name">
			</p>
		</div>

		<div class="tableRow">
			<p>Address:</p>
			<p>
				<input type="text" name="address">
			</p>
		</div>

		<div class="tableRow">
			<p>City:</p>
			<p>
				<input type="text" name="city">
			</p>
		</div>

		<div class="tableRow">
			<p>State:</p>
			<p>
				<input type="text" name="state">
			</p>
		</div>

		<div class="tableRow">
			<p>Zip:</p>
			<p>
				<input type="text" name="zip">
			</p>
		</div>

		<div class="tableRow">
			<p>Phone:</p>
			<p>
				<input type="tel" name="phone">
			</p>
		</div>

		<div class="tableRow">
			<p></p>
			<p>
				<input type="submit" value="Ordina">
			</p>
		</div>
	<aside>
		<div id = "cartSidebar">
		<p>Prodotti da acquistare</p>
		<% double costo = 0.0;
			Carrello cart = ((Carrello)request.getSession().getAttribute("Carrello"));   %>
		<p> Numero di articoli: <%= cart.getNumProdotti() %></p>
		<table>
		<tr>
					<th></th>
					<th>Nome</th>
					<th>Quantità</th>
					<th>Prezzo complessivo</th>
				</tr>
		<% List<ItemCarrello> items = cart.getProducts();
		
					for (ItemCarrello i : items) {
				%>
				<tr>
					<td><img class="TopProduct"
						src="../GetProductTopImage?Codice=<%=i.getIDProdotto()%>"
						onerror="this.src='<%=request.getContextPath()%>/images/NoPhotoAvailable.jpg'"
						style="width: 100px; height: 100px" alt="Product_image" /></td>
					<td><%=i.getNomeProdotto()%></td>
					<td><%=i.getQuantità() %></td>
					<td><%costo += Math.round(i.getPrezzo() * i.getQuantità()*100.00)/100.00;%><%= Math.round(i.getPrezzo() * i.getQuantità() * 100.00)/100.00%></td>
				</tr>
				<%} %>
			</table>
			<p>Costo totale: <%= Math.round(costo*100.00)/100.00 %></p>
		</div>
	</aside>
</body>
</html>