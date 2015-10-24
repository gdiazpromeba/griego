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
public enum TipoAdverbio {
    Modo("adverbio.modo", "MOD"), Tiempo("adverbio.tiempo", "TIE"), Lugar("adverbio.lugar", "LUG"), Cantidad("adverbio.cantidad", "CNT"), Medio("adverbio.medio", "MED"), 
    Frecuencia("adverbio.frecuencia", "FRC"), Negacion("adverbio.negacion", "NEG"), Causa("adverbio.causa", "CAU"),  Afirmacion("adverbio.afirmacion", "AFI"), Otro("adverbio.otro", "OTR");
    
    private String etiquetaRecursos;
    private String abreviatura;
    
    TipoAdverbio (String etiquetaRecursos, String abreviatura){
        this.etiquetaRecursos = etiquetaRecursos;
        this.abreviatura = abreviatura;     
    }
    
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }   
    
    public String abreviatura(){
        return this.abreviatura;
    }
 
    
    public static TipoAdverbio getEnum(String abreviatura){
        for (TipoAdverbio enu : values()) {
            if (enu.abreviatura.equals(abreviatura)){
                return enu;
            }
        }
        throw new RuntimeException("no hay enum de GradoComparacion para la abreviatura " + abreviatura);
    }     
 
    
}
