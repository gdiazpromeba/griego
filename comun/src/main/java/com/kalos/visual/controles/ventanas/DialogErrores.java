/*
 * Created on Nov 20, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.kalos.visual.controles.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author gonzy
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DialogErrores extends JDialog {
	



	/**
	 * @param owner
	 * @param title
	 * @throws java.awt.HeadlessException
	 */
//	public DialogErrores(Frame owner, String title) {
//		super(owner, title);
//	}

	
	private JButton butOK=new JButton(Recursos.getCadena("aceptar"));
	private DialogErrores esta=this;
	private JLabel reporte=new JLabel();
	
	
	
	
	
	private String cadenaStack;
	
	private void inicializaEtiqueta(String cadenaStack){
		StringBuffer sb=new StringBuffer();
		sb.append("<html><body>");
		sb.append(Recursos.getCadena("ayudenos_a_mejorar"));
		sb.append(" <a href=\"#\">");
        sb.append(Recursos.getCadena("reportando_defecto") + "</a>");
		sb.append("</body></html>");
		reporte.setText(sb.toString());
		this.cadenaStack=cadenaStack;
		
		
		
		
		reporte.addMouseListener(new MouseAdapter(){
		  public void mouseClicked(MouseEvent e) {
		  	llamaAMailto();
		  	dispose();
		  }
		});
	}
		
		
		
		
		
		
	
	
	private void llamaAMailto(){
		StringSelection stringSelection = new StringSelection( cadenaStack);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents( stringSelection, null);
	    System.out.println(cadenaStack);
  		StringBuffer sb=new StringBuffer("mailto:info@kalos-software.com");
  		sb.append("?subject=defect in KALÓS");
  		sb.append("&body=Please attach the log file 'kalos.log' found in your Kalos directory. ");
  		sb.append("That would help us find what the problem was. ");
  		sb.append("Also, feel free to add comments of your own to this message,  ");
  		sb.append("indicating how it happened, what you were trying to do, etc. ");
  		try{
  	      BrowserLauncher.openURL(sb.toString());
  		}catch(IOException ex){
  			ex.printStackTrace();
  		}

	}
	

	private void inicializaControles(String claveTitulo){
		Container cnt=this.getContentPane();
		cnt.setLayout(new BorderLayout());
		
		FormLayout layout=new FormLayout(
                "3dlu, 200dlu, pref, 3dlu", // cols
                "15dlu, 3dlu, 15dlu, 3dlu, 15dlu" // rows
        );
		
		
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();

        CellConstraints cc = new CellConstraints();

        builder.addLabel(claveTitulo,   cc.xyw(2,  1, 2));
        builder.add(reporte ,      cc.xy(2, 3));
        builder.add(butOK ,        cc.xy(3, 5));
        
        
        cnt.add(builder.getPanel());
        pack();
        
		butOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				esta.dispose();
			}
		});

		
	
	}
	
	
	/**
	 * @param owner
	 * @param tituloBarra
	 * @param modal
	 * @throws java.awt.HeadlessException
	 */

	
	private void inicializacion(String tituloBarra, String mensaje, boolean modal, Exception ex) throws HeadlessException {
		esta=this;
		String cadenaStack=getStackTrace(ex);
		inicializaEtiqueta(cadenaStack);
		inicializaControles(mensaje);
		
	}
	
	
	
	
	


	
	/**
	 * intentar llamar este constructor en una sola línea, porque es revisado por "PurificaRecursos" para extraer etiquetas de recursos
	 * @param owner
	 * @param tituloBarra
	 * @param mensaje
	 * @param modal
	 * @param ex
	 * @throws HeadlessException
	 */
	public DialogErrores(JFrame owner, String tituloBarra, String mensaje, boolean modal, Exception ex) throws HeadlessException {
		super(owner, tituloBarra, modal);
		inicializacion(tituloBarra, mensaje, modal, ex);
	}


	
	public String getStackTrace( Throwable aThrowable ) {
	  final Writer result = new StringWriter();
	  final PrintWriter printWriter = new PrintWriter( result );
	  aThrowable.printStackTrace( printWriter );
	  return result.toString();
	}
	

	
    public static void muestraVentanaAcercaDe(Component comp){
      	StringBuffer sb=new StringBuffer();
      	sb.append("KALÓS version " + Configuracion.getVersionNumero() + " \n");
      	sb.append("1995-2011 KALLISTOS CORPORATION \n");
      	if (Configuracion.getNombre()==null){
      		sb.append(Recursos.getCadena("usuario_no_registrado"));
      	}else{
      		sb.append(Recursos.getCadena("dueño_registrado") + ":"  + Configuracion.getNombre());	
      	}
      	JOptionPane.showMessageDialog(comp,  sb.toString(), Recursos.getCadena("acerca_de"), JOptionPane.PLAIN_MESSAGE);
      }


}
