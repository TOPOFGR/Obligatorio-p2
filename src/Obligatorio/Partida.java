/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obligatorio;

import java.util.*;

/**
 *
 * @author TOPOF
 */
public class Partida {
    
    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private Ficha[][] tablero;
    private boolean verN;
    private boolean TurnoRojo;
    private Ficha fichaVacia;
    private Ficha fichaBorde;
    private String historial;
    private boolean[] movimientos;

    public Jugador getJugadorRojo() {
        return jugadorRojo;
    }

    public void setJugadorRojo(Jugador jugadorRojo) {
        this.jugadorRojo = jugadorRojo;
    }

    public Jugador getJugadorAzul() {
        return jugadorAzul;
    }

    public void setJugadorAzul(Jugador jugadorAzul) {
        this.jugadorAzul = jugadorAzul;
    }

    public Ficha[][] getTablero() {
        return tablero;
    }

    public void setTablero(Ficha[][] tablero) {
        this.tablero = tablero;
    }

    public boolean isVerN() {
        return verN;
    }

    public void setVerN(boolean verN) {
        this.verN = verN;
    }

    public boolean isTurnoRojo() {
        return TurnoRojo;
    }

    public void setTurnoRojo(boolean TurnoRojo) {
        this.TurnoRojo = TurnoRojo;
    }

    public Ficha getFichaVacia() {
        return fichaVacia;
    }

    public void setFichaVacia(Ficha fichaVacia) {
        this.fichaVacia = fichaVacia;
    }

    public Ficha getFichaBorde() {
        return fichaBorde;
    }

    public void setFichaBorde(Ficha fichaBorde) {
        this.fichaBorde = fichaBorde;
    }
    
    public Partida(Jugador jugadorRojo, Jugador jugadorAzul) {
        this.jugadorRojo = jugadorRojo;
        this.jugadorAzul = jugadorAzul;
        this.tablero = new Ficha[10][11];
        this.TurnoRojo=true;
        this.verN=true;
        this.fichaVacia= new Ficha("Vacio",0);
        this.fichaBorde= new Ficha("Borde",-1);
        this.historial= "";
        this.movimientos = new boolean[] {true,true,true,true,true,true,true,true};
        
        for (int i = 0; i < tablero.length - 1; i++) {
            for (int j = 0; j < tablero[0].length - 1; j++) {
                tablero[i][j] = fichaVacia;
            }
        }

        for (int i = 0; i < tablero[0].length; i++) {
            tablero[0][i] = fichaBorde;
            tablero[9][i] = fichaBorde;
        }
        for (int i = 0; i < tablero.length; i++) {
            tablero[i][0] = fichaBorde;
            tablero[i][10] = fichaBorde;
        }

        for (int i = 1; i < 9; i++) {
            tablero[1][i + 1] = new Ficha("Azul", i);
            tablero[8][9 - i] = new Ficha("Rojo", i);

        }
    }
   
    public void moverFicha(String movimiento) {    //El metodo ya supone que le estan pasando un String de largo 2
        String turno = "Azul";
        int sentido = 1;
        if (isTurnoRojo()) {
            sentido = -1;
            turno="Rojo";
        }
        
        int ficha = Integer.parseInt(movimiento.substring(0,1));
        if (movimientos[ficha]){
            int i = 0;
        int j = 0;
        for (int x = 0; x < tablero.length - 1; x++) {
            for (int y = 0; y < tablero[0].length - 1; y++) {
                if (tablero[x][y].getValor() == ficha && tablero[x][y].getTipo().equals(turno)) {
                    i = x;
                    j = y;
                }
            }
        }

        char direccion = movimiento.charAt(1);
        
        switch (direccion) {
            case 'A':
                tablero[i + sentido][j + 1] = tablero[i][j];
                tablero[i][j] = fichaVacia;
                mostrarTablero();
                break;
            case 'D':
                if (tablero[i + sentido][j + 1].getTipo().equals("Vacio")) {
                    tablero[i + sentido][j + 1] = tablero[i][j];
                    tablero[i][j] = fichaVacia;
                    mostrarTablero();
                } else {
                    System.out.println("Movimiento no válido");
                }
                break;
            case 'I':
                if (tablero[i + sentido][j - 1].getTipo().equals("Vacio")) {
                    tablero[i + sentido][j - 1] = tablero[i][j];
                    tablero[i][j] = fichaVacia;
                    mostrarTablero();
                    
                } else {
                    System.out.println("Movimiento no válido");
                }
                break;
            default:
                System.out.println("Movimiento no válido");
        }
        }else{
            System.out.println("Movimiento no válido");
        }
        
    }
    public void Juego(int formaTerm){
        boolean terminado = false;
        while (!terminado){
            
        }
    }
    
    public void cambioTurno(){
        TurnoRojo=!TurnoRojo;
        Arrays.fill(movimientos, 0, 7, true);
        
    }
    
    public boolean sePuedeMover(int ficha){
        String turno = "Azul";
        int sentido = 1;
        if (isTurnoRojo()) {
            sentido = -1;
            turno="Rojo";
        }
        int fila=0;
        int columna=0;
        
        boolean ret = false;
        for (int i = 0; i < tablero.length - 1; i++) {
            for (int j = 0; j < tablero[0].length - 1; j++) {
                if (tablero[i][j].getValor() == ficha && tablero[i][j].getTipo().equals(turno)) {
                    fila = i;
                    columna = j;
                }
            }
        }
        if (tablero[fila+sentido][columna].getTipo().equals("Vacio") || tablero[fila+sentido][columna-1].getTipo().equals("Vacio") || tablero[fila+sentido][columna+1].getTipo().equals("Vacio")){
            ret=false;
        }
        
        
        
        return ret;
        
    }
    
    public void sumaPuntos(){
        int rojo=0;
        int azul=0;
        for (int j = 1; j < tablero[0].length - 1; j++) {
            for (int i = 1; i < 5; i++) {
                if(tablero[i][j].getTipo().equals("Rojo")){
                    rojo+=tablero[i][j].getValor();
                }
            }
            for (int i = 5; i < tablero.length-1; i++) {
                if(tablero[i][j].getTipo().equals("Azul")){
                    azul+=tablero[i][j].getValor();
                }
            }
        }
        if (rojo>azul){
            jugadorRojo.setVictorias(jugadorRojo.getVictorias()+1);
        }
        if (azul>rojo){
            jugadorAzul.setVictorias(jugadorAzul.getVictorias()+1);
        }
    }
    
    public void mostrarTablero() {
        if (verN) {
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
    
    public void mostrarHistorial(){
        
    }
    
}
