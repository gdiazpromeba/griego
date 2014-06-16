package kalos.beans;

import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.Caso;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.Numero;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.Voz;

public class IrrParticipioSimpleBean {
  private String id;
  private String verboId;
  private Voz voz;
  private Aspecto aspecto;
  private FuerteDebil fuerte;
  private Particularidad partic;
  private int subPart;
  private Genero genero;
  private Caso caso;
  private Numero numero;
  private String forma;
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
 * @return Returns the forma.
 */
public String getForma() {
	return forma;
}
/**
 * @param forma The forma to set.
 */
public void setForma(String forma) {
	this.forma = forma;
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
 * @return Returns the numero.
 */
public Numero getNumero() {
	return numero;
}
/**
 * @param numero The numero to set.
 */
public void setNumero(Numero numero) {
	this.numero = numero;
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
 * @return Returns the subPart.
 */
public int getSubPart() {
	return subPart;
}
/**
 * @param subPart The subPart to set.
 */
public void setSubPart(int subPart) {
	this.subPart = subPart;
}
/**
 * @return Returns the verboId.
 */
public String getVerboId() {
	return verboId;
}
/**
 * @param verboId The verboId to set.
 */
public void setVerboId(String verboId) {
	this.verboId = verboId;
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
