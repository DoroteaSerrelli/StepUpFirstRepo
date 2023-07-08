<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type = "image/x-icon" href="/images/icon_site.ico">
<meta name = "viewport" content = "width:device-width, initial-scale = 1.0">
<link type = "text/css" rel = "stylesheet" href = "style/Header.css">
</head>
<body>
<header>
	<img class = "logo" src = "images/logo.png" width = "130" height = "200"/>
	<div id = "icon-container">
		<a href = "common/Wishlist.jsp"><img id = "iconw" src = "images/icon_wishlist2.png" width = "50" height = "50" onmouseout = "document.getElementById('iconw').src='images/icon_wishlist2.png'" onmouseover = "document.getElementById('iconw').src='images/icon_wishlist_focus.png'"></a>
		<a href = "common/Profilo.jsp"><img id = "iconp" src = "images/icon_profile2.png" width = "50" height = "50" onmouseout = "document.getElementById('iconp').src='images/icon_profile2.png'" onmouseover = "document.getElementById('iconp').src='images/icon_profile_focus.png'"></a>
		<a href = "Carrello.jsp"><img id = "iconc" src = "images/icon_carrello2.png" width = "50" height = "50" onmouseout = "document.getElementById('iconc').src='images/icon_carrello2.png'" onmouseover = "document.getElementById('iconc').src='images/icon_carrello_focus.png'"></a>
	</div>

<nav>
	<ul>
		<li> <a href = "Homepage.jsp">HOME</a></li>
		<li><a href = "Sport.jsp">SPORT</a></li>
		<li><a href = "Brand.jsp">BRAND</a></li>
		<li><a href = "Scarpe.jsp">SCARPE</a></li>
		<li><a href = "Moda.jsp">MODA</a></li>
		<li><a href = "Accessori.jsp">ACCESSORI</a></li>
		<li><a href = "Integratori.jsp">INTEGRATORI</a></li>
	</ul>
</nav>

<form id="search_mini_form" action="https://www.maxisport.com/catalogsearch/result/" method="get">
    <div id="algolia-searchbox">
        <label for="search">Cerca</label>
        <input id="search" type="text" name="searchBar" class="searchBar" autocomplete="off" spellcheck="false" autocapitalize="none" placeholder="Cerca nel sito...">
        <span class="clear-cross clear-query-autocomplete"></span>
        
    </div>
</form>

</header>
</body>
</html>