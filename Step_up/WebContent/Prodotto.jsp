<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, dao.PhotoControl, dao.ProductDAODataSource, model.ProductDTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prodotto</title>
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<script src = "<%= request.getContextPath()%>/scripts/Product_page.js"></script>
<script src = "<%=request.getContextPath() %>/scripts/Cart.js"></script>
</head>
<body>

	<%@include file = "Header.jsp" %>
	<div id = "page">
	<%int codice = Integer.parseInt(request.getParameter("codice"));
	  ProductDAODataSource dao = new ProductDAODataSource();
	  ProductDTO product = dao.doRetrieveByKey(codice);
	%>
	<div class="product-image">
		<img src="GetProductTopImage?Codice=<%= product.getIDProdotto()%>" onerror="this.src='<%=request.getContextPath()%>/images/NoPhotoAvailable.jpg'" style="width:150px;height:150px" alt="Product_image">
	<%	LinkedList<Integer> photos = PhotoControl.loadGalleryPhotos(product.getIDProdotto());
			for(int iter: photos){%>
			<img src = "GetProductImages?CodiceP=<%=product.getIDProdotto()%>&CodiceI=<%= iter%>" onerror="this.src='<%=request.getContextPath()%>/images/NoPhotoAvailable.jpg'" style="width:150px;height:150px" />
			<%}%>
	</div>
	<div class="product-details">
		<h1><%= product.getNomeProdotto()%></h1>
		<p><%=product.getDescrizione_breve() %></p>
		<button id="show-more-btn" >Mostra di più</button>
  		<div id="detailed-description" style="display: none;">
    		<p><%= product.getDescrizione_dettagliata() %></p>
		</div>
		<p>Prezzo: <%= product.getPrezzo() %></p>
		<button class = "carrello-button" id = "<%=product.getIDProdotto()%>" type="button" name="Acquista" onclick = "insertCart(this, productAdded<%=product.getIDProdotto()%>)">Aggiungi al carrello</button>
		<a href = "ManageWishlist?action=insert&codice=<%=product.getIDProdotto() %>" ><button class = "pulsante" type = "button">Aggiungi nella wishlist</button></a>
		<br><span id="productAdded<%=product.getIDProdotto()%>"></span><br>
		</div>
	</div>
	<%@include file = "Footer.jsp" %>
</body>
</html>