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
            <div class="span9" id="produit-affichage">
                <center>
                <h2>Bienvenue chez Sucradom</h2>
                <img src="http://sucradom.url.ph/wp-content/uploads/2014/08/paques-300x208.jpg" alt="" style="position: relative; float: right"/>
                <br>
                <p style="text-align: justify">
                    Sucradom est une société de vente à domicile, spécialisée dans les denrées alimentaires. Ainsi vous trouverez chez nous toute sorte
                de produits allant des confiseries, bonbons, aux gateaux, voir tout récemment des vins de producteur.
                </p>
               
                <p style="text-align: justify">
                    Souvenez vous des gateaux de votre grand mère, des bonnes madelaines, ou des bonbons de votre enfance. Les Arlequins, les réglisses, 
                    les sucettes de sucre candy, Sucradom, met un point d'honneur à conserver ses vielles traditions, et le goût parfois disparu de ces confiseries.
                </p>
                </center>
            
            
            </div>
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
                                            String addToPanier_Href = Base.CONTEXT_PATH + "/Panier?Methode=AddOne&IDproduit=" + produit.ID;
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
                                <p><%= produit_Prix%></p>
                                <a class="btn btn-primary" href="<%= produitLien_Href%>"> Plus d'infos</a>
                                <a class="btn btn-success" href="<%= addToPanier_Href%>">Ajouter au panier</a>
                            </div>
                        </div>
                    </li>    
                    <%
                                        }
                                    }
                                }
                            }
                        }
                        String Erreur = (String) request.getAttribute("Erreur");
                        if (Erreur != null) {
                    %>
                    <li class="span8" id="principal-content"><%=Erreur%></li>
                        <%
                            }
                        %>
                </ul>
            </div>
        </div>

    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>