import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class ArchivoLectura {
    // version que funciona similar a las versiones previas
    // El metodo hayMayLineas siempre avanza
    // el metodo linea devuelve la linea actual, no avanza
    
    private String linea;

    private Scanner in;

    public ArchivoLectura(String unNombre) {
        try {
            in = new Scanner(Paths.get(unNombre));
        } 
        catch (IOException e) {
            System.err.println("Error ");
            System.exit(1);
        }

    }

    public boolean hayMasLineas() {
        boolean hay = false;
        if (in.hasNext()){
            linea = in.nextLine();
            hay = true;
        }
        
        return hay;
    }

    public String linea() {
        return linea;
    }

    public void cerrar() {
        in.close();

    }
}
