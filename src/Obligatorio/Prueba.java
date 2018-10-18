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
public class Prueba{

    //Metodo para ingressar un entero con un maximo y un mínimo
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
            } catch (Exception e) {
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

    public static void main(String[] args)  {
        Sistema s = new Sistema();
        int opcion = 0;
        do {
            opcion = pedirInt("Ingrese opción deseada \n 1) Registrar Jugador"
                    + "\n 2) Jugar Partida \n 3) Replicar Partida\n 4) Ranking "
                    + "\n 5) Salir del juego", 1, 5);
            switch (opcion) {
                case 1:
                    s.agregarRanking(RegistrarJugador(s));
                    break;
                case 2:
                    if (s.getListaRankings().size() < 2) {
                        System.out.println("No hay suficientes jugadores registrados");
                    } else {
                        Jugador jugadorRojo = s.getListaRankings().get(pedirInt("Ingrese el número del jugador rojo \n" + s.mostrarLista(s.getListaRankings()), 1, s.getListaRankings().size()) - 1);
                        s.getListaRankings().remove(jugadorRojo);
                        Jugador jugadorAzul = s.getListaRankings().get(pedirInt("Ingrese el número del jugador azul \n" + s.mostrarLista(s.getListaRankings()), 1, s.getListaRankings().size()) - 1);
                        s.getListaRankings().add(jugadorRojo);
                        int tipoTerm = pedirInt("Ingrese el número de la forma de terminación: \n 1) Límite de movimientos  \n 2) Llegar con una pieza al lado opuesto \n 3) Llegar con todas las piezas al lado opuesto", 1, 3);
                        int movMax = Integer.MAX_VALUE;
                        if (tipoTerm == 1) {
                            movMax = pedirInt("Ingrese el límite de movimientos", 1, 100);
                        }
                        Partida p = new Partida(jugadorRojo, jugadorAzul, tipoTerm, movMax);
                        s.agregarPartida(p);
                        while (!p.isTerminado()) {
                            if (p.hayMovimientos()) {
                                mostrarTablero(p);
                                String mensaje = "";
                                if (p.isTurnoRojo()) {
                                    mensaje += "\033[31m" + "Turno del jugador rojo. " + "\033[0m";
                                } else {
                                    mensaje += "\033[34m" + "Turno del jugador azul." + "\033[0m";
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
                                
                            } else {
                                p.cambioTurno();
                            }
                            p.termino();
                        }
                        if(p.getContador()>=2){
                            System.out.println("Ya no hay movimientos posibles");
                        }
                        p.sumaPuntos();
                        System.out.println("Partida terminada");
                        if (p.getResultado().equals("Rojo")) {
                            System.out.println("Ganó el jugador Rojo de Alias: " + jugadorRojo.getAlias());
                        } else {
                            if (p.getResultado().equals("Azul")) {
                                System.out.println("Ganó el jugador Azul de Alias " + jugadorAzul.getAlias());
                            } else {
                                System.out.println("Juego terminado en empate");
                            }
                        }
                    }
                    break;

                case 3:
                    if (s.cantidadPartidas() < 1) {
                        System.out.println("No hay partidas registradas");
                        enter();
                    } else {
                        replicarPartida(s);
                    }
                    break;

                case 4:
                    if (s.cantidadRankings() < 1) {
                        System.out.println("No hay Jugadores");
                        enter();
                    } else {
                        s.ordenarRankings();
                        System.out.println("" + s.mostrarLista(s.getListaRankings()));
                    }

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
            alias = leerTexto("El Alias: " + alias + " no esta disponible."
                    + " Ingresa uno nuevo");
        }
        int edad = pedirInt("Ingrese Edad", 1, 200);
        Jugador j = new Jugador(nombre, alias, edad);
        return j;
    }

    //Se valida si el String ingresado es vacio
    public static String leerTexto(String mensaje) {
        Scanner input = new Scanner(System.in);
        System.out.println(mensaje);
        String texto="";
        try {
              texto = input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato incorrecto");
                input.next();
            } catch (Exception ex) {
                System.out.println("Error al ingresar dato");
                input= new Scanner(System.in);
                input.next();
                
            }
        while (texto.trim().isEmpty()) {
            System.out.println("Ingrese un texto válido");
            texto = input.nextLine();
        }
        return texto;
    }

    //Case 3
    public static void replicarPartida(Sistema s) {
        s.ordenarPartidas();
        Partida p = s.getListaPartidas().get(pedirInt("Ingrese el número de "
                + "la partida que desea replicar\n" + s.mostrarLista(s.getListaPartidas()),
                0, s.getListaPartidas().size()) - 1);
        p.reset();
        for (int i = 0; i < p.cantidadMovimientos(); i++) {
            String mov = p.getListaMovimientos().get(i);
            String sigmov="";
            if (i+1<p.cantidadMovimientos()) {
                sigmov=p.getListaMovimientos().get(i+1);
            }
            mostrarTablero(p);
            p.replicarPartida(mov);
            if (sigmov.equals("CT")) {
                p.setTurnoRojo(!(p.isTurnoRojo()));
                i++;
            }
            enter();
        }
        System.out.println("Réplica Finalizada ");
    }

    //Solo sirve para presionar enter (y que lo ingresado por el usuario sea vacio)
    public static void enter() {
        System.out.println("Presione Enter para seguir");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while (!s.trim().isEmpty()) {
            System.out.println("Solo presione Enter");
            s = in.nextLine();
        }
    }

    // Metodo que imprime el tablero segun la forma que el usuario desee
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
