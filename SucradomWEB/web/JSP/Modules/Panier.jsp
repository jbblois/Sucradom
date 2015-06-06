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
        <div class="row">
            <%@include file="../Blocs/Navigation.jsp" %>
            <div class="span9">
                <h2>Votre panier</h2>
                <%                    TeteCommande Panier = Session.GetPanier(request);
                    String prixCommande = Panier.GetPrixTTC() + " euros";
                    String formAction = Base.CONTEXT_PATH + "/Panier?Methode=Valider";
                    if (Panier != null) {
                %>
                <FORM class="col-lg-12" Method="POST" Action="<%= formAction%>">
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
                                ArrayList<LigneCommande> lignes = Panier.GetLigneCommandes();
                                for (LigneCommande ligne : lignes) {
                                    Produit produit = ligne.Produit;
                                    String libelleProduit = produit.Libelle;
                                    String quantiteProduit = "" + ligne.Quantite;
                                    float UTTC = ligne.PrixUnitaire * (1 + (ligne.ValeurTaxe / 100));
                                    String prixUnitaireTTC = "" + UTTC + " euros";
                                    String totalLigneTTC = "" + ligne.GetPrixTTC();
                                    String supprimerLigne = Base.CONTEXT_PATH + "/Panier?Methode=Delete&IDproduit=" + ligne.Produit.ID;
                                    
                            %>


                            <tr>
                                <td><%=libelleProduit%></td>
                                <td>
                                    <%=quantiteProduit%> 
                                </td>
                                <td><%=prixUnitaireTTC%></td>
                                <td><%=totalLigneTTC%>         <b>    </b>       <a href="<%=supprimerLigne%>"><i class="icon-trash"></i></a></td>
                            </tr>

                            <%
                                }
                                
                            %>
                        </tbody>
                    </table>
                    <dl class="dl-horizontal pull-right">

                        <dt>Total:</dt>
                        <dd><%= prixCommande%></dd>
                    </dl>

                        
                    <div class="span4">
                        <p style="text-align: center">Selectionnez votre adresse de livraison :</p>
                        <select class="col-lg-12" name="IDadresse">
                            <%
                                for (Adresse adresse : Session.GetClient(request).GetAdresses()) {
                                    int IDadresse = adresse.ID;
                                    String stringAdresse = adresse.toString();
                            %>
                            <option value="<%=IDadresse%>" ><%=stringAdresse%></option>
                            <%
                                }
                            %>               
                        </select>
                    </div>
                    <div class="span4">
                        <button class="btn btn-primary ">Continuer mes achats</button>
                        <input class="btn btn-success pull-right" type=submit value="Valider mon Pannier">
                    </div>
                       
                </form>
                <div class="clearfix"></div>


            </div>
            <%
                }
                String Erreur = (String) request.getAttribute("Erreur");
                if (Erreur != null) {
            %>     
            <div class="col-lg-12" id="principal-content"><%=Erreur%></div>    
            <%
                }
            %>
        </div>
    </div>
        <%@include file="../Blocs/Pieds.jsp" %>

</html>