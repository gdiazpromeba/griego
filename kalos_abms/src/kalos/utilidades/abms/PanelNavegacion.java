package kalos.utilidades.abms;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import kalos.recursos.Recursos;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Panel usado en los ABM's, que contiene los botones de navegación
 * (adelante, atrás, etc) 
 * @author gdiaz
 *
 */
public class PanelNavegacion extends JPanel {
	
	protected JButton butSiguiente = new JButton(Recursos.cargador.getImagen("StepForward16.gif"));
	protected JButton butAnterior = new JButton(Recursos.cargador.getImagen("StepBack16.gif"));
	protected JButton butSiguienteSalto = new JButton(Recursos.cargador.getImagen("FastForward16.gif"));
	protected JButton butAnteriorSalto = new JButton(Recursos.cargador.getImagen("Rewind16.gif"));
	
	/**
	 * habilita o inhabilita todos los botones
	 * @param valor
	 */
	public void habilitaNavegacion(boolean valor){
		butAnterior.setEnabled(valor);
		butAnteriorSalto.setEnabled(valor);
		butSiguiente.setEnabled(valor);
		butSiguienteSalto.setEnabled(valor);
	}
	
	
	public PanelNavegacion(){
		disposicion();
		butAnterior.setMnemonic(KeyEvent.VK_LEFT);
		butSiguiente.setMnemonic(KeyEvent.VK_RIGHT);
	}
	
	/**
	 * dispone los controles en el panel
	 */
	public void disposicion(){
	 	FormLayout layout=new FormLayout(
	 			"pref, 3dlu, pref, 3dlu, pref, 3dlu, pref", // cols
	 		    "fill:15dlu" // rows
	 	);
	 	
	     PanelBuilder builder = new PanelBuilder(layout);
	     builder.setDefaultDialogBorder();
	     
//	 	DefaultFormBuilder builder = 
//	    new DefaultFormBuilder(layout, new FormDebugPanel());
	     
	     CellConstraints cc = new CellConstraints();
	     
	     layout.setRowGroups(new int[][]{{1}});
	     layout.setColumnGroups(new int[][]{{1,3,5,7}});

	     builder.add(butAnteriorSalto,                            cc.xy(1,  1));
	     builder.add(butAnterior,                                 cc.xy(3,  1));
	     builder.add(butSiguiente,                                cc.xy(5,  1));
	     builder.add(butSiguienteSalto,                           cc.xy(7,  1));

	     this.setLayout(new BorderLayout());
	     this.add(builder.getPanel());
		
	}

}
