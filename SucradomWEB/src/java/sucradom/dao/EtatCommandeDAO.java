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
import sucradom.metier.EtatCommande;
import sucradom.utile.Base;



/**
 *
 * @author user
 */
public abstract class EtatCommandeDAO 
{
    private static String _Properties = "EtatCommande.ID,EtatCommande.Nom";
    public static EtatCommande Select(int ID)
    {
        EtatCommande etatCommande = null;
        
        String query = " SELECT " + _Properties
                     + " FROM EtatCommande "
                     + " WHERE EtatCommande.ID = ? ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                etatCommande = GetEtatCommande(rs);
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
        
        return etatCommande;
    }
    public static ArrayList<EtatCommande> List()
    {
        ArrayList<EtatCommande> listEtatCommandes = null;
        
        String query = " SELECT " + _Properties
                     + " FROM EtatCommande ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                EtatCommande etatCommande = GetEtatCommande(rs);
                if (etatCommande != null) 
                {
                    listEtatCommandes.add(etatCommande);
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
        
        return listEtatCommandes;
    }
    
    public static EtatCommande GetEtatCommande(ResultSet RS)
    {
        try 
        {
            EtatCommande etatCommande = new EtatCommande
                (
                    RS.getInt("ID"),
                    RS.getString("Nom")
                );
            return etatCommande;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
}
