package kalos.analisismorfologico.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import kalos.beans.AdjetivoBean;
import kalos.datos.adaptadores.AdaptadorGerenteAdjetivos;
import kalos.datos.gerentes.GerenteAdjetivos;
import kalos.enumeraciones.Beta;
import kalos.flexion.declinacion.DeclinaAdjetivos;
import kalos.operaciones.OpPalabras;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class TiposAdjetivoTest extends TestCase {

	private DeclinaAdjetivos declinaAdjetivos;

	private GerenteAdjetivos gerenteAdjetivos;
	

	public TiposAdjetivoTest(String metodo) {
		super(metodo);
	}

	public static void main(String[] args) {
		new TiposAdjetivoTest("testTipos");
	}



	public void testTipos() {
		int codigo=0;
		String letra = null;
		try {
			for (int i = 0; i < Beta.arrBeta.length; i++) {
				letra = new String(new char[] { Beta.arrBeta[i] });
				System.out.println("procesando letra " + letra);
				List<String> idsLetra = gerenteAdjetivos.seleccionaPorLetra(letra);
				AdaptadorGerenteAdjetivos aga=new AdaptadorGerenteAdjetivos(gerenteAdjetivos);
				List<AdjetivoBean> entradasLetra=new ArrayList<AdjetivoBean>(aga.getBeans(idsLetra));
				for (AdjetivoBean bean : entradasLetra) {
					codigo=bean.getCodigo();
					int tipoBD=bean.getTipoAdjetivo();
					if (tipoBD == 11) { // los que están marcados como
												// irregulares no se analizan
						continue;
					}
					String mascComp=bean.getMasculino()!=null?OpPalabras.strBetaACompleto(bean.getMasculino()):null;
					String femComp=bean.getFemenino()!=null?OpPalabras.strBetaACompleto(bean.getFemenino()):null;
					String neuComp=bean.getNeutro()!=null?OpPalabras.strBetaACompleto(bean.getNeutro()):null;
					String mascFemComp=bean.getMascFem()!=null?OpPalabras.strBetaACompleto(bean.getMascFem()):null;
					int  tipoSugerido = declinaAdjetivos.sugiereTipoAdjetivoEntero(
							mascComp,
							femComp, 
							neuComp,
							mascFemComp
							);
					
					boolean losTiposSonIguales = tipoSugerido == tipoBD;
					if (!losTiposSonIguales ) {
						System.out.println("letra=" + bean.getLetra() + " codigo=" + bean.getCodigo() + " ****** tipo en BD=" + tipoBD + " sugeridos=" + tipoSugerido);
					}
					assertTrue(losTiposSonIguales);
				}
			}
		} catch (Exception ex) {
			System.out.println("error en letra=" + letra + " código=" + codigo);
			ex.printStackTrace();
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
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator + "applicationContext.xml");
		reader.loadBeanDefinitions(res);
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator + "analisisMorfologico.xml");
		reader.loadBeanDefinitions(res);

		contexto = new GenericApplicationContext(factory);
		return contexto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		ApplicationContext contexto = creaContexto();
		declinaAdjetivos = (DeclinaAdjetivos) contexto.getBean("declinaAdjetivos");
		gerenteAdjetivos = (GerenteAdjetivos) contexto.getBean("gerenteAdjetivos");
	}

}
