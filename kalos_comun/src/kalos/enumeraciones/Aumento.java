package kalos.enumeraciones;


/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */

public enum Aumento {
	Ninguno, Normal, enEi;
	
	public static int getInt(Aumento valor){
		switch(valor){
		  case Ninguno:
			  return 0;
		  case Normal:
			  return -1;
		  case enEi:
			  return 2;
		  default:
			  throw new RuntimeException("aumento no encontrado");
		}
	}
	
	public static Aumento getEnum(int valor){
		switch(valor){
		  case(-1):
			  return Normal;
		  case(0):
			  return Ninguno;
		  case(2):
			  return enEi;
		  default:
			  throw new RuntimeException("no hay Aumento número " + valor);
		}
	}
	


}