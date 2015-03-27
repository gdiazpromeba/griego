package com.kalos.datos.adaptadores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kalos.beans.IrrSustantivoBean;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.datos.gerentes.GerenteIrrSustantivos;
import com.kalos.enumeraciones.Particularidad;

import org.apache.log4j.Logger;

public class AdaptadorGerenteIrrSustantivos implements FuenteDatosCacheable {
    private List<String> h;
    private GerenteIrrSustantivos gerenteIrrSustantivos;
    Logger g = Logger.getLogger(getClass().getName());

    public AdaptadorGerenteIrrSustantivos(GerenteIrrSustantivos paramSA) {
	this.gerenteIrrSustantivos = paramSA;
    }

    public void seleccionPorSustantivo(String paramString) {
	List<IrrSustantivoBean> localList = this.gerenteIrrSustantivos.seleccionaPorSustantivo(paramString);
	List<String> localArrayList = new ArrayList<String>();
	Iterator<IrrSustantivoBean> localIterator = localList.iterator();
	while (localIterator.hasNext()) {
	    IrrSustantivoBean localU = localIterator.next();
	    localArrayList.add(localU.getId());
	}
	this.h = localArrayList;
    }

    public void seleccionPorSustantivoYPartic(String paramString, Particularidad paramX) {
	List<IrrSustantivoBean> localList = this.gerenteIrrSustantivos.seleccionaPorSustantivoYPartic(paramString, paramX);
	List<String> localArrayList = new ArrayList<String>();
	Iterator<IrrSustantivoBean> localIterator = localList.iterator();
	while (localIterator.hasNext()) {
	    IrrSustantivoBean localU =  localIterator.next();
	    localArrayList.add(localU.getId());
	}
	this.h = localArrayList;
    }

    public List<IrrSustantivoBean> getBeans(List<String> paramList) {
	List<IrrSustantivoBean> localList = this.gerenteIrrSustantivos.getRegistros(paramList);
	return localList;
    }

    public int getLongitudTotal() {
	return this.h.size();
    }

    public List<String> getTodosLosId() {
	return this.h;
    }
}
