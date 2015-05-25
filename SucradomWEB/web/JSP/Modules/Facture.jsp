<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sucradom.utile.Base"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.metier.TeteCommande"%>
<%@page import="sucradom.metier.Produit"%>
<!DOCTYPE html>
<html>
    <%@include file="../Blocs/Head.jsp" %>
    <%@include file="../Blocs/Tete.jsp" %>
    
    <div class="container"> 
        <%@include file="../Blocs/Navigation.jsp" %>
        <div class="col-lg-9">
<%  
    TeteCommande SelectedCommande = (TeteCommande) request.getAttribute("SelectedCommande");
    if ( SelectedCommande != null) 
    {
        String etatCommande = SelectedCommande.EtatCommande.Nom;
        Date date = SelectedCommande.Date;
        String dateCommande = ""+date.getDay()+"/"+date.getMonth()+"/"+date.getYear();
%>
            <div class="col-lg-12">
                <%=etatCommande%> : <%=dateCommande%>
            </div>
            <div class="col-lg-12">
                La ligne de commande
<%        
        ArrayList<LigneCommande> lignes = SelectedCommande.GetLigneCommandes();
        for(LigneCommande ligne : lignes)
        {
            Produit produit = ligne.Produit;
            String libelleProduit = produit.Libelle;
            String quantite = ""+ligne.Quantite;
            float UTTC = ligne.PrixUnitaire * (1+(ligne.ValeurTaxe/100));
            String prixUnitaireTTC = ""+UTTC;
            String totalLigneTTC = ""+ligne.GetPrixTTC();
%>
                <div class="row">
                    <%=libelleProduit%> : <%=quantite%>
                </div>
<%        
        }
        String prixCommande = SelectedCommande.GetPrixTTC()+" euros";
%>
            </div>
            <div class="col-lg-12">
                Le prix total de la commande
            </div>
<% 
    }
%>
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>