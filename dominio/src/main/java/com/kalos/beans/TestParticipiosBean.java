package com.kalos.beans;

import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Voz;

public class TestParticipiosBean {
  private String id;
  private String verbo;
  private String formaDeclinada;
  private String idVerbo;
  private Voz voz;
  private Aspecto aspecto;
  private FuerteDebil fuerte;
  private Caso caso;
  private Numero numero;
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
 * @return Returns the formaDeclinada.
 */
public String getFormaDeclinada() {
	return formaDeclinada;
}
/**
 * @param formaDeclinada The formaDeclinada to set.
 */
public void setFormaDeclinada(String formaDeclinada) {
	this.formaDeclinada = formaDeclinada;
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
 * @return Returns the idVerbo.
 */
public String getIdVerbo() {
	return idVerbo;
}
/**
 * @param idVerbo The idVerbo to set.
 */
public void setIdVerbo(String idVerbo) {
	this.idVerbo = idVerbo;
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
/**
 * @return Returns the verbo.
 */
public String getVerbo() {
	return verbo;
}
/**
 * @param verbo The verbo to set.
 */
public void setVerbo(String verbo) {
	this.verbo = verbo;
}
}
