/*
 * Created on 31/01/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;


/**
 * @author gonzalo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public enum Genero {
	MasculinoOFemenino(4, "A", "masculino_o_femenino"), Masculino(1, "M", "masculino"), 
	Femenino(2, "F", "femenino"), Neutro(3, "N", "neutro");
	
    private int valorEntero;
    private String etiquetaRecursos;
    private String letra;
    
    Genero (int valorEntero, String letra, String etiquetaRecursos){
        this.valorEntero = valorEntero;
        this.etiquetaRecursos = etiquetaRecursos;
        this.letra = letra;
    }
    
    public String getCadenaRecursos(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public int valorEntero(){
        return this.valorEntero;
    }   
    
    public String valorLetra(){
        return this.letra;
    }     
    
    
    public static Genero getEnum(int valorEntero){
        for (Genero enu : values()) {
            if (enu.valorEntero==valorEntero){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de genero para el número " + valorEntero);
    }
    
    public static Genero getEnum(String letra){
        for (Genero enu : values()) {
            if (enu.letra.equals(letra)){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de genero para la letra " + letra);
    }   
	
	/**
	 * devuelve sólo el masculino, femenino y neutro
	 */
	public static Genero[] getMFN(){
		return new Genero[]{Masculino, Femenino, Neutro};
	}

	
}
