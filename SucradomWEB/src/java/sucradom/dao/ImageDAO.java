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
import sucradom.metier.Image;
import sucradom.utile.Base;



/**
 *
 * @author user
 */
public abstract class ImageDAO 
{
    private static String _Properties = "Image.ID,Image.Path,Image.Alt";
    
    public static Image Select(int ID)
    {
        Image image = null;
        
        String query = " SELECT " + _Properties
                     + " FROM Image "
                     + " WHERE Image.ID = ? ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, ID);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                image = GetImage(rs);
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
        
        return image;
    }
    public static ArrayList<Image> List()
    {
        ArrayList<Image> listImages = null;
        
        String query = " SELECT " + _Properties
                     + " FROM Image ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Image image = GetImage(rs);
                if (image != null) 
                {
                    listImages.add(image);
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
        
        return listImages;
    }
    
    public static Image GetImage(ResultSet RS)
    {
        try 
        {
            Image image = new Image
                (
                    RS.getInt("ID"),
                    RS.getString("Path"),
                    RS.getString("Alt")
                );
            return image;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
}
