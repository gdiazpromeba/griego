package com.kalos.operaciones;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ExcepcionCaracterNoEncontradoEnPalabra extends ExcepcionCaracterNoEncontrado {
  protected int posicion;
  public ExcepcionCaracterNoEncontradoEnPalabra(int caracter, int posicion) {
    super(caracter);
    this.posicion=posicion;
  }

  public int getPosicion(){
    return posicion;
  }
  
  public String getMessage(){
  	StringBuffer sb=new StringBuffer("carácter " + caracter + " \n");
  	sb.append("valor entero=" + (int)caracter+ " \n");
  	sb.append("valor hexadecimal=" + Integer.toHexString(caracter)+ " \n");
  	sb.append("no encontrado en alguno de los cases");
  	return sb.toString(); 
  }

  public void mensajeErrorEntrada(JTextField txt) {
    StringBuffer sBuff=new StringBuffer("error_mala_entrada");
    sBuff.append(": " + (char)caracter + " \n");
    sBuff.append("nº: " + caracter + " \n");
    sBuff.append("valor_hexadecimal" + ":" + "\n");
    sBuff.append(Integer.toString(caracter, 16) + "\n");
    sBuff.append("volcado_palabra" + ":" +  "\n");
    sBuff.append(OpPalabras.cadenaCompletaAString(txt.getText()));
    JOptionPane.showMessageDialog(txt.getParent(), sBuff.toString());
    txt.select(posicion, posicion+1);
    txt.requestFocus();
  }


}