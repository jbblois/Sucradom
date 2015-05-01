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
    /// Logique d'interaction pour Clients.xaml
    /// </summary>
    public partial class Clients : UserControl
    {
		private Clients_ViewModel _ViewModel;
        public Clients()
        {
			DataContext = _ViewModel = new Clients_ViewModel();
            InitializeComponent();
        }

		private void Button_Insert_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.AjouterClient();
		}

		private void Button_Update_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.ModifierClient();
		}

		private void Button_Delete_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.SupprimerClient();
		}

		private void Button_Adresses_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.CarnetAdresses();
		}

        private void Button_Commandes_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.CarnetCommandes();
        }
    }
}
