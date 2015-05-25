<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sucradom.utile.Session"%>
<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Client"%>
<!DOCTYPE html>
<html>
    <%@include file="../Blocs/Head.jsp" %>
    <%@include file="../Blocs/Tete.jsp" %>
    <div class="container"> 
        <div class="row">
            <%@include file="../Blocs/Navigation_compte.jsp" %>

            <div class="span7"><!--Corps-->
                <%
                    Client client = (Client) request.getSession(true).getAttribute("Client");
                    String clientNom = client.Nom;
                    String clientPrenom = client.Prenom;
                    String clientEmail = client.Email;
                    String clientTelephone = client.Telephone;
                %>
                <div class="col-lg-offset-1 col-lg-7">
                    <div class="">Nom :       <%= clientNom%>      </div><br>
                    <div class="">Prenom :    <%= clientPrenom%>   </div><br>
                    <div class="">Email :     <%= clientEmail%>    </div><br>
                    <div class="">Télephone : <%= clientTelephone%></div><br>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>
