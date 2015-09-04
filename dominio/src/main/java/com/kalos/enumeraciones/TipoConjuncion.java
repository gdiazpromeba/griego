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
package com.kalos.enumeraciones;

import com.kalos.recursos.Recursos;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.0 $
 */
public enum TipoConjuncion {
    Coordinante("conjuncion.coordinante", "COORD"), Subordinante("conjuncion.subordinante", "SUBORD");
    
    private String etiquetaRecursos;
    private String abreviatura;
    
    TipoConjuncion (String etiquetaRecursos, String abreviatura){
        this.etiquetaRecursos = etiquetaRecursos;
        this.abreviatura = abreviatura;
        
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }   
    
    public String abreviatura(){
        return this.abreviatura;
    }
    

    
    public static TipoConjuncion getEnum(String abreviatura){
        for (TipoConjuncion enu : values()) {
            if (enu.abreviatura.equals(abreviatura)){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de TipoConjuncion para la abreviatura " + abreviatura);
    }    
    

   
}
