using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Formulaires
{
    public class Commande_ViewModel : ViewModel
    {
        private tetecommande _TeteCommande;

        public tetecommande TeteCommande
        {
            get { return _TeteCommande; }
            set { _TeteCommande = value; }
        }

        public etatcommande SelectedEtat
        {
            get { return TeteCommande.etatcommande; }
            set
            {
                TeteCommande.etatcommande = value;
                NotifyPropertyChanged();
            }
        }

        public client SelectedClient
        {
            get { return TeteCommande.client; }
            set
            {
                TeteCommande.client = value;
                NotifyPropertyChanged();
            }
        }

        public adresse SelectedAdresse
        {
            get { return TeteCommande.adresse; }
            set
            {
                TeteCommande.adresse = value;
                NotifyPropertyChanged();
            }
        }


        private float _PrixHT;
        public float PrixHT
        {
            get { return _PrixHT; }
            set 
            { 
                _PrixHT = value;
                NotifyPropertyChanged();
            }
        }

        private float _PrixTTC;
        public float PrixTTC
        {
            get { return _PrixTTC; }
            set
            {
                _PrixTTC = value;
                NotifyPropertyChanged();
            }
        }

        private void RefreshPrix()
        {
            PrixHT = TeteCommande.PrixHT;
            PrixTTC = TeteCommande.PrixTTC;
        }

        private ObservableCollection<lignecommande> _LignesOfCommande;
        public ObservableCollection<lignecommande> LignesOfCommande
        {
            get { return _LignesOfCommande; }
            set
            {
                _LignesOfCommande = value;
                NotifyPropertyChanged();
            }
        }
        
        public void RefreshLignes()
        {
            LignesOfCommande = new ObservableCollection<lignecommande>(ViewModel.lignecommandes.Where(lc => lc.FID_Commande == TeteCommande.ID));
        }

        private lignecommande _SelectedLigne;
        public lignecommande SelectedLigne
        {
            get { return _SelectedLigne; }
            set
            {
                _SelectedLigne = value;
                NotifyPropertyChanged();
            }
        }


        private Boolean _IsNewCommande;

        public Commande_ViewModel()
        {
            TeteCommande = new tetecommande();
            TeteCommande.Date = DateTime.Now;
            TeteCommande.lignecommandes = new List<lignecommande>();
            _IsNewCommande = true;
            RefreshLignes();
            RefreshPrix();
        } 
        public Commande_ViewModel(client SelectedClient)
        {
            TeteCommande = new tetecommande();
            TeteCommande.Date = DateTime.Now;
            TeteCommande.client = SelectedClient;
            TeteCommande.lignecommandes = new List<lignecommande>();
            _IsNewCommande = true;
            RefreshLignes();
            RefreshPrix();
        }
        public Commande_ViewModel(tetecommande SelectedCommande)
        {
            TeteCommande = SelectedCommande;
            _IsNewCommande = false;
            RefreshLignes();
            RefreshPrix();
        }

        public Boolean Enregistrer()
        {
            if (TeteCommande.client != null)
            {
                if (TeteCommande.adresse != null)
                {
                    if (TeteCommande.Date != null)
                    {
                        if (TeteCommande.etatcommande != null)
                        {
                            if (_IsNewCommande)
                            {
                                ViewModel.tetecommandes.Add(TeteCommande);
                                Context.tetecommandes.Add(TeteCommande);
                            }
                            return true;
                        }
                        else
                        {
                            Outils.Alerte("Veuillez choisir un état de commande");
                        }
                    }
                    else
                    {
                        Outils.Alerte("Veuillez selectionner une date");
                    }
                }
                else
                {
                    Outils.Alerte("Veuillez selectionner une adresse de livraison");
                }
            }
            else
            {
                Outils.Alerte("Veuillez selectionner un client");
            }
            return false;
        }

        public Boolean AjouterLigne()
        {
            new Formulaires.Ligne(TeteCommande).ShowDialog();
            RefreshLignes();
            RefreshPrix();
            return true;
        }
        public Boolean ModifierLigne()
        {
            if (SelectedLigne != null)
            {
                new Formulaires.Ligne(SelectedLigne).ShowDialog();
                RefreshLignes();
                RefreshPrix();
                return true;
            }
            else
            {
                Outils.Alerte("Veuillez selectionner une ligne");
            }
            return false;
        }
        public Boolean SupprimerLigne()
        {
            if (SelectedLigne != null)
            {
                if (Outils.Choix("Voulez vous supprimer cette ligne de commande?","Supprimer"))
                {
                    Context.lignecommandes.Remove(SelectedLigne);
                    ViewModel.lignecommandes.Remove(SelectedLigne);
                    RefreshLignes();
                    RefreshPrix();
                    return true;    
                }
            }
            else
            {
                Outils.Alerte("Veuillez selectionner une ligne");
            }
            return false;
        }
    }
}
