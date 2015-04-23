using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Formulaires
{
	public class Provision_ViewModel : ViewModel
	{
		private provision _ProvisionOfVM;
        public provision ProvisionOfVM
        {
            get { return _ProvisionOfVM; }
            set 
            {
                _ProvisionOfVM = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouvelleProvision;

        public Provision_ViewModel(produit Produit)
        {
            ProvisionOfVM = new provision();
			ProvisionOfVM.produit = Produit;
            _IsNouvelleProvision = true;
        }
        public Provision_ViewModel(provision SelectedProvision)
        {
            ProvisionOfVM = SelectedProvision;
            _IsNouvelleProvision = false;
        }

        public Boolean Enregistrer()
        {
			if(ProvisionOfVM.Quantite > 0)
			{
				if(ProvisionOfVM.Date.CompareTo(DateTime.Now) <= 0)
				{
					if(_IsNouvelleProvision)
					{
						Context.provisions.Add(ProvisionOfVM);
						ViewModel.provisions.Add(ProvisionOfVM);
						return true;
					}
					else
					{
						return true;
					}
				}
				else
				{
					Outils.Alerte("Veuillez saisir une date passée ou actuelle !");
				}
			}
			else
			{
				Outils.Alerte("Veuillez saisir un quantité supérieur à 0 !");
			}
            return false;
        }
	}
}
