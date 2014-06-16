package kalos.utilidades.abms;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import kalos.operaciones.OpBeans;
import kalos.recursos.Recursos;
import kalos.visual.modelos.PagingModel;
import kalos.visual.renderers.FRGenericos;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//import java.awt.HeadlessException;

/**
 * ABM que usa Spring-JDBC y beans como back-end
 */
public abstract class ABM <T> extends JPanel implements ApplicationContextAware {

//	private org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(this.getClass().getName());
	

	protected PagingModel<T> pagingModel;
	
	// cosas del application context
	private ApplicationContext contexto;

	public void setApplicationContext(ApplicationContext contexto) {
		this.contexto = contexto;
	}

	public ApplicationContext getApplicationContext() {
		return this.contexto;
	}

	
	protected JTable tabla = new JTable();
	protected JPanel panelTabla=new JPanel();


	protected JPanel panBusqueda = new JPanel();
	
	/**
	 * una toolbar que se usa para tareas auxiliares como copiar la misma forma en júnico, etc
	 * Se usa sólo si el contenedor es un JFrame
	 */
	protected JToolBar barAyuda = new JToolBar();
	
	
	/**
	 * se llama después de actualizar el bean en la base de datos, para
	 * colocar la selección en el lugar adecuado de la tabla, y 
	 * para refrescar el CacheMovil externamente
	 */
    protected void despuesDeActualizar(){
        String id=OpBeans.getId(ultimoBeanSeleccionado);
        int indice=pagingModel.getIndice(id);
        pagingModel.modifica(ultimoBeanSeleccionado, indice);
//        
//        logger.info("después de actualizar 1");
//    	String id=OpBeans.getId(ultimoBeanSeleccionado);
//    	logger.info("después de actualizar 2");
//    	int indice=pagingModel.getIndice(id);
//    	logger.info("después de actualizar 3");;
//    	pagingModel.borra(indice);
//    	logger.info("después de actualizar 4");
//    	pagingModel.inserta(ultimoBeanSeleccionado, indice);
//    	logger.info("después de actualizar 5");
//    	tabla.getSelectionModel().setSelectionInterval(indice, indice);
//    	logger.info("después de actualizar 6");
    }
	
    /**
     * se llama después de insertar, para actualizar el CacheMovil y la tabla externamente
     *
     */
    protected void despuesDeInsertar(T bean){
    	pagingModel.inserta(bean, Math.max(0, tabla.getSelectedRow()));
    }
    
    /**
     * se llama después de borrar, para actualizar el CacheMovil y la tabla externamente
     *
     */
    protected void despuesDeBorrar(){
    	pagingModel.borra(tabla.getSelectedRow());
    }
    
    
    
    
	protected JMenuBar barMenu=new JMenuBar();

	/**
	 * el panel de detalles, que se muestra en la segunda solapa
	 * Es subclaseado por un panel de detalle específico del ABM
	 */
	protected PanelDetalleBeans panDetalle;

	protected JPanel panSolapas ;

	/**
	 * el panel que contiene los botones agregar, borrar, etc
	 */
	private PanelBotonesAccion panAccion = new PanelBotonesAccion();
	
	/**
	 * indica que un agregado está en proceso
	 */
	protected boolean agregando = false;

	/**
	 * indica que un agregado (de muchos) está en proceso
	 */
	protected boolean agregandoMuchos = false;

	/**
	 * indica que se está editando una entrada existente
	 */
	protected boolean editando = false;

	/**
	 * el control de solapas que contiene en una solapa la tabla-lista, en otra el panel de
	 * detalle, y en un tercero tal vez la tabla o reporte de flexión
	 */
	protected JTabbedPane tabSolapas = new JTabbedPane();

	/**
	 * las propiedades del bean que se muestran en las columnas
	 */
	protected String[] propiedades;
	
	/**
	 * las claves de los mensajes que serán los encabezados de columna
	 */
	protected String[] encabezadosComluma;
	
	/**
	 * los anchos predeterminados de las columnas
	 */
	protected int[] anchos;
	
	/**
	 * las cadenas que sirven para construir los renderers
	 */
	protected String[] renderers;
	
	

	
	protected T ultimoBeanSeleccionado;
	
	

	public ABM(PanelDetalleBeans detalle, String titulo) {
		this.panDetalle = detalle;
		panelTabla.setLayout(new BorderLayout());
		panDetalle.inicializa();
		preparaPanSolapas();
		inicializaTabla();

		this.setLayout(new BorderLayout());
		JPanel cont=new JPanel();
		cont.setLayout(new BorderLayout());
		this.add(cont, BorderLayout.CENTER);
		cont.add(panSolapas, BorderLayout.CENTER);
		cont.add(panBusqueda, BorderLayout.NORTH);
		cont.add(panAccion, BorderLayout.EAST);
		eventosNavegacion();
		eventosAccion();
		
		setFocusTraversalPolicy(new ManejaFocos());
	}
	
	
    protected void preparaColumnas() {
        tabla.setColumnModel(new DefaultTableColumnModel());
        for (int i = 0; i < getEncabezadosComluma().length; i++) {
            TableCellRenderer tcr = FRGenericos.obtieneRendererColumna(renderers[i]);
            TableColumn tc = new TableColumn(i, anchos[i], tcr, null);
            tc.setHeaderValue(Recursos.getCadena(getEncabezadosComluma()[i]));
            tabla.addColumn(tc);
        }
    }
	

	/**
	 * iniciliza la barra de ayuda y la de menú. Los ABMs pueden descartar este método
	 * con una implementación vacía, o pueden agregarle botones a las barras y 
	 * luego lamar agregaToolbar().  
	 */
	protected abstract void preparaBarras();
	
	/**
	 * Cuando las subclases de ABM están contenidas en un JFrame, este método se puede llamar
	 * externamente, con el JFrame contenedor como parámetro 
	 * (siempre y cuando se necesite una toolbar)
	 * @param frame  el frame contenedor
	 * 
	 */
    public void agregaToolbar(JFrame frame) {
		frame.getContentPane().add(barAyuda, BorderLayout.NORTH);
	}
    
	/**
	 * Cuando las subclases de ABM están contenidas en un JFrame, este método se puede llamar
	 * externamente, con el JFrame contenedor como parámetro 
	 * (siempre y cuando se necesite una menuBar)
	 * @param frame  el frame contenedor
	 * 
	 */
    public void agregaMenuBar(JFrame frame){
    	frame.setJMenuBar(barMenu);
    }

	protected abstract void inserta();
	
	
	protected abstract boolean validaPreInsercion();

	protected boolean confirmaInsertar() {
		if (!validaPreInsercion())
			return false;
		inserta();
//		seleccionarFilaEnTabla(getPk());
		limpieza();
		return true;
	}

	/**
	 * agrega eventos a los botones de acción
	 */
	private void eventosAccion() {
		// eventos de los botones del panel de detalle
		panAccion.butInsertarMuchos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				insertarMuchosApretado();
			}
		});
		panAccion.butInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				insertarApretado();
			}
		});
		panAccion.butBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				borrarApretado();
			}
		});
		panAccion.butModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				modificarApretado();
			}
		});
		
		panAccion.butConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				confirmarApretado();
			}
		});
		
		panAccion.butCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				cancelarApretado();
			}
		});
	}
	
	/**
	 * agrega eventos a los botones de navegación
	 */
	private void eventosNavegacion() {
		// eventos de los botones del panel de detalle
		panDetalle.getBotonAnterior().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				moverSeleccion(-1);
			}
		});
		panDetalle.getBotonAnteriorSalto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				moverSeleccion(-10);
			}
		});
		panDetalle.getBotonSiguiente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				moverSeleccion(1);
			}
		});
		panDetalle.getBotonSiguienteSalto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				moverSeleccion(10);
			}
		});
	}
	
	protected abstract void borra();
	
	protected void borrarApretado(){
	    int answer = JOptionPane.showConfirmDialog(this, Recursos.getCadena("borrar_realmente"), 
	    		Recursos.getCadena("borrar"), JOptionPane.YES_NO_OPTION);
	    if (answer == JOptionPane.NO_OPTION) {
	        return;
	    }
		int seleccion=tabla.getSelectedRow();
		borra();
		limpieza();
		if (tabla.getRowCount()>0){
			if (--seleccion < 0)
				tabla.setRowSelectionInterval(0, 0);
			else if (tabla.getRowCount() > seleccion) {
				tabla.setRowSelectionInterval(seleccion, seleccion);
			}
			else
				tabla.setRowSelectionInterval(0, 0);
		}
		tabla.repaint();
		tabla.revalidate();
	}

	/**
	 * 
	 * Lugares puede ser positivo o negativo.
	 * 
	 * @param lugares
	 */
	protected void moverSeleccion(int lugares) {
		ListSelectionModel lsm = tabla.getSelectionModel();
		int primero = lsm.getAnchorSelectionIndex();
		int segundo = lsm.getLeadSelectionIndex();
		int cantidad = tabla.getRowCount();
		int primeroPropuesto = primero + lugares;
		int segundoPropuesto = segundo + lugares;
		if (lugares > 0) {
			if (cantidad > segundoPropuesto) {
				lsm.setSelectionInterval(primeroPropuesto, segundoPropuesto);
			} else {
				lsm.setSelectionInterval(cantidad - 1, cantidad - 1);
			}
		} else {
			if (primeroPropuesto > 0) {
				lsm.setSelectionInterval(primeroPropuesto, segundoPropuesto);
			} else {
				lsm.setSelectionInterval(0, 0);
			}
		}
		seleccionCambio();
	}

	public void ningunValorValido() {
		JOptionPane.showMessageDialog(this, Recursos.getCadena("ningun_valor_valido_seleccionado"), Recursos
				.getCadena("busqueda"), JOptionPane.OK_OPTION);
	}

	

	/**
	 * crea el panel de solapas, que ocupa el centro del ABM, y contiene la el control de solapas
	 * que contiene la lista en la primera solapa y el panel de detalle en la segunda
	 *
	 */
	private void preparaPanSolapas() {
		panSolapas=new JPanel();
		panSolapas.setLayout(new BorderLayout());
		panSolapas.add(tabSolapas);
		tabSolapas.insertTab(Recursos.getCadena("lista"), null, panelTabla, null, 0);
		tabSolapas.insertTab(Recursos.getCadena("detalles"), null, panDetalle, null, 1);
		tabSolapas.setMnemonicAt(0, KeyEvent.VK_L);
		tabSolapas.setMnemonicAt(1, KeyEvent.VK_D);
	}

	/**
	 * selecciona en la tabla una la fila cuyo bean subyacente sea el denotado por el id dado como parámetro
	 * sólo sirve si el valor a seleccionar está dentro del segmento visible de valores
	 * @param id  el id del bean a seleccionar en la tabla
	 */
	public void seleccionarFilaEnTabla(String id) {
		int indice = pagingModel.getIndice(id);
		tabla.setRowSelectionInterval(indice, indice);
		tabla.scrollRectToVisible(tabla.getCellRect(tabla.getSelectedRow(), 0, true));
	}

	public void avanzaSeleccionSiPosible() {
		int indice=tabla.getSelectedRow();
		if (indice < (tabla.getRowCount() - 1)) {
			tabla.setRowSelectionInterval(indice + 1, indice + 1);
			tabla.scrollRectToVisible(tabla.getCellRect(tabla.getSelectedRow(), 0, true));
		}
	}

	protected void agregaSolapa(String titulo, JPanel panel, int mnemonico) {
		int lugar = tabSolapas.getComponentCount();
		tabSolapas.insertTab(titulo, null, panel, null, lugar);
		tabSolapas.setMnemonicAt(lugar, mnemonico);
	}

	/**
	 * llamada al apretar el botón de insertar, se usa para llenar
	 * los campos del panel de detalle con valores por defecto o que se pueden deducir 
	 * sin teclear (por ejemplo, autonuméricos)
	 * @see #insertarApretado()
	 */
	abstract protected void llenadoPreInsercion();

	protected void seleccionaPrimera(JTable tbl) {
		if (tbl.getRowCount() > 0)
			tbl.setRowSelectionInterval(0, 0);
	}


	/**
	 * Vincula un DM con una tabla, especificando los nombres de las columnas
	 * del Dm que corresponden exactamente en número y tipo con las columnas de
	 * la tabla.
	 * 
	 * @param dm
	 * @param columnasVisiblesTm
	 */
	protected void cargaBeansEnTabla(List<?> lista) {
		tabla.setColumnModel(new DefaultTableColumnModel());
		for (int i = 0; i < getEncabezadosComluma().length; i++) {
			TableCellRenderer tcr = FRGenericos.obtieneRendererColumna(renderers[i]);
			TableColumn tc = new TableColumn(i, anchos[i], tcr, null);
			tc.setHeaderValue(Recursos.getCadena(getEncabezadosComluma()[i]));
			tabla.addColumn(tc);
		}
		try {
			DefaultTableModel tm = new TablemodelNoEditable(Recursos.getArray(getEncabezadosComluma()), 0);
			for (Object bean : lista) {
				Vector<Object> fila = new Vector<Object>();
				for (String propiedad : propiedades) {
					Object valor = BeanUtils.getProperty(bean, propiedad);
					fila.add(valor);
				}
				tm.addRow(fila);
			}
			tabla.setModel(tm);
			seleccionaPrimera(tabla);
			tabla.revalidate();
			tabla.repaint();
			habilitaOperaciones();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	/**
	 * setea atributos de la tabla que no cambian
	 */
	private void inicializaTabla() {
		tabla.setAutoCreateColumnsFromModel(false);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				if (ev.getValueIsAdjusting()) {
					return;
				} else {
					seleccionCambio();
				}
			}
		});
	}

	
	/**
	 * se activa cuando la seleción el la tabla cambia, y pone el bean que
	 * corresponde a la fina en el panel de detalle
	 * Puede descartarse (siempre y cuando se llame a super.seleccionCambio())
	 * desde las subclases para agregar cosas que quiero que pasen al cambiar la 
	 * selección, por ejemplo, el sugerir una flexión
	 * 
	 * Compruebo si cam es no nulo poque distintos métodos del abm hijo pueden implementar o no el CacheMovil
	 */
//	protected void seleccionCambioInterna() {
//		int fila = tabla.getSelectedRow();
//		if (fila==-1)
//			return;
//		ultimoBeanSeleccionado= cam.get(fila);
//	}
	
	
	protected void seleccionCambioInterna() {
		int fila = tabla.getSelectedRow();
		if (fila==-1)
			return;
		ultimoBeanSeleccionado=pagingModel.getFila(fila);
	}
	
	protected abstract void seleccionCambio();

	public void mensajeVacio() {
		JOptionPane.showMessageDialog(this, Recursos.getCadena("no_puede_ser_vacio"), Recursos.getCadena("busqueda"),
				JOptionPane.OK_OPTION);
	}

	


	protected void insertarMuchosApretado() {
		agregandoMuchos = true;
		insertarApretado();
	}

	protected void insertarApretado() {
		agregando = true;
		editando = false;
		panAccion.estadosTrasInsertar();
		tabSolapas.setSelectedIndex(1);
		limpieza();
		panDetalle.setEditable(true);
		llenadoPreInsercion();
		panDetalle.habilitaNavegacion(false);
		tabSolapas.setSelectedIndex(1);
		panDetalle.setModoAgregado();

	}
	
	/**
	 * pasa el contenido del bean seleccionado a los campos del panel de detalle
	 * @param obj
	 */
	protected final void llenaCampos(Object obj) {
		panDetalle.beanACampos(obj);
	}
	
	/**
	 * pasa el contenido del panel de detalle a un bean del tipo correspondiente al ABM
	 * @param obj
	 */
	public final void pueblaRegistroConDetalle(Object obj) {
		panDetalle.camposABean(obj);
	}

	protected void modificarApretado() {
		agregando = false;
		editando = true;
		panAccion.estadosTrasModificar();
		panDetalle.setEditable(true);
		panDetalle.habilitaNavegacion(false);
		tabSolapas.setSelectedIndex(1);
		panDetalle.setModoEdicion();
	}

	/**
	 * reinicializa el contenido de los controles
	 * del panel de detalle
	 */
	protected final void limpieza() {
		panDetalle.limpieza();
	}


	protected void confirmarApretado() {
		if (agregando || agregandoMuchos) {
			if (!confirmaInsertar())
				return;
		} else if (editando) {
			if (!confirmaModificar())
				return;
		}
		if (!agregandoMuchos) {
			agregando = false;
			editando = false;
			agregandoMuchos = false;
			panAccion.estadosTrasAgregar();
			panDetalle.setEditable(false);
			panDetalle.habilitaNavegacion(true);
		} else {
			insertarApretado();
		}
	}

	protected void cancelarApretado() {
		agregando = false;
		agregandoMuchos = false;
		editando = false;
		panAccion.estadosTrasCancelar();
		limpieza();
		panDetalle.setEditable(false);
		panDetalle.habilitaNavegacion(true);
		// tm.cleanBuffer();
		panDetalle.limpieza();
	}
	
	protected abstract void actualiza();

	/**
	 * llamada al principio de todo, antes de emprender ninguna acción
	 * 
	 */
	protected void inhabilitaTodo() {
		panAccion.inhabilitaTodo();
		panDetalle.setEditable(false);
		panDetalle.habilitaNavegacion(false);
	}
	
	protected boolean confirmaModificar(){
		if (!panDetalle.validaPreInsercion())
			return false;
		actualiza();
		seleccionarFilaEnTabla(getPk());
		return true;
	}
	
	

	protected void habilitaOperaciones() {
		panAccion.habilitaOperaciones();
		panDetalle.habilitaNavegacion(true);
	}



	class ManejaFocos extends DefaultFocusTraversalPolicy {
		public Component getDefaultComponent(Container focusCycleRoot) {
			return tabSolapas;
		}
	}
	
	
	protected void tablemodelATabla(){
        tabla.setModel(pagingModel);
        panelTabla.removeAll();
        JScrollPane jsp=PagingModel.createPagingScrollPaneForTable(tabla);
        panelTabla.add(jsp);
        panelTabla.repaint();
        panelTabla.revalidate();
        panelTabla.repaint();
	}

	public String getPk() {
		if (ultimoBeanSeleccionado!=null)
		 return OpBeans.getId(ultimoBeanSeleccionado);
		return null;
	}


	public int[] getAnchos() {
		return anchos;
	}

	public void setAnchos(int[] anchos) {
		this.anchos = anchos;
	}

	public String[] getEncabezadosComluma() {
		return encabezadosComluma;
	}

	/**
	 * tratar de llamar a esta función siempre en una sola línea, así se puede revisar el código después con "PurificaRecursos"
	 * @param encabezadosComluma
	 */
	public void setEncabezadosComluma(String[] encabezadosComluma) {
		this.encabezadosComluma = encabezadosComluma;
	}

	public String[] getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(String[] propiedades) {
		this.propiedades = propiedades;
	}

	public String[] getRenderers() {
		return renderers;
	}

	public void setRenderers(String[] renderers) {
		this.renderers = renderers;
	}
	

}