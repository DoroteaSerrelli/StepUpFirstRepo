<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content = "width:device-width, initial-scale = 1.0">
<title>Step up - Homepage</title>
<script src = "scripts/jquery-3.6.0.js"></script>
<script src = "<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<script src = "scripts/BannerPubblicitario.js"></script>
</head>
<body>
<%@include file = "Header.jsp" %>
	<div id = "page">
		<div id = "banner"></div>
	</div>

<%@ include file="Footer.jsp" %>
</body>
</html>