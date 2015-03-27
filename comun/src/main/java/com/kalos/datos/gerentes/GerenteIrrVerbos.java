package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.IrrVerbo;
import com.kalos.enumeraciones.Particularidad;

public interface GerenteIrrVerbos extends Reseleccionable{
	
	List<String> seleccionaIdsPorVerboPartic(String verboId, Particularidad partic);
	
	List<IrrVerbo> seleccionaPorVerboPartic(String verboId, Particularidad partic);

	List<IrrVerbo> seleccionaPorVerboParaParticipios(String verboId);
	
	List<IrrVerbo> seleccionaPorVerboParticParaInfinitivos(String verboId, Particularidad partic);
	
	List<IrrVerbo> seleccionaPorTema(String tema);

	List<IrrVerbo> getBeans(List<String> ids);
	
	List<Particularidad> seleccionaParticularidades(String verboId);

	IrrVerbo getInidvidual(String irrVerboId);

	void inserta(IrrVerbo bean);

	void modifica(IrrVerbo bean);

	void borra(String irrVerboId);
	
}
