// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.iu.diccionario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.kalos.beans.EntradaDiccionario;
import com.kalos.datos.adaptadores.AdaptadorGerenteDiccionario;
import com.kalos.datos.gerentes.GerenteDiccionario;
import com.kalos.enumeraciones.Ignorancia;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.iu.EscuchaSolapa;
import com.kalos.iu.PanelPrincipal;
import com.kalos.iu.PanelProgreso;
import com.kalos.iu.tareas.TareaDiccionario;
import com.kalos.iu.tareas.TareaHabilitaComponentes;
import com.kalos.iu.tareas.TareaLeyenda;
import com.kalos.iu.tareas.TareaOcultaProgreso;
import com.kalos.operaciones.OpPalabras;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;
import com.kalos.visual.controles.combos.ComboEnumeracion;
import com.kalos.visual.controles.deslizador.Deslizador;
import com.kalos.visual.controles.listas.filtroenumeraciones.EnumSeleccionada;
import com.kalos.visual.controles.listas.filtroenumeraciones.FiltroEnumeraciones;
import com.kalos.visual.controles.textos.alternable.TextoAlternable;
import com.kalos.visual.controles.util.TipografiaCambiable;
import com.kalos.visual.controles.util.UtilTipografias;
import com.kalos.visual.controles.ventanas.DialogErrores;
import com.kalos.visual.modelos.DictionaryPM;
import com.kalos.visual.modelos.PagingModel;

import foxtrot.Worker;

// Referenced classes of package kalos.iu.D:
//            B

public class PanelDiccionario extends JPanel implements TipografiaCambiable, EscuchaSolapa {

    Logger log = Logger.getLogger(getClass().getName());
    

    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    public PanelDiccionario(GerenteDiccionario m1) {
		cmbLugaresSubcadena = new ComboEnumeracion(LugarSubcadena.values());
		tabla = new JTable();
		panel = new JPanel();
		textoAlternable = new TextoAlternable(false);
		cmbIgnorancias = new ComboEnumeracion(Ignorancia.values());
		tiposPalabra = new FiltroEnumeraciones(TipoPalabra.values());
		fontSize = new Deslizador();
		botBuscar = new JButton(Recursos.getCadena("buscar"));
		tab = new JTabbedPane();
		detalle = new DetalleDiccionario();
		adGerenteDir = new AdaptadorGerenteDiccionario(m1);
		tabla.setFont(Configuracion.getFont());
		tabla.setSelectionMode(0);
		preparaTabla();
		tabla.setColumnModel(colModelConResultados);
		disposicion();
		tab.add(Recursos.getCadena("lista"), panel);
		tab.add(Recursos.getCadena("detalles"), detalle);
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		fontSize.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				Deslizador des = (Deslizador) propertychangeevent.getSource();
				cambiaTamañoFont(des.getValor());
			}
		});
		botBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				if (tiposPalabra.getSeleccionadas().size() == 0) {
					JOptionPane.showMessageDialog(
							SwingUtilities.getWindowAncestor((JButton) actionevent.getSource()),
							Recursos.getCadena("seleccione_al_menos_un_tipo_de_palabra"));
					return;
				}
				botBuscar.setEnabled(false);
				cmbIgnorancias.setEnabled(false);
				try {
					procesaCadena((Ignorancia) cmbIgnorancias.getEnumeracionSeleccionada(), false);
				} catch (Exception exception) {
					log.error(
							"error al invocar procesaCadena en PanelDiccionario (desde el buscar tradicional) ",
							exception);
					JFrame jframe = (JFrame) SwingUtilities.windowForComponent(botBuscar);
					DialogErrores d1 = new DialogErrores(jframe, Recursos.getCadena("error"),
							Recursos.getCadena("error_de_diccionario"), true, exception);
					d1.setLocationRelativeTo(null);
					d1.setVisible(true);
				}
			}

		});
	}

	private void D() {
		LugarSubcadena h1 = (LugarSubcadena) cmbLugaresSubcadena.getEnumeracionSeleccionada();
		if (h1.equals(LugarSubcadena.Exacto) || h1.equals(LugarSubcadena.Fin)) {
			String s = textoAlternable.getCadenaCompleta();
			if (s == null)
				return;
			s = OpPalabras.sigmaFinal(s);
			textoAlternable.getJTextField().setText(OpPalabras.strCompletoAUnicode(s));
		}
	}

	private void preparaTabla() {
		tabla.setAutoCreateColumnsFromModel(false);
		colModelConResultados = new DefaultTableColumnModel();
		for (int i1 = 0; i1 < 3; i1++)
			colModelConResultados.addColumn(new TableColumn(i1));

		colModelConResultados.getColumn(0).setPreferredWidth(100);
		colModelConResultados.getColumn(1).setPreferredWidth(50);
		colModelConResultados.getColumn(2).setPreferredWidth(200);
		colModelConResultados.getColumn(0).setHeaderValue(Recursos.getCadena("forma"));
		colModelConResultados.getColumn(1).setHeaderValue(Recursos.getCadena("tipo"));
		colModelConResultados.getColumn(2).setHeaderValue(Recursos.getCadena("significado"));
		colModelVacio = new DefaultTableColumnModel();
		colModelVacio.addColumn(new TableColumn());
		colModelVacio.getColumn(0).setHeaderValue(Recursos.getCadena("ninguna_forma_encontrada"));
	}

	public void disposicion() {
		FormLayout formlayout = new FormLayout(
				"pref, 3dlu, 120dlu, 10dlu, 40dlu, 3dlu, pref, 60dlu, 3dlu, pref, fill:1dlu:grow(1), right:pref, 3dlu, pref",
				"fill:pref ,3dlu, fill:pref ,3dlu, fill:pref ,3dlu, 27dlu, 40dlu, fill:pref , 3dlu, fill:1dlu:grow(0.9)");
		PanelBuilder panelbuilder = new PanelBuilder(formlayout);
		panelbuilder.setDefaultDialogBorder();
		CellConstraints cellconstraints = new CellConstraints();
		formlayout.setRowGroups(new int[][] { new int[] { 1, 3, 5 } });
		panelbuilder.addLabel(Recursos.getCadena("ingrese_forma"), cellconstraints.xy(1, 1));
		panelbuilder.add(textoAlternable, cellconstraints.xyw(3, 1, 3));
		panelbuilder.add(botBuscar, cellconstraints.xy(7, 1));
		panelbuilder.addLabel(Recursos.getCadena("lugar_expresion"), cellconstraints.xy(1, 3));
		panelbuilder.add(cmbLugaresSubcadena, cellconstraints.xy(3, 3));
		panelbuilder.addLabel(Recursos.getCadena("ignorar"), cellconstraints.xy(1, 5));
		panelbuilder.add(cmbIgnorancias, cellconstraints.xy(3, 5));
		panelbuilder.addLabel(Recursos.getCadena("tipo_de_palabra"), cellconstraints.xy(1, 7));
		panelbuilder.add(tiposPalabra, cellconstraints.xywh(3, 7, 6, 2));
		panelbuilder.addLabel(Recursos.getCadena("tamaño_tipografia"), cellconstraints.xy(12, 1));
		panelbuilder.add(fontSize, cellconstraints.xy(14, 1));
		panelbuilder.add(tab, cellconstraints.xyw(1, 11, 14));
		setLayout(new BorderLayout());
		add(panelbuilder.getPanel());
	}

	public void setUltimaEntradaDiccionario(EntradaDiccionario n1) {
		detalle.setEntradaDiccionario(n1);
	}

	private List<TipoPalabra> enumSeleccionadaATipoPalabra(List<EnumSeleccionada> list) {
		List<TipoPalabra> arraylist = new ArrayList<TipoPalabra>();
		TipoPalabra l1;
		for (Iterator<EnumSeleccionada> iterator = list.iterator(); iterator.hasNext(); arraylist
				.add(l1)) {
			EnumSeleccionada a = iterator.next();
			l1 = (TipoPalabra) a.getEnumeracion();
		}
		return arraylist;
	}

	public void proceder(String palabraCompleta, Ignorancia ignorancia) throws Exception {
		String palabraBeta = OpPalabras.strCompletoABeta(palabraCompleta);
		LugarSubcadena lugarSubcadena = (LugarSubcadena) cmbLugaresSubcadena.getEnumeracionSeleccionada();
		Worker.post(new TareaLeyenda(panProgreso, "buscando"));
		List<TipoPalabra> tiposPalabraSel = enumSeleccionadaATipoPalabra(tiposPalabra.getSeleccionadas());
		DictionaryPM pagModDiccionario = (DictionaryPM) Worker.post(new TareaDiccionario(adGerenteDir, palabraBeta, ignorancia, lugarSubcadena, tiposPalabraSel));
		if (pagModDiccionario == null)
			Worker.post(new TareaLeyenda(panProgreso, "ninguna_forma_encontrada"));
		else
			Worker.post(new TareaLeyenda(panProgreso, "formas_encontradas", new String[] { Integer.toString(pagModDiccionario.getRowCount()) }));
		Worker.post(new TareaOcultaProgreso(panProgreso));
		Worker.post(new TareaHabilitaComponentes(new Component[] { botBuscar, cmbIgnorancias }));
		if (pagModDiccionario.getRowCount() > 0) {
			tabla.setColumnModel(colModelConResultados);
			tabla.setModel(pagModDiccionario);
			tabla.scrollRectToVisible(tabla.getCellRect(0, 0, false));
			tabla.getSelectionModel().setSelectionInterval(0, 0);
		} else {
			tabla.setModel(new DefaultTableModel());
			tabla.setColumnModel(colModelVacio);
		}
		panel.removeAll();
		panel.add(PagingModel.createPagingScrollPaneForTable(tabla));
		panel.revalidate();
	}

	public void cambiaTamañoFont(float f1) {
		UtilTipografias.cambiaTamañoEnTabla(tabla, f1);
		detalle.cambiaTamañoFont(f1);
		repaint();
		revalidate();
	}

	public JTable getTabla() {
		return tabla;
	}

	public ComboEnumeracion getLugarSubcadena() {
		return cmbLugaresSubcadena;
	}

	public void procesaCadena(Ignorancia ingorancia, boolean flag) throws Exception {
		D();
		String cadena = textoAlternable.getCadenaCompleta();

		switch (ingorancia) {
		case TodosLosDiacriticos: // '\001'
			cadena = OpPalabras.neutraliza(cadena);
			break;

		case SignosLargaCorta: // '\002'
			cadena = OpPalabras.strAbreviaCompleta(cadena);
			break;
		}
		if (cadena == null)
			cadena = "";
		proceder(cadena, ingorancia);
	}

	public void navegaTablaResultado(KeyEvent keyevent) {
		KeyEvent keyevent1 = new KeyEvent(tabla, 401, keyevent.getWhen(), 0, keyevent.getKeyCode(),
				keyevent.getKeyChar());
		tabla.dispatchEvent(keyevent1);
	}

	public void setPanelProgreso(PanelProgreso b) {
		panProgreso = b;
	}
	
	public void miSolapaSeleccionada(){
	    if (tabla.getSelectedRow()>0){
	        DictionaryPM  model = (DictionaryPM)tabla.getModel();
	        EntradaDiccionario endic = model.getFila(tabla.getSelectedRow());
	        panelPrincipal.setEntradaDiccionario(endic);
	    }
	}
	
	   public void setPanelPrincipal(PanelPrincipal panelPrincipal){
	        this.panelPrincipal = panelPrincipal;
	    }	


	private ComboEnumeracion cmbLugaresSubcadena;
	private JTable tabla;
	private TableColumnModel colModelConResultados;
	private TableColumnModel colModelVacio;
	private JPanel panel;
	private TextoAlternable textoAlternable;
	private ComboEnumeracion cmbIgnorancias;
	private FiltroEnumeraciones tiposPalabra;
	private Deslizador fontSize;
	private JButton botBuscar;
	private PanelProgreso panProgreso;
	private JTabbedPane tab;
	private DetalleDiccionario detalle;
	private AdaptadorGerenteDiccionario adGerenteDir;
	private PanelPrincipal panelPrincipal;
}
