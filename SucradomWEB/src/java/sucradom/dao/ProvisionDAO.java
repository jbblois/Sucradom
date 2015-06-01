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
import sucradom.metier.Provision;
import sucradom.utile.Base;

/**
 *
 * @author user
 */
public abstract class ProvisionDAO 
{
    private static String _Properties = "provision.ID,provision.Date,provision.FID_Produit";
    
    public static Provision Select(int ID)
    {
        Provision provision = null;
        
        String query = " SELECT " + _Properties
                     + " FROM provision "
                     + " WHERE provision.ID = ? ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                provision = GetProvision(rs);
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
        
        return provision;
    }
    public static ArrayList<Provision> List()
    {
        ArrayList<Provision> listProvisions = null;
        
        String query = " SELECT " + _Properties
                     + " FROM provision ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Provision provision = GetProvision(rs);
                if (provision != null) 
                {
                    listProvisions.add(provision);
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
        
        return listProvisions;
    }
    
    public static Provision GetProvision(ResultSet RS)
    {
        try 
        {
            Provision provision = new Provision
                (
                    RS.getInt("ID"),
                    RS.getDate("Date"),
                    RS.getInt("Quantite"),
                    ProduitDAO.Select(RS.getInt("FID_Produit"))
                );
            return provision;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
}
