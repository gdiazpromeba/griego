package kalos.enumeraciones;

public enum ValorExplosion {
  Siempre, Nunca, SegunHeuristica;
  
  /**
   * transforma una cadena (de archivos de configuración, etc) en una theEnumeración
   * @param enu
   * @return
   */
  public static String getStringDeEnu(ValorExplosion enu){
 	 if (enu.equals(Siempre))
 		 return "Siempre";
 	 else if (enu.equals(Nunca))
 		 return "Nunca";
 	 else 
 		 return "SegunHeuristica";
  }
  
  /**
   * dada una theEnumeración, devuelve una cadena (sin acentos, para archivos de configuración)
   * @param cadena
   * @return
   */
  public static ValorExplosion getEnuDeString(String cadena){
 	 if (cadena.equals("Siempre"))
 		 return Siempre;
 	 else if (cadena.equals("Nunca"))
 		 return Nunca;
 	 else 
 		 return SegunHeuristica;
  }
  
  
}
