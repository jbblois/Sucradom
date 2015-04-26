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
public class Produit 
{
    public int ID;
    public String Libelle;
    public String Description;
    public float Prix;
    public String IsDisponible;
    public Categorie Categorie;
    public Image Image;
    public Taxe Taxe;

    public Produit(int ID, String Libelle, String Description, float Prix, String IsDisponible, Categorie Categorie, Image Image, Taxe Taxe) 
    {
        this.ID = ID;
        this.Libelle = Libelle;
        this.Description = Description;
        this.Prix = Prix;
        this.IsDisponible = IsDisponible;
        this.Categorie = Categorie;
        this.Image = Image;
        this.Taxe = Taxe;
    }

}
