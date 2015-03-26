package kalos.enumeraciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import kalos.recursos.CadenasEnum;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public enum TipoPalabra {
    Sustantivo, Adjetivo, Adverbio, Articulo, Verbo, Participio, Infinitivo, PronombrePersonal, PronombreRelativo, Conjuncion, Preposicion, PronombreReflexivo, PronombreIndefinido, Interjeccion, PronombreInterrogativo;

    public static TipoPalabra getEnum(String tp) {
	switch (tp) {
	case "ADJ":
	    return Adjetivo;
	case "ADV":
	    return Adverbio;
	case "ART":
	    return Articulo;
	case "CONJ":
	    return Conjuncion;
	case "INTERJ":
	    return Interjeccion;
	case "PPE":
	    return PronombrePersonal;
	case "PREF":
	    return PronombreReflexivo;
	case "PREL":
	    return PronombreRelativo;
	case "PREP":
	    return Preposicion;
	case "PRINT":
	    return PronombreInterrogativo;
	case "PRONINDEF":
	    return PronombreIndefinido;
	case "SUS":
	    return Sustantivo;
	case "VER":
	    return Verbo;
	default:
	    throw new RuntimeException("la cadena " + tp + " no corresponde a ningún tipo de palabra");
	}
    }

    public static String getString(TipoPalabra tp) {
	return CadenasEnum.getCadena(tp);
    }
    
    public  String getStringCorta(){
	switch(this){
	case Adjetivo:
	    return "ADJ";
	case Adverbio:
	    return "ADV";
	case Articulo:
	    return "ART";
	case Conjuncion:
	    return "CONJ";
	case Interjeccion:
	    return "INTERJ";
	case PronombrePersonal:
	    return "PPE";
	case PronombreReflexivo:
	    return "PREF";
	case PronombreRelativo:
	    return "PREL";
	case Preposicion:
	    return "PREP";
	case PronombreInterrogativo:
	    return "PRINT";
	case PronombreIndefinido:
	    return "PRONINDEF";
	case Sustantivo:
	    return "SUS";
	case Verbo:
	    return "VER";
	default:
	  throw new RuntimeException("no se encuentra cadena para la enumeración " + this);
		    
	}
    }
    
    /**
     * esto probablemente no está bien. No entiendo qué transformación de Enunms está ocurriendo acá
     * @param lista
     * @return
     */
    public static List<TipoPalabra> getTiposPalabra(List<Enum<?>> lista){
	List<TipoPalabra> result=new ArrayList<TipoPalabra>();
	for (Iterator<Enum<?>> iterator = lista.iterator(); iterator.hasNext();) {
	    Enum<?> enum1 =  iterator.next();
	    result.add((TipoPalabra)enum1);
	}
	return result;
    }

}
