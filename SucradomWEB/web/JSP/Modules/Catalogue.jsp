<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Categorie"%>
<%@page import="sucradom.metier.Produit"%>
<%@page import="sucradom.dao.CategorieDAO"%>
<%@page import="sucradom.dao.ProduitDAO"%>
<!DOCTYPE html>
<html>
    <%@include file="../Blocs/Head.jsp" %>
    <%@include file="../Blocs/Tete.jsp" %>
    <div class="container"> 
        <%@include file="../Blocs/Navigation.jsp" %>
        <div class="col-lg-9"><!--Corps-->
<%  
    Categorie SelectedCategorie = (Categorie) request.getAttribute("SelectedCategorie");
    if ( SelectedCategorie != null) 
    {
        ArrayList<Produit> listeProduits = SelectedCategorie.GetProduits();
        if(listeProduits != null)
        {
            if(listeProduits.size() > 0)
            {
                for (Produit produit : listeProduits) 
                {
                    if (produit.IsDisponible.equals("OUI")) 
                    {
                        String produitLien_Href = Base.CONTEXT_PATH+"/Produit?Methode=Go&IDproduit="+ produit.ID;
                        String produitLien_Nom = produit.Libelle;
                        String pathNoSpaces = produit.Image.Path.replace(' ', '_');
                        String produitImage_Path = Base.CONTEXT_PATH+"/RESSOURCES/"+pathNoSpaces+".png";
                        String produitImage_Alt = produit.Image.Alt;
%>
            <div class="col-lg-4">
                <img src="<%= produitImage_Path %>" alt="<%= produitImage_Alt %>" height="100" width="100">
                <a href="<%= produitLien_Href %>">  <%= produitLien_Nom %> </a>
            </div>
<%  
                    }
                }
            }
            else
            {
%>
            <div class="row">PAS DE PRODUITS DISPONIBLES</div>
<%  
            }
        }
        else
        {
%>
            <div class="row">PAS DE PRODUITS DISPONIBLES</div>
<%  
        }
    }
    else
    {
%>
            <div class="row">PAS DE CATEGORIE SELECTIONNEE</div>
<%  
    }
%>
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>
