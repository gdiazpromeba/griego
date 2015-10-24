package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TermRegNominal;
import com.kalos.beans.TermRegSustantivo;
import com.kalos.datos.dao.TermRegSustantivoDAO;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;

public class GerenteTermRegSustantivoImpl  implements GerenteTermRegSustantivo   {

	
	private TermRegSustantivoDAO termRegSustantivoDAO;

	

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTermRegSustantivo#seleccionaPorTerminacion(java.lang.String)
	 */
	public List<TermRegSustantivo> seleccionaPorTerminacion(String terminacion) {
		List<TermRegSustantivo> resultado= termRegSustantivoDAO.seleccionaPorTerminacion(terminacion);
		return resultado;
	}

	
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteTermRegSustantivo#seleccionaMaxSubindice(int, int, int)
	 */
	public int seleccionaMaxSubindice(int tipoSustantivo, Caso caso, Numero numero){
		return termRegSustantivoDAO.seleccionaMaxSubindice(tipoSustantivo, caso, numero);
	}


	/**
	 * @param termRegSustantivoDAO The termRegSustantivoDAO to set.
	 */
	public void setTermRegSustantivoDAO(TermRegSustantivoDAO termRegSustantivoDAO) {
		this.termRegSustantivoDAO = termRegSustantivoDAO;
	}
	
	public void inserta(TermRegSustantivo bean){
		termRegSustantivoDAO.inserta(bean);
	}

	
}
