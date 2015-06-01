/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static sucradom.dao.TaxeDAO.GetTaxe;
import sucradom.metier.Taxe;
import sucradom.utile.Base;

/**
 *
 * @author user
 */
public abstract class TaxeDAO 
{
    private static String _Properties = "taxe.ID,taxe.Nom,taxe.Valeur";
    
    public static Taxe Select(int ID)
    {
        Taxe taxe = null;
        
        String query = " SELECT " + _Properties
                     + " FROM taxe "
                     + " WHERE taxe.ID = ? ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                taxe = GetTaxe(rs);
            }
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        return taxe;
    }
    public static ArrayList<Taxe> List()
    {
        ArrayList<Taxe> listTaxes = null;
        
        String query = " SELECT " + _Properties
                     + " FROM taxe ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            listTaxes = new ArrayList<Taxe>();
            while(rs.next()) {
                Taxe taxe = GetTaxe(rs);
                if (taxe != null) 
                {
                    listTaxes.add(taxe);
                }
            }
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        return listTaxes;
    }
    
        public static Taxe GetTaxe(ResultSet RS)
    {
        try 
        {
            Taxe taxe = new Taxe
                (
                    RS.getInt("ID"),
                    RS.getString("Nom"),
                    RS.getFloat("Valeur")
                );
            return taxe;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
}
