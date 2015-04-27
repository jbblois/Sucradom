<%-- 
    Document   : Accueil
    Created on : 27 avr. 2015, 08:44:54
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="DESIGN/css/bootstrap.css" rel="stylesheet">
        <link href="DESIGN/css/base.css" rel="stylesheet">
        <title>Sucradom</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <%@include file="Tete.jsp"  %>
            </div>
             <div class="row">
                 <div class="col-lg-3">
                     <%@include file="Navigation.jsp"  %>
                 </div>
                 <div class="col-lg-9">
                    <%@include file="Corps.jsp"  %>
                 </div>
            </div>
             <div class="row">
                 <%@include file="Pieds.jsp"  %>
            </div>
        </div>
    </body>
</html>
