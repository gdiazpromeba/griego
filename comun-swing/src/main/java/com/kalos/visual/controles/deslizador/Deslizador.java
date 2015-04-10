// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.deslizador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class Deslizador extends JPanel {
    class EscuchaCambiosDocumento implements DocumentListener {

	public void insertUpdate(DocumentEvent documentevent) {
	    valorCambio();
	}

	public void removeUpdate(DocumentEvent documentevent) {
	    valorCambio();
	    ;
	}

	public void changedUpdate(DocumentEvent documentevent) {
	    valorCambio();
	}

    }

    public Deslizador() {
	text = new JTextField("12");
	button = new JToggleButton("...");
	dialog = new JDialog();
	slider = new JSlider(1, 8, 40, 12);
	dialog.setUndecorated(true);
	D = 12;
	dialog.add(slider);
	button.addMouseListener(new MouseAdapter() {

	    public void mouseClicked(MouseEvent mouseevent) {
		JToggleButton jtogglebutton = (JToggleButton) mouseevent.getSource();
		if (jtogglebutton.isSelected()) {
		    dialog.setSize(new Dimension(20, 80));
		    Point point = jtogglebutton.getLocationOnScreen();
		    dialog.setLocation((int) point.getX(), (int) point.getY() - 80);
		    dialog.setVisible(true);
		    slider.requestFocus();
		}
	    }

	});
	slider.addChangeListener(new ChangeListener() {

	    public void stateChanged(ChangeEvent changeevent) {
		JSlider jslider = (JSlider) changeevent.getSource();
		text.setText(Integer.toString(jslider.getValue()));
	    }

	});
	text.getDocument().addDocumentListener(new EscuchaCambiosDocumento());
	dialog.addWindowListener(new WindowAdapter() {

	    public void windowDeactivated(WindowEvent windowevent) {
		button.setSelected(false);
		dialog.dispose();
	    }

	});
	disposicion();
    }

    public void disposicion() {
	FormLayout formlayout = new FormLayout("15dlu,1dlu,10dlu", "fill:15dlu");
	PanelBuilder panelbuilder = new PanelBuilder(formlayout);
	CellConstraints cellconstraints = new CellConstraints();
	panelbuilder.add(text, cellconstraints.xy(1, 1));
	panelbuilder.add(button, cellconstraints.xy(3, 1));
	setLayout(new BorderLayout());
	add(panelbuilder.getPanel());
    }

    private void valorCambio() {
	Scanner scanner = new Scanner(text.getText());
	if (!scanner.hasNextInt()) {
	    text.setBackground(Color.red);
	} else {
	    text.setBackground(Color.white);
	    int i = D;
	    D = scanner.nextInt();
	    if (D > slider.getMaximum())
		D = slider.getMaximum();
	    if (D < slider.getMinimum())
		D = slider.getMinimum();
	    firePropertyChange("valor", i, D);
	}
	scanner.close();
    }

    public void setValor(int i) {
	slider.setValue(i);
	text.setText(Integer.toString(i));
    }

    public int getValor() {
	return D;
    }

    // static JDialog D(A a) {
    // return a.E;
    // }

    // static JSlider C(A a) {
    // return a.B;
    // }
    //
    // static JTextField B(A a) {
    // return a.A;
    // }
    //
    // static JToggleButton A(A a) {
    // return a.C;
    // }
    //
    // static void E(A a) {
    // a.A();
    // }

    private JTextField text;
    private JToggleButton button;
    private JDialog dialog;
    private JSlider slider;
    private int D;
}
