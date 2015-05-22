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
        <div class="row">
            <%@include file="../Blocs/Navigation.jsp" %>
            <div class="span9" ><!--Corps-->
                <ul class="thumbnails">
                <%    Categorie SelectedCategorie = (Categorie) request.getAttribute("SelectedCategorie");
                    if (SelectedCategorie != null) {
                        ArrayList<Produit> listeProduits = SelectedCategorie.GetProduits();
                        if (listeProduits != null) {
                            if (listeProduits.size() > 0) {
                                for (Produit produit : listeProduits) {
                                    if (produit.IsDisponible.equals("OUI")) {
                                        String produitLien_Href = Base.CONTEXT_PATH + "/Produit?Methode=Go&IDproduit=" + produit.ID;
                                        String produit_Nom = produit.Libelle;
                                        String produit_Prix = produit.Prix + " €";
                                        
                                        String pathNoSpaces = produit.Image.Path.replace(' ', '_');
                                        String produitImage_Path = Base.CONTEXT_PATH + "/RESSOURCES/" + pathNoSpaces + ".png";
                                        String produitImage_Alt = produit.Image.Alt;
                %>
                
                <li class="span3" >
                    <div class="thumbnail" id="produit-affichage">
                        <img src="http://dummyimage.com/200x200/878787/fff.png&text=Image+Produit" alt="Sucradom" width="300" height="300">
                        <div class="caption">
                            <h4><%= produit_Nom%></h4>
                            <p><%= produit_Prix %></p>
                            <a class="btn btn-primary" href="<%= produitLien_Href%>"> Plus d'infos</a>
                            <a class="btn btn-success" href="#">Ajouter au panier</a>
                        </div>
                    </div>
                </li>    
                <%
                        }
                    }
                } else {
                %>
                <li class="span9" id="principal-content">PAS DE PRODUITS DISPONIBLES</li>
                <%
                    }
                } else {
                %>
                <li class="span9" id="principal-content">PAS DE PRODUITS DISPONIBLES</li>
                <%
                    }
                } else {
                %>
                <li class="span9" id="principal-content">PAS DE CATEGORIE SELECTIONNEE</li>
                <%
                    }
                %>
                </ul>
                
            </div>
        </div>
            </div>
    <%@include file="../Blocs/Pieds.jsp" %>
    
</html>
