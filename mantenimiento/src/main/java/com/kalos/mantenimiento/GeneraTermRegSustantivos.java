/*
 * Created on Sep 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.kalos.mantenimiento;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.kalos.beans.DesinSust;
import com.kalos.beans.TemaTermRegNominal;
import com.kalos.beans.TermRegSustantivo;
import com.kalos.beans.TipoJerarquico;
import com.kalos.datos.adaptadores.AdaptadorGerenteDesinSust;
import com.kalos.datos.gerentes.GerenteDesinSust;
import com.kalos.datos.gerentes.GerenteTemasTermRegNominal;
import com.kalos.datos.gerentes.GerenteTermRegSustantivo;
import com.kalos.datos.gerentes.GerenteTiposSustantivo;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.AcentoConcuerda;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.PosicionConcuerda;
import com.kalos.operaciones.OpPalabras;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * Esta clase puebla la tabla de "terminaciones regulares de sustantivos", la cual se usa para 
 * analizar morfológicamente cualquiier palabra nominal (no sólo sustantivos).
 * Debería ser ejecutada cada vez que se modifica el árbol de desinencias nominales.
 * 
*/
public class GeneraTermRegSustantivos {

	private static GerenteTiposSustantivo gerenteTiposSustantivo;

	private static GerenteDesinSust gerenteDesinSust;

	private static GerenteTemasTermRegNominal gerenteTemasTermRegNominal;

	private static GerenteTermRegSustantivo gerenteTermRegSustantivo;

	private static Logger logger = Logger.getLogger(GeneraTermRegSustantivos.class.getName());

	private static Connection con;



	public static void main(String[] args) {
		try {
			ApplicationContext contexto = creaContexto();
			DataSource kalosDataSource = (DataSource) contexto.getBean("kalosDataSource");
			con=kalosDataSource.getConnection();
			gerenteTiposSustantivo = (GerenteTiposSustantivo) contexto.getBean("gerenteTiposSustantivo");
			gerenteDesinSust = (GerenteDesinSust) contexto.getBean("gerenteDesinSust");
			gerenteTemasTermRegNominal = (GerenteTemasTermRegNominal) contexto.getBean("gerenteTemasTermRegNominal");
			gerenteTermRegSustantivo = (GerenteTermRegSustantivo) contexto.getBean("gerenteTermRegSustantivo");

			quitaIndices(con);
			Statement stm=con.createStatement();
			stm.executeUpdate("DELETE FROM TERM_REG_SUST");
			stm.close();
			generaSinContraccion();
			generaConContraccion();
			restauraIndices(con);
			System.out.println("finalizó la escritura de TERM_REG_SUST");
		
		} catch (Exception e) {
			logger.error("error - abortando ....", e);
		}

	}

	/**
	 * genera las desinencias "extendidas" de las desinencias que tienen
	 * contracción. Los tipos indicados en los "cases" son los casos que
	 * realmente tienen la desinencia, no sus hojas.
	 * 
	 */
	public static void generaConContraccion() {
		logger.info("generando con contracción");

		AdaptadorGerenteDesinSust agds = new AdaptadorGerenteDesinSust(gerenteDesinSust);
		List<String> ids = gerenteDesinSust.seleccionaContracciones();
		List<DesinSust> desinencias = new ArrayList<DesinSust>(agds.getBeans(ids));

		Map<String, Set<String>> temasContractos = obtieneTemasContractos();

		for (DesinSust ds : desinencias) {
			String desinencia = ds.getDesinencia();
			desinencia = OpPalabras.strBetaACompleto(desinencia);

			Set<String> temas = temasContractos.get(ds.getTipoSustantivoId());
			int i = gerenteTermRegSustantivo.seleccionaMaxSubindice(ds.getTipoSustantivo(), ds.getCaso(), ds
					.getNumero());

			for (String tema : temas) {
				tema = OpPalabras.strBetaACompleto(tema);
				String res = OpPalabras.contraeGenerica(tema, desinencia, ds.getContraccion(), 0, Acento.Ninguno);
				res = res.substring(3);
				res = OpPalabras.desacentuar(res);
				res = OpPalabras.strCompletoABeta(res);
				i++;
				TermRegSustantivo trs = new TermRegSustantivo();
				trs.setTipoSustantivo(ds.getTipoSustantivo());
				trs.setCaso(ds.getCaso());
				trs.setNumero(ds.getNumero());
				trs.setOrigenTema(ds.getOrigenTema());
				trs.setSubindice(i);
				trs.setTerminacion(res);
				trs.setExContraccion(true);
				String hc = hojasConcatenadas(ds.getTipoSustantivo());
				trs.setTiposHoja(hc);
				trs.setAcento(ds.getAcento());
				trs.setSilaba(ds.getSilaba());
				trs.setAcentoConcuerda(AcentoConcuerda.getInt(ds.getAcentoConcuerda()));
				trs.setPosicionConcuerda(PosicionConcuerda.getInt(ds.getPosicionConcuerda()));
				gerenteTermRegSustantivo.inserta(trs);
			}
		}
	}
	
	/**
	 * quita índices en los campos, lo cual 
	 * acelera los UPDATES considerablemente
	 * @param con
	 * @throws Exception
	 */
	public static void quitaIndices(Connection con)throws Exception{
		logger.info("quitando índices  ...");
		
		StringBuffer sb=new StringBuffer();
		sb.append("ALTER create  table TERM_REG_SUST(   \n");  
	    sb.append(" TIPO_SUSTANTIVO INTEGER not null INDEX_NONE,   \n");
	    sb.append(" CASO INTEGER not null INDEX_NONE,     \n");
	    sb.append(" NUMERO INTEGER not null  INDEX_NONE,          \n");
	    sb.append(" SUBINDICE INTEGER not null  INDEX_NONE,          \n");
	    sb.append(" TERMINACION VARCHAR(15) INDEX_NONE,          \n");
	    sb.append(" TIPOS_SUST_HOJA VARCHAR(50) not null,          \n");
	    sb.append(" EX_CONTRACCION INTEGER INDEX_NONE,          \n");
	    sb.append(" ACENTO_CONCUERDA INTEGER   INDEX_NONE,          \n");
	    sb.append(" POSICION_CONCUERDA INTEGER   INDEX_NONE,          \n");
	    sb.append(" ACENTO INTEGER   INDEX_NONE,          \n");
	    sb.append(" SILABA INTEGER   INDEX_NONE,          \n");
	    sb.append(" REGEX_DESINENCIA VARCHAR(40) INDEX_NONE,        \n");
	    sb.append(" ORIGEN_TEMA CHAR(2) not null INDEX_NONE        \n");
	    sb.append(" ); \n");
	    Statement stmUpd=con.createStatement();
	    stmUpd.executeUpdate(sb.toString());
	    stmUpd.close();
	}
	
	public static void restauraIndices(Connection con)throws Exception{
		logger.info("restaurando índices  ...");
		
		StringBuffer sb=new StringBuffer();
		sb.append("ALTER create  table TERM_REG_SUST(   \n");  
	    sb.append(" TIPO_SUSTANTIVO INTEGER not null,   \n");
	    sb.append(" CASO INTEGER not null ,     \n");
	    sb.append(" NUMERO INTEGER not null  ,          \n");
	    sb.append(" SUBINDICE INTEGER not null  INDEX_NONE,          \n");
	    sb.append(" TERMINACION VARCHAR(15) ,          \n");
	    sb.append(" TIPOS_SUST_HOJA VARCHAR(50) not null INDEX_NONE,          \n");
	    sb.append(" EX_CONTRACCION INTEGER INDEX_NONE,          \n");
	    sb.append(" ACENTO_CONCUERDA INTEGER  INDEX_NONE,          \n");
	    sb.append(" POSICION_CONCUERDA INTEGER  INDEX_NONE,          \n");
	    sb.append(" ACENTO INTEGER   INDEX_NONE,          \n");
	    sb.append(" SILABA INTEGER   INDEX_NONE,          \n");
	    sb.append(" REGEX_DESINENCIA VARCHAR(40) INDEX_NONE,        \n");
	    sb.append(" ORIGEN_TEMA CHAR(2) not null INDEX_NONE,        \n");
	    sb.append(" constraint PK_TERM_REG_SUST primary key (TIPO_SUSTANTIVO, NUMERO, SUBINDICE) \n");
	    sb.append(" ); \n");
	    Statement stmUpd=con.createStatement();
	    stmUpd.executeUpdate(sb.toString());
	    stmUpd.close();
	}
	
	

	/**
	 * obtiene un mapa "tipo-temas contractos ficticios" de la base de datos
	 * 
	 * @return
	 */
	private static Map<String, Set<String>> obtieneTemasContractos() {
		List<TemaTermRegNominal> temasTrn = gerenteTemasTermRegNominal.seleccionaTodos();
		Map<String, Set<String>> resultado = new HashMap<String, Set<String>>();
		for (TemaTermRegNominal ttrn : temasTrn) {
			String tipo = ttrn.getIdTipoNominal();
			String tema = ttrn.getTema();
			Set<String> temas = resultado.get(tipo);
			if (temas == null) {
				temas = new HashSet<String>();
				resultado.put(tipo, temas);
			}
			temas.add(tema);
		}
		return resultado;
	}


	private static String hojasConcatenadas(int tipo) {
		Map<Integer, String> mapaTiposId = gerenteTiposSustantivo.getMapaTiposID();
		String tipoId = mapaTiposId.get(tipo);
		List<TipoJerarquico> tijHoja = gerenteTiposSustantivo.getTiposHoja(tipoId);
		StringBuffer sb = new StringBuffer("-");
		for (TipoJerarquico tij : tijHoja) {
			sb.append(tij.getValorEntero());
			sb.append("-");
		}
		return sb.toString();
	}

	/**
	 * Simplemente copia las desinencias que no tienen contracción tal cual a la
	 * tabla de TERM_REG_SUST
	 * 
	 */
	public static void generaSinContraccion() {
		logger.info("generando sin contracción");
		try {
			con.setAutoCommit(false);
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT * FROM DESIN_SUST WHERE CONTRACCION=0 ORDER BY TIPO_SUSTANTIVO, NUMERO, CASO");
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sb.toString());
			StringBuffer sbIns = new StringBuffer("INSERT INTO TERM_REG_SUST  ( \n");
			sbIns.append("  TIPO_SUSTANTIVO, \n");
			sbIns.append("  CASO, \n");
			sbIns.append("  NUMERO, \n");
			sbIns.append("  ORIGEN_TEMA, \n");
			sbIns.append("  SUBINDICE, \n");
			sbIns.append("  TERMINACION,  \n");
			sbIns.append("  EX_CONTRACCION,  \n");
			sbIns.append("  TIPOS_SUST_HOJA,  \n");
			sbIns.append("  ACENTO,  \n");
			sbIns.append("  SILABA,  \n");
			sbIns.append("  ACENTO_CONCUERDA, \n");
			sbIns.append("  POSICION_CONCUERDA,  \n");
			sbIns.append("  REGEX_DESINENCIA)  \n");
			sbIns.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)  \n");
			PreparedStatement psIns = con.prepareStatement(sbIns.toString());
			int contador = 0;
			while (rs.next()) {
				int tipo = rs.getInt("TIPO_SUSTANTIVO");
				String desinencia = rs.getString("DESINENCIA");
				String origenTema = rs.getString("ORIGEN_TEMA");
				int acento = rs.getInt("ACENTO");
				int silaba = rs.getInt("SILABA");
				int acentoConcuerda = rs.getInt("ACENTO_CONCUERDA");
				int posicionConcuerda = rs.getInt("POSICION_CONCUERDA");
				String regexDesinencia = rs.getString("REGEX_DESINENCIA");

				desinencia = OpPalabras.strBetaACompleto(desinencia);
				desinencia = OpPalabras.desacentuar(desinencia);
				desinencia = OpPalabras.strCompletoABeta(desinencia);
				int caso = rs.getInt("CASO");
				int numero = rs.getInt("NUMERO");
				int subindice = rs.getInt("SUBINDICE");


				psIns.setInt(1, tipo);
				psIns.setInt(2, caso);
				psIns.setInt(3, numero);
				psIns.setString(4, origenTema);
				psIns.setInt(5, subindice);
				psIns.setString(6, desinencia);
				psIns.setInt(7, 0);
				// fabrico una cadena con los tipos-hoja concatenados
				String hc = hojasConcatenadas(tipo);
				psIns.setString(8, hc);
				psIns.setInt(9, acento);
				psIns.setInt(10, silaba);
				psIns.setInt(11, acentoConcuerda);
				psIns.setInt(12, posicionConcuerda);
				psIns.setString(13, regexDesinencia);

				psIns.executeUpdate();
				if (contador % 100 == 0)
					con.commit();

			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


    public static ApplicationContext creaContexto() {
        GenericApplicationContext contexto;
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource res = new ClassPathResource("daos.xml");
        reader.loadBeanDefinitions(res);
        res = new ClassPathResource("gerentes-datos.xml");
        reader.loadBeanDefinitions(res);
        res = new ClassPathResource("flexion.xml");
        reader.loadBeanDefinitions(res);

        contexto = new GenericApplicationContext(factory);
        contexto.refresh();
        return contexto;
    }   

}
