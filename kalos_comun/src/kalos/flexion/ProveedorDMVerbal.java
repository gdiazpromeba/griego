/*
 * Created on May 4, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package kalos.flexion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import kalos.beans.VerboBean;
import kalos.datos.gerentes.GerenteIrrVerbos;
import kalos.datos.gerentes.GerenteVerbos;
import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.Persona;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.Voz;
import kalos.flexion.conjugacion.CacheFlexionInfinitivos;
import kalos.flexion.conjugacion.CacheFlexionVerbos;
import kalos.flexion.conjugacion.Infinitivos;
import kalos.flexion.conjugacion.Verbos;
import kalos.flexion.conjugacion.negocio.Ocurrencia;
import kalos.flexion.conjugacion.negocio.OcurrenciaInfinitivo;

/**
 * 
 * Representa el contacto del paquete de conjugación con el exterior.
 * Su objetivo es proveer representaciones de la conjugación de un
 * verbo o infinitivo, via dataModules
 * Estos datamodules presentan el dato bruto, sin rotar ni seleccionar ni transformar en renderers de celda
 * Su interfaz con el mundo exterior (fuera de este paquete) debe ser la clase CreadorTableModelsGrilla  
 * @author GDiaz
 *
 */
public class ProveedorDMVerbal {
	
    private GerenteIrrVerbos gerenteIrrVerbos;
    private GerenteVerbos gerenteVerbos;
    private Verbos verbos;
    private Infinitivos infinitivos;
    private UtilidadesTM utilidadesTM;
    private CacheFlexionVerbos cacheFlexionVerbos; 
    private CacheFlexionInfinitivos cacheFlexionInfinitivos;
	
	
	/**
	 * Produce un tableModel con los infinitivos, adecuado para reportar
	 * @param conta
	 * @return
	 */
	DefaultTableModel modeloInfinitivos(String idVerbo){
		VerboBean veb=gerenteVerbos.seleccionaUno(idVerbo);
		DefaultTableModel tmInf=(DefaultTableModel) modeloInfinitivosBasico(veb);
		return tmInf;
	}

	
	

	
	

	
	
	/**
	 * Produce un tablemodel simple, encolumnado por modos, basándose los datos de un objeto manOc
	 * que ya está poblado. Se llama desde el ABM de verbos solamente, para tener una vista preliminar 
	 * de la conjugacion antes de ingresar el verbo. 
	 * @param manOc
	 * @param sinDual
	 * @return
	 */
	DefaultTableModel modeloVerbosPorModosRegular(Particularidad partic, Ocurrencia oc, boolean sinDual){
		DefaultTableModel tm=(DefaultTableModel) modeloVerbosBasicoRegular(partic, oc, sinDual);
		utilidadesTM.borraColumna(tm, "PARTIC");
		
		utilidadesTM.horizontaliza(tm, 
		      new String[]{"VOZ", "TIEMPO", "FUERTE", "PERSONA", "SUBPART"},
		      new String[]{"VOZ", "TIEMPO", "FUERTE", "PERSONA", "SUBPART"},
					"MODO", 
					new Object[]{Modo.Indicativo, Modo.Subjuntivo, Modo.Optativo, Modo.Imperativo},
					"FORMA",
					new String[]{"INDICATIVO", "SUBJUNTIVO", "OPTATIVO", "IMPERATIVO"}			                                                                                           
		);

      utilidadesTM.ordenaSegunColumnas(tm, new String[]{"VOZ", "TIEMPO", "FUERTE", "PERSONA", "SUBPART" },
      		new boolean[]{true, true, true, true, true, true});
      //ya no necesito las subpart para nada
      utilidadesTM.borraColumna(tm, "SUBPART");
      
      return tm;
	}	
	

	
	
	/**
	 * Dado un verbo, produce un tablemodel ya encolumnado por modos, lo cual es más adecuado para 
	 * ser convertido en reporte.
	 * @param conta
	 * @return un DefaultTableModel con las formas conjugadas encolumnadas por modo
	 */
	DefaultTableModel modeloVerbosPorModos(String idVerbo, boolean sinDual){
		DefaultTableModel tm=(DefaultTableModel) modeloVerbosBasico(idVerbo, sinDual);
		

		
		
		
		utilidadesTM.horizontaliza(tm, 
		      new String[]{"PARTIC", "ORDEN_PARTIC", "VOZ", "TIEMPO", "FUERTE", "PERSONA", "SUBPART"},
		      new String[]{"ORDEN_PARTIC", "VOZ", "TIEMPO", "FUERTE", "PERSONA", "SUBPART"},
					"MODO", 
					new Object[]{Modo.Indicativo, Modo.Subjuntivo, Modo.Optativo, Modo.Imperativo},
					"FORMA",
					new String[]{"INDICATIVO", "SUBJUNTIVO", "OPTATIVO", "IMPERATIVO"}			                                                                                           
		);

		

		
      //ordeno para evitar repetir personas
      utilidadesTM.ordenaSegunColumnas(tm, new String[]{"ORDEN_PARTIC",  "VOZ", "TIEMPO", "FUERTE", "PERSONA", "SUBPART" },
      		new boolean[]{true, true, true, true, true, true});
      
      
      
      

      return tm;
	}
	
	
	
	/**
	 * Dado un verbo, produce un tablemodel de las primeras o segundas personas (compacto),
	 * ya encolumnado por modos, lo cual es más adecuado para ser convertido en reporte.
	 * @param conta
	 * @return un DefaultTableModel con las formas conjugadas encolumnadas por modo y sólo las 1ps o 2ps del imperativo
	 */
	DefaultTableModel modeloVerbosPorModosCompacto(String idVerbo){
		DefaultTableModel tm=(DefaultTableModel) modeloVerbosBasico(idVerbo, true);
		utilidadesTM.dejaSelect(tm, new String[]{"PERSONA", "MODO"}, new Object[]{Persona._1ps, Modo.Indicativo});
		
		
		return tm;
	}

	
	/**
	 * Crea un tablemodel con la conjugación regular de un verbo, basándose en un objeto
	 * ManejaOcurrenciasverbales ya poblado. Este método se usa sólo desde el ABM de verbos, 
	 * para tener una visión preliminar del verbo antes de ingresarlo
	 * @param sinDual
	 * @return
	 */
	DefaultTableModel modeloVerbosBasicoRegular(Particularidad partic,  Ocurrencia oc,  boolean sinDual){
	    DefaultTableModel tm=new DefaultTableModel(new String[]{"PARTIC",  "SUBPART", "VOZ", "TIEMPO", "FUERTE", "PERSONA", "MODO", "FORMA"}, 0);
	    Persona ultimaPersona=Persona._3pd;
	    if (sinDual)
	        ultimaPersona=Persona._3pp;

	        for (int iVoz=Voz.getInt(Voz.Activa); iVoz<=Voz.getInt(Voz.Pasiva); iVoz++){
	        	Voz voz=Voz.getEnum(iVoz);
	            for (int iPersona=Persona.getInt(Persona._1ps); iPersona<=Persona.getInt(ultimaPersona); iPersona++){
	            	Persona persona=Persona.getEnum(iPersona);
	                for (int iModo=1; iModo<=4; iModo++){
	                	Modo modo=Modo.getEnum(iModo);
	                	for (Tiempo tiempo: Tiempo.values()){
	                        for (FuerteDebil fuerte: FuerteDebil.values()){
	                            int cantidad=oc.getCantidadDeFormas(voz, modo, tiempo,   fuerte, persona);
	                            for (int formas=0; formas<cantidad; formas++){
	                                String forma  = oc.getFormaIndividual(voz, modo,  tiempo,   fuerte, persona, formas);
	                                if (forma==null) continue;
	                                tm.addRow(new Object[]{partic, formas, voz, tiempo, fuerte, persona, modo,  
	                                new ClaveFlexion("sinIdTodavía", forma, voz, modo, tiempo, fuerte, persona, partic, formas, null, null, null, null)});
	                            }
	                        }
	                    }
	                }
	            }
	        }

	    
	    return tm;
	}

	
	
	
	
	DefaultTableModel modeloVerbosBasico(String idVerbo, boolean sinDual){
	    
	    VerboBean beanVerbo=gerenteVerbos.seleccionaIndividualSinSignificado(idVerbo);
		DefaultTableModel tm=new DefaultTableModel(new String[]{"PARTIC",  "SUBPART", "VOZ", "TIEMPO", "FUERTE", "PERSONA", "MODO", "FORMA"}, 0);
	    List<Particularidad> particsOrden=gerenteVerbos.seleccionaPartics(idVerbo);
	    particsOrden=new ArrayList<Particularidad>(particsOrden);
	    
	    Persona ultimaPersona=Persona._3pd;
	    if (sinDual)
	        ultimaPersona=Persona._3pp;
	    
	    
	    
	    for (Particularidad partic: particsOrden){
	        
	    	Ocurrencia oc=cacheFlexionVerbos.getOcurrencia(beanVerbo.getId(), partic);
	    	if (oc==null){
	    		oc=verbos.conjuga(beanVerbo,  partic);
	    		cacheFlexionVerbos.setOcurrencia(beanVerbo.getId(), partic, oc);
	    	}
	        for (Voz voz: Voz.values()){
	            for (int iPersona=Persona.getInt(Persona._1ps); iPersona<=Persona.getInt(ultimaPersona); iPersona++){
	            	Persona persona=Persona.getEnum(iPersona);
	                for (Modo modo: Modo.values()){
	                	for (Tiempo tiempo: Tiempo.values()){
	                        for (FuerteDebil fuerte: FuerteDebil.values()){
	                            int cantidad=oc.getCantidadDeFormas(voz, modo, tiempo,   fuerte, persona);
	                            for (int formas=0; formas<cantidad; formas++){
	                                String forma  = oc.getFormaIndividual(voz, modo,  tiempo,   fuerte, persona, formas);
	                                if (forma==null) continue;
	                                tm.addRow(new Object[]{partic, formas, voz, tiempo, fuerte, persona, modo,  
	                                new ClaveFlexion(idVerbo, forma, voz, modo, tiempo, fuerte, persona, partic, formas, null, null, null, null)});
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }
	    
	    return tm;
	}
	

	
	
	
	
	
	
	/**
	 * Conjuga un verbo en todas sus particularidades y puebla con ellas un tableModel,
	 * Si el parámetro soloConjugacion es verdadero, sólo procuce la conjugación 
	 * sin poblar nungún datamodel. Dicho parámetro se usa para tests masivos de conjugación, 
	 * en los que devolver un datamodel es innecesario 
	 * @param conta
	 * @param canonica
	 * @param dibujado
	 * @param tipoVerbo
	 * @param soloConjugacion
	 * @return
	 */

	
	DefaultTableModel modeloInfinitivosBasico(VerboBean beanVerbo){
		DefaultTableModel tm=new DefaultTableModel(new String[]{"PARTIC", "SUBPART", "VOZ", "ASPECTO", "FUERTE", "FORMA"}, 0);
		String idVerbo=beanVerbo.getId();
		List<Particularidad> partics=gerenteVerbos.seleccionaPartics(idVerbo);

		for (Particularidad partic: partics){
	    	OcurrenciaInfinitivo oc=cacheFlexionInfinitivos.getOcurrencia(beanVerbo.getId(), partic);
	    	if (oc==null){
	    		oc=new OcurrenciaInfinitivo();
	    		infinitivos.conjuga(oc, beanVerbo,  partic);
	    		cacheFlexionInfinitivos.setOcurrencia(beanVerbo.getId(), partic, oc);
	    	}
			for (int iVoz=Voz.getInt(Voz.Activa); iVoz<=Voz.getInt(Voz.Pasiva); iVoz++){
				Voz voz=Voz.getEnum(iVoz);
				for (Aspecto aspecto: Aspecto.values()){
					for (FuerteDebil fuerte: FuerteDebil.values()){
						int cantidad=oc.getCantidadDeFormas(voz, aspecto, fuerte);
						for (int subPart=0; subPart<cantidad; subPart++){
							String forma  = oc.getFormaIndividual(voz, aspecto,  fuerte, subPart);
							if (forma==null) continue;
							utilidadesTM.agregaFilaComoAray(tm, new Object[]{partic, subPart, 
							        voz, aspecto, fuerte, 
							        new ClaveFlexion(idVerbo, forma, voz, (Modo)null, aspecto, fuerte, null, partic, subPart, null, null, null, null)});
						}
					}
				}
			}
			
		}
		return tm;
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
	 * @return Returns the infinitivos.
	 */
	public Infinitivos getInfinitivos() {
		return infinitivos;
	}







	/**
	 * @param infinitivos The infinitivos to set.
	 */
	public void setInfinitivos(Infinitivos infinitivos) {
		this.infinitivos = infinitivos;
	}







	/**
	 * @return Returns the gerenteVerbos.
	 */
	public GerenteVerbos getGerenteVerbos() {
		return gerenteVerbos;
	}







	/**
	 * @param gerenteVerbos The gerenteVerbos to set.
	 */
	public void setGerenteVerbos(GerenteVerbos gerenteVerbos) {
		this.gerenteVerbos = gerenteVerbos;
	}







	/**
	 * @return Returns the verbos.
	 */
	public Verbos getVerbos() {
		return verbos;
	}







	/**
	 * @param verbos The verbos to set.
	 */
	public void setVerbos(Verbos verbos) {
		this.verbos = verbos;
	}







	/**
	 * @param utilidadesTM The utilidadesTM to set.
	 */
	public void setUtilidadesTM(UtilidadesTM utilidadesTM) {
		this.utilidadesTM = utilidadesTM;
	}









	/**
	 * @param cacheFlexionVerbos The cacheFlexionVerbos to set.
	 */
	public void setCacheFlexionVerbos(CacheFlexionVerbos cacheFlexionVerbos) {
		this.cacheFlexionVerbos = cacheFlexionVerbos;
	}









	/**
	 * @param cacheFlexionInfinitivos The cacheFlexionInfinitivos to set.
	 */
	public void setCacheFlexionInfinitivos(CacheFlexionInfinitivos cacheFlexionInfinitivos) {
		this.cacheFlexionInfinitivos = cacheFlexionInfinitivos;
	}	
	
}
