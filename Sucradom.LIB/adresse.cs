//------------------------------------------------------------------------------
// <auto-generated>
//    Ce code a été généré à partir d'un modèle.
//
//    Des modifications manuelles apportées à ce fichier peuvent conduire à un comportement inattendu de votre application.
//    Les modifications manuelles apportées à ce fichier sont remplacées si le code est régénéré.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Sucradom.LIB
{
    using System;
    using System.Collections.Generic;
    
    public partial class adresse
    {
        public adresse()
        {
            this.tetecommandes = new HashSet<tetecommande>();
        }
    
        public int ID { get; set; }
        public string Numero { get; set; }
        public string Rue { get; set; }
        public string Cp { get; set; }
        public string Ville { get; set; }
        public string Pays { get; set; }
        public int FID_Client { get; set; }
    
        public virtual client client { get; set; }
        public virtual ICollection<tetecommande> tetecommandes { get; set; }
    }
}
