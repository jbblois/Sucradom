using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Onglets
{
    public class Clients_ViewModel : ViewModel
    {
        private client _SelectedClient;
        public client SelectedClient
        {
            get { return _SelectedClient; }
            set 
            {
                _SelectedClient = value;
                NotifyPropertyChanged();
            }
        }


        public Clients_ViewModel()
        {

        }

        public void AjouterClient()
        {   
            new Formulaires.Client().ShowDialog();
        }

        public Boolean ModifierClient()
        {
            if (SelectedClient != null)
            {
				new Formulaires.Client(SelectedClient).ShowDialog();
                return true;
            }
			else
			{
				Outils.Alerte("Veuillez selectionner un client !");
			}
            return false;
        }

        public Boolean SupprimerClient()
        {
            if (SelectedClient != null)
            {
				ViewModel.clients.Remove(SelectedClient);
				Context.clients.Remove(SelectedClient);
                return true;
            }
			else
			{
				Outils.Alerte("Veuillez selectionner un client !");
			}
            return false;
        }

		public Boolean CarnetAdresses()
		{
			if(SelectedClient != null)
			{
				//new Fenetres.Adresses(SelectedClient).ShowDialog();
				return true;
			}
			else
			{
				Outils.Alerte("Veuillez selectionner un client dont le carnet d'adresses doit être modifié !");
			}
			return false;
		}
	}
}
