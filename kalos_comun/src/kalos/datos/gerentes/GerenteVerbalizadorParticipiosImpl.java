package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.VerbalizadorBean;
import kalos.datos.dao.VerbalizadorParticipiosDAO;

public class GerenteVerbalizadorParticipiosImpl implements GerenteVerbalizadorParticipios {
	
	private VerbalizadorParticipiosDAO verbalizadorParticipiosDAO;
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteVerbalizadorParticipios#seleccionaPorTerminacionGenitivo(java.lang.String)
	 */
	public List<VerbalizadorBean> seleccionaPorTerminacionGenitivo(String terminacionGenitivo){
		return verbalizadorParticipiosDAO.seleccionaPorTerminacionGenitivo(terminacionGenitivo);
	}
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteVerbalizadorParticipios#seleccionaPorTerminacionNominativo(java.lang.String)
	 */
	public List<VerbalizadorBean> seleccionaPorTerminacionNominativo(String terminacionNominativo){
		return verbalizadorParticipiosDAO.seleccionaPorTerminacionNominativo(terminacionNominativo);
	}

	/**
	 * @param verbalizadorParticipiosDAO The verbalizadorParticipiosDAO to set.
	 */
	public void setVerbalizadorParticipiosDAO(VerbalizadorParticipiosDAO verbalizadorParticipiosDAO) {
		this.verbalizadorParticipiosDAO = verbalizadorParticipiosDAO;
	}

	
}
