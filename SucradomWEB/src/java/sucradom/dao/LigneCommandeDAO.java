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
import sucradom.metier.LigneCommande;
import sucradom.metier.TeteCommande;
import sucradom.utile.Base;

/**
 *
 * @author user
 */
public abstract class LigneCommandeDAO 
{
    private static String _Properties = "lignecommande.FID_Commande,lignecommande.FID_Produit,lignecommande.Quantite,lignecommande.PrixUnitaire,lignecommande.ValeurTaxe";
    
    public static LigneCommande Select(int FID_Commande, int FID_Produit)
    {
        LigneCommande ligneCommande = null;
        
        String query = " SELECT " + _Properties
                     + " FROM lignecommande "
                     + " WHERE lignecommande.FID_Commande = ? AND lignecommande.FID_Produit = ?;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, FID_Commande);
            ps.setInt(2, FID_Produit);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                ligneCommande = GetLigneCommande(rs);
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
        
        return ligneCommande;
    }
    public static ArrayList<LigneCommande> List()
    {
        ArrayList<LigneCommande> listLigneCommandes = null;
        
        String query = " SELECT " + _Properties
                     + " FROM lignecommande ;";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                LigneCommande ligneCommande = GetLigneCommande(rs);
                if (ligneCommande != null) 
                {
                    listLigneCommandes.add(ligneCommande);
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
        
        return listLigneCommandes;
    }
    public static ArrayList<LigneCommande> LignesOfCommande(int FID_Commande)
    {
        ArrayList<LigneCommande> listLigneCommandes = new ArrayList<>();
        
        String query = " SELECT " + _Properties
                     + " FROM lignecommande"
                     + " WHERE FID_Commande = ?";
        
        PreparedStatement ps = null;
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, FID_Commande);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                LigneCommande ligneCommande = GetLigneCommande(rs);
                if (ligneCommande != null) 
                {
                    listLigneCommandes.add(ligneCommande);
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
        
        return listLigneCommandes;
    }
    public static LigneCommande GetLigneCommande(ResultSet RS)
    {
        try 
        {
            LigneCommande ligneCommande = new LigneCommande
                (
                    TeteCommandeDAO.Select(RS.getInt("FID_Commande")),
                    ProduitDAO.Select(RS.getInt("FID_Produit")),
                    RS.getInt("Quantite"),
                    RS.getFloat("PrixUnitaire"),
                    RS.getFloat("ValeurTaxe")
                );
            return ligneCommande;
        } 
        catch (Exception exc) 
        {
            return null;
        }
    }
    public static int Insert(LigneCommande Ligne)
    {
        String query = " INSERT INTO lignecommande("+_Properties+")"
                     + " VALUES(?,?,?,?,?)";
        
        PreparedStatement ps = null;
        
        try {
            ps = Base.GetConnection().prepareStatement(query);
            ps.setInt(1, Ligne.Commande.ID);
            ps.setInt(2, Ligne.Produit.ID);
            ps.setInt(3, Ligne.Quantite);
            ps.setFloat(4, Ligne.PrixUnitaire);
            ps.setFloat(5, Ligne.ValeurTaxe);
            
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
