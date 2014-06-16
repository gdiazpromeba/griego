package kalos.datos.dao;

import java.util.List;

import kalos.beans.VerbalizadorBean;

public interface VerbalizadorParticipiosDAO {

	List<VerbalizadorBean> seleccionaPorTerminacionNominativo(String terminacionNominativo);

	List<VerbalizadorBean> seleccionaPorTerminacionGenitivo(String terminacionGenitivo);

}