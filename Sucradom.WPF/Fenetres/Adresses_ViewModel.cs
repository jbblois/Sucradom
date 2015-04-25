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
                Outils.Alerte("Veuillez selectionner une adresse !");
			}
			return false;
		}

		public Boolean SupprimerAdresse()
		{
			if(SelectedAdresse != null)
			{
					ViewModel.adresses.Remove(SelectedAdresse);
                    Context.adresses.Remove(SelectedAdresse);
			}
            else
            {
                Outils.Alerte("Veuillez selectionner une adresse !");
            }
			return false;
		}
	}
}
