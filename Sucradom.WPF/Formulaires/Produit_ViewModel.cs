using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Formulaires
{
    public class Produit_ViewModel : ViewModel
    {
        private produit  _Produit;
        public produit Produit
        {
            get { return _Produit; }
            set 
            {
                _Produit = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouveauProduit;

        public Produit_ViewModel()
        {
            Produit = new produit();
            _IsNouveauProduit = true;
        }
        public Produit_ViewModel(produit SelectedProduit)
        {
            Produit = SelectedProduit;
            _IsNouveauProduit = true;
        }

        public Boolean Enregistrer()
        {
            if (Produit.Libelle != null && Produit.Libelle != "")
            {
                if (Produit.categorie != null)
                {

                    if (_IsNouveauProduit)
                    {
                        if (ViewModel.produits.FirstOrDefault(P => P.Libelle.Equals(Produit.Libelle)) == null)
                        {
                            image image = ViewModel.images.FirstOrDefault(I => I.Path.Equals(Produit.Libelle));
                            if (image == null)
                            {
                                image = new image();
                                image.Path = Produit.Libelle;
                                image.Alt = Produit.Libelle;
                            }
                            Produit.image = image;
                            Context.produits.Add(Produit);
                            ViewModel.produits.Add(Produit);
                            return true;
                        }
                        else
                        {
                            Outils.Alerte("Un produit avec ce libellé existe déjà !");
                        }
                    }
                    else
                    {
                        if (ViewModel.produits.Count(P => P.Libelle.Equals(Produit.Libelle)) <= 1)
                        {
                            image image = Context.images.FirstOrDefault(I => I.Path.Equals(Produit.Libelle));
                            if (image == null)
                            {
                                image = new image();
                                image.Path = Produit.Libelle;
                                image.Alt = Produit.Libelle;
                            }
                            Produit.image = image;
                            return true;
                        }
                        else
                        {
                            Outils.Alerte("Un produit avec ce libellé existe déjà !");
                        }
                    } 
                }
                else
                {
                    Outils.Alerte("Veuillez sélectionner une catégorie");
                }
            }
            else
            {
                Outils.Alerte("Veuillez saisir un libellé ! ");
            }
            return false;
        }

    }
}
