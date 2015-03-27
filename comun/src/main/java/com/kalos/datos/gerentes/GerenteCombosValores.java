package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.ValorCombo;

public interface GerenteCombosValores {

    public abstract List<com.kalos.beans.ValorCombo> getPorClave(String paramString);

    public abstract void setCombosDAO(com.kalos.datos.dao.CombosDAO paramdA);

}