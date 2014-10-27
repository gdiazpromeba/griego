package kalos.enumeraciones;

public enum Idioma {
	Castellano, Ingles, Frances;
	

	
	public static Idioma  getEnum(String valor){
		if (valor.equals("en")){
			return Ingles;
		}else if (valor.equals("fr")){
			return Frances;
		}else if (valor.equals("es")){
			return Castellano;
		}else{
			  throw new RuntimeException("no hay Idioma " + valor);
		}
	}
	
	public static String  getString(Idioma valor){
	    switch(valor){
	    case Castellano:
		return  "es";
	    case Ingles:
		return "en";
	    default:
		 return "fr";
	    }
	   
	}	
	
}