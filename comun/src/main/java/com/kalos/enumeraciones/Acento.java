package com.kalos.enumeraciones;

public enum Acento {
	Agudo, Circunflejo, Grave, Ninguno;
	
	public static int getInt(Acento valor){
		switch(valor){
		case Agudo:
			return 1;
		case Grave:
			return 2;
		case Circunflejo:
			return 3;
		default:
			return 0;
		}
	}
	
	public static Acento getEnum(int valor){
		switch(valor){
		  case 1:
			  return Agudo;
		  case 2:
			  return Grave;
		  case 3:
			  return Circunflejo;
		  case 0:
			  return Ninguno;
		  default:
			  throw new RuntimeException("no hay Acento número " + valor);
		}
	}	
	
}
