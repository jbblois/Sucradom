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
public class Provision 
{
    public int ID;
    public Date Date;
    public int Quantite;
    public Produit Produit;

    public Provision(int ID, Date Date, int Quantite, Produit Produit) 
    {
        this.ID = ID;
        this.Date = Date;
        this.Quantite = Quantite;
        this.Produit = Produit;
    }
}
