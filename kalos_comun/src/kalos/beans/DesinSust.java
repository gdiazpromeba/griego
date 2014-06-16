package kalos.beans;

import kalos.enumeraciones.Acento;
import kalos.enumeraciones.AcentoConcuerda;
import kalos.enumeraciones.Caso;
import kalos.enumeraciones.Contraccion;
import kalos.enumeraciones.Numero;
import kalos.enumeraciones.OrigenTema;
import kalos.enumeraciones.PosicionConcuerda;

/**
 * bean que contiene una terminación nominal
 * @author gdiaz
 *
 */
public class DesinSust {
	private String id;
	private int subindice;
	private String desinencia;
	private int silaba;
	private Acento acento;
	private Caso caso;
	private Numero numero;
	private OrigenTema origenTema;
	private Contraccion contraccion;
	private int tipoSustantivo;
	private String tipoSustantivoId;
	private String tipoSustDesClave;
	private AcentoConcuerda acentoConcuerda;
	private PosicionConcuerda posicionConcuerda;
	private String regexDesinencia;
	private Integer orden;
	private int ordenPorDefecto;
	private boolean aceptaDieresis;
	private String regexDieresis;
	
	
	/**
	 * @return Returns the acento.
	 */
	public Acento getAcento() {
		return acento;
	}
	/**
	 * @param acento The acento to set.
	 */
	public void setAcento(Acento acento) {
		this.acento = acento;
	}
	/**
	 * @return Returns the acentoConcuerda.
	 */
	public AcentoConcuerda getAcentoConcuerda() {
		return acentoConcuerda;
	}
	/**
	 * @param acentoConcuerda The acentoConcuerda to set.
	 */
	public void setAcentoConcuerda(AcentoConcuerda acentoConcuerda) {
		this.acentoConcuerda = acentoConcuerda;
	}
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
	 * @return Returns the contraccion.
	 */
	public Contraccion getContraccion() {
		return contraccion;
	}
	/**
	 * @param contraccion The contraccion to set.
	 */
	public void setContraccion(Contraccion contraccion) {
		this.contraccion = contraccion;
	}
	/**
	 * @return Returns the desinencia.
	 */
	public String getDesinencia() {
		return desinencia;
	}
	/**
	 * @param desinencia The desinencia to set.
	 */
	public void setDesinencia(String desinencia) {
		this.desinencia = desinencia;
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
	 * @return Returns the orden.
	 */
	public Integer getOrden() {
		return orden;
	}
	/**
	 * @param orden The orden to set.
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	/**
	 * @return Returns the ordenPorDefecto.
	 */
	public int getOrdenPorDefecto() {
		return ordenPorDefecto;
	}
	/**
	 * @param ordenPorDefecto The ordenPorDefecto to set.
	 */
	public void setOrdenPorDefecto(int ordenPorDefecto) {
		this.ordenPorDefecto = ordenPorDefecto;
	}
	/**
	 * @return Returns the posicionConcuerda.
	 */
	public PosicionConcuerda getPosicionConcuerda() {
		return posicionConcuerda;
	}
	/**
	 * @param posicionConcuerda The posicionConcuerda to set.
	 */
	public void setPosicionConcuerda(PosicionConcuerda posicionConcuerda) {
		this.posicionConcuerda = posicionConcuerda;
	}
	/**
	 * @return Returns the regexDesinencia.
	 */
	public String getRegexDesinencia() {
		return regexDesinencia;
	}
	/**
	 * @param regexDesinencia The regexDesinencia to set.
	 */
	public void setRegexDesinencia(String regexDesinencia) {
		this.regexDesinencia = regexDesinencia;
	}
	/**
	 * @return Returns the silaba.
	 */
	public int getSilaba() {
		return silaba;
	}
	/**
	 * @param silaba The silaba to set.
	 */
	public void setSilaba(int silaba) {
		this.silaba = silaba;
	}
	/**
	 * @return Returns the subindice.
	 */
	public int getSubindice() {
		return subindice;
	}
	/**
	 * @param subindice The subindice to set.
	 */
	public void setSubindice(int subindice) {
		this.subindice = subindice;
	}

	/**
	 * @return Returns the tipoSustantivo.
	 */
	public int getTipoSustantivo() {
		return tipoSustantivo;
	}
	/**
	 * @param tipoSustantivo The tipoSustantivo to set.
	 */
	public void setTipoSustantivo(int tipoSustantivo) {
		this.tipoSustantivo = tipoSustantivo;
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
	 * @return Returns the tipoSustDesClave.
	 */
	public String getTipoSustDesClave() {
		return tipoSustDesClave;
	}
	/**
	 * @param tipoSustDesClave The tipoSustDesClave to set.
	 */
	public void setTipoSustDesClave(String tipoSustDesClave) {
		this.tipoSustDesClave = tipoSustDesClave;
	}
	/**
	 * @return Returns the tipoSustantivoId.
	 */
	public String getTipoSustantivoId() {
		return tipoSustantivoId;
	}
	/**
	 * @param tipoSustantivoId The tipoSustantivoId to set.
	 */
	public void setTipoSustantivoId(String tipoSustantivoId) {
		this.tipoSustantivoId = tipoSustantivoId;
	}
    public OrigenTema getOrigenTema() {
        return this.origenTema;
    }
    public void setOrigenTema(OrigenTema origenTema) {
        this.origenTema = origenTema;
    }
    public boolean isAceptaDieresis() {
        return this.aceptaDieresis;
    }
    public void setAceptaDieresis(boolean aceptaDieresis) {
        this.aceptaDieresis = aceptaDieresis;
    }
    public String getRegexDieresis() {
        return this.regexDieresis;
    }
    public void setRegexDieresis(String regexDieresis) {
        this.regexDieresis = regexDieresis;
    }
}
