package kalos.datos.dao;

import java.util.List;

import kalos.beans.ValorCombo;

public interface CombosDAO  {

	/**
	 * Obtiene una lista con todos los valores para un combo, dada la clave
	 * del combo
	 * @return
	 */
	List<ValorCombo> getPorClave(String claveCombo);

}