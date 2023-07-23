<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "dao.ProductDAODataSource, model.ProductDTO,dao.IBeanIntDAO, dao.PhotoControl, java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiornamento catalogo</title>
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src = "<%= request.getContextPath()%>/scripts/adminscripts.js"></script>
</head>
<body>
<%@include file = "AdminHeader.jsp" %>
<%  ProductDAODataSource dao = new ProductDAODataSource();
	Collection<ProductDTO> products = dao.doRetrieveAllExistent("idprodotto"); %>
	
	<h2>Prodotti</h2>

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
		<%	LinkedList<Integer> photos = PhotoControl.loadGalleryPhotos(bean.getIDProdotto());
			for(int iter: photos){%>
				<tr><td><img src = "../GetProductImages?CodiceP=<%=bean.getIDProdotto()%>&CodiceI=<%= iter%>" onerror="this.src='../images/NoPhotoAvailable.jpg'" style="width:100px;height:100px" /></td></tr>
			<%}
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
	<h3>Inserimento dati</h3>
	<form name = "inserimento" action="Catalogo" method="POST">
		<input type = "hidden" name = "action" value = "insert">
		
		<label for="Codice">Codice:</label><br> 
		<input name="Codice" type="text" required placeholder="Inserisci codice prodotto"><br> 
		
		<label for="Nome">Nome:</label><br> 
		<input name="Nome" type="text" required placeholder="Inserisci nome prodotto"><br> 
		
		<label for="DescrizioneBreve">Descrizione breve:</label><br>
		<textarea name="DescrizioneBreve" cols = "100" rows="10" required placeholder="Inserisci una breve descrizione del prodotto..."></textarea><br>
		
		<label for="DescrizioneDettagliata">Descrizione dettagliata:</label><br>
		<textarea name="DescrizioneDettagliata" cols = "100" rows="10" required placeholder="Inserisci una descrizione dettagliata del prodotto..."></textarea><br>
		
		<label for="Brand">Brand:</label><br> 
		<input name="Brand" type="text" required placeholder="Inserisci il brand"><br> 
		
		<label for="Prezzo">Prezzo:</label><br> 
		<input name="Prezzo" type="number" step = "0.01" value="" required><br>

		<label for="Categoria">Categoria:</label><br> 
		<select name = "Categoria">
			<option value = "Sport">Sport</option>
			<option value = "Moda">Moda</option>
			<option value = "Scarpe">Scarpe</option>
			<option value = "Accessori">Accessori</option>
			<option value = "Integratori">Integratori</option>
		</select>
		<br><br>
		<button class = "pulsante" type="submit">Aggiungi</button>
		<button class = "pulsante " type="reset">Reset</button>
	</form>
	<h3>Inserimento immagine di evidenza</h3>
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
		<button class = "pulsante" type="submit" value="Upload">Caricamento immagine</button>
		<button class = "pulsante" type="reset">Reset</button>
	</form>
	<h3>Inserimento immagine in galleria immagini del prodotto</h3>
	<form action="UploadProductImages" enctype="multipart/form-data" method="post">
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
		<button class = "pulsante" type="submit" value="Upload">Caricamento</button>
		<button class = "pulsante" type="reset">Reset</button>
	</form>
	
	<h2>Cancellazione di un prodotto</h2>
	<form name = "inserimento" action="Catalogo" method="POST">
		<input type = "hidden" name = "action" value = "delete">
		<select name = "Codice">
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
		
		<button class = "pulsante" type="submit">Cancella</button><input type="reset" value="Reset">
	</form>
	
	<h2>Cancellazione di un'immagine dalla galleria delle immagini di un prodotto</h2>
	<form name = "deleteImages" action="DeleteProductImages" method="POST">
		Nome prodotto: 
		<select name = "Codice" id = "selectImage">
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
		<fieldset id = "image-container"></fieldset>
		
		<button class = "pulsante" type="submit">Rimuovi</button><input type="reset" value="Reset">
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
		
		<label for="DescrizioneDettagliata">Descrizione dettagliata:</label><br>
		<textarea name="DescrizioneDettagliata" cols = "100" rows="10" placeholder="Inserisci una descrizione dettagliata del prodotto..."></textarea><br>
		
		<label for="Brand">Brand:</label><br> 
		<input name="Brand" type="text" placeholder="Inserisci il brand"><br> 
		
		<label for="Prezzo">Prezzo:</label><br> 
		<input name="Prezzo" type="number" step = "0.01" value=""><br>
		
		<label for="Categoria">Categoria:</label><br> 
		<select name = "Categoria">
			<option value=""></option>
			<option value="Sport">Sport</option>
			<option value="Moda">Moda</option>
			<option value="Scarpe">Scarpe</option>
			<option value="Accessori">Accessori</option>
			<option value="Integratori">Integratori</option>
		</select>
	<br><br>
		<button class = "pulsante" type="submit">Modifica</button><input type="reset" value="Reset">
	</form>
	
	<%@ include file="AdminFooter.jsp" %>
</body>
</html>