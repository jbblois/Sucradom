/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.dao;

import java.util.ArrayList;
import sucradom.metier.Produit;


/**
 *
 * @author user
 */
public abstract class ProduitDAO 
{
    private static String _Properties = "ID,";
    
    public static Produit Select(int ID)
    {
        return null;
    }
    public static ArrayList<Produit> List()
    {
        return null;
    }
}
