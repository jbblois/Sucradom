<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Client"%>

<div class="row">
<%
    String formAction = Base.CONTEXT_PATH+"/ModifierMDP?Methode=Try";
%>
    <FORM class="col-lg-12" Method="POST" Action="<%= formAction %>">
        <div class="row">
            Mot de passe actuel:<input type=password name=OldMDP>           
        </div>
        <div class="row">
            Nouveau mot de passe:<input type=password name=NewMDP>           
        </div>
        <div class="row">
            Confirmation:<input type=password name=RepMDP>           
        </div>        
        <input class="row" type=submit value="Modifier le mot de passe">
<%
    String erreur = (String) request.getAttribute("Erreur");
    if (erreur != null) 
    {
%>
        <div class="row" style="color: #ac2925"><%=erreur%></div>
<%     
    }
%>
    </FORM>
</div>
