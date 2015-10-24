package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;

public enum Ignorancia {
	 TodosLosDiacriticos("ignorar.todos_diacriticos"), SignosLargaCorta("ignorar.signos_larga_corta"), Nada("ignorar.nada");
	 

	    private String etiquetaRecursos;
	    
	    Ignorancia (String etiquetaRecursos){
	        this.etiquetaRecursos = etiquetaRecursos;
	    }
	    
	    public String toString(){
	        return Recursos.getCadena(this.etiquetaRecursos);
	    }
	    


}
