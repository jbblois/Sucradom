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
public class Connexion extends HttpServlet {

    private String _Module = "Acceuil";
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
                case "Deconnexion":
                    Deconnexion(request, response);
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
        this.getServletContext().getRequestDispatcher("/JSP/Modules/"+_Module+".jsp" ).forward( request, response );
    }
    
    protected void Go(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Client client = Session.GetClient(request);
        if (client != null) 
        {
            //Client est déjà connecté redirection sur page compte
            request.setAttribute("Erreur", null);
            _Module = "Compte";
        }
        else
        {
            //Client est pas connecté et peut se connecter
            request.setAttribute("Erreur", null);
            _Module = "Connexion";
        }
    }
    
    protected void Deconnexion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
            Session.SetClient(request, null);
            _Module = "Accueil";
    }
    
    protected void Try(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if(Session.GetClient(request) == null)
        {
            String email = (String) request.getParameter("Email");
            String motDePasse = (String) request.getParameter("MotDePasse");
            Client client = ClientDAO.Select(email, motDePasse);
            if (client != null) 
            {
                //Client à réussit à se connecter
                Session.SetClient(request, client);
                request.setAttribute("Erreur", null);
                _Module = "Compte";
            }
            else
            {
                //Client n'a pas réussit à se connecter
                request.setAttribute("Erreur", "Veuillez vérifier l'email ou le mot de passe saisi");
                _Module = "Connexion";
            }
        }
        else
        {
            _Module = "Compte";
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
