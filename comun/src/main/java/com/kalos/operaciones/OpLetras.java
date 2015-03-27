package com.kalos.operaciones;

import static com.kalos.enumeraciones.CompLetras.*;
import com.kalos.enumeraciones.uLetras;
import com.kalos.fonts.CaracterGriego;
import com.kalos.fonts.CaracterGriegoFactory;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class OpLetras {
	
	public static char desacentuarLetra(char ncar) {
		char ret;
		switch (ncar) {
		//alfa
		case cAlfaCircunflejo:
			ret = cAlfaLarga;
			break;
		case cAlfaAsperoCircunflejo:
			ret = cAlfaAsperoLarga;
			break;
		case cAlfaSuaveCircunflejo:
			ret = cAlfaSuaveLarga;
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
			
			
		case cAlfaCircunflejoMayuscula:
			ret = cAlfaLargaMayuscula;
			break;
		case cAlfaAsperoCircunflejoMayuscula:
			ret = cAlfaAsperoLargaMayuscula;
			break;
		case cAlfaSuaveCircunflejoMayuscula:
			ret = cAlfaSuaveLargaMayuscula;
			break;
		case cAlfaSubscriptaAgudoMayuscula:
		case cAlfaSubscriptaGraveMayuscula:
		case cAlfaSubscriptaCircunflejoMayuscula:
			ret = cAlfaSubscriptaMayuscula;
			break;
		case cAlfaSubscriptaAsperoAgudoMayuscula:
		case cAlfaSubscriptaAsperoGraveMayuscula:
		case cAlfaSubscriptaAsperoCircunflejoMayuscula:
			ret = cAlfaSubscriptaAsperoMayuscula;
			break;
		case cAlfaSubscriptaSuaveAgudoMayuscula:
		case cAlfaSubscriptaSuaveGraveMayuscula:
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
			
			
		case cOmegaAgudoMayuscula:
		case cOmegaGraveMayuscula:
		case cOmegaCircunflejoMayuscula:
			ret = cOmegaMayuscula;
			break;
		case cOmegaAsperoAgudoMayuscula:
		case cOmegaAsperoGraveMayuscula:
		case cOmegaAsperoCircunflejoMayuscula:
			ret = cOmegaAsperoMayuscula;
			break;
		case cOmegaSuaveAgudoMayuscula:
		case cOmegaSuaveGraveMayuscula:
		case cOmegaSuaveCircunflejoMayuscula:
			ret = cOmegaSuaveMayuscula;
			break;
		case cOmegaSubscriptaAgudoMayuscula:
		case cOmegaSubscriptaGraveMayuscula:
		case cOmegaSubscriptaCircunflejoMayuscula:
			ret = cOmegaSubscriptaMayuscula;
			break;
		case cOmegaSubscriptaAsperoAgudoMayuscula:
		case cOmegaSubscriptaAsperoGraveMayuscula:
		case cOmegaSubscriptaAsperoCircunflejoMayuscula:
			ret = cOmegaSubscriptaMayuscula;
			break;
		case cOmegaSubscriptaSuaveAgudoMayuscula:
		case cOmegaSubscriptaSuaveGraveMayuscula:
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
			
		case cEtaAgudoMayuscula:
		case cEtaGraveMayuscula:
		case cEtaCircunflejoMayuscula:
			ret = cEtaMayuscula;
			break;
		case cEtaAsperoAgudoMayuscula:
		case cEtaAsperoGraveMayuscula:
		case cEtaAsperoCircunflejoMayuscula:
			ret = cEtaAsperoMayuscula;
			break;
		case cEtaSuaveAgudoMayuscula:
		case cEtaSuaveGraveMayuscula:
		case cEtaSuaveCircunflejoMayuscula:
			ret = cEtaSuaveMayuscula;
			break;
		case cEtaSubscriptaAgudoMayuscula:
		case cEtaSubscriptaGraveMayuscula:
		case cEtaSubscriptaCircunflejoMayuscula:
			ret = cEtaSubscriptaMayuscula;
			break;
		case cEtaSubscriptaAsperoAgudoMayuscula:
		case cEtaSubscriptaAsperoGraveMayuscula:
		case cEtaSubscriptaAsperoCircunflejoMayuscula:
			ret = cEtaSubscriptaAsperoMayuscula;
			break;
		case cEtaSubscriptaSuaveAgudoMayuscula:
		case cEtaSubscriptaSuaveGraveMayuscula:
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
			
		case cEpsilonAgudoMayuscula:
		case cEpsilonGraveMayuscula:
			ret = cEpsilonMayuscula;
			break;
		case cEpsilonAsperoGraveMayuscula:
		case cEpsilonAsperoAgudoMayuscula:
			ret = cEpsilonAsperoMayuscula;
			break;
		case cEpsilonSuaveAgudoMayuscula:
		case cEpsilonSuaveGraveMayuscula:
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
			
		case cOmicronAgudoMayuscula:
		case cOmicronGraveMayuscula:
		case cOmicronAsperoMayuscula:
			ret = cOmicronMayuscula;
			break;
		case cOmicronAsperoAgudoMayuscula:
		case cOmicronAsperoGraveMayuscula:
			ret = cOmicronAsperoMayuscula;
			break;
		case cOmicronSuaveAgudoMayuscula:
		case cOmicronSuaveGraveMayuscula:
			ret = cOmicronSuaveMayuscula;
			break;
			
			
			
			
			//iota
		case cIotaCircunflejo:
			ret = cIotaLarga;
			break;
		case cIotaAsperoCircunflejo:
			ret = cIotaAsperoLarga;
			break;
		case cIotaSuaveCircunflejo:
			ret = cIotaSuaveLarga;
			break;
			
			
		case cIotaCircunflejoMayuscula:
			ret = cIotaLargaMayuscula;
			break;
		case cIotaAsperoCircunflejoMayuscula:
			ret = cIotaAsperoLargaMayuscula;
			break;
		case cIotaSuaveCircunflejoMayuscula:
			ret = cIotaSuaveLargaMayuscula;
			break;
			
			
			
			//upsilon
		case cUpsilonCircunflejo:
			ret = cUpsilonLarga;
			break;
		case cUpsilonAsperoCircunflejo:
			ret = cUpsilonAsperoLarga;
			break;
		case cUpsilonSuaveCircunflejo:
			ret = cUpsilonSuaveLarga;
			break;
			
			
		case cUpsilonCircunflejoMayuscula:
			ret = cUpsilonLargaMayuscula;
			break;
		case cUpsilonAsperoCircunflejoMayuscula:
			ret = cUpsilonAsperoLargaMayuscula;
			break;
			
			
			
			//largas-cortas ******************
			//alfa
		case cAlfaAgudoCorta:
		case cAlfaGraveCorta:
			ret = cAlfaCorta;
			break;
		case cAlfaAsperoAgudoCorta:
		case cAlfaAsperoGraveCorta:
			ret = cAlfaAsperoCorta;
			break;
		case cAlfaSuaveAgudoCorta:
		case cAlfaSuaveGraveCorta:
			ret = cAlfaSuaveCorta;
			break;
		case cAlfaAgudoLarga:
		case cAlfaGraveLarga:
			ret = cAlfaLarga;
			break;
		case cAlfaAsperoAgudoLarga:
		case cAlfaAsperoGraveLarga:
			ret = cAlfaAsperoLarga;
			break;
		case cAlfaSuaveAgudoLarga:
		case cAlfaSuaveGraveLarga:
			ret = cAlfaSuaveLarga;
			break;
			
			
		case cAlfaAsperoAgudoCortaMayuscula:
		case cAlfaAsperoGraveCortaMayuscula:
			ret = cAlfaAsperoCortaMayuscula;
			break;
		case cAlfaSuaveAgudoCortaMayuscula:
		case cAlfaSuaveGraveCortaMayuscula:
			ret = cAlfaSuaveCortaMayuscula;
			break;
		case cAlfaAgudoLargaMayuscula:
		case cAlfaGraveLargamayuscula:
			ret = cAlfaLargaMayuscula;
			break;
		case cAlfaAsperoAgudoLargaMayuscula:
		case cAlfaAsperoGraveLargaMayuscula:
			ret = cAlfaAsperoLargaMayuscula;
			break;
		case cAlfaSuaveAgudoLargaMayuscula:
		case cAlfaSuaveGraveLargaMayuscula:
			ret = cAlfaSuaveLargaMayuscula;
			break;
			
			
			
			//iota
		case cIotaAgudoCorta:
		case cIotaGraveCorta:
			ret = cIotaCorta;
			break;
		case cIotaAsperoAgudoCorta:
		case cIotaAsperoGraveCorta:
			ret = cIotaAsperoCorta;
			break;
		case cIotaSuaveAgudoCorta:
		case cIotaSuaveGraveCorta:
			ret = cIotaSuaveCorta;
			break;
		case cIotaDieresisAgudoCorta:
			ret = cIotaDieresisCorta;
			break;
		case cIotaAgudoLarga:
		case cIotaGraveLarga:
			ret = cIotaLarga;
			break;
		case cIotaAsperoAgudoLarga:
		case cIotaAsperoGraveLarga:
			ret = cIotaAsperoLarga;
			break;
		case cIotaSuaveAgudoLarga:
		case cIotaSuaveGraveLarga:
			ret = cIotaSuaveLarga;
			break;
		case cIotaDieresisAgudoLarga:
			ret = cIotaDieresisLarga;
			break;
			
		case cIotaAgudoCortaMayuscula:
		case cIotaGraveCortaMayuscula:
			ret = cIotaCortaMayuscula;
			break;
		case cIotaAsperoAgudoCortaMayuscula:
		case cIotaAsperoGraveCortaMayuscula:
			ret = cIotaAsperoCortaMayuscula;
			break;
		case cIotaSuaveAgudoCortaMayuscula:
		case cIotaSuaveGraveCortaMayuscula:
			ret = cIotaSuaveCortaMayuscula;
			break;
		case cIotaDieresisAgudoCortaMayuscula:
			ret = cIotaDieresisCortaMayuscula;
			break;
		case cIotaAgudoLargaMayuscula:
		case cIotaGraveLargaMayuscula:
			ret = cIotaLargaMayuscula;
			break;
		case cIotaAsperoAgudoLargaMayuscula:
		case cIotaAsperoGraveLargaMayuscula:
			ret = cIotaAsperoLargaMayuscula;
			break;
		case cIotaSuaveAgudoLargaMayuscula:
		case cIotaSuaveGraveLargaMayuscula:
			ret = cIotaSuaveLargaMayuscula;
			break;
		case cIotaDieresisAgudoLargaMayuscula:
			ret = cIotaDieresisLargaMayuscula;
			break;
			
			
			
			//upsilon
		case cUpsilonAgudoCorta:
		case cUpsilonGraveCorta:
			ret = cUpsilonCorta;
			break;
		case cUpsilonAsperoAgudoCorta:
		case cUpsilonAsperoGraveCorta:
			ret = cUpsilonAsperoCorta;
			break;
		case cUpsilonSuaveAgudoCorta:
		case cUpsilonSuaveGraveCorta:
			ret = cUpsilonSuaveCorta;
			break;
			
			
			
		case cUpsilonDieresisAgudoCorta:
		case cUpsilonDieresisGraveCorta:
			ret = cUpsilonDieresisCorta;
			break;
		case cUpsilonAgudoLarga:
		case cUpsilonGraveLarga:
			ret = cUpsilonLarga;
			break;
		case cUpsilonAsperoAgudoLarga:
		case cUpsilonAsperoGraveLarga:
			ret = cUpsilonAsperoLarga;
			break;
		case cUpsilonSuaveAgudoLarga:
		case cUpsilonSuaveGraveLarga:
			ret = cUpsilonSuaveLarga;
			break;
		case cUpsilonDieresisAgudoLarga:
		case cUpsilonDieresisGraveLarga:
			ret = cUpsilonDieresisLarga;
			break;
			
		case cUpsilonAgudoCortaMayuscula:
		case cUpsilonGraveCortaMayuscula:
			ret = cUpsilonCortaMayuscula;
			break;
		case cUpsilonAsperoAgudoCortaMayuscula:
		case cUpsilonAsperoGraveCortaMayuscula:
			ret = cUpsilonAsperoCortaMayuscula;
			break;
		case cUpsilonDieresisAgudoCortaMayuscula:
		case cUpsilonDieresisGraveCortaMayuscula:
			ret = cUpsilonDieresisCortaMayuscula;
			break;
		case cUpsilonAgudoLargaMayuscula:
		case cUpsilonGraveLargaMayuscula:
			ret = cUpsilonLargaMayuscula;
			break;
		case cUpsilonAsperoAgudoLargaMayuscula:
		case cUpsilonAsperoGraveLargaMayuscula:
			ret = cUpsilonAsperoLargaMayuscula;
			break;
		case cUpsilonDieresisAgudoLargaMayuscula:
		case cUpsilonDieresisGraveLargaMayuscula:
			ret = cUpsilonDieresisLargaMayuscula;
			break;
			
			
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
		
		case cAlfaLarga:
			ret =   cAlfaCircunflejo; break;
		case cAlfaAsperoLarga:
			ret =  cAlfaAsperoCircunflejo; break;
		case cAlfaSuaveLarga:
			ret =  cAlfaSuaveCircunflejo; break;
		case cAlfaSubscripta:
			ret =  cAlfaSubscriptaCircunflejo; break;
		case cAlfaSubscriptaAspero:
			ret =  cAlfaSubscriptaAsperoCircunflejo; break;
		case cAlfaSubscriptaSuave:
			ret =  cAlfaSubscriptaSuaveCircunflejo; break;
			
		case cAlfaLargaMayuscula:
			ret =   cAlfaCircunflejoMayuscula; break;
		case cAlfaAsperoLargaMayuscula:
			ret =  cAlfaAsperoCircunflejoMayuscula; break;
		case cAlfaSuaveLargaMayuscula:
			ret =  cAlfaSuaveCircunflejoMayuscula; break;
		case cAlfaSubscriptaMayuscula:
			ret =  cAlfaSubscriptaCircunflejoMayuscula; break;
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
			
			
		case cOmegaMayuscula:
			ret =  cOmegaCircunflejoMayuscula; break;
		case cOmegaAsperoMayuscula:
			ret =  cOmegaAsperoCircunflejoMayuscula; break;
		case cOmegaSuaveMayuscula:
			ret =  cOmegaSuaveCircunflejoMayuscula; break;
		case cOmegaSubscriptaMayuscula:
			ret =  cOmegaSubscriptaCircunflejoMayuscula; break;
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
			ret =  cEtaCircunflejoMayuscula; break;
		case cEtaAsperoMayuscula:
			ret =  cEtaAsperoCircunflejoMayuscula; break;
		case cEtaSuaveMayuscula:
			ret =  cEtaSuaveCircunflejoMayuscula; break;
		case  cEtaSubscriptaMayuscula:
			ret =  cEtaSubscriptaCircunflejoMayuscula; break;
		case cEtaSubscriptaAsperoMayuscula:
			ret =  cEtaSubscriptaAsperoCircunflejoMayuscula; break;
		case cEtaSubscriptaSuaveMayuscula:
			ret =  cEtaSubscriptaSuaveCircunflejoMayuscula; break;
			
			
			//iota
		case cIotaLarga:
			ret =  cIotaCircunflejo; break;
		case cIotaAsperoLarga:
			ret =  cIotaAsperoCircunflejo; break;
		case cIotaSuaveLarga:
			ret =  cIotaSuaveCircunflejo; break;
			
		case cIotaLargaMayuscula:
			ret =  cIotaCircunflejoMayuscula; break;
		case cIotaAsperoLargaMayuscula:
			ret =  cIotaAsperoCircunflejoMayuscula; break;
		case cIotaSuaveLargaMayuscula:
			ret =  cIotaSuaveCircunflejoMayuscula; break;
			
			
			//upsilon
		case cUpsilonLarga:
			ret =  cUpsilonCircunflejo; break;
		case cUpsilonAsperoLarga:
			ret =  cUpsilonAsperoCircunflejo; break;
		case cUpsilonSuaveLarga:
			ret =  cUpsilonSuaveCircunflejo; break;
			
			
		case cUpsilonLargaMayuscula:
			ret =  cUpsilonCircunflejoMayuscula; break;
		case cUpsilonAsperoLargaMayuscula:
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
			ret=  cAlfaSubscriptaAgudoMayuscula; break;
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
			ret = cEtaAgudoMayuscula;
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
			
			
			
			//largas-cortas ***********************
			//alfa
		case cAlfaCorta:
			ret= cAlfaAgudoCorta; break;
		case cAlfaAsperoCorta:
			ret= cAlfaAsperoAgudoCorta; break;
		case cAlfaSuaveCorta:
			ret=  cAlfaSuaveAgudoCorta; break;
			
		case cAlfaAsperoCortaMayuscula:
			ret = cAlfaAsperoAgudoCortaMayuscula;
			break;
		case cAlfaSuaveCortaMayuscula:
			ret = cAlfaSuaveAgudoCortaMayuscula;
			break;
			
			
			//iota
		case cIotaCorta:
			ret= cIotaAgudoCorta; break;
		case cIotaAsperoCorta:
			ret= cIotaAsperoAgudoCorta; break;
		case cIotaSuaveCorta:
			ret= cIotaSuaveAgudoCorta;  break;
		case cIotaDieresisCorta:
			ret= cIotaDieresisAgudoCorta; break;
			
		case cIotaAsperoCortaMayuscula:
			ret = cIotaAsperoAgudoCortaMayuscula;
			break;
		case cIotaSuaveCortaMayuscula:
			ret = cIotaSuaveAgudoCortaMayuscula;
			break;
			
			
			
			//épsilon
		case cUpsilonCorta:
			ret= cUpsilonAgudoCorta; break;
		case cUpsilonAsperoCorta:
			ret= cUpsilonAsperoAgudoCorta; break;
		case cUpsilonSuaveCorta:
			ret= cUpsilonSuaveAgudoCorta;  break;
		case cUpsilonDieresisCorta:
			ret= cUpsilonDieresisAgudoCorta; break;
		case cUpsilonAsperoCortaMayuscula:
			ret= cUpsilonAsperoAgudoCortaMayuscula; break;
			
			
			//alfa
		case cAlfaLarga:
			ret= cAlfaAgudoLarga; break;
		case cAlfaAsperoLarga:
			ret= cAlfaAsperoAgudoLarga; break;
		case cAlfaSuaveLarga:
			ret=  cAlfaSuaveAgudoLarga; break;
			
		case cAlfaAsperoLargaMayuscula:
			ret= cAlfaAsperoAgudoLargaMayuscula; break;
		case cAlfaSuaveLargaMayuscula:
			ret=  cAlfaSuaveAgudoLargaMayuscula; break;
			
			
			//iota
		case cIotaLarga:
			ret= cIotaAgudoLarga; break;
		case cIotaAsperoLarga:
			ret= cIotaAsperoAgudoLarga; break;
		case cIotaSuaveLarga:
			ret= cIotaSuaveAgudoLarga;  break;
		case cIotaDieresisLarga:
			ret= cIotaDieresisAgudoLarga; break;
			
		case cIotaAsperoLargaMayuscula:
			ret= cIotaAsperoAgudoLargaMayuscula; break;
		case cIotaSuaveLargaMayuscula:
			ret= cIotaSuaveAgudoLargaMayuscula;  break;
			
			
			//épsilon
		case cUpsilonLarga:
			ret= cUpsilonAgudoLarga; break;
		case cUpsilonAsperoLarga:
			ret= cUpsilonAsperoAgudoLarga; break;
		case cUpsilonSuaveLarga:
			ret= cUpsilonSuaveAgudoLarga;  break;
		case cUpsilonDieresisLarga:
			ret= cUpsilonDieresisAgudoLarga; break;
		case cUpsilonAsperoLargaMayuscula:
			ret = cUpsilonAsperoAgudoLargaMayuscula;
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
			
		case cAlfaSubscriptaMayuscula:
			ret=  cAlfaSubscriptaGraveMayuscula; break;
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
			ret = cEtaGraveMayuscula;
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
			
			
			
			//largas-cortas ***********************
			//alfa
		case cAlfaCorta:
			ret= cAlfaGraveCorta; break;
		case cAlfaAsperoCorta:
			ret= cAlfaAsperoGraveCorta; break;
		case cAlfaSuaveCorta:
			ret=  cAlfaSuaveGraveCorta; break;
			
		case cAlfaAsperoCortaMayuscula:
			ret = cAlfaAsperoGraveCortaMayuscula;
			break;
		case cAlfaSuaveCortaMayuscula:
			ret = cAlfaSuaveGraveCortaMayuscula;
			break;
			
			
			//iota
		case cIotaCorta:
			ret= cIotaGraveCorta; break;
		case cIotaAsperoCorta:
			ret= cIotaAsperoGraveCorta; break;
		case cIotaSuaveCorta:
			ret= cIotaSuaveGraveCorta;  break;
		case cIotaDieresisCorta:
			ret= cIotaDieresisGraveCorta; break;
			
		case cIotaAsperoCortaMayuscula:
			ret = cIotaAsperoGraveCortaMayuscula;
			break;
		case cIotaSuaveCortaMayuscula:
			ret = cIotaSuaveGraveCortaMayuscula;
			break;
			
			
			
			//épsilon
		case cUpsilonCorta:
			ret= cUpsilonGraveCorta; break;
		case cUpsilonAsperoCorta:
			ret= cUpsilonAsperoGraveCorta; break;
		case cUpsilonSuaveCorta:
			ret= cUpsilonSuaveGraveCorta;  break;
		case cUpsilonDieresisCorta:
			ret= cUpsilonDieresisGraveCorta; break;
		case cUpsilonAsperoCortaMayuscula:
			ret= cUpsilonAsperoGraveCortaMayuscula; break;
			
			
			//alfa
		case cAlfaLarga:
			ret= cAlfaGraveLarga; break;
		case cAlfaAsperoLarga:
			ret= cAlfaAsperoGraveLarga; break;
		case cAlfaSuaveLarga:
			ret=  cAlfaSuaveGraveLarga; break;
			
		case cAlfaAsperoLargaMayuscula:
			ret= cAlfaAsperoGraveLargaMayuscula; break;
		case cAlfaSuaveLargaMayuscula:
			ret=  cAlfaSuaveGraveLargaMayuscula; break;
			
			
			//iota
		case cIotaLarga:
			ret= cIotaGraveLarga; break;
		case cIotaAsperoLarga:
			ret= cIotaAsperoGraveLarga; break;
		case cIotaSuaveLarga:
			ret= cIotaSuaveGraveLarga;  break;
		case cIotaDieresisLarga:
			ret= cIotaDieresisGraveLarga; break;
			
		case cIotaAsperoLargaMayuscula:
			ret= cIotaAsperoGraveLargaMayuscula; break;
		case cIotaSuaveLargaMayuscula:
			ret= cIotaSuaveGraveLargaMayuscula;  break;
			
			
			//épsilon
		case cUpsilonLarga:
			ret= cUpsilonGraveLarga; break;
		case cUpsilonAsperoLarga:
			ret= cUpsilonAsperoGraveLarga; break;
		case cUpsilonSuaveLarga:
			ret= cUpsilonSuaveGraveLarga;  break;
		case cUpsilonDieresisLarga:
			ret= cUpsilonDieresisGraveLarga; break;
		case cUpsilonAsperoLargaMayuscula:
			ret = cUpsilonAsperoGraveLargaMayuscula;
			break;
			
			
			
		default:
			ret= letra;
		}
		return ret;
	}
	
	/**
	 * Pasa un carácter de Unicode a Completo. 
	 * Si el carácter no tiene información de larga-corta, se asume corto.
	 * @param caracter
	 * @return
	 * @throws ExcepcionCaracterNoEncontrado
	 */
	public static char carUnicodeACompleto(char caracter) throws ExcepcionCaracterNoEncontrado{
	  switch(caracter){
		case uLetras.cAlfa: return cAlfaCorta;
		case uLetras.cIota: return cIotaCorta;
		case uLetras.cEta: return cEta;
		case uLetras.cOmicron: return cOmicron;
		case uLetras.cOmega: return cOmega;
		case uLetras.cUpsilon: return cUpsilonCorta;
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
		
		case uLetras.cIotaAspero: return cIotaAsperoCorta;
		case uLetras.cIotaSuave: return cIotaSuaveCorta;
		case uLetras.cIotaAgudo: return cIotaAgudoCorta;
		case uLetras.cIotaAsperoAgudo: return cIotaAsperoAgudoCorta;
		case uLetras.cIotaSuaveAgudo: return cIotaSuaveAgudoCorta;
		case uLetras.cIotaGrave: return cIotaGraveCorta;
		case uLetras.cIotaAsperoGrave: return cIotaAsperoGraveCorta;
		case uLetras.cIotaSuaveGrave: return cIotaSuaveGraveCorta;
		case uLetras.cIotaCircunflejo: return cIotaCircunflejo;
		case uLetras.cIotaAsperoCircunflejo: return cIotaAsperoCircunflejo;
		case uLetras.cIotaSuaveCircunflejo: return cIotaSuaveCircunflejo;
		case uLetras.cIotaDieresis: return cIotaDieresisCorta;
		case uLetras.cIotaDieresisAgudo: return cIotaDieresisAgudoCorta;
		case uLetras.cIotaDieresisGrave: return cIotaDieresisGraveCorta;
		
		
		
		
		case uLetras.cAlfaSubscripta: return cAlfaSubscripta;
		case uLetras.cAlfaAspero: return cAlfaAsperoCorta;
		case uLetras.cAlfaSuave: return cAlfaSuaveCorta;
		case uLetras.cAlfaAgudo: return cAlfaAgudoCorta;
		case uLetras.cAlfaAsperoAgudo: return cAlfaAsperoAgudoCorta;
		case uLetras.cAlfaSuaveAgudo: return cAlfaSuaveAgudoCorta;
		case uLetras.cAlfaGrave: return cAlfaGraveCorta;
		case uLetras.cAlfaAsperoGrave: return cAlfaAsperoGraveCorta;
		case uLetras.cAlfaSuaveGrave: return cAlfaSuaveGraveCorta;
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
		
		
		case uLetras.cUpsilonAspero: return cUpsilonAsperoCorta;
		case uLetras.cUpsilonSuave: return cUpsilonSuaveCorta;
		case uLetras.cUpsilonAgudo: return cUpsilonAgudoCorta;
		case uLetras.cUpsilonAsperoAgudo: return cUpsilonAsperoAgudoCorta;
		case uLetras.cUpsilonSuaveAgudo: return cUpsilonSuaveAgudoCorta;
		case uLetras.cUpsilonGrave: return cUpsilonGraveCorta;
		case uLetras.cUpsilonAsperoGrave: return cUpsilonAsperoGraveCorta;
		case uLetras.cUpsilonSuaveGrave: return cUpsilonSuaveGraveCorta;
		case uLetras.cUpsilonCircunflejo: return cUpsilonCircunflejo;
		case uLetras.cUpsilonAsperoCircunflejo: return cUpsilonAsperoCircunflejo;
		case uLetras.cUpsilonSuaveCircunflejo: return cUpsilonSuaveCircunflejo;
		case uLetras.cUpsilonDieresis: return cUpsilonDieresisCorta;
		case uLetras.cUpsilonDieresisAgudo: return cUpsilonDieresisAgudoCorta;
		case uLetras.cUpsilonDieresisGrave: return cUpsilonDieresisGraveCorta;
		
		
		
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
		case uLetras.cRhoSuave:
		case uLetras.cRhoAspero:
			return cRho;
		case uLetras.cPsi: return cPsi;
		case uLetras.cDzeta: return cDzeta;
		case uLetras.cXi: return cXi;
		
		//mayúsculas
		case uLetras.cAlfaMayuscula: return cAlfaCortaMayuscula;
		case uLetras.cIotaMayuscula: return cIotaCortaMayuscula;
		case uLetras.cEtaMayuscula: return cEtaMayuscula;
		case uLetras.cOmicronMayuscula: return cOmicronMayuscula;
		case uLetras.cOmegaMayuscula: return cOmegaMayuscula;
		case uLetras.cUpsilonMayuscula: return cUpsilonCortaMayuscula;
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
		
		case uLetras.cIotaAsperoMayuscula: return cIotaAsperoCortaMayuscula;
		case uLetras.cIotaSuaveMayuscula: return cIotaSuaveCortaMayuscula;
		
		case uLetras.cIotaAsperoAgudoMayuscula: return cIotaAsperoAgudoCortaMayuscula;
		case uLetras.cIotaSuaveAgudoMayuscula: return cIotaSuaveAgudoCortaMayuscula;
		case uLetras.cIotaAsperoCircunflejoMayuscula: return cIotaAsperoCircunflejoMayuscula;
		case uLetras.cIotaSuaveCircunflejoMayuscula: return cIotaSuaveCircunflejoMayuscula;
		
		
		
		
		case uLetras.cAlfaSubscriptaMayuscula: return cAlfaSubscriptaMayuscula;
		case uLetras.cAlfaAsperoMayuscula: return cAlfaAsperoCortaMayuscula;
		case uLetras.cAlfaSuaveMayuscula: return cAlfaSuaveCortaMayuscula;
		
		case uLetras.cAlfaAsperoAgudoMayuscula: return cAlfaAsperoAgudoCortaMayuscula;
		case uLetras.cAlfaSuaveAgudoMayuscula: return cAlfaSuaveAgudoCortaMayuscula;
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
		
		case uLetras.cUpsilonAsperoMayuscula: return cUpsilonAsperoCortaMayuscula;
		case uLetras.cUpsilonAsperoAgudoMayuscula: return cUpsilonAsperoAgudoCortaMayuscula;
		case uLetras.cUpsilonAgudoMayuscula: return cUpsilonAgudoCortaMayuscula;
		case uLetras.cUpsilonAsperoCircunflejoMayuscula: return cUpsilonAsperoCircunflejoMayuscula;
		
		
		//caracteres con información de larga-corta(makron/vrachy)
		case uLetras.cAlfaVrachy : return cAlfaCorta;
		case uLetras.cAlfaVrachyMayuscula : return cAlfaCortaMayuscula;
		case uLetras.cAlfaMakron: return cAlfaLarga;
		case uLetras.cAlfaMakronMayuscula : return cAlfaLargaMayuscula;
		case uLetras.cIotaVrachy : return cIotaCorta;
		case uLetras.cIotaVrachyMayuscula : return cIotaCortaMayuscula;
		case uLetras.cIotaMakron: return cIotaLarga;
		case uLetras.cIotaMakronMayuscula : return cIotaLargaMayuscula;
		case uLetras.cUpsilonVrachy : return cUpsilonCorta;
		case uLetras.cUpsilonVrachyMayuscula : return cUpsilonCortaMayuscula;
		case uLetras.cUpsilonMakron: return cUpsilonLarga;
		case uLetras.cUpsilonMakronMayuscula : return cUpsilonLargaMayuscula;
		
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
		
		
		default:{
			System.out.println("carácter no encontrado hex=" + Integer.toHexString(caracter));
			throw new ExcepcionCaracterNoEncontrado((int)caracter);
		}
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
			
		case cAlfaAsperoCircunflejoMayuscula:
		case cAlfaSuaveCircunflejoMayuscula:
			ret = cAlfaCircunflejoMayuscula;
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
			
		case cAlfaSubscriptaAsperoAgudoMayuscula:
		case cAlfaSubscriptaSuaveAgudoMayuscula:
			ret = cAlfaSubscriptaAgudoMayuscula;
			break;
			
			
			
		case cAlfaSubscriptaAsperoGrave:
		case cAlfaSubscriptaSuaveGrave:
			ret = cAlfaSubscriptaGrave;
			break;
			
			
		case cAlfaSubscriptaAsperoGraveMayuscula:
		case cAlfaSubscriptaSuaveGraveMayuscula:
			ret = cAlfaSubscriptaGraveMayuscula;
			break;
			
			
			
		case cAlfaSubscriptaAsperoCircunflejo:
		case cAlfaSubscriptaSuaveCircunflejo:
			ret = cAlfaSubscriptaCircunflejo;
			break;
			
		case cAlfaSubscriptaAsperoCircunflejoMayuscula:
		case cAlfaSubscriptaSuaveCircunflejoMayuscula:
			ret = cAlfaSubscriptaCircunflejoMayuscula;
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
			
			
		case cEtaAsperoAgudoMayuscula:
		case cEtaSuaveAgudoMayuscula:
			ret = cEtaAgudoMayuscula;
			break;
			
		case cEtaAsperoGrave:
		case cEtaSuaveGrave:
			ret = cEtaGrave;
			break;
			
		case cEtaAsperoGraveMayuscula:
		case cEtaSuaveGraveMayuscula:
			ret = cEtaGraveMayuscula;
			break;
			
		case cEtaAsperoCircunflejo:
		case cEtaSuaveCircunflejo:
			ret = cEtaCircunflejo;
			break;
			
		case cEtaAsperoCircunflejoMayuscula:
		case cEtaSuaveCircunflejoMayuscula:
			ret = cEtaCircunflejoMayuscula;
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
			
		case cEtaSubscriptaAsperoAgudoMayuscula:
		case cEtaSubscriptaSuaveAgudoMayuscula:
			ret = cEtaSubscriptaAgudoMayuscula;
			break;
			
			
		case cEtaSubscriptaAsperoGrave:
		case cEtaSubscriptaSuaveGrave:
			ret = cEtaSubscriptaGrave;
			break;
			
			
		case cEtaSubscriptaAsperoGraveMayuscula:
		case cEtaSubscriptaSuaveGraveMayuscula:
			ret = cEtaSubscriptaGraveMayuscula;
			break;
			
			
		case cEtaSubscriptaAsperoCircunflejo:
		case cEtaSubscriptaSuaveCircunflejo:
			ret = cEtaSubscriptaCircunflejo;
			break;
			
		case cEtaSubscriptaAsperoCircunflejoMayuscula:
		case cEtaSubscriptaSuaveCircunflejoMayuscula:
			ret = cEtaSubscriptaCircunflejoMayuscula;
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
			
		case cOmicronAsperoAgudoMayuscula:
		case cOmicronSuaveAgudoMayuscula:
			ret = cOmicronAgudoMayuscula;
			break;
			
			
		case cOmicronAsperoGrave:
		case cOmicronSuaveGrave:
			ret = cOmicronGrave;
			break;
			
		case cOmicronAsperoGraveMayuscula:
		case cOmicronSuaveGraveMayuscula:
			ret = cOmicronGraveMayuscula;
			break;
			
			
			//upsilon
			
		case cUpsilonAsperoCircunflejo:
		case cUpsilonSuaveCircunflejo:
			ret = cUpsilonCircunflejo;
			break;
			
			
			
		case cUpsilonAsperoCircunflejoMayuscula:
			ret = cUpsilonCircunflejoMayuscula;
			break;
			
			
			
			//iota
			
			
		case cIotaAsperoCircunflejo:
		case cIotaSuaveCircunflejo:
			ret = cIotaCircunflejo;
			break;
			
		case cIotaAsperoCircunflejoMayuscula:
		case cIotaSuaveCircunflejoMayuscula:
			ret = cIotaCircunflejoMayuscula;
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
			
			
		case cEpsilonAsperoAgudoMayuscula:
		case cEpsilonSuaveAgudoMayuscula:
			ret = cEpsilonAgudoMayuscula;
			break;
			
			
		case cEpsilonSuaveGrave:
		case cEpsilonAsperoGrave:
			ret = cEpsilonGrave;
			break;
			
		case cEpsilonSuaveGraveMayuscula:
		case cEpsilonAsperoGraveMayuscula:
			ret = cEpsilonGraveMayuscula;
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
			
		case cOmegaAsperoAgudoMayuscula:
		case cOmegaSuaveAgudoMayuscula:
			ret = cOmegaAgudoMayuscula;
			break;
			
			
		case cOmegaAsperoGrave:
		case cOmegaSuaveGrave:
			ret = cOmegaGrave;
			break;
			
		case cOmegaAsperoGraveMayuscula:
		case cOmegaSuaveGraveMayuscula:
			ret = cOmegaGraveMayuscula;
			break;
			
			
		case cOmegaAsperoCircunflejo:
		case cOmegaSuaveCircunflejo:
			ret = cOmegaCircunflejo;
			break;
			
		case cOmegaAsperoCircunflejoMayuscula:
		case cOmegaSuaveCircunflejoMayuscula:
			ret = cOmegaCircunflejoMayuscula;
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
			
		case cOmegaSubscriptaAsperoAgudoMayuscula:
		case cOmegaSubscriptaSuaveAgudoMayuscula:
			ret = cOmegaSubscriptaAgudoMayuscula;
			break;
			
			
		case cOmegaSubscriptaAsperoGrave:
		case cOmegaSubscriptaSuaveGrave:
			ret = cOmegaSubscriptaGrave;
			break;
			
		case cOmegaSubscriptaAsperoGraveMayuscula:
		case cOmegaSubscriptaSuaveGraveMayuscula:
			ret = cOmegaSubscriptaGraveMayuscula;
			break;
			
			
		case cOmegaSubscriptaAsperoCircunflejo:
		case cOmegaSubscriptaSuaveCircunflejo:
			ret = cOmegaSubscriptaCircunflejo;
			break;
			
		case cOmegaSubscriptaAsperoCircunflejoMayuscula:
		case cOmegaSubscriptaSuaveCircunflejoMayuscula:
			ret = cOmegaSubscriptaCircunflejoMayuscula;
			break;
			
			
			//rho
		case cRhoAspero:
		case cRhoSuave:
			ret = cRho;
			break;
			
		case cRhoAsperoMayuscula:
			ret = cRho;
			break;
			
			
			//largas-cortas ****************
			//alfa
		case cAlfaAsperoCorta:
		case cAlfaSuaveCorta:
			ret = cAlfaCorta;
			break;
		case cAlfaAsperoAgudoCorta:
		case cAlfaSuaveAgudoCorta:
			ret = cAlfaAgudoCorta;
			break;
		case cAlfaAsperoGraveCorta:
		case cAlfaSuaveGraveCorta:
			ret = cAlfaGraveCorta;
			break;
			
		case cAlfaAsperoCortaMayuscula:
		case cAlfaSuaveCortaMayuscula:
			ret = cAlfaCortaMayuscula;
			break;
		case cAlfaAsperoAgudoCortaMayuscula:
		case cAlfaSuaveAgudoCortaMayuscula:
			ret = cAlfaAgudoCorta;
			break;
			
			
			
			
			//upsilon
		case cUpsilonAsperoCorta:
		case cUpsilonSuaveCorta:
			ret = cUpsilonCorta;
			break;
			
		case cUpsilonAsperoCortaMayuscula:
			ret = cUpsilonCortaMayuscula;
			break;
			
			
			
		case cUpsilonAsperoAgudoCorta:
		case cUpsilonSuaveAgudoCorta:
			ret = cUpsilonAgudoCorta;
			break;
			
		case cUpsilonAsperoAgudoCortaMayuscula:
			ret = cUpsilonAgudoCortaMayuscula;
			break;
			
			
			
		case cUpsilonAsperoGraveCorta:
		case cUpsilonSuaveGraveCorta:
			ret = cUpsilonGraveCorta;
			break;
			
		case cUpsilonAsperoGraveCortaMayuscula:
			ret = cUpsilonGraveCortaMayuscula;
			break;
			
			
			//iota
		case cIotaAsperoCorta:
		case cIotaSuaveCorta:
			ret = cIotaCorta;
			break;
			
		case cIotaAsperoCortaMayuscula:
		case cIotaSuaveCortaMayuscula:
			ret = cIotaCortaMayuscula;
			break;
			
			
		case cIotaAsperoAgudoCorta:
		case cIotaSuaveAgudoCorta:
			ret = cIotaCorta;
			break;
			
		case cIotaAsperoAgudoCortaMayuscula:
		case cIotaSuaveAgudoCortaMayuscula:
			ret = cIotaCortaMayuscula;
			break;
			
		case cIotaAsperoGraveCorta:
		case cIotaSuaveGraveCorta:
			ret = cIotaGraveCorta;
			break;
			
		case cIotaAsperoGraveCortaMayuscula:
		case cIotaSuaveGraveCortaMayuscula:
			ret = cIotaGraveCortaMayuscula;
			break;
			
			
			//alfa
		case cAlfaAsperoLarga:
		case cAlfaSuaveLarga:
			ret = cAlfaLarga;
			break;
			
		case cAlfaAsperoLargaMayuscula:
		case cAlfaSuaveLargaMayuscula:
			ret = cAlfaLargaMayuscula;
			break;
			
		case cAlfaAsperoAgudoLarga:
		case cAlfaSuaveAgudoLarga:
			ret = cAlfaAgudoLarga;
			break;
			
		case cAlfaAsperoAgudoLargaMayuscula:
		case cAlfaSuaveAgudoLargaMayuscula:
			ret = cAlfaAgudoLargaMayuscula;
			break;
			
			
		case cAlfaAsperoGraveLarga:
		case cAlfaSuaveGraveLarga:
			ret = cAlfaGraveLarga;
			break;
			
		case cAlfaAsperoGraveLargaMayuscula:
		case cAlfaSuaveGraveLargaMayuscula:
			ret = cAlfaGraveLargamayuscula;
			break;
			
			
			//upsilon
		case cUpsilonAsperoLarga:
		case cUpsilonSuaveLarga:
			ret = cUpsilonLarga;
			break;
			
		case cUpsilonAsperoLargaMayuscula:
			ret = cUpsilonLargaMayuscula;
			break;
			
			
		case cUpsilonAsperoAgudoLarga:
		case cUpsilonSuaveAgudoLarga:
			ret = cUpsilonAgudoLarga;
			break;
			
		case cUpsilonAsperoAgudoLargaMayuscula:
			ret = cUpsilonAgudoLargaMayuscula;
			break;
			
			
		case cUpsilonAsperoGraveLarga:
		case cUpsilonSuaveGraveLarga:
			ret = cUpsilonGraveLarga;
			break;
			
		case cUpsilonAsperoGraveLargaMayuscula:
			ret = cUpsilonGraveLargaMayuscula;
			break;
			
			
			//iota
		case cIotaAsperoLarga:
		case cIotaSuaveLarga:
			ret = cIotaLarga;
			break;
			
		case cIotaAsperoLargaMayuscula:
		case cIotaSuaveLargaMayuscula:
			ret = cIotaLargaMayuscula;
			break;
			
			
		case cIotaAsperoAgudoLarga:
		case cIotaSuaveAgudoLarga:
			ret = cIotaLarga;
			break;
			
		case cIotaAsperoAgudoLargaMayuscula:
		case cIotaSuaveAgudoLargaMayuscula:
			ret = cIotaLargaMayuscula;
			break;
			
		case cIotaAsperoGraveLarga:
		case cIotaSuaveGraveLarga:
			ret = cIotaGraveLarga;
			break;
			
		case cIotaAsperoGraveLargaMayuscula:
		case cIotaSuaveGraveLargaMayuscula:
			ret = cIotaGraveLargaMayuscula;
			break;
			
			
		default:
			ret = ncar;
		}
		;
		return ret;
	}
	
	public static char espirituSuaveCaracter (char letra){
		switch(letra){
		case cAlfaLarga:
			return cAlfaSuaveLarga;
		case cAlfaCorta:
			return cAlfaSuaveCorta;
		case cAlfaAgudoLarga:
			return cAlfaSuaveAgudoLarga;
		case cAlfaAgudoCorta:
			return cAlfaSuaveAgudoCorta;
		case cAlfaCircunflejo:
			return cAlfaSuaveCircunflejo;
		case cAlfaSubscripta:
			return cAlfaSubscriptaSuave;
		case cAlfaSubscriptaAgudo:
			return cAlfaSubscriptaSuaveAgudo;
		case cAlfaSubscriptaCircunflejo:
			return cAlfaSubscriptaSuaveCircunflejo;
			
		case cAlfaLargaMayuscula:
			return cAlfaSuaveLargaMayuscula;
		case cAlfaCortaMayuscula:
			return cAlfaSuaveCortaMayuscula;
		case cAlfaAgudoLargaMayuscula:
			return cAlfaSuaveAgudoLargaMayuscula;
		case cAlfaCircunflejoMayuscula:
			return cAlfaSuaveCircunflejoMayuscula;
		case cAlfaSubscriptaMayuscula:
			return cAlfaSubscriptaSuaveMayuscula;
		case cAlfaSubscriptaAgudoMayuscula:
			return cAlfaSubscriptaSuaveAgudoMayuscula;
		case cAlfaSubscriptaCircunflejoMayuscula:
			return cAlfaSubscriptaSuaveCircunflejoMayuscula;
			
			
			
			
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
		case cEtaCircunflejoMayuscula:
			return cEtaSuaveCircunflejoMayuscula;
		case cEtaSubscriptaMayuscula:
			return cEtaSubscriptaSuaveMayuscula;
		case cEtaSubscriptaAgudoMayuscula:
			return cEtaSubscriptaSuaveAgudoMayuscula;
		case cEtaSubscriptaCircunflejoMayuscula:
			return cEtaSubscriptaSuaveCircunflejoMayuscula;
			
			
			
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
		case cOmegaCircunflejoMayuscula:
			return cOmegaSuaveCircunflejoMayuscula;
		case cOmegaSubscriptaMayuscula:
			return cOmegaSubscriptaSuaveMayuscula;
		case cOmegaSubscriptaAgudoMayuscula:
			return cOmegaSubscriptaSuaveAgudoMayuscula;
		case cOmegaSubscriptaCircunflejoMayuscula:
			return cOmegaSubscriptaSuaveCircunflejoMayuscula;
			
			
			
		case cIotaLarga:
			return cIotaSuaveLarga;
		case cIotaCorta:
			return cIotaSuaveCorta;
		case cIotaAgudoLarga:
			return cIotaSuaveAgudoLarga;
		case cIotaAgudoCorta:
			return cIotaSuaveAgudoCorta;
		case cIotaCircunflejo:
			return cIotaSuaveCircunflejo;
			
		case cIotaLargaMayuscula:
			return cIotaSuaveLargaMayuscula;
		case cIotaCortaMayuscula:
			return cIotaSuaveCortaMayuscula;
		case cIotaAgudoLargaMayuscula:
			return cIotaSuaveAgudoLargaMayuscula;
		case cIotaAgudoCortaMayuscula:
			return cIotaSuaveAgudoCortaMayuscula;
		case cIotaCircunflejoMayuscula:
			return cIotaSuaveCircunflejoMayuscula;
			
			
			
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
			
			
			
		case cUpsilonLarga:
			return cUpsilonSuaveLarga;
		case cUpsilonCorta:
			return cUpsilonSuaveCorta;
		case cUpsilonAgudoLarga:
			return cUpsilonSuaveAgudoLarga;
		case cUpsilonAgudoCorta:
			return cUpsilonSuaveAgudoCorta;
		case cUpsilonGraveLarga:
			return cUpsilonSuaveGraveLarga;
		case cUpsilonCircunflejo:
			return cUpsilonSuaveCircunflejo;
			
		default:
			return letra;
		}
	}
	
	public static char dieresisLetra(char letra){
		char ret;
		switch(letra){
		case cIotaCorta:
			ret = cIotaDieresisCorta ; break;
		case cIotaAgudoCorta:
			ret = cIotaDieresisAgudoCorta ; break;
		case cIotaLarga:
			ret = cIotaDieresisLarga ; break;
		case cIotaAgudoLarga:
			ret = cIotaDieresisAgudoLarga ; break;
			
			
		case cIotaCortaMayuscula:
			ret = cIotaDieresisCortaMayuscula ; break;
		case cIotaAgudoCortaMayuscula:
			ret = cIotaDieresisAgudoCortaMayuscula ; break;
		case cIotaLargaMayuscula:
			ret = cIotaDieresisLargaMayuscula ; break;
		case cIotaAgudoLargaMayuscula:
			ret = cIotaDieresisAgudoLargaMayuscula ; break;
			
			
			
			
		case cUpsilonCorta:
			ret = cUpsilonDieresisCorta ; break;
		case cUpsilonAgudoCorta:
			ret = cUpsilonDieresisAgudoCorta ; break;
		case cUpsilonLarga:
			ret = cUpsilonDieresisLarga ; break;
		case cUpsilonAgudoLarga:
			ret = cUpsilonDieresisAgudoLarga ; break;
			
		case cUpsilonCortaMayuscula:
			ret = cUpsilonDieresisCortaMayuscula ; break;
		case cUpsilonAgudoCortaMayuscula:
			ret = cUpsilonDieresisAgudoCortaMayuscula ; break;
		case cUpsilonLargaMayuscula:
			ret = cUpsilonDieresisLargaMayuscula ; break;
		case cUpsilonAgudoLargaMayuscula:
			ret = cUpsilonDieresisAgudoLargaMayuscula ; break;
			
			
		default:
			ret=letra;
		}
		return ret;
	}
	
	/**
	 * quita el diéresis a una letra, si lo tiene
	 * @param letra
	 * @return
	 */
	public static char quitaDieresisLetra(char letra){
		char ret;
		switch(letra){
		case cIotaDieresisCorta:
			ret = cIotaCorta ; break;
		case cIotaDieresisAgudoCorta:
			ret = cIotaAgudoCorta ; break;
		case cIotaDieresisLarga:
			ret = cIotaLarga ; break;
		case cIotaDieresisAgudoLarga:
			ret = cIotaAgudoLarga ; break;
			
			
		case cIotaDieresisCortaMayuscula:
			ret = cIotaCortaMayuscula ; break;
		case cIotaDieresisAgudoCortaMayuscula:
			ret = cIotaAgudoCortaMayuscula ; break;
		case cIotaDieresisLargaMayuscula:
			ret = cIotaLargaMayuscula ; break;
		case cIotaDieresisAgudoLargaMayuscula:
			ret = cIotaAgudoLargaMayuscula ; break;
			
			
			
			
		case cUpsilonDieresisCorta:
			ret = cUpsilonCorta ; break;
		case cUpsilonDieresisAgudoCorta:
			ret = cUpsilonAgudoCorta ; break;
		case cUpsilonDieresisLarga:
			ret = cUpsilonLarga ; break;
		case cUpsilonDieresisAgudoLarga:
			ret = cUpsilonAgudoLarga ; break;
			
		case cUpsilonDieresisCortaMayuscula:
			ret = cUpsilonCortaMayuscula ; break;
		case cUpsilonDieresisAgudoCortaMayuscula:
			ret = cUpsilonAgudoCortaMayuscula ; break;
		case cUpsilonDieresisLargaMayuscula:
			ret = cUpsilonLargaMayuscula ; break;
		case cUpsilonDieresisAgudoLargaMayuscula:
			ret = cUpsilonAgudoLargaMayuscula ; break;
			
			
		default:
			ret=letra;
		}
		return ret;
	}	
	
	public static char espirituAsperoCaracter (char letra){
		switch(letra){
		case cAlfaLarga:
			return cAlfaAsperoLarga;
		case cAlfaCorta:
			return cAlfaAsperoCorta;
		case cAlfaAgudoCorta:
			return cAlfaAsperoAgudoCorta;
		case cAlfaAgudoLarga:
			return cAlfaAsperoAgudoLarga;
		case cAlfaCircunflejo:
			return cAlfaAsperoCircunflejo;
		case cAlfaSubscripta:
			return cAlfaSubscriptaAspero;
		case cAlfaSubscriptaAgudo:
			return cAlfaSubscriptaAsperoAgudo;
		case cAlfaSubscriptaCircunflejo:
			return cAlfaSubscriptaAsperoCircunflejo;
			
		case cAlfaCortaMayuscula:
			return cAlfaAsperoCortaMayuscula;
		case cAlfaLargaMayuscula:
			return cAlfaAsperoLargaMayuscula;
		case cAlfaAgudoLargaMayuscula:
			return cAlfaAsperoAgudoLargaMayuscula;
		case cAlfaCircunflejoMayuscula:
			return cAlfaAsperoCircunflejoMayuscula;
		case cAlfaSubscriptaMayuscula:
			return cAlfaSubscriptaAsperoMayuscula;
		case cAlfaSubscriptaAgudoMayuscula:
			return cAlfaSubscriptaAsperoAgudoMayuscula;
		case cAlfaSubscriptaCircunflejoMayuscula:
			return cAlfaSubscriptaAsperoCircunflejoMayuscula;
			
			
			
		case cEta:
			return cEtaAspero;
		case cEtaAgudo:
			return cEtaAsperoAgudo;
		case cEtaCircunflejo:
			return cEtaAsperoCircunflejo;
		case cEtaSubscripta:
			return cEtaSubscriptaAspero;
		case cEtaSubscriptaAgudo:
			return cEtaSubscriptaAsperoAgudo;
		case cEtaSubscriptaCircunflejo:
			return cEtaSubscriptaAsperoCircunflejo;
			
			
		case cEtaMayuscula:
			return cEtaAsperoMayuscula;
		case cEtaAgudoMayuscula:
			return cEtaAsperoAgudoMayuscula;
		case cEtaCircunflejoMayuscula:
			return cEtaAsperoCircunflejoMayuscula;
		case cEtaSubscriptaMayuscula:
			return cEtaSubscriptaAsperoMayuscula;
		case cEtaSubscriptaAgudoMayuscula:
			return cEtaSubscriptaAsperoAgudoMayuscula;
		case cEtaSubscriptaCircunflejoMayuscula:
			return cEtaSubscriptaAsperoCircunflejoMayuscula;
			
			
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
		case cOmegaCircunflejoMayuscula:
			return cOmegaAsperoCircunflejoMayuscula;
		case cOmegaSubscriptaMayuscula:
			return cOmegaSubscriptaAsperoMayuscula;
		case cOmegaSubscriptaAgudoMayuscula:
			return cOmegaSubscriptaAsperoAgudoMayuscula;
		case cOmegaSubscriptaCircunflejoMayuscula:
			return cOmegaSubscriptaAsperoCircunflejoMayuscula;
			
			
			
		case cIotaLarga:
			return cIotaAsperoLarga;
		case cIotaCorta:
			return cIotaAsperoCorta;
		case cIotaAgudoLarga:
			return cIotaAsperoAgudoLarga;
		case cIotaAgudoCorta:
			return cIotaAsperoAgudoCorta;
		case cIotaCircunflejo:
			return cIotaAsperoCircunflejo;
			
		case cIotaLargaMayuscula:
			return cIotaAsperoLargaMayuscula;
		case cIotaCortaMayuscula:
			return cIotaAsperoCortaMayuscula;
		case cIotaAgudoCortaMayuscula:
			return cIotaAsperoAgudoCortaMayuscula;
		case cIotaAgudoLargaMayuscula:
			return cIotaAsperoAgudoLargaMayuscula;
		case cIotaCircunflejoMayuscula:
			return cIotaAsperoCircunflejoMayuscula;
			
			
			
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
			
			
		case cUpsilonLarga:
			return cUpsilonAsperoLarga;
		case cUpsilonCorta:
			return cUpsilonAsperoCorta;
		case cUpsilonAgudoLarga:
			return cUpsilonAsperoAgudoLarga;
		case cUpsilonAgudoCorta:
			return cUpsilonAsperoAgudoCorta;
		case cUpsilonGraveLarga:
			return cUpsilonAsperoGraveLarga;
		case cUpsilonCircunflejo:
			return cUpsilonAsperoCircunflejo;
			
		case cUpsilonLargaMayuscula:
			return cUpsilonAsperoLargaMayuscula;
		case cUpsilonCortaMayuscula:
			return cUpsilonAsperoCortaMayuscula;
		case cUpsilonAgudoLargaMayuscula:
			return cUpsilonAsperoAgudoLargaMayuscula;
		case cUpsilonAgudoCortaMayuscula:
			return cUpsilonAsperoAgudoCortaMayuscula;
		case cUpsilonGraveLargaMayuscula:
			return cUpsilonAsperoGraveLargaMayuscula;
		case cUpsilonCircunflejoMayuscula:
			return cUpsilonAsperoCircunflejoMayuscula;
			
			
			
		default:
			return letra;
		}
	}
	
	
	public static char subscribeLetra(char letra){
		switch(letra){
		//alfa
		case cAlfaLarga: case cAlfaCorta:
			return cAlfaSubscripta;
		case cAlfaAgudoLarga:  case cAlfaAgudoCorta:
			return cAlfaSubscriptaAgudo;
		case cAlfaCircunflejo:
			return cAlfaSubscriptaCircunflejo;
		case cAlfaSuaveLarga: case cAlfaSuaveCorta:
			return cAlfaSubscriptaSuave;
		case cAlfaAsperoLarga: case cAlfaAsperoCorta:
			return cAlfaSubscriptaAspero;
		case cAlfaSuaveAgudoLarga: case cAlfaSuaveAgudoCorta:
			return cAlfaSubscriptaSuaveAgudo;
		case cAlfaAsperoAgudoLarga: case cAlfaAsperoAgudoCorta:
			return cAlfaSubscriptaAsperoAgudo;
		case cAlfaSuaveCircunflejo:
			return cAlfaSubscriptaSuaveCircunflejo;
		case cAlfaAsperoCircunflejo:
			return cAlfaSubscriptaAsperoCircunflejo;
			
		case cAlfaLargaMayuscula: case cAlfaCortaMayuscula:
			return cAlfaSubscriptaMayuscula;
		case cAlfaAgudoLargaMayuscula:  //case cAlfaAgudoCortaMayuscula:
			return cAlfaSubscriptaAgudoMayuscula;
		case cAlfaCircunflejoMayuscula:
			return cAlfaSubscriptaCircunflejoMayuscula;
		case cAlfaSuaveLargaMayuscula: case cAlfaSuaveCortaMayuscula:
			return cAlfaSubscriptaSuaveMayuscula;
		case cAlfaAsperoLargaMayuscula: case cAlfaAsperoCortaMayuscula:
			return cAlfaSubscriptaAsperoMayuscula;
		case cAlfaSuaveAgudoLargaMayuscula: case cAlfaSuaveAgudoCortaMayuscula:
			return cAlfaSubscriptaSuaveAgudoMayuscula;
		case cAlfaAsperoAgudoLargaMayuscula: case cAlfaAsperoAgudoCortaMayuscula:
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
		case cEtaAgudoMayuscula:
			return cEtaSubscriptaAgudoMayuscula;
		case cEtaCircunflejoMayuscula:
			return cEtaSubscriptaCircunflejoMayuscula;
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
		case cOmegaAgudoMayuscula:
			return cOmegaSubscriptaAgudoMayuscula;
		case cOmegaCircunflejoMayuscula:
			return cOmegaSubscriptaCircunflejoMayuscula;
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
	 *  ofrece la versión corta de este carácter,
	 *  en caso de que se pueda y sea una vocal corta o indeterminada
	 *  caso contrario, devuelve la misma letra
	 * */
	public static char acortaCaracter(char caracter){
		switch(caracter){
		case cAlfaLarga:  return cAlfaCorta;
		case cAlfaAsperoLarga: return cAlfaAsperoCorta;
		case cAlfaSuaveLarga: return cAlfaSuaveCorta;
		case cAlfaAgudoLarga: return cAlfaAgudoCorta;
		case cAlfaAsperoAgudoLarga: return cAlfaAsperoAgudoCorta;
		case cAlfaSuaveAgudoLarga: return cAlfaSuaveAgudoCorta;
		case cAlfaAsperoGraveLarga: return cAlfaAsperoGraveCorta;
		case cAlfaSuaveGraveLarga: return cAlfaSuaveGraveCorta;
		
		case cAlfaLargaMayuscula: return cAlfaCortaMayuscula;
		case cAlfaAsperoLargaMayuscula: return cAlfaAsperoCortaMayuscula;
		case cAlfaSuaveLargaMayuscula: return cAlfaSuaveCortaMayuscula;
		case cAlfaAsperoAgudoLargaMayuscula: return cAlfaAsperoAgudoCortaMayuscula;
		case cAlfaSuaveAgudoLargaMayuscula: return cAlfaSuaveAgudoCortaMayuscula;
		case cAlfaAsperoGraveLargaMayuscula:return cAlfaAsperoGraveCortaMayuscula;
		case cAlfaSuaveGraveLargaMayuscula: return cAlfaSuaveGraveCortaMayuscula;
		
		
		case cIotaLarga: return cIotaCorta;
		case cIotaAsperoLarga: return cIotaAsperoCorta;
		case cIotaSuaveLarga: return cIotaSuaveCorta;
		case cIotaAgudoLarga: return cIotaAgudoCorta;
		case cIotaAsperoAgudoLarga: return cIotaAsperoAgudoCorta;
		case cIotaSuaveAgudoLarga: return cIotaSuaveAgudoCorta;
		case cIotaAsperoGraveLarga: return cIotaAsperoGraveCorta;
		
		case cIotaLargaMayuscula: return cIotaCortaMayuscula;
		case cIotaAsperoLargaMayuscula: return cIotaAsperoCortaMayuscula;
		case cIotaSuaveLargaMayuscula: return cIotaSuaveCortaMayuscula;
		case cIotaAgudoLargaMayuscula: return cIotaAgudoCortaMayuscula;
		case cIotaAsperoAgudoLargaMayuscula: return cIotaAsperoAgudoCortaMayuscula;
		case cIotaSuaveAgudoLargaMayuscula: return cIotaSuaveAgudoCortaMayuscula;
		case cIotaAsperoGraveLargaMayuscula:return cIotaAsperoGraveCortaMayuscula;
		
		
		case cUpsilonLarga: return cUpsilonCorta;
		case cUpsilonAsperoLarga: return cUpsilonAsperoCorta;
		case cUpsilonSuaveLarga: return cUpsilonSuaveCorta;
		case cUpsilonAgudoLarga: return cUpsilonAgudoCorta;
		case cUpsilonAsperoAgudoLarga: return cUpsilonAsperoAgudoCorta;
		case cUpsilonSuaveAgudoLarga: return cUpsilonSuaveAgudoCorta;
		case cUpsilonAsperoGraveLarga: return cUpsilonAsperoGraveCorta;
		
		case cUpsilonAsperoLargaMayuscula: return cUpsilonAsperoCortaMayuscula;
		case cUpsilonLargaMayuscula:  return cUpsilonCortaMayuscula;
		case cUpsilonAgudoLargaMayuscula:  return cUpsilonAgudoCortaMayuscula;
		case cUpsilonAsperoAgudoLargaMayuscula: return cUpsilonAsperoAgudoCortaMayuscula;
		case cUpsilonAsperoGraveLargaMayuscula: return cUpsilonAsperoGraveCortaMayuscula;
		
		
		default:
			return caracter;
		}
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
	 * devolviendo un carácter unicode compuesto (por ejemplo, alfa con acento agudo)
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
			return aux.getVersionLarga();
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
	 * informa si un carácter unicode tiene información de larga-corta.
	 * Se usa para recorrer palabras enteras y determinar si información de
	 * larga-corta ya está presente en la palabra 
	 * @param caracter
	 * @return
	 */
	public static boolean tieneInformacionLargaCorta(char caracter){
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
	
	
	
	
	
	
}