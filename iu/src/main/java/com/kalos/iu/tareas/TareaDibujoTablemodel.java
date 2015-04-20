package com.kalos.iu.tareas;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.kalos.beans.ResultadoUniversal;
import com.kalos.iu.PanelPrincipal;
import com.kalos.iu.analisismorfologico.PanelAM;
import com.kalos.recursos.Recursos;

import foxtrot.Task;

public class TareaDibujoTablemodel extends Task {

	private DefaultTableModel dtm;
	private JTable tabla;
	private boolean ignorando;
	private TableColumnModel columnModelVacio;
	//private PanelPrincipal panelPrincipal;
	
	public TareaDibujoTablemodel(DefaultTableModel dtm, JTable tabla, boolean ignorando) {
		this.dtm = dtm;
		this.tabla = tabla;
		this.ignorando = ignorando;
	
		this.columnModelVacio = new DefaultTableColumnModel();
		this.columnModelVacio.addColumn(new TableColumn());
		this.columnModelVacio.getColumn(0).setHeaderValue(Recursos.getCadena("ninguna_forma_encontrada"));
	}
	
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());
	
	public Object run(){
	    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (dtm != null && dtm.getRowCount() > 0) {
			TableColumnModel columnModelNormal=creaColumnmodelNormal(ignorando);
			tabla.setColumnModel(columnModelNormal);
			tabla.scrollRectToVisible(tabla.getCellRect(0, 0, false));
			tabla.setModel(dtm);
			tabla.repaint();
			tabla.getSelectionModel().setSelectionInterval(0, 0);
		} else {
			tabla.setModel(new DefaultTableModel());
			tabla.setColumnModel(columnModelVacio);
			tabla.repaint();
		}
		return null;
	}
	


	/**
	 * Crea el columnmodel normal.
	 * 
	 * @param ignorando
	 *            si el combo tiene algún "ignorar", es necesario mostrar la forma
	 *            original
	 */
	private TableColumnModel creaColumnmodelNormal(boolean ignorando){
		TableColumnModel columnModelNormal = new DefaultTableColumnModel();
		int i=0;
		TableColumn tc ;
		if (ignorando){
			tc = new TableColumn(i); columnModelNormal.addColumn(tc); tc.setCellRenderer(null);
			columnModelNormal.getColumn(i).setHeaderValue(Recursos.getCadena("forma"));
			columnModelNormal.getColumn(i).setPreferredWidth(100);
			i++;
		}
		
		tc = new TableColumn(i); columnModelNormal.addColumn(tc); tc.setCellRenderer(null);
		columnModelNormal.getColumn(i).setHeaderValue(Recursos.getCadena("accidentes"));
		columnModelNormal.getColumn(i).setPreferredWidth(200);
		
		i++;
		tc = new TableColumn(i); columnModelNormal.addColumn(tc); tc.setCellRenderer(null);
		columnModelNormal.getColumn(i).setHeaderValue(Recursos.getCadena("significado"));
		columnModelNormal.getColumn(i).setPreferredWidth(200);
		return columnModelNormal;
	}

}
