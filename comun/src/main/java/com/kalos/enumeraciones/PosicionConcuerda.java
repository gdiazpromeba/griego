package com.kalos.enumeraciones;

public enum PosicionConcuerda {
	Estandar, ReferenciaNatural, UltimaONormal, NGSing, Silaba, GSing, NSing;
	
	public static int getInt(PosicionConcuerda valor){
		switch(valor){
		case Estandar:
			return 0;
		case ReferenciaNatural:
			return 1;
		case UltimaONormal:
			return 2;
		case NGSing:
			return 3;
		case Silaba:
			return 4;
		case GSing:
			return 5;
		default:  //Nsing
			return 6;
		}
	}
	
	public static PosicionConcuerda getEnum(int valor){
		switch(valor){
		  case 0:
			  return Estandar;
		  case 1:
			  return ReferenciaNatural;
		  case 2:
			  return UltimaONormal;
		  case 3:
			  return NGSing;
		  case 4:
			  return Silaba;
		  case 5:
			  return GSing;
		  case 6:
			  return NGSing;
		  default:
			  throw new RuntimeException("no hay PosicionConcuerda número " + valor);
		}
	}	
	
}
