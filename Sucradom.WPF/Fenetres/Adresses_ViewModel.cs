using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;
using System.Collections.ObjectModel;

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

        public void RefreshAdresses()
        {
            AdressesOfClient = new ObservableCollection<adresse>(ViewModel.adresses.Where(p => p.FID_Client == ClientOfVM.ID));
        }

        public ObservableCollection<adresse> AdressesOfClient { get; set; }

        public Adresses_ViewModel(client SelectedClient)
        {
            this.ClientOfVM = SelectedClient;
            RefreshAdresses();
        }

        public void AjouterAdresse()
        {
            new Formulaires.Adresse(ClientOfVM).ShowDialog();
            RefreshAdresses();
        }

        public Boolean ModifierAdresse()
        {
            if (SelectedAdresse != null)
            {
                new Formulaires.Adresse(SelectedAdresse).ShowDialog();
                RefreshAdresses();
                return true;
            }
            else
            {
                Outils.Alerte("Veuillez selectionner une adresse!");
            }
            return false;
        }

        public Boolean SupprimerAdresse()
        {
            if (SelectedAdresse != null)
            {
                Context.adresses.Remove(SelectedAdresse);
                ViewModel.adresses.Remove(SelectedAdresse);
                RefreshAdresses();
                return true;
            }
            else
            {
                Outils.Alerte("Veuillez selectionner une adresse !");
            }
            return false;
        }
    }
}
