package com.kalos.enumeraciones;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public enum Silaba  {
	ultima, penultima, antepenultima, ninguna, primera, segunda; 
	
	public static Silaba getEnum(int silaba){
		switch(silaba){
		case(-1):
			return ultima;
		case(-2):
			return penultima;
		case(-3):
			return antepenultima;
		case(0):
			 return ninguna;
		case(1):
			return primera;
		case(2):
			 return segunda;
		default:
			throw new RuntimeException("no hay theEnum de sílaba para el número " + silaba);
		}
	}
	

	public static int getInt(Silaba silaba){
		switch(silaba){
		  case ultima:
			  return -1;
		  case penultima:
			  return -2;
		  case antepenultima:
			  return -3;
		  case ninguna:
			  return 0;
		  case primera:
			  return 1;
		  case segunda:
			  return 2;
		  default:
			  throw new RuntimeException("no hay sílaba número " + silaba);
		}
	}
      

      
}


