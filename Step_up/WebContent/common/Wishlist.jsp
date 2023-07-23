<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "dao.WishlistDAODataSource, model.WishlistDTO, model.ProductDTO, java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wishlist</title>
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
</head>
<body>
<%
	WishlistDAODataSource dao = new WishlistDAODataSource();
	String user = (String)(request.getSession()).getAttribute("username");
	WishlistDTO dto = dao.doRetrieveWishlistByKey(user);
	Collection<ProductDTO> wishProducts = dao.doRetrieveAll("IDPRODOTTO", dto);
%>
<%@include file = "../Header.jsp" %>
<div id = "page">
	
	<img class = "logo" src = "<%=request.getContextPath()%>/images/wishlist_phrase.png" width = "300" height = "200">
	<div id = "wishlist">
		<% if(wishProducts.size() > 0){
			for(ProductDTO p: wishProducts){%>
			<div id="product-field">
			<img class="TopProduct"
				src="../GetProductTopImage?Codice=<%= p.getIDProdotto()%>"
				onerror="this.src='<%=request.getContextPath() %>/images/NoPhotoAvailable.jpg'"
				style="width: 100px; height: 100px" alt="Product_image" />
				<p>Nome: <%= p.getNomeProdotto() %> </p>
				<p>Categoria: <%=p.getCategoria() %></p>
				<p>Brand: <%=p.getBrand() %></p>
				<p>Prezzo: <%=p.getPrezzo() %></p>
				<a href = "ManageWishlist?action=delete&codice=<%= p.getIDProdotto() %>"><button class = "wishlist-button" type = "button">Cancella da wishlist</button></a>
				<a href="<%=request.getContextPath() %>/Prodotto.jsp?codice=<%= p.getIDProdotto() %>"><button class = "details-button">Dettagli</button></a>
			<%}
		}else{
		%>
		<p>La tua wishlist è vuota</p>
		<%} %>
	</div>
</div>
</div>
<%@include file = "../Footer.jsp" %>
</body>
</html>