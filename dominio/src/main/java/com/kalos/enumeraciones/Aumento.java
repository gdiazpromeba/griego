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

public enum Aumento {
	Ninguno(0, "ninguno"), Normal(-1, "normal"), enEi(2, "en_ei");
	
    private String etiquetaRecursos;
    private int valorEntero;
    
    Aumento (int valorEntero, String etiquetaRecursos){
        this.etiquetaRecursos = etiquetaRecursos;
        this.valorEntero = valorEntero;
        
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }   

    
    public int valorEntero(){
        return this.valorEntero;
    } 
    
    public static Aumento getEnum(int valorEntero){
        for (Aumento enu : values()) {
            if (enu.valorEntero == valorEntero){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de Aumento para el número " + valorEntero);
    } 	
	


	


}