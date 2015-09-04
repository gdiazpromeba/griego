package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;

public enum Numero {
	Singular(1, "singular"), Plural(2, "plural"), Dual(3, "dual");
	
    private int valorEntero;
    private String etiquetaRecursos;
    
    Numero (int valorEntero, String etiquetaRecursos){
        this.valorEntero = valorEntero;
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public int valorEntero(){
        return this.valorEntero;
    }   
    
    
    public static Numero getEnum(int valorEntero){
        for (Numero enu : values()) {
            if (enu.valorEntero==valorEntero){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de Numero para el número " + valorEntero);
    }	
	
}