<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name = "viewport " content="initial-scale=1, width=device-width">
<title>Dashboard Admin</title>
</head>
<body>
<div id = "page">
<%@include file = "AdminHeader.jsp"%>
<h1>Pagina Riservata all'amministratore</h1>
<h2>Benvenuto!</h2>

<p>Le azioni che puoi fare sono:
<br> <a href = "UpdateCatalogo.jsp"><button class = "pulsante">Aggiornare il catalogo dei prodotti</button></a>
<br> <a href = "UpdateVetrina.jsp"><button class = "pulsante" >Aggiornare le vetrine</button></a>
<br> <a href = "OrdiniCommissionati.jsp"><button class = "pulsante">Elencare gli ordini commissionati</button></a>
</p>
<p>
<a href="<%=request.getContextPath()%>/common/Logout"><button class = "pulsante">Logout</button></a>
</p>

<%@include file = "AdminFooter.jsp"%>
</div>
</body>
</html>