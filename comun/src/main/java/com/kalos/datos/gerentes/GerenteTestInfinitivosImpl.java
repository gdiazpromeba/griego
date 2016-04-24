package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TestInfinitivoBean;
import com.kalos.datos.dao.TestInfinitivosDAO;

public class GerenteTestInfinitivosImpl implements GerenteTestInfinitivos  {

    private TestInfinitivosDAO testInfinitivosDAO;

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTestVerbos#seleccionaTodo()
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTestInfinitivos#seleccionaTodo()
	 */
	public List<TestInfinitivoBean> seleccionaTodos() {
		return testInfinitivosDAO.seleccionaTodos();
	}

	/**
	 * @param testInfinitivosDAO The testInfinitivosDAO to set.
	 */
	public void setTestInfinitivosDAO(TestInfinitivosDAO testInfinitivosDAO) {
		this.testInfinitivosDAO = testInfinitivosDAO;
	}
	
	

}
