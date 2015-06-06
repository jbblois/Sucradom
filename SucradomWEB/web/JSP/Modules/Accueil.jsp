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
                de produits allant des confiseries, bonbons, aux gâteaux, voir tout récemment des vins de producteur.
                </p>
               
                <p style="text-align: justify">
                    Souvenez vous des gâteaux de votre grand mère, des bonnes madelaines, ou des bonbons de votre enfance. Les Arlequins, les réglisses, 
                    les sucettes de sucre candy, Sucradom, met un point d'honneur à conserver ses vielles traditions, et le goût parfois disparu de ces confiseries.
                </p>
                </center>
            
            
            </div>
            
        </div>

    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>