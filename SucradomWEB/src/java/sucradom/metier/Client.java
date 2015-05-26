/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.metier;

import java.util.ArrayList;
import sucradom.dao.AdresseDAO;
import sucradom.dao.TeteCommandeDAO;

/**
 *
 * @author user
 */
public class Client 
{
    public int ID;
    public String Nom;
    public String Prenom;
    public String Telephone;
    public String Email;
    public String MotDePasse;
    public String IsActif;

    public Client(int ID, String Nom, String Prenom, String Telephone, String Email, String MotDePasse, String IsActif) 
    {
        this.ID = ID;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Telephone = Telephone;
        this.Email = Email;
        this.MotDePasse = MotDePasse;
        this.IsActif = IsActif;
    }
    
    private ArrayList<TeteCommande> _Commandes;
    public ArrayList<TeteCommande> GetCommandes()
    {
        if (_Commandes == null) 
        {
            _Commandes = TeteCommandeDAO.CommandesOfClient(ID);
        }
        return _Commandes;
    }
    
    private ArrayList<Adresse> _Adresses;
    public ArrayList<Adresse> GetAdresses()
    {
        if (_Adresses == null) 
        {
            _Adresses = AdresseDAO.AdressesOfClient(ID);
        }
        return _Adresses;
    }
}
