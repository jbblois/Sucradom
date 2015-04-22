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
        private categorie  _Categorie;
        public categorie Categorie
        {
            get { return _Categorie; }
            set 
            {
                _Categorie = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouvelleCategorie;

        public Categorie_ViewModel()
        {
            Categorie = new categorie();
            _IsNouvelleCategorie = true;
        }
        public Categorie_ViewModel(categorie SelectedCategorie)
        {
            Categorie = SelectedCategorie;
            _IsNouvelleCategorie = true;
        }

        public Boolean Enregistrer()
        {
            if (Categorie.Libelle != null && Categorie.Libelle != "")
            {
                if (_IsNouvelleCategorie)
                {
                    if (ViewModel.categories.FirstOrDefault(T => T.Libelle.Equals(Categorie.Libelle)) == null)
                    {
                        image image = ViewModel.images.FirstOrDefault(I => I.Path.Equals(Categorie.Libelle));
                        if (image == null)
                        {
                            image = new image();
                            image.Path = Categorie.Libelle;
                            image.Alt = Categorie.Libelle;
                        }
                        Categorie.image = image;
                        Context.categories.Add(Categorie);
                        ViewModel.categories.Add(Categorie);
                        return true;
                    }
                    else
                    {
                        Outils.Alerte("Une catégorie avec ce libellé existe déjà !");
                    }
                }
                else
                {
                    if (ViewModel.categories.Count(T => T.Libelle.Equals(Categorie.Libelle)) <= 1)
                    {
                        image image = Context.images.FirstOrDefault(I => I.Path.Equals(Categorie.Libelle));
                        if (image == null)
                        {
                            image = new image();
                            image.Path = Categorie.Libelle;
                            image.Alt = Categorie.Libelle;
                        }
                        Categorie.image = image;
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
