// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.botones;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.kalos.recursos.CargaRecursos;

public class BotonBuscar extends JButton {

    public BotonBuscar() {
	  super(new ImageIcon(CargaRecursos.getResourceURL("img/buscar16.gif")));
	  setPreferredSize(new Dimension(22, 22));
	  setMaximumSize(new Dimension(22, 22));
	  this.boton=this;
	  manejaEventos();
    }
    //para el adapter
    private BotonBuscar boton;

    private void manejaEventos() {
	addComponentListener(new ComponentAdapter() {
	    public void componentResized(ComponentEvent componentevent) {
		boton.setSize(new Dimension(22, 22));
	    }
	});
    }
}
