using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Formulaires
{
    public class Client_ViewModel : ViewModel
    {
        private client  _ClientOfVM;
        public client ClientOfVM
        {
            get { return _ClientOfVM; }
            set 
            {
                _ClientOfVM = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouveauClient;

        public Client_ViewModel()
        {
            ClientOfVM = new client();
            _IsNouveauClient = true;
        }
        public Client_ViewModel(client SelectedClient)
        {
            ClientOfVM = SelectedClient;
			_IsNouveauClient = false;
        }

        public Boolean Enregistrer()
        {
            if (ClientOfVM.Nom != null && ClientOfVM.Nom != "")
            {
                if (ClientOfVM.Prenom != null && ClientOfVM.Prenom != "")
                {
                    if (Outils.IsEmail(ClientOfVM.Email))
                    {
                        if (Outils.IsNumeroTelephone(ClientOfVM.Telephone))
                        {
                            if (_IsNouveauClient)
                            {
                                if (ViewModel.clients.FirstOrDefault(C => C.Nom.Equals(ClientOfVM.Nom) && C.Prenom.Equals(ClientOfVM.Prenom)) == null)
                                {
                                    ClientOfVM.GenererMDP();
                                    Context.clients.Add(ClientOfVM);
                                    ViewModel.clients.Add(ClientOfVM);
                                    Outils.Alerte("Le mot de passe pour " + ClientOfVM.NomComplet + " est " + ClientOfVM.MotDePasse);
                                    return true;
                                }
                                else
                                {
                                    Outils.Alerte("Un client avec ce nom existe déjà !");
                                }
                            }
                            else
                            {
                                if (ViewModel.clients.Count(C => C.Nom.Equals(ClientOfVM.Nom) && C.Prenom.Equals(ClientOfVM.Prenom)) <= 1)
                                {
                                    return true;
                                }
                                else
                                {
                                    Outils.Alerte("Un client avec ce nom existe déjà !");
                                }
                            }
                        }
                        else
                        {
                            Outils.Alerte("Veuillez saisir un numéro de téléphone valide !");
                        }    
                    }
                    else
                    {
                        Outils.Alerte("Veuillez saisir un email valide !");
                    }
                }
                else
                {
                    Outils.Alerte("Veuillez saisir un prenom ! ");
                }
            }
            else
            {
                Outils.Alerte("Veuillez saisir un nom !");
            }
            return false;
        }

    }
}
