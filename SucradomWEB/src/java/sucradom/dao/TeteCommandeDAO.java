/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.dao;

import java.util.ArrayList;
import sucradom.metier.TeteCommande;


/**
 *
 * @author user
 */
public abstract class TeteCommandeDAO 
{
    private static String _Properties = "ID,";
    
    public static TeteCommande Select(int ID)
    {
        return null;
    }
    public static ArrayList<TeteCommande> List()
    {
        return null;
    }
}
