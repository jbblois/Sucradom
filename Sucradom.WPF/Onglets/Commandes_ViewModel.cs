using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Onglets
{
    public class Commandes_ViewModel : ViewModel
    {
        private tetecommande _SelectedCommande;
        public tetecommande SelectedCommande
        {
            get { return _SelectedCommande; }
            set 
            {
                _SelectedCommande = value;
                NotifyPropertyChanged();
            }
        }


        public Commandes_ViewModel()
        {

        }

        public void AjouterCommande()
        {   
            new Fenetres.Commande().ShowDialog();
        }

        public Boolean ModifierCommande()
        {
            if (SelectedCommande != null)
            {
                //********
                // PENSER A AJOUTER LE CONSTRUCTEUR DE MODIFICATION
                //new Fenetres.Commande(SelectedCommande).ShowDialog();
                return true;
            }
            return false;
        }

        /// <summary>
        /// PENSER A FAIRE LES ETAPES DE SUPRESSION
        /// </summary>
        /// <returns></returns>
        public Boolean SupprimerCommande()
        {
            if (SelectedCommande != null)
            {
                
                return true;
            }
            return false;
        }
    }
}
