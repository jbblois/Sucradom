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
    /// Logique d'interaction pour Produit.xaml
    /// </summary>
    public partial class Produit : Window
    {
        private Produit_ViewModel _ViewModel;
        public Produit()
        {
            DataContext = _ViewModel = new Produit_ViewModel();
            InitializeComponent();
            Button_Sauvegarder.Content = "Enregister le nouveau produit";
            Button_Sauvegarder.Background = Brushes.DarkGreen;
        }
        public Produit(produit SelectedProduit)
        {
            DataContext = _ViewModel = new Produit_ViewModel(SelectedProduit);
            InitializeComponent();
            Button_Sauvegarder.Content = "Enregistrer les modifications";
            Button_Sauvegarder.Background = Brushes.DarkGoldenrod;
        }

        private void Button_Save_Click(object sender, RoutedEventArgs e)
        {
            if (_ViewModel.Enregistrer())
            {
                this.Close();
            }
        }
    }
}
