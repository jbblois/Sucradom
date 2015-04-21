using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;
namespace Sucradom.WPF.Fenetres
{
    public class Taxes_ViewModel : ViewModel
    {
        private taxe _SelectedTaxe;
        public taxe SelectedTaxe
        {
            get { return _SelectedTaxe; }
            set 
            { 
                _SelectedTaxe = value;
                NotifyPropertyChanged();
            }
        }

        public Taxes_ViewModel()
        {
                
        }

        public void AjouterTaxe()
        {
            // ***** 
            // Penser à ajouter le constructeur d'insertion
            //new Formulaires.Taxe().ShowDialog();
        }
        public Boolean ModifierTaxe()
        {
            if (SelectedTaxe != null)
            {
                // ***** 
                // Penser à ajouter le constructeur de modification
                //new Formulaires.Taxe(SelectedTaxe).ShowDialog();
                return true;
            }
            return false;
        }
        public Boolean SupprimerTaxe()
        {
            if (SelectedTaxe != null)
            {
                // *****
                // Penser aux vérifications de suppressions
                return true;
            }
            return false;
        }
    }
}
