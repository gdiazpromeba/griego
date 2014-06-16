/*
 * Created on Apr 26, 2004
 */
package kalos.utilidades.integridad;

import java.io.File;
import java.util.List;
import java.util.Map;

import kalos.beans.SustantivoBean;
import kalos.datos.gerentes.GerenteSustantivos;
import kalos.datos.gerentes.GerenteTiposSustantivo;
import kalos.enumeraciones.Beta;
import kalos.enumeraciones.Genero;
import kalos.flexion.declinacion.Declina;
import kalos.operaciones.OpPalabras;
import kalos.utilidades.generadores.CreaDiccionario;
import kalos.utils.Listas;

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
 */
public class IntegridadSustantivos {

	private static ApplicationContext contexto;

	private static GerenteSustantivos gerenteSustantivos;

	private static GerenteTiposSustantivo gerenteTiposSustantivo;

	private static Declina declina;

	private static Logger logger = Logger.getLogger(CreaDiccionario.class.getName());

	public static void main(String[] args) throws Exception {
		DOMConfigurator.configure("log4j.xml");
		contexto = creaContexto();
		gerenteSustantivos = (GerenteSustantivos) contexto.getBean("gerenteSustantivos");
		gerenteTiposSustantivo = (GerenteTiposSustantivo) contexto.getBean("gerenteTiposSustantivo");
		declina = (Declina) contexto.getBean("declina");

		verificaTipos(true);

	}

	

	/**
	 * Averigua los tipos de sustantivos, excepto para aquellos que están
	 * marcados como 4 (inclasificables) Opcionalmente fuerza la clasificación
	 * automáticamente
	 * Parto las listas de devueltos en segmentos de 100 para mejor performance.
	 */
	private static void verificaTipos(boolean corrige) {
		Map<Integer, String> tiposSustantivo =gerenteTiposSustantivo.getMapaTiposID();

		for (char letra : Beta.arrBeta) {
			logger.info("***  letra " + letra + " ***");
			List<String> ids = gerenteSustantivos.seleccionaPorLetra(new String(new char[] { letra }));
			List<List<String>> segmentos = Listas.segmentos(ids, 100);
			for (List<String> segmento : segmentos) {
				List<SustantivoBean> beans = gerenteSustantivos.getBeans(segmento);
				for (SustantivoBean bean : beans) {
					String nominativo = bean.getNominativo();
					String genitivo = bean.getGenitivo();
					Genero genero = bean.getGenero();
					int tipoActual = bean.getTipoNominal();
					if (tipoActual != 4) {
						nominativo = OpPalabras.strBetaACompleto(nominativo);
						genitivo = OpPalabras.strBetaACompleto(genitivo);
						int tipoSugerido = declina.sugiereDeclinacion(nominativo, genitivo, genero);
						if (tipoSugerido != tipoActual) {
							logger.info("letra=" + bean.getLetra() + " código=" + bean.getCodigo() + " nominativo="
									+ bean.getNominativo());
							logger.info("  actual=" + tipoActual + " sugerido=" + tipoSugerido);
							if (corrige) {
								bean.setTipoNominal(tipoSugerido);
								bean.setIdTipo(tiposSustantivo.get(tipoSugerido));
								gerenteSustantivos.actualiza(bean);
								logger.info("  corregido");
							}
						}
					}
				}
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
