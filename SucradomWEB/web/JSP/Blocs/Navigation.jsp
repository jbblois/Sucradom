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
        <br>
    </ul>
    <div class="">
<%
    TeteCommande panier = Session.GetPanier(request);
    if(panier != null)
    {
        String nombreDeProduits = ""+panier.GetLigneCommandes().size();
        String prixCommande = panier.GetPrixTTC()+" ?";
%>
        <div class="col-lg-12">
            Produit: <%= nombreDeProduits %>         
        </div>
        <div class="col-lg-12">
            Prix total : <%= prixCommande %>            
        </div>           
<%
    }
%>
    </div>   
</div>
