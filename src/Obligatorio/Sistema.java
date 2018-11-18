package Obligatorio;

//import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//Autores: Santiago Rügnitz(215381) y Franco Galeano(230996)
public class Sistema extends Observable implements Serializable {

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

    public String RegistrarJugador(String nombre, String alias, int edad) {
        String ret = "";

        if (ValidarAlias(alias)) {
            ret = "El Alias ya existe";
        }

        if (alias.trim().isEmpty()) {
            ret = "No se ingresó un alias";
        }

        if (nombre.trim().isEmpty()) {
            ret = "No se ingresó un nombre";
        }
        if (edad > 200 || edad < 1) {
            ret = "La edad debe estar entre 1 y 200 (inclusive)";
        }

        Jugador j = new Jugador(nombre, alias, edad);
        if (ret.isEmpty()) {
            agregarRanking(j);
        }

        return ret;
    }

    public String[] getRankingFormat() {
        String[] ret = new String[this.listaRankings.size()];
        for (int i = 0; i < listaRankings.size(); i++) {
            ret[i] = listaRankings.get(i).toString();
        }
        return ret;
    }

    public void cerrar() {
        FileOutputStream ff = null;
        try {
            ff = new FileOutputStream("datosSistema.txt");
            BufferedOutputStream b = new BufferedOutputStream(ff);
            ObjectOutputStream ss = new ObjectOutputStream(b);
            ss.writeObject(this);
            ss.flush();
            ss.close();
        } catch (Exception ex) {
            System.out.println("Error al guardar");
        } finally {
            try {
                ff.close();
            } catch (IOException ex) {
                System.out.println("Algo salio mal");
            }
        }
    }

    public String[] getListaPartidasFormat() {
        String[] ret = new String[this.listaPartidas.size()];
        for (int i = 0; i < this.listaPartidas.size(); i++) {
            ret[i] = this.listaPartidas.get(i).toString();
        }
        return ret;
    }

    public void guardarJugadores(String path) {
//        String json = new Gson().toJson(this.getListaRankings());
        path += File.separator + "listaJugadores.JSON";
        File f = new File(path);
        try {
            FileOutputStream fOut = new FileOutputStream(f);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
       //     myOutWriter.append(json);
            myOutWriter.close();
            fOut.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error al guardar lista jugadores");
        } catch (IOException ex) {
            System.out.println("Error al guardar lista jugadores");

        }

    }

}
