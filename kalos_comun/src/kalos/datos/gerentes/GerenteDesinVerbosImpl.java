package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.DesinVerbo;
import kalos.datos.dao.DesinVerbosDAO;

public class GerenteDesinVerbosImpl implements GerenteDesinVerbos {
	
	private DesinVerbosDAO desinVerbosDAO;
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteDesinVerbos#seleccionaTodas()
	 */
	public List<DesinVerbo> seleccionaTodas(){
		return desinVerbosDAO.seleccionaTodas();
	}
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteDesinVerbos#seleccionaRestringidas()
	 */
	public List<DesinVerbo> seleccionaRestringidas(){
		return desinVerbosDAO.seleccionaRestringidas();
	}

	public void setDesinVerbosDAO(DesinVerbosDAO desinVerbosDAO) {
		this.desinVerbosDAO = desinVerbosDAO;
	}


}
