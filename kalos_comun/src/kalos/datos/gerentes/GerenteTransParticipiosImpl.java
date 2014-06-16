package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.TransParticipiosBean;
import kalos.datos.dao.TransParticipiosDAO;

public class GerenteTransParticipiosImpl implements GerenteTransParticipios {

	private TransParticipiosDAO transParticipiosDAO;
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTransParticipios#seleccionaTodos()
	 */
	public List<TransParticipiosBean> seleccionaTodos(){
		return transParticipiosDAO.getTodos();
	}
	
	public List<TransParticipiosBean> seleccionaPorSubstraer(String forma){
		return transParticipiosDAO.seleccionaPorSubstraer(forma);
	}

	/**
	 * @param transParticipiosDAO The transParticipiosDAO to set.
	 */
	public void setTransParticipiosDAO(TransParticipiosDAO transParticipiosDAO) {
		this.transParticipiosDAO = transParticipiosDAO;
	}
	
}
