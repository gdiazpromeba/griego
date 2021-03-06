package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.AdjetivoComoNominalBean;

public interface AdjetivosComoNominalesDAO {

	List<AdjetivoComoNominalBean> seleccionaPorNominativoParaAM(String nominativo);

	List<AdjetivoComoNominalBean> seleccionaPorGenitivoParaAM(String genitivo);

	void inserta(AdjetivoComoNominalBean bean);

	List<AdjetivoComoNominalBean> seleccionaPorTipos(Integer[] tiposEnteros);
	
	void borraTodo();
	
	void borraAdjetivo(String id);
	
	void setAutocommit(boolean valor);

	void commit();

	public List<AdjetivoComoNominalBean> seleccionaTodos() ;

}