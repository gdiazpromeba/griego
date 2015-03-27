package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.IrrAdjetivoEntero;

public interface IrrAdjetivosEnterosDAO {

	abstract List<String> seleccionaPorAdjetivo(String adjetivoId);

	abstract IrrAdjetivoEntero seleccionIndividual(String irrAdjetivoEnteroId);
	
	List<String> seleccionaPartics(String idAdjetivo);

	abstract void inserta(IrrAdjetivoEntero iae);

	abstract void modifica(IrrAdjetivoEntero bean);

	abstract void borra(String cmsGroupId);

	List<IrrAdjetivoEntero> getRegistros(List<String> ids);
	
	void borraAdjetivo(String id);
}