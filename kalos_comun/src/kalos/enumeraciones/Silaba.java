package kalos.enumeraciones;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public enum Silaba  {
	_0, _1, _2, _3, _4, _5; 
	
	public static Silaba getEnum(int silaba){
		switch(silaba){
		case(0):
			return _0;
		case(1):
			return _1;
		case(2):
			return _2;
		case(3):
			 return _3;
		case(4):
			return _4;
		case(5):
			 return _5;
		default:
			throw new RuntimeException("no hay theEnum de sílaba para el número " + silaba);
		}
	}
	

	public static int getInt(Silaba silaba){
		switch(silaba){
		  case _0:
			  return 0;
		  case _1:
			  return 1;
		  case _2:
			  return 2;
		  case _3:
			  return 3;
		  case _4:
			  return 4;
		  case _5:
			  return 5;
		  default:
			  throw new RuntimeException("no hay sílaba número " + silaba);
		}
	}
      

      
}


