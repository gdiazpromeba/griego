/*
 * Created on Sep 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.mantenimiento;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import kalos.beans.AdjetivoComoNominalBean;
import kalos.beans.AdjetivoBean;
import kalos.beans.IrrAdjetivoEntero;
import kalos.datos.adaptadores.AdaptadorGerenteAdjetivos;
import kalos.datos.adaptadores.AdaptadorGerenteIrrAdjetivosEnteros;
import kalos.datos.gerentes.GerenteAdjetivos;
import kalos.datos.gerentes.GerenteAdjetivosComoNominales;
import kalos.datos.gerentes.GerenteIrrAdjetivosEnteros;
import kalos.enumeraciones.Beta;
import kalos.enumeraciones.Genero;
import kalos.flexion.declinacion.Declina;
import kalos.flexion.declinacion.DeclinaAdjetivos;
import kalos.flexion.declinacion.ProblemaSugiriendoDeclinacion;
import kalos.operaciones.OpPalabras;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 * @author GDiaz
 * 
 *         Generación dese cero de la tabla ADJETIVOS_COMO_NOMINALES, la cual es
 *         muy útil para realizar el análisis morfológico de los adjetivos
 *         utilizando los mismos mecanismos que para los sustantivos.
 *
 *         Consta de dos partes principales: una que genera entradas basándose
 *         en la tabla de adjetivos misma, y otra que las genera basándose en la
 *         tabla IRR_ADJETIVOS_ENTEROS.
 *
 *         Debe ejecutarse cada vez que la lista de adjetivos cambie de
 *         cualquier manera. Y, por supuesto, cada vez que las desinencias
 *         nominales cambien.
 *
 **/
public class GeneraAdjetivosComoNominales {

    private static DeclinaAdjetivos declinaAdjetivos;

    private static Declina declina;

    private static GerenteAdjetivosComoNominales gerenteAdjetivosComoNominales;

    private static GerenteIrrAdjetivosEnteros gerenteIrrAdjetivosEnteros;

    private static GerenteAdjetivos gerenteAdjetivos;

    private static Logger logger = Logger.getLogger(GeneraAdjetivosComoNominales.class.getName());

    public static void main(String[] args) throws Exception {
	try {
	    DOMConfigurator.configure("log4j.xml");
	    ApplicationContext contexto = creaContexto();
	    gerenteAdjetivos = (GerenteAdjetivos) contexto.getBean("gerenteAdjetivos");
	    declinaAdjetivos = (DeclinaAdjetivos) contexto.getBean("declinaAdjetivos");
	    declina = (Declina) contexto.getBean("declina");
	    gerenteAdjetivosComoNominales = (GerenteAdjetivosComoNominales) contexto
		    .getBean("gerenteAdjetivosComoNominales");
	    gerenteIrrAdjetivosEnteros = (GerenteIrrAdjetivosEnteros) contexto.getBean("gerenteIrrAdjetivosEnteros");

	    DataSource kalosDataSource = (DataSource) contexto.getBean("kalosDataSource");
	    Connection con = kalosDataSource.getConnection();

	    quitaIndices(con);

	    gerenteAdjetivosComoNominales.borraTodo();
	    generaDesdeAdjetivos();
	    generaDesdeIrrAdjetivos();

	    restauraIndices(con);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println("finalizó la escritura de ADJETIVOS_COMO_NOMINALES");
    }

    public static void quitaIndices(Connection con) throws Exception {
	logger.info("restaurando índices  ...");

	StringBuffer sb = new StringBuffer();
	sb.append("ALTER create  table ADJETIVOS_COMO_NOMINALES(   \n");
	sb.append("ADJETIVO_ID  CHAR(32) not null INDEX_NONE ,   \n");
	sb.append("GENERO       CHAR(1) not null INDEX_NONE ,   \n");
	sb.append("NOMINATIVO   VARCHAR(30) not null INDEX_NONE, \n");
	sb.append("GENITIVO     VARCHAR(30) not null INDEX_NONE ,\n");
	sb.append("TIPO_NOMINAL INTEGER not null INDEX_NONE , \n");
	sb.append("PARTIC       CHAR(3) not null INDEX_NONE , \n");
	sb.append("SUBINDICE    INTEGER default '0' not null INDEX_NONE , \n");
	sb.append("GRADO_COMPARACION CHAR(1) not null INDEX_NONE \n");
	sb.append(");     \n");
	Statement stmUpd = con.createStatement();
	stmUpd.executeUpdate(sb.toString());
	stmUpd.close();
    }

    public static void restauraIndices(Connection con) throws Exception {
	logger.info("restaurando índices  ...");

	StringBuffer sb = new StringBuffer();
	sb.append("ALTER create  table ADJETIVOS_COMO_NOMINALES(   \n");
	sb.append("ADJETIVO_ID  CHAR(32) not null,   \n");
	sb.append("GENERO       CHAR(1) not null,   \n");
	sb.append("NOMINATIVO   VARCHAR(30) not null, \n");
	sb.append("GENITIVO     VARCHAR(30) not null, \n");
	sb.append("TIPO_NOMINAL INTEGER not null, \n");
	sb.append("PARTIC       CHAR(3) not null, \n");
	sb.append("SUBINDICE    INTEGER default '0' not null, \n");
	sb.append("GRADO_COMPARACION CHAR(1) not null, \n");
	sb.append("CONSTRAINT PK_ADJETIVOS_COMO_NOMINALES PRIMARY KEY(ADJETIVO_ID, GENERO, PARTIC, SUBINDICE),  \n");
	sb.append("CONSTRAINT FK_ADJ_COMO_NOM_ADJ FOREIGN KEY(ADJETIVO_ID) REFERENCES ADJETIVOS(ADJETIVO_ID)  \n");
	sb.append(");     \n");
	Statement stmUpd = con.createStatement();
	stmUpd.executeUpdate(sb.toString());
	stmUpd.close();
    }

    /**
     * Genera entradas de "AdjetivoComoNominal" a partir de la tabla de
     * adjetivos, y las inserta en la tabla ADJETIVOS_COMO_NOMINALES. Cada
     * entrada de "ADJETIVOS" generará una entrada "AdjetivoComoNominal" por
     * género que tenga. La inserción se hace en lotes (batches) aprovechando
     * que insertaVarios es transaccional.
     * 
     * @throws Exception
     */
    private static void generaDesdeAdjetivos() throws Exception {
	AdaptadorGerenteAdjetivos aga = new AdaptadorGerenteAdjetivos(gerenteAdjetivos);
	for (int i = 0; i < Beta.arrBeta.length; i++) {
	    String letra = new String(new char[] { Beta.arrBeta[i] });
	    System.out.println("generando entradas para los adjetivos en " + letra);
	    List<String> idsLetra = gerenteAdjetivos.seleccionaPorLetra(letra);
	    List<AdjetivoBean> todosLetra = new ArrayList(aga.getBeans(idsLetra));
	    int cantidad = 0, segmento = 100;
	    while (todosLetra.size() > 0) {
		List<AdjetivoBean> lote = new ArrayList<AdjetivoBean>(todosLetra.subList(0,
			Math.min(segmento, todosLetra.size())));
		todosLetra.removeAll(lote);
		List<AdjetivoComoNominalBean> adNominales = new ArrayList<AdjetivoComoNominalBean>();
		for (Iterator<AdjetivoBean> it = lote.iterator(); it.hasNext();) {
		    AdjetivoBean ea = it.next();
		    // irregulares
		    if (ea.getTipoAdjetivo() == 11) // irregulares
										 // (11)
										 // or
										 // géneros
										 // aislados
										 // (4)
			continue;
		    generaEntradaNominal(ea, adNominales);
		}
		cantidad += lote.size();
		System.out.println("  procesó " + cantidad + " restantes " + todosLetra.size());
		gerenteAdjetivosComoNominales.insertaVarios(adNominales);
	    }
	}
    }

    /**
     * Genera entradas AdjetivoComoNominal a partir de la tabla
     * IRR_ADJETIVOS_ENTEROS, y las inserta en ADJETIVOS_COMO_NOMINALES. Cada
     * entrada de IRR_ADJETIVOS genera una en ADJETIVOS_COMO_NOMINALES. La
     * inserción se hace en batches aprovechando que insertaVarios es
     * transaccional.
     * 
     * @throws Exception
     */
    private static void generaDesdeIrrAdjetivos() throws Exception {
	AdaptadorGerenteAdjetivos agea = new AdaptadorGerenteAdjetivos(gerenteAdjetivos);
	for (int i = 0; i < Beta.arrBeta.length; i++) {
	    String letra = new String(new char[] { Beta.arrBeta[i] });
	    logger.info("generando entradas desde la lista IRR_ADJETIVOS_ENTEROS, para los adjetivos en " + letra);
	    List<String> idsLetra = gerenteAdjetivos.seleccionaPorLetra(letra);
	    List<AdjetivoBean> todosLetra = new ArrayList(agea.getBeans(idsLetra));
	    int cantidad = 0, segmento = 100;
	    while (todosLetra.size() > 0) {
		List<AdjetivoBean> lote = new ArrayList<AdjetivoBean>(todosLetra.subList(0,
			Math.min(segmento, todosLetra.size())));
		todosLetra.removeAll(lote);
		List<AdjetivoComoNominalBean> adNominales = new ArrayList<AdjetivoComoNominalBean>();
		for (Iterator<AdjetivoBean> it = lote.iterator(); it.hasNext();) {
		    AdjetivoBean ea = it.next();
		    generaEntradaNominalDesdeIrr(ea, adNominales);
		}
		cantidad += lote.size();
		logger.info("  procesó " + cantidad + "  restantes " + todosLetra.size());
		gerenteAdjetivosComoNominales.insertaVarios(adNominales);
	    }
	}
    }

    /**
     * Genera valores del tipo "AdjetivoComoNominal" usando la tabla de
     * IRR_ADJETIVOS_ENTEROS. Con los valores generados incrementa la lista
     * listaACN.
     * 
     * @param ea
     * @param listaACN
     * @throws Exception
     */
    private static void generaEntradaNominalDesdeIrr(AdjetivoBean ea, List<AdjetivoComoNominalBean> listaACN)
	    throws Exception {
	AdaptadorGerenteIrrAdjetivosEnteros air = new AdaptadorGerenteIrrAdjetivosEnteros(gerenteIrrAdjetivosEnteros);
	List<String> ids = gerenteIrrAdjetivosEnteros.seleccionaPorAdjetivo(ea.getId());
	List<IrrAdjetivoEntero> iae = new ArrayList(air.getBeans(ids));
	for (IrrAdjetivoEntero entero : iae) {
	    AdjetivoComoNominalBean nominal = new AdjetivoComoNominalBean();
	    nominal.setIdAdjetivo(entero.getAdjetivoId());
	    nominal.setGenero(entero.getGenero());
	    nominal.setNominativo(entero.getNominativo());
	    nominal.setGenitivo(entero.getGenitivo());
	    nominal.setTipoNominal(entero.getTipoSustantivo());
	    nominal.setParticularidad(entero.getParticularidad());
	    nominal.setSubindice(entero.getSubindice());
	    nominal.setGrado(entero.getGrado());
	    listaACN.add(nominal);
	}
    }

    private static void generaEntradaNominal(AdjetivoBean ea, List<AdjetivoComoNominalBean> listaACN) throws Exception {
	int tipoAdjetivo = ea.getTipoAdjetivo();
	// carga el map con los nominativos que existen
	Genero generoParaSugerir = null;
	Map<Genero, String> nominativos = new HashMap<Genero, String>();
	Map<Genero, String> genitivos = new HashMap<Genero, String>();
	String genitivoAUsar;
	if (ea.getMasculino() != null) {
	    nominativos.put(Genero.Masculino, OpPalabras.strBetaACompleto(ea.getMasculino()));
	    if (ea.getGenitivo() != null) {
		genitivos.put(Genero.Masculino, OpPalabras.strBetaACompleto(ea.getGenitivo()));
	    }
	}
	if (ea.getFemenino() != null) {
	    generoParaSugerir = Genero.Femenino;
	    nominativos.put(Genero.Femenino, OpPalabras.strBetaACompleto(ea.getFemenino()));
	    if (ea.getGenitivo() != null && ea.getTipoAdjetivo() != 2 && ea.getTipoAdjetivo() != 3) {
		genitivos.put(Genero.Femenino, OpPalabras.strBetaACompleto(ea.getGenitivo()));
	    }
	}
	if (ea.getNeutro() != null) {
	    generoParaSugerir = Genero.Neutro;
	    nominativos.put(Genero.Neutro, OpPalabras.strBetaACompleto(ea.getNeutro()));
	    if (ea.getGenitivo() != null) {
		genitivos.put(Genero.Neutro, OpPalabras.strBetaACompleto(ea.getGenitivo()));
	    }
	}
	if (ea.getMascFem() != null) {
	    generoParaSugerir = Genero.MasculinoOFemenino;
	    nominativos.put(Genero.MasculinoOFemenino, OpPalabras.strBetaACompleto(ea.getMascFem()));
	    if (ea.getGenitivo() != null) {
		genitivos.put(Genero.MasculinoOFemenino, OpPalabras.strBetaACompleto(ea.getGenitivo()));
	    }
	}

	for (Map.Entry<Genero, String> entradaNominativo : nominativos.entrySet()) {
	    Genero genero = entradaNominativo.getKey();
	    String genitivo = genitivos.get(genero);
	    if (genitivo == null) {
		genitivo = declinaAdjetivos.sugiereGenitivo(nominativos, genero, tipoAdjetivo);
		genitivos.put(genero, genitivo);
	    }
	}

	// ahora propone los tipos de sustantivo
	Map<Genero, Integer> tipos = new HashMap<Genero, Integer>();
	for (Genero genero : Genero.values()) {
	    String nominativo = nominativos.get(genero);
	    String genitivo = genitivos.get(genero);
	    if (nominativo != null) {
		int tipo;
		try {
		    tipo = declina.sugiereDeclinacion(nominativo, genitivo, genero);
		} catch (ProblemaSugiriendoDeclinacion e) {
		    StringBuffer sb = new StringBuffer(e.getMessage());
		    sb.append(" el nominativo es " + OpPalabras.strCompletoABeta(nominativo));
		    sb.append(" y el genitivo es " + OpPalabras.strCompletoABeta(genitivo));
		    logger.error(sb.toString());
		    throw new RuntimeException(sb.toString());
		}
		tipos.put(genero, tipo);
	    }
	}

	// genera los beanes
	for (Genero genero : Genero.values()) {
	    String nominativo = nominativos.get(genero);
	    String genitivo = genitivos.get(genero);
	    Integer tipo = tipos.get(genero);
	    if (nominativo != null) {
		nominativo = OpPalabras.strCompletoABeta(nominativo);
		genitivo = OpPalabras.strCompletoABeta(genitivo);
		AdjetivoComoNominalBean bean = new AdjetivoComoNominalBean();
		bean.setIdAdjetivo(ea.getId());
		bean.setGenero(genero);
		bean.setNominativo(nominativo);
		bean.setGenitivo(genitivo);
		bean.setTipoNominal(tipo);
		bean.setGrado(ea.getGrado());
		bean.setParticularidad(ea.getParticularidad());
		listaACN.add(bean);
	    }
	}

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
