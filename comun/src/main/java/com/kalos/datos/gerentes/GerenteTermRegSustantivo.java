package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TermRegSustantivo;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;

public interface GerenteTermRegSustantivo {

	List<TermRegSustantivo> seleccionaPorTerminacion(String terminacion);
	
	/**
	 * Usada para la generación de TERM_TEG_SUST. Selecciona el máximo subíndice que se haya generado
	 * hasta ese momento en la tabla TERM_REG_SUST
	 * @param tipoSustantivo
	 * @param caso
	 * @param numero
	 * @return
	 */
	int seleccionaMaxSubindice(int tipoSustantivo, Caso caso, Numero numero);
	
	/** 
	 * inserta una línea en TERM_REG_SUST
	 * @param bean
	 */
	public void inserta(TermRegSustantivo bean);

}