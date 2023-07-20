<link type = "text/css" rel = "stylesheet" href = "<%= request.getContextPath()%>/style/Header.css">
<link type = "text/css" rel = "stylesheet" href = "<%= request.getContextPath()%>/style/Responsive.css">
<link rel="shortcut icon" type="image/ico" href="<%=request.getContextPath() %>/images/icon_site.ico"/>
<header>
	<img class = "logo" src = "<%= request.getContextPath()%>/images/logo.png" width = "130" height = "200"/>
	<div id = "icon-container">
		<a href = "<%= request.getContextPath()%>/common/Wishlist.jsp"><img id = "iconw" src = "<%= request.getContextPath()%>/images/icon_wishlist2.png" width = "50" height = "50" onmouseout = "document.getElementById('iconw').src='<%= request.getContextPath()%>/images/icon_wishlist2.png'" onmouseover = "document.getElementById('iconw').src='<%= request.getContextPath()%>/images/icon_wishlist_focus.png'"></a>
		<a href = "<%= request.getContextPath()%>/common/Profilo.jsp"><img id = "iconp" src = "<%= request.getContextPath()%>/images/icon_profile2.png" width = "50" height = "50" onmouseout = "document.getElementById('iconp').src='<%= request.getContextPath()%>/images/icon_profile2.png'" onmouseover = "document.getElementById('iconp').src='<%= request.getContextPath()%>/images/icon_profile_focus.png'"></a>
		<a href = "<%= request.getContextPath()%>/Carrello.jsp"><img id = "iconc" src = "<%= request.getContextPath()%>/images/icon_carrello2.png" width = "50" height = "50" onmouseout = "document.getElementById('iconc').src='<%= request.getContextPath()%>/images/icon_carrello2.png'" onmouseover = "document.getElementById('iconc').src='<%= request.getContextPath()%>/images/icon_carrello_focus.png'"></a>
	</div>

<nav>
	<ul>
		<li> <a href = "<%= request.getContextPath()%>/Homepage.jsp">HOME</a></li>
		<li><a href = "<%= request.getContextPath()%>/Sport.jsp">SPORT</a></li>
		<li><a href = "<%= request.getContextPath()%>/Brand.jsp">BRAND</a></li>
		<li><a href = "<%= request.getContextPath()%>/Scarpe.jsp">SCARPE</a></li>
		<li><a href = "<%= request.getContextPath()%>/Moda.jsp">MODA</a></li>
		<li><a href = "<%= request.getContextPath()%>/Accessori.jsp">ACCESSORI</a></li>
		<li><a href = "<%= request.getContextPath()%>/Integratori.jsp">INTEGRATORI</a></li>
	</ul>
</nav>

<div id="searchBar_field">
    <div id="algolia-searchbox">
        <label for="search">Cerca</label>
        <input id="searchBar" type="text" name="searchBar" autocomplete="off" spellcheck="false" autocapitalize="none" placeholder="Cerca nel sito..." onchange = "searching()">
        <span class="clear-cross clear-query-autocomplete"></span>
    </div>
</div>

</header>