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
import sucradom.dao.ClientDAO;
import sucradom.metier.Client;
import sucradom.utile.Session;

/**
 *
 * @author user
 */
public class ModifierMDP extends HttpServlet {
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
                case "Try":
                    Try(request, response);
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
    
    protected void Go(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Client client = Session.GetClient(request);
        if (client != null) 
        {
            //Client est déjà connecté redirection sur page compte
            Index.RequestDispatcher(request, response, this, "/JSP/Modules/ModifierMDP.jsp");
        }
        else
        {
            //Client est pas connecté et doit se connecter
            request.setAttribute("Erreur", "Veuillez vous connecter !");
            Index.RequestDispatcher(request, response, this, "/Connexion");
        }
    }
    
    protected void Try(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Client client = Session.GetClient(request);
        String oldMDP = (String) request.getParameter("OldMDP");
        String newMDP = (String) request.getParameter("NewMDP");
        String repMDP = (String) request.getParameter("RepMDP");
        
        String erreur = "";
        if(client.MotDePasse.equals(oldMDP))
        {
            if(!newMDP.equals(""))
            {
                if(!newMDP.equals(oldMDP))
                {
                    if(!newMDP.equals(repMDP))
                    {
                        erreur = "La confirmation du mot de passe n'est pas identique au nouveau mot de passe !";
                    }
                }
                else
                {
                    erreur = "Veuillez saisir un nouveau mot de passe différent de l'ancien !";
                }
            }
            else
            {
                erreur = "Veuillez saisir votre ancien mot de passe !";
            }
        }
        else
        {
            erreur = "Veuillez saisir votre ancien mot de passe !";
        }
        

        if(erreur.equals(""))
        {
            client.MotDePasse = newMDP;
            if(ClientDAO.Update(client) == 1)
            {
                Index.RequestDispatcher(request, response, this, "/Compte");
            }
            else
            {
                erreur = "Erreur lors de la sauvegarde du nouveau mot de passe";
            }
            
        }
        
        if(!erreur.equals(""))
        {
            request.setAttribute("Erreur", erreur);
            Index.RequestDispatcher(request, response, this, "/ModfierMDP");
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
