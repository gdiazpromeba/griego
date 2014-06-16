package kalos.beans;

import kalos.enumeraciones.Caso;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.Numero;

public class TestAdjetivosBean {
  private String id;
  private String idAdjetivo;
  private String formaDeclinada;
  private Caso caso;
  private Numero numero;
  private Genero genero;
  
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
 * @return Returns the idAdjetivo.
 */
public String getIdAdjetivo() {
	return idAdjetivo;
}
/**
 * @param idAdjetivo The idAdjetivo to set.
 */
public void setIdAdjetivo(String idAdjetivo) {
	this.idAdjetivo = idAdjetivo;
}
/**
 * @param id The id to set.
 */
public void setId(String id) {
	this.id = id;
}

}
