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
/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.0 $
 */
public enum OrigenTema {
    NominativoSingular, NominativoPlural, VocativoSingular, GenitivoSingular, DativoPlural;
    
    
    public static String getCadena(OrigenTema origenTema){
        switch(origenTema){
          case NominativoSingular:
            return "NS";
          case NominativoPlural:
              return "NP";
          case VocativoSingular:
              return "VS";
          case GenitivoSingular:
              return "GS";
          case DativoPlural:
              return "DP";
          default:
            return "error";
        }
      }
    
    public static OrigenTema getEnum(String origenTema){
        if (origenTema.equals("NS"))
          return NominativoSingular;
        else if (origenTema.equals("NP"))
          return NominativoPlural;
        else if (origenTema.equals("VS"))
            return VocativoSingular;
        else if (origenTema.equals("GS"))
            return GenitivoSingular;
        else if (origenTema.equals("DP"))
            return DativoPlural;
        else 
            throw new RuntimeException("no sé qué origen de tema es la cadena " + origenTema);
    }    
    

}
