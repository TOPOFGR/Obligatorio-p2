/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obligatorio;

/**
 *
 * @author TOPOF
 */
public class Jugador {

    private String nombre;
    private String alias;
    private int edad;

    //Constructores
    public Jugador() {
        this.setNombre("Pepe");
        this.setAlias("xXPepeXx");
        this.setEdad(18);
    }

    public Jugador(String unNombre, String unAlias, int unaEdad) {
        this.setNombre(unNombre);
        this.setAlias(unAlias);
        this.setEdad(unaEdad);
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

    @Override
    public String toString() {
        return "Jugador de nombre " + this.getNombre() + ", alias" + this.getAlias() + " y de " + this.getEdad() + "años";
    }

}
