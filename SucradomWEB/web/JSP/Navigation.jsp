<%-- 
    Document   : Navigation
    Created on : 27 avr. 2015, 09:25:41
    Author     : user
--%>

<%@page import="sucradom.utile.Base"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="sucradom.dao.CategorieDAO"%>
<%@page import="sucradom.metier.Categorie"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
    NAVIGATION
<% 
    ArrayList<Categorie> listeCategories = (ArrayList<Categorie>) request.getAttribute("Categories");
    for (Categorie categorie : listeCategories) 
    {
        String lien_href = Base.CONTEXT_PATH+"/Home?Type=Categorie&ID="+ categorie.ID;
        String lien_nom = categorie.Libelle;
%>
        <a class="row" href="<%= lien_href %>" >  <%= lien_nom %> </a>
<%  }
%>
</div>