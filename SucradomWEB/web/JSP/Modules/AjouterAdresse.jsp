<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../Blocs/Head.jsp" %>
    <%@include file="../Blocs/Tete.jsp" %>

    <div class="container"> 
<%       
    String formAction = Base.CONTEXT_PATH + "/AjouterAdresse?Methode=Try";
%>
        <div class="row">
            <%@include file="../Blocs/Navigation_compte.jsp" %>

            <div class="span7"><!--Corps-->
                <FORM class="col-lg-12" Method="POST" Action="<%= formAction%>">
                    <div class="row">
                        Pays :  <input type=text name=Pays>           
                    </div>
                    <div class="row">
                       Ville :  <input type=text name=Ville>           
                    </div>
                    <div class="row">
                       CP :  <input type=text name=CP>           
                    </div>
                    <div class="row">
                       Rue :  <input type=text name=Rue>           
                    </div>
                    <div class="row">
                       Numéro :  <input type=text name=Numero>           
                    </div>
                    <input class="row" type=submit value="Ajouter l'adresse">
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
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>
