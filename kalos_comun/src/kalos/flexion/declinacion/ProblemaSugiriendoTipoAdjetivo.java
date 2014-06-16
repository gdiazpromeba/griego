package kalos.flexion.declinacion;

public class ProblemaSugiriendoTipoAdjetivo extends RuntimeException {

	private String masculino;
	private String femenino;
	private String neutro;
	
	
	public ProblemaSugiriendoTipoAdjetivo(String mensaje, String masculino, String femenino, String neutro){
		super(mensaje);
		this.masculino=masculino;
		this.femenino=femenino;
		this.neutro=neutro;
	}
	

	/**
	 * @return Returns the femenino.
	 */
	public String getFemenino() {
		return femenino;
	}


	/**
	 * @param femenino The femenino to set.
	 */
	public void setFemenino(String femenino) {
		this.femenino = femenino;
	}


	/**
	 * @return Returns the masculino.
	 */
	public String getMasculino() {
		return masculino;
	}


	/**
	 * @param masculino The masculino to set.
	 */
	public void setMasculino(String masculino) {
		this.masculino = masculino;
	}


	/**
	 * @return Returns the neutro.
	 */
	public String getNeutro() {
		return neutro;
	}


	/**
	 * @param neutro The neutro to set.
	 */
	public void setNeutro(String neutro) {
		this.neutro = neutro;
	}

}
