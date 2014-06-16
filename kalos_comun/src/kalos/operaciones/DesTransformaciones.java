/*
 * Created on Aug 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.operaciones;

import kalos.enumeraciones.Aumento;

/**
 * Se usa como valor de retorno de los desaumentos/desreduplicaciones
 */
public class DesTransformaciones {
	private String original;
	private String desReduplicada;
	private String desAumentada;
	private Aumento aumento;
	private boolean reduplicacion;
	private boolean regular;
	
	//hash
	Integer hashPropio;
	
	public int hashCode(){
		if (hashPropio==null){
			hashPropio=calculaHashCode();
		}
		return hashPropio;
	}
	
	public boolean equals(Object obj){
		if (obj instanceof DesTransformaciones){
			if (obj.hashCode()==hashCode()){
				return true;
			}
		}
		return false;
	}
    
    @Override
    public String toString(){
        StringBuffer sb=new StringBuffer("DesTransformaciones: ");
        sb.append(" redup=" + reduplicacion + " ");
        sb.append(" aum:" + aumento + " ");
        if (desReduplicada!=null){
            sb.append("desRed=" + OpPalabras.strCompletoABeta(desReduplicada) + " "); 
        }else{
            sb.append("desRed=nulo  ");
        }
        if (desAumentada!=null){
            sb.append("desAum=" + OpPalabras.strCompletoABeta(desAumentada) + " ");
        }else{
            sb.append("desAum=nulo  ");
        }
        return sb.toString();
    }
	
	private int calculaHashCode(){
		StringBuffer sb=new StringBuffer();
		sb.append(original);
		sb.append("|");
		sb.append(desReduplicada);
		sb.append("|");
		sb.append(desAumentada);
		sb.append("|");
		sb.append(aumento);
		sb.append("|");
		sb.append(reduplicacion);
		sb.append("|");
		sb.append(regular);
		return sb.toString().hashCode();
	}
	


	/**
	 * @param temaOriginal
	 * @param temaResultado
	 * @param aumento
	 * @param reduplicacion
	 */
	public DesTransformaciones(String temaOriginal, String desAumentada, String desReduplicada,
			                   Aumento aumento, boolean reduplicacion, boolean regular){
		this.original=temaOriginal;
		this.desAumentada=desAumentada;
		this.desReduplicada=desReduplicada;
		this.aumento=aumento;
		this.reduplicacion=reduplicacion;
		this.setRegular(regular);
	}
	
	
	
    public DesTransformaciones clona(){
        return new DesTransformaciones(original, desAumentada, desReduplicada, aumento, reduplicacion, regular);
    }
	
	/**
	 * @return Returns the aumento.
	 */
	public Aumento getAumento() {
		return aumento;
	}
	/**
	 * @param aumento The aumento to set.
	 */
	public void setAumento(Aumento aumento) {
		this.aumento = aumento;
	}
	/**
	 * @return Returns the desAumentada.
	 */
	public String getDesAumentada() {
		return desAumentada;
	}
	/**
	 * @param desAumentada The desAumentada to set.
	 */
	public void setDesAumentada(String desAumentada) {
		this.desAumentada = desAumentada;
	}
	/**
	 * @return Returns the desReduplicada.
	 */
	public String getDesReduplicada() {
		return desReduplicada;
	}
	/**
	 * @param desReduplicada The desReduplicada to set.
	 */
	public void setDesReduplicada(String desReduplicada) {
		this.desReduplicada = desReduplicada;
	}
	/**
	 * @return Returns the original.
	 */
	public String getOriginal() {
		return original;
	}
	/**
	 * @param original The original to set.
	 */
	public void setOriginal(String original) {
		this.original = original;
	}
	/**
	 * @return Returns the reduplicacion.
	 */
	public boolean isReduplicacion() {
		return reduplicacion;
	}
	/**
	 * @param reduplicacion The reduplicacion to set.
	 */
	public void setReduplicacion(boolean reduplicacion) {
		this.reduplicacion = reduplicacion;
	}

	/**
	 * @param regular The regular to set.
	 */
	public void setRegular(boolean regular) {
		this.regular = regular;
	}

	/**
	 * @return Returns the regular.
	 */
	public boolean isRegular() {
		return regular;
	}
	
	
	

	
	
}
