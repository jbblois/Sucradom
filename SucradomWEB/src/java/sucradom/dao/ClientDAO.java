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
    private static String _Properties = "[Client].ID,[Client].Nom,[Client].Prenom,[Client].Telephone,[Client].Email,[Client].MotDePasse,[Client].IsActif";
    
    public static Client Select(int ID)
    {
        Client client = null;
        
        String query = " SELECT " + _Properties
                     + " FROM [Client] "
                     + " WHERE [Client].ID = ? ;";
        
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
    public static ArrayList<Client> List()
    {
        ArrayList<Client> listClients = null;
        
        String query = " SELECT " + _Properties
                     + " FROM [Client] ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            listClients = new ArrayList<Client>();
            while(rs.next()) {
                Client client = GetClient(rs);
                if (client != null) 
                {
                    listClients.add(client);
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
        
        return listClients;
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
