package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.IrrVerboIndividual;
import com.kalos.enumeraciones.Particularidad;

public interface IrrVerbosIndividualesDAO {

	List<IrrVerboIndividual> seleccionaPorVerboPartic(String verboId, Particularidad partic);
	
	List<String> seleccionaIdsPorVerboPartic(String verboId, Particularidad partic);
	
	List<IrrVerboIndividual> seleccionaPorForma(String forma);
	
	List<Particularidad> seleccionaPartics(String idVerbo);
	
	void inserta(IrrVerboIndividual bean);
	
	void modifica(IrrVerboIndividual bean);
	
	void borra(String pk);
	
	List<IrrVerboIndividual> getRegistros(List<String> ids);
	


}