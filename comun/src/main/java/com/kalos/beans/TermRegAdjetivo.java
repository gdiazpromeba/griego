package com.kalos.beans;


/**
 * Terminación de un adjetivo en proceso de sufrir análisis morfológico.
 * @author Manuela
 *
 */
public class TermRegAdjetivo extends TermRegSustantivo {

    private boolean transformadoAMasculino;

    public boolean isTransformadoAMasculino() {
        return transformadoAMasculino;
    }

    public void setTransformadoAMasculino(boolean transformadoAMasculino) {
        this.transformadoAMasculino = transformadoAMasculino;
        this.hashCodePropio=null;
    }
    
    protected int calculaHashCode(){
  	  StringBuffer sb=new StringBuffer();
  	  sb.append(getTipoSustantivo());
  	  sb.append("|");
  	  sb.append(getCaso().name());
  	  sb.append("|");
  	  sb.append(getNumero().name());
  	  sb.append("|");
  	  sb.append(getOrigenTema().name());
  	  sb.append("|");
  	  sb.append(getSubindice());
  	  sb.append("|");
  	  sb.append(getTerminacion());
  	  sb.append("|");
  	  sb.append(getFormaOriginal());
  	  sb.append("|");
  	  sb.append(isExContraccion());
  	  sb.append("|");
  	  sb.append(getAcento().name());
  	  sb.append("|");
  	  sb.append(getSilaba().name());
  	  sb.append("|");
  	  sb.append(getTiposHoja());
  	  sb.append("|");
  	  sb.append(getNominativoPropuesto());
  	  sb.append("|");
  	  sb.append(getGenitivoPropuesto());
  	  sb.append("|");
  	  sb.append(isTransformadoAMasculino());
  	  return sb.toString().hashCode();
    }
    
    
    public TermRegAdjetivo clona(){
        TermRegAdjetivo nuevo = new TermRegAdjetivo();
        nuevo.setAcento(getAcento());
        nuevo.setAcentoConcuerda(getAcentoConcuerda());
        nuevo.setAceptaDieresis(isAceptaDieresis());
        nuevo.setCaso(getCaso());
        nuevo.setExContraccion(isExContraccion());
        nuevo.setFormaOriginal(getFormaOriginal());
        nuevo.setGenitivoPropuesto(getGenitivoPropuesto());
        nuevo.setIdTipoSustantivo(getIdTipoSustantivo());
        nuevo.setNominativoPropuesto(getNominativoPropuesto());
        nuevo.setNumero(getNumero());
        nuevo.setOrigenTema(getOrigenTema());
        nuevo.setPosicionConcuerda(getPosicionConcuerda());
        nuevo.setRegExDesinencia(getRegExDesinencia());
        nuevo.setRegexDieresis(getRegexDieresis());
        nuevo.setSilaba(getSilaba());
        nuevo.setSubindice(getSubindice());
        nuevo.setTerminacion(getTerminacion());
        nuevo.setTerminacionPendienteRevision(getTerminacionPendienteRevision());
        nuevo.setTiposHoja(getTiposHoja());
        nuevo.setTipoSustantivo(getTipoSustantivo());
        nuevo.setTransformadoAMasculino(transformadoAMasculino);
        return nuevo;
    }
    
    /* (non-Javadoc)
   * @see com.kalos.beans.TermRegNominal#equals(java.lang.Object)
   */
  public boolean equals(Object obj){
  	  if (obj instanceof TermRegAdjetivo){
  		  return hashCode()==obj.hashCode();
  	  }
  	  return false;
    }
    
}
