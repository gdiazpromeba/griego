package kalos.beans;

import java.util.HashMap;
import java.util.Map;

import kalos.enumeraciones.GradoComparacion;
import kalos.enumeraciones.Idioma;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.TipoAdverbio;

public class AdverbioBean implements ISignificados{
	private String id;
	private String letra;
	private int codigo;
	private Particularidad partic;
	private GradoComparacion grado;
	private String adverbio;
	private TipoAdverbio tipo;
	//significados
	private Map<Idioma, Significado> significados=new HashMap<Idioma, Significado>();

	
	/**
	 * @return Returns the adverbio.
	 */
	public String getAdverbio() {
		return adverbio;
	}
	/**
	 * @param adverbio The adverbio to set.
	 */
	public void setAdverbio(String adverbio) {
		this.adverbio = adverbio;
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
	 * @return Returns the grado.
	 */
	public GradoComparacion getGrado() {
		return grado;
	}
	/**
	 * @param grado The grado to set.
	 */
	public void setGrado(GradoComparacion grado) {
		this.grado = grado;
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
	/**
	 * @return Returns the tipo.
	 */
	public TipoAdverbio getTipo() {
		return tipo;
	}
	/**
	 * @param tipo The tipo to set.
	 */
	public void setTipo(TipoAdverbio tipo) {
		this.tipo = tipo;
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
