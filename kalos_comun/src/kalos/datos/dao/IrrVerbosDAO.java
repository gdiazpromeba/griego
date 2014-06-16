package kalos.datos.dao;

import java.util.List;

import kalos.beans.IrrVerbo;
import kalos.enumeraciones.Particularidad;

public interface IrrVerbosDAO {

	List<IrrVerbo> seleccionaPorVerboPartic(String verboId, Particularidad partic);
	
	List<String> seleccionaIdsPorVerboPartic(String verboId, Particularidad partic); 

	List<IrrVerbo> seleccionaPorVerboParaParticipios(String verboId);
	
	List<IrrVerbo>  seleccionaPorVerboParticParaInfinitivos(String verboId, Particularidad partic);
	
	List<IrrVerbo> seleccionaPorTema(String tema);
	
    public List<IrrVerbo> getRegistros(List<String> ids);
	
	List<Particularidad> seleccionaPartics(String idVerbo);

	IrrVerbo getInidvidual(String irrVerboId);

	void inserta(IrrVerbo bean);

	void modifica(IrrVerbo bean);

	void borra(String irrVerboId);

}