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
    /// Logique d'interaction pour Taxe.xaml
    /// </summary>
    public partial class Taxe : Window
    {
        private Taxe_ViewModel _ViewModel;
        public Taxe()
        {
            DataContext = _ViewModel = new Taxe_ViewModel();
            InitializeComponent();
            Button_Sauvegarder.Content = "Enregister la nouvelle taxe";
            Button_Sauvegarder.Background = Brushes.DarkGreen;
        }

        public Taxe(taxe  SelectedTaxe)
        {
            DataContext = _ViewModel = new Taxe_ViewModel(SelectedTaxe);
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
