package kalos.utilidades.comienzo;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import kalos.enumeraciones.CompLetras;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;
import kalos.utilidades.abms.ABM;
import kalos.visual.GerenteDeApariencias;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class ComienzoABM extends JFrame implements CompLetras {

	private static int ancho = 422, alto = 238;

	private static Logger logger = Logger.getLogger(ComienzoABM.class.getName());

	private static ComienzoABM com;

	/**
	 * el panel que contiene los controles que comienzan el programa.
	 */
	private static Caratula car;

	/**
	 * crea un archivo de log y lo asigna al lugar apropiado. Si esto falla, se debería terminar la aplicación.
	 * @return
	 */
	private static boolean creaArchivoLog() {
		try {
			DOMConfigurator.configure("log4j.xml");
			logger = Logger.getLogger("kalos");
			logger.info("Archivo de log creado exitosamente");
			return true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, Recursos.getCadena("fallo_inicializando_bitacora"));
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Creo una instancia de este frame, la dimensiono y muestro.
	 * Le agrego un panel Carátula 
	 *
	 */
	private static void creaPantalla() throws Exception {
		GerenteDeApariencias.poneLYFPorDefecto();
		com = new ComienzoABM();
		inicializa();
		

		com.setUndecorated(true);
		int horizontal = ancho, vertical = alto;

		car = new Caratula();
		com.setContentPane(car);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int deltaX = (int) (dim.getWidth() / 2) - (horizontal / 2);
		int deltaY = (int) (dim.getHeight() / 2) - (vertical / 2);
		com.setBounds(deltaX, deltaY, horizontal, vertical);
		com.setVisible(true);

		estadoIncialComboIdioma();
		GradacionPanel inic = new GradacionPanel(50, 255, 10, car);
		inic.getThread().start();
		eleccionIdioma();
		
		creaContexto();

	}

	public static void creaContexto() {
		
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		FileSystemResource res = new FileSystemResource(System.getProperty("user.dir") + File.separator
				+ "daos.xml");
		reader.loadBeanDefinitions(res);
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator
				+ "gerentes-datos.xml");
		reader.loadBeanDefinitions(res);
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator
				+ "flexion.xml");
		reader.loadBeanDefinitions(res);
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator
				+ "interfaz-usuario.xml");
		reader.loadBeanDefinitions(res);
		
		contexto=new GenericApplicationContext(factory);
		
//		contexto = new FileSystemXmlApplicationContext(System.getProperty("user.dir") + File.separator
//				+ "applicationContext.xml");
		logger.debug("contexto cargado exitosamente");
	}

	public static void muestraFrame(String abm, Dimension dim) {
		JFrame fra = new JFrame();
		ABM elAbm = (ABM) contexto.getBean(abm);
		fra.add(elAbm);
		fra.pack();
		fra.setSize(dim);
		fra.setVisible(true);
		fra.setLocationRelativeTo(null);
		elAbm.agregaToolbar(fra);
		elAbm.agregaMenuBar(fra);
		
		fra.addWindowListener(new EscuchaVentana(fra));
	}
	
	private static class EscuchaVentana extends WindowAdapter{
		private JFrame frame;
		public  EscuchaVentana(JFrame frame) {
			this.frame=frame;
		}
		public void windowClosing(WindowEvent ev) {
		    this.frame.dispose();
		}
		
//		@Override
//		public void windowDeiconified(WindowEvent e) {
//			System.out.println("ancho=" +  this.frame.getWidth() + " alto=" + this.frame.getHeight());
//		}
	}

	public static void main(String[] args) throws Exception {
		boolean logOK = creaArchivoLog();
		if (!logOK) {
			System.exit(0);
		}
		//abreBaseDeDatos();
		creaPantalla();
		eventos();
	}



	/**
	 * el comienzo de la aplicación propiamente dicho.
	 * después de cada task, están esas variables "OK" que nos indican que el paso
	 * pudo completarse. Si alguna task no se pudo completar, se muestra un mensaje adecuado en la última
	 * task (lidiaConErrores)
	 *
	 */
	public static void comienzo() {
		com.setVisible(false);

		JFrame fra = new JFrame();
		fra.setSize(new Dimension(200, 200));
		JMenuBar meb = new JMenuBar();
		meb.setLayout(new GridLayout(0, 1));

		JMenu menuAdjetivos = new JMenu("Adjetivos");
		meb.add(menuAdjetivos);
        JMenu menuAdverbios = new JMenu("Adverbios");
        meb.add(menuAdverbios);
        JMenu menuConjunciones = new JMenu("Conjunciones");
        meb.add(menuConjunciones);
        JMenu menuDesinSust = new JMenu("Desinenicas Sustantivo");
        meb.add(menuDesinSust);
        JMenu menuInterjecciones= new JMenu("Interjecciones");
        meb.add(menuInterjecciones);        
        JMenu menuParticulas= new JMenu("Partículas");
        meb.add(menuParticulas);
        JMenu menuPreposiciones = new JMenu("Preposiciones");
        meb.add(menuPreposiciones);
		JMenu menuSust = new JMenu("Sustantivos");
		meb.add(menuSust);
        JMenu menuVerbos = new JMenu("Verbos");
        meb.add(menuVerbos);

        
        

        


		JMenuItem abmAdjetivos = new JMenuItem("ABM adjetivos");
		menuAdjetivos.add(abmAdjetivos);
		abmAdjetivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				muestraFrame("abmAdjetivos", new Dimension(1001, 525));
			}
		});

		JMenuItem abmDesinenciasSustantivo = new JMenuItem("ABM Desinenicas Sustantivo");
		menuDesinSust.add(abmDesinenciasSustantivo);
		abmDesinenciasSustantivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				muestraFrame("abmDesinSust", new Dimension(800, 600));
			}
		});
		
        JMenuItem abmIrrsSustantivo = new JMenuItem("ABM Irregularidades de sustantivos");
        menuSust.add(abmIrrsSustantivo);
        abmIrrsSustantivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                muestraFrame("abmIrrSustantivos", new Dimension(800, 600));
            }
        });		

		JMenuItem abmSustantivos = new JMenuItem("ABM Sustantivo");
		menuSust.add(abmSustantivos);
		abmSustantivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				muestraFrame("abmSustantivos", new Dimension(833, 646));
			}
		});
        
        JMenuItem abmVerbos = new JMenuItem("ABM Verbo");
        menuVerbos.add(abmVerbos);
        abmVerbos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                muestraFrame("abmVerbos", new Dimension(946, 637));
            }
        });
        
        JMenuItem abmAdverbios = new JMenuItem("ABM Adverbio");
        menuAdverbios.add(abmAdverbios);
        abmAdverbios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                muestraFrame("abmAdverbios", new Dimension(800, 600));
            }
        });
        
        JMenuItem abmConjunciones = new JMenuItem("ABM Conjunciones");
        menuConjunciones.add(abmConjunciones);
        abmConjunciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                muestraFrame("abmConjunciones", new Dimension(800, 600));
            }
        });
        
        JMenuItem  abmPreposiciones= new JMenuItem("ABM Preposiciones");
        menuPreposiciones.add(abmPreposiciones);
        abmPreposiciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                muestraFrame("abmPreposiciones", new Dimension(800, 600));
            }
        });   
        
        JMenuItem  abmParticulas= new JMenuItem("ABM Partículas");
        menuParticulas.add(abmParticulas);
        abmParticulas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                muestraFrame("abmParticulas", new Dimension(800, 600));
            }
        });
        
        JMenuItem  abmInterjecciones= new JMenuItem("ABM Interjecciones");
        menuInterjecciones.add(abmInterjecciones);
        abmInterjecciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                muestraFrame("abmInterjecciones", new Dimension(800, 600));
            }
        });         


		fra.add(meb);
		fra.setVisible(true);

		fra.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				//cierraBaseDeDatos();
				System.exit(0);
			}
		});

	}

	/**
	 * pone el estado inicial del combo de idioma, de acuerdo a lo 
	 * que hubiere en el archivo de configuración
	 *
	 */
	private static void estadoIncialComboIdioma() {
		if (Configuracion.getUltimoIdioma().equals("en")) {
			car.cmbIdiomas.setSelectedIndex(0);
			car.botEntrar.setText("Start");
		} else if (Configuracion.getUltimoIdioma().equals("es")) {
			car.cmbIdiomas.setSelectedIndex(1);
			car.botEntrar.setText("Comenzar");
		} else if (Configuracion.getUltimoIdioma().equals("fr")) {
			car.cmbIdiomas.setSelectedIndex(2);
			car.botEntrar.setText("Commencer");
		}
	}

	/** cambia la configuración de idioma según lo que
	 * está seleccionado en el menú en ese momento
	 *
	 */
	private static void eleccionIdioma() {
		switch (car.cmbIdiomas.getSelectedIndex()) {
		case 0:
			Configuracion.setUltimoIdioma("en");
			car.botEntrar.setText("Start");
			Configuracion.reescribeIni();
			break;
		case 1:
			Configuracion.setUltimoIdioma("es");
			car.botEntrar.setText("Comenzar");
			Configuracion.reescribeIni();
			break;
		case 2:
			Configuracion.setUltimoIdioma("fr");
			car.botEntrar.setText("Commencer");
			Configuracion.reescribeIni();
			break;
		}
		car.botEntrar.repaint();
		car.botEntrar.invalidate();
		Recursos.cambiaLocale(Configuracion.getUltimoIdioma());
	}

	/**
	 * agrega eventos (especialmente el comienzo propiamente dicho) a los
	 * controles de la carátula
	 */
	private static void eventos() {
		car.cmbIdiomas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent mev) {
				if (mev.getStateChange() != ItemEvent.SELECTED)
					return;
				eleccionIdioma();
			}
		});

		car.botEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				comienzo();
			}
		});
	}

	public static ApplicationContext contexto;

	public static Connection con;

	public static ApplicationContext getContexto() {
		return ComienzoABM.contexto;
	}

	public static void inicializa() throws Exception {

		if (logger != null) {
			logger.info("user.dir=" + System.getProperty("user.dir"));
			logger.info("user.home=" + System.getProperty("user.home"));
			logger.info("user.name=" + System.getProperty("user.name"));
			logger.info("os.name=" + System.getProperty("os.name"));
		}

		Thread.yield();

		Recursos.cambiaLocale(Configuracion.getUltimoIdioma());
		//KalosApp.cambiaKalosLookAndFeel(Configuracion.getUltimoLf());

		//idioma (para JFreeReport)
		Locale.setDefault(new Locale(Configuracion.getUltimoIdioma()));

//		if (logger != null)
//			logger.info("número de serie=" + NumeroDeSerie.generaNumeroDeSerieUnico());
		Thread.yield();

		//ayuda
		String idioma = Configuracion.getUltimoIdioma();
		if (logger != null)
			logger.info("idioma=" + idioma);

		/**
		 * puebla el map configfonts
		 * con los contenidos del archivo KALOS.INI
		 * crea las dos fonts de Unicode
		 */

		//carga de módulos cacheables
		//		CacheDeModulos.getModulo(CacheDeModulos.TODO_TIPOS_SUSTANTIVO);
		//		CacheDeModulos.getModulo(CacheDeModulos.TODO_TIPOS_VERBO);
		//		CacheDeModulos.getModulo(CacheDeModulos.TODOS_VERBOS);
		//		CacheDeModulos.getModulo(CacheDeModulos.TODOS_VERBOS_COMPUESTOS);
		//		CacheDeModulos.getModulo(CacheDeModulos.TODOS_SUSTANTIVOS);
		//		CacheDeModulos.getModulo(CacheDeModulos.TODOS_ADVERBIOS);

	}

}