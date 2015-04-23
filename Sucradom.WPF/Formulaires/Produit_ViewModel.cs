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
        private produit _ProduitOfVM;
        public produit ProduitOfVM
        {
            get { return _ProduitOfVM; }
            set 
            {
                _ProduitOfVM = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouveauProduit;

        public Produit_ViewModel()
        {
            ProduitOfVM = new produit();
            _IsNouveauProduit = true;
        }
        public Produit_ViewModel(produit SelectedProduit)
        {
            ProduitOfVM = SelectedProduit;
            _IsNouveauProduit = true;
        }

        public Boolean Enregistrer()
        {
            if (ProduitOfVM.Libelle != null && ProduitOfVM.Libelle != "")
            {
                if (ProduitOfVM.categorie != null)
                {

                    if (_IsNouveauProduit)
                    {
                        if (ViewModel.produits.FirstOrDefault(P => P.Libelle.Equals(ProduitOfVM.Libelle)) == null)
                        {
                            image image = ViewModel.images.FirstOrDefault(I => I.Path.Equals(ProduitOfVM.Libelle));
                            if (image == null)
                            {
                                image = new image();
                                image.Path = ProduitOfVM.Libelle;
                                image.Alt = ProduitOfVM.Libelle;
                            }
                            ProduitOfVM.image = image;
                            Context.produits.Add(ProduitOfVM);
                            ViewModel.produits.Add(ProduitOfVM);
                            return true;
                        }
                        else
                        {
                            Outils.Alerte("Un produit avec ce libellé existe déjà !");
                        }
                    }
                    else
                    {
                        if (ViewModel.produits.Count(P => P.Libelle.Equals(ProduitOfVM.Libelle)) <= 1)
                        {
                            image image = Context.images.FirstOrDefault(I => I.Path.Equals(ProduitOfVM.Libelle));
                            if (image == null)
                            {
                                image = new image();
                                image.Path = ProduitOfVM.Libelle;
                                image.Alt = ProduitOfVM.Libelle;
                            }
                            ProduitOfVM.image = image;
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
