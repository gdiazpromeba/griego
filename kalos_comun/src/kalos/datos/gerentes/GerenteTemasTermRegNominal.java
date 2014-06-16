package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.TemaTermRegNominal;

public interface GerenteTemasTermRegNominal {

	/**
	 * devuelve una lista de todos los temas artificiales contractos
	 * creados para calcular las terminaciones contractas
	 * @return
	 */
	List<TemaTermRegNominal> seleccionaTodos();

	

	/**
	 * borra la tabla de temas nominales contractos (usado por la rutina generadora)
	 *
	 */
	void borra();

	/**
	 * inserta un registro en la tabla de temas nominales contractos (usado por la rutina generadora)
	 * @param ttrn
	 */
	void inserta(TemaTermRegNominal ttrn);

}