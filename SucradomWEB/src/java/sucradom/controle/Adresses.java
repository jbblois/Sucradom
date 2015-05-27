/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.controle;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static sucradom.controle.Index.RequestDispatcher;
import sucradom.dao.AdresseDAO;
import sucradom.dao.TeteCommandeDAO;
import sucradom.metier.TeteCommande;
import sucradom.utile.Session;

/**
 *
 * @author user
 */
public class Adresses extends HttpServlet {
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
        String methode = (String) request.getParameter("Methode");
        if(methode != null)
        {
            switch(methode) 
            {
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
    
    private void Go(HttpServletRequest request, HttpServletResponse response)
    {
        String module = "";
        if(Session.GetClient(request) == null)
        {
            module ="Connexion";
        }
        else
        {
            module ="Adresses";
        }
        Index.RequestDispatcher(request, response, this, "/JSP/Modules/"+module+".jsp");
    }
    
    private void Delete(HttpServletRequest request, HttpServletResponse response)
    {
        String stringID = (String)request.getParameter("IDadresse");
        if (stringID != null) 
        {
            int IDadresse =  Integer.parseInt(stringID);
            ArrayList<TeteCommande> commandes = TeteCommandeDAO.CommandesOfAdresse(IDadresse);
            if(commandes != null)
            {
                if(commandes.size() == 0)
                {
                    if(AdresseDAO.Delete(IDadresse) != 1)
                    {
                        request.setAttribute("Erreur", "Problème survenu lors de la suppresion !");
                    }
                }
                else
                {
                    request.setAttribute("Erreur", "Cette adresse est utilisée pour une commande !");
                }
            }
        }
        Index.RequestDispatcher(request, response, this, "/Adresses");
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
