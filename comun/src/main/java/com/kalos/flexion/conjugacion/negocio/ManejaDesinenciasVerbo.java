package com.kalos.flexion.conjugacion.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kalos.beans.DesinVerbo;
import com.kalos.datos.gerentes.GerenteDesinVerbos;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.Voz;
import com.kalos.operaciones.OpPalabras;

public class ManejaDesinenciasVerbo {
    @SuppressWarnings("unchecked")
    private List<Desinencia>[][][][][][] desinencias = new List[7][4][6][7][2][32];

    public Desinencia[] getDesinencias(int paramInt, Voz voz, Modo modo, Tiempo tiempo, FuerteDebil fuerte,
	    Persona persona) {
	int i = voz.valorEntero();
	int j = modo.valorEntero();
	int k = tiempo.valorEntero();
	int m = FuerteDebil.getInt(fuerte);
	int n = persona.valorEntero();
	List<Desinencia> localList = this.desinencias[paramInt][i][j][k][m][n];
	if (localList == null) {
	    return null;
	}
	try {
	    return (Desinencia[]) localList.toArray(new Desinencia[0]);
	} catch (Exception localException) {
	    System.out.println("error al tratar de obtener la desinencia ***********");
	    System.out.println("juego=" + paramInt);
	    System.out.println("voz=" + voz);
	    System.out.println("modo=" + modo);
	    System.out.println("tiempo=" + tiempo);
	    System.out.println("persona=" + persona);
	    System.out.println("***************************************************");
	    throw new RuntimeException("error en ManejaDesinencias.getDesinencias()");
	}
    }

    private void puebla(int juego, Voz voz, Modo modo, Tiempo tiempo, FuerteDebil fuerte, Persona persona, String paramString, int paramInt2,
	    Acento acento) {
	int i = voz.valorEntero();
	int j = modo.valorEntero();
	int k = tiempo.valorEntero();
	int m = FuerteDebil.getInt(fuerte);
	int n = persona.valorEntero();
	try {
	    if (this.desinencias[juego][i][j][k][m][n] == null) {
		this.desinencias[juego][i][j][k][m][n] = new ArrayList<Desinencia>();
	    }
	    this.desinencias[juego][i][j][k][m][n].add(new Desinencia(paramString, paramInt2, acento, juego));
	} catch (Exception localException) {
	    System.out.println("error al tratar de obtener la desinencia ***********");
	    System.out.println("juego=" + juego);
	    System.out.println("voz=" + voz);
	    System.out.println("modo=" + modo);
	    System.out.println("tiempo=" + tiempo);
	    System.out.println("persona=" + persona);
	    System.out.println("***************************************************");
	    throw new RuntimeException("error en ManejaDesinencias.setDesinencias()");
	}
    }

    public ManejaDesinenciasVerbo(boolean todas, GerenteDesinVerbos gerDesverbos) {
	List<DesinVerbo> localList;
	if (!todas) {
	    localList = gerDesverbos.seleccionaRestringidas();
	} else {
	    localList = gerDesverbos.seleccionaTodas();
	}
	Iterator<DesinVerbo> localIterator = localList.iterator();
	while (localIterator.hasNext()) {
	    DesinVerbo localk =  localIterator.next();
	    puebla(localk.getJuego(), localk.getVoz(), localk.getModo(), localk.getTiempo(), localk.getFuerte(),
		    localk.getPersona(), OpPalabras.strBetaACompleto(localk.getDesinencia()), localk.getSilaba(),
		    localk.getAcento());
	}
    }


}
