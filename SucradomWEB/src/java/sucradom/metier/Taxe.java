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
public class Taxe 
{
    public int ID;
    public String Nom;
    public float Valeur;

    public Taxe(int ID, String Nom, float Valeur) 
    {
        this.ID = ID;
        this.Nom = Nom;
        this.Valeur = Valeur;
    }
}
