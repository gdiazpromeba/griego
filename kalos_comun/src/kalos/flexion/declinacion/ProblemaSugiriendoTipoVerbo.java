package kalos.flexion.declinacion;

public class ProblemaSugiriendoTipoVerbo extends RuntimeException {

	private String verbo;
	private String sufijo;
	
	
	public ProblemaSugiriendoTipoVerbo(String mensaje, String verbo, String sufijo){
		super(mensaje);
		this.verbo=verbo;
		this.sufijo=sufijo;
	}


	/**
	 * @return Returns the sufijo.
	 */
	public String getSufijo() {
		return sufijo;
	}


	/**
	 * @param sufijo The sufijo to set.
	 */
	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
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
