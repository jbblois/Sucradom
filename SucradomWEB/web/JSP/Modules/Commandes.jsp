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
        <div class="col-lg-12">COMMANDES</div>
        <%
            ArrayList<TeteCommande> commandes = Session.GetClient(request).GetCommandes();
            for (TeteCommande commande : commandes) 
            {
                String commande_href = Base.CONTEXT_PATH + "/Facture?Methode=Go&IDcommande=" + commande.ID;
                Date dateCommande = commande.Date;
                String command_date = ""+dateCommande.getDay()+"/"+dateCommande.getMonth()+"/"+dateCommande.getYear();
        %>
                    <li>
                        <a class="" href="<%= commande_href%>">  <%= dateCommande%> </a>
                    </li>
        <%
            }
        %>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>
