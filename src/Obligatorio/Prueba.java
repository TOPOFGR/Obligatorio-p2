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
            opcion = leerNumero("Ingrese opción deseada \n 1)Registrar Jugador"
                    + "\n 2) Jugar Partida \n 3) Replicar Partida\n 4)Ranking "
                    + "\n 5)Salir del juego");
            switch (opcion) {
                case 1:
                    s.agregarJugadores(RegistrarJugador(s));
                    break;
                case 2:
                    if (s.getListaJugadores().size() < 2) {
                        System.out.println("No hay suficientes jugadores registrados");
                    } else {
                        Partida p = RegistrarPartida(s);
                        s.agregarPartida(p);
                        boolean jugando=true;
                        while (jugando){
                            p.mostrarTablero();
                            pedirComando(p);
                            
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
        Jugador jugadorRojo = s.getListaJugadores().get(pedirInt("Ingrese el número del jugador rojo \n" + s.mostrarLista(s.getListaJugadores()), 1, s.getListaJugadores().size())-1);
        s.getListaJugadores().remove(jugadorRojo);
        Jugador jugadorAzul = s.getListaJugadores().get(pedirInt("Ingrese el número del jugador azul \n" + s.mostrarLista(s.getListaJugadores()), 1, s.getListaJugadores().size())-1);
        s.getListaJugadores().add(jugadorRojo);
        int tipoTerm = pedirInt("Mensaje re lindo preguntando la forma de ganar", 1, 3);
        Partida p = new Partida(jugadorRojo, jugadorAzul,tipoTerm);
        return p;
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
    public static boolean[] estaAlineado(Partida partida, int fila, int columna) {
        Ficha[][] matriz = partida.getTablero();
        int numero = matriz[fila][columna].getValor();
        boolean[] esta = new boolean[8];
        int fila1 = fila;
        int fila2 = fila;
        int columna1 = columna;
        int columna2 = columna;
        int diagonalp = numero;
        int horizontal = numero;
        int vertical = numero;
        int diagonals = numero;
        while (columna2 >= 1 || columna1 < matriz.length - 1
                || columna1 < matriz[0].length - 1 || columna2 >= 1) {
            columna1++;
            columna2--;
            fila1++;
            fila2--;

            //Diagonal a la izquierda arriba
            if (columna2 >= 1 && fila2 >= 1 && matriz[fila2][columna2].getValor() != 0) {
                diagonalp += matriz[fila2][columna2].getValor();

            }
            //Diagonal a la derecha abajo
            if (columna1 < matriz.length && fila1 < matriz[0].length - 1
                    && matriz[fila1][columna1].getValor() != 0) {
                diagonalp += matriz[fila1][columna1].getValor();

            }
            //Diagonal a la derecha arriba
            if (fila2 >= 1 && columna1 < matriz.length - 1
                    && matriz[fila2][columna1].getValor() != 0) {
                diagonals += matriz[fila2][columna1].getValor();
            }
            //Diagonal a la izquierda abajo
            if (columna2 >= 1 && fila1 < matriz[0].length - 1
                    && matriz[fila1][columna2].getValor() != 0) {
                diagonals += matriz[fila1][columna2].getValor();
            }
            //Horizontal hacia la izquierda
            if (columna2 >= 1 && matriz[fila][columna2].getValor() != 0) {
                horizontal += matriz[fila][columna2].getValor();
            }
            //Horizontal hacia la derecha
            if (columna1 < matriz.length - 1 && matriz[fila][columna1].getValor() != 0) {
                horizontal += matriz[fila][columna1].getValor();
            }
            //vertical hacia arriba
            if (fila1 < matriz[0].length - 1 && matriz[fila2][columna].getValor() != 0) {
                vertical += matriz[fila1][columna].getValor();
            }
            //vertical hacia abajo            
            if (fila2 >= 1 && matriz[fila2][columna].getValor() != 0) {
                vertical += matriz[fila2][columna].getValor();
            }
        }
        if (vertical < 9 && vertical != numero) {
            esta[vertical] = true;
        }
        if (horizontal < 9 && horizontal != numero) {
            esta[horizontal] = true;
        }
        if (diagonalp < 9 && diagonalp != numero) {
            esta[diagonalp] = true;
        }
        if (diagonals < 9 && diagonals != numero) {
            esta[diagonals] = true;
        }
        return esta;
    }

    public static String Turno(Partida partida, int fila, int columna) {
        Ficha[][] matriz = partida.getTablero();
        int aux = 0;
        boolean[] turnos = estaAlineado(partida, fila, columna);
        aux = 0;
        for (int i = 0; i < turnos.length; i++) {
            if (turnos[i]) {
                aux++;
            }
        }
        System.out.print("Puede mover ");
        while (aux < turnos.length) {
            if (turnos[aux]) {
                System.out.print(aux + " ");
            }
            aux++;
        }
        String ficha = leerTexto("Ingrese dato");
        int num = Integer.parseInt(ficha.substring(01));
        while (!turnos[num] && num < 0 && num > 8) {
            ficha = leerTexto("El numero seleccionado no se encuentra "
                    + "o no se puede seleccionar, elija nuevamente");
        }

        return ficha;
    }

    public static void pedirComando(Partida partida) {
        if (partida.isTurnoRojo()){
            System.out.print("Turno del jugador rojo. ");
        }
        else{
            System.out.println("Turno del jugador azul. ");
        }
        System.out.println("Ingrese un comando");
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
            if (dato != null && !dato.isEmpty() && dato.trim().length() > 0) {
                if (dato.length() == 2 && Character.isDigit(dato.charAt(0)) && Character.isLetter(dato.charAt(1))) {                                                     //COMPROBAR QUE ES UN INT Y UN CHAR
                    int ficha = Integer.parseInt(dato.substring(0, 1));
                    if (ficha > 0 && ficha < 9 && partida.sePuedeMover(ficha)) {
                        partida.moverFicha(dato);
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
                if (!comandoCorrecto) {
                    System.out.println("Comando incorrecto");
                }

            } else {
                System.out.println("No se ingresó un comando");
            }
        }
    }
}
