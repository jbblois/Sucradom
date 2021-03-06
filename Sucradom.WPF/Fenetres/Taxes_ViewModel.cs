﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sucradom.LIB;

namespace Sucradom.WPF.Fenetres
{
    public class Taxes_ViewModel : ViewModel
    {
        private taxe _SelectedTaxe;
        public taxe SelectedTaxe
        {
            get { return _SelectedTaxe; }
            set 
            {
                _SelectedTaxe = value;
                NotifyPropertyChanged();
            }
        }

        public Taxes_ViewModel()
        {

        }

        public void AjouterTaxe()
        {   
            new Formulaires.Taxe().ShowDialog();
        }

        public Boolean ModifierTaxe()
        {
            if (SelectedTaxe != null)
            {
                new Formulaires.Taxe(SelectedTaxe).ShowDialog();
                return true;
            }
            else
            {
                Outils.Alerte("Veuillez selectionner une taxe !");
            }
            return false;
        }

        public Boolean SupprimerTaxe()
        {
            if (SelectedTaxe != null)
            {
                if (SelectedTaxe.NbProduits == 0)
                {
                    if (Outils.Choix("Voulez-vous supprimer la taxe selectionnée", "Suppression"))
                    {
                        Context.taxes.Remove(SelectedTaxe);
                        ViewModel.taxes.Remove(SelectedTaxe);
                        return true;
                    }
                }
                else
                {
                    Outils.Alerte("Des produits utilisent cette taxe !");
                }
            }
            else
            {
                Outils.Alerte("Veuillez selectionner une taxe !");
            }
            return false;
        }
    }
}
