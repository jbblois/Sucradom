/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.utile;

import javax.servlet.http.HttpServletRequest;
import sucradom.metier.Client;
import sucradom.metier.TeteCommande;

/**
 *
 * @author user
 */
public abstract class Session
{
    public static Client GetClient(HttpServletRequest request)
    {
        return (Client)request.getSession(true).getAttribute("Client");
    }
    public static void SetClient(HttpServletRequest request, Client client)
    {
       request.getSession(true).setAttribute("Client",client);
    }
    
    public static TeteCommande GetPanier(HttpServletRequest request)
    {
        return (TeteCommande)request.getSession(true).getAttribute("Panier");
    }
    public static void SetPanier(HttpServletRequest request, TeteCommande panier)
    {
       request.getSession(true).setAttribute("Panier",panier);
    }
    public static void SetPanier(HttpServletRequest request)
    {
        TeteCommande panier = new TeteCommande(0, null, GetClient(request), null, null, true);
        request.getSession(true).setAttribute("Panier",panier);
    }
}
