using System;
using System.Collections.Generic;
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

		public Provisions_ViewModel(produit SelectedProduit)
		{
			this.ProduitOfVM = SelectedProduit;
		}

		public void AjouterProvision()
		{
			new Formulaires.Provision(ProduitOfVM).ShowDialog();
		}

		public Boolean ModifierProvision()
		{
			if(SelectedProvision != null)
			{
				new Formulaires.Provision(SelectedProvision).ShowDialog();
				return true;
			}
			else
			{
				Outils.Alerte("Veuillez selectionner un approvisionnement à modifier!");
			}
			return false;
		}

		public Boolean SupprimerProvision()
		{
			if(SelectedProvision != null)
			{
				ViewModel.provisions.Remove(SelectedProvision);
				Context.provisions.Remove(SelectedProvision);
				return true;
			}
			else
			{
				Outils.Alerte("Veuillez selectionner un approvisionnement à supprimer !");
			}
			return false;
		}
	}
}
