<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.ProductDTO,dao.ProductDAODataSource, java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name = "viewport" content = "width:device-width, initial-scale = 1.0">
<title>Sport</title>
</head>
<body>
<%@include file = "Header.jsp" %>

	<%ProductDAODataSource pdao = new ProductDAODataSource();
Collection<ProductDTO> products = pdao.doRetrieveAll("nomeprodotto");

%>

<div id = "page">
	<%
		if(!products.isEmpty())
		{
			for(ProductDTO p: products){
				if(p.getCategoria().equals("Sport")){%>
		<div id="product">
				<img class="TopProduct"
					src="GetProductTopImage?Codice=<%= p.getIDProdotto()%>" onerror="this.src='<%=request.getContextPath() %>/images/NoPhotoAvailable.jpg'" style="width:100px;height:100px" alt="Product_image" />
				<h5><%=p.getNomeProdotto()%></h5>
				<h6>
					Prezzo:
					<%=p.getPrezzo()%></h6>
				<h6>
					Descrizione breve:
					<%=p.getDescrizione_breve()%></h6>
				<a href = "ManageCarrello?action=insert"><input type="button" name="Acquista" value="Aggiungi al carrello"></a>
				<a href = "<%=request.getContextPath() %>/common/ManageWishlist?action=insert&codice=<%= p.getIDProdotto()%>"><input type="button" name="Wishlist" value="Aggiungi alla wishlist"></a>
				<a href="Prodotto.jsp?Codice=<%= p.getIDProdotto() %>"><input type="button" name="Wishlist" value="Dettagli"></a>
			</div>
			
		<%}
			}
		}%>

</div>	

<%@ include file="Footer.jsp" %>
</body>
</html>