<%@page import="sucradom.metier.LigneCommande"%>
<%@page import="java.sql.Date"%>
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
        <%@include file="../Blocs/Navigation_compte.jsp" %>
        <div class="span8">
            <%
                TeteCommande SelectedCommande = (TeteCommande) request.getAttribute("SelectedCommande");
                
                if (SelectedCommande != null) {
                    
                    String etatCommande = SelectedCommande.EtatCommande.Nom;
                    Date date = SelectedCommande.Date;
                    String dateCommande = Base.DateToString(date);
                    String nomCLient = SelectedCommande.Client.Prenom + " " + SelectedCommande.Client.Nom;
                    String emailClient = SelectedCommande.Client.Email;
                    Integer referenceCommande = SelectedCommande.ID;
                    String adresseCommande = SelectedCommande.Adresse.toString();
            %>
            <div class="">
                <div class="span4">
                    <p style="text-align: left"><u>Référence de la commande :</u><br><%=referenceCommande%></p>
                    <p style="text-align: left"><u>Client :</u><br><%=nomCLient%></p>
                    <p style="text-align: left"><u>Email :</u><br> <%=emailClient%></p>
                    <p style="text-align: left"><u>Adresse de livraison :</u><br> <%=adresseCommande%></p>  
                </div>
                
                <div class="span3">
                    <p style="text-align: right"><u>Passée le : </u><br><%=dateCommande%></p>
                    <p style="text-align: right"><u>Etat de la commande :</u><br> <%=etatCommande%></p>
                </div>


            </div>
                <br>
                <br>
                <br>
            <div class="span8">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Références</th>
                            <th>Quantité</th>
                            <th>Prix unitaire TTC</th>
                            <th>Total TTC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<LigneCommande> lignes = SelectedCommande.GetLigneCommandes();
                            for (LigneCommande ligne : lignes) {
                                Produit produit = ligne.Produit;
                                String libelleProduit = produit.Libelle;
                                String quantiteProduit = "" + ligne.Quantite;
                                float UTTC = ligne.PrixUnitaire * (1 + (ligne.ValeurTaxe / 100));
                                String prixUnitaireTTC = "" + UTTC;
                                String totalLigneTTC = "" + ligne.GetPrixTTC();
                        %>
                        <tr>
                            <td><%=libelleProduit%></td>
                            <td>
                                <%=quantiteProduit%> 
                            </td>
                            <td><%=prixUnitaireTTC%></td>
                            <td><%=totalLigneTTC%></td>
                        </tr>
                        <%
                            }
                            String prixCommande = SelectedCommande.GetPrixTTC() + " euros";
                        %>
                    </tbody>
                </table>
            </div>
                    <br>
                    <br>
            <div class="">
                

                <div class="span8">
                    <p style="text-align: right">Prix Total TTC : <%= prixCommande%></p>
                </div>
            </div>

            <%
                }
            %>
        </div>
    </div>
    <%@include file="../Blocs/Pieds.jsp" %>
</html>