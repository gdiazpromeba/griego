package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TestSustantivoBean;
import com.kalos.datos.dao.TestSustantivosDAO;

public class GerenteTestSustantivosImpl implements GerenteTestSustantivos   {

    private TestSustantivosDAO testSustantivosDAO;


	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTestSustantivos#seleccionaTodo()
	 */
	public List<TestSustantivoBean> seleccionaTodos() {
		return testSustantivosDAO.seleccionaTodos();
	}


	/**
	 * @param testSustantivoDAO The testSustantivoDAO to set.
	 */
	public void setTestSustantivosDAO(TestSustantivosDAO testSustantivosDAO) {
		this.testSustantivosDAO = testSustantivosDAO;
	}

	
	

}
