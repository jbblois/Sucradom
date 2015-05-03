using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Onglets
{
    public class Commandes_ViewModel : ViewModel
    {
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


        public Commandes_ViewModel()
        {

        }

        public Boolean AjouterCommande()
        {
                new Formulaires.Commande().ShowDialog();
                return true;
        }

        public Boolean ModifierCommande()
        {
            if (SelectedCommande != null)
            {
                new Formulaires.Commande(SelectedCommande).ShowDialog();
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
