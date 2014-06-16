package kalos.enumeraciones;

/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */

public enum Espiritu {
	Ninguno, Aspero, Suave;
	
	public int getInt(Espiritu espiritu){
		switch(espiritu){
		case Ninguno:
			return 0;
		case Aspero:
			return 1;
		default: //suave
			return 2;
		}
	}
	
	public static Espiritu getEnum(int espiritu){
		switch(espiritu){
		case 0:
			return Ninguno;
		case 1:
			return Aspero;
		case 2:
			return Suave;
		default: //suave
			throw new RuntimeException("no existe espíritu para el valor entero " + espiritu);
		}
	}

}