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

public enum Contraccion {
	suma(-1), sumaAcentuada(0), vocalica(1), consonantica(2), monosilabosEw(3), jonica(4), comePrimera(6);
	
    private int valorEntero;

    
    Contraccion (int valorEntero){
        this.valorEntero = valorEntero;
    }
    

    
    public int valorEntero(){
        return this.valorEntero;
    }   
    
   
    
    
    public static Contraccion getEnum(int valorEntero){
        for (Contraccion enu : values()) {
            if (enu.valorEntero==valorEntero){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de contracción para el número " + valorEntero);
    }	


	


}