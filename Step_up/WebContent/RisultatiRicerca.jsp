<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, model.ProductDTO, org.json.simple.JSONArray, org.json.simple.JSONObject"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<title>Risultati ricerca</title>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Cart.js"></script>
</head>
<body>
	<%@include file="Header.jsp"%>

	<div id="page">
		<div id="results">
			<h1>Risultati della ricerca</h1>
			<%Collection<ProductDTO> prodotti = (Collection<ProductDTO>) request.getAttribute("risultati");
				if(prodotti == null){%>
			<p>Nessun risultato</p>
			<%}else{
					for (ProductDTO p : prodotti) {%>

			<div id="product-field">
				<img class="TopProduct"
					src="GetProductTopImage?Codice=<%=p.getIDProdotto()%>"
					onerror="this.src='<%=request.getContextPath()%>/images/NoPhotoAvailable.jpg'"
					style="width: 100px; height: 100px" alt="Product_image" />
				<h5><%=p.getNomeProdotto()%></h5>
				<h6>
					Prezzo:
					<%=p.getPrezzo()%></h6>

				<button class="carrello-button" id="<%=p.getIDProdotto()%>"
					type="button" name="Acquista"
					onclick="insertCart(this, productAdded<%=p.getIDProdotto()%>)">Aggiungi
					al carrello</button>

				<a
					href="<%=request.getContextPath()%>/common/ManageWishlist?action=insert&codice="><button
						class="wishlist-button" id="<%=p.getIDProdotto()%>" type="button"
						name="Wishlist" onclick="">Aggiungi alla wishlist</button></a> <a
					href="<%=request.getContextPath()%>/Prodotto.jsp?codice=<%=p.getIDProdotto()%>"><button
						class="details-button">Dettagli</button></a>
			</div>
			<br>
			<span id="productAdded<%=p.getIDProdotto()%>"></span><br>
			<%}	
			}%>

		</div>
	</div>

	<%@include file="Footer.jsp"%>
</body>
</html>