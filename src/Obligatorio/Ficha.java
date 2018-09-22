/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

/**
 *
 * @author Santiago
 */
public class Ficha {
    private String tipo;
    private int valor;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Ficha(String tipo, int valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    
    

    @Override
    public String toString() {
        String ret="";
        if(tipo.equals("Rojo")){
            ret="\033[31m"+this.getValor()+"\033[0m";
        }
        if(tipo.equals("Azul")){
            ret="\033[34m"+this.getValor()+"\033[0m";
            
        }
        if(tipo == null){
            ret=" ";
            
        }
        return ret;
    }
    
    
    
    
   
}
    
