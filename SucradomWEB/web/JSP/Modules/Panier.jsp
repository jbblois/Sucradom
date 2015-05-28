<%@page import="sucradom.metier.Adresse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sucradom.utile.Base"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.metier.TeteCommande"%>
<%@page import="sucradom.metier.Produit"%>
<!DOCTYPE html>
<html>
    <%@include file="../Blocs/Head.jsp" %>
    <%@include file="../Blocs/Tete.jsp" %>
    
    <div class="container"> 
        <%@include file="../Blocs/Navigation.jsp" %>
        <div class="col-lg-9">
<%
    TeteCommande Panier = Session.GetPanier(request);
    String prixCommande = Panier.GetPrixTTC()+" euros";
    String formAction = Base.CONTEXT_PATH + "/Panier?Methode=Valider";
    if ( Panier != null) 
    {
%>
            <FORM class="col-lg-12" Method="POST" Action="<%= formAction %>">
                <div class="col-lg-12">
<%        
        ArrayList<LigneCommande> lignes = Panier.GetLigneCommandes();
        for(LigneCommande ligne : lignes)
        {
            Produit produit = ligne.Produit;
            String libelleProduit = produit.Libelle;
            String quantiteProduit = ""+ligne.Quantite;
            float UTTC = ligne.PrixUnitaire * (1+(ligne.ValeurTaxe/100));
            String prixUnitaireTTC = ""+UTTC+" euros";
            String totalLigneTTC = ""+ligne.GetPrixTTC();
            String supprimerLigne = Base.CONTEXT_PATH + "/Panier?Methode=Delete&IDproduit="+ligne.Produit.ID;
%>
                    <div class="col-lg-12">
                        <div class="col-lg-9">
                            <%=libelleProduit%> x <%=quantiteProduit%> à <%=prixUnitaireTTC%> = <%=totalLigneTTC%>
                        </div>
                        <div class="col-lg-3">
                            <a class="btn btn-danger" href="<%=supprimerLigne%>">Supprimer</a>
                        </div>
                    </div>
<%        
        }
%>
                </div>
                <select class="col-lg-12" name="IDadresse">
<%
        for(Adresse adresse : Session.GetClient(request).GetAdresses())
        {
            int IDadresse = adresse.ID;
            String stringAdresse = adresse.toString(); 
%>
                    <option value="<%=IDadresse%>" ><%=stringAdresse%></option>
<%
        }
%>               
                </select>
                <input class="row" type=submit value=Valider>
            </FORM>
<%  
    }
    String Erreur = (String) request.getAttribute("Erreur");
    if (Erreur != null) 
    {   
%>     
           <div class="col-lg-12" id="principal-content"><%=Erreur%></div>    
<%
    }
%>
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>