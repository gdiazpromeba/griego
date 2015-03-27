package com.kalos.flexion;

import java.util.List;

import com.kalos.enumeraciones.ArticulacionConsonante;
import com.kalos.enumeraciones.Espiritu;
import com.kalos.fonts.CarPos;
import com.kalos.fonts.CaracterGriego;
import com.kalos.fonts.CaracterGriegoFactory;
import com.kalos.fonts.TipoLetra;
import com.kalos.operaciones.OpLetrasUnicode;
import com.kalos.operaciones.OpPalabras;

import org.apache.log4j.Logger;

public class Preposiciones {
    private String F = OpPalabras.strBetaACompleto("PERI");
    private String I = OpPalabras.strBetaACompleto("PRO");
    private String E = OpPalabras.strBetaACompleto("PROJ");
    private String K = OpPalabras.strBetaACompleto("PROS");
    private String B = OpPalabras.strBetaACompleto("N");
    private String C = OpPalabras.strBetaACompleto("E)K");
    private String G = OpPalabras.strBetaACompleto("SUN");
    private String D = OpPalabras.strBetaACompleto("J");
    private String A = OpPalabras.strBetaACompleto("S");
    private String H = OpPalabras.strBetaACompleto("I");
    Logger J = Logger.getLogger(getClass().getName());

    public String une(String paramString1, String paramString2, boolean paramBoolean) {
	Espiritu localO = OpPalabras.getEspiritu(paramString1);
	paramString1 = OpPalabras.desespirituar(paramString1);
	if ((paramString2.equals(this.F)) || (paramString2.equals(this.I))) {
	    paramString1 = B(paramString2, paramString1);
	    paramString1 = A(paramString2, paramString1);
	    return A(paramString1, paramString2, paramBoolean);
	}
	if (paramString2.equals(this.E)) {
	    paramString2 = this.K;
	}
	CaracterGriego localC1 = CaracterGriegoFactory.produceCaracterGriego(paramString1.charAt(0));
	CaracterGriego localC2 = CaracterGriegoFactory
		.produceCaracterGriego(paramString2.charAt(paramString2.length() - 1));
	if (localC2.esVocal()) {
	    if (localC1.esVocal()) {
		paramString2 = OpPalabras.comeFinal(paramString2, 1);
		localC2 = CaracterGriegoFactory.produceCaracterGriego(paramString2.charAt(paramString2.length() - 1));
	    }
	    if (localO == Espiritu.Aspero) {
		if (localC2.getCaracter() == 'τ') {
		    paramString2 = paramString2.replace('τ', 'θ');
		} else if (localC2.getCaracter() == 'π') {
		    paramString2 = paramString2.replace('π', 'φ');
		}
	    }
	    paramString1 = A(paramString2, paramString1);
	    paramString1 = B(paramString2, paramString1);
	    return A(paramString1, paramString2, paramBoolean);
	}
	if (paramString2.endsWith(this.B)) {
	    ArticulacionConsonante localV = TipoLetra.getArticulacionConsonante(localC1.getCaracter());
	    if (!localV.equals(ArticulacionConsonante.NoEsConsonante)) {
		if (localV.equals(ArticulacionConsonante.Labial)) {
		    paramString2 = paramString2.replace('ν', 'μ');
		} else if (localV.equals(ArticulacionConsonante.Gutural)) {
		    paramString2 = paramString2.replace('ν', 'γ');
		}
	    }
	}
	if ((paramString2.equals(this.C)) && (localC1.esVocal())) {
	    paramString2 = paramString2.replace('κ', 'ξ');
	}
	if (paramString2.equals(this.G)) {
	    int i = localC1.getCaracter();
	    if ((i == 950) || (i == 963)) {
		paramString2 = OpPalabras.comeFinal(paramString2, 1);
	    }
	}
	if (paramString2.endsWith(this.D)) {
	    paramString2 = OpPalabras.comeFinal(paramString2, 1).concat(this.A);
	}
	return A(paramString1, paramString2, paramBoolean);
    }

    private String B(String paramString1, String paramString2) {
	if (paramString1.endsWith(this.H)) {
	    CarPos localA = CarPos.getCarPos(paramString2, 0);
	    char c = localA.getCaracter();
	    c = OpLetrasUnicode.dieresisLetra(c);
	    StringBuffer localStringBuffer = new StringBuffer(paramString2);
	    localStringBuffer.setCharAt(0, c);
	    paramString2 = localStringBuffer.toString();
	}
	return paramString2;
    }

    private String A(String paramString1, String paramString2) {
	CarPos localA1 = CarPos.getCarPos(paramString1, paramString1.length() - 1);
	CarPos localA2 = CarPos.getCarPos(paramString2, 0);
	if ((localA1.esVocal()) && (localA2.getCaracter() == 'ρ')) {
	    paramString2 = new String(new char[] { 'ρ' }).concat(paramString2);
	}
	return paramString2;
    }

    private String A(String paramString1, String paramString2, boolean paramBoolean) {
	if (!paramBoolean) {
	    StringBuffer localStringBuffer = new StringBuffer();
	    localStringBuffer.append(paramString2);
	    localStringBuffer.append(paramString1);
	    return localStringBuffer.toString();
	}
	StringBuffer localStringBuffer = new StringBuffer();
	localStringBuffer.append(OpPalabras.desacentuar(paramString2));
	localStringBuffer.append(OpPalabras.desacentuar(paramString1));
	return OpPalabras.acentua(localStringBuffer.toString());
    }

    public String une(String paramString, List<String> paramList, boolean paramBoolean) {
	if (paramList == null) {
	    return paramString;
	}
	String str1 = paramString;
	for (int i = paramList.size() - 1; i >= 0; i--) {
	    String str2 = (String) paramList.get(i);
	    boolean bool = i == paramList.size() - 1;
	    bool = (bool) && (paramBoolean);
	    str1 = une(str1, str2, bool);
	}
	return str1;
    }
}
