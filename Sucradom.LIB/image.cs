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
    
    public partial class image
    {
        public image()
        {
            this.categories = new HashSet<categorie>();
            this.produits = new HashSet<produit>();
        }
    
        public int ID { get; set; }
        public string Path { get; set; }
        public string Alt { get; set; }
    
        public virtual ICollection<categorie> categories { get; set; }
        public virtual ICollection<produit> produits { get; set; }
    }
}
