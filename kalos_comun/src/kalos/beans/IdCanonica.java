package kalos.beans;

/**
 * el id (de verbo o sustantivo) y la forma canónica
 * para mostrar en el título de los reportes
 * @author Gonzalo
 *
 */
public class IdCanonica {
  private String id;
  private String canonica;
/**
 * @return Returns the canonica.
 */
public String getCanonica() {
	return canonica;
}
/**
 * @param canonica The canonica to set.
 */
public void setCanonica(String canonica) {
	this.canonica = canonica;
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
