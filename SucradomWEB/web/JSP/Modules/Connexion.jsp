<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Client"%>

<div class="row">
<%
    String formAction = Base.CONTEXT_PATH+"/Index?JSPfolder=TryConnexion";
%>
    <FORM class="col-lg-12" Method="POST" Action="<%= formAction %>">
        <div class ="row" >
            <div class="col-lg-6">
                Email:<input type=text name=Email>         
            </div>
            <div class="col-lg-6">
                Mot de passe:<input type=password name=MotDePasse>           
            </div>
        </div>         
        <input class="row" type=submit value=Connexion>
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

