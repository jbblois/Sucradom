<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Categorie"%>
<%@page import="sucradom.metier.Produit"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.dao.ProduitDAO"%>
BODY
<%  
    Categorie SelectedCategorie = (Categorie) request.getAttribute("SelectedCategorie");
    if ( SelectedCategorie != null) 
    {
        ArrayList<Produit> listeProduits = SelectedCategorie.GetProduits();
        if(listeProduits != null)
        {
            for (Produit produit : listeProduits) 
            {
                String Nav_lien_href = Base.CONTEXT_PATH+"/Index?JSPfolder=Produit&IDproduit="+ produit.ID;
                String Nav_lien_nom = produit.Libelle;
%>
                <a class="row" href="<%= Nav_lien_href %>">  <%= Nav_lien_nom %> </a>
<%     
            }
        }
        else
        {
%>
        <div>PAS DE PRODUITS DISPONIBLES</div>
<%  
        }
    }
    else
    {
%>
        <div>PAS DE CATEGORIE SELECTIONNEE</div>
<%  
    }
%>
