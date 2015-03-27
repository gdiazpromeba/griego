
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
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.kalos.beans.CubosTipoPartBean;
import com.kalos.beans.IrrParticipioEntero;
import com.kalos.beans.IrrParticipioSimpleBean;
import com.kalos.beans.IrrVerbo;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.TermRegNominal;
import com.kalos.beans.TermRegParticipio;
import com.kalos.beans.TermRegSustantivo;
import com.kalos.beans.TransParticipiosBean;
import com.kalos.beans.VerbalizadorBean;
import com.kalos.beans.VerboBean;
import com.kalos.datos.gerentes.GerenteCubosTipoPart;
import com.kalos.datos.gerentes.GerenteIrrParticipiosEnteros;
import com.kalos.datos.gerentes.GerenteIrrParticipiosSimples;
import com.kalos.datos.gerentes.GerenteTransParticipios;
import com.kalos.datos.gerentes.GerenteVerbalizadorParticipios;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.datos.gerentes.GerenteVerbosCompuestos;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Espiritu;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.enumeraciones.Voz;
import com.kalos.enumeraciones.utils.TransformadorTiempoAspecto;
import com.kalos.flexion.declinacion.OcParticipio;
import com.kalos.flexion.declinacion.Participios;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.AnalisisAcento;
import com.kalos.operaciones.DesTransformaciones;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;

//import org.apache.log4j.Logger;

/**
 * Clase que realiza el análisis morfológico de los participios.
 * Primero realiza una búsqueda por terminación nominal, para determinar el tipo, número y posible
 * tipo de sustantivo.
 * De eso se trata de reconstruir el nominativo o el genitivo. 
 * Esos nominativos o genitivos se pasan por una rutina que, de acuerdo a la terminación, les averigua el aspecto.
 * Una vez que se tiene el aspecto, se les des-transforma el tema para llegar a la forma canónica.
 * 
 * @author GDiaz
 */
public class AMParticipios implements AnalizadorMorfologico {

    private AMNominal amNominal;
    private AMUtil amUtil;
    private AMVerbal amVerbal;
    private GerenteVerbos gerenteVerbos;
    private GerenteIrrParticipiosEnteros gerenteIrrParticipiosEnteros;
    private GerenteIrrParticipiosSimples gerenteIrrParticipiosSimples;
    private GerenteCubosTipoPart gerenteCubosTipoPart;
    private List<CubosTipoPartBean> listaCubosTipoPart;
    private GerenteTransParticipios gerenteTransParticipios;
    private GerenteVerbalizadorParticipios gerenteVerbalizadorParticipios;

    //private Logger logger=Logger.getLogger(this.getClass().getName());

    private Participios participios;

    /**
     * @param participios The participios to set.
     */
    public void setParticipios(Participios participios) {
        this.participios = participios;
    }

    /**
     * @param gerenteVerbalizadorParticipios The gerenteVerbalizadorParticipios to set.
     */
    public void setGerenteVerbalizadorParticipios(GerenteVerbalizadorParticipios gerenteVerbalizadorParticipios) {
        this.gerenteVerbalizadorParticipios = gerenteVerbalizadorParticipios;
    }

    /**
     * @param gerenteTransParticipios The gerenteTransParticipios to set.
     */
    public void setGerenteTransParticipios(GerenteTransParticipios gerenteTransParticipios) {
        this.gerenteTransParticipios = gerenteTransParticipios;
    }

    /**
     * @param gerenteIrrParticipiosEnteros The gerenteIrrParticipiosEnteros to set.
     */
    public void setGerenteIrrParticipiosEnteros(GerenteIrrParticipiosEnteros gerenteIrrParticipiosEnteros) {
        this.gerenteIrrParticipiosEnteros = gerenteIrrParticipiosEnteros;
    }

    /**
     * @param gerenteVerbos The gerenteVerbos to set.
     */
    public void setGerenteVerbos(GerenteVerbos gerenteVerbos) {
        this.gerenteVerbos = gerenteVerbos;
    }

    /**
     * @param amVerbal The amVerbal to set.
     */
    public void setAmVerbal(AMVerbal amVerbal) {
        this.amVerbal = amVerbal;
    }

    /**
     * @param amUtil The amUtil to set.
     */
    public void setAmUtil(AMUtil amUtil) {
        this.amUtil = amUtil;
    }

    /**
     * Punto de entrada de esta clase. La función tiene parámetros de entrada y de salida
     * @param entradas      las entradas
     * @param setResultado  resultado final
     */

    public long buscaCanonica(String[] entradas, Set<ResultadoUniversal> setResultado, AACacheable cacheAA, boolean validaContraFlexion, boolean debug) {
        //		cargaDependencias();
        Set<TermRegSustantivo> setPaso1 = new LinkedHashSet<TermRegSustantivo>();
        Set<TermRegParticipio> setPaso2 = new LinkedHashSet<TermRegParticipio>();
        Set<ObjYDest> setPaso3 = new LinkedHashSet<ObjYDest>();
        Set<TermRegParticipio> setConCanonicasPropuestas = new LinkedHashSet<TermRegParticipio>();
        List<ObjYDest> aBuscarPorTema = new ArrayList<ObjYDest>();
        Set<TermRegSustantivo> nomGenDisponibles = new LinkedHashSet<TermRegSustantivo>();
        List<TermRegParticipio> nomGenDisponiblesParticipio = new ArrayList<TermRegParticipio>();
        long tiempoInicial = System.currentTimeMillis(), lapsoEnMilis, tiempoFinal;

        Set<String> setEntradas = new HashSet<String>();

        //agrego las entradas al treeModel
        for (int i = 0; i < entradas.length; i++) {
            if (debug)
                System.out.println("entrada=" + OpPalabras.strCompletoABeta(entradas[i]));
            setEntradas.add(entradas[i]);
            revisaIrrPartIndividuales(setResultado, entradas[i], debug);
        }

        amNominal.paso1(setEntradas, setPaso1, cacheAA, debug); //obtención de juegos caso-tiempo-tipos nominales posibles - según la  terminación
        //amUtil.conservaSolo(setPaso1, new String[] { "terminacion", "caso" }, new Object[] { "N", Caso.Nominativo });
        //		amUtil.conservaSolo(setPaso1, new String[]{ "caso", "numero", "tipoSustantivo"}, new Object[]{ Caso.Nominativo,  Numero.Singular, 46});

        filtraTiposPosibles(setPaso1, debug); //quita los tipos de sustantivo que no son de participio

        revisaNomGen(setPaso1, nomGenDisponibles, debug); //revisa si las formas no son ya nominativos o genitivos

        amNominal.reconstruyeTemas(setPaso1, nomGenDisponibles, cacheAA, debug); //para transformar en nom o gen los que no lo son

        amNominal.validaContraTerminacionesPendientes(nomGenDisponibles, cacheAA, debug);

        nomGenDisponiblesParticipio = transformaNominalEnParticipio(nomGenDisponibles);

        //chequeo de IRR_PARTICIPIOS_ENTEROS
        revisaIrrParticipiosEnteros(nomGenDisponiblesParticipio, setResultado, debug);

        inyeccionCamposVerbales(nomGenDisponiblesParticipio, setPaso2, debug); //agrega los campos de carácter verbal (voz, aspecto, fuerte)
        //		amUtil.conservaSolo(setPaso2, new String[]{"aspecto"}, new Object[]{Aspecto.Perfectivo});

        filtraGeneroVozAspecto(setPaso2, debug);

        incorporaADestransformar(setPaso2, debug);
        amUtil.desTransformacionesTemas(setPaso2, setPaso3, aBuscarPorTema, debug); //des-reduplica los perfectivos

        List<ObjYDest> temasABuscar = new ArrayList<ObjYDest>(); //lista de registros conteniendo temas que se buscarán en IRR_VERBOS, más sus destransformaciones
        trataDescarteDestransformacion(aBuscarPorTema, temasABuscar, cacheAA, debug); //pasa los temas descartados por des-transformaciones por TRANS_PARTICIPIOS, y agrega el resultado a temasABuscar
        temaPropuestoACanonica(setPaso3, setConCanonicasPropuestas, temasABuscar, cacheAA, debug); //remplaza las terminaciones de nom. o gen. por las verbales

        List<TermRegParticipio> resultadosIrr = new ArrayList<TermRegParticipio>(); //lista que contendrá los registros con información agregada de PK de verbos por los temas  a buscar
        Map<String, List<IrrVerbo>> mapBusquedasHechas = new HashMap<String, List<IrrVerbo>>();
        amVerbal.encuentraTemasTemprano(temasABuscar, resultadosIrr, mapBusquedasHechas, debug);

        agregaResultadosIrr(setResultado, resultadosIrr, debug);

        comparaReconstruidosConTabla(setConCanonicasPropuestas, setResultado, false, debug); //busca dichas formas verbales en la tabla de verbos
        comparaReconstruidosConTabla(setConCanonicasPropuestas, setResultado, true, debug); //busca dichas formas verbales en la tabla de verbos

        amVerbal.pueblaCanonicasVerbos(setResultado);
        if (validaContraFlexion) {
            validaConFlexion(setResultado);
        }

        tiempoFinal = System.currentTimeMillis();
        lapsoEnMilis = tiempoFinal - tiempoInicial;
        if (debug) {
            GregorianCalendar lapso = new GregorianCalendar();
            lapso.setTimeInMillis(lapsoEnMilis);
            System.out.println("tardó " + lapso.get(Calendar.MINUTE) + " minutos " + lapso.get(Calendar.SECOND)
                    + " segundos " + lapso.get(Calendar.MILLISECOND) + " milisegundos");
        }

        //limpieza (no totalmente necesaria, pero prolija)

        return lapsoEnMilis;

    }

    /**
     * Hay resultados de los participios que simplemente no se pueden "podar" hasta no contar con una flexión.
     * Este método flexiona todas las entradas y poda lo que no se haya encontrado en la flexión
     * @param resultados
     */
    private void validaConFlexion(Set<ResultadoUniversal> resultados) {
        for (Iterator<ResultadoUniversal> it = resultados.iterator(); it.hasNext();) {
            ResultadoUniversal reu = (ResultadoUniversal) it.next();

            Map<Particularidad, OcParticipio> mapaOcurrencias = participios.flexiona(reu.getId());
            OcParticipio ocurrencia = mapaOcurrencias.get(reu.getParticularidad());
            if (ocurrencia == null) {
                it.remove();
                continue;
            }
            List<String> formas = ocurrencia.getForma(reu.getVoz(), reu.getAspecto(), reu.getFuerte(), reu.getGenero(),
                    reu.getCaso(), reu.getNumero());
            if (formas == null) {
                it.remove();
                continue;
            }
            if (!formas.contains(reu.getFormaAccidentada())) {
                it.remove();
                continue;
            }
        }
    }

    /**
     * transforma las terminaciones TermRegSustantivo que hay en el el set de nodos,
     * en terminaciones TermRegParticipio, las cuales tienen además la información verbal
     * @param nodosNominal
     */
    private List<TermRegParticipio> transformaNominalEnParticipio(Set<TermRegSustantivo> nodosNominal) {
        List<TermRegParticipio> listTrp = new ArrayList<TermRegParticipio>();
        for (TermRegSustantivo nominal : nodosNominal) {
            TermRegSustantivo trs = (TermRegSustantivo) nominal;
            TermRegParticipio trp = new TermRegParticipio();
            try {
                BeanUtils.copyProperties(trp, trs);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            listTrp.add(trp);
        }
        return listTrp;
    }

    /**
     * Versión especial para participios de esta función. Determina cuál es el registro que se desTransformará, y 
     * duplica su contenido en una nueva columna FORMA_A_DESTRANSFORMAR
     * @param aDestransformar
     */
    public void incorporaADestransformar(Collection<TermRegParticipio> aDestransformar, boolean debug) {
        for (TermRegParticipio trp : aDestransformar) {
            String aDestr = null;

            if (trp.getNominativoPropuesto() != null) {
                aDestr = trp.getNominativoPropuesto();
            } else if (trp.getGenitivoPropuesto() != null) {
                aDestr = trp.getGenitivoPropuesto();
            } else {
                aDestr = trp.getFormaOriginal();
            }
            trp.setFormaADestransformar(aDestr);
        }
        if (debug) {
            System.out.println("*** después de incorporar el FORMA_A_DESTRANSFORMAR ***");
            amUtil.debugSet(aDestransformar, new String[] { "formaOriginal", "terminacion", "terminacionVerbalizada",
                    "formaADestransformar", "temaPropuesto", "nominativoPropuesto", "genitivoPropuesto" });
        }
    }

    private ExtractorPrefijos extractorPrefijos;
    private GerenteVerbosCompuestos gerenteVerbosCompuestos;

    /**
     * revisa el set de entrada por nominativos o genitivos (que van a estar en FORMA_ORIGINAL si la forma 
     * ya es tentativamente nom/gen, o en NOM/GEN_PROPUESTO si fue reconstruida), y los compara con la tabla
     * IRR_PARTICIPIOS_ENTEROS. Todo resultado positivo se saca de setNomGen y se agrega directamente a resultados,
     * con formato universal
     * 
     * @param setNomGen
     * @param resultados
     */
    private void revisaIrrParticipiosEnteros(List<TermRegParticipio> setNomGen, Set<ResultadoUniversal> resultados, boolean debug) {
        StringBuffer sbDebug = new StringBuffer();
        List<TermRegParticipio> quitados = new ArrayList<TermRegParticipio>(); //lista de los regNomGen quitados y agregados a resultados (para debug solamente)
        //maps para cachear búsquedas
        Map<String, List<IrrParticipioEntero>> ipesNom = new HashMap<String, List<IrrParticipioEntero>>();
        Map<String, List<IrrParticipioEntero>> ipesGen = new HashMap<String, List<IrrParticipioEntero>>();
        HashMap<Object[], TemaConPreps[]> cacheExtraccionPrefijos = new HashMap<Object[], TemaConPreps[]>();
        for (Iterator<TermRegParticipio> it = setNomGen.iterator(); it.hasNext();) {
            TermRegParticipio regNomGen = it.next();

            //obtengo un nominativo o genitivo
            String nominativo = null, genitivo = null;
            Espiritu espirituNom = null, espirituGen = null;
            Caso caso = regNomGen.getCaso();
            Numero numero = regNomGen.getNumero();

            boolean casoNomSing = caso.equals(Caso.Nominativo) && numero.equals(Numero.Singular);
            boolean casoGenSing = caso.equals(Caso.Genitivo) && numero.equals(Numero.Singular);
            boolean tieneNomPropuesto = regNomGen.getNominativoPropuesto() != null;
            boolean tieneGenPropuesto = regNomGen.getGenitivoPropuesto() != null;

            if (casoNomSing) {
                nominativo = regNomGen.getFormaOriginal();
                espirituNom = OpPalabras.getEspiritu(nominativo);
            } else if (casoGenSing) {
                genitivo = regNomGen.getFormaOriginal();
                espirituGen = OpPalabras.getEspiritu(genitivo);
            } else if (tieneNomPropuesto) {
                nominativo = regNomGen.getNominativoPropuesto();
            } else if (tieneGenPropuesto) {
                genitivo = regNomGen.getGenitivoPropuesto();
            }

            List<IrrParticipioEntero> ipes = null;
            Espiritu espirituAConsiderar = null;
            if (nominativo != null) {
                ipes = ipesNom.get(nominativo);
                if (ipes == null) {
                    ipes = new ArrayList<IrrParticipioEntero>();
                    TemaConPreps[] tcps = extractorPrefijos.averiguaPrefijos(nominativo, 1, cacheExtraccionPrefijos);
                    for (TemaConPreps tcp : tcps) {
                        List<IrrParticipioEntero> ipesAux = gerenteIrrParticipiosEnteros.seleccionaPorNominativo(OpPalabras.strCompletoABeta(tcp.resto));
                        cargaPrepsEnListaIpes(ipesAux, tcp.preps);
                        ipes.addAll(ipesAux);
                    }
                    ipesNom.put(nominativo, ipes);
                }
                espirituAConsiderar = espirituNom;
            } else if (genitivo != null) {
                ipes = ipesGen.get(genitivo);
                if (ipes == null) {
                    ipes = new ArrayList<IrrParticipioEntero>();
                    TemaConPreps[] tcps = extractorPrefijos.averiguaPrefijos(genitivo, 1, cacheExtraccionPrefijos);
                    for (TemaConPreps tcp : tcps) {
                        List<IrrParticipioEntero> ipesAux = gerenteIrrParticipiosEnteros.seleccionaPorGenitivo(OpPalabras.strCompletoABeta(tcp.resto));
                        cargaPrepsEnListaIpes(ipesAux, tcp.preps);
                        ipes.addAll(ipesAux);
                    }
                    ipesGen.put(genitivo, ipes);
                }
                espirituAConsiderar = espirituGen;
            }
            Map<String, VerboBean> busquedasDic = new HashMap<String, VerboBean>();
            boolean encontro = false; //flag que se pone a true si se encontraron uno o más registros 
            //de IRR_PARTICIPIOS_ENTEROS. Lo uso para substraer el registro del 
            //set de entrada
            for (IrrParticipioEntero ipe : ipes) {
                VerboBean regDic = null;
                String idVerbo = ipe.getVerboId();
                String formaCanonica = null;
                //si el verbo no es compuesto
                if (ipe.getPreposiciones().size() == 0) {
                    regDic = busquedasDic.get(idVerbo);
                    if (regDic == null) {
                        regDic = gerenteVerbos.seleccionaIndividualSinSignificado(idVerbo);
                        busquedasDic.put(idVerbo, regDic);
                    }
                    formaCanonica = OpPalabras.strBetaACompleto(regDic.getVerbo());
                    //comprobación de espíritu, sólo se aplica si el verbo con es compuesto
                    Espiritu espirituVerbo = OpPalabras.getEspiritu(formaCanonica);
                    if (nominativo == null && genitivo == null) {
                        if (espirituAConsiderar != espirituVerbo) {
                            continue;
                        }
                    }
                } else {
                    //si el verbo es compuesto
                    String idCompuesto = gerenteVerbosCompuestos.seleccionaPorVerboSimpleYPreps(ipe.getVerboId(), ipe.getPreposiciones());
                    regDic = busquedasDic.get(idCompuesto);
                    if (regDic == null) {
                        regDic = gerenteVerbos.seleccionaIndividualSinSignificado(idCompuesto);
                        busquedasDic.put(idCompuesto, regDic);
                    }
                    if (regDic == null)
                        continue;
                    formaCanonica = OpPalabras.strBetaACompleto(regDic.getVerbo());
                }

                String formaAccidentada = regNomGen.getFormaOriginal();

                Particularidad particIrr = ipe.getPartic();
                Particularidad particCanonica = regDic.getParticularidad();
                Voz voz = ipe.getVoz();
                Aspecto aspecto = ipe.getAspecto();
                FuerteDebil fuerte = ipe.getFuerte();
                Genero generoIrr = ipe.getGenero();

                //comparo si el género de IRR_PARTICIPIOS_ENTEROS conicide con alguno de los tipos-nominales-hoja
                //del registro de trabajo
                String tiposHoja = regNomGen.getTiposHoja();
                String tipoRegistroTrabajo = "-" + ipe.getTipoSustantivo() + "-";
                if (!tiposHoja.contains(tipoRegistroTrabajo)) {
                    continue;
                }

                //comparo si el género de los tipos de sustantivo (hoja) del participio encontrados en IRR_PARTICIPIOS_ENTEROS,
                //coincide con los géneros que, para esos tipos de sustantivo, hay en CUBOS_TIPO_PART
                String[] sTsh = tiposHoja.split("-");
                boolean hayUnCuboConGeneroCoincidente = false;

                for (int e = 0; e < sTsh.length; e++) {
                    if (sTsh[e].equals("")) {
                        continue;
                    }
                    Integer th = Integer.parseInt(sTsh[e]);
                    if (hayTipoGenero(generoIrr, th)) {
                        hayUnCuboConGeneroCoincidente = true;
                        break;
                    }
                }

                if (!hayUnCuboConGeneroCoincidente) {
                    if (debug) {
                        sbDebug.append(" al registro siguiente: "
                                + OpBeans.debugBean(regNomGen, new String[] { "TERMINACION", "FORMA_ORIGINAL" }));
                        sbDebug.append("   se le encontró el siguiente registro coincidente en forma en IRR_PARTICIPIOS_ENTEROS : "
                                + OpBeans.debugBean(ipe, new String[] {}));
                        sbDebug
                                .append("   pero su género no coinicde con ninguna de las muestras que, para ese tipo de sustantivo, hay en CUBOS_TIPO_PART \n");
                    }
                    continue;
                }
                encontro = true;

                String idCompuesto = null;
                if (ipe.getPreposiciones().size() > 0) {
                    idCompuesto = gerenteVerbosCompuestos.seleccionaPorVerboSimpleYPreps(idVerbo, ipe.getPreposiciones());
                    if (idCompuesto == null) {
                        continue;
                    } else {
                        idVerbo = idCompuesto;
                    }
                }
                ResultadoUniversal reu = new ResultadoUniversal(TipoPalabra.Participio, idVerbo, null, particCanonica,
                        particIrr, voz, formaAccidentada, null, aspecto, fuerte, null, caso, generoIrr, numero, null,
                        null, formaCanonica, ipe.getPreposiciones(), idCompuesto);
                resultados.add(reu);

                if (caso.equals(Caso.Nominativo)) {
                    //TODO: asumo que todos los nominativos reportados en IRR_PARTICIPIOS_ENTEROS tienen nom=voc, lo 
                    //cual no es consistente con representar un vocativo distinto (en -N) para el tipo 70
                    ResultadoUniversal reuVoc = (ResultadoUniversal) OpBeans.clona(reu);
                    reuVoc.setCaso(Caso.Vocativo);
                    resultados.add(reuVoc);
                }
            }
            if (encontro) {
                quitados.add(regNomGen);
                it.remove();
            }
        }

        if (debug) {
            System.out.println("********************* revisaIrrParticipiosEnteros");
            System.out.println(sbDebug.toString());
            System.out.println("registros agregados a 'resultados' : ");
            amUtil.debugBeans(quitados, new String[] { "formaOriginal", "nominativoPropuesto",
                    "genitivoPropuesto" });
        }

    }

    /**
     * carga una lista de preposiciones en todos los componentes de una lista de IrrParticipiosEnteros
     * @param lista				 
     * @param preposiciones		(en BETA)
     */
    private void cargaPrepsEnListaIpes(List<IrrParticipioEntero> ipes, List<String> preposiciones) {
        for (IrrParticipioEntero ipe : ipes) {
            ipe.setPreposiciones(preposiciones);
        }
    }

    //map de tipo a colección de géneros
    private Map<Integer, Collection<Genero>> mapGeneros;

    /**
     * devuelve verdadero si en la tabla de CUBOS_TIPO_PART hay por lo menos un tipo "tipo" 
     * cuyo género sea "genero"
     * @param tipo
     * @param genero
     * @return
     */
    public boolean hayTipoGenero(Genero genero, Integer tipo) {
        if (mapGeneros == null)
            pueblaMapGeneros();
        Collection<Genero> generos = mapGeneros.get(tipo);
        if (generos == null)
            return false;
        else if (generos.contains(genero))
            return true;
        else
            return false;
    }

    /**
     * puebla el map de tipo-generos, con un tipo como clave y una colección de géneros como elementos
     *
     */
    private void pueblaMapGeneros() {
        mapGeneros = new HashMap<Integer, Collection<Genero>>();
        for (CubosTipoPartBean ctp : this.listaCubosTipoPart) {
            int tipoDm = ctp.getTipoSustantivo();
            Collection<Genero> generos = mapGeneros.get(tipoDm);
            Genero generoDm = ctp.getGenero();
            if (generos == null) {
                generos = new HashSet<Genero>();
                generos.add(generoDm);
                mapGeneros.put(tipoDm, generos);
            } else if (!generos.contains(generoDm)) {
                generos.add(generoDm);
            }
        }

    }

    /**
     * Le agrega al set de resultado la lista de registros que habíamos obtenido después de comparar con irregularidades
     * @param setResultado   el conjunto definitivo de resultados
     * @param lstIrr         la lista (conteniendo claves de tabla VERBOS más información nominal) con 
     *                       resultados producto de buscar irregularidades en IRR_VERBOS 
     */
    public void agregaResultadosIrr(Set<ResultadoUniversal> setResultado, List<TermRegParticipio> lstIrr, boolean debug) {
        StringBuffer sbDebug = new StringBuffer();
        Collection<TermRegParticipio> lstResultados = new HashSet<TermRegParticipio>(lstIrr);
        for (Iterator<TermRegParticipio> it = lstResultados.iterator(); it.hasNext();) {
            TermRegParticipio trp = it.next();

            String idVerbo = trp.getIdVerbo();
            VerboBean regDic = gerenteVerbos.seleccionaIndividualSinSignificado(idVerbo);

            Particularidad particIrr = trp.getParticularidad();
            Particularidad particCanonica = regDic.getParticularidad();
            Aspecto aspecto = TransformadorTiempoAspecto.comoAspecto(trp.getAspecto());
            Voz voz = trp.getVoz();
            FuerteDebil fuerte = trp.getFuerte();
            List<String> preposiciones = trp.getPreposiciones();
            Caso caso = trp.getCaso();
            Genero genero = trp.getGenero();
            Numero numero = trp.getNumero();

            //como a aqué me viene un conjunto de irregularidades que puede contener cualquier tiempo, 
            //filtro aquellas que no sean aspecto (es decir, no 2 ni 6)
            if (!aspecto.equals(Aspecto.Infectivo) && !aspecto.equals(Aspecto.Futuro) && !aspecto.equals(Aspecto.Confectivo)
                    && !aspecto.equals(Aspecto.Perfectivo)) {
                if (debug) {
                    sbDebug.append("  eliminando el siguiente registro por ser una irregularidad no-aspecto \n");
                    sbDebug.append(OpBeans.debugBean(trp, new String[] {}));
                }
                continue;
            }

            ResultadoUniversal reu = new ResultadoUniversal(TipoPalabra.Participio, idVerbo, null, particCanonica,
                    particIrr, voz, trp.getFormaOriginal(), null, aspecto, fuerte, null, caso, genero, numero, null,
                    null, OpPalabras.strBetaACompleto(regDic.getVerbo()), preposiciones, null);

            setResultado.add(reu);
        }
        if (debug) {
            System.out.println("agregaResultadosIrr: ***********************************************");
            System.out.println(sbDebug.toString());
            System.out
                    .println("así queda el resultado después de agregaresultadosIrr");
            amUtil.debugBeans(setResultado, new String[] { "formaOriginal", "temaPropuesto", "terminacion" });
        }
    }

    /**
     * Le agrega al set de resultado la lista de registros que habíamos obtenido después de comparar con irregularidades
     * @param setResultado   el conjunto definitivo de resultados
     * @param lstIrr         la lista (conteniendo claves de tabla VERBOS más información nominal) con 
     *                       resultados producto de buscar irregularidades en IRR_VERBOS 
     */
    public void revisaIrrPartIndividuales(Set<ResultadoUniversal> setResultado, String formaOriginal, boolean debug) {
        StringBuffer sbDebug = new StringBuffer();
        String formaOriginalBeta = OpPalabras.strCompletoABeta(formaOriginal);
        List<IrrParticipioSimpleBean> ips = gerenteIrrParticipiosSimples.seleccionaPorForma(formaOriginalBeta);
        for (IrrParticipioSimpleBean regIrr : ips) {
            String idVerbo = regIrr.getVerboId();
            VerboBean regDic = gerenteVerbos.seleccionaIndividualSinSignificado(idVerbo);

            String formaCanonica = regDic.getVerbo();
            String formaAccidentada = regIrr.getForma();
            Particularidad particIrr = regIrr.getPartic();
            Particularidad particCanonica = regDic.getParticularidad();
            Aspecto aspecto = regIrr.getAspecto();
            Voz voz = regIrr.getVoz();
            FuerteDebil fuerte = regIrr.getFuerte();
            Caso caso = regIrr.getCaso();
            Genero genero = regIrr.getGenero();
            Numero numero = regIrr.getNumero();

            ResultadoUniversal reu = new ResultadoUniversal(TipoPalabra.Participio, idVerbo, null, particCanonica,
                    particIrr, voz, formaAccidentada, null, aspecto, fuerte, null, caso, genero, numero, null,
                    null, formaCanonica, null, null);

            setResultado.add(reu);
        }
        if (debug) {
            System.out.println("revisaIrrPartIndividuales: ***********************************************");
            System.out.println(sbDebug.toString());
            System.out
                    .println("así queda el resultado después de agregarle lo proveniente de la lista de hallazgos en irregularidades");
            amUtil.debugBeans(setResultado, new String[] { "formaOriginal", "temaPropuesto", "terminacion",
                    "formaCanonica" });
        }
    }

    /**
     * toma el set y le borra aquellos registros cuyos tipos de sustantivo tentativos
     * no están entre los tipos permitidos para participios
     * @param set
     */
    private <T extends TermRegNominal> void filtraTiposPosibles(Set<T> set, boolean debug) {
        StringBuffer sbDebug = new StringBuffer();
        for (Iterator<T> it = set.iterator(); it.hasNext();) {
            T trp = it.next();
            String tiposSustHoja = trp.getTiposHoja();
            boolean esta = false;
            for (CubosTipoPartBean ctp : this.listaCubosTipoPart) {
                String infijo = "-" + ctp.getTipoSustantivo() + "-";
                if (tiposSustHoja.indexOf(infijo) > -1) {
                    esta = true;
                    break;
                }
            }
            if (!esta) {
                if (debug)
                    sbDebug.append(" quitando " + OpBeans.debugBean(trp, new String[] {}) + "\n");
                it.remove();
            }
        }
        if (debug) {
            System.out.println("filtraTiposPosibles ********************************************");
            System.out.println(sbDebug.toString());
            System.out.println("el set después de quitarle los tipos no-participio");
            amUtil.debugBeans(set, new String[] {});
        }

    }

    /**
     * Ya habiendo dotado de voz,género y aspecto a los registros, borro aquéllos que no coincidan 
     * con la lista de tiposPosibles para dichas voz, género y aspecto.
     * Recorre el model de tipos posibles una vez más
     */
    private void filtraGeneroVozAspecto(Set<TermRegParticipio> set, boolean debug) {
        for (Iterator<TermRegParticipio> it = set.iterator(); it.hasNext();) {
            TermRegParticipio reg = it.next();
            if (!algunoTieneGeneroVozAspecto(reg)) {
                if (debug) {
                    System.out.println("  quitando este registro porque no pasa el test de CUBOS_TIPOS_PART");
                }
                it.remove();
            }
        }
        if (debug) {
            System.out.println("filtraGeneroVozAspecto *************");
            System.out.println("el set después de filtrar por tipo nominal en combinación con género, aspecto y voz");
            amUtil.debugBeans(set, new String[] { "formaOriginal", "terminacionVerbalizada" });
        }
    }

    /**
     * Función utilitaria de filtraGeneroVozAspecto.
     * Dado un registro de los que van camino a ser reconstruidos, revisa sus TIPOS_SUST_HOJA
     * uno por uno contra la tabla de CUBOS_TIPO_PART, y verifica que al menos uno de los tipos-hoja
     * corresponda al género, voz, y aspect que para ese tipo se especifica en la tabla.
     * @param reg
     * @param genero
     * @param voz
     * @param aspecto
     * @return
     */
    private boolean algunoTieneGeneroVozAspecto(TermRegParticipio reg) {
        String tiposHoja = reg.getTiposHoja();
        String[] arrTiposHoja = tiposHoja.split("-");
        boolean coincide = false;
        for (int i = 0; i < arrTiposHoja.length; i++) {
            if (arrTiposHoja[i].trim().equals(""))
                continue;
            for (CubosTipoPartBean regCubos : this.listaCubosTipoPart) {
                int tipoCubos = regCubos.getTipoSustantivo();
                String sTipoCubos = "-".concat(Integer.toString(tipoCubos)).concat("-");
                Genero generoCubos = regCubos.getGenero();
                Aspecto aspectoCubos = regCubos.getAspecto();
                Voz vozCubos = regCubos.getVoz();
                if (tiposHoja.contains(sTipoCubos) && generoCubos.equals(reg.getGenero())
                        && vozCubos.equals(reg.getVoz()) && aspectoCubos.equals(reg.getAspecto())) {
                    coincide = true;
                    break;
                }
            }
        }
        return coincide;
    }

    /**
     * Este método trabaja con la tabla TRANS_PARTICIPIOS, la cual contiene terminaciones varias del nominativo
     * y genitivo de participios, y en base a ellas y al aspecto/voz/genero en que se encuentran, sugiere qué
     * hay que substraerle a la forma de entrada para transformarla en una forma canónica (verbo en 1ps).
     * Alternativamente, dicha tabla puede sugerir que lo que hay que hacer en realidad es buscar por tema en la
     * tabla de irregulairdades. 
     * 
     * Toma el campo TEMA_PROPUESTO+TERMINACION, que contiene un tema nominal de participio 
     * ya desreduplicado, por ejemplo PAIDEU+WN, o PAIDEU+KWN o PAIDEUOUS+A;
     * y, según el género-voz-aspecto, le resto o transformo lo que haga falta del final para
     * obtener una forma verbal canónica. Dicha forma queda en un campo llamado FORMA_CANONICA
     * 
     * @param setOriginal					los registros a analizar
     * @param desTransformaciones			las des-transformaciones de tema que se aplicaron al set original
     * @param setSiguiente                  (de salida) contendrá los registros que siguen el camino hacia la canónica, 
     *                        				con su campo FORMA_CANONICA poblado
     * @param setTemas						(de salida) con temas a buscar en irregularidades
     * @param desTransformacionesTemas      (de salida) con las des-transformaciones de los temas a buscar
     * 										en irregularidades (un subconjunto de desTransformaciones, sin modificar)
     * @param debug								
     */
    private void temaPropuestoACanonica(Set<ObjYDest> setOriginal, Set<TermRegParticipio> setSiguiente, List<ObjYDest> lstTemas, AACacheable cacheAA, boolean debug) {
        Map<String, List<TransParticipiosBean>> busquedasOrig = new HashMap<String, List<TransParticipiosBean>>();
        StringBuffer sbDebug = new StringBuffer();
        sbDebug.append("hay " + setOriginal.size() + " registros a analizar");
        for (ObjYDest regDest : setOriginal) {
            TermRegParticipio reg = (TermRegParticipio) regDest.getRegistro();
            DesTransformaciones dest = regDest.getDestransformacion();

            String formaDestransformada = null;

            formaDestransformada = reg.getFormaDestransformada();
            Espiritu espirituDestransf = OpPalabras.getEspiritu(formaDestransformada);
            AnalisisAcento aaDestr = cacheAA.getAnalisisAcento(formaDestransformada);

            List<TransParticipiosBean> dm = null;
            dm = busquedasOrig.get(formaDestransformada);
            if (dm == null) {
                String substraer = OpPalabras.strCompletoABeta(formaDestransformada);
                dm = gerenteTransParticipios.seleccionaPorSubstraer(substraer);
                sbDebug.append("  consultando TRANS_PARTICIPIOS por SUBSTRAER=" + substraer + " obtuve " + dm.size() + " resultados\n");
                busquedasOrig.put(formaDestransformada, dm);
            }

            String formaDestransformadaBeta = OpPalabras.strCompletoABeta(reg.getFormaDestransformada());
            int filas = dm.size();
            if (debug) {
                sbDebug.append(filas + "  filas para el registro registro reconstruible: ");
                sbDebug.append(OpBeans.debugBean(reg, new String[] { "formaOriginal", "terminacionVerbalizada", "formaADestransformar",
                        "formaDestransformada", "nominativoPropuesto", "genitivoPropuesto" }) + " \n");
            }

            //Registros con "AGREGAR" tratan de formar una forma canónica. Registros con "AGREGAR_TEMA" tratan de formar un tema para IRR_VERBOS 

            for (TransParticipiosBean tpb : dm) {
                if (!comparacionAceptable(reg, tpb, sbDebug, debug)) {
                    continue;
                }
                if (!posicionSubstractaAceptable(tpb, reg, aaDestr, sbDebug, debug)) {
                    continue;
                }

                //la regular expression
                String regexp = tpb.getRegexOrig();
                if (regexp != null && !formaDestransformadaBeta.matches(regexp)) {
                    if (debug) {
                        sbDebug.append("    la regexp del registro de TRANS_PARTICIPIOS=" + regexp
                                + " no concuerda con la forma que se quiere reconstruir=" + formaDestransformadaBeta
                                + " \n");
                    }
                    continue;
                }

                int tamañoSubstraccion = OpPalabras.strBetaACompleto(tpb.getSubstraer()).length();
                String agregar = tpb.getAgregar();
                String agregarTema = tpb.getAgregarTema();
                TermRegParticipio aAgregar = (TermRegParticipio) OpBeans.clona(reg);
                aAgregar.setTipoVerboExtendido(tpb.getTipoVerbo());

                //si "agregar" es lo que es <> nulo en la fila del TRANS_PARTICIPIO, el resultado intenta transformarse en canónica
                if (agregar != null) {
                    String formaCanonica = formaDestransformada.substring(0, formaDestransformada.length()
                            - tamañoSubstraccion);
                    formaCanonica += OpPalabras.strBetaACompleto(tpb.getAgregar());
                    Acento agregarAcento = tpb.getAgregarAcento();
                    Integer agregarPosicion = tpb.getAgregarPosicion();
                    if (agregarAcento != null) {
                        formaCanonica = OpPalabras.desacentuar(formaCanonica);
                        formaCanonica = OpPalabras.acentua(formaCanonica, agregarPosicion.intValue(), agregarAcento);
                    }
                    formaCanonica = OpPalabras.espirituPalabra(formaCanonica, espirituDestransf); //restauro espíritu
                    aAgregar.setFormaCanonica(formaCanonica);
                    setSiguiente.add(aAgregar);
                }

                //si "agregarTema" es lo que es <> nulo en la fila del TRANS_PARTICIPIO, el resultado se suma a la lista de cosas 
                //a buscar en la tabla de irregularidades
                if (agregarTema != null) {
                    String tema = formaDestransformada.substring(0, formaDestransformada.length() - tamañoSubstraccion);
                    tema += OpPalabras.strBetaACompleto(agregarTema);
                    tema = OpPalabras.espirituPalabra(tema, espirituDestransf); //restauro espíritu
                    aAgregar.setTemaPropuesto(tema);

                    //también agrego el contenido de la columna "juego", si éste es no nulo, porque eso implicará que
                    //en la búsqueda de irregulairdades sólo ese juego de desinencias estará permitido
                    Integer juego = tpb.getJuego();
                    if (juego != null)
                        aAgregar.setJuego(juego);
                    aAgregar.setContraccionComedora(tpb.getContraccionComedora());

                    ObjYDest nuevaRegDest = new ObjYDest(aAgregar, dest);
                    lstTemas.add(nuevaRegDest);
                }
            }
        }
        if (debug) {
            System.out
                    .println("temaPropuestoACanonica (Interacción con TRANS_PARTICIPIOS ************************************");
            System.out.println(sbDebug.toString());
            System.out.println("el set de canónicas después de agregarle FORMA_CANONICA***");
            amUtil.debugBeans(setSiguiente, new String[] { "formaCanonica", "formaDeclinada", "formaOriginal",
                    "temaPropuesto", "genitivoPropuesto", "nominativoPropuesto", "formaADestransformar",
                    "formaDestransformada" });
            System.out.println("el set de temas propuestos a buscar en irregularidades***");
            amUtil.debugSet(lstTemas, new String[] { "formaCanonica", "formaDeclinada", "formaOriginal",
                    "temaPropuesto", "genitivoPropuesto", "nominativoPropuesto", "formaADestransformar",
                    "formaDestransformada" });
        }
    }

    /**
     * Trata las formas que no pudieron ser des-transformadas naturalmente desde el vamos por 
     * destransformacionesDeTemas. Lo que recupera se compara con TRANS_PARTICIPIOS, pero sólo 
     * activando la columna AGREGAR_TEMA
     * 
     * @param temasDescartados
     * @param lstTemasIrr
     * @param debug
     */
    private void trataDescarteDestransformacion(List<ObjYDest> temasDescartados, List<ObjYDest> lstTemasIrr, AACacheable cacheAA, boolean debug) {
        Map<String, List<TransParticipiosBean>> busquedasOrig = new HashMap<String, List<TransParticipiosBean>>();
        StringBuffer sbDebug = new StringBuffer();

        for (ObjYDest regDest : temasDescartados) {
            TermRegParticipio reg = (TermRegParticipio) regDest.getRegistro();
            DesTransformaciones dest = regDest.getDestransformacion();

            String formaDestransformada = null;

            formaDestransformada = reg.getFormaDestransformada();
            Espiritu espirituDestransf = OpPalabras.getEspiritu(formaDestransformada);
            AnalisisAcento aaDestr = cacheAA.getAnalisisAcento(formaDestransformada);

            List<TransParticipiosBean> dm = null;
            dm = busquedasOrig.get(formaDestransformada);
            String formaDestransformadaBeta = OpPalabras.strCompletoABeta(reg.getFormaDestransformada());
            if (dm == null) {
                dm = gerenteTransParticipios.seleccionaPorSubstraer(formaDestransformadaBeta);
                busquedasOrig.put(formaDestransformada, dm);
            }

            int filas = dm.size();
            if (debug) {
                sbDebug.append(filas
                        + " filas para el registro registro reconstruible"
                        + OpBeans.debugBean(reg, new String[] { "formaOriginal", "terminacionVerbalizada",
                                "formaADestransformar", "formaDestransformada", "genitivoPropuesto" }) + "\n");
            }

            //Registros con "AGREGAR" tratan de formar una forma canónica. Registros con "AGREGAR_TEMA" tratan de formar un tema para IRR_VERBOS 

            for (TransParticipiosBean regDm : dm) {
                if (!comparacionAceptable(reg, regDm, sbDebug, debug)) {
                    continue;
                }
                if (!posicionSubstractaAceptable(regDm, reg, aaDestr, sbDebug, debug)) {
                    continue;
                } else {

                }

                //la regular expression
                String regexp = regDm.getRegexOrig();
                if (regexp != null && !formaDestransformadaBeta.matches(regexp)) {
                    if (debug) {
                        sbDebug.append("    la regexp del registro de TRANS_PARTICIPIOS=" + regexp
                                + " no concuerda con la forma que se quiere reconstruir=" + formaDestransformadaBeta
                                + " \n");
                    }
                    continue;
                }

                int tamañoSubstraccion = OpPalabras.strBetaACompleto(regDm.getSubstraer()).length();
                String agregarTema = regDm.getAgregarTema();
                TermRegParticipio aAgregar = (TermRegParticipio) OpBeans.clona(reg);
                aAgregar.setTipoVerboExtendido(regDm.getTipoVerbo());

                //si "agregarTema" es lo que es <> nulo en la fila del TRANS_PARTICIPIO, el resultado se suma a la lista de cosas 
                //a buscar en la tabla de irregularidades
                if (agregarTema != null) {
                    String tema = formaDestransformada.substring(0, formaDestransformada.length() - tamañoSubstraccion);
                    tema += OpPalabras.strBetaACompleto(agregarTema);
                    tema = OpPalabras.espirituPalabra(tema, espirituDestransf); //restauro espíritu
                    aAgregar.setTemaPropuesto(tema);

                    //también agrego el contenido de la columna "juego", si éste es no nulo, porque eso implicará que
                    //en la búsqueda de irregulairdades sólo ese juego de desinencias estará permitido
                    Integer juego = regDm.getJuego();
                    if (juego != null)
                        aAgregar.setJuego(juego);
                    aAgregar.setContraccionComedora(regDm.getContraccionComedora());

                    lstTemasIrr.add(new ObjYDest(aAgregar, dest));
                }
            }
        }
        if (debug) {
            System.out
                    .println("trataDescarteDestransformacion (Interacción con TRANS_PARTICIPIOS ************************************");
            System.out.println(sbDebug.toString());
            System.out.println("la lista de temas irregulares a buscar en IRR_VERBOS");
            amUtil.debugSet(lstTemasIrr, new String[] { "formaCanonica", "formaDeclinada", "formaOriginal",
                    "temaPropuesto", "genitivoPropuesto", "nominativoPropuesto", "terminacion", "formaADestransformar",
                    "formaDestransformada" });
        }
    }

    /**
     * Compara el registro reconstruido con registros de la tabla TRANS_PARTICIPIOS, para determinar si reúnen la 
     * suficiente similaridad.
     * @param reconstruido
     * @param deTablaTrans
     * @return
     */
    private boolean comparacionAceptable(TermRegParticipio reconstruido, TransParticipiosBean deTablaTrans, StringBuffer sbDebug,
            boolean debug) {
        //evalúo si es aceptable utilizar esta substracción: la voz, el aspecto y el género propuestos por
        //la tabla deben ser los mismos que los del registro a tratar
        Voz vozReg = reconstruido.getVoz();
        FuerteDebil fuerteReg = reconstruido.getFuerte();
        Aspecto aspectoReg = TransformadorTiempoAspecto.comoAspecto(reconstruido.getAspecto());
        Genero generoReg = reconstruido.getGenero();
        Voz vozTabla = deTablaTrans.getVoz();
        Aspecto aspectoTabla = deTablaTrans.getAspecto();
        FuerteDebil fuerteTabla = deTablaTrans.getFuerte();
        Genero generoTabla = deTablaTrans.getGenero();

        boolean vozOK = vozReg.equals(vozTabla);
        boolean aspectoOK = aspectoReg.equals(aspectoTabla) && fuerteReg.equals(fuerteTabla);
        boolean generoOK = generoReg.equals(generoTabla);

        if (vozOK && aspectoOK && generoOK)
            return true;
        else {
            if (debug) {
                if (!vozOK) {
                    sbDebug
                            .append("     comparacionAceptable: se rechazó por VOZ el siguiente registro de TRANS_PART: ");
                } else if (!aspectoOK) {
                    sbDebug
                            .append("     comparacionAceptable: se rechazó por ASPECTO el siguiente registro de TRANS_PART: ");
                } else if (!generoOK) {
                    sbDebug
                            .append("     comparacionAceptable: se rechazó por GENERO el siguiente registro de TRANS_PART: ");
                }
                sbDebug.append("     " + OpBeans.debugBean(deTablaTrans, new String[] {}) + "\n");
            }
            return false;
        }
    }

    /**
     * función utilitaria llamada desde temaPropuestoACanonica, para limitar aún más la producción de formas.
     * Compara la forma acentuada a analizar con la posición del acento que, según la tabla TRANS_PART, la 
     * forma a la que se substrajo debería tener. Devolver falso significa que ese registro de la tabla TRANS_PART no
     * sirve, que la operación no es aceptable. 
     * 
     * @param deTransPart     el registro de la tabla TRANS_PART
     * @param reconstruido    el regiostro reconstruido, que contiene a la forma a trabajar
     * @param aaDestr         el análisis acento de la forma original, que ya está hecho desde la rutina llamadora
     * @param debug           variable que indica si vuelco resultados a la consola
     * @return                true si la comparación es aceptable, false si no
     */
    private boolean posicionSubstractaAceptable(TransParticipiosBean deTransPart, TermRegParticipio reconstruido, AnalisisAcento aaDestr,
            StringBuffer sbDebug, boolean debug) {
        //posición del acento en la forma original
        Integer posicionSubstracta = deTransPart.getSubstractaPosicion();
        if (posicionSubstracta != null) {
            if ((posicionSubstracta.intValue() < 0 && aaDestr.actuales.silaba != posicionSubstracta.intValue())
                    || (posicionSubstracta.intValue() > 0 && aaDestr.actuales.silabaB1 != posicionSubstracta.intValue())) {
                if (debug) {
                    sbDebug
                            .append("     posicionSubstractaAceptable: se rechazó por la POSICIÓN DEL ACENTO en la forma original el siguiente registro de TRANS__PART: ");
                    sbDebug.append("     " + deTransPart.toString());
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Recorre el campo "formaCanonica" del set, buscando iguales en las formas fáciles de  la tabla de de diccionarios.
     * Con los hallazgos puebla el set de resultados.
     * @param setOriginal
     */
    private void comparaReconstruidosConTabla(Set<TermRegParticipio> setOriginal, Set<ResultadoUniversal> setSiguiente, boolean pasivar, boolean debug) {
        Map<String, List<VerboBean>> cacheBusquedasOriginales = new HashMap<String, List<VerboBean>>();
        for (TermRegParticipio reg : setOriginal) {
            String formaCanonica = reg.getFormaCanonica();
            if (pasivar)
                formaCanonica = OpPalabras.canonicaAMedia(formaCanonica);

            //			boolean esOriginal=((caso==Caso.Nominativo || caso==Caso.Genitivo) && numero==Numero.Singular);

            List<VerboBean> verbos = null;
            formaCanonica = OpPalabras.strCompletoABeta(formaCanonica);
            verbos = cacheBusquedasOriginales.get(formaCanonica);
            if (verbos == null) {
                verbos = gerenteVerbos.seleccionaPorVerboParaAM(formaCanonica);
                cacheBusquedasOriginales.put(formaCanonica, verbos);
            }
            //			}
            for (VerboBean regDm : verbos) {
                String idVerbo = regDm.getId();
                Particularidad particCanonica = regDm.getParticularidad();
                Particularidad particIrr = reg.getParticularidad();
                String formaAccidentada = reg.getFormaOriginal();
                String formaCanonica2 = regDm.getVerbo();
                Voz voz = reg.getVoz();
                if (pasivar && voz.equals(Voz.Media))
                    voz = Voz.Activa;
                Aspecto aspecto = TransformadorTiempoAspecto.comoAspecto(reg.getAspecto());
                FuerteDebil fuerte = reg.getFuerte();
                Caso caso2 = reg.getCaso();
                Genero genero = reg.getGenero();
                Numero numero2 = reg.getNumero();
                List<String> preposiciones = reg.getPreposiciones();

                ResultadoUniversal reu = new ResultadoUniversal(
                        TipoPalabra.Participio, idVerbo, null, particCanonica, particIrr,
                        voz, formaAccidentada, null, aspecto, fuerte, null, caso2, genero, numero2,
                        null, null, OpPalabras.strBetaACompleto(formaCanonica2), preposiciones, null
                        );
                setSiguiente.add(reu);
            }
        }
        if (debug) {
            System.out
                    .println("el set resultado después comparaReconstruidosConTabla (búsqueda en la tabla de verbos)");
            amUtil.debugSet(setSiguiente, new String[] { "formaAccidentada", "formaCanonica" });
        }

    }

    /**
     * Si la terminación tentativa es un nominativo o un genitivo, los pongo en el set nomGenDisponibles 
     * y los borro del set original
     * @param setOriginal
     * @param setSiguiente
     * @param debug
     */
    private void revisaNomGen(Collection<TermRegSustantivo> setOriginal, Set<TermRegSustantivo> nomGenDisponibles, boolean debug) {
        Set<TermRegSustantivo> aBorrar = new HashSet<TermRegSustantivo>();
        nomGenDisponibles.clear();
        for (TermRegSustantivo reg : setOriginal) {
            reg = (TermRegSustantivo) OpBeans.clona(reg);

            Caso caso = reg.getCaso();
            Numero numero = reg.getNumero();

            //si el tema utilizado es nom.sing. o gen.sing., no me molesto en des-transformar nada,
            //simplemente busco en la tabla a ver si está y si no descarto esta posibilidad

            if ((caso.equals(Caso.Genitivo) || caso.equals(Caso.Nominativo)) && numero.equals(Numero.Singular)) {
                nomGenDisponibles.add(reg);
                aBorrar.add(reg);
            }
        }
        setOriginal.removeAll(aBorrar);

        if (debug) {
            System.out.println("el set de entrada después de borrarle los nom/gen");
            amUtil.debugSet(setOriginal, new String[] {});
            System.out.println("** y éste es el contenido de nomGenDisponibles: ");
            amUtil.debugBeans(nomGenDisponibles, new String[] { "formaOriginal" });
        }
    }

    /**
     * Usa la tabla VERBALIZACION_PARTICIPIOS
     * a los registros del set de entrada les agrega los campos "VOZ", "ASPECTO", "GENERO"  y "TIPO_VERBO", que 
     * se pueblan en base a las terminaciones registradas en la tabla TERM_REG_PART
     * 
     * La terminación considerada para "verbalizar" (es decir, la TERM_NOM o TERM_GEN considerada), se 
     * agrega en el campo TERM_VERBALIZADA (ej: "KWJ", que contiene caracteréstica de aspecto).
     * 
     * Nótese que este método sólo funciona con registros que tienen nominativo o genitivo singular.
     * @param nomgen
     */
    private void inyeccionCamposVerbales(List<TermRegParticipio> nomgen, Set<TermRegParticipio> setSiguiente, boolean debug) {
        //abro el DMTermRegPart independentemente. Se lo recorrerá en su totalidad
        //en cada pasada

        StringBuffer sbDebug = new StringBuffer();
        for (TermRegParticipio reg : nomgen) {

            Caso caso = reg.getCaso();
            Numero numero = reg.getNumero();
            String genitivo = null;
            String nominativo = null;

            if (caso.equals(Caso.Genitivo) && numero.equals(Numero.Singular))
                genitivo = reg.getFormaOriginal();
            else if (caso.equals(Caso.Nominativo) && reg.getNumero().equals(Numero.Singular))
                nominativo = reg.getFormaOriginal();
            else if (reg.getGenitivoPropuesto() != null) {
                genitivo = reg.getGenitivoPropuesto();
            } else if (reg.getNominativoPropuesto() != null) {
                nominativo = reg.getNominativoPropuesto();
            }

            List<VerbalizadorBean> dm = null;

            if (genitivo != null) {
                genitivo = OpPalabras.strCompletoABeta(genitivo);
                dm = gerenteVerbalizadorParticipios.seleccionaPorTerminacionGenitivo(genitivo);

            } else if (nominativo != null) {
                nominativo = OpPalabras.strCompletoABeta(nominativo);
                dm = gerenteVerbalizadorParticipios.seleccionaPorTerminacionNominativo(nominativo);
            }

            for (VerbalizadorBean regDm : dm) {
                if (genitivo != null) {
                    TermRegParticipio regAAgregar = (TermRegParticipio) OpBeans.clona(reg);
                    regAAgregar.setVoz(regDm.getVoz());
                    regAAgregar.setAspecto(regDm.getAspecto());
                    regAAgregar.setFuerte(regDm.getFuerte());
                    regAAgregar.setGenero(regDm.getGenero());
                    regAAgregar.setTipoVerbo(regDm.getTipoVerbo());
                    regAAgregar.setTerminacionVerbalizada(OpPalabras.strBetaACompleto(regDm.getTerminacionGenitivo()));
                    setSiguiente.add(regAAgregar);
                } else if (nominativo != null) {
                    TermRegParticipio regAAgregar = (TermRegParticipio) OpBeans.clona(reg);
                    regAAgregar.setVoz(regDm.getVoz());
                    regAAgregar.setAspecto(regDm.getAspecto());
                    regAAgregar.setFuerte(regDm.getFuerte());
                    regAAgregar.setGenero(regDm.getGenero());
                    regAAgregar.setTipoVerbo(regDm.getTipoVerbo());
                    regAAgregar.setTerminacionVerbalizada(OpPalabras.strBetaACompleto(regDm.getTerminacionNominativo()));
                    setSiguiente.add(regAAgregar);
                }
            }
        }

        //pasada para eliminar tipos que no correspondan con su género
        //borro los que tengan los tipos hoja neutros para en una inyección no neutra
        for (Iterator<TermRegParticipio> it = setSiguiente.iterator(); it.hasNext();) {
            TermRegParticipio reg = it.next();
            Genero genero = reg.getGenero();
            if (!genero.equals(Genero.Neutro)
                    && amNominal.todosTiposSonDelGenero(reg.getTiposHoja(), amNominal.tiposNeutros)) {
                if (debug) {
                    sbDebug.append("  quitando el siguiente registro porque el género no es neutro: \n");
                    sbDebug.append("    " + OpBeans.debugBean(reg, new String[] { "formaOriginal", "nominativoPropuesto", "genitivoPropuesto" }) + "\n");
                }
                it.remove();
            } else if ((!genero.equals(Genero.Masculino) && !genero.equals(Genero.Femenino))
                    && amNominal.todosTiposSonDelGenero(reg.getTiposHoja(), amNominal.tiposNoNeutros)) {
                if (debug) {
                    sbDebug.append("  quitando el siguiente registro porque el género no es masc/fem: \n");
                    sbDebug.append("    " + OpBeans.debugBean(reg, new String[] { "formaOriginal", "nominativoPropuesto", "genitivoPropuesto" }) + "\n");
                }
                it.remove();
            }
        }

        if (debug) {
            System.out
                    .println("inyeccionCamposVerbales (agregar VOZ-ASPECTO-GENERO de TERM_REG_PART )************************");
            System.out.println(sbDebug.toString());
            amUtil.debugBeans(setSiguiente, new String[] { "formaOriginal", "nominativoPropuesto", "genitivoPropuesto", "terminacionVerbalizada" });
        }

    }

    /**
     * @param amNominal The amNominal to set.
     */
    public void setAmNominal(AMNominal amNominal) {
        this.amNominal = amNominal;
    }

    /**
     * @param gerenteCubosTipoPart The gerenteCubosTipoPart to set.
     */
    public void setGerenteCubosTipoPart(GerenteCubosTipoPart gerenteCubosTipoPart) {
        this.gerenteCubosTipoPart = gerenteCubosTipoPart;
        this.listaCubosTipoPart = this.gerenteCubosTipoPart.seleccionaTodos();
    }

    /**
     * @param gerenteIrrParticipiosSimples The gerenteIrrParticipiosSimples to set.
     */
    public void setGerenteIrrParticipiosSimples(GerenteIrrParticipiosSimples gerenteIrrParticipiosSimples) {
        this.gerenteIrrParticipiosSimples = gerenteIrrParticipiosSimples;
    }

    /**
     * @param listaCubosTipoPart The listaCubosTipoPart to set.
     */
    public void setListaCubosTipoPart(List<CubosTipoPartBean> listaCubosTipoPart) {
        this.listaCubosTipoPart = listaCubosTipoPart;
    }

    /**
     * @param extractorPrefijos the extractorPrefijos to set
     */
    public void setExtractorPrefijos(ExtractorPrefijos extractorPrefijos) {
        this.extractorPrefijos = extractorPrefijos;
    }

    /**
     * @param gerenteVerbosCompuestos the gerenteVerbosCompuestos to set
     */
    public void setGerenteVerbosCompuestos(GerenteVerbosCompuestos gerenteVerbosCompuestos) {
        this.gerenteVerbosCompuestos = gerenteVerbosCompuestos;
    }

}
