package com.kalos.datos.adaptadores;

import java.util.List;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.TipoJerarquico;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.datos.gerentes.GerenteAdjetivos;

import org.apache.log4j.Logger;

public class AdaptadorGerenteAdjetivos implements FuenteDatosCacheable {
    private List<String> Z;
    private GerenteAdjetivos gerenteAdjetivos;
    Logger Y = Logger.getLogger(getClass().getName());

    public AdaptadorGerenteAdjetivos(GerenteAdjetivos paramf) {
	this.gerenteAdjetivos = paramf;
    }

    public void seleccionaPorLetraInicial(String paramString) {
	this.Z = this.gerenteAdjetivos.seleccionaPorLetra(paramString);
    }

    public void seleccionaPorCanonica(String paramString) {
	this.Z = this.gerenteAdjetivos.seleccionaPorCanonica(paramString);
    }

    public void seleccionPorTipos(List<TipoJerarquico> paramList) {
	this.Z = this.gerenteAdjetivos.seleccionaPorTipos(paramList);
    }

    public List<AdjetivoBean> getBeans(List<String> paramList) {
	List<AdjetivoBean> localList = this.gerenteAdjetivos.getBeans(paramList);
	return localList;
    }

    public int getLongitudTotal() {
	return this.Z.size();
    }

    public List<String> getTodosLosId() {
	return this.Z;
    }
}
