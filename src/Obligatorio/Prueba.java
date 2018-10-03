/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obligatorio;

import java.util.*;

/**
 *
 * @author Santiago Rügnitz y Franco Galeano
 */
public class Prueba {

    public static int pedirInt(String mensaje, int min, int max) {
        System.out.println(mensaje);
        Scanner input = new Scanner(System.in);
        boolean continuar = false;
        int dato = 0;
        while (!continuar) {
            try {
                dato = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato incorrecto");
                input.next();
            } catch (Exception ex) {
                System.out.println("Error al ingresar dato");
                input.next();
            }
            if (dato >= min && dato <= max) {
                continuar = true;
            } else {
                System.out.println("Valor no válido, ingrese un entero entre " + min + " y " + max);
            }
        }
        input.nextLine();
        return dato;
    }

    public static void main(String[] args) {
        Sistema s = new Sistema();
        int opcion = 0;
        do {
            opcion = pedirInt("Ingrese opción deseada \n 1)Registrar Jugador"
                    + "\n 2) Jugar Partida \n 3) Replicar Partida\n 4)Ranking "
                    + "\n 5)Salir del juego", 1, 5);
            switch (opcion) {
                case 1:
                    s.agregarJugadores(RegistrarJugador(s));
                    break;
                case 2:
                    if (s.getListaJugadores().size() < 2) {
                        System.out.println("No hay suficientes jugadores registrados");
                    } else {
                        Jugador jugadorRojo = s.getListaJugadores().get(pedirInt("Ingrese el número del jugador rojo \n" + s.mostrarLista(s.getListaJugadores()), 1, s.getListaJugadores().size()) - 1);
                        s.getListaJugadores().remove(jugadorRojo);
                        Jugador jugadorAzul = s.getListaJugadores().get(pedirInt("Ingrese el número del jugador azul \n" + s.mostrarLista(s.getListaJugadores()), 1, s.getListaJugadores().size()) - 1);
                        s.getListaJugadores().add(jugadorRojo);
                        int tipoTerm = pedirInt("Ingrese el número de la forma de terminación: \n 1) Límite de movimientos  \n 2) Llegar con una pieza al lado opuesto \n 3) Llegar con todas las piezas al lado opuesto", 1, 3);
                        int movMax = Integer.MAX_VALUE;
                        if (tipoTerm == 1) {
                            movMax = pedirInt("Ingrese el límite de movimientos", 1, 100);
                        }
                        Partida p = new Partida(jugadorRojo, jugadorAzul, tipoTerm, movMax);
                        s.agregarPartida(p);
                        int contador = 0;
                        while (!p.isTerminado()) {
                            if (p.hayMovimientos()) {
                                contador = 0;
                                mostrarTablero(p);
                                String mensaje = "";
                                if (p.isTurnoRojo()) {
                                    mensaje +="\033[31m"+"Turno del jugador rojo. "+"\033[0m" ;
                                } else {
                                    mensaje +="\033[34m"+"Turno del jugador azul."+"\033[0m";
                                }
                                mensaje += "Se pueden mover las fichas: " + p.mostrarMovimientos() + ". Recuerde que puede cambiar la forma de visualización con VERR/VERN y pasar de turno (si ya se hizo un movimiento) con PASAR";
                                boolean comandoCorrecto = false;
                                while (!comandoCorrecto) {
                                    String dato = leerTexto(mensaje);
                                    if (p.recibirComando(dato)) {
                                        comandoCorrecto = true;
                                    } else {
                                        System.out.println("Texto incorrecto");
                                    }
                                }
                                p.comprobarMov();
                                if (p.termino()) {
                                    System.out.println("Se llegó a la condición de terminación");
                                    p.setTerminado(true);
                                }
                            } else {
                                p.cambioTurno();
                                contador++;
                                if (contador == 2) {
                                    System.out.println("Ya no hay movimientos posibles");
                                    p.setTerminado(true);
                                }

                            }
                            //Guardar Resultados
                        }
                    }
                    break;

                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Fin");
                    break;
                default:
                    System.out.println("Ingrese  número entre 1 y 5");
                    break;
            }
        } while (opcion != 5);

    }

//Case 1
    public static Jugador RegistrarJugador(Sistema s) {
        String nombre = leerTexto("Ingrese Nombre");
        String alias = leerTexto("Ingrese Alias");
        while (s.ValidarAlias(alias)) {
            alias = leerTexto("Alias no Disponible. Ingresa uno nuevo");
        }
        int edad = pedirInt("Ingrese Edad", 1, 200);
        Jugador j = new Jugador(nombre, alias, edad);
        return j;
    }

//Case 2
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

    public static void mostrarTablero(Partida p) {
        Ficha[][] tablero = p.getTablero();
        if (p.isVerN()) {
            for (int i = 1; i < tablero.length - 1; i++) {
                String linea = "";
                for (int j = 1; j < tablero[0].length - 1; j++) {

                    if (tablero[i][j].getTipo().equals("Azul") || tablero[i][j].getTipo().equals("Rojo")) {
                        linea += "|" + tablero[i][j];
                    } else {
                        linea += "| ";
                    }
                }
                System.out.println(linea + "|");
                System.out.println("+-+-+-+-+-+-+-+-+-+");

            }

        } else {
            for (int i = 1; i < tablero.length - 1; i++) {
                String linea = "";
                for (int j = 1; j < tablero[0].length - 1; j++) {

                    if (tablero[i][j].getTipo().equals("Azul") || tablero[i][j].getTipo().equals("Rojo")) {
                        linea += tablero[i][j] + " ";
                    } else {
                        linea += "- ";
                    }
                }
                System.out.println(linea);

            }

        }
    }

}
