package kalos.enumeraciones;


public enum GradoComparacion {
	Positivo, Comparativo, Superlativo;
	
	public static String getLetra(GradoComparacion grado){
		  switch(grado){
			case Positivo:
			  return "P";
			case Comparativo:
				return "C";
			case Superlativo:
				return "S";
			default:
			  return "error";
		  }
		}

	public static int getInt(GradoComparacion grado){
		  switch(grado){
			case Positivo:
			  return 1;
			case Comparativo:
				return 2;
			default: //superlativo
				return 3;
		  }
	}	
	
	public static GradoComparacion getEnum(String grado){
		if (grado.equals("P"))
		  return Positivo;
		else if (grado.equals("C"))
		  return Comparativo;
		else if (grado.equals("S"))
			return Superlativo;
		else 
			throw new RuntimeException("no sé qué grado es la letra " + grado);
	}
	
}
