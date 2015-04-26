/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.dao;

import java.util.ArrayList;
import sucradom.metier.Taxe;


/**
 *
 * @author user
 */
public abstract class TaxeDAO 
{
    private static String _Properties = "ID,";
    
    public static Taxe Select(int ID)
    {
        return null;
    }
    public static ArrayList<Taxe> List()
    {
        return null;
    }
}
