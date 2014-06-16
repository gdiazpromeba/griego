package kalos.enumeraciones;


/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */

public enum Persona {
	_1ps, _2ps, _3ps, _1pp, _2pp, _3pp, _2pd, _3pd;
	
	public static int getInt(Persona valor){
		switch(valor){
		  case _1ps:
			  return 1;
		  case _2ps:
			  return 2;
		  case _3ps:
			  return 3;
		  case _1pp:
			  return 4;
		  case _2pp:
			  return 5;
		  case _3pp:
			  return 6;
		  case _2pd:
			  return 7;
		  case _3pd:
			  return 8;
		  default:
			  throw new RuntimeException("persona no encontrada");
		}
	}
	
	public static Persona getEnum(int valor){
		switch(valor){
		  case(1):
			  return _1ps;
		  case(2):
			  return _2ps;
		  case(3):
			  return _3ps;
		  case(4):
			  return _1pp;
		  case(5):
			  return _2pp;
		  case(6):
			  return _3pp;
		  case(7):
			  return _2pd;
		  case(8):
			  return _3pd;
		  default:
			  throw new RuntimeException("no hay Persona número " + valor);
		}
	}
	


}