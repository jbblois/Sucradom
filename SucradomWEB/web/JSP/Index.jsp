<%-- 
    Document   : Accueil
    Created on : 27 avr. 2015, 08:44:54
    Author     : user
--%>

<%@page import="sucradom.utile.Base"%>
<%
    String JSPfolder =(String) request.getParameter("JSPfolder");
    if (JSPfolder == null) 
    {
        JSPfolder = "Accueil";
    }
    request.getRequestDispatcher( "./Blocs/Tete.jsp" ).include(request, response );
    try
    {
        request.getRequestDispatcher( "./Modules/"+JSPfolder+".jsp" ).include(request, response );
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    request.getRequestDispatcher( "./Blocs/Pieds.jsp" ).include(request, response );
%>

