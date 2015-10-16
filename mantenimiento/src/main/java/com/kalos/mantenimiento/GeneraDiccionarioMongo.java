package com.kalos.mantenimiento;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.event.ListSelectionEvent;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.AdverbioBean;
import com.kalos.beans.ConjuncionBean;
import com.kalos.beans.EncParticulaBean;
import com.kalos.beans.EntradaDiccionario;
import com.kalos.beans.ISignificados;
import com.kalos.beans.InterjeccionBean;
import com.kalos.beans.ParticulaBean;
import com.kalos.beans.PreposicionBean;
import com.kalos.beans.Significado;
import com.kalos.beans.SustantivoBean;
import com.kalos.beans.VerboBean;
import com.kalos.comun.config.DaoConfig;
import com.kalos.comun.config.FlexionConfig;
import com.kalos.comun.config.ServicesConfig;
import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteAdverbios;
import com.kalos.datos.gerentes.GerenteConjunciones;
import com.kalos.datos.gerentes.GerenteDiccionario;
import com.kalos.datos.gerentes.GerenteEncParticulas;
import com.kalos.datos.gerentes.GerenteInterjecciones;
import com.kalos.datos.gerentes.GerenteParticulas;
import com.kalos.datos.gerentes.GerentePreposiciones;
import com.kalos.datos.gerentes.GerenteSignificados;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.datos.util.DBUtil;
import com.kalos.enumeraciones.Beta;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;
import com.kalos.operaciones.comparadores.ComparadorBeansGriegos;
import com.kalos.recursos.Recursos;
import com.kalos.utils.Listas;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class GeneraDiccionarioMongo {

	private static Logger logger = Logger
			.getLogger(GeneraTermRegInfinitivo.class.getName());

	private static Connection con;

	private static ApplicationContext contexto;

	private static GerenteDiccionario gerenteDiccionario;
	private static GerenteSignificados gerenteSignificados;

	public static ApplicationContext creaContexto() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(DaoConfig.class, ServicesConfig.class);
		context.refresh();
		return context;
	}

	public static void main(String[] args) throws Exception {
		contexto = creaContexto();
		DataSource kalosDataSource = (DataSource) contexto.getBean("kalosDataSource");
		con = kalosDataSource.getConnection();
		con.setAutoCommit(false);

		creaContexto();
		
		gerenteDiccionario = (GerenteDiccionario) contexto.getBean("gerenteDiccionario");
		gerenteSignificados = (GerenteSignificados) contexto.getBean("gerenteSignificados");

		Recursos.cambiaLocale("en");
		MongoClient mongoClient = new MongoClient(new MongoClientURI(
				"mongodb://localhost"));
		MongoDatabase mongoDb = mongoClient.getDatabase("kalos");
		MongoCollection diccionarioMongo = mongoDb.getCollection("diccionario");
		
		if (diccionarioMongo != null) {
			diccionarioMongo.drop();
		}

		mongoDb.createCollection("diccionario");



		for (char carLetra : Beta.arrBeta) {
			String letra = String.valueOf(carLetra);
			System.out.println("letter=" + letra);
			construyeSustantivos(letra, diccionarioMongo);
			construyeAdverbios(letra, diccionarioMongo);
			construyeAdjetivos(letra, diccionarioMongo);
			construyeVerbos(letra, diccionarioMongo);
			construyeInterjeciones(letra, diccionarioMongo);
		}

		
		 construyeParticulas(diccionarioMongo);
		 construyePreposiciones(diccionarioMongo);
		 construyeConjunciones(diccionarioMongo);
		 
		 System.out.println("terminado");

		int codigo = 10;
		for (char carLetra : Beta.arrBeta) {
			String letra = String.valueOf(carLetra);
			System.out.println("ordenando " + letra);
			codigo = ordenaLetra(letra, codigo, diccionarioMongo);
			codigo += 10;
		}

		mongoClient.close();

	}

	private static List<Document> getDocsSignificado(ISignificados bean) {
		gerenteSignificados.pueblaTodosLosSignificados(bean);
		Map<Idioma, Significado> sigs = bean.getSignificados();
		List<Document> sigDocs = new ArrayList<>();
		for (Map.Entry<Idioma, Significado> sigEntry : sigs.entrySet()) {
			Significado sig = sigEntry.getValue();
			Document docSig = new Document(sig.getIdioma(), sig.getValor());
			sigDocs.add(docSig);
		}
		return sigDocs;
	}

	private static void construyeSustantivos(String letra, MongoCollection diccionario) throws SQLException {

		GerenteSustantivos gerenteSustantivos = (GerenteSustantivos) contexto.getBean("gerenteSustantivos");
		List<String> idsSustantivo = gerenteSustantivos.seleccionaPorLetra(letra);

		List<List<String>> segmentos = Listas.segmentos(idsSustantivo, 10);

		int codigo = 50000000;
		for (List<String> list : segmentos) {

			List<SustantivoBean> sustantivos = gerenteSustantivos
					.getBeans(list);

			for (SustantivoBean bean : sustantivos) {

				Document ent = new Document();
				ent.append("_id", bean.getId());
				ent.append("letter", letra);
				ent.append("code", codigo++);
				ent.append("wordType", TipoPalabra.Sustantivo.toString());

				poneFormasEnBeanDiccionario(bean.getNominativo(), ent);

				List<Document> listaSigs = getDocsSignificado(bean);
				ent.append("meanings", listaSigs);

				diccionario.insertOne(ent);
			}

		}

	}

	private static void construyeAdverbios(String letra,
			MongoCollection diccionario) throws SQLException {

		GerenteAdverbios gerente = (GerenteAdverbios) contexto.getBean("gerenteAdverbios");
		List<String> ids = gerente.seleccionaPorLetra(letra);

		List<List<String>> segmentos = Listas.segmentos(ids, 10);

		int codigo = 10;
		for (List<String> list : segmentos) {

			List<AdverbioBean> beans = gerente.getBeans(list);

			for (AdverbioBean bean : beans) {

				Document ent = new Document();
				ent.append("_id", bean.getId());
				ent.append("letter", letra);
				ent.append("code", codigo++);
				ent.append("wordType", TipoPalabra.Adverbio.toString());

				poneFormasEnBeanDiccionario(bean.getAdverbio(), ent);

				List<Document> listaSigs = getDocsSignificado(bean);
				ent.append("meanings", listaSigs);

				diccionario.insertOne(ent);
			}

		}

	}

	private static void construyeAdjetivos(String letra, MongoCollection diccionario) throws SQLException {

		GerenteAdjetivos gerente = (GerenteAdjetivos) contexto.getBean("gerenteAdjetivos");
		List<String> ids = gerente.seleccionaPorLetra(letra);

		List<List<String>> segmentos = Listas.segmentos(ids, 10);

		int codigo = 10;
		for (List<String> list : segmentos) {

			List<AdjetivoBean> beans = gerente.getBeans(list);

			for (AdjetivoBean bean : beans) {

				Document ent = new Document();
				ent.append("_id", bean.getId());
				ent.append("letter", letra);
				ent.append("code", codigo++);
				ent.append("wordType", TipoPalabra.Adjetivo.toString());

				String formaBeta = null;
				if (bean.getMasculino() != null)
					formaBeta = bean.getMasculino();
				else if (bean.getMascFem() != null)
					formaBeta = bean.getMascFem();
				else if (bean.getFemenino() != null)
					formaBeta = bean.getFemenino();
				else if (bean.getNeutro() != null)
					formaBeta = bean.getNeutro();
				poneFormasEnBeanDiccionario(formaBeta, ent);

				List<Document> listaSigs = getDocsSignificado(bean);
				ent.append("meanings", listaSigs);

				diccionario.insertOne(ent);
			}
		}

	}

	/**
	 * dada la forma beta inicial, carga el resto de las strings relacionadas en
	 * el bean de EntradaDiccionario
	 * 
	 * @param formaBeta
	 * @param ent
	 */
	private static void poneFormasEnBeanDiccionario(String formaBeta,
			Document ent) {
		String formaCompleta = OpPalabras.strBetaACompleto(formaBeta);
		String neutralizadaBeta = OpPalabras.neutralizaBeta(formaBeta);
		String formaAbreviada = OpPalabras.strAbreviaCompleta(formaCompleta);
		String formaBetaAbreviado = OpPalabras.strCompletoABeta(formaAbreviada);
		String formaUnicode = OpPalabras.strCompletoAUnicode(formaCompleta);
		ent.append("beta", formaBeta);
		ent.append("betaNeutralized", neutralizadaBeta);
		ent.append("betaShortened", formaBetaAbreviado);
		ent.append("unicode", formaUnicode);
	}

	private static void construyeVerbos(String letra, MongoCollection diccionario) throws SQLException {

		GerenteVerbos gerente = (GerenteVerbos) contexto.getBean("gerenteVerbos");
		List<String> ids = gerente.seleccionaPorLetra(letra);

		List<List<String>> segmentos = Listas.segmentos(ids, 10);

		int codigo = 10;
		for (List<String> list : segmentos) {

			List<VerboBean> beans = gerente.getBeans(list);

			for (VerboBean bean : beans) {
				
				Document ent = new Document();
				ent.append("_id", bean.getId());
				ent.append("letter", letra);
				ent.append("code", codigo++);
				ent.append("wordType", TipoPalabra.Verbo.toString());		
				
				poneFormasEnBeanDiccionario(bean.getVerbo(), ent);

				List<Document> listaSigs = getDocsSignificado(bean);
				ent.append("meanings", listaSigs);

				diccionario.insertOne(ent);
			}

		}

	}

	private static void construyeInterjeciones(String letra, MongoCollection diccionario) throws SQLException {

		GerenteInterjecciones gerente = (GerenteInterjecciones) contexto.getBean("gerenteInterjecciones");
		List<String> ids = gerente.seleccionaPorLetra(letra);

		List<List<String>> segmentos = Listas.segmentos(ids, 10);

		int codigo = 10;
		for (List<String> list : segmentos) {

			List<InterjeccionBean> beans = gerente.getBeans(list);

			for (InterjeccionBean bean : beans) {
				
				Document ent = new Document();
				ent.append("_id", bean.getId());
				ent.append("letter", letra);
				ent.append("code", codigo++);
				ent.append("wordType", TipoPalabra.Interjeccion.toString());		
				
				
				poneFormasEnBeanDiccionario(bean.getInterjeccion(), ent);

				List<Document> listaSigs = getDocsSignificado(bean);
				ent.append("meanings", listaSigs);

				diccionario.insertOne(ent);				
	
			}

		}

	}

	/**
	 * lo que construye son los "encabezados" de partícula. Es decir, para los
	 * pronombres personales por ejemplo, solamente EGW/
	 * 
	 * @throws SQLException
	 */
	private static void construyeParticulas(MongoCollection diccionario) throws SQLException {

		GerenteEncParticulas gerente = (GerenteEncParticulas) contexto.getBean("gerenteEncParticulas");

		int codigo = 10;

		List<EncParticulaBean> beans = gerente.getTodos();

		for (EncParticulaBean bean : beans) {
			
			Document ent = new Document();
			ent.append("_id", bean.getId());
			ent.append("letter", bean.getForma().substring(0, 1));
			ent.append("code", codigo++);
			ent.append("wordType", bean.getTipoPalabra().toString());	
			
			poneFormasEnBeanDiccionario(bean.getForma(), ent);


			List<Document> listaSigs = getDocsSignificado(bean);
			ent.append("meanings", listaSigs);

			diccionario.insertOne(ent);		
		}

	}

	private static void construyePreposiciones(MongoCollection diccionario) throws SQLException {

		GerentePreposiciones gerente = (GerentePreposiciones) contexto.getBean("gerentePreposiciones");

		int codigo = 10;

		List<String> ids = gerente.seleccionaTodosLosIds();

		List<List<String>> segmentos = Listas.segmentos(ids, 10);

		for (List<String> list : segmentos) {

			List<PreposicionBean> beans = gerente.getBeans(list);

			for (PreposicionBean bean : beans) {
				
				Document ent = new Document();
				ent.append("_id", bean.getId());
				ent.append("letter", bean.getPreposicion().substring(0, 1));
				ent.append("code", codigo++);
				ent.append("wordType", TipoPalabra.Preposicion.toString());				
				
				poneFormasEnBeanDiccionario(bean.getPreposicion(), ent);


				List<Document> listaSigs = getDocsSignificado(bean);
				ent.append("meanings", listaSigs);

				diccionario.insertOne(ent);		
			}

		}

	}

	private static void construyeConjunciones(MongoCollection diccionario) throws SQLException {

		GerenteConjunciones gerente = (GerenteConjunciones) contexto.getBean("gerenteConjunciones");

		int codigo = 10;

		List<ConjuncionBean> beans = gerente.seleccionaTodos();

		for (ConjuncionBean bean : beans) {
			Document ent = new Document();
			ent.append("_id", bean.getId());
			ent.append("letter", bean.getForma().substring(0, 1));
			ent.append("code", codigo++);
			ent.append("wordType", TipoPalabra.Conjuncion.toString());				
			
			poneFormasEnBeanDiccionario(bean.getForma(), ent);

			List<Document> listaSigs = getDocsSignificado(bean);
			ent.append("meanings", listaSigs);

			diccionario.insertOne(ent);	
		}

	}

	/**
	 * ordena 1 letra (para todas las formas, todos los tipos de palabra) del
	 * diccionario
	 * 
	 * @param letra
	 */
	public static int ordenaLetra(String letra, int codigoInicial, MongoCollection diccionario) throws Exception {
		List<String> ids = gerenteDiccionario.seleccionaPorLetra(letra);
		List<EntradaDiccionario> beans = gerenteDiccionario.getRegistros(ids);
		String[] propiedades = { "normalBeta" };
		OpBeans.pasaDeBetaACompleto(beans, propiedades);
		Collections.sort(beans, new ComparadorBeansGriegos(propiedades));
		OpBeans.pasaDeCompletoABeta(beans, propiedades);
		int codigo = codigoInicial;
		Iterator<EntradaDiccionario> localIterator = beans.iterator();
		while (localIterator.hasNext()) {
			EntradaDiccionario ent = localIterator.next();
			String id = ent.getId();
			diccionario.updateOne(new Document("_id", id), new Document("$set", new Document("code", codigo)));
			codigo += 10;
			if (codigo % 1000 == 0) {
				System.out.println("ordenando, código=" + codigo);
			}
		}
		return codigo;
	}

}
