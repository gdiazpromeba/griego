/*
 * Created on 31/01/2004
 *
 */
package kalos.flexion.declinacion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kalos.beans.IrrParticipioEntero;
import kalos.beans.IrrParticipioSimpleBean;
import kalos.beans.IrrVerbo;
import kalos.beans.VerboBean;
import kalos.beans.VerboSimpleCompuesto;
import kalos.datos.gerentes.GerenteIrrParticipiosEnteros;
import kalos.datos.gerentes.GerenteIrrParticipiosSimples;
import kalos.datos.gerentes.GerenteIrrVerbos;
import kalos.datos.gerentes.GerentePreposicionesEnVerbos;
import kalos.datos.gerentes.GerenteVerbos;
import kalos.datos.gerentes.GerenteVerbosCompuestos;
import kalos.enumeraciones.Acento;
import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.Caso;
import kalos.enumeraciones.Contraccion;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.Numero;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.Voz;
import kalos.enumeraciones.utils.TransformadorTiempoAspecto;
import kalos.flexion.Preposiciones;
import kalos.flexion.conjugacion.Verbos;
import kalos.operaciones.OpPalabras;
import kalos.operaciones.TiposVerbo;

// TODO meter la información de participios simples


/**
 * @author gonzalo
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class Participios {

    private GerenteIrrVerbos gerenteIrrVerbos;

    private GerenteVerbos gerenteVerbos;
    
    private GerenteVerbosCompuestos gerenteVerbosCompuestos;
    
    private GerentePreposicionesEnVerbos gerentePreposicionesEnVerbos;
    
    private GerenteIrrParticipiosEnteros gerenteIrrParticipiosEnteros;

    private GerenteIrrParticipiosSimples gerenteIrrParticipiosSimples;

    private Declina declina;
    
    Preposiciones preposiciones;  
    
//    Logger logger=Logger.getLogger(Participios.class.getName());

    
    private CacheFlexionParticipio cacheFlexionParticipio;
    
    /**
     * Llama a "flexionaInterna", pero a través de un caché que me evita recalcularla si ya existe
     * También funde las ocurrencias "expandibles" y "no expandibles" que esta rutina devuelve. 
     * @param verboId
     */
    public Map<Particularidad, OcParticipio> flexiona(String verboId) {
    	Map<Particularidad, OcParticipio> mapaCache=cacheFlexionParticipio.getMapaOcurrencias(verboId);
    	if (mapaCache==null){
    		Map<Particularidad, OcParticipio> mapaExpansibles=new HashMap<Particularidad, OcParticipio>();
    		Map<Particularidad, OcParticipio> mapaNoExpansibles=new HashMap<Particularidad, OcParticipio>();
    		flexionaInterna(verboId, mapaExpansibles, mapaNoExpansibles);
    		mapaCache=mapaExpansibles;
    		cacheFlexionParticipio.setMapaOcurrencias(verboId, mapaCache);
    	}
    	return mapaCache;
    }
    
    
    
    /**
     * Dada una clave primaria de verbos,devuelve dos maps con las
     * particularidades ('REG', 'DOR', etc) como clave y OcurrenciasParticipio
     * como elementos. La diferencia entre ambos es que uno contiene las
     * ocurrencias que contienen sólo nom y genitivo (por lo que son expansibles
     * en el futuro), mientras que el otro map contiene formas aisladas (no
     * nom/gen) y por lo tanto no es expansible Me las arreglo para que vayan al
     * mapa de expansibles las flexiones regulares, más todo lo que provenga de
     * la tabla de IRR_VERBOS, más todo lo que provenga de la tabla de
     * IRR_PARTICIPIOS_ENTEROS. Las irregularidades provenientes de
     * IRR_PARTICIPIOS_SIMPLES no se expanden y van a "no expansibles", dado que
     * si IRR_PARTICIPIOS_SIMPLES proveyera nominativos o genitivos singulares,
     * sería de esperar que éstos fueran provistos en pares en
     * IRR_PARTICIPIOS_ENTEROS.
     * 
     * 
     * @param letra
     *            parámetro de entrada para identificar el verbo
     * @param ocExpansibles
     *            parámetro de salida que contredrá un map de ocurrencias con
     *            sólo nominativo y genitivo singular (y por ende expansibles a
     *            completo)
     * @param ocNoExpansibles
     *            parámetro de salida que contredrá un map de ocurrencias no
     *            nom/gen sing (y por ende no expansibles a completo)
     * 
     * @return
     */
    public void flexionaInterna(String verboId, Map<Particularidad, OcParticipio> ocExpansibles, Map<Particularidad, OcParticipio> ocNoExpansibles) {
    	VerboBean verboOriginal = gerenteVerbos.seleccionaUno(verboId);
        
		List<String> preps = null;
		VerboBean verboAConjugar=verboOriginal;
		
		if (verboOriginal.isCompuesto()){
			VerboSimpleCompuesto vsc=gerenteVerbosCompuestos.seleccionaPorVerboCompuesto(verboOriginal.getId());
			verboAConjugar=gerenteVerbos.seleccionaIndividualSinSignificado(vsc.getIdVerboSimple());
			//averiguo las preposiciones
			if (verboOriginal.isCompuesto()) {
				preps = gerentePreposicionesEnVerbos.seleccionaPorVerbo(verboOriginal.getId());
				for (int i = 0; i < preps.size(); i++) {
					preps.set(i, OpPalabras.strBetaACompleto(preps.get(i)));
				}
			}
		}
        
        
        // comienzo por poner el esqueleto regular (si no es un verbo dibujado)
        if (!verboAConjugar.isDibujado()){
        	pueblaEsqueletoRegular(verboAConjugar, ocExpansibles, preps);
        }
       
        // luego, las irregularidades de IRR_VERBOS
        List<IrrVerbo> irrVerbos = gerenteIrrVerbos.seleccionaPorVerboParaParticipios(verboAConjugar.getId());
        pueblaConIrrVerbos(ocExpansibles, irrVerbos, preps);

        // luego, las irregularidades de IRR_PARTICIPIOS_ENTEROS
        List<IrrParticipioEntero> irrPartEnteros = gerenteIrrParticipiosEnteros.seleccionaPorVerbo(verboAConjugar.getId());
        pueblaConIrrParticipiosEnteros(ocExpansibles, irrPartEnteros, preps);        

        // por último, la información de IRR_PARTICIPIOS_SIMPLES
        // (va a no-expansibles)
        List<IrrParticipioSimpleBean> irrPartSimples = gerenteIrrParticipiosSimples.seleccionaPorVerbo(verboAConjugar.getId());
        pueblaConIrrParticipiosIndividuales(ocNoExpansibles, irrPartSimples, preps);
        
		//si el verbo original era compuesto, hago una pasada para el compuesto, sin preposiciones, que sobreescribe
        //lo que haya poblado basado en el simple
        //no sé por qué carajo hacía lo que sigue
        /*
        if (verboOriginal.isCompuesto()){
            // comienzo por poner el esqueleto regular (si no es un verbo dibujado)
            if (!verboAConjugar.isDibujado()){
            	pueblaEsqueletoRegular(verboOriginal, ocExpansibles, null);
            }
           
            // luego, las irregularidades de IRR_VERBOS
            irrVerbos = gerenteIrrVerbos.seleccionaPorVerboParaParticipios(verboOriginal.getId());
            pueblaConIrrVerbos(ocExpansibles, irrVerbos, null);

            // luego, las irregularidades de IRR_PARTICIPIOS_ENTEROS
            irrPartEnteros = gerenteIrrParticipiosEnteros.seleccionaPorVerbo(verboOriginal.getId());
            pueblaConIrrParticipiosEnteros(ocExpansibles, irrPartEnteros, null);        

            // por último, la información de IRR_PARTICIPIOS_SIMPLES
            // (va a no-expansibles)
            irrPartSimples = gerenteIrrParticipiosSimples.seleccionaPorVerbo(verboOriginal.getId());
            pueblaConIrrParticipiosIndividuales(ocNoExpansibles, irrPartSimples, null);		
        }
        //fin del absurdo
        */
        
		// expando todas las expansibles
		for (Iterator<Particularidad> it = ocExpansibles.keySet().iterator(); it.hasNext();) {
			Particularidad partic = it.next();
			OcParticipio oc = (OcParticipio) ocExpansibles.get(partic);
			transformaNGEnDetallado(oc);
		}
        
        
        fusionaCompletasConAisladas(ocExpansibles, ocNoExpansibles);
    }

    
    /**
     * puebla el mapa de Ocurrencias expansibles con 1 Ocurrencia, de la misma particularida del verbo, en base a  
     * información deducida de la forma canónica
     * @param beanVerbo
     * @param ocExpansibles
     */
    private void pueblaEsqueletoRegular(VerboBean beanVerbo, Map<Particularidad, OcParticipio> ocExpansibles, List<String> preps){
        OcParticipio oc = new OcParticipio();
        String canonica = OpPalabras.strBetaACompleto(beanVerbo.getVerbo());
        for (Aspecto aspecto: Aspecto.values()){
        	for (Voz voz: Voz.values()){
        		if ((aspecto.equals(Aspecto.Infectivo) || aspecto.equals(Aspecto.Perfectivo)) && voz.equals(Voz.Pasiva))
        		  continue;
        		for (Genero genero: Genero.getMFN()){
                    Object[] res = new Object[3];
                    produceTemasParticipio(canonica, beanVerbo.getTipoVerbo(), aspecto, voz, genero, res);
                    String nominativo = (String) res[0];
                    String genitivo = (String) res[1];
                    nominativo=preposiciones.une(nominativo, preps, false);
                    genitivo=preposiciones.une(genitivo, preps, false);
                    
                    Integer tipoSustantivo = (Integer) res[2];
                    oc.setFormaIndividual(voz, aspecto, FuerteDebil.Debil, genero, Caso.Nominativo, Numero.Singular, 0,
                            nominativo);
                    oc.setFormaIndividual(voz, aspecto, FuerteDebil.Debil, genero, Caso.Genitivo, Numero.Singular, 0,
                            genitivo);
                    oc.setTipoSustantivo(voz, aspecto, FuerteDebil.Debil, genero, Numero.Singular, 0, tipoSustantivo);
                }
                ocExpansibles.put(beanVerbo.getParticularidad(), oc);
        	}
        }
    }
    
    
    /**
     * puebla las Ocurrencias (del mapa de no expansibles), o las crea si es necesario, con la 
     * información de IRR_PARTICIPIOS_SIMPLES
     * @param ocNoExpansibles
     * @param irrPartSimples
     */
    private void pueblaConIrrParticipiosIndividuales(Map<Particularidad, OcParticipio> ocNoExpansibles, List<IrrParticipioSimpleBean> irrPartSimples, List<String> preps){
        for (IrrParticipioSimpleBean regAux : irrPartSimples) {
            // si la particularidad no existe, la agrego
            OcParticipio oc = (OcParticipio) ocNoExpansibles.get(regAux.getPartic());
            if (ocNoExpansibles.get(regAux.getPartic()) == null) {
                oc = new OcParticipio();
                ocNoExpansibles.put(regAux.getPartic(), oc);
            }
            String forma=OpPalabras.strBetaACompleto(regAux.getForma());
            forma=preposiciones.une(forma, preps, false);
            oc.setFormaIndividual(regAux.getVoz(), regAux.getAspecto(), regAux.getFuerte(), regAux.getGenero(), regAux
                    .getCaso(), regAux.getNumero(), regAux.getSubPart(), forma);
        }
    }
    
    /**
     * puebla las Ocurrencias (o las crea si es necesario) con el contenido de IRR_PARTICIPIOS_ENTEROS
     * @param ocExpansibles
     * @param irrPartEnteros
     */
    private void pueblaConIrrParticipiosEnteros(Map<Particularidad, OcParticipio> ocExpansibles, List<IrrParticipioEntero> irrPartEnteros, List<String> preps){
        for (IrrParticipioEntero ipe : irrPartEnteros) {
            String nominativo = OpPalabras.strBetaACompleto(ipe.getNominativo());
            String genitivo = OpPalabras.strBetaACompleto(ipe.getGenitivo());
            nominativo=preposiciones.une(nominativo, preps, false);
            genitivo=preposiciones.une(genitivo, preps, false);
            
            // si la particularidad no existe, la agrego
            OcParticipio oc = (OcParticipio) ocExpansibles.get(ipe.getPartic());
            if (ocExpansibles.get(ipe.getPartic()) == null) {
                oc = new OcParticipio();
                ocExpansibles.put(ipe.getPartic(), oc);
            }
            // si esa combinación ya existe le actualizo el
            // nominativo-genitvo-tipo, de otro modo agrego
            oc.setFormaIndividual(ipe.getVoz(), ipe.getAspecto(), ipe.getFuerte(), ipe.getGenero(), Caso.Nominativo, Numero.Singular, ipe.getSubPart(), nominativo);
            oc.setFormaIndividual(ipe.getVoz(), ipe.getAspecto(), ipe.getFuerte(), ipe.getGenero(), Caso.Genitivo, Numero.Singular, ipe.getSubPart(), genitivo);
            oc.setTipoSustantivo(ipe.getVoz(), ipe.getAspecto(), ipe.getFuerte(), ipe.getGenero(), Numero.Singular, ipe.getSubPart(), ipe.getTipoSustantivo());
            // no deduzco el femenino y neutro, si no están en
            // IRR_PARTICIPIOS_ENTEROS, joderse
        }

    }
    
    
    
    /**
     * puebla las Ocurrencias de cada partucularidad (o las crea si es necesario), según la información
     * de irregularidad proveniente de IRR_VERBOS
     * @param ocExpansibles
     * @param irrVerbos
     */
    private void pueblaConIrrVerbos(Map<Particularidad, OcParticipio> ocExpansibles, List<IrrVerbo> irrVerbos, List<String> preps){
        for (IrrVerbo irrVerbo : irrVerbos) {
            Aspecto aspecto = TransformadorTiempoAspecto.comoAspecto(irrVerbo.getTiempo());
            // ("propaga" sólo se usa para propagar de la activa a la media)
            boolean propaga = (irrVerbo.getVoz().equals(Voz.Activa)) && !Participios.esDeponente(irrVerbo); 
            // si la particularidad no existe, la agrego
            OcParticipio oc = (OcParticipio) ocExpansibles.get(irrVerbo.getPartic());
            if (ocExpansibles.get(irrVerbo.getPartic()) == null) {
                oc = new OcParticipio();
                ocExpansibles.put(irrVerbo.getPartic(), oc);
            }

            for (Genero genero: Genero.getMFN()) {
                Object[] res = new Object[3];
                produceTemasParticipioIrr(res, irrVerbo, aspecto, genero, false);
                String nominativo = (String) res[0];
                String genitivo = (String) res[1];
                nominativo=preposiciones.une(nominativo, preps, false);
                genitivo=preposiciones.une(genitivo, preps, false);
                
                Integer tipoSustantivo = (Integer) res[2];
                // si esa combinación ya existe le actualizo el
                // nominativo-genitvo-tipo, de otro modo agrego
                oc.setFormaIndividual(irrVerbo.getVoz(), aspecto, irrVerbo.getFuerte(), genero, Caso.Nominativo,
                        Numero.Singular, irrVerbo.getSubPart(), nominativo);
                oc.setFormaIndividual(irrVerbo.getVoz(), aspecto, irrVerbo.getFuerte(), genero, Caso.Genitivo,
                        Numero.Singular, irrVerbo.getSubPart(), genitivo);
                oc.setTipoSustantivo(irrVerbo.getVoz(), aspecto, irrVerbo.getFuerte(), genero, Numero.Singular, irrVerbo
                        .getSubPart(), tipoSustantivo);

                if (propaga) {
                    produceTemasParticipioIrr(res, irrVerbo, aspecto, genero, true);
                    nominativo = (String) res[0];
                    genitivo = (String) res[1];
                    nominativo=preposiciones.une(nominativo, preps, false);
                    genitivo=preposiciones.une(genitivo, preps, false);

                    tipoSustantivo = (Integer) res[2];
                    // de nuevo actualizo o agrego, pero con la voz pasiva
                    oc.setFormaIndividual(Voz.Media, aspecto, irrVerbo.getFuerte(), genero, Caso.Nominativo,
                            Numero.Singular, irrVerbo.getSubPart(), nominativo);
                    oc.setFormaIndividual(Voz.Media, aspecto, irrVerbo.getFuerte(), genero, Caso.Genitivo,
                            Numero.Singular, irrVerbo.getSubPart(), genitivo);
                    oc.setTipoSustantivo(Voz.Media, aspecto, irrVerbo.getFuerte(), genero, Numero.Singular, irrVerbo
                            .getSubPart(), tipoSustantivo);
                }
            }
        }
    }
    
    
	

    

    /**
     * produce temas de participio para el género y aspecto dados, en base a la
     * información de irregularidades dada en el registro regIrr, de la tabla
     * IRR_VERBOS
     * 
     * @param res
     * @param regIrr
     * @param aspecto
     * @param genero
     */
    private void produceTemasParticipioIrr(Object[] res, IrrVerbo regIrr, Aspecto aspecto, Genero genero,
            boolean aMedia) {
        Object[] resAux = null;

        if (aspecto == Aspecto.Infectivo) {
            resAux = infectivoIrr(regIrr, genero, aMedia);
        } else if (aspecto == Aspecto.Futuro) {
            resAux = futuroIrr(regIrr, genero, aMedia);
        } else if (aspecto == Aspecto.Confectivo) {
            resAux = confectivoIrr(regIrr, genero, aMedia);
        } else if (aspecto == Aspecto.Perfectivo) {
            resAux = perfectivoIrr(regIrr, genero, aMedia);
        }
        res[0] = resAux[0];
        res[1] = resAux[1];
        res[2] = resAux[2];
    }

    private void produceTemasParticipio(String canonica, int tipoVerbo, Aspecto aspecto, Voz voz, Genero genero,
            Object[] res) {
        Object[] resAux = null;
        boolean deponente;
        
        String tema = Verbos.extraeTemaDadaCanonica(canonica, tipoVerbo, aspecto);
        deponente = TiposVerbo.esDeponente(tipoVerbo);

        StringBuffer sbTema = new StringBuffer(tema);
        tema = sbTema.toString();

        if (aspecto.equals(Aspecto.Infectivo)) {
            resAux = infectivo(tema, voz, genero, deponente, tipoVerbo);
        } else if (aspecto.equals(Aspecto.Futuro)) {
            resAux = futuro(tema, voz, genero, deponente, tipoVerbo);
        } else if (aspecto.equals(Aspecto.Confectivo)) {
            resAux = confectivo(tema, voz, genero, deponente, tipoVerbo);
        } else if (aspecto.equals(Aspecto.Perfectivo)) {
            resAux = perfectivo(tema, voz, genero, deponente, tipoVerbo);
        }
        res[0] = resAux[0];
        res[1] = resAux[1];
        res[2] = resAux[2];
    }

    private Object[] confectivo(String tema, Voz voz, Genero genero, boolean deponente, int tipoVerbo) {
        Object[] objRes = new Object[3];
        boolean fuerte = false;

        String temaCorto;
        String temaLargo;

        Contraccion contraccion = null;
        int juego = -1;
        if (TiposVerbo.esVocalicoNoContracto(tipoVerbo) || TiposVerbo.esVocalicoContracto(tipoVerbo))
            contraccion = Contraccion.sumaAcentuada;
        else if (TiposVerbo.esMudo(tipoVerbo) || TiposVerbo.esDzeta(tipoVerbo))
            contraccion = Contraccion.consonantica;
        else
            contraccion = Contraccion.sumaAcentuada;
        if (TiposVerbo.esVocalicoContractoNormal(tipoVerbo) || TiposVerbo.esVocalicoContractoDeponente(tipoVerbo)) {
            temaLargo = OpPalabras.alargaTemaVC(tema);
            temaCorto = temaLargo;
        } else {
            temaLargo = tema;
            temaCorto = OpPalabras.acortaUltimaVocal(tema);
        }

        // if (TiposVerbo.esVocalicoNoContracto(tipoVerbo) ||
        // TiposVerbo.esVocalicoContracto(tipoVerbo)){
        if (voz.equals(Voz.Activa) && !deponente) {
            if (fuerte) {
                if (juego == 2) {
                    if (genero.equals(Genero.Masculino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("N"), -1, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("NTOJ"), -2, Acento.Agudo,
                                objRes, Genero.Masculino);
                    } else if (genero.equals(Genero.Femenino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SA"), -2, Acento.Circunflejo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("SHJ"), -2, Acento.Agudo,
                                objRes, Genero.Femenino);
                    } else if (genero.equals(Genero.Neutro)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("N"), -1, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("NTOJ"), -2, Acento.Agudo,
                                objRes, Genero.Neutro);
                    }
                } else {
                    if (genero.equals(Genero.Masculino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("WN"), -1, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("ONTOJ"), -2, Acento.Agudo,
                                objRes, Genero.Masculino);
                    } else if (genero.equals(Genero.Femenino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("OUSA"), -2,
                                Acento.Circunflejo, temaLargo, contraccion, OpPalabras
                                        .strBetaACompleto("OUSHJ"), -2, Acento.Agudo, objRes, Genero.Femenino);
                    } else if (genero.equals(Genero.Neutro)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("ON"), -1, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("ONTOJ"), -2, Acento.Agudo,
                                objRes, Genero.Neutro);
                    }
                }
            } else {
                if (genero.equals(Genero.Masculino)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SA_J"), -2, Acento.Agudo,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SANTOJ"), -3, Acento.Ninguno,
                            objRes, Genero.Masculino);
                } else if (genero.equals(Genero.Femenino)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SA_SA"), -3, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SA_SHJ"), -2, Acento.Ninguno,
                            objRes, Genero.Femenino);
                } else if (genero.equals(Genero.Neutro)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SAN"), -2, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SANTOJ"), -3, Acento.Ninguno,
                            objRes, Genero.Neutro);
                }
            }
        } else if (voz.equals(Voz.Media) || (voz.equals(Voz.Activa) && deponente)) {
            if (fuerte) {
                if (juego == 2) {
                    if (genero.equals(Genero.Masculino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("MENOJ"), -3, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("MENOU"), -2, Acento.Agudo,
                                objRes, Genero.Masculino);
                    } else if (genero.equals(Genero.Femenino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("MENH"), -2, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("MENHJ"), -2, Acento.Agudo,
                                objRes, Genero.Femenino);
                    } else if (genero.equals(Genero.Neutro)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("MENON"), -3, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("MENOU"), -2, Acento.Agudo,
                                objRes, Genero.Neutro);
                    }
                } else {
                    if (genero.equals(Genero.Masculino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENOJ"), -3, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENOU"), -2,
                                Acento.Agudo, objRes, Genero.Masculino);
                    } else if (genero.equals(Genero.Femenino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENH"), -2, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENHJ"), -2,
                                Acento.Agudo, objRes, Genero.Femenino);
                    } else if (genero.equals(Genero.Neutro)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENON"), -3, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENOU"), -2,
                                Acento.Agudo, objRes, Genero.Neutro);
                    }
                }
            } else {
                if (genero.equals(Genero.Masculino)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENOJ"), -3, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENOU"), -2, Acento.Ninguno,
                            objRes, Genero.Masculino);
                } else if (genero.equals(Genero.Femenino)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENH"), -2, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENHJ"), -2, Acento.Ninguno,
                            objRes, Genero.Femenino);
                } else if (genero.equals(Genero.Neutro)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENON"), -3, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENOU"), -2, Acento.Ninguno,
                            objRes, Genero.Neutro);
                }

            }
        } else if (voz.equals(Voz.Pasiva)) {
            if (genero.equals(Genero.Masculino)) {
                pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QEIJ"), -1, Acento.Agudo, temaCorto,
                        contraccion, OpPalabras.strBetaACompleto("QENTOJ"), -2, Acento.Ninguno, objRes,
                        Genero.Masculino);
            } else if (genero.equals(Genero.Femenino)) {
                pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QEISA"), -2, Acento.Circunflejo,
                        temaCorto, contraccion, OpPalabras.strBetaACompleto("QEISHJ"), -2, Acento.Ninguno,
                        objRes, Genero.Femenino);
            } else if (genero.equals(Genero.Neutro)) {
                pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QEN"), -1, Acento.Ninguno, temaCorto,
                        contraccion, OpPalabras.strBetaACompleto("QENTOJ"), -2, Acento.Ninguno, objRes,
                        Genero.Neutro);
            }

        }

        return objRes;
    }

    /**
     * produce temas de participio dado un registro de IRR_VERBOS
     * 
     * @param regIrr
     * @param genero
     * @return
     */
    private Object[] confectivoIrr(IrrVerbo regIrr, Genero genero, boolean aMedia) {
        Object[] objRes = new Object[3];
        String tema = OpPalabras.strBetaACompleto(regIrr.getTema());
        Contraccion contraccion = regIrr.getContraccion();
        String temaLargo = OpPalabras.alargaUltimaVocal(tema);
        String temaCorto = OpPalabras.acortaUltimaVocal(tema);
        FuerteDebil fuerte = regIrr.getFuerte();
        int juego = regIrr.getJuego();
        Voz jvoz = regIrr.getVozJuego();
        if (aMedia && jvoz.equals(Voz.Activa))
            jvoz = Voz.Media;
        int juegoDesVerbal = juego;
        boolean enMi = juegoDesVerbal == 2;
        boolean enNuMi = juegoDesVerbal == 6;

        if (enMi || enNuMi) {
            if (enMi) {
                temaLargo = tema;
                temaCorto = OpPalabras.acortaUltimaVocal(tema);
            } else {// en numi
                temaLargo = tema.concat(OpPalabras.strBetaACompleto("NU_"));
                temaCorto = tema;
            }
        }

        // if (TiposVerbo.esVocalicoNoContracto(tipoVerbo) ||
        // TiposVerbo.esVocalicoContracto(tipoVerbo)){
        if (jvoz.equals(Voz.Activa)) {
            if (fuerte == FuerteDebil.Fuerte) {
                if (enMi || enNuMi) {
                    if (genero.equals(Genero.Masculino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("N"), -1, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("NTOJ"), -2, Acento.Agudo,
                                objRes, Genero.Masculino);
                    } else if (genero.equals(Genero.Femenino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SA"), -2, Acento.Circunflejo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("SHJ"), -2, Acento.Agudo,
                                objRes, Genero.Femenino);
                    } else if (genero.equals(Genero.Neutro)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("N"), -1, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("NTOJ"), -2, Acento.Agudo,
                                objRes, Genero.Neutro);
                    }
                } else {
                    if (genero.equals(Genero.Masculino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("WN"), -1, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("ONTOJ"), -2, Acento.Agudo,
                                objRes, Genero.Masculino);
                    } else if (genero.equals(Genero.Femenino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("OUSA"), -2,
                                Acento.Circunflejo, temaLargo, contraccion, OpPalabras
                                        .strBetaACompleto("OUSHJ"), -2, Acento.Agudo, objRes, Genero.Femenino);
                    } else if (genero.equals(Genero.Neutro)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("ON"), -1, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("ONTOJ"), -2, Acento.Agudo,
                                objRes, Genero.Neutro);
                    }
                }
            } else {
                if (genero.equals(Genero.Masculino)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SA_J"), -2, Acento.Agudo,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SANTOJ"), -3, Acento.Ninguno,
                            objRes, Genero.Masculino);
                } else if (genero.equals(Genero.Femenino)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SA_SA"), -3, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SA_SHJ"), -2, Acento.Ninguno,
                            objRes, Genero.Femenino);
                } else if (genero.equals(Genero.Neutro)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SAN"), -2, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SANTOJ"), -3, Acento.Ninguno,
                            objRes, Genero.Neutro);
                }
            }
        } else if (jvoz.equals(Voz.Media)) {
            if (fuerte == FuerteDebil.Fuerte) {
                if (juego == 2) {
                    if (genero.equals(Genero.Masculino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("MENOJ"), -3, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("MENOU"), -2, Acento.Agudo,
                                objRes, Genero.Masculino);
                    } else if (genero.equals(Genero.Femenino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("MENH"), -2, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("MENHJ"), -2, Acento.Agudo,
                                objRes, Genero.Femenino);
                    } else if (genero.equals(Genero.Neutro)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("MENON"), -3, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("MENOU"), -2, Acento.Agudo,
                                objRes, Genero.Neutro);
                    }
                } else {
                    if (genero.equals(Genero.Masculino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENOJ"), -3, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENOU"), -2,
                                Acento.Agudo, objRes, Genero.Masculino);
                    } else if (genero.equals(Genero.Femenino)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENH"), -2, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENHJ"), -2,
                                Acento.Agudo, objRes, Genero.Femenino);
                    } else if (genero.equals(Genero.Neutro)) {
                        pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENON"), -3, Acento.Agudo,
                                temaLargo, contraccion, OpPalabras.strBetaACompleto("OMENOU"), -2,
                                Acento.Agudo, objRes, Genero.Neutro);
                    }
                }
            } else {
                if (genero.equals(Genero.Masculino)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENOJ"), -3, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENOU"), -2, Acento.Ninguno,
                            objRes, Genero.Masculino);
                } else if (genero.equals(Genero.Femenino)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENH"), -2, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENHJ"), -2, Acento.Ninguno,
                            objRes, Genero.Femenino);
                } else if (genero.equals(Genero.Neutro)) {
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENON"), -3, Acento.Ninguno,
                            temaLargo, contraccion, OpPalabras.strBetaACompleto("SAMENOU"), -2, Acento.Ninguno,
                            objRes, Genero.Neutro);
                }

            }
        } else if (jvoz.equals(Voz.Pasiva)) {
            if (fuerte == FuerteDebil.Fuerte) {
                if (genero.equals(Genero.Masculino)) {
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("EIJ"), -1, Acento.Agudo,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("ENTOJ"), -2, Acento.Ninguno,
                            objRes, Genero.Masculino);
                } else if (genero.equals(Genero.Femenino)) {
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("EISA"), -2, Acento.Circunflejo,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("EISHJ"), -2, Acento.Ninguno,
                            objRes, Genero.Femenino);
                } else if (genero.equals(Genero.Neutro)) {
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("EN"), -1, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("ENTOJ"), -2, Acento.Ninguno,
                            objRes, Genero.Neutro);
                }
            } else {
                if (genero.equals(Genero.Masculino)) {
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QEIJ"), -1, Acento.Agudo,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("QENTOJ"), -2, Acento.Ninguno,
                            objRes, Genero.Masculino);
                } else if (genero.equals(Genero.Femenino)) {
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QEISA"), -2, Acento.Circunflejo,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("QEISHJ"), -2, Acento.Ninguno,
                            objRes, Genero.Femenino);
                } else if (genero.equals(Genero.Neutro)) {
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QEN"), -1, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("QENTOJ"), -2, Acento.Ninguno,
                            objRes, Genero.Neutro);
                }

            }

        }

        return objRes;
    }

    /**
     * Función utilitaria que acentúa y contrae los temas y desinencias que se
     * les mandan como parámetro, y coloca el resultado en un array de objetos
     * 
     * @param temaNom
     * @param contrNom
     * @param desNom
     * @param posNom
     * @param aceNom
     * @param temaGen
     * @param contrGen
     * @param desGen
     * @param posGen
     * @param aceGen
     * @param res
     *            el array de resultados que se devuelve lleno
     */
    private void pone(String temaNom, Contraccion contrNom, String desNom, int posNom, Acento aceNom, String temaGen,
            Contraccion contrGen, String desGen, int posGen, Acento aceGen, Object[] res, Genero genero) {
        res[0] = OpPalabras.contraeGenerica(temaNom, desNom, contrNom, posNom, aceNom);
        res[1] = OpPalabras.contraeGenerica(temaGen, desGen, contrGen, posGen, aceGen);
        res[2] = declina.sugiereDeclinacion((String) res[0], (String) res[1], genero);
    }

    private Object[] futuro(String tema, Voz voz, Genero genero, boolean deponente, int tipoVerbo) {

        Contraccion contraccion = null;
        String temaLargo = null, temaCorto = null;
        Object[] res = new Object[3];

        if (TiposVerbo.esVocalicoContracto(tipoVerbo)) {
            contraccion = Contraccion.sumaAcentuada;
            temaLargo = OpPalabras.alargaTemaVC(tema);
            temaCorto = temaLargo;
            tema = temaLargo;
        } else if (TiposVerbo.esVocalicoNoContracto(tipoVerbo)) {
            temaLargo = OpPalabras.alargaUltimaVocal(tema);
            temaCorto = OpPalabras.acortaUltimaVocal(tema);
            contraccion = Contraccion.sumaAcentuada;
        } else if (TiposVerbo.esMudo(tipoVerbo)) {
            temaLargo = OpPalabras.alargaUltimaVocal(tema);
            temaCorto = OpPalabras.acortaUltimaVocal(tema);
            contraccion = Contraccion.consonantica;
        } else {
            temaLargo = OpPalabras.alargaUltimaVocal(tema);
            temaCorto = OpPalabras.acortaUltimaVocal(tema);
            contraccion = Contraccion.consonantica;
        }

        if (contraccion != Contraccion.vocalica && contraccion != Contraccion.jonica) {
            if (voz.equals(Voz.Activa) && !deponente) {
                if (genero.equals(Genero.Masculino))
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SWN"), -2, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SONTOJ"), -3, Acento.Ninguno, res,
                            Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(tema, contraccion, OpPalabras.strBetaACompleto("SOUSA"), -3, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SOUSHJ"), -2, Acento.Ninguno, res,
                            Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SON"), -2, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SONTOJ"), -3, Acento.Ninguno, res,
                            Genero.Neutro);
            } else if (voz.equals(Voz.Media) || (deponente && voz.equals(Voz.Activa))) {
                if (genero.equals(Genero.Masculino))
                    pone(tema, contraccion, OpPalabras.strBetaACompleto("SOMENOJ"), -3, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SOMENOU"), -2, Acento.Ninguno, res,
                            Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(tema, contraccion, OpPalabras.strBetaACompleto("SOMENH"), -2, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SOMENHJ"), -2, Acento.Ninguno, res,
                            Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(tema, contraccion, OpPalabras.strBetaACompleto("SOMENON"), -3, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SOMENOU"), -2, Acento.Ninguno, res,
                            Genero.Neutro);
            } else if (voz.equals(Voz.Pasiva)) {
                if (genero.equals(Genero.Masculino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENOJ"), -3, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENOU"), -2,
                            Acento.Ninguno, res, Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENH"), -2, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENHJ"), -2,
                            Acento.Ninguno, res, Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENON"), -3, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENOU"), -2,
                            Acento.Ninguno, res, Genero.Neutro);
            }
        } else { // futuro ático
            if (voz.equals(Voz.Activa) && !deponente) {
                if (genero.equals(Genero.Masculino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("WN"), 0, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("ONTOJ"), 0, Acento.Ninguno,
                            res, Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("OUSA"), 0, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("OUSHJ"), 0, Acento.Ninguno,
                            res, Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("ON"), -1, Acento.Circunflejo,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("ONTOJ"), 0, Acento.Ninguno,
                            res, Genero.Neutro);
            } else if (voz.equals(Voz.Media) || (deponente && voz.equals(Voz.Activa))) {
                if (genero.equals(Genero.Masculino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENOJ"), -3, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENOU"), -2, Acento.Ninguno,
                            res, Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENH"), -2, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENHJ"), -2, Acento.Ninguno,
                            res, Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENON"), -3, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENOU"), -2, Acento.Ninguno,
                            res, Genero.Neutro);
            }
        }
        return res;
    }

    /**
     * genera temas de participio dado un registro de IRR_VERBOS
     * 
     * @param regIrr
     * @param genero
     * @return
     */
    private Object[] futuroIrr(IrrVerbo regIrr, Genero genero, boolean aMedia) {
        String temaLargo = null, temaCorto = null;
        Object[] res = new Object[3];
        String tema = OpPalabras.strBetaACompleto(regIrr.getTema());
        Contraccion contraccion = regIrr.getContraccion();
        temaLargo = OpPalabras.alargaUltimaVocal(tema);
        temaCorto = OpPalabras.acortaUltimaVocal(tema);
        Voz jvoz = regIrr.getVozJuego();
        if (aMedia && jvoz.equals(Voz.Activa))
            jvoz = Voz.Media;

        if (contraccion != Contraccion.vocalica && contraccion != Contraccion.jonica) {
            if (jvoz.equals(Voz.Activa)) {
                if (genero.equals(Genero.Masculino))
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SWN"), -2, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SONTOJ"), -3, Acento.Ninguno, res,
                            Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(tema, contraccion, OpPalabras.strBetaACompleto("SOUSA"), -3, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SOUSHJ"), -2, Acento.Ninguno, res,
                            Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(temaLargo, contraccion, OpPalabras.strBetaACompleto("SON"), -2, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SONTOJ"), -3, Acento.Ninguno, res,
                            Genero.Neutro);
            } else if (jvoz.equals(Voz.Media)) {
                if (genero.equals(Genero.Masculino))
                    pone(tema, contraccion, OpPalabras.strBetaACompleto("SOMENOJ"), -3, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SOMENOU"), -2, Acento.Ninguno, res,
                            Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(tema, contraccion, OpPalabras.strBetaACompleto("SOMENH"), -2, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SOMENHJ"), -2, Acento.Ninguno, res,
                            Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(tema, contraccion, OpPalabras.strBetaACompleto("SOMENON"), -3, Acento.Ninguno, tema,
                            contraccion, OpPalabras.strBetaACompleto("SOMENOU"), -2, Acento.Ninguno, res,
                            Genero.Neutro);
            } else if (jvoz.equals(Voz.Pasiva)) {
                if (genero.equals(Genero.Masculino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENOJ"), -3, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENOU"), -2,
                            Acento.Ninguno, res, Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENH"), -2, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENHJ"), -2,
                            Acento.Ninguno, res, Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENON"), -3, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("QHSOMENOU"), -2,
                            Acento.Ninguno, res, Genero.Neutro);
            }
        } else { // futuro ático
            if (jvoz.equals(Voz.Activa)) {
                if (genero.equals(Genero.Masculino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("WN"), 0, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("ONTOJ"), 0, Acento.Ninguno,
                            res, Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("OUSA"), 0, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("OUSHJ"), 0, Acento.Ninguno,
                            res, Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("ON"), -1, Acento.Circunflejo,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("ONTOJ"), 0, Acento.Ninguno,
                            res, Genero.Neutro);
            } else if (jvoz.equals(Voz.Media)) {
                if (genero.equals(Genero.Masculino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENOJ"), -3, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENOU"), -2, Acento.Ninguno,
                            res, Genero.Masculino);
                else if (genero.equals(Genero.Femenino))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENH"), -2, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENHJ"), -2, Acento.Ninguno,
                            res, Genero.Femenino);
                else if (genero.equals(Genero.Neutro))
                    pone(temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENON"), -3, Acento.Ninguno,
                            temaCorto, contraccion, OpPalabras.strBetaACompleto("OMENOU"), -2, Acento.Ninguno,
                            res, Genero.Neutro);
            }
        }
        return res;
    }

    private Object[] perfectivo(String tema, Voz voz, Genero genero, boolean deponente, int tipoVerbo) {
        String resNominativo = null, resGenitivo = null;
        int resTipoSustantivo = 0;
        String temaLargo, temaCorto;
        Contraccion contraccion;

        if (TiposVerbo.esVocalicoContracto(tipoVerbo)) {
            temaLargo = OpPalabras.alargaTemaVC(tema);
            temaCorto = temaLargo;
            tema = temaLargo;
        } else {
            temaLargo = OpPalabras.alargaUltimaVocal(tema);
            temaCorto = OpPalabras.acortaUltimaVocal(tema);
        }
        tema = OpPalabras.reduplica(tema);
        temaLargo = OpPalabras.reduplica(temaLargo);
        temaCorto = tema = OpPalabras.reduplica(temaCorto);
        if (TiposVerbo.esMudo(tipoVerbo) || TiposVerbo.esDzeta(tipoVerbo))
            contraccion = Contraccion.consonantica;
        else
            contraccion = Contraccion.sumaAcentuada;

        if (voz.equals(Voz.Activa) && !deponente) {
            if (genero.equals(Genero.Masculino)) {
                resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KWJ"), contraccion,
                        -1, Acento.Agudo);
                resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KOTOJ"), contraccion,
                        -2, Acento.Ninguno);
            } else if (genero.equals(Genero.Femenino)) {
                resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KUIA"), contraccion,
                        -2, Acento.Circunflejo);
                resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KUIA_J"), contraccion,
                        -2, Acento.Agudo);
            } else if (genero.equals(Genero.Neutro)) {
                resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KON"), contraccion,
                        -1, Acento.Ninguno);
                resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KOTOJ"), contraccion,
                        -2, Acento.Ninguno);
            }
        } else if (voz.equals(Voz.Media) || (voz.equals(Voz.Activa) && deponente)) {
            if (genero.equals(Genero.Masculino)) {
                resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENOJ"),
                        contraccion, -2, Acento.Agudo);
                resGenitivo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("MENOU"), contraccion, -2,
                        Acento.Ninguno);
            } else if (genero.equals(Genero.Femenino)) {
                resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENH"), contraccion,
                        -2, Acento.Agudo);
                resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENHJ"), contraccion,
                        -2, Acento.Ninguno);
            } else if (genero.equals(Genero.Neutro)) {
                resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENON"),
                        contraccion, -2, Acento.Agudo);
                resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENOU"), contraccion,
                        -2, Acento.Ninguno);
            }
        }
        resTipoSustantivo = declina.sugiereDeclinacion(resNominativo, resGenitivo, genero);
        return new Object[] { resNominativo, resGenitivo, new Integer(resTipoSustantivo) };
    }

    /**
     * produce temas de participio dado un registro de IRR_VERBOS
     * 
     * @param regIrr
     * @param genero
     * @return
     */
    private Object[] perfectivoIrr(IrrVerbo regIrr, Genero genero, boolean aMedia) {
        String resNominativo = null, resGenitivo = null;
        int resTipoSustantivo = 0;
        String temaLargo, temaCorto;
        String tema = OpPalabras.strBetaACompleto(regIrr.getTema());

        temaCorto = OpPalabras.acortaUltimaVocal(tema);
        temaLargo = tema;
        if (regIrr.isReduplicacion()) {
            tema = OpPalabras.reduplica(tema);
            temaLargo = OpPalabras.reduplica(temaLargo);
            temaCorto = tema = OpPalabras.reduplica(temaCorto);
        }
        Contraccion contraccion = regIrr.getContraccion();
        Voz jvoz = regIrr.getVozJuego();
        if (aMedia && jvoz.equals(Voz.Activa))
            jvoz = Voz.Media;
        FuerteDebil fuerte = regIrr.getFuerte();

        if (jvoz.equals(Voz.Activa)) {
            if (fuerte == FuerteDebil.Fuerte) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("WJ"),
                            contraccion, -1, Acento.Agudo);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("OTOJ"),
                            contraccion, -2, Acento.Ninguno);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("UIA"),
                            contraccion, -2, Acento.Circunflejo);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("UIA_J"),
                            contraccion, -2, Acento.Agudo);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("OJ"),
                            contraccion, -1, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("OTOJ"),
                            contraccion, -2, Acento.Ninguno);
                }
            } else {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KWJ"),
                            contraccion, -1, Acento.Agudo);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KOTOJ"),
                            contraccion, -2, Acento.Ninguno);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KUIA"),
                            contraccion, -2, Acento.Circunflejo);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KUIA_J"),
                            contraccion, -2, Acento.Agudo);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KOJ"),
                            contraccion, -1, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("KOTOJ"),
                            contraccion, -2, Acento.Ninguno);
                }

            }
        } else if (jvoz.equals(Voz.Media)) {
            if (genero.equals(Genero.Masculino)) {
                resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENOJ"),
                        contraccion, -2, Acento.Agudo);
                resGenitivo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("MENOU"), contraccion, -2,
                        Acento.Ninguno);
            } else if (genero.equals(Genero.Femenino)) {
                resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENH"), contraccion,
                        -2, Acento.Agudo);
                resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENHJ"), contraccion,
                        -2, Acento.Ninguno);
            } else if (genero.equals(Genero.Neutro)) {
                resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENON"),
                        contraccion, -2, Acento.Agudo);
                resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENOU"), contraccion,
                        -2, Acento.Ninguno);
            }
        }
        resTipoSustantivo = declina.sugiereDeclinacion(resNominativo, resGenitivo, genero);
        return new Object[] { resNominativo, resGenitivo, new Integer(resTipoSustantivo) };
    }

    /**
     * Usado para crear temas de participio a partir de un registro de
     * irregularidad obtenido de IRR_VERBOS
     * 
     * @param regIrr
     * @param genero
     * @return
     */
    private Object[] infectivoIrr(IrrVerbo regIrr, Genero genero, boolean aMedia) {

        String resNominativo = null, resGenitivo = null;
        int resTipoSustantivo = 0;
        Contraccion contraccion = regIrr.getContraccion();

        String tema = OpPalabras.strBetaACompleto(regIrr.getTema());
        Voz voz = regIrr.getVozJuego();
        if (aMedia)
            voz = Voz.Media;
        int juegoDesVerbal = regIrr.getJuego();
        boolean enMi = juegoDesVerbal == 2;
        boolean enNuMi = juegoDesVerbal == 6;

        if (enMi || enNuMi) {
            String temaLargo, temaCorto;
            if (enMi) {
                temaLargo = tema;
                temaCorto = OpPalabras.acortaUltimaVocal(tema);
            } else {// en numi
                temaLargo = tema.concat(OpPalabras.strBetaACompleto("NU_"));
                temaCorto = tema.concat(OpPalabras.strBetaACompleto("NU"));
            }
            if (voz.equals(Voz.Activa)) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(temaLargo, OpPalabras.strBetaACompleto("J"),
                            contraccion, -1, Acento.Agudo);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("NTOJ"),
                            contraccion, -2, Acento.Agudo);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(temaLargo, OpPalabras.strBetaACompleto("SA"),
                            contraccion, -2, Acento.Circunflejo);
                    resGenitivo = OpPalabras.contraeGenerica(temaLargo, OpPalabras.strBetaACompleto("SHJ"),
                            contraccion, -2, Acento.Agudo);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("N"),
                            contraccion, -1, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("NTOJ"),
                            contraccion, -2, Acento.Agudo);
                }
            } else if (voz.equals(Voz.Media)) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENOJ"),
                            contraccion, 0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENOU"),
                            contraccion, 0, Acento.Ninguno);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENH"),
                            contraccion, 0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENHJ"),
                            contraccion, 0, Acento.Ninguno);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENON"),
                            contraccion, 0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENOU"),
                            contraccion, 0, Acento.Ninguno);
                }
            }
        } else if (!contraccion.equals(Contraccion.vocalica)) {
            String temaCorto = OpPalabras.acortaUltimaVocal(tema);

            if (voz.equals(Voz.Activa)) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("WN"), contraccion, 0,
                            Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("ONTOJ"),
                            contraccion, 0, Acento.Ninguno);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OUSA"), contraccion,
                            0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OUSHJ"), contraccion,
                            0, Acento.Ninguno);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("ON"), contraccion,
                            -2, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("ONTOJ"),
                            contraccion, 0, Acento.Ninguno);
                }
            } else if (voz.equals(Voz.Media)) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OMENOJ"),
                            contraccion, 0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("OMENOU"),
                            contraccion, 0, Acento.Ninguno);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OMENH"), contraccion,
                            0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OMENHJ"), contraccion,
                            0, Acento.Ninguno);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("OMENON"),
                            contraccion, 0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("OMENOU"),
                            contraccion, 0, Acento.Ninguno);
                }
            }

        } else if (contraccion.equals(Contraccion.vocalica)) {
            String temaCorto = tema;
            if (voz.equals(Voz.Activa)) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("WN"),
                            Contraccion.vocalica, -1, Acento.Circunflejo)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("ONTOJ"),
                            Contraccion.vocalica, -2, Acento.Circunflejo)[2];
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OUSA"),
                            Contraccion.vocalica, -2, Acento.Circunflejo)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OUSHJ"),
                            Contraccion.vocalica, -2, Acento.Agudo)[2];
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("ON"),
                            Contraccion.vocalica, -1, Acento.Circunflejo)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("ONTOJ"),
                            Contraccion.vocalica, -2, Acento.Circunflejo)[2];
                }
            } else if (voz.equals(Voz.Media)) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENOJ"),
                            Contraccion.vocalica, -3)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENOU"),
                            Contraccion.vocalica, -2)[2];
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENH"),
                            Contraccion.vocalica, -2)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENHJ"),
                            Contraccion.vocalica, -2)[2];
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENON"),
                            Contraccion.vocalica, -3)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENOU"),
                            Contraccion.vocalica, -2)[2];
                }
            }
        }
        resTipoSustantivo = declina.sugiereDeclinacion(resNominativo, resGenitivo, genero);
        return new Object[] { resNominativo, resGenitivo, new Integer(resTipoSustantivo) };
    }

    private Object[] infectivo(String tema, Voz voz, Genero genero, boolean deponente, int tipoVerbo) {

        String resNominativo = null, resGenitivo = null;
        Contraccion contraccion = Contraccion.sumaAcentuada;
        boolean juego2 = TiposVerbo.esEnMiPropiamente(tipoVerbo) || TiposVerbo.esEnNuMi(tipoVerbo);

        if (juego2) {
            String temaCorto = OpPalabras.acortaUltimaVocal(tema);
            if (voz.equals(Voz.Activa) && !deponente) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("J"), contraccion, -1,
                            Acento.Agudo);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("NTOJ"),
                            contraccion, -2, Acento.Agudo);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("SA"), contraccion,
                            -2, Acento.Circunflejo);
                    resGenitivo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("SHJ"), contraccion, -2,
                            Acento.Agudo);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("N"), contraccion, -1,
                            Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("NTOJ"),
                            contraccion, -2, Acento.Agudo);
                }
            } else if (voz.equals(Voz.Media) || (voz.equals(Voz.Activa) && deponente)) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("MENOJ"), contraccion,
                            0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENOU"),
                            contraccion, 0, Acento.Ninguno);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("MENH"), contraccion,
                            0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("MENHJ"), contraccion,
                            0, Acento.Ninguno);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENON"),
                            contraccion, 0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("MENOU"),
                            contraccion, 0, Acento.Ninguno);
                }
            }
        } else if (!TiposVerbo.esVocalicoContracto(tipoVerbo) && !contraccion.equals(Contraccion.vocalica)) {
            String temaCorto = tema; // OpPalabras.acortaUltimaVocal(tema);
            if (voz.equals(Voz.Activa) && !deponente) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("WN"), contraccion, 0,
                            Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("ONTOJ"),
                            contraccion, 0, Acento.Ninguno);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OUSA"), contraccion,
                            0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OUSHJ"), contraccion,
                            0, Acento.Ninguno);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("ON"), contraccion,
                            -2, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("ONTOJ"),
                            contraccion, 0, Acento.Ninguno);
                }
            } else if (voz.equals(Voz.Media) || (voz.equals(Voz.Activa) && deponente)) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OMENOJ"),
                            contraccion, 0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("OMENOU"),
                            contraccion, 0, Acento.Ninguno);
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OMENH"), contraccion,
                            0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(tema, OpPalabras.strBetaACompleto("OMENHJ"), contraccion,
                            0, Acento.Ninguno);
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("OMENON"),
                            contraccion, 0, Acento.Ninguno);
                    resGenitivo = OpPalabras.contraeGenerica(temaCorto, OpPalabras.strBetaACompleto("OMENOU"),
                            contraccion, 0, Acento.Ninguno);
                }
            }

        } else if (TiposVerbo.esVocalicoContracto(tipoVerbo) || contraccion.equals(Contraccion.vocalica)) {
            String temaCorto = tema;
            if (voz.equals(Voz.Activa) && !deponente) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("WN"),
                            Contraccion.vocalica, -1, Acento.Circunflejo)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("ONTOJ"),
                            Contraccion.vocalica, -2, Acento.Circunflejo)[2];
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OUSA"),
                            Contraccion.vocalica, -2, Acento.Circunflejo)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OUSHJ"),
                            Contraccion.vocalica, -2, Acento.Agudo)[2];
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("ON"),
                            Contraccion.vocalica, -1, Acento.Circunflejo)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("ONTOJ"),
                            Contraccion.vocalica, -2, Acento.Circunflejo)[2];
                }
            } else if (voz.equals(Voz.Media) || (voz.equals(Voz.Activa) && deponente)) {
                if (genero.equals(Genero.Masculino)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENOJ"),
                            Contraccion.vocalica, -3)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENOU"),
                            Contraccion.vocalica, -2)[2];
                } else if (genero.equals(Genero.Femenino)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENH"),
                            Contraccion.vocalica, -2)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENHJ"),
                            Contraccion.vocalica, -2)[2];
                } else if (genero.equals(Genero.Neutro)) {
                    resNominativo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENON"),
                            Contraccion.vocalica, -3)[2];
                    resGenitivo = OpPalabras.contraeVocal(temaCorto, OpPalabras.strBetaACompleto("OMENOU"),
                            Contraccion.vocalica, -2)[2];
                }
            }
        }
        if (resNominativo==null || resGenitivo==null){
        	StringBuffer mensaje=new StringBuffer();
        	if (resNominativo==null){
        		mensaje.append("la reconstrucción del nominativo ");
        	}else{
        		mensaje.append("la reconstrucción del genitivo ");
        	}
        	mensaje.append(" falló para el siguiente tema=" + OpPalabras.strCompletoABeta(tema) + "\n");
        	mensaje.append("   voz=" + voz + "\n");
        	mensaje.append("   genero=" + genero + "\n");
        	mensaje.append("   deponente=" + deponente + "\n");
        	mensaje.append("   tipoVerbo=" + tipoVerbo + "\n");
        	throw new RuntimeException(mensaje.toString());
        }
        
        int resTipoSustantivo = declina.sugiereDeclinacion(resNominativo, resGenitivo, genero);
        return new Object[] { resNominativo, resGenitivo, new Integer(resTipoSustantivo) };
    }

    private static boolean esDeponente(IrrVerbo regIrr) {
        return (regIrr.getVoz().equals(Voz.Activa) && regIrr.getVozJuego().equals(Voz.Media));
    }

    static boolean esFuturoAtico(IrrVerbo regIrr) {
        return (regIrr.getTiempo().equals(Tiempo.Futuro) && regIrr.getTiempoJuego().equals(Tiempo.Presente) && regIrr
                .getContraccion().equals(Contraccion.vocalica));
    }

    /**
     * Dada una ocurrencia de participio que tiene nada más que el nominativo y
     * el genitivo singular de cada género, la puebla internamente con todas las
     * formas
     * 
     * @param op
     */
    private void transformaNGEnDetallado(OcParticipio op) {
        for (int iVoz = Voz.getInt(Voz.Activa); iVoz <= Voz.getInt(Voz.Pasiva); iVoz++) {
            Voz voz = Voz.getEnum(iVoz);
            for (Aspecto aspecto : Aspecto.values()) {
                for (FuerteDebil fuerte : FuerteDebil.values()) {
                    for (Genero genero : Genero.getMFN()) {
                        int cantFormasNom = op.cantidadDeFormas(voz, aspecto, fuerte, genero, Caso.Nominativo,
                                Numero.Singular);
                        int cantFormasGen = op.cantidadDeFormas(voz, aspecto, fuerte, genero, Caso.Genitivo,
                                Numero.Singular);
                        int cantidad = Math.min(cantFormasNom, cantFormasGen);
                        for (int i = 0; i < cantidad; i++) {
                            Integer tipoSustantivo = op.getTipoSustantivo(voz, aspecto, fuerte, genero,
                                    Numero.Singular, i);
                            String nominativo = op.getFormaIndividual(voz, aspecto, fuerte, genero, Caso.Nominativo,
                                    Numero.Singular, i);
                            String genitivo = op.getFormaIndividual(voz, aspecto, fuerte, genero, Caso.Genitivo,
                                    Numero.Singular, i);
                            if (nominativo != null && genitivo != null) {
                                OcNominal os = declina.declinaRegular(nominativo, genitivo, tipoSustantivo
                                        .intValue(), false, false);
                                ocSustAOcPart(os, op, voz, aspecto, fuerte, genero, i);
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * Pasa el contenido de una ocurrencia de sustantivo a la ocurrencia de
     * participio, en la voz-aspecto-fuerte-genero-subindice dado de ésta
     * última.
     * 
     * @param os
     * @param op
     * @param genero
     */
    private static void ocSustAOcPart(OcNominal os, OcParticipio op, Voz voz, Aspecto aspecto, FuerteDebil fuerte,
            Genero genero, int subindice) {
        for (int iNum = 1; iNum <= 3; iNum++) {
            Numero numero = Numero.getEnum(iNum);
            for (int iCaso = 1; iCaso <= 5; iCaso++) {
                Caso caso = Caso.getEnum(iCaso);
                String forma = os.getFormaIndividual(caso, numero, 0);
                op.setFormaIndividual(voz, aspecto, fuerte, genero, caso, numero, subindice, forma);
            }
        }
    }

    /**
     * Fusiona el map de OcParticipios completas con el de aisladas. El
     * resultado queda en mapCompletas
     * 
     * @param mapCompletas
     *            un mapa de OcParticipios ya expandidas (completadas)
     * @param aislada
     *            un mapa de OcParticipios aisladas, conteniendo una o unas
     *            pocas formas posiblemente no nom/gen, y cuyas claves pueden
     *            coincidir parcialmente o no con las claves de mapCompletas
     */
    public static void fusionaCompletasConAisladas(Map<Particularidad, OcParticipio> mapOcCompletas, 
    		Map<Particularidad, OcParticipio> mapOcAisladas) {
        Set<Particularidad> clavesExpansibles = mapOcCompletas.keySet();
        Set<Particularidad> clavesAisladas = mapOcAisladas.keySet();

        //busco la interseccion
        Set<Particularidad> interseccion = new HashSet<Particularidad>();
        interseccion.addAll(clavesExpansibles);
        interseccion.retainAll(clavesAisladas);

        //en todos los de la interseccion, sobreescribo las formas completas con las aisladas
        for (Particularidad partic : interseccion) {
            OcParticipio completa = (OcParticipio) mapOcCompletas.get(partic);
            OcParticipio aislada = (OcParticipio) mapOcAisladas.get(partic);
            for (int iVoz = Voz.getInt(Voz.Activa); iVoz <= Voz.getInt(Voz.Pasiva); iVoz++) {
                Voz voz = Voz.getEnum(iVoz);
                for (Aspecto aspecto : Aspecto.values()) {
                    for (FuerteDebil fuerte : FuerteDebil.values()) {
                        for (Genero genero : Genero.values()) {
                            for (Numero numero : Numero.values()) {
                                for (Caso caso : Caso.values()) {
                                    int cantidadAislada = aislada.cantidadDeFormas(voz, aspecto, fuerte, genero, caso,
                                            numero);
                                    for (int i = 0; i < cantidadAislada; i++) {
                                        String formaAislada = aislada.getFormaIndividual(voz, aspecto, fuerte, genero,
                                                caso, numero, i);
                                        completa.setFormaIndividual(voz, aspecto, fuerte, genero, caso, numero, i,
                                                formaAislada);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //las ocurrencias de aisladas cuyas particularidades que no forman parte de la intersección se agregan 
        //directamente al mapa de completas en forma independiente
        Set<Particularidad> restoAisladas = new HashSet<Particularidad>();
        restoAisladas.addAll(clavesAisladas);
        restoAisladas.removeAll(interseccion);
        for ( Particularidad partic  : restoAisladas) {
            OcParticipio aislada = (OcParticipio) mapOcAisladas.get(partic);
            mapOcCompletas.put(partic, aislada);
        }
    }

    /**
     * @return Returns the gerenteIrrVerbos.
     */
    public GerenteIrrVerbos getGerenteIrrVerbos() {
        return gerenteIrrVerbos;
    }

    /**
     * @param gerenteIrrVerbos The gerenteIrrVerbos to set.
     */
    public void setGerenteIrrVerbos(GerenteIrrVerbos gerenteIrrVerbos) {
        this.gerenteIrrVerbos = gerenteIrrVerbos;
    }

    /**
     * @return Returns the gerenteIrrParticipiosEnteros.
     */
    public GerenteIrrParticipiosEnteros getGerenteIrrParticipiosEnteros() {
        return gerenteIrrParticipiosEnteros;
    }

    /**
     * @param gerenteIrrParticipiosEnteros The gerenteIrrParticipiosEnteros to set.
     */
    public void setGerenteIrrParticipiosEnteros(GerenteIrrParticipiosEnteros gerenteIrrParticipiosEnteros) {
        this.gerenteIrrParticipiosEnteros = gerenteIrrParticipiosEnteros;
    }

    /**
     * @return Returns the gerenteIrrParticipiosSimples.
     */
    public GerenteIrrParticipiosSimples getGerenteIrrParticipiosSimples() {
        return gerenteIrrParticipiosSimples;
    }

    /**
     * @param gerenteIrrParticipiosSimples The gerenteIrrParticipiosSimples to set.
     */
    public void setGerenteIrrParticipiosSimples(GerenteIrrParticipiosSimples gerenteIrrParticipiosSimples) {
        this.gerenteIrrParticipiosSimples = gerenteIrrParticipiosSimples;
    }

    /**
     * @return Returns the declina.
     */
    public Declina getDeclina() {
        return declina;
    }

    /**
     * @param declina The declina to set.
     */
    public void setDeclina(Declina declina) {
        this.declina = declina;
    }

    public void setGerenteVerbos(GerenteVerbos gerenteVerbos) {
        this.gerenteVerbos = gerenteVerbos;
    }

	/**
	 * @param cacheFlexionParticipio The cacheFlexionParticipio to set.
	 */
	public void setCacheFlexionParticipio(CacheFlexionParticipio cacheFlexionParticipio) {
		this.cacheFlexionParticipio = cacheFlexionParticipio;
	}



	public void setGerenteVerbosCompuestos(GerenteVerbosCompuestos gerenteVerbosCompuestos) {
		this.gerenteVerbosCompuestos = gerenteVerbosCompuestos;
	}



	public void setGerentePreposicionesEnVerbos(GerentePreposicionesEnVerbos gerentePreposicionesEnVerbos) {
		this.gerentePreposicionesEnVerbos = gerentePreposicionesEnVerbos;
	}



	/**
	 * @param preposiciones the preposiciones to set
	 */
	public void setPreposiciones(Preposiciones preposiciones) {
		this.preposiciones = preposiciones;
	}

}
