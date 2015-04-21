using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Fenetres
{
    public class Categories_ViewModel : ViewModel
    {
        private categorie _SelectedCategorie;
        public categorie SelectedCategorie
        {
            get { return _SelectedCategorie; }
            set 
            {
                _SelectedCategorie = value;
                NotifyPropertyChanged();
            }
        }


        public Categories_ViewModel()
        {

        }

        public void AjouterCategorie()
        {   //********
            // PENSER A AJOUTER LE CONSTRUCTEUR D'INSERTION
            //new Formulaires.Categorie().ShowDialog();
        }

        public Boolean ModifierCategorie()
        {
            if (SelectedCategorie != null)
            {
                //********
                // PENSER A AJOUTER LE CONSTRUCTEUR DE MODIFICATION
                //new Formulaires.Categorie(SelectedCategorie).ShowDialog();
                return true;
            }
            return false;
        }

        /// <summary>
        /// ¨PENSER A FAIRE LES ETAPES DE SUPRESSIONS
        /// </summary>
        /// <returns></returns>
        public Boolean SupprimerCategorie()
        {
            if (SelectedCategorie != null)
            {
                
                return true;
            }
            return false;
        }
    }
}
