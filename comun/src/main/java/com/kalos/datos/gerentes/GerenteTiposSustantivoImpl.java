// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.gerentes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kalos.beans.TipoJerarquico;
import com.kalos.beans.TipoSustantivo;
import com.kalos.bibliotecadatos.JerarquiaBeans;
import com.kalos.datos.dao.TiposSustantivoDAO;

// Referenced classes of package kalos.E.E:
//            h

public class GerenteTiposSustantivoImpl implements GerenteTiposSustantivo {

    public List<TipoSustantivo> getTodos() {
	List<TipoSustantivo> list = tipoSustantivoDAO.getTodos();
	return list;
    }

    public boolean esPluralizado(int i) {
	if (mapaTs == null) {
	    mapaTs = new HashMap<Integer, TipoSustantivo>();
	    List<TipoSustantivo> list = tipoSustantivoDAO.getTodos();
	    Iterator<TipoSustantivo> iterator = list.iterator();
	    do {
		if (!iterator.hasNext())
		    break;
		TipoSustantivo ts =  iterator.next();
		if (ts.isPluralizado())
		    mapaTs.put(Integer.valueOf(ts.getValorEntero()), ts);
	    } while (true);
	}
	return mapaTs.keySet().contains(Integer.valueOf(i));
    }

    public List<TipoJerarquico> getTiposHoja(String s) {
	JerarquiaBeans<TipoJerarquico> a = new JerarquiaBeans<TipoJerarquico>(getTodos(), "padreId");
	return a.getHojas(s);
    }

    public Map<Integer, String> getMapaTiposID() {
	List<TipoSustantivo> list = tipoSustantivoDAO.getTodos();
	Map<Integer, String> hashmap = new HashMap<Integer, String>();
	TipoSustantivo o1;
	for (Iterator<TipoSustantivo> iterator = list.iterator(); iterator.hasNext(); hashmap.put(Integer.valueOf(o1.getValorEntero()),
		o1.getId()))
	    o1 =  iterator.next();

	return hashmap;
    }

    public TipoSustantivo seleccionIndividual(String s) {
	TipoSustantivo o1 = tipoSustantivoDAO.seleccionaIndividual(s);
	return o1;
    }

    public TipoSustantivo seleccionIndividual(int i) {
	TipoSustantivo o1 = tipoSustantivoDAO.seleccionaIndividual(i);
	return o1;
    }

    public TiposSustantivoDAO getTiposSustantivoDAO() {
	return tipoSustantivoDAO;
    }

    public void setTiposSustantivoDAO(TiposSustantivoDAO o1) {
	tipoSustantivoDAO = o1;
    }

    private TiposSustantivoDAO tipoSustantivoDAO;
    private Map<Integer, TipoSustantivo> mapaTs;
}
