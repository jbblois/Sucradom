/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static sucradom.dao.ClientDAO.GetClient;
import sucradom.metier.Produit;
import sucradom.utile.Base;

/**
 *
 * @author user
 */
public abstract class RequetesSQL 
{
    public static int QuantiteDisponible(int FID_Produit)
    {
        return QuantiteAchete(FID_Produit) - QuantiteVendue(FID_Produit);
    }
    private static int QuantiteAchete(int FID_Produit)
    {
        int achats = 0;
        
        String query = " SELECT SUM(provision.Quantite) AS Achats"
                     + " FROM provision"
                     + " WHERE provision.FID_Produit = ?"
                     + " AND provision.Date < NOW();";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, FID_Produit);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                achats = rs.getInt("Achats");
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
        
        return achats;
    }
    private static int QuantiteVendue(int FID_Produit)
    {
        int ventes = 0;
        
        String query = " SELECT SUM(lignecommande.Quantite) AS Ventes"
                     + " FROM teteCommande"
                     + " INNER JOIN ligneCommande ON teteCommande.ID = ligneCommande.FID_Commande"
                     + " WHERE teteCommande.Date <  NOW()"
                     + " AND teteCommande.FID_Etat > 1 AND teteCommande.FID_Etat < 5"
                     + " AND ligneCommande.FID_Produit = ?;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, FID_Produit);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                ventes = rs.getInt("Achats");
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
        
        return ventes;
    }
}
