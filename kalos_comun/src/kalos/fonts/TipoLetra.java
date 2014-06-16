// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.fonts;


import kalos.enumeraciones.ArticulacionConsonante;
import kalos.enumeraciones.CaracterLiq;
import kalos.enumeraciones.CompLetras;
import kalos.enumeraciones.Espiritu;
import kalos.operaciones.OpLetras;
import kalos.operaciones.excepciones.ExcepcionLetra;

import org.apache.log4j.Logger;

public class TipoLetra
    implements CompLetras
{


    public static int getTipoLetraBasico(char c)
    {
        char c1 = OpLetras.desespirituarLetra(OpLetras.desacentuarLetra(c));
        switch(c1)
        {
        case 917: 
        case 919: 
        case 927: 
        case 937: 
        case 949: 
        case 951: 
        case 959: 
        case 969: 
        case 8115: 
        case 8124: 
        case 8131: 
        case 8140: 
        case 8179: 
        case 8188: 
        case 9137: 
        case 9145: 
        case 9157: 
        case 9162: 
        case 9163: 
        case 13233: 
        case 13241: 
        case 13253: 
        case 13258: 
        case 13259: 
        case 17329: 
        case 17337: 
        case 17349: 
        case 21425: 
        case 21433: 
        case 21445: 
            return 1;

        case 914: 
        case 915: 
        case 916: 
        case 918: 
        case 920: 
        case 922: 
        case 923: 
        case 924: 
        case 925: 
        case 926: 
        case 928: 
        case 929: 
        case 931: 
        case 932: 
        case 934: 
        case 935: 
        case 936: 
        case 946: 
        case 947: 
        case 948: 
        case 950: 
        case 952: 
        case 954: 
        case 955: 
        case 956: 
        case 957: 
        case 958: 
        case 960: 
        case 961: 
        case 962: 
        case 963: 
        case 964: 
        case 966: 
        case 967: 
        case 968: 
            return 2;

        case 10: // '\n'
        case 32: // ' '
        case 33: // '!'
        case 38: // '&'
        case 42: // '*'
        case 43: // '+'
        case 44: // ','
        case 45: // '-'
        case 46: // '.'
        case 61: // '='
        case 63: // '?'
        case 95: // '_'
        case 8125: 
            return 3;
        }
        throw new ExcepcionLetra(c1, (new StringBuilder()).append("Error tratando de encontrar el tipo de letra b\341sico para ").append(c1).append(" hex=").append(Integer.toHexString(c1)).toString());
    }

    public static int getTipoVocal(char c)
    {
        char c1 = OpLetras.desespirituarLetra(OpLetras.desacentuarLetra(c));
        switch(c1)
        {
        case 917: 
        case 927: 
        case 949: 
        case 959: 
        case 9137: 
        case 9145: 
        case 9157: 
        case 9162: 
        case 9163: 
        case 17329: 
        case 17337: 
        case 17349: 
            return 1;

        case 919: 
        case 937: 
        case 951: 
        case 969: 
        case 8115: 
        case 8124: 
        case 8131: 
        case 8140: 
        case 8179: 
        case 8188: 
        case 13233: 
        case 13241: 
        case 13253: 
        case 13258: 
        case 13259: 
        case 21425: 
        case 21433: 
        case 21445: 
            return 2;
        }
        throw new RuntimeException((new StringBuilder()).append("Funci\363n TipoVocal: problemas para determinar qu\351 tipo de vocal es ").append(c1).append(" n\372mero ").append(c1).toString());
    }

    public static ArticulacionConsonante getArticulacionConsonante(char c)
    {
        char c1 = c;
        switch(c1)
        {
        case 950: 
        case 958: 
        case 968: 
            return ArticulacionConsonante.Doble;

        case 946: 
        case 960: 
        case 966: 
            return ArticulacionConsonante.Labial;

        case 947: 
        case 954: 
        case 967: 
            return ArticulacionConsonante.Gutural;

        case 948: 
        case 952: 
        case 964: 
            return ArticulacionConsonante.Dental;

        case 955: 
        case 961: 
            return ArticulacionConsonante.Lateral;

        case 956: 
        case 957: 
            return ArticulacionConsonante.Nasal;

        case 962: 
        case 963: 
            return ArticulacionConsonante.Silbante;

        case 949: 
        case 951: 
        case 953: 
        case 959: 
        case 965: 
        default:
            return ArticulacionConsonante.NoEsConsonante;
        }
    }

    public static int getCaracterLiq(char c)
    {
        char c1 = OpLetras.desespirituarLetra(OpLetras.desacentuarLetra(c));
        ArticulacionConsonante v = getArticulacionConsonante(c1);
        switch (v){
          case Dental:
          case Gutural:
          case Labial:
        	  return CaracterLiq.enuLicuadora;
          case Lateral:
        	  return CaracterLiq.enuLicuada;
          default:
        	  return CaracterLiq.ningunoDeLosDos;
        }
    }

    public static Espiritu getEspiritu(char c)
    {
        switch(c)
        {
        case 7943: 
        case 7951: 
        case 7953: 
        case 7955: 
        case 7957: 
        case 7961: 
        case 7963: 
        case 7965: 
        case 7969: 
        case 7971: 
        case 7973: 
        case 7975: 
        case 7977: 
        case 7979: 
        case 7981: 
        case 7983: 
        case 7991: 
        case 7999: 
        case 8001: 
        case 8003: 
        case 8005: 
        case 8009: 
        case 8011: 
        case 8013: 
        case 8023: 
        case 8031: 
        case 8033: 
        case 8035: 
        case 8037: 
        case 8039: 
        case 8041: 
        case 8043: 
        case 8045: 
        case 8047: 
        case 8065: 
        case 8067: 
        case 8069: 
        case 8071: 
        case 8073: 
        case 8075: 
        case 8077: 
        case 8079: 
        case 8081: 
        case 8083: 
        case 8085: 
        case 8087: 
        case 8089: 
        case 8091: 
        case 8093: 
        case 8095: 
        case 8097: 
        case 8099: 
        case 8101: 
        case 8103: 
        case 8107: 
        case 8109: 
        case 8111: 
        case 16129: 
        case 16131: 
        case 16133: 
        case 16177: 
        case 16179: 
        case 16181: 
        case 16209: 
        case 16211: 
        case 16213: 
        case 20225: 
        case 20227: 
        case 20229: 
        case 20273: 
        case 20275: 
        case 20277: 
        case 20305: 
        case 20307: 
        case 20309: 
        case 24321: 
        case 24323: 
        case 24325: 
        case 24369: 
        case 24371: 
        case 24373: 
        case 24401: 
        case 24403: 
        case 24405: 
        case 28417: 
        case 28419: 
        case 28421: 
        case 28465: 
        case 28467: 
        case 28469: 
        case 28497: 
        case 28499: 
        case 28501: 
            return Espiritu.Aspero;

        case 7942: 
        case 7950: 
        case 7952: 
        case 7954: 
        case 7956: 
        case 7960: 
        case 7962: 
        case 7964: 
        case 7968: 
        case 7970: 
        case 7972: 
        case 7974: 
        case 7976: 
        case 7978: 
        case 7980: 
        case 7982: 
        case 7990: 
        case 7998: 
        case 8000: 
        case 8002: 
        case 8004: 
        case 8008: 
        case 8010: 
        case 8012: 
        case 8022: 
        case 8032: 
        case 8034: 
        case 8036: 
        case 8038: 
        case 8040: 
        case 8042: 
        case 8044: 
        case 8064: 
        case 8066: 
        case 8068: 
        case 8070: 
        case 8072: 
        case 8074: 
        case 8076: 
        case 8078: 
        case 8080: 
        case 8082: 
        case 8084: 
        case 8086: 
        case 8088: 
        case 8090: 
        case 8092: 
        case 8094: 
        case 8096: 
        case 8098: 
        case 8100: 
        case 8102: 
        case 8104: 
        case 8106: 
        case 8108: 
        case 8110: 
        case 16128: 
        case 16130: 
        case 16132: 
        case 16176: 
        case 16178: 
        case 16180: 
        case 16208: 
        case 16212: 
        case 20224: 
        case 20226: 
        case 20228: 
        case 20272: 
        case 20274: 
        case 20276: 
        case 20304: 
        case 20306: 
        case 20308: 
        case 24320: 
        case 24322: 
        case 24324: 
        case 24368: 
        case 24370: 
        case 24372: 
        case 28416: 
        case 28418: 
        case 28420: 
        case 28464: 
        case 28466: 
        case 28468: 
        case 61466: 
            return Espiritu.Suave;
        }
        return Espiritu.Ninguno;
    }

    static Logger CC = Logger.getLogger(TipoLetra.class.getName());

}
