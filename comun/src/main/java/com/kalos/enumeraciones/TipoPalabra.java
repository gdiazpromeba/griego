package com.kalos.enumeraciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kalos.recursos.CadenasEnum;
import com.kalos.recursos.Recursos;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public enum TipoPalabra {
    Sustantivo("sustantivo", "SUS"), Adjetivo("adjetivo", "ADJ"), Adverbio("adverbio", "ADV"), Articulo ("articulo", "ART") , 
    Verbo("verbo", "VER"), Participio("participio", "PART"), Infinitivo("infinitivo", "INFIN"), PronombrePersonal("pronombre_personal", "PPE"),
    PronombreRelativo("pronombre_relativo", "PREL"), Conjuncion ("conjuncion", "CONJ"), Preposicion("preposicion", "PREP"), 
    PronombreReflexivo("pronombre_reflexivo", "PREF"), PronombreIndefinido("pronombre_indefinido", "PRONINDEF"), 
    Interjeccion("interjeccion", "INTERJ"), PronombreInterrogativo("pronombre_interrogativo", "PRINT");

    private String etiquetaRecursos;
    private String abreviatura;
    
    TipoPalabra (String etiquetaRecursos, String abreviatura){

        this.etiquetaRecursos = etiquetaRecursos;
        this.abreviatura = abreviatura;
    }
    
    public String getCadenaRecursos(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public String getAbreviatura(){
        return this.abreviatura;
    }    
    
    public static TipoPalabra getEnum(String abreviatura){
        for (TipoPalabra enu : values()) {
            if (enu.abreviatura==abreviatura){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de TipoPalabra para la abreviatura " + abreviatura);
    }    
    
    public static List<TipoPalabra> getTiposPalabra(List<Enum<?>> lista) {
        List<TipoPalabra> result = new ArrayList<TipoPalabra>();
        for (Iterator<Enum<?>> iterator = lista.iterator(); iterator.hasNext();) {
            Enum<?> enum1 = iterator.next();
            result.add((TipoPalabra) enum1);
        }
        return result;
    } 
    
 

}
