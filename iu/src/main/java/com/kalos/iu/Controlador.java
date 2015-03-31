// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.iu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.kalos.beans.EntradaDiccionario;
import com.kalos.enumeraciones.Reportes;
import com.kalos.iu.diccionario.PanelDiccionario;
import com.kalos.iu.flexion.PanelFlexion;
import com.kalos.recursos.Recursos;
import com.kalos.visual.controles.combos.ComboEnumeracion;
import com.kalos.visual.modelos.DictionaryPM;

public class Controlador {
	private class _D implements ActionListener {

		public void actionPerformed(ActionEvent actionevent) {
			ComboEnumeracion d = panelFlexion.getPanelEleccionTipoReporte().getMasTipos();
			Reportes m = (Reportes) d.getEnumeracionSeleccionada();
			d.setEnabled(false);
			JButton jbutton = (JButton) actionevent.getSource();
			jbutton.setEnabled(false);
			panelFlexion.creaTableModel(null, m);
		}

	}

	private class _C implements ActionListener {

		public void actionPerformed(ActionEvent actionevent) {
			ComboEnumeracion d = panelFlexion.getPanelEleccionTipoReporte().getTiposReporte();
			Reportes m = (Reportes) d.getEnumeracionSeleccionada();
			if (m == null)
				return;
			if (m.equals(Reportes.NINGUN_TIPO_DISPONIBLE))
				return;
			if (entradaDiccionario == null) {
				return;
			} else {
				d.setEnabled(false);
				JButton jbutton = (JButton) actionevent.getSource();
				jbutton.setEnabled(false);
				panelFlexion.creaTableModel(entradaDiccionario.getId(), m);
				return;
			}
		}

	}

	private class _B implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent listselectionevent) {
			if (listselectionevent.getValueIsAdjusting())
				return;
			JTable jtable = panelDiccionario.getTabla();
			int i = jtable.getSelectedRow();
			if (i == -1) {
				return;
			} else {
				DictionaryPM b = (DictionaryPM)jtable.getModel();
				entradaDiccionario = b.getFila(i);
				panelDiccionario.setUltimaEntradaDiccionario(entradaDiccionario);
				return;
			}
		}

	}

	private class _A extends ComponentAdapter {

		public void componentShown(ComponentEvent componentevent) {
			panelFlexion.activar(entradaDiccionario);
			if (entradaDiccionario != null)
				panelFlexion.setFormaCanonica(entradaDiccionario.getNormal());
			else
				panelFlexion.setFormaCanonica(Recursos.getCadena("ninguna"));
		}

	}

	public Controlador(PanelDiccionario a, PanelFlexion a1) {
		panelDiccionario = a;
		panelFlexion = a1;
		JTable jtable = a.getTabla();
		jtable.getSelectionModel().addListSelectionListener(new _B());
		a1.addComponentListener(new _A());
		a1.getPanelEleccionTipoReporte().getCrear().addActionListener(new _C());
		a1.getPanelEleccionTipoReporte().getCrearMas().addActionListener(new _D());
	}

	public void navegaTablaResultado(KeyEvent keyevent) {
		JTable jtable = panelDiccionario.getTabla();
		KeyEvent keyevent1 = new KeyEvent(jtable, 401, keyevent.getWhen(), 0,
				keyevent.getKeyCode(), keyevent.getKeyChar());
		jtable.dispatchEvent(keyevent1);
	}



	private PanelDiccionario panelDiccionario;
	PanelFlexion panelFlexion;
	private EntradaDiccionario entradaDiccionario;
}
