package com.kalos.analisismorfologico.negocio;


/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.kalos.beans.IrrVerboIndividual;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.TermRegVerbal;
import com.kalos.beans.TermRegVerbo;
import com.kalos.beans.VerboBean;
import com.kalos.datos.gerentes.GerenteIrrVerbosIndividuales;
import com.kalos.datos.gerentes.GerenteTermRegVerbo;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.enumeraciones.Voz;
import com.kalos.flexion.conjugacion.Verbos;
import com.kalos.flexion.conjugacion.negocio.Ocurrencia;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.AnalisisAcento;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;

//import org.apache.log4j.Logger;

public class AMVerbos implements AnalizadorMorfologico{
	
	@Autowired
	private AMUtil<TermRegVerbo> amUtil;
	@Autowired
	private AMVerbal<TermRegVerbo> amVerbal;
	private GerenteTermRegVerbo gerenteTermRegVerbo;
	private Verbos verbos;
	private GerenteVerbos gerenteVerbos;
	
	

	//private Logger logger=Logger.getLogger(this.getClass().getName());
	

	/**
	 * encuentra la o las formas canónicas de las entradas dadas
	 * @param entradas          las entradas
	 * @param setResultado      el conjunto resultado
	 * @param cacheAA           un caché de palabras para el análisis de acento
	 * @param validarConFlexion   indica si, después de haber encontrado formas canónicas, debo flexionarlas para 
	 *                            comprobar si las entradas están ahí. Esto es necesario ya que el análisis morfológico suele
	 *                            generar, inevitablemente, entradas espurias.
	 *                            En producción este valor debe ser verdadero. Para un test rápido o individual, puede ser falso.
	 * @param debug             si debug es verdadero, imprimer mensajes de debug a System.out
	 * @return
	 */
	public Set<ResultadoUniversal>  buscaCanonica(String[] entradas,  AACacheable cacheAA, boolean validarConFlexion, boolean debug)   {
		long tiempoInicial=System.currentTimeMillis();
		Set<TermRegVerbo> setPaso1=new LinkedHashSet<TermRegVerbo>();
		Set<TermRegVerbo> setPaso2=new LinkedHashSet<TermRegVerbo>();
		Set<TermRegVerbo> setPaso2_5=new LinkedHashSet<>(); 
		Set<ObjYDest<TermRegVerbo>> setPaso3=new LinkedHashSet<>();
		Set<TermRegVerbo> setPaso4=new LinkedHashSet<TermRegVerbo>();
		HashMap<Object[], TemaConPreps[]> cacheExtraccionPrefijos=new HashMap<Object[], TemaConPreps[]>();
		Map<TemaConPreps, HashSet<TermRegVerbo>> mapTemasPropuestos=new HashMap<TemaConPreps, HashSet<TermRegVerbo>>();
		//entradas=OpPalabras.filtraImposibles(entradas);
		//agrego las entradas al treeModel
		Set<String> setEntradas=new HashSet<String>(Arrays.asList(entradas));
		
		List<ObjYDest<TermRegVerbo>> temIrr=new ArrayList<>();
		//lista que contiene resultados producto de buscar en irregularidades
		List<TermRegVerbo> resultadosIrr=new ArrayList<>();
		
		//utilizar la función "conservasolo"  para dejar una determinada rama del árbol solamente
		paso1(setEntradas, setPaso1, cacheExtraccionPrefijos, cacheAA, debug);             //obtención de juegos voz-modo-tiempo-persona posibles según terminación
		//amUtil.conservaSolo(setPaso1, new String[]{"voz", "modo", "tiempo", "persona"}, new Object[]{Voz.Activa, Modo.Indicativo, Tiempo.Perfecto, Persona._2ps});
		amVerbal.extiendeTipos(setPaso1, setPaso2, debug);   //expansión de los "nodos" de paso 1, según  pertenezcan a ciertas terminaciones
//		amUtil.conservaSolo(setPaso2, new String[]{"tiempo", "tipoDesinencia"}, new Object[]{Tiempo.Pluscuamperfecto, 10 });
		amVerbal.averiguaPreposiciones(setPaso2, setPaso2_5, ExtractorPrefijos.TODOS_LOS_NODOS,cacheExtraccionPrefijos, debug); //averiguación de preposiciones
		amUtil.incorporaADestransformar(setPaso2_5, debug);
		amUtil.desTransformacionesTemas(setPaso2_5, setPaso3, temIrr, debug); //des-transformación de aumentos y reduplicaciones
		amVerbal.incorporaTemaPropuestoAReconstruidos(setPaso3, temIrr, debug);
		amVerbal.incorporaTemaPropuestoIrregulares(temIrr, debug);
		
//		amUtil.conservaSolo(setPaso3, new String[]{"FORMA_ORIGINAL"}, new Object[]{OpPalabras.strBetaACompleto("E)/PEISQE")});
		amUtil.vocalUnitivaTemas(setPaso3, temIrr);  //preparación de temas para la búsqueda temprana
		amVerbal.aplicaEncuentraTemasTemprano(setPaso3, resultadosIrr, temIrr, debug);  //búsqueda temprana de temas
		amVerbal.envoltorioRestauraFormas(setPaso3, setPaso4, cacheAA, debug);   //unión de temas y desinencias de canónica
		amUtil.agrupaTemasEnSet(setPaso4, mapTemasPropuestos, debug);  //conversión del resultado en un set con el tema propuesto como clave
        setPaso1=null; setPaso2=null; setPaso2_5=null; setPaso3=null; setPaso4=null; temIrr=null;
        
        Set<ResultadoUniversal> resultados = new HashSet<>();
        amVerbal.buscaReconstruidaEnTablas(resultados, mapTemasPropuestos, false, false, debug); //comprobación de las formas de diccionario obtenidas contra la base de datos
        
		amVerbal.buscaReconstruidaEnTablas(resultados, mapTemasPropuestos, true, false, debug);
		amVerbal.comparaConTemasSemirreconstruidos(resultados, resultadosIrr, false, debug);
//		entradas=agregaEntradaMi(entradas);
		buscaIrrIndivConjugacion(resultados, entradas, cacheAA, debug);
		amVerbal.pueblaCanonicasVerbos(resultados);
		if (validarConFlexion){
		  validaConFlexion(resultados);
		}
		
		long tiempoFinal=System.currentTimeMillis();
		long lapsoEnMilis=tiempoFinal-tiempoInicial;
		
		
		
		if (debug){
			GregorianCalendar lapso=new GregorianCalendar();
			lapso.setTimeInMillis(lapsoEnMilis);
			System.out.println("tardó " + lapso.get(Calendar.MINUTE) +  " minutos " +  lapso.get(Calendar.SECOND) + " segundos " +  lapso.get(Calendar.MILLISECOND) + " milisegundos");
		}
		return resultados;
			
	}
	
	
	/**
	 * Hay resultados de los verbos que simplemente no se pueden "podar" hasta no contar con una flexión.
	 * Este método flexiona todas las entradas y poda lo que no se haya encontrado en la flexión
	 * @param resultados
	 */
	private void validaConFlexion(Set<ResultadoUniversal> resultados){
		for (Iterator<ResultadoUniversal> it=resultados.iterator(); it.hasNext();){
			ResultadoUniversal reu=(ResultadoUniversal)it.next();
			VerboBean verbo=gerenteVerbos.seleccionaUno(reu.getIdSimpleOCompuesto());
			Ocurrencia ocVerbo=verbos.conjuga(verbo, reu.getParticularidad());
			if (!ocVerbo.contieneForma(reu.getFormaAccidentada(), reu.getVoz(), reu.getModo(), reu.getTiempo(), reu.getFuerte(), reu.getPersona())){
				it.remove();
				continue;
			}
		}
	}
	
	
	/**
	/**
	 * Paso 1
	 * Entra un set de nodos conteniendo la entradas originales (en formato completo); están colgando del nodo raíz
	 * A cada uno de esos nodos nodos se 
	 * les adjunta un conjunto de nodos conteniendo una o más TermRegVerbos, que son terminaciones posibles
	 * para esa entrada, y se los agrega al conjunto "setOriginal"
	 * 
	 * @param raiz
	 * @param siguiente
	 * @param debug
	 */
	private void paso1(Set<String> entradas, Set<TermRegVerbo> siguiente,  HashMap<Object[], TemaConPreps[]> cacheExtraccionPrefijos,  AACacheable cacheAA, boolean debug){
		StringBuffer sbDebug=new StringBuffer();
		for (String entrada: entradas){
			String entradaBeta=OpPalabras.strCompletoABeta(entrada);
			String entradaBetaDesacentuada=OpPalabras.strCompletoABeta(OpPalabras.desacentuar(entrada));
			List<TermRegVerbo> terminaciones=gerenteTermRegVerbo.seleccionaPorTerminacion(entradaBetaDesacentuada);
		    
			Set<TermRegVerbo>  resultado = terminaciones.stream()
			.filter(
			  trv -> {
					String regex=trv.getRegEx();
					if (regex!=null && !entradaBeta.matches(regex)){
						if (debug){
							sbDebug.append("la terminación del tipo de desinencia " + trv.getTipoDesinencia()+  " es '" + trv.getTerminacion()  + "' y coincide,");
							sbDebug.append(" pero no así la REGEXP "  + trv.getRegEx() + " \n");
						}
						return false;
					}
					return true;
			  } 
			)
			.filter(
			  trv ->{
					if (!amVerbal.silabaAcentoAceptables(entrada, trv, cacheExtraccionPrefijos, cacheAA)){
						if (debug){
							sbDebug.append("la terminación del tipo de desinencia " + trv.getTipoDesinencia() + " es '" + trv.getTerminacion()  + "' y coincide,");
							sbDebug.append(" pero no así los campos sílaba-acento \n");
						}
						return false;
					}
					return true;
			  }
			)
			.map( trv -> {
		      trv.setFormaOriginal(entrada);
		      return trv;
		     })
			.collect(Collectors.toSet());
			
			siguiente.addAll(resultado);

		}
		if (debug){
		  System.out.println("después de la búsqueda de terminaciones (paso 1) *********************");
		  System.out.println("  rechazados ***");
		  System.out.println(sbDebug.toString());
		  System.out.println("  creados ***");
		  amUtil.debugBeans(siguiente, new String[]{});
		}
	}

	










	/**
	 * @return Returns the gerenteTermRegVerbo.
	 */
	public GerenteTermRegVerbo getGerenteTermRegVerbo() {
		return gerenteTermRegVerbo;
	}









	/**
	 * @param gerenteTermRegVerbo The gerenteTermRegVerbo to set.
	 */
	public void setGerenteTermRegVerbo(GerenteTermRegVerbo gerenteTermRegVerbo) {
		this.gerenteTermRegVerbo = gerenteTermRegVerbo;
	}



	/**
	 * @param gerenteVerbos The gerenteVerbos to set.
	 */
	public void setGerenteVerbos(GerenteVerbos gerenteVerbos) {
		this.gerenteVerbos = gerenteVerbos;
	}



	/**
	 * @param verbos The verbos to set.
	 */
	public void setVerbos(Verbos verbos) {
		this.verbos = verbos;
	}
	
	/**
	 * Compruebo las entradas una por una contra la tabla
	 * IRR_VERBOS_INDIVIDUALES (si se llama desde AMVerbos) o contra
	 * IRR_INFINITIVOS (si se llama desde AMInfinitivos) Cualquier hallazgo se
	 * agrega al set de resultado También considero posibles extracciones de
	 * extractorPrefijos
	 * 
	 * @param setResultado
	 * @param lstIrr
	 */
	private void buscaIrrIndivConjugacion (Set<ResultadoUniversal> setResultado, String[] entradas, AACacheable cacheAA, boolean debug) {

		Map<Object[], TemaConPreps[]> cacheExtraccionPreps = new HashMap<Object[], TemaConPreps[]>();

		for (int i = 0; i < entradas.length; i++) {
			int respetarFinales = 0;
//			if (cacheAA.getAnalisisAcento(entradas[i]).actuales.indiceLetraAcentuada > -1)
//				respetarFinales = entradas[i].length()
//						- cacheAA.getAnalisisAcento(entradas[i]).actuales.indiceLetraAcentuada;
			TemaConPreps[] extracciones = extractorPrefijos.averiguaPrefijos(entradas[i], respetarFinales, cacheExtraccionPreps);
			for (int j = 0; j < extracciones.length; j++) {
				//si  el resto no tiene acento (por ejemplo, porque éste caía en al preposición), la acentúo
				String entrada=extracciones[j].resto;
				AnalisisAcento aa=cacheAA.getAnalisisAcento(entrada);
				if (aa.actuales.tipoAcento.equals(Acento.Ninguno)){
				  entrada=OpPalabras.acentua(entrada);
				}
				entrada=OpPalabras.strCompletoABeta(entrada);
				if (debug){
					System.out.println("buscando " + entrada);
				}
				List<IrrVerboIndividual> dm=encuentraFormasIndividuales(entrada, cacheAA);

				Map<String, VerboBean> mapBusquedas = new HashMap<String, VerboBean>();
				for (IrrVerboIndividual bean : dm) {
					String idVerbo =  bean.getVerboId();
					// cacheo la letra-forma para búsquedas
					VerboBean entradaVerbo = mapBusquedas.get(idVerbo);
					if (entradaVerbo == null) {
						entradaVerbo = gerenteVerbos.seleccionaIndividualSinSignificado(idVerbo);
						mapBusquedas.put(idVerbo, entradaVerbo);
					}

					ResultadoUniversal reu = new ResultadoUniversal(TipoPalabra.Verbo, idVerbo, null, entradaVerbo.getParticularidad(),  bean.getPartic(), bean.getVoz(),
							entradas[i], bean.getTiempo(), null, bean.getFuerte(), bean.getPersona(), null, null, null, bean.getModo(), null, null,
							extracciones[j].preps, null);

					setResultado.add(reu);
				}
			}
		}
		if (debug) {
			System.out
					.println("buscaIrrIndivConjugacion: así queda el resultado después la búsqueda de irregularidades individuales en verbos o infinitivos ****************************");
			amUtil.debugBeans(setResultado, new String[] { "formaCanonica", "formaAccidentada" });
		}
	}	
	
	
	/**
	 * básicamente busca las formas de entrada en la lista de irregularidades de verbo individuales,
	 * con un único retruécano: si la forma termina en "mi", "ti", "si" (no siendo en "nu_mi"),
	 * y está acentuada naturalmente, se le pone el acento en la última sílaba 
	 * y se intenta de nuevo
	 * Esto se hace porque a varios verbos en MI les gusta terminar en I/, pero cuando tienen preposición 
	 * se acentúan normalmente.  
	 * Por ejemplo:
	 *   SU/MFHMI vs. FHMI/
	 *   E)/XESTI vs. E)STI/
	 * Si con las forma modificadas a "I/" sí se encontró algo, se la agrega al resultado sólo si era una 
	 * 1ps/2ps/3ps, presente indicativo activo. 
	 * @param formas
	 * @return
	 */
	private List<IrrVerboIndividual> encuentraFormasIndividuales(String forma, AACacheable cacheaa){
		List<IrrVerboIndividual> resultado1 = gerenteIrrVerbosIndividuales.seleccionaPorForma(forma);
		AnalisisAcento aa=cacheaa.getAnalisisAcento(OpPalabras.strBetaACompleto(forma));
		boolean actualesNaturales=aa.actuales.silaba==aa.sugeridos.silaba && aa.actuales.tipoAcento==aa.sugeridos.tipoAcento;
		
		if (
				actualesNaturales &&
				(
				  (forma.endsWith("MI") && !forma.endsWith("NU_MI"))
				  || forma.endsWith("SI") && !forma.endsWith("NU_SI"))
				  || (forma.endsWith("TI") 
				) 
		    ){
			String entrada2 = OpPalabras.desacentuar(OpPalabras.strBetaACompleto(forma));
			entrada2 = OpPalabras.acentua(entrada2, -1);
			entrada2= OpPalabras.strCompletoABeta(entrada2);
			List<IrrVerboIndividual> dm2 = gerenteIrrVerbosIndividuales.seleccionaPorForma(entrada2);
			for (IrrVerboIndividual irr : dm2) {
				boolean personaOk=irr.getPersona()==Persona._1ps || irr.getPersona()==Persona._2ps || irr.getPersona()==Persona._3ps;
				if (personaOk && irr.getTiempo() == Tiempo.Presente && irr.getModo() == Modo.Indicativo && irr.getVoz() == Voz.Activa) {
					resultado1.add(irr);
				}
			}
		}
		return resultado1;	
	}
	
	private GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales;
	private ExtractorPrefijos extractorPrefijos;


	/**
	 * @param gerenteIrrVerbosIndividuales the gerenteIrrVerbosIndividuales to set
	 */
	public void setGerenteIrrVerbosIndividuales(GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales) {
		this.gerenteIrrVerbosIndividuales = gerenteIrrVerbosIndividuales;
	}



	/**
	 * @param extractorPrefijos the extractorPrefijos to set
	 */
	public void setExtractorPrefijos(ExtractorPrefijos extractorPrefijos) {
		this.extractorPrefijos = extractorPrefijos;
	}

	
}

