// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.// Jad home page: http://www.kpdus.com/jad.html// Decompiler options: packimports(3) package com.kalos.iu.flexion;import java.awt.BorderLayout;import java.awt.event.ComponentAdapter;import java.awt.event.ComponentEvent;import java.beans.PropertyChangeEvent;import java.beans.PropertyChangeListener;import java.util.HashMap;import java.util.Map;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.JTabbedPane;import javax.swing.JTable;import javax.swing.SwingUtilities;import javax.swing.table.DefaultTableModel;import com.kalos.beans.EntradaDiccionario;import com.kalos.enumeraciones.Reportes;import com.kalos.flexion.CreadorTableModelsGrilla;import com.kalos.flexion.CreadorTableModelsReporte;import com.kalos.iu.EscuchaSolapa;import com.kalos.iu.PanelPrincipal;import com.kalos.iu.PanelProgreso;import com.kalos.iu.jasper.UtilidadJasper;import com.kalos.iu.tareas.TareaCuadro;import com.kalos.iu.tareas.TareaLeyenda;import com.kalos.iu.tareas.TareaOcultaProgreso;import com.kalos.recursos.Configuracion;import com.kalos.recursos.Recursos;import com.kalos.visual.controles.deslizador.Deslizador;import com.kalos.visual.controles.util.UtilTipografias;import com.kalos.visual.controles.ventanas.DialogErrores;import com.kalos.visual.modelos.DictionaryPM;import org.apache.log4j.Logger;import org.springframework.beans.BeansException;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.ApplicationContext;import org.springframework.context.ApplicationContextAware;// Referenced classes of package kalos.iu.F://            C, Bimport foxtrot.Worker;public class PanelFlexion extends JPanel implements ApplicationContextAware, EscuchaSolapa {        Logger logger = Logger.getLogger(getClass().getName());	public PanelFlexion() {		tab = new JTabbedPane();		panelJasper = new JPanel();		panelJasper.setLayout(new BorderLayout());		panelTabla = new JPanel();		panelFlexionNorte = new PanelFlexionNorte();		cacheTablas = new HashMap<Reportes, DefaultTableModel>();		cacheJasper = new HashMap<Reportes, DefaultTableModel>();		ejecutandose = false;		setLayout(new BorderLayout());		add(panelFlexionNorte, BorderLayout.NORTH);		add(tab, BorderLayout.CENTER);		panelTabla.setLayout(new BorderLayout());		tab.addTab(Recursos.getCadena("cuadro"), panelTabla);		tab.addTab(Recursos.getCadena("vista_preliminar_impresion"), panelJasper);		tabla = new JTable();		tabla.setFont(Configuracion.getFont());		panelFlexionNorte.deslizador.addPropertyChangeListener(new PropertyChangeListener() {			public void propertyChange(PropertyChangeEvent propertychangeevent) {				Deslizador a = (Deslizador) propertychangeevent.getSource();				cambiaTamañoFont(a.getValor());			}		});		addComponentListener(new ComponentAdapter() {			public void componentShown(ComponentEvent componentevent) {				proceder();			}		});	}	private void proceder() {		if (!ejecutandose) {			tabla.setSelectionMode(0);			repaint();			new Thread() {				public void run() {					panProgreso = (PanelProgreso) applicationContext.getBean("panelProgreso");					creadorTMGrilla = (CreadorTableModelsGrilla) applicationContext.getBean("creadorTableModelsGrilla");					creadorTMReporte = (CreadorTableModelsReporte) applicationContext.getBean("creadorTableModelsReporte");					paramReporte = (ParametrosReporte) applicationContext.getBean("parametrosReporte");					utilJasper = (UtilidadJasper) applicationContext.getBean("utilidadJasper");					panelFlexionNorte.habilitarBotones();				}			}.start();			ejecutandose = true;		}	}	public void creaTableModel(String id, Reportes tipoReporte) {		try {			logger.info("creando tableModel de flexión para id=" +  id + " tipo de reporte=" + tipoReporte);			TareaCuadro tareaCuadro = new TareaCuadro(id, tabla, tipoReporte, paramReporte, creadorTMGrilla, creadorTMReporte, cacheTablas, cacheJasper, panelTabla, panelJasper, utilJasper);			Worker.post(new TareaLeyenda(panProgreso, "creando_cuadros"));			Worker.post(tareaCuadro);			Worker.post(new TareaOcultaProgreso(panProgreso));			panelFlexionNorte.getCrear().setEnabled(true);			panelFlexionNorte.getCrearMas().setEnabled(true);			panelFlexionNorte.getTiposReporte().setEnabled(true);			panelFlexionNorte.getMasTipos().setEnabled(true);		} catch (Exception exception) {			logger.error("error en la generación de cuadros", exception);			panProgreso.barra.setIndeterminate(false);			panProgreso.etiqueta.setVisible(false);			panProgreso.barra.setVisible(false);			panelFlexionNorte.getCrear().setEnabled(true);			panelFlexionNorte.getCrearMas().setEnabled(true);			panelFlexionNorte.getTiposReporte().setEnabled(true);			panelFlexionNorte.getMasTipos().setEnabled(true);			JFrame jframe = (JFrame) SwingUtilities.getRoot(this);			DialogErrores d = new DialogErrores(jframe, Recursos.getCadena("error"), Recursos.getCadena("error_en_am"),					true, exception);			d.setLocationRelativeTo(null);			d.setVisible(true);		}	}	public void setCreadorTableModelsGrilla(CreadorTableModelsGrilla j) {		creadorTMGrilla = j;	}	public PanelFlexionNorte getPanelEleccionTipoReporte() {		return panelFlexionNorte;	}	public void setCreadorTableModelsReporte(CreadorTableModelsReporte f) {		creadorTMReporte = f;	}	public void setParametrosReporte(ParametrosReporte b) {		paramReporte = b;	}	public void setUtilidadJasper(UtilidadJasper b) {		utilJasper = b;	}	public void cambiaTamañoFont(float f) {		UtilTipografias.cambiaTamañoEnTabla(tabla, f);		repaint();		revalidate();	}	public void setPanelProgreso(PanelProgreso b) {		panProgreso = b;	}	public void setApplicationContext(ApplicationContext applicationcontext) throws BeansException {		applicationContext = applicationcontext;	}	    public void miSolapaSeleccionada(){      panelFlexionNorte.pueblaSegunEntrada(this.panelPrincipal.getEntradaDiccionario());    }	        public void setPanelPrincipal(PanelPrincipal panelPrincipal){        this.panelPrincipal = panelPrincipal;    }    	private CreadorTableModelsGrilla creadorTMGrilla;	private CreadorTableModelsReporte creadorTMReporte;	private ParametrosReporte paramReporte;	private JTabbedPane tab;	private PanelFlexionNorte panelFlexionNorte;	private JPanel panelTabla;	private JPanel panelJasper;	private UtilidadJasper utilJasper;	private JTable tabla;	private PanelProgreso panProgreso;	private ApplicationContext applicationContext;	private Map<Reportes, DefaultTableModel> cacheTablas;	private Map<Reportes, DefaultTableModel> cacheJasper;	private boolean ejecutandose;	private PanelPrincipal panelPrincipal;}