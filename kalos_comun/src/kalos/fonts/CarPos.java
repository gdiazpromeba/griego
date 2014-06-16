package kalos.fonts;

/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */
import kalos.enumeraciones.CompLetras;

public class CarPos implements CompLetras, Comparable<CarPos>{

      private CaracterGriego caracterGriego;
      private int posicion;


      /**
       * Decorator de CaracterGriego (+ posición)
       * @param carGrie
       * @param posicion
       */
      public CarPos(char car, int posicion){
        this.caracterGriego=CaracterGriegoFactory.produceCaracterGriego(car);
        this.posicion=posicion;
      }

      public static CarPos getCarPos(String cadena, int posicion){
        if (posicion>=0 && posicion<cadena.length())
          return new CarPos(cadena.charAt(posicion), posicion);
        else
          return null;
      }





      public CaracterGriego getCaracterGriego(){
      	return caracterGriego;
      }

      //métodos de acceso
      public char getCaracter(){
        return caracterGriego.getCaracter();
      }
      public int getPosicion(){
        return posicion;
      }
      public boolean esLarga(){
        return caracterGriego.esLarga();
      }
      public boolean esCorta(){
        return caracterGriego.esCorta();
      }
      public boolean esSuave(){
        return caracterGriego.esSuave();
      }
      public boolean esAspero(){
        return caracterGriego.esAspero();
      }
      
      public boolean tieneEspiritu(){
      	return caracterGriego.tieneEspiritu();
      }
      
      public boolean esVocal(){
        return caracterGriego.esVocal();
      }
      public boolean esConsonante(){
        return caracterGriego.esConsonante();
      }
      public boolean esLiquida(){
        return caracterGriego.esLiquida();
      }
      public boolean esMuda(){
            return caracterGriego.esMuda();
      }
      
      public boolean esDoble(){
      	return caracterGriego.esDoble();
      }
      public boolean esSignoEspecial(){
            return caracterGriego.esSignoEspecial();
      }
      public boolean tieneAcentoAgudo(){
        return caracterGriego.tieneAcentoAgudo();
      }
      public boolean tieneAcentoCircunflejo(){
        return caracterGriego.tieneAcentoCircunflejo();
      }
      public boolean tieneAcentoGrave(){
        return caracterGriego.tieneAcentoGrave();
      }


      public boolean esMayuscula(){
        return caracterGriego.esMayuscula();
      }


      public boolean esMinuscula(){
        return caracterGriego.esMinuscula();
      }
      
      
	public int getOrden(){
		return caracterGriego.getOrden();
	}


      public char getVersionLarga(){
        return caracterGriego.getVersionLarga();
      }
      public char getVersionCorta(){
        return caracterGriego.getVersionCorta();
      }
      
      public char getLetraBase(){
      	return caracterGriego.getLetraBase();
      }

      public String toString(){
        return caracterGriego.toString();
      }

      /**
       * Devuelve true si la letra tiene diéresis (ojo, que a diferencia de los otros signos, el diéresis forma parte de la letra base
       */
      public boolean tieneDieresis(){
        return caracterGriego.tieneDieresis();
      }
      /**
       * Devuelve true si la letra tiene subscripta (ojo, que a diferencia de los otros signos, la subscripta forma parte de la letra base
       */
      public boolean tieneSubscripta(){
        return caracterGriego.tieneSubscripta();
      }



      public int compareTo(CarPos o) {
        if (this.caracterGriego.equals(o.caracterGriego) && this.posicion==o.posicion)
          return 0;
        else if (this.caracterGriego.hashCode()<o.caracterGriego.hashCode())
          return -1;
        else if (this.posicion<o.posicion)
          return -1;
        else
          return 1;
      }


}