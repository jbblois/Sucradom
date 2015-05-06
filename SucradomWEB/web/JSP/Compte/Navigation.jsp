<%@page import="sucradom.utile.Base"%>
<%
    String href = Base.CONTEXT_PATH;
%>
<a class="row" href="<%= href+"/Connexion?Methode=Deconnexion" %>"> Se déconnecter </a>
<a class="row" href="<%= href+"/ModifierMDP?Methode=Go" %>"> Modifier le mot de passe </a>
<a class="row" href="<%= href+"/Commandes" %>"> Mes commandes  </a>