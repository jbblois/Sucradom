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
    /// Logique d'interaction pour Client.xaml
    /// </summary>
    public partial class Client : Window
    {
        private Client_ViewModel _ViewModel;
        public Client()
        {
            DataContext = _ViewModel = new Client_ViewModel();
            InitializeComponent();
            Button_Sauvegarder.Content = "Enregister le nouveau client";
            Button_Sauvegarder.Background = Brushes.DarkGreen;
        }
        public Client(client SelectedClient)
        {
            DataContext = _ViewModel = new Client_ViewModel(SelectedClient);
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
