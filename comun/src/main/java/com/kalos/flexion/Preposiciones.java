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
	private String peri = OpPalabras.strBetaACompleto("PERI");
	private String pro = OpPalabras.strBetaACompleto("PRO");
	private String proj = OpPalabras.strBetaACompleto("PROJ");
	private String pros = OpPalabras.strBetaACompleto("PROS");
	private String n = OpPalabras.strBetaACompleto("N");
	private String ek = OpPalabras.strBetaACompleto("E)K");
	private String sun = OpPalabras.strBetaACompleto("SUN");
	private String j = OpPalabras.strBetaACompleto("J");
	private String s = OpPalabras.strBetaACompleto("S");
	private String i = OpPalabras.strBetaACompleto("I");
	Logger log = Logger.getLogger(getClass().getName());

	public String une(String palabra, String prep, boolean acentuar) {
		Espiritu espiritu = OpPalabras.getEspiritu(palabra);
		palabra = OpPalabras.desespirituar(palabra);
		if ((prep.equals(this.peri)) || (prep.equals(this.pro))) {
			palabra = uneConDieresis(prep, palabra);
			palabra = uneConRho(prep, palabra);
			return uneAcentuando(palabra, prep, acentuar);
		}
		if (prep.equals(this.proj)) {
			prep = this.pros;
		}
		CaracterGriego primerCar = CaracterGriegoFactory.produceCaracterGriego(palabra.charAt(0));
		CaracterGriego ultimoCar = CaracterGriegoFactory.produceCaracterGriego(prep.charAt(prep.length() - 1));
		if (ultimoCar.esVocal()) {
			if (primerCar.esVocal()) {
				prep = OpPalabras.comeFinal(prep, 1);
				ultimoCar = CaracterGriegoFactory.produceCaracterGriego(prep.charAt(prep.length() - 1));
			}
			if (espiritu == Espiritu.Aspero) {
				if (ultimoCar.getCaracter() == 'τ') {
					prep = prep.replace('τ', 'θ');
				} else if (ultimoCar.getCaracter() == 'π') {
					prep = prep.replace('π', 'φ');
				}
			}
			palabra = uneConRho(prep, palabra);
			palabra = uneConDieresis(prep, palabra);
			return uneAcentuando(palabra, prep, acentuar);
		}
		if (prep.endsWith(this.n)) {
			ArticulacionConsonante localV = TipoLetra.getArticulacionConsonante(primerCar.getCaracter());
			if (!localV.equals(ArticulacionConsonante.NoEsConsonante)) {
				if (localV.equals(ArticulacionConsonante.Labial)) {
					prep = prep.replace('ν', 'μ');
				} else if (localV.equals(ArticulacionConsonante.Gutural)) {
					prep = prep.replace('ν', 'γ');
				}
			}
		}
		if ((prep.equals(this.ek)) && (primerCar.esVocal())) {
			prep = prep.replace('κ', 'ξ');
		}
		if (prep.equals(this.sun)) {
			int i = primerCar.getCaracter();
			if ((i == 950) || (i == 963)) {
				prep = OpPalabras.comeFinal(prep, 1);
			}
		}
		if (prep.endsWith(this.j)) {
			prep = OpPalabras.comeFinal(prep, 1).concat(this.s);
		}
		return uneAcentuando(palabra, prep, acentuar);
	}

	private String uneConDieresis(String prep, String palabra) {
		if (prep.endsWith(this.i)) {
			CarPos primerCar = CarPos.getCarPos(palabra, 0);
			char c = primerCar.getCaracter();
			c = OpLetrasUnicode.dieresisLetra(c);
			StringBuffer sb = new StringBuffer(palabra);
			sb.setCharAt(0, c);
			palabra = sb.toString();
		}
		return palabra;
	}

	private String uneConRho(String prep, String palabra) {
		CarPos ultimoCar = CarPos.getCarPos(prep, prep.length() - 1);
		CarPos primerCar = CarPos.getCarPos(palabra, 0);
		if ((ultimoCar.esVocal()) && (primerCar.getCaracter() == 'ρ')) {
			palabra = new String(new char[] { 'ρ' }).concat(palabra);
		}
		return palabra;
	}

	private String uneAcentuando(String palabra, String prep, boolean acentuar) {
		if (!acentuar) {
			StringBuffer sb = new StringBuffer();
			sb.append(prep);
			sb.append(palabra);
			return sb.toString();
		}
		StringBuffer sb = new StringBuffer();
		sb.append(OpPalabras.desacentuar(prep));
		sb.append(OpPalabras.desacentuar(palabra));
		return OpPalabras.acentua(sb.toString());
	}

	public String une(String paramString, List<String> paramList,
			boolean paramBoolean) {
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
