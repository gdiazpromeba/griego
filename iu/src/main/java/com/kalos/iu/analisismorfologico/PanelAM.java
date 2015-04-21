
package com.kalos.iu.analisismorfologico;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import com.kalos.analisismorfologico.negocio.AMAdjetivos;
import com.kalos.analisismorfologico.negocio.AMAdverbios;
import com.kalos.analisismorfologico.negocio.AMConjunciones;
import com.kalos.analisismorfologico.negocio.AMInfinitivos;
import com.kalos.analisismorfologico.negocio.AMInterjecciones;
import com.kalos.analisismorfologico.negocio.AMParticipios;
import com.kalos.analisismorfologico.negocio.AMParticulas;
import com.kalos.analisismorfologico.negocio.AMPreposiciones;
import com.kalos.analisismorfologico.negocio.AMSustantivos;
import com.kalos.analisismorfologico.negocio.AMUtil;
import com.kalos.analisismorfologico.negocio.AMVerbos;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.datos.gerentes.GerenteSignificados;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Ignorancia;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.flexion.UtilidadesTM;
import com.kalos.iu.PanelPrincipal;
import com.kalos.iu.PanelProgreso;
import com.kalos.iu.registro.VentanaMolesta;
import com.kalos.iu.tareas.TareaAM;
import com.kalos.iu.tareas.TareaDibujoTablemodel;
import com.kalos.iu.tareas.TareaHabilitaComponentes;
import com.kalos.iu.tareas.TareaLeyenda;
import com.kalos.iu.tareas.TareaOcultaProgreso;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.AnalisisAcento;
import com.kalos.operaciones.ExcepcionCaracterNoEncontrado;
import com.kalos.operaciones.OpPalabras;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;
import com.kalos.visual.controles.combos.ComboEnumeracion;
import com.kalos.visual.controles.deslizador.Deslizador;
import com.kalos.visual.controles.listas.filtroenumeraciones.FiltroEnumeraciones;
import com.kalos.visual.controles.textos.alternable.TextoAlternable;
import com.kalos.visual.controles.util.TipografiaCambiable;
import com.kalos.visual.controles.util.UtilTipografias;
import com.kalos.visual.controles.ventanas.DialogErrores;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import foxtrot.Task;
import foxtrot.Worker;

public class PanelAM extends JPanel implements ApplicationContextAware, TipografiaCambiable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private TextoAlternable textoEntrada = new TextoAlternable(false);
    private ComboEnumeracion ignorar = new ComboEnumeracion(Ignorancia.TodosLosDiacriticos);
    private JTable tabla = new JTable();
    private AMVerbos amVerbos;
    private AMInfinitivos amInfinitivos;
    private AMSustantivos amSustantivos;
    private AMAdjetivos amAdjetivos;
    private AMParticipios amParticipios;
    private AMParticulas amParticulas;
    private AMConjunciones amConjunciones;
    private AMPreposiciones amPreposiciones;
    private AMAdverbios amAdverbios;
    private AMInterjecciones amInterjecciones;
    private AMUtil amUtil;
    private JButton buscar = new JButton(Recursos.getCadena("buscar"));
    private GerenteSignificados gerenteSignificados;
    private UtilidadesTM utilidadesTM;
    private PanelProgreso panelProgreso = new PanelProgreso();
    private VentanaMolesta ventanaMolesta;
    private Deslizador deslizador = new Deslizador();
    private ApplicationContext contexto;
    private JTabbedPane tab = new JTabbedPane();
    private DetallelAM detalle = new DetallelAM();
    private List<ResultadoUniversal> resultados;
    private PanelPrincipal panelPrincipal;

    private FiltroEnumeraciones tipoPalabra = new FiltroEnumeraciones(TipoPalabra.values());
    
    Logger log=Logger.getLogger(this.getClass().getName());

    public PanelAM() throws Exception {
        buscar.setText(Recursos.getCadena("cargando"));
        buscar.setEnabled(false);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setFont(Configuracion.getFont());
        tab.add(Recursos.getCadena("lista"), new JScrollPane(tabla));
        tab.add(Recursos.getCadena("detalles"), detalle);
        tabla.getSelectionModel().addListSelectionListener(new EscuchaSeleccionTabla());
        ignorar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                tabla.setColumnModel(new DefaultTableColumnModel());
                tabla.repaint();
            }
        });
        textoEntrada.getJTextField().addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent ev) {
                String cadenaCompleta = textoEntrada.getCadenaCompleta();
                if (cadenaCompleta == null)
                    return;
                cadenaCompleta = OpPalabras.sigmaFinal(cadenaCompleta);
                textoEntrada.setCadenaCompleta(cadenaCompleta);
            }
        });
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (tabla.getSelectionModel().getValueIsAdjusting()) return;
                if (tabla.getSelectedRow() > -1) {
                    PanelAM panelAM = panelPrincipal.getPanelAM();
                    ResultadoUniversal reu = panelAM.getResultadoAt(tabla.getSelectedRow());
                    panelPrincipal.setResultadoUniversal(reu);
                }

            }
        });

        buscar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                String cadenaCompleta = textoEntrada.getCadenaCompleta();
                if (StringUtils.isEmpty(cadenaCompleta)) {
                    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor((JButton) ev.getSource()), Recursos
                            .getCadena("no_puede_ser_vacio"));
                    return;
                }
                buscar.setEnabled(false);
                ignorar.setEnabled(false);

                try {
                    proceder();
                } catch (Exception e) {
                    logger.error("error al invocar proceder ", e);
                    JFrame fra = (JFrame) SwingUtilities.getRoot(buscar);

                    DialogErrores dle = new DialogErrores(fra, Recursos.getCadena("error"), Recursos.getCadena("error_en_am"), true, e);
                    dle.setLocationRelativeTo(null);
                    dle.setVisible(true);
                }

            }
        });
        deslizador.setValor(Configuracion.getTamañoAM());
        deslizador.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                Deslizador deslizador = (Deslizador) evt.getSource();
                cambiaTamañoFont(deslizador.getValor());
            }
        });

        // esta carga hay que ponerla en un evento, porque no puede ocurrir
        // hasta que el panel reciba el contexto
        this.addComponentListener(new ComponentAdapter() {

            public void componentShown(ComponentEvent ev) {
                new CargaAnalizadores().start();
            }
        });
        disposicion();
        repaint();
    }

    public void setPanelPrincipal(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * cosas que dejo para cuando el control es mostrado
     * 
     */
    private class CargaAnalizadores extends Thread {

        public void run() {
            panelProgreso = (PanelProgreso) contexto.getBean("panelProgreso");
            ventanaMolesta = (VentanaMolesta) contexto.getBean("ventanaMolesta");
            utilidadesTM = (UtilidadesTM) contexto.getBean("utilidadesTM");
            amUtil = (AMUtil) contexto.getBean("amUtil");
            gerenteSignificados = (GerenteSignificados) contexto.getBean("gerenteSignificados");
            amVerbos = (AMVerbos) contexto.getBean("amVerbos");
            amParticipios = (AMParticipios) contexto.getBean("amParticipios");
            amInfinitivos = (AMInfinitivos) contexto.getBean("amInfinitivos");
            amSustantivos = (AMSustantivos) contexto.getBean("amSustantivos");
            amAdjetivos = (AMAdjetivos) contexto.getBean("amAdjetivos");
            amConjunciones = (AMConjunciones) contexto.getBean("amConjunciones");
            amPreposiciones = (AMPreposiciones) contexto.getBean("amPreposiciones");
            amParticulas = (AMParticulas) contexto.getBean("amParticulas");
            amAdverbios = (AMAdverbios) contexto.getBean("amAdverbios");
            amInterjecciones = (AMInterjecciones) contexto.getBean("amInterjecciones");
            buscar.setText(Recursos.getCadena("buscar"));
            buscar.setEnabled(true);
            // constructed = true;
        }

    }

    public void disposicion() {
        FormLayout layout = new FormLayout(
                "pref, 3dlu, 90dlu, 10dlu, 40dlu, 3dlu, pref, 60dlu, 30dlu, fill:1dlu:grow(1), right:pref, 3dlu, pref", // cols
                "fill:pref ,3dlu, fill:pref ,3dlu, fill:pref ,3dlu, fill:1dlu:grow(1)" // rows
        );

        PanelBuilder builder = new PanelBuilder(layout);

        //         DefaultFormBuilder builder = new DefaultFormBuilder(layout,
        //         new FormDebugPanel());

        builder.setDefaultDialogBorder();

        CellConstraints cc = new CellConstraints();

        // layout.setRowGroups(new int[][] { { 1, 3, 5 } });

        builder.addLabel(Recursos.getCadena("ingrese_forma"), cc.xy(1, 1));
        builder.add(textoEntrada, cc.xyw(3, 1, 3));
        builder.add(buscar, cc.xy(7, 1));
        builder.addLabel(Recursos.getCadena("ignorar"), cc.xy(1, 3));
        builder.add(ignorar, cc.xy(3, 3));
        builder.addLabel(Recursos.getCadena("tipo_de_palabra"), cc.xy(1, 5));
        builder.add(tipoPalabra, cc.xyw(3, 5, 7));
        builder.addLabel(Recursos.getCadena("tamaño_tipografia"), cc.xy(11, 1));
        builder.add(deslizador, cc.xy(13, 1));

        builder.add(tab, cc.xyw(1, 7, 13));

        setLayout(new BorderLayout());
        add(builder.getPanel());

    }

    private void proceder() {
        String cadenaCompleta = textoEntrada.getCadenaCompleta();
        try {
            Ignorancia tipoIgnorancia = (Ignorancia) ignorar.getEnumeracionSeleccionada();
            boolean ignorando = !tipoIgnorancia.equals(Ignorancia.Nada);
            DefaultTableModel dtm = null;
            if (cadenaCompleta != null && !cadenaCompleta.trim().equals("")) {
                logger.info("procediendo al AM con cadena=" + OpPalabras.strCompletoABeta(cadenaCompleta)
                        + " ignorancia=" + tipoIgnorancia);
                if (!validar(cadenaCompleta, tipoIgnorancia)) {
                    buscar.setEnabled(true);
                    ignorar.setEnabled(true);
                    return;
                }
                String[] entradas = null;
                String[] entradasQuePuedenNoTenerAcento = null;
                resultados = new ArrayList<ResultadoUniversal>();
                switch (tipoIgnorancia) {
                    case Nada:
                        entradas = new String[] { cadenaCompleta };
                        entradasQuePuedenNoTenerAcento = entradas;
                        break;
                    case SignosLargaCorta:
                        // String ccAcortada =
                        // OpPalabras.strAbreviaCompleta(cadenaCompleta);
                        ;
                        entradas = OpPalabras.explotaCompletaLargaBreve(cadenaCompleta);
                        entradasQuePuedenNoTenerAcento = entradas;
                        break;
                    case TodosLosDiacriticos:
                        try {
                            String ccNeutralizada = OpPalabras.neutraliza(cadenaCompleta);
                            entradas = OpPalabras.explotaCompleta(ccNeutralizada);
                            entradasQuePuedenNoTenerAcento = OpPalabras.explotaCompleta(ccNeutralizada, true);
                        } catch (ExcepcionCaracterNoEncontrado ex) {
                            logger.error("excepción en AM ", ex);
                        }
                        break;
                }

                int maximoFormas = Configuracion.getMaximoFormasAM();
                logger.info("la forma fue explotada en " + entradas.length + " formas");
                if (entradas.length > maximoFormas) {

                    logger.info("son demasiadas formas y el AM se suspende");
                    String mensaje = Recursos.getCadena("am.maximo_numero_formas_superado");
                    mensaje = mensaje.replace("{1}", Integer.toString(maximoFormas));
                    JOptionPane.showMessageDialog(this, mensaje);
                    Worker.post(new TareaOcultaProgreso(panelProgreso));
                    Worker.post(new TareaRedibujaTodo());
                    buscar.setEnabled(true);
                    ignorar.setEnabled(true);
                    return;
                }

                HashSet<ResultadoUniversal> resVerbos = new HashSet<ResultadoUniversal>();
                HashSet<ResultadoUniversal> resSustantivos = new HashSet<ResultadoUniversal>();
                HashSet<ResultadoUniversal> resInfinitivos = new HashSet<ResultadoUniversal>();
                HashSet<ResultadoUniversal> resParticipios = new HashSet<ResultadoUniversal>();
                HashSet<ResultadoUniversal> resAdjetivos = new HashSet<ResultadoUniversal>();
                HashSet<ResultadoUniversal> resAdverbios = new HashSet<ResultadoUniversal>();
                HashSet<ResultadoUniversal> resInterjecciones = new HashSet<ResultadoUniversal>();
                HashSet<ResultadoUniversal> resConjunciones = new HashSet<ResultadoUniversal>();
                HashSet<ResultadoUniversal> resPreposiciones = new HashSet<ResultadoUniversal>();
                HashSet<ResultadoUniversal> resParticulas = new HashSet<ResultadoUniversal>();

                AACacheable cacheAA = new AACacheable();

                if (Configuracion.getNombre() == null) {
                    ventanaMolesta.muestraMolestia(this);
                }

                List<TipoPalabra> tiposAAnalizar = TipoPalabra.getTiposPalabra(tipoPalabra.getEnumsSeleccionadas());
                if (tiposAAnalizar.contains(TipoPalabra.Verbo)) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_verbos"));
                    Worker.post(new TareaAM(amVerbos, entradas, resVerbos, cacheAA));
                }

                if (tiposAAnalizar.contains(TipoPalabra.Sustantivo)) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_sustantivos"));
                    Worker.post(new TareaAM(amSustantivos, entradas, resSustantivos, cacheAA));
                }

                if (tiposAAnalizar.contains(TipoPalabra.Infinitivo)) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_infinitivos"));
                    Worker.post(new TareaAM(amInfinitivos, entradas, resInfinitivos, cacheAA));
                }

                if (tiposAAnalizar.contains(TipoPalabra.Participio)) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_participios"));
                    Worker.post(new TareaAM(amParticipios, entradas, resParticipios, cacheAA));
                }

                if (tiposAAnalizar.contains(TipoPalabra.Adjetivo)) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_adjetivos"));
                    Worker.post(new TareaAM(amAdjetivos, entradas, resAdjetivos, cacheAA));
                }

                if (tiposAAnalizar.contains(TipoPalabra.Adverbio)) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_adverbios"));
                    Worker.post(new TareaAM(amAdverbios, entradas, resAdjetivos, cacheAA));
                }

                if (tiposAAnalizar.contains(TipoPalabra.Interjeccion)) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_interjecciones"));
                    Worker.post(new TareaAM(amInterjecciones, entradas, resAdjetivos, cacheAA));
                }

                if (tiposAAnalizar.contains(TipoPalabra.Conjuncion)) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_conjunciones"));
                    Worker.post(new TareaAM(amConjunciones, entradasQuePuedenNoTenerAcento, resConjunciones, cacheAA));
                }

                if (tiposAAnalizar.contains(TipoPalabra.Preposicion)) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_preposiciones"));
                    Worker.post(new TareaAM(amPreposiciones, entradasQuePuedenNoTenerAcento, resConjunciones, cacheAA));
                }

                boolean hayParticulas = tiposAAnalizar.contains(TipoPalabra.Articulo)
                        || tiposAAnalizar.contains(TipoPalabra.PronombrePersonal)
                        || tiposAAnalizar.contains(TipoPalabra.PronombreReflexivo)
                        || tiposAAnalizar.contains(TipoPalabra.PronombreRelativo)
                        || tiposAAnalizar.contains(TipoPalabra.PronombreInterrogativo)
                        || tiposAAnalizar.contains(TipoPalabra.PronombreIndefinido);

                if (hayParticulas) {
                    Worker.post(new TareaLeyenda(panelProgreso, "am.analizando_otros"));
                    Worker.post(new TareaAM(amParticulas, entradasQuePuedenNoTenerAcento, resParticulas, cacheAA));
                }

                resultados.addAll(resVerbos);
                resultados.addAll(resSustantivos);
                resultados.addAll(resInfinitivos);
                resultados.addAll(resParticipios);
                resultados.addAll(resAdjetivos);
                resultados.addAll(resAdverbios);
                resultados.addAll(resInterjecciones);
                resultados.addAll(resConjunciones);
                resultados.addAll(resPreposiciones);
                resultados.addAll(resParticulas);

                Collections.sort(resultados, new ComparadorResultados<ResultadoUniversal>());

                gerenteSignificados.pueblaSignificadosResultados(resultados);
                dtm = cargaResultadosEnModelo(resultados, ignorando);
            }
            TareaDibujoTablemodel tdt = new TareaDibujoTablemodel(dtm, tabla, ignorando);
            Worker.post(new TareaHabilitaComponentes(new Component[] { buscar, ignorar }));
            Worker.post(new TareaOcultaProgreso(panelProgreso));
            Worker.post(tdt);
            Worker.post(new TareaRedibujaTodo());
        } catch (Exception e) {
            logger.error("error en el análisis morfológico", e);
            panelProgreso.barra.setIndeterminate(false);
            panelProgreso.barra.setVisible(false);
            panelProgreso.etiqueta.setVisible(false);
            buscar.setEnabled(true);
            ignorar.setEnabled(true);
            JFrame fra = (JFrame) SwingUtilities.getRoot(this);
            DialogErrores dle = new DialogErrores(fra, Recursos.getCadena("error"), Recursos.getCadena("error_en_am"), true, e);
            dle.setLocationRelativeTo(null);
            dle.setVisible(true);
        }

    }

    /**
     * clase interna para escuchar los movimientos de selección en la tabla de
     * resultados, y pasárselos al panel de detalle
     * 
     * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
     * @version $Revision: 1.0 $
     */
    private class EscuchaSeleccionTabla implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent ev) {
            if (ev.getValueIsAdjusting())
                return;
            int indice = tabla.getSelectedRow();
            if (indice == -1)
                return;
            ResultadoUniversal resultado = resultados.get(indice);
            detalle.setResultadoUniversal(resultado);
        }
    }

    /**
     * devuelve un array con una cadena extra agregada al final
     * 
     * @param entradas
     * @param nuevaEntrada
     * @return
     */
    String[] agregaEntrada(String[] entradas, String nuevaEntrada) {
        String[] nuevoArray = new String[entradas.length + 1];
        for (int i = 0; i < entradas.length; i++) {
            nuevoArray[i] = entradas[i];
        }
        nuevoArray[nuevoArray.length - 1] = nuevaEntrada;
        return nuevoArray;
    }

    private class TareaRedibujaTodo extends Task {

        public Object run() {
            JFrame fra = (JFrame) SwingUtilities.getWindowAncestor(panelProgreso);
            if (Configuracion.getNombre() == null) {
                fra.repaint();
            }
            return null;
        }

    }

    private DefaultTableModel cargaResultadosEnModelo(List<ResultadoUniversal> resultados, boolean ignorando) {
        DefaultTableModel dtm = new DefaultTableModel(0, ignorando ? 3 : 2);
        for (ResultadoUniversal resultado : resultados) {
            String hablado = amUtil.accidentesHablados(resultado);
            Vector<Object> fila = new Vector<Object>();
            if (ignorando) {
                fila.add(OpPalabras.strCompletoAUnicode(resultado.getFormaAccidentada()));
            }
            fila.add(hablado);
            fila.add(resultado.getSignificado());
            dtm.addRow(fila);
        }
        if (ignorando) {
            utilidadesTM.reemplazaNombresColumna(dtm, new String[] { "forma_flexionada", "accidentes", "significado" });
        } else {
            utilidadesTM.reemplazaNombresColumna(dtm, new String[] { "accidentes", "significado" });
        }
        return dtm;
    }

    /**
     * Orden de los resultados universales por id del referente primero, luego
     * por tipo de palabra
     * 
     * @author gdiaz
     * 
     */
    private class ComparadorResultados<T extends ResultadoUniversal> implements Comparator<T> {

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Comparator#compare(T, T)
         */
        public int compare(T o1, T o2) {
            T reu1 = (T) o1;
            T reu2 = (T) o2;
            int resultado = reu1.getId().compareTo(reu2.getId());
            if (resultado != 0)
                return resultado;
            return reu1.getTipoPalabra().compareTo(reu2.getTipoPalabra());
        }

    }

    public void cambiaTamañoFont(float tamaño) {
        UtilTipografias.cambiaTamañoEnTabla(tabla, tamaño);
        detalle.cambiaTamañoFont(tamaño);
        Configuracion.setTamañoAM((int) tamaño);
        repaint();
        revalidate();
    }

    /**
     * @return Returns the tabla.
     */
    public JTable getTabla() {
        return tabla;
    }

    /**
     * @param gerenteSignificados
     *            The gerenteSignificados to set.
     */
    public void setGerenteSignificados(GerenteSignificados gerenteSignificados) {
        this.gerenteSignificados = gerenteSignificados;
    }

    /**
     * @param amAdjetivos
     *            The amAdjetivos to set.
     */
    public void setAmAdjetivos(AMAdjetivos amAdjetivos) {
        this.amAdjetivos = amAdjetivos;
    }

    /**
     * @param amInfinitivos
     *            The amInfinitivos to set.
     */
    public void setAmInfinitivos(AMInfinitivos amInfinitivos) {
        this.amInfinitivos = amInfinitivos;
    }

    /**
     * @param amSustantivos
     *            The amSustantivos to set.
     */
    public void setAmSustantivos(AMSustantivos amSustantivos) {
        this.amSustantivos = amSustantivos;
    }

    /**
     * @param amUtil
     *            The amUtil to set.
     */
    public void setAmUtil(AMUtil amUtil) {
        this.amUtil = amUtil;
    }

    /**
     * @param amVerbos
     *            The amVerbos to set.
     */
    public void setAmVerbos(AMVerbos amVerbos) {
        this.amVerbos = amVerbos;
    }

    /**
     * @param amParticipios
     *            The amParticipios to set.
     */
    public void setAmParticipios(AMParticipios amParticipios) {
        this.amParticipios = amParticipios;
    }

    /**
     * indica si entre los tipos de palabra a buscar hay tipos partícula
     * 
     * @return
     */
    private boolean buscaParticulas() {
        List<Enum<?>> enumSeleccionadas = tipoPalabra.getEnumsSeleccionadas();
        return enumSeleccionadas.contains(TipoPalabra.Articulo);
    }

    public boolean validar(String cadenaCompleta, Ignorancia ignorancia) {
        List<String> errores = new ArrayList<String>();
        List<String> advertencias = new ArrayList<String>();
        AnalisisAcento aa = AnalisisAcento.getAnalisisAcento(cadenaCompleta);
        switch (ignorancia) {
            case Nada:
                if (aa.actuales.tipoAcento.equals(Acento.Ninguno)) {
                    if (!buscaParticulas()) {
                        errores.add(Recursos.getCadena("validacion.ignora_nada.forma_no_tiene_acentos"));
                    }
                }
                if (OpPalabras.empiezaConVocal(cadenaCompleta) && !OpPalabras.tieneEspiritu(cadenaCompleta)) {
                    errores.add(Recursos.getCadena("validacion.ignora_nada.forma_no_tiene_espiritus"));
                }
                break;
            case SignosLargaCorta:
                if (aa.actuales.tipoAcento.equals(Acento.Ninguno)) {
                    if (!buscaParticulas()) {
                        errores.add(Recursos.getCadena("validacion.ignora_larga_corta.forma_no_tiene_acentos"));
                    }
                }
                if (OpPalabras.empiezaConVocal(cadenaCompleta) && !OpPalabras.tieneEspiritu(cadenaCompleta)) {
                    errores.add(Recursos.getCadena("validacion.ignora_larga_corta.forma_no_tiene_espiritus"));
                }
                if (OpPalabras.tieneInformacionLargaCorta(cadenaCompleta)) {
                    advertencias.add(Recursos.getCadena("validacion.ignora_larga_corta.larga_ignorada"));
                }
                break;
            case TodosLosDiacriticos:
                if (OpPalabras.tieneInformacionLargaCorta(cadenaCompleta)) {
                    advertencias.add(Recursos.getCadena("validacion.ignora_todo.larga_ignorada"));
                }
                if (!aa.actuales.tipoAcento.equals(Acento.Ninguno)) {
                    if (!buscaParticulas()) {
                        advertencias.add(Recursos.getCadena("validacion.ignora_todo.acento_ignorado"));
                    }
                }
                if (OpPalabras.tieneEspiritu(cadenaCompleta)) {
                    advertencias.add(Recursos.getCadena("validacion.ignora_todo.espiritu_ignorado"));
                }
                if (OpPalabras.tieneDieresis(cadenaCompleta)) {
                    advertencias.add(Recursos.getCadena("validacion.ignora_todo.dieresis_ignorado"));
                }
                if (OpPalabras.tieneSubscripta(cadenaCompleta)) {
                    advertencias.add(Recursos.getCadena("validacion.ignora_todo.subscripta_ignorada"));
                }
                break;
        }
        if (errores.size() > 0) {
            StringBuffer mensaje = new StringBuffer();
            for (String error : errores) {
                mensaje.append(error + "\n");
            }
            JOptionPane.showMessageDialog(this, mensaje.toString());
            return false;
        }
        if (advertencias.size() > 0) {
            StringBuffer mensaje = new StringBuffer();
            for (String advertencia : advertencias) {
                mensaje.append(advertencia + "\n");
            }
            JOptionPane.showMessageDialog(this, mensaje.toString());
            return true;
        }
        return true;
    }

    /**
     * @return Returns the buscar.
     */
    public JButton getBuscar() {
        return buscar;
    }
    
    public ResultadoUniversal getResultadoAt(int row) {
        return resultados.get(row);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        this.contexto = arg0;

    }

    /**
     * @param amConjunciones
     *            the amConjunciones to set
     */
    public void setAmConjunciones(AMConjunciones amConjunciones) {
        this.amConjunciones = amConjunciones;
    }

    public void setPanelProgreso(PanelProgreso panelProgreso) {
        this.panelProgreso = panelProgreso;
    }

}
