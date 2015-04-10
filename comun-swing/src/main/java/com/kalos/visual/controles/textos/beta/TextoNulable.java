// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.textos.beta;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import com.kalos.recursos.CargaRecursos;
import com.kalos.recursos.Recursos;

public class TextoNulable extends JPanel {

    public TextoNulable(int i, boolean flag) {
	this(i);
	habilitado = flag;
	boton.setEnabled(flag);
	boton.setSelected(true);
	texto.setEditable(false);
	if (!flag)
	    boton.setVisible(false);
    }

    public TextoNulable(int i) {
	habilitado = false;
	boton = new JToggleButton(new ImageIcon(CargaRecursos.getResourceURL("img/quita16.gif")));
	texto = new JTextField(i);
	boton.setPreferredSize(new Dimension(22, 22));
	boton.setMaximumSize(new Dimension(22, 22));
	setLayout(new BorderLayout());
	add(texto, "Center");
	add(boton, "East");
	boton.addMouseListener(new MouseAdapter() {

	    public void mouseClicked(MouseEvent mouseevent) {
		JToggleButton jtogglebutton = (JToggleButton) mouseevent.getSource();
		texto.setEditable(!jtogglebutton.isSelected());
		if (jtogglebutton.isSelected()) {
		    texto.setText(Recursos.getCadena("nulo"));
		    texto.setEditable(false);
		} else {
		    texto.setText("");
		    texto.setEditable(true);
		}
	    }

	});
    }

    public void setEnabled(boolean flag) {
	boton.setEnabled(habilitado && flag);
    }

    public String getText() {
	if (texto.getText().equals(Recursos.getCadena("nulo")))
	    return null;
	else
	    return texto.getText().toUpperCase();
    }

    public void setText(String s) {
	if (s == null) {
	    texto.setText(Recursos.getCadena("nulo"));
	    texto.setEditable(false);
	    boton.setSelected(true);
	} else {
	    texto.setText(s);
	    boton.setSelected(false);
	    texto.setEditable(habilitado);
	}
    }

    public void requestFocus() {
	texto.requestFocus();
    }

    public JTextField elTexto() {
	return texto;
    }

    private JTextField texto;
    private boolean habilitado;
    private JToggleButton boton;
}
