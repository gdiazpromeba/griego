package com.kalos.flexion.declinacion;

import static com.kalos.enumeraciones.CompLetras.cAlfaCorta;
import static com.kalos.enumeraciones.CompLetras.cEpsilon;
import static com.kalos.enumeraciones.CompLetras.cIotaCorta;
import static com.kalos.enumeraciones.CompLetras.cOmega;
import static com.kalos.enumeraciones.CompLetras.cOmicron;
import static com.kalos.enumeraciones.CompLetras.cUpsilonCorta;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kalos.beans.IrrSustantivoBean;
import com.kalos.beans.SustantivoBean;
import com.kalos.datos.gerentes.GerenteIrrSustantivos;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteTiposSustantivo;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Espiritu;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.OrigenTema;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.fonts.CarPos;
import com.kalos.operaciones.AnalisisAcento;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title: Kalos
 * </p>
 * <p>
 * Description: Greek verb conjugation and research tool
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class Declina  {
	
	Logger logger=Logger.getLogger(this.getClass());

    private GerenteTiposSustantivo gerenteTiposSustantivo;

    private GerenteSustantivos gerenteSustantivos;

    private GerenteIrrSustantivos gerenteIrrSustantivos;

    private ManejaDesinenciasSustantivo manejaDesinenciasSustantivo;
    

    public void pueblaConIrregularidades(OcNominal oc, String idSustantivo, Particularidad particularidad) {
    	List<IrrSustantivoBean> irrs=gerenteIrrSustantivos.seleccionaPorSustantivoYPartic(idSustantivo, particularidad);
    	
        for (IrrSustantivoBean irs: irrs) {
            oc.setFormaIndividual(irs.getCaso(), irs.getNumero(), irs.getForma(), irs.getSubindice());
        }
    }

    // devuelve un map cuya clave es la particularidad y cuyo contenido son las
    // ocurrencias
    // de sustantivo, adecuadamente completadas
    // con valores de la tabla de irregularidades
    public EnumMap<Particularidad, OcNominal> declina(String sustantivoId) {
        // lo primero que hago es obtener las irregularidades de ese sustantivo
        List<Particularidad> pacs = gerenteSustantivos.seleccionaPartics(sustantivoId);
        EnumMap<Particularidad, OcNominal> mapIndexIrr = new EnumMap<Particularidad, OcNominal>(Particularidad.class);
        for (Particularidad partic : pacs) {
            mapIndexIrr.put(partic, new OcNominal());
        }
        // luego obtengo una declinacion regular (o lo que sea que es la
        // particularidad del registro en SUSTANTIVOS) , y reemplazo/agrego en
        // el map
        // esto sólo ocurre bajo determinadas condiciones (que no sea dibujado y
        // que tenga una combinación válida)
        SustantivoBean reg = gerenteSustantivos.seleccionaUno(sustantivoId);

        OpBeans.pasaDeBetaACompleto(reg, new String[]{"nominativo", "genitivo"});
        int tipoSustantivo = reg.getTipoNominal();
        boolean pluralizado = reg.isPluralizado();
        boolean soloSingular = reg.isSoloSingular();
        boolean sinDeclinacion = (tipoSustantivo == 4);
        boolean dibujado = reg.isDibujado();
        if (!dibujado && !sinDeclinacion) {
            OcNominal ocAux = declinaRegular(reg.getNominativo(), reg.getGenitivo(), tipoSustantivo, pluralizado, soloSingular);
            mapIndexIrr.put(reg.getPartic(), ocAux);
        }

        // luego completo el map con las Ocurrencias basadas en irregularidades
        // sueltas de la base de datos
        Set<Particularidad> setParticsIndice = mapIndexIrr.keySet();
        for (Particularidad partic: setParticsIndice) {
          List<IrrSustantivoBean> irrSusts = gerenteIrrSustantivos.seleccionaPorSustantivoYPartic(sustantivoId,
                    partic);
            for (IrrSustantivoBean regAux : irrSusts) {
                OcNominal oc = (OcNominal) mapIndexIrr.get(partic);
                String forma=OpPalabras.strBetaACompleto(regAux.getForma());
                oc.setFormaIndividual(regAux.getCaso(), regAux.getNumero(), forma, regAux.getSubindice());
            }
        }

        return mapIndexIrr;

    }

    /**
     * asigna el gerente de tipos, que es usado para construir el mapa
     * tipos-tiposid
     * 
     * @param gerenteTiposSustantivo
     */
    public void setGerenteTiposSustantivo(GerenteTiposSustantivo gerenteTiposSustantivo) {
        this.gerenteTiposSustantivo = gerenteTiposSustantivo;
        mapTiposID = this.gerenteTiposSustantivo.getMapaTiposID();
    }

    /**
     * mapa para transformar los tipos de adjetivo enteros, legibles en cadenas
     * de caracteres de 32
     */
    private Map<Integer, String> mapTiposID;

    /**
     * devuelve un tipo adjetivo del tipo 32 caracteres.
     * 
     * @param tipo
     * @return
     */
    public String aChar32(int tipo) {
        return mapTiposID.get(tipo);
    }

    /**
     * devuelve un tipo nominal entero, dado el tipo de 32 caracteres
     * 
     * @param tipoId
     * @return
     */
    public int aInt(String tipoId) {
        for (Map.Entry<Integer, String> entrada : mapTiposID.entrySet()) {
            String idTipo = entrada.getValue();
            if (idTipo.equals(tipoId)) {
                return entrada.getKey();
            }
        }
        throw new RuntimeException("id de tipo de adjetivo inválido");
    }

    /**
     * llena la ocurrencia de un sustantivo de tipo invariable con la forma que
     * se recibe como parámetro
     * 
     * @param oc
     * @param forma
     */
    private static void llenaInvariables(OcNominal oc, String forma) {
        for (int iCaso = 1; iCaso <= 5; iCaso++) {
            Caso caso = Caso.getEnum(iCaso);
            for (int iNum = 1; iNum <= 3; iNum++)
                oc.setFormaIndividual(caso, Numero.getEnum(iNum), forma);
        }
    }



    /**
     * flexiona la forma nominal sólo basándose en la información de la entrada, sin 
     * involucrar irregularidades de tabla
     * @param nominativo
     * @param genitivo
     * @param tipoSustantivo
     * @param pluralizado
     * @param soloSingular
     * @return
     */
    public OcNominal declinaRegular( String nominativo, String genitivo, int tipoSustantivo, boolean pluralizado,
            boolean soloSingular) {
        OcNominal ocAux = new OcNominal();

        // si es invariable, simplemente lleno todo con el nominativo
        if (tipoSustantivo == 0) {
            llenaInvariables(ocAux, nominativo);
            return ocAux;
        }

        
        AnalisisAcento[] arrAA = new AnalisisAcento[2];
        arrAA[0] = AnalisisAcento.getAnalisisAcento(nominativo);
        arrAA[1] = AnalisisAcento.getAnalisisAcento(genitivo);
        
        
        Map<OrigenTema, String> temas = preparaTemas(nominativo, genitivo, tipoSustantivo);

        // normalmente el nominativo y el genitivo singulares son uno cada uno y
        // se ponen directamente.
        // Ocasionalmente (genitivos de omega-digamma), tienen más de una
        // desinencia y tienen que tratarse como todo el resto
        Numero numeroDesinencia=pluralizado?Numero.Plural:Numero.Singular;
        if (manejaDesinenciasSustantivo.getDesinencia(tipoSustantivo, numeroDesinencia, Caso.Nominativo).length > 1) {
            pone(Caso.Nominativo, Numero.Singular, pluralizado, temas, arrAA, tipoSustantivo, ocAux);
        } else {
            ocAux.setFormaIndividual(Caso.Nominativo, Numero.Singular, nominativo);
        }

        if (manejaDesinenciasSustantivo.getDesinencia(tipoSustantivo,  numeroDesinencia, Caso.Genitivo).length > 1) {
            pone(Caso.Genitivo, Numero.Singular, pluralizado, temas, arrAA, tipoSustantivo, ocAux);
        } else {
            ocAux.setFormaIndividual(Caso.Genitivo, Numero.Singular, genitivo);
        }

        pone(Caso.Vocativo, Numero.Singular, pluralizado, temas, arrAA, tipoSustantivo, ocAux);
        pone(Caso.Acusativo, Numero.Singular, pluralizado, temas, arrAA, tipoSustantivo, ocAux);
        pone(Caso.Dativo, Numero.Singular, pluralizado, temas, arrAA, tipoSustantivo, ocAux);

        if (!soloSingular && !pluralizado) {
            pone(Caso.Nominativo, Numero.Plural, false, temas, arrAA, tipoSustantivo, ocAux);
            pone(Caso.Vocativo, Numero.Plural, false, temas, arrAA, tipoSustantivo, ocAux);
            pone(Caso.Acusativo, Numero.Plural, false, temas, arrAA, tipoSustantivo, ocAux);
            pone(Caso.Genitivo, Numero.Plural, false, temas, arrAA, tipoSustantivo, ocAux);
            pone(Caso.Dativo, Numero.Plural, false, temas, arrAA, tipoSustantivo, ocAux);

            pone(Caso.Nominativo, Numero.Dual, false, temas, arrAA, tipoSustantivo, ocAux);
            pone(Caso.Vocativo, Numero.Dual, false, temas, arrAA, tipoSustantivo, ocAux);
            pone(Caso.Acusativo, Numero.Dual, false, temas, arrAA, tipoSustantivo, ocAux);
            pone(Caso.Genitivo, Numero.Dual, false, temas, arrAA, tipoSustantivo, ocAux);
            pone(Caso.Dativo, Numero.Dual, false, temas, arrAA, tipoSustantivo, ocAux);
        }
        return ocAux;
    }



    /**
     * puebla algo de la colección de temas que potencialmente podr'ia usarse
     * para declinar
     * 
     * @param temas
     * @param nominativo
     * @param genitivo
     */
    private static Map<OrigenTema, String>  preparaTemas(String nominativo, String genitivo, int tipoSustantivo) {
        Map<OrigenTema, String> resultado=new HashMap<OrigenTema, String>();
        // nominativo singular
        resultado.put(OrigenTema.NominativoSingular, extraeTemaNominativoSingular(nominativo, genitivo, tipoSustantivo));
        resultado.put(OrigenTema.NominativoPlural, extraeTemaNominativoPlural(nominativo, genitivo, tipoSustantivo));
        resultado.put(OrigenTema.VocativoSingular, extraeTemaVocativoSingular(nominativo, genitivo, tipoSustantivo));
        resultado.put(OrigenTema.GenitivoSingular, extraeTemaGenitivoSingular(nominativo, genitivo, tipoSustantivo));
        resultado.put(OrigenTema.DativoPlural, extraeTemaDativoPlural(nominativo, genitivo, tipoSustantivo));
        return resultado;
    }

    /**
     * Método sobrecargado. Similar a los otros pero toma toda la información de
     * la tabla de desinencias
     * 
     * @author gonzy
     * 
     */
    private void pone(Caso caso, Numero numero, boolean pluralizado, Map<OrigenTema, String> temas, AnalisisAcento[] arrAA,
            int tipoSustantivo, OcNominal oc) {
        Numero numeroAAgarrar = numero;
        if (pluralizado && numero == Numero.Singular)
            numeroAAgarrar = Numero.Plural;
        DesinenciaSustantivo[] arrDes = manejaDesinenciasSustantivo.getDesinencia(tipoSustantivo, numeroAAgarrar, caso);
        AnalisisAcento aaNom = arrAA[0];
        AnalisisAcento aaGen = arrAA[1];

        boolean debug = false;
        if (debug)
            System.out.println("tipoSustantivo=" + tipoSustantivo + "  numero=" + numero + " caso=" + caso);
        for (int i = 0; i < arrDes.length; i++) {
            OrigenTema origenTema=arrDes[i].origenTema;
            String desinencia = arrDes[i].forma;

            int posicion = 0;
            int posicionConcuerda = arrDes[i].posicionConcuerda;
            if (posicionConcuerda == 0) { // posición natural
                posicion = 0;
            } else if (posicionConcuerda == 2) {// última si tiene 2 sílabas,
                                                // natural si más
                AnalisisAcento aa = AnalisisAcento.getAnalisisAcento(temas.get(origenTema) + desinencia);
                if (aa.cantidadDeSilabas <= 2)
                    posicion = -1;
                else
                    posicion = 0;
            } else if (posicionConcuerda == 3) {// concuerda consigo mismo
                if (caso == Caso.Nominativo && numero == Numero.Singular)
                    posicion = aaNom.actuales.silabaB1;
                else if (caso == Caso.Vocativo && numero == Numero.Singular)
                    posicion = aaNom.actuales.silabaB1;
                else if (caso == Caso.Acusativo && numero == Numero.Singular)
                    posicion = aaNom.actuales.silabaB1;
                else if (caso == Caso.Genitivo && numero == Numero.Singular)
                    posicion = aaGen.actuales.silabaB1;
                else if (caso == Caso.Dativo && numero == Numero.Singular)
                    posicion = aaGen.actuales.silabaB1;
                else if ((caso == Caso.Nominativo || caso == Caso.Vocativo) && numero == Numero.Plural)
                    posicion = aaNom.actuales.silabaB1;
                else if (caso == Caso.Acusativo && numero == Numero.Plural)
                    posicion = aaNom.actuales.silabaB1;
                else if (caso == Caso.Genitivo && numero == Numero.Plural)
                    posicion = aaGen.actuales.silabaB1;
                else if (caso == Caso.Dativo && numero == Numero.Plural)
                    posicion = aaGen.actuales.silabaB1;
                else if ((caso == Caso.Nominativo || caso == Caso.Vocativo || caso == Caso.Acusativo)
                        && numero == Numero.Dual)
                    posicion = aaNom.actuales.silabaB1;
                else if ((caso == Caso.Genitivo || caso == Caso.Dativo) && numero == Numero.Dual)
                    posicion = aaGen.actuales.silabaB1;

            } else if (posicionConcuerda == 1) {// concuerda con el caso/numero
                                                // con el que debe
                if (caso == Caso.Nominativo && numero == Numero.Singular)
                    posicion = aaNom.actuales.silabaB1;
                else if (caso == Caso.Vocativo && numero == Numero.Singular)
                    posicion = aaNom.actuales.silabaB1;
                else if (caso == Caso.Acusativo && numero == Numero.Singular){
                    posicion = aaNom.actuales.silabaB1;
                }else if (caso == Caso.Genitivo && numero == Numero.Singular)
                    posicion = aaGen.actuales.silabaB1;
                else if (caso == Caso.Dativo && numero == Numero.Singular)
                    posicion = aaGen.actuales.silabaB1;
                else if ((caso == Caso.Nominativo || caso == Caso.Vocativo || caso == Caso.Acusativo)
                        && numero == Numero.Plural) {
                    // concuerda con la acentuación del nominativo, pero no más
                    // al principio que lo natural
                    String provisiorio = OpPalabras.contraeGenerica(temas.get(origenTema), arrDes[i].forma,
                            arrDes[i].contraccion, 0, Acento.Ninguno);
                    int posicionNatural = AnalisisAcento.getAnalisisAcento(provisiorio).sugeridos.silabaB1;
                    int posicionNominativo = aaNom.actuales.silabaB1;
                    if (posicionNominativo < posicionNatural)
                        posicion = posicionNatural;
                    else
                        posicion = posicionNominativo;

                } else if (caso == Caso.Genitivo && numero == Numero.Plural)
                    posicion = aaGen.actuales.silabaB1;
                else if (caso == Caso.Dativo && numero == Numero.Plural)
                    posicion = aaGen.actuales.silabaB1;
                else if ((caso == Caso.Nominativo || caso == Caso.Vocativo || caso == Caso.Acusativo)
                        && numero == Numero.Dual) {
                    // concuerda con la acentuación del nominativo, pero no más
                    // al principio que lo natural
                    String provisiorio = OpPalabras.contraeGenerica(temas.get(origenTema), arrDes[i].forma,
                            arrDes[i].contraccion, 0, Acento.Ninguno);
                    int posicionNatural = AnalisisAcento.getAnalisisAcento(provisiorio).sugeridos.silabaB1;
                    int posicionNominativo = aaNom.actuales.silabaB1;
                    if (posicionNominativo < posicionNatural)
                        posicion = posicionNatural;
                    else
                        posicion = posicionNominativo;
                } else if ((caso == Caso.Genitivo || caso == Caso.Dativo) && numero == Numero.Dual)
                    posicion = aaGen.actuales.silabaB1;
            } else if (posicionConcuerda == 4) {
                posicion = arrDes[i].silaba;
            } else if (posicionConcuerda == 5) {
                posicion = aaGen.actuales.silabaB1;
            } else if (posicionConcuerda == 6) {
                posicion = aaNom.actuales.silabaB1;
            }

            // acento
            Acento acento = Acento.Ninguno;
            int acentoConcuerda = arrDes[i].acentoConcuerda;
            if (acentoConcuerda == 0) {
                acento = Acento.Ninguno;
            } else if (acentoConcuerda == 1 || acentoConcuerda == 3) {
                if (arrDes[i].origenTema.equals(OrigenTema.NominativoSingular)) {
                    acento = aaNom.actuales.tipoAcento;
                } else if ( arrDes[i].origenTema.equals(OrigenTema.GenitivoSingular)) {
                    acento = aaGen.actuales.tipoAcento;
                }
            } else if (acentoConcuerda == 2) {
                acento = aaNom.actuales.tipoAcento;
            } else if (acentoConcuerda == 4) {
                acento = arrDes[i].acento;
            }

            if (debug) {
                StringBuffer sb = new StringBuffer("origenTema=" + arrDes[i].origenTema + " tema="
                        + OpPalabras.strCompletoABeta(temas.get(arrDes[i].origenTema)) + " forma="
                        + OpPalabras.strCompletoABeta(arrDes[i].forma) + " posicion=" + posicion + " acento=" + acento
                        + " contraccion=" + arrDes[i].contraccion);
                if (temas.get(arrDes[i].origenTema) == null)
                    sb
                            .append("ERROR: la rutina de extracción de temas no pudo obtener dicho tema para dicho tipo de sustantivo \n");
                System.out.println(sb.toString());
            }

            String forma=null;
            try {
                forma = OpPalabras.contraeGenerica(temas.get(arrDes[i].origenTema), arrDes[i].forma,
                        arrDes[i].contraccion, posicion, acento);
            } catch (Exception e) {
                StringBuffer sb=new StringBuffer();
                sb.append("error al tratar de contraer el tema  " +  OpPalabras.strCompletoABeta(temas.get(arrDes[i].origenTema + "\n")));
                sb.append("con la siguiente desinencia de forma=" + OpPalabras.strCompletoABeta(arrDes[i].forma) +  " contraccion=" + arrDes[i].contraccion + " posicionConcuerda=" + arrDes[i].posicionConcuerda + " acentoConcuerda=" + arrDes[i].acentoConcuerda);
                logger.error(sb.toString(), e);
                forma="";
            }
            if (debug)
                System.out.println("forma resultante=" + OpPalabras.strCompletoABeta(forma));
            oc.setFormaIndividual(caso, numero, forma);
        }

    }

    /**
     * extrae el tema indicado
     * @param caso
     * @param numero
     * @param nominativo
     * @param genitivo
     * @param tipoSustantivo
     * @return
     */
    public static String extraeTema(OrigenTema origenTema, String nominativo, String genitivo, int tipoSustantivo){
    	switch(origenTema){
    	case NominativoSingular:
    	    return extraeTemaNominativoSingular(nominativo, genitivo, tipoSustantivo);
    	case NominativoPlural:
    	    return extraeTemaNominativoPlural(nominativo, genitivo, tipoSustantivo);
    	case VocativoSingular:
    	    return extraeTemaVocativoSingular(nominativo, genitivo, tipoSustantivo);
    	case GenitivoSingular:
    	    return extraeTemaGenitivoSingular(nominativo, genitivo, tipoSustantivo);
    	case DativoPlural:
    	    return extraeTemaDativoPlural(nominativo, genitivo, tipoSustantivo);
    	}
    	throw new RuntimeException("esa extracción de tema no existe para el origen " + origenTema);
    }
    
    private static String extraeTemaGenitivoSingular(String nominativo, String genitivo, int tipoSustantivo) {
        String resultado;
        switch (tipoSustantivo) {
        case 73:
        case 121:
        case 122:
        case 123:
        case 72: { // sigma caída: come 3 y agrega épsilon
            StringBuffer tema = new StringBuffer(OpPalabras.desacentuar(genitivo));
            tema.delete(tema.length() - 3, tema.length());
            tema.append(cEpsilon);
            resultado = tema.toString();
            break;
        }
        case 50: { // sigma caída: come 3
            StringBuffer tema = new StringBuffer(OpPalabras.desacentuar(genitivo));
            tema.delete(tema.length() - 3, tema.length());
            resultado = tema.toString();
            break;
        }
        case 114:
        case 115:
        case 90:
        case 132:
            { // de tercera que comen 3
            return OpPalabras.desacentuar(OpPalabras.comeFinal(genitivo, 3));
            }
        case 92: //delcinación ática
        case 93: //declinación ática
        case 82: //genitivos dóricos
        	{ 
            return OpPalabras.desacentuar(OpPalabras.comeFinal(genitivo, 1));
        }
        case 74: { // sigma caída en -aj: come 2 y agrega a
            StringBuffer tema = new StringBuffer(OpPalabras.desacentuar(genitivo));
            tema.delete(tema.length() - 2, tema.length());
            tema.append(cAlfaCorta);
            resultado = tema.toString();
            break;
        }

        default:
            resultado = OpPalabras.desacentuar(genitivo);
            resultado = OpPalabras.comeFinal(resultado, 2);
            break;
        }
        return resultado;
    }

    private static String extraeTemaNominativoPlural(String nominativo, String genitivo, int tipoSustantivo) {
        String resultado;
        switch (tipoSustantivo) {
        case 78:
            resultado = OpPalabras.desacentuar(genitivo);
            resultado = OpPalabras.comeFinal(resultado, 3);
            break;
        case 37:
        case 39: // líquidos sincopados
            resultado = OpPalabras.desacentuar(nominativo);
            resultado = OpPalabras.comeFinal(resultado, 2) + OpPalabras.strBetaACompleto("ER");
            break;
        default:
            resultado = null;
            break;
        }
        return resultado;
    }

    private static String extraeTemaDativoPlural(String nominativo, String genitivo, int tipoSustantivo) {
        String resultado;
        switch (tipoSustantivo) {
        case 65:
        case 66:
        case 62:
        case 21:
        case 124:  case 125:
        case 103:  
        case 52:
        case 63:
        case 64:
        case 26:
        case 55:
        case 56:
        case 59:
        case 60:
            resultado = OpPalabras.desacentuar(genitivo);
            resultado = OpPalabras.comeFinal(resultado, 3);
            break;
        case 25:
        case 61: {// participios perfectos neutros esto/j esto/toj, el dativo
                    // es estw=si
            resultado = OpPalabras.comeFinal(genitivo, 4);
            StringBuffer tema = new StringBuffer(resultado);
            tema.append(cOmega);
            resultado = tema.toString();
            break;
        }
        case 112:
        case 113:
        case 70:
        case 29:
        case 30: // genitivos en -nt- alarga la vocal
            resultado = OpPalabras.desacentuar(genitivo);
            resultado = OpPalabras.comeFinal(resultado, 4);
            // si el genitivo tiene diptongo no hago nada
            if (OpPalabras.ultimaSilabaDiptongo(resultado))
                return resultado;
            StringBuffer tema = new StringBuffer(resultado);
            CarPos ultima = CarPos.getCarPos(resultado, resultado.length() - 1);
            if (ultima.getLetraBase() == cOmicron) {
                tema.append(cUpsilonCorta);
                resultado = tema.toString();
            } else if (ultima.getLetraBase() == cAlfaCorta || ultima.getLetraBase() == cUpsilonCorta
                    || ultima.getLetraBase() == cIotaCorta) {
                tema.setCharAt(tema.length() - 1, ultima.getVersionLarga());
                resultado = tema.toString();
            } else if (ultima.getLetraBase() == cEpsilon) {
                tema.append(cIotaCorta);
                resultado = tema.toString();
            }
            // corrijo el espíritu, para que no quede espirituada la primera
            // letra del diptongo, ej: O)NTOS -> O)USI en lugar de OU)SI
            Espiritu espiritu = OpPalabras.getEspiritu(genitivo);
            if (espiritu != Espiritu.Ninguno) {
                resultado = OpPalabras.desespirituar(resultado);
                resultado = OpPalabras.espirituPalabra(resultado, espiritu);
            }
            break;
        default:
            resultado = null;
            break;
        }
        return resultado;
    }

    /**
     * Extrae el tema de vocativo singular. -se usa para los sustantivos en -nt-
     * sin diptongo en el nominativo Para cualquier otro caso deriva la
     * extracción al nominativo singular
     * 
     * @param nominativo
     * @param genitivo
     * @param tipoSustantivo
     * @return
     */
    private static String extraeTemaVocativoSingular(String nominativo, String genitivo, int tipoSustantivo) {
        if (tipoSustantivo == 70) {
            String tema = OpPalabras.comeFinal(genitivo, 4);
            return OpPalabras.desacentuar(tema);
        } else if (tipoSustantivo == 76) {
            String tema = OpPalabras.comeFinal(genitivo, 3);
            return OpPalabras.desacentuar(tema);
        } else if (tipoSustantivo == 80) {
            StringBuffer nomAux = new StringBuffer(OpPalabras.desacentuar(nominativo));
            nomAux.deleteCharAt(nomAux.length() - 1);
            CarPos ultima = CarPos.getCarPos(nomAux.toString(), nomAux.length() - 1);
            nomAux.setCharAt(nomAux.length() - 1, ultima.getVersionCorta());
            return nomAux.toString();
        } else if (tipoSustantivo==124 || tipoSustantivo==125){
        	//en nu, acento natural
        	if (nominativo.endsWith(OpPalabras.strBetaACompleto("WN"))){
        		String temaVocativo=OpPalabras.reemplazaFinal(nominativo, 2, OpPalabras.strBetaACompleto("O"));
        		return temaVocativo;
        	}else{
        		return extraeTemaNominativoSingular(nominativo, genitivo, tipoSustantivo);
        	}
        } else{
            return extraeTemaNominativoSingular(nominativo, genitivo, tipoSustantivo);
        }
    }

    /**
     * Extrae el tema de nominativo singular, quitándole la cantidad de letras
     * apropiada al tema de nominativo
     * 
     * @param nominativo
     * @param genitivo
     * @param tipoSustantivo
     * @return
     */
    private static String extraeTemaNominativoSingular(String nominativo, String genitivo, int tipoSustantivo) {
        String resultado = OpPalabras.desacentuar(nominativo);
        String genitivoDesacentuado=OpPalabras.desacentuar(genitivo);
        switch (tipoSustantivo) {
        // de primera que comen 1
        case 7:
        case 8:
        case 6:
        case 27:
        case 10:
        case 31:
        case 33:
        case 12:
            resultado = resultado.substring(0, resultado.length() - 1);
            break;
        // de primera que comen 2
        case 108:
        case 109:
        case 104:
        case 105:
        case 82: //genitivo dórico
            resultado = OpPalabras.comeFinal(resultado, 2);
            break;
        case 75: // de segunda que comen 1

            resultado = OpPalabras.comeFinal(resultado, 1);
            break;
        // de segunda que comen 2
        case 106:
        case 107:
        case 110:
        case 111:
            resultado = OpPalabras.comeFinal(resultado, 2);
            break;
        // declinación ática que comen 2
        case 92:
        case 93:
            resultado = OpPalabras.comeFinal(resultado, 2);
            break;
        // de segunda que comen 3
        case 49:
        case 118:
        case 119:
            resultado = OpPalabras.comeFinal(resultado, 3);
            break;
        // de 3ra que comen 1:
        case 65:
        case 66:
        case 61:
        case 62:
        case 116:
        case 117:
        case 124: case 125: case 84: 
        case 103:
        case 52:
        case 67:
        case 68:
        case 63:
        case 64:
        case 81:
        case 128:	
        case 29:
        case 30:
            resultado = OpPalabras.comeFinal(resultado, 1);
            break;
        // en xi o psi
        case 35:
        case 36:
            if (genitivoDesacentuado.endsWith(OpPalabras.strBetaACompleto("KTOJ")))
                resultado = OpPalabras.comeFinal(OpPalabras.desacentuar(genitivo), 3);
            else
                resultado = OpPalabras.comeFinal(OpPalabras.desacentuar(genitivo), 2);
            break;
        // de 3ra con tema=nominativo
        case 37:
        case 39:
            resultado = nominativo;
            break;
        // de 3ra que comen 2
        case 73:
        case 74:
        case 50:
        case 72:
        case 79:
        case 114:
        case 115:
        case 78:
        case 25:
        case 121:
        case 122:
        case 123:
            resultado = OpPalabras.comeFinal(resultado, 2);
            break;
        // de 3ra que comen 3
        case 90:
            resultado = OpPalabras.comeFinal(resultado, 3);
            break;
        // de 3ra que no comen nada
        case 26:
        case 70:
        case 85:
        case 112:
        case 113:
        case 129:
            break;
        // pluralizados que comen 2 del plural
        case 96:
        case 100:
            resultado = OpPalabras.comeFinal(resultado, 2);
            break;
        // pluralizados que comen 1 del plural
        case 101:
            resultado = OpPalabras.comeFinal(resultado, 1);
            break;
        default:
            return null;
//            throw new RuntimeException("Error en extraeTema: tipo de sustantivo desconocido " + tipoSustantivo);
        }
        return resultado;
    }

    /**
     * Dice si un acento es de segunda. Presupone que la primera se verificó
     * primero
     * 
     * @param nominativo
     * @param genitivo
     * @return
     */
    private static int esDeSegunda(String nominativo, String genitivo) throws ProblemaSugiriendoDeclinacion {
        String nomSinAcento = OpPalabras.strCompletoABeta(OpPalabras.desacentuar(nominativo));
        String genSinAcento = OpPalabras.strCompletoABeta(OpPalabras.desacentuar(genitivo));
        AnalisisAcento aaNom = OpPalabras.getAnalisisAcento(nominativo);

        if (genSinAcento.endsWith("OU")) {
            if (OpPalabras.right(nomSinAcento, 2).equals("OJ"))
                if (aaNom.actuales.silaba == aaNom.sugeridos.silaba)
                    return 106;
                else
                    return 107;
            else if (OpPalabras.right(nomSinAcento, 2).equals("ON"))
                if (aaNom.actuales.silaba == aaNom.sugeridos.silaba)
                    return 110;
                else
                    return 111;
            else if (OpPalabras.right(nomSinAcento, 3).equals("OUJ"))
                if (aaNom.actuales.silaba == aaNom.sugeridos.silaba)
                    return 118;
                else
                    return 119;
            else if (OpPalabras.right(nomSinAcento, 3).equals("OUN"))
                return 49;
            else if (OpPalabras.right(nomSinAcento, 1).equals("O"))
                return 75;
            else {
                throw new ProblemaSugiriendoDeclinacion(
                        "problemas al sugerir declinacion con  formas que creí que eran de segunda", nominativo,
                        genitivo);
            }
        } else if (nomSinAcento.endsWith("A_J") && genSinAcento.endsWith("A_")){
        	return 82; //masculinos con genitivo dórico
        }else{
            return 0;
        }
    }

    /**
     * devuelve el id de 32 caracteres de los tipos nominales inclasificables
     * 
     * @return
     */
    public String getIdIrregulares() {
        return mapTiposID.get(4);
    }

    /**
     * wrapper de sugiereTipoSustantivo, que devuelve el ID de tipo de 32
     * caracteres
     * 
     * @param nominativo
     * @param genitivo
     * @param genero
     * @return
     */
    public String sugiereTipoNominalId(String nominativo, String genitivo, Genero genero) {
        int tipoNominal = sugiereDeclinacion(nominativo, genitivo, genero);
        String tipoId = mapTiposID.get(tipoNominal);
        return tipoId;
    }

    public int sugiereDeclinacion(String nominativo, String genitivo, Genero genero)
            throws ProblemaSugiriendoDeclinacion {

        int resultado = 0;

        // pluralizados 
        resultado = esPluralizado(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        // 1ra declinacion
        resultado = esDePrimera(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        // 2da declinacion
        resultado = esDeSegunda(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        // declinación ática
        resultado = esAtica(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        // 3ra declinacion
        resultado = esConsonanticoContracto(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        resultado = esConsonanticoLiquido(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        resultado = esEnNu(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        resultado = esDental(nominativo, genitivo, genero);
        if (resultado != 0)
            return resultado;

        // sigma caída
        resultado = esSigmaCaida(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        // -eu-au-ou-
        resultado = esEuAuOu(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        // -iu-
        resultado = esIU(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        // -omega-digamma-
        resultado = esOmegaDigamma(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        // -oi-
        resultado = esOi(nominativo, genitivo);
        if (resultado != 0)
            return resultado;

        // invariable
        if (nominativo.equals(genitivo))
            return 0;

        // indeclinable/irregular (no pude sugerir)
        if (logger.isDebugEnabled()){
	        StringBuffer sb=new StringBuffer();
	        sb.append("No pude sugerir el tipo nominal para el nominativo " + OpPalabras.strCompletoABeta(nominativo));
	        sb.append(" genitivo=" + OpPalabras.strCompletoABeta(genitivo));
	        sb.append(" genero=" + genero);
	        logger.debug(sb.toString());
        }
        return 4;
    }

    /**
     * Determina si el par nominativo/genitivo pertenece a la primera
     * declinación, y en caso afirmativo a qué juego en especial
     * 
     * @param nominativo
     * @param genitivo
     * @return
     */
    private static int esDePrimera(String nominativo, String genitivo) {
        String nom = OpPalabras.strCompletoABeta(nominativo);
        String sAuxGen = OpPalabras.desacentuar(genitivo);
        sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
        String sAuxNom = OpPalabras.desacentuar(nominativo);
        sAuxNom = OpPalabras.strCompletoABeta(sAuxNom);
        AnalisisAcento aaNom = OpPalabras.getAnalisisAcento(nominativo);

        if (nominativo.endsWith(OpPalabras.strBetaACompleto("A="))
                && genitivo.endsWith(OpPalabras.strBetaACompleto("A=J")))
            return 10; // alfa contracta
        else if (nominativo.endsWith(OpPalabras.strBetaACompleto("H="))
                && genitivo.endsWith(OpPalabras.strBetaACompleto("H=J")))
            return 12; // eta contracta
        else if ((nom.endsWith("A") || nom.endsWith("A/"))
                && (sAuxGen.endsWith("A_J") || sAuxGen.endsWith("IAJ") || sAuxGen.endsWith("UAJ")))
            return 7; // alfa pura corta
        else if ((nom.endsWith("A_") || nom.endsWith("A/") || nom.endsWith("A_/"))
                && (sAuxGen.endsWith("A_J") || sAuxGen.endsWith("IAJ") || sAuxGen.endsWith("UAJ")))
            return 8; // alfa pura larga
        else if ((sAuxNom.endsWith("A") || sAuxNom.endsWith("A_")) && sAuxGen.endsWith("HJ"))
            if (aaNom.actuales.silaba == aaNom.sugeridos.silaba)
                return 6; // sustantivos
            else
                return 27; // participios femeninos, como DEIKNU=SA, LUQEI=SA,
                            // que no están acentuados naturalmente en el
                            // nominativo
        else if (sAuxNom.endsWith("H") && sAuxGen.endsWith("HJ"))
            if (aaNom.actuales.silaba == aaNom.sugeridos.silaba)
                return 31;
            else
                return 33;
        else if ((sAuxNom.endsWith("A_J") || sAuxNom.endsWith("AJ")) && sAuxGen.endsWith("OU")) // masculinos
                                                                                                // en
                                                                                                // alfa
            if (aaNom.actuales.silaba == aaNom.sugeridos.silaba)
                return 108;
            else
                return 109;
        else if (sAuxNom.endsWith("HJ") && sAuxGen.endsWith("OU")) // masculinos
                                                                    // en eta
            if (aaNom.actuales.silaba == aaNom.sugeridos.silaba)
                return 104;
            else
                return 105;

        return 0;
    }

    /**
     * Determina si el par nominativo/genitivo pertenece a la declinación en
     * omega-digamma,
     * 
     * @param nominativo
     * @param genitivo
     * @return
     */
    private static int esOmegaDigamma(String nominativo, String genitivo) {
        String sAuxGen = OpPalabras.desacentuar(genitivo);
        sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
        String sAuxNom = OpPalabras.desacentuar(nominativo);
        sAuxNom = OpPalabras.strCompletoABeta(sAuxNom);
        AnalisisAcento aaNom = AnalisisAcento.getAnalisisAcento(nominativo);
        if (sAuxNom.endsWith("WJ") && (sAuxGen.endsWith("WOJ") || sAuxGen.endsWith("W")))
            if (aaNom.actuales.silaba == aaNom.sugeridos.silaba)
                return 114;
            else
                return 115;
        else
            return 0;
    }

    /**
     * Determina si el par nominativo/genitivo pertenece a la declinación ática,
     * y en caso afirmativo su subtipo
     * 
     * @param nominativo
     * @param genitivo
     * @return
     */
    private static int esAtica(String nominativo, String genitivo) {
        String sAuxGen = OpPalabras.desacentuar(genitivo);
        sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
        String sAuxNom = OpPalabras.desacentuar(nominativo);
        sAuxNom = OpPalabras.strCompletoABeta(sAuxNom);
        if ((sAuxNom.endsWith("WJ") || sAuxNom.endsWith("WN")) && sAuxGen.endsWith("W")) {
            if (sAuxNom.endsWith("WJ"))
                return 92;
            else
                return 93;
        } else
            return 0;
    }

    /**
     * Determina si es pluralizado (de primera, segunda o tercera)
     * 
     * @param nominativo
     * @param genitivo
     * @return
     */
    private static int esPluralizado(String nominativo, String genitivo) {

        String sAuxGen = OpPalabras.desacentuar(genitivo);
        sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
        String sAuxNom = OpPalabras.desacentuar(nominativo);
        sAuxNom = OpPalabras.strCompletoABeta(sAuxNom);
        if (sAuxNom.endsWith("AI") && sAuxGen.endsWith("WN")) {
            return 96;
        } else if (sAuxNom.endsWith("OI") && sAuxGen.endsWith("WN")) {
            return 100;
        } else if (sAuxNom.endsWith("A") && sAuxGen.endsWith("WN")) {
            return 101;
        } else if (sAuxNom.endsWith("EJ") && sAuxGen.endsWith("WN")) {
            return 131;
        } else if (sAuxNom.endsWith("EIJ") && sAuxGen.endsWith("WN")) {
            return 132;
        } else
            return 0;
    }

    /**
     * Determina si el par nominativo/genitivo pertenece a la categoría de
     * verbos en dentales, y en caso afirmativo a qué juego en especial
     * 
     * @param nominativo
     * @param genitivo
     * @return
     */
    private static int esDental(String nominativo, String genitivo, Genero genero) throws ProblemaSugiriendoDeclinacion {
        String sAuxGen = OpPalabras.neutraliza(genitivo);
        sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
        String sAuxNom = OpPalabras.neutraliza(nominativo);
        sAuxNom = OpPalabras.strCompletoABeta(sAuxNom);
        AnalisisAcento aaNom = OpPalabras.getAnalisisAcento(nominativo);
        AnalisisAcento aaGen = OpPalabras.getAnalisisAcento(genitivo);

        if ( sAuxGen.endsWith("TOJ") && !sAuxGen.endsWith("NTOJ")) {
            if (nominativo.endsWith(OpPalabras.strBetaACompleto("O/J"))) {
                return 25;
            } else if (sAuxNom.endsWith("LA") || sAuxNom.endsWith("MA") || sAuxNom.endsWith("IA")
                    || sAuxNom.endsWith("NA") || sAuxNom.endsWith("MI") || sAuxNom.endsWith("LI")
                    || sAuxNom.endsWith("AJ") || sAuxNom.endsWith("AR") || sAuxNom.endsWith("WR")
                    || sAuxNom.endsWith("ER") || sAuxNom.endsWith("RU") || sAuxNom.endsWith("NU"))
                return 26;
        }
        
        if (sAuxGen.endsWith("IDOJ") && sAuxNom.endsWith("I")) {
                return 26;
        }


        if (sAuxGen.endsWith("DOJ") || sAuxGen.endsWith("TOJ") || sAuxGen.endsWith("QOJ")) {
            if (sAuxNom.endsWith("J") || sAuxNom.endsWith("N")) {
                if (sAuxGen.endsWith("NTOJ") || sAuxGen.endsWith("KTOJ")) { // en
                                                                            // -nt-
                    if ((sAuxNom.endsWith("ON") || sAuxNom.endsWith("OUN")
                            || nominativo.endsWith(OpPalabras.strBetaACompleto("A/N"))
                            || nominativo.endsWith(OpPalabras.strBetaACompleto("AN"))
                            || nominativo.endsWith(OpPalabras.strBetaACompleto("W=N"))
                            || nominativo.endsWith(OpPalabras.strBetaACompleto("U/N")) || sAuxNom.endsWith(("EN")))
                            && genero == Genero.Neutro)
                        // participios neutros
                        if (aaNom.actuales.silaba == aaNom.sugeridos.silaba)
                            return 29;
                        else
                            return 30;
                    else if (aaGen.actuales.silaba == -2)
                        if (nominativo.endsWith(OpPalabras.strBetaACompleto("OU=J"))
                                || nominativo.endsWith(OpPalabras.strBetaACompleto("W=N"))) // contractos
                            return 113;
                        else
                            return 112;
                    else
                        return 70;
                } else if ((sAuxNom.endsWith("UJ") || sAuxNom.endsWith("U_J") || sAuxNom.endsWith("IJ") || sAuxNom
                        .endsWith("I_J"))
                        && aaNom.actuales.silaba == -2) {// dentales en -is
                                                            // -us graves
                    if (aaNom.actuales.silabaB1 == aaGen.actuales.silabaB1)
                        if (sAuxNom.endsWith("IJ") || sAuxNom.endsWith("I_J"))
                            return 66;
                        else
                            return 65;
                    else if (sAuxNom.endsWith("IJ") || sAuxNom.endsWith("I_J"))
                        return 68;
                    else
                        return 67;
                } else { // no en -is -us graves
                    if (aaNom.actuales.silabaB1 == aaGen.actuales.silabaB1)
                        if (sAuxNom.endsWith("IJ") || sAuxNom.endsWith("I_J"))
                            return 62;
                        else
                            return 61;
                    else if (sAuxNom.endsWith("IJ") || sAuxNom.endsWith("I_J"))
                        return 64;
                    else
                        return 63;
                }

            } else{
                StringBuffer mensaje=new StringBuffer("genitivo terminado en -TOJ/DOJ pero el nominativo no es en -J ni en -A ");
                mensaje.append("nominativo=" + OpPalabras.strCompletoABeta(nominativo) + " ");
                mensaje.append("genitvo=" + OpPalabras.strCompletoABeta(genitivo));
                throw new ProblemaSugiriendoDeclinacion(mensaje.toString(), nominativo, genitivo);
            }
        } else
            return 0;
    }

    /**
     * Determina si el par nominativo/genitivo pertenece a la categoría de verbos en -nu
     * @param nominativo
     * @param genitivo
     * @return
     */
    private static int esEnNu(String nominativo, String genitivo) throws ProblemaSugiriendoDeclinacion {
        String sAuxGen = OpPalabras.desacentuar(genitivo);
        sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
        String sAuxNom = OpPalabras.desacentuar(nominativo);
        sAuxNom = OpPalabras.strCompletoABeta(OpPalabras.desespirituar(sAuxNom));
        AnalisisAcento aaNom = AnalisisAcento.getAnalisisAcento(nominativo);

        if (sAuxGen.endsWith("NOJ")) {
            if (sAuxNom.endsWith("N")){
                if (aaNom.actuales.silaba == aaNom.sugeridos.silaba) {
                    if (sAuxNom.endsWith("ON") || sAuxNom.endsWith("EN") || sAuxNom.endsWith("AN")) {
                        // neutros
                        return 125;
                    } else {
                        return 124;
                    }
                } else
                    return 103;
            }else if (sAuxGen.endsWith("J")){
                return 52;
            }else{
                throw new ProblemaSugiriendoDeclinacion(
                        "genitivo terminado en -NOS pero el nominativo no es en -N ni en -J ", nominativo, genitivo);
            }
        } else
            return 0;
    }

    /**
     * Determina si el genitivo pertenece a la categoría en -xi o -psi contractos
     * Si pertenece, devuelve a qué categoría (36 0 37)
     * Si no, devuelve 0
     * @param genitivo
     * @return
     */
    private static int esConsonanticoContracto(String nominativo, String genitivo) {
        String sAux = OpPalabras.desacentuar(genitivo);
        sAux = OpPalabras.strCompletoABeta(sAux);
        if (sAux.endsWith("BOJ") || sAux.endsWith("FOJ") || sAux.endsWith("POJ") || sAux.endsWith("KOJ")
                || sAux.endsWith("COJ") || sAux.endsWith("GOJ") || sAux.endsWith("KTOJ")) {
            AnalisisAcento aaNom = OpPalabras.getAnalisisAcento(nominativo);
            AnalisisAcento aaGen = OpPalabras.getAnalisisAcento(genitivo);
            if (aaNom.actuales.silabaB1 == aaGen.actuales.silabaB1)
                return 35;
            else
                return 36;
        } else
            return 0;
    }

    private static int esConsonanticoLiquido(String nominativo, String genitivo) {
        String sAuxGen = OpPalabras.desacentuar(genitivo);
        sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
        String sAuxNom = OpPalabras.desacentuar(nominativo);
        sAuxNom = OpPalabras.strCompletoABeta(sAuxNom);
        AnalisisAcento aaNom = AnalisisAcento.getAnalisisAcento(nominativo);
        AnalisisAcento aaGen = OpPalabras.getAnalisisAcento(genitivo);

        if (sAuxNom.endsWith("R") && sAuxGen.endsWith("ROJ")) {
            if (sAuxGen.endsWith("TROJ") || sAuxGen.endsWith("DROJ")) //con síncopa
                if (aaNom.actuales.silabaB1 == aaGen.actuales.silabaB1)
                    return 37;
                else
                    return 39;
            else //sin síncopa
            if (aaNom.actuales.silaba == -1)
                return 117;
            else
                return 116;

        } else {
            return 0;
        }
    }

    /**
     * Determina si un verbo es del tipo "sigma caída", y si lo es su subtipo
     * @param nominativo
     * @param genitivo
     * @return
     */
    private static int esSigmaCaida(String nominativo, String genitivo) {
        String sAuxGen = OpPalabras.desacentuar(genitivo);
        sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
        String sAuxNom = OpPalabras.desacentuar(nominativo);
        sAuxNom = OpPalabras.strCompletoABeta(sAuxNom);
        if (sAuxNom.endsWith("J") && sAuxGen.endsWith("OUJ") || (sAuxNom.endsWith("AJ") && sAuxGen.endsWith("WJ"))) {
            if (sAuxNom.endsWith("OJ") || sAuxNom.endsWith("AJ")) {
                if (sAuxNom.endsWith("OJ"))
                    return 73;
                else
                    return 74;
            } else {
                if (sAuxNom.endsWith("EJ"))
                    return 72; //neutros
                else {
                    if (nominativo.endsWith(OpPalabras.strBetaACompleto("H=J")))
                        return 50; //"con vocal ante -es", es contracto por la vocal 
                    else if (nominativo.endsWith(OpPalabras.strBetaACompleto("H/J")))
                        return 121; //adjetivos en H/J de acento no natural
                    else if (nominativo.endsWith(OpPalabras.strBetaACompleto("HJ"))) { //acento natural
                        if (sAuxNom.startsWith("*")) {
                            return 122; //nombres propios
                        } else {
                            return 123; //adjetivos
                        }
                    }

                }
            }
        }
        return 0;
    }

    /**
     * Determina si un verbo es eu au ou, y si lo es su subtipo
     * @param declinacion
     * @param juego
     * @return
     */
    private static int esEuAuOu(String nominativo, String genitivo) {
        String sAuxGen = OpPalabras.desacentuar(genitivo);
        sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
        String sAuxNom = OpPalabras.desacentuar(nominativo);
        sAuxNom = OpPalabras.strCompletoABeta(sAuxNom);
        //new/j pertenece a esta categoría, pero no quiero que sean contempladas las formas
        //en (i-u) -ewj.
        //el acento nunca cae en la W en este tipo de palabras.
        boolean esDeIU=esIU(nominativo, genitivo)!=0;
        
        if ((sAuxNom.endsWith("EUJ") && sAuxGen.endsWith("WJ") && !esDeIU)
                || ((sAuxNom.endsWith("AUJ") || sAuxNom.endsWith("OUJ")) && sAuxGen.endsWith("OJ"))) {
            if (sAuxNom.endsWith("EUJ") && sAuxGen.endsWith("WJ"))
                return 78;
            else
                return 79;
        } else
            return 0;
    }

    /**
     * Determina si un sustantivo es femenino en oi
     * @param declinacion
     * @param juego
     * @return
     */
    private static int esOi(String nominativo, String genitivo) {
        String sAuxGen = OpPalabras.strCompletoABeta(genitivo);
        String sAuxNom = OpPalabras.strCompletoABeta(nominativo);
        if ((sAuxNom.endsWith("W/") && sAuxGen.endsWith("OU=J")))
            return 90;
        else
            return 0;
    }

    /**
	 * Determina si un verbo es "i u", y si lo es su subtipo
	 * 
	 * @param declinacion
	 * @param juego
	 * @return
	 */
	private static int esIU(String nominativo, String genitivo) {
		String sAuxGen = OpPalabras.neutraliza(genitivo);
		sAuxGen = OpPalabras.strCompletoABeta(sAuxGen);
		String sAuxNom = OpPalabras.neutraliza(nominativo);
		sAuxNom = OpPalabras.strCompletoABeta(sAuxNom);
		boolean genUosIos = sAuxGen.endsWith("UOJ") || sAuxGen.endsWith("IOJ");
		//los genitivos acentuados en la W pertenecen al grupo au_eu
		boolean genEws = sAuxGen.endsWith("EWJ") && !genitivo.endsWith(OpPalabras.strBetaACompleto("W/J"))   && !genitivo.endsWith(OpPalabras.strBetaACompleto("W=J"));
		boolean genEos = sAuxGen.endsWith("EOJ");
		//los nominativos en EUJ pertenecen al grupo au_eu
		boolean nomIsUs = sAuxNom.endsWith("IJ") || (sAuxNom.endsWith("UJ") && ! sAuxNom.endsWith("EUJ"));
		boolean nomU = sAuxNom.endsWith("U");
		boolean nomI = sAuxNom.endsWith("I");
//		AnalisisAcento aaNom=AnalisisAcento.getAnalisisAcento(nominativo);

		if ((nomI || nomU || nomIsUs) && (genUosIos || genEws || genEos)) {
			if ((nomU || nomIsUs) && genUosIos) {
				// invariables
				return 81;
			} else { //variables
				if (genEos) { //adjetivos
				    if (!nomU){
				        return 84; //masculinos
				    }else{
				        return 85; //neutros
				    }
				} else {
					// genitivo en ewj
					if (nomU || nomI) {
						// neutros
						return 129;
					} else {
						// masculinos y femeninos
						return 128;
					}
				}
			}
		}
		return 0;
	}

    /**
	 * @return Returns the gerenteSustantivos.
	 */
    public GerenteSustantivos getGerenteSustantivos() {
        return gerenteSustantivos;
    }

    /**
	 * @param gerenteSustantivos
	 *            The gerenteSustantivos to set.
	 */
    public void setGerenteSustantivos(GerenteSustantivos gerenteSustantivos) {
        this.gerenteSustantivos = gerenteSustantivos;
    }

    /**
     * @return Returns the gerenteIrrSustantivos.
     */
    public GerenteIrrSustantivos getGerenteIrrSustantivos() {
        return gerenteIrrSustantivos;
    }

    /**
     * @param gerenteIrrSustantivos The gerenteIrrSustantivos to set.
     */
    public void setGerenteIrrSustantivos(GerenteIrrSustantivos gerenteIrrSustantivos) {
        this.gerenteIrrSustantivos = gerenteIrrSustantivos;
    }

    /**
     * @return Returns the manejaDesinenciasSustantivo.
     */
    public ManejaDesinenciasSustantivo getManejaDesinenciasSustantivo() {
        return manejaDesinenciasSustantivo;
    }

    /**
     * @param manejaDesinenciasSustantivo The manejaDesinenciasSustantivo to set.
     */
    public void setManejaDesinenciasSustantivo(ManejaDesinenciasSustantivo manejaDesinenciasSustantivo) {
        this.manejaDesinenciasSustantivo = manejaDesinenciasSustantivo;
    }

    /**
     * @return Returns the gerenteTiposSustantivo.
     */
    public GerenteTiposSustantivo getGerenteTiposSustantivo() {
        return gerenteTiposSustantivo;
    }

}
