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

namespace Sucradom.WPF.Onglets
{
    /// <summary>
    /// Logique d'interaction pour Commandes.xaml
    /// </summary>
    public partial class Commandes : UserControl
    {
        private Commandes_ViewModel _ViewModel;
        public Commandes()
        {
            DataContext = _ViewModel = new Commandes_ViewModel();
            InitializeComponent();
        }

        private void Button_Update_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.ModifierCommande();
        }

        private void Button_Delete_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.SupprimerCommande();
        }

    }
}
