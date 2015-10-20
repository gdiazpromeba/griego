package com.kalos.analisismorfologico.negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kalos.beans.IrrSustantivoBean;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.SustantivoBean;
import com.kalos.beans.TermRegSustantivo;
import com.kalos.beans.TermRegVerbal;
import com.kalos.beans.TipoJerarquico;
import com.kalos.beans.TipoSustantivo;
import com.kalos.datos.gerentes.GerenteIrrSustantivos;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteTiposSustantivo;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.flexion.declinacion.Declina;
import com.kalos.flexion.declinacion.OcNominal;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AMSustantivos <T extends TermRegVerbal> implements AnalizadorMorfologico, ApplicationContextAware {
	
	private AMNominal amNominal;
	private GerenteSustantivos gerenteSustantivos;
	private GerenteIrrSustantivos gerenteIrrSustantivos;
	private GerenteTiposSustantivo gerenteTiposSustantivo;
	private Declina declina;
	private boolean Y = false;
	private ApplicationContext applicationContext;
	private static String cadenaTiposHoja;
	private final Set<Integer> setTipos = new HashSet<Integer>();
	
	@Autowired
	private AMUtil<T> amUtil;
	

	public void setApplicationContext(ApplicationContext paramApplicationContext) {
		this.applicationContext = paramApplicationContext;
	}

	private void paso1() {
		if (!this.Y) {
			this.gerenteTiposSustantivo = (GerenteTiposSustantivo) this.applicationContext
					.getBean("gerenteTiposSustantivo");
			this.amNominal = (AMNominal) this.applicationContext.getBean("amNominal");
			this.gerenteIrrSustantivos = (GerenteIrrSustantivos) this.applicationContext
					.getBean("gerenteIrrSustantivos");
			this.gerenteSustantivos = (GerenteSustantivos) this.applicationContext.getBean("gerenteSustantivos");
			this.declina = (Declina) this.applicationContext.getBean("declina");
			constructorVago();
			this.Y = true;
		}
	}

	public long buscaCanonica(String[] formas, Set<ResultadoUniversal> paramHashSet, AACacheable paramB, boolean paramBoolean1, boolean paramBoolean2) {
		paso1();

		Set<TermRegSustantivo> setPpal = new HashSet<TermRegSustantivo>();
		Set<TermRegSustantivo> setTrs2 = new HashSet<TermRegSustantivo>();
		long l1 = System.currentTimeMillis();
		Set<String> entradas = new HashSet<String>(Arrays.asList(formas));
		this.amNominal.paso1(entradas, setPpal, paramB, paramBoolean2);
		// amUtil.conservaSolo(setPpal, new String[]{"caso", "numero",
		// "tipoSustantivo"}, new Object[]{Caso.Acusativo, Numero.Singular,
		// 12});
		this.amNominal.corrigePluralizados(setPpal);
		revisaNomGen(setPpal, paramHashSet, paramBoolean2);
		this.amNominal.reconstruyeTemas(setPpal, setTrs2, paramB, paramBoolean2);
		paso2(setTrs2, paramHashSet, paramBoolean2);
		paso3(formas, paramHashSet, paramBoolean2);
		paso4(formas, paramHashSet, paramBoolean2);
		paso5(paramHashSet);
		if (paramBoolean1) {
			paso6(paramHashSet);
		}
		long l2 = System.currentTimeMillis();
		long l3 = l2 - l1;
		if (paramBoolean2) {
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			localGregorianCalendar.setTimeInMillis(l3);
			System.out.println("tardó " + localGregorianCalendar.get(12) + " minutos " + localGregorianCalendar.get(13)+ " segundos " + localGregorianCalendar.get(14) + " milisegundos");
		}
		return l3;
	}

	private void paso5(Collection<ResultadoUniversal> paramCollection) {
		List<String> ids = new ArrayList<String>();
		Iterator<ResultadoUniversal> itRes = paramCollection.iterator();
		while (itRes.hasNext()) {
			ResultadoUniversal localObject2 = itRes.next();
			if (localObject2.getFormaCanonica() == null) {
				ids.add(localObject2.getId());
			}
		}
		List<SustantivoBean> beans = this.gerenteSustantivos.getBeans(ids);
		Map<String, SustantivoBean> mapBeans = new HashMap<String, SustantivoBean>();
		Iterator<SustantivoBean> itBeans = beans.iterator();
		while (itBeans.hasNext()) {
			SustantivoBean bean = itBeans.next();
			mapBeans.put(bean.getId(), bean);
		}
		Iterator<ResultadoUniversal> itRes2 = paramCollection.iterator();
		while (itRes2.hasNext()) {
			ResultadoUniversal res = itRes2.next();
			if (res.getFormaCanonica() == null) {
				SustantivoBean subsBean = mapBeans.get(res.getId());
				String nomOGen = subsBean.primerCampoNoVacio();
				nomOGen = OpPalabras.strBetaACompleto(nomOGen);
				res.setFormaCanonica(nomOGen);
			}
		}
	}

	private boolean contieneTipo(TermRegSustantivo param_) {
		String str = param_.getTiposHoja();
		String[] arrayOfString = str.split("\\-");
		boolean bool = false;
		for (int i = 0; i < arrayOfString.length; i++) {
			if (!arrayOfString[i].equals("")) {
				Integer localInteger = Integer.valueOf(Integer.parseInt(arrayOfString[i]));
				if (!this.setTipos.contains(localInteger)) {
					bool = true;
					break;
				}
			}
		}
		return bool;
	}

	public void constructorVago() {
		List<TipoSustantivo> lstBeansTipoSust = this.gerenteTiposSustantivo.getTodos();
		Set<Integer> localHashSet = new HashSet<Integer>();
		Iterator<TipoSustantivo> itLstBeansTipoSust = lstBeansTipoSust.iterator();
		Genero genero;
		List<TipoJerarquico> tiposHoja;
		while (itLstBeansTipoSust.hasNext()) {
			TipoSustantivo tipSust = itLstBeansTipoSust.next();
			genero = tipSust.getGeneroSugerido();
			if (genero == Genero.Neutro) {
				tiposHoja = this.gerenteTiposSustantivo.getTiposHoja(tipSust.getId());
				Iterator<TipoJerarquico> localIterator = tiposHoja.iterator();
				while (localIterator.hasNext()) {
					TipoJerarquico locale = localIterator.next();
					localHashSet.add(Integer.valueOf(locale.getValorEntero()));
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("(.*)(");
		Iterator<Integer> localObject2 = localHashSet.iterator();
		while (localObject2.hasNext()) {
			int i = localObject2.next().intValue();
			sb.append("-" + i + "-");
			if (localObject2.hasNext()) {
				sb.append("|");
			}
		}
		sb.append(")(.*)");
		cadenaTiposHoja = sb.toString();
		Iterator<TipoSustantivo> itTipSust = lstBeansTipoSust.iterator();
		while (localObject2.hasNext()) {
			TipoSustantivo ts = itTipSust.next();
			TipoPalabra tipPalabra = ts.getTipoPalabra();
			if ((tipPalabra == TipoPalabra.Interjeccion) || (tipPalabra == TipoPalabra.Conjuncion)) {
				this.setTipos.add(Integer.valueOf(ts.getValorEntero()));
			}
		}
	}

	/**
	 * revisa los que ya son nominativo o genitivo singular
	 * 
	 * @param terminaciones
	 * @param setSalida
	 * @param debug
	 */
	public void revisaNomGen(Collection<TermRegSustantivo> terminaciones, Set<ResultadoUniversal> setSalida,
			boolean debug) {
		StringBuffer debugSb = new StringBuffer();
		Set<TermRegSustantivo> localHashSet = new HashSet<TermRegSustantivo>();
		Map<String, List<SustantivoBean>> localHashMap1 = new HashMap<String, List<SustantivoBean>>();
		Map<String, List<SustantivoBean>> localHashMap2 = new HashMap<String, List<SustantivoBean>>();
		Iterator<TermRegSustantivo> localIterator1 = terminaciones.iterator();
		while (localIterator1.hasNext()) {
			TermRegSustantivo termRegSust = localIterator1.next();
			termRegSust = termRegSust.clona();
			if (!contieneTipo(termRegSust)) {
				if (debug) {
					debugSb.append("descartando por no ser sustantivo:  " + termRegSust.toString() + " \n");
				}
			} else {
				Caso caso = termRegSust.getCaso();
				Numero numero = termRegSust.getNumero();
				String formaOriginal = termRegSust.getFormaOriginal();
				boolean esNomOrGen = (caso == Caso.Genitivo) || (caso == Caso.Nominativo);
				boolean esSingular = numero == Numero.Singular;
				if (esNomOrGen && esSingular) {
					String str2 = OpPalabras.strCompletoABeta(formaOriginal);
					if (debug) {
						debugSb.append("buscando en revisaNomGen caso=" + caso + " parametro  " + str2 + " \n");
					}
					List<SustantivoBean> localList;
					if (caso == Caso.Nominativo) {
						localList = localHashMap1.get(str2);
						if (localList == null) {
							localList = this.gerenteSustantivos.seleccionaPorNominativoParaAM(str2);
							localHashMap1.put(str2, localList);
						}
					} else {
						localList = localHashMap2.get(str2);
						if (localList == null) {
							localList = this.gerenteSustantivos.seleccionaPorGenitivoParaAM(str2);
							localHashMap2.put(str2, localList);
						}
					}
					Iterator<SustantivoBean> localIterator2 = localList.iterator();
					while (localIterator2.hasNext()) {
						SustantivoBean locali = localIterator2.next();
						String str3 = termRegSust.getTiposHoja();
						String str4 = "-" + locali.getTipoNominal() + "-";
						if (str3.indexOf(str4) == -1) {
							if (debug) {
								debugSb.append("  rechazando:  " + locali.getNominativo() + " - ");
								debugSb.append(locali.getGenitivo() + " tipo " + locali.getTipoNominal());
								debugSb.append("  porque su tipo ");
								debugSb.append(
										" no está contenido en la lista de posibles tipos hoja=" + str3 + "  \n");
							}
						} else {
							if (debug) {
								debugSb.append("  va a resultados:  " + termRegSust.toString() + " \n");
							}
							ResultadoUniversal localj1 = new ResultadoUniversal(TipoPalabra.Sustantivo, locali.getId(),
									null, locali.getPartic(), null, null, formaOriginal, null, null, null, null, caso,
									locali.getGenero(), numero, null, null,
									OpPalabras.strBetaACompleto(locali.getNominativo()), null, null);
							setSalida.add(localj1);
							if (caso == Caso.Nominativo) {
								if (termRegSust.getTiposHoja().matches(cadenaTiposHoja)) {
									ResultadoUniversal localj2 = localj1.clona();
									localj2.setCaso(Caso.Vocativo);
									setSalida.add(localj2);
									ResultadoUniversal localj3 = localj1.clona();
									localj3.setCaso(Caso.Acusativo);
									setSalida.add(localj3);
									if (debug) {
										debugSb.append("expandido a Vocativo y Acusativo por ser neutro \n");
									}
								} else {
									int k = locali.getTipoNominal();
									int m = 0;
									for (int n = 0; n < this.amNominal.getNomVocDistintos().length; n++) {
										if (k == this.amNominal.getNomVocDistintos()[n].intValue()) {
											m = 1;
											break;
										}
										if (m == 0) {
											ResultadoUniversal resVoc = localj1.clona();
											resVoc.setCaso(Caso.Vocativo);
											setSalida.add(resVoc);
											if (debug) {
												debugSb.append("expandido a Vocativo por tener Nom=Voc \n");
											}
										}
									}
								}
							}
						}
					}
					localHashSet.add(termRegSust);
				}
			}
		}
		terminaciones.removeAll(localHashSet);
		if (debug) {
			System.out.println("revisaNomGen **************");
			System.out.println(debugSb.toString());
			System.out.println("resultados ");
			this.amUtil.debugSet(setSalida, new String[0]);
		}
	}

	private void paso2(Set<TermRegSustantivo> terminaciones, Set<ResultadoUniversal> resultados, boolean paramBoolean) {
		StringBuffer localStringBuffer = new StringBuffer();
		Map<String, List<SustantivoBean>> localHashMap1 = new HashMap<String, List<SustantivoBean>>();
		Map<String, List<SustantivoBean>> localHashMap2 = new HashMap<String, List<SustantivoBean>>();
		Iterator<TermRegSustantivo> localIterator1 = terminaciones.iterator();
		while (localIterator1.hasNext()) {
			TermRegSustantivo trs = localIterator1.next();
			String str1 = OpPalabras.strCompletoABeta(trs.getNominativoPropuesto());
			String str2 = OpPalabras.strCompletoABeta(trs.getGenitivoPropuesto());
			List<SustantivoBean> localList = null;
			if (str1 != null) {
				localList = localHashMap1.get(str1);
				if (localList == null) {
					if (paramBoolean) {
						localStringBuffer.append("buscando nominativo  ..." + str1 + " \n");
					}
					localList = this.gerenteSustantivos.seleccionaPorNominativoParaAM(str1);
					localHashMap1.put(str1, localList);
				} else if (paramBoolean) {
					localStringBuffer.append("reusando búsqueda nominativo  ..." + str1 + " \n");
				}
			} else if (str2 != null) {
				localList = localHashMap2.get(str2);
				if (localList == null) {
					if (paramBoolean) {
						localStringBuffer.append("buscando genitivo  ..." + str2 + " \n");
					}
					localList = this.gerenteSustantivos.seleccionaPorGenitivoParaAM(str2);
					localHashMap2.put(str2, localList);
				} else if (paramBoolean) {
					localStringBuffer.append("reusando búsqueda genitivo fácil ..." + str2 + " \n");
				}
			}
			Iterator<SustantivoBean> localIterator2 = localList.iterator();
			while (localIterator2.hasNext()) {
				SustantivoBean locali = localIterator2.next();
				String str3 = trs.getTiposHoja();
				String str4 = "-" + locali.getTipoNominal() + "-";
				if (str3.indexOf(str4) > -1) {
					if (paramBoolean) {
						localStringBuffer.append("va a resultados" + trs.toString() + " \n");
						localStringBuffer
								.append("  porque el tipo " + str4 + " existe dentro de las hojas " + str3 + " \n");
					}
					ResultadoUniversal localj = new ResultadoUniversal(TipoPalabra.Sustantivo, locali.getId(), null,
							locali.getPartic(), null, null, trs.getFormaOriginal(), null, null, null, null,
							trs.getCaso(), locali.getGenero(), trs.getNumero(), null, null,
							OpPalabras.strBetaACompleto(locali.getNominativo()), null, null);
					resultados.add(localj);
				} else if (paramBoolean) {
					localStringBuffer.append("   se encontró el sustantivo " + locali.getNominativo());
					localStringBuffer.append(" - " + locali.getGenitivo() + " tipo=" + locali.getTipoNominal());
					localStringBuffer
							.append(" pero su tipo no concuerda con los tipos hoja propuestos=" + str3 + " \n");
				}
			}
		}
		if (paramBoolean) {
			System.out.println("buscaNomGen ******************");
			System.out.println(localStringBuffer.toString());
			System.out.println("resultados: ");
			this.amUtil.debugBeans(resultados, new String[] { "FORMA_DECLINADA", "GENITIVO", "NOMINATIVO" });
		}
	}

	private void paso6(Set<ResultadoUniversal> paramSet) {
		Iterator<ResultadoUniversal> localIterator = paramSet.iterator();
		while (localIterator.hasNext()) {
			ResultadoUniversal localj = localIterator.next();
			EnumMap<Particularidad, OcNominal> localEnumMap = this.declina.declina(localj.getId());
			OcNominal localD = localEnumMap.get(localj.getParticularidad());
			if (localD == null) {
				localIterator.remove();
			} else {
				List<String> localList = localD.getFormas(localj.getCaso(), localj.getNumero());
				if (localList == null) {
					localIterator.remove();
				} else if (!localList.contains(localj.getFormaAccidentada())) {
					localIterator.remove();
				}
			}
		}
	}

	private void paso3(String[] paramArrayOfString, Set<ResultadoUniversal> paramSet, boolean paramBoolean) {
		for (int i = 0; i < paramArrayOfString.length; i++) {
			String str1 = paramArrayOfString[i];
			String str2 = OpPalabras.strCompletoABeta(paramArrayOfString[i]);
			List<IrrSustantivoBean> localList = this.gerenteIrrSustantivos.seleccionaPorForma(str2);
			Iterator<IrrSustantivoBean> localIterator = localList.iterator();
			while (localIterator.hasNext()) {
				IrrSustantivoBean localU = localIterator.next();
				String str3 = localU.getSustantivoId();
				SustantivoBean locali = this.gerenteSustantivos.seleccionaIndividualParaAM(str3);
				ResultadoUniversal localj = new ResultadoUniversal(TipoPalabra.Sustantivo, str3, null,
						locali.getPartic(), localU.getParticularidad(), null, str1, null, null, null, null,
						localU.getCaso(), locali.getGenero(), localU.getNumero(), null, null,
						OpPalabras.strBetaACompleto(locali.getNominativo()), null, null);
				paramSet.add(localj);
			}
		}
		if (paramBoolean) {
			System.out.println("resultados luego de la búsqueda de irregularidades de adjetivos");
			this.amUtil.debugSet(paramSet, new String[] { "nominativo", "genitivo" });
		}
	}

	private void paso4(String[] paramArrayOfString, Set<ResultadoUniversal> paramSet, boolean paramBoolean) {
		for (int i = 0; i < paramArrayOfString.length; i++) {
			String str1 = paramArrayOfString[i];
			String str2 = OpPalabras.strCompletoABeta(paramArrayOfString[i]);
			List<SustantivoBean> localList = this.gerenteSustantivos.seleccionaInvariables(str2);
			Iterator<SustantivoBean> localIterator = localList.iterator();
			while (localIterator.hasNext()) {
				SustantivoBean locali = localIterator.next();
				ResultadoUniversal localj = new ResultadoUniversal(TipoPalabra.Sustantivo, locali.getId(), null,
						locali.getPartic(), null, null, str1, null, null, null, null, Caso.Nominativo,
						locali.getGenero(), Numero.Singular, null, null,
						OpPalabras.strBetaACompleto(locali.getNominativo()), null, null);
				paramSet.add(localj);
			}
		}
		if (paramBoolean) {
			System.out.println("resultados luego de la búsqueda de invariables ");
			this.amUtil.debugSet(paramSet, new String[] { "nominativo", "genitivo" });
		}
	}



	public AMNominal getAmNominal() {
		return this.amNominal;
	}

	public void setAmNominal(AMNominal paramR) {
		this.amNominal = paramR;
	}

	public void setGerenteSustantivos(GerenteSustantivos paramj) {
		this.gerenteSustantivos = paramj;
	}

	public void setGerenteIrrSustantivos(GerenteIrrSustantivos paramSA) {
		this.gerenteIrrSustantivos = paramSA;
	}

	public void setDeclina(Declina paramE) {
		this.declina = paramE;
	}
}
