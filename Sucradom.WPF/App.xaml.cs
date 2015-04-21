using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;
using Sucradom.LIB;
using Sucradom.WPF;
namespace Sucradom.WPF
{
    /// <summary>
    /// Logique d'interaction pour App.xaml
    /// </summary>
    public partial class App : Application
    {
        /// <summary>
        /// Constructeur de base de l'application
        /// </summary>
        public App()
        {
            if (Base.Debut()) // == true on vérifie si on a une connexion à la BDD
            {
                //On affiche la page d'accueil
                new Fenetres.Accueil().ShowDialog();
            }
            else
            {
                //On affiche le message d'erreur
                Outils.Erreur("Pas de connexion à la base de données");
            }

        }

        /// <summary>
        /// Methode apellée a la fin du programme
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Application_Exit(object sender, ExitEventArgs e)
        {
            if (Outils.Choix("Voulez vous sauvegarder les modifications apportées ?", "Sauvegarder"))
            {
                if (!Base.Sauvegarder()) // ==false
                {
                    Outils.Erreur(@"La sauvegarde n'a pas pu s'effectuée !");
                }
            }

            if (!Base.Fin()) // ==false
            {
                Outils.Alerte("La connexion à la base de donnée n'a pas été correctement effectuée");
            }
        }
    }
}

