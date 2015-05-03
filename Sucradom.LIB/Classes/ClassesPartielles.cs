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

       public int Quantite 
       { 
           get 
           {
                // sommes des ventes jusqu'a aujourdh'hui (lignesCommandes)
                int sumVentes = lignecommandes.Where(lc => lc.tetecommande.Date.CompareTo(DateTime.Now) <= 0 
											            && lc.tetecommande.etatcommande.ID > 1 
                                                        && lc.tetecommande.etatcommande.ID < 5)
										      .Sum(lc => lc.Quantite) ;
               // sommes des achats jusqu'a aujourd'hui (provisions)
		        int sumAchats = provisions.Where(p => p.Date.CompareTo(DateTime.Now) <= 0)
									      .Sum(p => p.Quantite);
		        return sumAchats - sumVentes;
           } 
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
       public String Nom
       {
           get
           {
               return NomP1 + " " + NomP2;
           }

       }
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
               return Ville + " ( " + Cp + " )";
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
   public partial class lignecommande
   {
       public float PrixHT 
       {
           get
           {
               return (float)(Quantite * PrixUnitaire);

           }
       
       }
       public float PrixTTC
       {
           get
           {
               return (float)( Quantite * PrixUnitaire * ( (100+ValeurTaxe)/100 ) );

           }

       }
   }
   public partial class tetecommande
   {
       public string Nom
       {
           get
           {
               String nom = ""+ID;
               
               String clientID = "" + client.ID;
               for (int i = clientID.Length; i < 3; i++)
               {
                   nom += '0';               
               }
               nom += clientID;
               String dayOfYear = "" + Date.DayOfYear;
               for (int i = dayOfYear.Length; i < 3; i++)
               {
                   nom += '0'; 
               }
               nom += dayOfYear;
               return (nom);

           }

       }
       public string DateString
       {
           get
           {
               return (Date.Day + "/" + Date.Month + "/" + Date.Year);

           }

       }

       public float PrixHT
       {
           get 
           {
               return lignecommandes.Sum(lc => lc.PrixHT);
           }
       }
       public float PrixTTC
       {
           get
           {
               return lignecommandes.Sum(lc => lc.PrixTTC);
           }
       }
   }
}
