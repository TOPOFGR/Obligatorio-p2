package Obligatorio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Autores: Santiago Rügnitz(215381) y Franco Galeano(230996)
public class Partida implements Serializable {

    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private Ficha[][] tablero;
    private boolean TurnoRojo;
    private Ficha fichaVacia;
    private Ficha fichaBorde;
    private boolean[] movimientos;
    private int tipoTerm;
    private int contadorMov;
    private boolean seMovio;
    private boolean terminado;
    private ArrayList<String> listaMovimientos;
    private LocalDateTime fecha;
    private String resultado;
    private int contador;
    private int movMax;
    private boolean replay;

    public int getMovMax() {
        return movMax;
    }

    public void setMovMax(int movMax) {
        this.movMax = movMax;
    }

    public boolean isReplay() {
        return replay;
    }

    public void setReplay(boolean replay) {
        this.replay = replay;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

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

    public int getContadorMov() {
        return contadorMov;
    }

    public void setContadorMov(int contadorMov) {
        this.contadorMov = contadorMov;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public boolean isSeMovio() {
        return seMovio;
    }

    public void setseMovio(boolean seMovio) {
        this.seMovio = seMovio;
    }

    //Constructor
    public Partida(Jugador jugadorRojo, Jugador jugadorAzul, int tipoTerm, int movMax, boolean replay) {
        this.jugadorRojo = jugadorRojo;
        this.jugadorAzul = jugadorAzul;
        this.tablero = new Ficha[10][11];
        this.TurnoRojo = true;
        this.fichaVacia = new Ficha("Vacio", 0);
        this.fichaBorde = new Ficha("Borde", -1);
        this.movimientos = new boolean[]{true, true, true, true, true, true, true, true, true};
        this.tipoTerm = tipoTerm;
        this.contadorMov = movMax;
        this.movMax = movMax;
        this.seMovio = false;
        this.terminado = false;
        this.fecha = LocalDateTime.now();
        this.reset();
        this.listaMovimientos = new ArrayList<>();
        this.resultado = "Empate";
        this.contador = 0;
        this.replay = replay;
    }

    //Método que recibe un movimiento: si es posible devuelve true y mueve la ficha, si es imposible devuelve false
    public boolean moverFicha(String movimiento) {
        boolean ret = true;
        int sentido = 1;
        if (isTurnoRojo()) {
            sentido = -1;
        }

        int ficha = Integer.parseInt(movimiento.substring(0, 1));
        if (movimientos[ficha] || this.isReplay()) {
            int[] array = encontrarPosicion(ficha);
            int i = array[0];
            int j = array[1];

            char direccion = movimiento.charAt(1);

            switch (direccion) {
                case 'A':
                    if (tablero[i + sentido][j].getTipo().equals("Vacio")) {
                        tablero[i + sentido][j] = tablero[i][j];
                        tablero[i][j] = fichaVacia;
                        this.calculosFicha(ficha, i + sentido, j, movimiento);
                    } else {
                        ret = false;
                    }
                    break;
                case 'D':
                    if (tablero[i + sentido][j + 1].getTipo().equals("Vacio")) {
                        tablero[i + sentido][j + 1] = tablero[i][j];
                        tablero[i][j] = fichaVacia;
                        this.calculosFicha(ficha, i + sentido, j + 1, movimiento);
                    } else {
                        ret = false;
                    }
                    break;
                case 'I':
                    if (tablero[i + sentido][j - 1].getTipo().equals("Vacio")) {
                        tablero[i + sentido][j - 1] = tablero[i][j];
                        tablero[i][j] = fichaVacia;
                        this.calculosFicha(ficha, i + sentido, j - 1, movimiento);
                    } else {
                        ret = false;
                    }
                    break;
                default:
                    ret = false;
            }
        } else {
            ret = false;
        }
        if (ret) {
            agregarMovimiento(movimiento);
            this.termino();
//            if (!this.hayMovimientos()) {
//                this.cambioTurno();
//            }
            while(!this.hayMovimientos()&&this.contador<=2){
                this.cambioTurno();
                
            }
            if(this.contador>=2){
                    this.termino();
                }
        }
        return ret;
    }

    //Cambia de turno
    public void cambioTurno() {
        TurnoRojo = !TurnoRojo;
        Arrays.fill(movimientos, 1, 9, true);
        this.comprobarMov();
        if (!this.hayMovimientos()) {
            this.setContador(this.getContador() + 1);
        }
        this.setseMovio(false);
    }

    //Verifica si la ficha dada tiene lugar para moverse
    public boolean sePuedeMover(int ficha) {
        boolean ret = false;
        int sentido = 1;
        if (isTurnoRojo()) {
            sentido = -1;
        }
        int[] aux = encontrarPosicion(ficha);
        int fila = aux[0];
        int columna = aux[1];

        if (movimientos[ficha] && (tablero[fila + sentido][columna].getTipo().equals("Vacio") || tablero[fila + sentido][columna - 1].getTipo().equals("Vacio") || tablero[fila + sentido][columna + 1].getTipo().equals("Vacio"))) {
            ret = true;
        }

        return ret;

    }

    //Calcula puntajes y da la victoria al jugador ganador (si hay)
    public void sumaPuntos() {
        int rojo = 0;
        int azul = 0;
        for (int i = 1; i < 10; i++) {

            if (tablero[1][i].getTipo().equals("Rojo")) {
                rojo += tablero[1][i].getValor();
            }

            if (tablero[8][i].getTipo().equals("Azul")) {
                azul += tablero[8][i].getValor();
            }

        }
        if (rojo > azul) {
            this.setResultado("Rojo");
            this.agregarVictoria(jugadorRojo);
        }
        if (azul > rojo) {
            this.setResultado("Azul");
            this.agregarVictoria(jugadorAzul);
        }
        
        this.setReplay(true);
    }

    //Aumenta en uno la cantidad de victorias (si corresponde)
    public void agregarVictoria(Jugador jugador) {
        if (!this.isReplay()) {
                jugador.setVictorias(jugador.getVictorias() + 1);
        }
    }

    //Verifica según el tipo de terminación si el juego finalizó
    public void termino() {
        int fila = 8;
        String turno = "Azul";
        if (this.isTurnoRojo()) {
            fila = 1;
            turno = "Rojo";
        }
        if (this.getContador() >= 2) {
            this.setTerminado(true);
            this.sumaPuntos();
        } else {

            switch (this.getTipoTerm()) {
                case 1:
                    if (this.getContadorMov() == 0) {
                        this.setTerminado(true);
                        this.sumaPuntos();
                    }
                    break;
                case 2:
                    for (int i = 1; i < 10; i++) {
                        if (tablero[fila][i].getTipo().equals(turno)) {
                            this.setTerminado(true);
                            this.sumaPuntos();
                        }
                    }
                    break;
                case 3:
                    int cantFichas = 0;
                    for (int j = 1; j < tablero[0].length - 1; j++) {

                        if (tablero[fila][j].getTipo().equals("Rojo")) {
                            cantFichas++;
                        }

                    }
                    if (cantFichas == 8) {
                        this.setTerminado(true);
                        this.sumaPuntos();
                    }

                    break;
            }
        }
    }

    //Verifica si al jugador le quedan movimientos
    public boolean hayMovimientos() {
        boolean ret = false;
        for (int i = 0; i < movimientos.length; i++) {
            if (movimientos[i]) {
                ret = true;
            }
        }
        return ret;
    }

    //Comprueba si las fichas del array que se pueden mover tienen lugar para moverse
    public void comprobarMov() {
        for (int i = 1; i < movimientos.length; i++) {
            if (movimientos[i]) {
                movimientos[i] = sePuedeMover(i);
            }
        }
    }

    //Devuelve un array con la posición en la matriz de el numero que se movió
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

    //Agrega el movimiento a la lista, actualiza contadores y Verifica que otras fichas se pueden mover
    public void calculosFicha(int ficha, int fila, int columna, String mov) {
        this.setseMovio(true);
        this.setContador(0);
        this.setContadorMov(this.getContadorMov() - 1);

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
        this.comprobarMov();
    }

    //Verifica que tipo de String se recibió
    public boolean recibirComando(String dato) {
        boolean ret = false;
        char turno = 'A';
        if (isTurnoRojo()) {
            turno = 'R';
        }
        if (dato != null && !dato.isEmpty() && dato.trim().length() > 0) {
            if (dato.length() == 3 && dato.charAt(2) == turno && Character.isDigit(dato.charAt(0)) && Character.isLetter(dato.charAt(1))) {
                int ficha = Integer.parseInt(dato.substring(0, 1));
                if (ficha > 0 && ficha < 9 && this.sePuedeMover(ficha)) {
                    ret = moverFicha(dato);
                }
            }
        }
        if (dato.equals("X")) {
            agregarMovimiento(dato);
            ret = true;
            this.setTerminado(true);
            if (this.isTurnoRojo()) {
                this.setResultado("Azul");
                this.agregarVictoria(this.getJugadorAzul());
            } else {
                this.setResultado("Rojo");
                this.agregarVictoria(this.getJugadorRojo());
            }

            this.setReplay(true);
        }

        if (dato.equals("CT") && this.isSeMovio()) {
            agregarMovimiento(dato);
            this.cambioTurno();
            ret = true;
        }

        return ret;
    }

//Pone al tablero en la posicion inicial
    public void reset() {
        this.setTurnoRojo(true);
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

    public ArrayList<String> getListaMovimientos() {
        return listaMovimientos;
    }

    //Agrega el movimiento a la lista, si la partida termino no hace nada
    public void agregarMovimiento(String unString) {
        if (!this.isTerminado()) {
            this.getListaMovimientos().add(unString);
        }

    }

    public int cantidadMovimientos() {
        return this.getListaMovimientos().size();
    }

    @Override
    public String toString() {
        String resultado = "Termino en empate";
        if (this.getResultado().equals("Rojo")) {
            resultado = "Gano el jugador rojo";
        }
        if (this.getResultado().equals("Azul")) {
            resultado = "Gano el jugador azul";
        }

        return "Fecha: " + this.getFecha().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + ". Jugador rojo: " + this.getJugadorRojo().getAlias() + ". Jugador azul: " + this.getJugadorAzul().getAlias() + ". " + resultado;
    }

}
