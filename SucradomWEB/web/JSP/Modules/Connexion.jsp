<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Client"%>
<!DOCTYPE html>
<html>
    <%@include file="../Blocs/Head.jsp" %>
    <%@include file="../Blocs/Tete.jsp" %>
    <div class="container"> 
<%
        String formAction = Base.CONTEXT_PATH+"/Connexion?Methode=Try";
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
    <%@include file="../Blocs/Pieds.jsp" %>
</html>




