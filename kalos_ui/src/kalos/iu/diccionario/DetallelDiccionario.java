package kalos.iu.diccionario;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kalos.beans.EntradaDiccionario;
import kalos.beans.Significado;
import kalos.enumeraciones.Idioma;
import kalos.recursos.CadenasEnum;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;
import kalos.visual.controles.util.TipografiaCambiable;
import kalos.visual.controles.util.UtilTipografias;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class DetallelDiccionario extends JPanel implements TipografiaCambiable{

//	private Logger logger = Logger.getLogger(this.getClass().getName());

	private JLabel forma=new JLabel();
	private JLabel tipoPalabra=new JLabel();
	private JTextArea significado=new JTextArea();
	
	public DetallelDiccionario() {
        significado.setLineWrap(true);
        significado.setWrapStyleWord(true);
		disposicion();
	}


	public void setEntradaDiccionario(EntradaDiccionario entrada){
	    forma.setText(entrada.getNormal());
	    tipoPalabra.setText(CadenasEnum.getCadena(entrada.getTipoPalabra()));
	    Significado sig=entrada.getSignificados().get(Idioma.getEnum(Configuracion.getIdiomaSignificados()));
	    significado.setText(sig.getValor());
	}
	
	
	public void cambiaTamañoFont(float tamaño){
	       UtilTipografias.cambiaTamañoEnComponente(significado, tamaño);
	       UtilTipografias.cambiaTamañoEnComponente(forma, tamaño);
	       repaint();
	       revalidate();
	}
	
	public void disposicion() {
		FormLayout layout = new FormLayout("pref, 3dlu, 40dlu, fill:1dlu:grow(1)",  // cols
				"fill:pref ,3dlu, fill:pref , 3dlu, fill:1dlu:grow(0.9)" // rows
		);

		PanelBuilder builder = new PanelBuilder(layout);

//		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
//			new FormDebugPanel());

		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		layout.setRowGroups(new int[][] { { 1, 3, 5 } });

		builder.addLabel(Recursos.getCadena("forma"), cc.xy(1, 1));
		builder.add(forma, cc.xyw(3, 1, 2)); 
		builder.addLabel(Recursos.getCadena("tipo_de_palabra"), cc.xy(1, 3));
		builder.add(tipoPalabra, cc.xy(3, 3));
		builder.addLabel(Recursos.getCadena("significado"), cc.xy(1, 5));
		JScrollPane scpSignificado=new JScrollPane(significado, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.add(scpSignificado, cc.xyw(3, 5, 2));

		setLayout(new BorderLayout());
		add(builder.getPanel());

	}
	

}
