package com.kalos.datos.gerentes;

import java.util.List;
import java.util.Map;

import com.kalos.beans.TipoJerarquico;

public interface GerenteTiposVerbo {

	List<TipoJerarquico> getTodos();

	Map<Integer, String> getMapaTiposID();

}