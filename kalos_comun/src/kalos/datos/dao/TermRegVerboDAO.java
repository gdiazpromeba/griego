package kalos.datos.dao;

import java.util.List;

import kalos.beans.TermRegVerbo;

public interface TermRegVerboDAO {

	List<TermRegVerbo> seleccionaPorTerminacion(String terminacion);

}