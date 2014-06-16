package kalos.beans;

import kalos.enumeraciones.TipoPalabra;

public class EncParticulaBean {
	private String id;
	private String forma;
	private TipoPalabra tipoPalabra;
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
