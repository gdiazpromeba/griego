package com.kalos.enumeraciones;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public enum Silaba  {
	ultima(-1), penultima(-2), antepenultima(-3), ninguna(0), primera(1), segunda(2); 
	
    private int valorEntero;

    
    Silaba (int valorEntero){
        this.valorEntero = valorEntero;
    }
    

    
    public int valorEntero(){
        return this.valorEntero;
    }   
    
   
    
    
    public static Silaba getEnum(int valorEntero){
        for (Silaba enu : values()) {
            if (enu.valorEntero==valorEntero){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de Silaba para el número " + valorEntero);
    }	
	

	
      

      
}


