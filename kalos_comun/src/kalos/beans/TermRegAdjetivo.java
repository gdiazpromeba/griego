package kalos.beans;


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
  	  sb.append(getCaso());
  	  sb.append("|");
  	  sb.append(getNumero());
  	  sb.append("|");
  	  sb.append(getOrigenTema());
  	  sb.append("|");
  	  sb.append(getSubindice());
  	  sb.append("|");
  	  sb.append(getTerminacion());
  	  sb.append("|");
  	  sb.append(getFormaOriginal());
  	  sb.append("|");
  	  sb.append(isExContraccion());
  	  sb.append("|");
  	  sb.append(getAcento());
  	  sb.append("|");
  	  sb.append(getSilaba());
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
    
    

    
    /* (non-Javadoc)
   * @see kalos.beans.TermRegNominal#equals(java.lang.Object)
   */
  public boolean equals(Object obj){
  	  if (obj instanceof TermRegAdjetivo){
  		  return hashCode()==obj.hashCode();
  	  }
  	  return false;
    }
    
}
