using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Formulaires
{
    public class Commande_ViewModel : ViewModel
    {
        private tetecommande _TeteCommande;

        public tetecommande TeteCommande
        {
            get { return _TeteCommande; }
            set { _TeteCommande = value; }
        }

    }
}
