<%@page import="sucradom.utile.Session"%>
<%@page import="sucradom.metier.Client"%>
<%
    Client client = Session.Client;
    String clientNom = client.Nom;
    String clientPrenom = client.Prenom;
    String clientEmail = client.Email;
    String clientTelephone = client.Telephone;
%>
    <div class="row">Nom :       <%= clientNom %>       </div>
    <div class="row">Prenom :    <%= clientPrenom %>    </div>
    <div class="row">Email :     <%= clientEmail %>     </div>
    <div class="row">Télephone : <%= clientTelephone %> </div>
