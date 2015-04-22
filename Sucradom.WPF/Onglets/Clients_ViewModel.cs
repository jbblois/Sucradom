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
                //********
                // PENSER A AJOUTER LE CONSTRUCTEUR DE MODIFICATION
                //new Formulaires.Client(SelectedClient).ShowDialog();
                return true;
            }
            return false;
        }

        /// <summary>
        /// PENSER A FAIRE LES ETAPES DE SUPRESSION
        /// </summary>
        /// <returns></returns>
        public Boolean SupprimerClient()
        {
            if (SelectedClient != null)
            {
                
                return true;
            }
            return false;
        }
    }
}
