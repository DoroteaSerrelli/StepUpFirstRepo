<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.ProfileDTO,dao.ProfileDAODataSource"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Area riservata</title>
</head>
<body>


	<h1>Area riservata</h1>

	<p>
		Benvenuto nella tua area riservata.<br> Qui puoi visionare il tuo
		profilo: i tuoi dati personali e gli ordini effettuati
	</p>
	<fieldset>

		<legend>Dati personali</legend>
		<jsp:useBean id="bean" class="model.ProfileDTO" scope="page">
			<%ProfileDAODataSource dao = new ProfileDAODataSource();
	bean = dao.doRetrieveByKey((String)session.getAttribute("username"));
%>

			<p>
				Nome:
				<%= bean.getNome() %>
				Cognome:
				<%= bean.getCognome() %>
				Telefono:
				<%= bean.getTelefono() %>
				Sesso:
				<%= bean.getSesso() %>
			</p>
		</jsp:useBean>
		<a href="<%= request.getContextPath() %>/common/Profilo.jsp">Aggiorna i
			dati personali</a>


	</fieldset>
	<fieldset>
<legend>Ordini effettuati</legend>
</fieldset>

<a href="<%= request.getContextPath() %>/common/Logout">Logout</a>
</body>
</html>