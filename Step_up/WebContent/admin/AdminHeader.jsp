<link rel = "stylesheet" type = "text/css" href = "<%=request.getContextPath()%>/style/Admin.css">
<header>
<img class = "logo" src = "<%= request.getContextPath()%>/images/logo.png" width = "130" height = "200"/>
<nav id="breadcrumb">
  <ul class="breadcrumb">
    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/admin/DashboardAdmin.jsp">Dashboard</a></li>
    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/admin/UpdateCatalogo.jsp">Catalogo prodotti</a></li>
    <li class="breadcrumb-item"><a href = "<%=request.getContextPath()%>/admin/UpdateVetrina.jsp">Vetrine</a></li>
    <li class="breadcrumb-item"><a href = "<%=request.getContextPath()%>/admin/OrdiniCommissionati.jsp">Gestione ordini</a></li>
  </ul>
</nav>

</header>