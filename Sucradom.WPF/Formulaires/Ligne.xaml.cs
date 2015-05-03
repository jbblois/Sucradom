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

namespace Sucradom.WPF.Formulaires
{
    /// <summary>
    /// Logique d'interaction pour Ligne.xaml
    /// </summary>
    public partial class Ligne : Window
    {
        private Ligne_ViewModel _ViewModel;
        public Ligne(tetecommande SelectedCommande)
        {
            DataContext = _ViewModel = new Ligne_ViewModel(SelectedCommande);
            InitializeComponent();
            Button_Sauvegarder.Content = "Enregistrer la ligne de commande";
            Button_Sauvegarder.Background = Brushes.DarkGreen; 
        }
        public Ligne(lignecommande SelectedLigne)
        {
            DataContext = _ViewModel = new Ligne_ViewModel(SelectedLigne);
            InitializeComponent();
            Button_Sauvegarder.Content = "Enregistrer les modification";
            Button_Sauvegarder.Background = Brushes.DarkGoldenrod; 
        }

        private void Button_Sauvegarder_Click(object sender, RoutedEventArgs e)
        {
            if (_ViewModel.Enregistrer())
            {
                this.Close();
            }
        }
    }
}
