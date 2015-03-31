// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.iu.flexion;

import java.util.HashMap;
import java.util.Map;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.Significado;
import com.kalos.beans.SustantivoBean;
import com.kalos.beans.VerboBean;
import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Reportes;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;
import com.kalos.recursos.CadenasEnum;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;

public class ParametrosReporte {

	public void setGerenteAdjetivos(GerenteAdjetivos f1) {
		gerenteAdjetivos = f1;
	}

	public void setGerenteVerbos(GerenteVerbos p) {
		gerenteVerbos = p;
	}

	public Map parametros(String s, Reportes m) {

		switch (m) {
		case VERBOS_POR_MODO: // '\001'
		case VERBOS_POR_MODO_SIN_DUAL: // '\002'
			return F(s);

		case VERBOS_POR_MODO_ABREVIADO: // '\003'
			return G(s);

		case VERBOS_POR_VOZ: // '\004'
		case VERBOS_POR_VOZ_SIN_DUAL: // '\005'
			return J(s);

		case INFINITIVOS_POR_VOZ: // '\006'
			return E(s);

		case PARTICIPIOS_POR_CASO_ABREVIADO: // '\007'
			return D(s);

		case PARTICIPIOS_POR_VOZ_ABREVIADO: // '\b'
			return H(s);

		case PARTICIPIOS_POR_NUMERO: // '\t'
		case PARTICIPIOS_POR_NUMERO_SIN_DUAL: // '\n'
		case PARTICIPIOS_POR_NUMERO_SIN_VOCATIVO: // '\013'
			return B(s);

		case PARTICIPIOS_POR_VOZ: // '\f'
		case PARTICIPIOS_POR_VOZ_SIN_DUAL: // '\r'
		case PARTICIPIOS_POR_VOZ_SIN_VOCATIVO: // '\016'
			return C(s);

		case SUSTANTIVOS_POR_NUMERO: // '\017'
			return A(s);

		case SUSTANTIVOS_POR_NUMERO_SIN_DUAL: // '\020'
			return L(s);

		case ADJETIVOS_POR_GENERO: // '\021'
			return I(s);

		case ADJETIVOS_POR_NUMERO: // '\022'
		case ADJETIVOS_POR_NUMERO_SIN_DUAL: // '\023'
			return K(s);

		case ARTICULOS_POR_GENERO: // '\024'
			return L();

		case PRONOMBRES_PERSONALES_POR_CASO: // '\025'
			return A(true);

		case PRONOMBRES_PERSONALES_POR_CASO_SIN_DUAL: // '\026'
			return A(false);

		case PRONOMBRES_RELATIVOS_POR_GENERO_1: // '\027'
			return F();

		case PRONOMBRES_RELATIVOS_POR_GENERO_2: // '\030'
			return E();

		case PRONOMBRES_RELATIVOS_POR_GENERO_3: // '\031'
			return K();

		case PRONOMBRES_REFLEXIVOS_POR_GENERO: // '\032'
			return J();

		case PRONOMBRES_INTERROGATIVOS_POR_GENERO: // '\033'
			return mapaPronombresInterrogativos();

		case CONJUNCIONES_POR_TIPO: // '\034'
			return I();

		case CONJUNCIONES_ALFABETICO: // '\035'
			return B();

		case PREPOSICIONES_ALFABETICO: // '\036'
			return C();

		case PREPOSICIONES_POR_CASO: // '\037'
			return A();

		case INTERJECCIONES_ALFABETICO: // ' '
			return H();

		case PRONOMBRES_INDEFINIDOS_POR_GENERO: // '!'
			return D();
		}
		throw new RuntimeException((new StringBuilder()).append("el tipo de reporte ").append(m)
				.append(" no est\341 contemplado").toString());
	}

	private Map A(boolean flag) {
		HashMap hashmap = new HashMap();
		if (flag)
			hashmap.put("P_TITULO", Recursos.getCadena("reportes.pronombres_personales_por_caso"));
		else
			hashmap.put("P_TITULO",
					Recursos.getCadena("reportes.pronombres_personales_por_caso_sin_dual"));
		hashmap.put("P_NOMINATIVO", Recursos.getCadena("nominativo"));
		hashmap.put("P_ACUSATIVO", Recursos.getCadena("acusativo"));
		hashmap.put("P_GENITIVO", Recursos.getCadena("genitivo"));
		hashmap.put("P_DATIVO", Recursos.getCadena("dativo"));
		return hashmap;
	}

	private Map F() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.pronombres_relativos_por_genero_I"));
		hashmap.put("P_MASCULINO", Recursos.getCadena("masculino"));
		hashmap.put("P_FEMENINO", Recursos.getCadena("femenino"));
		hashmap.put("P_NEUTRO", Recursos.getCadena("neutro"));
		return hashmap;
	}

	private Map D() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.pronombres_indefinidos_por_genero"));
		hashmap.put("P_MASCULINO_O_FEMENINO", Recursos.getCadena("masculino_o_femenino"));
		hashmap.put("P_NEUTRO", Recursos.getCadena("neutro"));
		return hashmap;
	}

	private Map mapaPronombresInterrogativos() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.pronombres_relativos_por_genero_I"));
		hashmap.put("P_MASC_FEM", Recursos.getCadena("masculino_o_femenino"));
		hashmap.put("P_NEUTRO", Recursos.getCadena("neutro"));
		return hashmap;
	}

	private Map E() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.pronombres_relativos_por_genero_II"));
		hashmap.put("P_MASCULINO", Recursos.getCadena("masculino"));
		hashmap.put("P_FEMENINO", Recursos.getCadena("femenino"));
		hashmap.put("P_NEUTRO", Recursos.getCadena("neutro"));
		return hashmap;
	}

	private Map K() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.pronombres_relativos_por_genero_III"));
		hashmap.put("P_MASCULINO", Recursos.getCadena("masculino"));
		hashmap.put("P_FEMENINO", Recursos.getCadena("femenino"));
		hashmap.put("P_NEUTRO", Recursos.getCadena("neutro"));
		return hashmap;
	}

	private Map J() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.pronombres_reflexivos_por_genero"));
		hashmap.put("P_MASCULINO", Recursos.getCadena("masculino"));
		hashmap.put("P_FEMENINO", Recursos.getCadena("femenino"));
		hashmap.put("P_NEUTRO", Recursos.getCadena("neutro"));
		return hashmap;
	}

	private Map I() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.conjunciones_por_tipo"));
		return hashmap;
	}

	private Map H() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.interjecciones_alfabeticamente"));
		return hashmap;
	}

	private Map B() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.conjunciones_alfabeticamente"));
		return hashmap;
	}

	private Map C() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.preposiciones_alfabeticamente"));
		return hashmap;
	}

	private Map A() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.preposiciones_por_caso"));
		return hashmap;
	}

	private Map F(String s) {
		HashMap hashmap = new HashMap();
		VerboBean h1 = gerenteVerbos.seleccionaUno(s);
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(h1.getVerbo()));
		Significado q1 = (Significado) h1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_INDICATIVO", Recursos.getCadena("indicativo"));
		hashmap.put("P_SUBJUNTIVO", Recursos.getCadena("subjuntivo"));
		hashmap.put("P_OPTATIVO", Recursos.getCadena("optativo"));
		hashmap.put("P_IMPERATIVO", Recursos.getCadena("imperativo"));
		return hashmap;
	}

	private Map G(String s) {
		HashMap hashmap = new HashMap();
		VerboBean h1 = gerenteVerbos.seleccionaUno(s);
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(h1.getVerbo()));
		Significado q1 = (Significado) h1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_ACTIVA", Recursos.getCadena("activa"));
		hashmap.put("P_MEDIA", Recursos.getCadena("media"));
		hashmap.put("P_PASIVA", Recursos.getCadena("pasiva"));
		return hashmap;
	}

	private Map E(String s) {
		HashMap hashmap = new HashMap();
		VerboBean h1 = gerenteVerbos.seleccionaUno(s);
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(h1.getVerbo()));
		Significado q1 = (Significado) h1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_ACTIVA", Recursos.getCadena("activa"));
		hashmap.put("P_MEDIA", Recursos.getCadena("media"));
		hashmap.put("P_PASIVA", Recursos.getCadena("pasiva"));
		return hashmap;
	}

	private Map J(String s) {
		HashMap hashmap = new HashMap();
		VerboBean h1 = gerenteVerbos.seleccionaUno(s);
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(h1.getVerbo()));
		Significado q1 = (Significado) h1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_ACTIVA", Recursos.getCadena("activa"));
		hashmap.put("P_MEDIA", Recursos.getCadena("media"));
		hashmap.put("P_PASIVA", Recursos.getCadena("pasiva"));
		return hashmap;
	}

	private Map C(String s) {
		HashMap hashmap = new HashMap();
		VerboBean h1 = gerenteVerbos.seleccionaUno(s);
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(h1.getVerbo()));
		Significado q1 = (Significado) h1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_ACTIVA", Recursos.getCadena("activa"));
		hashmap.put("P_MEDIA", Recursos.getCadena("media"));
		hashmap.put("P_PASIVA", Recursos.getCadena("pasiva"));
		return hashmap;
	}

	private Map D(String s) {
		HashMap hashmap = new HashMap();
		VerboBean h1 = gerenteVerbos.seleccionaUno(s);
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(h1.getVerbo()));
		Significado q1 = (Significado) h1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_NOMINATIVO", Recursos.getCadena("nominativo"));
		hashmap.put("P_GENITIVO", Recursos.getCadena("genitivo"));
		return hashmap;
	}

	private Map H(String s) {
		HashMap hashmap = new HashMap();
		VerboBean h1 = gerenteVerbos.seleccionaUno(s);
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(h1.getVerbo()));
		Significado q1 = (Significado) h1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_ACTIVA", Recursos.getCadena("activa"));
		hashmap.put("P_MEDIA", Recursos.getCadena("media"));
		hashmap.put("P_PASIVA", Recursos.getCadena("pasiva"));
		return hashmap;
	}

	private Map B(String s) {
		HashMap hashmap = new HashMap();
		VerboBean h1 = gerenteVerbos.seleccionaUno(s);
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(h1.getVerbo()));
		Significado q1 = (Significado) h1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_SINGULAR", Recursos.getCadena("singular"));
		hashmap.put("P_PLURAL", Recursos.getCadena("plural"));
		hashmap.put("P_DUAL", Recursos.getCadena("dual"));
		return hashmap;
	}

	private Map A(String s) {
		HashMap hashmap = new HashMap();
		SustantivoBean k = gerenteSustantivos.seleccionaUno(s);
		String s1 = OpBeans.primerCampoNoVacio(k, new String[] { "nominativo", "genitivo" });
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(s1));
		Significado q1 = (Significado) k.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		String s2 = CadenasEnum.getCadena(k.getGenero()).toLowerCase();
		hashmap.put("P_SUBTITULO", (new StringBuilder()).append("(").append(s2).append(") ")
				.append(q1.getValor()).toString());
		hashmap.put("P_SINGULAR", Recursos.getCadena("singular"));
		hashmap.put("P_PLURAL", Recursos.getCadena("plural"));
		hashmap.put("P_DUAL", Recursos.getCadena("dual"));
		return hashmap;
	}

	private Map L(String s) {
		HashMap hashmap = new HashMap();
		SustantivoBean k = gerenteSustantivos.seleccionaUno(s);
		String s1 = OpBeans.primerCampoNoVacio(k, new String[] { "nominativo", "genitivo" });
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(s1));
		Significado q1 = (Significado) k.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		String s2 = CadenasEnum.getCadena(k.getGenero()).toLowerCase();
		hashmap.put("P_SUBTITULO", (new StringBuilder()).append("(").append(s2).append(") ")
				.append(q1.getValor()).toString());
		hashmap.put("P_SINGULAR", Recursos.getCadena("singular"));
		hashmap.put("P_PLURAL", Recursos.getCadena("plural"));
		return hashmap;
	}

	private Map I(String s) {
		HashMap hashmap = new HashMap();
		AdjetivoBean f1 = gerenteAdjetivos.seleccionaUno(s);
		String s1 = OpBeans.primerCampoNoVacio(f1, new String[] { "mascFem", "masculino",
				"femenino", "neutro" });
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(s1));
		Significado q1 = (Significado) f1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_MASC_FEM", Recursos.getCadena("masculino_o_femenino"));
		hashmap.put("P_MASCULINO", Recursos.getCadena("masculino"));
		hashmap.put("P_FEMENINO", Recursos.getCadena("femenino"));
		hashmap.put("P_NEUTRO", Recursos.getCadena("neutro"));
		return hashmap;
	}

	private Map L() {
		HashMap hashmap = new HashMap();
		hashmap.put("P_TITULO", Recursos.getCadena("reportes.articulos_por_genero"));
		hashmap.put("P_MASCULINO", Recursos.getCadena("masculino"));
		hashmap.put("P_FEMENINO", Recursos.getCadena("femenino"));
		hashmap.put("P_NEUTRO", Recursos.getCadena("neutro"));
		return hashmap;
	}

	private Map K(String s) {
		HashMap hashmap = new HashMap();
		AdjetivoBean f1 = gerenteAdjetivos.seleccionaUno(s);
		String s1 = OpBeans.primerCampoNoVacio(f1, new String[] { "mascFem", "masculino",
				"femenino", "neutro" });
		hashmap.put("P_TITULO", OpPalabras.strBetaAUnicode(s1));
		Significado q1 = (Significado) f1.getSignificados().get(
				Idioma.getEnum(Configuracion.getIdiomaSignificados()));
		hashmap.put("P_SUBTITULO", q1.getValor());
		hashmap.put("P_SINGULAR", Recursos.getCadena("singular"));
		hashmap.put("P_PLURAL", Recursos.getCadena("plural"));
		hashmap.put("P_DUAL", Recursos.getCadena("dual"));
		return hashmap;
	}

	public void setGerenteSustantivos(GerenteSustantivos j1) {
		gerenteSustantivos = j1;
	}

	private GerenteVerbos gerenteVerbos;
	private GerenteSustantivos gerenteSustantivos;
	private GerenteAdjetivos gerenteAdjetivos;
}
