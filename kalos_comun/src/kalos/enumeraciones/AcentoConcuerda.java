package kalos.enumeraciones;

public enum AcentoConcuerda {
	SinForzado, ElMismo, Nominativo, Innecesario, Forzado;
	
	public static int getInt(AcentoConcuerda valor){
		switch(valor){
		case SinForzado:
			return 0;
		case ElMismo:
			return 1;
		case Nominativo:
			return 2;
		case Innecesario:
			return 3;
		default:  //forzado
			return 4;
		}
	}
	
	public static AcentoConcuerda getEnum(int valor){
		switch(valor){
		  case 0:
			  return SinForzado;
		  case 1:
			  return ElMismo;
		  case 2:
			  return Nominativo;
		  case 3:
			  return Innecesario;
		  case 4:
			  return Forzado;
		  default:
			  throw new RuntimeException("no hay AcentoConcuerda número " + valor);
		}
	}	
	
}
