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
import sucradom.dao.CategorieDAO;
import sucradom.dao.ProduitDAO;

/**
 *
 * @author user
 */
public class Index extends HttpServlet {

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
        String JSPfolder = request.getParameter("JSPfolder");
        if (JSPfolder == null) 
        {
            GoCatalogue(request, response);
        }
        else
        {
            switch(JSPfolder)
            {
                case "Catalogue":
                    GoCatalogue(request, response);
                    break;
                case "Compte":
                    GoCompte(request, response);
                    break;
                case "Connexion":
                    GoConnexion(request, response);
                    break;
                case "Produit":
                    GoProduit(request, response);
                    break;
                default:
                    GoAccueil(request, response);
                    break;
            }
        }
    }

    protected void GoAccueil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Accueil" ).forward( request, response );
    }
    
    protected void GoCompte(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Compte" ).forward( request, response );
    }
    
    protected void GoCatalogue(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        if (request.getParameter("IDcategorie") != null) 
        {
            String stringID = (String)request.getParameter("IDcategorie");
            int IDcategorie =  Integer.parseInt(stringID);
            request.setAttribute("SelectedCategorie", CategorieDAO.Select(IDcategorie));
            
        }
        this.getServletContext().getRequestDispatcher("/JSP/Index.jsp?JSPfolder=Catalogue" ).forward( request, response );
    }
    protected void GoProduit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        if (request.getParameter("IDproduit") != null) 
        {
            String stringID = (String)request.getParameter("IDproduit");
            int IDproduit =  Integer.parseInt(stringID);
            request.setAttribute("SelectedProduit", ProduitDAO.Select(IDproduit));
        }
        this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Produit" ).forward( request, response );
    }
    protected void GoConnexion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Connexion" ).forward( request, response );
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
