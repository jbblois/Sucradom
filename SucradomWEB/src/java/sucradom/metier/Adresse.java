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
public class Adresse 
{
    public int ID;
    public String Numero;
    public String Rue;
    public String Cp;
    public String Ville;
    public String Pays;
    public Client Client;

    public Adresse(int ID, String Numero, String Rue, String Cp, String Ville, String Pays, Client Client) 
    {
        this.ID = ID;
        this.Numero = Numero;
        this.Rue = Rue;
        this.Cp = Cp;
        this.Ville = Ville;
        this.Pays = Pays;
        this.Client = Client;
    }

    @Override
    public String toString() 
    {
        return this.Numero+" rue "+this.Rue+", "+this.Cp+" "+this.Ville+" "+this.Pays.toUpperCase();
    }
    
    
    
}
