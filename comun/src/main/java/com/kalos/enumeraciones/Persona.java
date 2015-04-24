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

public enum Persona {
	_1ps(1, "1ps"), _2ps(2, "2ps"), _3ps(3, "3ps"), _1pp(4, "1pp"), _2pp(5, "2pp"), _3pp(6, "3pp"), _2pd(7, "2pd"), _3pd(8, "3pd");
	
    
    private int valorEntero;
    private String etiquetaRecursos;
    
    Persona (int valorEntero, String etiquetaRecursos){
        this.valorEntero = valorEntero;
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }	
    
    
    public int valorEntero(){
        return this.valorEntero;
    }   
    
   
    
    
    public static Persona getEnum(int valorEntero){
        for (Persona enu : values()) {
            if (enu.valorEntero==valorEntero){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de Persona para el número " + valorEntero);
    }   
	



}