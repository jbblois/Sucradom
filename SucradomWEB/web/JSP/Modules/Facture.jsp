<%@page import="sucradom.metier.LigneCommande"%>
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
        <%@include file="../Blocs/Navigation_compte.jsp" %>
        <div class="span8">
<%  
    TeteCommande SelectedCommande = (TeteCommande) request.getAttribute("SelectedCommande");
    if ( SelectedCommande != null) 
    {
        String etatCommande = SelectedCommande.EtatCommande.Nom;
        Date date = SelectedCommande.Date;
        String dateCommande = Base.DateToString(date);
        String adresseCommande = SelectedCommande.Adresse.toString();
%>
            <div class="col-lg-12">
                Passée le : <%=dateCommande%> , <%=etatCommande%>
            </div>
            <div class="col-lg-12">
                Livraison au : <%=adresseCommande%>
            </div>
            <div class="col-lg-12">
<%        
        ArrayList<LigneCommande> lignes = SelectedCommande.GetLigneCommandes();
        for(LigneCommande ligne : lignes)
        {
            Produit produit = ligne.Produit;
            String libelleProduit = produit.Libelle;
            String quantiteProduit = ""+ligne.Quantite;
            float UTTC = ligne.PrixUnitaire * (1+(ligne.ValeurTaxe/100));
            String prixUnitaireTTC = ""+UTTC;
            String totalLigneTTC = ""+ligne.GetPrixTTC();
%>
                <div class="col-lg-9">
                            <%=libelleProduit%> x <%=quantiteProduit%> à <%=prixUnitaireTTC%> = <%=totalLigneTTC%>
                </div>
<%        
        }
        String prixCommande = SelectedCommande.GetPrixTTC()+" euros";
%>
            </div>
            <div class="col-lg-12">
                Le prix total : <%= prixCommande%>
            </div>
<% 
    }
%>
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>