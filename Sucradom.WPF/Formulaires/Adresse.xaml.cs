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
	/// Interaction logic for Adresse.xaml
	/// </summary>
	public partial class Adresse : Window
	{
		private Adresse_ViewModel _ViewModel;

		public Adresse(client SelectedClient)
		{
			DataContext = _ViewModel = new Adresse_ViewModel(SelectedClient);
			InitializeComponent();
			Button_Sauvegarder.Content = "Enregister l'adresse";
			Button_Sauvegarder.Background = Brushes.DarkGreen;
		}

		public Adresse(adresse SelectedAdresse)
		{
			DataContext = _ViewModel = new Adresse_ViewModel(SelectedAdresse);
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
