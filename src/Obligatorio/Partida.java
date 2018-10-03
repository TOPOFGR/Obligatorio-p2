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
    private int tipoTerm;
    private int movimientosMax;
    private int movimientosActuales;
    private int contadorJugadas;
    private boolean terminado;

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

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public boolean[] getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(boolean[] movimientos) {
        this.movimientos = movimientos;
    }

    public int getTipoTerm() {
        return tipoTerm;
    }

    public void setTipoTerm(int tipoTerm) {
        this.tipoTerm = tipoTerm;
    }

    public int getMovimientosMax() {
        return movimientosMax;
    }

    public void setMovimientosMax(int movimientosMax) {
        this.movimientosMax = movimientosMax;
    }

    public int getMovimientosActuales() {
        return movimientosActuales;
    }

    public void setMovimientosActuales(int movimientosActuales) {
        this.movimientosActuales = movimientosActuales;
    }

    public int getContadorJugadas() {
        return contadorJugadas;
    }

    public void setContadorJugadas(int contadorJugadas) {
        this.contadorJugadas = contadorJugadas;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }
    
    

    public Partida(Jugador jugadorRojo, Jugador jugadorAzul, int tipoTerm, int movMax) {
        this.jugadorRojo = jugadorRojo;
        this.jugadorAzul = jugadorAzul;
        this.tablero = new Ficha[10][11];
        this.TurnoRojo = true;
        this.verN = true;
        this.fichaVacia = new Ficha("Vacio", 0);
        this.fichaBorde = new Ficha("Borde", -1);
        this.historial = "";
        this.movimientos = new boolean[]{true, true, true, true, true, true, true, true, true};
        this.tipoTerm = tipoTerm;
        this.movimientosMax = movMax;
        this.contadorJugadas=0;
        this.terminado=false;

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
            turno = "Rojo";
        }

        int ficha = Integer.parseInt(movimiento.substring(0, 1));
        if (movimientos[ficha]) {
            int[] array = encontrarPosicion(ficha);
            int i = array[0];
            int j = array[1];

            char direccion = movimiento.charAt(1);

            switch (direccion) {
                case 'A':
                    if (tablero[i + sentido][j].getTipo().equals("Vacio")) {
                        tablero[i + sentido][j] = tablero[i][j];
                        tablero[i][j] = fichaVacia;
                        sumaLineas(ficha);
                        this.setContadorJugadas(this.getContadorJugadas()+1);

                    } else {
                        System.out.println("Movimiento no válido");
                    }
                    break;
                case 'D':
                    if (tablero[i + sentido][j + 1].getTipo().equals("Vacio")) {
                        tablero[i + sentido][j + 1] = tablero[i][j];
                        tablero[i][j] = fichaVacia;
                        sumaLineas(ficha);
                        this.setContadorJugadas(this.getContadorJugadas()+1);
                    } else {
                        System.out.println("Movimiento no válido");
                    }
                    break;
                case 'I':
                    if (tablero[i + sentido][j - 1].getTipo().equals("Vacio")) {
                        tablero[i + sentido][j - 1] = tablero[i][j];
                        tablero[i][j] = fichaVacia;
                        sumaLineas(ficha);
                        this.setContadorJugadas(this.getContadorJugadas()+1);
                    } else {
                        System.out.println("Movimiento no válido");
                    }
                    break;
                default:
                    System.out.println("Movimiento no válido");
            }
        } else {
            System.out.println("Movimiento no válido");
        }

    }

    public void cambioTurno() {
        TurnoRojo = !TurnoRojo;
        Arrays.fill(movimientos, 0, 7, true);

    }

    public boolean sePuedeMover(int ficha) {
        boolean ret = false;
        String turno = "Azul";
        int sentido = 1;
        if (isTurnoRojo()) {
            sentido = -1;
            turno = "Rojo";
        }
        int[] aux = encontrarPosicion(ficha);
        int fila = aux[0];
        int columna = aux[1];

        if (movimientos[ficha] && (tablero[fila + sentido][columna].getTipo().equals("Vacio") || tablero[fila + sentido][columna - 1].getTipo().equals("Vacio") || tablero[fila + sentido][columna + 1].getTipo().equals("Vacio"))) {
            ret = true;
        }

        return ret;

    }

    public void sumaPuntos() {
        int rojo = 0;
        int azul = 0;
        for (int j = 1; j < tablero[0].length - 1; j++) {
            for (int i = 1; i < 5; i++) {
                if (tablero[i][j].getTipo().equals("Rojo")) {
                    rojo += tablero[i][j].getValor();
                }
            }
            for (int i = 5; i < tablero.length - 1; i++) {
                if (tablero[i][j].getTipo().equals("Azul")) {
                    azul += tablero[i][j].getValor();
                }
            }
        }
        if (rojo > azul) {
            jugadorRojo.setVictorias(jugadorRojo.getVictorias() + 1);
        }
        if (azul > rojo) {
            jugadorAzul.setVictorias(jugadorAzul.getVictorias() + 1);
        }
    }


    public boolean termino() {
        boolean ret = false;
        int fila = 5;
        String turno = "Azul";
        if (isTurnoRojo()) {
            fila = 4;
            turno = "Rojo";
        }
        switch (this.tipoTerm) {
            case 1:
                if (this.movimientosActuales == this.movimientosMax) {
                    ret = true;
                }
                break;
            case 2:
                for (int i = 1; i < 9; i++) {
                    if (tablero[fila][i].getTipo().equals(turno)) {
                        ret = true;
                    }
                }
                break;
            case 3:
                int cantFichas = 0;
                if (isTurnoRojo()) {
                    for (int j = 1; j < tablero[0].length - 1; j++) {
                        for (int i = 1; i < 5; i++) {
                            if (tablero[i][j].getTipo().equals("Rojo")) {
                                cantFichas++;
                            }
                        }

                    }
                } else {
                    for (int j = 1; j < tablero[0].length - 1; j++) {
                        for (int i = 5; i < tablero.length - 1; i++) {
                            if (tablero[i][j].getTipo().equals("Azul")) {
                                cantFichas++;
                            }
                        }
                    }
                }
                if (cantFichas == 8) {
                    ret = true;
                }

                break;

        }

        return ret;
    }

    public String mostrarMovimientos() {
        String ret = "";

        for (int i = 1; i < 9; i++) {
            if (movimientos[i]) {
                ret += i + " ";
            }
        }

        return ret;
    }

    public boolean hayMovimientos() {
        boolean ret = false;
        for (int i = 0; i < movimientos.length; i++) {
            if (movimientos[i]) {
                ret = true;
            }
        }
        return ret;
    }

    public void comprobarMov() {
        for (int i = 1; i < movimientos.length; i++) {
            if (movimientos[i]) {
                movimientos[i] = sePuedeMover(i);
            }
        }
    }

    public int[] encontrarPosicion(int ficha) {
        int[] ret = new int[2];
        String turno = "Azul";
        if (isTurnoRojo()) {
            turno = "Rojo";
        }
        for (int i = 0; i < tablero.length - 1; i++) {
            for (int j = 0; j < tablero[0].length - 1; j++) {
                if (tablero[i][j].getValor() == ficha && tablero[i][j].getTipo().equals(turno)) {
                    ret[0] = i;
                    ret[1] = j;
                }
            }

        }
        return ret;
    }

    public void sumaLineas(int ficha) {
        int[] aux = encontrarPosicion(ficha);
        int fila = aux[0];
        int columna = aux[1];
        int diagonalP = ficha;
        int horizontal = ficha;
        int vertical = ficha;
        int diagonalS = ficha;
        boolean arriba = true;
        boolean abajo = true;
        boolean derecha = true;
        boolean izquierda = true;
        int num = 0;
        while (izquierda || derecha || arriba || abajo) {
            num++;
            if (fila - num == 0) {
                arriba = false;
            }
            if (fila + num == 9) {
                abajo = false;
            }
            if (columna + num == 10) {
                derecha = false;
            }
            if (columna - num == 0) {
                izquierda = false;
            }

            if (arriba) {
                vertical += tablero[fila - num][columna].getValor();
            }
            if (abajo) {
                vertical += tablero[fila + num][columna].getValor();
            }
            if (derecha) {
                horizontal += tablero[fila][columna + num].getValor();
            }
            if (izquierda) {
                horizontal += tablero[fila][columna - num].getValor();
            }
            if (arriba && izquierda) {
                diagonalP += tablero[fila - num][columna - num].getValor();
            }
            if (arriba && derecha) {
                diagonalS += tablero[fila - num][columna + num].getValor();
            }
            if (abajo && izquierda) {
                diagonalS += tablero[fila + num][columna - num].getValor();
            }
            if (abajo && derecha) {
                diagonalP += tablero[fila + num][columna + num].getValor();
            }

        }

        Arrays.fill(movimientos, false);
        if (vertical < 9 && vertical != ficha) {
            movimientos[vertical] = true;
        }
        if (horizontal < 9 && horizontal != ficha) {
            movimientos[horizontal] = true;
        }
        if (diagonalP < 9 && diagonalP != ficha) {
            movimientos[diagonalP] = true;
        }
        if (diagonalS < 9 && diagonalS != ficha) {
            movimientos[diagonalS] = true;
        }
    }

    public boolean recibirComando(String dato) {
        boolean ret = false;
        if (dato != null && !dato.isEmpty() && dato.trim().length() > 0) {
            if (dato.length() == 2 && Character.isDigit(dato.charAt(0)) && Character.isLetter(dato.charAt(1))) {
                int ficha = Integer.parseInt(dato.substring(0, 1));
                if (ficha > 0 && ficha < 9 && this.sePuedeMover(ficha)) {
                    this.moverFicha(dato);
                    ret = true;
                }
            }
            if (dato.equals("VERR")) {
                ret = true;
                this.setVerN(false);
            }
            if (dato.equals("VERN")) {
                ret = true;
                this.setVerN(true);
            }
            if (dato.equals("X")) {
                ret = true;
                this.setTerminado(true);
            }
            if (dato.equals("PASAR")) {
                ret = true;
                if (true) {
                    this.setContadorJugadas(0);
                    this.cambioTurno();
                }

            }

        }
        return ret;
    }
}
