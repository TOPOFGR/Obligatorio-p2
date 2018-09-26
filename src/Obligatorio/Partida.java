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
public class Partida {
    
    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private Ficha[][] tablero;
    private boolean verN;
    private boolean TurnoRojo;
    private Ficha fichaVacia;
    private Ficha fichaBorde;

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

    
    

    public Partida(Jugador jugadorRojo, Jugador jugadorAzul, Ficha[][] tableroNuevo) {
        this.jugadorRojo = jugadorRojo;
        this.jugadorAzul = jugadorAzul;
        this.tablero = new Ficha[10][11];
        this.TurnoRojo=true;
        this.verN=true;
        this.fichaVacia= new Ficha("Vacio",0);
        this.fichaBorde= new Ficha("Borde",-1);
        
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
                if (tablero[i + sentido][j].getTipo().equals("Vacio")) {
                    tablero[i + sentido][j] = tablero[i][j];
                    tablero[i][j] = fichaVacia;
                } else {
                    System.out.println("Movimiento no válido");
                }
                break;
            case 'D':
                if (tablero[i + sentido][j + 1].getTipo().equals("Vacio")) {
                    tablero[i + sentido][j + 1] = tablero[i][j];
                    tablero[i][j] = fichaVacia;
                } else {
                    System.out.println("Movimiento no válido");
                }
                break;
            case 'I':
                if (tablero[i + sentido][j - 1].getTipo().equals("Vacio")) {
                    tablero[i + sentido][j - 1] = tablero[i][j];
                    tablero[i][j] = fichaVacia;
                } else {
                    System.out.println("Movimiento no válido");
                }
                break;
            default:
                System.out.println("Movimiento no válido");
        }
    }

    
    
}
