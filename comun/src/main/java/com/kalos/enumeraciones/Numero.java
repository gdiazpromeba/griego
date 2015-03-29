package com.kalos.enumeraciones;

public enum Numero {
	Singular, Plural, Dual;
	
	public static int getInt(Numero valor){
		switch(valor){
		case Singular:
			return 1;
		case Plural:
			return 2;
		case Dual:
		    return 3;
		default:
		    throw new RuntimeException("no hay número para " + valor);
		}
	}
	
	public static Numero getEnum(int valor){
		switch(valor){
		  case 1:
			  return Singular;
		  case 2:
			  return Plural;
		  case 3:
			  return Dual;
		  default:
			  throw new RuntimeException("no hay enum para " + valor);
		}
	}	
	
}