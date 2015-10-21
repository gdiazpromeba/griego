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
import java.util.Collection;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.AdjetivoComoNominalBean;
import com.kalos.beans.IrrAdjetivoIndividual;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.TermRegAdjetivo;
import com.kalos.beans.TermRegSustantivo;
import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteAdjetivosComoNominales;
import com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Silaba;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.flexion.declinacion.DeclinaAdjetivos;
import com.kalos.flexion.declinacion.OcAdjetivo;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.AnalisisAcento;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AMAdjetivos implements AnalizadorMorfologico, ApplicationContextAware {

    private AMUtil<?> amUtil;
    private AMNominal amNominal;
    private GerenteAdjetivosComoNominales gerenteAdjetivosComoNominales;
    private GerenteAdjetivos gerenteAdjetivos;
    private GerenteIrrAdjetivosIndividuales gerenteIrrAdjetivosIndividuales;
    private DeclinaAdjetivos declinaAdjetivos;
    
    //Logger logger=Logger.getLogger(this.getClass().getName());

    /**
	 * @param gerenteAdjetivos The gerenteAdjetivos to set.
	 */
	public void setGerenteAdjetivos(GerenteAdjetivos gerenteAdjetivos) {
		this.gerenteAdjetivos = gerenteAdjetivos;
	}

	
	/**
	 * indica si los beans dependientes ya han sido cargados, por única vez
	*/
	private boolean dependenciasCargadas=false;
	private ApplicationContext contexto;
	
	public void setApplicationContext(ApplicationContext contexto) {
		this.contexto=contexto;
	}
	
	private void cargaDependencias(){
		if (!dependenciasCargadas){
			this.gerenteAdjetivos=(GerenteAdjetivos)contexto.getBean("gerenteAdjetivos");
			this.gerenteAdjetivosComoNominales=(GerenteAdjetivosComoNominales)contexto.getBean("gerenteAdjetivosComoNominales");
			this.gerenteIrrAdjetivosIndividuales=(GerenteIrrAdjetivosIndividuales)contexto.getBean("gerenteIrrAdjetivosIndividuales");
			this.amNominal=(AMNominal)contexto.getBean("amNominal");
			this.declinaAdjetivos=(DeclinaAdjetivos)contexto.getBean("declinaAdjetivos");
			dependenciasCargadas=true;
		}
	}
	
	

    public Set<ResultadoUniversal> buscaCanonica(String[] entradas, AACacheable cacheAA,  boolean validaContraFlexion, boolean debug) {
        Set<ResultadoUniversal> resultados = new HashSet<>();
    	
    	cargaDependencias();
    	Set<TermRegSustantivo> setPaso1 = new HashSet<TermRegSustantivo>();
        Set<TermRegAdjetivo> termsAdj ;
        Set<TermRegAdjetivo> nomGenReconstruidos = new HashSet<TermRegAdjetivo>();

        long tiempoInicial = System.currentTimeMillis();
        Set<String> setEntradas = new HashSet<String>(Arrays.asList(entradas));
        //obtención de uegos voz-modo-tiempo-persona posibles según terminación
        amNominal.paso1(setEntradas, setPaso1, cacheAA, debug);
        amNominal.corrigePluralizados(setPaso1);
        // utilizar la función "conservasolo" para dejar una determinada rama
        // del árbol solamente
        
//        amUtil.conservaSolo(setPaso1, new String[]{"caso", "numero", "tipoSustantivo"}, new Object[]{Caso.Acusativo, Numero.Singular, 84});
//        amUtil.conservaSolo(setPaso1, new String[]{"caso", "numero", "tipoSustantivo"}, new Object[]{Caso.Vocativo, Numero.Singular, 100});
//        amUtil.conservaSolo(setPaso1, new String[]{"caso", "numero"}, new Object[]{Caso.Vocativo, Numero.Singular});

        
       termsAdj=trataFemenino(setPaso1, debug);
//        amUtil.conservaSolo(termsAdj, new String[]{"tipoSustantivo"}, new Object[]{131});
        buscaNomGenDirecto(termsAdj, resultados, debug);
        //reconstruye los temas en base a la desinencia propuesta
        amNominal.reconstruyeTemas(termsAdj, nomGenReconstruidos, cacheAA, debug); 
        //búsqueda contra las formas fáciles
        buscaNomGenReconstruidos(nomGenReconstruidos, resultados, debug); 
        
        buscaEnInvariables(entradas, resultados, debug);
        buscaIndividuales(setEntradas, resultados);
        
        pueblaCanonicasAdjetivos(resultados);
        if (validaContraFlexion){
            validaConFlexion(resultados);
          }
        
        long tiempoFinal = System.currentTimeMillis();
        long lapsoEnMilis = tiempoFinal - tiempoInicial;
        if (debug) {
            GregorianCalendar lapso = new GregorianCalendar();
            lapso.setTimeInMillis(lapsoEnMilis);
            System.out.println("tardó " + lapso.get(Calendar.MINUTE) + " minutos " + lapso.get(Calendar.SECOND)
                    + " segundos " + lapso.get(Calendar.MILLISECOND) + " milisegundos");
        }
        return resultados;
        

    }
    
    private void buscaIndividuales(Set<String> entradas, Set<ResultadoUniversal> resultado){
        
        List<ResultadoUniversal> lstResultados=new ArrayList<ResultadoUniversal>();
            
        for (String entrada: entradas){
            String entradaBeta=OpPalabras.strCompletoABeta(entrada);
            List<IrrAdjetivoIndividual> individuales=gerenteIrrAdjetivosIndividuales.seleccionaPorForma(entradaBeta);
            for (IrrAdjetivoIndividual indiv: individuales){
                ResultadoUniversal reu = new ResultadoUniversal(TipoPalabra.Adjetivo, indiv.getAdjetivoId(),
                        null, null, indiv.getParticularidad(), null,
                        entrada, null, null, null, null, indiv.getCaso(), indiv.getGenero(), indiv.getNumero(),
                        null, indiv.getGrado(), null, null, null);
                lstResultados.add(reu);
            }
        }
        
        resultado.addAll(lstResultados);
    }
    
    /**
     * puebla la forma canónica de los resultados universales
     * @param reus  Los resultados universales, en cualquier tipo de colección
     */
    private void pueblaCanonicasAdjetivos(Collection<ResultadoUniversal> reus){
    	//creación de la lista de ids
    	List<String> ids=new ArrayList<String>();
    	for(ResultadoUniversal reu: reus){
    		ids.add(reu.getId());
    	}
    	//obtención de la lista de entradas de adjetivo. Creo un mapita id-entrada.
    	List<AdjetivoBean> eas=gerenteAdjetivos.getBeans(ids);
    	Map<String, AdjetivoBean> mapaEntradas=new HashMap<String, AdjetivoBean>();
    	for (AdjetivoBean ea: eas){
    		mapaEntradas.put(ea.getId(), ea);
    	}

    	//poblamiento de la forma canónica
    	for(Iterator<ResultadoUniversal> it=reus.iterator(); it.hasNext(); ){
    		ResultadoUniversal reu=it.next();
    		AdjetivoBean ea=mapaEntradas.get(reu.getId());
    		String canonica= ea.primerCampoNoVacio();
    		canonica=OpPalabras.strBetaACompleto(canonica);
    		reu.setFormaCanonica(canonica);
    	}
    }
    

    /**
     * Hay resultados de los adjetivos que simplemente no se pueden "podar" hasta no contar con una flexión.
     * Este método flexiona todas las entradas y poda lo que no se haya encontrado en la flexión
     * @param resultados
     */
    private void validaConFlexion(Set<ResultadoUniversal> resultados){
        for (Iterator<ResultadoUniversal> it=resultados.iterator(); it.hasNext();){
            ResultadoUniversal reu=it.next();
            
            EnumMap<Particularidad, OcAdjetivo> mapaOcurrencias= declinaAdjetivos.declina(reu.getId());
            OcAdjetivo ocurrencia=mapaOcurrencias.get(reu.getParticularidad());
            if (ocurrencia==null){
                it.remove();
                continue;
            }
            List<String> formas=ocurrencia.getForm(reu.getGrado(), reu.getGenero(), reu.getCaso(), reu.getNumero());
            if (formas==null){
                it.remove();
                continue;
            }
            if (!formas.contains(reu.getFormaAccidentada())){
                it.remove();
                continue;
            }
        }
    }
    
//    private String allHashes(Set set){
//        StringBuffer sb=new StringBuffer();
//        for (Object obj: set){
//            sb.append(obj.hashCode() +",");
//        }
//        return sb.toString();
//    }
    
    
	/**
	 * busca en los sustantivos que son invariables (los cuales no pueden encontrarse mediante una reconstrucción)
	 * @param entradas
	 * @param setResultado
	 * @param debug
	 */
	private void buscaEnInvariables(String[] entradas, Set<ResultadoUniversal> setResultado, boolean debug) {
		for (int i = 0; i < entradas.length; i++) {
			String entrada=entradas[i];
			String entradaBeta = OpPalabras.strCompletoABeta(entradas[i]);
			List<AdjetivoBean> dm = gerenteAdjetivos.seleccionaInvariables(entradaBeta);
			for (AdjetivoBean bean : dm) {
				ResultadoUniversal reu = new ResultadoUniversal(
						TipoPalabra.Adjetivo, bean.getId(), null, bean.getParticularidad(), null, null, 
						entrada, null, null, null,
						null, Caso.Nominativo, Genero.Masculino, Numero.Singular, null, bean.getGrado(), 
						OpPalabras.strBetaACompleto(bean.getMasculino()), null, null);

				setResultado.add(reu);
			}

		}
		if (debug) {
			System.out.println("resultados luego de la búsqueda de invariables ");
			amUtil.debugSet(setResultado, new String[] { "nominativo", "genitivo" });
		}
	}
    

    /**
     * Convierte las terminaciones de sustantivo en terminaciones de adjetivo.
     * La única diferencia es que ciertos plurales femeninos de 1ra son transformados en 
     * masculinos de 2da, para poder proceder con el AM regularmente.
     * Los beans cuyas terminaciones hayan sido transformadas tendrán el campo "transformadoAMasculino"
     * verdadero.
     *  
     * @param terminaciones el set con los beans que serán transformados. Es destruido al terminar.
     * @return el set de adjetivos
     */
    private Set<TermRegAdjetivo> trataFemenino(Set<TermRegSustantivo> terminaciones, boolean debug) {
    	Set<TermRegAdjetivo> resultado = new HashSet<TermRegAdjetivo>();
        for (TermRegSustantivo trs : terminaciones) {
            TermRegAdjetivo tra = trs.aTermregAdjetivo();
            resultado.add(tra);
            //ahora hace una copia que modifica y agrega el el caso de los femeninos con acento raro
            TermRegAdjetivo nuevoTra= trs.aTermregAdjetivo(); 
            boolean esPlural=nuevoTra.getNumero().equals(Numero.Plural);
            boolean esNomVoc=nuevoTra.getCaso().equals(Caso.Nominativo)  || nuevoTra.getCaso().equals(Caso.Vocativo) ;
            boolean esGenitivo=nuevoTra.getCaso().equals(Caso.Genitivo);
            int tipo=nuevoTra.getTipoSustantivo();
            
            
            if (esPlural && ((esNomVoc && tipo==45))|| (esGenitivo && tipo==1) ){
                TermRegAdjetivo copia= nuevoTra.clona();
                nuevoTra.setTerminacionPendienteRevision(copia);
                if (esNomVoc){
                	nuevoTra.setTransformadoAMasculino(true);
                    String formaOriginal=nuevoTra.getFormaOriginal();
                    AnalisisAcento aa=AnalisisAcento.getAnalisisAcento(nuevoTra.getFormaOriginal());
                    formaOriginal=OpPalabras.desacentuar(formaOriginal);
                    formaOriginal=StringUtils.overlay(formaOriginal, OpPalabras.strBetaACompleto("OI"), formaOriginal.length()-2, formaOriginal.length());
                    formaOriginal=OpPalabras.acentua(formaOriginal, aa.actuales.silabaB1);
                    nuevoTra.setFormaOriginal(formaOriginal);
                    nuevoTra.setTerminacion("OI");
                    nuevoTra.setTiposHoja("-106-107-");
                    nuevoTra.setTipoSustantivo(46);
                    nuevoTra.setAcentoConcuerda(1);
                }else{
                	nuevoTra.setTransformadoAMasculino(true);
                	nuevoTra.setTiposHoja("-101-100-75-119-118-106-107-49-110-111-");
                	nuevoTra.setTipoSustantivo(2);
                	nuevoTra.setPosicionConcuerda(1);
                	nuevoTra.setSilaba(Silaba.getEnum(0));
                }
            }
        }
        terminaciones.clear();
        if (debug) {
            System.out.println("trataFemenino ******************");
            amUtil.debugBeans(resultado, new String[] { "formaOriginal"});
        }
        return resultado;
    }

    /**
     * Busca los nominativos y genitivos reconstruidos en "reconstruyeTemas", en
     * los respectivos campos "fáciles" de la base de datos. Los resultados son
     * registros que agrega como tales directamente al set de resultados
     * 
     * @param setOriginal
     * @param setResultados
     */
    private void buscaNomGenReconstruidos(Set<TermRegAdjetivo> setOriginal, Set<ResultadoUniversal> setResultados, boolean debug) {
        StringBuffer sbDebug = new StringBuffer();
        Set<TermRegSustantivo> aBorrar = new HashSet<TermRegSustantivo>();
        Map<String, List<AdjetivoComoNominalBean>> llamadasNom = new HashMap<String, List<AdjetivoComoNominalBean>>();
        Map<String, List<AdjetivoComoNominalBean>> llamadasGen = new HashMap<String, List<AdjetivoComoNominalBean>>();
        for (TermRegAdjetivo reg : setOriginal) {
            String nomPropuesto = OpPalabras.strCompletoABeta(reg.getNominativoPropuesto());
            String genPropuesto = OpPalabras.strCompletoABeta(reg.getGenitivoPropuesto());

            List<AdjetivoComoNominalBean> dm = null;
            if (nomPropuesto != null) {
                dm = llamadasNom.get(nomPropuesto);
                if (dm == null) {
                    if (debug)
                        sbDebug.append("buscando nominativo ..." + nomPropuesto + " \n");
                    dm = gerenteAdjetivosComoNominales.seleccionaPorNominativoAM(nomPropuesto);
                    llamadasNom.put(nomPropuesto, dm);
                } else {
                    if (debug)
                        sbDebug.append("reusando búsqueda nominativo  ..." + nomPropuesto + " \n");
                }
            } else if (genPropuesto != null) {
                dm = llamadasGen.get(genPropuesto);
                if (dm == null) {
                    if (debug)
                        sbDebug.append("buscando genitivo ..." + genPropuesto + " \n");
                    dm = gerenteAdjetivosComoNominales.seleccionaPorGenitivoParaAM(genPropuesto);
                    llamadasGen.put(genPropuesto, dm);
                } else {
                    if (debug)
                        sbDebug.append("reusando búsqueda genitivo  ..." + genPropuesto + " \n");
                }
            }
            for (AdjetivoComoNominalBean es : dm) {
                // el tipo de lo que encontró tiene que estar entre los
                // TIPOS_SUST_HOJA de lo que propuse
                String tiposHoja = reg.getTiposHoja();
                String tipo = "-" + es.getTipoNominal() + "-";
                
                if (tiposHoja.contains(tipo)) {
                	if (reg.isTransformadoAMasculino() && !es.getGenero().equals(Genero.Masculino)){
                		if (debug){
                		  sbDebug.append("se rechaza el siguiente registro encontrado en ADJETIVOS_COMO_NOMINALES: " + OpBeans.debugBean(reg, new String[]{"nominativoPropuesto", "genitivoPropuesto"}) + " \n");
                		  sbDebug.append("  porque el bean reconstituido es un femenino transformado a masculino, pero \n");
                		  sbDebug.append("  el género encontrado en ADJETIVOS_COMO_NOMNINALES no es masculino \n");
                		}
                		continue;
                	}
                    if (debug) {
                        sbDebug.append("va a resultados: " + OpBeans.debugBean(reg, new String[]{"nominativoPropuesto", "genitivoPropuesto"}) + " \n");
                        sbDebug.append("  porque el tipo de sust " + tipo + " existe dentro de las hojas \n");
                    }
                    
//                  String nominativo=es.getNominativo();
                    Genero genero=es.getGenero();
                    String formaOriginal=reg.getFormaOriginal();
                    if (reg.isTransformadoAMasculino()){
                        genero=Genero.Femenino;
                        formaOriginal=reg.getTerminacionPendienteRevision().getFormaOriginal();
                    }
                                        
                    ResultadoUniversal reu = new ResultadoUniversal(TipoPalabra.Adjetivo, es.getIdAdjetivo(), null,
                            es.getParticularidad(), null, null, formaOriginal, null, null, null, null, reg
                                    .getCaso(), genero, reg.getNumero(),  null,  es.getGrado(), null, null, null);

                    setResultados.add(reu);
                    aBorrar.add(reg);
                } else {
                    if (debug) {
                        sbDebug.append("   se encontró el adjetivo " + es.getNominativo());
                        sbDebug.append(" - " + es.getGenitivo() + " tipo=" + es.getTipoNominal());
                        sbDebug.append(" pero su tipo no concuerda con los tipos hoja propuestos=" + tiposHoja + " \n");
                    }

                }
            }
        }
        
        setOriginal.removeAll(aBorrar);
        if (debug) {
            System.out.println("buscaNomGenReconstruidos ******************");
            System.out.println(sbDebug.toString());
            System.out.println("resultados: ");
            amUtil.debugBeans(setResultados, new String[] { "formaAccidentada"});
        }

    }

    /**
     * comparo la foma original de los beanes del set de entrada contra los
     * nominativos y genitivos de ADJETIVOS_COMO_NOMINALES. Si coinciden, agrego
     * ese bean directamente al resultado y lo borro del set de entrada.
     * 
     * @param setOriginal
     * @param setResultados
     * @param debug
     */
    private void buscaNomGenDirecto(Set<TermRegAdjetivo> setOriginal, Set<ResultadoUniversal> setResultados,
            boolean debug) {
        StringBuffer sbDebug = new StringBuffer();
        Set<TermRegAdjetivo> aBorrar = new HashSet<TermRegAdjetivo>();
        Map<String, List<AdjetivoComoNominalBean>> llamadasNom = new HashMap<String, List<AdjetivoComoNominalBean>>();
        Map<String, List<AdjetivoComoNominalBean>> llamadasGen = new HashMap<String, List<AdjetivoComoNominalBean>>();
        for (TermRegAdjetivo reg : setOriginal) {
            String nomPropuesto = null;
            String genPropuesto = null;
            if (reg.getCaso().equals(Caso.Nominativo) && reg.getNumero().equals(Numero.Singular)) {
                nomPropuesto = OpPalabras.strCompletoABeta(reg.getFormaOriginal());
            } else if (reg.getCaso().equals(Caso.Genitivo) && reg.getNumero().equals(Numero.Singular)) {
            	genPropuesto = OpPalabras.strCompletoABeta(reg.getFormaOriginal());
            } else {
                continue;
            }

            List<AdjetivoComoNominalBean> dm = null;
            if (nomPropuesto != null) {
                dm = llamadasNom.get(nomPropuesto);
                if (dm == null) {
                    if (debug)
                        sbDebug.append("buscando nominativo ..." + nomPropuesto + " \n");
                    dm = gerenteAdjetivosComoNominales.seleccionaPorNominativoAM(nomPropuesto);
                    llamadasNom.put(nomPropuesto, dm);
                } else {
                    if (debug)
                        sbDebug.append("reusando búsqueda nominativo  ..." + nomPropuesto + " \n");
                }
            } else if (genPropuesto != null) {
                dm = llamadasGen.get(genPropuesto);
                if (dm == null) {
                    if (debug)
                        sbDebug.append("buscando genitivo ..." + genPropuesto + " \n");
                    dm = gerenteAdjetivosComoNominales.seleccionaPorGenitivoParaAM(genPropuesto);
                    llamadasGen.put(genPropuesto, dm);
                } else {
                    if (debug)
                        sbDebug.append("reusando búsqueda genitivo  ..." + genPropuesto + " \n");
                }
            }
            for (AdjetivoComoNominalBean es : dm) {
                // el tipo de lo que encontró tiene que estar entre los
                // TIPOS_SUST_HOJA de lo que propuse
                String tiposHoja = reg.getTiposHoja();
                String tipo = "-" + es.getTipoNominal() + "-";
                if (es.getTipoNominal()==0 || tiposHoja.indexOf(tipo) > -1) {

                    if (debug) {
                        sbDebug.append("va a resultados" + reg.toString() + " \n");
                        sbDebug.append("  porque el tipo de sust " + tipo + " existe dentro de las hojas \n");
                    }
                    
//                    String nominativo=es.getNominativo();
                    Genero genero=es.getGenero();
                    String formaOriginal=reg.getFormaOriginal();
                    if (reg.isTransformadoAMasculino()){
                        genero=Genero.Femenino;
                        formaOriginal=reg.getTerminacionPendienteRevision().getFormaOriginal();
                    }

                    ResultadoUniversal reu = new ResultadoUniversal(TipoPalabra.Adjetivo, es.getIdAdjetivo(), null,
                            es.getParticularidad(), null, null, formaOriginal, null, null, null, null, reg
                                    .getCaso(), genero, reg.getNumero(), null, es.getGrado(), null, null, null);

                    setResultados.add(reu);
                    aBorrar.add(reg);
                } else {
                    if (debug) {
                        sbDebug.append("   se encontró el sustantivo " + es.getNominativo());
                        sbDebug.append(" - " + es.getGenitivo() + " tipo=" + es.getTipoNominal());
                        sbDebug.append(" pero su tipo no concuerda con los tipos hoja propuestos=" + tiposHoja + " \n");
                    }

                }
            }
        }
        setOriginal.removeAll(aBorrar);
        if (debug) {
            System.out.println("buscaNomGenDirecto ******************");
            System.out.println(sbDebug.toString());
            System.out.println("  resultados: ");
            amUtil.debugBeans(setResultados, new String[] { "formaAccidentada", "formaCanonica" });
        }

    }



    /**
     * @return Returns the amNominal.
     */
    public AMNominal getAmNominal() {
        return amNominal;
    }

    /**
     * @param amNominal The amNominal to set.
     */
    public void setAmNominal(AMNominal amNominal) {
        this.amNominal = amNominal;
    }

    /**
     * @param gerenteAdjetivosComoNominales The gerenteAdjetivosComoNominales to set.
     */
    public void setGerenteAdjetivosComoNominales(GerenteAdjetivosComoNominales gerenteAdjetivosComoNominales) {
        this.gerenteAdjetivosComoNominales = gerenteAdjetivosComoNominales;
    }

    public void setGerenteIrrAdjetivosIndividuales(GerenteIrrAdjetivosIndividuales gerenteIrrAdjetivosIndividuales) {
        this.gerenteIrrAdjetivosIndividuales = gerenteIrrAdjetivosIndividuales;
    }

}
