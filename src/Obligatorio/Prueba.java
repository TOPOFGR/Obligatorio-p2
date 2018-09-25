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
                    s.agregarPartida(RegistrarPartida(s));
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Fin");
                    break;
                default:
                    System.out.println("Ingrese ");
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
    public static Partida RegistrarPartida(Sistema s) {
        String formcolumna1 = leerTexto("Ingrese forma de visualización");
        boolean formcolumna2 = Visualizacion(formcolumna1);
        Partida p = new Partida();
        return p;
    }

    //Método de para ver que tipo de visualización se quiere
    public static boolean Visualizacion(String forma) {
        boolean visual = true;
        if (!forma.equalsIgnoreCase("VERN") && !forma.equalsIgnoreCase("VERR")) {
            forma = leerTexto("Ingrese forma de visualización correcta");
        }
        if (forma.equalsIgnoreCase("VERN")) {
            visual = true;
        }
        if (forma.equalsIgnoreCase("VERR")) {
            visual = false;
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

    //Alineado 
    public static int[] estaAlineado(Ficha[][] matriz, int fila, int columna) {
        int[] esta = {-1, -1, -1, -1};
        int fila1 = fila;
        int fila2 = fila;
        int columna1 = columna;
        int columna2 = columna;
        boolean diagonal1 = false;
        boolean diagonal2 = false;
        boolean diagonal3 = false;
        boolean diagonal4 = false;
        boolean horizontal1 = false;
        boolean horizontal2 = false;
        boolean vertical1 = false;
        boolean vertical2 = false;
        while (columna2 >= 0 || columna1 < matriz.length
                || columna1 < matriz[0].length || columna2 >= 0) {
            columna1++;
            columna2--;
            fila1++;
            fila2--;

            //Diagonal a la izquierda arriba
            if (!diagonal1 && columna2 >= 0 && fila2 >= 0
                    && matriz[fila2][columna2].getValor() != 0) {
                if (diagonal2) {
                    esta[0] += matriz[fila2][columna2].getValor() + matriz[fila][columna].getValor();
                    diagonal1 = true;

                } else {
                    esta[0] += matriz[fila2][columna2].getValor() + matriz[fila][columna].getValor();
                    diagonal1 = true;

                }
            }
            //Diagonal a la derecha abajo
            if (!diagonal2 && columna1 < matriz.length
                    && fila1 < matriz[0].length && matriz[fila1][columna1].getValor() != 0) {

                if (diagonal1) {
                    esta[0] += matriz[fila1][columna1].getValor();
                    diagonal2 = true;
                } else {
                    esta[0] += matriz[fila1][columna1].getValor() + matriz[fila][columna].getValor();
                    diagonal2 = true;

                }
            }
            //Diagonal a la derecha arriba
            if (!diagonal3 && fila2 >= 0 && columna1 < matriz.length
                    && matriz[fila2][columna1].getValor() != 0) {
                if (diagonal4) {
                    esta[1] += matriz[fila2][columna1].getValor();
                    diagonal3 = true;
                } else {
                    esta[1] += matriz[fila2][columna1].getValor() + matriz[fila][columna].getValor();
                    diagonal3 = true;
                }

            }
            //Diagonal a la izquierda abajo
            if (!diagonal4 && columna2 >= 0 && fila1 < matriz[0].length
                    && matriz[fila1][columna2].getValor() != 0) {
                if (diagonal3) {
                    esta[1] += matriz[fila1][columna2].getValor();
                    diagonal4 = true;

                } else {
                    esta[1] += matriz[fila1][columna2].getValor() + matriz[fila][columna].getValor();
                    diagonal4 = true;
                }
            }
        }

        if (!horizontal1 && columna2 >= 0 && matriz[fila][columna2].getValor() != 0) {
            if (horizontal2) {
                esta[2] += matriz[fila][columna2];
                horizontal1 = true;
            } else {
                esta[2] += matriz[fila][columna2] + matriz[fila][columna];
                horizontal1 = true;
            }
        }
        if (!horizontal1 && columna1 < matriz.length && matriz[fila][columna2] + matriz[fila][columna]) {
            if (horizontal2) {
                esta[2] += matriz[fila][columna1];
                horizontal2 = true;

            } else {
                esta[2] += matriz[fila][columna1] + matriz[fila][columna];
                horizontal2 = true;

            }
        }
        if (!vertical1 && fila1 < matriz[0].length && matriz[fila2][columna].getValor()
                != 0) {
            if (vertical2) {
                esta[3] += matriz[fila1][columna];
            } else {
                esta[3] += matriz[fila1][columna] + matriz[fila][columna];

            }
        }
        if (!vertical2 && fila2 >= 0 && matriz[fila2][columna].getValor()
                != 0) {
            if (vertical1) {
                esta[3] += matriz[fila2][columna] + matriz[fila][columna];
                vertical2 = true;
            } else {
                esta[3] += matriz[fila2][columna] + matriz[fila][columna];
                vertical2 = true;
            }
        }

        return esta;
    }

    public static int Turno(Ficha[][] matriz, int fila, int columna) {
        int aux = 0;
        int[] turnos = estaAlienado(matriz, fila, columna);
        for (int i = 0; i < turnos.length; i++) {
            if (turnos[i] == 0 && turnos[i] > 8) {
                aux++;
            }
        }
        if (aux == turnos.length) {
            System.out.println("No tiene más turnos");
            return -1;
        } else {
            aux = 0;
            System.out.println("Puede mover ");
            while (aux < turnos.length) {
                if (turnos[aux] > -1 && turnos[aux] > 8) {
                    System.out.println(turnos[aux] + " ");
                }
                aux++;
            }
            int ficha = leerNumero("Seleccione la ficha que quiere mover");
            boolean esta = false;
            int falta = 0;
            while (!esta) {
                aux = 0;
                if (ficha == turnos[aux]) {
                    esta = true;
                }
                if (aux == turnos.length) {
                    ficha = leerNumero("El numero seleccionado no se encuentra "
                            + "o no se puede seleccionar, elija nuevamente");
                }
            }

            return ficha;
        }
    }
}
