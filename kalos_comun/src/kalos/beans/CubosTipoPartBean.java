package kalos.beans;

import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.Voz;

public class CubosTipoPartBean {
  private String nominativo;
  private String genitivo;
  private Genero genero;
  private Aspecto aspecto;
  private Voz voz;
  private FuerteDebil fuerte;
  private int tipoSustantivo;
  
/**
 * @return Returns the tipoSustantivo.
 */
public int getTipoSustantivo() {
	return tipoSustantivo;
}
/**
 * @param tipoSustantivo The tipoSustantivo to set.
 */
public void setTipoSustantivo(int tipoSustantivo) {
	this.tipoSustantivo = tipoSustantivo;
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
 * @return Returns the genitivo.
 */
public String getGenitivo() {
	return genitivo;
}
/**
 * @param genitivo The genitivo to set.
 */
public void setGenitivo(String genitivo) {
	this.genitivo = genitivo;
}
/**
 * @return Returns the nominativo.
 */
public String getNominativo() {
	return nominativo;
}
/**
 * @param nominativo The nominativo to set.
 */
public void setNominativo(String nominativo) {
	this.nominativo = nominativo;
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
