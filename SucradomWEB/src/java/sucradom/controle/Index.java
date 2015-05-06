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
import sucradom.dao.ClientDAO;
import sucradom.dao.ProduitDAO;
import sucradom.metier.Client;

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
                case "Accueil":
                    GoAccueil(request, response);
                    break;
                case "Catalogue":
                    GoCatalogue(request, response);
                    break;
                case "Compte":
                    GoCompte(request, response);
                    break;
                case "ModifierMDP":
                    ModifierMDP(request, response);
                    break;
                case "TryModifierMDP":
                    TryModifierMDP(request, response);
                    break;
                case "Connexion":
                    GoConnexion(request, response);
                    break;
                case "TryConnexion":
                    TryConnexion(request, response);
                    break;
                case "Deconnexion":
                    Deconnexion(request, response);
                    break;
                case "Panier":
                    GoPanier(request, response);
                    break;
                case "Produit":
                    GoProduit(request, response);
                    break;
                case "Commandes":
                    GoCommandes(request, response);
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
    protected void GoPanier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Panier" ).forward( request, response );
    }
    
    //<editor-fold desc="Redirection liées à la page Connexion">
        protected void GoConnexion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Client client = (Client)request.getSession(true).getAttribute("Client");
        if (client != null) 
        {
            //Client est déjà connecté redirection sur page compte
            request.setAttribute("Erreur", null);
            this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Compte" ).forward( request, response );
        }
        else
        {
            //Client est pas connecté et peut se connecter
            request.setAttribute("Erreur", null);
            this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Connexion" ).forward( request, response );
        }
    }
        
    protected void Deconnexion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.getSession(true).setAttribute("Client",null);
        GoAccueil(request, response);
    }
    
    protected void TryConnexion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if(request.getSession(true).getAttribute("Client") == null)
        {
            String email = (String) request.getParameter("Email");
            String motDePasse = (String) request.getParameter("MotDePasse");
            Client client = ClientDAO.Select(email, motDePasse);
            if (client != null) 
            {
                //Client à réussit à se connecter
                request.getSession(true).setAttribute("Client",client);
                request.setAttribute("Erreur", null);
                this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Compte" ).forward( request, response );
            }
            else
            {
                //Client n'a pas réussit à se connecter
                request.setAttribute("Erreur", "Veuillez vérifier l'email ou le mot de passe saisi");
                this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Connexion" ).forward( request, response );
            }
        }
        else
        {
            GoCompte(request, response);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Redirection liées à la page Compte">
    protected void GoCompte(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Client client = (Client)request.getSession(true).getAttribute("Client");
        if (client != null) 
        {
            //Client est connecté
            request.setAttribute("Erreur", null);
            this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Compte" ).forward( request, response );
        }
        else
        {
            //Client est pas connecté redirection sur page connexion
            request.setAttribute("Erreur", "Veuillez vous connecter");
            this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Connexion" ).forward( request, response );
        }
    }
    protected void ModifierMDP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.setAttribute("Erreur", null);
        this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=ModifierMDP" ).forward( request, response );
            
    }
    protected void TryModifierMDP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String oldMDP = (String) request.getParameter("OldMDP");
        String newMDP = (String) request.getParameter("NewMDP");
        String repMDP = (String) request.getParameter("RepMDP");
        Client client =  (Client) request.getSession(true).getAttribute("Client");
        String erreur = "";
        if (client != null) 
        {
            if(client.MotDePasse.equals(oldMDP))
            {
                if(!oldMDP.equals(newMDP))
                {
                    if(!newMDP.equals(repMDP))
                    {
                        erreur = "Veuillez la confirmaion n'est pas identique au nouveau mot de passe";
                    }
                }
                else
                {
                    erreur = "Veuillez saisir un mot de passe différent de l'actuel";
                }
            }
            else
            {
                erreur = "Veuillez saisir votre mot de passe";
            }
            
            if(erreur.equals(""))
            {
                //On UPDATE le client
                request.setAttribute("Erreur", null);
                client.MotDePasse = newMDP;
                if(ClientDAO.Update(client) == 1)
                {
                    this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Compte" ).forward( request, response );
                }
                else
                {
                    request.setAttribute("Erreur", "Erreur : la modification n'a pas pu être sauvegardée !");
                    this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=ModifierMDP" ).forward( request, response );
                }
            }
            else
            {
                //On redirige sur le formulaire de modification.
                request.setAttribute("Erreur", erreur);
                this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=TryModifierMDP" ).forward( request, response );
            }
        }
        else
        {
            GoCompte(request, response);
        }
    }
    protected void GoCommandes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        this.getServletContext().getRequestDispatcher( "/JSP/Index.jsp?JSPfolder=Commandes" ).forward( request, response );
    }
    //</editor-fold>
    
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
