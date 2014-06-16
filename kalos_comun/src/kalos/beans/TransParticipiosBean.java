package kalos.beans;

import kalos.enumeraciones.Acento;
import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.Caso;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.Voz;

public class TransParticipiosBean {
	private String id;
	private String substraer;
	private String agregar;
	private Aspecto aspecto;
	private FuerteDebil fuerte;
	private Voz voz;
	private Caso caso;
	private Genero genero;
	private String agregarTema;
	private int tipoVerbo;
	private Acento agregarAcento;
	private int agregarPosicion;
	private int substractaPosicion;
	private String regexOrig;
	private int juego;
	private int contraccionComedora;
	/**
	 * @return Returns the agregar.
	 */
	public String getAgregar() {
		return agregar;
	}
	/**
	 * @param agregar The agregar to set.
	 */
	public void setAgregar(String agregar) {
		this.agregar = agregar;
	}
	/**
	 * @return Returns the agregarAcento.
	 */
	public Acento getAgregarAcento() {
		return agregarAcento;
	}
	/**
	 * @param agregarAcento The agregarAcento to set.
	 */
	public void setAgregarAcento(Acento agregarAcento) {
		this.agregarAcento = agregarAcento;
	}
	/**
	 * @return Returns the agregarPosicion.
	 */
	public int getAgregarPosicion() {
		return agregarPosicion;
	}
	/**
	 * @param agregarPosicion The agregarPosicion to set.
	 */
	public void setAgregarPosicion(int agregarPosicion) {
		this.agregarPosicion = agregarPosicion;
	}
	/**
	 * @return Returns the agregarTema.
	 */
	public String getAgregarTema() {
		return agregarTema;
	}
	/**
	 * @param agregarTema The agregarTema to set.
	 */
	public void setAgregarTema(String agregarTema) {
		this.agregarTema = agregarTema;
	}
	/**
	 * @return Returns the aspecto.
	 */
	public Aspecto getAspecto() {
		return aspecto;
	}
	/**
	 * @param aspecto The aspecto to set.
	 */
	public void setAspecto(Aspecto aspecto) {
		this.aspecto = aspecto;
	}
	/**
	 * @return Returns the caso.
	 */
	public Caso getCaso() {
		return caso;
	}
	/**
	 * @param caso The caso to set.
	 */
	public void setCaso(Caso caso) {
		this.caso = caso;
	}
	/**
	 * @return Returns the contraccionComedora.
	 */
	public int getContraccionComedora() {
		return contraccionComedora;
	}
	/**
	 * @param contraccionComedora The contraccionComedora to set.
	 */
	public void setContraccionComedora(int contraccionComedora) {
		this.contraccionComedora = contraccionComedora;
	}
	/**
	 * @return Returns the fuerte.
	 */
	public FuerteDebil getFuerte() {
		return fuerte;
	}
	/**
	 * @param fuerte The fuerte to set.
	 */
	public void setFuerte(FuerteDebil fuerte) {
		this.fuerte = fuerte;
	}
	/**
	 * @return Returns the genero.
	 */
	public Genero getGenero() {
		return genero;
	}
	/**
	 * @param genero The genero to set.
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
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
	 * @return Returns the juego.
	 */
	public int getJuego() {
		return juego;
	}
	/**
	 * @param juego The juego to set.
	 */
	public void setJuego(int juego) {
		this.juego = juego;
	}
	/**
	 * @return Returns the regexOrig.
	 */
	public String getRegexOrig() {
		return regexOrig;
	}
	/**
	 * @param regexOrig The regexOrig to set.
	 */
	public void setRegexOrig(String regexOrig) {
		this.regexOrig = regexOrig;
	}
	/**
	 * @return Returns the substractaPosicion.
	 */
	public int getSubstractaPosicion() {
		return substractaPosicion;
	}
	/**
	 * @param substractaPosicion The substractaPosicion to set.
	 */
	public void setSubstractaPosicion(int substractaPosicion) {
		this.substractaPosicion = substractaPosicion;
	}
	/**
	 * @return Returns the substraer.
	 */
	public String getSubstraer() {
		return substraer;
	}
	/**
	 * @param substraer The substraer to set.
	 */
	public void setSubstraer(String substraer) {
		this.substraer = substraer;
	}
	/**
	 * @return Returns the tipoVerbo.
	 */
	public int getTipoVerbo() {
		return tipoVerbo;
	}
	/**
	 * @param tipoVerbo The tipoVerbo to set.
	 */
	public void setTipoVerbo(int tipoVerbo) {
		this.tipoVerbo = tipoVerbo;
	}
	/**
	 * @return Returns the voz.
	 */
	public Voz getVoz() {
		return voz;
	}
	/**
	 * @param voz The voz to set.
	 */
	public void setVoz(Voz voz) {
		this.voz = voz;
	}
}
