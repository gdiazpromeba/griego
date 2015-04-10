package com.kalos.visual.controles.textos.teclas;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.TextAction;

import com.kalos.enumeraciones.CompLetras;


/**
 * Acci�n que se usa para la adici�n signos diacr�ticos a letras ya escritas
 *
 */
public class TACopiarPegar extends TextAction implements CompLetras{
	
	
	
	int m_tecla;
	int m_mascara;
	JTextField m_txt;
	public TACopiarPegar(JTextField txt, int tecla, int mascara){
		super("");
		m_tecla=tecla;
		m_mascara=mascara;
		m_txt=txt;
	}
	public void actionPerformed(ActionEvent e) {
		
		 Clipboard cb = Toolkit.getDefaultToolkit ().getSystemClipboard ();
		 
		 //copiar
		 if (
		 		(m_tecla==KeyEvent.VK_C && m_mascara== KeyEvent.CTRL_MASK)
				||
				(m_tecla==KeyEvent.VK_INSERT && m_mascara== KeyEvent.CTRL_MASK)
			){
			 String s=m_txt.getText();
			 StringSelection contents = new  StringSelection (s);  
			 cb.setContents (contents, null);
		 }else if (
		 		(m_tecla==KeyEvent.VK_V && m_mascara== KeyEvent.CTRL_MASK)
				||
				(m_tecla==KeyEvent.VK_INSERT && m_mascara== KeyEvent.SHIFT_MASK)
			){
		 	 Transferable  content = cb.getContents (this);  
		 	 try { 
		 	 	String S = (String)  content.getTransferData (DataFlavor.stringFlavor);
		 	 	m_txt.setText (S);		 
		 	 }catch(Exception ex){
		 	 	ex.printStackTrace();
		 	 }
		}
	}
}