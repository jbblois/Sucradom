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
    
    public partial class provision
    {
        public int ID { get; set; }
        public System.DateTime Date { get; set; }
        public int Quantite { get; set; }
        public int FID_Produit { get; set; }
    
        public virtual produit produit { get; set; }
    }
}
