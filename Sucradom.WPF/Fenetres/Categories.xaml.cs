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
    /// Logique d'interaction pour Categories.xaml
    /// </summary>
    public partial class Categories : Window
    {
        private Categories_ViewModel _ViewModel;

        public Categories()
        {
            DataContext = _ViewModel = new Categories_ViewModel();

            InitializeComponent();
        }

        #region Buttons_Clicks
        private void Button_Insert_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.AjouterCategorie();
        }

        private void Button_Update_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.ModifierCategorie();
        }

        private void Button_Delete_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.SupprimerCategorie();
        }
        #endregion
    }
}
