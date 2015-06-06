/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.metier;

import java.sql.Date;
import java.util.ArrayList;
import sucradom.dao.LigneCommandeDAO;

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

    public TeteCommande(ArrayList<LigneCommande> Lignes) 
    {
        _LigneCommandes = Lignes;
    }

    
    public TeteCommande(int ID, Date Date, Client Client, EtatCommande EtatCommande, Adresse Adresse, boolean InitLignesCommandes) 
    {
        this.ID = ID;
        this.Date = Date;
        this.Client = Client;
        this.EtatCommande = EtatCommande;
        this.Adresse = Adresse;
        if(InitLignesCommandes)
        {
            this._LigneCommandes = new ArrayList<>();
        }
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
    
    public float GetPrixTTC()
    {
        int prixTTC = 0;
        for (LigneCommande ligne : _LigneCommandes) 
        {
            prixTTC += ligne.GetPrixTTC();
        }
        return prixTTC;
    }
    
    public float GetPrixHT()
    {
        int prixHT = 0;
        for (LigneCommande ligne : _LigneCommandes) 
        {
            prixHT += ligne.GetPrixHT();
        }
        return prixHT;
    }
}
