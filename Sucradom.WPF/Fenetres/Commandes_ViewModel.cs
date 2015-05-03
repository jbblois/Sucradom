using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;
using System.Collections.ObjectModel;

namespace Sucradom.WPF.Fenetres
{
    public class Commandes_ViewModel : ViewModel
    {

        private client _ClientOfVM;

        private ObservableCollection<tetecommande> _CommandesOfClient;
        public ObservableCollection<tetecommande> CommandesOfClient
        {
            get { return _CommandesOfClient; }
            set
            {
                _CommandesOfClient = value;
                NotifyPropertyChanged();
            }
        }
        public void RefreshCommandes()
        {
            CommandesOfClient = new ObservableCollection<tetecommande>(ViewModel.tetecommandes.Where(tc => tc.FID_Client == _ClientOfVM.ID));
        }

        private tetecommande _SelectedCommande;
        public tetecommande SelectedCommande
        {
            get { return _SelectedCommande; }
            set
            {
                _SelectedCommande = value;
                NotifyPropertyChanged();
            }
        }

        public Commandes_ViewModel(client ClientOfVM)
        {
            this._ClientOfVM = ClientOfVM;
            RefreshCommandes();
        }

        public Boolean AjouterCommande()
        {
            new Formulaires.Commande(_ClientOfVM).ShowDialog();
            RefreshCommandes();
            return true;
        }

        public Boolean ModifierCommande()
        {
            if (SelectedCommande != null)
            {
                new Formulaires.Commande(SelectedCommande).ShowDialog();
                RefreshCommandes();
                return true;
            }
            else
            {
                Outils.Alerte("Veuillez sélectionner une commande !");
                return false;
            }
        }

        /// <summary>
        /// PENSER A FAIRE LES ETAPES DE SUPRESSION
        /// </summary>
        /// <returns></returns>
        public Boolean SupprimerCommande()
        {
            if (SelectedCommande != null)
            {
                if (Outils.Choix("Voulez-vous supprimer la commande selectionnée","Suppression"))
                {
                    foreach (lignecommande ligne in SelectedCommande.lignecommandes)
                    {
                        Context.lignecommandes.Remove(ligne);
                        ViewModel.lignecommandes.Remove(ligne);
                    }
                    Context.tetecommandes.Remove(SelectedCommande);
                    ViewModel.tetecommandes.Remove(SelectedCommande);
                    RefreshCommandes();
                    return true;
                }
            }
            else
            {
                Outils.Alerte("Veuillez sélectionner une commande !");
            }
            return false;
        }
    }
}
