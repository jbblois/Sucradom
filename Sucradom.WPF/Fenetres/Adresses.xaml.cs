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

namespace Sucradom.WPF.Fenetres
{
	/// <summary>
	/// Interaction logic for Adresses.xaml
	/// </summary>
	public partial class Adresses : Window
	{
		private Adresses_ViewModel _ViewModel;
		public Adresses(client SelectedClient)
		{
			DataContext = _ViewModel = new Adresses_ViewModel(SelectedClient);
			InitializeComponent();
		}

		#region Button_Clicks
		private void Button_Insert_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.AjouterAdresse();
		}

		private void Button_Update_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.ModifierAdresse();
		}

		private void Button_Delete_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.SupprimerAdresse();
		}
		#endregion
	}
}
