package com.kalos.enumeraciones;




/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */

public enum Aspecto implements TiempoOAspecto {
	Infectivo, Futuro, Confectivo, Perfectivo;
	
	public static int getInt(Aspecto aspecto){
		switch(aspecto){
		case Infectivo:
			return 1;
		case Futuro:
			return 3;
		case Confectivo:
			return 4;
		case Perfectivo:
			return 5;
		default:
			throw new RuntimeException("aspecto no encontrado " + aspecto);
		}
	}
	
	
	public static Aspecto getEnum(int valor){
		switch(valor){
		case 1:
			return Aspecto.Infectivo;
		case 3:
			return Aspecto.Futuro;
		case 4:
			return Aspecto.Confectivo;
		case 5:
			return Aspecto.Perfectivo;
		default:
			throw new RuntimeException("no hay aspecto para el número " + valor);
		
		}
	}

  
  
}