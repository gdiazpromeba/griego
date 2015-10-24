package com.kalos.beans;

import java.util.HashMap;
import java.util.Map;

import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;


public class InterjeccionBean implements ISignificados{
	private String id;
	private String letra;
	private int codigo;
	private Particularidad partic;
	private String interjeccion;
	//significados
	private Map<Idioma, Significado> significados=new HashMap<Idioma, Significado>();

	
	/**
	 * @return Returns the interjeccion.
	 */
	public String getInterjeccion() {
		return interjeccion;
	}
	/**
	 * @param interjeccion The interjeccion to set.
	 */
	public void setInterjeccion(String interj) {
		this.interjeccion = interj;
	}
	/**
	 * @return Returns the codigo.
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the letra.
	 */
	public String getLetra() {
		return letra;
	}
	/**
	 * @param letra The letra to set.
	 */
	public void setLetra(String letra) {
		this.letra = letra;
	}
	/**
	 * @return Returns the partic.
	 */
	public Particularidad getPartic() {
		return partic;
	}
	/**
	 * @param partic The partic to set.
	 */
	public void setPartic(Particularidad partic) {
		this.partic = partic;
	}
	
	
	//significados
	/**
	 * @return Returns the significados.
	 */
	public Map<Idioma, Significado> getSignificados() {
		return significados;
	}
	/**
	 * @param significados The significados to set.
	 */
	public void setSignificados(Map<Idioma, Significado> significados) {
		this.significados = significados;
	}
	
}
