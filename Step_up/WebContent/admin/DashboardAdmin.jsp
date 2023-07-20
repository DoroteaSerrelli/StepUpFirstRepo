<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard Admin</title>
<link rel = "stylesheet" type = "text/css" href = "../style/admin.css">
</head>
<body>
<%@include file = "AdminHeader.jsp"%>
<h1>Benvenuto nella Pagina Riservata</h1>

<p>
Congratulazioni! <br> Questa pagina è accessibile solo all'amministratore.
</p>
Le azioni che puoi fare sono:
<br> <a href = "UpdateCatalogo.jsp"><button id = "pulsante">Aggiornare il catalogo dei prodotti</button></a>
<br> <a href = "UpdateVetrina.jsp"><button id = "pulsante" >Aggiornare le vetrine</button></a>
<br> <a href = "OrdiniCommissionati.jsp"><button id = "pulsante">Elencare gli ordini commissionati</button></a>

<p>
<a href="<%=request.getContextPath()%>/common/Logout">Logout</a>
</p>

<%@include file = "AdminFooter.jsp"%>
</body>
</html>