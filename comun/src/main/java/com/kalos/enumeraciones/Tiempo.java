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

public enum Tiempo implements TiempoOAspecto {
	Presente(1, "presente"), Imperfecto(2, "imperfecto"), Futuro(3, "futuro"), 
	Aoristo(4, "aoristo"), Perfecto(5, "perfecto"), Pluscuamperfecto(6, "pluscuamperfecto"); 
	
	
	private int valorEntero;
	private String etiquetaRecursos;
	
	Tiempo (int valorEntero, String etiquetaRecursos){
	    this.valorEntero = valorEntero;
	    this.etiquetaRecursos = etiquetaRecursos;
	}
	
	public String getCadenaRecursos(){
	    return Recursos.getCadena(this.etiquetaRecursos);
	}
	
	public int valorEntero(){
	    return this.valorEntero;
	}
	
	
	public static Tiempo getEnum(int valorEntero){
	    for (Tiempo tiempo : values()) {
            if (tiempo.valorEntero==valorEntero){
                return tiempo;
            }
        }
        throw new RuntimeException("no hay theEnum de tiempo para el número " + valorEntero);
	}
	

      

      
}


