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
import sucradom.utile.Base;
/**
 *
 * @author user
 */
public abstract class TeteCommandeDAO 
{
    private static String _Properties = "tetecommande.ID,tetecommande.Date,tetecommande.FID_Client,tetecommande.FID_Etat,tetecommande.FID_Adresse";
    
    public static TeteCommande Select(int ID)
    {
        TeteCommande teteCommande = null;
        
        String query = " SELECT " + _Properties
                     + " FROM tetecommande "
                     + " WHERE tetecommande.ID = ? ;";
        
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
    public static TeteCommande SelectLast()
    {
        TeteCommande teteCommande = null;
        
        String query = " SELECT " + _Properties
                     + " FROM tetecommande"
                     + " WHERE tetecommande.ID = "
                     + " (" 
                     + "    SELECT MAX(tetecommande.ID)" 
                     + "    FROM tetecommande" 
                     + " );";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
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
                     + " FROM tetecommande ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            listTeteCommandes = new ArrayList<TeteCommande>();
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
    public static ArrayList<TeteCommande> CommandesOfClient(int FID_Client)
    {
        ArrayList<TeteCommande> listTeteCommandes = null;
        
        String query = " SELECT " + _Properties
                     + " FROM tetecommande "
                     + " WHERE tetecommande.FID_Client=?;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, FID_Client);
            ResultSet rs = ps.executeQuery();
            listTeteCommandes = new ArrayList<TeteCommande>();
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
    
    public static ArrayList<TeteCommande> CommandesOfAdresse(int FID_Adresse)
    {
        ArrayList<TeteCommande> listTeteCommandes = null;
        
        String query = " SELECT " + _Properties
                     + " FROM tetecommande "
                     + " WHERE tetecommande.FID_Adresse=?;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, FID_Adresse);
            ResultSet rs = ps.executeQuery();
            listTeteCommandes = new ArrayList<TeteCommande>();
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
                    AdresseDAO.Select(RS.getInt("FID_Adresse")),
                    false
                );
            return teteCommande;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
    
    public static int Insert(TeteCommande Panier)
    {   
        String query = " INSERT INTO tetecommande(tetecommande.Date,tetecommande.FID_Client,tetecommande.FID_Etat,tetecommande.FID_Adresse)"
                     + " VALUES(?,?,?,?);";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setDate(1, Panier.Date);
            ps.setInt(2, Panier.Client.ID);
            ps.setInt(3, Panier.EtatCommande.ID);
            ps.setInt(4, Panier.Adresse.ID);
            
            return ps.executeUpdate();
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
        return 0;
    }
}
