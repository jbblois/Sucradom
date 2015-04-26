/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucradom.metier;

/**
 *
 * @author user
 */
public class Image 
{
    public int ID;
    public String Path;
    public String Alt;

    public Image(int ID, String Path, String Alt) 
    {
        this.ID = ID;
        this.Path = Path;
        this.Alt = Alt;
    }
}
