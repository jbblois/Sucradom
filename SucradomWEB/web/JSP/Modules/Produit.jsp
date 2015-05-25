<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sucradom.utile.Base"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.metier.Produit"%>
<!DOCTYPE html>
<html>
    <%@include file="../Blocs/Head.jsp" %>
    <%@include file="../Blocs/Tete.jsp" %>

    <div class="container">
        <div class="row">
            <%@include file="../Blocs/Navigation.jsp" %>
            <div class="span9">
                <div class="row">
                    <%    Produit SelectedProduit = (Produit) request.getAttribute("SelectedProduit");
                    if (SelectedProduit != null) {
                        String produitLibelle = SelectedProduit.Libelle;
                        String produitDescription = SelectedProduit.Description;
                        String produitCategorie = SelectedProduit.Categorie.Libelle;
                        String produitTaxe = "" + SelectedProduit.Taxe.Valeur + " %";
                        String produitPrix = "" + SelectedProduit.Prix + " ";
                        String produitQuantite = "" + SelectedProduit.Quantite();
                        String pathNoSpaces = SelectedProduit.Image.Path.replace(' ', '_');
                        String produitImage_Path = Base.CONTEXT_PATH + "/RESSOURCES/" + pathNoSpaces + ".png";
                        String produitImage_Alt = SelectedProduit.Image.Alt;
                    %>
                    <div class="span5">
                        <img src="http://dummyimage.com/200x200/878787/fff.png&text=Image+Produit" alt="" width="470" height="310">
                        <!--    <img src="<%= produitImage_Path%>" alt="<%= produitImage_Alt%>" height="200" width="200">   -->
                    </div>

                    <div class="span4">
                        <h4><%= produitLibelle%></h4>
                        <h5>Catégorie : <%= produitCategorie%></h5>
                        <p><%= produitDescription%></p>
                        <h4>Prix : <%= produitPrix%> €   </h4><h4>Taxe : <%= produitTaxe%></h4>
                        <form action="panier.php">
                            
                            <input type="text" name="qte">
                            <!--
                            <select name="qte" class="span1">
                                <option>1</option>
                            </select>
                            -->

                            <div>
                                <div>Quantité disponible : <%= produitQuantite%> </div>
                                <button class="btn btn-primary">Ajouter au panier</button>
                            </div>
                        </form>

                    </div>
                    <%
                } else {
                %>
                <div >PAS DE PRODUIT SELECTIONNEE</div>
                <%
                    }
                %>
                </div>


            </div>

        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>