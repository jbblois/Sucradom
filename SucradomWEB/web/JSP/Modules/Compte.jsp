<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sucradom.utile.Session"%>
 <%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Client"%>
<!DOCTYPE html>
<html>
    <%@include file="../Blocs/Head.jsp" %>
    <%@include file="../Blocs/Tete.jsp" %>
    <div class="container"> 
        <div class="col-lg-4">   
<%
    String href = Base.CONTEXT_PATH;
%>
            <a class="row" href="<%= href+"/Connexion?Methode=Deconnexion" %>"> 
                Se déconnecter    
            </a>
            <a class="row" href="<%= href+"/ModifierMDP?Methode=Go" %>"> 
                Modifier le mot de passe 
            </a>
            <a class="row" href="<%= href+"/Commandes" %>"> 
                Mes commandes                         
            </a>
        </div>
        <div class="col-lg-8">
<%
    Client client = (Client) request.getSession(true).getAttribute("Client");
    String clientNom = client.Nom;
    String clientPrenom = client.Prenom;
    String clientEmail = client.Email;
    String clientTelephone = client.Telephone;
%>
            <div class="row">Nom :       <%= clientNom %>       </div>
            <div class="row">Prenom :    <%= clientPrenom %>    </div>
            <div class="row">Email :     <%= clientEmail %>     </div>
            <div class="row">Télephone : <%= clientTelephone %> </div>
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>
