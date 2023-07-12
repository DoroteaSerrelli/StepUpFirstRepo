<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "dao.WishlistDAODataSource, model.WishlistDTO, model.ProductDTO, java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Wishlist</title>
</head>
<body>
<%
	WishlistDAODataSource dao = new WishlistDAODataSource();
	String user = (String)(request.getSession()).getAttribute("username");
	WishlistDTO dto = dao.doRetrieveWishlistByKey(user);
	Collection<ProductDTO> wishProducts = dao.doRetrieveAll("IDPRODOTTO", dto);
%>
<div id = "page">
	
	
	<div id = "wishlist">
		<% if(wishProducts.size() > 0){
			for(ProductDTO p: wishProducts){%>
			<a href="<%=request.getContextPath() %>/Prodotto.jsp?Codice=<%= p.getIDProdotto() %>">
				<p><img src = "GetProductTopImage?Codice=<%= p.getIDProdotto()%>"/></p>
				<p>Nome: <%= p.getNomeProdotto() %> </p>
				<p>Categoria: <%=p.getCategoria() %></p>
				<p>Brand: <%=p.getBrand() %></p>
				<p>Prezzo: <%=p.getPrezzo() %></p>
				<a href = "ManageWishlist?action=delete&codice=<%= p.getIDProdotto() %>"><button type = "button"></button></a>
				</a>
			<%}
		}else{
		%>
		<p>La tua wishlist è vuota</p>
		<%} %>
	</div>
</div>

</body>
</html>