package com.kalos.flexion.conjugacion.negocio;

import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.Voz;

/**
 * almacena las coordenadas necesarias para encontrar una lista de formas
 * en un objeto Ocurrencia
 * Como las vamos a almacenar en un HashMap, tienen que sobreescribir equals() y hashCode()
 * 
 * @author gdiaz
 */
public class CoordenadasOc {


    private int hashCode;
	
	@Override
    public int hashCode() {
      return this.hashCode;
    }

    public CoordenadasOc(Voz voz, Modo modo, Tiempo tiempo, FuerteDebil fuerte, Persona persona){
        this.hashCode= voz.valorEntero() + 10 * modo.valorEntero() + 100 * tiempo.valorEntero() 
        + 1000 * FuerteDebil.getInt(fuerte) + 10000 * persona.valorEntero();
	}
	
	public boolean equals(Object obj){
		if (!(obj instanceof CoordenadasOc))
			return false;
		else{
			CoordenadasOc ocf=(CoordenadasOc)obj;
            return ocf.hashCode==this.hashCode;
		}
	}
	
	
}
