package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;




/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */

public enum Aspecto implements TiempoOAspecto {
	Infectivo(1, "infectivo"), Futuro(3, "futuro"), Confectivo(4, "confectivo"), Perfectivo(5, "perfectivo");
	
    private int valorEntero;
    private String etiquetaRecursos;
    
    Aspecto (int valorEntero, String etiquetaRecursos){
        this.valorEntero = valorEntero;
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public int valorEntero(){
        return this.valorEntero;
    }	
    
    
    public static Aspecto getEnum(int valorEntero){
        for (Aspecto enu : values()) {
            if (enu.valorEntero==valorEntero){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de aspecto para el número " + valorEntero);
    }    
	
  
}