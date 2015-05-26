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
        <div class="col-lg-12">Adresses</div>
        <%
            ArrayList<Adresse> adresses = Session.GetClient(request).GetAdresses();
            for (Adresse adresse : adresses) 
            {
                String adresseString = ""+adresse.Numero+" rue "+adresse.Rue+" ("+adresse.Cp+") "+adresse.Pays.toUpperCase();
                String supprimerAdresse = Base.CONTEXT_PATH + "/Adressses?Methode=Delete&IDadresse="+adresse.ID;
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
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>
