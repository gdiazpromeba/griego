package com.kalos.visual.controles.textos.teclas;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.TextAction;

import com.kalos.enumeraciones.CompLetras;


/**
 * Acci�n que se usa para la adici�n signos diacr�ticos a letras ya escritas
 *
 */
public class TAMovimientoSeleccion extends TextAction implements CompLetras{
	
	
	
	int m_tecla;
	int m_mascara;
	JTextField m_txt;
	
	public TAMovimientoSeleccion(JTextField txt, int tecla, int mascara){
		super("");
		m_tecla=tecla;
		m_mascara=mascara;
		m_txt=txt;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (m_txt.getText().length()>0){
			int iPos=m_txt.getCaretPosition(), iLen=m_txt.getText().length();

			if (m_tecla==KeyEvent.VK_HOME & m_mascara==0)
				m_txt.setCaretPosition(0);
			else if (m_tecla==KeyEvent.VK_END & m_mascara==0)
				m_txt.setCaretPosition(iLen);
			else if (m_tecla==KeyEvent.VK_HOME & m_mascara==Event.SHIFT_MASK)
				m_txt.moveCaretPosition(0);
			else if (m_tecla==KeyEvent.VK_END & m_mascara==Event.SHIFT_MASK)
				m_txt.moveCaretPosition(iLen);
			else if (m_tecla==KeyEvent.VK_LEFT & m_mascara==0){
				if (iPos>0)
					m_txt.setCaretPosition(iPos-1);
			}else if (m_tecla==KeyEvent.VK_RIGHT & m_mascara==0){
				if (iPos<iLen)
					m_txt.setCaretPosition(iPos+1);
			}else if (m_tecla==KeyEvent.VK_LEFT & m_mascara==Event.SHIFT_MASK){
				if (iPos>0)
					m_txt.moveCaretPosition(iPos-1);
			}else if (m_tecla==KeyEvent.VK_RIGHT & m_mascara==Event.SHIFT_MASK){
				if (iPos<iLen)
					m_txt.moveCaretPosition(iPos+1);
			}
			
		}
	}
}