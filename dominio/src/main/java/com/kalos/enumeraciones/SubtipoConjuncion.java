package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;

public enum SubtipoConjuncion {
    Copulativa("conjuncion.copulativa", "COP"), Adversativa("conjuncion.adversativa", "ADV"), Disyuntiva("conjuncion.disyuntiva", "DIS"), 
    Comparativa("conjuncion.comparativa", "COM"), Declarativa("conjuncion.declarativa", "DEC"), Causal("conjuncion.causal", "CAU"), 
    Temporal("conjuncion.temporal", "TEM"), Conclusiva("conjuncion.conclusiva", "CNC"), Final("conjuncion.final", "FIN"), 
    Condicional("conjuncion.supositiva", "CND"), Concesiva("conjuncion.concesiva", "CON");
    
    private String etiquetaRecursos;
    private String abreviatura;
    
    SubtipoConjuncion (String etiquetaRecursos, String abreviatura){
        this.etiquetaRecursos = etiquetaRecursos;
        this.abreviatura = abreviatura;
        
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }   
    
    public String abreviatura(){
        return this.abreviatura;
    }
    

    
    public static SubtipoConjuncion getEnum(String abreviatura){
        for (SubtipoConjuncion enu : values()) {
            if (enu.abreviatura.equals(abreviatura)){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de SubipoConjuncion para la abreviatura " + abreviatura);
    }      


}
