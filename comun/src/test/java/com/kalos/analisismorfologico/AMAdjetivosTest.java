package com.kalos.analisismorfologico;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.kalos.analisismorfologico.negocio.AMAdjetivos;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.TestAdjetivosBean;
import com.kalos.datos.gerentes.GerenteTestAdjetivos;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.CompLetras;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.OpPalabras;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AMAdjetivosTest extends TestCase implements CompLetras {

	private static Logger logger = Logger.getLogger(AMAdjetivosTest.class.getName());

	private GerenteTestAdjetivos gerenteTestAdjetivos;

	private AMAdjetivos amAdjetivos;

	public AMAdjetivosTest(String metodo) {
		super(metodo);
	}


	private static long tiempoAcumulado = 0;

	public void testTabla() {
		try {
			List<TestAdjetivosBean> todos = gerenteTestAdjetivos.seleccionaTodos();
			AACacheable cacheAA=null;
			String idAdjetivoAnterior="";
			for (TestAdjetivosBean bean : todos) {
				if (!idAdjetivoAnterior.equals(bean.getIdAdjetivo())){
					//cambio el caché cada vez que cambio de sustantivo
					cacheAA=new AACacheable();
				}				
				boolean ret = testGenerico(bean.getFormaDeclinada(), bean.getIdAdjetivo(), bean.getCaso(), bean
						.getGenero(), bean.getNumero(), cacheAA);
				if (!ret) {
					logger.error("falló para la forma " + bean.getFormaDeclinada());
					logger.info("id=" + bean.getIdAdjetivo() + " caso=" + bean.getCaso() + " genero="
							+ bean.getGenero() + " numero=" + bean.getNumero());
				}
				assertEquals(ret, true);
				if (!ret) {
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Dada una forma conjugada, llama al análisis morfológico y comprueba si el código del sustantivo canónico 
	 * está en el set de resultado
	 * @param formaDeclinada: en BETA
	 * @param conta         
	 */
	public boolean testGenerico(String formaDeclinada, String idAdjetivo, Caso caso, Genero genero, Numero numero, AACacheable cacheAA) {
		System.out.print("forma declinada: ... " + formaDeclinada);
		String[] entradas = new String[] { OpPalabras.strBetaACompleto(formaDeclinada) };
		HashSet<ResultadoUniversal> sRes = new HashSet<ResultadoUniversal>();
		long tardo = amAdjetivos.buscaCanonica(entradas, sRes, cacheAA, false, false);
		tiempoAcumulado += tardo;
		System.out.print("  tardanza=" + tardo + " tiempo acumulado=" + tiempoAcumulado + "\n");
		boolean encontroCodigo = false;
		for (Iterator<ResultadoUniversal> it = sRes.iterator(); it.hasNext();) {
			ResultadoUniversal regAux = it.next();
			boolean idIgual = regAux.getId().equals(idAdjetivo);
			boolean casoIgual = regAux.getCaso().equals(caso);
			boolean generoIgual = regAux.getGenero().equals(genero);
			boolean numeroIgual = regAux.getNumero().equals(numero);
			if (idIgual && casoIgual && generoIgual && numeroIgual) {
				encontroCodigo = true;
				break;
			}
		}
		return encontroCodigo;
	}

	public static ApplicationContext creaContexto() {
		ApplicationContext contexto;
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		ClassPathResource res = new ClassPathResource( "daos.xml");
		reader.loadBeanDefinitions(res);
		res = new ClassPathResource( "gerentes-datos.xml");
		reader.loadBeanDefinitions(res);
		res = new ClassPathResource( "flexion.xml");
		reader.loadBeanDefinitions(res);
		res = new ClassPathResource( "analisisMorfologico.xml");
		reader.loadBeanDefinitions(res);

		contexto = new GenericApplicationContext(factory);
		return contexto;
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		ApplicationContext contexto = creaContexto();
		amAdjetivos = (AMAdjetivos) contexto.getBean("amAdjetivos");
		amAdjetivos.setApplicationContext(contexto);
		gerenteTestAdjetivos = (GerenteTestAdjetivos) contexto.getBean("gerenteTestAdjetivos");
	}

}