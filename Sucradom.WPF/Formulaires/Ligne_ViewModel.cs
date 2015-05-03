using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Formulaires
{
    public class Ligne_ViewModel : ViewModel
    {
        private lignecommande _LigneOfVM;
        public lignecommande LigneOfVM
        {
            get { return _LigneOfVM; }
            set
            {
                _LigneOfVM = value;
                NotifyPropertyChanged();
            }
        }

        public List<produit> ProduitsDisponibles
        {
            get { return produits.Where(p => p.IsDisponible.Equals("OUI")).ToList(); }
        }

        public produit SelectedProduit
        {
            get { return LigneOfVM.produit; }
            set
            {
                LigneOfVM.produit = value;
                ValeurTaxe = value.taxe.Valeur;
                PrixUnitaire = value.Prix;
                NotifyPropertyChanged();
            }
        }
        public int Quantite
        {
            get { return LigneOfVM.Quantite; }
            set 
            { 
                LigneOfVM.Quantite = value;
                PrixTTC = LigneOfVM.PrixTTC;
                NotifyPropertyChanged();
            }
        }
        public float ValeurTaxe
        {
            get { return LigneOfVM.ValeurTaxe; }
            set 
            { 
                LigneOfVM.ValeurTaxe = value;
                NotifyPropertyChanged();
            }
        }
        public float PrixUnitaire
        {
            get { return LigneOfVM.PrixUnitaire; }
            set
            {
                LigneOfVM.PrixUnitaire = value;
                PrixTTC = LigneOfVM.PrixTTC;
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

        private Boolean _IsNouvelleLigne;

        public Ligne_ViewModel(tetecommande SelectedCommande)
        {
            LigneOfVM = new lignecommande();
            LigneOfVM.tetecommande = SelectedCommande;
            _IsNouvelleLigne = true;
        }
        public Ligne_ViewModel(lignecommande SelectedLigne)
        {
            LigneOfVM = SelectedLigne;
            _IsNouvelleLigne = false;
        }

        public Boolean Enregistrer()
        {
            if (LigneOfVM.produit != null)
            {
                if (LigneOfVM.Quantite > 0)
                {
                    if (LigneOfVM.PrixUnitaire >= 0)
                    {
                        if (LigneOfVM.Quantite <= LigneOfVM.produit.Quantite)
                        {
                            if (_IsNouvelleLigne)
                            {
                                if (ViewModel.lignecommandes.FirstOrDefault(lc => lc.tetecommande.ID == LigneOfVM.tetecommande.ID && lc.produit.ID == LigneOfVM.produit.ID) == null)
                                {
                                    Context.lignecommandes.Add(LigneOfVM);
                                    ViewModel.lignecommandes.Add(LigneOfVM);
                                    return true;
                                }
                                else
                                {
                                    Outils.Alerte("Ce produit à déjà été commandé !");
                                }
                            }
                            else
                            {
                                if (ViewModel.lignecommandes.Count(lc => lc.tetecommande.ID == LigneOfVM.tetecommande.ID && lc.produit.ID == LigneOfVM.produit.ID) <= 1)
                                {
                                    return true;
                                }
                                else
                                {
                                    Outils.Alerte("Ce produit à déjà été commandé !");
                                }
                            }
                        }
                        else
                        {
                            Outils.Alerte("Pas assez de stock disponible ! ("+LigneOfVM.produit.Quantite+")");
                        }
                    }
                    else
                    {
                        Outils.Alerte("Veuillez saisir un prix positif ou nul");
                    }
                }
                else
                {
                    Outils.Alerte("Veuillez saisir une quantité supérieur à zéro !");
                }
            }
            else
            {
                Outils.Alerte("Veuillez choisir un produit !");
            }
            return false;
        }
    }
}
