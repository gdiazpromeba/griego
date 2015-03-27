package com.kalos.operaciones;


/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ExcepcionCaracterNoEncontrado extends Exception {
  protected int caracter;
  public ExcepcionCaracterNoEncontrado(int caracter) {
    this.caracter=caracter;
  }
  public int getCaracter(){
    return caracter;
  }

}