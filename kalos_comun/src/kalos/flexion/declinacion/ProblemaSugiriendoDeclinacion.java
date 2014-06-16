/*
 * Created on Aug 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.flexion.declinacion;


/**
 * @author GDiaz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ProblemaSugiriendoDeclinacion extends RuntimeException{
	private String nominativo;
	private String genitivo;
	
	
    public ProblemaSugiriendoDeclinacion(String mensaje, String nominativo, String genitivo){
    	super(mensaje);
    	this.nominativo=nominativo;
    	this.genitivo=genitivo;
    	
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
}
