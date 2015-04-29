<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.metier.Produit"%>
BODY Produit
<%  
    Produit SelectedProduit = (Produit) request.getAttribute("SelectedProduit");
    if ( SelectedProduit != null) 
    {
        String produitLibelle = SelectedProduit.Libelle;
        String produitCategorie = SelectedProduit.Categorie.Libelle;
        String produitTaxe = ""+SelectedProduit.Taxe.Valeur+" %";
        String produitPrix = ""+SelectedProduit.Prix+" euros";
%>
        <div >Libelle : <%= produitLibelle %>   </div>
        <div >Categorie : <%= produitCategorie %> </div> 
        <div >Taxe : <%= produitTaxe %> </div> 
        <div >Prix : <%= produitPrix %> </div>
<%        
    }
    else
    {
%>
        <div>PAS DE PRODUIT SELECTIONNEE</div>
<%  
    }
%>