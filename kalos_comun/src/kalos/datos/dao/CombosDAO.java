package kalos.datos.dao;

import java.util.List;

import kalos.beans.ValorCombo;

public interface CombosDAO {

    public abstract List<ValorCombo> getPorClave(String paramString);

}