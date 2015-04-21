using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB; 

namespace Sucradom.WPF
{
    // : INotifyPropertyChanged (interface  qui avertit dès qu'une propriété à été modifiée)
    public class ViewModel : INotifyPropertyChanged
    {
        // Raccourci pour récupérer la connexion à la DB
        public static sucradomEntities Context { get { return Base.Context; } }

        #region Methode
        
        #endregion

        /// <summary>
        /// Permet de remettre toutes les ObservableCollections à null.
        /// </summary>
        private static void RegenererListes()
        {
            _adresses = null;
            _categories = null;
            _clients = null;
            _etatcommandes = null;
            _images = null;
            _lignecommandes = null;
            _produits = null;
            _provisions = null;
            _taxes = null;
            _tetecommandes = null;
        }
       
        /// <summary>
        /// Permet de sauvegarder les modifications apportées à la DB
        /// </summary>
        /// <returns>retourne true si la sauvegarde c'est bien déroulée</returns>
        public static Boolean Sauvegarder()
        {
            RegenererListes();
            return Base.Sauvegarder();
        }
       
        /// <summary>
        /// Permet d'annuler les modifications apportées à la DB
        /// </summary>
        /// <returns>retourne true si l'annulation c'est bien déroulée</returns>
        public static Boolean Annuler()
        {
            RegenererListes();
            return Base.Annuler();
        }

        #region ObservableCollections
        //Collections statiques assurant la synchronisation entre les differents affichages
        //Instances uniques au programme (Singletons)

        private static ObservableCollection<adresse> _adresses;
        public static ObservableCollection<adresse> adresses
        {
            get 
            {
                if (_adresses == null)
                {
                    _adresses = new ObservableCollection<adresse>(Context.adresses);
                }
                return _adresses;
            }
        }

        private static ObservableCollection<categorie> _categories;
        public static ObservableCollection<categorie> categories
        {
            get
            {
                if (_categories == null)
                {
                    _categories = new ObservableCollection<categorie>(Context.categories);
                }
                return _categories;
            }
        }

        private static ObservableCollection<client> _clients;
        public static ObservableCollection<client> clients
        {
            get
            {
                if (_clients == null)
                {
                    _clients = new ObservableCollection<client>(Context.clients);
                }
                return _clients;
            }
        }

        private static ObservableCollection<etatcommande> _etatcommandes;
        public static ObservableCollection<etatcommande> etatcommandes
        {
            get
            {
                if (_etatcommandes == null)
                {
                    _etatcommandes = new ObservableCollection<etatcommande>(Context.etatcommandes);
                }
                return _etatcommandes;
            }
        }

        private static ObservableCollection<image> _images;
        public static ObservableCollection<image> images
        {
            get
            {
                if (_images == null)
                {
                    _images = new ObservableCollection<image>(Context.images);
                }
                return _images;
            }
        }

        private static ObservableCollection<lignecommande> _lignecommandes;
        public static ObservableCollection<lignecommande> lignecommandes
        {
            get
            {
                if (_lignecommandes == null)
                {
                    _lignecommandes = new ObservableCollection<lignecommande>(Context.lignecommandes);
                }
                return _lignecommandes;
            }
        }

        private static ObservableCollection<produit> _produits;
        public static ObservableCollection<produit> produits
        {
            get
            {
                if (_produits == null)
                {
                    _produits = new ObservableCollection<produit>(Context.produits);
                }
                return _produits;
            }
        }

        private static ObservableCollection<provision> _provisions;
        public static ObservableCollection<provision> provisions
        {
            get
            {
                if (_provisions == null)
                {
                    _provisions = new ObservableCollection<provision>(Context.provisions);
                }
                return _provisions;
            }
        }

        private static ObservableCollection<taxe> _taxes;
        public static ObservableCollection<taxe> taxes
        {
            get
            {
                if (_taxes == null)
                {
                    _taxes = new ObservableCollection<taxe>(Context.taxes);
                }
                return _taxes;
            }
        }

        private static ObservableCollection<tetecommande> _tetecommandes;
        public static ObservableCollection<tetecommande> tetecommandes
        {
            get
            {
                if (_tetecommandes == null)
                {
                    _tetecommandes = new ObservableCollection<tetecommande>(Context.tetecommandes);
                }
                return _tetecommandes;
            }
        }
        #endregion

        #region implémentation INotifyPropertyChanged
        public event PropertyChangedEventHandler PropertyChanged;
        protected void NotifyPropertyChanged([CallerMemberName] String propertyName = "")
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));  
            }
        }
        #endregion

        private static List<String> _OuiNon;
        /// <summary>
        /// Liste permettant de gérer les pseudos booleen
        /// </summary>
        public static List<String> OuiNon
        {
            get
            {

                if (_OuiNon == null)
                {
                    _OuiNon = new List<String> { "OUI", "NON" };
                }
                return _OuiNon;
            }
        }
    }
}
