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

public enum FuerteDebil {
	Debil(0, "debil"), Fuerte(1, "fuerte");
	
    private String etiquetaRecursos;
    private int valorEntero;
    
    FuerteDebil (int valorEntero, String etiquetaRecursos){
        this.etiquetaRecursos = etiquetaRecursos;
        this.valorEntero = valorEntero;
        
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }   
    

    
    public int valorEntero(){
        return this.valorEntero;
    } 
    
    public static FuerteDebil getEnum(int valorEntero){
        for (FuerteDebil tiempo : values()) {
            if (tiempo.valorEntero==valorEntero){
                return tiempo;
            }
        }
        throw new RuntimeException("no hay theEnum de FuerteDebil para el número " + valorEntero);
    }	
	

	

}