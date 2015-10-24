package com.kalos.analisismorfologico.negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.kalos.beans.ConjuncionBean;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.datos.gerentes.GerenteConjunciones;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.OpPalabras;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AMConjunciones implements AnalizadorMorfologico, ApplicationContextAware {
	private GerenteConjunciones gerenteConjunciones;
	Logger logger = Logger.getLogger(getClass().getName());
	private boolean e = false;
	private ApplicationContext c;
	private List<String> g;

	public void setApplicationContext(ApplicationContext paramApplicationContext) {
		this.c = paramApplicationContext;
	}

	private void paso1() {
		if (!this.e) {
			this.gerenteConjunciones = (GerenteConjunciones) this.c.getBean("gerenteConjunciones");
			this.e = true;
			this.g = new ArrayList<String>();
			List<ConjuncionBean> localList = this.gerenteConjunciones.seleccionaConjuncionesNoAcentuables();
			Iterator<ConjuncionBean> localIterator = localList.iterator();
			while (localIterator.hasNext()) {
				ConjuncionBean localE = localIterator.next();
				String str = localE.getForma();
				str = OpPalabras.strBetaACompleto(str);
				this.g.add(str);
			}
		}
	}

	public Set<ResultadoUniversal> buscaCanonica(String[] paramArrayOfString, AACacheable cacheAA, boolean paramBoolean1, boolean paramBoolean2) {
		paso1();
		Set<ResultadoUniversal> resultados = new HashSet<>();
		long tiempo = System.currentTimeMillis();
		Set<String> localHashSet = new HashSet<String>(Arrays.asList(paramArrayOfString));
		paso2(localHashSet, resultados);
		long tiempo2 = System.currentTimeMillis();
		long tiempo3 = tiempo2 - tiempo;
		if (paramBoolean2) {
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			localGregorianCalendar.setTimeInMillis(tiempo3);
			System.out.println("tardó " + localGregorianCalendar.get(12) + " minutos " + localGregorianCalendar.get(13)
					+ " segundos " + localGregorianCalendar.get(14) + " milisegundos");
		}
		return resultados;
	}

	private void paso2(Set<String> formas, Set<ResultadoUniversal> resultados) {
		List<ResultadoUniversal> localArrayList = new ArrayList<ResultadoUniversal>();
		Iterator<String> localIterator1 = formas.iterator();
		while (localIterator1.hasNext()) {
			String str1 = localIterator1.next();
			String str2 = OpPalabras.strCompletoABeta(str1);
			List<ConjuncionBean> localList = this.gerenteConjunciones.seleccionaPorFormaParaAM(str2);
			Iterator<ConjuncionBean> localIterator2 = localList.iterator();
			while (localIterator2.hasNext()) {
				ConjuncionBean bean = localIterator2.next();
				ResultadoUniversal localj = new ResultadoUniversal(TipoPalabra.Conjuncion, bean.getId(), null, null,
						bean.getPartic(), null, str1, null, null, null, null, null, null, null, null, null, null, null,
						null);
				localArrayList.add(localj);
			}
		}
		resultados.addAll(localArrayList);
	}

	public void setGerenteConjunciones(GerenteConjunciones paramMA) {
		this.gerenteConjunciones = paramMA;
	}
}
