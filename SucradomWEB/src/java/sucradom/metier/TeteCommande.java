/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.metier;

import java.sql.Date;

/**
 *
 * @author user
 */
public class TeteCommande 
{
    public int ID;
    public Date Date;
    public float PrixTotal;
    public Client Client;
    public EtatCommande EtatCommande;
    public Adresse Adresse;

    public TeteCommande(int ID, Date Date, float PrixTotal, Client Client, EtatCommande EtatCommande, Adresse Adresse) 
    {
        this.ID = ID;
        this.Date = Date;
        this.PrixTotal = PrixTotal;
        this.Client = Client;
        this.EtatCommande = EtatCommande;
        this.Adresse = Adresse;
    }
}
