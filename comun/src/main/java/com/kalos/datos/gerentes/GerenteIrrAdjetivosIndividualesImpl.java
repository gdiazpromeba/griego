package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.IrrAdjetivoIndividual;
import com.kalos.datos.dao.IrrAdjetivosIndividualesDAO;

public class GerenteIrrAdjetivosIndividualesImpl implements GerenteIrrAdjetivosIndividuales {

	private IrrAdjetivosIndividualesDAO irrAdjetivosIndividualesDAO;
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#seleccionaPorAdjetivo(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales#seleccionaPorAdjetivo(java.lang.String)
	 */
	public List<String> seleccionaPorAdjetivo(String adjetivoId){
		List<String> irrs=irrAdjetivosIndividualesDAO.seleccionaPorAdjetivo(adjetivoId);
		return irrs;
	}
    
    public List<IrrAdjetivoIndividual> seleccionaPorForma(String forma){
        return irrAdjetivosIndividualesDAO.seleccionaPorForma(forma);
    }

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#seleccionIndividual(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales#seleccionIndividual(java.lang.String)
	 */
	public IrrAdjetivoIndividual seleccionIndividual(String irrAdjetivoEnteroId){
		IrrAdjetivoIndividual iae=irrAdjetivosIndividualesDAO.seleccionIndividual(irrAdjetivoEnteroId);
		return iae;
	}

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#inserta(kalos.beans.IrrAdjetivoEntero)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales#inserta(kalos.beans.IrrAdjetivoIndividual)
	 */
	public void inserta(IrrAdjetivoIndividual iae){
		irrAdjetivosIndividualesDAO.inserta(iae);
	}

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#modifica(kalos.beans.IrrAdjetivoEntero)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales#modifica(kalos.beans.IrrAdjetivoIndividual)
	 */
	public void modifica(IrrAdjetivoIndividual bean){
	  irrAdjetivosIndividualesDAO.modifica(bean);
	}

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#borra(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales#borra(java.lang.String)
	 */
	public void borra(String irrAdjetivoIndividualId){
		irrAdjetivosIndividualesDAO.borra(irrAdjetivoIndividualId);
	}
	
    /* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales#getBeans(java.util.List)
	 */
    public List<IrrAdjetivoIndividual> getBeans(List<String> ids){
    	return irrAdjetivosIndividualesDAO.getRegistros(ids);
    }

	
    

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales#setIrrAdjetivosEnterosDAO(kalos.datos.dao.IrrAdjetivosIndividualesDAO)
	 */
	public void setIrrAdjetivosIndividualesDAO(IrrAdjetivosIndividualesDAO irrAdjetivosIndividualesDAO) {
		this.irrAdjetivosIndividualesDAO = irrAdjetivosIndividualesDAO;
	}

}