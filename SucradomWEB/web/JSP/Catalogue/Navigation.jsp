<%@page import="sucradom.utile.Base"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="sucradom.dao.CategorieDAO"%>
<%@page import="sucradom.metier.Categorie"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
NAVIGATION
<% 
    for (Categorie categorie : Base.GetCategories()) 
    {
        String Nav_lien_href = Base.CONTEXT_PATH+"/Index?JSPfolder=Catalogue&IDcategorie="+ categorie.ID;
        String Nav_lien_nom = categorie.Libelle;
%>
        <a class="row" href="<%= Nav_lien_href %>">  <%= Nav_lien_nom %> </a>
<%
    }
%>