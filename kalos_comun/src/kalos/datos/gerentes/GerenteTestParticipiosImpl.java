package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.TestParticipiosBean;
import kalos.datos.dao.TestParticipiosDAO;

public class GerenteTestParticipiosImpl implements GerenteTestParticipios  {

    private TestParticipiosDAO testParticipiosDAO;

    public List<TestParticipiosBean> seleccionaTodos() {
		return testParticipiosDAO.seleccionaTodos();
	}
	
	
	/**
	 * @param testParticipiosDAO The testParticipiosDAO to set.
	 */
	public void setTestParticipiosDAO(TestParticipiosDAO testParticipiosDAO) {
		this.testParticipiosDAO = testParticipiosDAO;
	}





}
