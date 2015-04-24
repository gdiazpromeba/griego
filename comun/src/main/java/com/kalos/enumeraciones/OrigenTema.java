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
public enum OrigenTema {
    NominativoSingular("NS", "origen_tema.nominativo_singular"), NominativoPlural("NP", "origen_tema.nominativo_plural"), 
    VocativoSingular("VS", "origen_tema.vocativo_singular"), GenitivoSingular("GS", "origen_tema.genitivo_singular"), 
    DativoPlural("DP", "origen_tema.dativo_plural");
    
    private final String abreviatura; 
    private final String etiquetaRecursos; 
    
    OrigenTema(String abreviatura, String etiquetaRecursos) {
        this.abreviatura = abreviatura;
        this.etiquetaRecursos = etiquetaRecursos;
    }
 



    public static OrigenTema getEnum(String s){
        for (OrigenTema p : OrigenTema.values()) {
            if (p.abreviatura.equals(s)) return p;
        }
        throw new RuntimeException("OrigenTema not found for string " + s );
    }
   
    public String toString(){
        return Recursos.getCadena(this.etiquetaRecursos);
    }
    
    public String abreviatura(){
        return this.abreviatura;
    }    
    
    

    
    

}
