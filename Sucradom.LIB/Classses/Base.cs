using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sucradom.LIB
{
    public static class Base
    {
        #region Connexion a la BDD
        /// <summary>
        /// Le contexte représente la BDD
        /// </summary>
        public static sucradomEntities Context { get; private set; }

        /// <summary>
        /// Permet d'ouvrir une connexion avec la BDD
        /// </summary>
        /// <returns>retourne true si l'action c'est bien effectuée</returns>
        public static Boolean Debut()
        {
            Context = new sucradomEntities();
            if (Context != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        /// <summary>
        /// Permet de sauvegarder les modifications apportées à la DB
        /// </summary>
        /// <returns>retourne true si l'action c'est bien effectuée</returns>
        public static Boolean Sauvegarder()
        {
            try
            {
                Context.SaveChanges();
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }

        }

        /// <summary>
        /// Permet d'annuler les modifications apportées à la DB
        /// </summary>
        /// <returns>retourne true si l'action c'est bien effectuée</returns>
        public static Boolean Annuler()
        {
            try
            {
                Context.Dispose();
                Context = new sucradomEntities();
                return true;
            }
            catch (Exception)
            {
                return false;
            }

        }

        /// <summary>
        /// Permet de fermer la connexion a la BDD
        /// </summary>
        /// <returns>retourne true si l'action c'est bien effectuée</returns>
        public static Boolean Fin()
        {
            try
            {
                Context.Dispose();
                Context = null;
                return true;
            }
            catch (Exception)
            {
                return false;
            }

        }
        #endregion

        #region Variables Globales
        #endregion

    }
}
