package com.kalos.enumeraciones;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public enum Voz {
	Activa, Media, Pasiva;
	
	public static Voz getEnum(int voz){
		switch(voz){
		case(1):
			return Activa;
		case(2):
			return Media;
		case(3):
			return Pasiva;
		default:
			throw new RuntimeException("no hay Voz para el número "+ voz);
		}
	}
	

	public static int getInt(Voz voz){
		switch(voz){
		case Activa:
			return 1;
		case Media:
			return 2;
		default:
			return 3;
		}
	}
}