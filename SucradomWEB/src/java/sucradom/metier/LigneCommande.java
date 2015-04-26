/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.metier;

/**
 *
 * @author user
 */
public class LigneCommande 
{
    public TeteCommande Commande;
    public Produit Produit;
    public int Quantite;
    public float PrixTTC;

    public LigneCommande(TeteCommande Commande, Produit Produit, int Quantite, int PrixTTC)
    {
        this.Commande = Commande;
        this.Produit = Produit;
        this.Quantite = Quantite;
        this.PrixTTC = PrixTTC;
    }
}
