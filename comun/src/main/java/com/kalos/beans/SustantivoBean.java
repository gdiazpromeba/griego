package com.kalos.beans;

import java.util.HashMap;
import java.util.Map;

import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;

/**
 * Una entrada de diccionario de sustantivos
 * @author gdiaz
 *
 */
public class SustantivoBean implements ISignificados, NomGenTipo{
	private String id;
	private String letra;
	private int codigo;
	private String nominativo;
	private String genitivo;
	private Genero genero;
	private Particularidad partic;
	private boolean dibujado;
	private boolean pluralizado;
	private boolean soloSingular;
	private int tipoNominal;
	private String idTipo;
	private Map<Idioma, Significado> significados=new HashMap<Idioma, Significado>();
	
	
	/**
	 * @param significados The significados to set.
	 */
	public void setSignificados(Map<Idioma, Significado> significados) {
		this.significados = significados;
	}
	


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean isDibujado() {
		return dibujado;
	}

	public void setDibujado(boolean dibujado) {
		this.dibujado = dibujado;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getGenitivo() {
		return genitivo;
	}

	public void setGenitivo(String genitivo) {
		this.genitivo = genitivo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getNominativo() {
		return nominativo;
	}

	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}

	public Particularidad getPartic() {
		return partic;
	}

	public void setPartic(Particularidad partic) {
		this.partic = partic;
	}

	public boolean isPluralizado() {
		return pluralizado;
	}

	public void setPluralizado(boolean pluralizado) {
		this.pluralizado = pluralizado;
	}

	public boolean isSoloSingular() {
		return soloSingular;
	}

	public void setSoloSingular(boolean soloSingular) {
		this.soloSingular = soloSingular;
	}

	public int getTipoNominal() {
		return tipoNominal;
	}

	public void setTipoNominal(int tipo) {
		this.tipoNominal = tipo;
	}

	public Map<Idioma, Significado> getSignificados() {
		return significados;
	}



	
	
	

}
