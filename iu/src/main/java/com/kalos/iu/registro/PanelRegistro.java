package com.kalos.iu.registro;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.kalos.recursos.Recursos;
import com.kalos.visual.controles.menues.FabricaMenues;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
/**
 * panel para registratse. Es utilizado (distintas instancias) tanto por la VentanaMolesta como por la VentanaRegistro
 * @author gdiaz
 *
 */
@SuppressWarnings("serial")
public class PanelRegistro extends JPanel {
	 public JTextField nombre = new JTextField(4);

	 public  JTextField txtClave1 = new JTextField(4);

	 public  JTextField txtClave2 = new JTextField(4);

	 public JTextField txtClave3 = new JTextField(4);

	 public  JTextField txtClave4 = new JTextField(4);
	 
     public JButton butIngresarClave = new JButton(Recursos.getCadena("ingresar_clave_activacion"));
		
     public JButton todaviaNo = new JButton(Recursos.getCadena("todavia_no"));
     
     private Logger logger=Logger.getLogger(PanelRegistro.class.getName());

     
     public PanelRegistro(){
    	 disposicion();
     }
     
     /**
      * devuelve la clave formada por los 4 texos concatenados
      * @return
      */
     public String getClave(){
 		StringBuffer clave=new StringBuffer();
		clave.append(txtClave1.getText());
		clave.append("-");
		clave.append(txtClave2.getText());
		clave.append("-");
		clave.append(txtClave3.getText());
		clave.append("-");
		clave.append(txtClave4.getText());
		return clave.toString();
     }
     
     public void reinicializaClave(){
			txtClave1.setText("");
			txtClave2.setText("");
			txtClave3.setText("");
			txtClave4.setText("");
     }
     
     public String getNombre(){
    	 return nombre.getText();
     }
     
 	void disposicion() {
		setLayout(new BorderLayout());

		FormLayout layout = new FormLayout(
				"pref, 3dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu", // cols
				"15dlu, 15dlu, 3dlu, 15dlu, 15dlu, 3dlu, 15dlu" // rows
		);

		layout.setColumnGroups(new int[][] { {1, 3, 5, 7} });
		
//		DefaultFormBuilder builder = new DefaultFormBuilder(layout, new FormDebugPanel());
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();

		builder.addLabel(Recursos.getCadena("su_nombre"), cc.xyw(1, 1, 3));
		builder.add(nombre, cc.xyw(1, 2, 9));

		builder.addLabel(Recursos.getCadena("su_clave_de_activacion"), cc.xyw(1, 4, 7));
		builder.add(txtClave1, cc.xy(1, 5));
		builder.add(txtClave2, cc.xy(3, 5));
		builder.add(txtClave3, cc.xy(5, 5));
		builder.add(txtClave4, cc.xy(7, 5));

		builder.add(butIngresarClave, cc.xyw(1, 7, 3));
		builder.add(todaviaNo, cc.xyw(5, 7, 3));
		
		

		FabricaMenues.adjuntaMenuCopiarPegar(txtClave1, false, true);
		FabricaMenues.adjuntaMenuCopiarPegar(txtClave2, false, true);
		FabricaMenues.adjuntaMenuCopiarPegar(txtClave3, false, true);
		FabricaMenues.adjuntaMenuCopiarPegar(txtClave4, false, true);

		add(builder.getPanel());
		setBorder(BorderFactory.createLineBorder(Color.black, 1));
		repaint();
		// revalidate();
	} 
 	
 	
 	
     
	
}
