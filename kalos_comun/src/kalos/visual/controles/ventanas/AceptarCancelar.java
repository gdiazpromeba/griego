/*
 * Created on Apr 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kalos.visual.controles.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import kalos.recursos.Recursos;

import com.jgoodies.forms.builder.ButtonBarBuilder;

/**
 * ventana con botones de aceptar y cancelar.
 * los controles específicos van a un panel llamado panelCentral
 * Una variable "aceptó" indica cuál de los botones se apretó
 * @author gdiaz
 *
 */
public abstract class AceptarCancelar extends JDialog {

	JTextPane texto;
	protected JButton botAceptar=new JButton(Recursos.getCadena("aceptar"));
	protected JButton botCancelar=new JButton(Recursos.getCadena("cancelar"));
	private JPanel panelCentral=new JPanel();
	protected boolean acepto;
	/**
	 * evita que redirija los métodos de "agregar" y "setLayout" al panel central hasta que yo quiera hacerlo
	 */
	private boolean comenzarAInterceptar=false;
	

	private void inicializacion(){
        ButtonBarBuilder builder = new ButtonBarBuilder();
        builder.addGlue();
        builder.addGriddedButtons(new JButton[] {botAceptar, botCancelar});
        builder.setDefaultDialogBorder();
		//setLocationRelativeTo(padre);
		setLayout(new BorderLayout());
		add(builder.getPanel(), BorderLayout.SOUTH);
		add(panelCentral, BorderLayout.CENTER);
		
		botAceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				aceptar();
				if (acepto)
					setVisible(false);
			}
		});
		
		botCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				cancelar();
				setVisible(false);
			}
		});
		
		   this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		        cancelar();
		      }
		    });
		   comenzarAInterceptar=true;
	}
	
	
	public AceptarCancelar(JFrame frame) {
		super(frame);
		inicializacion();

	}
	
	public AceptarCancelar() {
		super();
		inicializacion();

	}
	
	public boolean isAcepto(){
		return acepto;
	}
	
	
	protected abstract void aceptar();
	protected abstract void cancelar();
	

	
	public void setLayout(LayoutManager lam){
		if (!comenzarAInterceptar){
			Container contentPane=null;
			try{
			  contentPane=getContentPane();
			}catch(Exception ex){}
			if (contentPane!=null)
				contentPane.setLayout(lam);
			else
			  super.setLayout(lam); 
		}else{
		  panelCentral.setLayout(lam);//éste es mi código
		}
	}
	
	public Component add(Component componente){
		if (!comenzarAInterceptar)
			if (getContentPane()!=null)
			  return super.getContentPane().add(componente);
			else
			  return super.add(componente);
		else
			return panelCentral.add(componente);
	}
	
	public void add(Component componente, Object constraints){
		if (!comenzarAInterceptar){
			boolean pudeAgregar=true;
			try{
				super.getContentPane().add(componente, constraints);
				
			}catch(Exception ex){
				pudeAgregar=false;
			}
			if (!pudeAgregar)
				super.add(componente, constraints);
			
			   
		}else 
			panelCentral.add(componente, constraints);
	}
	
	public void setAceptarhabilitado(boolean valor){
		botAceptar.setEnabled(valor);
	}
	
	/**
	 * devuelve el panel central
	 * @return
	 */
	public JPanel getPanelCentral(){
		return this.panelCentral;
	}
	
	
	


}
