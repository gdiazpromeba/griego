package com.kalos.beans;

import java.util.HashMap;
import java.util.Map;

import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.SubtipoConjuncion;
import com.kalos.enumeraciones.TipoConjuncion;

/**
 * Una entrada de diccionario de adjetivos
 * @author gdiaz
 *
 */
public class ConjuncionBean implements ISignificados{
	private String id;
	private String forma;
	private SubtipoConjuncion subtipo;
	private TipoConjuncion tipo;
	private boolean parte;
	private Particularidad partic;
	private int codigo;
	private Map<Idioma, Significado> significados=new HashMap<Idioma, Significado>();
	
	
	/**
	 * @param significados The significados to set.
	 */
	public void setSignificados(Map<Idioma, Significado> significados) {
		this.significados = significados;
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
	 * @return Returns the tipo.
	 */
	public SubtipoConjuncion getSubtipo() {
		return subtipo;
	}

	/**
	 * @param subtipo The idTipo to set.
	 */
	public void setSubtipo(SubtipoConjuncion subtipo) {
		this.subtipo = subtipo;
	}


	/**
	 * @return Returns the significados.
	 */
	public Map<Idioma, Significado> getSignificados() {
		return significados;
	}



    /**
     * @return the codigo
     */
    public int getCodigo() {
        return this.codigo;
    }



    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }



    /**
     * @return the partic
     */
    public Particularidad getPartic() {
        return this.partic;
    }



    /**
     * @param partic the partic to set
     */
    public void setPartic(Particularidad partic) {
        this.partic = partic;
    }



    /**
     * @return the forma
     */
    public String getForma() {
        return this.forma;
    }



    /**
     * @param forma the forma to set
     */
    public void setForma(String forma) {
        this.forma = forma;
    }



    public TipoConjuncion getTipo() {
        return this.tipo;
    }



    public void setTipo(TipoConjuncion tipo) {
        this.tipo = tipo;
    }



    public boolean isParte() {
        return this.parte;
    }



    public void setParte(boolean parte) {
        this.parte = parte;
    }



}
