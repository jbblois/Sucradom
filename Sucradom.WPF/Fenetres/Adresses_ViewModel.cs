using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Fenetres
{
	public class Adresses_ViewModel : ViewModel
	{
		public client ClientOfVM { get; set; }

		private adresse _SelectedAdresse;
		public adresse SelectedAdresse
		{
			get { return _SelectedAdresse; }
			set
			{
				_SelectedAdresse = value;
				NotifyPropertyChanged();
			}
		}

		public Adresses_ViewModel(client SelectedClient)
		{
			this.ClientOfVM = SelectedClient;
		}

		public void AjouterAdresse()
		{
			new Formulaires.Adresse(ClientOfVM).ShowDialog();
		}

		public Boolean ModifierAdresse()
		{
			if(SelectedAdresse != null)
			{
				new Formulaires.Adresse(SelectedAdresse).ShowDialog();
				return true;
			}
			else
			{
				Outils.Alerte("Veuillez selectionner une catégorie !");
			}
			return false;
		}

		public Boolean SupprimerAdresse()
		{
			if(SelectedAdresse != null)
			{
				if(SelectedAdresse.NbProduits == 0)
				{
					ViewModel.taxes.Remove(SelectedAdresse);
					Context.taxes.Remove(SelectedAdresse);
					return true;
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
