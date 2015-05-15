<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sucradom.utile.Base"%>
<%
    String designBootstrap = Base.DESIGN_PATH+"/bootstrap.css";
    String designbase = Base.DESIGN_PATH+"/base.css";
%>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%= designBootstrap%>" rel="stylesheet">
        <link href="<%= designbase%>" rel="stylesheet">
        <title>Sucradom</title>
</head>
