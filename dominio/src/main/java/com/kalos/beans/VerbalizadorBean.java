package com.kalos.beans;

import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Voz;

public class VerbalizadorBean {
  private Voz voz;
  private Aspecto aspecto;
  private String terminacionNominativo;
  private String terminacionGenitivo;
  private int tipoVerbo;
  private FuerteDebil fuerte;
  private Genero genero;
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
 * @return Returns the terminacionGenitivo.
 */
public String getTerminacionGenitivo() {
	return terminacionGenitivo;
}
/**
 * @param terminacionGenitivo The terminacionGenitivo to set.
 */
public void setTerminacionGenitivo(String terminacionGenitivo) {
	this.terminacionGenitivo = terminacionGenitivo;
}
/**
 * @return Returns the terminacionNominativo.
 */
public String getTerminacionNominativo() {
	return terminacionNominativo;
}
/**
 * @param terminacionNominativo The terminacionNominativo to set.
 */
public void setTerminacionNominativo(String terminacionNominativo) {
	this.terminacionNominativo = terminacionNominativo;
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
