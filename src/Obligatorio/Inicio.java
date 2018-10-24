
package Obligatorio;

/**
 *
 * @author Santiago
 */
public class Inicio {

    public static void main(String[] args) {
        Sistema s = new Sistema(); //hacerlo bien con el file
        Menu v = new Menu(s);
        v.setVisible(true);
        
    }
    
}
