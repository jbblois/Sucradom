<%@page import="sucradom.utile.Base"%>
<%@page import="sucradom.metier.Categorie"%>
<%@page import="sucradom.metier.Produit"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sucradom.dao.ProduitDAO"%>
<%  
    Categorie SelectedCategorie = (Categorie) request.getAttribute("SelectedCategorie");
    if ( SelectedCategorie != null) 
    {
        ArrayList<Produit> listeProduits = SelectedCategorie.GetProduits();
        if(listeProduits != null)
        {
            if(listeProduits.size() > 0)
            {
                for (Produit produit : listeProduits) 
                {
                    if (produit.IsDisponible.equals("OUI")) 
                    {
                        String produitLien_Href = Base.CONTEXT_PATH+"/Produit?Methode=Go&IDproduit="+ produit.ID;
                        String produitLien_Nom = produit.Libelle;
                        String pathNoSpaces = produit.Image.Path.replace(' ', '_');
                        String produitImage_Path = Base.CONTEXT_PATH+"/RESSOURCES/"+pathNoSpaces+".png";
                        String produitImage_Alt = produit.Image.Alt;
%>
                        <div class="col-lg-4">
                            <img src="<%= produitImage_Path %>" alt="<%= produitImage_Alt %>" height="100" width="100">
                            <a href="<%= produitLien_Href %>">  <%= produitLien_Nom %> </a>
                        </div>
<%  
                    }
                }
            }
            else
            {
%>
                <div class="col-lg-12">PAS DE PRODUITS DISPONIBLES</div>
<%  
            }
        }
        else
        {
%>
        <div class="col-lg-12">PAS DE PRODUITS DISPONIBLES</div>
<%  
        }
    }
    else
    {
%>
        <div class="col-lg-12">PAS DE CATEGORIE SELECTIONNEE</div>
<%  
    }
%>
