
package Obligatorio;

import Interfaz.Menu;
import java.io.*;
/**
 *
 * @author Santiago
 */
public class Inicio {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException { 
        File f = new File("datosSistema.txt"); 
        Sistema s; 
        if (f.exists()) { 
            FileInputStream ff = new FileInputStream("datosSistema.txt"); 
            BufferedInputStream bb = new BufferedInputStream(ff); 
            ObjectInputStream ss = new ObjectInputStream(bb); 
            s= (Sistema) (ss.readObject()); 
             
        }else{ 
            s=new Sistema(); 
        } 
        Menu v = new Menu(s);
        v.setVisible(true);
        
    }
 
    
        
        
}
