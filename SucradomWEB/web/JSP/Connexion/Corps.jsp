<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Client"%>
<%
        String formAction = Base.CONTEXT_PATH+"/Index?JSPfolder=TryConnexion";
%>
        <DIV class="row">
            <FORM Method="POST" Action="<%= formAction %>">
                <DIV class="row">Email        :<INPUT type=text name=Email>          </DIV>
                <DIV class="row">Mot de passe :<INPUT type=password name=MotDePasse> </DIV>
                <DIV class="row">              <INPUT type=submit value=Connexion>     </DIV>
            </FORM>
        </DIV>
<%
        String erreur = (String) request.getSession(true).getAttribute("Erreur");
        if (erreur != null) 
        {
%>
            <DIV class="row" style="color: #ac2925"><%=erreur%></DIV>
<%     
        }
%>

