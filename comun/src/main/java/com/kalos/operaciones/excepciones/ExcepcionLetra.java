package com.kalos.operaciones.excepciones;

public class ExcepcionLetra extends RuntimeException {
    private char letra;
	public ExcepcionLetra(char letra, String mensaje){
        super(mensaje);
	    this.letra=letra;
    }
	public char getLetra(){
		return letra;
	}
}
