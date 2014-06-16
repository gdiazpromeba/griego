/*
 * Created on May 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package kalos.operaciones;

import kalos.fonts.CarPos;

/**
 * @author GDiaz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Comparador {
  
  /**
   * Compara la primera cadena con la segunda
   * devuel
   * @param primera
   * @param segunda
   * @ return 1 si la primera es mayor que la segunda
   *          0 si son iguales
   *          -1 si la primera es menor que la segunda
   */
  public static int compara(String primera, String segunda){
  	int puntero=0;
  	int lonPrimera=primera.length();
  	while (puntero<primera.length()){
  		CarPos cp1=CarPos.getCarPos(primera, puntero);
  		CarPos cp2=CarPos.getCarPos(segunda, puntero);
  		if (cp2==null) return 1;
  		if (cp1.getOrden() >cp2.getOrden())
  		  return 1;
  		else if (cp1.getOrden()<cp2.getOrden())
  		  return -1;
  		else
  			puntero++;
  	}
	int lonSegunda=segunda.length();
  	if (lonSegunda==lonPrimera)
  	  return 0;
  	else 
  	  return -1;
  }

}
