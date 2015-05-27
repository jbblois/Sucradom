<%@page import="java.sql.Date"%>
<%@page import="sucradom.metier.TeteCommande"%>
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
            ArrayList<TeteCommande> commandes = Session.GetClient(request).GetCommandes();
            for (TeteCommande commande : commandes) 
            {
                String commandeHref= Base.CONTEXT_PATH + "/Facture?Methode=Go&IDcommande=" + commande.ID;
                Date dateCommande = commande.Date;
                String commandeText = commande.ID+" "+Base.DateToString(dateCommande);
        %>
            <div class="col-lg-12">
                <a class="" href="<%= commandeHref%>">  <%= commandeText%> </a>
            </div>
        <%
            }
        %>
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>
