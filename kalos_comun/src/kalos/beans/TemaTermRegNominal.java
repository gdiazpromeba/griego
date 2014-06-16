package kalos.beans;

/**
 * Representación del tema que sirve para deducir la terminación contracta de un tipo nominal
 * @author Gonzalo
 *
 */
public class TemaTermRegNominal {

	private String idTipoNominal;
	private String tema;
	/**
	 * @return Returns the tema.
	 */
	public String getTema() {
		return tema;
	}
	/**
	 * @param tema The tema to set.
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getIdTipoNominal() {
		return idTipoNominal;
	}
	public void setIdTipoNominal(String idTipoNominal) {
		this.idTipoNominal = idTipoNominal;
	}
}
