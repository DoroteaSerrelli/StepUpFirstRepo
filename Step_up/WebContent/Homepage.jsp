<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, model.ProductDTO, dao.VetrinaDAODataSource, model.VetrinaDTO"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<title>Step up - Homepage</title>
<script src="scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<script src="scripts/BannerPubblicitario.js"></script>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div id="banner"></div>
	<div id="page">
		<%
		VetrinaDAODataSource vdao = new VetrinaDAODataSource();
		Collection<VetrinaDTO> vetrine = vdao.doRetrieveAll("NOMEVETRINA");

		if (!vetrine.isEmpty()) {
		%>
		<h3>Benvenuto nel sito Step Up</h3>
		<p>Dai uno sguardo alle nostre vetrine, naviga nelle categorie
			prodotti e ricerca i prodotti di tuo interesse</p>
		<%
		for (VetrinaDTO v : vetrine) {
		%>
		<div class="vetrina">
			<h4><%=v.getNomeVetrina()%></h4>
			<%
			Collection<ProductDTO> prodotti = vdao.doRetrieveAllProducts("IDVETRINA", v.getIDVetrina());
			for (ProductDTO p : prodotti) {
			%>

			<div id="product-field">
				<img class="TopProduct"
					src="GetProductTopImage?Codice=<%=p.getIDProdotto()%>"
					onerror="this.src='<%=request.getContextPath()%>/images/NoPhotoAvailable.jpg'"
					style="width: 100px; height: 100px" alt="Product_image" />
				<h5><%=p.getNomeProdotto()%></h5>
				<h5>
					Categoria:
					<%=p.getCategoria()%></h5>
				<h6>
					Prezzo:
					<%=p.getPrezzo()%></h6>
				<button class="carrello-button" id="<%=p.getIDProdotto()%>"
					type="button" name="Acquista"
					onclick="insertCart(this, productAdded<%=p.getIDProdotto()%>)">Aggiungi
					al carrello</button>
				<a
					href="<%=request.getContextPath()%>/common/ManageWishlist?action=insert&codice=<%=p.getIDProdotto()%>"><button
						class="wishlist-button" type="button" name="Wishlist">Aggiungi
						alla wishlist</button></a> <a
					href="<%=request.getContextPath()%>/Prodotto.jsp?codice=<%=p.getIDProdotto()%>"><button
						class="details-button">Dettagli</button></a>
			</div>
			<br>
			<span id="productAdded<%=p.getIDProdotto()%>"></span><br>

			<%
			}
			}
			} else {
			%>
			<p>Naviga nelle nostre sezioni del sito per acquistare i prodotti
				o usae la nostra barra di ricerca.</p>
			<%
			}
			%>

		</div>
	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>