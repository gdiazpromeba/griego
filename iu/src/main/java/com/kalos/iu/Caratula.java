// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.kalos.recursos.CargaRecursos;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

// Referenced classes of package kalos.iu:
//            D

public class Caratula extends JPanel {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    public void setMensajeProgreso(String s) {
		C = s;
		repaint();
	}

	@SuppressWarnings("rawtypes")
    public Caratula() {
	    setBackground(Color.red);
		comboIdiomaEtiquetas = new JComboBox();
		comboItiomaContenidos = new JComboBox();
		cmbTeclados = new JComboBox();
		botComenzar = new JButton();
		botSelTipog = new JButton();
		C = "";
		ImageIcon imageicon = Recursos.cargador.getImagen("columnas_mediana.jpg");
		imagen = imageicon.getImage();
		setLayout(new BorderLayout());
		setBackground(Color.white);
		((DefaultComboBoxModel) comboIdiomaEtiquetas.getModel()).addElement(Recursos.getCadena("ingles"));
		((DefaultComboBoxModel) comboIdiomaEtiquetas.getModel()).addElement(Recursos.getCadena("castellano"));
		((DefaultComboBoxModel) comboIdiomaEtiquetas.getModel()).addElement(Recursos.getCadena("frances"));
		((DefaultComboBoxModel) comboIdiomaEtiquetas.getModel()).addElement(Recursos.getCadena("portugues"));
		((DefaultComboBoxModel) comboItiomaContenidos.getModel()).addElement(Recursos.getCadena("ingles"));
		((DefaultComboBoxModel) comboItiomaContenidos.getModel()).addElement(Recursos.getCadena("castellano"));
		((DefaultComboBoxModel) comboItiomaContenidos.getModel()).addElement(Recursos.getCadena("frances"));
		try {
			;
			Document document = (new SAXReader()).read(CargaRecursos.getResourceURL("keyboards.xml"));
			Element element = document.getRootElement();
			List<Element> elements = element.elements("keyboard");
			for (Element elem : elements) {
			    ((DefaultComboBoxModel) cmbTeclados.getModel()).addElement(elem.attributeValue("name"))  ; 
            }
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		setLayout(null);
		add(comboIdiomaEtiquetas);
		add(comboItiomaContenidos);
		add(cmbTeclados);
		add(botComenzar);
		add(botSelTipog);
		comboIdiomaEtiquetas.setBounds(380, 275, 140, 20);
		comboItiomaContenidos.setBounds(380, 300, 140, 20);
		cmbTeclados.setBounds(380, 325, 140, 20);
		botSelTipog.setBounds(380, 350, 30, 20);
		botSelTipog.setText("...");
		botComenzar.setBounds(430, 375, 90, 20);
		botComenzar.setText(Recursos.getCadena("comenzar"));
		botComenzar.setEnabled(false);
		comboIdiomaEtiquetas.setEnabled(false);
		comboItiomaContenidos.setEnabled(false);
		botSelTipog.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				muestraEleccionTipografia();
			}

		});
		if (Configuracion.getFont() == null)
			muestraEleccionTipografia();
	}

	private void muestraEleccionTipografia() {
		EleccionTipografia elecTip = new EleccionTipografia();
		elecTip.setModal(true);
		elecTip.setTitle(Recursos.getCadena("tipografias"));
		elecTip.pack();
		elecTip.setLocationRelativeTo((JFrame) SwingUtilities
				.getWindowAncestor(botSelTipog));
		elecTip.setVisible(true);
	}

	public void setValorAlfa(int i) {
		A = i;
	}

	public void paintComponent(Graphics g) {
	    System.out.println("en caratula paintComponent");
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g;
		RenderingHints renderinghints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setRenderingHints(renderinghints);
		g.setColor(Color.white);
//		drawBackground(g);
		g.setFont(new Font("Dialog", 0, 20));
		g.drawString(
				(new StringBuilder()).append("KAL\323S ")
						.append(Configuracion.getVersionNumero()).toString(),
				400, 40);
		g.setFont(new Font("Dialog", 2, 11));
		g.drawString("beautiful Greek software", 378, 55);
		g.setFont(new Font("Dialog", 0, 9));
		if (Configuracion.getNombre() == null)
			g.drawString(Recursos.getCadena("usuario_no_registrado"), 280, 380);
		else
			g.drawString(
					(new StringBuilder())
							.append(Recursos.getCadena("due\361o_registrado"))
							.append(" : ").append(Configuracion.getNombre())
							.toString(), 280, 380);
		g.drawString("1995-2011 Kallistos Corporation", 280, 390);
		g.setFont(new Font("Dialog", 0, 10));
		g.drawString(Recursos.getCadena("etiquetas"), 313, 287);
		g.drawString(Recursos.getCadena("significados"), 313, 312);
		g.drawString(Recursos.getCadena("teclado"), 313, 342);
		g.drawString(Recursos.getCadena("tipografias"), 313, 367);
		g.setFont(new Font("Dialog", 1, 9));
		g.drawString(C, 200, 230);
	}

//	public void drawBackground(Graphics g) {
//	    System.out.println("en caratula paintComponent");
//		int i = getWidth();
//		int j = getHeight();
//		int k = imagen.getWidth(this);
//		int l = imagen.getHeight(this);
//		for (int i1 = 0; i1 < i; i1 += k) {
//			for (int j1 = 0; j1 < j; j1 += l)
//				g.drawImage(imagen, i1, j1, this);
//
//		}
//
//	}

//	public void paint(Graphics g) {
//		drawBackground(g);
//		super.paint(g);
//	}
	
	public void habilitacionBotonComenzar(boolean habilitacion){
	    this.botComenzar.setEnabled(habilitacion);
	}
	
	public void setTextoBotonComenzar(String text){
	    this.botComenzar.setText(text);
	}
	
	public void addListenerToBotonComenzar(ActionListener listenComenzar){
	    this.botComenzar.addActionListener(listenComenzar);
	}



	public JComboBox comboIdiomaEtiquetas;
	public JComboBox comboItiomaContenidos;
	public JComboBox cmbTeclados;
	private JButton botComenzar;
	public JButton botSelTipog;
	Image imagen;
	private String C;
	int A;
}
