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
import sucradom.metier.Client;
import sucradom.utile.Base;



/**
 *
 * @author user
 */
public abstract class ClientDAO 
{
    private static String _Properties = "client.ID,client.Nom,client.Prenom,client.Telephone,client.Email,client.MotDePasse,client.IsActif";
    
    public static Client Select(int ID)
    {
        Client client = null;
        
        String query = " SELECT " + _Properties
                     + " FROM client "
                     + " WHERE client.ID = ? ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                client = GetClient(rs);
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
        
        return client;
    }
    public static Client Select(String Email, String MotDePasse)
    {
        Client client = null;
        
        String query = " SELECT " + _Properties
                     + " FROM client"
                     + " WHERE client.Email = ?"
                     + " AND client.MotDePasse = ?;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setString(1, Email);
            ps.setString(2, MotDePasse);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                client = GetClient(rs);
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
        
        return client;
    }
    public static ArrayList<Client> List()
    {
        ArrayList<Client> listclients = null;
        
        String query = " SELECT " + _Properties
                     + " FROM client ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            listclients = new ArrayList<Client>();
            while(rs.next()) {
                Client client = GetClient(rs);
                if (client != null) 
                {
                    listclients.add(client);
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
        
        return listclients;
    }
    
    public static int Update(Client client)
    {
        int rowUpdated = 0;
        String query = " UPDATE client SET client.Nom = ?,client.Prenom = ?,client.Telephone = ?,client.Email = ?,client.MotDePasse = ?"
                     + " WHERE client.ID = ?;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setString(1, client.Nom);
            ps.setString(2, client.Prenom);
            ps.setString(3, client.Telephone);
            ps.setString(4, client.Email);
            ps.setString(5, client.MotDePasse);
            ps.setInt(6, client.ID);
            
            rowUpdated = ps.executeUpdate();
            ps.close();
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
        
        return rowUpdated;
    }
    
    public static Client GetClient(ResultSet RS)
    {
        try 
        {
            Client client = new Client
                (
                    RS.getInt("ID"),
                    RS.getString("Nom"),
                    RS.getString("Prenom"),
                    RS.getString("Telephone"),
                    RS.getString("Email"),
                    RS.getString("MotDePasse"),
                    RS.getString("IsActif")
                );
            return client;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
}
