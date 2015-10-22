package com.kalos.analisismorfologico;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.kalos.analisismorfologico.negocio.AMAdjetivos;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.TestAdjetivosBean;
import com.kalos.datos.gerentes.GerenteTestAdjetivos;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.OpPalabras;

;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AMAdjetivosTest extends BaseAMTest {

	private static Logger logger = Logger.getLogger(AMAdjetivosTest.class.getName());
    
	private GerenteTestAdjetivos gerenteTestAdjetivos;

	private AMAdjetivos amAdjetivos;




	private static long tiempoAcumulado = 0;

	public void testTabla() {
		try {
		    LocalTime inicio= LocalTime.now();
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
			LocalTime fin= LocalTime.now();
			Duration duracion = Duration.between(inicio, fin);
			logger.info("Duración del test de ánálisis morfológicos = " + duracion.getSeconds() + " segundos");
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
		System.out.println("forma declinada: ... " + formaDeclinada);
		String[] entradas = new String[] { OpPalabras.strBetaACompleto(formaDeclinada) };
		Set<ResultadoUniversal> sRes = amAdjetivos.buscaCanonica(entradas, cacheAA, false, false);
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