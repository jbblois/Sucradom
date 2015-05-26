/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sucradom.dao.EtatCommandeDAO;
import sucradom.dao.LigneCommandeDAO;
import sucradom.dao.ProduitDAO;
import sucradom.dao.TeteCommandeDAO;
import sucradom.metier.LigneCommande;
import sucradom.metier.TeteCommande;
import sucradom.metier.Produit;
import sucradom.metier.Taxe;
import sucradom.utile.Session;

/**
 *
 * @author user
 */
public class Panier extends HttpServlet {

    private String _Module = "Accueil";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if(Session.GetClient(request) != null)
        {
            String methode = (String) request.getParameter("Methode");
            if (methode != null) 
            {
                switch(methode) 
                {
                    case "AddOne":
                        AddOne(request, response);
                    break;
                    case "AddQuantite":
                        AddQuantite(request, response);
                    break;
                    case "Valider":
                        Valider(request, response);
                    break;
                    default:
                        Go(request, response);
                    break;
                }
            }
            else
            {
                Go(request, response);
            }
        }
        else
        {
            _Module = "Connexion";
        }
        this.getServletContext().getRequestDispatcher("/JSP/Modules/"+_Module+".jsp" ).forward( request, response );
    }
    
    protected void Go(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        TeteCommande panier = Session.GetPanier(request);
        if(panier != null)
        {
            _Module = "Panier";
        }
        else
        {
            _Module = "Catalogue";
            request.setAttribute("Erreur", "Votre panier est vide");
        }
    }
    
    protected void AddOne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if (request.getParameter("IDproduit") != null) 
        {
            String stringID = (String)request.getParameter("IDproduit");
            int IDproduit =  Integer.parseInt(stringID);
            Produit produitSelectionne = ProduitDAO.Select(IDproduit);
            if(produitSelectionne != null)
            {
                AddLigne(request, produitSelectionne, 1);
                _Module = "Panier";
            }
        }
        
    }
    
    protected void AddQuantite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if (request.getParameter("IDproduit") != null) 
        {
            String stringID = (String)request.getParameter("IDproduit");
            int IDproduit =  Integer.parseInt(stringID);
            Produit produitSelectionne = ProduitDAO.Select(IDproduit);
            
            String stringQuantite = (String)request.getParameter("Quantite");
            try 
            {
                int Quantite =  Integer.parseInt(stringQuantite);
                
                if(Quantite > 0)
                {
                    AddLigne(request, produitSelectionne, Quantite);
                }
                else
                {
                    request.setAttribute("Erreur", "Veuillez saisir une quantité supérieur à 0");
                }
            } 
            catch (Exception e) 
            {
                request.setAttribute("Erreur", "Veuillez saisir une quantité valide");
            }
            
        }
    }
    
    private void AddLigne(HttpServletRequest request, Produit ProduitSelectionne, int Quantite)
    {
        TeteCommande panier = Session.GetPanier(request);
        if(panier == null)
        {
            Session.SetPanier(request);
            panier = Session.GetPanier(request);
        }
        LigneCommande produitDejaCommande = ProduitDejaCommande(request, panier, ProduitSelectionne);
        if(produitDejaCommande == null)
        {
            if(Quantite <= ProduitSelectionne.Quantite())
            {
                Taxe taxe = ProduitSelectionne.Taxe;
                LigneCommande ligne = new LigneCommande(panier, ProduitSelectionne, Quantite, ProduitSelectionne.Prix, taxe.Valeur);
                panier.GetLigneCommandes().add(ligne);
            }
            else
            {
                request.setAttribute("Erreur", "Pas assez de quantité en stock !");
            }
        }
        else
        {
            Quantite = Quantite + produitDejaCommande.Quantite;
            if(Quantite <= ProduitSelectionne.Quantite())
            {
               produitDejaCommande.Quantite = Quantite;
            }
            else
            {
                request.setAttribute("Erreur", "Pas assez de quantité en stock !");
            }
        }
    }
    
    private LigneCommande ProduitDejaCommande(HttpServletRequest request, TeteCommande panier, Produit ProduitSelectionne)
    {
        for (LigneCommande ligne : panier.GetLigneCommandes()) 
        {
            Produit produitDeLaLigne = ligne.Produit;
            if(produitDeLaLigne.ID == ProduitSelectionne.ID)
            {
                return ligne;
            }
        }
        return null;
    }
    
    protected boolean Valider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        TeteCommande panier = Session.GetPanier(request);
        if( panier != null)
        {
            ArrayList<LigneCommande> lignes = panier.GetLigneCommandes();
            if(!lignes.isEmpty())
            {
                //1 - INSERT TeteCommande WITH Date = Now() AND EtatCommande = NonPayée
                java.util.Date utilDate = new Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                panier.Date = sqlDate;
                panier.EtatCommande = EtatCommandeDAO.Select(1);
                if(TeteCommandeDAO.Insert(panier))
                {
                    //2 - SELECT Last TeteCommande
                    panier = TeteCommandeDAO.SelectLast();
                    if(panier != null && panier.Date.equals(sqlDate))
                    {
                        //3 - INSERT each LigneCommande 
                        for (LigneCommande ligne : lignes) 
                        {
                            ligne.Commande = panier;
                            if(!LigneCommandeDAO.Insert(ligne))
                            {
                                request.setAttribute("Erreur", "Erreur lors de l'enregistrement de la commande !");
                                return false;
                            }
                        }
                        //4 - Vider le panier
                        Session.SetPanier(request, null);
                         _Module = "Compte";
                         return true;
                    }
                }
            }
        }
        return false;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
