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

public enum AspectosSegundos implements TiempoOAspecto{
	Infectivo("infectivo"), Futuro("futuro"), FuturoSegundo("futuro_segundo"), Confectivo("confectivo"), 
	ConfectivoSegundo("confectivo_segundo"), Perfectivo("perfectivo"), PerfectivoSegundo("perfectivo_segundo"); 
	
    private String etiquetaRecursos;
    
    AspectosSegundos (String etiquetaRecursos){
        this.etiquetaRecursos = etiquetaRecursos;
    }
    
    public String getCadenaRecursos(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }	
}


