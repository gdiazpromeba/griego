/*
 * Created on Dec 25, 2004
 *
 */
package com.kalos.analisismorfologico.negocio;

import static com.kalos.enumeraciones.CompLetras.cIotaCorta;
import static com.kalos.enumeraciones.CompLetras.cIotaDieresisCorta;
import static com.kalos.enumeraciones.CompLetras.cUpsilonCorta;
import static com.kalos.enumeraciones.CompLetras.cUpsilonDieresisCorta;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.kalos.beans.IrrVerbo;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.TermRegInfinitivo;
import com.kalos.beans.TermRegParticipio;
import com.kalos.beans.TermRegVerbal;
import com.kalos.beans.TermRegVerbo;
import com.kalos.beans.TieneJuego;
import com.kalos.beans.VerboBean;
import com.kalos.beans.Verboide;
import com.kalos.datos.gerentes.GerenteIrrInfinitivos;
import com.kalos.datos.gerentes.GerenteIrrVerbos;
import com.kalos.datos.gerentes.GerenteIrrVerbosIndividuales;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.datos.gerentes.GerenteVerbosCompuestos;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.CompLetras;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Propagacion;
import com.kalos.enumeraciones.Silaba;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.TiempoOAspecto;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.enumeraciones.TipoVerbo;
import com.kalos.enumeraciones.Voz;
import com.kalos.enumeraciones.utils.TransformadorTiempoAspecto;
import com.kalos.fonts.CarPos;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.AnalisisAcento;
import com.kalos.operaciones.DesTransformaciones;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;
import com.kalos.operaciones.TiposVerbo;

//import org.apache.log4j.Logger;

/**
 * @author Gonzalo
 * 
 *         Reúne los métodos utilitarios estáticos de naturaleza verbal, a
 *         utilizar por AMVerbos, AMParticipios y AMInfinitivos
 * 
 */
public class AMVerbal<T extends TermRegVerbal> {

	@Autowired
	private AMUtil<T> amUtil;

	private ExtractorPrefijos extractorPrefijos;
	private GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales;
	private GerenteVerbosCompuestos gerenteVerbosCompuestos;
	private GerenteIrrInfinitivos gerenteIrrInfinitivos;
	private GerenteVerbos gerenteVerbos;
	private GerenteIrrVerbos gerenteIrrVerbos;

	/**
	 * @return Returns the gerenteVerbos.
	 */
	public GerenteVerbos getGerenteVerbos() {
		return gerenteVerbos;
	}

	/**
	 * @param gerenteVerbos
	 *            The gerenteVerbos to set.
	 */
	public void setGerenteVerbos(GerenteVerbos gerenteVerbos) {
		this.gerenteVerbos = gerenteVerbos;
	}

	/**
	 * @return Returns the gerenteIrrInfinitivos.
	 */
	public GerenteIrrInfinitivos getGerenteIrrInfinitivos() {
		return gerenteIrrInfinitivos;
	}

	/**
	 * @return Returns the gerenteIrrVerbosIndividuales.
	 */
	public GerenteIrrVerbosIndividuales getGerenteIrrVerbosIndividuales() {
		return gerenteIrrVerbosIndividuales;
	}

	/**
	 * Le agrega al set de resultado los registros que habíamos obtenido después
	 * del paso 3 al comparar con la tabla de IRR_VERBOS
	 * 
	 * @param serResultado
	 * @param lstIrr
	 */
	public void comparaConTemasSemirreconstruidos(Set<ResultadoUniversal> setResultado, List<T> lstIrr,
			boolean infinitivo, boolean debug) {
		Set<T> setIrr = new HashSet<>(lstIrr);
		Map<String, VerboBean> mapEntradasVerbo = new HashMap<String, VerboBean>();
		for (Iterator<T> it = setIrr.iterator(); it.hasNext();) {
			TermRegVerbal regIrr = it.next();
			String idVerbo = regIrr.getIdVerbo();
			String formaAccidentada = regIrr.getFormaOriginal();
			VerboBean ev = mapEntradasVerbo.get(idVerbo);
			if (ev == null) {
				ev = gerenteVerbos.seleccionaIndividualSinSignificado(idVerbo);
				mapEntradasVerbo.put(idVerbo, ev);
			}
			Particularidad particIrr = regIrr.getParticularidad();
			Particularidad particCanonica = ev.getParticularidad();
			List<String> preposiciones = regIrr.getPreposiciones();
			Voz voz = regIrr.getVoz();
			if (voz == Voz.Media && TiposVerbo.esDeponente(ev.getTipoVerbo()))
				voz = Voz.Activa;
			Modo modo = null;
			Tiempo tiempo = null;
			Persona persona = null;
			Aspecto aspecto = null;
			FuerteDebil fuerte = regIrr.getFuerte();
			if (!infinitivo) {

				modo = ((TermRegVerbo) regIrr).getModo();
				tiempo = ((TermRegVerbo) regIrr).getTiempo();
				persona = ((TermRegVerbo) regIrr).getPersona();
			} else {
				TiempoOAspecto toa = regIrr.getTiempoOAspecto();
				aspecto = TransformadorTiempoAspecto.comoAspecto(toa);
			}

			ResultadoUniversal reu = new ResultadoUniversal(infinitivo ? TipoPalabra.Infinitivo : TipoPalabra.Verbo,
					idVerbo, null, particCanonica, particIrr, voz, formaAccidentada, tiempo, aspecto, fuerte, persona,
					null, null, null, modo, null, null, preposiciones, null);

			setResultado.add(reu);
		}
		if (debug) {
			System.out.println(
					"así queda el resultado después del paso 6 (agregar los temas que habíamos encontrado tempranamente) **********************");
			amUtil.debugBeans(setResultado,
					new String[] { "formaAccidentada", "formaCanonica", "temaPropuesto", "terminacion" });
		}
	}

	/**
	 * Esta función recibe todas las formas que es posible reconstruir antes de
	 * comprobar con formas de diccionario "auténticas" de la tabla diccionario.
	 * (No incluye los temas recuperadpos de IRR_VERBOS, que se agregan
	 * después). Las formas tentativas están en el campo "TemaPropuesto" de los
	 * registros del map entrante. Hacemos dos pasadas, porque también
	 * comparamos otra vez las formas de la lista entrante, ahora pasivadas. El
	 * parmetro "pasivar" me dice si pasivo a la froma antes de contestar.
	 * Nótese que los elementos que se agregan a la lista resultado ya no son
	 * nodos
	 */

	public <T extends TermRegVerbal> void buscaReconstruidaEnTablas(Set<ResultadoUniversal> setResultado,
			Map<TemaConPreps, HashSet<T>> mapTemasPropuestos, boolean pasivar, boolean verboide, boolean debug) {
		Set<TemaConPreps> keySet = mapTemasPropuestos.keySet();
		StringBuffer sbDebug = new StringBuffer();
		for (Iterator<TemaConPreps> it = keySet.iterator(); it.hasNext();) {
			TemaConPreps clave = (TemaConPreps) it.next();
			String temaPropuesto = OpPalabras.strBetaACompleto(clave.resto);
			String temaPropuestoADB;
			if (temaPropuesto == null)
				continue;
			if (pasivar)
				temaPropuesto = OpPalabras.canonicaAMedia(temaPropuesto);
			if (temaPropuesto == null)// no pudo encontrar una media aceptable
				continue;

			temaPropuestoADB = OpPalabras.strCompletoABeta(temaPropuesto);
			if (debug) {
				sbDebug.append("buscando en tablas " + temaPropuestoADB + "\n");
			}

			// recojo los las EntradaDiccionario
			List<VerboBean> verbos = gerenteVerbos.seleccionaPorVerboParaAM(temaPropuestoADB);
			for (VerboBean ev : verbos) {
				// descarto los dibujados, ya que esto es reconstrucción
				if (ev.isDibujado())
					continue;
				Set<T> valueListCanons = mapTemasPropuestos.get(clave);
				for (Iterator<T> f = valueListCanons.iterator(); f.hasNext();) {
					T beanCanonica = f.next();
					String formaAccidentada = OpBeans.getPropiedad(beanCanonica, "formaOriginalCompuesta");
					// String significado = ev.getSignificado();
					Particularidad particCanonica = ev.getParticularidad();
					Particularidad particIrr = null;
					List<String> preposiciones = beanCanonica.getPreposiciones();
					Voz voz = beanCanonica.getVoz();
					if (pasivar && voz.equals(Voz.Media))
						voz = Voz.Activa;
					Modo modo = null;
					Tiempo tiempo = null;
					Aspecto aspecto = null;
					Persona persona = null;
					if (!verboide) {
						modo = ((TermRegVerbo) beanCanonica).getModo();
						tiempo = ((TermRegVerbo) beanCanonica).getTiempo();
						persona = ((TermRegVerbo) beanCanonica).getPersona();
					} else {
						aspecto = (Aspecto) beanCanonica.getTiempoOAspecto();
					}

					ResultadoUniversal reu = new ResultadoUniversal(
							verboide ? TipoPalabra.Infinitivo : TipoPalabra.Verbo, ev.getId(), null, particCanonica,
							particIrr, voz, formaAccidentada, tiempo, aspecto, FuerteDebil.Debil, persona, null, null,
							null, modo, null, null, preposiciones, null);

					setResultado.add(reu);
				}
			}
		}
		if (debug) {
			System.out.println(
					"AMVerbal.buscaReconstruidaEnTablas (comprobación contra formas de diccionario auténticas )*********************");
			System.out.println(sbDebug.toString());
			System.out.println("así queda el resultado   ***************************");
			amUtil.debugBeans(setResultado, new String[] { "formaCanonica", "formaOriginal" });
		}
	}

	/**
	 * Evalúa si el registro que encontró de la tabla TERM_REG_VERBOS es
	 * aceptable respecto de la acentuación de la forma original.
	 * 
	 * @param formaOriginal
	 * @param regDesinencia
	 */
	public boolean silabaAcentoAceptables(String formaOriginal, TermRegVerbal beanDesinencia,
			Map<Object[], TemaConPreps[]> cacheExtraccionPrefijos, AACacheable cacheAA) {
		AnalisisAcento aaOrig = cacheAA.getAnalisisAcento(formaOriginal);
		Silaba silaba = beanDesinencia.getSilaba();
		Acento acento = beanDesinencia.getAcento();

		if (silaba == null)
			return true;
		if (silaba.equals(Silaba.ninguna) && acento.equals(Acento.Ninguno)
				&& (aaOrig.actuales.silaba != aaOrig.sugeridos.silaba
						|| aaOrig.actuales.tipoAcento != aaOrig.actuales.tipoAcento)) {
			// intento extraerle extractorPrefijos para excusar que no coincida
			int hastaDondeAnalizo = aaOrig.actuales.indiceLetraAcentuada - 1;
			if (hastaDondeAnalizo > 0) {
				TemaConPreps[] tcp = extractorPrefijos.averiguaPrefijos(formaOriginal, hastaDondeAnalizo,
						cacheExtraccionPrefijos);
				for (int i = 1; i < tcp.length; i++) { // el primero es siempre
					// la forma original
					AnalisisAcento aaResto = AnalisisAcento.getAnalisisAcento(tcp[i].resto);
					if (aaResto.actuales.silaba == aaResto.sugeridos.silaba
							|| aaResto.actuales.tipoAcento == aaResto.actuales.tipoAcento)
						return true;
					// TODO: de alguna manera, debería indicar que esta
					// aceptabilidad tiene como condición el considerar a ese
					// verbo como
					// compuesto solamente, en el exterior de esta rutina
				}
			}

			return false;
		} else if (silaba != Silaba.ninguna && !acento.equals(Acento.Ninguno)
				&& (aaOrig.actuales.silaba != silaba.valorEntero() || !aaOrig.actuales.tipoAcento.equals(acento))) {
			return false;
		} else if (silaba != Silaba.ninguna && Silaba.getEnum(aaOrig.actuales.silaba) != silaba) {
			return false;
		} else if (beanDesinencia.getTipoDesinencia() == TipoVerbo.NoHojas.VOCALICO_NO_CONTRACTO
				&& silaba == Silaba.getEnum(aaOrig.actuales.silaba)) {
			// cuando la TERM_REG fue generada for las desinencias
			// "vocálicas no contractas", la
			// sílaba puede ser forzada, pero si lo es, el acento debe ser
			// normal para la sílaba
			// por ejemplo: la TERM_REG_INFINITIVO DE BALEI=N (inf aoristo)
			// tiene marcada la sílaba
			// -1, pero BALEI/N no es válido
			return aaOrig.actuales.esTipoAcentonaturalDadaPosicion;
		} else
			return true;
	}

	/**
	 * Se invoca desde el paso2 si el registro en cuestión parece ser
	 * VOCALICO_NO_CONTRACTO. Investigando la vocal unitiva se determina su es
	 * un auténtico verbo en -U o -I, o si es un consonántico. Si es un verbo en
	 * -U o -I se genera un nodo para estos tipos de verbo sin importar el
	 * tiempo. Si resulta ser un consonántico sólo se generan nodos si el
	 * aspecto es infectivo Investiga y convierte esa proto-clasificación en un
	 * auténtico tipo de verbo, investigando su vocal unitiva.
	 * 
	 * @param setSiguiente
	 *            el set en el cual se pueblan los resultados siguientes
	 * @param nodo
	 *            el nodo del cual también se cuelgan otros nodos con los
	 *            registros resultado
	 */
	private void desarrollaTipoW(Set<T> setSiguiente, TermRegVerbal regW) {
		String formaOriginal = regW.getFormaOriginal();
		// todo lo que pued obtener de aqué estoy seguro que no es vocálico
		// contracto, así que
		// las reglas de acentuación "estrictas" se aplican
		boolean esInfinitivo = (regW instanceof Verboide);
		if (!esInfinitivo && !esLicitoEstricto(formaOriginal))
			return;

		String desinencia = regW.getTerminacion();
		String foMenosDes = OpPalabras.comeFinal(formaOriginal, desinencia.length());
		CarPos letraUnitiva = CarPos.getCarPos(formaOriginal, formaOriginal.length() - 1 - desinencia.length());
		// mudos, líquidos, dobles, en épsilon, en iota
		// siempre puede ser una forma muy corta a la que la desinencia de tabla
		// le come todo, y no queda letra unitiva
		if (letraUnitiva != null) {
			T aAgregar = regW.clona();
			// para épsilon y iota se generan nodos sin importar el aspecto
			if (letraUnitiva.getLetraBase() == cUpsilonCorta || letraUnitiva.getLetraBase() == cUpsilonDieresisCorta
					|| letraUnitiva.getLetraBase() == cIotaCorta || letraUnitiva.getLetraBase() == cIotaDieresisCorta) {
				aAgregar.setTipoVerboExtendido(amUtil.tiempoExtendidoLetra(letraUnitiva.getLetraBase()));
			} else {
				TiempoOAspecto toa = regW.getTiempoOAspecto();
				Tiempo tiempo = TransformadorTiempoAspecto.comoTiempo(toa);
				if (tiempo.compareTo(Tiempo.Imperfecto) <= 0) {
					// consonantes mudas, líquidas y dobles, iota, épsilon
					if (letraUnitiva.esMuda() || letraUnitiva.esLiquida() || letraUnitiva.esDoble()) {
						aAgregar.setTipoVerboExtendido(amUtil.tiempoExtendidoLetra(letraUnitiva.getLetraBase()));
					} else if (foMenosDes.endsWith("SS")) {
						aAgregar.setTipoVerboExtendido(TipoVerbo.DOBLE_SIGMA_NORMAL);
					} else if (!foMenosDes.endsWith("TT")) {
						aAgregar.setTipoVerboExtendido(TipoVerbo.DOBLE_TAU_NORMAL);
						// terminacion=new String(new char[]{cTau, cTau}) +
						// desinencia;
					} else {
						throw new RuntimeException("incapaz de encontrar el tipoVerboExtendido para el siguiente tema "
								+ OpPalabras.strCompletoABeta(foMenosDes));
					}
				}
			}
			if (aAgregar.getTipoVerboExtendido() != 0) {
				aAgregar.setTerminacion(desinencia);
				setSiguiente.add((T) aAgregar);
			}

		}
	}

	/**
	 * Expande los nodos de la lista anterior, creando o enganchando para cada
	 * TIPO_DESINENCIA, uno o más tipos de verbo extendido.
	 */
	public void extiendeTipos(Set<T> setOriginal, Set<T> setSiguiente, boolean debug) {
		for (Iterator<T> it = setOriginal.iterator(); it.hasNext();) {
			T trv = it.next();
			trv.setTerminacion(OpPalabras.strBetaACompleto(trv.getTerminacion()));
			// TermRegVerbo trvNuevo = (TermRegVerbo) trv.clona();
			String formaOriginal = trv.getFormaOriginal();
			CarPos letraUnitiva = CarPos.getCarPos(formaOriginal,
					formaOriginal.length() - 1 - trv.getTerminacion().length());
			T trvNuevo = null;

			int tipoDesinencia = trv.getTipoDesinencia();
			TiempoOAspecto toa = trv.getTiempoOAspecto();
			Tiempo tiempo = TransformadorTiempoAspecto.comoTiempo(toa);

			// si es un tipo de desinencia==-1 (cualquier cosa que no cuadre en
			// los tipos de desinencia establecidos)
			// sigo de largo a la espera de la búsqueda por temas
			if (tipoDesinencia < 0) {
				trvNuevo = trv.clona();
				trvNuevo.setTipoVerboExtendido(tipoDesinencia);
				setSiguiente.add(trvNuevo);
				continue;
			}

			// si es una aoristo segundo, sigo de largo, esperando hasta la
			// búsqueda de temas
			FuerteDebil fuerte = trv.getFuerte();
			if (tiempo == Tiempo.Aoristo && fuerte == FuerteDebil.Fuerte) {
				trvNuevo = trv.clona();
				trvNuevo.setTipoVerboExtendido(tipoDesinencia);
				setSiguiente.add(trvNuevo);
				continue;
			}

			// mi, numi
			if (tipoDesinencia == TipoVerbo.NoHojas.MI_PROPIAMENTE) {
				trvNuevo = trv.clona();
				trvNuevo.setTipoVerboExtendido(TipoVerbo.MI_PROPIAMENTE_NORMAL);
				setSiguiente.add(trvNuevo);
			} else if (tipoDesinencia == TipoVerbo.NoHojas.NUMI) {
				trvNuevo = trv.clona();
				trvNuevo.setTipoVerboExtendido(TipoVerbo.NUMI_NORMAL);
				setSiguiente.add(trvNuevo);
			}

			// el VOCALICO_NO_CONTRACTO significa que termina en tiene
			// terminación en W, pero puede querer
			// decir cualquier cosa: un mudo, un en dzeta, un líquido (m/n/ll)
			// infectivo en los consonánticos, o un vocálico no contracto
			// en cualquier tiempo
			if (tipoDesinencia == TipoVerbo.NoHojas.VOCALICO_NO_CONTRACTO) {
				desarrollaTipoW(setSiguiente, trv);
			}

			// si el tipo_desinencia==1 y el tiempo no es infectivo, agrego de
			// cajón la posibilidad de que sea un dental, un dobelsigma, un
			// dobletau o un dzeta
			// que perdió la consonante (ninguno de los cuales est'a
			// representado en la lista de terminaciones_regulares)
			// No agrego dental si es pasiva y la terminación no comienza con S,
			// ya que es YEUSQ.., no YEUQ...
			if (tipoDesinencia == TipoVerbo.NoHojas.VOCALICO_NO_CONTRACTO && tiempo.valorEntero() > 2) {
				if (trv.getTerminacion().charAt(0) == CompLetras.cSigma || trv.getVoz() != Voz.Pasiva) {
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.TAU_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.DELTA_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.THETA_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.DOBLE_SIGMA_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.DOBLE_TAU_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.DZETA_NORMAL);
					setSiguiente.add(trvNuevo);
				}
			}

			// confectivo y prefectivo de líquidos
			if (tiempo.compareTo(Tiempo.Futuro) >= 0 && tipoDesinencia == TipoVerbo.NoHojas.CONSONANTICO_LIQUIDO) {
				trvNuevo = trv.clona();
				trvNuevo.setTipoVerboExtendido(amUtil.tiempoExtendidoLetra(letraUnitiva.getCaracter()));
				setSiguiente.add(trvNuevo);
			}

			// consonánticos mudos en tiempos no infectivos
			if (letraUnitiva != null && tiempo.compareTo(Tiempo.Futuro) >= 0
					&& (tipoDesinencia == TipoVerbo.NoHojas.CONSONANTICO_GUTURALES
							|| tipoDesinencia == TipoVerbo.NoHojas.CONSONANTICO_LABIALES
							|| tipoDesinencia == TipoVerbo.NoHojas.CONSONANTICO_DENTALES)) {
				if (tipoDesinencia == TipoVerbo.NoHojas.CONSONANTICO_GUTURALES) {// guturales
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.GAMMA_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.JI_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.KAPPA_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.DOBLE_SIGMA_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.DOBLE_TAU_NORMAL);
					setSiguiente.add(trvNuevo);
				} else if (tipoDesinencia == TipoVerbo.NoHojas.CONSONANTICO_LABIALES) {
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.PI_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.FI_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.BETA_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.PI_TAU_NORMAL);
					setSiguiente.add(trvNuevo);
				} else if (tipoDesinencia == TipoVerbo.NoHojas.CONSONANTICO_DENTALES) {
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.DELTA_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.THETA_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.TAU_NORMAL);
					setSiguiente.add(trvNuevo);
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(TipoVerbo.DZETA_NORMAL);
					setSiguiente.add(trvNuevo);
				} // infectivo de los consonánticos líquidos -- la terminación
					// se forma con la letra más las terminaciones básicas
				else if (letraUnitiva != null && tipoDesinencia == TipoVerbo.NoHojas.VOCALICO_NO_CONTRACTO
						&& letraUnitiva.esLiquida() && (tiempo == Tiempo.Presente | tiempo == Tiempo.Imperfecto)) {
					trvNuevo = trv.clona();
					trvNuevo.setTipoVerboExtendido(amUtil.tiempoExtendidoLetra(letraUnitiva.getCaracter()));
					trvNuevo.setTerminacion(
							new String(new char[] { letraUnitiva.getCaracter() }) + trv.getTerminacion());
					if (formaOriginal.endsWith(trvNuevo.getTerminacion()))
						setSiguiente.add(trvNuevo);
				}
			}

			// vocálicos contractos en tiempos infectivos o no infectivos (no se
			// hace nada porque la desinencia ya está completa y el
			// tipo_verbo_extendido es el mismo que que el tipo_desinencia
			// sólo fuerzo el "normal"
			else if (tipoDesinencia == TipoVerbo.NoHojas.VOCALICO_CONTRACTO_ALFA
					|| tipoDesinencia == TipoVerbo.NoHojas.VOCALICO_CONTRACTO_EPSILON
					|| tipoDesinencia == TipoVerbo.NoHojas.VOCALICO_CONTRACTO_OMICRON) {
				trvNuevo = trv.clona();
				switch (tipoDesinencia) {
				case TipoVerbo.NoHojas.VOCALICO_CONTRACTO_ALFA:
					trvNuevo.setTipoVerboExtendido(TipoVerbo.VC_ALFA_NORMAL);
					break;
				case TipoVerbo.NoHojas.VOCALICO_CONTRACTO_EPSILON:
					trvNuevo.setTipoVerboExtendido(TipoVerbo.VC_EPSILON_NORMAL);
					break;
				case TipoVerbo.NoHojas.VOCALICO_CONTRACTO_OMICRON:
					trvNuevo.setTipoVerboExtendido(TipoVerbo.VC_OMICRON_NORMAL);
					break;
				}
				setSiguiente.add(trvNuevo);
			}
		}
		if (debug) {
			System.out.println("el setsigiente después de 2 (expansión a tipo-verbo-extendido) **********************");
			amUtil.debugSet(setSiguiente, new String[] { "FORMA_ORIGINAL", "GEN_PROPUESTO", "NOM_PROPUESTO" });
		}
	}

	/**
	 * puebla el campo "tema Propuesto". Si puede, a la colección de "regulares"
	 * reconstruidos, si no puede, se lo agrega al set de irregulares y se
	 * puebla dicho campo
	 * 
	 * @param reconstruidos
	 *            el set al que primariamente se intentará poblarle el
	 *            "temaPropuesto"
	 * @param irregulares
	 *            el set al que alternativamente se intentará poblarle el
	 *            "temaPropuesto"
	 * @param debug
	 */
	public void incorporaTemaPropuestoAReconstruidos(Collection<ObjYDest<T>> reconstruidos, Collection<ObjYDest<T>> irregulares, boolean debug) {
		
		//separo en líquidos y no líquidos
		Map<Boolean, List<ObjYDest<T>>> liqNoLiq = reconstruidos.stream().collect(
		  partitioningBy(oyd->  TiposVerbo.esLiquido(oyd.getRegistro().getTipoVerboExtendido()))
		);
		
		//los líquidos, los resto del original
		List<ObjYDest<T>> liquidos = liqNoLiq.get(Boolean.TRUE);
		reconstruidos.removeAll(liquidos);
		
		//luego les calculo (a los liquidos) su propio tema, y los agrego a irregulares, desde donde seguirán su camino a la canonización
		liquidos = liquidos.stream().map( oyd -> {
			T reg = oyd.getRegistro();
			TiempoOAspecto toa = reg.getTiempoOAspecto();
			String temaPropuesto;
			if (TransformadorTiempoAspecto.comoTiempo(toa) == Tiempo.Futuro) {
				temaPropuesto = reg.getFormaDestransformada().substring(0,reg.getFormaDestransformada().length() - reg.getTerminacion().length());
				temaPropuesto = temaPropuesto.concat(OpPalabras.strBetaACompleto("E"));
			} else {
				temaPropuesto = reg.getFormaDestransformada().substring(0,reg.getFormaDestransformada().length() - reg.getTerminacion().length());
			}
			reg.setTemaPropuesto(temaPropuesto);
			return oyd;
		})
		.collect(toList());
		irregulares.addAll(liquidos);
				
        //a los no líquidos, les quito también las forma demasiado cortas
		List<ObjYDest<T>> noLiq = liqNoLiq.get(Boolean.FALSE);
		noLiq = noLiq.stream().filter(oyd -> {
          	T reg = oyd.getRegistro();
          	return reg.getTerminacion().length() <= reg.getFormaDestransformada().length(); 
		})
		.collect(toList());
		
		//finalmente, a los no líquidos también les doy su tema propuesto, y siguen camino a la canonización
		noLiq = noLiq.stream().map( oyd->{
			T reg = oyd.getRegistro();
			String temaPropuesto = reg.getFormaDestransformada().substring(0, reg.getFormaDestransformada().length() - reg.getTerminacion().length());
			reg.setTemaPropuesto(temaPropuesto);
			return oyd;
		})
	    .collect(toList());
		
        //esto no hace falta porque las referencias de reconstruidos ya fueron modificadas
		//		reconstruidos.clear();
        //		reconstruidos.addAll(noLiq);

		if (debug) {
			System.out.println("AMVerbal.incorporaTemasPropuestoReconstruido ********");
			System.out.println(" el set 'regular' con 'temaPropuesto' poblado (si se pudo): ");
			amUtil.debugSet(reconstruidos, new String[] { "FORMA_ORIGINAL", "TERMINACION", "FORMA_DESTRANSFORMADA",
					"TEMA_PROPUESTO", "NOM_PROPUESTO", "GEN_PROPUESTO", "TERM_VERBALIZADA", "FORMA_A_DESTRANSFORMAR" });
			System.out.println(" el set 'irregular' con 'temaPropuesto' poblado (si se pudo): ");
			amUtil.debugSet(irregulares, new String[] { "FORMA_ORIGINAL", "TERMINACION", "FORMA_DESTRANSFORMADA",
					"TEMA_PROPUESTO", "NOM_PROPUESTO", "GEN_PROPUESTO", "TERM_VERBALIZADA", "FORMA_A_DESTRANSFORMAR" });

		}
	}
	
	


	public void incorporaTemaPropuestoIrregulares(List<ObjYDest<T>> irregulares, boolean debug) {
		
		//actuo sólo sobre los que no tienen tema propuesto ya
		//(a algunos, se los pudo haber agregado incorporaRemaPropuestoAReconstruidos)
		List<ObjYDest<T>> sinTP = irregulares.stream().filter( oyd->{
		  T trv = oyd.getRegistro();
		  return trv.getTemaPropuesto()==null;
		})
	    .collect(toList());
		
		//este grupo de elementos a poblar se divide en dos: los futuros líquidos no pasivos, y todo el resto
		Map<Boolean, List<ObjYDest<T>>> futLiqNopOResto = sinTP.stream().collect(
		  partitioningBy( oyd->{
			  T trv = oyd.getRegistro();
			  boolean esLiquido = TiposVerbo.esLiquido(trv.getTipoDesinencia());
			  TiempoOAspecto toa = trv.getTiempoOAspecto();
			  boolean esFuturo = TransformadorTiempoAspecto.comoTiempo(toa) == Tiempo.Futuro;
			  return (esLiquido && esFuturo && trv.getVoz() != Voz.Pasiva);
		  })
		);
	    List<ObjYDest<T>> futLiqNop = futLiqNopOResto.get(Boolean.TRUE);
	    List<ObjYDest<T>> resto = futLiqNopOResto.get(Boolean.FALSE);
	    
	    //les aplico su transformación a los futuros líquidos no pasivos
	    futLiqNop.stream().map(oyd->{
	    	T trv = oyd.getRegistro();
	    	String temaPropuesto = trv.getFormaDestransformada().substring(0, trv.getFormaDestransformada().length() - trv.getTerminacion().length());
			temaPropuesto = temaPropuesto.concat(OpPalabras.strBetaACompleto("E"));
	    	trv.setTemaPropuesto(temaPropuesto);
	    	return oyd;
	    })
	    .collect(toList());
	    
	    //del resto, primero elimino los muy cortos (que son de todos modos irregulares)
	    //permito que la terminación sea hasta igual de larga que la forma
	    List<ObjYDest<T>> restoCortos = resto.stream().filter(oyd->{
	    	T trv = oyd.getRegistro();
	    	return trv.getFormaDestransformada().length() <= trv.getTerminacion().length();
	    })
	    .collect(toList());
	    //eliminarlos significa restarlos de irregulares
	    irregulares.removeAll(restoCortos);
		
	    
	    //al resto no cortos, les aplico la transformación que puebla el tema propuesto.
	    //Esta sería la transformación "normal"
	    resto.removeAll(restoCortos);
	    resto.stream().map(oyd->{
	    	T trv = oyd.getRegistro();
	    	String temaPropuesto = trv.getFormaDestransformada().substring(0, trv.getFormaDestransformada().length() - trv.getTerminacion().length());
	    	trv.setTemaPropuesto(temaPropuesto);
	    	return oyd;
	    })
	    .collect(toList());	    

		if (debug) {
			System.out.println("*** incorporaTemaPropuestoIrregulares ***");
			amUtil.debugBeans(irregulares, new String[] { "FORMA_ORIGINAL", "TERMINACION", "FORMA_DESTRANSFORMADA",
					"TEMA_PROPUESTO", "NOM_PROPUESTO", "GEN_PROPUESTO", "TERM_VERBALIZADA", "FORMA_A_DESTRANSFORMAR" });
		}
	}	


	/**
	 * Se aplica a una forma verbal que ya sé que no es de un vocálico contracto
	 * infectivo
	 * 
	 * @param verbo
	 * @return
	 */
	private boolean esLicitoEstricto(String verbo) {
		AnalisisAcento aa = OpPalabras.getAnalisisAcento(verbo);
		return aa.actuales.licitoEstricto;
	}

	/**
	 * Pega el tema que se propone de conjuntos anteriores con la terminación
	 * (creada por esta rutina) Esta función sirve tanto para los verbos como
	 * para los infinitivos. porque el campo "tiempoOAspecto" acepta el campo
	 * TIEMPO de los verbos o el campo ASPECTO de los infinitivos
	 */
	public void restauraForma(T trv,  List<String> lstResultados, AACacheable cacheAA) {
		Voz voz = trv.getVoz();
		TiempoOAspecto tiempoOAspecto = trv.getTiempoOAspecto();
		String temaPropuesto = trv.getTemaPropuesto();
		Tiempo tiempo = TransformadorTiempoAspecto.comoTiempo(tiempoOAspecto);
		String terminacion = trv.getTerminacion();
		int conjugacion = trv.getTipoVerboExtendido();
		String sAux;
		String sTema = OpPalabras.desacentuar(temaPropuesto);
		switch (conjugacion) {
		case TipoVerbo.UPSILON_NORMAL:
			// si la forma termina en diptongo, por ejemplo E)/LUON, no tengo manera de saber si
			// el tema era originalmente con vocal breve (LU/W) o con vocal larga (LU_/W) , de modo
			// que agrego las dos posibilidades.
			// Lo mismo pasa si el tema es pasivo, se acorta y no tengo manera de saber si procede de un
			// tema largo o corto
			// Lo mismo si es un tema de perfectivo
			boolean esPerfectivo = (tiempo.equals(Tiempo.Perfecto) || tiempo.equals(Tiempo.Pluscuamperfecto));
			boolean esTemaPasivo = voz.equals(Voz.Pasiva) && (tiempo.equals(Tiempo.Futuro) || tiempo.equals(Tiempo.Aoristo));
			if ((terminacion.length() > 1
					&& OpPalabras.esDiptongo(sTema.charAt(sTema.length() - 1), terminacion.charAt(0))) || esTemaPasivo
					|| esPerfectivo) {
				String formaFinal = OpPalabras.comeFinal(sTema, 1) + OpPalabras.strBetaACompleto("U/W");
				formaFinal = OpPalabras.acentua(formaFinal, -2, Acento.Agudo);
				lstResultados.add(formaFinal);
				formaFinal = OpPalabras.comeFinal(sTema, 1) + OpPalabras.strBetaACompleto("U_/W");
				formaFinal = OpPalabras.acentua(formaFinal, -2, Acento.Agudo);
				lstResultados.add(formaFinal);

			} else {
				String formaFinal = sTema + OpPalabras.strBetaACompleto("W");
				formaFinal = OpPalabras.acentua(formaFinal, -2, Acento.Agudo);
				lstResultados.add(formaFinal);
			}
			break;
		case TipoVerbo.IOTA_NORMAL:
			// idem que con la upsilon, primero verifico diptongo
			if ((terminacion.length() > 1 && OpPalabras.esDiptongo(sTema.charAt(sTema.length() - 1), terminacion.charAt(0)))
					|| (voz == Voz.Pasiva && (tiempo.equals(Tiempo.Futuro) || tiempo.equals(Tiempo.Aoristo))
							|| (tiempo.equals(Tiempo.Perfecto) || tiempo.equals(Tiempo.Pluscuamperfecto)))) {
				String formaFinal = OpPalabras.comeFinal(sTema, 1) + OpPalabras.strBetaACompleto("I/W");
				formaFinal = OpPalabras.acentua(formaFinal, -2, Acento.Agudo);
				lstResultados.add(formaFinal);
				formaFinal = OpPalabras.comeFinal(sTema, 1) + OpPalabras.strBetaACompleto("I_/W");
				formaFinal = OpPalabras.acentua(formaFinal, -2, Acento.Agudo);
				lstResultados.add(formaFinal);
			} else {
				String formaFinal = sTema + OpPalabras.strBetaACompleto("W");
				formaFinal = OpPalabras.acentua(formaFinal, -2, Acento.Agudo);
				lstResultados.add(formaFinal);
			}
			break;
		case TipoVerbo.VC_EPSILON_NORMAL:
		case TipoVerbo.VC_ALFA_NORMAL:
		case TipoVerbo.VC_OMICRON_NORMAL:
			sTema += OpPalabras.strBetaACompleto("W");
			lstResultados.add(OpPalabras.acentua(sTema, -2, Acento.Agudo));
			break;
		case TipoVerbo.LAMBDA_NORMAL:
		case TipoVerbo.LAMBDA_DEP:
		case TipoVerbo.NU_NORMAL:
		case TipoVerbo.NU_DEP:
		case TipoVerbo.RHO_NORMAL:
		case TipoVerbo.RHO_DEP:
		case TipoVerbo.DELTA_NORMAL:
		case TipoVerbo.DELTA_DEP:
		case TipoVerbo.DOBLE_SIGMA_NORMAL:
		case TipoVerbo.DOBLE_SIGMA_DEP:
		case TipoVerbo.DOBLE_TAU_NORMAL:
		case TipoVerbo.DOBLE_TAU_DEP:
		case TipoVerbo.TAU_NORMAL:
		case TipoVerbo.TAU_DEP:
		case TipoVerbo.THETA_NORMAL:
		case TipoVerbo.THETA_DEP:
		case TipoVerbo.GAMMA_NORMAL:
		case TipoVerbo.GAMMA_DEP:
		case TipoVerbo.KAPPA_NORMAL:
		case TipoVerbo.KAPPA_DEP:
		case TipoVerbo.JI_NORMAL:
		case TipoVerbo.JI_DEP:
		case TipoVerbo.BETA_NORMAL:
		case TipoVerbo.BETA_DEP:
		case TipoVerbo.PI_NORMAL:
		case TipoVerbo.PI_DEP:
		case TipoVerbo.FI_NORMAL:
		case TipoVerbo.FI_DEP:
		case TipoVerbo.PI_TAU_NORMAL:
		case TipoVerbo.PI_TAU_DEP:
		case TipoVerbo.DZETA_NORMAL:
		case TipoVerbo.DZETA_DEP:
		case TipoVerbo.XI_NORMAL:
		case TipoVerbo.XI_DEP:
		case TipoVerbo.PSI_NORMAL:
		case TipoVerbo.PSI_DEP:
			// tratamiento similar para todos los verbos mudos, líquidos, dobles
			// o en dzeta
			// (es decir, todos los listados en tiempoExtendidoConsonante)
			if (tiempo.equals(Tiempo.Presente) || tiempo.equals(Tiempo.Imperfecto)) { // infectivo
				// para el infectivo
				sAux = sTema.concat(OpPalabras.strBetaACompleto("W"));
				AnalisisAcento aaAux = cacheAA.getAnalisisAcento(sAux);
				if (aaAux.cantidadDeSilabas >= 2) {
					sAux = OpPalabras.acentua(sAux, -2, Acento.Agudo);
					lstResultados.add(sAux);
				}
			} else { // no infectivo
				// labiales
				if (conjugacion == TipoVerbo.BETA_NORMAL || conjugacion == TipoVerbo.PI_NORMAL
						|| conjugacion == TipoVerbo.FI_NORMAL || conjugacion == TipoVerbo.PI_TAU_NORMAL) {
					sAux = sTema.concat(OpPalabras.strBetaACompleto("W"));
					sAux = OpPalabras.acentua(sAux);
					lstResultados.add(sAux);
					// guturales
				} else if (conjugacion == TipoVerbo.GAMMA_NORMAL || conjugacion == TipoVerbo.KAPPA_NORMAL
						|| conjugacion == TipoVerbo.JI_NORMAL || conjugacion == TipoVerbo.DOBLE_TAU_NORMAL
						|| conjugacion == TipoVerbo.DOBLE_SIGMA_NORMAL) {
					sAux = sTema.concat(OpPalabras.strBetaACompleto("W"));
					sAux = OpPalabras.acentua(sAux);
					lstResultados.add(sAux);
					// dentales
				} else if (TiposVerbo.esDentalOEnDZeta(conjugacion)) {
					// los dentales reemplazan su consonante por una "S", de
					// modo que si el tema termina en "S", esta "S" hay que
					// quitarla del tema antes de pegarle la desinencia
					if (sTema.endsWith(OpPalabras.strBetaACompleto("S")))
						sTema = OpPalabras.comeFinal(sTema, 1);

					if (conjugacion == TipoVerbo.DELTA_NORMAL) {
						sAux = sTema.concat(OpPalabras.strBetaACompleto("DW"));
						sAux = OpPalabras.acentua(sAux);
						lstResultados.add(sAux);
					} else if (conjugacion == TipoVerbo.TAU_NORMAL) {
						sAux = sTema.concat(OpPalabras.strBetaACompleto("TW"));
						sAux = OpPalabras.acentua(sAux);
						lstResultados.add(sAux);
					} else if (conjugacion == TipoVerbo.THETA_NORMAL) {
						sAux = sTema.concat(OpPalabras.strBetaACompleto("QW"));
						sAux = OpPalabras.acentua(sAux);
						lstResultados.add(sAux);
					} else if (conjugacion == TipoVerbo.DZETA_NORMAL) {
						sAux = sTema + OpPalabras.strBetaACompleto("ZW");
						sAux = OpPalabras.acentua(sAux);
						lstResultados.add(sAux);
					}

				}
			} // del if infectivo-no infectivo
			break;
		}

	}

	/**
	 * consiste en llamar a "Restauraformas", que pega los temas obtenidos con
	 * las terminaciones
	 */
	@SuppressWarnings("unchecked")
	public void envoltorioRestauraFormas(Set<ObjYDest<T>> setOriginal, Set<T> setSiguiente, AACacheable cacheAA, boolean debug) {
		//descarto los fuertes, ya que son intrínsicamente irregulares
	  setOriginal = setOriginal.stream()
				.filter(oyd -> oyd.getRegistro().getFuerte()!= FuerteDebil.Fuerte)
				.collect(toSet());
		
		setOriginal.stream().forEach( oyd ->{
				T trv =  oyd.getRegistro();
				List<String> lstResTemas = new ArrayList<String>();
				restauraForma(trv, lstResTemas, cacheAA);
				Set<T> nuevos = lstResTemas.stream().map(s->{
				  T regNew = trv.clona();
				  regNew.setTemaPropuesto(s);
			    return regNew;	  
				}).collect(toSet());
				setSiguiente.addAll(nuevos);
		});
		
		if (debug) {
			System.out.println("AMVerbal.envoltorioRestauraFormas (pegar el tema con la terminación) *************");
			System.out.println("  el set de entrada mide " + setOriginal.size());
			System.out.println("  el set de siguiente es ");
			amUtil.debugBeans(setSiguiente, new String[] { "terminacion", "formaOriginal", "temaPropuesto",
					"formaOriginalCompuesta", "formaADestransformar", "formaDestransformada" });
		}
	}

	/**
	 * Basándose en lo que ya obtuve con la tabla de IRR_VERBOS, puebla la lista
	 * resultadosIrr con los registros de IRR_VERBOS que sean compatibles con lo
	 * que ya reconstruí. El registro que se agrega a la lista es igual en
	 * estructura a los de la tabla IRR_VERBOS, excepto por los siguientes
	 * campos agregados -("FORMA_ORIGINAL") la forma tipeada ya interpretada en
	 * una combinaciónespecífica de cortas y largas -("PREPOSICIONES") que a
	 * esta altura ya fueron averiguadas
	 * 
	 * @param lstTemas
	 *            : una lista de registros reconstruidos que contiene temas que
	 *            ya es posible buscar en IRR_VERBOS en su campo TEMA_PROPUESTO.
	 * @param resultadosIrr
	 *            (salida)la lista a la que van a parar los resultados positivos
	 *            de haber encontrado un tema aceptable
	 * @param busquedasHechas
	 *            map que puede contener queries ya hechos en IRR_VERBOS para un
	 *            determinado tema.
	 */
	public void encuentraTemasTemprano(List<ObjYDest<T>> lstTemas, List<T> resultadosIrr,
			Map<String, List<IrrVerbo>> busquedasHechas, boolean debug) {
		StringBuffer sbDebug = new StringBuffer();

		for (ObjYDest<T> nodo : lstTemas) {
			encuentraTemasTempranoIndividual(nodo, resultadosIrr, busquedasHechas, sbDebug, debug);
		}
		if (debug) {
			System.out.println(
					"encuentraTemasTemprano <ObjYDest>******************************************************************");
			System.out.println(sbDebug.toString());
			System.out.println("  así queda resultadosIrr: ");
			amUtil.debugSet(resultadosIrr,
					new String[] { "terminacion", "temaPropuesto", "terminacionVerbalizada", "formaOriginal",
							"formaCompuesta", "formaADestransformar", "formaDestransformada", "temaPropuesto" });
		}
	}

	public void encuentraTemasTemprano(Set<ObjYDest<T>> nodos, List<T> resultadosIrr,
			Map<String, List<IrrVerbo>> busquedasHechas, boolean debug) {
		StringBuffer sbDebug = new StringBuffer();

		for (ObjYDest<T> regDest : nodos) {
			encuentraTemasTempranoIndividual(regDest, resultadosIrr, busquedasHechas, sbDebug, debug);
		}
		if (debug) {
			System.out.println("AMVerbal.encuentraTemasTemprano *************************************");
			System.out.println(sbDebug.toString());
			System.out.println("  así queda resultadosIrr: ");
			amUtil.debugBeans(resultadosIrr, new String[] { "terminacion", "terminacionVerbalizada", "formaOriginal",
					"formaCompuesta", "formaADestransformar", "formaDestransformada", "temaPropuesto" });
		}
	}

	/**
	 * Transforma una destransformación en una lista de objetos del mismo tipo
	 * que los del objeto de la destransformación, pero depurada en base a
	 * comparar con la información existente en la tabla IRR_VERBOS
	 * 
	 * @param regDest
	 * @param resultadosIrr
	 * @param busquedasHechas
	 * @param sbDebug
	 * @param debug
	 */
	@SuppressWarnings("unchecked")
	public <T extends TermRegVerbal> void encuentraTemasTempranoIndividual(ObjYDest<T> regDest, List<T> resultadosIrr,
			Map<String, List<IrrVerbo>> busquedasHechas, StringBuffer sbDebug, boolean debug) {
		TermRegVerbal regTemas = (TermRegVerbal) regDest.getRegistro();
		DesTransformaciones desTrans = regDest.getDestransformacion();
		String temaPropuesto = regTemas.getTemaPropuesto();
		if (temaPropuesto.equals("")) {
			return;
		}
		String tema = OpPalabras.desacentuar(temaPropuesto);
		if (debug) {
			sbDebug.append("  en encuentraTemasTemprano, averiguando por: " + OpPalabras.strCompletoABeta(tema) + "\n");
		}
		TiempoOAspecto toa = regTemas.getTiempoOAspecto();
		Tiempo tiempoReconstruido = TransformadorTiempoAspecto.comoTiempo(toa);
		Voz voz = regTemas.getVoz();
		boolean esVerboide = false;
		Modo modoTemas = null;
		if (regTemas instanceof TermRegInfinitivo || regTemas instanceof TermRegParticipio) {
			esVerboide = true;
		} else {
			modoTemas = ((TermRegVerbo) regTemas).getModo();
		}

		try {
			tema = OpPalabras.desacentuar(tema);
			HashMap<Object[], TemaConPreps[]> cacheExtraccionPrefijos = new HashMap<Object[], TemaConPreps[]>();
			TemaConPreps[] arrTcp = extractorPrefijos.averiguaPrefijos(tema, 1, cacheExtraccionPrefijos);

			for (TemaConPreps tcp : arrTcp) {
				tema = tcp.resto;
				tema = OpPalabras.strCompletoABeta(tema);
				List<IrrVerbo> irrvs = busquedasHechas.get(tema);
				if (irrvs == null) {
					irrvs = gerenteIrrVerbos.seleccionaPorTema(tema);
					busquedasHechas.put(tema, irrvs);
				}
				if (debug && irrvs.size() > 0) {
					sbDebug.append("    " + irrvs.size() + " a comparar con el reconstruido "
							+ OpBeans.debugBean(regTemas, new String[] { "temaPropuesto", "formaOriginal",
									"formaADestransformar", "formaOrignialCompuesta", "terminacion" })
							+ "\n");
				}
				for (IrrVerbo irrVerbo : irrvs) {
					Propagacion propaga = irrVerbo.getPropagacion();
					Modo modoIrr = irrVerbo.getModo();

					Voz jvoz = irrVerbo.getVozJuego();

					// si el modo del registro de temas reconstruidos no es el
					// mismo o resultado de una propagación de
					// irregularidades, no puedo usarlo
					if (modoTemas != modoIrr && ( /* verboides */modoTemas != null || modoIrr != Modo.Indicativo)
							&& (modoIrr != Modo.Indicativo || !propaga.esDeModo())) {
						if (debug) {
							sbDebug.append("    rechazando porque el modo del registro reconstruido (" + modoTemas
									+ ") no es igual que el de los registros de iregularidad (" + modoIrr
									+ "), ni hay una propagación 'TodosLosModos' involucrada\n");
						}
						continue;
					}

					// lo siguiente lo hago para asegurarme que no continúo con
					// registros que una vez aumentados/reduplicados
					// no coincidirían con mi forma
					if (desTrans.isReduplicacion() != irrVerbo.isReduplicacion()) {
						if (debug) {
							sbDebug.append("    uno rechazado por distinta reduplicación, registro destransformado="
									+ desTrans.isReduplicacion() + " irrVerbo=" + irrVerbo.isReduplicacion() + "\n");
						}
						continue;
					} else if (!esVerboide && (modoTemas == Modo.Indicativo
							&& (tiempoReconstruido == Tiempo.Imperfecto || tiempoReconstruido == Tiempo.Aoristo)
							&& desTrans.getAumento() != irrVerbo.getAumento())) {
						if (debug) {
							sbDebug.append(
									"    uno rechazado por intentar reconstruir una destransformación aumentada de un no-indicativo\n");
						}
						continue;
					}
					// fin del segmento comprobador
					if (comparacionAceptable(regTemas, irrVerbo, sbDebug, debug)) {
						T nuevoReconstruido = (T) regTemas.clona();
						if (!esVerboide) {
							((TermRegVerbo) nuevoReconstruido).setPersona(((TermRegVerbo) regTemas).getPersona());
						}
						nuevoReconstruido.setIdVerbo(irrVerbo.getVerboId());
						nuevoReconstruido.setParticularidad(irrVerbo.getPartic());

						// modifico la voz y el modo cuando la irregularidad que
						// encontró se debe a propagaciones

						if (voz.equals(Voz.Media) && jvoz.equals(Voz.Activa) && propaga.esDeVoz())
							nuevoReconstruido.setVoz(voz);
						if (nuevoReconstruido instanceof TermRegVerbo && modoTemas != Modo.Indicativo
								&& modoIrr == Modo.Indicativo && propaga.esDeModo())
							((TermRegVerbo) nuevoReconstruido).setModo(modoTemas);
						nuevoReconstruido.setPreposiciones(tcp.preps);
						if (tcp.preps != null && tcp.preps.size() > 0) {
							nuevoReconstruido.setCompuesto(true);
						} else {
							nuevoReconstruido.setCompuesto(false);
						}
						if (debug) {
							sbDebug.append("    se agrega al resultado="
									+ OpBeans.debugBean(nuevoReconstruido, new String[] {}) + "\n");
						}
						resultadosIrr.add(nuevoReconstruido);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Compara valores del registro reconstruido con valores de IRR_VERBOS cuyo
	 * tema coincide, y decide si la similaridad es suficienta para agregar ese
	 * registro de irregularidad a la lista de resultados posibles. Es una
	 * función utilitaria de encuentraTemasTemprano
	 * 
	 * @param reconstruido
	 * @param irregularidad
	 * @return
	 */
	private boolean comparacionAceptable(TermRegVerbal reconstruido, IrrVerbo irregularidad, StringBuffer sbDebug, boolean debug) {
		TiempoOAspecto toaReconstruido = reconstruido.getTiempoOAspecto();
		boolean fuerteReconstruido = reconstruido.getFuerte() == FuerteDebil.Fuerte;
		Voz vozReconstruida = reconstruido.getVoz();
		// si no hay modo reconstruido, es que estamos reconstruyendo e partir
		// de un infinitivo o un participo
		Modo modo = null;
		boolean esTermRegVerbo = reconstruido instanceof TermRegVerbo;
		if (esTermRegVerbo)
			modo = ((TermRegVerbo) reconstruido).getModo();
		Voz jvoz = irregularidad.getVozJuego();
		Tiempo ntie = irregularidad.getTiempo();
		boolean fuerte = irregularidad.getFuerte() == FuerteDebil.Fuerte;
		Modo modoIrr = irregularidad.getModo();
		Contraccion contraccion = irregularidad.getContraccion();
		Propagacion propagacion = irregularidad.getPropagacion();
		int tieneContComedora = 0;
		if (reconstruido instanceof TermRegParticipio) {
			tieneContComedora = ((TermRegParticipio) reconstruido).getContraccionComedora();
		}
		// tiempos: el reconstruido debe coincidir con el irregular, o el
		// reconstruido ser infectivo de un irregular aoristo segundo
		// o el reconstruido ser presente de un irregular futuro ático
		boolean tiemposOK = false;

		int valorToa;
		if (toaReconstruido instanceof Tiempo) {
			valorToa = ((Tiempo) toaReconstruido).valorEntero();
		} else {
			valorToa = ((Aspecto) toaReconstruido).valorEntero();
		}

		if (valorToa == ntie.valorEntero() && fuerte == fuerteReconstruido) {
			tiemposOK = true;
		} else if (valorToa == Tiempo.Imperfecto.valorEntero() && irregularidad.getTiempo().equals(Tiempo.Presente)
				&& irregularidad.isPats()) {
			tiemposOK = true;
		} else {
			tiemposOK = false;
		}

		// voces: deben coincidir o la voz reconstruida debe ser media y la
		// irregularidad activa propagante
		boolean vozOK = false;
		if (vozReconstruida == jvoz)
			vozOK = true;
		else if (vozReconstruida == Voz.Media && jvoz.equals(Voz.Activa) && propagacion.esDeVoz())
			vozOK = true;
		else
			vozOK = false;

		// modos: deben coincidir o el modo reconstruido no ser indicativo y la
		// irregularidad indicativo propagante
		boolean modoOK = false;
		if (!esTermRegVerbo) // es un infinitivo
			modoOK = true;
		else if (modo == modoIrr)
			modoOK = true;
		else if (esTermRegVerbo && modoIrr.equals(Modo.Indicativo) && !modo.equals(Modo.Indicativo)
				&& propagacion.esDeModo())
			modoOK = true;
		else
			modoOK = false;

		// juego de desinencias: si el verbo reconstruido tiene una columna
		// JUEGO (como viene de dato desde TRANS_PARTICIPIOS)
		// con algunas desinencias que requieren juego 2, por ejemplo, entonces
		// dicho juego indicado tiene que coincidir con el
		// campo JUEGO del registro de IRR_VERBOS
		boolean juegoOK = true;
		boolean reconstruidoTieneJuego = (reconstruido instanceof TieneJuego);
		if (reconstruidoTieneJuego) {
			int juegoReconstruido = ((TieneJuego) reconstruido).getJuego();
			if (juegoReconstruido != 0) {
				int juegoIrr = irregularidad.getJuego();
				if (juegoReconstruido != juegoIrr) {
					juegoOK = false;
				}
			}
		}

		boolean contraccionOK = true;
		if (tieneContComedora != 0) {
			if (!ntie.equals(Tiempo.Aoristo))
				contraccionOK = true; // sólo en el aoristo funciona la
			// contracción comedora
			else if (tieneContComedora == 1 && contraccion.equals(Contraccion.comePrimera))
				contraccionOK = true;
			else if (tieneContComedora == -1 && !contraccion.equals(Contraccion.comePrimera))
				contraccionOK = true;
			else if (tieneContComedora == 0)
				contraccionOK = true;
			else
				contraccionOK = false;
		}

		boolean resultado = (tiemposOK && vozOK && modoOK && juegoOK && contraccionOK);
		if (!resultado && debug) {
			if (!tiemposOK) {
				sbDebug.append("    no coincide en el TIEMPO con el registro de IRR_VERBOS:  "
						+ OpBeans.debugBean(irregularidad, new String[] {}) + "\n");
			} else if (!vozOK) {
				sbDebug.append("    no coinciden en la VOZ con el registro de IRR_VERBOS:  "
						+ OpBeans.debugBean(irregularidad, new String[] {}) + "\n");
			} else if (!modoOK) {
				sbDebug.append("    no coinciden en el MODO con el registro de IRR_VERBOS:  "
						+ OpBeans.debugBean(irregularidad, new String[] {}) + "\n");
			} else if (!juegoOK) {
				sbDebug.append("    no coincide en el JUEGO REQUERIDO con el registro de IRR_VERBOS:  "
						+ OpBeans.debugBean(irregularidad, new String[] {}) + "\n");
			} else if (!contraccionOK) {
				sbDebug.append(
						"    la especificación de COTRACCIÓN COMEDORA no coincide con el registro de IRR_VERBOS:  "
								+ OpBeans.debugBean(irregularidad, new String[] {}) + "\n");
			}

		}

		return resultado;
	}

	/**
	 * 
	 * @param setSiguiente
	 *            conjunto de entrada con lo reconstruido regularmente
	 * @param resultadosIrr
	 *            lista de salida con temas irregulares
	 * @param aBuscarPorTema
	 *            conjunto de entrada con temas irregulares
	 * @param debug
	 */
	public void aplicaEncuentraTemasTemprano(Set<ObjYDest<T>> setSiguiente, List<T> resultadosIrr,
			List<ObjYDest<T>> aBuscarPorTema, boolean debug) {
		resultadosIrr.clear();
		Map<String, List<IrrVerbo>> busquedasHechas = new HashMap<String, List<IrrVerbo>>();
		encuentraTemasTemprano(setSiguiente, resultadosIrr, busquedasHechas, debug);
		encuentraTemasTemprano(aBuscarPorTema, resultadosIrr, busquedasHechas, debug);
		if (debug) {
			System.out.println("AMVerbal.aplicaEncuentraTemasTemprano ********************");
			System.out.println(" el conjunto regular de entrada medía " + setSiguiente.size());
			System.out.println(" el conjunto de temas irregulares de entrada medía " + aBuscarPorTema.size());
			System.out.println(" el resultado es: ");
			amUtil.debugBeans(resultadosIrr, new String[] {});
		}
	}

	/**
	 * Agrega al set de análisis las posibilidades de extractorPrefijos. Si
	 * quiero analizar sólo extractorPrefijos,
	 * ExtractorPrefijos.averiguaPreposiciones se encarga de tomar todos los
	 * nodos menos el raíz (que es lo que le entra).
	 * 
	 * @param setOriginal
	 * @param setSiguiente
	 */
	@SuppressWarnings("unchecked")
	public void averiguaPreposiciones(Collection<T> setOriginal, Set<T> setSiguiente, int nivelPreposiciones,
			Map<Object[], TemaConPreps[]> cacheExtraccionPrefijos, boolean debug) {

		// si el nivel de extractorPrefijos es 0, simplemente copio el set
		if (nivelPreposiciones == ExtractorPrefijos.NADA) {
			setSiguiente.addAll(setOriginal);
			return;
		}
		for (TermRegVerbal trv : setOriginal) {
			String formaOriginal = trv.getFormaOriginal();

			String terminacion = trv.getTerminacion();
			TemaConPreps[] arrTcp = extractorPrefijos.averiguaPrefijos(formaOriginal, terminacion.length(),
					cacheExtraccionPrefijos);
			for (int i = 0; i < arrTcp.length; i++) {
				T trvNuevo = (T) trv.clona();
				trvNuevo.setFormaOriginalCompuesta(formaOriginal);

				// les caía en la preposición sean incluidas
				trvNuevo.setFormaOriginal(arrTcp[i].resto);
				List<String> preposiciones = new ArrayList<String>();
				for (int e = 0; e < arrTcp[i].preps.size(); e++) {
					preposiciones.add(arrTcp[i].preps.get(e));
				}
				trvNuevo.setPreposiciones(preposiciones);
				setSiguiente.add(trvNuevo);
			}
		}
		if (debug) {
			System.out.println(
					"el set siguiente después de la extracción de extractorPrefijos  ***********************************");
			amUtil.debugBeans(setSiguiente, new String[] { "formaOriginal", "formaOriginalCompuesta", "terminacion" });
		}
	}

	public void pueblaCanonicasVerbos(Collection<ResultadoUniversal> reus) {
		// creación de la lista de ids
		List<String> ids = new ArrayList<String>();

		for (Iterator<ResultadoUniversal> it = reus.iterator(); it.hasNext();) {
			ResultadoUniversal reu = it.next();
			List<String> preposiciones = reu.getPreposiciones();
			boolean tienePreps = preposiciones != null && preposiciones.size() > 0;
			if (tienePreps) {
				String idCompuesto = gerenteVerbosCompuestos.seleccionaPorVerboSimpleYPreps(reu.getId(), preposiciones);
				reu.setIdCompuesto(idCompuesto);
			}
			if (tienePreps && reu.getIdCompuesto() == null) {
				it.remove();
			}
			ids.add(reu.getIdSimpleOCompuesto());
		}

		// obtención de la lista de entradas de verbo. Creo un mapita
		// id-entrada.
		List<VerboBean> eas = gerenteVerbos.seleccionaPorIdsParaAM(ids);
		Map<String, VerboBean> mapaEntradas = new HashMap<String, VerboBean>();
		for (VerboBean ea : eas) {
			mapaEntradas.put(ea.getId(), ea);
		}

		// poblamiento de la forma canónica
		// (compuesta si la hay, si no simple)
		for (ResultadoUniversal reu : reus) {
			String id = reu.getIdSimpleOCompuesto();
			VerboBean ea = mapaEntradas.get(id);
			String canonica = ea.getVerbo();
			if (ea.getSufijo() != null) {
				canonica = canonica.concat(" " + ea.getSufijo());
			}
			canonica = OpPalabras.strBetaACompleto(canonica);
			reu.setFormaCanonica(canonica);
		}
	}

	/**
	 * @return Returns the gerenteIrrVerbos.
	 */
	public GerenteIrrVerbos getGerenteIrrVerbos() {
		return gerenteIrrVerbos;
	}

	/**
	 * @param gerenteIrrVerbos
	 *            The gerenteIrrVerbos to set.
	 */
	public void setGerenteIrrVerbos(GerenteIrrVerbos gerenteIrrVerbos) {
		this.gerenteIrrVerbos = gerenteIrrVerbos;
	}

	/**
	 * @return Returns the extractorPrefijos.
	 */
	public ExtractorPrefijos getExtractorPrefijos() {
		return extractorPrefijos;
	}

	/**
	 * @param extractorPrefijos
	 *            The extractorPrefijos to set.
	 */
	public void setExtractorPrefijos(ExtractorPrefijos preposiciones) {
		this.extractorPrefijos = preposiciones;
	}

	public void setGerenteIrrVerbosIndividuales(GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales) {
		this.gerenteIrrVerbosIndividuales = gerenteIrrVerbosIndividuales;
	}

	public void setGerenteIrrInfinitivos(GerenteIrrInfinitivos gerenteIrrInfinitivos) {
		this.gerenteIrrInfinitivos = gerenteIrrInfinitivos;
	}

	/**
	 * @param gerenteVerbosCompuestos
	 *            The gerenteVerbosCompuestos to set.
	 */
	public void setGerenteVerbosCompuestos(GerenteVerbosCompuestos gerenteVerbosCompuestos) {
		this.gerenteVerbosCompuestos = gerenteVerbosCompuestos;
	}

}
