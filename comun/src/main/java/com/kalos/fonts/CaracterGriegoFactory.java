package com.kalos.fonts;

import java.util.HashMap;
import java.util.Map;


public class CaracterGriegoFactory {

  private static boolean instanciado=false;
  private static CaracterGriegoFactory instancia;
  private Map<Character, CaracterGriego> mapCarGriegos=new HashMap<Character, CaracterGriego>();
  private CaracterGriegoFactory(){
  }

  /**
   * Dado un carácter básico car,  devuelve una referencia a un CaracterGriego,
   * creándolo y agregúndolo a la lista si es necesario
   * @param car
   */
  private CaracterGriego encuentraCaracterGriego(char car){
    Character carKey=new Character(car);
    CaracterGriego cag=(CaracterGriego)mapCarGriegos.get(carKey);
    if (cag==null){
      cag = new CaracterGriego(car);
      mapCarGriegos.put(carKey, cag);
    }
    return cag;
  }


  /**
   * Instancia el la factory  como singleton, instancióndola si es necesario
   * Y devuelve el caracter griego
   * @param car
   * @return
   */
  public static CaracterGriego produceCaracterGriego(char car){
    if (!instanciado){
      instancia = new CaracterGriegoFactory();
      instanciado=true;
    }
    return instancia.encuentraCaracterGriego(car);
  }
}