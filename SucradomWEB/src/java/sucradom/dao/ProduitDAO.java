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
import sucradom.metier.Produit;
import sucradom.utile.Base;

/**
 *
 * @author user
 */
public abstract class ProduitDAO 
{
    private static String _Properties = "Produit.ID,Produit.Libelle,Produit.Description,Produit.Prix,Produit.Quantite,Produit.IsDisponible,Produit.FID_Categorie,Produit.FID_Image";
    
    public static Produit Select(int ID)
    {
        Produit produit = null;
        
        String query = " SELECT " + _Properties
                     + " FROM Produit "
                     + " WHERE Produit.ID = ? ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                produit = GetProduit(rs);
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
        
        return produit;
    }
    public static ArrayList<Produit> List()
    {
        ArrayList<Produit> listProduits = null;
        
        String query = " SELECT " + _Properties
                     + " FROM Produit ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Produit produit = GetProduit(rs);
                if (produit != null) 
                {
                    listProduits.add(produit);
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
        
        return listProduits;
    }
    
    public static Produit GetProduit(ResultSet RS)
    {
        try 
        {
            Produit produit = new Produit
                (
                    RS.getInt("ID"),
                    RS.getString("Libelle"),
                    RS.getString("Description"),
                    RS.getFloat("Prix"),
                    RS.getInt("Quantite"),
                    RS.getString("IsDisponible"),
                    CategorieDAO.Select(RS.getInt("FID_Categorie")),
                    ImageDAO.Select(RS.getInt("FID_Image")),
                    TaxeDAO.Select(RS.getInt("FID_Taxe"))
                );
            return produit;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
}
