package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.TestInfinitivoBean;
import kalos.datos.dao.TestInfinitivosDAO;

public class GerenteTestInfinitivosImpl implements GerenteTestInfinitivos  {

    private TestInfinitivosDAO testInfinitivosDAO;

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTestVerbos#seleccionaTodos()
	 */
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTestInfinitivos#seleccionaTodos()
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
