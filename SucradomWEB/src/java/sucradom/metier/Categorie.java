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
public class Categorie 
{
    public int ID;
    public String Libelle;
    public Image Image;

    public Categorie(int ID, String Libelle, Image Image) 
    {
        this.ID = ID;
        this.Libelle = Libelle;
        this.Image = Image;
    }
}
