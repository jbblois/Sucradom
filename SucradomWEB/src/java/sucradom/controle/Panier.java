/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sucradom.dao.ProduitDAO;
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
        
        _Module = "Panier";
    }
    protected void AddOne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if (request.getParameter("IDproduit") != null) 
        {
            String stringID = (String)request.getParameter("IDproduit");
            int IDproduit =  Integer.parseInt(stringID);
            Produit produit = ProduitDAO.Select(IDproduit);
            if(produit != null)
            {
                
                TeteCommande panier = Session.GetPanier(request);
                if(panier == null)
                {
                    Session.SetPanier(request);
                    panier = Session.GetPanier(request);
                }
                Taxe taxe = produit.Taxe;
                LigneCommande ligne = new LigneCommande(panier, produit, 1, produit.Prix, taxe.Valeur);
                //Verifier les doublons
                panier.GetLigneCommandes().add(ligne);
            }
        }
        _Module = "Panier";
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
