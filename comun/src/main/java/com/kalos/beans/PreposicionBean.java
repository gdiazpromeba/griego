package com.kalos.beans;

import java.util.HashMap;
import java.util.Map;

import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;

public class PreposicionBean  implements ISignificados{
	private String preposicion;
	private String formaDiccionario;
	private int orden;
	private Particularidad particularidad;
	private Map<Idioma, Significado> significados=new HashMap<Idioma, Significado>();;
	private String id;
	private boolean acusativo;
	private boolean genitivo;
	private boolean dativo;


	/**
	 * @return Returns the orden.
	 */
	public int getOrden() {
		return orden;
	}
	/**
	 * @param orden The orden to set.
	 */
	public void setOrden(int orden) {
		this.orden = orden;
	}
	/**
	 * @return Returns the preposicion.
	 */
	public String getPreposicion() {
		return preposicion;
	}
	/**
	 * @param preposicion The preposicion to set.
	 */
	public void setPreposicion(String preposicion) {
		this.preposicion = preposicion;
	}
    public Particularidad getParticularidad() {
        return this.particularidad;
    }
    public void setParticularidad(Particularidad particularidad) {
        this.particularidad = particularidad;
    }
    public Map<Idioma, Significado> getSignificados() {
        return this.significados;
    }
    public void setSignificados(Map<Idioma, Significado> significados) {
        this.significados = significados;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public boolean isAcusativo() {
        return this.acusativo;
    }
    public void setAcusativo(boolean acusativo) {
        this.acusativo = acusativo;
    }
    public boolean isGenitivo() {
        return this.genitivo;
    }
    public void setGenitivo(boolean genitivo) {
        this.genitivo = genitivo;
    }
    public boolean isDativo() {
        return this.dativo;
    }
    public void setDativo(boolean dativo) {
        this.dativo = dativo;
    }
    public String getFormaDiccionario() {
        return this.formaDiccionario;
    }
    public void setFormaDiccionario(String formaDiccionario) {
        this.formaDiccionario = formaDiccionario;
    }

}
