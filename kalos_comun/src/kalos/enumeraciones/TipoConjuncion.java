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
package kalos.enumeraciones;
/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.0 $
 */
public enum TipoConjuncion {
    Coordinante, Subordinante;
    
    public static String getCadena(TipoConjuncion tipo) {
        switch (tipo) {
        case Coordinante:
            return "COORD";
        case Subordinante:
            return "SUBORD";
        default:
            throw new RuntimeException("tipo de conjunción no encontrado");
        }
    }

    public static TipoConjuncion getEnum(String tipo) {
        if (tipo.equals("COORD"))
            return Coordinante;
        else if (tipo.equals("SUBORD"))
            return Subordinante;
            throw new RuntimeException("el tipo de conjunción para la cadena " + tipo + " no existe ");
    }
   
}
