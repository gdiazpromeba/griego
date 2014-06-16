package kalos.utilidades.abms.utilidades.dialogs;

import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kalos.recursos.Recursos;
import kalos.visual.controles.ventanas.AceptarCancelar;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


/**
 * ventana que acepta un rango para reordenar las listas de los ABM's
 * @author gdiaz
 *
 */
public class VentanaReordenar extends AceptarCancelar {
	
	private boolean acepto;

	private JPanel panel;
	private JTextField actualDesde=new JTextField();
	private JTextField actualHasta=new JTextField();
	private JTextField deseadoDesde=new JTextField();
	private JLabel errores=new JLabel();
	
	
	public VentanaReordenar(JFrame owner){
		super(owner);
		setTitle(Recursos.getCadena("reordenar"));
		panel=getPanelCentral();
		disposicion();
	}
	
	protected void aceptar() {
		if (validacion()) {
			acepto = true;
			setVisible(false);
		}
	}
	
	protected void cancelar() {
			acepto = false;
			setVisible(false);
	}	
	
	

	/**
	 * dispone los controles
	 */
	private void disposicion() {
		FormLayout layout = new FormLayout("pref, 3dlu, 50dlu, 3dlu, 50dlu", // cols
				"20dlu, fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu" //rows
		);

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		// 	DefaultFormBuilder builder = 
		//    new DefaultFormBuilder(layout, new FormDebugPanel());

		CellConstraints cc = new CellConstraints();

		layout.setRowGroups(new int[][] { { 2,4,6} });
		layout.setColumnGroups(new int[][] { { 3,5 } });

		builder.addLabel(Recursos.getCadena("desde_codigo_actual"), cc.xy(1, 2));
		builder.add(actualDesde, cc.xy(3, 2));
		
		builder.addLabel(Recursos.getCadena("hasta_codigo_actual"), cc.xy(1, 4));
		builder.add(actualHasta, cc.xy(3, 4));
		
		builder.addLabel(Recursos.getCadena("codigo_inicial_deseado"), cc.xy(1, 6));
		builder.add(deseadoDesde, cc.xy(3, 6));

		panel.setLayout(new BorderLayout());
		panel.add(builder.getPanel());
	}
	
	private int desdeActual;
	private int hastaActual;
	private int desdeDeseado;
	
	private boolean validacion(){
		String dca=actualDesde.getText();
		Scanner scan1=new Scanner(dca);
		if (scan1.hasNextInt()){
			desdeActual=scan1.nextInt();
		}else{
			errores.setText("código actual desde incorrecto");
			return false;
		}
		
		String hca=actualHasta.getText();
		Scanner scan2=new Scanner(hca);
		if (scan2.hasNextInt()){
			hastaActual=scan2.nextInt();
		}else{
			errores.setText("código actual hasta incorrecto");
			return false;
		}

		
		String hcd=deseadoDesde.getText();
		Scanner scan3=new Scanner(hcd);
		if (scan3.hasNextInt()){
			desdeDeseado=scan3.nextInt();
		}else{
			errores.setText("código deseado desde incorrecto");
			return false;
		}
		return true;
		
	}


	/**
	 * @return Returns the desdeActual.
	 */
	public int getDesdeActual() {
		return desdeActual;
	}


	/**
	 * @param desdeActual The desdeActual to set.
	 */
	public void setDesdeActual(int desdeActual) {
		this.desdeActual = desdeActual;
	}


	/**
	 * @return Returns the desdeDeseado.
	 */
	public int getDesdeDeseado() {
		return desdeDeseado;
	}


	/**
	 * @param desdeDeseado The desdeDeseado to set.
	 */
	public void setDesdeDeseado(int desdeDeseado) {
		this.desdeDeseado = desdeDeseado;
	}


	/**
	 * @return Returns the hastaActual.
	 */
	public int getHastaActual() {
		return hastaActual;
	}


	/**
	 * @param hastaActual The hastaActual to set.
	 */
	public void setHastaActual(int hastaActual) {
		this.hastaActual = hastaActual;
	}


	/**
	 * @return Returns the acepto.
	 */
	public boolean isAcepto() {
		return acepto;
	}
	
	

}
