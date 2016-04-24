package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.TipoJerarquico;

public interface TiposAdjetivoDAO {

	List<TipoJerarquico> seleccionaTodo();

	public void inserta(TipoJerarquico si) ;


}