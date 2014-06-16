package kalos.utilidades;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import kalos.beans.EntradaDiccionario;
import kalos.datos.gerentes.GerenteDiccionario;
import kalos.datos.util.DBUtil;
import kalos.enumeraciones.Beta;
import kalos.operaciones.OpPalabras;
import kalos.operaciones.comparadores.ComparadorBeansGriegos;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class ImportaParticulas {

	private static Logger logger=Logger.getLogger(ImportaParticulas.class.getName());
	
	
	private static Connection con;
	private static ApplicationContext contexto;
	private static GerenteDiccionario gerenteDiccionario;
	
    
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		DOMConfigurator.configure("log4j.xml");
		contexto=creaContexto();
		DataSource kalosDataSource = (DataSource) contexto.getBean("kalosDataSource");
		con=kalosDataSource.getConnection();
		gerenteDiccionario = (GerenteDiccionario) contexto.getBean("gerenteDiccionario");

		
  		
  		agregaParticulas(con);


	}
	
	

	/**
	 * agrega los datos de adjetivos a la tabla de diccionarios
	 * @param con
	 * @throws Exception
	 */
	public static void agregaParticulas(Connection con) throws Exception{
		logger.info("agregando partículas ...");
		StringBuffer sb=new StringBuffer();
		sb.append("INSERT INTO PARTICULAS( \n");
		sb.append("  PARTICULA_ID, \n");
		sb.append("  TIPO_PALABRA, \n");
		sb.append("  PARTICULARIDAD, \n");
		sb.append("  CASO, \n");
		sb.append("  PERSONA, \n");
		sb.append("  SUBINDICE, \n");
		sb.append("  GENERO, \n");
		sb.append("  NUMERO, \n");
		sb.append("  FORMA, BLOQUE) \n");
		sb.append("VALUES ( \n");
		sb.append("  ?,?,?,?,?,?,?,?,?, 1) \n");
		PreparedStatement stm=con.prepareStatement(sb.toString());
		
		
		BufferedReader bur=new BufferedReader(new InputStreamReader(new FileInputStream("pronombres_relativos.txt")));
		String line=bur.readLine();
		while(line!=null){
//			logger.info("línea=" + line);
			if (line.startsWith("#")){
				line=bur.readLine();
				continue; //comentario
			}
			String[] valores=line.split("\t");
			stm.setString(1, DBUtil.getHashableId());
			stm.setString(2, "PREL");
			stm.setString(3, valores[0]);//particularidad
			stm.setInt(4, Integer.parseInt(valores[1]));//caso
			//persona
			String persona=valores[2].trim();
			if (!persona.equals("null")){
				stm.setInt(5, Integer.parseInt(persona));	
			}
			stm.setInt(6, Integer.parseInt(valores[3]));//subíndice
			//genero
			String genero=valores[4].trim();
			if (!genero.equals("null")){
			  stm.setString(7, genero);//género
			}
			//numero
			String numero=valores[5].trim();
			if (!numero.equals("null")){
				stm.setInt(8, Integer.parseInt(numero));
			}
			stm.setString(9, valores[6].trim());//forma
			stm.executeUpdate();
			line=bur.readLine();
		}
		stm.close();
		logger.info("terminó de agregar partículas ...");
	}
	
	

	
	public static ApplicationContext creaContexto() {
		ApplicationContext contexto;
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		FileSystemResource res = new FileSystemResource(System.getProperty("user.dir") + File.separator + "daos.xml");
		reader.loadBeanDefinitions(res);
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator + "gerentes-datos.xml");
		reader.loadBeanDefinitions(res);
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator + "flexion.xml");
		reader.loadBeanDefinitions(res);

		contexto = new GenericApplicationContext(factory);
		return contexto;
	}

}
