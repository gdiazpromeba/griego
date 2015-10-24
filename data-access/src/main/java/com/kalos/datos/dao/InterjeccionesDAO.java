package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.InterjeccionBean;

public interface InterjeccionesDAO {

	public abstract List<InterjeccionBean> getRegistros(List<String> ids);
	
	public List<InterjeccionBean> seleccionaTodos();

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getPorLetra(java.lang.String)
	 */
	public abstract List<String> seleccionaPorLetra(String letra);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getPorLetra(java.lang.String)
	 */
	public abstract List<String> seleccionaPorCanonica(String canonica);
	
	public List<InterjeccionBean> seleccionaPorFormaSinSignificado(String forma);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getInidvidual(java.lang.String)
	 */
	public abstract InterjeccionBean getInidvidual(String adjetivoId);

	public abstract void modificaCodigoIndividual(int nuevoCodigo, String id);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#inserta(kalos.beans.AdjetivoBean)
	 */
	public abstract void inserta(InterjeccionBean ea);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#modifica(kalos.beans.AdjetivoBean)
	 */
	public abstract void modifica(InterjeccionBean ea);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#borra(java.lang.String)
	 */
	public abstract void borra(String cmsGroupId);

}