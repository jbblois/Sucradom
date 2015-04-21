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
    /// Logique d'interaction pour Taxes.xaml
    /// </summary>
    public partial class Taxes : Window
    {
        private Taxes_ViewModel _ViewModel;
        public Taxes()
        {
            DataContext = _ViewModel = new Taxes_ViewModel();
            InitializeComponent();
        }

        #region Button_Clicks
        private void Button_Insert_Click(object sender, RoutedEventArgs e)
        {

        }

        private void Button_Update_Click(object sender, RoutedEventArgs e)
        {

        }

        private void Button_Delete_Click(object sender, RoutedEventArgs e)
        {

        }        
        #endregion
    }
}
