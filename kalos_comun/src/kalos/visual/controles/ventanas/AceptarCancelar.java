// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.visual.controles.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import kalos.recursos.Recursos;

import com.jgoodies.forms.builder.ButtonBarBuilder;

public abstract class AceptarCancelar extends JDialog {

    private void disposicion() {
	ButtonBarBuilder buttonbarbuilder = new ButtonBarBuilder();
	buttonbarbuilder.addGlue();
	buttonbarbuilder.addGriddedButtons(new JButton[] { botAceptar, botCancelar });
	buttonbarbuilder.setDefaultDialogBorder();
	setLayout(new BorderLayout());
	add(buttonbarbuilder.getPanel(), "South");
	add(panel, "Center");
	botAceptar.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent actionevent) {
		aceptar();
		if (acepta)
		    panel.setVisible(false);
	    }

	});
	botCancelar.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent actionevent) {
		cancelar();
		panel.setVisible(false);
	    }

	});
	addWindowListener(new WindowAdapter() {

	    public void windowClosing(WindowEvent windowevent) {
		cancelar();
	    }

	});
	tieneContainer = true;
    }

    public AceptarCancelar(JFrame jframe) {
	super(jframe);
	botAceptar = new JButton(Recursos.getCadena("aceptar"));
	botCancelar = new JButton(Recursos.getCadena("cancelar"));
	panel = new JPanel();
	tieneContainer = false;
	disposicion();
    }

    public AceptarCancelar() {
	botAceptar = new JButton(Recursos.getCadena("aceptar"));
	botCancelar = new JButton(Recursos.getCadena("cancelar"));
	panel = new JPanel();
	tieneContainer = false;
	disposicion();
    }

    public boolean isAcepto() {
	return acepta;
    }

    protected abstract void aceptar();

    protected abstract void cancelar();

    public void setLayout(LayoutManager layoutmanager) {
	if (!tieneContainer) {
	    Container container = null;
	    try {
		container = getContentPane();
	    } catch (Exception exception) {
	    }
	    if (container != null)
		container.setLayout(layoutmanager);
	    else
		super.setLayout(layoutmanager);
	} else {
	    panel.setLayout(layoutmanager);
	}
    }

    public Component add(Component component) {
	if (!tieneContainer) {
	    if (getContentPane() != null)
		return super.getContentPane().add(component);
	    else
		return super.add(component);
	} else {
	    return panel.add(component);
	}
    }

    public void add(Component component, Object obj) {
	if (!tieneContainer) {
	    boolean flag = true;
	    try {
		super.getContentPane().add(component, obj);
	    } catch (Exception exception) {
		flag = false;
	    }
	    if (!flag)
		super.add(component, obj);
	} else {
	    panel.add(component, obj);
	}
    }

    public void setAceptarhabilitado(boolean flag) {
	botAceptar.setEnabled(flag);
    }

    public JPanel getPanelCentral() {
	return panel;
    }

    JTextPane textPane;
    protected JButton botAceptar;
    protected JButton botCancelar;
    private JPanel panel;
    protected boolean acepta;
    private boolean tieneContainer;
}
