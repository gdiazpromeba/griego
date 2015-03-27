package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.TermRegVerbo;

public interface TermRegVerboDAO {

	List<TermRegVerbo> seleccionaPorTerminacion(String terminacion);

}