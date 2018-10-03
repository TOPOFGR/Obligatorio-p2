/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obligatorio;

import java.util.ArrayList;

/**
 *
 * @author TOPOF
 */
public class Sistema {

    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Partida> listaPartidas;
    private ArrayList<Partida> listaRankings;
    
    public String mostrarLista(ArrayList lista){
        String ret="";
        for (int i=0;i<lista.size();i++){
            ret= ret+((i+1)+"- "+lista.get(i)+"\n");
        }
        return ret;
    }

    public Sistema() {
        listaJugadores = new ArrayList<>();
        listaPartidas = new ArrayList<>();
        listaRankings = new ArrayList<>();
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void agregarJugadores(Jugador unJugador) {
        this.getListaJugadores().add(unJugador);
    }

    public int cantidadJugadores() {
        return this.getListaJugadores().size();
    }

    public ArrayList<Partida> getListaPartidas() {
        return listaPartidas;
    }

    public void agregarPartida(Partida unaPartida) {
        this.getListaPartidas().add(unaPartida);
    }

    public int cantidadPartidas() {
        return this.getListaPartidas().size();
    }

    public ArrayList<Partida> getListaRankings() {
        return listaRankings;
    }

    public void agregarRanking(Partida unaPartida) {
        this.getListaRankings().add(unaPartida);
    }

    public int cantidadRankings() {
        return this.getListaRankings().size();
    }
    
    public boolean ValidarAlias(String alias) {
        for (int i = 0; i < this.cantidadJugadores(); i++) {
            if (alias.equals(this.getListaJugadores().get(i).getAlias())) {
                return true;
            }
        }
        return false;
    }

}
