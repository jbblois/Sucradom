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
import sucradom.dao.AdresseDAO;
import sucradom.dao.EtatCommandeDAO;
import sucradom.dao.LigneCommandeDAO;
import sucradom.dao.ProduitDAO;
import sucradom.dao.TeteCommandeDAO;
import sucradom.metier.Adresse;
import sucradom.metier.LigneCommande;
import sucradom.metier.TeteCommande;
import sucradom.metier.Produit;
import sucradom.metier.Taxe;
import sucradom.utile.Base;
import sucradom.utile.Session;

/**
 *
 * @author user
 */
public class Panier extends HttpServlet {
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
                    case "Delete":
                        Delete(request, response);
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
            Index.RequestDispatcher(request, response, this, "/Connexion");
        }
    }
    
    protected void Go(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        TeteCommande panier = Session.GetPanier(request);
        if(panier != null)
        {
            Index.RequestDispatcher(request, response, this, "/JSP/Modules/Panier.jsp");
        }
        else
        {
            Index.RequestDispatcher(request, response, this, "/Catalogue");
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
                AddLigne(request, response, produitSelectionne, 1);
            }
        }
        Index.RequestDispatcher(request, response, this, "/Panier?Methode=Go");
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
                    if(Quantite <= produitSelectionne.Quantite())
                    {
                        AddLigne(request, response, produitSelectionne, Quantite);
                        Index.RequestDispatcher(request, response, this, "/Panier?Methode=Go");
                    
                    }
                    else
                    {
                        request.setAttribute("Erreur", "Veuillez saisir une quantité qui ne dépasse pas les stocks disponibles");
                        Index.RequestDispatcher(request, response, this, "/Panier?Methode=GO&IDproduit="+IDproduit);
                    }
                }
                else
                {
                    request.setAttribute("Erreur", "Veuillez saisir une quantité supérieur à 0");
                    Index.RequestDispatcher(request, response, this, "/Panier?Methode=GO&IDproduit="+IDproduit);
                }
            } 
            catch (Exception e) 
            {
                request.setAttribute("Erreur", "Veuillez saisir un entier");
                Index.RequestDispatcher(request, response, this, "/Panier?Methode=GO&IDproduit="+IDproduit);
            }
            
        }
        else
        {
            Index.RequestDispatcher(request, response, this, "/Catalogue");
        }
    }
    
    private void AddLigne(HttpServletRequest request, HttpServletResponse response, Produit ProduitSelectionne, int Quantite)
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
                request.setAttribute("Erreur", null);
                Index.RequestDispatcher(request, response, this, "/Panier?Methode=Go");
            }
            else
            {
                request.setAttribute("Erreur", "Pas assez de quantité en stock !");
                Index.RequestDispatcher(request, response, this, "/Produit?Methode=Go&IDproduit="+ProduitSelectionne.ID);
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
        boolean check = true;
        TeteCommande panier = Session.GetPanier(request);
        if( panier != null)
        {
            String stringID = request.getParameter("IDadresse");
            int IDadresse = Integer.parseInt(stringID);
            Adresse adresse = AdresseDAO.Select(IDadresse);
            if(adresse != null)
            {
                ArrayList<LigneCommande> lignes = panier.GetLigneCommandes();
                if(!lignes.isEmpty())
                {
                    //1 - INSERT TeteCommande WITH Date = Now() AND EtatCommande = NonPayée
                    java.util.Date utilDate = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    
                    panier.Adresse = adresse;
                    panier.Date = sqlDate;
                    panier.EtatCommande = EtatCommandeDAO.Select(1);
                    
                    if(TeteCommandeDAO.Insert(panier) == 1)
                    {
                        //2 - SELECT Last TeteCommande
                        panier = TeteCommandeDAO.SelectLast();
                        if(panier != null)
                        {
                            //3 - INSERT each LigneCommande 
                            for (LigneCommande ligne : lignes) 
                            {
                                ligne.Commande = panier;
                                if(LigneCommandeDAO.Insert(ligne) != 1)
                                {
                                    request.setAttribute("Erreur", "Erreur lors de l'enregistrement de la commande !");
                                    check = false;
                                }
                            }
                            
                        }
                        else{check = false;}
                    }
                    else{check = false;}
                }
                else{check = false;}
            }
            else{check = false;}
        }
        else{check = false;}
        
        if(check)
        {
            //4 - Vider le panier
            Session.SetPanier(request, null);
            Index.RequestDispatcher(request, response, this, "/Commandes?Methode=Go");
            check = true;
        }
        else
        {
            Index.RequestDispatcher(request, response, this, "/Panier?Methode=GO");
        }
        return check;
    }
    
    protected void Delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if (request.getParameter("IDproduit") != null) 
        {
            String stringID = (String)request.getParameter("IDproduit");
            int IDproduit =  Integer.parseInt(stringID);
            int i = 0;
            LigneCommande DeletedLine = null;
            for (LigneCommande ligne : Session.GetPanier(request).GetLigneCommandes()) 
            {
                if(ligne.Produit.ID == IDproduit)
                {
                    DeletedLine = ligne;
                    break;
                }
            }
            Session.GetPanier(request).GetLigneCommandes().remove(DeletedLine);
            Index.RequestDispatcher(request, response, this, "/Panier?Methode=Go");
        }
        
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
