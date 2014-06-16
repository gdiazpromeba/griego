package kalos.beans;

import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.Persona;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.Voz;

public class IrrVerboIndividual implements IBeanIrrVerbo {
	private String id;

	private String verboId;

	private String forma;

	private Persona persona;

	private Particularidad partic;

	private int subPart;

	private Modo modo;

	private Tiempo tiempo;

	private FuerteDebil fuerte;

	private Voz voz;

	private int ordenProceso;
	
	private boolean compuesto;
	
	/**
	 * usado para los verbos que pueden componerse, indica si al agregarle preposiciones el acento 
	 * debería quedar en el mismp lugar
	 */
	private boolean respetaAcento;

	/**
	 * @return Returns the forma.
	 */
	public String getForma() {
		return forma;
	}

	/**
	 * @param forma
	 *            The forma to set.
	 */
	public void setForma(String forma) {
		this.forma = forma;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#getFuerte()
	 */
	public FuerteDebil getFuerte() {
		return fuerte;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#setFuerte(kalos.enumeraciones.FuerteDebil)
	 */
	public void setFuerte(FuerteDebil fuerte) {
		this.fuerte = fuerte;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#getId()
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#getModo()
	 */
	public Modo getModo() {
		return modo;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#setModo(kalos.enumeraciones.Modo)
	 */
	public void setModo(Modo modo) {
		this.modo = modo;
	}

	/**
	 * @return Returns the ordenProceso.
	 */
	public int getOrdenProceso() {
		return ordenProceso;
	}

	/**
	 * @param ordenProceso
	 *            The ordenProceso to set.
	 */
	public void setOrdenProceso(int ordenProceso) {
		this.ordenProceso = ordenProceso;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#getPartic()
	 */
	public Particularidad getPartic() {
		return partic;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#setPartic(kalos.enumeraciones.Particularidad)
	 */
	public void setPartic(Particularidad partic) {
		this.partic = partic;
	}

	/**
	 * @return Returns the persona.
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            The persona to set.
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#getSubPart()
	 */
	public int getSubPart() {
		return subPart;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#setSubPart(int)
	 */
	public void setSubPart(int subPart) {
		this.subPart = subPart;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#getTiempo()
	 */
	public Tiempo getTiempo() {
		return tiempo;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#setTiempo(kalos.enumeraciones.Tiempo)
	 */
	public void setTiempo(Tiempo tiempo) {
		this.tiempo = tiempo;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#getVerboId()
	 */
	public String getVerboId() {
		return verboId;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#setVerboId(java.lang.String)
	 */
	public void setVerboId(String verboId) {
		this.verboId = verboId;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#getVoz()
	 */
	public Voz getVoz() {
		return voz;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#setVoz(kalos.enumeraciones.Voz)
	 */
	public void setVoz(Voz voz) {
		this.voz = voz;
	}



	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#isRespetaAcento()
	 */
	public boolean isRespetaAcento() {
		return respetaAcento;
	}

	/* (non-Javadoc)
	 * @see kalos.beans.IBeanIrrVerbo#setRespetaAcento(boolean)
	 */
	public void setRespetaAcento(boolean respetaAcento) {
		this.respetaAcento = respetaAcento;
	}

	public boolean isCompuesto() {
		return compuesto;
	}

	public void setCompuesto(boolean compuesto) {
		this.compuesto = compuesto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IrrVerboIndividual other = (IrrVerboIndividual) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
