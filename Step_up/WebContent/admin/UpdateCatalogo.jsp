<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "dao.ProductDAODataSource, model.ProductDTO,dao.IBeanProductDAO, java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiornamento catalogo</title>
<%@include file = "../Header.jsp" %>
</head>
<body>
<%  ProductDAODataSource dao = new ProductDAODataSource();
	Collection<ProductDTO> products = dao.doRetrieveAll("idprodotto"); %>
	
	<h2>Prodotti</h2>
	<a href="product">List</a>
	<table border="1">
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductDTO bean = (ProductDTO) it.next();
		%>
		<tr>
			<td><%=bean.getIDProdotto()%></td>
			<td><img src="../GetProductTopImage?Codice=<%=bean.getIDProdotto()%>" onerror="this.src='../images/NoPhotoAvailable.jpg'" style="width:100px;height:100px"></td>
			<td><%=bean.getNomeProdotto()%></td>
			<td><%=bean.getDescrizione_breve()%></td>
			
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Nessun prodotto disponibile</td>
		</tr>
		<%
			}
		%>
	</table>
	<h2>Inserimento prodotto</h2>
	<br>
	<h3>Inserimento immagine</h3>
	<form action="UploadProductTopImage" enctype="multipart/form-data" method="post">
		Nome prodotto: 
		<select name="id">
			<%
			if (products != null && products.size() > 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductDTO item = (ProductDTO) it.next();
			%>
			<option value="<%=item.getIDProdotto()%>"><%=item.getNomeProdotto()%></option>
			<%
			}
			}
			%>
		</select> <br> 
		<input class="file" type="file" name="talkPhoto" value="" maxlength="255"> <br> 
		<button id = "pulsante" type="submit" value="Upload"></button>
		<button id = "pulsante" type="reset">Reset</button>
	</form>
	<h3>Inserimento dati</h3>
	<form name = "inserimento" action="Catalogo" method="POST">
		<input type = "hidden" name = "action" value = "insert">
		
		<label for="Codice">Codice:</label><br> 
		<input name="Codice" type="text" required placeholder="Inserisci codice prodotto"><br> 
		
		<label for="Nome">Nome:</label><br> 
		<input name="Nome" type="text" required placeholder="Inserisci nome prodotto"><br> 
		
		<label for="DescrizioneBreve">Descrizione:</label><br>
		<textarea name="DescrizioneBreve" maxlength="100" rows="3" required placeholder="enter description"></textarea><br>
		
		<label for="Prezzo">Prezzo:</label><br> 
		<input name="Prezzo" type="number" min="0" value="" required><br>

		<label for="Categoria">Categoria:</label><br> 
		<input name="Categoria" type="text" required><br>

		<button id = "pulsante" type="submit">Aggiungi</button><input type="reset" value="Reset">
	</form>
	
	<h2>Cancellazione di un prodotto</h2>
	<form name = "inserimento" action="Catalogo" method="POST">
		<input type = "hidden" name = "action" value = "delete">
		
		<label for="Codice">Codice:</label><br> 
		<input name="Codice" type="text" required placeholder="Inserisci codice prodotto"><br> 
		
		<button id = "pulsante" type="submit">Cancella</button><input type="reset" value="Reset">
	</form>
	
	<h2>Modificare un prodotto - inserisci i valori nei campi che vuoi modificare</h2>
	<form name = "inserimento" action="Catalogo" method="POST">
		<input type = "hidden" name = "action" value = "update">
		
		<label for="Codice">Codice:</label><br> 
		<input name="Codice" type="text" required placeholder="Inserisci codice prodotto"><br> 
		
		<label for="Nome">Nome:</label><br> 
		<input name="Nome" type="text" placeholder="Inserisci nome prodotto"><br> 
		
		<label for="DescrizioneBreve">Descrizione:</label><br>
		<textarea name="DescrizioneBreve" maxlength="100" rows="3" placeholder="enter description"></textarea><br>
		
		<label for="Prezzo">Prezzo:</label><br> 
		<input name="Prezzo" type="number" min="0" value="0"><br>

		<label for="Categoria">Categoria:</label><br> 
		<input name="Categoria" type="text"><br>

		<button id = "pulsante" type="submit">Modifica</button><input type="reset" value="Reset">
	</form>
	
	<%@ include file="../Footer.jsp" %>
</body>
</html>