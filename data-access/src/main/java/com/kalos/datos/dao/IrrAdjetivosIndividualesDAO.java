package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.IrrAdjetivoIndividual;

public interface IrrAdjetivosIndividualesDAO {

    public List<IrrAdjetivoIndividual> seleccionaTodo();

    public abstract List<IrrAdjetivoIndividual> seleccionaPorForma(String forma);

    /* (non-Javadoc)
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#getRegistros(java.util.List)
     */
    public abstract List<IrrAdjetivoIndividual> getRegistros(List<String> ids);

    /* (non-Javadoc)
     * @see kalos.dao.IrrAdjetivosEnterosDAO#seleccionaPorAdjetivo(java.lang.String)
     */
    /* (non-Javadoc)
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#seleccionaPorAdjetivo(java.lang.String)
     */
    public abstract List<String> seleccionaPorAdjetivo(String adjetivoId);

    /* (non-Javadoc)
     * @see kalos.dao.IrrAdjetivosEnterosDAO#seleccionIndividual(java.lang.String)
     */
    /* (non-Javadoc)
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#seleccionIndividual(java.lang.String)
     */
    public abstract IrrAdjetivoIndividual seleccionIndividual(String irrAdjetivoIndividualId);

    public abstract List<String> seleccionaPartics(String idAdjetivo);

    /* (non-Javadoc)
     * @see kalos.dao.AdjetivoDAO#inserta(kalos.beans.AdjetivoBean)
     */
    /* (non-Javadoc)
     * @see kalos.dao.IrrAdjetivosEnterosDAO#inserta(kalos.beans.IrrAdjetivoEntero)
     */
    /* (non-Javadoc)
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#inserta(kalos.beans.IrrAdjetivoIndividual)
     */
    public abstract void inserta(IrrAdjetivoIndividual iae);

    /* (non-Javadoc)
     * @see kalos.dao.IrrAdjetivosEnterosDAO#modifica(kalos.beans.IrrAdjetivoEntero)
     */
    /* (non-Javadoc)
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#modifica(kalos.beans.IrrAdjetivoIndividual)
     */
    public abstract void modifica(IrrAdjetivoIndividual bean);

    /* (non-Javadoc)
     * @see kalos.dao.AdjetivoDAO#borra(java.lang.String)
     */
    /* (non-Javadoc)
     * @see kalos.dao.IrrAdjetivosEnterosDAO#borra(java.lang.String)
     */
    /* (non-Javadoc)
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#borra(java.lang.String)
     */
    public abstract void borra(String cmsGroupId);

}