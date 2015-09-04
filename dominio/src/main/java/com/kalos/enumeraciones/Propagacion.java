package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public enum Propagacion {
	Ninguna(0, "propagacion.ninguna"), haciaModoYVoz(-1,"propagacion.hacia_modo_y_voz"), haciaLaVoz(2, "propagacion.hacia_la_voz"), 
	haciaElModo(3, "propagacion.hacia_el_modo"); 
	
    private int valorEntero;
    private String etiquetaRecursos;
    
    Propagacion (int valorEntero, String etiquetaRecursos){
        this.valorEntero = valorEntero;
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public int valorEntero(){
        return this.valorEntero;
    }   
    
    public static Propagacion getEnum(int valorEntero){
        for (Propagacion enu : values()) {
            if (enu.valorEntero==valorEntero){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de Silaba para el número " + valorEntero);
    }
	
	public boolean esDeVoz(){
		return this.equals(haciaModoYVoz) || this.equals(haciaLaVoz);
	}
	
	public boolean esDeModo(){
		return this.equals(haciaModoYVoz) || this.equals(haciaElModo);
	}
	
      

      
}


