package com.kalos.beans;

import java.util.HashMap;
import java.util.Map;

import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.TipoPalabra;

public class EncParticulaBean implements ISignificados {
	private String id;
	private String forma;
	private TipoPalabra tipoPalabra;
    private Map significados;
    
    public EncParticulaBean() {
        significados = new HashMap<>();
    }
    
    public Map<Idioma, Significado> getSignificados() {
        return significados;
    }

    public void setSignificados(Map<Idioma, Significado> map) {
        significados = map;
    }
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the forma
	 */
	public String getForma() {
		return forma;
	}
	/**
	 * @param forma the forma to set
	 */
	public void setForma(String forma) {
		this.forma = forma;
	}
	/**
	 * @return the tipoPalabra
	 */
	public TipoPalabra getTipoPalabra() {
		return tipoPalabra;
	}
	/**
	 * @param tipoPalabra the tipoPalabra to set
	 */
	public void setTipoPalabra(TipoPalabra tipoPalabra) {
		this.tipoPalabra = tipoPalabra;
	}

}
