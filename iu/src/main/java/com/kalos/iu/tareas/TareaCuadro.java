package com.kalos.iu.tareas;

import java.awt.Color;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.kalos.enumeraciones.Reportes;
import com.kalos.flexion.CreadorTableModelsGrilla;
import com.kalos.flexion.CreadorTableModelsReporte;
import com.kalos.iu.flexion.ParametrosReporte;
import com.kalos.iu.jasper.JRViewerKalos;
import com.kalos.iu.jasper.UtilidadJasper;
import com.kalos.recursos.Recursos;
import com.kalos.visual.renderers.RendererCeldaReporte;
import foxtrot.Task;

public class TareaCuadro extends Task {

	private CreadorTableModelsGrilla creadorTableModelsGrilla;

	private CreadorTableModelsReporte creadorTableModelsReporte;

	private Map<Reportes, DefaultTableModel> cacheTablas;

	private Map<Reportes, DefaultTableModel> cacheJasper;

	private JPanel panelTabla;

	private JPanel panelJasper;

	private UtilidadJasper utilidadJasper;

	private JTable tabla;

	private Reportes tipoReporte;

	private String id;


	private ParametrosReporte parametrosReporte;

//	Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public Object run() throws Exception {
		DefaultTableModel tmTabla = null;
		DefaultTableModel tmJasper = null;
		if (tmTabla == null) {
			tmTabla = creadorTableModelsGrilla.getTMGrilla(id, tipoReporte);
//			tmTabla=new TableMoldeDebug(tmTabla, "tablemodel de Tabla");
			if (tmTabla==null){
				JOptionPane.showMessageDialog(panelTabla, Recursos.getCadena("ninguna_forma_encontrada"));
				return null;
			}
			cacheTablas.put(tipoReporte, tmTabla);
			tmJasper = creadorTableModelsReporte.getTMReporte(id, tipoReporte);
//			tmJasper=new TableMoldeDebug(tmTabla, "tablemodel de jasper");
			cacheJasper.put(tipoReporte, tmJasper);
		}
		this.tabla.setModel(tmTabla);
		renderersDeTabla(tabla, true);
		panelTabla.removeAll();
		panelTabla.add(new JScrollPane(this.tabla));
		panelJasper.removeAll();
		Map<String, String> parametros = parametrosReporte.parametros(id, tipoReporte);
		JRViewerKalos visor = utilidadJasper.visorJasper(tipoReporte, parametros, tmJasper);
		panelJasper.add(visor);
		panelTabla.revalidate();
		return null;
	}

	public TareaCuadro(String id, JTable tabla, Reportes tipoReporte, ParametrosReporte parametrosReporte,
			CreadorTableModelsGrilla creadorTableModelsGrilla, CreadorTableModelsReporte creadorTableModelsReporte,
			Map<Reportes, DefaultTableModel> cacheTablas, Map<Reportes, DefaultTableModel> cacheJasper,
			JPanel panelTabla, JPanel panelJasper, UtilidadJasper utilidadJasper) {
		this.id = id;
		this.tipoReporte = tipoReporte;
		this.creadorTableModelsGrilla = creadorTableModelsGrilla;
		this.creadorTableModelsReporte = creadorTableModelsReporte;
		this.parametrosReporte = parametrosReporte;
		this.cacheTablas = cacheTablas;
		this.cacheJasper = cacheJasper;
		this.panelTabla = panelTabla;
		this.panelJasper = panelJasper;
		this.utilidadJasper = utilidadJasper;
		this.tabla = tabla;
	}

	/**
	 * pone renderers del tipo "CeldaReporte" en todas las columnas de la tabla
	 * 
	 * @param tabla
	 *            la tabla a transformar
	 * @param todoSinCheck
	 *            indica que todas las celdas deben ser presentadas desprovistas
	 *            de "ccheckbox", independientemente del subtipo de CeldaReporte
	 *            que las presente
	 */
	private void renderersDeTabla(JTable tabla, boolean todoSinCheck) {
		for (int i = 0; i < tabla.getColumnCount(); i++) {
			TableColumn tc = tabla.getColumnModel().getColumn(i);
			tc.setCellRenderer(new RendererCeldaReporte(todoSinCheck));
			tc.setCellEditor(null);
		}
	}



}
