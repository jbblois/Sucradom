<%@page import="java.sql.Date"%>
<%@page import="sucradom.metier.Adresse"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.utile.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../Blocs/Head.jsp" %>
    <%@include file="../Blocs/Tete.jsp" %>
    <div class="container"> 
        <%@include file="../Blocs/Navigation_compte.jsp" %>
        <div class="span8">
<%
    ArrayList<Adresse> adresses = Session.GetClient(request).GetAdresses();
    for (Adresse adresse : adresses) 
    {
        String adresseString = adresse.toString();
        String supprimerAdresse = Base.CONTEXT_PATH + "/Adresses?Methode=Delete&IDadresse="+adresse.ID;
%>
            <div class="col-lg-12">
                <div class="col-lg-9"><%=adresseString%></div>
                 <div class="col-lg-3"><a class="btn btn-danger" href="<%=supprimerAdresse%>">Supprimer</a></div>
            </div>
<%
    }
    String ajouterAdresse = Base.CONTEXT_PATH + "/AjouterAdresse?Methode=Go";
%>
            <div class="col-lg-12">
                <a class="btn btn-success" href="<%=ajouterAdresse%>">Ajouter une adresse</a>
            </div>
<%
    String Erreur = (String) request.getAttribute("Erreur");
    if (Erreur != null) 
    {
%>
            <div class="col-lg-12"><%=Erreur%></div>
<%     
    }
%>       
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>
