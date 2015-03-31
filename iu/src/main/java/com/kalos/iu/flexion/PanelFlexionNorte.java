// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.iu.flexion;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kalos.beans.EntradaDiccionario;
import com.kalos.enumeraciones.Reportes;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;
import com.kalos.visual.controles.combos.ComboEnumeracion;
import com.kalos.visual.controles.deslizador.Deslizador;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class PanelFlexionNorte extends JPanel {

	public JButton getCrear() {
		return botCrear;
	}

	public void habilitarBotones() {
		B = false;
		botCrear.setEnabled(!B && H);
		botCrear.setText(Recursos.getCadena("crear"));
		botCrearMas.setText(Recursos.getCadena("crear"));
		botCrearMas.setEnabled(true);
	}

	public ComboEnumeracion getTiposReporte() {
		return cmbChartTypes;
	}

	public PanelFlexionNorte() {
		cmbChartTypes = new ComboEnumeracion("kalos.enumeraciones.Reportes");
		cmbMoreChartTypes = new ComboEnumeracion(new Reportes[] { Reportes.ARTICULOS_POR_GENERO,
				Reportes.PRONOMBRES_PERSONALES_POR_CASO,
				Reportes.PRONOMBRES_PERSONALES_POR_CASO_SIN_DUAL,
				Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_1,
				Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_2,
				Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_3,
				Reportes.PRONOMBRES_REFLEXIVOS_POR_GENERO,
				Reportes.PRONOMBRES_INTERROGATIVOS_POR_GENERO, Reportes.CONJUNCIONES_ALFABETICO,
				Reportes.CONJUNCIONES_POR_TIPO, Reportes.PREPOSICIONES_ALFABETICO,
				Reportes.PREPOSICIONES_POR_CASO, Reportes.INTERJECCIONES_ALFABETICO,
				Reportes.PRONOMBRES_INDEFINIDOS_POR_GENERO });
		botCrear = new JButton(Recursos.getCadena("cargando"));
		botCrearMas = new JButton(Recursos.getCadena("cargando"));
		deslizador = new Deslizador();
		etiqueta = new JLabel();
		B = true;
		H = false;
		F = Logger.getLogger(getClass().getName());
		etiqueta.setFont(Configuracion.getFont());
		disposicion();
		botCrear.setEnabled(false);
		botCrearMas.setEnabled(false);
	}

	public void setFormaCanonica(String s) {
		etiqueta.setText(s);
	}

	public void disposicion() {
		FormLayout formlayout = new FormLayout(
				"pref,3dlu,pref,3dlu,pref, fill:1dlu:grow, right:pref, 3dlu, pref",
				"fill:pref:grow(.1), 3dlu, fill:pref:grow(.9),  3dlu,pref,3dlu ");
		PanelBuilder panelbuilder = new PanelBuilder(formlayout);
		panelbuilder.setDefaultDialogBorder();
		CellConstraints cellconstraints = new CellConstraints();
		panelbuilder.addLabel(Recursos.getCadena("forma_canonica"), cellconstraints.xy(1, 1));
		panelbuilder.add(etiqueta, cellconstraints.xy(3, 1));
		panelbuilder.addLabel(Recursos.getCadena("elija_tipo_de_reporte"), cellconstraints.xy(1, 3));
		panelbuilder.add(cmbChartTypes, cellconstraints.xy(3, 3));
		panelbuilder.add(botCrear, cellconstraints.xy(5, 3));
		panelbuilder.addLabel(Recursos.getCadena("mas_tipos_de_cuadro"), cellconstraints.xy(1, 5));
		panelbuilder.add(cmbMoreChartTypes, cellconstraints.xy(3, 5));
		panelbuilder.add(botCrearMas, cellconstraints.xy(5, 5));
		panelbuilder.addLabel(Recursos.getCadena("tama\361o_tipografia"), cellconstraints.xy(7, 3));
		panelbuilder.add(deslizador, cellconstraints.xy(9, 3));
		setLayout(new BorderLayout());
		add(panelbuilder.getPanel());
	}

	public void pueblaSegunEntrada(EntradaDiccionario n) {
		cmbChartTypes.removeAllItems();
		if (n == null) {
			cmbChartTypes.addItem(Reportes.NINGUN_TIPO_DISPONIBLE);
			H = false;
		} else {
			H = true;

			switch (n.getTipoPalabra()) {
			case Adjetivo: // '\001'
				cmbChartTypes.addItem(Reportes.ADJETIVOS_POR_GENERO);
				cmbChartTypes.addItem(Reportes.ADJETIVOS_POR_NUMERO);
				cmbChartTypes.addItem(Reportes.ADJETIVOS_POR_NUMERO_SIN_DUAL);
				break;

			case Adverbio: // '\002'
				cmbChartTypes.addItem(Reportes.NINGUN_TIPO_DISPONIBLE);
				break;

			case Sustantivo: // '\003'
				cmbChartTypes.addItem(Reportes.SUSTANTIVOS_POR_NUMERO);
				cmbChartTypes.addItem(Reportes.SUSTANTIVOS_POR_NUMERO_SIN_DUAL);
				break;

			case Verbo: // '\004'
				cmbChartTypes.addItem(Reportes.VERBOS_POR_MODO_ABREVIADO);
				cmbChartTypes.addItem(Reportes.VERBOS_POR_MODO);
				cmbChartTypes.addItem(Reportes.VERBOS_POR_VOZ);
				cmbChartTypes.addItem(Reportes.VERBOS_POR_MODO_SIN_DUAL);
				cmbChartTypes.addItem(Reportes.VERBOS_POR_VOZ_SIN_DUAL);
				cmbChartTypes.addItem(Reportes.PARTICIPIOS_POR_CASO_ABREVIADO);
				cmbChartTypes.addItem(Reportes.PARTICIPIOS_POR_VOZ_ABREVIADO);
				cmbChartTypes.addItem(Reportes.PARTICIPIOS_POR_NUMERO);
				cmbChartTypes.addItem(Reportes.PARTICIPIOS_POR_VOZ);
				cmbChartTypes.addItem(Reportes.PARTICIPIOS_POR_NUMERO_SIN_DUAL);
				cmbChartTypes.addItem(Reportes.PARTICIPIOS_POR_VOZ_SIN_DUAL);
				cmbChartTypes.addItem(Reportes.PARTICIPIOS_POR_NUMERO_SIN_VOCATIVO);
				cmbChartTypes.addItem(Reportes.PARTICIPIOS_POR_VOZ_SIN_VOCATIVO);
				cmbChartTypes.addItem(Reportes.INFINITIVOS_POR_VOZ);
				break;

			case Articulo: // '\005'
				cmbChartTypes.addItem(Reportes.ARTICULOS_POR_GENERO);
				break;

			case PronombrePersonal: // '\006'
				cmbChartTypes.addItem(Reportes.PRONOMBRES_PERSONALES_POR_CASO);
				cmbChartTypes.addItem(Reportes.PRONOMBRES_PERSONALES_POR_CASO_SIN_DUAL);
				break;

			case PronombreRelativo: // '\007'
				cmbChartTypes.addItem(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_1);
				cmbChartTypes.addItem(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_2);
				cmbChartTypes.addItem(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_3);
				break;

			case PronombreReflexivo: // '\b'
				cmbChartTypes.addItem(Reportes.PRONOMBRES_REFLEXIVOS_POR_GENERO);
				break;

			case Conjuncion: // '\t'
				cmbChartTypes.addItem(Reportes.CONJUNCIONES_ALFABETICO);
				cmbChartTypes.addItem(Reportes.CONJUNCIONES_POR_TIPO);
				break;

			case Preposicion: // '\n'
				cmbChartTypes.addItem(Reportes.PREPOSICIONES_ALFABETICO);
				cmbChartTypes.addItem(Reportes.PREPOSICIONES_POR_CASO);
				break;

			case Interjeccion: // '\013'
				cmbChartTypes.addItem(Reportes.INTERJECCIONES_ALFABETICO);
				// fall through

			case PronombreIndefinido: // '\f'
				cmbChartTypes.addItem(Reportes.PRONOMBRES_INDEFINIDOS_POR_GENERO);
				break;
			}
		}
		botCrear.setEnabled(!B && H);
	}

	public JButton getCrearMas() {
		return botCrearMas;
	}

	public ComboEnumeracion getMasTipos() {
		return cmbMoreChartTypes;
	}

	private ComboEnumeracion cmbChartTypes;
	private ComboEnumeracion cmbMoreChartTypes;
	private JButton botCrear;
	private JButton botCrearMas;
	Deslizador deslizador;
	private JLabel etiqueta;
	private boolean B;
	private boolean H;
	Logger F;
}
