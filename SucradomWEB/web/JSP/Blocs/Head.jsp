<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sucradom.utile.Base"%>
<%
    String designBootstrap = Base.DESIGN_PATH+"/bootstrap.css";
    String designbase = Base.DESIGN_PATH+"/base.css";
    String designCustom = Base.DESIGN_PATH+"/custom.css";
    String designBootstrapMin = Base.DESIGN_PATH+"/bootstrap.min.css";
   
%>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%= designBootstrapMin%>" rel="stylesheet">
        <link href="<%= designCustom%>" rel="stylesheet"/>
        <link href="<%= designBootstrap%>" rel="stylesheet">
        <link href="<%= designbase%>" rel="stylesheet">
    
        
        
        
        
       
        
        <title>Sucradom</title>
</head>
