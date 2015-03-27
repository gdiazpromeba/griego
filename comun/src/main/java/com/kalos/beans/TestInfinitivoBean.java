package com.kalos.beans;

import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Voz;

public class TestInfinitivoBean {
  private String id;
  private String idVerbo;
  private String verbo;
  private String formaConjugada;
  private Voz voz;
  private Aspecto aspecto;
  private FuerteDebil fuerte;

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
 * @return Returns the formaConjugada.
 */
public String getFormaConjugada() {
	return formaConjugada;
}
/**
 * @param formaConjugada The formaConjugada to set.
 */
public void setFormaConjugada(String formaConjugada) {
	this.formaConjugada = formaConjugada;
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
}
