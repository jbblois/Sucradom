using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Formulaires
{
    public class Categorie_ViewModel : ViewModel
    {
        private categorie  _CategorieOfVM;
        public categorie CategorieOfVM
        {
            get { return _CategorieOfVM; }
            set 
            {
                _CategorieOfVM = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouvelleCategorie;

        public Categorie_ViewModel()
        {
            CategorieOfVM = new categorie();
            _IsNouvelleCategorie = true;
        }
        public Categorie_ViewModel(categorie SelectedCategorie)
        {
            CategorieOfVM = SelectedCategorie;
            _IsNouvelleCategorie = true;
        }

        public Boolean Enregistrer()
        {
            if (CategorieOfVM.Libelle != null && CategorieOfVM.Libelle != "")
            {
                if (_IsNouvelleCategorie)
                {
                    if (ViewModel.categories.FirstOrDefault(T => T.Libelle.Equals(CategorieOfVM.Libelle)) == null)
                    {
                        image image = ViewModel.images.FirstOrDefault(I => I.Path.Equals(CategorieOfVM.Libelle));
                        if (image == null)
                        {
                            image = new image();
                            image.Path = CategorieOfVM.Libelle;
                            image.Alt = CategorieOfVM.Libelle;
                        }
                        CategorieOfVM.image = image;
                        Context.categories.Add(CategorieOfVM);
                        ViewModel.categories.Add(CategorieOfVM);
                        return true;
                    }
                    else
                    {
                        Outils.Alerte("Une catégorie avec ce libellé existe déjà !");
                    }
                }
                else
                {
                    if (ViewModel.categories.Count(T => T.Libelle.Equals(CategorieOfVM.Libelle)) <= 1)
                    {
                        image image = Context.images.FirstOrDefault(I => I.Path.Equals(CategorieOfVM.Libelle));
                        if (image == null)
                        {
                            image = new image();
                            image.Path = CategorieOfVM.Libelle;
                            image.Alt = CategorieOfVM.Libelle;
                        }
                        CategorieOfVM.image = image;
                        return true;
                    }
                    else
                    {
                        Outils.Alerte("Une catégorie avec ce libellé existe déjà !");
                    }
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
