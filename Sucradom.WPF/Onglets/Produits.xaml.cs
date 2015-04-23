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
using System.Windows.Navigation;
using System.Windows.Shapes;
using Sucradom.LIB;

namespace Sucradom.WPF.Onglets
{
    /// <summary>
    /// Logique d'interaction pour Produits.xaml
    /// </summary>
    public partial class Produits : UserControl
    {
        private Produits_ViewModel _ViewModel;
        public Produits()
        {
            DataContext = _ViewModel = new Produits_ViewModel();
            InitializeComponent();
        }

        private void Button_Insert_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.AjouterProduit();
        }

        private void Button_Update_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.ModifierProduit();
        }

        private void Button_Delete_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.SupprimerProduit();
        }
    }
}
