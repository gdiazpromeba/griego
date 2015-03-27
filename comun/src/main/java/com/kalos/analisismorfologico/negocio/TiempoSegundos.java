package com.kalos.analisismorfologico.negocio;

public class TiempoSegundos {

    private long tiempoAnterior;
    
    
    public TiempoSegundos(){
    	tiempoAnterior=System.currentTimeMillis();
    }
    
    public void setTiempo(String tarea){
    	long nuevoTiempo=System.currentTimeMillis();
    	long segundos=(nuevoTiempo-tiempoAnterior);
    	tiempoAnterior=nuevoTiempo;
    	System.out.println(tarea + " tardó " + segundos + " milisegundos");
    }

}
