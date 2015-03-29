/*
 * Created on Sep 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.kalos.mantenimiento;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.kalos.beans.AdjetivoComoNominalBean;
import com.kalos.beans.DesinSust;
import com.kalos.beans.NomGenTipo;
import com.kalos.beans.SustantivoBean;
import com.kalos.beans.TemaTermRegNominal;
import com.kalos.beans.TipoJerarquico;
import com.kalos.beans.TipoSustantivo;
import com.kalos.datos.adaptadores.AdaptadorGerenteDesinSust;
import com.kalos.datos.adaptadores.AdaptadorGerenteSustantivos;
import com.kalos.datos.gerentes.GerenteAdjetivosComoNominales;
import com.kalos.datos.gerentes.GerenteDesinSust;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteTemasTermRegNominal;
import com.kalos.datos.gerentes.GerenteTiposSustantivo;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.OrigenTema;
import com.kalos.flexion.declinacion.Declina;
import com.kalos.operaciones.OpPalabras;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * Generación de los temas ficticios contractos usados para generar las
 * terminaciones nominales de aquellas desinencias que son contractas. Los temas
 * a contraer son almacenados en una tabla "TEMAS_TERM_REG_NOMINAL". La
 * generación es costosa, porque para saber los temas se extrae el tema de cada
 * sustantivo. La lista de temas ficticios a contraer es luego utilizada por
 * GeneraTermRegSust. Estrictamente hablando, esta generación debería ejecutarse
 * antes de cada vez que se ejecute GeneraTermRegSust, pero en la práctica es
 * raro que se generen contracciones con nuevas letras muy a menudo, así que con
 * ejecutar esta clase muy de vez en cuando es suficiente.
 * 
 * 
 * @author GDiaz
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class GeneraTemasTermRegNominal {

	private static GerenteTiposSustantivo gerenteTiposSustantivo;

	private static GerenteSustantivos gerenteSustantivos;
	
	private static GerenteAdjetivosComoNominales gerenteAdjetivosComoNominales;

	private static GerenteDesinSust gerenteDesinSust;

	private static GerenteTemasTermRegNominal gerenteTemasTermRegNominal;

//	private static Logger logger = Logger.getLogger(GeneraTemasTermRegNominal.class.getName());

	private static Connection con;



	public static void main(String[] args) throws Exception {
		ApplicationContext contexto = creaContexto();
		DataSource kalosDataSource = (DataSource) contexto.getBean("kalosDataSource");
		con=kalosDataSource.getConnection();
		con.setAutoCommit(false);
		gerenteTiposSustantivo = (GerenteTiposSustantivo) contexto.getBean("gerenteTiposSustantivo");
		gerenteSustantivos = (GerenteSustantivos) contexto.getBean("gerenteSustantivos");
		gerenteDesinSust = (GerenteDesinSust) contexto.getBean("gerenteDesinSust");
		gerenteTemasTermRegNominal = (GerenteTemasTermRegNominal) contexto.getBean("gerenteTemasTermRegNominal");
		gerenteAdjetivosComoNominales = (GerenteAdjetivosComoNominales) contexto.getBean("gerenteAdjetivosComoNominales");
		

		gerenteTemasTermRegNominal.borra();

		generaConContraccion2();
		con.commit();
		System.out.println("terminado");
	}

	/**
	 * genera las desinencias "extendidas" de las desinencias que tienen
	 * contracción. 
	 * 
	 */
	public static void generaConContraccion2() {
		AdaptadorGerenteDesinSust agds=new AdaptadorGerenteDesinSust(gerenteDesinSust);
		List<String> ids = gerenteDesinSust.seleccionaContracciones();
		List<DesinSust> desinVocalicas=new ArrayList<DesinSust>(agds.getBeans(ids));
		Map<String, Set<String>> hojasPorTipo = extraeTiposHoja(desinVocalicas);
		Map<String, Set<String>> temasPorHoja = new HashMap<String, Set<String>>();
		for (DesinSust ds : desinVocalicas) {
			temasAContraer(ds, hojasPorTipo, temasPorHoja);
		}
		for (Map.Entry<String, Set<String>> entrada : temasPorHoja.entrySet()) {
			String tipoHoja = entrada.getKey();
			Set<String> temas = entrada.getValue();
			for (String tema : temas) {
				TemaTermRegNominal ttrn = new TemaTermRegNominal();
				ttrn.setIdTipoNominal(tipoHoja);
				ttrn.setTema(tema);
				gerenteTemasTermRegNominal.inserta(ttrn);
			}

		}
	}
	/**
	 * devuelve un mapa de ids de tipos de sustantivo, organizado de la forma 
	 * "id del padre-lista de ids de los hijos" 
	 * desinencias

	 * @param desinencias la lista de registros de desinencias
	 * @return
	 */
	private static Map<String, Set<String>> extraeTiposHoja(List<DesinSust> desinencias) {
		Map<String, Set<String>> resultado = new HashMap<String, Set<String>>();
		
		//primero genero una lista no repetida de todos los id de tipos de sustantivo 
		//que la lista de desinencias contiene
		Set<String> idsTipoSust=new HashSet<String>();
		for (DesinSust ds : desinencias) {
			idsTipoSust.add(ds.getTipoSustantivoId());
		}
		
		
		for (String idTipoSust : idsTipoSust) {
			Set<String> hojasDeUno = resultado.get(idTipoSust);
			if (hojasDeUno == null) {
				hojasDeUno = new HashSet<String>();
				resultado.put(idTipoSust, hojasDeUno);
			}
			List<TipoJerarquico> tijHoja = gerenteTiposSustantivo
					.getTiposHoja(idTipoSust);
			for (TipoJerarquico tij : tijHoja) {
				hojasDeUno.add(tij.getId());
			}
		}

		return resultado;
	}

	/**
	 * temas a contraer
	 * 
	 * @param tipoSustantivo
	 * @return
	 */
	public static void temasAContraer(DesinSust registroDesinencia,
			Map<String, Set<String>> hojasPorTipo,
			Map<String, Set<String>> temasPorTipo) {

		// qué tema es el que se contrae
		OrigenTema origenTema=registroDesinencia.getOrigenTema(); 

		// finalmente, agarro los 5 primeros de cada tipo "hoja"
		// (selecciono por cada tipo idividualmente para lograr mayor
		// representatividad de los tipos
		// de verbo
		String tipo = registroDesinencia.getTipoSustantivoId();

		Set<String> temas = temasPorTipo.get(tipo);
		if (temas == null) {
			temas = new HashSet<String>();
			temasPorTipo.put(tipo, temas);
			
			
			// hojas
			Set<String> hojas = hojasPorTipo.get(tipo);
			for (String hoja : hojas) {
				AdaptadorGerenteSustantivos ags=new AdaptadorGerenteSustantivos(gerenteSustantivos);
				TipoSustantivo tis=gerenteTiposSustantivo.seleccionIndividual(hoja);
				//temas provenientes de la lista de sustantivos
				List<String> ids = gerenteSustantivos.seleccionaPorTipos(new Integer[] { tis.getValorEntero() });
				List<SustantivoBean> sustantivos = new ArrayList<SustantivoBean>(ags.getBeans(ids));
				for (SustantivoBean es : sustantivos) {
					pueblaTemas(temas, es, origenTema);
				}
				//temas provenientes de la lista de adjetivos-como-nominales
				List<AdjetivoComoNominalBean> acns=gerenteAdjetivosComoNominales.seleccionaPorTipos(new Integer[]{tis.getValorEntero()});
				for (AdjetivoComoNominalBean bean : acns) {
					pueblaTemas(temas, bean, origenTema);
				}
			}
		}
	}
	
	/**
	 * agrega (posiblemente) una entrada a temas, según el tema que se extraiga de ese registro nominal
	 * @param temas
	 * @param ngt
	 * @param caso
	 * @param numero
	 */
	private static void pueblaTemas(Set<String> temas, NomGenTipo ngt, OrigenTema origenTema){
		String nominativo = OpPalabras.strBetaACompleto(ngt.getNominativo());
		String genitivo = OpPalabras.strBetaACompleto(ngt.getGenitivo());
		String tema = Declina.extraeTema(origenTema, nominativo, genitivo, ngt.getTipoNominal());
		String temaBeta = OpPalabras.strCompletoABeta(tema);
		if (temaBeta.endsWith("U") || temaBeta.endsWith("U_")) {
			temas.add("TEKU");
		} else if (temaBeta.endsWith("A")
				|| temaBeta.endsWith("A_")) {
			temas.add("TEKA");
		} else if (temaBeta.endsWith("E")) {
			temas.add("TEKE");
		} else if (temaBeta.endsWith("O")) {
			temas.add("TEKO");
		} else if (temaBeta.endsWith("P") || temaBeta.endsWith("B")
				|| temaBeta.endsWith("F")) {
			temas.add("TREP");
		} else if (temaBeta.endsWith("K") || temaBeta.endsWith("G")
				|| temaBeta.endsWith("X")) {
			temas.add("TREK");
		}
	}



	
    public static ApplicationContext creaContexto() {
        ApplicationContext contexto;
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource res = new ClassPathResource("daos.xml");
        reader.loadBeanDefinitions(res);
        res = new ClassPathResource("gerentes-datos.xml");
        reader.loadBeanDefinitions(res);
        res = new ClassPathResource("flexion.xml");
        reader.loadBeanDefinitions(res);

        contexto = new GenericApplicationContext(factory);
        return contexto;
    }	
	

}
