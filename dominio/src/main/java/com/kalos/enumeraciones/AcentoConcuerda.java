package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;

public enum AcentoConcuerda {
	SinForzado(0, "acento_concuerda.sin_forzar"), ElMismo(1, "acento_concuerda.el_mismo"), Nominativo(2, "acento_concuerda.nominativo"), 
	Innecesario(3, "acento_concuerda.innecesario"), Forzado(4, "acento_concuerda.forzado");
	
    
    private int valorEntero;
    private String etiquetaRecursos;
    
    AcentoConcuerda (int valorEntero, String etiquetaRecursos){
        this.valorEntero = valorEntero;
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public int valorEntero(){
        return this.valorEntero;
    }
    
    
    public static AcentoConcuerda getEnum(int valorEntero){
        for (AcentoConcuerda tiempo : values()) {
            if (tiempo.valorEntero==valorEntero){
                return tiempo;
            }
        }
        throw new RuntimeException("no hay theEnum de AcentoConcuerda para el número " + valorEntero);
    }	
	
	
	
}
