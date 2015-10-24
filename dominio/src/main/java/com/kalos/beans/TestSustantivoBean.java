package com.kalos.beans;

import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;

public class TestSustantivoBean {
  private String id;
  private String idSustantivo;
  private String formaDeclinada;
  private Caso caso;
  private Numero numero;
  
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
 * @return Returns the idSustantivo.
 */
public String getIdSustantivo() {
	return idSustantivo;
}
/**
 * @param idSustantivo The idSustantivo to set.
 */
public void setIdSustantivo(String idSustantivo) {
	this.idSustantivo = idSustantivo;
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

}
