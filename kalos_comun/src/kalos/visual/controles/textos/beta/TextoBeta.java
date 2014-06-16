/*
 * Created on 16/03/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.visual.controles.textos.beta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import kalos.enumeraciones.CompLetras;
import kalos.enumeraciones.Espiritu;
import kalos.fonts.ExcepcionTransformacion;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;
import kalos.visual.controles.botones.BotonQuita;
;

/**
 * @author gonzy
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TextoBeta extends JPanel {
	private JTextField txtBeta;
	private  JLabel lblUnicode=new JLabel();
	private boolean autocorrige=true;
	private TextoBeta esta;
	private BotonQuita botNulo=new BotonQuita();
	private JPanel panResto=new JPanel(); //el panel en donde van la etiqueta y el resto
	
	public TextoBeta(int ancho, boolean autocorrige, boolean nulable){
		this(ancho,autocorrige);
		if (nulable) 
			panResto.add(botNulo, BorderLayout.WEST);
		panResto.repaint();

	}

	
	
	public TextoBeta(int ancho, boolean autocorrige){
		this(ancho);
		this.autocorrige=autocorrige;
	}
	
	public TextoBeta(int ancho){
		esta=this;
		txtBeta=new JTextField(ancho);
		this.setLayout(new GridLayout(0, 2));
		panResto.setLayout(new BorderLayout());
		panResto.add(lblUnicode, BorderLayout.CENTER);
		lblUnicode.setPreferredSize(txtBeta.getSize());
		this.add(txtBeta);
		this.add(panResto);
		lblUnicode.setFont(Configuracion.getFont());
		
		txtBeta.getDocument().addDocumentListener(new DocumentListener(){
		  public void insertUpdate(DocumentEvent ev){
	  	muestraEnGriego(txtBeta, lblUnicode);
  		
	  }
  	public void removeUpdate(DocumentEvent ev){
		muestraEnGriego(txtBeta, lblUnicode);
	  }
	  public void changedUpdate(DocumentEvent ev){
		muestraEnGriego(txtBeta, lblUnicode);
	  }			
   });
		
		txtBeta.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent ev){
				correccionAutomatica(txtBeta);
			}
		});
		
		
		botNulo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				txtBeta.setText(Recursos.getCadena("nulo"));
			}
		});
	}
	
	/**
	 * Se activa al perder el foco, y realiza algunas correcciones automáticamente
	 * @param txtBeta
	 */
	public void correccionAutomatica(JTextField txtBeta){
	  if (txtBeta.getText().length()==0) 
      	return;
      else if (txtBeta.getText().equals(Recursos.getCadena("nulo")))
      	return;

		StringBuffer completo=new StringBuffer(OpPalabras.strBetaACompleto(txtBeta.getText().toUpperCase()));
		if (!autocorrige)
			return;
		//corrijo sigma final
		if (completo.charAt(completo.length()-1)==CompLetras.cSigma)
		  completo.setCharAt(completo.length()-1, CompLetras.cSigmaFinal);
		//espíritu suave si no tiene ninguno
		Espiritu espiritu=OpPalabras.getEspiritu(completo.toString());
		if (espiritu==Espiritu.Ninguno)
		  completo=new StringBuffer(OpPalabras.espirituPalabra(completo.toString(), Espiritu.Suave));
		txtBeta.setText(OpPalabras.strCompletoABeta(completo.toString()));
		setFocusable(true);
		
		addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				esta.txtBeta.requestFocus();
				esta.txtBeta.setCaretPosition(esta.txtBeta.getText().length());
			}
		});
	}
	
	/**
	 * Muestra lo que metí en "beta kalos" en le texto de origen
	 * pasado a Unicode griego en la etiqueta de destino.
	 * Utiliza la excepción para poner el control en rojo si algo falla
	 * 
	 * @param origen
	 * @param destino
	 */
	public void muestraEnGriego(JTextField origen, JLabel destino){
		try{
			if (origen.getText().equals(Recursos.getCadena("nulo")))
				return;
			String textoEnBetaKalos=origen.getText().toUpperCase();
			if (textoEnBetaKalos.equals("*")) { //el asterisco no puede traducirse a completo, pero comienza solo al principio de una palabra
				origen.setBackground(Color.white);
				return;
			}
			String textoCompleto=null;
			try{
			  textoCompleto=OpPalabras.strBetaACompleto(textoEnBetaKalos);
			}catch(ExcepcionTransformacion et){
				origen.setBackground(Color.red);
				return;
			}
			if (OpPalabras.getCantidadDeAcentos(textoCompleto)>1){
				origen.setBackground(Color.red);
				return;
			}
				
			//origen.setText(textoEnBetaKalos.substring(0, textoEnBetaKalos.length()-1));
		  destino.setText( OpPalabras.strCompletoAUnicode(textoCompleto));
		  origen.setBackground(Color.white);
		}catch(Exception ex){
			origen.setBackground(Color.red);
		   return;
	    }
	}
	
	public String getText(){
		if (txtBeta.getText().equals(Recursos.getCadena("nulo")))
		  return null;
		else
		  return txtBeta.getText().toUpperCase();
	}
	
	public void setText(String texto){
		if (texto==null)
			txtBeta.setText(Recursos.getCadena("nulo"));
		else
		 txtBeta.setText(texto);
	}
	
	public void requestFocus(){
		txtBeta.requestFocus();
		
	}
	
	public JTextField elTexto(){
      return txtBeta;
	}
	
	
	
}
