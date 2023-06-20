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
if (isAdmin == null){	
    response.sendRedirect(request.getContextPath() + "/login.jsp"); 
    return;
}
%>
<body>
<h1>Benvenuto nella Pagina Riservata</h1>

<p>
Congratulazioni! <br> Questa pagina è accessibile sia all'utente registrato che all'amministratore.
</p>

<p>
<a href="Login.jsp">Logout</a>
</p>

</body>
</html>

