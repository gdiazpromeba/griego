/*
 * Created on May 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.kalos.visual.controles.textos.alternable;


import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.Element;
import javax.swing.text.View;

/**
 * @author GDiaz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TextUIBasica extends BasicTextFieldUI {
	public View create (Element elemento){
		return new MiFieldView(elemento);
	}
	  
}
