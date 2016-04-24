package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.AdjetivoBean;

public interface AdjetivoDAO {

	public abstract List<AdjetivoBean> getRegistros(List<String> ids);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#seleccionaTodo()
	 */
	public abstract List<AdjetivoBean> getTodos();

	public abstract List<String> getPorTipos(Integer[] tipos);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getPorLetra(java.lang.String)
	 */
	public abstract List<String> seleccionaPorLetra(String letra);

	public abstract List<AdjetivoBean> seleccionaInvariables(String forma);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getPorLetra(java.lang.String)
	 */
	public abstract List<String> seleccionaPorCanonica(String canonica);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getInidvidual(java.lang.String)
	 */
	public abstract AdjetivoBean getInidvidual(String adjetivoId);

	public abstract void modificaCodigoIndividual(int nuevoCodigo,
			String idAdjetivo);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#inserta(kalos.beans.AdjetivoBean)
	 */
	public abstract void inserta(AdjetivoBean ea);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#modifica(kalos.beans.AdjetivoBean)
	 */
	public abstract void modifica(AdjetivoBean ea);

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#borra(java.lang.String)
	 */
	public abstract void borra(String id);

	public abstract void initDao() throws Exception;

}