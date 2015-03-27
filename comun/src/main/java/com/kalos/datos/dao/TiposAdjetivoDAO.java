package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.TipoJerarquico;

public interface TiposAdjetivoDAO {

	/**
	 * Obtiene una lista con todos los beans "AdjetivoBean"
	 * @return
	 */
	List<TipoJerarquico> getTodos();


}