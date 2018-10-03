/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obligatorio;

import java.util.Comparator;

/**
 *
 * @author TOPOF
 */

public class RankingDescendente implements Comparator <Jugador>{
      @Override
    public int compare (Jugador j1,Jugador j2){
     return j2.getVictorias()-j1.getVictorias();
}
}
