package kalos.enumeraciones;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public enum Propagacion {
	Ninguna, haciaModoYVoz, haciaLaVoz, haciaElModo; 
	
	public static Propagacion getEnum(int propagacion){
		switch(propagacion){
		case(-1):
			return haciaModoYVoz;
		case(2):
			return haciaLaVoz;
		case(0):
			return Ninguna;
        case(3):
            return haciaElModo;
		default:
			throw new RuntimeException("no hay theEnum de propagación para el número " + propagacion);
		}
	}
	

	public static int getInt(Propagacion propagacion){
		switch(propagacion){
		  case haciaModoYVoz:
			  return -1;
		  case haciaLaVoz:
			  return 2;
		  case Ninguna:
			  return 0;
          case haciaElModo:
              return 3;
		  default:
			  throw new RuntimeException("no hay propagación número " + propagacion);
		}
	}
	
	public boolean esDeVoz(){
		return this.equals(haciaModoYVoz) || this.equals(haciaLaVoz);
	}
	
	public boolean esDeModo(){
		return this.equals(haciaModoYVoz) || this.equals(haciaElModo);
	}
	
      

      
}


