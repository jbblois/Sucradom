using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;
using Sucradom.LIB; 

namespace Sucradom.WPF
{
    public static class Outils
    {
        /// <summary>
        /// Methode affichant un message d'alerte à l'écran
        /// </summary>
        /// <param name="Message">Alerte à afficher</param>
        public static void Alerte(String Message)
        {
            MessageBox.Show(Message, "Attention", MessageBoxButton.OK, MessageBoxImage.Warning);
        }

        /// <summary>
        /// Methode affichant un message d'erreur à l'écran
        /// </summary>
        /// <param name="Message">Erreur à afficher</param>
        public static void Erreur(String Message)
        {
            MessageBox.Show(Message, "Erreur", MessageBoxButton.OK, MessageBoxImage.Error);
        }

        /// <summary>
        /// Methode affichant un choix et récuperant le résultat
        /// </summary>
        /// <param name="Message"></param>
        /// <param name="Titre"></param>
        /// <returns></returns>
        public static Boolean Choix(String Message, String Titre)
        {
            return (MessageBox.Show(Message, Titre, MessageBoxButton.YesNo, MessageBoxImage.Question) == MessageBoxResult.Yes);            
        }

        /// <summary>
        /// Permet de verifier que la chaine de caractère en paramêtre est un numéro de téléphone
        /// </summary>
        /// <param name="Telephone">String à tester</param>
        /// <returns></returns>
        public static Boolean IsNumeroTelephone(String Telephone)
        {
            Int64 telNumero = 0;
            if (Int64.TryParse(Telephone, out telNumero))
            {
                if (telNumero > 0 && telNumero < 9999999999)
                {
                    return true;
                }
            }
            return false;
        }

        /// <summary>
        /// Permet de verifier que la chaine de caractère en paramêtre est un numéro de téléphone
        /// </summary>
        /// <param name="Email">String à tester</param>
        /// <returns></returns>
        public static Boolean IsEmail(String Email)
        {
            return ( Email.Contains('@') && Email.Contains('.') ) ;
        }
    }
}
