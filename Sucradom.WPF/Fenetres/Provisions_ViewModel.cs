using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Fenetres
{
    public class Provisions_ViewModel : ViewModel
    {
        public produit ProduitOfVM { get; set; }

        private provision _SelectedProvision;
        public provision SelectedProvision
        {
            get { return _SelectedProvision; }
            set
            {
                _SelectedProvision = value;
                NotifyPropertyChanged();
            }
        }

        private ObservableCollection<provision> _ProvisionsOfProduit ;
        public ObservableCollection<provision> ProvisionsOfProduit
        {
            get { return _ProvisionsOfProduit; }
            set 
            { 
                _ProvisionsOfProduit = value; 
                NotifyPropertyChanged();
            }
        }

        public void RefreshProvisions()
        {
            ProvisionsOfProduit = new ObservableCollection<provision>(ViewModel.provisions.Where(p => p.FID_Produit == ProduitOfVM.ID));
        }

        public Provisions_ViewModel(produit SelectedProduit)
        {
            this.ProduitOfVM = SelectedProduit;
            RefreshProvisions();
        }

        public void AjouterProvision()
        {
            new Formulaires.Provision(ProduitOfVM).ShowDialog();
            RefreshProvisions();
        }

        public Boolean ModifierProvision()
        {
            if (SelectedProvision != null)
            {
                new Formulaires.Provision(SelectedProvision).ShowDialog();
                RefreshProvisions();
                return true;
            }
            else
            {
                Outils.Alerte("Veuillez selectionner un approvisionnement!");
            }
            return false;
        }

        public Boolean SupprimerProvision()
        {
            if (SelectedProvision != null)
            {
                Context.provisions.Remove(SelectedProvision);
                ViewModel.provisions.Remove(SelectedProvision);
                ProvisionsOfProduit.Remove(SelectedProvision);
                return true;
            }
            else
            {
                Outils.Alerte("Veuillez selectionner un approvisionnement !");
            }
            return false;
        }
    }
}
