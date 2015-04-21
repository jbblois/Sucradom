using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;
using Sucradom.WPF.Onglets;
namespace Sucradom.WPF.Fenetres
{
    public class Accueil_ViewModel : ViewModel
    {
        // Definition des références des UserControls (utilisé commes onglets),
        // et de leurs accesseurs.
        public Clients GestionClients { get; private set; }
        public Commandes GestionCommandes { get; private set; }
        public Produits GestionProduits { get; private set; }

        public Accueil_ViewModel()
        {
            GestionClients = new Clients();
            GestionCommandes = new Commandes();
            GestionProduits = new Produits();
        }
    }
}
