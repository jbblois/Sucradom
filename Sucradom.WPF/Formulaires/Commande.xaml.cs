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
    /// Logique d'interaction pour Commande.xaml
    /// </summary>
    public partial class Commande : Window
    {
        private Commande_ViewModel _ViewModel;

        public Commande()
        {
            DataContext = _ViewModel = new Commande_ViewModel();
            NewCommande();
            
        }
        private void NewCommande()
        {
            InitializeComponent();
            Button_Sauvegarder.Content = "Enregistrer la commande";
            Button_Sauvegarder.Background = Brushes.DarkGreen;
        }
        public Commande(client SelectedClient)
        {
            DataContext = _ViewModel = new Commande_ViewModel(SelectedClient);
            NewCommande();
        }
		public Commande(tetecommande SelectedCommande)
		{
            DataContext = _ViewModel = new Commande_ViewModel(SelectedCommande);
			InitializeComponent();
            Button_Sauvegarder.Content = "Enregistrer les modifications";
            Button_Sauvegarder.Background = Brushes.DarkGoldenrod;
		}


        private void Button_Sauvegarder_Click(object sender, RoutedEventArgs e)
        {
            if (_ViewModel.Enregistrer())
            {
                this.Close();
            }
        }

        private void Button_Insert_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.AjouterLigne();
        }

        private void Button_Update_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.ModifierLigne();
        }

        private void Button_Delete_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.SupprimerLigne();
        }
    }
}
