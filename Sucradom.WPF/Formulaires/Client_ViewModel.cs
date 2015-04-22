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
        private client  _Client;
        public client Client
        {
            get { return _Client; }
            set 
            {
                _Client = value; 
                NotifyPropertyChanged();
            }
        }

        private Boolean _IsNouveauClient;

        public Client_ViewModel()
        {
            Client = new client();
            _IsNouveauClient = true;
        }
        public Client_ViewModel(client SelectedClient)
        {
            Client = SelectedClient;
            _IsNouveauClient = true;
        }

        public Boolean Enregistrer()
        {
            if (Client.Nom != null && Client.Nom != "")
            {
                if (Client.Prenom != null && Client.Prenom != "")
                {
                    if (Outils.IsEmail(Client.Email))
                    {
                        if (Outils.IsNumeroTelephone(Client.Telephone))
                        {
                            if (_IsNouveauClient)
                            {
                                if (ViewModel.clients.FirstOrDefault(C => C.Nom.Equals(Client.Nom) && C.Prenom.Equals(Client.Prenom)) == null)
                                {
                                    Context.clients.Add(Client);
                                    ViewModel.clients.Add(Client);
                                    return true;
                                }
                                else
                                {
                                    Outils.Alerte("Un client avec ce nom existe déjà !");
                                }
                            }
                            else
                            {
                                if (ViewModel.clients.Count(C => C.Nom.Equals(Client.Nom) && C.Prenom.Equals(Client.Prenom)) <= 1)
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
