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
import sucradom.metier.Categorie;
import sucradom.utile.Base;

/**
 *
 * @author user
 */
public abstract class CategorieDAO 
{
    private static String _Properties = "Categorie.ID,Categorie.Libelle,Categorie.FID_Image";
    
    public static Categorie Select(int ID)
    {
        Categorie categorie = null;
        
        String query = " SELECT " + _Properties
                     + " FROM Categorie "
                     + " WHERE Categorie.ID = ? ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                categorie = GetCategorie(rs);
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
        
        return categorie;
    }
    public static ArrayList<Categorie> List()
    {
        ArrayList<Categorie> listCategories = null;
        
        String query = " SELECT " + _Properties
                     + " FROM Categorie ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Categorie categorie = GetCategorie(rs);
                if (categorie != null) 
                {
                    listCategories.add(categorie);
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
        
        return listCategories;
    }
    
    public static Categorie GetCategorie(ResultSet RS)
    {
        try 
        {
            Categorie categorie = new Categorie
                (
                    RS.getInt("ID"),
                    RS.getString("Libelle"),
                    ImageDAO.Select(RS.getInt("FID_Image"))
                );
            return categorie;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
}
