<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Categorie"%>
<div class="span2" ><!--Colonne-->
    
        <ul class="nav nav-list" id="nav-content">
            <li class="nav-header"><center><h5>Catégories de produits</h5></center></li>
                <%
                    for (Categorie categorie : Base.GetCategories()) {
                        String Nav_lien_href = Base.CONTEXT_PATH + "/Catalogue?IDcategorie=" + categorie.ID;
                        String Nav_lien_nom = categorie.Libelle;
                %>
            <li class="">
                
                <a class="" href="<%= Nav_lien_href%>">  <%= Nav_lien_nom%> </a>
            </li>

            <%
                }
            %>
            <br>
        </ul>


    
</div>
