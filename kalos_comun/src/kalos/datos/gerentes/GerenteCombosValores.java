package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.ValorCombo;

public interface GerenteCombosValores {

    public abstract List<kalos.beans.ValorCombo> getPorClave(String paramString);

    public abstract void setCombosDAO(kalos.datos.dao.CombosDAO paramdA);

}