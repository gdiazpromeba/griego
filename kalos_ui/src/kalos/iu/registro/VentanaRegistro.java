/*
 * Created on Apr 11, 2005
 */
package kalos.iu.registro;

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

import org.apache.log4j.Logger;

import kalos.datos.gerentes.GerenteSeguridad;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;

/**
 * @author D9104575
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class VentanaRegistro extends JDialog {

    private PanelRegistro panelRegistro=new PanelRegistro();

	private GerenteSeguridad gerenteSeguridad;
	
	JPanel panelTransparente=new JPanel();
	private Logger logger=Logger.getLogger(VentanaRegistro.class.getName());

	public VentanaRegistro(GerenteSeguridad gerenteSeguridad) {
		this.gerenteSeguridad = gerenteSeguridad;
		this.setTitle(Recursos.getCadena("registro"));
		disposicion();
		panelRegistro.butIngresarClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ingresaClave();
			}
		});
		
		
		panelRegistro.todaviaNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				quitaMolestia();
			}
		});		
		

	}

	public void muestraMolestia(JPanel panel) {
		Configuracion.aumentaVecesVentana();
		if ((Configuracion.getVecesVentana() %3) == 0){
			JFrame fra = (JFrame) SwingUtilities.getWindowAncestor(panel);
			// quitaMolestia(panel);
			// this.setVisible(true);
			panelRegistro.reinicializaClave();
			JDialog ventana=new JDialog();
			ventana.setLayout(new BorderLayout());
			ventana.setUndecorated(true);
			ventana.setModal(true);
			ventana.add(this);
			ventana.setLocationRelativeTo(fra);
			ventana.setBounds(fra.getWidth()/2, fra.getHeight()/2, ventana.getWidth(), ventana.getHeight());
			ventana.pack();
			

			
			panelRegistro.todaviaNo.setEnabled(false);
			repaint();
			Timer timer=new Timer();
			TimerTask tarea=new TimerTask(){
				public void run(){
					panelRegistro.todaviaNo.setEnabled(true);
					repaint();
				}
			};
			timer.schedule(tarea, 5000);
			
			
			ventana.setVisible(true);
		}
	}

	/**
	 * quita la pantalla de la capa superior del panel de contenido del JFrame
	 * la aplicación
	 * 
	 * @param panel
	 */
	public void quitaMolestia() {
	  dispose();
	}

	void disposicion() {
		setLayout(new BorderLayout());
		add(panelRegistro);
		repaint();
		// revalidate();
	}

	// public VentanaMolesta(JFrame padre){
	// super(Recursos.getCadena("registro"),
	// FabricaDeIconos.getImageIcon("item_configuracion_despues"));
	// recargaPanel();

	//        
	// }

	private void ingresaClave() {
		logger.debug("en ingresaClave");
		panelRegistro.getClave();
		logger.debug("registrando ...");
		boolean activacion=gerenteSeguridad.registra(panelRegistro.getNombre(), panelRegistro.getClave());
		logger.debug("registró con activación=" + activacion);
		if (activacion){
			JOptionPane.showMessageDialog(this, Recursos.getCadena("gracias_por_haber_comprado"));
			logger.debug("estableciendo el nombre "+ panelRegistro.getNombre());
			Configuracion.setNombre(panelRegistro.getNombre());
			logger.debug("quitando molestia");
			quitaMolestia();
		}else{
			logger.debug("no hubo activación");
			JOptionPane.showMessageDialog(this, Recursos.getCadena("clave_incorrecta"));
		}
	}

	/**
	 * @return the gerenteSeguridad
	 */
	public GerenteSeguridad getGerenteSeguridad() {
		return gerenteSeguridad;
	}
}
