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
    
    private Jugador jugadoruno;
    private Jugador jugadordos;
    //private Ficha unaFicha;

    public Partida() {
        this.setJugadoruno(new Jugador());
        this.setJugadordos(new Jugador());
        
    }

    public Partida (Jugador Jugador1, Jugador Jugador2) {
        this.setJugadoruno(Jugador1);
        this.setJugadordos(Jugador2);
    }

    public Jugador getJugadoruno() {
        return jugadoruno;
    }

    public void setJugadoruno(Jugador Jugador1) {
        this.jugadoruno = Jugador1;
    }

    public Jugador getJugadordos() {
        return jugadordos;
    }

    public void setJugadordos(Jugador Jugador2) {
        this.jugadordos = Jugador2;
    }
    
    
    
    
    
    
}
