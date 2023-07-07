<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.ProductDTO,dao.ProductDAODataSource, java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name = "viewport" content = "width:device-width, initial-scale = 1.0">
<title>Scarpe</title>
</head>
<%@include file = "Header.jsp" %>
</head>
<body>

	<%ProductDAODataSource pdao = new ProductDAODataSource();
Collection<ProductDTO> products = pdao.doRetrieveAll("nomeprodotto");

%>

<div id = "page">
	<%
		if(!products.isEmpty())
		{
			for(ProductDTO p: products){
				if(p.getCategoria().equals("Integratori")){%>
				<div id = "product">
					<img class = "TopProduct" src = "images/product-image/<%= p.getTopImage() %>"/>
					<h5><%= p.getNomeProdotto() %></h5>
					<h6>Prezzo: <%=p.getPrezzo() %></h6>
					<h6>Descrizione breve: <%=p.getDescrizione_breve() %></h6>
					<button type = "button" name = "Acquista" value = "Aggiungi al carrello"></button>
					<button type = "button" name = "Wishlist" value = "Aggiungi alla wishlist"></button>
				</div>
			<%}
			}
		}
	%>

</div>


</div>	

<%@ include file="Footer.jsp" %>
</body>
</html>