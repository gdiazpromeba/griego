package com.kalos.enumeraciones;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public enum Contraccion {
	suma, sumaAcentuada, vocalica, consonantica, monosilabosEw, jonica, comePrimera;

	public static Contraccion getEnum(int contraccion){
		switch(contraccion){
		case(-1):
			return suma;
		case(0):
			return sumaAcentuada;
		case(1):
			return vocalica;
		case(2):
			return consonantica;
		case(3):
			return monosilabosEw;
		case(4):
			return jonica;
		case(6):
			return comePrimera;
		default:
			throw new RuntimeException("no hay contracción para el número "+ contraccion);
		}
	}
	
	
	public static int getInt(Contraccion contraccion){
		switch(contraccion){
		case suma:
			return -1;
		case sumaAcentuada:
			return 0;
		case vocalica:
			return 1;
		case consonantica:
			return 2;
		case monosilabosEw:
			return 3;
		case jonica:
			return 4;
		case comePrimera:
			return 6;
		default:
			throw new RuntimeException("no hay número para la contracción "+ contraccion);
		}
	}	

}