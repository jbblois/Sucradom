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
import sucradom.metier.TeteCommande;
import sucradom.metier.TeteCommande;
import sucradom.utile.Base;
/**
 *
 * @author user
 */
public abstract class TeteCommandeDAO 
{
    private static String _Properties = "TeteCommande.ID,TeteCommande.Date,TeteCommande.FID_Client,TeteCommande.FID_Etat,TeteCommande.FID_Adresse";
    
    public static TeteCommande Select(int ID)
    {
        TeteCommande teteCommande = null;
        
        String query = " SELECT " + _Properties
                     + " FROM TeteCommande "
                     + " WHERE TeteCommande.ID = ? ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                teteCommande = GetTeteCommande(rs);
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
        
        return teteCommande;
    }
    public static ArrayList<TeteCommande> List()
    {
        ArrayList<TeteCommande> listTeteCommandes = null;
        
        String query = " SELECT " + _Properties
                     + " FROM TeteCommande ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                TeteCommande teteCommande = GetTeteCommande(rs);
                if (teteCommande != null) 
                {
                    listTeteCommandes.add(teteCommande);
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
        
        return listTeteCommandes;
    }
    
    public static TeteCommande GetTeteCommande(ResultSet RS)
    {
        try 
        {
            TeteCommande teteCommande = new TeteCommande
                (
                    RS.getInt("ID"),
                    RS.getDate("Date"),
                    ClientDAO.Select(RS.getInt("FID_Client")),
                    EtatCommandeDAO.Select(RS.getInt("FID_Etat")),
                    AdresseDAO.Select(RS.getInt("FID_Adresse"))
                );
            return teteCommande;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
}
