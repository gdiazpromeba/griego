package com.kalos.recursos;

public class ExcepcionEnumeracion extends RuntimeException {

	private String theEnumeracion;
	private int valor;
	
	public ExcepcionEnumeracion(String theEnumeracion, int valor) {
		super("para la theEnumeración " + theEnumeracion  + "no se encontró la cadena del valor " + valor);
		this.theEnumeracion=theEnumeracion;
		this.valor=valor;
	}


	public String getEnumeracion() {
		return theEnumeracion;
	}

	public int getValor() {
		return valor;
	}

}
