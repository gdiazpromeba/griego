package com.kalos.analisismorfologico.negocio;import java.util.ArrayList;import java.util.Arrays;import java.util.GregorianCalendar;import java.util.HashSet;import java.util.Iterator;import java.util.List;import java.util.Set;import com.kalos.beans.PreposicionBean;import com.kalos.beans.ResultadoUniversal;import com.kalos.datos.gerentes.GerentePreposiciones;import com.kalos.enumeraciones.TipoPalabra;import com.kalos.operaciones.AACacheable;import com.kalos.operaciones.OpPalabras;import org.apache.log4j.Logger;import org.springframework.context.ApplicationContext;import org.springframework.context.ApplicationContextAware;public class AMPreposiciones implements AnalizadorMorfologico, ApplicationContextAware {	private GerentePreposiciones gerentePreposiciones;	Logger logger = Logger.getLogger(getClass().getName());	private boolean cargo = false;	private ApplicationContext applicationContext;	private List<String> S;	public void setApplicationContext(ApplicationContext paramApplicationContext) {		this.applicationContext = paramApplicationContext;	}	private void paso1() {		if (!this.cargo) {			this.gerentePreposiciones = (GerentePreposiciones) this.applicationContext.getBean("gerentePreposiciones");			this.cargo = true;			this.S = new ArrayList<String>();			List<PreposicionBean> localList = this.gerentePreposiciones.seleccionaPreposicionesNoAcentuables();			Iterator<PreposicionBean> localIterator = localList.iterator();			while (localIterator.hasNext()) {				PreposicionBean prep = localIterator.next();				String str = prep.getFormaDiccionario();				str = OpPalabras.strBetaACompleto(str);				this.S.add(str);			}		}	}	/**	 * en este caso el parámetro validaContraFlexion no se usa, dado que las preposiciones no se flexionan	 */	public  Set<ResultadoUniversal> buscaCanonica(String[] formas, AACacheable aaCache, boolean validaContraFlexion, boolean debug) {		paso1();		Set<ResultadoUniversal> resultados = new HashSet<>();		long tiempoInicial = System.currentTimeMillis();		Set<String> setFormas = new HashSet<String>(Arrays.asList(formas));		paso2(setFormas, resultados);		long tiempoFinal = System.currentTimeMillis();		long lapso = tiempoFinal - tiempoInicial;		if (debug) {			GregorianCalendar tiempo = new GregorianCalendar();			tiempo.setTimeInMillis(lapso);			System.out.println("tardó " + tiempo.get(GregorianCalendar.MINUTE) + " minutos " + tiempo.get(GregorianCalendar.SECOND) + " segundos " + tiempo.get(GregorianCalendar.MILLISECOND) + " milisegundos");		}		return resultados;	}	private void paso2(Set<String> setFormas, Set<ResultadoUniversal> paramSet1) {		List<ResultadoUniversal> resultados = new ArrayList<ResultadoUniversal>();		Iterator<String> localIterator1 = setFormas.iterator();		while (localIterator1.hasNext()) {			String str1 = (String) localIterator1.next();			String str2 = OpPalabras.strCompletoABeta(str1);			List<PreposicionBean> localList = this.gerentePreposiciones.seleccionaPorFormaParaAM(str2);			Iterator<PreposicionBean> localIterator2 = localList.iterator();			while (localIterator2.hasNext()) {				PreposicionBean bean = localIterator2.next();				ResultadoUniversal localj = new ResultadoUniversal(TipoPalabra.Preposicion, bean.getId(), null, null,						bean.getParticularidad(), null, str1, null, null, null, null, null, null, null, null, null,						null, null, null);				resultados.add(localj);			}		}		paramSet1.addAll(resultados);	}	public void setGerentePreposiciones(GerentePreposiciones paramq) {		this.gerentePreposiciones = paramq;	}}