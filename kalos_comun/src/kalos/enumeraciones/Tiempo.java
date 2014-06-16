package kalos.enumeraciones;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public enum Tiempo implements TiempoOAspecto {
	Presente, Imperfecto, Futuro, Aoristo, Perfecto, Pluscuamperfecto; 
	
	public static Tiempo getEnum(int tiempo){
		switch(tiempo){
		case(1):
			return Presente;
		case(2):
			return Imperfecto;
		case(3):
			return Futuro;
		case(4):
			 return Aoristo;
		case(5):
			return Perfecto;
		case(6):
			 return Pluscuamperfecto;
		default:
			throw new RuntimeException("no hay theEnum de tiempo para el número " + tiempo);
		}
	}
	

	public static int getInt(Tiempo tiempo){
		switch(tiempo){
		  case Presente:
			  return 1;
		  case Imperfecto:
			  return 2;
		  case Futuro:
			  return 3;
		  case Aoristo:
			  return 4;
		  case Perfecto:
			  return 5;
		  case Pluscuamperfecto:
			  return 6;
		  default:
			  throw new RuntimeException("no hay tiempo número " + tiempo);
		}
	}
      

      
}


