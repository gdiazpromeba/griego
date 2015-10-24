package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;

public enum LugarSubcadena {
	Principio("lugar.principio"), Medio("lugar.mitad"), Fin("lugar.final"), Exacto("lugar.exacto");
	
    private String etiquetaRecursos;
    
    LugarSubcadena (String etiquetaRecursos){
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }	
	
}
