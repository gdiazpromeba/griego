package kalos.beans;

/**
 * representa un tipo jerárquico, con una valor numérico entero manejable, y
 * una clave de recurso String para la descripción
 * @author Gonzalo
 *
 */
public class TipoJerarquico {
   
	/**
	 * la clave primaroa
	 */
	private String id;
	
	/**
	 * un valor más manejable que la clave primaria, pero también único
	 */
	private int valorEntero;
	
	/**
	 * referencia al tipo adjetivo padre
	 */
	private String padreId;
	
	/**
	 * clave del recurso String que describe a este tipo de adjetivo
	 */
	private String desClave;

	public String getDesClave() {
		return desClave;
	}

	public void setDesClave(String desClave) {
		this.desClave = desClave;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPadreId() {
		return padreId;
	}

	public void setPadreId(String padreId) {
		this.padreId = padreId;
	}

	public int getValorEntero() {
		return valorEntero;
	}

	public void setValorEntero(int valorEntero) {
		this.valorEntero = valorEntero;
	}
	
	public boolean equals(TipoJerarquico tij){
	    if (tij!=null && id!=null){
	        return tij.id.equals(id);
	    }else if (tij.id==null || id==null){
	        return true;
	    }else{
	        return false;
	    }
	}


}
