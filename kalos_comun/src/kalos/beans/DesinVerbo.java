package kalos.beans;

import kalos.enumeraciones.Acento;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Persona;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.Voz;

/**
 * bean que contiene una terminación nominal
 * @author gdiaz
 *
 */
public class DesinVerbo {
	private int juego;
	private Voz voz;
	private Modo modo;
	private Tiempo tiempo;
	private FuerteDebil fuerte;
	private Persona persona;
	private String desinencia;
	private int silaba;
	private int subindice;
	private Acento acento;
	private Integer orden;
	private Integer ordenPorDefecto;
	
	public Acento getAcento() {
		return acento;
	}
	public void setAcento(Acento acento) {
		this.acento = acento;
	}
	public String getDesinencia() {
		return desinencia;
	}
	public void setDesinencia(String desinencia) {
		this.desinencia = desinencia;
	}
	public FuerteDebil getFuerte() {
		return fuerte;
	}
	public void setFuerte(FuerteDebil fuerte) {
		this.fuerte = fuerte;
	}
	public int getJuego() {
		return juego;
	}
	public void setJuego(int juego) {
		this.juego = juego;
	}
	public Modo getModo() {
		return modo;
	}
	public void setModo(Modo modo) {
		this.modo = modo;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public Integer getOrdenPorDefecto() {
		return ordenPorDefecto;
	}
	public void setOrdenPorDefecto(Integer ordenPorDefecto) {
		this.ordenPorDefecto = ordenPorDefecto;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public int getSilaba() {
		return silaba;
	}
	public void setSilaba(int silaba) {
		this.silaba = silaba;
	}
	public int getSubindice() {
		return subindice;
	}
	public void setSubindice(int subindice) {
		this.subindice = subindice;
	}
	public Tiempo getTiempo() {
		return tiempo;
	}
	public void setTiempo(Tiempo tiempo) {
		this.tiempo = tiempo;
	}
	public Voz getVoz() {
		return voz;
	}
	public void setVoz(Voz voz) {
		this.voz = voz;
	}
}
