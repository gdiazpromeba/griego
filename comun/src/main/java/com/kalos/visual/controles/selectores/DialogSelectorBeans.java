// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.selectores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kalos.bibliotecadatos.ListaSeleccionable;
import com.kalos.recursos.Recursos;
import com.kalos.visual.controles.ventanas.AceptarCancelar;

public abstract class DialogSelectorBeans extends AceptarCancelar {

    public DialogSelectorBeans() {
	centerPanel = new JPanel();
	northPanel = new JPanel();
	southPanel = new JPanel();
	panBusqueda = new JPanel();
	listaSeleccionable = new ListaSeleccionable(null);
	setDefaultCloseOperation(1);
	setLayout(new BorderLayout());
	centerPanel.setLayout(new BorderLayout());
	centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	add(centerPanel, "Center");
	add(northPanel, "North");
	add(southPanel, "South");
	southPanel.setLayout(new BorderLayout());
	southPanel.add(panBusqueda, "Center");
	northPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
	northPanel.setLayout(new GridBagLayout());
	panBusqueda.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
	panBusqueda.setLayout(new GridBagLayout());
    }

    protected void cancelar() {
	acepta = false;
	setVisible(false);
    }

    protected void aceptar() {
	if (listaSeleccionable == null || getBeanSeleccionado() == null) {
	    JOptionPane.showMessageDialog(this, Recursos.getCadena("no_puede_ser_vacio"),
		    Recursos.getCadena("seleccion"), 0);
	    return;
	} else {
	    acepta = true;
	    setVisible(false);
	    return;
	}
    }

    public abstract void mensajeVacio();

    public final void fuerzaSeleccion(String s) {
	listaSeleccionable.setPK(s);
	E();
    }

    protected void E() {
    }

    public Object getBeanSeleccionado() {
	return listaSeleccionable.getSeleccionado();
    }

    public String getPK() {
	return listaSeleccionable.getPK();
    }

    public abstract void llenaCampos();

    public abstract void limpieza();

    public abstract void fonts();

    protected void setListaSeleccionable(java.util.List list) {
	listaSeleccionable = new ListaSeleccionable(list);
    }

    protected java.util.List getListaSeleccionable() {
	return listaSeleccionable.getLista();
    }

    protected JPanel centerPanel;
    protected JPanel northPanel;
    private JPanel southPanel;
    protected JPanel panBusqueda;
    protected ListaSeleccionable listaSeleccionable;
}
