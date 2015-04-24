package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;

public enum PosicionConcuerda {
	Estandar(0, "posicion_concuerda.estandar"), ReferenciaNatural(1, "posicion_concuerda.referencia_natural"), UltimaONormal(2, "posicion_concuerda.ultima_o_normal"), 
	NGSing(3, "posicion_concuerda.nominativo_o_genitivo"), Silaba(4, "posicion_concuerda.silaba"), GSing(5, "posicion_concuerda.genitivo_singular"), 
	NSing(6, "posicion_concuerda.nominativo_singular");
	
    
    private int valorEntero;
    private String etiquetaRecursos;
    
    PosicionConcuerda (int valorEntero, String etiquetaRecursos){
        this.valorEntero = valorEntero;
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public int valorEntero(){
        return this.valorEntero;
    }
    
    
    public static PosicionConcuerda getEnum(int valorEntero){
        for (PosicionConcuerda tiempo : values()) {
            if (tiempo.valorEntero==valorEntero){
                return tiempo;
            }
        }
        throw new RuntimeException("no hay theEnum de PosicionConcuerda para el número " + valorEntero);
    }	
	
	
	
}
