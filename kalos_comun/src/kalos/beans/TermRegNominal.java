package kalos.beans;

import kalos.enumeraciones.Acento;
import kalos.enumeraciones.Caso;
import kalos.enumeraciones.Numero;
import kalos.enumeraciones.OrigenTema;

public interface TermRegNominal {

	int hashCode();

	boolean equals(Object obj);

	/**
	 * @return Returns the acento.
	 */
	Acento getAcento();

	/**
	 * @param acento The acento to set.
	 */
	void setAcento(Acento acento);

	/**
	 * @return Returns the acentoConcuerda.
	 */
	int getAcentoConcuerda();

	/**
	 * @param acentoConcuerda The acentoConcuerda to set.
	 */
	void setAcentoConcuerda(int acentoConcuerda);

	/**
	 * @return Returns the caso.
	 */
	Caso getCaso();

	/**
	 * @param caso The caso to set.
	 */
	void setCaso(Caso caso);

	
	OrigenTema getOrigenTema();
	
	void setOrigenTema(OrigenTema origenTema);


	/**
	 * @return Returns the exContraccion.
	 */
	boolean isExContraccion();

	/**
	 * @param exContraccion The exContraccion to set.
	 */
	void setExContraccion(boolean exContraccion);

	/**
	 * @return Returns the idTipoSustantivo.
	 */
	int getIdTipoSustantivo();

	/**
	 * @param idTipoSustantivo The idTipoSustantivo to set.
	 */
	void setIdTipoSustantivo(int idTipoSustantivo);

	/**
	 * @return Returns the numero.
	 */
	Numero getNumero();

	/**
	 * @param numero The numero to set.
	 */
	void setNumero(Numero numero);

	/**
	 * @return Returns the posicionConcuerda.
	 */
	int getPosicionConcuerda();

	/**
	 * @param posicionConcuerda The posicionConcuerda to set.
	 */
	void setPosicionConcuerda(int posicionConcuerda);

	/**
	 * @return Returns the regExDesinencia.
	 */
	String getRegExDesinencia();

	/**
	 * @param regExDesinencia The regExDesinencia to set.
	 */
	void setRegExDesinencia(String regExDesinencia);

	/**
	 * @return Returns the silaba.
	 */
	int getSilaba();

	/**
	 * @param silaba The silaba to set.
	 */
	void setSilaba(int silaba);

	/**
	 * @return Returns the subindice.
	 */
	int getSubindice();

	/**
	 * @param subindice The subindice to set.
	 */
	void setSubindice(int subindice);

	/**
	 * @return Returns the terminacion.
	 */
	String getTerminacion();

	/**
	 * @param terminacion The terminacion to set.
	 */
	void setTerminacion(String terminacion);

	/**
	 * @return Returns the tiposHoja.
	 */
	String getTiposHoja();

	/**
	 * @param tiposHoja The tiposHoja to set.
	 */
	void setTiposHoja(String tiposHoja);

	/**
	 * @return Returns the tipoSustantivo.
	 */
	int getTipoSustantivo();

	/**
	 * @param tipoSustantivo The tipoSustantivo to set.
	 */
	void setTipoSustantivo(int tipoSustantivo);

	/**
	 * @return Returns the formaOriginal.
	 */
	String getFormaOriginal();

	/**
	 * @param formaOriginal The formaOriginal to set.
	 */
	void setFormaOriginal(String formaOriginal);

	/**
	 * @return Returns the terminacionPendienteRevision.
	 */
	TermRegNominal getTerminacionPendienteRevision();

	/**
	 * @param terminacionPendienteRevision The terminacionPendienteRevision to set.
	 */
	void setTerminacionPendienteRevision(TermRegNominal termRegNominal);

	/**
	 * @return Returns the genitivoPropuesto.
	 */
	String getGenitivoPropuesto();

	/**
	 * @param genitivoPropuesto The genitivoPropuesto to set.
	 */
	void setGenitivoPropuesto(String genitivoPropuesto);

	/**
	 * @return Returns the nominativoPropuesto.
	 */
	String getNominativoPropuesto();

	/**
	 * @param nominativoPropuesto The nominativoPropuesto to set.
	 */
	void setNominativoPropuesto(String nominativoPropuesto);


}