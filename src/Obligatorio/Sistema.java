
package Obligatorio;

import java.util.ArrayList;

//Autores: Santiago Rügnitz(215381) y Franco Galeano(230996)

public class Sistema {

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

}
