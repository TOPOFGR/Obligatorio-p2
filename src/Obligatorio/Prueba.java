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
            opcion = pedirInt("Ingrese opción deseada \n 1) Registrar Jugador"
                    + "\n 2) Jugar Partida \n 3) Replicar Partida\n 4) Ranking "
                    + "\n 5) Salir del juego", 1, 5);
            switch (opcion) {
                case 1:
                    s.agregarJugadores(RegistrarJugador(s));
                    break;
                case 2:
                    if (s.getListaJugadores().size() < 2) {
                        System.out.println("No hay suficientes jugadores registrados");
                    } else {
                        jugar(s);

                    }
                    break;

                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println(".:GAME OVER:.");
                    break;
                default:
                    System.out.println("Ingrese numero entre 1 y 5");
                    break;
            }
        } while (opcion != 5);

    }

//Case 1
    public static Jugador RegistrarJugador(Sistema s) {
        String nombre = leerTexto("Ingrese Nombre");
        String alias = leerTexto("Ingrese Alias ");
        while (s.ValidarAlias(alias)) {
            alias = leerTexto("Alias no Disponible. Ingresa uno nuevo");
        }
        int edad = pedirInt("Ingrese Edad", 1, 200);
        Jugador j = new Jugador(nombre, alias, edad);
        return j;
    }

//Case 2
    public static void jugar(Sistema s) {
        //Crear Partida
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

        Partida partida = p;
        //Jugar
        boolean jugando = true;
        int contador = 0;
        while (jugando) {

            if (p.hayMovimientos()) {
                contador = 0;
                p.mostrarTablero();
                //Aca iba pedirComando
                if (partida.isTurnoRojo()) {
                    System.out.print("Turno del jugador rojo. ");
                } else {
                    System.out.println("Turno del jugador azul. ");
                }
                System.out.println("Se pueden mover las fichas: " + partida.mostrarMovimientos());
                Scanner input = new Scanner(System.in);
                String dato = "";
                boolean comandoCorrecto = false;
                int contadorJugadas = 0;
                while (!comandoCorrecto) {
                    try {
                        dato = input.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Tipo de dato incorrecto");
                        input.next();
                    } catch (Exception ex) {
                        System.out.println("Error al ingresar comando");
                        input.next();
                    }
                    // aca termina pedir dato + if
                    if (dato != null && !dato.isEmpty() && dato.trim().length() > 0) {
                        if (dato.length() == 2 && Character.isDigit(dato.charAt(0)) && Character.isLetter(dato.charAt(1))) {
                            int ficha = Integer.parseInt(dato.substring(0, 1));
                            if (ficha > 0 && ficha < 9 && partida.sePuedeMover(ficha)) {
                                partida.moverFicha(dato);
                                contadorJugadas++;
                                partida.setHistorial(partida.getHistorial() + dato + " ");
                                comandoCorrecto = true;
                            }
                        }
                        if (dato.equals("VERR")) {
                            comandoCorrecto = true;
                            partida.setVerN(false);
                        }
                        if (dato.equals("VERN")) {
                            comandoCorrecto = true;
                            partida.setVerN(true);
                        }
                        if (dato.equals("X")) {
                            comandoCorrecto = true;
                            jugando = false;
                        }
                        if (dato.equals("PASAR")) {
                            comandoCorrecto = true;
                            if (contadorJugadas > 0) {
                                contadorJugadas = 0;
                                partida.cambioTurno();
                            }

                        }
                        if (!comandoCorrecto) {
                            System.out.println("Comando incorrecto");
                        }

                    } else {
                        System.out.println("No se ingresó un comando");
                    }
                }

                p.comprobarMov();
                if (p.termino()) {
                    System.out.println("Se llegó a la condición de terminación");
                    jugando = false;
                }
            } else {
                p.cambioTurno();
                contador++;
            }
            if (contador == 2) {
                System.out.println("Ya no hay movimientos posibles");
                jugando = false;
            }

        }
        //Guardar Resultados
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

    public static void pedirComando(Partida partida) {
        if (partida.isTurnoRojo()) {
            System.out.print("Turno del jugador rojo. ");
        } else {
            System.out.println("Turno del jugador azul. ");
        }
        System.out.println("Se pueden mover las fichas: " + partida.mostrarMovimientos());
        Scanner input = new Scanner(System.in);
        String dato = "";
        boolean comandoCorrecto = false;
        while (!comandoCorrecto) {
            try {
                dato = input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato incorrecto");
                input.next();
            } catch (Exception ex) {
                System.out.println("Error al ingresar comando");
                input.next();
            }
            // aca termina pedir dato + if
            if (dato != null && !dato.isEmpty() && dato.trim().length() > 0) {
                if (dato.length() == 2 && Character.isDigit(dato.charAt(0)) && Character.isLetter(dato.charAt(1))) {
                    int ficha = Integer.parseInt(dato.substring(0, 1));
                    if (ficha > 0 && ficha < 9 && partida.sePuedeMover(ficha)) {
                        partida.moverFicha(dato);
                        partida.setHistorial(partida.getHistorial() + dato + " ");
                        comandoCorrecto = true;
                    }
                }
                if (dato.equals("VERR")) {
                    comandoCorrecto = true;
                    partida.setVerN(false);
                }
                if (dato.equals("VERN")) {
                    comandoCorrecto = true;
                    partida.setVerN(true);
                }
                if (dato.equals("X")) {
                    comandoCorrecto = true;
                    //jugando = false;
                }
                if (dato.equals("PASAR")) {
                    comandoCorrecto = true;
                    if (true) {
                        //contadorJugadas = 0;
                        partida.cambioTurno();
                    }

                }
                if (!comandoCorrecto) {
                    System.out.println("Comando incorrecto");
                }

            } else {
                System.out.println("No se ingresó un comando");
            }
        }
    }
}
