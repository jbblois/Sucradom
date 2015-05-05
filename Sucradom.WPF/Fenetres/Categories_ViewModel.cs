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
        {   
            new Formulaires.Categorie().ShowDialog();
        }
        public Boolean ModifierCategorie()
        {
            if (SelectedCategorie != null)
            {
                new Formulaires.Categorie(SelectedCategorie).ShowDialog();
                return true;
            }
            else
            {
                Outils.Alerte("Veuillez selectionner une catégorie !");
            }
            return false;
        }
        public Boolean SupprimerCategorie()
        {
            if (SelectedCategorie != null)
            {
                if (SelectedCategorie.NbProduits == 0)
                {
                    if (Outils.Choix("Voulez-vous supprimer la catégorie selectionnée", "Suppression"))
                    { 
                        Context.categories.Remove(SelectedCategorie);
                        ViewModel.categories.Remove(SelectedCategorie);
                        return true;   
                    }
                                     
                }
                else
                {
                    Outils.Alerte("La catégorie possède des produits !");
                }
            }
            return false;
        }
    }
}
