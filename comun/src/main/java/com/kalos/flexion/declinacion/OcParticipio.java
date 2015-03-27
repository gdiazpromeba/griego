package com.kalos.flexion.declinacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Voz;
import com.kalos.operaciones.OpPalabras;

import org.apache.log4j.Logger;

public class OcParticipio {
    @SuppressWarnings("unchecked")
    public List<String>[][][][][][] formas = new List[4][6][2][5][6][4];
    private Logger logger = Logger.getLogger(getClass().getName());
    @SuppressWarnings("unchecked")
    private List<Integer>[][][][][] indices = new List[4][6][2][5][4];

    public void inicializarAVacia() {
	for (Voz localZ : Voz.values()) {
	    for (Aspecto localk : Aspecto.values()) {
		for (FuerteDebil localP : FuerteDebil.values()) {
		    for (Genero localA : Genero.getMFN()) {
			for (Caso locala : Caso.values()) {
			    for (Numero localn : Numero.values()) {
				int i8 = Caso.getInt(locala);
				int i9 = Genero.getInt(localA);
				int i10 = Voz.getInt(localZ);
				int i11 = Aspecto.getInt(localk);
				int i12 = FuerteDebil.getInt(localP);
				int i13 = Numero.getInt(localn);
				this.formas[i10][i11][i12][i9][i8][i13] = null;
			    }
			}
		    }
		}
	    }
	}
    }

    public List<String> getForm(Voz voz, Aspecto aspecto, FuerteDebil fuerte, Genero paramA, Caso caso,
	    Numero paramn) {
	int i = Caso.getInt(caso);
	int j = Genero.getInt(paramA);
	int k = Voz.getInt(voz);
	int m = Aspecto.getInt(aspecto);
	int n = FuerteDebil.getInt(fuerte);
	int i1 = Numero.getInt(paramn);
	return this.formas[k][m][n][j][i][i1];
    }

    public String getFormaIndividual(Voz voz, Aspecto aspecto, FuerteDebil fuerte, Genero paramA, Caso caso,
	    Numero paramn, int paramInt) {
	int i = Caso.getInt(caso);
	int j = Genero.getInt(paramA);
	int k = Voz.getInt(voz);
	int m = Aspecto.getInt(aspecto);
	int n = FuerteDebil.getInt(fuerte);
	int i1 = Numero.getInt(paramn);
	List<String> localList = this.formas[k][m][n][j][i][i1];
	if ((localList != null) && (paramInt < localList.size())) {
	    return (String) localList.get(paramInt);
	}
	return null;
    }

    public List<String> getForma(Voz voz, Aspecto aspecto, FuerteDebil fuerte, Genero paramA, Caso caso,
	    Numero paramn) {
	int i = Caso.getInt(caso);
	int j = Genero.getInt(paramA);
	int k = Voz.getInt(voz);
	int m = Aspecto.getInt(aspecto);
	int n = FuerteDebil.getInt(fuerte);
	int i1 = Numero.getInt(paramn);
	return this.formas[k][m][n][j][i][i1];
    }

    public void addFormaIndividual(Voz voz, Aspecto aspecto, FuerteDebil fuerte, Genero paramA, Caso caso,
	    Numero paramn, String paramString) {
	int i = Caso.getInt(caso);
	int j = Genero.getInt(paramA);
	int k = Voz.getInt(voz);
	int m = Aspecto.getInt(aspecto);
	int n = FuerteDebil.getInt(fuerte);
	int i1 = Numero.getInt(paramn);
	if (this.formas[k][m][n][j][i][i1] == null) {
	    this.formas[k][m][n][j][i][i1] = new ArrayList<String>();
	}
	this.formas[k][m][n][j][i][i1].add(paramString);
    }

    public void setTipoSustantivo(Voz voz, Aspecto aspecto, FuerteDebil fuerte, Genero paramA, Numero paramn,
	    int paramInt, Integer paramInteger) {
	int i = Genero.getInt(paramA);
	int j = Voz.getInt(voz);
	int k = Aspecto.getInt(aspecto);
	int m = FuerteDebil.getInt(fuerte);
	int n = Numero.getInt(paramn);
	if (this.indices[j][k][m][i][n] == null) {
	    this.indices[j][k][m][i][n] = new ArrayList<Integer>();
	}
	List<Integer> localList = this.indices[j][k][m][i][n];
	int i1 = paramInt - (localList.size() - 1);
	for (int i2 = 0; i2 < i1; i2++) {
	    localList.add(null);
	}
	localList.set(paramInt, paramInteger);
    }

    public Integer getTipoSustantivo(Voz voz, Aspecto aspecto, FuerteDebil fuerte, Genero paramA, Numero paramn,
	    int paramInt) {
	int i = Genero.getInt(paramA);
	int j = Voz.getInt(voz);
	int k = Aspecto.getInt(aspecto);
	int m = FuerteDebil.getInt(fuerte);
	int n = Numero.getInt(paramn);
	List<Integer> localList = this.indices[j][k][m][i][n];
	if (localList == null) {
	    this.indices[j][k][m][i][n] = new ArrayList<Integer>();
	    return null;
	}
	if (localList.size() == 0) {
	    return null;
	}
	return (Integer) localList.get(paramInt);
    }

    public void setFormaIndividual(Voz voz, Aspecto aspecto, FuerteDebil fuerte, Genero paramA, Caso caso,
	    Numero paramn, int paramInt, String paramString) {
	int i = Caso.getInt(caso);
	int j = Genero.getInt(paramA);
	int k = Voz.getInt(voz);
	int m = Aspecto.getInt(aspecto);
	int n = FuerteDebil.getInt(fuerte);
	int i1 = Numero.getInt(paramn);
	try {
	    if (this.formas[k][m][n][j][i][i1] == null) {
		this.formas[k][m][n][j][i][i1] = new ArrayList<String>();
	    }
	} catch (Exception localException) {
	    this.logger.error("voz=" + k + " aspecto=" + m + " fuerte=" + n + " genero=" + j + " caso=" + i + " numero="
		    + i1, localException);
	}
	List<String> localList = this.formas[k][m][n][j][i][i1];
	int i2 = paramInt - (localList.size() - 1);
	for (int i3 = 0; i3 < i2; i3++) {
	    localList.add(null);
	}
	localList.set(paramInt, paramString);
    }

    public int cantidadDeFormas(Voz voz, Aspecto aspecto, FuerteDebil fuerte, Genero paramA, Caso caso,
	    Numero paramn) {
	int i = Caso.getInt(caso);
	int j = Genero.getInt(paramA);
	int k = Voz.getInt(voz);
	int m = Aspecto.getInt(aspecto);
	int n = FuerteDebil.getInt(fuerte);
	int i1 = Numero.getInt(paramn);
	List<String> localList = this.formas[k][m][n][j][i][i1];
	if (localList != null) {
	    return localList.size();
	}
	return 0;
    }

    public String dump() {
	int i = 0;
	StringBuffer localStringBuffer1 = new StringBuffer(1000);
	for (Voz localZ : Voz.values()) {
	    for (Aspecto localk : Aspecto.values()) {
		for (FuerteDebil localP : FuerteDebil.values()) {
		    for (Numero localn : Numero.values()) {
			for (Genero localA : Genero.values()) {
			    for (Caso locala : Caso.values()) {
				StringBuffer localStringBuffer2 = new StringBuffer();
				localStringBuffer2.append("voz=" + localZ + " aspecto=" + localk + " fuerte=" + localP
					+ " numero=" + localn + "\n");
				List<String> localList = getForm(localZ, localk, localP, localA, locala, localn);
				if ((localList != null) && (localList.size() > 0)) {
				    Iterator<String> localIterator = localList.iterator();
				    while (localIterator.hasNext()) {
					String str = (String) localIterator.next();
					localStringBuffer2.append("  " + OpPalabras.strCompletoABeta(str) + "\n");
					i++;
				    }
				    localStringBuffer1.append(localStringBuffer2);
				}
			    }
			}
		    }
		}
	    }
	}
	localStringBuffer1.append("cantidad formas=" + i);
	return localStringBuffer1.toString();
    }
}
