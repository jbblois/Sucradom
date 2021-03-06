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
    private static String _Properties = "adresse.ID,adresse.Numero,adresse.Rue,adresse.Cp,adresse.Ville,adresse.Pays,adresse.FID_Client";
    
    public static Adresse Select(int ID)
    {
        Adresse adresse = null;
        
        String query = " SELECT " + _Properties
                     + " FROM adresse "
                     + " WHERE adresse.ID = ? ;";
        
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
                     + " FROM adresse ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            listAdresses = new ArrayList<Adresse>();
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
                     + " FROM adresse"
                     + " WHERE adresse.FID_Client = ?;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, FID_Client);
            ResultSet rs = ps.executeQuery();
            listAdresses = new ArrayList<Adresse>();
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
    
    public static int Insert(Adresse Adresse)
    {
        
        String query = " INSERT INTO adresse (adresse.Numero,adresse.Rue,adresse.Cp,adresse.Ville,adresse.Pays,adresse.FID_Client)"
                     + " VALUES(?,?,?,?,?,?)";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setString(1, Adresse.Numero);
            ps.setString(2, Adresse.Rue);
            ps.setString(3, Adresse.Cp);
            ps.setString(4, Adresse.Ville);
            ps.setString(5, Adresse.Pays);
            ps.setInt(6, Adresse.Client.ID);

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
    
    public static int Delete(int IDadresse)
    {
        
        String query = " DELETE FROM adresse"
                     + " WHERE adresse.ID = ?;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, IDadresse);

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
