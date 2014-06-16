package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.ValorCombo;
import kalos.datos.dao.CombosDAO;

public class GerenteCombosValoresImpl implements GerenteCombosValores {

	private CombosDAO combosDAO;
	
	public List<ValorCombo> getPorClave(String claveCombo) {
		return combosDAO.getPorClave(claveCombo);
	}

	/**
	 * @param combosDAO The combosDAO to set.
	 */
	public void setCombosDAO(CombosDAO combosDAO) {
		this.combosDAO = combosDAO;
	}


}
