/*
 *  This program is an unpublished work fully protected by the United
 *  States copyright laws and is considered a trade secret belonging
 *  to Turner Entertainment Group. To the extent that this work may be
 *  considered "published,"
 *  the following notice applies:
 *
 *      "Copyright 2005, Turner Entertainment Group."
 *
 *  Any unauthorized use, reproduction, distribution, display,
 *  modification, or disclosure of this program is strictly prohibited.
 *
 */
/**
 * 
 */
package kalos.enumeraciones;
/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.0 $
 */
public enum TipoAdverbio {
    Modo, Tiempo, Lugar, Cantidad, Medio, Frecuencia, Negacion, Causa,  Afirmacion, Otro;
    
    
    public static TipoAdverbio getEnum(String cadena){
        if (cadena.equals("TIE"))
            return Tiempo;
        else if (cadena.equals("LUG"))
            return Lugar;
        else if (cadena.equals("CNT"))
            return Cantidad;
        else if (cadena.equals("MED"))
            return Medio;
        else if (cadena.equals("FRC"))
            return Frecuencia;
        else if (cadena.equals("NEG"))
            return Negacion;
        else if (cadena.equals("CAU"))
            return Causa;
        else if (cadena.equals("MOD"))
            return Modo;
        else if (cadena.equals("AFI"))
            return Afirmacion;
        else if (cadena.equals("OTR"))
            return Otro;
        else 
            throw new RuntimeException("tipo de adjetivo para la cadena=" + cadena);
    }
    
    public static String getCadena(TipoAdverbio tipo){
        switch(tipo){
        case Tiempo:
            return "TIE";
        case Lugar:
            return "LUG";
        case Cantidad:
            return "CNT";
        case Medio:
            return "MED";
        case Frecuencia:
            return "FRC";
        case Negacion:
            return "NEG";
        case Causa:
            return "CAU";
        case Modo:
            return "MOD";
        case Afirmacion:
            return "AFI";
        case Otro:
            return "OTR";
        default:
            throw new RuntimeException("no hay cadena para la enumeración " + tipo);
        }
    }
    
}
