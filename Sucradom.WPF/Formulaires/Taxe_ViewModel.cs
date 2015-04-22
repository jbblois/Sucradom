using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Formulaires
{
    public class Taxe_ViewModel : ViewModel
    {
        private taxe  _Taxe;
        public taxe Taxe
        {
            get { return _Taxe; }
            set 
            {
                _Taxe = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouvelleTaxe;

        public Taxe_ViewModel()
        {
            Taxe = new taxe();
            _IsNouvelleTaxe = true;
        }
        public Taxe_ViewModel(taxe SelectedTaxe)
        {
            Taxe = SelectedTaxe;
            _IsNouvelleTaxe = true;
        }

        public Boolean Enregistrer()
        {
            if (Taxe.Nom != null && Taxe.Nom != "")
            {
                if (_IsNouvelleTaxe)
                {
                    if (ViewModel.taxes.FirstOrDefault(T => T.Nom.Equals(Taxe.Nom)) == null)
                    {
                        Context.taxes.Add(Taxe);
                        ViewModel.taxes.Add(Taxe);
                        return true;
                    }
                    else
                    {
                        Outils.Alerte("Une taxe avec ce nom existe déjà !");
                    }
                }
                else
                {
                    if (ViewModel.taxes.Count(T => T.Nom.Equals(Taxe.Nom)) <= 1)
                    {
                        return true;
                    }
                    else
                    {
                        Outils.Alerte("Une taxe avec ce nom existe déjà !");
                    }
                }
            }
            else
            {
                Outils.Alerte("Veuillez saisir un nom ! ");
            }
            return false;
        }

    }
}
