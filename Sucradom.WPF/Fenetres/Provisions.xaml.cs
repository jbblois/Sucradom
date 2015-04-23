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
	/// Interaction logic for Provisions.xaml
	/// </summary>
	public partial class Provisions : Window
	{
		private Provisions_ViewModel _ViewModel;

		public Provisions(produit SelectedProduit)
		{
			DataContext = _ViewModel = new Provisions_ViewModel(SelectedProduit);
			InitializeComponent();
		}

		#region Button_Clicks
		private void Button_Insert_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.AjouterProvision();
		}

		private void Button_Update_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.ModifierProvision();
		}

		private void Button_Delete_Click(object sender, RoutedEventArgs e)
		{
			_ViewModel.SupprimerProvision();
		}
		#endregion
	}
}
