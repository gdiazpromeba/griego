package com.kalos.flexion.declinacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.kalos.beans.DesinSust;
import com.kalos.beans.TipoJerarquico;
import com.kalos.beans.TipoSustantivo;
import com.kalos.bibliotecadatos.JerarquiaBeans;
import com.kalos.datos.adaptadores.AdaptadorGerenteDesinSust;
import com.kalos.datos.gerentes.GerenteDesinSust;
import com.kalos.datos.gerentes.GerenteTiposSustantivo;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.AcentoConcuerda;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.OrigenTema;
import com.kalos.enumeraciones.PosicionConcuerda;
import com.kalos.operaciones.OpPalabras;

// Referenced classes of package kalos.H.C:
//            J

public class ManejaDesinenciasSustantivo {

    private GerenteDesinSust gerenteDesinSust;
    private GerenteTiposSustantivo gerenteTiposSustantivo;
    private List<DesinenciaSustantivo> desinencias[][][];
    private Set<DesinenciaSustantivo> setDesinenciasSustantivo;

    public ManejaDesinenciasSustantivo() {
	setDesinenciasSustantivo = new HashSet<DesinenciaSustantivo>();
    }

    public DesinenciaSustantivo[] getDesinencia(int i, Numero n1, Caso a1) {
	int j = n1.valorEntero();
	int k = a1.valorEntero();
	List<DesinenciaSustantivo> list = desinencias[i][j][k];
	if (list != null) {
	    return (DesinenciaSustantivo[]) desinencias[i][j][k].toArray(new DesinenciaSustantivo[0]);
	} else {
	    StringBuffer stringbuffer = new StringBuffer("la desinencia pedida no existe:");
	    stringbuffer.append((new StringBuilder()).append("  tipoSustantivo=").append(i).append("\n").toString());
	    stringbuffer.append((new StringBuilder()).append("  número=").append(n1).append("\n").toString());
	    stringbuffer.append((new StringBuilder()).append("  caso=").append(a1).append("\n").toString());
	    throw new RuntimeException(stringbuffer.toString());
	}
    }

    private DesinenciaSustantivo cargaIndividual(String forma, Acento acento, int i, Contraccion contraccion,
	    OrigenTema origenTema, int j, int k) {
	DesinenciaSustantivo desinenciaSustantivo = new DesinenciaSustantivo(forma, acento, i, contraccion, origenTema,
		j, k);
	if (setDesinenciasSustantivo.contains(desinenciaSustantivo)) {
	    for (Iterator<DesinenciaSustantivo> iterator = setDesinenciasSustantivo.iterator(); iterator.hasNext();) {
		DesinenciaSustantivo j2 = iterator.next();
		if (j2.equals(desinenciaSustantivo)) {
		    return j2;
		}
	    }

	    return null;
	} else {
	    setDesinenciasSustantivo.add(desinenciaSustantivo);
	    return desinenciaSustantivo;
	}
    }

    private static String[] idsTipos(List<TipoJerarquico> list) {
	List<String> arraylist = new ArrayList<String>();
	TipoJerarquico tij;
	for (Iterator<TipoJerarquico> iterator = list.iterator(); iterator.hasNext(); arraylist.add(tij.getId())) {
	    tij = iterator.next();
	}

	return (String[]) arraylist.toArray(new String[0]);
    }

    @SuppressWarnings("unchecked")
    public void cargaDesinencias(boolean flag) {
	desinencias = new ArrayList[133][4][6];
	List<TipoSustantivo> list = gerenteTiposSustantivo.getTodos();
	JerarquiaBeans<TipoJerarquico> a1 = new JerarquiaBeans<TipoJerarquico>(list, "padreId");
	List<TipoJerarquico> list1 = a1.getHojas();
	for (Iterator<TipoJerarquico> iterator = list1.iterator(); iterator.hasNext();) {
	    TipoSustantivo e1 = (TipoSustantivo) iterator.next();
	    String s = e1.getId();
	    int i = e1.getValorEntero();
	    List<TipoJerarquico> regYAncestros = a1.getRegistroYAncestros(s);
	    String idsTipos[] = idsTipos(regYAncestros);
	    AdaptadorGerenteDesinSust agds = new AdaptadorGerenteDesinSust(gerenteDesinSust);
	    agds.seleccionaPorTipos(idsTipos, flag);
	    List<DesinSust> arraylist = new ArrayList<DesinSust>(agds.getBeans(agds.getTodosLosId()));
	    Iterator<DesinSust> iterator1 = arraylist.iterator();
	    while (iterator1.hasNext()) {
		DesinSust a2 = iterator1.next();
		int k = a2.getCaso().valorEntero();
		int l = a2.getNumero().valorEntero();
		List<DesinenciaSustantivo> lstDes = desinencias[i][l][k];
		if (lstDes == null) {
		    lstDes = new ArrayList<DesinenciaSustantivo>();
		    desinencias[i][l][k] = lstDes;
		}
		lstDes.add(cargaIndividual(OpPalabras.strBetaACompleto(a2.getDesinencia()), a2.getAcento(),
			a2.getSilaba(), a2.getContraccion(), a2.getOrigenTema(),
			a2.getPosicionConcuerda().valorEntero(),
			a2.getAcentoConcuerda().valorEntero()));
	    }
	}

    }

    public GerenteDesinSust getGerenteDesinSust() {
	return gerenteDesinSust;
    }

    public void setGerenteDesinSust(GerenteDesinSust n1) {
	gerenteDesinSust = n1;
	cargaDesinencias(false);
    }

    public GerenteTiposSustantivo getGerenteTiposSustantivo() {
	return gerenteTiposSustantivo;
    }

    public void setGerenteTiposSustantivo(GerenteTiposSustantivo h1) {
	gerenteTiposSustantivo = h1;
    }
}
