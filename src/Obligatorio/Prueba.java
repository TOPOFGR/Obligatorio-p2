/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obligatorio;

import java.util.*;

/**
 *
 * @author Santiago Rugnitz y Franco Galeano
 */
public class Prueba {
    
    public static void main(String[] args) {
        Sistema s = new Sistema();
        int opcion = 0;
        do {
            opcion = leerNumero("Ingrese opción deseada \n 1)Registrar Jugador"
                    + "\n 2) Jugar Partida \n 3) Replicar Partida\n 4)Ranking "
                    + "\n 5)Salir del juego");
            switch (opcion) {
                case 1:
                    s.agregarJugadores(RegistrarJugador(s));
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    System.out.println("Fin");
                    break;
            }
        } while (opcion != 5);
        
    }
    //Case 1
    public static Jugador RegistrarJugador(Sistema s) {
        String nombre = leerTexto("Ingrese Nombre");
        String alias = leerTexto("Ingrese Alias");
        while (ValidarAlias(alias, s)) {
            alias = leerTexto("Alias no Disponiblem Ingresa uno nuevo");
        }
        int edad = leerNumero("Ingrese Edad");
        Jugador j = new Jugador(nombre, alias, edad);
        return j;
    }
    //Se valida que el Alias no exista en el Sistema
    public static boolean ValidarAlias(String alias, Sistema s) {
        for (int i = 0; i < s.cantidadJugadores(); i++) {
            if (alias.equals(s.getListaJugadores().get(i).getAlias())) {
                return true;                
            }
        }
        return false;
    }
    //Case 2
    public static Partida RegistrarPartida(Sistema s){
        String forma1 = leerTexto("Ingrese forma de visualización");
        boolean forma2 =Visualizacion(forma1);
        Partida p = new Partida();
        return p;
    }
    //Método de para ver que tipo de visualización se quiere
    public static boolean Visualizacion(String forma){
        boolean visual=true;
        if (!forma.equalsIgnoreCase("VERN")&& !forma.equalsIgnoreCase("VERR")) {
            forma=leerTexto("Ingrese forma de visualización correcta");
        }
        if (forma.equalsIgnoreCase("VERN")) {
            visual=true;
        }
        if (forma.equalsIgnoreCase("VERR")) {
            visual=false;
        }
        return visual;
    }

//Se valida que el numero sea mayor a 0
    public static int leerNumero(String mensaje) {
        System.out.println(mensaje);
        Scanner in = new Scanner(System.in);
        int numero = 0;
        boolean error = false;
        while (!error) {
            try {
                numero = in.nextInt();
                while (numero < 0) {
                    System.out.println("Ingrese un valor positivo");
                    numero = in.nextInt();
                }
                error = true;
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un valor numérico y además positivo");
                in.nextLine();
            }
        }
        return numero;
    }

//Se valida si el String ingresado es vacio
    public static String leerTexto(String mensaje) {
        System.out.println(mensaje);
        Scanner in = new Scanner(System.in);
        String texto = in.nextLine();
        while (texto.trim().isEmpty()) {
            System.out.println("Ingrese un texto válido");
            texto = in.nextLine();
        }
        return texto;
    }
}
