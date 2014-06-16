/*
 * Created on 12/03/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.analisismorfologico.negocio;

import static kalos.enumeraciones.CompLetras.cAlfaCorta;
import static kalos.enumeraciones.CompLetras.cBeta;
import static kalos.enumeraciones.CompLetras.cDelta;
import static kalos.enumeraciones.CompLetras.cDzeta;
import static kalos.enumeraciones.CompLetras.cEpsilon;
import static kalos.enumeraciones.CompLetras.cFi;
import static kalos.enumeraciones.CompLetras.cGamma;
import static kalos.enumeraciones.CompLetras.cIotaCorta;
import static kalos.enumeraciones.CompLetras.cJi;
import static kalos.enumeraciones.CompLetras.cKappa;
import static kalos.enumeraciones.CompLetras.cLambda;
import static kalos.enumeraciones.CompLetras.cMu;
import static kalos.enumeraciones.CompLetras.cNu;
import static kalos.enumeraciones.CompLetras.cOmicron;
import static kalos.enumeraciones.CompLetras.cPi;
import static kalos.enumeraciones.CompLetras.cPsi;
import static kalos.enumeraciones.CompLetras.cRho;
import static kalos.enumeraciones.CompLetras.cSigma;
import static kalos.enumeraciones.CompLetras.cTau;
import static kalos.enumeraciones.CompLetras.cTheta;
import static kalos.enumeraciones.CompLetras.cUpsilonCorta;
import static kalos.enumeraciones.CompLetras.cXi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import kalos.beans.ResultadoUniversal;
import kalos.beans.TermRegVerbal;
import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.Aumento;
import kalos.enumeraciones.Caso;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.GradoComparacion;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Numero;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.Persona;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.TiempoOAspecto;
import kalos.enumeraciones.TipoPalabra;
import kalos.enumeraciones.TipoVerbo;
import kalos.enumeraciones.Voz;
import kalos.enumeraciones.utils.TransformadorTiempoAspecto;
import kalos.operaciones.DesTransformaciones;
import kalos.operaciones.OpBeans;
import kalos.operaciones.OpPalabras;
import kalos.operaciones.TiposVerbo;
import kalos.recursos.CadenasEnum;
import kalos.recursos.Recursos;

/**
 * @author gonzy
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class AMUtil {
	
	
//	Logger logger=Logger.getLogger(this.getClass().getName());

	public void paso3_5(Set<ObjYDest> setSiguiente, List<ObjYDest> aBuscarPorTema) {
		vocalUnitivaTemas(setSiguiente);
		vocalUnitivaTemas(aBuscarPorTema);
	}

	/**
	 * agrega la vocal unitiva a un bean en el campo "vocalUnitivaRestaurada"
	 * 
	 * @param reg
	 */
	public void vocalUnitivaTemas(Object reg) {
		String temaPropuesto = (String) OpBeans.getPropiedadObject(reg, "temaPropuesto");
		StringBuffer tema = new StringBuffer(temaPropuesto);
		int tipoVerboExt = OpBeans.getPropiedadInt(reg, "tipoVerboExtendido");
		TiempoOAspecto toa = (TiempoOAspecto) OpBeans.getPropiedadObject(reg, new String[] { "tiempo", "aspecto" });
		int tiempo = Tiempo.getInt(TransformadorTiempoAspecto.comoTiempo(toa));
		// los VC, tanto infectivos como no infectivos, comen la desinencia
		// completa con
		// vocal unitiva y todo para formar el tema, así que necesitan que
		// se la restaure
		if (TiposVerbo.esVocalicoContracto(tipoVerboExt)) {
			if (tipoVerboExt == TipoVerbo.VC_ALFA_NORMAL) {
				tema.append(cAlfaCorta);
				OpBeans.setPropiedad(reg, "vocalUnitivaRestaurada", "A");
			} else if (tipoVerboExt == TipoVerbo.VC_EPSILON_NORMAL) {
				tema.append(cEpsilon);
				OpBeans.setPropiedad(reg, "vocalUnitivaRestaurada", "E");
			} else if (tipoVerboExt == TipoVerbo.VC_OMICRON_NORMAL) {
				tema.append(cOmicron);
				OpBeans.setPropiedad(reg, "vocalUnitivaRestaurada", "O");
			}
			// indiqué con un campo adicional que restauró la desinencia;
			// así, al final cuando busque la
			// forma canónica en el diccionario, podré descartar aquéllas
			// que sean TI_MA/W directamente
			// (sin haber tenido la vocal unitiva restaurada)
		}
		//
		else if (TiposVerbo.esMudoNormal(tipoVerboExt) && tiempo > 2) {
			switch (tipoVerboExt) {
			// consonánticos mudos no infectivos
			case TipoVerbo.BETA_NORMAL:
				tema.append(cBeta);
				break;
			case TipoVerbo.PI_NORMAL:
				tema.append(cPi);
				break;
			case TipoVerbo.FI_NORMAL:
				tema.append(cFi);
				break;
			case TipoVerbo.PI_TAU_NORMAL:
				tema.append(cPi);
				tema.append(cTau);
				break;
			case TipoVerbo.KAPPA_NORMAL:
				tema.append(cKappa);
				break;
			case TipoVerbo.GAMMA_NORMAL:
				tema.append(cGamma);
				break;
			case TipoVerbo.JI_NORMAL:
				tema.append(cJi);
				break;
			case TipoVerbo.DOBLE_SIGMA_NORMAL:
				tema.append(cSigma);
				tema.append(cSigma);
				break;
			case TipoVerbo.DOBLE_TAU_NORMAL:
				tema.append(cTau);
				tema.append(cTau);
				break;
			}
		}
		OpBeans.setPropiedad(reg, "temaPropuesto", tema.toString());
	}

	
	
	
	/**
	 * llama a "vocalUnitivaTemas" para todos los beans de esa lista de ObjYDest
	 * 
	 * @param lista
	 */
	public void vocalUnitivaTemas(List<ObjYDest> lista) {
		for (ObjYDest red : lista) {
			Object reg = red.getRegistro();
			vocalUnitivaTemas(reg);
		}
	}

	/**
	 * llama a "vocalUnitivaTemas" para todos los beans de esa lista de nodos
	 * que contienen RegDests
	 * 
	 * @param lista
	 */
	public void vocalUnitivaTemas(Set<ObjYDest> set) {
		for (ObjYDest red : set) {
			Object reg = red.getRegistro();
			vocalUnitivaTemas(reg);
		}
	}

	/**
	 * 
	 * Este paso se ocupa de expandir según "destransformaciones" de temas
	 * (removiendo aumentos y reduplicaciones). El resultado se divide en dos:
	 * en el setSiguiente van los registros cuyo tema puede seguir camino hacia
	 * la forma canónica, y en la lista aBuscarPorTema se pone lo que no pudo
	 * sobrevivir la desTransformación, pero que aún sirve para la búsqueda por
	 * tema.
	 * 
	 * Al final queda un tema definitivo en el campo "temaPropuesto" (atención:
	 * el conjunto de resultados finales puede contener numerosos resultados que
	 * en lo único que difieren es en el tema propuesto
	 * 
	 * @param setOriginal
	 *            los nodos de entrada, a destransformar
	 * @param setDestransformados
	 *            (de salida), contiene las formas a destransformadas
	 * @param aBuscarPorTema
	 *            (de salida) contiene las formas a buscar en la tabla de
	 *            irregularidades verbales
	 * @param debug
	 */
	public <T extends TermRegVerbal> void desTransformacionesTemas(Collection<T> setOriginal,
			Set<ObjYDest> setDestransformados, List<ObjYDest> aBuscarPorTema, boolean debug) {
		// la lstTemas acumula cuplas ObjYDest's con todos los resultados que
		// encuentro, o la forma
		// original si la des-transformación de turno no pudo producir nada
		// luego, la lstTemas se irá a comparar con los temas de la tabla de
		// IRR_VERBOS

		for (T regAux : setOriginal) {
			// String desinencia=regAux.getString("TERMINACION");
			// int iLenTer=desinencia.length();
			List<DesTransformaciones> arrDesaumentado = new ArrayList<DesTransformaciones>();
			List<DesTransformaciones> arrDesresuplicado = new ArrayList<DesTransformaciones>();
			TiempoOAspecto toa = (TiempoOAspecto) OpBeans.getPropiedadObject(regAux,
					new String[] { "tiempo", "aspecto" });
			Modo modo = null;
			if (OpBeans.tienePropiedad(regAux, "modo")) {
				modo = (Modo) OpBeans.getPropiedadObject(regAux, "modo");
			}

			String formaADestransformar = regAux.getFormaADestransformar();

			Tiempo tiempoSwitch = TransformadorTiempoAspecto.comoTiempo(toa);

			// desaumentos y desreduplicaciones
			switch (tiempoSwitch) {
			case Imperfecto:
			case Aoristo: {
				// los agrego como vienen a la futura búsqueda de temas, pero a
				// condición de que el registro no
				// tenga aumento ni reduplicación
				
				TermRegVerbal regNew = (TermRegVerbal) OpBeans.clona(regAux);
				regNew.setFormaDestransformada(formaADestransformar);
				aBuscarPorTema.add(new ObjYDest(regNew, new DesTransformaciones(formaADestransformar, null, null,
						Aumento.Ninguno, false, false)));
				// des-transformaciones
				if (modo == Modo.Indicativo) {
					if (OpPalabras.esEspecificamenteDesAumentable(formaADestransformar)) {
						OpPalabras.desaumentaConAcento(formaADestransformar, arrDesaumentado, false);
						if (arrDesaumentado.size() > 0) {
							for (int e = 0; e < arrDesaumentado.size(); e++) {
								DesTransformaciones dest = arrDesaumentado.get(e);
								String itDesAum = dest.getDesAumentada();
								regNew = (TermRegVerbal) OpBeans.clona(regAux);
								regNew.setFormaDestransformada(itDesAum);
								if (dest.isRegular()) {
									setDestransformados.add(new ObjYDest(regNew, dest));
								} else {
									aBuscarPorTema.add(new ObjYDest((TermRegVerbal)OpBeans.clona(regNew), dest));
								}
							}
						} else {
							throw new RuntimeException("la palabra "
									+ OpPalabras.strCompletoABeta(formaADestransformar)
									+ ", pese a ser específicamente des-aumentable, no presenta formas des-aumentadas");
						}
					}
				} else {// no es indicativo
					regNew = (TermRegVerbal) OpBeans.clona(regAux);
					regNew.setFormaDestransformada(formaADestransformar);
					setDestransformados.add(new ObjYDest(regNew, new DesTransformaciones(formaADestransformar, null,
							null, Aumento.Ninguno, false, true)));
					aBuscarPorTema.add(new ObjYDest((TermRegVerbal)OpBeans.clona(regNew), new DesTransformaciones(
							formaADestransformar, null, null, Aumento.Ninguno, false, true)));
				}
			}
				break;
			case Perfecto: {
				// primero lo agrego como viene a la futura búsqueda de temas,
				// pero a condición
				// de que le registro no tenga aumentos ni reduplicaciones
				TermRegVerbal regNew = (TermRegVerbal) OpBeans.clona(regAux);
				regNew.setFormaDestransformada(formaADestransformar);
				aBuscarPorTema.add(new ObjYDest((TermRegVerbal)OpBeans.clona(regNew), new DesTransformaciones(formaADestransformar,
						null, null, Aumento.Ninguno, false, false)));

				// luego, aplico des-transformaciones
				if (OpPalabras.esDesTransformable(formaADestransformar)) {
					OpPalabras.desReduplicaConAcento(formaADestransformar, arrDesresuplicado, false);
					if (arrDesresuplicado.size() > 0) {
						for (int e = 0; e < arrDesresuplicado.size(); e++) {
							DesTransformaciones dest = arrDesresuplicado.get(e);
							String itDesRed = dest.getDesReduplicada();
							regNew = (TermRegVerbal) OpBeans.clona(regAux);
							regNew.setFormaDestransformada(itDesRed);
							if (dest.isRegular()) {
								setDestransformados.add(new ObjYDest(regNew, dest));
							} else {
								aBuscarPorTema.add(new ObjYDest((TermRegVerbal)OpBeans.clona(regNew), dest));
							}
						}
					} else {
						throw new RuntimeException("la palabra " + OpPalabras.strCompletoABeta(formaADestransformar)
								+ ", pese a ser des-transformable, no presenta formas des-reduplicadas");
					}
				} else {// simplemente agrego a la lista de temas, con una
					// desTransformación vacía
					regNew = (TermRegVerbal) OpBeans.clona(regAux);
					regNew.setFormaDestransformada(formaADestransformar);
					aBuscarPorTema.add(new ObjYDest(regNew, new DesTransformaciones(formaADestransformar, null, null,
							Aumento.Ninguno, false, true)));
				}
				break;
			}
			case Pluscuamperfecto: {
				// los agrego como vienen a la futura búsqueda de temas, pero a
				// condición de que no tengan aumento ni
				// reduplicación
				TermRegVerbal regNew = (TermRegVerbal) OpBeans.clona(regAux);
				;
				regNew.setFormaDestransformada(formaADestransformar);
				aBuscarPorTema.add(new ObjYDest((TermRegVerbal)OpBeans.clona(regNew), new DesTransformaciones(formaADestransformar,
						null, null, Aumento.Ninguno, false, false)));

				// des-transformaciones
				if (OpPalabras.esEspecificamenteDesAumentable(formaADestransformar)) {
					OpPalabras.desaumentaConAcento(formaADestransformar, arrDesaumentado, false);
					if (arrDesaumentado.size() > 0) {
						for (int e = 0; e < arrDesaumentado.size(); e++) {
							DesTransformaciones dest = (DesTransformaciones) arrDesaumentado.get(e);
							String itDesAum = dest.getDesAumentada();
							if (dest.isRegular()) {
								if (OpPalabras.esEspecificamenteDesReduplicable(itDesAum)) {
									OpPalabras.desReduplicaConAcento(itDesAum, arrDesresuplicado, false);
									if (arrDesresuplicado.size() > 0) {
										// puede ser que haya des-aumentos pero
										// no des-reduplicaciones (por ejemplo,
										// cuando el aumento es también
										// la reduplicación como en
										// E)/YEUSTO
										for (int g = 0; g < arrDesresuplicado.size(); g++) {
											DesTransformaciones dest2 = arrDesresuplicado.get(g);
											String itDesRed = dest2.getDesReduplicada();
											regNew = (TermRegVerbal) OpBeans.clona(regAux);
											;
											regNew.setFormaDestransformada(itDesRed);
											if (dest2.isRegular()) {
												dest2.setDesAumentada(dest.getDesAumentada());
												dest2.setAumento(dest.getAumento());
												setDestransformados.add(new ObjYDest(regNew, dest));
											} else {
												aBuscarPorTema.add(new ObjYDest((TermRegVerbal)OpBeans.clona(regNew), dest2));
											}
										}
									} else {
										throw new RuntimeException(
												"la palabra "
														+ OpPalabras.strCompletoABeta(itDesAum)
														+ ", pese a serespecíficamente des-reduplicable, no presenta formas des-reduplicadas");
									}
								} else {// no esespecíficamente
									// des-reduplicable
									regNew = (TermRegVerbal) OpBeans.clona(regAux);
									;
									regNew.setFormaDestransformada(dest.getDesAumentada());
									setDestransformados.add(new ObjYDest(regNew, dest));
									aBuscarPorTema.add(new ObjYDest((TermRegVerbal)OpBeans.clona(regNew), dest));
								}

							} else {
								regNew = (TermRegVerbal) OpBeans.clona(regAux);
								regNew.setFormaDestransformada(dest.getDesAumentada());
								aBuscarPorTema.add(new ObjYDest((TermRegVerbal)OpBeans.clona(regNew), dest));
							}
						}
					} else {
						throw new RuntimeException("la palabra " + OpPalabras.strCompletoABeta(formaADestransformar)
								+ ", pese a serespecíficamente des-aumentable, no presenta formas des-aumentadas");
					}
				} else {// agrego a la lista de temas, con una desTransformación
					// vacía
					regNew = (TermRegVerbal) OpBeans.clona(regAux);
					regNew.setFormaDestransformada(formaADestransformar);
					aBuscarPorTema.add(new ObjYDest(regNew, new DesTransformaciones(formaADestransformar, null, null,
							Aumento.Ninguno, false, false)));
				}
				break;
			}
			default: // simplemente agrego
				TermRegVerbal regNew = (TermRegVerbal) OpBeans.clona(regAux);
				regNew.setFormaDestransformada(formaADestransformar);
				setDestransformados.add(new ObjYDest(regNew, new DesTransformaciones(formaADestransformar, null, null,
						Aumento.Ninguno, false, true)));
				aBuscarPorTema.add(new ObjYDest((TermRegVerbal)OpBeans.clona(regNew), new DesTransformaciones(formaADestransformar,
						null, null, Aumento.Ninguno, false, true)));
			}
		}

		if (debug) {
			System.out.println("AMUtil:desTransformacionesTemas **********************");
			System.out.println("  la colección original mide " + setOriginal.size());
			System.out.println("  y éste es el set regular de salida:  " );
			debugRegDes(setDestransformados, new String[] { "formaOriginal", "formaOriginalCompuesta",  "nominativoPropuesto",
					"genitivoPropuesto", "temaPropuesto", "terminacionVerbalizada", "formaADestransformar",
					"formaDestransformada" });
			System.out.println("  y éste es el set de temas irregulares que va a 'a busca por temas': " );
			debugRegDes(aBuscarPorTema, new String[] { "formaOriginal", "nominativoPropuesto",
					"genitivoPropuesto", "temaPropuesto", "terminacionVerbalizada", "formaADestransformar",
					"formaDestransformada" });			
		}
	}

	static char[][] tiemposExtendidos = new char[][] { { cBeta, TipoVerbo.BETA_NORMAL }, { cPi, TipoVerbo.PI_NORMAL },
			{ cFi, TipoVerbo.FI_NORMAL },

			{ cJi, TipoVerbo.JI_NORMAL }, { cKappa, TipoVerbo.KAPPA_NORMAL }, { cGamma, TipoVerbo.GAMMA_NORMAL },

			{ cTau, TipoVerbo.TAU_NORMAL }, { cDelta, TipoVerbo.DELTA_NORMAL }, { cTheta, TipoVerbo.THETA_NORMAL },

			{ cNu, TipoVerbo.NU_NORMAL }, { cLambda, TipoVerbo.LAMBDA_NORMAL }, { cMu, TipoVerbo.MU_NORMAL },
			{ cRho, TipoVerbo.MU_NORMAL },

			{ cDzeta, TipoVerbo.DZETA_NORMAL }, { cXi, TipoVerbo.XI_NORMAL }, { cPsi, TipoVerbo.PSI_NORMAL },

			{ cUpsilonCorta, TipoVerbo.UPSILON_NORMAL }, { cIotaCorta, TipoVerbo.IOTA_NORMAL }

	};

	/**
	 * Determina el "tiempo Extendido" (que se presume normal) basándose en un
	 * carácter que es la letra unitiva
	 * 
	 * @param letra
	 * @return
	 */
	public int tiempoExtendidoLetra(char letra) {
		for (int i = 0; i < tiemposExtendidos.length; i++) {
			char car = (char) tiemposExtendidos[i][0];
			if (car == letra) {
				return (int) tiemposExtendidos[i][1];
			}
		}
		throw new RuntimeException("si llamo a tiempoExtendidoLetra la resolución es obligatoria. Letra=" + letra
				+ " hex=" + Integer.toHexString(letra));
	}

	public String consonanteDeTiempoExtendido(int tiempoExtendido) {
		// primero las dobles
		if (tiempoExtendido == TipoVerbo.DOBLE_SIGMA_NORMAL)
			return OpPalabras.strBetaACompleto("SS");
		else if (tiempoExtendido == TipoVerbo.DOBLE_TAU_NORMAL)
			return OpPalabras.strBetaACompleto("TT");
		for (int i = 0; i < tiemposExtendidos.length; i++) {
			int entero = (int) tiemposExtendidos[i][1];
			if (entero == tiempoExtendido) {
				return new String(new char[] { tiemposExtendidos[i][0] });
			}
		}
		throw new RuntimeException(
				"si llamo a consonanteDeTiempoExtendido la resolución es obligatoria. Tiempo extendido="
						+ tiempoExtendido);
	}

	/**
	 * Para debuguear. Conserva sólo los registros de ese set cuyas columnas
	 * indicadas tengan determinados valores
	 * 
	 * @param columnas
	 * @param valores
	 */
	public void conservaSolo(Set<?> set, String[] columnas, Object[] valores) {
		List<Object> lstARemover = new ArrayList<Object>();
		Iterator<?> it = set.iterator();
		while (it.hasNext()) {
			Object reg = it.next();
			boolean cumple = true;
			for (int i = 0; i < columnas.length; i++) {
				Object valorRegistro = OpBeans.getPropiedadObject(reg, columnas[i]);
				if (!valorRegistro.equals(valores[i])) {
					cumple = false;
					break;
				}
			}
			if (!cumple)
				lstARemover.add(reg);
		}
		set.removeAll(lstARemover);
	}

	public void envuelveYAgregaBean(Object bean, DefaultMutableTreeNode padre,
			Collection<DefaultMutableTreeNode> conjuntoTransversal) {
		DefaultMutableTreeNode nodAgregado = new DefaultMutableTreeNode(bean);
		padre.add(nodAgregado);
		conjuntoTransversal.add(nodAgregado);
	}

	/**
	 * Hace un volcado de cualquier set que contenga NodoRegistros
	 * 
	 * @param lst
	 */

	public void debugSet(Collection<?> lst, String[] columnasABeta) {
		for (Iterator<?> it = lst.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof ObjYDest) {
				ObjYDest regDest = (ObjYDest) obj;
				System.out.println(regDest.getRegistro());
				// regDest.getRegistro().dumpBeta(columnasABeta);
				DesTransformaciones dest = regDest.getDestransformacion();
				System.out.println("  aumento=" + dest.getAumento() + " reduplicación=" + dest.isReduplicacion());
			}
		}
	}

	/**
	 * Hace un volcado de cualquier set que contenga Registros
	 * 
	 * @param lst
	 */

	public void debugBeans(Collection<?> beans, String[] columnasBeta) {
		Arrays.sort(columnasBeta);
		try {
			StringBuffer sb = new StringBuffer();
			for (Object bean : beans) {
				sb.append(OpBeans.debugBean(bean, columnasBeta) + "\n");
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void debugNodes(Collection<DefaultMutableTreeNode> nodes, String[] columnasBeta) {
		Arrays.sort(columnasBeta);
		StringBuffer salida = new StringBuffer();
		for (DefaultMutableTreeNode node : nodes) {
			Object bean = node.getUserObject();
			salida.append(OpBeans.debugBean(bean, columnasBeta) + "\n");
		}
		System.out.println(salida.toString());
	}

	/**
	 * Imprime el la información de una colección de nodos que contienen
	 * regdests
	 * 
	 * @param nodes
	 * @param columnasBeta
	 */
	public void debugRegDes(Collection<ObjYDest> nodes, String[] columnasBeta) {
		Arrays.sort(columnasBeta);
		StringBuffer salida = new StringBuffer();
		for (ObjYDest red : nodes) {
			salida.append("es ObjYDest \n");
			salida.append("  bean:");
			Object bean = red.getRegistro();
			salida.append(OpBeans.debugBean(bean, columnasBeta) + "\n");
			salida.append("  destransformación:");
			DesTransformaciones dest = red.getDestransformacion();
			salida.append(dest.toString() + "\n");
		}
		System.out.println(salida.toString());
	}

	/**
	 * Toma un set de registros que tienen FORMA_ORIGINAL, y duplica dicho campo
	 * bajo el nombre FORMA_A_DESTRANSFORMAR. Esto se hace porque en otros
	 * contextos, la forma "a destransformar" no es necesariamente la forma
	 * original.
	 * 
	 * @param aDestransformar
	 */
	public <T extends TermRegVerbal> void incorporaADestransformar(Collection<T> aDestransformar, boolean debug) {
		for (Iterator<T> it = aDestransformar.iterator(); it.hasNext();) {
			T trv = (T) it.next();
			trv.setFormaADestransformar(trv.getFormaOriginal());
		}
		if (debug) {
			System.out.println("*** después de incorporar el FORMA_A_DESTRANSFORMAR ***");
			debugBeans(aDestransformar, new String[] { "formaOriginal", "terminacion", "formaOriginalCompuesta",  "formaADestransformar",
					"temaPropuesto", "nominativoPropuesto", "genitivoPropuesto" });
		}
	}

		
	/**
	 * 
	 * Convierte el set de NodeRegistros setOriginal, en un Map con los
	 * temaPropuestos como clave, y cuyos elementos son todos los canAux que
	 * corresponden a ese temapropuesto
	 * 
	 * @param setOriginal
	 * @param mapTemasPropuestos
	 * @param debug
	 */
	public <T extends TermRegVerbal> void agrupaTemasEnSet(Set<T> setOriginal,
			Map<TemaConPreps, HashSet<T>> mapTemasPropuestos, boolean debug) {
		// primero creamos un set con sólo los temasPropuestos, y al mismo
		// tiempo voy haciendo un map
		// que tiene como claves los temaspropuestos, y como valores listas de
		// canons
		mapTemasPropuestos.clear();
		for (T regAux : setOriginal) {
			String temaProp = OpPalabras.strCompletoABeta((String) OpBeans.getPropiedadObject(regAux, "temaPropuesto"));
			if (!mapTemasPropuestos.containsKey(temaProp)) {
				HashSet<T> setAux = new HashSet<T>();
				setAux.add(regAux);
				TemaConPreps clave=new TemaConPreps(temaProp, regAux.getPreposiciones());
				mapTemasPropuestos.put(clave, setAux);
			} else {
				HashSet<T> setAux = mapTemasPropuestos.get(temaProp);
				setAux.add(regAux);
			}
		}
		if (debug) {
			System.out.println("el set (original) después de agrupaTemasEnSet ***********************************");
			debugSet(setOriginal, new String[] { "formaOriginalCompuesta", "formaADestransformar",
					"formaDestransformada" });
			debugMapTemas(mapTemasPropuestos);
		}
	}

	public <T extends TermRegVerbal> void debugMapTemas(Map<TemaConPreps, HashSet<T>> mapTemasPropuestos) {
		Set<TemaConPreps> keySet = mapTemasPropuestos.keySet();
		System.out.println("forma del map de temas propuestos luego de 4_5 ");
		for (Iterator<TemaConPreps> it = keySet.iterator(); it.hasNext();) {
			TemaConPreps str = it.next();
			System.out.println("****" + str.resto + "  " + str.preps + " ****");
			Set<T> setAux = mapTemasPropuestos.get(str);
			for (Iterator<T> it2 = setAux.iterator(); it2.hasNext();) {
				Object reg = it2.next();
				OpBeans.debugBean(reg, new String[] { "FORMA_ORIGINAL", "TEMA_PROPUESTO", "TERMINACION",
						"FORMA_ORGINAL_COMPUESTA", "FORMA_A_DESTRANSFORMAR", "FORMA_DESTRANSFORMADA" });
			}
		}
	}

	/**
	 * comparador usado para ordenar sets que contengan Registros
	 * 
	 * @author Gonzalo
	 */
	public static class ComparaResultadosUniversales implements Comparator<ResultadoUniversal> {
		public int compare(ResultadoUniversal nr1, ResultadoUniversal nr2) {

			int resComp = 0;

			TipoPalabra tipoPalabra1 = nr1.getTipoPalabra();
			TipoPalabra tipoPalabra2 = nr2.getTipoPalabra();
			resComp = tipoPalabra1.compareTo(tipoPalabra2);
			if (resComp != 0)
				return resComp;

			String id1 = nr1.getId();
			String id2 = nr2.getId();
			resComp = id1.compareTo(id2);
			if (resComp != 0)
				return resComp;

			Voz voz1 = nr1.getVoz();
			Voz voz2 = nr2.getVoz();
			if (voz1 != null && voz2 != null) {
				resComp = voz1.compareTo(voz2);
				if (resComp != 0)
					return resComp;
			}

			Tiempo tiempo1 = nr1.getTiempo();
			Tiempo tiempo2 = nr2.getTiempo();
			if (tiempo1 != null && tiempo2 != null) {
				resComp = tiempo1.compareTo(tiempo2);
				if (resComp != 0)
					return resComp;
			}

			Aspecto aspecto1 = nr1.getAspecto();
			Aspecto aspecto2 = nr2.getAspecto();
			if (aspecto1 != null && aspecto2 != null) {
				resComp = aspecto1.compareTo(aspecto2);
				if (resComp != 0)
					return resComp;
			}

			Modo modo1 = nr1.getModo();
			Modo modo2 = nr2.getModo();
			if (modo1 != null && modo2 != null) {
				resComp = modo1.compareTo(modo2);
				if (resComp != 0)
					return resComp;
			}

			Persona persona1 = nr1.getPersona();
			Persona persona2 = nr2.getPersona();
			if (persona1 != null && persona2 != null) {
				resComp = persona1.compareTo(persona2);
				if (resComp != 0)
					return resComp;
			}

			Numero numero1 = nr1.getNumero();
			Numero numero2 = nr2.getNumero();
			if (numero1 != null && numero2 != null) {
				resComp = numero1.compareTo(numero2);
				if (resComp != 0)
					return resComp;
			}

			Genero genero1 = nr1.getGenero();
			Genero genero2 = nr2.getGenero();
			if (genero1 != null && genero2 != null) {
				resComp = genero1.compareTo(genero2);
				if (resComp != 0)
					return resComp;
			}

			Caso caso1 = nr1.getCaso();
			Caso caso2 = nr2.getCaso();
			if (caso1 != null && caso2 != null) {
				resComp = caso1.compareTo(caso2);
				if (resComp != 0)
					return resComp;
			}

			return 0;
		}

	}

	public String accidentesHablados(ResultadoUniversal resUniversal) {
		StringBuffer frase = new StringBuffer();

		TipoPalabra tipoPalabra = resUniversal.getTipoPalabra();

		switch (tipoPalabra) {
		case Sustantivo: {
			frase.append(Recursos.getCadena("sustantivo").toLowerCase() + " ");
			frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
			frase.append(CadenasEnum.getCadena(resUniversal.getCaso()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getGenero()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getNumero()).toLowerCase());
			frase.append(" ");
			break;
		}
		case Participio: {
			frase.append(Recursos.getCadena("participio_del_verbo") + " ");
			frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
			frase.append(CadenasEnum.getCadena(resUniversal.getCaso()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getGenero()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getNumero()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getAspecto()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getVoz()).toLowerCase());
			frase.append(" ");
			break;
		}
		case Infinitivo: {
			frase.append(Recursos.getCadena("infinitivo_del_verbo") + " ");
			frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
			frase.append(CadenasEnum.getCadena(resUniversal.getAspecto()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getVoz()).toLowerCase());
			frase.append(" ");
			break;
		}
		case Verbo: {
			frase.append(Recursos.getCadena("verbo").toLowerCase() + " ");
			frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
			frase.append(CadenasEnum.getCadena(resUniversal.getTiempo()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getModo()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getVoz()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getPersona()).toLowerCase());
			frase.append(" ");
			break;
		}
		case Adjetivo: {
			frase.append(Recursos.getCadena("adjetivo").toLowerCase() + " ");
			frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
			frase.append(CadenasEnum.getCadena(resUniversal.getCaso()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getGenero()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getNumero()).toLowerCase());
			if (!resUniversal.getGrado().equals(GradoComparacion.Positivo)) {
				frase.append(" ");
				if (resUniversal.getGrado().equals(GradoComparacion.Comparativo)) {
					frase.append(Recursos.getCadena("grado.comparativo"));
				} else {
					frase.append(Recursos.getCadena("grado.superlativo"));
				}
			}
			break;
		}
		case Articulo: {
			frase.append(Recursos.getCadena("articulo").toLowerCase() + " ");
			frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
			frase.append(CadenasEnum.getCadena(resUniversal.getCaso()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getGenero()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getNumero()).toLowerCase());
			break;
		}
		
		case PronombrePersonal:{
			frase.append(Recursos.getCadena("pronombre_personal").toLowerCase() + " ");
			frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
			frase.append(CadenasEnum.getCadena(resUniversal.getCaso()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getPersona()).toLowerCase());
			break;
		}
		
		case PronombreRelativo:{
			frase.append(Recursos.getCadena("pronombre_relativo").toLowerCase() + " ");
			frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
			frase.append(CadenasEnum.getCadena(resUniversal.getCaso()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getGenero()).toLowerCase());
			frase.append(" ");
			frase.append(CadenasEnum.getCadena(resUniversal.getNumero()).toLowerCase());
			break;
		}
		
        case PronombreInterrogativo:{
            frase.append(Recursos.getCadena("pronombre_interrogativo").toLowerCase() + " ");
            frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
            frase.append(CadenasEnum.getCadena(resUniversal.getCaso()).toLowerCase());
            frase.append(" ");
            frase.append(CadenasEnum.getCadena(resUniversal.getGenero()).toLowerCase());
            frase.append(" ");
            frase.append(CadenasEnum.getCadena(resUniversal.getNumero()).toLowerCase());
            break;
        }
        
        case PronombreReflexivo:{
            frase.append(Recursos.getCadena("pronombre_reflexivo").toLowerCase() + " ");
            frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
            frase.append(CadenasEnum.getCadena(resUniversal.getCaso()).toLowerCase());
            frase.append(" ");
            frase.append(CadenasEnum.getCadena(resUniversal.getPersona()).toLowerCase());
            break;
        }
        
        case PronombreIndefinido:{
            frase.append(Recursos.getCadena("pronombre_indefinido").toLowerCase() + " ");
            frase.append(OpPalabras.strCompletoAUnicode(resUniversal.getFormaCanonica()) + ", ");
            frase.append(CadenasEnum.getCadena(resUniversal.getCaso()).toLowerCase());
            frase.append(" ");
            frase.append(CadenasEnum.getCadena(resUniversal.getGenero()).toLowerCase());
            frase.append(" ");
            frase.append(CadenasEnum.getCadena(resUniversal.getNumero()).toLowerCase());
            break;
        }
        
        
		case Conjuncion:
		case Adverbio:
		case Preposicion:
		case Interjeccion:
		    frase.append(CadenasEnum.getCadena(resUniversal.getTipoPalabra()).toLowerCase());
		    frase.append(", ");
		    frase.append(Recursos.getCadena("invariable"));
		    break;
		}

		Particularidad partic = resUniversal.getParticIrr();
		if (partic == null) {
			partic = resUniversal.getParticCanonica();
		}
		if (partic != null && !partic.equals(Particularidad.Regular)) {
			frase.append(" " + CadenasEnum.getCadena(partic));
		}
		return frase.toString();
	}

}
