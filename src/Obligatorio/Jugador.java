
package Obligatorio;

//Autores: Santiago Rügnitz(215381) y Franco Galeano(230996)

import java.io.Serializable;


public class Jugador implements Serializable{

    private String nombre;
    private String alias;
    private int edad;
    private int victorias;

    //Constructores
    public Jugador(String nombre, String alias, int edad) {
        this.nombre = nombre;
        this.alias = alias;
        this.edad = edad;
        this.victorias = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String unNombre) {
        this.nombre = unNombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String unAlias) {
        this.alias = unAlias;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int unaEdad) {
        this.edad = unaEdad;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    @Override
    public String toString() {
        return "Jugador de nombre " + this.getNombre() + ", alias " + this.getAlias() + " y de " + this.getEdad() + " años. Victorias: " + this.getVictorias();
    }

}
