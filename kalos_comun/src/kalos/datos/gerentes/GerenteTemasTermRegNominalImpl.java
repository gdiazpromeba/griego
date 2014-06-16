package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.TemaTermRegNominal;
import kalos.datos.dao.TemasTermRegNominalDAO;

public class GerenteTemasTermRegNominalImpl implements GerenteTemasTermRegNominal {

	private TemasTermRegNominalDAO temasTermRegNominalDAO;
	
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTemasTermRegNominal#seleccionaTodos()
	 */
	public List<TemaTermRegNominal> seleccionaTodos(){
		return temasTermRegNominalDAO.seleccionaTodos();
	}
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTemasTermRegNominal#borra()
	 */
	public void borra(){
		temasTermRegNominalDAO.borra();
	}
	

	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTemasTermRegNominal#inserta(kalos.beans.TemaTermRegNominal)
	 */
	public void inserta(TemaTermRegNominal ttrn){
		temasTermRegNominalDAO.inserta(ttrn);
	}

	/**
	 * @param temasTermRegNominalDAO The temasTermRegNominalDAO to set.
	 */
	public void setTemasTermRegNominalDAO(TemasTermRegNominalDAO temasTermRegNominalDAO) {
		this.temasTermRegNominalDAO = temasTermRegNominalDAO;
	}

}