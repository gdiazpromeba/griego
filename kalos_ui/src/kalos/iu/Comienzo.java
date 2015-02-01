// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.iu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import kalos.analisismorfologico.negocio.AMAdjetivos;
import kalos.analisismorfologico.negocio.AMAdverbios;
import kalos.analisismorfologico.negocio.AMConjunciones;
import kalos.analisismorfologico.negocio.AMInterjecciones;
import kalos.analisismorfologico.negocio.AMNominal;
import kalos.analisismorfologico.negocio.AMParticipios;
import kalos.analisismorfologico.negocio.AMParticulas;
import kalos.analisismorfologico.negocio.AMPreposiciones;
import kalos.analisismorfologico.negocio.AMSustantivos;
import kalos.analisismorfologico.negocio.AMUtil;
import kalos.analisismorfologico.negocio.AMVerbal;
import kalos.analisismorfologico.negocio.ExtractorPrefijos;
import kalos.datos.gerentes.GerenteSeguridad;
import kalos.datos.gerentes.GerenteVerbalizadorParticipios;
import kalos.datos.gerentes.GerenteVerbos;
import kalos.datos.gerentes.GerenteVerbosCompuestos;
import kalos.flexion.declinacion.Participios;
import kalos.iu.analisismorfologico.PanelAM;
import kalos.iu.diccionario.PanelDiccionario;
import kalos.iu.flexion.PanelFlexion;
import kalos.iu.registro.VentanaRegistro;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;
import kalos.visual.GerenteDeApariencias;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

// Referenced classes of package kalos.iu:
//            A, B, C, E

public class Comienzo {
	static class ListenerComenzar implements ActionListener {

		public void actionPerformed(ActionEvent actionevent) {
			JFrame jframe = new JFrame();
			Menues c = (Menues) Comienzo.getApplicationContext().getBean("menues");
			c.inicializa(jframe);
			jframe.setTitle((new StringBuilder()).append("KALÓS ")
					.append(Configuracion.getVersionNumero()).toString());
			jframe.setLayout(new BorderLayout());
			kalos.iu.Comienzo.I = new JPanel();
			kalos.iu.Comienzo.I.setLayout(new BorderLayout());
			JTabbedPane jtabbedpane = new JTabbedPane();
			kalos.iu.Comienzo.I.add(kalos.iu.Comienzo.getPanelProgreso(), "South");
			kalos.iu.Comienzo.I.add(jtabbedpane, "Center");
			jtabbedpane.add(Recursos.getCadena("diccionario"),
					kalos.iu.Comienzo.getPanelDiccionario());
			jtabbedpane.add(Recursos.getCadena("analisis_morfologico"),
					kalos.iu.Comienzo.getPanelAM());
			jtabbedpane.add(Recursos.getCadena("flexion"), kalos.iu.Comienzo.getPanelFlexion());
			jframe.add(kalos.iu.Comienzo.I);
			jframe.setSize(777, 550);
			jframe.setLocationRelativeTo(null);
			jframe.setVisible(true);
			Comienzo.getFrame().dispose();
			jframe.addWindowListener(new WindowAdapter() {

				public void windowClosing(WindowEvent windowevent) {
					System.exit(0);
				}

			});

		}

	}

	public static void main(String args[]) {
		DOMConfigurator.configure("log4j.xml");
		Recursos.cambiaLocale(Configuracion.getUltimoIdioma());
		GerenteDeApariencias.poneLYFPorDefecto();
		logger.info("********************** INICIANDO *********************************");
		logger.info((new StringBuilder()).append("user.dir=")
				.append(System.getProperty("user.dir")).toString());
		logger.info((new StringBuilder()).append("user.home=")
				.append(System.getProperty("user.home")).toString());
		logger.info((new StringBuilder()).append("user.name=")
				.append(System.getProperty("user.name")).toString());
		logger.info((new StringBuilder()).append("os.name=").append(System.getProperty("os.name"))
				.toString());
		logger.info((new StringBuilder()).append("os.version=")
				.append(System.getProperty("os.version")).toString());
		logger.info((new StringBuilder()).append("font=").append(Configuracion.getFont())
				.toString());
		logger.info((new StringBuilder()).append("versi\363n de Kal\363s=")
				.append(Configuracion.getVersionNumero()).toString());
		caratula = new Caratula();
		idiomaEtiquetas();
		idiomaContenidos();
		manejaTeclado();
		C();
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setContentPane(caratula);
		frame.setSize(533, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		cargaBeans();
		caratula.botComenzar.addActionListener(new ListenerComenzar());
	}

	private static void cargaBeans() {
		caratula.botComenzar.setEnabled(false);
		caratula.cmbTeclados.setEnabled(false);
		caratula.comboIdiomaEtiquetas.setEnabled(false);
		caratula.comboItiomaContenidos.setEnabled(false);
		new Thread() {

			public void run() {
				caratula.setMensajeProgreso(Recursos.getCadena("progreso.creando_contexto"));
				if (Configuracion.isDebug())
					applicationContext = Comienzo.creaContexto();
				else
					applicationContext = Comienzo.creaContextoClasspath();

				caratula.setMensajeProgreso(Recursos
						.getCadena("progreso.creando_controles_visuales"));
				panelDiccionario = (PanelDiccionario) applicationContext
						.getBean("panelResultadosDiccionario");
				panelAM = (PanelAM) applicationContext.getBean("panelResultadosAM");
				// panelAM.setApplicationContext(applicationContext);
				panelProgreso = (PanelProgreso) applicationContext.getBean("panelProgreso");
				panelFlexion = (PanelFlexion) applicationContext.getBean("panelTablaFlexion");
				ventanaRegistro =(VentanaRegistro) applicationContext.getBean("ventanaRegistro");
				// panelFlexion.setApplicationContext(applicationContext);
				new Thread() {

					public void run() {
						AMParticipios d = (AMParticipios) Comienzo.getApplicationContext().getBean(
								"amParticipios");
						d.setParticipios((Participios) Comienzo.getApplicationContext().getBean(
								"participios"));
						d.setAmUtil((AMUtil) Comienzo.getApplicationContext().getBean("amUtil"));
						d.setAmNominal((AMNominal) Comienzo.getApplicationContext().getBean(
								"amNominal"));
						d.setAmVerbal((AMVerbal) Comienzo.getApplicationContext().getBean(
								"amVerbal"));
						d.setExtractorPrefijos((ExtractorPrefijos) Comienzo.getApplicationContext()
								.getBean("extractorPrefijos"));
						d.setGerenteVerbos((GerenteVerbos) Comienzo.getApplicationContext()
								.getBean("gerenteVerbos"));
						d.setGerenteVerbosCompuestos((GerenteVerbosCompuestos) Comienzo
								.getApplicationContext().getBean("gerenteVerbosCompuestos"));
						d.setGerenteVerbalizadorParticipios((GerenteVerbalizadorParticipios) Comienzo
								.getApplicationContext().getBean("gerenteVerbalizadorParticipios"));
						AMSustantivos f = (AMSustantivos) Comienzo.getApplicationContext().getBean(
								"amSustantivos");
						f.setApplicationContext(Comienzo.getApplicationContext());
						AMAdjetivos l = (AMAdjetivos) Comienzo.getApplicationContext().getBean(
								"amAdjetivos");
						l.setApplicationContext(Comienzo.getApplicationContext());
						AMParticulas s = (AMParticulas) Comienzo.getApplicationContext().getBean(
								"amParticulas");
						s.setApplicationContext(Comienzo.getApplicationContext());
						AMConjunciones h = (AMConjunciones) Comienzo.getApplicationContext()
								.getBean("amConjunciones");
						h.setApplicationContext(Comienzo.getApplicationContext());
						AMPreposiciones e = (AMPreposiciones) Comienzo.getApplicationContext()
								.getBean("amPreposiciones");
						e.setApplicationContext(Comienzo.getApplicationContext());
						AMAdverbios o = (AMAdverbios) Comienzo.getApplicationContext().getBean(
								"amAdverbios");
						o.setApplicationContext(Comienzo.getApplicationContext());
						AMInterjecciones a = (AMInterjecciones) Comienzo.getApplicationContext()
								.getBean("amInterjecciones");
						a.setApplicationContext(Comienzo.getApplicationContext());
					}

				}.start();
				caratula.setMensajeProgreso(Recursos.getCadena("progreso.creando_control_eventos"));
				new Controlador(panelDiccionario, kalos.iu.Comienzo.getPanelFlexion());
				caratula.botComenzar.setEnabled(true);
				caratula.cmbTeclados.setEnabled(true);
				caratula.comboIdiomaEtiquetas.setEnabled(true);
				caratula.comboItiomaContenidos.setEnabled(true);
				caratula.setMensajeProgreso("");
				Comienzo.T();
			}

		}.start();
		caratula.repaint();
	}

	private static void I() {
		GerenteSeguridad ka = (GerenteSeguridad) applicationContext.getBean("gerenteSeguridad");
		Configuracion.setNombre(ka.getNombre());
	}

	private static void idiomaEtiquetas() {
		if (Configuracion.getUltimoIdioma().equals("en"))
			caratula.comboIdiomaEtiquetas.setSelectedIndex(0);
		else if (Configuracion.getUltimoIdioma().equals("es"))
			caratula.comboIdiomaEtiquetas.setSelectedIndex(1);
		else if (Configuracion.getUltimoIdioma().equals("fr"))
			caratula.comboIdiomaEtiquetas.setSelectedIndex(2);
		else if (Configuracion.getUltimoIdioma().equals("pr"))
			caratula.comboIdiomaEtiquetas.setSelectedIndex(3);
	}

	private static void idiomaContenidos() {
		if (Configuracion.getIdiomaSignificados().equals("en"))
			caratula.comboItiomaContenidos.setSelectedIndex(0);
		else if (Configuracion.getIdiomaSignificados().equals("es"))
			caratula.comboItiomaContenidos.setSelectedIndex(1);
		else if (Configuracion.getIdiomaSignificados().equals("fr"))
			caratula.comboItiomaContenidos.setSelectedIndex(2);
	}

	private static void manejaTeclado() {
		caratula.cmbTeclados.setSelectedItem(Configuracion.getUltimoTeclado());
	}

	private static void B() {
		Configuracion.setUltimoTeclado((String) caratula.cmbTeclados.getSelectedItem());
		Configuracion.reescribeIni();
	}

	private static void C() {
		caratula.comboIdiomaEtiquetas.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent itemevent) {
				if (itemevent.getStateChange() != 1) {
					return;
				} else {
					reescribeIdiomaEtiquetas();
					;
					return;
				}
			}

		});
		caratula.comboItiomaContenidos.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent itemevent) {
				if (itemevent.getStateChange() != 1) {
					return;
				} else {
					reescribeIdiomaSignificados();
					return;
				}
			}

		});
		caratula.cmbTeclados.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent itemevent) {
				if (itemevent.getStateChange() != 1) {
					return;
				} else {
					kalos.iu.Comienzo.manejaTeclado();
					return;
				}
			}

		});
	}

	private static void reescribeIdiomaEtiquetas() {
		switch (caratula.comboIdiomaEtiquetas.getSelectedIndex()) {
		case 0: // '\0'
			Configuracion.setUltimoIdioma("en");
			Configuracion.reescribeIni();
			break;

		case 1: // '\001'
			Configuracion.setUltimoIdioma("es");
			Configuracion.reescribeIni();
			break;

		case 2: // '\002'
			Configuracion.setUltimoIdioma("fr");
			Configuracion.reescribeIni();
			break;

		case 3: // '\003'
			Configuracion.setUltimoIdioma("pr");
			Configuracion.reescribeIni();
			break;
		}
		Recursos.cambiaLocale(Configuracion.getUltimoIdioma());
		caratula.botComenzar.setText(Recursos.getCadena("comenzar"));
		caratula.repaint();
		caratula.revalidate();
		cargaBeans();
	}

	private static void reescribeIdiomaSignificados() {
		switch (caratula.comboItiomaContenidos.getSelectedIndex()) {
		case 0: // '\0'
			Configuracion.setIdiomaSignificados("en");
			Configuracion.reescribeIni();
			break;

		case 1: // '\001'
			Configuracion.setIdiomaSignificados("es");
			Configuracion.reescribeIni();
			break;

		case 2: // '\002'
			Configuracion.setIdiomaSignificados("fr");
			Configuracion.reescribeIni();
			break;
		}
		cargaBeans();
	}

	public static ApplicationContext creaContextoClasspath() {
		DefaultListableBeanFactory defaultlistablebeanfactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xmlbeandefinitionreader = new XmlBeanDefinitionReader(
				defaultlistablebeanfactory);
		ClassPathResource classpathresource = new ClassPathResource("daos.xml");
		xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
		classpathresource = new ClassPathResource("flexion.xml");
		xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
		classpathresource = new ClassPathResource("gerentes-datos.xml");
		xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
		classpathresource = new ClassPathResource("analisisMorfologico.xml");
		xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
		classpathresource = new ClassPathResource("iu.xml");
		xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
		GenericApplicationContext genericapplicationcontext = new GenericApplicationContext(
				defaultlistablebeanfactory);
		return genericapplicationcontext;
	}

	public static ApplicationContext creaContexto() {
		logger.info((new StringBuilder())
				.append("creando nuevo contexto, con la configuraci\363n de significado =")
				.append(Configuracion.getIdiomaSignificados()).toString());
		DefaultListableBeanFactory defaultlistablebeanfactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xmlbeandefinitionreader = new XmlBeanDefinitionReader(
				defaultlistablebeanfactory);
		FileSystemResource filesystemresource = new FileSystemResource((new StringBuilder())
				.append(System.getProperty("user.dir")).append(File.separator).append("daos.xml")
				.toString());
		xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
		filesystemresource = new FileSystemResource((new StringBuilder())
				.append(System.getProperty("user.dir")).append(File.separator)
				.append("gerentes-datos.xml").toString());
		xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
		filesystemresource = new FileSystemResource((new StringBuilder())
				.append(System.getProperty("user.dir")).append(File.separator)
				.append("analisisMorfologico.xml").toString());
		xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
		filesystemresource = new FileSystemResource((new StringBuilder())
				.append(System.getProperty("user.dir")).append(File.separator)
				.append("flexion.xml").toString());
		xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
		filesystemresource = new FileSystemResource((new StringBuilder())
				.append(System.getProperty("user.dir")).append(File.separator).append("iu.xml")
				.toString());
		xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
		GenericApplicationContext genericapplicationcontext = new GenericApplicationContext(
				defaultlistablebeanfactory);
		return genericapplicationcontext;
	}

	static Caratula getCaratula() {
		return caratula;
	}

	static void setApplicationContext(ApplicationContext applicationcontext) {
		Comienzo.applicationContext = applicationcontext;
	}

	static void setPanelDiccionario(PanelDiccionario a) {
		Comienzo.panelDiccionario = a;
	}

	static ApplicationContext getApplicationContext() {
		return Comienzo.applicationContext;
	}

	static void setPanelAM(PanelAM a) {
		Comienzo.panelAM = a;
	}

	static PanelAM getPanelAM() {
		return panelAM;
	}

	static void setPanelProgreso(PanelProgreso b) {
		Comienzo.panelProgreso = b;
	}

	static void setPanelFlexion(PanelFlexion a) {
		panelFlexion = a;
	}

	static PanelFlexion getPanelFlexion() {
		return panelFlexion;
	}

	static PanelDiccionario getPanelDiccionario() {
		return panelDiccionario;
	}

	static void T() {
		I();
	}

	static PanelProgreso getPanelProgreso() {
		return Comienzo.panelProgreso;
	}

	static JFrame getFrame() {
		return Comienzo.frame;
	}
	
	static VentanaRegistro getVentanaRegistro(){
		return Comienzo.ventanaRegistro;
	}

	static void E() {
		reescribeIdiomaEtiquetas();
	}

	static void G() {
		reescribeIdiomaSignificados();
	}

	static void A() {
		B();
	}

	private static Logger logger = Logger.getLogger(Comienzo.class);
	private static PanelDiccionario panelDiccionario;
	private static PanelAM panelAM;
	private static PanelProgreso panelProgreso;
	private static PanelFlexion panelFlexion;
	private static Caratula caratula;
	private static JFrame frame;
	private static ApplicationContext applicationContext;
	private static VentanaRegistro ventanaRegistro;
	public static JPanel I;

}
