package com.kalos.enumeraciones;

public enum EnuExplosiones {
 Acentos, Espiritus, Mayusculas, Dieresis, Subscriptas, NuFinal, LargasBreves;
 
 
 /**
  * dada una theEnumeración, devuelve una cadena (sin acentos, para archivos de configuración)
  * @param cadena
  * @return
  */
 public static EnuExplosiones getEnuDeString(String cadena){
	 if (cadena.equals("Acentos"))
		 return Acentos;
	 else if (cadena.equals("Espiritus"))
		 return Espiritus;
	 else if (cadena.equals("Mayusculas"))
		 return Mayusculas;
	 else if (cadena.equals("Dieresis"))
		 return Dieresis;
	 else if (cadena.equals("Subscriptas"))
		 return Subscriptas;
	 else if (cadena.equals("NuFinal"))
		 return NuFinal;
	 else 
		 return LargasBreves;
 }
 
 /**
  * transforma una cadena (de archivos de configuración, etc) en una theEnumeración
  * @param enu
  * @return
  */
 public static String getStringDeEnu(EnuExplosiones enu){
	 if (enu.equals(Acentos))
		 return "Acentos";
	 else if (enu.equals(Espiritus))
		 return "Espiritus";
	 else if (enu.equals(Mayusculas))
		 return "Mayusculas";
	 else if (enu.equals(Dieresis))
		 return "Dieresis";
	 else if (enu.equals(Subscriptas))
		 return "Subscriptas" ;
	 else if (enu.equals(NuFinal))
		 return "NuFinal";
	 else 
		 return "LargasBreves";
 }
}
