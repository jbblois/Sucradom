using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Formulaires
{
	public class Adresse_ViewModel : ViewModel
	{
		private adresse _AdresseOfVM;
        public adresse AdresseOfVM
        {
            get { return _AdresseOfVM; }
            set 
            {
                _AdresseOfVM = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouvelleAdresse;

        public Adresse_ViewModel(client SelectedClient)
        {
            AdresseOfVM = new adresse();
			AdresseOfVM.client = SelectedClient;
            _IsNouvelleAdresse = true;
        }
        public Adresse_ViewModel(adresse SelectedAdresse)
        {
            AdresseOfVM = SelectedAdresse;
            _IsNouvelleAdresse = false;
        }

        public Boolean Enregistrer()
        {
			if(true)
			{
				if(_IsNouvelleAdresse)
				{
					Context.adresses.Add(AdresseOfVM);
					ViewModel.adresses.Add(AdresseOfVM);
					return true;
				}
				else
				{
					return true;
				}
			}
			else
			{
				Outils.Alerte("");
			}
            return false;
        }
	}
}
