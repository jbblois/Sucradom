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
    TeteCommande Panier = Session.GetPanier(request);
    if ( Panier != null) 
    {
%>
            <div class="col-lg-12">
                Le contenu de la commande
<%        
        ArrayList<LigneCommande> lignes = Panier.GetLigneCommandes();
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
                    ligne : <%=libelleProduit%>
                </div>
<%        
        }
        String prixCommande = Panier.GetPrixTTC()+" euros";
%>
            </div>
            <div class="col-lg-12">
                Le prix total de la commande
            </div>
<% 
    }
    else
    {
%>
    <div class="col-lg-12">
        Le panier est vide
    </div>
<%         
    }
%>
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>