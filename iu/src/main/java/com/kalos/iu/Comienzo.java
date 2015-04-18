// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.iu;

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

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kalos.analisismorfologico.negocio.AMAdjetivos;
import com.kalos.analisismorfologico.negocio.AMAdverbios;
import com.kalos.analisismorfologico.negocio.AMConjunciones;
import com.kalos.analisismorfologico.negocio.AMInterjecciones;
import com.kalos.analisismorfologico.negocio.AMNominal;
import com.kalos.analisismorfologico.negocio.AMParticipios;
import com.kalos.analisismorfologico.negocio.AMParticulas;
import com.kalos.analisismorfologico.negocio.AMPreposiciones;
import com.kalos.analisismorfologico.negocio.AMSustantivos;
import com.kalos.analisismorfologico.negocio.AMUtil;
import com.kalos.analisismorfologico.negocio.AMVerbal;
import com.kalos.analisismorfologico.negocio.ExtractorPrefijos;
import com.kalos.datos.gerentes.GerenteSeguridad;
import com.kalos.datos.gerentes.GerenteVerbalizadorParticipios;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.datos.gerentes.GerenteVerbosCompuestos;
import com.kalos.datos.modulos.FuenteDeDatosKalos;
import com.kalos.flexion.declinacion.Participios;
import com.kalos.iu.analisismorfologico.PanelAM;
import com.kalos.iu.config.IuConfig;
import com.kalos.iu.diccionario.PanelDiccionario;
import com.kalos.iu.flexion.PanelFlexion;
import com.kalos.iu.registro.VentanaRegistro;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;
import com.kalos.visual.GerenteDeApariencias;

// Referenced classes of package kalos.iu:
//            A, B, C, E

public class Comienzo {

    static class ListenerComenzar implements ActionListener {

        public void actionPerformed(ActionEvent actionevent) {
            JFrame jframe = new JFrame();
            Menues c = (Menues) Comienzo.getApplicationContext().getBean("menues");
            c.inicializa(jframe);
            jframe.setTitle("KALÓS " + Configuracion.getVersionNumero());
            jframe.setLayout(new BorderLayout());
            Comienzo.panel = new JPanel();
            Comienzo.panel.setLayout(new BorderLayout());
            JTabbedPane jtabbedpane = new JTabbedPane();
            Comienzo.panel.add(Comienzo.getPanelProgreso(), "South");
            Comienzo.panel.add(jtabbedpane, "Center");
            jtabbedpane.add(Recursos.getCadena("diccionario"), Comienzo.getPanelDiccionario());
            jtabbedpane.add(Recursos.getCadena("analisis_morfologico"), Comienzo.getPanelAM());
            jtabbedpane.add(Recursos.getCadena("flexion"), Comienzo.getPanelFlexion());
            jframe.add(Comienzo.panel);
            jframe.setSize(1084, 670);
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
        logger.info("el dir de ejecución es " + new File(".").getAbsolutePath() );
        logger.info("o bien" +  new File(Comienzo.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent().toString() );
        //logger.info("el classloader=" + Comienzo.class.getClassLoader().getResource("db.conf").getPath());
        Recursos.cambiaLocale(Configuracion.getUltimoIdioma());
        GerenteDeApariencias.poneLYFPorDefecto();
        logger.info("********************** INICIANDO *********************************");
        logger.info("user.dir=" + System.getProperty("user.dir"));
        logger.info("user.home=" + System.getProperty("user.home"));
        logger.info("user.name=" + System.getProperty("user.name"));
        logger.info("os.version=" + System.getProperty("os.version"));
        logger.info("font=" + Configuracion.getFont());
        logger.info("versión de Kalós=" + Configuracion.getVersionNumero());
        caratula = new Caratula();
        idiomaEtiquetas();
        idiomaContenidos();
        manejaTeclado();
        combosIdiomaYTeclados();
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.add(caratula);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        cargaBeans();
        caratula.addListenerToBotonComenzar(new ListenerComenzar());
    }

    private static void cargaBeans() {
        caratula.habilitacionBotonComenzar(false);
        caratula.cmbTeclados.setEnabled(false);
        caratula.comboIdiomaEtiquetas.setEnabled(false);
        caratula.comboItiomaContenidos.setEnabled(false);
        new Thread() {

            public void run() {
                caratula.setMensajeProgreso(Recursos.getCadena("progreso.creando_contexto"));
                logger.info("creando el applicationContext");
                applicationContext = Comienzo.creaContextoClasspath();
                logger.info("applicationContext creado");
                caratula.setMensajeProgreso(Recursos.getCadena("progreso.creando_controles_visuales"));
                
                
                panelAM = (PanelAM) applicationContext.getBean("panelResultadosAM");
                panelDiccionario = (PanelDiccionario) applicationContext.getBean("panelResultadosDiccionario");               
                panelAM.setApplicationContext(applicationContext);
                panelProgreso = (PanelProgreso) applicationContext.getBean("panelProgreso");
                panelFlexion = (PanelFlexion) applicationContext.getBean("panelTablaFlexion");
                ventanaRegistro = (VentanaRegistro) applicationContext.getBean("ventanaRegistro");
                panelFlexion.setApplicationContext(applicationContext);
                caratula.setMensajeProgreso(Recursos.getCadena("progreso.creando_control_eventos"));
                new Controlador(panelDiccionario, Comienzo.getPanelFlexion());
                caratula.habilitacionBotonComenzar(true);
                caratula.cmbTeclados.setEnabled(true);
                caratula.comboIdiomaEtiquetas.setEnabled(true);
                caratula.comboItiomaContenidos.setEnabled(true);
                caratula.setMensajeProgreso("");
                Comienzo.obtieneNombre();
            }

        }.start();
//        caratula.repaint();
    }

    private static void obtieneNombre() {
        GerenteSeguridad gerenteSeguridad = (GerenteSeguridad) applicationContext.getBean("gerenteSeguridad");
        Configuracion.setNombre(gerenteSeguridad.getNombre());
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

    private static void reescribeIdiomaTeclados() {
        Configuracion.setUltimoTeclado((String) caratula.cmbTeclados.getSelectedItem());
        Configuracion.reescribeIni();
    }

    private static void combosIdiomaYTeclados() {
        caratula.comboIdiomaEtiquetas.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent itemevent) {
                if (itemevent.getStateChange() != 1) {
                    return;
                } else {
                    reescribeIdiomaEtiquetas();
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
                    reescribeIdiomaTeclados();
                    return;
                }
            }

        });
    }

    private static void reescribeIdiomaEtiquetas() {
        switch (caratula.comboIdiomaEtiquetas.getSelectedIndex()) {
            case 0: // '\0'
                com.kalos.recursos.Configuracion.setUltimoIdioma("en");
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
        caratula.setTextoBotonComenzar(Recursos.getCadena("comenzar"));
//        caratula.repaint();
//        caratula.revalidate();
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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(IuConfig.class);
        context.refresh();
        logger.info("refrescando el applicationContext");
        return context;
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



    static PanelProgreso getPanelProgreso() {
        return Comienzo.panelProgreso;
    }

    static JFrame getFrame() {
        return Comienzo.frame;
    }

    static VentanaRegistro getVentanaRegistro() {
        return Comienzo.ventanaRegistro;
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
    public static JPanel panel;

}
