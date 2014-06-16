/*
 * Created on 31/01/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.enumeraciones;


/**
 * @author gonzalo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public enum Genero {
	MasculinoOFemenino, Masculino, Femenino, Neutro;
	
	
	
//	public static String getCadena(Integer genero){
//	  switch(genero.intValue()){
//		case Masculino:
//		  return Recursos.getCadena("masculino");
//		case Femenino:
//		  return Recursos.getCadena("femenino");
//		case Neutro:
//		  return Recursos.getCadena("neutro");
//		case MasculinoOFemenino:
//		  return Recursos.getCadena("masculino_o_femenino");
//		default:
//		  return "error";
//	  }
//	}
	
	/**
	 * devuelve sólo el masculino, femenino y neutro
	 */
	public static Genero[] getMFN(){
		return new Genero[]{Masculino, Femenino, Neutro};
	}
	
	public static String getLetra(Genero genero){
		  switch(genero){
			case Masculino:
			  return "M";
			case Femenino:
				return "F";
			case Neutro:
				return "N";
			case MasculinoOFemenino:
				return "A";
			default:
			  return "error";
		  }
		}
	

	
	public static int getInt(Genero genero){
		  switch(genero){
			case Masculino:
			  return 1;
			case Femenino:
				return 2;
			case Neutro:
				return 3;
			default:
				return 4;
		  }
	}
	
	
	public static Genero getEnum(int genero){
		  switch(genero){
			case 1:
			  return Masculino;
			case 2:
				return Femenino;
			case 3:
				return Neutro;
			default:
				return MasculinoOFemenino;
		  }
	}
	
	public static Genero getEnum(String genero){
		if (genero.equals("F"))
		  return Femenino;
		else if (genero.equals("M"))
		  return Masculino;
		else if (genero.equals("N"))
			return Neutro;
		else if (genero.equals("A"))
			return MasculinoOFemenino;
		else 
			throw new RuntimeException("no sé qué genero es la letra " + genero);
	}
	
	
	




	
}
