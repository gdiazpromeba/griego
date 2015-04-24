package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;

public enum Acento {
	Agudo(1, "agudo"), Circunflejo(3, "circunflejo"), Grave(2, "grave"), Ninguno(0, "ninguno");
	
    private int valorEntero;
    private String etiquetaRecursos;
    
    Acento (int valorEntero, String etiquetaRecursos){
        this.valorEntero = valorEntero;
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public int valorEntero(){
        return this.valorEntero;
    }
    
    
    public static Acento getEnum(int valorEntero){
        for (Acento tiempo : values()) {
            if (tiempo.valorEntero==valorEntero){
                return tiempo;
            }
        }
        throw new RuntimeException("no hay theEnum de Acento para el número " + valorEntero);
    }	
	

	
	
	
}
