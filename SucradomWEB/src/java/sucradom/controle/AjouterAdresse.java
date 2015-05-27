/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sucradom.dao.AdresseDAO;
import sucradom.dao.ClientDAO;
import sucradom.metier.Adresse;
import sucradom.metier.Client;
import sucradom.utile.Session;

/**
 *
 * @author user
 */
public class AjouterAdresse extends HttpServlet {
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
        String module = "";
        if (client != null) 
        {
            //Client est déjà connecté redirection sur page compte
            request.setAttribute("Erreur", null);
            module = "AjouterAdresse";
        }
        else
        {
            //Client est pas connecté et doit se connecter
            request.setAttribute("Erreur", "Veuillez vous connecter !");
            module = "Connexion";
        }
        Index.RequestDispatcher(request, response, this, "/JSP/Modules/"+module+".jsp");
    }
    
    protected void Try(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Client client = Session.GetClient(request);
        String stringPays = (String) request.getParameter("Pays");
        String stringCP = (String) request.getParameter("CP");
        String stringVille = (String) request.getParameter("Ville");
        String stringRue = (String) request.getParameter("Rue");
        String stringNumero = (String) request.getParameter("Numero");
        
        String servlet = "";
        if(stringPays != null 
        && stringCP != null 
        && stringVille != null 
        && stringRue != null 
        && stringNumero != null)
        { 
            Adresse adresse = new Adresse(0, stringNumero, stringRue, stringCP, stringVille, stringPays, client);
            if(AdresseDAO.Insert(adresse) != 1)
            {
                request.setAttribute("Erreur", "Problème survenu lors de l'enregistrement de l'adresse !");
            }
            servlet = "Adresses";
        }
        else
        {
            request.setAttribute("Erreur", "Veuillez remplir tous les champs !");
            servlet = "AjouterAdresses";
        }
        Index.RequestDispatcher(request, response, this, "/"+servlet);
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
