<%@page import="sucradom.utile.Base"%>
<%
    String href = Base.CONTEXT_PATH+"/Index?JSPfolder=";
%>
<a class="row" href="<%= href+"Deconnexion" %>"> Se d�connecter </a>
<a class="row" href="<%= href+"ModifierMDP" %>"> Modifier le mot de passe </a>
<a class="row" href="<%= href+"Commandes" %>"> Mes commandes  </a>