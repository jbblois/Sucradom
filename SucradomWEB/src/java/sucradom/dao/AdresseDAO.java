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
import sucradom.metier.Adresse;
import sucradom.utile.Base;



/**
 *
 * @author user
 */
public abstract class AdresseDAO 
{
    private static String _Properties = "Adresse.ID,Adresse.Numero,Adresse.Rue,Adresse.Cp,Adresse.Ville,Adresse.Pays,Adresse.FID_Client";
    
    public static Adresse Select(int ID)
    {
        Adresse adresse = null;
        
        String query = " SELECT " + _Properties
                     + " FROM Adresse "
                     + " WHERE Adresse.ID = ? ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                adresse = GetAdresse(rs);
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
        
        return adresse;
    }
    public static ArrayList<Adresse> List()
    {
        ArrayList<Adresse> listAdresses = null;
        
        String query = " SELECT " + _Properties
                     + " FROM Adresse ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Adresse adresse = GetAdresse(rs);
                if (adresse != null) 
                {
                    listAdresses.add(adresse);
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
        
        return listAdresses;
    }
    
    public static ArrayList<Adresse> AdressesOfClient(int FID_Client)
    {
        ArrayList<Adresse> listAdresses = null;
        
        String query = " SELECT " + _Properties
                     + " FROM Adresse"
                     + " WHERE Adresse.FID_Client = ?;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, FID_Client);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                Adresse adresse = GetAdresse(rs);
                if (adresse != null) 
                {
                    listAdresses.add(adresse);
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
        
        return listAdresses;
    }
    
    public static Adresse GetAdresse(ResultSet RS)
    {
        try 
        {
            Adresse adresse = new Adresse
                (
                    RS.getInt("ID"),
                    RS.getString("Numero"),
                    RS.getString("Rue"),
                    RS.getString("Cp"),
                    RS.getString("Ville"),
                    RS.getString("Pays"),
                    ClientDAO.Select(RS.getInt("FID_Client"))
                );
            return adresse;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
}
