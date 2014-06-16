package kalos.visual.controles.textos.teclas;

/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Dï¿½az
 * @version 1.0
 */
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.text.TextAction;

import kalos.enumeraciones.uLetras;


/**
 * Acción que se usa para la adición de letras simples
 * Asume que los caracteres que se agregan son de tipo Unicode 
 *
 */

public class TAAgregadoSimple extends TextAction implements uLetras{

	char m_charAgregado;
	JTextField m_txt;
	public TAAgregadoSimple(char charAgregado, JTextField txt){
		super("");
		m_charAgregado=charAgregado;
		m_txt=txt;
	}
	public void actionPerformed(ActionEvent e) {
		StringBuffer sAux=new StringBuffer(m_txt.getText());
		int iStart=m_txt.getSelectionStart(), iEnd=m_txt.getSelectionEnd();
		//si hay algo seleccionado, eliminarlo
		if (iStart!=iEnd) 
			sAux.delete(iStart, iEnd);
		
		if (m_charAgregado==cUnderscore){
			sAux.insert(iStart, uLetras.cLarga);
	    }else{
		  //ahora sigo normalmente
		  //sAux.insert(iStart, Unicode.carAUnicodeSiNecesario(m_charAgregado));
		  sAux.insert(iStart, m_charAgregado);
		}
		m_txt.setText(sAux.toString());
		m_txt.setCaretPosition(iStart+1);
	}
	
	
}