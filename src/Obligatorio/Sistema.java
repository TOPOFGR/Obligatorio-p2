package Obligatorio;

import java.util.*;

//Autores: Santiago Rügnitz(215381) y Franco Galeano(230996)
public class Sistema  extends Observable{

    private ArrayList<Partida> listaPartidas;
    private ArrayList<Jugador> listaRankings;

    //Imprime la lista segun el ArrayList
    public String mostrarLista(ArrayList lista) {
        String ret = "";
        for (int i = 0; i < lista.size(); i++) {
            ret = ret + ("" + (i + 1) + "- " + lista.get(i) + "\n");
        }
        return ret;
    }

    public Sistema() {
        listaPartidas = new ArrayList<>();
        listaRankings = new ArrayList<>();
    }

    public void ordenarPartidas() {
        this.getListaPartidas().sort((Partida p1, Partida p2) -> {
            return p1.getFecha().compareTo(p2.getFecha());
        });
    }

    public void ordenarRankings() {
        this.getListaRankings().sort((Jugador j1, Jugador j2) -> {
            return j2.getVictorias() - (j1.getVictorias());
        });
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

    public ArrayList<Jugador> getListaRankings() {
        return listaRankings;
    }

    public void agregarRanking(Jugador unJugador) {
        this.getListaRankings().add(unJugador);
        this.setChanged();
        this.notifyObservers();
    }

    public int cantidadRankings() {
        return this.getListaRankings().size();
    }

    public boolean ValidarAlias(String alias) {
        for (int i = 0; i < this.cantidadRankings(); i++) {
            if (alias.equalsIgnoreCase(this.getListaRankings().get(i).getAlias())) {
                return true;
            }
        }
        return false;
    }

    public int RegistrarJugador(String nombre, String alias, int edad) {
        int ok = 0;
       
        if (ValidarAlias(alias)) {
            ok = 1;
        }
        
        if (alias.trim().isEmpty()) {
            ok=2;
        }
        
        if (nombre.trim().isEmpty()) {
            ok=3;
        }
        if (edad>200||edad<1) {
            ok=5;
        }
        
        Jugador j = new Jugador(nombre, alias, edad);
        if (ok == 0) {
            agregarRanking(j);
        }

        return ok;
    }
    public String[] getRankingFormat(){
        String[] ret = new String[this.listaRankings.size()];
        for (int i = 0; i <listaRankings.size(); i++) {
            ret[i]=listaRankings.get(i).toString();
        }
        return ret;
    }

}
