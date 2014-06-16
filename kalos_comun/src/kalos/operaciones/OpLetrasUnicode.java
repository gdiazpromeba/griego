package kalos.operaciones;

import kalos.enumeraciones.TipoLetraBasico;
import kalos.enumeraciones.uLetras;
import kalos.fonts.CaracterGriego;
import kalos.fonts.CaracterGriegoFactory;


/**
 * Qué se hace con las letras que no existen en Unicode, como "Alfa circunflejo mayúscula":
 *   - como entrada, no pueden existir (no se contemplan en los 'cases')
 *   - como salida, se "suavizan", es decir, por ejemplo, circunflejo(alfa mayúscula)=alfa circunfleja SUAVE mayúscula
 * 
 *   En general, no existen las "graves mayúsculas en ninguna combinación", ni las mayúsculas con acento sin
 *   indicación de espíritu.
 *   (en el caso de la épsilon, se "asperizan")
 */
public class OpLetrasUnicode implements uLetras {
	
	public static char desacentuarLetra(char ncar) {
		char ret;
		switch (ncar) {
		//alfa
		case cAlfaCircunflejo:
			ret = cAlfa;
			break;
		case cAlfaAsperoCircunflejo:
			ret = cAlfaAspero;
			break;
		case cAlfaSuaveCircunflejo:
			ret = cAlfaSuave;
			break;
		case cAlfaSubscriptaAgudo:
		case cAlfaSubscriptaGrave:
		case cAlfaSubscriptaCircunflejo:
			ret = cAlfaSubscripta;
			break;
		case cAlfaSubscriptaAsperoAgudo:
		case cAlfaSubscriptaAsperoGrave:
		case cAlfaSubscriptaAsperoCircunflejo:
			ret = cAlfaSubscriptaAspero;
			break;
		case cAlfaSubscriptaSuaveAgudo:
		case cAlfaSubscriptaSuaveGrave:
		case cAlfaSubscriptaSuaveCircunflejo:
			ret = cAlfaSubscriptaSuave;
			break;
			
			
		case cAlfaAsperoCircunflejoMayuscula:
			ret = cAlfaAsperoMayuscula;
			break;
		case cAlfaSuaveCircunflejoMayuscula:
			ret = cAlfaSuaveMayuscula;
			break;
		case cAlfaSubscriptaAsperoAgudoMayuscula:
		case cAlfaSubscriptaAsperoCircunflejoMayuscula:
			ret = cAlfaSubscriptaAsperoMayuscula;
			break;
		case cAlfaSubscriptaSuaveAgudoMayuscula:
		case cAlfaSubscriptaSuaveCircunflejoMayuscula:
			ret = cAlfaSubscriptaSuaveMayuscula;
			break;
			
			
			
			
			
			//omega
		case cOmegaAgudo:
		case cOmegaGrave:
		case cOmegaCircunflejo:
			ret = cOmega;
			break;
		case cOmegaAsperoAgudo:
		case cOmegaAsperoGrave:
		case cOmegaAsperoCircunflejo:
			ret = cOmegaAspero;
			break;
		case cOmegaSuaveAgudo:
		case cOmegaSuaveGrave:
		case cOmegaSuaveCircunflejo:
			ret = cOmegaSuave;
			break;
		case cOmegaSubscriptaAgudo:
		case cOmegaSubscriptaGrave:
		case cOmegaSubscriptaCircunflejo:
			ret = cOmegaSubscripta;
			break;
		case cOmegaSubscriptaAsperoAgudo:
		case cOmegaSubscriptaAsperoGrave:
		case cOmegaSubscriptaAsperoCircunflejo:
			ret = cOmegaSubscriptaAspero;
			break;
		case cOmegaSubscriptaSuaveAgudo:
		case cOmegaSubscriptaSuaveGrave:
		case cOmegaSubscriptaSuaveCircunflejo:
			ret = cOmegaSubscriptaSuave;
			break;
			
			
		case cOmegaAsperoAgudoMayuscula:
		case cOmegaAsperoCircunflejoMayuscula:
			ret = cOmegaAsperoMayuscula;
			break;
		case cOmegaSuaveAgudoMayuscula:
		case cOmegaSuaveCircunflejoMayuscula:
			ret = cOmegaSuaveMayuscula;
			break;
		case cOmegaSubscriptaAsperoAgudoMayuscula:
		case cOmegaSubscriptaAsperoCircunflejoMayuscula:
			ret = cOmegaSubscriptaMayuscula;
			break;
		case cOmegaSubscriptaSuaveAgudoMayuscula:
		case cOmegaSubscriptaSuaveCircunflejoMayuscula:
			ret = cOmegaSubscriptaSuaveMayuscula;
			break;
			
			
			
			//eta
		case cEtaAgudo:
		case cEtaGrave:
		case cEtaCircunflejo:
			ret = cEta;
			break;
		case cEtaAsperoAgudo:
		case cEtaAsperoGrave:
		case cEtaAsperoCircunflejo:
			ret = cEtaAspero;
			break;
		case cEtaSuaveAgudo:
		case cEtaSuaveGrave:
		case cEtaSuaveCircunflejo:
			ret = cEtaSuave;
			break;
		case cEtaSubscriptaAgudo:
		case cEtaSubscriptaGrave:
		case cEtaSubscriptaCircunflejo:
			ret = cEtaSubscripta;
			break;
		case cEtaSubscriptaAsperoAgudo:
		case cEtaSubscriptaAsperoGrave:
		case cEtaSubscriptaAsperoCircunflejo:
			ret = cEtaSubscriptaAspero;
			break;
		case cEtaSubscriptaSuaveAgudo:
		case cEtaSubscriptaSuaveGrave:
		case cEtaSubscriptaSuaveCircunflejo:
			ret = cEtaSubscriptaSuave;
			break;
			
		case cEtaAsperoAgudoMayuscula:
		case cEtaAsperoCircunflejoMayuscula:
			ret = cEtaAsperoMayuscula;
			break;
		case cEtaSuaveAgudoMayuscula:
		case cEtaSuaveCircunflejoMayuscula:
			ret = cEtaSuaveMayuscula;
			break;
		case cEtaSubscriptaAsperoAgudoMayuscula:
		case cEtaSubscriptaAsperoCircunflejoMayuscula:
			ret = cEtaSubscriptaAsperoMayuscula;
			break;
		case cEtaSubscriptaSuaveAgudoMayuscula:
		case cEtaSubscriptaSuaveCircunflejoMayuscula:
			ret = cEtaSubscriptaSuaveMayuscula;
			break;
			
			
			//epsilon
		case cEpsilonAgudo:
		case cEpsilonGrave:
		case cEpsilonCircunflejo:
			ret = cEpsilon;
			break;
		case cEpsilonAsperoGrave:
		case cEpsilonAsperoAgudo:
			ret = cEpsilonAspero;
			break;
		case cEpsilonSuaveAgudo:
		case cEpsilonSuaveGrave:
			ret = cEpsilonSuave;
			break;
			
		case cEpsilonAsperoAgudoMayuscula:
			ret = cEpsilonAsperoMayuscula;
			break;
		case cEpsilonSuaveAgudoMayuscula:
			ret = cEpsilonSuaveMayuscula;
			break;
			
			
			
			
			
			//omicron
		case cOmicronAgudo:
		case cOmicronGrave:
		case cOmicronCircunflejo:
			ret = cOmicron;
			break;
		case cOmicronAsperoAgudo:
		case cOmicronAsperoGrave:
			ret = cOmicronAspero;
			break;
		case cOmicronSuaveAgudo:
		case cOmicronSuaveGrave:
			ret = cOmicronSuave;
			break;
			
		case cOmicronAsperoAgudoMayuscula:
			ret = cOmicronAsperoMayuscula;
			break;
		case cOmicronSuaveAgudoMayuscula:
			ret = cOmicronSuaveMayuscula;
			break;
			
			
			
			
			//iota
		case cIotaCircunflejo:
			ret = cIota;
			break;
		case cIotaAsperoCircunflejo:
			ret = cIotaAspero;
			break;
		case cIotaSuaveCircunflejo:
			ret = cIotaSuave;
			break;
			
			
		case cIotaAsperoCircunflejoMayuscula:
			ret = cIotaAsperoMayuscula;
			break;
		case cIotaSuaveCircunflejoMayuscula:
			ret = cIotaSuaveMayuscula;
			break;
			
			
			
			//upsilon
		case cUpsilonCircunflejo:
			ret = cUpsilon;
			break;
		case cUpsilonAsperoCircunflejo:
			ret = cUpsilonAspero;
			break;
		case cUpsilonSuaveCircunflejo:
			ret = cUpsilonSuave;
			break;
			
			
			
			
			
			//alfa
		case cAlfaAgudo:
		case cAlfaGrave:
			ret = cAlfa;
			break;
		case cAlfaAsperoAgudo:
		case cAlfaAsperoGrave:
			ret = cAlfaAspero;
			break;
		case cAlfaSuaveAgudo:
		case cAlfaSuaveGrave:
			ret = cAlfaSuave;
			break;
			
			
		case cAlfaSuaveAgudoMayuscula:
		
			ret = cAlfaSuaveMayuscula;
			break;
		case cAlfaAsperoAgudoMayuscula:
		
			ret = cAlfaAsperoMayuscula;
			break;
			
			
			
			//iota
		case cIotaAgudo:
		case cIotaGrave:
			ret = cIota;
			break;
		case cIotaAsperoAgudo:
		case cIotaAsperoGrave:
			ret = cIotaAspero;
			break;
		case cIotaSuaveAgudo:
		case cIotaSuaveGrave:
			ret = cIotaSuave;
			break;
		case cIotaDieresisAgudo:
			ret = cIotaDieresis;
			break;
			

		case cIotaAsperoAgudoMayuscula:
		case cIotaSuaveAgudoMayuscula:
			
			//upsilon
		case cUpsilonAgudo:
		case cUpsilonGrave:
			ret = cUpsilon;
			break;
		case cUpsilonAsperoAgudo:
		case cUpsilonAsperoGrave:
			ret = cUpsilonAspero;
			break;
		case cUpsilonSuaveAgudo:
		case cUpsilonSuaveGrave:
			ret = cUpsilonSuave;
			break;
			
			
			
		case cUpsilonDieresisAgudo:
		case cUpsilonDieresisGrave:
			ret = cUpsilonDieresis;
			break;
			
		case cUpsilonAgudoMayuscula:

		case cUpsilonAsperoAgudoMayuscula:
			
		default:
			ret = ncar;
		break;
		}
		;
		return ret;
	}
	
	public static char acentoCircunflejoLetra(char letra){
		char ret;
		switch(letra){
		//alfa
		
		case cAlfa:
			ret =   cAlfaCircunflejo; break;
		case cAlfaAspero:
			ret =  cAlfaAsperoCircunflejo; break;
		case cAlfaSuave:
			ret =  cAlfaSuaveCircunflejo; break;
		case cAlfaSubscripta:
			ret =  cAlfaSubscriptaCircunflejo; break;
		case cAlfaSubscriptaAspero:
			ret =  cAlfaSubscriptaAsperoCircunflejo; break;
		case cAlfaSubscriptaSuave:
			ret =  cAlfaSubscriptaSuaveCircunflejo; break;
			
		case cAlfaMayuscula:
			ret =   cAlfaSuaveCircunflejoMayuscula; break;  //suavizada
		case cAlfaAsperoMayuscula:
			ret =  cAlfaAsperoCircunflejoMayuscula; break;
		case cAlfaSuaveMayuscula:
			ret =  cAlfaSuaveCircunflejoMayuscula; break;
		case cAlfaSubscriptaMayuscula:
			ret =  cAlfaSubscriptaSuaveCircunflejoMayuscula; break;  //suavizada
		case cAlfaSubscriptaAsperoMayuscula:
			ret =  cAlfaSubscriptaAsperoCircunflejoMayuscula; break;
		case cAlfaSubscriptaSuaveMayuscula:
			ret =  cAlfaSubscriptaSuaveCircunflejoMayuscula; break;
			
			
			
			//omega
		case cOmega:
			ret =  cOmegaCircunflejo; break;
		case cOmegaAspero:
			ret =  cOmegaAsperoCircunflejo; break;
		case cOmegaSuave:
			ret =  cOmegaSuaveCircunflejo; break;
		case cOmegaSubscripta:
			ret =  cOmegaSubscriptaCircunflejo; break;
		case  cOmegaSubscriptaAspero:
			ret =  cOmegaSubscriptaAsperoCircunflejo; break;
		case  cOmegaSubscriptaSuave:
			ret =  cOmegaSubscriptaSuaveCircunflejo; break;
			
			
		case cOmegaAsperoMayuscula:
			ret =  cOmegaAsperoCircunflejoMayuscula; break;
		case cOmegaSuaveMayuscula:
			ret =  cOmegaSuaveCircunflejoMayuscula; break;
		case  cOmegaSubscriptaAsperoMayuscula:
			ret =  cOmegaSubscriptaAsperoCircunflejoMayuscula; break;
		case  cOmegaSubscriptaSuaveMayuscula:
			ret =  cOmegaSubscriptaSuaveCircunflejoMayuscula; break;
			
			
			//eta
		case  cEta:
			ret =  cEtaCircunflejo; break;
		case cEtaAspero:
			ret =  cEtaAsperoCircunflejo; break;
		case cEtaSuave:
			ret =  cEtaSuaveCircunflejo; break;
		case  cEtaSubscripta:
			ret =  cEtaSubscriptaCircunflejo; break;
		case cEtaSubscriptaAspero:
			ret =  cEtaSubscriptaAsperoCircunflejo; break;
		case cEtaSubscriptaSuave:
			ret =  cEtaSubscriptaSuaveCircunflejo; break;
			
		case  cEtaMayuscula:
			ret =  cEtaSuaveCircunflejoMayuscula; break;
		case cEtaAsperoMayuscula:
			ret =  cEtaAsperoCircunflejoMayuscula; break;
		case cEtaSuaveMayuscula:
			ret =  cEtaSuaveCircunflejoMayuscula; break;
		case  cEtaSubscriptaMayuscula:
			ret =  cEtaSubscriptaSuaveCircunflejoMayuscula; break;
		case cEtaSubscriptaAsperoMayuscula:
			ret =  cEtaSubscriptaAsperoCircunflejoMayuscula; break;
		case cEtaSubscriptaSuaveMayuscula:
			ret =  cEtaSubscriptaSuaveCircunflejoMayuscula; break;
			
			
			//iota
		case cIota:
			ret =  cIotaCircunflejo; break;
		case cIotaAspero:
			ret =  cIotaAsperoCircunflejo; break;
		case cIotaSuave:
			ret =  cIotaSuaveCircunflejo; break;
			
		case cIotaMayuscula:
			ret =  cIotaSuaveCircunflejoMayuscula; break;
		case cIotaAsperoMayuscula:
			ret =  cIotaAsperoCircunflejoMayuscula; break;
		case cIotaSuaveMayuscula:
			ret =  cIotaSuaveCircunflejoMayuscula; break;
			
			
			//upsilon
		case cUpsilon:
			ret =  cUpsilonCircunflejo; break;
		case cUpsilonAspero:
			ret =  cUpsilonAsperoCircunflejo; break;
		case cUpsilonSuave:
			ret =  cUpsilonSuaveCircunflejo; break;
			
			
		case cUpsilonMayuscula:
			ret =  cUpsilonAsperoCircunflejoMayuscula; break; //el único épsilon circunflejo mayúscula que hay
		case cUpsilonAsperoMayuscula:
			ret =  cUpsilonAsperoCircunflejoMayuscula; break;
			
		default:
			ret =  letra;
		}
		return ret;
	}
	public static char acentoAgudoLetra (char letra){
		char ret;
		switch (letra){
		//alfa
		case cAlfaSubscripta:
			ret=  cAlfaSubscriptaAgudo; break;
		case cAlfaSubscriptaAspero:
			ret= cAlfaSubscriptaAsperoAgudo; break;
		case cAlfaSubscriptaSuave:
			ret= cAlfaSubscriptaSuaveAgudo; break;
			
		case cAlfaSubscriptaMayuscula:
			ret=  cAlfaSubscriptaSuaveAgudoMayuscula; break;
		case cAlfaSubscriptaAsperoMayuscula:
			ret= cAlfaSubscriptaAsperoAgudoMayuscula; break;
		case cAlfaSubscriptaSuaveMayuscula:
			ret= cAlfaSubscriptaSuaveAgudoMayuscula; break;
			
			
			
			
			
			//omega
		case cOmega:
			ret= cOmegaAgudo; break;
		case cOmegaAspero:
			ret= cOmegaAsperoAgudo; break;
		case cOmegaSuave:
			ret= cOmegaSuaveAgudo; break;
		case cOmegaSubscripta:
			ret= cOmegaSubscriptaAgudo; break;
		case  cOmegaSubscriptaAspero:
			ret= cOmegaSubscriptaAsperoAgudo; break;
		case  cOmegaSubscriptaSuave:
			ret= cOmegaSubscriptaSuaveAgudo; break;
			
		case cOmegaAsperoMayuscula:
			ret = cOmegaAsperoAgudoMayuscula;
			break;
		case cOmegaSuaveMayuscula:
			ret = cOmegaSuaveAgudoMayuscula;
			break;
		case cOmegaSubscriptaAsperoMayuscula:
			ret = cOmegaSubscriptaAsperoAgudoMayuscula;
			break;
		case cOmegaSubscriptaSuaveMayuscula:
			ret = cOmegaSubscriptaSuaveAgudoMayuscula;
			break;
			
			//eta
		case  cEta:
			ret= cEtaAgudo; break;
		case cEtaAspero:
			ret= cEtaAsperoAgudo; break;
		case cEtaSuave:
			ret= cEtaSuaveAgudo; break;
		case  cEtaSubscripta:
			ret= cEtaSubscriptaAgudo;  break;
		case cEtaSubscriptaAspero:
			ret= cEtaSubscriptaAsperoAgudo; break;
		case cEtaSubscriptaSuave:
			ret= cEtaSubscriptaSuaveAgudo;  break;
			
		case cEtaMayuscula:
			ret = cEtaSuaveAgudoMayuscula;
			break;
		case cEtaAsperoMayuscula:
			ret = cEtaAsperoAgudoMayuscula;
			break;
		case cEtaSuaveMayuscula:
			ret = cEtaSuaveAgudoMayuscula;
			break;
		case cEtaSubscriptaAsperoMayuscula:
			ret = cEtaSubscriptaAsperoAgudoMayuscula;
			break;
		case cEtaSubscriptaSuaveMayuscula:
			ret = cEtaSubscriptaSuaveAgudoMayuscula;
			break;
			
			
			
			//épsilon
		case cEpsilon:
			ret= cEpsilonAgudo; break;
		case cEpsilonAspero:
			ret= cEpsilonAsperoAgudo; break;
		case cEpsilonSuave:
			ret= cEpsilonSuaveAgudo;  break;
			
		case cEpsilonAsperoMayuscula:
			ret = cEpsilonAsperoAgudoMayuscula;
			break;
		case cEpsilonSuaveMayuscula:
			ret = cEpsilonSuaveAgudoMayuscula;
			break;
			
			
			
			//ómicron
		case cOmicron:
			ret= cOmicronAgudo; break;
		case cOmicronAspero:
			ret= cOmicronAsperoAgudo; break;
		case cOmicronSuave:
			ret= cOmicronSuaveAgudo; break;
			
		case cOmicronAsperoMayuscula:
			ret = cOmicronAsperoAgudoMayuscula;
			break;
		case cOmicronSuaveMayuscula:
			ret = cOmicronSuaveAgudoMayuscula;
			break;
			
			
			
			//s-s ***********************
			//alfa
			
			
			
			//iota
			
			
			
			
			//épsilon
			
			
			//alfa
		case cAlfa:
			ret= cAlfaAgudo; break;
		case cAlfaAspero:
			ret= cAlfaAsperoAgudo; break;
		case cAlfaSuave:
			ret=  cAlfaSuaveAgudo; break;
			
		case cAlfaAsperoMayuscula:
			ret= cAlfaAsperoAgudoMayuscula; break;
		case cAlfaSuaveMayuscula:
			ret=  cAlfaSuaveAgudoMayuscula; break;
			
			
			//iota
		case cIota:
			ret= cIotaAgudo; break;
		case cIotaAspero:
			ret= cIotaAsperoAgudo; break;
		case cIotaSuave:
			ret= cIotaSuaveAgudo;  break;
		case cIotaDieresis:
			ret= cIotaDieresisAgudo; break;
			
		case cIotaAsperoMayuscula:
			ret= cIotaAsperoAgudoMayuscula; break;
		case cIotaSuaveMayuscula:
			ret= cIotaSuaveAgudoMayuscula;  break;
			
			
			//épsilon
		case cUpsilon:
			ret= cUpsilonAgudo; break;
		case cUpsilonAspero:
			ret= cUpsilonAsperoAgudo; break;
		case cUpsilonSuave:
			ret= cUpsilonSuaveAgudo;  break;
		case cUpsilonDieresis:
			ret= cUpsilonDieresisAgudo; break;
		case cUpsilonAsperoMayuscula:
			ret = cUpsilonAsperoAgudoMayuscula;
			break;
			
			
			
		default:
			ret= letra;
		}
		return ret;
	}
	
	
	public static char acentoGraveLetra (char letra){
		char ret;
		switch (letra){
		//alfa
		case cAlfaSubscripta:
			ret=  cAlfaSubscriptaGrave; break;
		case cAlfaSubscriptaAspero:
			ret= cAlfaSubscriptaAsperoGrave; break;
		case cAlfaSubscriptaSuave:
			ret= cAlfaSubscriptaSuaveGrave; break;
			
		case cAlfaSubscriptaAsperoMayuscula:
			ret= cAlfaSubscriptaAsperoGraveMayuscula; break;
		case cAlfaSubscriptaSuaveMayuscula:
			ret= cAlfaSubscriptaSuaveGraveMayuscula; break;
			
			
			
			
			
			//omega
		case cOmega:
			ret= cOmegaGrave; break;
		case cOmegaAspero:
			ret= cOmegaAsperoGrave; break;
		case cOmegaSuave:
			ret= cOmegaSuaveGrave; break;
		case cOmegaSubscripta:
			ret= cOmegaSubscriptaGrave; break;
		case  cOmegaSubscriptaAspero:
			ret= cOmegaSubscriptaAsperoGrave; break;
		case  cOmegaSubscriptaSuave:
			ret= cOmegaSubscriptaSuaveGrave; break;
			
		case cOmegaAsperoMayuscula:
			ret = cOmegaAsperoGraveMayuscula;
			break;
		case cOmegaSuaveMayuscula:
			ret = cOmegaSuaveGraveMayuscula;
			break;
		case cOmegaSubscriptaAsperoMayuscula:
			ret = cOmegaSubscriptaAsperoGraveMayuscula;
			break;
		case cOmegaSubscriptaSuaveMayuscula:
			ret = cOmegaSubscriptaSuaveGraveMayuscula;
			break;
			
			//eta
		case  cEta:
			ret= cEtaGrave; break;
		case cEtaAspero:
			ret= cEtaAsperoGrave; break;
		case cEtaSuave:
			ret= cEtaSuaveGrave; break;
		case  cEtaSubscripta:
			ret= cEtaSubscriptaGrave;  break;
		case cEtaSubscriptaAspero:
			ret= cEtaSubscriptaAsperoGrave; break;
		case cEtaSubscriptaSuave:
			ret= cEtaSubscriptaSuaveGrave;  break;
			
		case cEtaMayuscula:
			ret = cEtaSuaveGraveMayuscula;
			break;
		case cEtaAsperoMayuscula:
			ret = cEtaAsperoGraveMayuscula;
			break;
		case cEtaSuaveMayuscula:
			ret = cEtaSuaveGraveMayuscula;
			break;
		case cEtaSubscriptaAsperoMayuscula:
			ret = cEtaSubscriptaAsperoGraveMayuscula;
			break;
		case cEtaSubscriptaSuaveMayuscula:
			ret = cEtaSubscriptaSuaveGraveMayuscula;
			break;
			
			
			
			//épsilon
		case cEpsilon:
			ret= cEpsilonGrave; break;
		case cEpsilonAspero:
			ret= cEpsilonAsperoGrave; break;
		case cEpsilonSuave:
			ret= cEpsilonSuaveGrave;  break;
			
		case cEpsilonAsperoMayuscula:
			ret = cEpsilonAsperoGraveMayuscula;
			break;
		case cEpsilonSuaveMayuscula:
			ret = cEpsilonSuaveGraveMayuscula;
			break;
			
			
			
			//ómicron
		case cOmicron:
			ret= cOmicronGrave; break;
		case cOmicronAspero:
			ret= cOmicronAsperoGrave; break;
		case cOmicronSuave:
			ret= cOmicronSuaveGrave; break;
			
		case cOmicronAsperoMayuscula:
			ret = cOmicronAsperoGraveMayuscula;
			break;
		case cOmicronSuaveMayuscula:
			ret = cOmicronSuaveGraveMayuscula;
			break;
			
			
			
			//s-s ***********************
			//alfa
			
			
			
			
			//alfa
		case cAlfa:
			ret= cAlfaGrave; break;
		case cAlfaAspero:
			ret= cAlfaAsperoGrave; break;
		case cAlfaSuave:
			ret=  cAlfaSuaveGrave; break;
			
		case cAlfaAsperoMayuscula:
			ret= cAlfaAsperoGraveMayuscula; break;
		case cAlfaSuaveMayuscula:
			ret=  cAlfaSuaveGraveMayuscula; break;
			
			
			//iota
		case cIota:
			ret= cIotaGrave; break;
		case cIotaAspero:
			ret= cIotaAsperoGrave; break;
		case cIotaSuave:
			ret= cIotaSuaveGrave;  break;
		case cIotaDieresis:
			ret= cIotaDieresisGrave; break;
			
		case cIotaAsperoMayuscula:
			ret= cIotaAsperoGraveMayuscula; break;
		case cIotaSuaveMayuscula:
			ret= cIotaSuaveGraveMayuscula;  break;
			
			
			//épsilon
		case cUpsilon:
			ret= cUpsilonGrave; break;
		case cUpsilonAspero:
			ret= cUpsilonAsperoGrave; break;
		case cUpsilonSuave:
			ret= cUpsilonSuaveGrave;  break;
		case cUpsilonDieresis:
			ret= cUpsilonDieresisGrave; break;
		case cUpsilonAsperoMayuscula:
			ret = cUpsilonAsperoGraveMayuscula;
			break;
			
			
			
		default:
			ret= letra;
		}
		return ret;
	}
	
	/**
	 * Pasa un carácter de Unicode a Completo. 
	 * Si el carácter no tiene información de -, se asume corto.
	 * @param caracter
	 * @return
	 * @throws ExcepcionCaracterNoEncontrado
	 */
	public static char carUnicodeACompleto(char caracter) throws ExcepcionCaracterNoEncontrado{
	  switch(caracter){
		case uLetras.cAlfa: return cAlfa;
		case uLetras.cIota: return cIota;
		case uLetras.cEta: return cEta;
		case uLetras.cOmicron: return cOmicron;
		case uLetras.cOmega: return cOmega;
		case uLetras.cUpsilon: return cUpsilon;
		case uLetras.cEpsilon: return cEpsilon;
		
		case uLetras.cEtaSubscripta: return cEtaSubscripta;
		case uLetras.cEtaAspero: return cEtaAspero;
		case uLetras.cEtaSuave: return cEtaSuave;
		case uLetras.cEtaAgudo: return cEtaAgudo;
		case uLetras.cEtaAsperoAgudo: return cEtaAsperoAgudo;
		case uLetras.cEtaSuaveAgudo: return cEtaSuaveAgudo;
		case uLetras.cEtaGrave: return cEtaGrave;
		case uLetras.cEtaAsperoGrave: return cEtaAsperoGrave;
		case uLetras.cEtaSuaveGrave: return cEtaSuaveGrave;
		case uLetras.cEtaCircunflejo: return cEtaCircunflejo;
		case uLetras.cEtaAsperoCircunflejo: return cEtaAsperoCircunflejo;
		case uLetras.cEtaSuaveCircunflejo: return cEtaSuaveCircunflejo;
		case uLetras.cEtaSubscriptaAspero: return cEtaSubscriptaAspero;
		case uLetras.cEtaSubscriptaSuave: return cEtaSubscriptaSuave;
		case uLetras.cEtaSubscriptaAgudo: return cEtaSubscriptaAgudo;
		case uLetras.cEtaSubscriptaAsperoAgudo: return cEtaSubscriptaAsperoAgudo;
		case uLetras.cEtaSubscriptaSuaveAgudo: return cEtaSubscriptaSuaveAgudo;
		case uLetras.cEtaSubscriptaGrave: return cEtaSubscriptaGrave;
		case uLetras.cEtaSubscriptaAsperoGrave: return cEtaSubscriptaAsperoGrave;
		case uLetras.cEtaSubscriptaSuaveGrave: return cEtaSubscriptaSuaveGrave;
		case uLetras.cEtaSubscriptaCircunflejo: return cEtaSubscriptaCircunflejo;
		case uLetras.cEtaSubscriptaAsperoCircunflejo: return cEtaSubscriptaAsperoCircunflejo;
		case uLetras.cEtaSubscriptaSuaveCircunflejo: return cEtaSubscriptaSuaveCircunflejo;
		
		case uLetras.cIotaAspero: return cIotaAspero;
		case uLetras.cIotaSuave: return cIotaSuave;
		case uLetras.cIotaAgudo: return cIotaAgudo;
		case uLetras.cIotaAsperoAgudo: return cIotaAsperoAgudo;
		case uLetras.cIotaSuaveAgudo: return cIotaSuaveAgudo;
		case uLetras.cIotaGrave: return cIotaGrave;
		case uLetras.cIotaAsperoGrave: return cIotaAsperoGrave;
		case uLetras.cIotaSuaveGrave: return cIotaSuaveGrave;
		case uLetras.cIotaCircunflejo: return cIotaCircunflejo;
		case uLetras.cIotaAsperoCircunflejo: return cIotaAsperoCircunflejo;
		case uLetras.cIotaSuaveCircunflejo: return cIotaSuaveCircunflejo;
		case uLetras.cIotaDieresis: return cIotaDieresis;
		case uLetras.cIotaDieresisAgudo: return cIotaDieresisAgudo;
		case uLetras.cIotaDieresisGrave: return cIotaDieresisGrave;
		
		
		
		
		case uLetras.cAlfaSubscripta: return cAlfaSubscripta;
		
		case uLetras.cAlfaAspero: return cAlfaAspero;
		case uLetras.cAlfaSuave: return cAlfaSuave;
		case uLetras.cAlfaAgudo: return cAlfaAgudo;
		case uLetras.cAlfaAsperoAgudo: return cAlfaAsperoAgudo;
		case uLetras.cAlfaSuaveAgudo: return cAlfaSuaveAgudo;
		case uLetras.cAlfaGrave: return cAlfaGrave;
		case uLetras.cAlfaAsperoGrave: return cAlfaAsperoGrave;
		case uLetras.cAlfaSuaveGrave: return cAlfaSuaveGrave;
		case uLetras.cAlfaCircunflejo: return cAlfaCircunflejo;
		case uLetras.cAlfaAsperoCircunflejo: return cAlfaAsperoCircunflejo;
		case uLetras.cAlfaSuaveCircunflejo: return cAlfaSuaveCircunflejo;
		case uLetras.cAlfaSubscriptaAspero: return cAlfaSuaveCircunflejo;
		case uLetras.cAlfaSubscriptaSuave: return cAlfaSubscriptaSuave;
		case uLetras.cAlfaSubscriptaAgudo: return cAlfaSubscriptaAgudo;
		case uLetras.cAlfaSubscriptaAsperoAgudo: return cAlfaSubscriptaAsperoAgudo;
		case uLetras.cAlfaSubscriptaSuaveAgudo: return cAlfaSubscriptaSuaveAgudo;
		case uLetras.cAlfaSubscriptaGrave: return cAlfaSubscriptaGrave;
		case uLetras.cAlfaSubscriptaAsperoGrave: return cAlfaSubscriptaAsperoGrave;
		case uLetras.cAlfaSubscriptaSuaveGrave: return cAlfaSubscriptaSuaveGrave;
		case uLetras.cAlfaSubscriptaCircunflejo: return cAlfaSubscriptaCircunflejo;
		case uLetras.cAlfaSubscriptaAsperoCircunflejo: return cAlfaSubscriptaAsperoCircunflejo;
		case uLetras.cAlfaSubscriptaSuaveCircunflejo: return cAlfaSubscriptaSuaveCircunflejo;
		
		
		case uLetras.cOmegaSubscripta: return cOmegaSubscripta;
		case uLetras.cOmegaSubscriptaAspero: return cOmegaSubscriptaAspero;
		case uLetras.cOmegaSubscriptaSuave: return cOmegaSubscriptaSuave;
		case uLetras.cOmegaAspero: return cOmegaAspero;
		case uLetras.cOmegaSuave: return cOmegaSuave;
		case uLetras.cOmegaAgudo: return cOmegaAgudo;
		case uLetras.cOmegaAsperoAgudo: return cOmegaAsperoAgudo;
		case uLetras.cOmegaSuaveAgudo: return cOmegaSuaveAgudo;
		case uLetras.cOmegaGrave: return cOmegaGrave;
		case uLetras.cOmegaAsperoGrave: return cOmegaAsperoGrave;
		case uLetras.cOmegaSuaveGrave: return cOmegaSuaveGrave;
		case uLetras.cOmegaCircunflejo: return cOmegaCircunflejo;
		case uLetras.cOmegaAsperoCircunflejo: return cOmegaAsperoCircunflejo;
		case uLetras.cOmegaSuaveCircunflejo: return cOmegaSuaveCircunflejo;
		case uLetras.cOmegaSubscriptaAgudo: return cOmegaSubscriptaAgudo;
		case uLetras.cOmegaSubscriptaAsperoAgudo: return cOmegaSubscriptaAsperoAgudo;
		case uLetras.cOmegaSubscriptaSuaveAgudo: return cOmegaSubscriptaSuaveAgudo;
		case uLetras.cOmegaSubscriptaGrave: return cOmegaSubscriptaGrave;
		case uLetras.cOmegaSubscriptaAsperoGrave: return cOmegaSubscriptaAsperoGrave;
		case uLetras.cOmegaSubscriptaSuaveGrave: return cOmegaSubscriptaSuaveGrave;
		case uLetras.cOmegaSubscriptaCircunflejo: return cOmegaSubscriptaCircunflejo;
		case uLetras.cOmegaSubscriptaAsperoCircunflejo: return cOmegaSubscriptaAsperoCircunflejo;
		case uLetras.cOmegaSubscriptaSuaveCircunflejo: return cOmegaSubscriptaSuaveCircunflejo;
		
		
		case uLetras.cEpsilonAspero: return cEpsilonAspero;
		case uLetras.cEpsilonSuave: return cEpsilonSuave;
		case uLetras.cEpsilonAgudo: return cEpsilonAgudo;
		case uLetras.cEpsilonAsperoAgudo: return cEpsilonAsperoAgudo;
		case uLetras.cEpsilonSuaveAgudo: return cEpsilonSuaveAgudo;
		case uLetras.cEpsilonGrave: return cEpsilonGrave;
		case uLetras.cEpsilonAsperoGrave: return cEpsilonAsperoGrave;
		case uLetras.cEpsilonSuaveGrave: return cEpsilonSuaveGrave;
		case uLetras.cEpsilonCircunflejo: return cEpsilonCircunflejo;
		
		
		case uLetras.cOmicronAspero: return cOmicronAspero;
		case uLetras.cOmicronSuave: return cOmicronSuave;
		case uLetras.cOmicronAgudo: return cOmicronAgudo;
		case uLetras.cOmicronAsperoAgudo: return cOmicronAsperoAgudo;
		case uLetras.cOmicronSuaveAgudo: return cOmicronSuaveAgudo;
		case uLetras.cOmicronGrave: return cOmicronGrave;
		case uLetras.cOmicronAsperoGrave: return cOmicronAsperoGrave;
		case uLetras.cOmicronSuaveGrave: return cOmicronSuaveGrave;
		case uLetras.cOmicronCircunflejo: return cOmicronCircunflejo;
		
		
		case uLetras.cUpsilonAspero: return cUpsilonAspero;
		case uLetras.cUpsilonSuave: return cUpsilonSuave;
		case uLetras.cUpsilonAgudo: return cUpsilonAgudo;
		case uLetras.cUpsilonAsperoAgudo: return cUpsilonAsperoAgudo;
		case uLetras.cUpsilonSuaveAgudo: return cUpsilonSuaveAgudo;
		case uLetras.cUpsilonGrave: return cUpsilonGrave;
		case uLetras.cUpsilonAsperoGrave: return cUpsilonAsperoGrave;
		case uLetras.cUpsilonSuaveGrave: return cUpsilonSuaveGrave;
		case uLetras.cUpsilonCircunflejo: return cUpsilonCircunflejo;
		case uLetras.cUpsilonAsperoCircunflejo: return cUpsilonAsperoCircunflejo;
		case uLetras.cUpsilonSuaveCircunflejo: return cUpsilonSuaveCircunflejo;
		case uLetras.cUpsilonDieresis: return cUpsilonDieresis;
		case uLetras.cUpsilonDieresisAgudo: return cUpsilonDieresisAgudo;
		case uLetras.cUpsilonDieresisGrave: return cUpsilonDieresisGrave;
		
		
		
		// consonantes
		case uLetras.cBeta: return cBeta;
		case uLetras.cJi: return cJi;
		case uLetras.cDelta: return cDelta;
		case uLetras.cFi: return cFi;
		case uLetras.cGamma: return cGamma;
		case uLetras.cKappa: return cKappa;
		case uLetras.cLambda: return cLambda;
		case uLetras.cMu: return cMu;
		case uLetras.cNu: return cNu;
		case uLetras.cSigma: return cSigma;
		case uLetras.cSigmaFinal: return cSigmaFinal;
		case uLetras.cTau: return cTau;
		case uLetras.cPi: return cPi;
		case uLetras.cTheta: return cTheta;
		case uLetras.cRho:
		case uLetras.cRhoAspero:
		case uLetras.cRhoSuave:
			return cRho;
		case uLetras.cPsi: return cPsi;
		case uLetras.cDzeta: return cDzeta;
		case uLetras.cXi: return cXi;
		
		//mayúsculas
		case uLetras.cAlfaMayuscula: return cAlfaMayuscula;
		case uLetras.cIotaMayuscula: return cIotaMayuscula;
		case uLetras.cEtaMayuscula: return cEtaMayuscula;
		case uLetras.cOmicronMayuscula: return cOmicronMayuscula;
		case uLetras.cOmegaMayuscula: return cOmegaMayuscula;
		case uLetras.cUpsilonMayuscula: return cUpsilonMayuscula;
		case uLetras.cEpsilonMayuscula: return cEpsilonMayuscula;
		
		case uLetras.cEtaSubscriptaMayuscula: return cEtaSubscriptaMayuscula;
		case uLetras.cEtaAsperoMayuscula: return cEtaAsperoMayuscula;
		case uLetras.cEtaSuaveMayuscula: return cEtaSuaveMayuscula;
		case uLetras.cEtaAsperoAgudoMayuscula: return cEtaAsperoAgudoMayuscula;
		case uLetras.cEtaSuaveAgudoMayuscula: return cEtaSuaveAgudoMayuscula;
		
		case uLetras.cEtaAsperoCircunflejoMayuscula: return cEtaAsperoCircunflejoMayuscula;
		case uLetras.cEtaSuaveCircunflejoMayuscula: return cEtaSuaveCircunflejoMayuscula;
		case uLetras.cEtaSubscriptaAsperoMayuscula: return cEtaSubscriptaAsperoMayuscula;
		case uLetras.cEtaSubscriptaSuaveMayuscula: return cEtaSubscriptaSuaveMayuscula;
		
		case uLetras.cEtaSubscriptaAsperoAgudoMayuscula: return cEtaSubscriptaAsperoAgudoMayuscula;
		case uLetras.cEtaSubscriptaSuaveAgudoMayuscula: return cEtaSubscriptaSuaveAgudoMayuscula;
		case uLetras.cEtaSubscriptaAsperoCircunflejoMayuscula: return cEtaSubscriptaAsperoCircunflejoMayuscula;
		case uLetras.cEtaSubscriptaSuaveCircunflejoMayuscula: return cEtaSubscriptaSuaveCircunflejoMayuscula;
		
		case uLetras.cIotaAsperoMayuscula: return cIotaAsperoMayuscula;
		case uLetras.cIotaSuaveMayuscula: return cIotaSuaveMayuscula;
		
		case uLetras.cIotaAsperoAgudoMayuscula: return cIotaAsperoAgudoMayuscula;
		case uLetras.cIotaSuaveAgudoMayuscula: return cIotaSuaveAgudoMayuscula;
		case uLetras.cIotaAsperoCircunflejoMayuscula: return cIotaAsperoCircunflejoMayuscula;
		case uLetras.cIotaSuaveCircunflejoMayuscula: return cIotaSuaveCircunflejoMayuscula;
		
		
		
		
		case uLetras.cAlfaSubscriptaMayuscula: return cAlfaSubscriptaMayuscula;
		
		case uLetras.cAlfaAsperoMayuscula: return cAlfaAsperoMayuscula;
		case uLetras.cAlfaSuaveMayuscula: return cAlfaSuaveMayuscula;
		
		case uLetras.cAlfaAsperoAgudoMayuscula: return cAlfaAsperoAgudoMayuscula;
		case uLetras.cAlfaSuaveAgudoMayuscula: return cAlfaSuaveAgudoMayuscula;
		case uLetras.cAlfaAsperoCircunflejoMayuscula: return cAlfaAsperoCircunflejoMayuscula;
		case uLetras.cAlfaSuaveCircunflejoMayuscula: return cAlfaSuaveCircunflejoMayuscula;
		case uLetras.cAlfaSubscriptaAsperoMayuscula: return cAlfaSuaveCircunflejoMayuscula;
		case uLetras.cAlfaSubscriptaSuaveMayuscula: return cAlfaSubscriptaSuaveMayuscula;
		
		case uLetras.cAlfaSubscriptaAsperoAgudoMayuscula: return cAlfaSubscriptaAsperoAgudoMayuscula;
		case uLetras.cAlfaSubscriptaSuaveAgudoMayuscula: return cAlfaSubscriptaSuaveAgudoMayuscula;
		case uLetras.cAlfaSubscriptaAsperoCircunflejoMayuscula: return cAlfaSubscriptaAsperoCircunflejoMayuscula;
		case uLetras.cAlfaSubscriptaSuaveCircunflejoMayuscula: return cAlfaSubscriptaSuaveCircunflejoMayuscula;
		
		
		
		case uLetras.cOmegaSubscriptaAsperoMayuscula: return cOmegaSubscriptaAsperoMayuscula;
		case uLetras.cOmegaSubscriptaSuaveMayuscula: return cOmegaSubscriptaSuaveMayuscula;
		case uLetras.cOmegaAsperoMayuscula: return cOmegaAsperoMayuscula;
		case uLetras.cOmegaSuaveMayuscula: return cOmegaSuaveMayuscula;
		
		case uLetras.cOmegaAsperoAgudoMayuscula: return cOmegaAsperoAgudoMayuscula;
		case uLetras.cOmegaSuaveAgudoMayuscula: return cOmegaSuaveAgudoMayuscula;
		
		case uLetras.cOmegaAsperoCircunflejoMayuscula: return cOmegaAsperoCircunflejoMayuscula;
		case uLetras.cOmegaSuaveCircunflejoMayuscula: return cOmegaSuaveCircunflejoMayuscula;
		
		case uLetras.cOmegaSubscriptaAsperoAgudoMayuscula: return cOmegaSubscriptaAsperoAgudoMayuscula;
		case uLetras.cOmegaSubscriptaSuaveAgudoMayuscula: return cOmegaSubscriptaSuaveAgudoMayuscula;
		case uLetras.cOmegaSubscriptaAsperoCircunflejoMayuscula: return cOmegaSubscriptaAsperoCircunflejoMayuscula;
		case uLetras.cOmegaSubscriptaSuaveCircunflejoMayuscula: return cOmegaSubscriptaSuaveCircunflejoMayuscula;
		
		
		case uLetras.cEpsilonAsperoMayuscula: return cEpsilonAsperoMayuscula;
		case uLetras.cEpsilonSuaveMayuscula: return cEpsilonSuaveMayuscula;
		
		case uLetras.cEpsilonAsperoAgudoMayuscula: return cEpsilonAsperoAgudoMayuscula;
		case uLetras.cEpsilonSuaveAgudoMayuscula: return cEpsilonSuaveAgudoMayuscula;
		
		
		
		case uLetras.cOmicronAsperoMayuscula : return cOmicronAsperoMayuscula;
		case uLetras.cOmicronSuaveMayuscula : return cOmicronSuaveMayuscula;
		
		case uLetras.cOmicronAsperoAgudoMayuscula : return cOmicronAsperoAgudoMayuscula;
		case uLetras.cOmicronSuaveAgudoMayuscula : return cOmicronSuaveAgudoMayuscula;
		
		case uLetras.cUpsilonAsperoMayuscula: return cUpsilonAsperoMayuscula;
		case uLetras.cUpsilonAsperoAgudoMayuscula: return cUpsilonAsperoAgudoMayuscula;
		case uLetras.cUpsilonAgudoMayuscula: return cUpsilonAgudoMayuscula;
		
		//caracteres con información de -(makron/vrachy)
		case uLetras.cAlfaVrachy : return cAlfa;
		case uLetras.cAlfaVrachyMayuscula : return cAlfaMayuscula;
		case uLetras.cAlfaMakron: return cAlfa;
		case uLetras.cAlfaMakronMayuscula : return cAlfaMayuscula;
		case uLetras.cIotaVrachy : return cIota;
		case uLetras.cIotaVrachyMayuscula : return cIotaMayuscula;
		case uLetras.cIotaMakron: return cIota;
		case uLetras.cIotaMakronMayuscula : return cIotaMayuscula;
		case uLetras.cUpsilonVrachy : return cUpsilon;
		case uLetras.cUpsilonVrachyMayuscula : return cUpsilonMayuscula;
		case uLetras.cUpsilonMakron: return cUpsilon;
		case uLetras.cUpsilonMakronMayuscula : return cUpsilonMayuscula;
		
		// consonantes
		case uLetras.cBetaMayuscula : return cBetaMayuscula;
		case uLetras.cJiMayuscula : return cJiMayuscula;
		case uLetras.cDeltaMayuscula : return cDeltaMayuscula;
		case uLetras.cFiMayuscula : return cFiMayuscula;
		case uLetras.cGammaMayuscula: return cGammaMayuscula;
		case uLetras.cKappaMayuscula : return cKappaMayuscula;
		case uLetras.cLambdaMayuscula : return cLambdaMayuscula;
		case uLetras.cMuMayuscula : return cMuMayuscula;
		case uLetras.cNuMayuscula : return cNuMayuscula;
		case uLetras.cSigmaMayuscula : return cSigmaMayuscula;
		case uLetras.cTauMayuscula : return cTauMayuscula;
		case uLetras.cPiMayuscula : return cPiMayuscula;
		case uLetras.cThetaMayuscula : return cThetaMayuscula;
		case uLetras.cRhoMayuscula :
		case uLetras.cRhoAsperoMayuscula:
			return cRhoMayuscula;
		case uLetras.cPsiMayuscula : return cPsiMayuscula;
		case uLetras.cDzetaMayuscula : return cDzetaMayuscula;
		case uLetras.cXiMayuscula : return cXiMayuscula;
		
		
		
		//caracteres especiales y acentos
		case uLetras.cEspacio: return cEspacio;
		case uLetras.cBarra: return cBarra;
		case uLetras.cCorcheteApertura: return cCorcheteApertura;
		case uLetras.cCorcheteCierre: return cCorcheteCierre;
		
		//signos especiales
		case uLetras.cPregunta: return cPregunta;
		case uLetras.cAdmiracion: return cAdmiracion;
		case uLetras.cAsterisco: return cAsterisco;
		case uLetras.cMas: return cMas;
		case uLetras.cMenos: return cMenos;
		case uLetras.cAmpersand: return cAmpersand;
		case uLetras.cUnderscore: return cUnderscore;
		
		
		default:
			throw new ExcepcionCaracterNoEncontrado((int)caracter);
		}
	}
	
	
	public static char desespirituarLetra(char ncar) {
		char ret;
		switch (ncar) {
		//alfa
		
		
		
		case cAlfaAsperoCircunflejo:
		case cAlfaSuaveCircunflejo:
			ret = cAlfaCircunflejo;
			break;
			
			
			
		case cAlfaSubscriptaAspero:
		case cAlfaSubscriptaSuave:
			ret = cAlfaSubscripta;
			break;
			
		case cAlfaSubscriptaAsperoMayuscula:
		case cAlfaSubscriptaSuaveMayuscula:
			ret = cAlfaSubscriptaMayuscula;
			break;
			
			
		case cAlfaSubscriptaAsperoAgudo:
		case cAlfaSubscriptaSuaveAgudo:
			ret = cAlfaSubscriptaAgudo;
			break;
			
			
			
			
		case cAlfaSubscriptaAsperoGrave:
		case cAlfaSubscriptaSuaveGrave:
			ret = cAlfaSubscriptaGrave;
			break;
			
			
			
			
			
		case cAlfaSubscriptaAsperoCircunflejo:
		case cAlfaSubscriptaSuaveCircunflejo:
			ret = cAlfaSubscriptaCircunflejo;
			break;
			
			
			
			
			//eta
		case cEtaAspero:
		case cEtaSuave:
			ret = cEta;
			break;
			
		case cEtaAsperoMayuscula:
		case cEtaSuaveMayuscula:
			ret = cEtaMayuscula;
			break;
			
		case cEtaAsperoAgudo:
		case cEtaSuaveAgudo:
			ret = cEtaAgudo;
			break;
			
			
			
		case cEtaAsperoGrave:
		case cEtaSuaveGrave:
			ret = cEtaGrave;
			break;
			
			
		case cEtaAsperoCircunflejo:
		case cEtaSuaveCircunflejo:
			ret = cEtaCircunflejo;
			break;
			
			
			
		case cEtaSubscriptaAspero:
		case cEtaSubscriptaSuave:
			ret = cEtaSubscripta;
			break;
			
		case cEtaSubscriptaAsperoMayuscula:
		case cEtaSubscriptaSuaveMayuscula:
			ret = cEtaSubscriptaMayuscula;
			break;
			
			
		case cEtaSubscriptaAsperoAgudo:
		case cEtaSubscriptaSuaveAgudo:
			ret = cEtaSubscriptaAgudo;
			break;
			
			
			
		case cEtaSubscriptaAsperoGrave:
		case cEtaSubscriptaSuaveGrave:
			ret = cEtaSubscriptaGrave;
			break;
			
			
			
			
		case cEtaSubscriptaAsperoCircunflejo:
		case cEtaSubscriptaSuaveCircunflejo:
			ret = cEtaSubscriptaCircunflejo;
			break;
			
			
			
			
			//omicron
		case cOmicronAspero:
		case cOmicronSuave:
			ret = cOmicron;
			break;
			
		case cOmicronAsperoMayuscula:
		case cOmicronSuaveMayuscula:
			ret = cOmicronMayuscula;
			break;
			
			
			
		case cOmicronAsperoAgudo:
		case cOmicronSuaveAgudo:
			ret = cOmicronAgudo;
			break;
			
			
			
		case cOmicronAsperoGrave:
		case cOmicronSuaveGrave:
			ret = cOmicronGrave;
			break;
			
			
			
			//upsilon
			
		case cUpsilonAsperoCircunflejo:
		case cUpsilonSuaveCircunflejo:
			ret = cUpsilonCircunflejo;
			break;
			
			
			
			
			
			
			//iota
			
			
		case cIotaAsperoCircunflejo:
		case cIotaSuaveCircunflejo:
			ret = cIotaCircunflejo;
			break;
			
			
			//epsilon
		case cEpsilonAspero:
		case cEpsilonSuave:
			ret = cEpsilon;
			break;
			
		case cEpsilonAsperoMayuscula:
		case cEpsilonSuaveMayuscula:
			ret = cEpsilonMayuscula;
			break;
			
			
		case cEpsilonAsperoAgudo:
		case cEpsilonSuaveAgudo:
			ret = cEpsilonAgudo;
			break;
			
			
			
			
		case cEpsilonSuaveGrave:
		case cEpsilonAsperoGrave:
			ret = cEpsilonGrave;
			break;
			
			
			
			//omega
		case cOmegaAspero:
		case cOmegaSuave:
			ret = cOmega;
			break;
			
		case cOmegaAsperoMayuscula:
		case cOmegaSuaveMayuscula:
			ret = cOmegaMayuscula;
			break;
			
			
		case cOmegaAsperoAgudo:
		case cOmegaSuaveAgudo:
			ret = cOmegaAgudo;
			break;
			
			
		case cOmegaAsperoGrave:
		case cOmegaSuaveGrave:
			ret = cOmegaGrave;
			break;
			
			
			
		case cOmegaAsperoCircunflejo:
		case cOmegaSuaveCircunflejo:
			ret = cOmegaCircunflejo;
			break;
			
			
			
		case cOmegaSubscriptaAspero:
		case cOmegaSubscriptaSuave:
			ret = cOmegaSubscripta;
			break;
			
		case cOmegaSubscriptaAsperoMayuscula:
		case cOmegaSubscriptaSuaveMayuscula:
			ret = cOmegaSubscriptaMayuscula;
			break;
			
			
		case cOmegaSubscriptaAsperoAgudo:
		case cOmegaSubscriptaSuaveAgudo:
			ret = cOmegaSubscriptaAgudo;
			break;
			
			
		case cOmegaSubscriptaAsperoGrave:
		case cOmegaSubscriptaSuaveGrave:
			ret = cOmegaSubscriptaGrave;
			break;
			
		case cOmegaSubscriptaAsperoCircunflejo:
		case cOmegaSubscriptaSuaveCircunflejo:
			ret = cOmegaSubscriptaCircunflejo;
			break;
			
		case cRhoAspero:
		case cRhoSuave:
			ret=cRho;
			break;
			
		case cRhoAsperoMayuscula:
			ret = cRhoMayuscula;
			break;
			
			//alfa
		case cAlfaAspero:
		case cAlfaSuave:
			ret = cAlfa;
			break;
			
		case cAlfaAsperoMayuscula:
		case cAlfaSuaveMayuscula:
			ret = cAlfaMayuscula;
			break;
			
		case cAlfaAsperoAgudo:
		case cAlfaSuaveAgudo:
			ret = cAlfaAgudo;
			break;
			
			
			
		case cAlfaAsperoGrave:
		case cAlfaSuaveGrave:
			ret = cAlfaGrave;
			break;
			
			
			
			//upsilon
		case cUpsilonAspero:
		case cUpsilonSuave:
			ret = cUpsilon;
			break;
			
		case cUpsilonAsperoMayuscula:
			ret = cUpsilonMayuscula;
			break;
			
			
		case cUpsilonAsperoAgudo:
		case cUpsilonSuaveAgudo:
			ret = cUpsilonAgudo;
			break;
			
		case cUpsilonAsperoAgudoMayuscula:
			ret = cUpsilonAgudoMayuscula;
			break;
			
			
		case cUpsilonAsperoGrave:
		case cUpsilonSuaveGrave:
			ret = cUpsilonGrave;
			break;
			
			
			
			//iota
		case cIotaAspero:
		case cIotaSuave:
			ret = cIota;
			break;
			
		case cIotaAsperoMayuscula:
		case cIotaSuaveMayuscula:
			ret = cIotaMayuscula;
			break;
			
			
		case cIotaAsperoAgudo:
		case cIotaSuaveAgudo:
			ret = cIota;
			break;
			
		case cIotaAsperoAgudoMayuscula:
		case cIotaSuaveAgudoMayuscula:
			ret = cIotaMayuscula;
			break;
			
		case cIotaAsperoGrave:
		case cIotaSuaveGrave:
			ret = cIotaGrave;
			break;
			
			
			
		default:
			ret = ncar;
		}
		;
		return ret;
	}
	
	public static char espirituSuaveCaracter (char letra){
		switch(letra){
		case cAlfa:
			return cAlfaSuave;
		case cAlfaAgudo:
			return cAlfaSuaveAgudo;
		case cAlfaCircunflejo:
			return cAlfaSuaveCircunflejo;
		case cAlfaSubscripta:
			return cAlfaSubscriptaSuave;
		case cAlfaSubscriptaAgudo:
			return cAlfaSubscriptaSuaveAgudo;
		case cAlfaSubscriptaCircunflejo:
			return cAlfaSubscriptaSuaveCircunflejo;
			
		case cAlfaMayuscula:
			return cAlfaSuaveMayuscula;
		case cAlfaAgudoMayuscula:
			return cAlfaSuaveAgudoMayuscula;
		case cAlfaSubscriptaMayuscula:
			return cAlfaSubscriptaSuaveMayuscula;
			
			
			
			
		case cEta:
			return cEtaSuave;
		case cEtaAgudo:
			return cEtaSuaveAgudo;
		case cEtaCircunflejo:
			return cEtaSuaveCircunflejo;
		case cEtaSubscripta:
			return cEtaSubscriptaSuave;
		case cEtaSubscriptaAgudo:
			return cEtaSubscriptaSuaveAgudo;
		case cEtaSubscriptaCircunflejo:
			return cEtaSubscriptaSuaveCircunflejo;
			
		case cEtaMayuscula:
			return cEtaSuaveMayuscula;
		case cEtaAgudoMayuscula:
			return cEtaSuaveAgudoMayuscula;
		case cEtaSubscriptaMayuscula:
			return cEtaSubscriptaSuaveMayuscula;
		
			
			
		case cOmega:
			return cOmegaSuave;
		case cOmegaAgudo:
			return cOmegaSuaveAgudo;
		case cOmegaCircunflejo:
			return cOmegaSuaveCircunflejo;
		case cOmegaSubscripta:
			return cOmegaSubscriptaSuave;
		case cOmegaSubscriptaAgudo:
			return cOmegaSubscriptaSuaveAgudo;
		case cOmegaSubscriptaCircunflejo:
			return cOmegaSubscriptaSuaveCircunflejo;
			
			
		case cOmegaMayuscula:
			return cOmegaSuaveMayuscula;
		case cOmegaAgudoMayuscula:
			return cOmegaSuaveAgudoMayuscula;
		case cOmegaSubscriptaMayuscula:
			return cOmegaSubscriptaSuaveMayuscula;
		
			
			
			
		case cIota:
			return cIotaSuave;
		case cIotaAgudo:
			return cIotaSuaveAgudo;
		case cIotaCircunflejo:
			return cIotaSuaveCircunflejo;
			
		case cIotaMayuscula:
			return cIotaSuaveMayuscula;
		case cIotaAgudoMayuscula:
			return cIotaSuaveAgudoMayuscula;
			
			
			
		case cEpsilon:
			return cEpsilonSuave;
		case cEpsilonAgudo:
			return cEpsilonSuaveAgudo;
			
		case cEpsilonMayuscula:
			return cEpsilonSuaveMayuscula;
		case cEpsilonAgudoMayuscula:
			return cEpsilonSuaveAgudoMayuscula;
			
			
		case cOmicron:
			return cOmicronSuave;
		case cOmicronAgudo:
			return cOmicronSuaveAgudo;
			
		case cOmicronMayuscula:
			return cOmicronSuaveMayuscula;
		case cOmicronAgudoMayuscula:
			return cOmicronSuaveAgudoMayuscula;
			
			
			
		case cUpsilon:
			return cUpsilonSuave;
		case cUpsilonAgudo:
			return cUpsilonSuaveAgudo;
		case cUpsilonGrave:
			return cUpsilonSuaveGrave;
		case cUpsilonCircunflejo:
			return cUpsilonSuaveCircunflejo;
			
		default:
			return letra;
		}
	}
	
	public static char dieresisLetra(char letra){
		char ret;
		switch(letra){
		case cIota:
			ret = cIotaDieresis ; break;
		case cIotaAgudo:
			ret = cIotaDieresisAgudo ; break;
			
		case cUpsilon:
		    ret= cUpsilonDieresis; break;
		case cUpsilonAgudo:
			ret = cUpsilonDieresisAgudo ; break;
		case cUpsilonMayuscula:
			ret = cUpsilonDieresisMayuscula ; break;
			
			
		default:
			ret=letra;
		}
		return ret;
	}
	
	public static char espirituAsperoCaracter (char letra){
		switch(letra){
		case cAlfa:
			return cAlfaAspero;
		case cAlfaAgudo:
			return cAlfaAsperoAgudo;
		case cAlfaCircunflejo:
			return cAlfaAsperoCircunflejo;
		case cAlfaSubscripta:
			return cAlfaSubscriptaAspero;
		case cAlfaSubscriptaAgudo:
			return cAlfaSubscriptaAsperoAgudo;
		case cAlfaSubscriptaCircunflejo:
			return cAlfaSubscriptaAsperoCircunflejo;
			
		case cAlfaMayuscula:
			return cAlfaAsperoMayuscula;
		case cAlfaAgudoMayuscula:
			return cAlfaAsperoAgudoMayuscula;
		case cAlfaSubscriptaMayuscula:
			return cAlfaSubscriptaAsperoMayuscula;
			
			
			
		case cEta:
			return cEtaAspero;
		case cEtaAgudo:
			return cEtaAsperoAgudo;
		case cEtaCircunflejo:
			return cEtaAsperoCircunflejo;
		case cEtaSubscripta:
			return cEtaSubscriptaAspero;
		case cEtaSubscriptaCircunflejo:
			return cEtaSubscriptaAsperoCircunflejo;
			
			
		case cEtaMayuscula:
			return cEtaAsperoMayuscula;
		case cEtaAgudoMayuscula:
			return cEtaAsperoAgudoMayuscula;
		case cEtaSubscriptaMayuscula:
			return cEtaSubscriptaAsperoMayuscula;
			
			
			
		case cOmega:
			return cOmegaAspero;
		case cOmegaAgudo:
			return cOmegaAsperoAgudo;
		case cOmegaCircunflejo:
			return cOmegaAsperoCircunflejo;
		case cOmegaSubscripta:
			return cOmegaSubscriptaAspero;
		case cOmegaSubscriptaAgudo:
			return cOmegaSubscriptaAsperoAgudo;
		case cOmegaSubscriptaCircunflejo:
			return cOmegaSubscriptaAsperoCircunflejo;
			
			
		case cOmegaMayuscula:
			return cOmegaAsperoMayuscula;
		case cOmegaAgudoMayuscula:
			return cOmegaAsperoAgudoMayuscula;
		case cOmegaSubscriptaMayuscula:
			return cOmegaSubscriptaAsperoMayuscula;
			
			
			
		case cIota:
			return cIotaAspero;
		case cIotaAgudo:
			return cIotaAsperoAgudo;
		case cIotaCircunflejo:
			return cIotaAsperoCircunflejo;
			
		case cIotaMayuscula:
			return cIotaAsperoMayuscula;
		case cIotaAgudoMayuscula:
			return cIotaAsperoAgudoMayuscula;
			
			
			
		case cEpsilon:
			return cEpsilonAspero;
		case cEpsilonAgudo:
			return cEpsilonAsperoAgudo;
			
		case cEpsilonMayuscula:
			return cEpsilonAsperoMayuscula;
		case cEpsilonAgudoMayuscula:
			return cEpsilonAsperoAgudoMayuscula;
			
			
		case cOmicron:
			return cOmicronAspero;
		case cOmicronAgudo:
			return cOmicronAsperoAgudo;
			
			
		case cOmicronMayuscula:
			return cOmicronAsperoMayuscula;
		case cOmicronAgudoMayuscula:
			return cOmicronAsperoAgudoMayuscula;
			
			
		case cUpsilon:
			return cUpsilonAspero;
		case cUpsilonAgudo:
			return cUpsilonAsperoAgudo;
		case cUpsilonGrave:
			return cUpsilonAsperoGrave;
		case cUpsilonCircunflejo:
			return cUpsilonAsperoCircunflejo;
			
		case cUpsilonMayuscula:
			return cUpsilonAsperoMayuscula;
		case cUpsilonAgudoMayuscula:
			return cUpsilonAsperoAgudoMayuscula;
		case cUpsilonGraveMayuscula:
			return cUpsilonAsperoGraveMayuscula;
			
			
			
		default:
			return letra;
		}
	}
	
	
	public static char subscribeLetra(char letra){
		switch(letra){
		//alfa
		case cAlfa: 
			return cAlfaSubscripta;
		case cAlfaAgudo:  
			return cAlfaSubscriptaAgudo;
		case cAlfaCircunflejo:
			return cAlfaSubscriptaCircunflejo;
		case cAlfaSuave: 
			return cAlfaSubscriptaSuave;
		case cAlfaAspero: 
			return cAlfaSubscriptaAspero;
		case cAlfaSuaveAgudo: 
			return cAlfaSubscriptaSuaveAgudo;
		case cAlfaAsperoAgudo: 
			return cAlfaSubscriptaAsperoAgudo;
		case cAlfaSuaveCircunflejo:
			return cAlfaSubscriptaSuaveCircunflejo;
		case cAlfaAsperoCircunflejo:
			return cAlfaSubscriptaAsperoCircunflejo;
			
		case cAlfaMayuscula: 
			return cAlfaSubscriptaMayuscula;
		case cAlfaSuaveMayuscula: 
			return cAlfaSubscriptaSuaveMayuscula;
		case cAlfaAsperoMayuscula: 
			return cAlfaSubscriptaAsperoMayuscula;
		case cAlfaSuaveAgudoMayuscula: 
			return cAlfaSubscriptaSuaveAgudoMayuscula;
		case cAlfaAsperoAgudoMayuscula: 
			return cAlfaSubscriptaAsperoAgudoMayuscula;
		case cAlfaSuaveCircunflejoMayuscula:
			return cAlfaSubscriptaSuaveCircunflejoMayuscula;
		case cAlfaAsperoCircunflejoMayuscula:
			return cAlfaSubscriptaAsperoCircunflejoMayuscula;
			
			
			
			//eta
		case cEta:
			return cEtaSubscripta;
		case cEtaAgudo:
			return cEtaSubscriptaAgudo;
		case cEtaCircunflejo:
			return cEtaSubscriptaCircunflejo;
		case cEtaSuave:
			return cEtaSubscriptaSuave;
		case cEtaAspero:
			return cEtaSubscriptaAspero;
		case cEtaSuaveAgudo:
			return cEtaSubscriptaSuaveAgudo;
		case cEtaAsperoAgudo:
			return cEtaSubscriptaAsperoAgudo;
		case cEtaSuaveCircunflejo:
			return cEtaSubscriptaSuaveCircunflejo;
		case cEtaAsperoCircunflejo:
			return cEtaSubscriptaAsperoCircunflejo;
			
			
		case cEtaMayuscula:
			return cEtaSubscriptaMayuscula;
		case cEtaSuaveMayuscula:
			return cEtaSubscriptaSuaveMayuscula;
		case cEtaAsperoMayuscula:
			return cEtaSubscriptaAsperoMayuscula;
		case cEtaSuaveAgudoMayuscula:
			return cEtaSubscriptaSuaveAgudoMayuscula;
		case cEtaAsperoAgudoMayuscula:
			return cEtaSubscriptaAsperoAgudoMayuscula;
		case cEtaSuaveCircunflejoMayuscula:
			return cEtaSubscriptaSuaveCircunflejoMayuscula;
		case cEtaAsperoCircunflejoMayuscula:
			return cEtaSubscriptaAsperoCircunflejoMayuscula;
			
			
			
			//omega
		case cOmega:
			return cOmegaSubscripta;
		case cOmegaAgudo:
			return cOmegaSubscriptaAgudo;
		case cOmegaCircunflejo:
			return cOmegaSubscriptaCircunflejo;
		case cOmegaSuave:
			return cOmegaSubscriptaSuave;
		case cOmegaAspero:
			return cOmegaSubscriptaAspero;
		case cOmegaSuaveAgudo:
			return cOmegaSubscriptaSuaveAgudo;
		case cOmegaAsperoAgudo:
			return cOmegaSubscriptaAsperoAgudo;
		case cOmegaSuaveCircunflejo:
			return cOmegaSubscriptaSuaveCircunflejo;
		case cOmegaAsperoCircunflejo:
			return cOmegaSubscriptaAsperoCircunflejo;
			
		case cOmegaMayuscula:
			return cOmegaSubscriptaMayuscula;
		case cOmegaSuaveMayuscula:
			return cOmegaSubscriptaSuaveMayuscula;
		case cOmegaAsperoMayuscula:
			return cOmegaSubscriptaAsperoMayuscula;
		case cOmegaSuaveAgudoMayuscula:
			return cOmegaSubscriptaSuaveAgudoMayuscula;
		case cOmegaAsperoAgudoMayuscula:
			return cOmegaSubscriptaAsperoAgudoMayuscula;
		case cOmegaSuaveCircunflejoMayuscula:
			return cOmegaSubscriptaSuaveCircunflejoMayuscula;
		case cOmegaAsperoCircunflejoMayuscula:
			return cOmegaSubscriptaAsperoCircunflejoMayuscula;
			
			
			
		default:
			
			return letra;
		}
		
	}
	static public void acentoAgudoCaracter(StringBuffer sb, int posicion){
		sb.setCharAt(posicion, acentoAgudoLetra(sb.charAt(posicion)));
	}
	static public void acentoCircunflejoCaracter(StringBuffer sb, int posicion){
		sb.setCharAt(posicion, acentoCircunflejoLetra(sb.charAt(posicion)));
	}
	
	
	

	
	

	
	/**
	 * determina si un carácter unicode es compuesto (superescrito, iota)
	 * @param carUnicode
	 * @return
	 */
	public static boolean esCompuesto(char carUnicode){
		switch(carUnicode){
		  case uLetras.cAgudo:
		  case uLetras.cCircunflejo:
		  case uLetras.cLarga:
		  case uLetras.cBreve:
		  case uLetras.cGrave:
		  case uLetras.cDieresis:
		  case uLetras.cAspero:
		  case uLetras.cSuave:
		  case uLetras.cIotaSubscripta:
		  	return true;
		  default:
		  	return false;
		}
	}
	
	
	/**
	 * compone un carácter unicode normal con la información de composición, 
	 * devolviendo un carácter unicode compuesto (por ejemplo, alfa con acebto agudo)
	 * Si el segundo carácter no es componedor, devuelve la primera letra original
	 * (de esta manera, esta función se puede usar en loops hasta obtener el carácter 
	 * compuesto).
	 * 
	 * @param normal
	 * @param compuesto
	 * @return
	 */
	public static char compone(char normal, char compuesto) throws ExcepcionCaracterNoEncontrado{
		if (compuesto==uLetras.cAgudo){
			return acentoAgudoLetra(OpLetras.carUnicodeACompleto(normal));
		}else if (compuesto==uLetras.cCircunflejo){
			return acentoCircunflejoLetra(OpLetras.carUnicodeACompleto(normal));
		}else if (compuesto==uLetras.cLarga){
			CaracterGriego aux=CaracterGriegoFactory.produceCaracterGriego(OpLetras.carUnicodeACompleto(normal));
			return aux.getVersionLarga();
		}else if (compuesto==uLetras.cBreve){
			CaracterGriego aux=CaracterGriegoFactory.produceCaracterGriego(OpLetras.carUnicodeACompleto(normal));
			return aux.getVersionCorta();
		}else if (compuesto==uLetras.cGrave){
			return acentoGraveLetra(OpLetras.carUnicodeACompleto(normal));
		}else if (compuesto==uLetras.cDieresis){
			return dieresisLetra(OpLetras.carUnicodeACompleto(normal));
		}else if (compuesto==uLetras.cAspero){
			return espirituAsperoCaracter(OpLetras.carUnicodeACompleto(normal));
		}else if (compuesto==uLetras.cSuave){
			return espirituSuaveCaracter(OpLetras.carUnicodeACompleto(normal));
		}else if (compuesto==uLetras.cIotaSubscripta){
			return subscribeLetra(OpLetras.carUnicodeACompleto(normal));
		}else{
			return OpLetras.carUnicodeACompleto(normal);
		}
	}
	
	/**
	 * informa si un carácter unicode tiene información de -.
	 * Se usa para recorrer palabras enteras y determinar si información de
	 * - ya está presente en la palabra 
	 * @param caracter
	 * @return
	 */
	public static boolean tieneInformacion(char caracter){
		switch(caracter){
		    //letras
			case uLetras.cAlfaVrachy:
			case uLetras.cAlfaMakron:
			case uLetras.cAlfaVrachyMayuscula:
			case uLetras.cAlfaMakronMayuscula:
			case uLetras.cIotaVrachy:
			case uLetras.cIotaMakron:
			case uLetras.cIotaVrachyMayuscula:
			case uLetras.cIotaMakronMayuscula:
			case uLetras.cUpsilonVrachy:
			case uLetras.cUpsilonMakron:
			case uLetras.cUpsilonVrachyMayuscula:
			case uLetras.cUpsilonMakronMayuscula:
			//de composición
			case uLetras.cLarga:
			case uLetras.cBreve:
				return true;
			default:
				return false;
		}
	}
	
	
	
	
	public static int getTipoLetraBasico(char car){
		char letra = OpLetrasUnicode.desespirituarLetra(OpLetrasUnicode.desacentuarLetra(car));
		switch(letra){
		case cAlfaLarga: case cAlfaCorta: case cAlfaLargaMayuscula: case cAlfaCortaMayuscula: case cAlfa: case cAlfaMayuscula:
		case cAlfaSubscripta: case cAlfaSubscriptaMayuscula:
		case cEpsilon: case cEpsilonMayuscula:
		case cIotaLarga: case cIotaCorta: case cIotaLargaMayuscula: case cIotaCortaMayuscula: case cIota: case cIotaMayuscula:
		case cOmicron: case cOmicronMayuscula:
		case cOmega: case cOmegaSubscripta: case cOmegaSubscriptaMayuscula:  case cOmegaMayuscula:
		case cEta: case cEtaSubscripta: case cEtaMayuscula: case cEtaSubscriptaMayuscula:
		case cUpsilonLarga: case cUpsilonCorta: case cUpsilonLargaMayuscula: case cUpsilonCortaMayuscula: case cUpsilon: case cUpsilonMayuscula:
		case cUpsilonDieresis: case cIotaDieresis:
			return TipoLetraBasico.enuVocal;
		case cPsi: case cPsiMayuscula:
		case cDzeta: case cDzetaMayuscula:
		case cXi: case cXiMayuscula:
		case cBeta: case cBetaMayuscula:
		case cPi: case cPiMayuscula:
		case cFi: case cFiMayuscula:
		case cJi: case cJiMayuscula:
		case cKappa: case cKappaMayuscula:
		case cGamma: case cGammaMayuscula:
		case cTau: case cTauMayuscula:
		case cTheta: case cThetaMayuscula:
		case cDelta: case cDeltaMayuscula:
		case cLambda: case cLambdaMayuscula:
		case cRho: case cRhoMayuscula:
		case cMu: case cMuMayuscula:
		case cNu: case cNuMayuscula:
		case cSigma: case cSigmaMayuscula:
		case cSigmaFinal:
			return TipoLetraBasico.enuConsonante;
		case cAdmiracion:
		case cPregunta:
		case cMenos:
		case cMas:
		case cAsterisco:
		case cUnderscore:
		case cEspacio:
		case cAmpersand:
		case cIgual:
		case cPunto:
		case cApostrofe:	
		case cNuevaLinea:
		case cComa:
			return TipoLetraBasico.enuSignoEspecial;
		case uLetras.cAgudo:
		case uLetras.cCircunflejo:
		case uLetras.cLarga:
		case uLetras.cBreve:
		case uLetras.cGrave:
		case uLetras.cDieresis:
		case uLetras.cAspero:
		case uLetras.cSuave:
		case uLetras.cIotaSubscripta:
			return TipoLetraBasico.enuComposicion;
		default:
			throw new RuntimeException("Función TipoLetraBasico: problemas para determinar qué tipo de letra es " + letra + " hex=" + Integer.toHexString(letra) );
		}
		
	}

	
}