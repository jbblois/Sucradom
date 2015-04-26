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
using System.Collections.ObjectModel;

namespace Sucradom.WPF.Formulaires
{
	/// <summary>
	/// Interaction logic for Provision.xaml
	/// </summary>
	public partial class Provision : Window
	{
		private Provision_ViewModel _ViewModel;

		public Provision(produit SelectedProduit)
		{
			DataContext = _ViewModel = new Provision_ViewModel(SelectedProduit);
			InitializeComponent();
			Button_Sauvegarder.Content = "Enregister l'approvisionnement";
			Button_Sauvegarder.Background = Brushes.DarkGreen;
		}

		public Provision(provision SelectedProvision)
		{
			DataContext = _ViewModel = new Provision_ViewModel(SelectedProvision);
			InitializeComponent();
			Button_Sauvegarder.Content = "Enregister les modifications";
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
