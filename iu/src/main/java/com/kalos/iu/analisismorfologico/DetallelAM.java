package com.kalos.iu.analisismorfologico;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.kalos.analisismorfologico.negocio.AMUtil;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.operaciones.OpPalabras;
import com.kalos.recursos.Recursos;
import com.kalos.visual.controles.util.TipografiaCambiable;
import com.kalos.visual.controles.util.UtilTipografias;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class DetallelAM extends JPanel implements TipografiaCambiable{

//	private Logger logger = Logger.getLogger(this.getClass().getName());

	private JLabel formaFlexionada=new JLabel();
	private JTextArea accidentes=new JTextArea();
	private JTextArea significado=new JTextArea();
	
	public DetallelAM() {
	    accidentes.setLineWrap(true);
	    accidentes.setWrapStyleWord(true);
        significado.setLineWrap(true);
        significado.setWrapStyleWord(true);
		disposicion();
	}


	public void setResultadoUniversal(ResultadoUniversal entrada){
	    formaFlexionada.setText(OpPalabras.strCompletoAUnicode(entrada.getFormaAccidentada()));
	    accidentes.setText(new AMUtil().accidentesHablados(entrada));
	    significado.setText(entrada.getSignificado());
	}
	
	
	public void cambiaTamañoFont(float tamaño){
	       UtilTipografias.cambiaTamañoEnComponente(significado, tamaño);
	       UtilTipografias.cambiaTamañoEnComponente(accidentes, tamaño);
	       UtilTipografias.cambiaTamañoEnComponente(formaFlexionada, tamaño);
	       repaint();
	       revalidate();
	}
	
	public void disposicion() {
		FormLayout layout = new FormLayout("pref, 3dlu, 40dlu, fill:1dlu:grow(1)",  // cols
				" fill:pref ,3dlu, fill:1dlu:grow(0.2) , 3dlu, fill:1dlu:grow(0.8)" // rows
		);

		PanelBuilder builder = new PanelBuilder(layout);

//		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
//			new FormDebugPanel());

		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		layout.setRowGroups(new int[][] { { 1, 3, 5 } });

		builder.addLabel(Recursos.getCadena("forma_flexionada"), cc.xy(1, 1));
		builder.add(formaFlexionada, cc.xyw(3, 1, 2)); 
		builder.addLabel(Recursos.getCadena("accidentes"), cc.xy(1, 3));
		JScrollPane scpAccidentes=new JScrollPane(accidentes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.add(scpAccidentes, cc.xyw(3, 3, 2));
		builder.addLabel(Recursos.getCadena("significado"), cc.xy(1, 5));
		JScrollPane scpSignificado=new JScrollPane(significado, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.add(scpSignificado, cc.xyw(3, 5, 2));

		setLayout(new BorderLayout());
		add(builder.getPanel());

	}
	

}
