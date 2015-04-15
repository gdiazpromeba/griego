package com.kalos.datos.gerentes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.kalos.beans.IrrSustantivoBean;
import com.kalos.beans.SustantivoBean;
import com.kalos.beans.TipoJerarquico;
import com.kalos.datos.dao.DiccionarioDAO;
import com.kalos.datos.dao.IrrSustantivosDAO;
import com.kalos.datos.dao.SustantivosDAO;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.comparadores.ComparadorBeansGriegos;

public class GerenteSustantivosImpl implements GerenteSustantivos {
    private SustantivosDAO sustantivosDAO;
    private IrrSustantivosDAO irrSustantivosDAO;
    private DiccionarioDAO diccionarioDAO;
    private GerenteSignificados gerenteSignificados;

    public List<SustantivoBean> seleccionaPorNominativoNeutralizado(String paramString, LugarSubcadena paramh) {
	List<TipoPalabra> localArrayList1 = new ArrayList<TipoPalabra>();
	localArrayList1.add(TipoPalabra.Sustantivo);
	List<String> localList = this.diccionarioDAO.seleccionaPorNeutralizada(paramString, paramh, localArrayList1);
	ArrayList localArrayList2 = new ArrayList();
	Iterator localIterator = localList.iterator();
	while (localIterator.hasNext()) {
	    String str = (String) localIterator.next();
	    SustantivoBean locali = this.sustantivosDAO.getInidvidual(str);
	    localArrayList2.add(locali);
	}
	return localArrayList2;
    }

    public List<SustantivoBean> seleccionaPorNominativoParaAM(String paramString) {
	return this.sustantivosDAO.seleccionaPorNominativoParaAM(paramString);
    }

    public List<String> seleccionaPorNominativo(String paramString, LugarSubcadena paramh) {
	return this.sustantivosDAO.seleccionaPorNominativo(paramString, paramh);
    }

    public List<SustantivoBean> seleccionaPorGenitivoParaAM(String paramString) {
	return this.sustantivosDAO.seleccionaPorGenitivoParaAM(paramString);
    }

    public SustantivoBean seleccionaIndividualParaAM(String paramString) {
	return this.sustantivosDAO.seleccionaIndividualParaAM(paramString);
    }

    public List<SustantivoBean> seleccionaInvariables(String paramString) {
	return this.sustantivosDAO.seleccionaInvariables(paramString);
    }

    public List<SustantivoBean> getBeans(List<String> paramList) {
	return this.sustantivosDAO.getRegistros(paramList);
    }

    public List<Particularidad> seleccionaPartics(String paramString) {
	List localArrayList = new ArrayList();
	List<String> localList = this.irrSustantivosDAO.seleccionaPartics(paramString);
	Object localObject = localList.iterator();
	while (((Iterator) localObject).hasNext()) {
	    String str = (String) ((Iterator) localObject).next();
	    localArrayList.add(Particularidad.getEnum(str));
	}
	localObject = this.sustantivosDAO.getInidvidual(paramString);
	if (!localArrayList.contains(((SustantivoBean) localObject).getPartic())) {
	    localArrayList.add(0, ((SustantivoBean) localObject).getPartic());
	}
	return localArrayList;
    }

    public List<IrrSustantivoBean> seleccionaIrregularidades(String paramString) {
	List<IrrSustantivoBean> localList = this.irrSustantivosDAO.seleccionaPorId(paramString);
	return localList;
    }

    public void insertaSustantivo(SustantivoBean parami) {
	this.sustantivosDAO.inserta(parami);
	this.gerenteSignificados.refresca(parami);
    }

    public void actualiza(SustantivoBean parami) {
	this.sustantivosDAO.modifica(parami);
	this.gerenteSignificados.refresca(parami);
    }

    public void borraSustantivo(String paramString) {
	this.sustantivosDAO.borra(paramString);
	this.gerenteSignificados.borraSignificadosDelReferente(paramString);
    }

    public SustantivoBean seleccionaUno(String paramString) {
	SustantivoBean locali = this.sustantivosDAO.getInidvidual(paramString);
	return locali;
    }

    public List<String> seleccionaPorTipos(List<TipoJerarquico> paramList) {
	Integer[] arrayOfInteger = new Integer[paramList.size()];
	for (int i = 0; i < arrayOfInteger.length; i++) {
	    TipoJerarquico locale = paramList.get(i);
	    arrayOfInteger[i] = Integer.valueOf(locale.getValorEntero());
	}
	List<String> localList = this.sustantivosDAO.getPorTipos(arrayOfInteger);
	return localList;
    }

    public List<String> seleccionaPorTipos(Integer[] paramArrayOfInteger) {
	List localList = this.sustantivosDAO.getPorTipos(paramArrayOfInteger);
	return localList;
    }

    public List<String> seleccionaPorLetra(String paramString) {
	List localList = this.sustantivosDAO.getPorLetra(paramString);
	return localList;
    }

    public IrrSustantivosDAO getIrrSustantivosDAO() {
	return this.irrSustantivosDAO;
    }

    public void setIrrSustantivosDAO(IrrSustantivosDAO paramv) {
	this.irrSustantivosDAO = paramv;
    }

    public SustantivosDAO getSustantivosDAO() {
	return this.sustantivosDAO;
    }

    public void setSustantivosDAO(SustantivosDAO paramA) {
	this.sustantivosDAO = paramA;
    }

    public DiccionarioDAO getDiccionarioDAO() {
	return this.diccionarioDAO;
    }

    public void setDiccionarioDAO(DiccionarioDAO paramu) {
	this.diccionarioDAO = paramu;
    }

    public GerenteSignificados getGerenteSignificados() {
	return this.gerenteSignificados;
    }

    public void setGerenteSignificados(GerenteSignificados paramn) {
	this.gerenteSignificados = paramn;
    }
}
