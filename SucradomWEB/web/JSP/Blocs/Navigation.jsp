<%@page import="sucradom.metier.LigneCommande"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.utile.Session"%>
<%@page import="sucradom.metier.TeteCommande"%>
<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Categorie"%>
<div class="span2" ><!--Colonne-->
    <ul class="nav nav-list" id="nav-content">
        <li class="nav-header"><center><h5>Catégories de produits</h5></center></li>
<%
    for (Categorie categorie : Base.GetCategories()) 
    {
        String categorie_href = Base.CONTEXT_PATH + "/Catalogue?IDcategorie=" + categorie.ID;
        String categorie_nom = categorie.Libelle;
%>
            <li class="">
                <a class="" href="<%= categorie_href%>">  <%= categorie_nom%> </a>
            </li>
<%
    }
%>
        </li>
    </ul>
<%
    TeteCommande navPanier = Session.GetPanier(request);
    if(navPanier != null)
    {
        String nombreDeProduits = ""+navPanier.GetLigneCommandes().size();
        String prixCommande = navPanier.GetPrixTTC()+" ?";
%>
<br>
    <div id="nav-content">
        <center><h5>Votre Panier</h5></center>
        <div >
            Produit: <%= nombreDeProduits %>         
        </div>
        <div >
            Prix total : <%= prixCommande %>            
        </div>  
    </div>  
<%
    }
%>

<%
    TeteCommande fakePanier = Base.FakeCommande();
    
    if(fakePanier != null)
    {
        String nombreDeProduits = ""+fakePanier.GetLigneCommandes().size();
        String prixCommande = fakePanier.GetPrixTTC()+" euros";
%>
    <div id="nav-content">
        <center><h5>Votre Panier</h5></center>
        <div >
            Produit: <%= nombreDeProduits %>         
        </div>
        <div >
            Prix total : <%= prixCommande %>            
        </div>  
    </div>  
<%
    }
%>
     
</div>
