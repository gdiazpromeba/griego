package kalos.flexion.conjugacion.negocio;

import kalos.enumeraciones.Acento;

/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */

public class Desinencia {
      public String cadena;
      int posicion;
      Acento tipoAcento;
      int juego;
      
      public Desinencia(String cadena, int posicion, Acento tipoAcento, int juego){
            this.cadena=cadena;
            this.posicion=posicion;
            this.tipoAcento=tipoAcento;
            this.juego=juego;
      }
      
  public Acento getTipoAcento(){
  	return tipoAcento;
  }
  
  public int getPosicion(){
   return posicion;
  }
      
      
      
}