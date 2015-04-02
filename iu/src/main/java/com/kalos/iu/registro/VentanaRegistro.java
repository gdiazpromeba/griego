// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.iu.registro;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.kalos.datos.gerentes.GerenteSeguridad;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;

import org.apache.log4j.Logger;

/**
 * Similar a la VentanaMolesta, pero aparece específicamente cuando se la llama por menú
 * @author gdiaz
 *
 */
public class VentanaRegistro extends JDialog {

	public VentanaRegistro(GerenteSeguridad gerSeg) {
		panelRegistro = new PanelRegistro();
		logger = Logger.getLogger(VentanaRegistro.class);
		gerenteSeguridad = gerSeg;
		setTitle(Recursos.getCadena("registro"));
		disposicion();
		panelRegistro.butIngresarClave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				ingresaClave();
			}

		});
		panelRegistro.todaviaNo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				quitaMolestia();
			}

		});
	}

	public void muestraMolestia(JPanel jpanel) {
		Configuracion.aumentaVecesVentana();
		if (Configuracion.getVecesVentana() % 3L == 0L) {
			JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(jpanel);
			panelRegistro.reinicializaClave();
			JDialog jdialog = new JDialog();
			jdialog.setLayout(new BorderLayout());
			jdialog.setUndecorated(true);
			jdialog.setModal(true);
			jdialog.add(this);
			jdialog.setLocationRelativeTo(jframe);
			jdialog.setBounds(jframe.getWidth() / 2, jframe.getHeight() / 2, jdialog.getWidth(),
					jdialog.getHeight());
			jdialog.pack();
			panelRegistro.butIngresarClave.setEnabled(false);
			repaint();
			Timer timer = new Timer();
			TimerTask timertask = new TimerTask() {

				public void run() {
					panelRegistro.butIngresarClave.setEnabled(true);
					repaint();
				}

			};
			timer.schedule(timertask, 5000L);
			jdialog.setVisible(true);
		}
	}

	public void quitaMolestia() {
		dispose();
	}

	void disposicion() {
		setLayout(new BorderLayout());
		add(panelRegistro);
		repaint();
	}

	private void ingresaClave() {
		logger.debug("en ingresaClave");
		panelRegistro.getClave();
		logger.debug("registrando ...");
		boolean flag = gerenteSeguridad.registra(panelRegistro.getNombre(), panelRegistro.getClave());
		logger.debug((new StringBuilder()).append("registró con activación=").append(flag)
				.toString());
		if (flag) {
			JOptionPane.showMessageDialog(this, Recursos.getCadena("gracias_por_haber_comprado"));
			logger.debug((new StringBuilder()).append("estableciendo el nombre ")
					.append(panelRegistro.getNombre()).toString());
			Configuracion.setNombre(panelRegistro.getNombre());
			logger.debug("quitando molestia");
			quitaMolestia();
		} else {
			logger.debug("no hubo activaci\363n");
			JOptionPane.showMessageDialog(this, Recursos.getCadena("clave_incorrecta"));
		}
	}

	public GerenteSeguridad getGerenteSeguridad() {
		return gerenteSeguridad;
	}

	private PanelRegistro panelRegistro;
	private GerenteSeguridad gerenteSeguridad;
	private Logger logger;
}
