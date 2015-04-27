/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.metier;

import java.sql.Date;
import java.util.ArrayList;
import sucradom.dao.LigneCommandeDAO;
import sucradom.dao.TeteCommandeDAO;

/**
 *
 * @author user
 */
public class TeteCommande 
{
    public int ID;
    public Date Date;
    public Client Client;
    public EtatCommande EtatCommande;
    public Adresse Adresse;

    public TeteCommande(int ID, Date Date, Client Client, EtatCommande EtatCommande, Adresse Adresse) 
    {
        this.ID = ID;
        this.Date = Date;
        this.Client = Client;
        this.EtatCommande = EtatCommande;
        this.Adresse = Adresse;
    }
    
    private ArrayList<LigneCommande> _LigneCommandes;
    public ArrayList<LigneCommande> GetLigneCommandes()
    {
        if (_LigneCommandes == null) 
        {
            _LigneCommandes = LigneCommandeDAO.LignesOfCommande(ID);
        }
        return _LigneCommandes;
    }
}
