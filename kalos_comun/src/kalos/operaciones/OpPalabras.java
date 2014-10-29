package kalos.operaciones;

import static kalos.enumeraciones.CompLetras.cAlfaAsperoCorta;
import static kalos.enumeraciones.CompLetras.cAlfaCorta;
import static kalos.enumeraciones.CompLetras.cAlfaLarga;
import static kalos.enumeraciones.CompLetras.cAlfaSuaveCorta;
import static kalos.enumeraciones.CompLetras.cAlfaSubscripta;
import static kalos.enumeraciones.CompLetras.cBeta;
import static kalos.enumeraciones.CompLetras.cDelta;
import static kalos.enumeraciones.CompLetras.cEpsilon;
import static kalos.enumeraciones.CompLetras.cEpsilonAspero;
import static kalos.enumeraciones.CompLetras.cEpsilonSuave;
import static kalos.enumeraciones.CompLetras.cEta;
import static kalos.enumeraciones.CompLetras.cEtaAspero;
import static kalos.enumeraciones.CompLetras.cEtaSuave;
import static kalos.enumeraciones.CompLetras.cEtaSubscripta;
import static kalos.enumeraciones.CompLetras.cEtaSubscriptaAspero;
import static kalos.enumeraciones.CompLetras.cEtaSubscriptaSuave;
import static kalos.enumeraciones.CompLetras.cFi;
import static kalos.enumeraciones.CompLetras.cGamma;
import static kalos.enumeraciones.CompLetras.cIotaAsperoCorta;
import static kalos.enumeraciones.CompLetras.cIotaCorta;
import static kalos.enumeraciones.CompLetras.cIotaSuaveCorta;
import static kalos.enumeraciones.CompLetras.cJi;
import static kalos.enumeraciones.CompLetras.cKappa;
import static kalos.enumeraciones.CompLetras.cLambda;
import static kalos.enumeraciones.CompLetras.cMu;
import static kalos.enumeraciones.CompLetras.cNu;
import static kalos.enumeraciones.CompLetras.cOmega;
import static kalos.enumeraciones.CompLetras.cOmegaAspero;
import static kalos.enumeraciones.CompLetras.cOmegaSuave;
import static kalos.enumeraciones.CompLetras.cOmegaSubscripta;
import static kalos.enumeraciones.CompLetras.cOmegaSubscriptaAspero;
import static kalos.enumeraciones.CompLetras.cOmegaSubscriptaSuave;
import static kalos.enumeraciones.CompLetras.cOmicron;
import static kalos.enumeraciones.CompLetras.cOmicronAspero;
import static kalos.enumeraciones.CompLetras.cOmicronSuave;
import static kalos.enumeraciones.CompLetras.cPi;
import static kalos.enumeraciones.CompLetras.cRho;
import static kalos.enumeraciones.CompLetras.cRhoAspero;
import static kalos.enumeraciones.CompLetras.cSigma;
import static kalos.enumeraciones.CompLetras.cSigmaFinal;
import static kalos.enumeraciones.CompLetras.cTau;
import static kalos.enumeraciones.CompLetras.cTheta;
import static kalos.enumeraciones.CompLetras.cUpsilonAsperoCorta;
import static kalos.enumeraciones.CompLetras.cUpsilonCorta;
import static kalos.enumeraciones.CompLetras.cUpsilonSuaveCorta;
import static kalos.enumeraciones.CompLetras.cXi;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kalos.enumeraciones.Acento;
import kalos.enumeraciones.ArticulacionConsonante;
import kalos.enumeraciones.Aumento;
import kalos.enumeraciones.Beta;
import kalos.enumeraciones.CaracterLiq;
import kalos.enumeraciones.CompLetras;
import kalos.enumeraciones.Contraccion;
import kalos.enumeraciones.Espiritu;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.TipoLetraBasico;
import kalos.enumeraciones.uLetras;
import kalos.fonts.CarPos;
import kalos.fonts.CaracterGriego;
import kalos.fonts.CaracterGriegoFactory;
import kalos.fonts.ExcepcionTransformacion;
import kalos.fonts.TipoLetra;
import kalos.operaciones.excepciones.ExcepcionLetra;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title: Kalos
 * </p>
 * <p>
 * Description: Greek verb conjugation and research tool
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class OpPalabras {

//	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OpPalabras.class.getName());

	public static String aumenta(String palabra, Aumento tipo) {
		if (tipo.equals(Aumento.Ninguno))
			return palabra;
		StringBuffer sBuff = new StringBuffer(palabra);
		aumentaInterna(sBuff, tipo);
		return sBuff.toString();
	}

	/**
	 * hay tres "acentúa" que pueden ser llamados por esta función: acentuar así
	 * nomás, acentuar en una posi- ciónespecífica o acentuar en una posición
	 *específica con acento agudo (si está el tercer parámetro, se entiende que
	 * está en true (circunflejo=false) y que necesita un agudo, ya que el
	 * circunflejo lo intenta por default
	 */
	public static String contraeGenerica(String cad1, String cad2, Contraccion contraccion, int posicion,
			Acento tipoAcento) {
		switch (contraccion) {
		case suma:
			return cad1 + cad2;
		case sumaAcentuada:
			return OpPalabras.acentua(cad1 + cad2, posicion, tipoAcento);
		case consonantica: {
			String[] resultado = OpPalabras.contraeConsonante(cad1, cad2, posicion, tipoAcento);
			if (resultado != null)
				return resultado[2];
			else
				return null;
		}
		case vocalica:
		case jonica:
		case monosilabosEw: {
			String[] resultado = OpPalabras.contraeVocal(cad1, cad2, contraccion, posicion, tipoAcento, true);
			if (resultado != null)
				return resultado[2];
			else
				return null;
		}
		case comePrimera:
			char primera = cad2.charAt(0);
			if (primera == CompLetras.cKappa || primera == CompLetras.cSigma || primera == CompLetras.cTheta)
				cad2 = cad2.substring(1);
			return OpPalabras.acentua(cad1.concat(cad2), posicion, tipoAcento);
		default:
			throw new RuntimeException("Contracción desconocida");
		}
	}

	/**
	 * Acentúa una palabra forzando la posición acento en el mismo lugar que
	 * otra palabra de referencia, y con el tipo de acento dejado a las normas
	 * naturales.
	 * 
	 * @param referencia
	 * @param aAcentuar
	 * @return
	 */
	public static String acentuarMismaPosicion(String referencia, String aAcentuar) {
		AnalisisAcento aaOrig = AnalisisAcento.getAnalisisAcento(referencia);
		return OpPalabras.acentua(aAcentuar, aaOrig.actuales.silabaB1, Acento.Ninguno);
	}

	/**
	 * el mismo método para acentuar en la misma posición, pero diciendo qué
	 * caché de análisis de acentos se utilza
	 * 
	 * @param cacheAA
	 * @param referencia
	 * @param aAcentuar
	 * @return
	 */
	public static String acentuarMismaPosicion(AACacheable cacheAA, String referencia, String aAcentuar) {
		AnalisisAcento aaOrig = cacheAA.getAnalisisAcento(referencia);
		return OpPalabras.acentua(aAcentuar, aaOrig.actuales.silabaB1, Acento.Ninguno);
	}

	/**
	 * Acentúa una palabra forzando la misma posición y tipo de acento que otra
	 * palabra de referencia.
	 * 
	 * @param referencia
	 * @param aAcentuar
	 * @return
	 */
	public static String acentuarTodoIgual(String referencia, String aAcentuar) {
		AnalisisAcento aaOrig = AnalisisAcento.getAnalisisAcento(referencia);
		return OpPalabras.acentua(aAcentuar, aaOrig.actuales.silabaB1, aaOrig.actuales.tipoAcento);
	}

	/**
	 * Acentúa una palabra teniendo como referencia la posición del acento en
	 * otra. Usada, por ejemplo, para acentuar un adjetivo neutro que acabamos
	 * de reconstruir a partir del masculino. El neutro será acentuado tan atrás
	 * como se pueda, pero no más atrás que el masculino. Por ejemplo, a)kth/mwn
	 * es en masculino a)ktemon. Si bien el neutro acentuado naturalmente sería
	 * a)/ktemon, quiero que sea a)kte/mon.
	 * 
	 * @param referencia
	 *            la forma ya acentuada, que se usa como referencia
	 * @param destino
	 *            la forma a acentuar. Puede tener ya un acento residual, pero
	 *            será ignorado.
	 * @return
	 */
	public static String acentuarHastaMismaPosicion(String referencia, String destino) {
		String destinoD = OpPalabras.desacentuar(destino);

		AnalisisAcento aaRef = AnalisisAcento.getAnalisisAcento(referencia);
		int refActual = aaRef.actuales.silaba;

		AnalisisAcento aaDest = AnalisisAcento.getAnalisisAcento(destinoD);
		int refDestino = aaDest.sugeridos.silaba;

		int silabaResultado = Math.max(refActual, refDestino);

		return OpPalabras.acentua(destino, silabaResultado, Acento.Ninguno);
	}

	/**
	 * si la palabra de referencia tiene acento natural, acentúa naturalmente la
	 * palabra nueva. Si el acento no es natural, acentúa la palabra nueva en la
	 * misma posición.
	 * 
	 * @param referencia
	 *            la palabra de referencia, acentuada
	 * @param nueva
	 *            la palabra a acentuar, sin acento
	 * @return la palabra a acentuar, acentuada.
	 */
	public static String acentoNaturalOIgual(String referencia, String nueva) {
		AnalisisAcento aaRef = AnalisisAcento.getAnalisisAcento(referencia);
		int refActual = aaRef.actuales.silaba;
		int refSugerido = aaRef.sugeridos.silaba;

		if (refActual == refSugerido) {
			return OpPalabras.acentua(nueva);
		} else {
			return OpPalabras.acentua(nueva, refActual);
		}
	}

	/**
	 * devuelve un array con las palabras originales más versiones sin -nu de
	 * palabras terminadas en -in o -en
	 * 
	 * @param completas
	 * @return
	 */
	public static String[] conySinNu(String palabra) {
		List<String> lsaAux = new ArrayList<String>();
		lsaAux.add(palabra);
		String en = OpPalabras.strBetaACompleto("EN");
		String in = OpPalabras.strBetaACompleto("IN");
		String neutra = OpPalabras.neutraliza(palabra);
		if (neutra.endsWith(en) || neutra.endsWith(in)) {
			String nueva = OpPalabras.comeFinal(palabra, 1);
			lsaAux.add(nueva);
		}
		return (String[]) lsaAux.toArray(new String[] {});
	}

	/**
	 * determina si una palabra (en Unicode) tiene caracteres compuestos
	 * (sobreescritos, iota)
	 * 
	 * @param textoUnicode
	 * @return
	 */
	public static boolean tieneCompuestos(String textoUnicode) {
		for (int i = 0; i < textoUnicode.length(); i++) {
			char car = textoUnicode.charAt(i);
			if (OpLetras.esCompuesto(car))
				return true;
		}
		return false;
	}

	/**
	 * Determina si una palabra (en unicode) tiene alguna información de
	 * larga-corta. ésta puede ser letras con makron, vrachy, o dichos
	 * caracateres por separado para componer
	 * 
	 * @param palabraUnicode
	 * @return
	 */
	public static boolean tieneInformacionLargaCorta(String palabraUnicode) {
		for (int i = 0; i < palabraUnicode.length(); i++) {
			char caracter = palabraUnicode.charAt(i);
			if (OpLetras.tieneInformacionLargaCorta(caracter))
				return true;
		}
		return false;
	}

	/**
	 * toma una palabra de unicode (que puede tener compuestos) y la convierte
	 * en una palabra completa normal (sin compuestos)
	 * 
	 * @param cadena
	 * @return
	 */
	public static String strUnicodeCompuestoANormal(String cadena) throws ExcepcionCaracterNoEncontradoEnPalabra {
		int contador = 0;
		try {
			if (!tieneCompuestos(cadena))
				return cadena;
			StringBuffer sb = new StringBuffer(cadena);

			while (contador < sb.length() - 1) {
				char normal = sb.charAt(contador);
				char posibleCompuesto = sb.charAt(contador + 1);
				char resultado = OpLetras.compone(normal, posibleCompuesto);
				if (resultado != normal) {
					sb.setCharAt(contador, OpLetras.compone(normal, posibleCompuesto));
					sb.deleteCharAt(contador + 1);
				}
				contador++;
			}
			return sb.toString();
		} catch (ExcepcionCaracterNoEncontrado ex) {
			throw new ExcepcionCaracterNoEncontradoEnPalabra(ex.getCaracter(), contador);
		}
	}

	public static AnalisisAcento getAnalisisAcento(String palabra) {
		return AnalisisAcento.getAnalisisAcento(palabra);
	}

	public static String strCompletoABeta(String palabra) {
		return Unicode.strCompletoABeta(palabra);
	}

	public static String dumpCadenaCompleta(String palabra) {
		return Unicode.dumpCadenaCompleta(palabra);
	}

	public static String cadenaCompletaAString(String palabra) {
		return Unicode.dumpCadenaCompleta(palabra);
	}

	/**
	 * 
	 * @param silaba
	 *            puede usarse para una palabra, pero presupone que tiene un
	 *            solo acento
	 * @return un array de ints. El primer elemento es el tipo de acento, el
	 *         segundo, el subíndice de la letra acentuada
	 * 
	 */
	public static Object[] contieneAcento(String silaba) {
		int indiceAcentuada = 0;
		Acento tipoAcento = Acento.Ninguno;
		for (int i = 0; i < silaba.length(); i++) {
			CarPos cpAux = CarPos.getCarPos(silaba, i);
			if (cpAux.tieneAcentoAgudo()) {
				indiceAcentuada = i;
				tipoAcento = Acento.Agudo;
				break;
			} else if (cpAux.tieneAcentoCircunflejo()) {
				indiceAcentuada = i;
				tipoAcento = Acento.Circunflejo;
				break;
			} else if (cpAux.tieneAcentoGrave()) {
				indiceAcentuada = i;
				tipoAcento = Acento.Grave;
				break;
			}
		}
		if (tipoAcento.equals(Acento.Ninguno))
			indiceAcentuada = -1;
		return new Object[] { tipoAcento, indiceAcentuada };

	}

	/**
	 * cambia una palabra de mi beta a el beta normal
	 * 
	 * @param palabra
	 * @return
	 */
	public static String strBetaKalosABeta(String palabra) {
		if (palabra == null)
			return null;
		String r1 = palabra.replaceAll("X", "\\[xi\\]");
		String r2 = r1.replaceAll("C", "\\[ji\\]");
		String r3 = r2.replaceAll("\\[xi\\]", "C");
		String r4 = r3.replaceAll("\\[ji\\]", "X");
		return r4;
	}

	/**
	 * cambia una palabra del beta perseus a mi beta
	 * 
	 * @param palabra
	 * @return
	 */
	public static String strBetaPerseusABetaKalos(String palabra) {
		if (palabra == null)
			return null;
		palabra = palabra.toUpperCase();
		// ji-xi
		String r1 = palabra.replaceAll("X", "\\[ji\\]");
		String r2 = r1.replaceAll("C", "\\[xi\\]");
		String r3 = r2.replaceAll("\\[xi\\]", "X");
		String resultado = r3.replaceAll("\\[ji\\]", "C");
		// sigma final
		if (resultado.endsWith("S"))
			resultado = OpPalabras.reemplazaFinal(resultado, 1, "J");
		// error de *)W en lugar de *W), que ocurre con las comenzadas en
		// mayúscula vocal
		if (resultado.startsWith("*)"))
			resultado = "*" + resultado.substring(2, 3) + ")" + resultado.substring(3);
		else if (resultado.startsWith("*("))
			resultado = "*" + resultado.substring(2, 3) + "(" + resultado.substring(3);
		// orden incorrecto del subscripto respecto del acento
		resultado = resultado.replaceAll("=\\|", "\\|=");
		resultado = resultado.replaceAll("/\\|", "\\|/");

		// quito ^, que es indicación de corta
		resultado = resultado.replaceAll("\\^", "");
		return resultado;
	}

	public static String strBetaACompleto(String palabra) {
		return Unicode.strBetaACompleto(palabra);
	}

	/**
	 * Facilita (desacentúa y desespiritúa) una palabra en código BETA
	 * 
	 * @param cadenaBeta
	 * @return
	 */
	public static String facilitaBeta(String cadenaBeta) {
		String cadena = strBetaACompleto(cadenaBeta);
		cadena = facilita(cadena);
		return strCompletoABeta(cadena);
	}

	/**
	 * Facilita (desacentúa y desespiritúa) una palabra
	 * 
	 * @param cadena
	 * @return
	 */
	public static String facilita(String cadena) {
		return desacentuar(desespirituar(cadena));
	}

	/**
	 * devuelve una cadena beta con todas las vocales pasadas a breve
	 * 
	 * @param cadenaBeta
	 *            una cadena en código beta
	 * @return
	 */
	public static String strAbreviaBeta(String cadenaBeta) {
		String completa = OpPalabras.strBetaACompleto(cadenaBeta);
		String completaAbreviada = strAbreviaCompleta(completa);
		return OpPalabras.strCompletoABeta(completaAbreviada);
	}

	/**
	 * devuelve una cadena completacon todas las vocales pasadas a corta
	 * 
	 * @param completa
	 *            una cadena completa
	 * @return
	 */
	public static String strAbreviaCompleta(String completa) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < completa.length(); i++) {
			CaracterGriego cag = CaracterGriegoFactory.produceCaracterGriego(completa.charAt(i));
			sb.append(cag.getVersionCorta());
		}
		String completaCorta = sb.toString();
		return completaCorta;
	}

	public static String strProgramaADatabase(String palabra) {
		return Unicode.strProgramaADatabase(palabra);
	}

	/**
	 * Convierte una cadena entrada en caracteres unicode a completa. Si hay
	 * signos sobreeescritos de larga corta (vrachy/makron) o dichos signos en
	 * las letras, los considera. Si hay un signo de larga/corta cualesquiera,
	 * no se explota por larga/corta Si no los hay, asume que la letra es
	 * siempre corta. Devuelve una única cadena completa.
	 * 
	 * @param palabra
	 *            la cadena en caracteres unicode
	 * @return
	 * @throws ExcepcionCaracterNoEncontradoEnPalabra
	 */
	public static String strUnicodeACompleto(String palabra) throws ExcepcionCaracterNoEncontradoEnPalabra {
		String completaSimple = Unicode.strUnicodeACompletoSimple(palabra);
		return completaSimple;
	}

	/**
	 * toma una cadena unicode (con caracteres indefinidos en ella y produce un
	 * array de cadenas Completas, con todas las combinaciones posibles de
	 * caracteres largos/cortos
	 */
	public static String[] explotaALargaBreve(String cadenaCompleta) {
		String sCompleta = cadenaCompleta;
		Silaba[] arrSil = Silabeo.silabea(sCompleta);

		// creo una lista con las posiciones de los caracteres indefinidos
		List<Integer> lstPosiciones = new ArrayList<Integer>();
		for (int i = 0; i < arrSil.length; i++) {
			if (Silaba.esAlargable(arrSil[i])) {
				lstPosiciones.add(new Integer(i));
			}
		}

		// si no hay ningún carácter indefinido, devuelvo un array conteniendo
		// lo mismo que entró (en completo) como único elemento
		if (lstPosiciones.isEmpty())
			return new String[] { sCompleta };

		// creo una lista de en donde pondré las cadenas de Resultado
		List<String> lstResult = new ArrayList<String>();

		// la lista lstPosiciones representa un byte, al que haremos pasar por
		// todos los
		// valores binarios posibles, con los true representando sílabas largas
		// y los
		// false representando sílabas cortas
		int iLongitud = lstPosiciones.size();
		int iValoresPosibles = (int) Math.pow(2, lstPosiciones.size());
		for (int i = 0; i < iValoresPosibles; i++) {
			List<Boolean> lstBits = Unicode.intAListaDeBits(i, iLongitud);// mide lo
																	// mismo que
																	// lstposiciones
			Silaba[] arrSilAux = Silabeo.cloneArray(arrSil); // new
																// StringBuffer(sCompleta);
																// //lo tengo
																// que
																// reconstruir
																// cada vez
			for (int e = 0; e < iLongitud; e++) {
				boolean bBit = ((Boolean) lstBits.get(e)).booleanValue();
				int iPos = ((Integer) lstPosiciones.get(e)).intValue();
				Unicode.setSilabaALargoOCorto(arrSilAux, iPos, bBit);
			}
			lstResult.add(Silabeo.recosntituyePalabra(arrSilAux));
		}
		return (String[]) (lstResult.toArray(new String[0]));
	}

	/**
	 * recibe una cadena en formato completo y devuelve todas las combinaciones
	 * factibles de vocal larga/vocal corta
	 * 
	 * @param cadenaCompleta
	 *            la cadena entrante (en formato completo)
	 * @return
	 */
	public static String[] explotaCompletaLargaBreve(String cadenaCompleta) {
		String sCompleta = cadenaCompleta;
		Silaba[] arrSil = Silabeo.silabea(sCompleta);

		// creo una lista con las posiciones de los caracteres indefinidos
		List<Integer> lstPosiciones = new ArrayList<Integer>();
		for (int i = 0; i < arrSil.length; i++) {
			if (Silaba.esAlargable(arrSil[i])) {
				lstPosiciones.add(new Integer(i));
			}
		}

		// si no hay ningún carácter indefinido, devuelvo un array conteniendo
		// lo mismo que entró (en completo) como único elemento
		if (lstPosiciones.isEmpty())
			return new String[] { sCompleta };

		// creo una lista de en donde pondré las cadenas de Resultado
		List<String> lstResult = new ArrayList<String>();

		// la lista lstPosiciones representa un byte, al que haremos pasar por
		// todos los valores binarios posibles, con los true representando sílabas largas
		// y los false representando sílabas cortas
		int iLongitud = lstPosiciones.size();
		int iValoresPosibles = (int) Math.pow(2, lstPosiciones.size());
		for (int i = 0; i < iValoresPosibles; i++) {
			List<Boolean> lstBits = Unicode.intAListaDeBits(i, iLongitud);
			//lstBits mide lo mismo que lstPosiciones
			Silaba[] arrSilAux = Silabeo.cloneArray(arrSil); 
			// lo tengo que reconstruir cada vez
			for (int e = 0; e < iLongitud; e++) {
				boolean bBit = ((Boolean) lstBits.get(e)).booleanValue();
				int iPos = ((Integer) lstPosiciones.get(e)).intValue();
				Unicode.setSilabaALargoOCorto(arrSilAux, iPos, bBit);
			}
			lstResult.add(Silabeo.recosntituyePalabra(arrSilAux));
		}
		return (String[]) (lstResult.toArray(new String[0]));
	}

	public static String strCompletoAUnicodeSinMakrones(String palabra) {
		return Unicode.strCompletoAUnicode(palabra, false);
	}

	public static String strCompletoAUnicode(String palabra) {
		return Unicode.strCompletoAUnicode(palabra, true);
	}

	public static String strBetaAUnicode(String cadenaBeta) {
		String cadenaCompleta = Unicode.strBetaACompleto(cadenaBeta);
		return Unicode.strCompletoAUnicode(cadenaCompleta, true);
	}

	/**
	 * Corrije la última letra a sigma final si es necesario (de una cadena
	 * completa)
	 * 
	 * @param palabra
	 * @return
	 */
	public static String sigmaFinal(String palabra) {
		int len = palabra.length();
		if (len == 0)
			return palabra;
		char ultimo = palabra.charAt(len - 1);
		StringBuffer res = new StringBuffer(palabra);
		if (ultimo == cSigma)
			res.setCharAt(len - 1, cSigmaFinal);
		return res.toString();
	}

	public static Silaba[] silabea(String palabra) {
		return Silabeo.silabea(palabra);
	}

	static void aumentaInterna(StringBuffer cadena, Aumento tipo) {

		if (tipo.equals(Aumento.Ninguno))
			return;
		StringBuffer pal = cadena;
		desacentuarPalabraInterna(pal);

		int iLenReemplazo = 0;
		boolean deboRestaurar = false; // el espíritu
		CarPos car0 = CarPos.getCarPos(pal.toString(), 0);
		CarPos car1 = CarPos.getCarPos(pal.toString(), 1);

		char base0 = car0.getLetraBase();
		char base1 = 0;
		if (car1 != null) {
			base1 = car1.getLetraBase();
		}

		if (tipo.equals(Aumento.Normal)) {// aumento normal
			if (car0.esVocal()) {
				if (base0 == cOmega | base0 == cOmegaSubscripta | base0 == cEta | base0 == cEtaSubscripta)
					return;
				if (car1 != null) {
					if (base0 == cAlfaCorta && base1 == cIotaCorta) {
						pal.delete(0, 2);
						pal.insert(0, cEtaSubscripta);
						iLenReemplazo = 1;
						deboRestaurar = true;
					} else if (base0 == cEpsilon && base1 == cIotaCorta) {
						pal.delete(0, 2);
						pal.insert(0, cEtaSubscripta);
						iLenReemplazo = 1;
						deboRestaurar = true;
					} else if (base0 == cOmicron && base1 == cIotaCorta) {
						pal.delete(0, 2);
						pal.insert(0, cOmegaSubscripta);
						iLenReemplazo = 1;
						deboRestaurar = true;
					} else if (base0 == cAlfaCorta && base1 == cUpsilonCorta) {
						pal.delete(0, 2);
						pal.insert(0, cUpsilonCorta);
						pal.insert(0, cEta);
						iLenReemplazo = 2;
						deboRestaurar = true;
					} else if (base0 == cEpsilon && base1 == cUpsilonCorta) {
						pal.delete(0, 2);
						pal.insert(0, cUpsilonCorta);
						pal.insert(0, cEta);
						iLenReemplazo = 2;
						deboRestaurar = true;
					} else if (base0 == cOmicron && base1 == cUpsilonCorta) {
						pal.delete(0, 2);
						pal.insert(0, cUpsilonCorta);
						pal.insert(0, cOmicron);
						iLenReemplazo = 2;
						deboRestaurar = true;
					}
				}
				// si hasta acá no encontró nada (iLenReemplazo==0)
				// comienzo a contemplar letras solas
				if (iLenReemplazo == 0) {
					if (base0 == cAlfaCorta) {
						pal.delete(0, 1);
						pal.insert(0, cEta);
						iLenReemplazo = 1;
						deboRestaurar = true;
					} else if (base0 == cAlfaSubscripta) {
						pal.delete(0, 1);
						pal.insert(0, cEtaSubscripta);
						iLenReemplazo = 1;
						deboRestaurar = true;
					} else if (base0 == cEpsilon) {
						pal.delete(0, 1);
						pal.insert(0, cEta);
						iLenReemplazo = 1;
						deboRestaurar = true;
					} else if (base0 == cOmicron) {
						pal.delete(0, 1);
						pal.insert(0, cOmega);
						iLenReemplazo = 1;
						deboRestaurar = true;
					} else if (base0 == cIotaCorta) {
						pal.delete(0, 1);
						pal.insert(0, cIotaCorta);
						iLenReemplazo = 1;
						deboRestaurar = true;
					} else if (base0 == cUpsilonCorta) {
						pal.delete(0, 1);
						pal.insert(0, cUpsilonCorta);
						iLenReemplazo = 1;
						deboRestaurar = true;
					} else if (base0 == cEta) {
						// no hace nada
						iLenReemplazo = 1;
						deboRestaurar = false;
					} else {
						pal.insert(0, cEpsilonSuave);
						deboRestaurar = false;
					}
				}

				// si hice cualquier otra cosa que el agregado e epsilon,
				// restauro espíritu
				if (deboRestaurar) {
					int iPosEspiritu = iLenReemplazo - 1;
					char carAEspirituar = pal.charAt(iPosEspiritu);
					boolean esAspero = false, esSuave = false;
					if (car0.esAspero())
						esAspero = true;
					else if (car0.esSuave())
						esSuave = true;
					else if (car1 != null)
						if (car1.esAspero())
							esAspero = true;
						else if (car1.esSuave())
							esSuave = true;
					if (esAspero)
						pal.setCharAt(iPosEspiritu, OpLetras.espirituAsperoCaracter(carAEspirituar));
					else if (esSuave)
						pal.setCharAt(iPosEspiritu, OpLetras.espirituSuaveCaracter(carAEspirituar));
				}

			} else {// no es vocal
				if (car0.getCaracter() == cRho | car0.getCaracter() == cRhoAspero) {
					pal.delete(0, 1);
					pal.insert(0, cRho);
					pal.insert(0, cRho);
					pal.insert(0, cEpsilonSuave);
				} else
					pal.insert(0, cEpsilonSuave);
			}
		} else if (tipo.equals(Aumento.enEi)) { // aumento en -ei (para los que
												// empiezan con -eta)
			int posEspiritu = 0;
			if (base0 == cEpsilon) {
				pal.delete(0, 1);
				pal.insert(0, cIotaCorta);
				pal.insert(0, cEpsilon);
				posEspiritu = 1;
			} else if (base0 == cAlfaCorta) {
				pal.delete(0, 1);
				pal.insert(0, cAlfaLarga);
				pal.insert(0, cEpsilon);
				posEspiritu = 0;
			} else if (base0 == cIotaCorta) {
				pal.delete(0, 1);
				pal.insert(0, cIotaCorta);
				pal.insert(0, cEpsilon);
				posEspiritu = 0;
			}
			// restauro espíritu
			char carAEspirituar = pal.charAt(posEspiritu);
			if (car0.esAspero())
				pal.setCharAt(posEspiritu, OpLetras.espirituAsperoCaracter(carAEspirituar));
			else if (car0.esSuave())
				pal.setCharAt(posEspiritu, OpLetras.espirituSuaveCaracter(carAEspirituar));

		}
	}

	/**
	 * Transforma una palabra que puede contener espíritus, acentos, subscripts,
	 * diéresis e indicaciones de larga/breve en una palabra completamente
	 * desprovista de todo ello.
	 * 
	 * @param palabra
	 * @return
	 */
	public static String neutraliza(String palabra) {
		if (StringUtils.isEmpty(palabra))
			return palabra;
		StringBuffer sb = new StringBuffer(20);
		for (int i = 0; i < palabra.length(); i++) {
			CarPos cpAux = CarPos.getCarPos(palabra, i);
			sb.append(cpAux.getLetraBase());
		}
		return sb.toString();
	}

	/**
	 * Neutraliza (desacentúa, desespiritúa, saca largos y todo diacrítico) una
	 * palabra en código BETA
	 * 
	 * @param cadenaBeta
	 * @return
	 */
	public static String neutralizaBeta(String cadenaBeta) {
		String cadena = strBetaACompleto(cadenaBeta);
		cadena = neutraliza(cadena);
		return strCompletoABeta(cadena);
	}

	private static void desacentuarPalabraInterna(StringBuffer palabra) {
		int lp = palabra.length();
		String pal=palabra.toString();
		boolean esMayuscula=OpPalabras.esMayuscula(pal);
		if (esMayuscula){
			pal=OpPalabras.minusculiza(pal);
			palabra.delete(0, palabra.length());
			palabra.append(pal);
		}
		for (int i = 0; i < lp; i++) {
			CarPos cpAnt = CarPos.getCarPos(palabra.toString(), i - 1);
			CarPos cp = CarPos.getCarPos(palabra.toString(), i);
			//si lo que saqué es un acento circunflejo y justo esa letra es un diptongo, 
			//corro el riesgo de que quede la versión larga de esa letra si no hago nada.
			//Así que me aseguro de poner la versión corta si esa letra forma parte de un 
			//diptongo no AI ni OI
			if (cp.tieneAcentoCircunflejo()) { 
				if ( 
				cpAnt != null
						&& ((cpAnt.getLetraBase() != cAlfaCorta && cpAnt.getLetraBase() != cOmicron) || cp
								.getLetraBase() != cIotaCorta)
						&& Silabeo.esDiptongo(cpAnt.getLetraBase(), cp.getLetraBase()))
					palabra.setCharAt(i, OpLetras.acortaCaracter(OpLetras.desacentuarLetra(cp.getCaracter())));
				else
					palabra.setCharAt(i, OpLetras.desacentuarLetra(palabra.charAt(i)));
			} else
				palabra.setCharAt(i, OpLetras.desacentuarLetra(palabra.charAt(i)));

		}
		if (esMayuscula){
			pal=palabra.toString();
			pal=OpPalabras.mayusculiza(pal);
			palabra.delete(0, palabra.length());
			palabra.append(pal);
		}
	}

	public static void desAumenta(String palabra, List<DesTransformaciones> lstResultados, boolean deReduplicacion,
			boolean soloRegulares) {
		lstResultados.clear();
		boolean esMayuscula=OpPalabras.esMayuscula(palabra);
		if (esMayuscula){
			palabra=OpPalabras.minusculiza(palabra);
		}
		char cInicial = palabra.charAt(0);
		String sResto = palabra.substring(1);
		String res = null;
		switch (cInicial) {
		case cEtaAspero:
			res = new String(new char[] { cAlfaAsperoCorta }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			res = new String(new char[] { cEpsilonAspero }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			break;
		case cEtaSuave:
			res = new String(new char[] { cAlfaSuaveCorta }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			res = new String(new char[] { cEpsilonSuave }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			break;
		case cEtaSubscriptaAspero:
			res = new String(new char[] { cAlfaCorta, cIotaAsperoCorta }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			res = new String(new char[] { cEpsilon, cIotaAsperoCorta }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			break;
		case cEtaSubscriptaSuave:
			res = new String(new char[] { cAlfaCorta, cIotaSuaveCorta }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			res = new String(new char[] { cEpsilon, cIotaSuaveCorta }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			break;
		case cOmegaSuave:
			res = new String(new char[] { cOmicronSuave }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			break;
		case cOmegaAspero:
			res = new String(new char[] { cOmicronAspero }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			break;
		case cOmegaSubscriptaSuave:
			res = new String(new char[] { cOmicron, cIotaSuaveCorta }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			break;
		case cOmegaSubscriptaAspero:
			res = new String(new char[] { cOmicron, cIotaAsperoCorta }) + sResto;
			lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			break;
		}

		// ahora comparo las primeras dos letras (el aumento de au-eu en hu)
		char cSegunda;
		if (palabra.length() > 1) {
			cSegunda = palabra.charAt(1);
			sResto = palabra.substring(2);
			if (cInicial == cEta && cSegunda == cUpsilonSuaveCorta) {
				res = new String(new char[] { cAlfaCorta, cUpsilonSuaveCorta }) + sResto;
				lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
				res = new String(new char[] { cEpsilon, cUpsilonSuaveCorta }) + sResto;
				lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			} else if (cInicial == cEta && cSegunda == cUpsilonAsperoCorta) {
				res = new String(new char[] { cAlfaCorta, cUpsilonAsperoCorta }) + sResto;
				lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
				res = new String(new char[] { cEpsilon, cUpsilonAsperoCorta }) + sResto;
				lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));
			}

			// el aumento de r en err
			if (palabra.substring(0, 3).equals(OpPalabras.strBetaACompleto("E)RR"))) {
				res = palabra.substring(2);
				lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.Normal, false, true));

				// tipo de aumento estándar con cualquier otra consonante
			} else if (cInicial == cEpsilonSuave && TipoLetra.getTipoLetraBasico(cSegunda) != TipoLetraBasico.enuVocal) {
				boolean regular = true;
				CarPos segunda = new CarPos(cSegunda, -1);
				CarPos tercera = CarPos.getCarPos(palabra, 2);

				if (segunda.esDoble()) {
					regular = true;
				} else if (segunda.esVocal()) {
					regular = false;
				} 
				/*else if (deReduplicacion && tercera != null && tercera.esConsonante()
						//&& (!segunda.esMuda() || !tercera.esLiquida())
						) {
					// la reduplicación aún de debe hacer normalmente (no con
					// aumento) si las dos consonantes siguientes
					// son líquida-licuante
					regular = false;
					SUSPENDO ESTO
				}*/

				if (!soloRegulares || regular) {
					res = palabra.substring(1);
					lstResultados.add(new DesTransformaciones(palabra, palabra.substring(1), null, Aumento.Normal, false, regular));
				}
			}

			// el aumento en ei (irregular)
			else if (!soloRegulares && palabra.startsWith(OpPalabras.strBetaACompleto("EI)"))) {
				res = OpPalabras.strBetaACompleto("E)") + palabra.substring(2);
				lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.enEi, false, false));
			} else if (!soloRegulares && palabra.startsWith(OpPalabras.strBetaACompleto("EI("))) {
				res = OpPalabras.strBetaACompleto("E(") + palabra.substring(2);
				lstResultados.add(new DesTransformaciones(palabra, res, null, Aumento.enEi, false, false));
			}

		}

		// si viene de reduplicación, paso todos los valores de aumento a
		// reduplicación
		if (deReduplicacion) {
			for (int i = 0; i < lstResultados.size(); i++) {
				DesTransformaciones dest = (DesTransformaciones) lstResultados.get(i);
				dest.setDesReduplicada(dest.getDesAumentada());
				dest.setDesAumentada(null);
				dest.setReduplicacion(dest.getAumento() == Aumento.Normal); // una
																			// reduplicación
																			// regular
																			// (1)
																			// proviene
																			// de
																			// un
																			// aumento
																			// regular
																			// (-1)
				dest.setAumento(Aumento.Ninguno);
			}
		}
		
		//revierto minúsculas
		if (esMayuscula){
			for (DesTransformaciones des: lstResultados){
				String desaumentada=des.getDesAumentada();
				if (desaumentada!=null){
					desaumentada=OpPalabras.mayusculiza(desaumentada);
					des.setDesAumentada(desaumentada);
				}
				String desreduplicada=des.getDesReduplicada();
				if (desreduplicada!=null){
					desreduplicada=OpPalabras.mayusculiza(desreduplicada);
					des.setDesReduplicada(desreduplicada);
				}
				
			}
		}

	}

	/**
	 * Decuelve la palabra con las x letras finales comidas (o una cadena vacía
	 * si x es mayor o igual a la longitud de la palabra).
	 * 
	 * @param cuantas
	 * @return
	 */
	public static String comeFinal(String palabra, int x) {
		if (x > palabra.length())
			return "";
		return palabra.substring(0, palabra.length() - x);
	}

	public static String desacentuar(String palabra) {
		StringBuffer sBuff = new StringBuffer(palabra);
		desacentuarPalabraInterna(sBuff);
		return sBuff.toString();
	}

	public static void desacentuar(StringBuffer palabra) {
		desacentuarPalabraInterna(palabra);
	}

	static int lugarAcentoAgudo(String pal) {
		StringBuffer palabra = new StringBuffer(pal);
		replace(palabra, "\u03b1\u03b9", "\u002b\u03b9", 0);
		int cantidad = 0;

		// este for se interrumpe justo en la posición en que la cuenta se
		// cumple
		int i = palabra.length();
		while (i > 0 & cantidad < 2) {
			i--;
			CarPos cpAux = CarPos.getCarPos(palabra.toString(), i);
			CarPos cpAux0 = CarPos.getCarPos(palabra.toString(), i - 1);
			if (cpAux.esLarga())
				cantidad = 2;
			else if (cpAux.esVocal()) {// corta o indeterminada
				cantidad++;
				if (cpAux.getLetraBase() == cIotaCorta) {
					if (cpAux0 != null)
						if (cpAux0.getLetraBase() == cAlfaCorta & cpAux0.esCorta()) {
							i--;
						}
				}
			}
		}

		// ahora acentúo en la primera vocal hacia la izquierda del
		// cumplimiento
		// (o si no puedo, en el cumplimiento mismo)

		// pero si la primera vocal a la derecha del cumplimiento forma
		// diptongo con la vocal del cumplimiento, no puedo acentuarla, porque
		// el acento va
		// siempre en la segunda, de modo que me tengo que correr aún más a la
		// izquierda
		boolean bCorriPorDiptongo = false;
		if (i < palabra.length() - 1)
			if (Silabeo.esDiptongo(palabra.charAt(i), palabra.charAt(i + 1))) {
				i--;
				bCorriPorDiptongo = true;
			}

		// si corrí por diptongo no es necesario restar una
		int iComienzo = i;
		if (!bCorriPorDiptongo)
			iComienzo--;
		for (int e = iComienzo; e >= 0; e--) {
			CarPos cpAux = CarPos.getCarPos(palabra.toString(), e);
			CarPos cpAux1 = CarPos.getCarPos(palabra.toString(), e + 1);
			if (cpAux.esVocal()) {
				// comprobación de diptongo con la letra siguiente (si lo hay,
				// hay que seguir retrocediendo)
				if (!Silabeo.esDiptongo(cpAux.getCaracter(), cpAux1.getCaracter()))
					return e;
			}
		}
		return i;
	}

	/**
	 * devuelve la primera letra significativa (sin contar los signos auxiliares
	 * como *) de una palabra en código beta
	 * 
	 * @param palabraBeta
	 * @return una cadena de 1 carácter beta
	 */
	public static String primeraLetraBeta(String palabraBeta) {
		if (palabraBeta == null || palabraBeta.equals(""))
			return "";
		String completa = OpPalabras.strBetaACompleto(palabraBeta);
		CaracterGriego cag = CaracterGriegoFactory.produceCaracterGriego(completa.charAt(0));
		char basica = cag.getLetraBase();
		String cadenaBasica = new String(new char[] { basica });
		cadenaBasica = OpPalabras.strCompletoABeta(cadenaBasica);
		return cadenaBasica;
	}

	public static String espirituPalabra(String palabra, Espiritu espiritu) {
		if (palabra.length() == 0)
			return palabra;
		if (!empiezaConVocal(palabra)) {
			return palabra;
		}
		CarPos letra0 = CarPos.getCarPos(palabra, 0);
		CarPos letra1 = CarPos.getCarPos(palabra, 1);
		// la primera no es vocal, así que con respecto a los espíritus no hay
		// nada que hacer
		int lugarEspiritu = -1;

		if (letra1 != null) {
			if ((Silabeo.esDiptongo(letra0.getCaracter(), letra1.getCaracter()))
					&& (letra1.getLetraBase() == CompLetras.cUpsilonCorta || letra1.getLetraBase() == CompLetras.cIotaCorta))
				lugarEspiritu = 1;
			else
				lugarEspiritu = 0;
		} else
			lugarEspiritu = 0;
		StringBuffer res = new StringBuffer(palabra);
		if (espiritu == Espiritu.Suave)
			res.setCharAt(lugarEspiritu, OpLetras.espirituSuaveCaracter(res.charAt(lugarEspiritu)));
		else if (espiritu == Espiritu.Aspero)
			res.setCharAt(lugarEspiritu, OpLetras.espirituAsperoCaracter(res.charAt(lugarEspiritu)));
		return res.toString();
	}

	// no importa si los dos primeros miembros son largos o cortos, porque
	// lo que venga se acorta con la función versionCorta
	static String arrContVoc[][] = { { "OI", "E", "OIE" },

	{ "A", "EIN", "A_N" }, { "A", "EI", "A|" }, { "A", "H", "A_" }, { "A", "H|", "A|" }, { "A", "E", "A_" },
			{ "A", "OI_", "W|" }, { "A", "OI_", "W|" }, { "A", "OI_", "W|" }, { "A", "OU", "W" }, { "A", "O", "W" },
			{ "A", "W", "W" }, { "A", "I", "AI" }, { "A", "A", "A_" }, { "A_", "U", "AU" },

			{ "E", "A", "H" }, { "E", "EI", "EI" }, { "E", "E", "EI" }, { "E", "H|", "H|" }, { "E", "H", "H" },
			{ "E", "I", "EI" }, { "E", "W", "W" }, { "E", "OU", "OU" }, { "E", "OI_", "OI_" }, { "E", "O", "OU" },
			{ "E", "U", "EU" },

			{ "I", "E", "IE" },

			{ "O", "I", "OI_" }, { "O", "EIN", "OUN" }, { "O", "EI", "OI_" }, { "O", "E", "OU" }, { "O", "H|", "OI_" },
			{ "O", "H", "W" }, { "O", "OI_", "OI_" }, { "O", "OU", "OU" }, { "O", "O", "OU" }, { "O", "W", "W" },

			{ "U", "E", "UE" }, { "U", "I", "UI" },

			{ "W", "E", "WE" },

	};

	static String arrContVocJonica[][] = { { "A", "EIN", "AN" }, { "A", "EI", "A|" }, { "A", "E", "A_" },
			{ "A", "O", "W" }, { "A", "OI_", "W|" }, { "A", "W", "W" },

			{ "E", "EI", "EI" }, { "E", "E", "EI" }, { "E", "H", "H" }, { "E", "H|", "H|" }, { "E", "W", "W" },
			{ "E", "OU", "EU" }, { "E", "OI_", "OI_" }, { "E", "O", "EU" },

			{ "O", "I", "OI_" }, { "O", "EIN", "OUN" }, { "O", "E", "EU" }, { "O", "H", "W" }, { "O", "EI", "OI_" },
			{ "O", "H|", "OI_" }, { "O", "OI_", "OI_" }, { "O", "O", "EU" }, { "O", "W", "W" }

	};

	/**
	 * indica si una palabra empieza con vocal
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean empiezaConVocal(String palabra) {
		CarPos primera = CarPos.getCarPos(palabra, 0);
		return primera.esVocal();
	}

	/**
	 * Averigua los posibles primeros términos de una contracción (vocálica
	 * normal o consonántica) dado el resultado, el segundo término y el tipo de
	 * contracción. No considera acentuaciones, sólo letras.
	 * 
	 * @param resultado
	 * @param segundoTermino
	 * @param contraccion
	 * @return
	 */
	public static String[] descontrae(String resultado, String segundoTermino, Contraccion contraccion) {
		String[][] arrCont = null;
		resultado = OpPalabras.desacentuar(resultado);
		segundoTermino = desacentuar(segundoTermino);
		List<String> primeros = new ArrayList<String>();
		if (contraccion == Contraccion.vocalica)
			arrCont = arrContVoc;
		else if (contraccion == Contraccion.consonantica)
			arrCont = arrContCons;
		for (int i = 0; i < arrCont.length; i++) {
			if (segundoTermino.startsWith(arrCont[i][1])) {
				StringBuffer segTerm = new StringBuffer(segundoTermino);
				segTerm.delete(0, arrCont[i][1].length());
				segTerm.insert(0, arrCont[i][2]);
				if (resultado.endsWith(segTerm.toString())) {
					StringBuffer primero = new StringBuffer(resultado);
					primero.delete(primero.length() - segTerm.length(), primero.length());
					primero.append(arrCont[i][0]);
					primeros.add(primero.toString());
				}
			}
		}
		return (String[]) primeros.toArray(new String[] {});
	}

	static String arrContVocEpsilon[][] = { { "E", "EI", "EI" }, { "E", "E", "EI" }, { "E", "H", "E" + "H" },
			{ "E", "H|", "E" + "H|" }, { "E", "W", "E" + "W" }, { "E", "OU", "E" + "OU" }, { "E", "OI_", "E" + "OI" },
			{ "E", "O", "E" + "O" } };

	private static void arrayACompleto(String[][] arrayContraccion) {
		for (int i = 0; i < arrayContraccion.length; i++)
			for (int e = 0; e < arrayContraccion[i].length; e++)
				arrayContraccion[i][e] = OpPalabras.strBetaACompleto(arrayContraccion[i][e]);

	}

	/**
	 * Método sobrecargado. Presupone que no se especifica el tipo de acento
	 * 
	 * @param pal1
	 * @param pal2
	 * @param contraccion
	 * @param posicion
	 * @return
	 */
	public static String[] contraeVocal(String pal1, String pal2, Contraccion contraccion, int posicion) {
		return contraeVocal(pal1, pal2, contraccion, posicion, Acento.Ninguno);
	}

	/**
	 * Función sobrecargada. Asume que la contracción vocálica es siempre
	 * permisiva (es decir, que tolera devolver la suma simple de palabras si no
	 * se encuentra contracción vocálica, en lugar de emitir un error. Se
	 * considera que el diptongo no se rompe
	 * 
	 * @param pal1
	 * @param pal2
	 * @param contraccion
	 * @param posicion
	 * @param acento
	 * 
	 * @return
	 */
	public static String[] contraeVocal(String pal1, String pal2, Contraccion contraccion, int posicion, Acento acento) {
		return contraeVocal(pal1, pal2, contraccion, posicion, acento, true);

	}

	/**
	 * 
	 * @param pal1
	 * @param pal2
	 * @param contraccion
	 * @param posicion
	 *            Si no importa poner cero
	 * @param permisiva
	 *            Permitir poner la suma en lugar de emitir un error si no se
	 *            encuentra contracción (por ejemplo, si alguna de las dos no es
	 *            vocal)
	 * @param rompeDiptongo
	 *            Si es verdadero y el resultado es un diptongo y la posición
	 *            cae en ese diptongo, entonces re-acentúa la palabra
	 *            rompióndolo
	 * 
	 * @return devuelve un array conteniendo en el elemento 0= la palabra1 con
	 *         la acentuación intermedia que hica para averiguar el acento en el
	 *         elemento 1= la palabra2 con la acentuación intermedia que hica
	 *         para averiguar el acento en el elemento 2= el resultado
	 */

	public static String[] contraeVocal(String pal1, String pal2, Contraccion contraccion, int posicion, Acento acento,
			boolean permisiva) {

		StringBuffer palabra1, palabra2;
		StringBuffer res = null;
		String[] arrRes = new String[3];

		// la contracción 1 es vocálica
		// la contracción 2 es consonántica
		// la contracción 3 es vocálica para monosílabos en éw
		// la contracción 4 es vocalica júnica (e + o = eu)
		// la contracción 5 hace en h en lugar de 'a' larga, para verbos como
		// za_w y cra_w
		String[][] arrContracciones;
		switch (contraccion) {
		case monosilabosEw:
			arrContracciones = arrContVocEpsilon;
			break;
		case jonica:
			arrContracciones = arrContVocJonica;
			break;
		default:
			arrContracciones = arrContVoc;
			break;

		}
		palabra1 = new StringBuffer(pal1);
		palabra2 = new StringBuffer(pal2);

		int comeDeSegunda = 0, comeDePrimera = 0;
		boolean encontroContraccion = false;
		for (int i = 0; i < arrContracciones.length; i++) {
			String segmento1 = desacentuar(right(palabra1.toString(), arrContracciones[i][0].length()));
			String segmento2 = desacentuar(left(palabra2.toString(), arrContracciones[i][1].length()));
			// para comparar con los arrays, aún necesito desespirituarlas y
			// sacarles los espíritus si los tienen
			String segmento1Des = OpPalabras.desespirituar(segmento1);
			String segmento2Des = OpPalabras.desespirituar(segmento2);
			if (segmento1Des.equals(arrContracciones[i][0]) && segmento2Des.equals(arrContracciones[i][1])) {
				String segmentoResultado = arrContracciones[i][2];
				encontroContraccion = true;
				Espiritu espiritu = OpPalabras.getEspiritu(segmento1); // restauro
																		// el
																		// espíritu
																		// si el
																		// primer
																		// segmento
																		// lo
																		// tenía
				if (espiritu != Espiritu.Ninguno)
					segmentoResultado = OpPalabras.espirituPalabra(segmentoResultado, espiritu);
				res = new StringBuffer(segmentoResultado);
				comeDePrimera = arrContracciones[i][0].length();
				comeDeSegunda = arrContracciones[i][1].length();
				break;
			}
		}
		if (!encontroContraccion) {
			if (!permisiva) {
				System.out.println("primera =" + OpPalabras.strCompletoABeta(pal1) + " segunda ="
						+ OpPalabras.strCompletoABeta(pal2));
				throw new RuntimeException("problemas con Contracci'onVocalica, tipo de contracci'on=" + contraccion);
			} else {
				arrRes[0] = pal1;
				arrRes[1] = pal2;
				arrRes[2] = acentua(pal1 + pal2, posicion, acento);
			}
		} else {
			if (acento == Acento.Ninguno) {
				arrRes = Silabeo.acentuaContraccionVocalica(palabra1.toString(), palabra2.toString(), res.toString(),
						comeDePrimera, comeDeSegunda, posicion);
			} else {
				arrRes[0] = pal1;
				arrRes[1] = pal2;
				String primera = palabra1.substring(0, palabra1.length() - comeDePrimera);
				String segunda = palabra2.substring(comeDeSegunda);
				arrRes[2] = OpPalabras.acentua(primera + res.toString() + segunda, posicion, acento);
			}
		}
		return arrRes;

	}

	public static String left(String cadena, int posiciones) {
		if (posiciones < 0)
			return "";
		if (posiciones >= cadena.length())
			return cadena;
		return cadena.substring(0, posiciones);
	}

	public static String right(String cadena, int posiciones) {
		if (posiciones <= 0)
			return "";
		if (posiciones >= cadena.length())
			return cadena;
		return cadena.substring(cadena.length() - posiciones);
	}

	/**
	 * Alarga temas de los verbos voc'alicos contractos
	 * 
	 * @param sTema
	 */
	public static void alargaTemaVC(StringBuffer sTema) {
		/**
		 * alarga los temas no infectivos de los verbos vocálicos contractos ,
		 * según la letra que preceda a la alfa
		 */
		CarPos cAntulte = CarPos.getCarPos(sTema.toString(), sTema.length() - 2);
		CarPos cUltimo = CarPos.getCarPos(sTema.toString(), sTema.length() - 1);
		if (cUltimo.getLetraBase() == cAlfaCorta) {
			if (cAntulte.getCaracter() == cRho | cAntulte.getLetraBase() == cIotaCorta
					| cAntulte.getLetraBase() == cEpsilon)
				sTema.setCharAt(sTema.length() - 1, cAlfaLarga); // alargo la
																	// alfa
			else if (cAntulte.getCaracter() == cLambda)
				;// no hago nada (sigue corta)
			else
				sTema.setCharAt(sTema.length() - 1, cEta);
		} else if (cUltimo.getLetraBase() == cEpsilon) {
			sTema.setCharAt(sTema.length() - 1, cEta);
		} else if (cUltimo.getLetraBase() == cOmicron) {
			sTema.setCharAt(sTema.length() - 1, cOmega);
		}
	}

	/**
	 * funci'on sobrecargada
	 * 
	 * @param tema
	 * @return
	 */
	public static String alargaTemaVC(String tema) {
		StringBuffer ret = new StringBuffer(tema);
		alargaTemaVC(ret);
		return ret.toString();

	}

	public static String[] contraeConsonante(String pal1, String pal2, int posicionAcento, Acento tipoAcento) {
		String resultadoContraccion = contraeConsonanteSinAcentuar(pal1, pal2);
		if (resultadoContraccion == null)
			return null;
		String[] arrRes = new String[3];
		Silaba[] arrPrimera = Silabeo.silabea(pal1);
		Silaba[] arrSegunda = Silabeo.silabea(pal2);

		// como necesito una posición y un tipo de acento para los segmentos,
		// aprovecho y los averiguo ahora
		AnalisisAcento aa = AnalisisAcento.getAnalisisAcento(resultadoContraccion);
		if (posicionAcento == 0 || tipoAcento.equals(Acento.Ninguno)) {
			if (resultadoContraccion.equals(""))
				return new String[] { "", "", "" };
			if (posicionAcento == 0) { // pueblo con un análisis independiente
				posicionAcento = aa.sugeridos.silaba;
				if (tipoAcento.equals(Acento.Ninguno))
					tipoAcento = aa.sugeridos.tipoAcento;
			}
		}
		resultadoContraccion = OpPalabras.acentua(resultadoContraccion, posicionAcento, tipoAcento);
		// acentúo uno de los segmentos, de acuerdo a dónde haya caído el acento
		AnalisisAcento aaAcentuado = AnalisisAcento.getAnalisisAcento(resultadoContraccion);

		if ((-1 * aaAcentuado.actuales.silaba) > arrSegunda.length) { // Estoy
																		// tratando
																		// de
																		// averiguar
																		// si va
																		// hacia
																		// atrás
																		// hasta
																		// la
																		// primera
																		// palabra
			arrRes[0] = Silabeo.acentua(concatenaSilabas(arrPrimera), aaAcentuado.actuales.silabaB1, tipoAcento);
			arrRes[1] = pal2;
		} else { // el acento cae en el segundo segmento
			arrRes[0] = pal1;
			arrRes[1] = Silabeo.acentua(concatenaSilabas(arrSegunda), aaAcentuado.actuales.silaba, tipoAcento);
		}
		arrRes[2] = resultadoContraccion;
		return arrRes;
	}

	/**
	 * Recibe una palabra ya contraida (vocálicamente, consonánticamente) y la
	 * compara con un array de contracciones que se recibe como parámetro.
	 * Devuelve aquellos miembros del array de contracciones que es posible que
	 * hayan generado la palabra. Si no encuentra nada apropiado en el array,
	 * devuelve null.
	 * 
	 * @param palabra
	 *            la palabra contraída que se quiere revisar
	 * @param palabra
	 *            el array de contracciones
	 * 
	 * @return tiene la forma, por ejemplo, {"temaComido", "M", "YAI"}, {..., ..., ... },
	 *         etc
	 * 
	 */
	public static String[][] cortaSegunArray(String[][] arrContracciones, String palabra) {
		String palabraNeutra = OpPalabras.desespirituar(OpPalabras.desacentuar(palabra));
		List<String[]> lstResultados = new ArrayList<String[]>();
		for (int i = 0; i < arrContracciones.length; i++) {
			String resCont = arrContracciones[i][2];
			// averiguo la última ocurrencia de resCont en la palabra
			int hay = palabraNeutra.lastIndexOf(resCont);
			if (hay != -1) {
				lstResultados
						.add(new String[] { arrContracciones[i][0], arrContracciones[i][1], palabra.substring(hay) });
			}
		}
		return (String[][]) lstResultados.toArray(new String[][] {});
	}

	/**
	 * Simplemente devuelve la concatenación de las cadenas de un array de
	 * sílabas
	 * 
	 * @param silabas
	 * @return
	 */
	private static String concatenaSilabas(Silaba[] silabas) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < silabas.length; i++)
			sb.append(silabas[i].getCadena());
		return sb.toString();
	}

	/**
	 * Efectúa la contracción consonántica basándose en comparaciones con el
	 * array arrContCons. Devuelve el resultado producir acentuación o alterar
	 * la existente.
	 * 
	 * @param palabra1
	 * @param palabra2
	 * @return
	 */
	public static String contraeConsonanteSinAcentuar(String palabra1, String palabra2) {
		String[][] arrContracciones = arrContCons;
		int comeDeSegunda = 0, comeDePrimera = 0;
		StringBuffer res = null;
		// precaución de muda más nt (en las 3pp del perfecto y pliscuamperfecto
		// medio)
		CarPos muda = CarPos.getCarPos(palabra1, palabra1.length() - 1);
		if (muda.esMuda() && palabra2.startsWith(OpPalabras.strBetaACompleto("NT")))
			return null;

		boolean encontroContraccion = false;
		for (int i = 0; i < arrContracciones.length; i++) {
			String segmento1 = desacentuar(right(palabra1.toString(), arrContracciones[i][0].length()));
			String segmento2 = desacentuar(left(palabra2.toString(), arrContracciones[i][1].length()));
			// para comparar con los arrays, aún necesito acortarlas y sacarles
			// los espíritus si los tienen
			String segmento1Neutro = OpPalabras.neutraliza(segmento1);
			String segmento2Neutro = OpPalabras.neutraliza(segmento2);
			if (segmento1Neutro.equals(arrContracciones[i][0]) && segmento2Neutro.equals(arrContracciones[i][1])) {
				String segmentoResultado = arrContracciones[i][2];
				encontroContraccion = true;
				Espiritu espiritu = OpPalabras.getEspiritu(segmento1); // restauro
																		// el
																		// espíritu
																		// si el
																		// primer
																		// segmento
																		// lo
																		// tenía
				if (espiritu != Espiritu.Ninguno)
					segmentoResultado = OpPalabras.espirituPalabra(segmentoResultado, espiritu);
				res = new StringBuffer(segmentoResultado);
				comeDePrimera = arrContracciones[i][0].length();
				comeDeSegunda = arrContracciones[i][1].length();
				break;
			}
		}

		StringBuffer palabraFinal = new StringBuffer();
		if (!encontroContraccion) {
			palabraFinal.append(palabra1);
			palabraFinal.append(palabra2);
			return palabraFinal.toString();
		} else {
			palabraFinal.append(palabra1.substring(0, palabra1.length() - comeDePrimera));
			palabraFinal.append(res);
			palabraFinal.append(palabra2.substring(comeDeSegunda));
		}
		return palabraFinal.toString();
	}

	public static String contraeConsonanteSinAcentuar2(String pal1, String pal2) {
		StringBuffer palabra1, palabra2, res = null;

		char ul1, au, u, s, p;

		palabra1 = new StringBuffer(pal1);
		palabra2 = new StringBuffer(pal2);

		// precauciones especiales para el perfecto y el pluscuamperfecto medios
		ul1 = palabra1.charAt(palabra1.length() - 1);

		// desinencias de 3pp en nt + consonante, se anula la palabra
		if (TipoLetra.getTipoLetraBasico(ul1) != TipoLetraBasico.enuVocal
				&& (palabra2.toString().equals("\u03bd\u03c4\u03b1\u03b9") || palabra2.toString().equals(
						"\u03bd\u03c4\u03bf")))
			return "";

		if (palabra1.length() == 0 || palabra2.length() == 0)
			return "";

		// averiguo la anteúltima y la última
		if (palabra1.length() > 1)
			au = palabra1.charAt(palabra1.length() - 2);
		else
			au = 0;
		u = palabra1.charAt(palabra1.length() - 1);

		// Rem rem si termina en doble "ss/tt" lo reemplazo por gutural y si
		// termina en pt por p sola
		String sAux = palabra1.toString();
		if (sAux.endsWith(OpPalabras.strBetaACompleto("SS")) || sAux.endsWith(OpPalabras.strBetaACompleto("TT"))) {
			palabra1.delete(palabra1.length() - 2, palabra1.length());
			palabra1.append(cGamma);
		} else if (sAux.endsWith(OpPalabras.strBetaACompleto("PT"))) {
			palabra1.delete(palabra1.length() - 2, palabra1.length());
			palabra1.append(cPi);
		}

		// vuelvo a averiguar la anteúltima y la última
		if (palabra1.length() > 1)
			au = palabra1.charAt(palabra1.length() - 2);
		else
			au = 0;
		u = palabra1.charAt(palabra1.length() - 1);

		if (palabra2.length() > 1)
			s = palabra2.charAt(1);
		else
			s = 0;
		p = palabra2.charAt(0);

		if ((u == cGamma || u == cJi || u == cKappa) && p == cMu)
			res = new StringBuffer(OpPalabras.strBetaACompleto("GM"));
		else if ((u == cGamma || u == cJi || u == cKappa) && (p == cSigma || p == cSigmaFinal))
			if (s != cTheta)
				res = new StringBuffer(OpPalabras.strBetaACompleto("X"));
			else
				res = new StringBuffer(OpPalabras.strBetaACompleto("C"));
		else if ((u == cGamma || u == cJi || u == cKappa) && p == cTau)
			res = new StringBuffer(OpPalabras.strBetaACompleto("KT"));
		else if ((u == cGamma || u == cJi || u == cKappa) && p == cTheta)
			res = new StringBuffer(OpPalabras.strBetaACompleto("CQ"));
		else if ((u == cGamma || u == cJi || u == cKappa) && p == cKappa)
			res = new StringBuffer(cJi);
		else if ((u == cDelta || u == cTheta || u == cTau) && p == cMu)
			res = new StringBuffer(OpPalabras.strBetaACompleto("SM"));
		else if ((u == cDelta || u == cTheta || u == cTau) && p == cSigma)
			res = new StringBuffer(OpPalabras.strBetaACompleto("S"));
		else if ((u == cDelta || u == cTheta || u == cTau) && p == cTau)
			res = new StringBuffer(OpPalabras.strBetaACompleto("ST"));
		else if ((u == cDelta || u == cTheta || u == cTau) && p == cTheta)
			res = new StringBuffer(OpPalabras.strBetaACompleto("SQ"));
		else if ((u == cDelta || u == cTheta || u == cTau) && p == cKappa)
			res = new StringBuffer(OpPalabras.strBetaACompleto("K"));
		else if ((u == cBeta || u == cPi || u == cFi) && p == cMu)
			if (TipoLetra.getTipoLetraBasico(au) == TipoLetraBasico.enuVocal)
				res = new StringBuffer(OpPalabras.strBetaACompleto("MM"));
			else
				res = new StringBuffer(OpPalabras.strBetaACompleto("M"));

		// este if fue para evitar la juntura de una consonante más dos mus,
		// como en los verbos que tienen el tema en consonante más labial
		// (pémpw, qúlpw, kárfw)
		else if ((u == cBeta || u == cPi || u == cFi) && (p == cSigma || p == cSigmaFinal))
			if (s != cTheta)
				res = new StringBuffer(OpPalabras.strBetaACompleto("Y"));
			else
				res = new StringBuffer(OpPalabras.strBetaACompleto("F"));

		// por ejemplo en los perfectos medios de pempw, kárfw, etc
		else if ((u == cBeta || u == cPi || u == cFi) && p == cTau)
			res = new StringBuffer(OpPalabras.strBetaACompleto("PT"));
		else if ((u == cBeta || u == cPi || u == cFi) && p == cTheta)
			res = new StringBuffer(OpPalabras.strBetaACompleto("FQ"));
		else if ((u == cBeta || u == cPi || u == cFi) && p == cKappa)
			res = new StringBuffer(OpPalabras.strBetaACompleto("F"));
		else if ((u == cLambda || u == cRho || u == cNu) && (p == cSigma && s == cTheta))
			res = new StringBuffer().append(u);
		else if (u == cXi && (p == cSigma || p == cKappa || p == cTheta || p == cMu)) {
			res = new StringBuffer(OpPalabras.strBetaACompleto("XH"));
			res.append(p);
		} else if (u == cNu && p == cMu)
			res = new StringBuffer(OpPalabras.strBetaACompleto("SM"));
		else if (u == cNu && p == cKappa)
			res = new StringBuffer(OpPalabras.strBetaACompleto("GK"));
		else if (u == cSigma && p == cSigma && s == cTheta)
			res = new StringBuffer().append(u);
		else if (u == cMu) {
			// para perfecto medio con final de tema en cMu
			if (p == cMu)
				res = new StringBuffer(OpPalabras.strBetaACompleto("MM"));
			// 'si no es ninguna de las primeras personas, reacciona como cNu
			else if (p == cSigma && s == cTheta)
				res = new StringBuffer(OpPalabras.strBetaACompleto("N"));
			else {
				System.out.println("ultima m, primera=" + p + "(hex " + Integer.toHexString(p) + ")");
				res = new StringBuffer(OpPalabras.strBetaACompleto("N"));
				res.append(p);
			}
		} else {
			// esta instancia significa que no se halló ninguna combinación de
			// consinantes digna de ser contraída. Por lo tanto, agregamos dos
			// consonantes ficticias en los extremos para que luego sean
			// quitadas
			palabra1.append('c');
			palabra2.insert(0, 'c');
			res = new StringBuffer(0);
		}

		// como las primera y última
		palabra1.delete(palabra1.length() - 1, palabra1.length());
		palabra2.delete(0, 1);
		return palabra1.toString() + res + palabra2.toString();
	}

	private static String[][] arrContCons = { { "G", "M", "GM" }, { "C", "M", "GM" }, { "K", "M", "GM" },

	{ "G", "SQ", "CQ" }, { "C", "SQ", "CQ" }, { "K", "SQ", "CQ" },

	{ "G", "S", "X" }, { "C", "S", "X" }, { "K", "S", "X" }, { "G", "J", "X" }, { "C", "J", "X" }, { "K", "J", "X" },

	{ "G", "T", "KT" }, { "C", "T", "KT" }, { "K", "T", "KT" },

	{ "G", "Q", "CQ" }, { "C", "Q", "CQ" }, { "K", "Q", "CQ" },

	{ "G", "SQ", "CQ" }, { "C", "SQ", "CQ" }, { "K", "SQ", "CQ" },

	{ "G", "K", "C" }, { "C", "K", "C" }, { "K", "K", "C" },

	{ "D", "M", "SM" }, { "Q", "M", "SM" }, { "T", "M", "SM" },

	{ "D", "S", "S" }, { "Q", "S", "S" }, { "T", "S", "S" },

	{ "D", "T", "ST" }, { "Q", "T", "ST" }, { "T", "T", "ST" },

	{ "D", "Q", "SQ" }, { "Q", "Q", "SQ" }, { "T", "Q", "SQ" },

	{ "D", "K", "K" }, { "Q", "K", "K" }, { "T", "K", "K" },

	{ "temaComido", "M", "MM" }, { "P", "M", "MM" }, { "F", "M", "MM" },

	{ "temaComido", "SQ", "FQ" }, { "P", "SQ", "FQ" }, { "F", "SQ", "FQ" },

	{ "temaComido", "S", "Y" }, { "P", "S", "Y" }, { "F", "S", "Y" },

	{ "temaComido", "J", "Y" }, { "P", "J", "Y" }, { "F", "J", "Y" },

	{ "temaComido", "T", "PT" }, { "P", "T", "PT" }, { "F", "T", "PT" },

	{ "temaComido", "Q", "FQ" }, { "P", "Q", "FQ" }, { "F", "Q", "FQ" },

	{ "temaComido", "K", "F" }, { "P", "K", "F" }, { "F", "K", "F" },

	// sigma-theta pierde la sigma después de lambda, rho o nu
			{ "L", "SQ", "LQ" }, { "R", "SQ", "RQ" }, { "N", "SQ", "NQ" },

			// la xi no contrae sino que agrega XH más la letra inicial del
			// segundo segmento
			{ "X", "S", "XHS" }, { "X", "K", "XHK" }, { "X", "Q", "XHQ" }, { "X", "M", "XHM" },

			{ "N", "M", "SM" }, { "N", "K", "GK" },

			{ "S", "SQ", "S" },

			{ "M", "M", "MM" },

			{ "M", "SQ", "N" } };

	public static String[][] getContraccionesConsonanticas() {
		return arrContCons;
	}

	static {
		arrayACompleto(arrContVoc);
		arrayACompleto(arrContVocEpsilon);
		arrayACompleto(arrContVocJonica);
		arrayACompleto(arrContCons);
	}

	static String alarVocAnt(String palabra) {
		StringBuffer Tema = new StringBuffer(palabra);
		CarPos ante, anpe;
		int largo;
		largo = Tema.length();

		// alarga la vocal anterior en el aoristo, de un tema ya debilitado
		ante = CarPos.getCarPos(palabra, largo - 2);
		anpe = CarPos.getCarPos(palabra, largo - 3);
		if (ante.getLetraBase() == cEpsilon)
			Tema.insert(largo - 2, cIotaCorta);
		else if (ante.getLetraBase() == cAlfaCorta)
			if (anpe.getLetraBase() != cIotaCorta & anpe.getLetraBase() != cRho)
				Tema.replace(largo - 2, largo - 1, new String(new char[] { cEta }));
		return Tema.toString();
	}

	public static String aumentaTema(String tema, String temaAumentado, kalos.enumeraciones.Modo modo, Tiempo tiempo) {
		String temaSin, temaCon;
		temaSin = tema;
		temaCon = temaAumentado;
		char letra;
		if (modo == Modo.Indicativo && (tiempo == Tiempo.Aoristo || tiempo == Tiempo.Imperfecto))
			return temaCon;
		else if (tiempo == Tiempo.Pluscuamperfecto) {
			// Rem evito que aumente los perfectos cuyo tema tiene aumento en
			// lugar de reduplicación
			// Rem se supone que s el perfecto comienza con vocal es porque ya
			// ha sido aumentado
			letra = temaSin.charAt(0);
			switch (TipoLetra.getTipoLetraBasico(letra)) {
			case TipoLetraBasico.enuVocal:
				return temaSin;
			default:
				return temaCon;
			}
		} else
			return temaSin;
	}

	/**
	 * Desaumenta la palabra, pero tratando de conservar el acento. Nótese que
	 * si no encuentra desaumentos adecuados, el resultado vuelve vacúo
	 * 
	 * @param palabra
	 * @param resultado
	 */
	public static void desaumentaConAcento(String palabra, List<DesTransformaciones> resultado, boolean soloRegulares) {
		AnalisisAcento aa = null;
		aa = AnalisisAcento.getAnalisisAcento(palabra);
		desAumenta(desacentuar(palabra), resultado, false, soloRegulares);
		for (int i = 0; i < resultado.size(); i++) {
			DesTransformaciones dest = (DesTransformaciones) resultado.get(i);
			String palabraDesaumentada = dest.getDesAumentada();
			aa = AnalisisAcento.getAnalisisAcento(palabra);
			if (aa.actuales.tipoAcento == Acento.Ninguno)
				continue;
			else {
				// si estaba acentuado justo en la sílaba reduplicada que se
				// quitó, necesito adelantar la
				// indicación de acento
				dest.setDesAumentada(OpPalabras.acentua(palabraDesaumentada, corrigePosicionComida(palabraDesaumentada,
						aa.actuales.silaba), aa.actuales.tipoAcento));
				resultado.set(i, dest);
			}
		}
	}

	/**
	 * devuelve verdadero si a la palabra se le puede aplicarespecíficamente
	 * des-reduplicación (como en PEP-); que se le pueda aplicar des-aumento no
	 * cuenta.
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean esEspecificamenteDesReduplicable(String palabra) {
		String desacentuada = OpPalabras.desacentuar(palabra);
		for (int i = 0; i < reduplicaciones.length; i++) {
			if (desacentuada.startsWith(reduplicaciones[i]))
				return true;
		}
		return false;
	}

	/**
	 * devuelve true si éste es un tema al cual se le pueden aplicar
	 * destransformaciones (des-aumento o des-reduplicacion)
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean esEspecificamenteDesAumentable(String palabra) {
		CarPos primera = CarPos.getCarPos(palabra, 0);
		CarPos segunda = CarPos.getCarPos(palabra, 1);

		if (!primera.esVocal())
			return false;
		if ((primera.getLetraBase() == cEta || primera.getLetraBase() == cOmega) && primera.tieneEspiritu()) 
			return true;
        else if (palabra.startsWith(OpPalabras.strBetaACompleto("HU)")))
                return true;
		else if (primera.getLetraBase() == cEpsilon && !primera.tieneEspiritu() && segunda != null
				&& segunda.getLetraBase() == cIotaCorta)
			return true; // desaumento irregular
		else if (primera.getLetraBase() == cEpsilon && primera.esSuave() && segunda != null && segunda.esConsonante())
			return true;
		else
			return false;
	}

	/**
	 * devuelve verdadero si la palabra se puede des-aumentar o des-reduplicar
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean esDesTransformable(String palabra) {
		return esEspecificamenteDesAumentable(palabra) || esEspecificamenteDesReduplicable(palabra);
	}

	/**
	 * Desreduplica la palabra, pero tratando de conservar el acento. Nótese que
	 * si no encuentra desresuplicaciones adecuadas, el resultado vuelve vacúo.
	 * 
	 * @param palabra
	 * @param resultado
	 */
	public static void desReduplicaConAcento(String palabra, List<DesTransformaciones> resultado, boolean soloRegulares) {
		AnalisisAcento aa = AnalisisAcento.getAnalisisAcento(palabra);
		desReduplica(desacentuar(palabra), resultado, soloRegulares);
		for (int i = 0; i < resultado.size(); i++) {
			DesTransformaciones dest = (DesTransformaciones) resultado.get(i);
			String res = dest.getDesReduplicada();
			if (aa.actuales.tipoAcento == Acento.Ninguno)
				continue;
			else {
				// si estaba acentuado justo en la sílaba reduplicada que se
				// quitó, necesito adelantar la
				// indicación de acento
				dest.setDesReduplicada(OpPalabras.acentua(res, corrigePosicionComida(res, aa.actuales.silaba),
						aa.actuales.tipoAcento));
				resultado.set(i, dest);
			}
		}
	}

	/**
	 * Una palabra "comida" (a la que se desaumentó o desreduplicó) que quiere
	 * conservar su acento, puede haber tenido el acento justo en la sílaba
	 * eliminada. Esta función se llama desde las funciones desaumentadoras o
	 * desreduplicadoras, y devuelve la nueva posición del acento, que va a ser
	 * la misma que la antigua o 1 (el principio de la palabra)
	 * 
	 * @param palabraComida
	 *            la palabra luego de ser desaumentada/desreduplicada
	 * @param antiguaPosicion
	 *            la posición original del acento, antes de ser
	 *            desaumentada/desreduplicada (se supone -1 based)
	 */
	private static int corrigePosicionComida(String palabraComida, int antiguaPosicion) {
		Silaba[] silabasDespues = OpPalabras.silabea(palabraComida);
		int posicion = antiguaPosicion;
		if (Math.abs(posicion) > silabasDespues.length)
			posicion = 1;
		return posicion;
	}

	public static String desespirituar(String palabra) {
		if (palabra.length() == 0)
			return palabra;
		StringBuffer sBuff = new StringBuffer(palabra);
		sBuff.setCharAt(0, OpLetras.desespirituarLetra(sBuff.charAt(0)));
		if (sBuff.length() > 1)
			sBuff.setCharAt(1, OpLetras.desespirituarLetra(sBuff.charAt(1)));
		return sBuff.toString();
	}

	/**
	 * Detecta si una palabra tiene espíritu
	 * 
	 * @param palabra
	 * @return 0 si no tiene, 1 si tiene espíritu suave, 2 si tiene áspero
	 */
	public static Espiritu getEspiritu(String palabra) {
		for (int i = 0; i < 2 && i < palabra.length(); i++) {
			CarPos cpAux = CarPos.getCarPos(palabra, i);
			if (cpAux.esSuave())
				return Espiritu.Suave;
			else if (cpAux.esAspero())
				return Espiritu.Aspero;
		}
		return Espiritu.Ninguno;
	}

	/**
	 * Detecta si una palabra contiene alguna letra con iota subscripta
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean tieneSubscripta(String palabra) {
		for (int i = 0; i < palabra.length(); i++) {
			CarPos cpAux = CarPos.getCarPos(palabra, i);
			if (cpAux.tieneSubscripta())
				return true;
		}
		return false;
	}

	/**
	 * Detecta si una palabra contiene alguna letra con diéresis
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean tieneDieresis(String palabra) {
		for (int i = 0; i < palabra.length(); i++) {
			CarPos cpAux = CarPos.getCarPos(palabra, i);
			if (cpAux.tieneDieresis())
				return true;
		}
		return false;
	}

	/**
	 * Detecta si la palabra tiene espíritu (áspero o suave)
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean tieneEspiritu(String palabra) {
		for (int i = 0; i < palabra.length(); i++) {
			CarPos cpAux = CarPos.getCarPos(palabra, i);
			if (cpAux.tieneEspiritu())
				return true;
		}
		return false;
	}

	private static String[] reduplicaciones = new String[] { OpPalabras.strBetaACompleto("BEB"),
			OpPalabras.strBetaACompleto("PEP"), OpPalabras.strBetaACompleto("PEF"), OpPalabras.strBetaACompleto("DED"),
			OpPalabras.strBetaACompleto("TET"), OpPalabras.strBetaACompleto("TEQ"), OpPalabras.strBetaACompleto("GEG"),
			OpPalabras.strBetaACompleto("KEK"), OpPalabras.strBetaACompleto("KEC"), OpPalabras.strBetaACompleto("MEM"),
			OpPalabras.strBetaACompleto("NEN"), OpPalabras.strBetaACompleto("LEL"), };

	public static void desReduplica(String palabra, List<DesTransformaciones> lstResultado, boolean soloRegulares) {
		lstResultado.clear();
		boolean encontre = false;
		for (int i = 0; i < reduplicaciones.length; i++) {
			if (palabra.startsWith(reduplicaciones[i])) {
				encontre = true;
				break;
			}
		}
		if (encontre) {
			lstResultado.add(new DesTransformaciones(palabra, null, palabra.substring(2), Aumento.Ninguno, true, true));
			return;
		} else
			// se trata de aumentos en realidad
			desAumenta(palabra, lstResultado, true, soloRegulares);
	}

	static void replace(StringBuffer sOriginal, String sCadenaABuscar, String sReemplazo, int comienzo) {
		int iRet;
		iRet = sOriginal.toString().indexOf(sCadenaABuscar, comienzo);
		StringBuffer sBuff = new StringBuffer();
		if (iRet != -1) {
			sBuff.append(sOriginal.substring(0, iRet));
			sBuff.append(sReemplazo);
			sBuff.append(sOriginal.substring(iRet + sCadenaABuscar.length()));
			sOriginal.setLength(0);
			sOriginal.append(sBuff);
		}
	}

	public static String reduplica(StringBuffer tema) {
		StringBuffer strResult = tema;
		char pl, sl = 0;

		if (strResult.length() == 0) {
			// "Se ha intentado reduplicar una palabra nula";
			strResult.setLength(0);
			return strResult.toString();
		}
		;
		pl = strResult.charAt(0);
		if (strResult.length() > 1)
			sl = strResult.charAt(1);

		// si la primera letra es vocal, "r" o consonante doble aumento en
		// lugar de reduplicar
		if (TipoLetra.getTipoLetraBasico(pl) == TipoLetraBasico.enuVocal || pl == cRho
				|| TipoLetra.getArticulacionConsonante(pl).equals(ArticulacionConsonante.Doble)) {
			// paso a char porque AumentaInetrna acepta char
			aumentaInterna(strResult, Aumento.Normal);
			return strResult.toString();
		}

		// si empieza por dos consonantes
		if (sl != 0 && TipoLetra.getTipoLetraBasico(sl) == TipoLetraBasico.enuConsonante)
			// y la primera no es licuadora o la segunda no es licuada
			if (TipoLetra.getCaracterLiq(pl) != CaracterLiq.enuLicuadora
					|| TipoLetra.getCaracterLiq(sl) != CaracterLiq.enuLicuada) {
				aumentaInterna(strResult, Aumento.Normal);
				return strResult.toString();
			}
		// empieza por una consonante seguida de una vocal
		// u otra consonante licuada (verdadera reduplicación)
		if (pl == cFi) {
			strResult.insert(0, "\u03C0\u03B5");
			return strResult.toString();
		} else if (pl == cTheta) {
			strResult.insert(0, "\u03C4\u03B5");
			return strResult.toString();
		} else if (pl == cJi) {
			strResult.insert(0, "\u03BA\u03B5");
			return strResult.toString();
		} else {
			strResult.insert(0, pl);
			strResult.insert(1, "\u03B5");
			return strResult.toString();
		}
	}

	public static String reduplica(String palabra) {
		StringBuffer sBuff = new StringBuffer(palabra);
		reduplica(sBuff);
		return sBuff.toString();
	}

	public static String reemplazaFinal(String palabraOriginal, int lugares, String pedazoNuevo) {
		int iLenOriginal = palabraOriginal.length();
		if (lugares > iLenOriginal) // error
			return "";
		return palabraOriginal.substring(0, iLenOriginal - lugares) + pedazoNuevo;
	}

	/**
	 * devuelve la voz media de un verbo, dada su forma canónica activa
	 * 
	 * @param verbo
	 *            en completa
	 * @return
	 */
	public static String canonicaAMedia(String verbo) {
		if (verbo.endsWith(OpPalabras.strBetaACompleto("A/W")))
			return reemplazaFinal(verbo, 2, OpPalabras.strBetaACompleto("A/OMAI"));
		else if (verbo.endsWith(OpPalabras.strBetaACompleto("E/W")))
			return reemplazaFinal(verbo, 2, OpPalabras.strBetaACompleto("E/OMAI"));
		else if (verbo.endsWith(OpPalabras.strBetaACompleto("O/W")))
			return reemplazaFinal(verbo, 2, OpPalabras.strBetaACompleto("O/OMAI"));
		else if (verbo.endsWith(OpPalabras.strBetaACompleto("W")))
			return reemplazaFinal(verbo, 1, OpPalabras.strBetaACompleto("OMAI"));
		else if (verbo.endsWith(OpPalabras.strBetaACompleto("NU_MI")))
			return reemplazaFinal(verbo, 4, OpPalabras.strBetaACompleto("NUMAI"));
		else
			return null;
	}

	/**
	 * devuelve la voz media de un verbo, dada su forma canónica activa
	 * 
	 * @param verbo
	 *            en completa
	 * @return
	 */
	public static String canonicaAActiva(String verbo) {
		if (verbo.endsWith(OpPalabras.strBetaACompleto("A/OMAI")))
			return reemplazaFinal(verbo, 2, OpPalabras.strBetaACompleto("A/W"));
		else if (verbo.endsWith(OpPalabras.strBetaACompleto("E/OMAI")))
			return reemplazaFinal(verbo, 2, OpPalabras.strBetaACompleto("E/W"));
		else if (verbo.endsWith(OpPalabras.strBetaACompleto("O/OMAI")))
			return reemplazaFinal(verbo, 2, OpPalabras.strBetaACompleto("O/W"));
		else if (verbo.endsWith(OpPalabras.strBetaACompleto("OMAI")))
			return reemplazaFinal(verbo, 1, OpPalabras.strBetaACompleto("W"));
		else if (verbo.endsWith(OpPalabras.strBetaACompleto("NUMAI")))
			return reemplazaFinal(verbo, 4, OpPalabras.strBetaACompleto("NU_MI"));
		else
			return null;
	}

	/**
	 * Devuelve una vocal con la versi'on larga de la 'ultima vocal, si 'esta la
	 * posee (A, I, U) Se llama de los participios neutros infectivos activos.
	 * Asume que el par'ametro no tiene acentos No se usa para los temas de los
	 * verbos voc'alicos contractos.
	 * 
	 * @param palabra
	 * @return
	 */
	public static String alargaUltimaVocal(String palabra) {
		// si la 'ultima s'ilaba ya es un diptongo, no hago nada
		Silaba[] arrSil = Silabeo.silabea(palabra);
		if (arrSil[arrSil.length - 1].esDiptongo())
			return palabra;
		CarPos ultima = CarPos.getCarPos(palabra, palabra.length() - 1);
		StringBuffer ret = new StringBuffer(palabra);
		ret.setCharAt(palabra.length() - 1, ultima.getVersionLarga());
		return ret.toString();
	}

	/**
	 * funci'on sobrecargada
	 * 
	 * @param tema
	 */
	public static void alargaUltimaVocal(StringBuffer tema) {
		String res = alargaUltimaVocal(tema.toString());
		tema.delete(0, tema.length());
		tema.append(res);
	}

	/**
	 * Devuelve una vocal con la versi'on corta de la 'ultima vocal, si 'esta la
	 * posee (A, I, U) Se llama de los participios futuros pasivos Asume que el
	 * parámetro no tiene acentos
	 * 
	 * @param palabra
	 * @return
	 */
	public static String acortaUltimaVocal(String palabra) {
		// si la última sílaba ya es un diptongo, no hago nada
		Silaba[] arrSil = Silabeo.silabea(palabra);
		if (arrSil[arrSil.length - 1].esDiptongo())
			return palabra;
		CarPos ultima = CarPos.getCarPos(palabra, palabra.length() - 1);
		StringBuffer ret = new StringBuffer(palabra);
		ret.setCharAt(palabra.length() - 1, ultima.getVersionCorta());
		return ret.toString();
	}

	/**
	 * Sobrecarga Acentúa sin intentar forzar acento ni posición, ni la ruptura
	 * de diptongo
	 */
	public static String acentua(String palabra) {
		return Silabeo.acentua(palabra, 0, Acento.Ninguno);
	}

	/**
	 * Devuelve la cantidad de acentos que tiene una palabra (se usa para
	 * validad una palabra ingresada, por si no tiene acentos o tiene más de
	 * uno)
	 * 
	 * @param palabra
	 * @return
	 */
	public static int getCantidadDeAcentos(String palabra) {
		Silaba[] silabas = Silabeo.silabea(palabra);
		int cantidadDeAcentos = 0;
		for (int i = 0; i < silabas.length; i++) {
			Acento ret = (Acento) OpPalabras.contieneAcento(silabas[i].getCadena())[0];
			if (!ret.equals(Acento.Ninguno)) {
				cantidadDeAcentos++;
			}
		}
		return cantidadDeAcentos;
	}

	/**
	 * Sobrecarga Acentúa forzando posición pero no acento ni ruptura de
	 * diptongo
	 */
	public static String acentua(String palabra, int posicion) {
		return Silabeo.acentua(palabra, posicion, Acento.Ninguno);
	}

	/**
	 * autoexplicativo
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean ultimaSilabaEsLarga(String palabra) {
		Silaba[] silGen = Silabeo.silabea(palabra);
		return silGen[silGen.length - 1].esLarga();
	}

	/**
	 * Dada una palabra en mayúscula o minúscula, devuelve un array de dos
	 * palabras, conteniendo la palabra en mayúscula y en minúscula
	 * 
	 * @param palabra
	 * @return
	 */
	public static String[] mayusculaMinuscula(String palabra) {
		String laOtra = null;
		if (esMayuscula(palabra))
			laOtra = minusculiza(palabra);
		else
			laOtra = mayusculiza(palabra);
		return new String[] { palabra, laOtra };
	}

	/**
	 * pone en mayúscula una palabra completa, que se supone en minúscula
	 * 
	 * @param palabra
	 * @return
	 */
	public static String mayusculiza(String palabra) {
	    String primera=palabra.substring(0, 1);
		String beta = strCompletoABeta(primera);
		beta = "*".concat(beta);
		return strBetaACompleto(beta).concat(palabra.substring(1));
	}

	/**
	 * pone en minúscula una palabra completa, que se supone en mayúscula
	 * 
	 * @param palabra
	 * @return
	 */
	public static String minusculiza(String palabra) {
	    String primera=palabra.substring(0, 1);
		String beta = strCompletoABeta(primera);
		beta = beta.substring(1); // quito el asterisco
		return strBetaACompleto(beta).concat(palabra.substring(1));
	}
	

	/**
	 * devuelve true si la palabra comienza con una letra mayúscula, false si es
	 * minúscula o si la cadena es vacía ("").
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean esMayuscula(String palabra) {
	    if (palabra.equals(""))
	        return false;
		CaracterGriego cag = CaracterGriegoFactory.produceCaracterGriego(palabra.charAt(0));
		return cag.esMayuscula();
	}

	/**
	 * autoexplicativo
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean ultimaSilabaDiptongo(String palabra) {
		Silaba[] silGen = Silabeo.silabea(palabra);
		return silGen[silGen.length - 1].esDiptongo();
	}

	private static class Unicode implements CompLetras {

		public static boolean m_bMostrarInterfazPobre = true;

		// private static Font fntGreek3Unicode=new Font("Athena",Font.PLAIN,
		// 15);
		private static Font fntLatina;

		private static Font fntGriega;

		public static void setFontLatina(Font font) {
			fntLatina = font;
		}

		public static void setFontGriega(Font font) {
			fntGriega = font;
		}

		public static Font getFontGriega() {
			return fntGriega;
		}

		public static Font getFontLatina() {
			return fntLatina;
		}

		public static String dumpCadenaCompleta(String cadena) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < cadena.length(); i++) {
				sb.append(Integer.toHexString(cadena.charAt(i)) + "\n");
				if (i < cadena.length() - 1)
					sb.append(", ");
				else
					sb.append("");
			}
			return sb.toString();
		}

		public static String strProgramaADatabase(String cadena) {
			/*
			 * String sForma=cadena; if (!m_bBaseDeDatosUnicode)
			 * sForma=strCompletoATransliterado(sForma); else
			 * sForma=strCompletoAUnicode(sForma); return sForma;
			 */
			return strCompletoABeta(cadena);
		}

		/**
		 * Solamente considera aquellos caracteres cuyo número difiere en
		 * uLetras respecto de CompLetras, es decir, las vocales para las cuales
		 * hace falta especificar si es largo o corto (alfa, iota, upsilon)
		 * 
		 * @param car
		 * @return
		 */
		public static char carCompletoAUnicode(char car) {

			switch (car) {
			case cAlfaCorta:
				return uLetras.cAlfa;
			case cAlfaAsperoCorta:
				return uLetras.cAlfaAspero;
			case cAlfaSuaveCorta:
				return uLetras.cAlfaSuave;
			case cAlfaAgudoCorta:
				return uLetras.cAlfaAgudo;
			case cAlfaAsperoAgudoCorta:
				return uLetras.cAlfaAsperoAgudo;
			case cAlfaSuaveAgudoCorta:
				return uLetras.cAlfaSuaveAgudo;
			case cAlfaGraveCorta:
				return uLetras.cAlfaGrave;
			case cAlfaAsperoGraveCorta:
				return uLetras.cAlfaAsperoGrave;
			case cAlfaSuaveGraveCorta:
				return uLetras.cAlfaSuaveGrave;

			case cAlfaLarga:
				return uLetras.cAlfa;
			case cAlfaAsperoLarga:
				return uLetras.cAlfaAspero;
			case cAlfaSuaveLarga:
				return uLetras.cAlfaSuave;
			case cAlfaAgudoLarga:
				return uLetras.cAlfaAgudo;
			case cAlfaAsperoAgudoLarga:
				return uLetras.cAlfaAsperoAgudo;
			case cAlfaSuaveAgudoLarga:
				return uLetras.cAlfaSuaveAgudo;
			case cAlfaGraveLarga:
				return uLetras.cAlfaGrave;
			case cAlfaAsperoGraveLarga:
				return uLetras.cAlfaAsperoGrave;
			case cAlfaSuaveGraveLarga:
				return uLetras.cAlfaSuave;

			case cIotaCorta:
				return uLetras.cIota;
			case cIotaAsperoCorta:
				return uLetras.cIotaAspero;
			case cIotaSuaveCorta:
				return uLetras.cIotaSuave;
			case cIotaAgudoCorta:
				return uLetras.cIotaAgudo;
			case cIotaAsperoAgudoCorta:
				return uLetras.cIotaAsperoAgudo;
			case cIotaSuaveAgudoCorta:
				return uLetras.cIotaSuaveAgudo;
			case cIotaGraveCorta:
				return uLetras.cIotaGrave;
			case cIotaAsperoGraveCorta:
				return uLetras.cIotaAsperoGrave;
			case cIotaSuaveGraveCorta:
				return uLetras.cIotaSuaveGrave;
			case cIotaDieresisCorta:
				return uLetras.cIotaDieresis;
			case cIotaDieresisAgudoCorta:
				return uLetras.cIotaDieresisAgudo;
			case cIotaDieresisGraveCorta:
				return uLetras.cIotaDieresisGrave;

			case cIotaLarga:
				return uLetras.cIota;
			case cIotaAsperoLarga:
				return uLetras.cIotaAspero;
			case cIotaSuaveLarga:
				return uLetras.cIotaSuave;
			case cIotaAgudoLarga:
				return uLetras.cIotaAgudo;
			case cIotaAsperoAgudoLarga:
				return uLetras.cIotaAsperoAgudo;
			case cIotaSuaveAgudoLarga:
				return uLetras.cIotaSuaveAgudo;
			case cIotaGraveLarga:
				return uLetras.cIotaGrave;
			case cIotaAsperoGraveLarga:
				return uLetras.cIotaAsperoGrave;
			case cIotaSuaveGraveLarga:
				return uLetras.cIotaSuaveGrave;
			case cIotaDieresisLarga:
				return uLetras.cIotaDieresis;
			case cIotaDieresisAgudoLarga:
				return uLetras.cIotaDieresisAgudo;
			case cIotaDieresisGraveLarga:
				return uLetras.cIotaDieresisGrave;

			case cUpsilonCorta:
				return uLetras.cUpsilon;
			case cUpsilonAsperoCorta:
				return uLetras.cUpsilonAspero;
			case cUpsilonSuaveCorta:
				return uLetras.cUpsilonSuave;
			case cUpsilonAgudoCorta:
				return uLetras.cUpsilonAgudo;
			case cUpsilonAsperoAgudoCorta:
				return uLetras.cUpsilonAsperoAgudo;
			case cUpsilonSuaveAgudoCorta:
				return uLetras.cUpsilonSuaveAgudo;
			case cUpsilonGraveCorta:
				return uLetras.cUpsilonGrave;
			case cUpsilonAsperoGraveCorta:
				return uLetras.cUpsilonAsperoGrave;
			case cUpsilonSuaveGraveCorta:
				return uLetras.cUpsilonSuaveGrave;
			case cUpsilonDieresisCorta:
				return uLetras.cUpsilonDieresis;
			case cUpsilonDieresisAgudoCorta:
				return uLetras.cUpsilonDieresisAgudo;
			case cUpsilonDieresisGraveCorta:
				return uLetras.cUpsilonDieresisGrave;

			case cUpsilonLarga:
				return uLetras.cUpsilon;
			case cUpsilonAsperoLarga:
				return uLetras.cUpsilonAspero;
			case cUpsilonSuaveLarga:
				return uLetras.cUpsilonSuave;
			case cUpsilonAgudoLarga:
				return uLetras.cUpsilonAgudo;
			case cUpsilonAsperoAgudoLarga:
				return uLetras.cUpsilonAsperoAgudo;
			case cUpsilonSuaveAgudoLarga:
				return uLetras.cUpsilonSuaveAgudo;
			case cUpsilonGraveLarga:
				return uLetras.cUpsilonGrave;
			case cUpsilonAsperoGraveLarga:
				return uLetras.cUpsilonAsperoGrave;
			case cUpsilonSuaveGraveLarga:
				return uLetras.cUpsilonSuaveGrave;
			case cUpsilonDieresisLarga:
				return uLetras.cUpsilonDieresis;
			case cUpsilonDieresisAgudoLarga:
				return uLetras.cUpsilonDieresisAgudo;
			case cUpsilonDieresisGraveLarga:
				return uLetras.cUpsilonDieresisGrave;

			// mayusculas
			case cAlfaCortaMayuscula:
				return uLetras.cAlfaMayuscula;
			case cAlfaAsperoCortaMayuscula:
				return uLetras.cAlfaAsperoMayuscula;
			case cAlfaSuaveCortaMayuscula:
				return uLetras.cAlfaSuaveMayuscula;
			case cAlfaAsperoAgudoCortaMayuscula:
				return uLetras.cAlfaAsperoAgudoMayuscula;
			case cAlfaSuaveAgudoCortaMayuscula:
				return uLetras.cAlfaSuaveAgudoMayuscula;

			case cAlfaAsperoLargaMayuscula:
				return uLetras.cAlfaAsperoMayuscula;
			case cAlfaSuaveLargaMayuscula:
				return uLetras.cAlfaSuaveMayuscula;
			case cAlfaAsperoAgudoLargaMayuscula:
				return uLetras.cAlfaAsperoAgudoMayuscula;
			case cAlfaSuaveAgudoLargaMayuscula:
				return uLetras.cAlfaSuaveAgudoMayuscula;

			case cIotaCortaMayuscula:
			case cIotaLargaMayuscula:
				return uLetras.cIotaMayuscula;

			case cIotaAsperoCortaMayuscula:
				return uLetras.cIotaAsperoMayuscula;
			case cIotaSuaveCortaMayuscula:
				return uLetras.cIotaSuaveMayuscula;
			case cIotaAsperoAgudoCortaMayuscula:
				return uLetras.cIotaAsperoAgudoMayuscula;
			case cIotaSuaveAgudoCortaMayuscula:
				return uLetras.cIotaSuaveAgudoMayuscula;

			case cIotaAsperoLargaMayuscula:
				return uLetras.cIotaAsperoMayuscula;
			case cIotaSuaveLargaMayuscula:
				return uLetras.cIotaSuaveMayuscula;
			case cIotaAsperoAgudoLargaMayuscula:
				return uLetras.cIotaAsperoAgudoMayuscula;
			case cIotaSuaveAgudoLargaMayuscula:
				return uLetras.cIotaSuaveAgudoMayuscula;

			case cUpsilonCortaMayuscula:
			case cUpsilonLargaMayuscula:
				return uLetras.cUpsilonMayuscula;

			case cUpsilonAsperoCortaMayuscula:
				return uLetras.cUpsilonAsperoMayuscula;
			case cUpsilonAsperoAgudoCortaMayuscula:
				return uLetras.cUpsilonAsperoAgudoMayuscula;

			case cUpsilonAsperoLargaMayuscula:
				return uLetras.cUpsilonAsperoMayuscula;
			case cUpsilonAsperoAgudoLargaMayuscula:
				return uLetras.cUpsilonAsperoAgudoMayuscula;
			default:
				return car;
			}
		}

		/**
		 * Dada una cadena unicode, va recorriendo sus caracteres y
		 * traducióndolos a comlpeto, considerando caracteres compuestos si
		 * éstos aparecen. Los caracteres sin información de larga/corta, los
		 * asume cortos. De este modo, el resultado es una cadena completo
		 * simple, no varias.
		 * 
		 * @param palabra
		 * @return
		 * @throws ExcepcionCaracterNoEncontradoEnPalabra
		 */
		public static String strUnicodeACompletoSimple(String palabra) throws ExcepcionCaracterNoEncontradoEnPalabra {
			StringBuffer sBuff = new StringBuffer();
			int iLen = palabra.length(), i = 0;
			while (i < iLen) {
				try {
					char puntero = palabra.charAt(i);
					char siguiente = 0;
					if (i + 1 <= iLen - 1)
						siguiente = palabra.charAt(i + 1);

					char completo;
					if (siguiente != 0 && OpLetrasUnicode.esCompuesto(siguiente)) {
						completo = OpLetras.compone(puntero, siguiente);
						i += 2;
					} else {
						completo = OpLetras.carUnicodeACompleto(puntero);
						i++;
					}
					sBuff.append(completo);
				} catch (ExcepcionCaracterNoEncontrado ex) {
					ex.printStackTrace();
					throw new ExcepcionCaracterNoEncontradoEnPalabra(palabra.charAt(i), i);
				}
			}
			return sBuff.toString();
		}

		public static String strCompletoAUnicode(String palabra, boolean conLarga) {
			StringBuffer sBuff = new StringBuffer();
			try {

				if (palabra == null)
					return null;
				int iLen = palabra.length();
				for (int i = 0; i < iLen; i++) {
					char completo = palabra.charAt(i);
					CaracterGriego cag = CaracterGriegoFactory.produceCaracterGriego(completo);
					sBuff.append(carCompletoAUnicode(palabra.charAt(i)));
					if (conLarga) {
						if (cag.esLarga() && cag.getVersionCorta() != cag.getVersionLarga()) {
							sBuff.append(uLetras.cLarga);
						}
					}
				}
			} catch (ExcepcionLetra el) {
				throw new ExcepcionTransformacion(ExcepcionTransformacion.COMPLETO_A_UNICODE, palabra, el);
			}
			return sBuff.toString();
		}

		public static String generaEscapes(String cadena) {
			String strAux = cadena;
			StringBuffer sBuff2 = new StringBuffer();
			for (int i = 0; i < strAux.length(); i++) {
				char cAux = strAux.charAt(i);
				String sHexString = Integer.toHexString((int) cAux);
				while (sHexString.length() < 4)
					sHexString = "0" + sHexString;
				sHexString = "\\u" + sHexString;
				sBuff2.append(sHexString);
			}
			return sBuff2.toString();
		}

		/**
		 * toma una cadena en unicode (con caracteres indefinidos en ella y
		 * produce un array de cadenas Completas, con todas las combinaciones
		 * posibles de caracteres largos/cortos
		 */
		public static String[] strUnicodeACompletoLargaYBreve(String cadenaUnicode)
				throws ExcepcionCaracterNoEncontradoEnPalabra {
			// paso la cadena a CompletoIndefinido
			String sCompleta = strUnicodeACompletoSimple(cadenaUnicode);
			Silaba[] arrSil = Silabeo.silabea(sCompleta);

			// creo una lista con las posiciones de los caracteres indefinidos
			List<Integer> lstPosiciones = new ArrayList<Integer>();
			for (int i = 0; i < arrSil.length; i++) {
				if (Silaba.esAlargable(arrSil[i])) {
					lstPosiciones.add(new Integer(i));
				}
			}

			// si no hay ningún carácter indefinido, devuelvo un array
			// conteniendo
			// lo mismo que entró (en completo) como único elemento
			if (lstPosiciones.isEmpty())
				return new String[] { sCompleta };

			// creo una lista de en donde pondré las cadenas de Resultado
			List<String> lstResult = new ArrayList<String>();

			// la lista lstPosiciones representa un byte, al que haremos pasar
			// por todos los
			// valores binarios posibles, con los true representando sílabas
			// largas y los
			// false representando sílabas cortas
			int iLongitud = lstPosiciones.size();
			int iValoresPosibles = (int) Math.pow(2, lstPosiciones.size());
			for (int i = 0; i < iValoresPosibles; i++) {
				List<Boolean> lstBits = intAListaDeBits(i, iLongitud);// mide lo mismo
																// que
																// lstposiciones
				Silaba[] arrSilAux = Silabeo.cloneArray(arrSil); // new
																	// StringBuffer(sCompleta);
																	// //lo
																	// tengo que
																	// reconstruir
																	// cada vez
				for (int e = 0; e < iLongitud; e++) {
					boolean bBit = ((Boolean) lstBits.get(e)).booleanValue();
					int iPos = ((Integer) lstPosiciones.get(e)).intValue();
					setSilabaALargoOCorto(arrSilAux, iPos, bBit);
				}
				lstResult.add(Silabeo.recosntituyePalabra(arrSilAux));
			}
			return (String[]) (lstResult.toArray(new String[0]));
		}

		/**
		 * Crea una lista de Booleans (de tamaño longitud) que representa a
		 * valorEntero en binario
		 */
		public static List<Boolean> intAListaDeBits(int valorEntero, int longitud) {
			// utilizo el método de los restos
			int resultado, resto, valor; // valor es el que va disminuyendo
			List<Boolean> lstBits = new ArrayList<Boolean>();
			valor = valorEntero;
			while (valor >= 2) {
				resultado = valor / 2;
				resto = valor % 2;
				lstBits.add(0, new Boolean(resto == 1 ? true : false));
				valor = resultado;
			}
			;
			lstBits.add(0, new Boolean(valor == 1 ? true : false));
			// completo con ceros a la izquierda si hace falta
			while (lstBits.size() < longitud)
				lstBits.add(0, new Boolean(false));
			return lstBits;
		}

		private static void setSilabaALargoOCorto(Silaba[] arrSil, int posicion, boolean alargo) {
			Silaba sil = arrSil[posicion];
			if (alargo) // si es false no hago nada porque ya es corta
				sil.cambiaALarga();
		}

		public static String[] arrProgramaADatabase(String[] arrPrograma) {
			List<String> lstRes = Arrays.asList(arrPrograma);
			int size = lstRes.size();
			for (int i = 0; i < size; i++) {
				String sAux = (String) lstRes.get(i);
				lstRes.set(i, strProgramaADatabase(sAux));
			}
			return (String[]) (lstRes.toArray(new String[0]));
		}

		/**
		 * transforma una cadena completa a código BETA
		 * 
		 * @param palabra
		 * @return
		 */
		public static String strCompletoABeta(String palabra) {
			if (palabra == null || palabra.equals(""))
				return palabra;
			StringBuffer sBuff = new StringBuffer();
			CarPos cpAux, cpAnt = null;
			for (int i = 0; i < palabra.length(); i++) {
				cpAux = CarPos.getCarPos(palabra, i);
				// si la letra es una vocal y forma diptongo no AI con una vocal
				// anterior
				// debo considerar qué había antes
				sBuff.append(carPosCompletoABeta(cpAnt, cpAux));
				cpAnt = cpAux;
			}
			return sBuff.toString();
		}

		/**
		 * Traduce un CarPos a su carácter equivalente en código Beta. Necesita
		 * la letra anterior en la palabra de origen (si la hay) para evitar
		 * poner la marca de larga si la letra forma parte de un diptongo no AI
		 * El orden que se asume es -1) signo de mayúscula antes de la letra 0)
		 * la letra misma 1ro) larga 2do) diéresis o signo de subscripta 3ro)
		 * espíritus 4to) acentos
		 * 
		 * @param anterior
		 * @param cp
		 * @return
		 */
		private static String carPosCompletoABeta(CarPos anterior, CarPos cp) {
			StringBuffer sBuff = new StringBuffer();
			if (cp.esMayuscula())
				sBuff.insert(0, '*');
			char carCompletoBasico = cp.getLetraBase();
			sBuff.append(carCompletoBasicoABeta(carCompletoBasico));
			// cualquier cosa que no sea una vocal (incluyendo los espacios,
			// guiones y underscores) pasa tal cual
			if (!cp.esVocal()) {
				return sBuff.toString();
			}
			// es vocal

			// contemplo si meto el signo de larga (_)
			// -si tiene circunflejo, ya es larga y no hace falta ponerele el
			// signo
			// -omega y eta son naturalmente largas, no necesitan signo
			// -las subscriptas son naturalmente largas, así que si algo es
			// subcripto no pongo nada
			// -los diptongos son de por sí largos, excepto AI que es de por sí
			// corto
			// para indicar que un diptongo AI es largo, se le pone el signo de
			// larga (_) a la I.
			// Antes de agregar un símbolo de larga a la vocal, debo fijarme se
			// forma un diptongo
			// con la letra anterior, en cuyo caso el signo es innecesario a no
			// ser que el diptongo sea justo
			// AI y la letra actuales sea una I larga
			if (cp.esLarga()) {
				if (!cp.tieneAcentoCircunflejo() && !cp.tieneSubscripta() && cp.getLetraBase() != cEta
						&& cp.getLetraBase() != cOmega) {
					if (anterior == null) // no puede ser diptongo porque no
											// hay nada antes
						sBuff.append('_');
					else if (!(Silabeo.esDiptongo(anterior.getCaracterGriego(), cp.getCaracterGriego()))) // no
																											// es
																											// diptongo
						sBuff.append('_');
					else if ((anterior.getLetraBase() == cAlfaCorta || anterior.getLetraBase() == cOmicron)
							&& cp.getLetraBase() == cIotaCorta && cp.esLarga()) // es
																				// diptongo
																				// alfa-iota
																				// larga
																				// u
																				// ómicron-iota
																				// larga
						sBuff.append('_');
				}
			}

			if (cp.tieneSubscripta()) {
				sBuff.append("|");
			}

			// trato el resto de los signos
			// diéresis
			if (cp.tieneDieresis())
				sBuff.append("+");

			// espíritu
			if (cp.esAspero())
				sBuff.append("(");
			else if (cp.esSuave())
				sBuff.append(")");

			// acentos
			if (cp.tieneAcentoAgudo())
				sBuff.append("/");
			else if (cp.tieneAcentoCircunflejo())
				sBuff.append("=");
            else if (cp.tieneAcentoGrave())
                sBuff.append("\\");
			

			return sBuff.toString();
		}

		/**
		 * Devuelve un array de caracteres conteniendo la representación de la
		 * letra base (recordar que subscriptas hacen distintas letras base)
		 * 
		 * @param carCompleto
		 * @return
		 */
		private static char[] carCompletoBasicoABeta(char carCompleto) {
			switch (carCompleto) {
			case cAlfaCorta:
				return new char[] { 'A' };
			case cAlfaSubscripta:
				return new char[] { 'A', '|' };
			case cIotaCorta:
				return new char[] { 'I' };
			case cEta:
				return new char[] { 'H' };
			case cEtaSubscripta:
				return new char[] { 'H', '|' };

			case cOmicron:
				return new char[] { 'O' };
			case cOmega:
				return new char[] { 'W' };
			case cOmegaSubscripta:
				return new char[] { 'W', '|' };

			case cUpsilonCorta:
				return new char[] { 'U' };

			case cEpsilon:
				return new char[] { 'E' };

			case cBeta:
				return new char[] { 'B' };
			case cJi:
				return new char[] { 'C' };
			case cDelta:
				return new char[] { 'D' };
			case cFi:
				return new char[] { 'F' };
			case cGamma:
				return new char[] { 'G' };
			case cKappa:
				return new char[] { 'K' };
			case cLambda:
				return new char[] { 'L' };
			case cMu:
				return new char[] { 'M' };
			case cNu:
				return new char[] { 'N' };
			case cSigma:
				return new char[] { 'S' };
			case cSigmaFinal:
				return new char[] { 'J' };
			case cTau:
				return new char[] { 'T' };
			case cPi:
				return new char[] { 'P' };
			case cTheta:
				return new char[] { 'Q' };
			case cRho:
				return new char[] { 'R' };
			case cPsi:
				return new char[] { 'Y' };
			case cDzeta:
				return new char[] { 'Z' };
			case cXi:
				return new char[] { 'X' };

			case cEspacio:
				return new char[] { ' ' };
			case cUnderscore:
				return new char[] { '_' };
			case cMenos:
				return new char[] { '-' };
			case cAmpersand:
				return new char[] { '&' };
			default:
				System.out
						.println("problemas al tratar de encontrar un carácter Beta para el carácter completo básico: "
								+ carCompleto + " número hex: " + Integer.toHexString(carCompleto));
				throw new RuntimeException(
						"problemas al tratar de encontrar un carácter Beta para el carácter completo: " + carCompleto
								+ " número: " + Integer.toHexString(carCompleto));
			}
		}

		private static boolean esLetraBeta(char car) {
			switch (car) {
			case Beta.cAlfa:
			case Beta.cIota:
			case Beta.cEta:
			case Beta.cOmicron:
			case Beta.cOmega:
			case Beta.cUpsilon:
			case Beta.cEpsilon:

			case Beta.cBeta:
			case Beta.cJi:
			case Beta.cDelta:
			case Beta.cFi:
			case Beta.cGamma:
			case Beta.cKappa:
			case Beta.cLambda:
			case Beta.cMu:
			case Beta.cNu:
			case Beta.cSigma:
			case Beta.cSigmaFinal:
			case Beta.cTau:
			case Beta.cPi:
			case Beta.cTheta:
			case Beta.cRho:
			case Beta.cPsi:
			case Beta.cDzeta:
			case Beta.cXi:

			// estos signos especiales también serán consideradso " letras"
			// dado que no modifican una letra anterior
			case Beta.cAmpersand:
			case Beta.cMenos:
			case Beta.cPunto:
			case Beta.cEspacio:
			case Beta.cApostrofe:
			case Beta.cNuevaLinea:
			case Beta.cComa:
				return true;
			default:
				return false;
			}
		}

		private static char betaACaracterBasico(char car) {
			switch (car) {
			// nótese que als vocales indeterminadas son cortas
			case Beta.cAlfa:
				return cAlfaCorta;
			case Beta.cIota:
				return cIotaCorta;
			case Beta.cEta:
				return cEta;
			case Beta.cOmicron:
				return cOmicron;
			case Beta.cOmega:
				return cOmega;
			case Beta.cUpsilon:
				return cUpsilonCorta;
			case Beta.cEpsilon:
				return cEpsilon;

			case Beta.cBeta:
				return cBeta;
			case Beta.cJi:
				return cJi;
			case Beta.cDelta:
				return cDelta;
			case Beta.cFi:
				return cFi;
			case Beta.cGamma:
				return cGamma;
			case Beta.cKappa:
				return cKappa;
			case Beta.cLambda:
				return cLambda;
			case Beta.cMu:
				return cMu;
			case Beta.cNu:
				return cNu;
			case Beta.cSigma:
				return cSigma;
			case Beta.cSigmaFinal:
				return cSigmaFinal;
			case Beta.cTau:
				return cTau;
			case Beta.cPi:
				return cPi;
			case Beta.cTheta:
				return cTheta;
			case Beta.cRho:
				return cRho;
			case Beta.cPsi:
				return cPsi;
			case Beta.cDzeta:
				return cDzeta;
			case Beta.cXi:
				return cXi;
			// caracteres "no letras"
			case Beta.cAmpersand:
				return cAmpersand;
			case Beta.cMenos:
				return cMenos;
			case Beta.cEspacio:
				return cEspacio;
			case Beta.cPunto:
				return cPunto;
			case Beta.cApostrofe:
				return cApostrofe;
			case Beta.cNuevaLinea:
				return cNuevaLinea;
			case Beta.cComa:
				return cComa;
			default:
				throw new RuntimeException("no encuentro el carácter básico para la letra Beta " + car);
			}
		}

		private static char mayusculizaCaracterBasico(char car) {
			switch (car) {
			// nótese que las vocales indeterminadas son cortas
			case cAlfaCorta:
				return cAlfaCortaMayuscula;
			case cIotaCorta:
				return cIotaCortaMayuscula;
			case cEta:
				return cEtaMayuscula;
			case cOmicron:
				return cOmicronMayuscula;
			case cOmega:
				return cOmegaMayuscula;
			case cUpsilonCorta:
				return cUpsilonCortaMayuscula;
			case cEpsilon:
				return cEpsilonMayuscula;

			case cBeta:
				return cBetaMayuscula;
			case cJi:
				return cJiMayuscula;
			case cDelta:
				return cDeltaMayuscula;
			case cFi:
				return cFiMayuscula;
			case cGamma:
				return cGammaMayuscula;
			case cKappa:
				return cKappaMayuscula;
			case cLambda:
				return cLambdaMayuscula;
			case cMu:
				return cMuMayuscula;
			case cNu:
				return cNuMayuscula;
			case cSigma:
				return cSigmaMayuscula;
			case cTau:
				return cTauMayuscula;
			case cPi:
				return cPiMayuscula;
			case cTheta:
				return cThetaMayuscula;
			case cRho:
				return cRhoMayuscula;
			case cPsi:
				return cPsiMayuscula;
			case cDzeta:
				return cDzetaMayuscula;
			case cXi:
				return cXiMayuscula;
			default:
				throw new RuntimeException("esta letra básica no se puede mayusculizar " + (int) car);
			}
		}

		/**
		 * Transforma una cadena en código BETA a completo El orden de
		 * diacríticos que se asume en el código BETA es -1) signo de mayúscula
		 * antes de la letra 0) la letra misma 1ro) larga 2do) diéresis o signo
		 * de subscripta 3ro) espíritus 4to) acentos
		 * 
		 * @param palabra
		 * @return
		 * @throws ExcepcionTransformacion
		 */
		public static String strBetaACompleto(String palabra) throws ExcepcionTransformacion {
			try {
				if (palabra == null || palabra.equals(""))
					return palabra;
				StringBuffer cadena = new StringBuffer(palabra);
				// hacer una cadena de CaracterGriegos, con letras básicas en
				// donde se pueda, y lo que sea signos, nulo
				List<CaracterGriego> lstAux = new ArrayList<CaracterGriego>();
				for (int i = 0; i < cadena.length(); i++) {
					char carBeta = cadena.charAt(i);
					if (esLetraBeta(carBeta))
						lstAux.add(CaracterGriegoFactory.produceCaracterGriego(betaACaracterBasico(carBeta)));
					else
						lstAux.add(null);
				}

				// recorro la palabra buscando signos de mayúscula. Si los
				// encuentro, mayusculizo la letra siguiente
				if (cadena.indexOf("*") > -1) {
					for (int i = 0; i < cadena.length(); i++) {
						char carBeta = cadena.charAt(i);
						if (carBeta == '*') {
							CaracterGriego cpAux = (CaracterGriego) lstAux.get(i + 1);
							lstAux.set(i + 1, CaracterGriegoFactory.produceCaracterGriego(Unicode
									.mayusculizaCaracterBasico(cpAux.getCaracter())));
						}
					}

					borraPosicion("*", cadena, lstAux);
				}

				// recorro la palabra buscando signos de diéresis. Si los
				// encuentro, los pongo la letra anterior
				if (cadena.indexOf("+") > -1) {
					for (int i = 0; i < cadena.length(); i++) {
						char carBeta = cadena.charAt(i);
						if (carBeta == '+') {
							CaracterGriego cpAux = null;
							int posicionVocal = i - 1;
							while (cpAux == null) { // a veces la vocal a
													// "dieresear" puede no
													// estar junto antes, porque
													// puede haber intercalado
													// algún nulo por otro signo
													// especial
								cpAux = (CaracterGriego) lstAux.get(posicionVocal);
								posicionVocal--;
							}
							lstAux.set(posicionVocal + 1, CaracterGriegoFactory.produceCaracterGriego(OpLetras
									.dieresisLetra(cpAux.getCaracter())));
						}
					}
					borraPosicion("+", cadena, lstAux);
				}

				// recorro la palabra buscando signos de larga. Si los
				// encuentro, alargo la letra anterior
				if (cadena.indexOf("_") > -1) {
					for (int i = 0; i < cadena.length(); i++) {
						char carBeta = cadena.charAt(i);
						if (carBeta == '_') {
							CaracterGriego cpAux = null;
							int posicionVocal = i - 1;
							while (cpAux == null) { // a veces la vocal a
													// "dieresear" puede no
													// estar junto antes, porque
													// puede haber intercalado
													// algún nulo por otro signo
													// especial
								cpAux = (CaracterGriego) lstAux.get(posicionVocal);
								posicionVocal--;
							}
							if (!cpAux.esVocal()) // si el carácter anterior
													// no es una vocal, tenemos
													// un problema
								throw new RuntimeException(
										"un signo '_' encontrado después de una letra que no es vocal");
							lstAux.set(posicionVocal + 1, CaracterGriegoFactory.produceCaracterGriego(cpAux
									.getVersionLarga()));
						}
					}
					borraPosicion("_", cadena, lstAux);
				}

				// recorro la palabra buscando el signo de subscripción. Si los
				// encuentro, subscribo la letra
				if (cadena.indexOf("|") > -1) {
					for (int i = 0; i < cadena.length(); i++) {
						char carBeta = cadena.charAt(i);
						if (carBeta == '|') {
							CaracterGriego cpAux = (CaracterGriego) lstAux.get(i - 1);
							char chAux = cpAux.getCaracter();
							lstAux.set(i - 1, CaracterGriegoFactory.produceCaracterGriego(OpLetras
									.subscribeLetra(chAux)));
						}
					}
					borraPosicion("|", cadena, lstAux);
				}

				// recorro la palabra buscando signos de espiritu. Si los
				// encuentro, espiritúo la letra anterior
				if (cadena.indexOf(")") > -1 || cadena.indexOf("(") > -1) {
					for (int i = 0; i < cadena.length(); i++) {
						char carBeta = cadena.charAt(i);
						if (carBeta == ')') {
							CaracterGriego cpAux = (CaracterGriego) lstAux.get(i - 1);
							lstAux.set(i - 1, CaracterGriegoFactory.produceCaracterGriego(OpLetras
									.espirituSuaveCaracter(cpAux.getCaracter())));
						} else if (carBeta == '(') {
							CaracterGriego cpAux = (CaracterGriego) lstAux.get(i - 1);
							lstAux.set(i - 1, CaracterGriegoFactory.produceCaracterGriego(OpLetras
									.espirituAsperoCaracter(cpAux.getCaracter())));
						}
					}
					borraPosicion(")", cadena, lstAux);
					borraPosicion("(", cadena, lstAux);
				}

				// recorro la palabra buscando signos de acento. Si los
				// encuentro, acentúo la letra anterior
				for (int i = 0; i < cadena.length(); i++) {
					char carBeta = cadena.charAt(i);
					if (carBeta == '/') {
						CaracterGriego cpAux = (CaracterGriego) lstAux.get(i - 1);
						lstAux.set(i - 1, CaracterGriegoFactory.produceCaracterGriego(OpLetras.acentoAgudoLetra(cpAux
								.getCaracter())));
					} else if (carBeta == '=') {
						CaracterGriego cpAux = (CaracterGriego) lstAux.get(i - 1);
						lstAux.set(i - 1, CaracterGriegoFactory.produceCaracterGriego(OpLetras
								.acentoCircunflejoLetra(cpAux.getVersionLarga())));
						;
					} else if (carBeta == '\\') {
						CaracterGriego cpAux = (CaracterGriego) lstAux.get(i - 1);
						lstAux.set(i - 1, CaracterGriegoFactory.produceCaracterGriego(OpLetras.acentoGraveLetra(cpAux
								.getCaracter())));
						;
					}
				}

				borraPosicion("/", cadena, lstAux);
				borraPosicion("=", cadena, lstAux);
				borraPosicion("\\", cadena, lstAux);

				// finalmente, produzco una palabra con la cadena de CarPoses
				StringBuffer sRes = new StringBuffer();
				int size = lstAux.size();
				for (int i = 0; i < size; i++) {
					CaracterGriego cgAux = (CaracterGriego) lstAux.get(i);
					char cAux = cgAux.getCaracter();
					sRes.append(cAux);
				}
				return sRes.toString();

			} catch (Exception ex) {
				StringBuffer sb = new StringBuffer(
						"Unicode.strBetaACompleto(): tuvimos problemas con la siguiente palabra " + palabra + "\n");
				for (int i = 0; i < palabra.length(); i++) {
					char cAux = palabra.charAt(i);
					String sHex = Integer.toString(cAux, 16);
					sb.append("  letra " + palabra.substring(i, i + 1) + " número " + i + "=" + palabra.charAt(i)
							+ " hex= " + sHex + "\n");
				}
//				logger.warning(sb.toString());
				throw new ExcepcionTransformacion(ExcepcionTransformacion.BETA_A_COMPLETO, palabra, ex);
			}

		}

		/**
		 * Llamada desde strBetaACompleto. Dadas una lista y un stringbuffer,
		 * encuentra ocurrencias de un carácter en el stringbuffer y si las
		 * encuentra borra la posición de la cadena y del stringBufer
		 * 
		 * @param caracter
		 * @param cadena
		 * @param lista
		 */
		private static void borraPosicion(String caracter, StringBuffer cadena, List<CaracterGriego> lista) {
			int encontrado;
			do {
				encontrado = cadena.indexOf(caracter);
				if (encontrado != -1) {
					cadena.deleteCharAt(encontrado);
					lista.remove(encontrado);
				}
			} while (encontrado != -1);
		}

		public static String strDatabaseAInterfazLiteral(String palabra) {
			return Unicode.strCompletoAUnicode(Unicode.strBetaACompleto(palabra), true);
		}

		public static String strBetaAInterfaz(String palabra) throws ExcepcionTransformacion {
			return Unicode.strCompletoAUnicode(strBetaACompleto(palabra), true);
		}

		public static String reprHex(String cadena) {
			if (cadena == null)
				return null;
			StringBuffer sb = new StringBuffer(100);
			for (int i = 0; i < cadena.length(); i++) {
				int car = (int) cadena.charAt(i);
				sb.append(Integer.toString(car, 16));
				if (i < (cadena.length() - 1))
					sb.append("-");
			}

			return sb.toString();
		}

	}

	/**
	 * @param palabra
	 * @param posicion
	 * @param acento
	 *            (según los valores de AnalisisAcento) 0= no especificado 1=
	 *            agudo 2= circunflejo
	 * @param rompeDiptongo
	 * @return
	 */
	public static String acentua(String palabra, int posicion, Acento acento) {
		if (!acento.equals(Acento.Ninguno) && posicion == 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("No se puede especificar el acento y no la posición.\n");
			sb.append(" Acento=" + acento + " posicion=" + posicion + "\n");
			sb.append(" palabra=" + OpPalabras.strCompletoABeta(palabra));
			throw new RuntimeException(sb.toString());
		} else {
			if (posicion == 0 && acento == Acento.Ninguno)
				return acentua(palabra);
			else if (posicion != 0 && acento == Acento.Ninguno)
				return acentua(palabra, posicion);
			else {
				return Silabeo.acentua(palabra, posicion, acento);
			}
		}
	}

	public static boolean esDiptongo(CaracterGriego car1, CaracterGriego car2) {
		return Silabeo.esDiptongo(car1, car2);
	}

	public static boolean esDiptongo(char car1, char car2) {
		return Silabeo.esDiptongo(car1, car2);
	}

	public static CarPos encuentraVocalHaciaElComienzo(String cadena, int posicion) {
		return Silabeo.encuentraVocalHaciaElComienzo(cadena, posicion);
	}

	/**
	 * Dada una palabra devuelve todas las combinadiones posibles de acentos,
	 * tanto agudos como circunflejos
	 */
	public static String[] multiplesAcentos(String entrada, boolean puedeCarecerDeAcento) {
		Silaba[] silabas = Silabeo.silabea(entrada);
		int maxSilabaAtras = 3;
		if (silabas.length < 3)
			maxSilabaAtras = silabas.length;
		Set<String> setAux = new HashSet<String>();
		// circunflejos
		String sCircunflejo = acentua(entrada, -1, Acento.Circunflejo);
		setAux.add(sCircunflejo);
		boolean ultimaLarga = silabas[silabas.length - 1].esLarga();
		if (!ultimaLarga) {
			sCircunflejo = acentua(entrada, -2, Acento.Circunflejo);
			setAux.add(sCircunflejo);
		}
		// agudos
		for (int i = 1; i <= maxSilabaAtras; i++) {
			String sAgudo = acentua(entrada, -1 * i, Acento.Agudo);
			setAux.add(sAgudo);

		}
		
		//nada (si es válido)
		if (puedeCarecerDeAcento){
			setAux.add(entrada);
		}
		return (String[]) (setAux.toArray(new String[0]));
	}

	/**
	 * Dada una palabra devuelve todas las combinaciones posibles con presencia
	 * de diéresis que esa palabra pueda tener. Por ejemplo, dada KAILUE
	 * devuelve: KAILUE, KAÏLUE, KAILÜE y KAÏLÜE 
	 */
	public static String[] multiplesSubscriptas(String entrada) {
		// primero determino las posición de iota y de épsilon
		// para ser sumadas a las listas tienen que tener una vocal adelante
		// antes o después
		ArrayList<Integer> posicionesSubscribibles = new ArrayList<Integer>();
		for (int i = 0; i < entrada.length(); i++) {
			CarPos car = CarPos.getCarPos(entrada, i);

			if (car.getLetraBase() == cOmega || (car.getLetraBase() == cAlfaCorta && car.esLarga())
					|| car.getLetraBase() == cEta) {
				posicionesSubscribibles.add(i);
			}
		}

		if (posicionesSubscribibles.size() == 0)
			return new String[] { entrada };

		List<String> lstResult = new ArrayList<String>();
		// la lista lstPosiciones representa un byte, al que haremos pasar por
		// todos los
		// valores binarios posibles, con los true representando posiciones con
		// diéresis y los
		// false representando posiciones sin diéresis
		int iLongitud = posicionesSubscribibles.size();
		int iValoresPosibles = (int) Math.pow(2, posicionesSubscribibles.size());
		for (int i = 0; i < iValoresPosibles; i++) {
			StringBuffer palabra = new StringBuffer(entrada);
			List<Boolean> lstBits = Unicode.intAListaDeBits(i, iLongitud);// mide lo
																	// mismo que
																	// posicionesIotaOUpsilon
			for (int e = 0; e < iLongitud; e++) {
				boolean bBit = ((Boolean) lstBits.get(e)).booleanValue();
				if (bBit) {
					int iPos = ((Integer) posicionesSubscribibles.get(e)).intValue();
					subscribePalabra(palabra, iPos);
				}

			}
			lstResult.add(palabra.toString());
		}
		return (String[]) (lstResult.toArray(new String[0]));
	}

	/**
	 * Dada una palabra devuelve todas las combinaciones posibles con presencia
	 * de diéresis que esa palabra pueda tener. Por ejemplo, dada KAILUE
	 * devuelve: KAILUE, KAÏLUE, KAILÜE y KAÏLÜE
	 */
	public static String[] multiplesDieresis(String entrada) {
		// primero determino las posición de iota y de épsilon cortas
		// para ser sumadas a las listas tienen que tener una vocal con la que
		// hipotéticamente
		// sería posible formar diptongo, ubicada antes o después de la vocal en
		// cuestión.
		// Mantego una lista de "después" para no agregar dos veces una misma
		// posición
		Set<Integer> despuesYaAnalizadas = new HashSet<Integer>();
		ArrayList<Integer> posicionesIotaOUpsilon = new ArrayList<Integer>();
		for (int i = 0; i < entrada.length(); i++) {
			CarPos car = CarPos.getCarPos(entrada, i);
			CarPos carAntes = CarPos.getCarPos(entrada, i - 1);
			CarPos carDespues = CarPos.getCarPos(entrada, i + 1);
			if (car.getLetraBase() == cUpsilonCorta || car.getLetraBase() == cIotaCorta) {
				if (carAntes != null && esDiptongo(carAntes.getCaracterGriego(), car.getCaracterGriego())) {
					if (!despuesYaAnalizadas.contains(i - 1))
						posicionesIotaOUpsilon.add(i);
				} else if (carDespues != null && esDiptongo(car.getCaracterGriego(), carDespues.getCaracterGriego())) {
					despuesYaAnalizadas.add(i);
					posicionesIotaOUpsilon.add(i);
				}
			}
		}

		if (posicionesIotaOUpsilon.size() == 0)
			return new String[] { entrada };

		List<String> lstResult = new ArrayList<String>();
		// la lista lstPosiciones representa un byte, al que haremos pasar por
		// todos los
		// valores binarios posibles, con los true representando posiciones con
		// diéresis y los
		// false representando posiciones sin diéresis
		int iLongitud = posicionesIotaOUpsilon.size();
		int iValoresPosibles = (int) Math.pow(2, posicionesIotaOUpsilon.size());
		for (int i = 0; i < iValoresPosibles; i++) {
			StringBuffer palabra = new StringBuffer(entrada);
			List<Boolean> lstBits = Unicode.intAListaDeBits(i, iLongitud);// mide lo
																	// mismo que
																	// posicionesIotaOUpsilon
			for (int e = 0; e < iLongitud; e++) {
				boolean bBit = ((Boolean) lstBits.get(e)).booleanValue();
				if (bBit) {
					int iPos = ((Integer) posicionesIotaOUpsilon.get(e)).intValue();
					dieresizaPalabra(palabra, iPos);
				}
			}
			lstResult.add(palabra.toString());
		}
		return (String[]) (lstResult.toArray(new String[0]));
	}

	/**
	 * Aplica un diéresis a en la posición indicada de la palabra dada. Tamnión
	 * contempla el movimiento de espíritu si el diéresis justo cae en lo que
	 * era un diptongo inicial
	 * 
	 * @param palabra
	 * @param posicion
	 */
	public static void dieresizaPalabra(StringBuffer palabra, int posicion) {
		CarPos car = CarPos.getCarPos(palabra.toString(), posicion);
		CarPos carAntes = CarPos.getCarPos(palabra.toString(), posicion - 1);
		// si la letra anterior era una vocal y ambas formabn diptongo (que es
		// lo más probable si llegue a llamar a esta función)
		// entonces verifico si la posición tiene espíritu, y lo muevo a la
		// anterior
		char caracter = car.getCaracter();
		char caracterAntes = carAntes != null ? carAntes.getCaracter() : 0;
		if (carAntes != null && carAntes.esVocal() && esDiptongo(caracterAntes, caracter)) {
			if (car.tieneEspiritu()) {
				boolean aspero = car.esAspero();
				caracter = OpLetras.desespirituarLetra(caracter);
				if (aspero)
					caracterAntes = OpLetras.espirituAsperoCaracter(caracterAntes);
				else
					caracterAntes = OpLetras.espirituSuaveCaracter(caracterAntes);
				caracter = OpLetras.desespirituarLetra(caracter);
			}
		}
		caracter = OpLetras.dieresisLetra(caracter);
		palabra.setCharAt(posicion, caracter);
		if (caracterAntes != 0)
			palabra.setCharAt(posicion - 1, caracterAntes);
	}

	private static void subscribePalabra(StringBuffer palabra, int posicion) {
		CarPos car = CarPos.getCarPos(palabra.toString(), posicion);
		char carConSubscripta = OpLetras.subscribeLetra(car.getCaracter());
		palabra.setCharAt(posicion, carConSubscripta);
	}

	/**
	 * dada una palabra devuelve todas las combinaciones posibles de espíritus
	 * (en el array de resultado siempre se incluye la forma entrante, más las
	 * formas espirituadas si son distintas, de modo que la cantidad de
	 * elementos del resultado es 1 o 3);
	 */
	public static String[] multiplesEspiritus(String entrada) {
		List<String> lstAux = new ArrayList<String>();
		lstAux.add(entrada);
		String sSuave = espirituPalabra(entrada, Espiritu.Suave);
		String sAspero = espirituPalabra(entrada, Espiritu.Aspero);
		if (!sSuave.equals(entrada))
			lstAux.add(sSuave);
		if (!sAspero.equals(entrada))
			lstAux.add(sAspero);
		return (String[]) (lstAux.toArray(new String[0]));
	}


	 public static String[] explotaCompleta(String palabraCompleta) throws ExcepcionCaracterNoEncontradoEnPalabra {
		 return explotaCompleta(palabraCompleta, false);
	 }

	/**
	 * recibe una cadena completa y la explota en todas las combinaciones
	 * posibles de acentos, espíritus, largas/cortas, subscriptas y diéresis
	 */
	public static String[] explotaCompleta(String palabraCompleta, boolean puedeCarecerDeAcento) throws ExcepcionCaracterNoEncontradoEnPalabra {

		List<String> resultados = null;

		resultados = Arrays.asList(OpPalabras.explotaCompletaLargaBreve(palabraCompleta));

		List<String> resTemp = new ArrayList<String>();
		for (String cadena : resultados) {
			String[] arrAux = multiplesEspiritus(cadena);
			resTemp.addAll(Arrays.asList(arrAux));
		}
		resultados = new ArrayList<String>(resTemp);

		resTemp = new ArrayList<String>();
		for (String cadena : resultados) {
			String[] arrAux = multiplesSubscriptas(cadena);
			resTemp.addAll(Arrays.asList(arrAux));
		}
		resultados = new ArrayList<String>(resTemp);

		resTemp = new ArrayList<String>();
		for (String cadena : resultados) {
			String[] arrAux = multiplesSubscriptas(cadena);
			resTemp.addAll(Arrays.asList(arrAux));
		}
		resultados = new ArrayList<String>(resTemp);

		resTemp = new ArrayList<String>();
		for (String cadena : resultados) {
			String[] arrAux = multiplesAcentos(cadena, puedeCarecerDeAcento);
			resTemp.addAll(Arrays.asList(arrAux));
		}
		resultados = new ArrayList<String>(resTemp);

		podaImposibles(resultados, puedeCarecerDeAcento);
		return (String[]) (resultados.toArray(new String[] {}));
	}

	/**
	 * recibe una lista de formas explotadas a partir de una forma sin marcas,
	 * y poda las formas imposibles
	 * 
	 * @param lst
	 */
	private static void podaImposibles(List<String> lst, boolean puedeCarecerDeAcento) {
		int contador = 0;
		while (contador < lst.size()) {
			String forma = (String) lst.get(contador);
			AnalisisAcento aa = AnalisisAcento.getAnalisisAcento(forma);
			CarPos primera = CarPos.getCarPos(forma, 0);
			// quito las que no tengan acento
			if (aa.actuales.silaba == 0 && !puedeCarecerDeAcento)
				lst.remove(contador);
			// quito las que comenzando con vocal no tengan espíritu
			else if (primera.esVocal() && OpPalabras.getEspiritu(forma) == Espiritu.Ninguno)
				lst.remove(contador);
			else
				contador++;
		}

	}

	/**
	 * función usada para debuguear. Analiza una cadena y devuelve la posición
	 * de cualquier vocal que no sea ni corta ni larga Si la cadena no posee
	 * indefinidas, devuelve -1
	 */
	public static int detectaIndefinidas(String str) {
		for (int i = 0; i < str.length(); i++) {
			CarPos cpAux = CarPos.getCarPos(str, i);
			if (cpAux.esVocal() && !cpAux.esCorta() && !cpAux.esLarga())
				return cpAux.getPosicion();
		}
		return -1;
	}

	private static class Silabeo implements CompLetras {

		static char[][] arrDiptongos = { { cAlfaCorta, cUpsilonCorta }, { cAlfaCorta, cIotaCorta },
				{ cEpsilon, cUpsilonCorta }, { cEpsilon, cIotaCorta }, { cOmicron, cUpsilonCorta },
				{ cOmicron, cIotaCorta }, { cEta, cUpsilonCorta }, { cUpsilonCorta, cIotaCorta }, };

		public static boolean esDiptongo(char letra1, char letra2) {
			CaracterGriego cg1 = CaracterGriegoFactory.produceCaracterGriego(letra1);
			CaracterGriego cg2 = CaracterGriegoFactory.produceCaracterGriego(letra2);
			return esDiptongo(cg1, cg2);
		}

		public static boolean esDiptongo(CaracterGriego letra1, CaracterGriego letra2) {
			char a = letra1.getLetraBase();
			char b = letra2.getLetraBase();
			// las débiles con acento rompen el diptongo
			if ((a == cIotaCorta || a == cUpsilonCorta) && letra1.tieneAcentoAgudo())
				return false;
			else if (letra1.tieneDieresis() || letra2.tieneDieresis())
				return false;
			for (int i = 0; i < arrDiptongos.length; i++)
				if (a == arrDiptongos[i][0] && b == arrDiptongos[i][1]) {
					return true;
				}
			return false;
		}

		public static String devuelveUltimaSilaba(String palabra) {
			int vocal;
			vocal = obtienePrimeraVocalPosible(palabra, palabra.length() - 1);
			if (vocal == -1)
				return palabra;
			else {
				CarPos letraAnterior = CarPos.getCarPos(palabra, vocal - 1);
				// si la letra anterior no existe, la sílaba empieza ahí.
				// lo mismo si la letra anterior es una vocal, porque eso
				// significa que cortó la sílaba antes en
				// obtienePrimeraVocalPosible, por no
				// ser diptongo o triptongo con mi vocal devuelta
				if (letraAnterior == null || letraAnterior.esVocal())
					return palabra.substring(vocal);
				else {
					CarPos letra0 = CarPos.getCarPos(palabra, vocal - 1);
					if (letra0 == null)
						return palabra.substring(vocal);
					if (letra0.esConsonante())
						return palabra.substring(obtienePrimeraConsonantePosible(palabra, vocal));
					else
						return palabra.substring(vocal);
				}
			}
		}

		/**
		 * empezando desde el final de la palabra, obtiene el lugar la
		 * consonante más cercana al comienzo que es posible que comience la
		 * última sílaba. Será la primer vocal que encuentre salvo que forme
		 * diptongo o triptongo con las 2 inmediatas anteriores. Si fracasa en
		 * encontrar una vocal, devuelve -1
		 * 
		 * Puede considerarse un envoltorio de encuentraVocalHaciaElComienzo,
		 * que además contempla diptongo
		 */
		private static int obtienePrimeraVocalPosible(String palabra, int posicion) {
			CarPos tercera = encuentraVocalHaciaElComienzo(palabra, posicion);
			if (tercera == null) // ni siquiera hay vocales en esta palabra
				return -1;
			CarPos segunda = CarPos.getCarPos(palabra, tercera.getPosicion() - 1);
			CarPos primera = CarPos.getCarPos(palabra, tercera.getPosicion() - 2);

			// los casos más fáciles: sólo la tercera existe o es vocal
			if (segunda == null || !segunda.esVocal())
				return tercera.getPosicion();
			// la segunda existe pero no forma diptongo con la primera
			else if (!esDiptongo(segunda.getCaracterGriego(), tercera.getCaracterGriego()))
				return tercera.getPosicion();
			// ahora sé que la segunda existe y forma diptongo con la primera
			// si la tercera no existe o no es vocal, la cosa se acaba en la
			// segunda
			else if (primera == null || !primera.esVocal())
				return segunda.getPosicion();
			// ahora sé que la segunda y la tercera forman diptongo y que la
			// primera es vocal
			else
				return segunda.getPosicion();

		}

		private static int obtienePrimeraConsonantePosible(String palabra, int posicion) {
			/**
			 * empezando desde una posición en la palabra (una vocal), obtiene
			 * la consonante más cercana al comienzo que es posible que comience
			 * la última sílaba. Será la primer consonante que encuentre salvo
			 * que forme grupo líquida-licuante la inmediata anterior. Si
			 * fracasa en encontrar una vocal, devuelve -1 Puede considerarse un
			 * envoltorio de encuentraConsonanteHaciaElComienzo, que además
			 * contempla líquida/licuante y la circunstancia de que haya dos
			 * consonantes al principio de la palabra, aunque no sean
			 * líquida/licuante
			 */
			CarPos consAux = encuentraConsonanteHaciaElComienzo(palabra, posicion);
			if (consAux == null)
				return -1;
			CarPos consAux_1 = CarPos.getCarPos(palabra, consAux.getPosicion() - 1);
			if (consAux_1 == null)
				return consAux.getPosicion(); // se acabó la palabra
			if (consAux_1.esConsonante() & consAux.getPosicion() == 1)
				return 0; // la consonante anterior no es licuadora,
			// pero se acabó la palabra, por lo tanto tiene que formar parte de
			// la primer sílaba. ejemplo: BDALLW
			if (esLicuanteLiquida(consAux_1.getCaracter(), consAux.getCaracter())) {
				// aún puede haber una consonante solitaria antes del grupo, por
				// ejemplo sklhnw
				if (consAux_1.getPosicion() == 1)
					if (TipoLetra.getTipoLetraBasico(palabra.charAt(0)) == TipoLetraBasico.enuConsonante)
						return 0;
				return consAux_1.getPosicion();
			} else
				return consAux.getPosicion();
		}

		public static boolean esLicuanteLiquida(char cons1, char cons2) {
			boolean bLicuante, bLiquida;
			switch (cons1) {
			case cBeta:
			case cPi:
			case cFi:
			case cJi:
			case cKappa:
			case cGamma:
			case cTau:
			case cTheta:
			case cDelta:
				bLicuante = true;
				break;
			default:
				bLicuante = false;
			}
			switch (cons2) {
			case cLambda:
			case cRho:
				bLiquida = true;
				break;
			default:
				bLiquida = false;
			}
			return bLicuante & bLiquida;
		}

		/**
		 * busca en palabra, desde posición inclusive hacia el comienzo, la
		 * primera vocal devuelve un CarPos o null si no encuentra nada
		 */
		public static CarPos encuentraVocalHaciaElComienzo(String palabra, int posicion) {
			if (posicion < 0 | posicion >= palabra.length())
				return null;
			for (int i = posicion; i >= 0; i--)
				if (TipoLetra.getTipoLetraBasico(palabra.charAt(i)) == TipoLetraBasico.enuVocal) {
					return CarPos.getCarPos(palabra, i);
				}
			return null;
		}

		/**
		 * busca en palabra, desde posición inclusive hacia el comienzo, la
		 * primera vocal devuelve un CarPos o null si no encuentra nada
		 */
		static CarPos encuentraConsonanteHaciaElComienzo(String palabra, int posicion) {
			if (posicion < 0 | posicion >= palabra.length())
				return null;
			for (int i = posicion; i >= 0; i--)
				if (TipoLetra.getTipoLetraBasico(palabra.charAt(i)) == TipoLetraBasico.enuConsonante)
					return CarPos.getCarPos(palabra, i);
			return null;
		}

		public static Silaba[] silabea(String palabra) {
			StringBuffer sBuff = new StringBuffer(palabra);
			List<Silaba> lstSilabas = new ArrayList<Silaba>();
			do {
				String sAux = devuelveUltimaSilaba(sBuff.toString());
				if (sAux != null) {
					lstSilabas.add(0, new Silaba(sAux));
					sBuff.delete(sBuff.length() - sAux.length(), sBuff.length());
				} else
					break;
			} while (sBuff.length() > 0);
			return (Silaba[]) (lstSilabas.toArray(new Silaba[0]));
		}

		public static Silaba[] cloneArray(Silaba[] arrSil) {
			List<Silaba> lstAux = new ArrayList<Silaba>();
			for (int i = 0; i < arrSil.length; i++)
				lstAux.add((Silaba) arrSil[i].clone());
			return (Silaba[]) lstAux.toArray(new Silaba[] {});
		}

		public static String recosntituyePalabra(Silaba[] arrSil) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arrSil.length; i++)
				sb.append(arrSil[i].getCadena());
			return sb.toString();
		}

		public static Silaba[] romperDiptongo(Silaba[] silabas, int indiceARomper) {
			List<Silaba> lstAux = new ArrayList<Silaba>();
			for (int i = 0; i < silabas.length; i++) {
				if (i != indiceARomper)
					lstAux.add(silabas[i]);
				else {
					Silaba[] arrAux = silabas[i].rompeDiptongo();
					for (int e = 0; e < arrAux.length; e++)
						lstAux.add(arrAux[e]);
				}
			}
			return (Silaba[]) lstAux.toArray(new Silaba[0]);
		}

		private static String acentoCircunflejoSilaba(Silaba silaba) {
			CarPos ultimaVocal = encuentraVocalHaciaElComienzo(silaba.getCadena(), silaba.getCadena().length() - 1);
			// si forman parte de un diptongo y son naturalmente cortas, las
			// perdono y pongo agudo
			if (silaba.esDiptongo()) {
				if (ultimaVocal.getLetraBase() == cOmicron || ultimaVocal.getLetraBase() == cEpsilon) {
					return acentoAgudoCaracter(silaba.getCadena(), ultimaVocal.getPosicion());
				}
			}
			return acentoCircunflejoCaracter(silaba.getCadena(), ultimaVocal.getPosicion());
		}

		private static String acentoAgudoSilaba(String silaba) {
			int largo = silaba.length();
			for (int i = largo - 1; i >= 0; i--)
				if (TipoLetra.getTipoLetraBasico(silaba.charAt(i)) == TipoLetraBasico.enuVocal) {
					return acentoAgudoCaracter(silaba, i);
				}
			return silaba;
		}

		private static String acentoAgudoCaracter(String str, int posicion) {
			StringBuffer sb = new StringBuffer(str);
			sb.setCharAt(posicion, acentoAgudoLetra(sb.charAt(posicion)));
			return sb.toString();
		}

		private static String acentoCircunflejoCaracter(String str, int posicion) {
			StringBuffer sb = new StringBuffer(str);
			sb.setCharAt(posicion, acentoCircunflejoLetra(CaracterGriegoFactory.produceCaracterGriego(
					sb.charAt(posicion)).getVersionLarga()));
			return sb.toString();
		}

		private static char acentoAgudoLetra(char letra) {
			char ret;
			switch (letra) {
			// alfa
			case cAlfaSubscripta:
				ret = cAlfaSubscriptaAgudo;
				break;
			case cAlfaSubscriptaAspero:
				ret = cAlfaSubscriptaAsperoAgudo;
				break;
			case cAlfaSubscriptaSuave:
				ret = cAlfaSubscriptaSuaveAgudo;
				break;
			// omega
			case cOmega:
				ret = cOmegaAgudo;
				break;
			case cOmegaAspero:
				ret = cOmegaAsperoAgudo;
				break;
			case cOmegaSuave:
				ret = cOmegaSuaveAgudo;
				break;
			case cOmegaSubscripta:
				ret = cOmegaSubscriptaAgudo;
				break;
			case cOmegaSubscriptaAspero:
				ret = cOmegaSubscriptaAsperoAgudo;
				break;
			case cOmegaSubscriptaSuave:
				ret = cOmegaSubscriptaSuaveAgudo;
				break;
			// eta
			case cEta:
				ret = cEtaAgudo;
				break;
			case cEtaAspero:
				ret = cEtaAsperoAgudo;
				break;
			case cEtaSuave:
				ret = cEtaSuaveAgudo;
				break;
			case cEtaSubscripta:
				ret = cEtaSubscriptaAgudo;
				break;
			case cEtaSubscriptaAspero:
				ret = cEtaSubscriptaAsperoAgudo;
				break;
			case cEtaSubscriptaSuave:
				ret = cEtaSubscriptaSuaveAgudo;
				break;
			// épsilon
			case cEpsilon:
				ret = cEpsilonAgudo;
				break;
			case cEpsilonAspero:
				ret = cEpsilonAsperoAgudo;
				break;
			case cEpsilonSuave:
				ret = cEpsilonSuaveAgudo;
				break;
			// ómicron
			case cOmicron:
				ret = cOmicronAgudo;
				break;
			case cOmicronAspero:
				ret = cOmicronAsperoAgudo;
				break;
			case cOmicronSuave:
				ret = cOmicronSuaveAgudo;
				break;
			// alfa
			case cAlfaCorta:
				ret = cAlfaAgudoCorta;
				break;
			case cAlfaAsperoCorta:
				ret = cAlfaAsperoAgudoCorta;
				break;
			case cAlfaSuaveCorta:
				ret = cAlfaSuaveAgudoCorta;
				break;
			// iota
			case cIotaCorta:
				ret = cIotaAgudoCorta;
				break;
			case cIotaAsperoCorta:
				ret = cIotaAsperoAgudoCorta;
				break;
			case cIotaSuaveCorta:
				ret = cIotaSuaveAgudoCorta;
				break;
			case cIotaDieresisCorta:
				ret = cIotaDieresisAgudoCorta;
				break;
			// épsilon
			case cUpsilonCorta:
				ret = cUpsilonAgudoCorta;
				break;
			case cUpsilonAsperoCorta:
				ret = cUpsilonAsperoAgudoCorta;
				break;
			case cUpsilonSuaveCorta:
				ret = cUpsilonSuaveAgudoCorta;
				break;
			case cUpsilonDieresisCorta:
				ret = cUpsilonDieresisAgudoCorta;
				break;
			// alfa
			case cAlfaLarga:
				ret = cAlfaAgudoLarga;
				break;
			case cAlfaAsperoLarga:
				ret = cAlfaAsperoAgudoLarga;
				break;
			case cAlfaSuaveLarga:
				ret = cAlfaSuaveAgudoLarga;
				break;
			// iota
			case cIotaLarga:
				ret = cIotaAgudoLarga;
				break;
			case cIotaAsperoLarga:
				ret = cIotaAsperoAgudoLarga;
				break;
			case cIotaSuaveLarga:
				ret = cIotaSuaveAgudoLarga;
				break;
			case cIotaDieresisLarga:
				ret = cIotaDieresisAgudoLarga;
				break;
			// épsilon
			case cUpsilonLarga:
				ret = cUpsilonAgudoLarga;
				break;
			case cUpsilonAsperoLarga:
				ret = cUpsilonAsperoAgudoLarga;
				break;
			case cUpsilonSuaveLarga:
				ret = cUpsilonSuaveAgudoLarga;
				break;
			case cUpsilonDieresisLarga:
				ret = cUpsilonDieresisAgudoLarga;
				break;
			default:
				ret = letra;
			}
			return ret;
		}

		private static char acentoCircunflejoLetra(char letra) {
			char ret;
			switch (letra) {
			// alfa
			case cAlfaCorta:
			case cAlfaLarga:
				ret = cAlfaCircunflejo;
				break;
			case cAlfaAsperoCorta:
			case cAlfaAsperoLarga:
				ret = cAlfaAsperoCircunflejo;
				break;
			case cAlfaSuaveCorta:
			case cAlfaSuaveLarga:
				ret = cAlfaSuaveCircunflejo;
				break;
			case cAlfaSubscripta:
				ret = cAlfaSubscriptaCircunflejo;
				break;
			case cAlfaSubscriptaAspero:
				ret = cAlfaSubscriptaAsperoCircunflejo;
				break;
			case cAlfaSubscriptaSuave:
				ret = cAlfaSubscriptaSuaveCircunflejo;
				break;
			// omega
			case cOmega:
				ret = cOmegaCircunflejo;
				break;
			case cOmegaAspero:
				ret = cOmegaAsperoCircunflejo;
				break;
			case cOmegaSuave:
				ret = cOmegaSuaveCircunflejo;
				break;
			case cOmegaSubscripta:
				ret = cOmegaSubscriptaCircunflejo;
				break;
			case cOmegaSubscriptaAspero:
				ret = cOmegaSubscriptaAsperoCircunflejo;
				break;
			case cOmegaSubscriptaSuave:
				ret = cOmegaSubscriptaSuaveCircunflejo;
				break;
			// eta
			case cEta:
				ret = cEtaCircunflejo;
				break;
			case cEtaAspero:
				ret = cEtaAsperoCircunflejo;
				break;
			case cEtaSuave:
				ret = cEtaSuaveCircunflejo;
				break;
			case cEtaSubscripta:
				ret = cEtaSubscriptaCircunflejo;
				break;
			case cEtaSubscriptaAspero:
				ret = cEtaSubscriptaAsperoCircunflejo;
				break;
			case cEtaSubscriptaSuave:
				ret = cEtaSubscriptaSuaveCircunflejo;
				break;
			// iota
			case cIotaCorta:
			case cIotaLarga:
				ret = cIotaCircunflejo;
				break;
			case cIotaAsperoCorta:
			case cIotaAsperoLarga:
				ret = cIotaAsperoCircunflejo;
				break;
			case cIotaSuaveCorta:
			case cIotaSuaveLarga:
				ret = cIotaSuaveCircunflejo;
				break;
			// upsilon
			case cUpsilonCorta:
			case cUpsilonLarga:
				ret = cUpsilonCircunflejo;
				break;
			case cUpsilonAsperoCorta:
			case cUpsilonAsperoLarga:
				ret = cUpsilonAsperoCircunflejo;
				break;
			case cUpsilonSuaveCorta:
			case cUpsilonSuaveLarga:
				ret = cUpsilonSuaveCircunflejo;
				break;
			default:
				ret = letra;
			}
			return ret;
		}

		/**
		 * En esta sobrecarga mando el circunflejo porque es el más neutro en
		 * este caso, el más abierto
		 * 
		 * @param silabas
		 * @param posicion
		 * @return
		 */
		public static String acentua(Silaba[] silabas, int posicion) {
			return acentua(silabas, posicion, Acento.Ninguno);
		}

		/**
		 * función acentúa aplicada a una palabra. Sobrecarga la función
		 * original, en la cual la acentuación se le aplica a un array de
		 * sílabas
		 * 
		 * @param palabra
		 * @param posicion
		 * @param tipoAcento
		 * @return
		 */
		public static String acentua(String palabra, int posicion, Acento tipoAcento) {
			Silaba[] silabas = Silabeo.silabea(palabra);
			return acentua(silabas, posicion, tipoAcento);
		}

		/**
		 * @param silabas
		 *            sílabas (no acentuadas) de la palabra
		 * @param posicion:
		 *            puede ser positiva (base 1) o negativa (base -1)
		 * @param tipoAcento
		 *            si es agudo, se va a forzar agudo. De otro modo, se
		 *            acentúa según se pueda(agudo o circunflejo)
		 * @param rompeDiptongo
		 *            si es true, la sílaba indicada es la sílaba DESPUÉS de
		 *            romper el diptongo
		 * @return
		 */
		public static String acentua(Silaba[] silabas, int posicion, Acento tipoAcento) {
			if (posicion > 0) // transformo a negativa
				posicion = -(silabas.length - posicion) - 1;

			// si no, se fuerza posición
			// ****************************************
			if (posicion == 0) {
				AnalisisAcento aa = AnalisisAcento.getAnalisisAcento(silabas);
				return producePalabraAcentuada(silabas, aa.sugeridos.silaba, aa.sugeridos.tipoAcento);
			}
			// si hay posición
			// ***************************************************
			// corrección por si la posición es positiva

			// mido las cantidades que hay desde la sílaba marcada por
			// "posición", exclusive
			int cantidad = 0;
			for (int i = silabas.length + posicion + 1; i < silabas.length; i++)
				if (silabas[i].esLarga())
					cantidad += 2;
				else
					cantidad++;
			// ahora acentúo en consecuencia. Circunflejo si puedo, si no agudo
			boolean bAgudo;
			if (silabas.length + posicion < 0)// es ilegal la indicacúon de
												// acentuación en la sílaba, uso
												// agudo
				bAgudo = true;
			else if (silabas[silabas.length + posicion].esLarga() && cantidad < 2)
				bAgudo = false;
			else
				bAgudo = true;
			bAgudo = bAgudo | (tipoAcento.equals(Acento.Agudo)); // si es
																	// agudo o
																	// no puedo
																	// poner
																	// circunflejo,
																	// el
																	// parámetro
																	// que mando
																	// es false
			return producePalabraAcentuada(silabas, posicion, bAgudo ? Acento.Agudo : Acento.Circunflejo);

		}

		/**
		 * Acentua una palabra como se le dice, sin fijarse en las cantidades La
		 * posición hay que convertirla a base 0
		 * 
		 * @param palabra
		 * @param posicion
		 * @param acento
		 * @return
		 */

		public static String acentuaForzosoAdelanteBase1(String palabra, int posicion, Acento tipoAcento) {
			Silaba[] silabas = silabea(palabra);
			int posicionNegativa = posicion - 1 - silabas.length;
			return producePalabraAcentuada(silabas, posicionNegativa, tipoAcento);
		}

		/**
		 * Acentua una palabra como se le dice, sin fijarse en las cantidades
		 * rompiendo el diptongo (generalmente UI como en ICQUI ) en la posición
		 * que se le indica La posición hay que convertirla a base 0
		 * 
		 * @param palabra
		 * @param posicion
		 * @param acento
		 * @param ruptura=
		 *            sílaba (desde 0) a la que se tratará de romperle el
		 *            diptongo, transformándola en dos
		 * @return
		 */
		public static String acentuaForzosoAdelanteBase1(String palabra, int posicion) {
			Silaba[] arrSil = silabea(palabra);
			int posicionDeCero = posicion - 1 - arrSil.length;
			return OpPalabras.acentua(palabra, posicionDeCero);
		}

		/**
		 * Produce la palabra acentuada en base al array de sílabas más
		 * indicaciones
		 * 
		 * @param arrSil
		 * @param silaba
		 *            la ubicación de la sílalba acentuada base -1
		 * @param tipoAcento
		 *            segúna AnalisisAcento
		 * @return
		 */
		private static String producePalabraAcentuada(Silaba[] arrSil, int silaba, Acento tipoAcento) {
			StringBuffer sBuff = new StringBuffer();
			for (int i = 0; i < arrSil.length; i++) {
				if ((i - arrSil.length) != silaba)
					sBuff.append(arrSil[i].getCadena());
				else if (tipoAcento == Acento.Circunflejo)
					sBuff.append(acentoCircunflejoSilaba(arrSil[i]));
				else
					sBuff.append(acentoAgudoSilaba(arrSil[i].getCadena()));
			}
			return sBuff.toString();
		}

		public static Silaba[] sumaSilabas(Silaba[] arr1, Silaba[] arr2) {
			List<Silaba> arrSuma = new ArrayList<Silaba>();
			List<Silaba> lstAux = Arrays.asList(arr1);
			List<Silaba> lstAux2 = Arrays.asList(arr2);
			arrSuma.addAll(lstAux);
			arrSuma.addAll(lstAux2);
			return (Silaba[]) (arrSuma.toArray(new Silaba[0]));
		}

		/**
		 * Analiza dos miembros a contraer vocálicamente y determina si el
		 * resultado lleva acento agudo o circunflejo, devolviendo la palbra
		 * acentuada comidosEnSegunda indica si hay letras iniciales en la
		 * segunda palabra que hay que ignorar (0 si ninguna)
		 * 
		 * @posicion la posicion positiva/negativa/0 del acento
		 * @return devuelve un array conteniendo en el elemento 0= la palabra1
		 *         con la acentuación intermedia que hice para averiguar el
		 *         acento en el elemento 1= la palabra2 con la acentuación
		 *         intermedia que hice para averiguar el acento en el elemento
		 *         2= el resultado
		 * 
		 */
		public static String[] acentuaContraccionVocalica(String palabra1, String palabra2, String res,
				int comidosEnPrimera, int comidosEnSegunda, int posicion) {
			Silaba[] arrPrimera = silabea(palabra1);
			Silaba[] arrSegunda = silabea(palabra2);
			Silaba[] arrSuma = sumaSilabas(arrPrimera, arrSegunda);
			int acentuable; // una variable que guardará la posición positiva
							// del acento, base 1
			AnalisisAcento aa = AnalisisAcento.getAnalisisAcento(arrSuma);
			String[] arrRes = new String[3];
			String sPrimera = palabra1.substring(0, palabra1.length() - comidosEnPrimera);
			String sSegunda = palabra2.substring(comidosEnSegunda);

			if (posicion == 0) {
				acentuable = arrSuma.length + aa.sugeridos.silaba + 1; // aa.silaba
																		// es
																		// negativo,
																		// lo
																		// pasé
																		// a
																		// positivo
				if (acentuable == arrPrimera.length) {// cae en el primer
														// miembro de la
														// contracción
					arrRes[0] = OpPalabras.acentua(palabra1, -1, Acento.Agudo);
					arrRes[1] = palabra2;
					arrRes[2] = sPrimera + OpPalabras.acentua(res) + sSegunda; // resultado
				} else if (acentuable == arrPrimera.length + 1) {// el acento
																	// cae en el
																	// segundo
																	// miembro
																	// de la
																	// contracción
					arrRes[0] = palabra1;
					arrRes[1] = OpPalabras.acentua(palabra2, -1 * arrSegunda.length, Acento.Agudo); // así
																									// me
																									// aseguro
																									// de
																									// que
																									// caiga
																									// en
																									// la
																									// primera
					arrRes[2] = sPrimera + acentoAgudoSilaba(res) + sSegunda;
				} else {// está en cualquier otro lado, ya sea adelante o atrás
						// del resultado,
					// acentúo según marque la posición (si la hay) o, si no la
					// hay, como pueda
					String sResultado = sPrimera + res + sSegunda;
					sResultado = OpPalabras.acentua(sResultado, posicion);
					if (acentuable < arrPrimera.length) { // Estoy tratando de
															// averiguar si va
															// atrás hacia la
															// primera palabra
						arrRes[0] = OpPalabras.acentua(palabra1, acentuable - arrPrimera.length);
						arrRes[1] = palabra2;
					} else { // el acento cae en el segundo segmento
						arrRes[0] = palabra1;
						arrRes[1] = OpPalabras.acentua(palabra2, acentuable - arrSuma.length);
					}
					arrRes[2] = sResultado;
				}
			} else {
				// si la posición viene externamente, no intento acentuar los
				// componentes [0] y [1], porque
				// eso no explica nada
				arrRes[0] = palabra1;
				arrRes[1] = palabra2;
				arrRes[2] = OpPalabras.acentua(sPrimera + res + sSegunda, posicion);
			}
			return arrRes;
		}

	}
	
	
}