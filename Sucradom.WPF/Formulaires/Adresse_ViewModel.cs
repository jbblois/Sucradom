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
			if(AdresseOfVM.Pays != "")
			{
                if (AdresseOfVM.Ville != "")
                {
                    if (AdresseOfVM.Cp != "")
                    {
                        if (AdresseOfVM.Rue != "")
                        {
                            if (AdresseOfVM.Numero != "")
                            {
                                if (_IsNouvelleAdresse)
                                {
                                    Context.adresses.Add(AdresseOfVM);
                                    ViewModel.adresses.Add(AdresseOfVM);
                                }
                                    return true;
                            }
                            else
                            {
                                Outils.Alerte("Veuillez saisir un numero !");
                            }
                        }
                        else
                        {
                            Outils.Alerte("Veuillez saisir une rue !");
                        }
                    }
                    else
                    {
                        Outils.Alerte("Veuillez saisir un code postal !");
                    }
                }
                else
                {
                    Outils.Alerte("Veuillez saisir une ville !");
                }
			}
			else
			{
				Outils.Alerte("Veuillez saisir un pays !");
			}
            return false;
        }
	}
}
