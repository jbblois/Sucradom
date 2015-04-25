using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sucradom.LIB
{
   public partial class client
   {
       /// <summary>
       /// Conversion du pseudo Booleen IsActif en Boolean
       /// </summary>
       public Boolean EstActif 
       { 
           get 
           {
               if (IsActif != null)
               {
                   // si IsActif == OUI alors .equals() retourne true
                   // sinon IsActif == NON alors .equals() retourne false
                    return IsActif.Equals("OUI");
               }
               else
               {
                   IsActif = "NON";
                   return false;
               }
           }
           set
           {
               //Condition ternaire
               //Variable = ( Condition ? 'ResultatTRUE' : 'ResultatFALSE' )
               IsActif = (value ? "OUI" : "NON");
           }
       }

       /// <summary>
       /// Permet de récupérer le nom complet d'un client
       /// </summary>
       public String NomComplet { get { return Nom.ToUpper() + " " + Prenom; } }

       /// <summary>
       /// Initialiser un mot de passe pour le client
       /// </summary>
       public void GenererMDP()
       { 
           MotDePasse =  Prenom.First() + "" + Nom.ToLower() + "" + DateTime.Now.DayOfYear; 
       }
       /// <summary>
       /// Compte le nombre de commandes de ce client
       /// </summary>
       public int NbCommandes 
       { 
           get
           {
               return tetecommandes.Count();
           }
       
       }
   }
   public partial class produit
   {
       /// <summary>
       /// Conversion du pseudo Booleen IsDisponible en Boolean
       /// </summary>
       public Boolean EstDisponible
       {
           get
           {
               if (Quantite > 0)
               {
                   return  (IsDisponible.Equals("OUI"));
               }
               else
               {
                   IsDisponible = "NON";
                   return false;
               }
           }
           set
           {
               if (Quantite > 0)
               {
                   //Condition ternaire
                   //Variable = ( Condition ? 'ResultatTRUE' : 'ResultatFALSE' )
                   IsDisponible = (value == true ? "OUI" : "NON");
               }
               else
               {
                   IsDisponible = "NON";
               }
           }
       }

	   public int QuantiteExacte()
	   {
		  int sumVentes = lignecommandes.Where(lc => lc.tetecommande.Date.CompareTo(DateTime.Now) <= 0 
											      && lc.tetecommande.etatcommande.ID != 0 )
										.Sum(lc => lc.Quantite) ;
		  int sumAchats = provisions.Where(p => p.Date.CompareTo(DateTime.Now) <= 0)
									.Sum(p => p.Quantite);
		  return sumAchats - sumVentes;
	   }

   }

   public partial class categorie
   {
       /// <summary>
       /// Compte le nombre de produits de cette catégorie
       /// </summary>
       public int NbProduits 
       { 
           get
           {
               return produits.Count();
           }
       
       }
   }
   public partial class taxe
   {
       /// <summary>
       /// Compte le nombre de produits qui ont cette taxe
       /// </summary>
       public int NbProduits
       {
           get
           {
               return produits.Count();
           }

       }
   }

   public partial class adresse
   {
       public String NomP1
       {
           get
           {
               return Numero + ", rue " + Rue;
           }

       }
       public String NomP2
       {
           get
           {
               return Ville + ", " + Cp.ToUpper();
           }

       }
   }
   public partial class provision
   {
       public String Nom
       {
           get
           {
               return Date.ToShortDateString() + " : " + Quantite;
           }

       }


       public String DateToString
       {
           get
           {
               return Date.Day + " / " + Date.Month + " / " + Date.Year;
           }
       }
   }
}
