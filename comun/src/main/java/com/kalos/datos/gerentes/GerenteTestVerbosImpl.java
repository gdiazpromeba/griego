package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TestVerboBean;
import com.kalos.beans.TipoJerarquico;
import com.kalos.datos.dao.TestVerbosDAO;

public class GerenteTestVerbosImpl implements GerenteTestVerbos {

    private TestVerbosDAO testVerbosDAO;
    private Object[] ultimosParametros;
    private String ultimaSeleccion;

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTestVerbos#seleccionaTodo()
	 */
	public List<TestVerboBean> seleccionaTodos() {
		return testVerbosDAO.seleccionaTodos();
	}
	
	
	public List<TipoJerarquico> reseleccionar(){
		throw new RuntimeException("reselección no implementada");
	}

	/**
	 * @return Returns the ultimaSeleccion.
	 */
	public String getUltimaSeleccion() {
		return ultimaSeleccion;
	}

	/**
	 * @param ultimaSeleccion The ultimaSeleccion to set.
	 */
	public void setUltimaSeleccion(String ultimaSeleccion) {
		this.ultimaSeleccion = ultimaSeleccion;
	}

	/**
	 * @return Returns the ultimosParametros.
	 */
	public Object[] getUltimosParametros() {
		return ultimosParametros;
	}

	/**
	 * @param ultimosParametros The ultimosParametros to set.
	 */
	public void setUltimosParametros(Object[] ultimosParametros) {
		this.ultimosParametros = ultimosParametros;
	}




	/**
	 * @return Returns the testVerbosDAO.
	 */
	public TestVerbosDAO getTestVerbosDAO() {
		return testVerbosDAO;
	}


	/**
	 * @param testVerbosDAO The testVerbosDAO to set.
	 */
	public void setTestVerbosDAO(TestVerbosDAO testVerbosDAO) {
		this.testVerbosDAO = testVerbosDAO;
	}

}
