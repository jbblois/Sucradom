using Sucradom.LIB;
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

namespace Sucradom.WPF.Formulaires
{
    /// <summary>
    /// Logique d'interaction pour Categorie.xaml
    /// </summary>
    public partial class Categorie : Window
    {
        private Categorie_ViewModel _ViewModel;
        public Categorie()
        {
            DataContext = _ViewModel = new Categorie_ViewModel();
            InitializeComponent();
        }

        public Categorie(categorie SelectedCategorie)
        {
            DataContext = _ViewModel = new Categorie_ViewModel(SelectedCategorie);
            InitializeComponent();
        }

        private void Button_Save_Click(object sender, RoutedEventArgs e)
        {
            _ViewModel.Enregistrer();
        }
    }
}
