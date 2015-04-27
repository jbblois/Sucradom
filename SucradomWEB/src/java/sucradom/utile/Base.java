/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import sucradom.dao.CategorieDAO;
import sucradom.metier.Categorie;

/**
 *
 * @author user
 */
public abstract class Base 
{
    private static final String DATABASE_TYPE = "mysql";
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection;
    
    private static final String DATABASE_IP = "localhost";
    private static final String DATABASE_PORT = "3306";
    private static final String DATABASE_URL = "jdbc:"+DATABASE_TYPE+"://"+DATABASE_IP+":"+DATABASE_PORT+"/Sucradom";
    private static final String DATABASE_LOGIN = "SucradomUSER";
    private static final String DATABASE_PASSWORD = "Candy53";
    
    public static final String IMAGES_PATH = "./Ressources";
    public static final String CONTEXT_PATH = "http://localhost:8084/SucradomWEB";
    
    public static Connection GetConnection() 
    {
        
        if (connection == null) 
        {
            try 
            {
                Class.forName(DATABASE_DRIVER);
            } 
            catch (ClassNotFoundException ex) 
            {
               ex.printStackTrace();
            }

            try 
            {
                connection = DriverManager.getConnection(DATABASE_URL,DATABASE_LOGIN,DATABASE_PASSWORD);       
            }
            catch (SQLException ex) 
            {
                ex.printStackTrace();
                return null;
            }
            catch (Exception exc) {
                exc.printStackTrace();
                return null;
            }
        }
        
        return connection;
    }
    
    public static void CloseConnection() 
    {
        
        if(connection != null) {
            try {
                connection.close();
                connection = null;
            }
            catch(Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    public static Date StringToDate(String DateTime)
    {
        Date date = new Date();
        String[] split = DateTime.split(" ")[0].split("-");
        date.setDate(Integer.parseInt(split[2]));
        date.setMonth(Integer.parseInt(split[1]));
        date.setYear(Integer.parseInt(split[0]));
        return date;
    }
    
    private static ArrayList<Categorie> _Categories;
    public static ArrayList<Categorie> GetCategories()
    {
        if (_Categories == null) 
        {
            _Categories = CategorieDAO.List();
        }
        return _Categories;
    }
}
