using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Onglets
{
    public class Produits_ViewModel : ViewModel
    {
        private produit _SelectedProduit;
        public produit SelectedProduit
        {
            get { return _SelectedProduit; }
            set 
            {
                _SelectedProduit = value;
                NotifyPropertyChanged();
            }
        }


        public Produits_ViewModel()
        {

        }

        public void AjouterProduit()
        {   
            new Formulaires.Produit().ShowDialog();
        }

        public Boolean ModifierProduit()
        {
            if (SelectedProduit != null)
            {
                //********
                // PENSER A AJOUTER LE CONSTRUCTEUR DE MODIFICATION
                //new Formulaires.Produit(SelectedProduit).ShowDialog();
                return true;
            }
            return false;
        }

        /// <summary>
        /// PENSER A FAIRE LES ETAPES DE SUPRESSION
        /// </summary>
        /// <returns></returns>
        public Boolean SupprimerProduit()
        {
            if (SelectedProduit != null)
            {
                
                return true;
            }
            return false;
        }
    }
}

