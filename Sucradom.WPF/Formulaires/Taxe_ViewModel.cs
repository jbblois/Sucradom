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
        private taxe  _TaxeOfVM;
        public taxe TaxeOfVM
        {
            get { return _TaxeOfVM; }
            set 
            {
                _TaxeOfVM = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouvelleTaxe;

        public Taxe_ViewModel()
        {
            TaxeOfVM = new taxe();
            _IsNouvelleTaxe = true;
        }
        public Taxe_ViewModel(taxe SelectedTaxe)
        {
            TaxeOfVM = SelectedTaxe;
            _IsNouvelleTaxe = true;
        }

        public Boolean Enregistrer()
        {
            if (TaxeOfVM.Nom != null && TaxeOfVM.Nom != "")
            {
                if (_IsNouvelleTaxe)
                {
                    if (ViewModel.taxes.FirstOrDefault(T => T.Nom.Equals(TaxeOfVM.Nom)) == null)
                    {
                        Context.taxes.Add(TaxeOfVM);
                        ViewModel.taxes.Add(TaxeOfVM);
                        return true;
                    }
                    else
                    {
                        Outils.Alerte("Une taxe avec ce nom existe déjà !");
                    }
                }
                else
                {
                    if (ViewModel.taxes.Count(T => T.Nom.Equals(TaxeOfVM.Nom)) <= 1)
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
