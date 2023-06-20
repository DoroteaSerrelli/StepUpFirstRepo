<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina Riservata</title>

</head>
<%
// Check user credentials
Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
if (isAdmin == null || isAdmin == false){	
    response.sendRedirect(request.getContextPath() + "/Login.jsp"); 
    return;
}
%>
<body>
<h1>Benvenuto nella Pagina Riservata</h1>

<p>
Congratulazioni! <br> Questa pagina è accessibile solo all'amministratore.
</p>
Le azioni che puoi fare sono:
<ul>
<li>Aggiornare il catalogo dei prodotti</li>
<li>Elencare i prodotti nel catalogo</li>
<li>Elencare gli ordini evasi </li>
</ul>
<p>
<a href="Login.jsp">Logout</a>
</p>

</body>
</html>

