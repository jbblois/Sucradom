<%-- 
    Document   : Accueil
    Created on : 27 avr. 2015, 08:44:54
    Author     : user
--%>

<%@page import="sucradom.utile.Base"%>
<%
    String module =(String) request.getParameter("Module");
    if (module == null) 
    {
        module = "Accueil";
    }
    request.getRequestDispatcher( "./Blocs/Tete.jsp" ).include(request, response );
    try
    {
        request.getRequestDispatcher( "./Modules/"+module+".jsp" ).include(request, response );
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    request.getRequestDispatcher( "./Blocs/Pieds.jsp" ).include(request, response );
%>

