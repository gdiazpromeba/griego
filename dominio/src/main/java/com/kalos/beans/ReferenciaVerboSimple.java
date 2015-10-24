package com.kalos.beans;

import java.util.List;

/**
 * Información usada por los verbos compuestos. 
 * Contiene la referencia al verbo simple y las preposiciones
 * que el verbo compuesto contiene.
 * @author gdiaz
 *
 */
public class ReferenciaVerboSimple {
	private VerboBean entradaVerboSimple;
	private List<String> preposiciones;
	
	
	public VerboBean getEntradaVerboSimple() {
		return entradaVerboSimple;
	}
	public void setEntradaVerboSimple(VerboBean entradaVerboSimple) {
		this.entradaVerboSimple = entradaVerboSimple;
	}
	public List<String> getPreposiciones() {
		return preposiciones;
	}
	public void setPreposiciones(List<String> preposiciones) {
		this.preposiciones = preposiciones;
	}
}
