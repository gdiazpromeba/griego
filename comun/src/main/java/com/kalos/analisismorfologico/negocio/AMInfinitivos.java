package com.kalos.analisismorfologico.negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.kalos.beans.IrrInfinitivoBean;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.TermRegInfinitivo;
import com.kalos.beans.VerboBean;
import com.kalos.datos.gerentes.GerenteIrrInfinitivos;
import com.kalos.datos.gerentes.GerenteTermRegInfinitivo;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.enumeraciones.Voz;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.AnalisisAcento;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;

public class AMInfinitivos implements AnalizadorMorfologico {

	@Autowired
	private AMUtil<TermRegInfinitivo> amUtil;
	@Autowired
	private AMVerbal<TermRegInfinitivo> amVerbal;

	private GerenteTermRegInfinitivo gerenteTermRegInfinitivo;
	private GerenteIrrInfinitivos gerenteIrrInfinitivos;
	private GerenteVerbos gerenteVerbos;
	private ExtractorPrefijos extractorPrefijos;

	public  Set<ResultadoUniversal> buscaCanonica(String[] entradas, AACacheable cacheAA, boolean paramBoolean1, boolean debug) {
		Set<ResultadoUniversal> resultados = new HashSet<>();
		Set<TermRegInfinitivo> setPaso1 = new LinkedHashSet<>();
		Set<TermRegInfinitivo> termsRegInf = new LinkedHashSet<>();
		Set<TermRegInfinitivo> triDespuesPreps = new LinkedHashSet<>();
		Set<ObjYDest<TermRegInfinitivo>> destransformados = new LinkedHashSet<>();
		Set<TermRegInfinitivo> setTemasPropuestos = new LinkedHashSet<TermRegInfinitivo>();
		long l1 = System.currentTimeMillis();
		Map<Object[], TemaConPreps[]> cacheExtraccionPrefijos = new HashMap<Object[], TemaConPreps[]>();
		Map<TemaConPreps, HashSet<TermRegInfinitivo>> mapTemasPropuestos = new HashMap<TemaConPreps, HashSet<TermRegInfinitivo>>();
		Set<String> setEntradas = new HashSet<String>(Arrays.asList(entradas));
		List<ObjYDest<TermRegInfinitivo>> aBuscarPorTema = new ArrayList<>();
		List<TermRegInfinitivo> localArrayList2 = new ArrayList<>();
		paso1(setEntradas, setPaso1, cacheExtraccionPrefijos, cacheAA, debug);
		// amUtil.conservaSolo(setPaso1, new String[]{"aspecto", "voz"}, new
		// Object[]{Aspecto.Infectivo, Voz.Media});
		this.amVerbal.extiendeTipos(setPaso1, termsRegInf, debug);
		this.amVerbal.averiguaPreposiciones(termsRegInf, triDespuesPreps, 1, cacheExtraccionPrefijos, debug);
		this.amUtil.incorporaADestransformar(triDespuesPreps, debug);
		this.amUtil.desTransformacionesTemas(triDespuesPreps, destransformados, aBuscarPorTema, debug);
		this.amVerbal.incorporaTemaPropuestoReconstruidos(destransformados, aBuscarPorTema, debug);
		this.amVerbal.incorporaTemaPropuestoIrregulares(aBuscarPorTema, debug);
		this.amUtil.vocalUnitivaTemas(destransformados, aBuscarPorTema);
		this.amVerbal.aplicaEncuentraTemasTemprano(destransformados, localArrayList2, aBuscarPorTema, debug);
		this.amVerbal.envoltorioRestauraFormas(destransformados, setTemasPropuestos, cacheAA, debug);
		this.amUtil.agrupaTemasEnSet(setTemasPropuestos, mapTemasPropuestos, debug);
		setPaso1 = null;
		termsRegInf = null;
		triDespuesPreps = null;
		destransformados = null;
		setTemasPropuestos = null;
		aBuscarPorTema = null;
		this.amVerbal.buscaReconstruidaEnTablas(resultados, mapTemasPropuestos, false, true, debug);
		this.amVerbal.buscaReconstruidaEnTablas(resultados, mapTemasPropuestos, true, true, debug);
		this.amVerbal.comparaConTemasSemirreconstruidos(resultados, localArrayList2, true, debug);
		buscaIrrIndivConjugacion(resultados, entradas, cacheAA, debug);
		this.amVerbal.pueblaCanonicasVerbos(resultados);
		long l2 = System.currentTimeMillis();
		long l3 = l2 - l1;
		if (debug) {
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			localGregorianCalendar.setTimeInMillis(l3);
			System.out.println("tardó " + localGregorianCalendar.get(12) + " minutos " + localGregorianCalendar.get(13)
					+ " segundos " + localGregorianCalendar.get(14) + " milisegundos");
		}
		return resultados;
	}

	public void buscaIrrIndivConjugacion(Set<ResultadoUniversal> paramSet, String[] paramArrayOfString, AACacheable cacheAA, boolean paramBoolean) {
		Map<Object[], TemaConPreps[]> cacheExtraccionPreps = new HashMap<Object[], TemaConPreps[]>();
		for (int i = 0; i < paramArrayOfString.length; i++) {
			int j = 0;
			TemaConPreps[] tcps = this.extractorPrefijos.averiguaPrefijos(paramArrayOfString[i], j, cacheExtraccionPreps);
			for (int k = 0; k < tcps.length; k++) {
				String str1 = tcps[k].resto;
				AnalisisAcento localD = cacheAA.getAnalisisAcento(str1);
				if (localD.sugeridos.tipoAcento.equals(Acento.Ninguno)) {
					str1 = OpPalabras.acentua(str1);
				}
				str1 = OpPalabras.strCompletoABeta(str1);
				if (paramBoolean) {
					System.out.println("buscando " + str1);
				}
				List<IrrInfinitivoBean> localList = this.gerenteIrrInfinitivos.seleccionaPorForma(str1);
				Map<String, VerboBean> localHashMap2 = new HashMap<String, VerboBean>();
				Iterator<IrrInfinitivoBean> localIterator = localList.iterator();
				while (localIterator.hasNext()) {
					IrrInfinitivoBean irrInfBean = localIterator.next();
					String str2 = irrInfBean.getVerboId();
					VerboBean localh = localHashMap2.get(str2);
					if (localh == null) {
						localh = this.gerenteVerbos.seleccionaIndividualSinSignificado(str2);
						localHashMap2.put(str2, localh);
					}
					String str3 = paramArrayOfString[i];
					Particularidad localX1 = irrInfBean.getPartic();
					Particularidad localX2 = localh.getParticularidad();
					Voz localZ = irrInfBean.getVoz();
					Aspecto localk = null;
					Persona local_ = null;
					Tiempo localj = null;
					Modo localp = null;
					FuerteDebil localP = null;
					localk = irrInfBean.getAspecto();
					localP = irrInfBean.getFuerte();
					ResultadoUniversal localj1 = new ResultadoUniversal(TipoPalabra.Infinitivo, str2, null, localX2,
							localX1, localZ, str3, localj, localk, localP, local_, null, null, null, localp, null, null,
							tcps[k].preps, null);
					paramSet.add(localj1);
				}
			}
		}
		if (paramBoolean) {
			System.out.println(
					"buscaIrrIndivConjugacion: así queda el resultado después la búsqueda de irregularidades individuales en verbos amVerbal infinitivos ****************************");
			this.amUtil.debugBeans(paramSet, new String[] { "formaCanonica", "formaAccidentada" });
		}
	}

	private void paso1(Set<String> entradas, Set<TermRegInfinitivo> paramSet1,
			Map<Object[], TemaConPreps[]> paramHashMap, AACacheable paramB, boolean debug) {
		StringBuffer sbDebug = new StringBuffer();

		for (String entrada : entradas) {

			String entradaBetaDesacentuada = OpPalabras.strCompletoABeta(OpPalabras.desacentuar(entrada));
			List<TermRegInfinitivo> terminaciones = this.gerenteTermRegInfinitivo
					.seleccionaPorTerminacion(entradaBetaDesacentuada);

			for (TermRegInfinitivo tri : terminaciones) {

				String regex = tri.getRegEx();
				if ((regex != null) && (!entradaBetaDesacentuada.matches(regex))) {
					if (debug) {
						sbDebug.append(" la terminación de este registro coincide, pero no así la REGEXP \n");
						sbDebug.append("   terminación=" + tri.getTerminacion() + "\n");
						sbDebug.append("   bean=" + OpBeans.debugBean(tri) + "\n");
					}
				} else if (!this.amVerbal.silabaAcentoAceptables(entrada, tri, paramHashMap, paramB)) {
					if (debug) {
						sbDebug.append(
								" la terminación de este registro coincide, pero no así los campos SILABA-ACENTO \n");
						sbDebug.append("   terminación=" + tri.getTerminacion() + "\n");
						sbDebug.append("   bean=" + OpBeans.debugBean(tri) + "\n");

					}
				} else {
					tri.setFormaOriginal(entrada);
					paramSet1.add(tri);
				}
			}
		}
		if (debug) {
			System.out.println("después de la búsqueda de terminaciones de infinitivo (paso 1) *********************");
			System.out.println(sbDebug.toString());
			this.amUtil.debugBeans(paramSet1, new String[0]);
		}
	}

	public GerenteTermRegInfinitivo getGerenteTermRegInfinitivo() {
		return this.gerenteTermRegInfinitivo;
	}

	public void setGerenteTermRegInfinitivo(GerenteTermRegInfinitivo paramIA) {
		this.gerenteTermRegInfinitivo = paramIA;
	}

	public void setExtractorPrefijos(ExtractorPrefijos paramN) {
		this.extractorPrefijos = paramN;
	}

	public void setGerenteIrrInfinitivos(GerenteIrrInfinitivos paramaA) {
		this.gerenteIrrInfinitivos = paramaA;
	}

	public void setGerenteVerbos(GerenteVerbos paramP) {
		this.gerenteVerbos = paramP;
	}
}
