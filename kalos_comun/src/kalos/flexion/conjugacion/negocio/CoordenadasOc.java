package kalos.flexion.conjugacion.negocio;

import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Persona;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.Voz;

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
        this.hashCode= Voz.getInt(voz) + 10 * Modo.getInt(modo) + 100 * Tiempo.getInt(tiempo) 
        + 1000 * FuerteDebil.getInt(fuerte) + 10000 * Persona.getInt(persona);
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
