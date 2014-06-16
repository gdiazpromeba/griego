package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.ValorCombo;

public interface GerenteCombosValores {

	List<ValorCombo> getPorClave(String claveCombo);
}
