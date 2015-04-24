package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public enum TiempoSegundos implements TiempoOAspecto{
	Presente("presente"), Imperfecto("imperfecto"), Futuro("futuro"), FuturoSegundo("futuro_segundo"), 
	Aoristo("aoristo"), AoristoSegundo("aoristo_segundo"),  Perfecto("perfecto"), PerfectoSegundo("perfecto_segundo"), 
	Pluscuamperfecto("pluscuamperfecto"), PluscuamperfectoSegundo("pluscuamperfecto_segundo"); 
	
    
    private String etiquetaRecursos;
    
    TiempoSegundos (String etiquetaRecursos){
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }	
	
}


