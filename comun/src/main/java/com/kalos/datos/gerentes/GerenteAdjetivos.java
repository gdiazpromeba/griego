package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.TipoJerarquico;
import com.kalos.datos.dao.IrrAdjetivosEnterosDAO;
import com.kalos.datos.dao.IrrAdjetivosIndividualesDAO;

public interface GerenteAdjetivos {

    public abstract com.kalos.datos.dao.AdjetivoDAO getAdjetivoDAO();

    public abstract void setAdjetivoDAO(com.kalos.datos.dao.AdjetivoDAO aa);

    public abstract void modificaVarios(List<AdjetivoBean> list);

    public abstract void ordena(String s);

    public abstract List<String> seleccionaPartics(String s);

    public abstract List<AdjetivoBean> seleccionaInvariables(String s);

    public abstract GerenteSignificados getGerenteSignificados();

    public abstract void setGerenteSignificados(GerenteSignificados n1);

    public abstract List<String> seleccionaPorLetra(String s);

    public abstract List<String> seleccionaPorCanonica(String s);

    public abstract List<String> seleccionaPorTipos(List<TipoJerarquico> list);

    public abstract List<String> seleccionaPorTipos(Integer ainteger[]);

    public abstract void inserta(AdjetivoBean f1);

    public abstract void borra(String s);

    public abstract void actualiza(AdjetivoBean f1);

    public abstract AdjetivoBean seleccionaUno(String s);

    public abstract List<AdjetivoBean> getBeans(List<String> list);

    public abstract List<String> reseleccionar();

    public abstract String getUltimaSeleccion();

    public abstract void setUltimaSeleccion(String s);

    public abstract Object[] getUltimosParametros();

    public abstract void setUltimosParametros(Object aobj[]);

    public abstract void setIrrAdjetivosEnterosDAO(IrrAdjetivosEnterosDAO e1);

    public abstract void setIrrAdjetivosIndividualesDAO(IrrAdjetivosIndividualesDAO z1);

    public abstract void setGerenteAdjetivosComoNominales(GerenteAdjetivosComoNominales g1);

    public abstract void setGerenteIrrAdjetivosEnteros(GerenteIrrAdjetivosEnteros k1);

}