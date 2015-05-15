<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Categorie"%>
<div class="col-lg-3" id="principal-content"><!--Colonne-->
<% 
    for (Categorie categorie : Base.GetCategories()) 
    {
        String Nav_lien_href = Base.CONTEXT_PATH+"/Catalogue?IDcategorie="+ categorie.ID;
        String Nav_lien_nom = categorie.Libelle;
%>
    <a class="row" href="<%= Nav_lien_href %>">  <%= Nav_lien_nom %> </a>
<%
    }
%>
</div>
