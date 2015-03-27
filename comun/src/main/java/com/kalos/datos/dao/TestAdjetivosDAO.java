package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.TestAdjetivosBean;

public interface TestAdjetivosDAO {

	/* (non-Javadoc)
	 * @see kalos.dao.TestSustantivosDAO#seleccionaTodos()
	 */
	List<TestAdjetivosBean> seleccionaTodos();

	/* (non-Javadoc)
	 * @see kalos.dao.IrrVerbosDAO#inserta(kalos.beans.IrrVerbo)
	 */
	void inserta(TestAdjetivosBean bean);

	/* (non-Javadoc)
	 * @see kalos.dao.IrrVerbosDAO#borra(java.lang.String)
	 */
	void borra(String id);
	
	
	void modifica(TestAdjetivosBean bean);

}