package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;


public enum GradoComparacion {
	Positivo(1, "positivo", "P"), Comparativo(2, "comparativo", "C"), Superlativo(3, "superlativo", "S");
	
    private String etiquetaRecursos;
    private String abreviatura;
    private int valorEntero;
    
    GradoComparacion (int valorEntero, String etiquetaRecursos, String abreviatura){
        this.etiquetaRecursos = etiquetaRecursos;
        this.abreviatura = abreviatura;
        this.valorEntero = valorEntero;
        
    }
    
    public String getCadenaRecursos(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }	
    
    public String abreviatura(){
        return this.abreviatura;
    }
    
    public int valorEntero(){
        return this.valorEntero;
    } 
	
    public static GradoComparacion getEnum(String abreviatura){
        for (GradoComparacion enu : values()) {
            if (enu.abreviatura.equals(abreviatura)){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de GradoComparacion para la abreviatura " + abreviatura);
    } 


	
}
