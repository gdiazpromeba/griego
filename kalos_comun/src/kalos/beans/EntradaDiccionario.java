package kalos.beans;

import java.util.HashMap;
import java.util.Map;

import kalos.enumeraciones.Idioma;
import kalos.enumeraciones.TipoPalabra;

/**
 * representa una entrada en la tabla DICCIONARIO, que contiene
 * las formas de diccionario de todos los tipos de palabra
 * @author gdiaz
 *
 */
public class EntradaDiccionario implements ISignificados{

	private String id;
	private String referenteId;
	private String normal;
	private String normalBeta;
	private String sinLargasBeta;
	private String neutralizadaBeta;
	private String unicode;
	private TipoPalabra tipoPalabra;
	private String letra;
	private int codigo;
	private Map<Idioma, Significado> significados=new HashMap<Idioma, Significado>();
	
	
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
	 * @return Returns the normal.
	 */
	public String getNormal() {
		return normal;
	}
	/**
	 * @param normal The normal to set.
	 */
	public void setNormal(String normal) {
		this.normal = normal;
	}
	/**
	 * @return Returns the referenteId.
	 */
	public String getReferenteId() {
		return referenteId;
	}
	/**
	 * @param referenteId The referenteId to set.
	 */
	public void setReferenteId(String referenteId) {
		this.referenteId = referenteId;
	}
	/**
	 * @return Returns the tipoPalabra.
	 */
	public TipoPalabra getTipoPalabra() {
		return tipoPalabra;
	}
	/**
	 * @param tipoPalabra The tipoPalabra to set.
	 */
	public void setTipoPalabra(TipoPalabra tipoPalabra) {
		this.tipoPalabra = tipoPalabra;
	}

	public Map<Idioma, Significado> getSignificados() {
		return significados;
	}

	public void setSignificados(Map<Idioma, Significado> significados) {
		this.significados = significados;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the normalBeta.
	 */
	public String getNormalBeta() {
		return normalBeta;
	}

	/**
	 * @param normalBeta The normalBeta to set.
	 */
	public void setNormalBeta(String normalBeta) {
		this.normalBeta = normalBeta;
	}
	public String getSinLargasBeta() {
	    return sinLargasBeta;
	}
	public void setSinLargasBeta(String sinLargasBeta) {
	    this.sinLargasBeta = sinLargasBeta;
	}
	public String getNeutralizadaBeta() {
	    return neutralizadaBeta;
	}
	public void setNeutralizadaBeta(String neutralizadaBeta) {
	    this.neutralizadaBeta = neutralizadaBeta;
	}
	public String getUnicode() {
	    return unicode;
	}
	public void setUnicode(String unicode) {
	    this.unicode = unicode;
	}
	
	

}
