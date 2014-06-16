package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.TestAdjetivosBean;
import kalos.datos.dao.TestAdjetivosDAO;

public class GerenteTestAdjetivosImpl implements GerenteTestAdjetivos {

	private TestAdjetivosDAO testAdjetivosDAO;

	/**
	 * @param testAdjetivosDAO The testAdjetivosDAO to set.
	 */
	public void setTestAdjetivosDAO(TestAdjetivosDAO testAdjetivosDAO) {
		this.testAdjetivosDAO = testAdjetivosDAO;
	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTestAdjetivos#borra(java.lang.String)
	 */
	public void borra(String id) {
		testAdjetivosDAO.borra(id);

	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTestAdjetivos#inserta(kalos.beans.TestAdjetivosBean)
	 */
	public void inserta(TestAdjetivosBean bean) {
		testAdjetivosDAO.inserta(bean);

	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteTestAdjetivos#seleccionaTodos()
	 */
	public List<TestAdjetivosBean> seleccionaTodos() {
		return testAdjetivosDAO.seleccionaTodos();
	}
	
	public void modifica(TestAdjetivosBean bean){
	    testAdjetivosDAO.modifica(bean);
	}

}
