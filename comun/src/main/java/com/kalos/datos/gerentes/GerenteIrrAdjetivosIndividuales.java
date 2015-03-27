package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.IrrAdjetivoIndividual;
import com.kalos.datos.dao.IrrAdjetivosIndividualesDAO;

public interface GerenteIrrAdjetivosIndividuales {

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#seleccionaPorAdjetivo(java.lang.String)
	 */
	public abstract List<String> seleccionaPorAdjetivo(String adjetivoId);
    
    /**
     * selecciona por la forma individual irregular
     * @param forma   la forma en código beta
     * @return
     */
    public abstract List<IrrAdjetivoIndividual> seleccionaPorForma(String forma);

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#seleccionIndividual(java.lang.String)
	 */
	public abstract IrrAdjetivoIndividual seleccionIndividual(
			String irrAdjetivoEnteroId);

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#inserta(kalos.beans.IrrAdjetivoEntero)
	 */
	public abstract void inserta(IrrAdjetivoIndividual iae);

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#modifica(kalos.beans.IrrAdjetivoEntero)
	 */
	public abstract void modifica(IrrAdjetivoIndividual bean);

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.negocio.gerentes.GerenteIrrAdjetivos#borra(java.lang.String)
	 */
	public abstract void borra(String irrAdjetivoIndividualId);

	public abstract List<IrrAdjetivoIndividual> getBeans(List<String> ids);

	/**
	 * @param irrAdjetivosIndividualesDAO The irrAdjetivosIndividualesDAO to set.
	 */
	public abstract void setIrrAdjetivosIndividualesDAO(
			IrrAdjetivosIndividualesDAO irrAdjetivosIndividualesDAO);

}