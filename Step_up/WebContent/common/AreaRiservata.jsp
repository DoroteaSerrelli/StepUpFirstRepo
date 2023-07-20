<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, model.ProfileDTO,dao.ProfileDAODataSource, dao.IndirizzoDAODataSource, model.IndirizzoDTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Area riservata</title>
</head>
<body>
	<%@include file = "../Header.jsp" %>

	<h1>Area riservata</h1>

	<p>
		Benvenuto nella tua area riservata.<br> Qui puoi visionare il tuo
		profilo: i tuoi dati personali e gli ordini effettuati
	</p>
	<fieldset>

		<legend>Dati personali</legend>
		<jsp:useBean id="bean" class="model.ProfileDTO" scope="page">
			<%
			ProfileDAODataSource dao = new ProfileDAODataSource();
			bean = dao.doRetrieveByKey((String) session.getAttribute("username"));
			IndirizzoDAODataSource address = new IndirizzoDAODataSource();
			Collection<IndirizzoDTO> addressesBean = address.doRetrieveAll("IDIndirizzo", bean.getUsername());
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
				Indirizzi:
				<%for(IndirizzoDTO dt : addressesBean){%>
					<p>Indirizzo n. <%= dt.getIDIndirizzo()%>: <%= dt.toString() %></p>
			<% } %>

			</p>
		</jsp:useBean>
		<a href="<%= request.getContextPath() %>/common/Profilo.jsp"><button class = "pulsante" >Aggiorna i dati personali</button></a>


	</fieldset>
	<fieldset>
<legend>Ordini effettuati</legend>
</fieldset>

<a href="<%= request.getContextPath() %>/common/Logout"><button class = "pulsante">Logout</button></a>
</body>
</html>