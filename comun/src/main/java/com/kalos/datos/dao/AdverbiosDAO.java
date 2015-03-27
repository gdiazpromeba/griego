package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.AdverbioBean;

public interface AdverbiosDAO {

	public abstract List<AdverbioBean> getRegistros(List<String> ids);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getPorLetra(java.lang.String)
	 */
	public abstract List<String> seleccionaPorLetra(String letra);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getPorLetra(java.lang.String)
	 */
	public abstract List<String> seleccionaPorCanonica(String canonica);
	
	public List<AdverbioBean> seleccionaPorFormaSinSignificado(String forma);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getInidvidual(java.lang.String)
	 */
	public abstract AdverbioBean getInidvidual(String adjetivoId);

	public abstract void modificaCodigoIndividual(int nuevoCodigo, String id);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#inserta(kalos.beans.AdjetivoBean)
	 */
	public abstract void inserta(AdverbioBean ea);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#modifica(kalos.beans.AdjetivoBean)
	 */
	public abstract void modifica(AdverbioBean ea);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#borra(java.lang.String)
	 */
	public abstract void borra(String cmsGroupId);

}