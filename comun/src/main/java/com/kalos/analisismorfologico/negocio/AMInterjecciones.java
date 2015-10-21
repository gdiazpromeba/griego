package com.kalos.analisismorfologico.negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.kalos.beans.InterjeccionBean;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.datos.gerentes.GerenteInterjecciones;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.OpPalabras;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AMInterjecciones implements AnalizadorMorfologico, ApplicationContextAware {
	private GerenteInterjecciones gerenteInterjecciones;
	Logger logger = Logger.getLogger(getClass().getName());
	private boolean Ç = false;
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext paramApplicationContext) {
		this.applicationContext = paramApplicationContext;
	}

	private void paso1() {
		if (!this.Ç) {
			this.gerenteInterjecciones = (GerenteInterjecciones) this.applicationContext
					.getBean("gerenteInterjecciones");
			this.Ç = true;
		}
	}

	public Set<ResultadoUniversal>  buscaCanonica(String[] formas,  AACacheable paramB, boolean paramBoolean1, boolean paramBoolean2) {
		paso1();
		Set<ResultadoUniversal> resultados = new HashSet<>();
		long l1 = System.currentTimeMillis();
		Set<String> localHashSet = new HashSet<String>(Arrays.asList(formas));
		paso2(localHashSet, resultados);
		long l2 = System.currentTimeMillis();
		long l3 = l2 - l1;
		if (paramBoolean2) {
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			localGregorianCalendar.setTimeInMillis(l3);
			System.out.println("tardó " + localGregorianCalendar.get(12) + " minutos " + localGregorianCalendar.get(13)
					+ " segundos " + localGregorianCalendar.get(14) + " milisegundos");
		}
		return resultados;
	}

	private void paso2(Set<String> paramSet, Set<ResultadoUniversal> resultados) {
		List<ResultadoUniversal> localArrayList = new ArrayList<ResultadoUniversal>();
		Iterator<String> localIterator1 = paramSet.iterator();
		while (localIterator1.hasNext()) {
			String str1 = (String) localIterator1.next();
			String str2 = OpPalabras.strCompletoABeta(str1);
			List<InterjeccionBean> localList = this.gerenteInterjecciones.seleccionaPorFormaSinSignificado(str2);
			Iterator<InterjeccionBean> localIterator2 = localList.iterator();
			while (localIterator2.hasNext()) {
				InterjeccionBean localY = localIterator2.next();
				ResultadoUniversal localj = new ResultadoUniversal(TipoPalabra.Interjeccion, localY.getId(), null, null,
						localY.getPartic(), null, str1, null, null, null, null, null, null, null, null, null, null,
						null, null);
				localArrayList.add(localj);
			}
		}
		resultados.addAll(localArrayList);
	}

	public void setGerenteInterjecciones(GerenteInterjecciones paramjA) {
		this.gerenteInterjecciones = paramjA;
	}
}
