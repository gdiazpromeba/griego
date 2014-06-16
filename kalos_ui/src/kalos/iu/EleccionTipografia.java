package kalos.iu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kalos.operaciones.OpPalabras;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class EleccionTipografia extends JDialog {
	
	JTextArea explicacion=new JTextArea(4,30);
	JList fonts=new JList();
	JTextArea texto=new JTextArea(4,30);
	JButton aceptar=new JButton(Recursos.getCadena("aceptar"));
	boolean aceptable=false;
	
	public EleccionTipografia(){
		explicacion();
		disposicion();
		textoGriego();
		tipografias();
		evaluarFont();
		fonts.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent ev){
				String nombre=(String)fonts.getSelectedValue();
				texto.setFont(new Font(nombre, Font.PLAIN, 12));
				evaluarFont();
			}
		});
		aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				Configuracion.setFont(texto.getFont());
				dispose();
			}
		});
		
		fuerzaSeleccion();
	}
	
	private void fuerzaSeleccion(){
		Font font=Configuracion.getFont();
		if (font!=null && aceptable){
			fonts.setSelectedValue(font.getName(), true);
		}
	}
	
	private void evaluarFont(){
		Font font=texto.getFont();
		aceptable=font.canDisplay('\u1f87');
		aceptar.setEnabled(aceptable);
	}
	
	
	public void disposicion() {
		FormLayout layout = new FormLayout(
				"200dlu:grow(.9), fill:pref", // cols
				"fill:pref ,3dlu, fill:66dlu ,3dlu, fill:60dlu:grow(.9), 3dlu, fill:15dlu" // rows
		);

		PanelBuilder builder = new PanelBuilder(layout);

//		DefaultFormBuilder builder = new DefaultFormBuilder(layout, new FormDebugPanel());

		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

//		layout.setColumnGroups(new int[][] { { 3, 7 } });
//		layout.setRowGroups(new int[][] { { 3, 7 } });

		builder.add(explicacion, cc.xyw(1, 1, 2));
		builder.add(new JScrollPane(fonts),       cc.xyw(1, 3, 2));
		builder.add(texto,       cc.xyw(1, 5, 2));		
		builder.add(aceptar,     cc.xy(2, 7));


		setLayout(new BorderLayout());
		add(builder.getPanel());

	}
	
	private void textoGriego(){
		StringBuffer sb=new StringBuffer();
		sb.append("\tColumn VI\n");
		sb.append("\n");
		sb.append("Silenus:\t" + OpPalabras.strBetaAUnicode("KAI\\ PW=J A)KOU/SW MHDENO\\J FWNH\\N KLU/WN") + ";\n");
		sb.append("Chorus:\t" + OpPalabras.strBetaAUnicode("E)MOI\\ PIQOU=") + ".\n");
		
		texto.setText(sb.toString());
		texto.setFont(Configuracion.getFont());
	}
	
	private void explicacion(){
		explicacion.setText(Recursos.getCadena("explicacion_tipografia"));
		explicacion.setBackground(this.getBackground());
	}

	
	private void tipografias() {
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		Vector<String> visFonts = new Vector<String>(fontNames.length);
		for (int i = 0; i < fontNames.length; i++) {
			Font f = new Font(fontNames[i], Font.PLAIN, 12);
			if (f.canDisplay('a')) {
				// Display only fonts that have the alphabetic characters.
				// On my machine there are almost 20 fonts (eg, Wingdings)
				// that don't display text.
				visFonts.add(fontNames[i]);
			} 
		}
		fonts.setModel(new DefaultComboBoxModel(visFonts));
	}
}
