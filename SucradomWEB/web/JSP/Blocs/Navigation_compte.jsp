<%@page import="sucradom.utile.Session"%>
<%@page import="sucradom.metier.TeteCommande"%>
<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Categorie"%>
<div class="span3" ><!--Colonne-->
    <%    String href = Base.CONTEXT_PATH;   %>
    <ul class="nav nav-list" id="nav-content">
        <li class="nav-header"><center><h5>Gestion du compte</h5></center></li>

            <li class="">
                <a class="row" href="<%= href + "/Connexion?Methode=Deconnexion"%>">  Se déconnecter </a>
            </li>
            <li class="">
                <a class="row" href="<%= href + "/ModifierMDP?Methode=Go"%>">  Changer le mot de passe </a>
            </li>
            <li class="">
                <a class="row" href="<%= href + "/Commandes"%>">  Mes commandes </a>
            </li>
            <li class="">
                <a class="row" href="<%= href + "/Adresses"%>">  Mes adresses </a>
            </li>
    </ul>
      
</div>
