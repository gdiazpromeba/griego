package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.TemaTermRegNominal;

public interface TemasTermRegNominalDAO {

	/**
	 * devuelve todos los temas de terminación nominal contracta con sus respectivos tipos
	 * nominales
	 * @return
	 */
	List<TemaTermRegNominal> seleccionaTodos();

	/**
	 * inserta un tema nominal contracto con su respectivo tipo nominal (usado por la rutina generadora)
	 * @param ttrn
	 */
	void inserta(TemaTermRegNominal ttrn);

	/**
	 * borra la tabla TEMAS_TERM_REG_SUST (usado por la rutina generadora)
	 *
	 */
	void borra();

}