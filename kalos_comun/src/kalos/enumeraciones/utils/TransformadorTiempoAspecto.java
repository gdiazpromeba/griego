package kalos.enumeraciones.utils;

import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.TiempoOAspecto;

public class TransformadorTiempoAspecto {
	
	public static Tiempo comoTiempo(TiempoOAspecto toa){
		if (toa instanceof Tiempo){
			return (Tiempo)toa;
		}else{
			Aspecto aspecto=(Aspecto)toa;
			int valor=Aspecto.getInt(aspecto);
			Tiempo tiempo=Tiempo.getEnum(valor);
			return tiempo;
		}
		
	}
	
	public static Aspecto comoAspecto(TiempoOAspecto toa){
		if (toa instanceof Aspecto){
			return (Aspecto)toa;
		}else{
			Tiempo tiempo=(Tiempo)toa;
			switch(tiempo){
			case Presente:
			case Imperfecto:
			    return Aspecto.Infectivo;
			case Futuro:
			    return Aspecto.Futuro;
			case Aoristo:
			    return Aspecto.Confectivo;
			case Perfecto:
			case Pluscuamperfecto:
			    return Aspecto.Perfectivo;
			}
		}
		return null;//compilador
		
	}


}
