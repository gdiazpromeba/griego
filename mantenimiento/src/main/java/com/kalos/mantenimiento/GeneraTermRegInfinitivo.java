package com.kalos.mantenimiento;

/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

/**
 * Esta clase recrea los valores de la tabla terminaciones_regulares, conjugando y extrayendo las
 * desinencias de varios verbos regulares, uno de cada tipo
 */

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.CompLetras;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.TipoVerbo;
import com.kalos.enumeraciones.Voz;
import com.kalos.operaciones.AnalisisAcento;
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
 *
 * Esta clase recrea los valores de la tabla TERM_REG_VERBOS, conjugando y extrayendo las
 * desinencias verbales de verbos reglares, uno de cada tipo.
 * Esta clase debe ser ejecutada cada vez que se cambian las  desinencias 
 * verbales
 * @author gdiaz
 *
 */
public class GeneraTermRegInfinitivo implements CompLetras{
	
	
	private static Logger logger = Logger.getLogger(GeneraTermRegInfinitivo.class.getName());

	private static Connection con;
	
	private static  ApplicationContext contexto;


	
	
	public static void main(String[] args) {
		try {
			contexto=creaContexto();
			DataSource kalosDataSource = (DataSource) contexto.getBean("kalosDataSource");
			con=kalosDataSource.getConnection();
			con.setAutoCommit(false);
			ApplicationContext contexto = creaContexto();
//			gerenteTermRegInfinitivo = (GerenteTermRegInfinitivo) contexto.getBean("gerenteTermRegInfinitivo");
			con.createStatement().executeUpdate("DELETE FROM TERM_REG_INFIN");
			genera();
			logger.info("finalizó la escritura de TERM_REG_INFINITIVOS");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
	
	

	static void genera() throws SQLException{
		//KalosApp.selecciones.setParticularidad("reg");
		//DataAccess.borTerminacionesRegulares();no anda, hay que borrarla de antemano
		
		con.setAutoCommit(false);
		Statement stm=null;
		StringBuffer sbIns=new StringBuffer();
		sbIns.append("INSERT INTO  \n");
		sbIns.append("TERM_REG_INFIN(  \n");
		sbIns.append("  VOZ,  \n");
		sbIns.append("  ASPECTO,  \n");
		sbIns.append("  FUERTE,  \n");
		sbIns.append("  TIPO_DESINENCIA,  \n");
		sbIns.append("  TERMINACION,  \n");
		sbIns.append("  CONTRACCION_GENERADORA,  \n");
		sbIns.append("  REGEX_TERM,  \n");
		sbIns.append("  SILABA,  \n");
		sbIns.append("  ACENTO)  \n");
		sbIns.append("VALUES(  \n");
		sbIns.append("  ?,?,?,?,?,?,?,?,?)  \n");
		PreparedStatement ins=con.prepareStatement(sbIns.toString());

		stm=con.createStatement();
		stm.executeUpdate("DELETE FROM TERM_REG_INFIN");
		con.commit();
		
		System.out.println(" insertando tipos de desinencia 1 -- general  -- incluye aoristos segundos de este juego");
		//vocálicos no contractos
		stm=con.createStatement();
		ResultSet rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1");
		String tema=OpPalabras.strBetaACompleto("PAKALU");
		
		
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinenciaBeta=rs.getString("DESINENCIA");	
			String desinenciaCompleta=OpPalabras.strBetaACompleto(desinenciaBeta);
			String sumada=OpPalabras.acentua(tema + desinenciaCompleta);

			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			int fuerte=rs.getInt("FUERTE");
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, fuerte); 
			ins.setInt(4, TipoVerbo.NoHojas.VOCALICO_NO_CONTRACTO);
			ins.setString(5, desinenciaBeta);
			ins.setInt(6, Contraccion.getInt(Contraccion.sumaAcentuada));
			ins.setString(7, null);
			ins.setInt(8, silaba);
			ins.setInt(9, Acento.getInt(acento));
			ins.executeUpdate();
		}
		stm.close();
		
		
		
		
		
		System.out.println(" insertando infectivo de contractos en e/w  (incluye optativos especiales) ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO in (1, 3) AND ASPECTO IN (1)");
		tema=OpPalabras.strBetaACompleto("PAKALE");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String res[]=OpPalabras.contraeVocal(tema, desinencia, Contraccion.vocalica, silaba, acento);
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.VOCALICO_CONTRACTO_EPSILON);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.vocalica));
			ins.setString(7, null); 
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();


		
		System.out.println(" insertando infectivo de contractos en e/w  con contracción jónica  ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1  AND ASPECTO IN (1)");
		tema=OpPalabras.strBetaACompleto("PAKALE");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String res[]=OpPalabras.contraeVocal(tema, desinencia, Contraccion.jonica, silaba, acento);
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.VOCALICO_CONTRACTO_EPSILON);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.jonica));
			ins.setString(7, null);
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();

		
		
		
		System.out.println(" insertando no-infectivos de contractos en e/w ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO >2 AND FUERTE=0");
		tema=OpPalabras.strBetaACompleto("PAKALH");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String res[]=OpPalabras.contraeVocal(tema, desinencia, Contraccion.vocalica, silaba, acento);
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.VOCALICO_CONTRACTO_EPSILON);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.sumaAcentuada));
			ins.setString(7, null);
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();


		
		
		System.out.println(" insertando infectivo de contractos en a/w (incluye optativos especiales) ");
		//vocálicos no contractos
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO in (1, 3) AND ASPECTO IN (1)");
		tema=OpPalabras.strBetaACompleto("PAKALA");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String res[]=OpPalabras.contraeVocal(tema, desinencia, Contraccion.vocalica, silaba, acento);
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.VOCALICO_CONTRACTO_ALFA);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.vocalica));
			ins.setString(7, null);
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();
		
		
		System.out.println(" insertando no-infectivos de contractos en a/w ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO >2 AND FUERTE=0");
		tema=OpPalabras.strBetaACompleto("PAKALH");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String res[]=OpPalabras.contraeVocal(tema, desinencia, Contraccion.vocalica, silaba, acento);
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.VOCALICO_CONTRACTO_ALFA);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.sumaAcentuada));
			ins.setString(7, null);
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();
		
		
		
		
	
		
		System.out.println(" insertando infectivo de contractos en o/w (incluye optativos especiales)");
		//vocálicos no contractos
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO in (1, 3) AND ASPECTO IN (1)");
		tema=OpPalabras.strBetaACompleto("PAKALO");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String res[]=OpPalabras.contraeVocal(tema, desinencia, Contraccion.vocalica, silaba, acento);
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.VOCALICO_CONTRACTO_OMICRON);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.vocalica));
			ins.setString(7, null);
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();

		System.out.println(" insertando no-infectivos de contractos en o/w ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO >2 AND FUERTE=0");
		tema=OpPalabras.strBetaACompleto("PAKALW");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String res[]=OpPalabras.contraeVocal(tema, desinencia, Contraccion.vocalica, silaba, acento);
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.VOCALICO_CONTRACTO_OMICRON);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.sumaAcentuada));
			ins.setString(7, null);
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();
		
		

		System.out.println(" insertando no-infectivos guturales ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO >2 AND FUERTE=0");
		tema=OpPalabras.strBetaACompleto("PAKALG");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			//la 3pp del plural perf y plperf, (ntai, nto) que son perifrésticas
			if (desinencia.startsWith(OpPalabras.strBetaACompleto("NT")))
				continue;
			String res[]=OpPalabras.contraeConsonante(tema, desinencia, silaba, acento);
			
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));

			
			
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.CONSONANTICO_GUTURALES);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.consonantica));
			ins.setString(7, null);
			ins.setInt(8, silaba); 
			ins.setInt(9, Acento.getInt(acento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();

		
		
		
		
		
		System.out.println(" insertando no-infectivos labiales ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO >2 AND FUERTE=0"); 
		tema=OpPalabras.strBetaACompleto("PAKALP");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			//la 3pp del plural perf y plperf, (ntai, nto) que son perifrésticas
			if (desinencia.startsWith(OpPalabras.strBetaACompleto("NT")))
				continue;
			String res[]=OpPalabras.contraeConsonante(tema, desinencia, silaba, acento);
			
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.CONSONANTICO_LABIALES);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.consonantica));
			ins.setString(7, null);
			ins.setInt(8, silaba); 
			ins.setInt(9, Acento.getInt(acento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();
		
		
		System.out.println(" insertando no-infectivos dentales ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO >2 AND FUERTE=0"); 
		tema=OpPalabras.strBetaACompleto("PAKALD");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			//la 3pp del plural perf y plperf, (ntai, nto) que son perifrésticas
			if (desinencia.startsWith(OpPalabras.strBetaACompleto("NT")))
				continue;
			String res[]=OpPalabras.contraeConsonante(tema, desinencia, silaba, acento);
			
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.CONSONANTICO_DENTALES);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.consonantica));
			ins.setString(7, null);
			ins.setInt(8, silaba); 
			ins.setInt(9, Acento.getInt(acento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();

		
		System.out.println(" insertando infectivos y aoristos segundos de juego 2 (en -mi) ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=2 ");
		tema=OpPalabras.strBetaACompleto("PAKA");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			int fuerte=rs.getInt("FUERTE");
			
			String forma=tema.concat(desinencia);
			forma=OpPalabras.acentua(forma,  silaba, acento);
			
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(forma);
			String terminacion=forma.substring(4);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, fuerte);  
			ins.setInt(4, TipoVerbo.NoHojas.MI_PROPIAMENTE);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.sumaAcentuada));
			ins.setString(7, null);
			ins.setInt(8, -2); 
			ins.setInt(9, Acento.getInt(Acento.Circunflejo));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();

		
		System.out.println(" insertando infectivos y aoristos segundos de juego 6 (en -numi) ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=6 ");
		tema=OpPalabras.strBetaACompleto("PAKANU_");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			int fuerte=rs.getInt("FUERTE");
			
			String forma=tema.concat(desinencia);
			forma=OpPalabras.acentua(forma,  silaba, acento);
			
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(forma);
			String terminacion=forma.substring(6);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, fuerte);  
			ins.setInt(4, TipoVerbo.NoHojas.NUMI);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.sumaAcentuada));
			ins.setString(7, null);
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();

     
		
		System.out.println(" infectivo de líquidos ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO IN (1) ");
		tema=OpPalabras.strBetaACompleto("PAKALE");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String sumada=OpPalabras.contraeVocal(tema, desinencia, Contraccion.vocalica, silaba, acento)[2];
			String terminacion=sumada.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(sumada);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.CONSONANTICO_LIQUIDO);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.vocalica));
			ins.setString(7, "(.*)[LRNM]" + UtilidadesGeneracion.escapaBetasRegexp(OpPalabras.strCompletoABeta(sumada.substring(5))));
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();
		
		
		
		System.out.println(" futuro ático de líquidos ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO IN (1) ");
		tema=OpPalabras.strBetaACompleto("PAKALE");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String res[]=OpPalabras.contraeVocal(tema, desinencia, Contraccion.vocalica, silaba, acento);
			String contracta=res[2];
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, Aspecto.getInt(Aspecto.Futuro));
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.CONSONANTICO_LIQUIDO);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.vocalica));
			ins.setString(7, "(.*)[(QI)LRN]" + OpPalabras.strCompletoABeta(OpPalabras.desacentuar(contracta.substring(5))));
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();
		
		

		
		
		System.out.println(" aoristo de líquidos  (activa y media) ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO=4 and VOZ in (1, 2) AND FUERTE=0");
		tema=OpPalabras.strBetaACompleto("KEKAR");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String contracta=OpPalabras.contraeGenerica(tema, desinencia, Contraccion.comePrimera, silaba, acento);
			String terminacion=contracta.substring(5);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, Aspecto.getInt(Aspecto.Confectivo));
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.CONSONANTICO_LIQUIDO);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.comePrimera));
			ins.setString(7, "(.*)[LRN]" + UtilidadesGeneracion.escapaBetasRegexp( OpPalabras.strCompletoABeta(contracta.substring(5))));
			ins.setInt(8, -2); 
			ins.setInt(9, 0);
			ins.executeUpdate();
		}
		stm.close();
		con.commit();
		
		System.out.println(" aoristo y futuro pasivos fuertes (sin theta) ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1  AND FUERTE=1 AND ASPECTO IN (3, 4) and VOZ=3");
		tema=OpPalabras.strBetaACompleto("PAKAR");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String contracta=OpPalabras.contraeGenerica(tema, desinencia, Contraccion.sumaAcentuada, silaba, acento);
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 1);  //fuerte
			ins.setInt(4, -1);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.sumaAcentuada));
			ins.setString(7, "(.*)[^Q]" + UtilidadesGeneracion.escapaBetasRegexp(OpPalabras.strCompletoABeta(contracta.substring(5))));
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();
		

		
		
		System.out.println(" aoristo y futuro pasivos débiles con theta de tema irregular ");
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1  AND FUERTE=0 AND ASPECTO IN (3, 4) and VOZ=3");
		tema=OpPalabras.strBetaACompleto("PAKAR");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String contracta=OpPalabras.contraeGenerica(tema, desinencia, Contraccion.sumaAcentuada, silaba, acento);
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, -1);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.sumaAcentuada));
			ins.setString(7, null);
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();
		

		
		
		System.out.println(" perfectivo de líquidos  (activa y media) ");
        
        	ins.setInt(1, Voz.getInt(Voz.Activa));
        	ins.setInt(2, Aspecto.getInt(Aspecto.Perfectivo));
        	ins.setInt(3, 0); // fuerte
        	ins.setInt(4, TipoVerbo.NoHojas.CONSONANTICO_LIQUIDO);
        	ins.setString(5, "ENAI");
        	ins.setInt(6, Contraccion.getInt(Contraccion.comePrimera));
        	ins.setString(7, "(.*)[LRN]ENAI");
        	ins.setInt(8, -2);
        	ins.setInt(9, 0);
        	ins.executeUpdate();
        
        	ins.setInt(1, Voz.getInt(Voz.Media));
        	ins.setInt(2, Aspecto.getInt(Aspecto.Perfectivo));
        	ins.setInt(3, 0); // fuerte
        	ins.setInt(4, TipoVerbo.NoHojas.CONSONANTICO_LIQUIDO);
        	ins.setString(5, "QAI");
        	ins.setInt(6, Contraccion.getInt(Contraccion.comePrimera));
        	ins.setString(7, "(.*)[LRN]QAI");
        	ins.setInt(8, -2);
        	ins.setInt(9, 0);
        	ins.executeUpdate();
        
        	stm.close();
        	con.commit();
		
		
		
		
		stm=con.createStatement();
		rs=stm.executeQuery("SELECT * FROM DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO IN (5) AND FUERTE=0");
		tema=OpPalabras.strBetaACompleto("PAKAR");
		while (rs.next()){
			int voz=rs.getInt("VOZ");
			int aspecto=rs.getInt("ASPECTO");
			String desinencia=OpPalabras.strBetaACompleto(rs.getString("DESINENCIA"));
			int silaba=rs.getInt("SILABA");
			Acento acento=Acento.getEnum(rs.getInt("ACENTO"));
			
			String contracta=OpPalabras.contraeGenerica(tema, desinencia, Contraccion.sumaAcentuada, silaba, acento);
			String terminacion=contracta.substring(5);
			AnalisisAcento aaContracta=AnalisisAcento.getAnalisisAcento(contracta);
			terminacion=OpPalabras.strCompletoABeta(OpPalabras.facilita(terminacion));
			
			ins.setInt(1, voz);
			ins.setInt(2, aspecto);
			ins.setInt(3, 0);  //fuerte
			ins.setInt(4, TipoVerbo.NoHojas.CONSONANTICO_LIQUIDO);
			ins.setString(5, terminacion);
			ins.setInt(6, Contraccion.getInt(Contraccion.sumaAcentuada));
			ins.setString(7, "(.*)[LRN]QAI" );
			ins.setInt(8, aaContracta.actuales.silaba); 
			ins.setInt(9, Acento.getInt(aaContracta.actuales.tipoAcento));
			ins.executeUpdate();
		}
		stm.close();
		con.commit();

		
        	System.out.println(" perfectivo segundo sin kappa, sin contracción  (activa y media) ");
        	stm = con.createStatement();
        	rs = stm.executeQuery("SELECT *  FROM  DESIN_INFINITIVOS WHERE JUEGO=1 AND ASPECTO IN (5) and FUERTE=1 ");
        	tema = OpPalabras.strBetaACompleto("PAKAR");
        
        	ins.setInt(1, Voz.getInt(Voz.Media));
        	ins.setInt(2, Aspecto.getInt(Aspecto.Perfectivo));
        	ins.setInt(3, 1); // fuerte
        	ins.setInt(4, -1);
        	ins.setString(5, "QAI");
        	ins.setInt(6, Contraccion.getInt(Contraccion.comePrimera));
        	ins.setString(7, "(.*)[^K]QAI");
        	ins.setInt(8, -2);
        	ins.setInt(9, 0);
        	ins.executeUpdate();
        
        	stm.close();
        	con.commit();
		




		
		
		System.out.println("Terminó de insertar desinencias ");
		
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