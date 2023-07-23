<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "model.ProductDTO,dao.ProductDAODataSource, java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<title>Sport</title>
<script src = "<%=request.getContextPath() %>/scripts/Cart.js"></script>
</head>
<body>
	<%@include file="Header.jsp"%>

	<%ProductDAODataSource pdao = new ProductDAODataSource();
Collection<ProductDTO> products = pdao.doRetrieveAllExistent("nomeprodotto");

%>

	<div id="page">
		<%
		if (!products.isEmpty()) {
			for (ProductDTO p : products) {
				if (p.getCategoria().equals("Sport")) {
		%>
		<div id="product-field">
			<img class="TopProduct"
				src="GetProductTopImage?Codice=<%=p.getIDProdotto()%>"
				onerror="this.src='<%=request.getContextPath()%>/images/NoPhotoAvailable.jpg'"
				style="width: 100px; height: 100px" alt="Product_image" />
			<h5><%=p.getNomeProdotto()%></h5>
			<h6>
				Prezzo:
				<%=p.getPrezzo()%></h6>

			<button class = "carrello-button" id = "<%=p.getIDProdotto()%>" type="button" name="Acquista" onclick = "insertCart(this, productAdded<%=p.getIDProdotto()%>)">Aggiungi al carrello</button>
			<a
				href="<%=request.getContextPath()%>/common/ManageWishlist?action=insert&codice=<%=p.getIDProdotto()%>"><button
					class = "wishlist-button" type="button" name="Wishlist">Aggiungi alla wishlist</button></a>
					<a
					href="<%=request.getContextPath()%>/Prodotto.jsp?codice=<%=p.getIDProdotto()%>"><button
						class="details-button">Dettagli</button></a>
		
		</div>
		<br><span id="productAdded<%=p.getIDProdotto()%>"></span><br>

		<%
		}
		}
		}
		%>

	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>