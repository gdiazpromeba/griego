package com.kalos.beans;

import java.util.List;

import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Voz;

public class IrrParticipioEntero {
  private String id;
  private String verboId;
  private Voz voz;
  private Aspecto aspecto;
  private FuerteDebil fuerte;
  private Particularidad partic;
  private int subPart;
  private String nominativo;
  private String genitivo;
  private Genero genero;
  private int tipoSustantivo;
  //extra para AM
  private List<String> preposiciones;
    
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
/**
 * @return the preposiciones
 */
public List<String> getPreposiciones() {
	return preposiciones;
}
/**
 * @param preposiciones the preposiciones to set
 */
public void setPreposiciones(List<String> preposiciones) {
	this.preposiciones = preposiciones;
}

  
}
