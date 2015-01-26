// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.iu.registro;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import kalos.C.A;
import kalos.C.F;
import kalos.E.E.KA;
import kalos.datos.gerentes.GerenteSeguridad;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;

import org.apache.log4j.Logger;

// Referenced classes of package kalos.iu.A:
//            C

public class VentanaMolesta extends JPanel {

	public VentanaMolesta(GerenteSeguridad gs) {
		panelRegistro = new PanelRegistro();
		textArea = new JTextArea();
		A = new JPanel();
		logger = Logger.getLogger(this.getClass().getName());
		logger.info("instanciando VentanaMolesta");
		gerenteSeguridad = gs;
		disposicion();
		panelRegistro.butIngresarClave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				panelRegistro.B(A);
			}

		});
		panelRegistro.todaviaNo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.quitaMolestia();
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
			jdialog.setBounds(jframe.getWidth() / 2, jframe.getHeight() / 2,
					jdialog.getWidth(), jdialog.getHeight());
			jdialog.pack();
			panelRegistro.B.setEnabled(false);
			repaint();
			Timer timer = new Timer();
			TimerTask timertask = new TimerTask() {

				public void run() {
					kalos.iu.A.B.setListaSeleccionable(A).panelRegistro.setEnabled(true);
					A.repaint();
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

	void disposicion() {
		setLayout(new BorderLayout());
		FormLayout formlayout = new FormLayout("pref", "pref, 3dlu, pref");
		textArea.setText(Recursos.getCadena("mensaje_molesto"));
		textArea.setBackground(getBackground());
		textArea.setForeground(Color.black);
		textArea.setEditable(false);
		PanelBuilder panelbuilder = new PanelBuilder(formlayout);
		panelbuilder.setDefaultDialogBorder();
		CellConstraints cellconstraints = new CellConstraints();
		panelbuilder.add(new JScrollPane(textArea), cellconstraints.xy(1, 1));
		panelbuilder.add(panelRegistro, cellconstraints.xy(1, 3));
		add(panelbuilder.getPanel());
		setBorder(BorderFactory.createLineBorder(Color.black, 1));
		repaint();
	}

	private void A() {
		panelRegistro.getClave();
		boolean flag = gerenteSeguridad.registra(panelRegistro.getNombre(),
				panelRegistro.getClave());
		if (flag) {
			JOptionPane.showMessageDialog(this,
					Recursos.getCadena("gracias_por_haber_comprado"));
			Configuracion.setNombre(panelRegistro.getNombre());
			quitaMolestia();
		} else {
			JOptionPane
					.showMessageDialog(this, Recursos.getCadena("clave_incorrecta"));
		}
	}

	static void B(B b) {
		b.A();
	}

	static PanelRegistro A(B b) {
		return b.B;
	}

	private PanelRegistro panelRegistro;
	private GerenteSeguridad gerenteSeguridad;
	JTextArea textArea;
	JPanel A;
	private Logger logger;
}
