package com.kalos.enumeraciones;public enum Caso {	Nominativo, Vocativo, Acusativo, Genitivo, Dativo;		public static int getInt(Caso valor){		switch(valor){		case Nominativo:			return 1;		case Vocativo:			return 2;		case Acusativo:			return 3;		case Genitivo:			return 4;		case Dativo:			return 5;		default:			return 0;		}	}		public static Caso getEnum(int valor){		switch(valor){		  case 1:			  return Nominativo;		  case 2:			  return Vocativo;		  case 3:			  return Acusativo;		  case 4:			  return Genitivo;		  case 5:			  return Dativo;		  default:			  throw new RuntimeException("no hay Acento número " + valor);		}	}		}