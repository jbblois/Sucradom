using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using Sucradom.LIB;
using Sucradom.WPF.Classes.ViewModels;
using Sucradom.WPF.Fenetres;


namespace Sucradom.WPF.Fenetres
{
    /// <summary>
    /// Logique d'interaction pour Accueil.xaml
    /// </summary>
    public partial class Accueil : Window
    {
        private Accueil_ViewModel _ViewModel;
        public Accueil()
        {
            DataContext = _ViewModel = new Accueil_ViewModel();
            InitializeComponent();
        }

        #region MenuItem_Clicks
        private void MenuItem_Categories_Click(object sender, RoutedEventArgs e)
        {
            this.Hide();
            new Categories().ShowDialog();
            this.Show();
        }

        private void MenuItem_Taxes_Click(object sender, RoutedEventArgs e)
        {
            this.Hide();
            new Taxes().ShowDialog();
            this.Show();
        }
        #endregion

        private void Button_Sauvegarder_Click(object sender, RoutedEventArgs e)
        {
            if (Outils.Choix( "Voulez vous sauvegarder les modifications apportées ?", "Sauvegarder"))
            {
                if (!ViewModel.Sauvegarder())
                {
                    Outils.Erreur(@"La sauvegarde n'a pas pu s'effectuée !");
                }
            }
            ViewModel.Sauvegarder();
        }

        private void Button_Annuler_Click(object sender, RoutedEventArgs e)
        {
            if (Outils.Choix("Voulez vous annuler les modifications apportées ?", "Annuler"))
            {
                if (!ViewModel.Annuler())
                {
                    Outils.Erreur(@"L'annulation n'a pas pu s'effectuée !");
                }
            }
        }
    }
}
