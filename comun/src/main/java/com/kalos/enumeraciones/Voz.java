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

public enum Voz {
	Activa(1, "voz_activa"), Media(2, "voz_media"), Pasiva(3, "voz_pasiva");
	
    private int valorEntero;
    private String etiquetaRecursos;
    
    Voz (int valorEntero, String etiquetaRecursos){
        this.valorEntero = valorEntero;
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public int valorEntero(){
        return this.valorEntero;
    }   
    
    
    public static Voz getEnum(int valorEntero){
        for (Voz enu : values()) {
            if (enu.valorEntero==valorEntero){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de voz para el número " + valorEntero);
    } 
}