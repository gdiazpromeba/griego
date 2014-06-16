package kalos.beans;

import kalos.enumeraciones.Genero;
import kalos.enumeraciones.TipoPalabra;

/**
 * Clase jerárquica (con padre, etc) que indica un tipo de sustantivo.
 * El padre, la clave de descripción y demás cosas generales están en la superclase
 * @author Gonzalo
 *
 */
public class TipoSustantivo extends TipoJerarquico {
  private Genero generoSugerido;
  private boolean pluralizado;
  private boolean soloSingular;
  private TipoPalabra tipoPalabra;
/**
 * @return Returns the generoSugerido.
 */
public Genero getGeneroSugerido() {
	return generoSugerido;
}
/**
 * @param generoSugerido The generoSugerido to set.
 */
public void setGeneroSugerido(Genero generoSugerido) {
	this.generoSugerido = generoSugerido;
}
/**
 * @return Returns the pluralizado.
 */
public boolean isPluralizado() {
	return pluralizado;
}
/**
 * @param pluralizado The pluralizado to set.
 */
public void setPluralizado(boolean pluralizado) {
	this.pluralizado = pluralizado;
}
/**
 * @return Returns the soloSingular.
 */
public boolean isSoloSingular() {
	return soloSingular;
}
/**
 * @param soloSingular The soloSingular to set.
 */
public void setSoloSingular(boolean soloSingular) {
	this.soloSingular = soloSingular;
}
/**
 * @return Returns the tipoPalabra.
 */
public TipoPalabra getTipoPalabra() {
	return tipoPalabra;
}
/**
 * @param tipoPalabra The tipoPalabra to set.
 */
public void setTipoPalabra(TipoPalabra tipoPalabra) {
	this.tipoPalabra = tipoPalabra;
}
  
}
