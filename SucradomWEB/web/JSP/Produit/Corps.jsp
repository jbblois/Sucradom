<%@page import="sucradom.utile.Base"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.metier.Produit"%>
<%  
    Produit SelectedProduit = (Produit) request.getAttribute("SelectedProduit");
    if ( SelectedProduit != null) 
    {
        String produitLibelle = SelectedProduit.Libelle;
        String produitDescription = SelectedProduit.Description;
        String produitCategorie = SelectedProduit.Categorie.Libelle;
        String produitTaxe = ""+SelectedProduit.Taxe.Valeur+" %";
        String produitPrix = ""+SelectedProduit.Prix+" euros";
        String produitQuantite = ""+SelectedProduit.Quantite();
        String pathNoSpaces = SelectedProduit.Image.Path.replace(' ', '_');
        String produitImage_Path = Base.CONTEXT_PATH+"/RESSOURCES/"+pathNoSpaces+".png";
        String produitImage_Alt = SelectedProduit.Image.Alt;
%>

    <div class="col-lg-4">
        <img src="<%= produitImage_Path %>" alt="<%= produitImage_Alt %>" height="200" width="200"> 

    </div>
    <div class="col-lg-6">
        <div >Libelle : <%= produitLibelle %> </div>
        <div > <%= produitDescription %> </div>
        <div >Categorie : <%= produitCategorie %> </div> 
        <div >Taxe : <%= produitTaxe %> </div> 
        <div >Prix : <%= produitPrix %> </div>
        <div >Quantité disponible : <%= produitQuantite %> </div>
    </div>
<%        
    }
    else
    {
%>
    <div class="col-lg-10">PAS DE PRODUIT SELECTIONNEE</div>
<%  
    }
%>