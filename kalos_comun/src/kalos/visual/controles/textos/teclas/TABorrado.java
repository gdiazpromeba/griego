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
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.TextAction;

import kalos.enumeraciones.CompLetras;

public class TABorrado extends TextAction implements CompLetras{
      /**
       * Acción que se usa para la adición signos diacrï¿½ticos a letras ya escritas
       *
       */


      int m_tecla;
      JTextField m_txt;
      public TABorrado(JTextField txt, int tecla){
            super("");
            m_tecla=tecla;
            m_txt=txt;
      }
      
      /**
       * Nótese que en este caso no hace falta la tranformación ida y vuelta a
       * completo (es más, perjudicaría si lo que estoy corrigiendo con el borrado
       * es un error de entrada)
       */
      public void actionPerformed(ActionEvent e) {
        StringBuffer sAux=new StringBuffer(m_txt.getText());
            int iStart=m_txt.getSelectionStart(), iEnd=m_txt.getSelectionEnd();
            int iPos=m_txt.getCaretPosition(), iLen=sAux.length();
            if (iStart!=iEnd){//borro el rango y eso es todo, independientemente de cuï¿½l de las teclas haya tocado
                  sAux.delete(iStart, iEnd);
				  m_txt.setText(sAux.toString());
                  m_txt.setCaretPosition(iStart);
                  return;
            }

            switch (m_tecla){
                  case KeyEvent.VK_BACK_SPACE:
                        if (iPos>0){
                              sAux.deleteCharAt(iPos-1);
							  m_txt.setText(sAux.toString());
                              m_txt.setCaretPosition(iPos-1);
                        }
                        break;
                  case KeyEvent.VK_DELETE:
                        if (iPos<iLen){
                              sAux.deleteCharAt(iPos);
							  m_txt.setText(sAux.toString());
                              m_txt.setCaretPosition(iPos);
                        }
                        break;
            }
      }
}