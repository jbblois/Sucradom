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
                new Formulaires.Produit(SelectedProduit).ShowDialog();
                return true;
            }
            else
            {
                Outils.Alerte("Veillez selectionner un produit");
            }
            return false;
        }

        public Boolean SupprimerProduit()
        {
            if (SelectedProduit != null)
            {
                if (SelectedProduit.lignecommandes.Count == 0)
                {
                    if (SelectedProduit.Quantite == 0)
                    {
                        if (Outils.Choix("Voulez-vous supprimer le produit selectionné", "Suppression"))
                        {
                            foreach (provision provision in SelectedProduit.provisions)
                            {
                                Context.provisions.Remove(provision);
                                ViewModel.provisions.Remove(provision);
                            }
                            Context.produits.Remove(SelectedProduit);
                            ViewModel.produits.Remove(SelectedProduit);
                            return true;
                        }
                    }
                    else
                    {
                        Outils.Alerte("Vous ne pouvez pas supprimer un produit dont le stock actuel est différent de 0");
                    }
                }
                else
                {
                    Outils.Alerte("Vous ne pouvez pas supprimer un produit lié à une ou plusieurs commandes");
                }
            }
            else
            {
                Outils.Alerte("Veillez selectionner un produit");
            }
            return false;
        }

		public Boolean GestionProvisions()
		{
			if(SelectedProduit != null)
			{
				new Fenetres.Provisions(SelectedProduit).ShowDialog();
			}
			else
			{
				Outils.Alerte("Veillez selectionner un produit à approvisionner");
			}
			return false;
		}
    }
}

