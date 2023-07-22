<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "dao.ProductDAODataSource, model.ProductDTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prodotto</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src = "<%= request.getContextPath()%>/scripts/Product_page.js"></script>
<script src = "<%=request.getContextPath() %>/scripts/Cart.js"></script>
</head>
<body>
	<%int codice = Integer.parseInt(request.getParameter("codice"));
	  ProductDAODataSource dao = new ProductDAODataSource();
	  ProductDTO product = dao.doRetrieveByKey(codice);
	%>
	<div class="product-image">
		<img src="GetProductTopImage?Codice=<%= product.getIDProdotto()%>" onerror="this.src='<%=request.getContextPath()%>/images/NoPhotoAvailable.jpg'" style="width:100px;height:100px" alt="Product_image">
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
		<a href = "ManageWishlist?action=insert&codice=<%=product.getIDProdotto() %>" ><button id = "pulsante" type = "button">Aggiungi nella wishlist</button></a>
		<br><span id="productAdded<%=product.getIDProdotto()%>"></span><br>
		
	</div>
</body>
</html>