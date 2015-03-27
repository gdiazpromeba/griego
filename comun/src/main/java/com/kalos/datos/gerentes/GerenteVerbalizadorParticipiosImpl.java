package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.VerbalizadorBean;
import com.kalos.datos.dao.VerbalizadorParticipiosDAO;

public class GerenteVerbalizadorParticipiosImpl implements GerenteVerbalizadorParticipios  {
	
	private VerbalizadorParticipiosDAO verbalizadorParticipiosDAO;
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbalizadorParticipios#seleccionaPorTerminacionGenitivo(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbalizadorParticipios#seleccionaPorTerminacionGenitivo(java.lang.String)
	 */
	public List<VerbalizadorBean> seleccionaPorTerminacionGenitivo(String terminacionGenitivo){
		return verbalizadorParticipiosDAO.seleccionaPorTerminacionGenitivo(terminacionGenitivo);
	}
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbalizadorParticipios#seleccionaPorTerminacionNominativo(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbalizadorParticipios#seleccionaPorTerminacionNominativo(java.lang.String)
	 */
	public List<VerbalizadorBean> seleccionaPorTerminacionNominativo(String terminacionNominativo){
		return verbalizadorParticipiosDAO.seleccionaPorTerminacionNominativo(terminacionNominativo);
	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbalizadorParticipios#setVerbalizadorParticipiosDAO(kalos.datos.dao.VerbalizadorParticipiosDAO)
	 */
	public void setVerbalizadorParticipiosDAO(VerbalizadorParticipiosDAO verbalizadorParticipiosDAO) {
		this.verbalizadorParticipiosDAO = verbalizadorParticipiosDAO;
	}

	
}
