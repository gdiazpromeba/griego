package kalos.enumeraciones;



/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */

public enum FuerteDebil {
	Debil, Fuerte;
	
	public static FuerteDebil getEnum(int fuerteDebil){
		switch(fuerteDebil){
		case(0):
			return Debil;
		case(1):
			return Fuerte;
		default:
			throw new RuntimeException("no hay carácter fuerte para numero "+ fuerteDebil);
		}
	}
	
	public static int getInt(FuerteDebil fud){
		switch(fud){
		case Debil:
			return 0;
		case Fuerte:
			return 1;
		default:
			throw new RuntimeException("no hay número para carácter fuerte "+ fud);
		}
	}
	

}