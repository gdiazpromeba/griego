// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.iu.registro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import kalos.datos.gerentes.GerenteSeguridad;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

// Referenced classes of package kalos.iu.A:
//            C

public class VentanaMolesta extends JPanel {

	public VentanaMolesta(GerenteSeguridad ka) {
		panelRegistro = new PanelRegistro();
		txtArea = new JTextArea();
		logger = Logger.getLogger(VentanaMolesta.class);
		logger.info("instanciando VentanaMolesta");
		gerenteSeguridad = ka;
		B();
		panelRegistro.butIngresarClave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				muestraMolestia(panelRegistro);
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
		JDialog jdialog = (JDialog) SwingUtilities.getWindowAncestor(this);
		jdialog.dispose();
	}

	void B() {
		setLayout(new BorderLayout());
		FormLayout formlayout = new FormLayout("pref", "pref, 3dlu, pref");
		txtArea.setText(Recursos.getCadena("mensaje_molesto"));
		txtArea.setBackground(getBackground());
		txtArea.setForeground(Color.black);
		txtArea.setEditable(false);
		PanelBuilder panelbuilder = new PanelBuilder(formlayout);
		panelbuilder.setDefaultDialogBorder();
		CellConstraints cellconstraints = new CellConstraints();
		panelbuilder.add(new JScrollPane(txtArea), cellconstraints.xy(1, 1));
		panelbuilder.add(panelRegistro, cellconstraints.xy(1, 3));
		add(panelbuilder.getPanel());
		setBorder(BorderFactory.createLineBorder(Color.black, 1));
		repaint();
	}

	private void ingresaClave() {
		panelRegistro.getClave();
		boolean flag = gerenteSeguridad.registra(panelRegistro.getNombre(),
				panelRegistro.getClave());
		if (flag) {
			JOptionPane.showMessageDialog(this, Recursos.getCadena("gracias_por_haber_comprado"));
			Configuracion.setNombre(panelRegistro.getNombre());
			quitaMolestia();
		} else {
			JOptionPane.showMessageDialog(this, Recursos.getCadena("clave_incorrecta"));
		}
	}

	private PanelRegistro panelRegistro;
	private GerenteSeguridad gerenteSeguridad;
	JTextArea txtArea;
	private Logger logger;
}
